package br.com.copergas.portalchamados.controller.ticket;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import br.com.copergas.portalchamados.controller.util.ComumController;
import br.com.copergas.portalchamados.model.comum.excecoes.ProjetoException;
import br.com.copergas.portalchamados.model.ticket.OrigemChamado;
import br.com.copergas.portalchamados.model.ticket.Prioridade;
import br.com.copergas.portalchamados.model.ticket.Setor;
import br.com.copergas.portalchamados.model.ticket.Sistema;
import br.com.copergas.portalchamados.model.ticket.Status;
import br.com.copergas.portalchamados.model.ticket.StatusCopergas;
import br.com.copergas.portalchamados.model.ticket.StatusFornecedor;
import br.com.copergas.portalchamados.model.ticket.Ticket;
import br.com.copergas.portalchamados.model.ticket.TicketCategoria;
import br.com.copergas.portalchamados.model.ticket.TicketSubCategoria;
import br.com.copergas.portalchamados.model.ticket.TipoAtendimento;
import br.com.copergas.portalchamados.model.ticket.TipoChamado;
import br.com.copergas.portalchamados.model.usuario.Usuario;
import br.com.copergas.portalchamados.model.util.Constantes;
import br.com.copergas.portalchamados.model.util.Util;

/**
 * @author Roberto Alencar
 * 
 */
@ManagedBean
@ViewScoped
public class TicketController extends ComumController implements Serializable {
    
    private static final long serialVersionUID = 3488145960995467998L;

    //Gráficos
    private CartesianChartModel graficoAbertosFechadosSemana;
    private CartesianChartModel graficoAbertosFechadosMes;
    private PieChartModel graficoPorTipoAtendimento;
    private PieChartModel graficoPorSetor;
    private PieChartModel graficoPorPrioridade;
    private PieChartModel graficoPorOrigemChamado;
    private PieChartModel graficoPorTipo;
    private PieChartModel graficoPorSistema;
    private PieChartModel graficoPorStatusCopergas;
    private PieChartModel graficoPorStatusFornecedor;
    private PieChartModel graficoPorDataVencimento;
    private PieChartModel graficoSLA;
    private Date dataInicialRelatorio;
    private Date dataFinalRelatorio;
    private int mesReferencia;
    private int semanaReferencia;
    
    //Objetos de controle
    private Ticket ticketSelecionado;
    private Ticket ticket;
    private Date dataInicial;
    private Date dataFinal;
    private Date dataInicialVencimento;
    private Date dataFinalVencimento;
    private Date dataInicialFechamento;
    private Date dataFinalFechamento;
    private boolean ehCadastroUsuario;
    
    //Listas
    private List<Ticket> listaTicket;
    private List<Status> listaStatus;
    private List<Sistema> listaSistema;
    private List<StatusCopergas> listaStatusCopergas;
    private List<StatusFornecedor> listaStatusFornecedor;
    private List<Setor> listaSetor;
    private List<Prioridade> listaPrioridade;
    private List<TicketCategoria> listaCategoria;
    private List<TicketSubCategoria> listaSubCategoria;
    private List<OrigemChamado> listaOrigemChamado;
    private List<TipoChamado> listaTipoChamado;
    private List<TipoAtendimento> listaTipoAtendimento;
    private List<Usuario> listaUsuario;
    
    public TicketController() throws ProjetoException {

	Ticket ticket = null;
	
	if (getAttributeSession(ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA) != null) {
	    if (getAttributeSession(ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA) instanceof Ticket) {
		ticketSelecionado = (Ticket) getAttributeSession(ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA);
		this.setTicket(ticketSelecionado);
		ticket = ticketSelecionado;
	    }
	} else {
	    ticket = fachada.criarTicket();
	    ticket.setStatus(fachada.obterStatus(Status.VALOR_STATUS_OPEN));
	    ticket.setUsuario(fachada.obterUsuario(Usuario.VALOR_USUARIO_UNASSIGNED));
	    this.setTicket(ticket);
	}
	
	setListaCategoria(fachada.listarCategoria(Boolean.TRUE));
	setListaSubCategoria(fachada.listarSubCategoria(Boolean.TRUE));
	setListaStatus(fachada.listarStatus(Boolean.TRUE));
	setListaSistema(fachada.listarSistema(Boolean.TRUE));
	setListaTipoAtendimento(fachada.listarTipoAtendimento(Boolean.TRUE));
	setListaPrioridade(fachada.listarPrioridade(Boolean.TRUE));
	setListaStatusCopergas(fachada.listarStatusCopergas(Boolean.TRUE));
	setListaStatusFornecedor(fachada.listarStatusFornecedor(Boolean.TRUE));
	setListaSetor(fachada.listarSetor(Boolean.TRUE));
	setListaOrigemChamado(fachada.listarOrigemChamado(Boolean.TRUE));
	setListaTipoChamado(fachada.listarTipoChamado(Boolean.TRUE));
	setListaUsuario(fachada.listarUsuario(new HashMap<String, Object>()));
	
	HashMap<String, Object> filtro = new HashMap<String, Object>();
	filtro.put(Ticket.ATRIBUTO_NUMERO, ticket.getNumero());
	filtro.put(Ticket.ATRIBUTO_USUARIO, ticket.getUsuario().getChavePrimaria());
	filtro.put(Ticket.ATRIBUTO_STATUS, ticket.getStatus().getChavePrimaria());
	this.setListaTicket(fachada.listarTicket(filtro));
	
	setHabilitaEdicao(Boolean.TRUE);
	this.mesReferencia = Calendar.getInstance().get(Calendar.MONTH);
	this.semanaReferencia = Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);
	gerarGraficosPorCategorias();
	gerarGraficosProdutividades();

	carregaCamposTelaAlteracao();
    }

    
    // Getters and Setters
    
    
    public Date getDataInicialFechamento() {
        return dataInicialFechamento;
    }
    public void setDataInicialFechamento(Date dataInicialFechamento) {
        this.dataInicialFechamento = dataInicialFechamento;
    }
    public Date getDataFinalFechamento() {
        return dataFinalFechamento;
    }
    public void setDataFinalFechamento(Date dataFinalFechamento) {
        this.dataFinalFechamento = dataFinalFechamento;
    }
    public PieChartModel getGraficoPorDataVencimento() {
        return graficoPorDataVencimento;
    }
    public void setGraficoPorDataVencimento(PieChartModel graficoPorDataVencimento) {
        this.graficoPorDataVencimento = graficoPorDataVencimento;
    }
    public Date getDataInicialVencimento() {
        return dataInicialVencimento;
    }
    public void setDataInicialVencimento(Date dataInicialVencimento) {
        this.dataInicialVencimento = dataInicialVencimento;
    }
    public Date getDataFinalVencimento() {
        return dataFinalVencimento;
    }
    public void setDataFinalVencimento(Date dataFinalVencimento) {
        this.dataFinalVencimento = dataFinalVencimento;
    }
    public int getMesReferencia() {
        return mesReferencia;
    }
    public void setMesReferencia(int mesReferencia) {
        this.mesReferencia = mesReferencia;
    }
    public int getSemanaReferencia() {
        return semanaReferencia;
    }
    public void setSemanaReferencia(int semanaReferencia) {
        this.semanaReferencia = semanaReferencia;
    }
    public CartesianChartModel getGraficoAbertosFechadosSemana() {
        return graficoAbertosFechadosSemana;
    }
    public void setGraficoAbertosFechadosSemana(CartesianChartModel graficoAbertosFechadosSemana) {
        this.graficoAbertosFechadosSemana = graficoAbertosFechadosSemana;
    }
    public CartesianChartModel getGraficoAbertosFechadosMes() {
        return graficoAbertosFechadosMes;
    }
    public void setGraficoAbertosFechadosMes(CartesianChartModel graficoAbertosFechadosMes) {
        this.graficoAbertosFechadosMes = graficoAbertosFechadosMes;
    }
    public PieChartModel getGraficoPorTipo() {
        return graficoPorTipo;
    }
    public void setGraficoPorTipo(PieChartModel graficoPorTipo) {
        this.graficoPorTipo = graficoPorTipo;
    }
    public PieChartModel getGraficoPorSistema() {
        return graficoPorSistema;
    }
    public void setGraficoPorSistema(PieChartModel graficoPorSistema) {
        this.graficoPorSistema = graficoPorSistema;
    }
    public PieChartModel getGraficoPorStatusCopergas() {
        return graficoPorStatusCopergas;
    }
    public void setGraficoPorStatusCopergas(PieChartModel graficoPorStatusCopergas) {
        this.graficoPorStatusCopergas = graficoPorStatusCopergas;
    }
    public PieChartModel getGraficoPorStatusFornecedor() {
        return graficoPorStatusFornecedor;
    }
    public void setGraficoPorStatusFornecedor(PieChartModel graficoPorStatusFornecedor) {
        this.graficoPorStatusFornecedor = graficoPorStatusFornecedor;
    }
    public PieChartModel getGraficoPorOrigemChamado() {
        return graficoPorOrigemChamado;
    }
    public void setGraficoPorOrigemChamado(PieChartModel graficoPorOrigemChamado) {
        this.graficoPorOrigemChamado = graficoPorOrigemChamado;
    }
    public PieChartModel getGraficoPorPrioridade() {
        return graficoPorPrioridade;
    }
    public void setGraficoPorPrioridade(PieChartModel graficoPorPrioridade) {
        this.graficoPorPrioridade = graficoPorPrioridade;
    }
    public PieChartModel getGraficoPorSetor() {
        return graficoPorSetor;
    }
    public void setGraficoPorSetor(PieChartModel graficoPorSetor) {
        this.graficoPorSetor = graficoPorSetor;
    }
    public PieChartModel getGraficoPorTipoAtendimento() {
        return graficoPorTipoAtendimento;
    }
    public void setGraficoPorTipoAtendimento(PieChartModel graficoPorTipoAtendimento) {
        this.graficoPorTipoAtendimento = graficoPorTipoAtendimento;
    }
    public Ticket getTicketSelecionado() {
	return ticketSelecionado;
    }
    public void setTicketSelecionado(Ticket ticketSelecionado) {
	this.ticketSelecionado = ticketSelecionado;
    }
    public Ticket getTicket() {
	return ticket;
    }
    public void setTicket(Ticket ticket) {
	this.ticket = ticket;
    }
    public List<Ticket> getListaTicket() {
	return listaTicket;
    }
    public void setListaTicket(List<Ticket> listaTicket) {
	this.listaTicket = listaTicket;
    }
    public Date getDataInicial() {
	return dataInicial;
    }
    public void setDataInicial(Date dataInicial) {
	this.dataInicial = dataInicial;
    }
    public Date getDataFinal() {
	return dataFinal;
    }
    public void setDataFinal(Date dataFinal) {
	this.dataFinal = dataFinal;
    }
    public List<Status> getListaStatus() {
        return listaStatus;
    }
    public void setListaStatus(List<Status> listaStatus) {
        this.listaStatus = listaStatus;
    }
    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }
    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    public List<TicketCategoria> getListaCategoria() {
        return listaCategoria;
    }
    public void setListaCategoria(List<TicketCategoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }
    public List<TicketSubCategoria> getListaSubCategoria() {
        return listaSubCategoria;
    }
    public void setListaSubCategoria(List<TicketSubCategoria> listaSubCategoria) {
        this.listaSubCategoria = listaSubCategoria;
    }
    public List<Setor> getListaSetor() {
        return listaSetor;
    }
    public void setListaSetor(List<Setor> listaSetor) {
        this.listaSetor = listaSetor;
    }
    public List<OrigemChamado> getListaOrigemChamado() {
        return listaOrigemChamado;
    }
    public void setListaOrigemChamado(List<OrigemChamado> listaOrigemChamado) {
        this.listaOrigemChamado = listaOrigemChamado;
    }
    public List<TipoChamado> getListaTipoChamado() {
        return listaTipoChamado;
    }
    public void setListaTipoChamado(List<TipoChamado> listaTipoChamado) {
        this.listaTipoChamado = listaTipoChamado;
    }
    public List<Sistema> getListaSistema() {
        return listaSistema;
    }
    public void setListaSistema(List<Sistema> listaSistema) {
        this.listaSistema = listaSistema;
    }
    public List<StatusCopergas> getListaStatusCopergas() {
        return listaStatusCopergas;
    }
    public void setListaStatusCopergas(List<StatusCopergas> listaStatusCopergas) {
        this.listaStatusCopergas = listaStatusCopergas;
    }
    public List<StatusFornecedor> getListaStatusFornecedor() {
        return listaStatusFornecedor;
    }
    public void setListaStatusFornecedor(List<StatusFornecedor> listaStatusFornecedor) {
        this.listaStatusFornecedor = listaStatusFornecedor;
    }
    public List<Prioridade> getListaPrioridade() {
        return listaPrioridade;
    }
    public void setListaPrioridade(List<Prioridade> listaPrioridade) {
        this.listaPrioridade = listaPrioridade;
    }
    public List<TipoAtendimento> getListaTipoAtendimento() {
        return listaTipoAtendimento;
    }
    public void setListaTipoAtendimento(List<TipoAtendimento> listaTipoAtendimento) {
        this.listaTipoAtendimento = listaTipoAtendimento;
    }
    public PieChartModel getGraficoSLA() {
        return graficoSLA;
    }
    public void setGraficoSLA(PieChartModel graficoSLA) {
        this.graficoSLA = graficoSLA;
    }
    
    
    // Métodos de controle


    public void pesquisar() throws ProjetoException {

	Map<String, Object> filtro = new HashMap<String, Object>();
	filtro.put(Ticket.ATRIBUTO_NUMERO, this.getTicket().getNumero());
	filtro.put(Ticket.ATRIBUTO_TITULO, this.getTicket().getTitulo());
	filtro.put(Ticket.ATRIBUTO_STATUS, this.getTicket().getStatus());
	filtro.put(Ticket.ATRIBUTO_USUARIO, this.getTicket().getUsuario());
	filtro.put(Ticket.ATRIBUTO_SISTEMA, this.getTicket().getSistema());
	filtro.put(Ticket.ATRIBUTO_CATEGORIA, this.getTicket().getCategoria());
	filtro.put(Ticket.ATRIBUTO_SUB_CATEGORIA, this.getTicket().getSubCategoria());
	filtro.put(Ticket.ATRIBUTO_SETOR, this.getTicket().getSetor());
	filtro.put(Ticket.ATRIBUTO_ORIGEM_CHAMADO, this.getTicket().getOrigemChamado());
	filtro.put(Ticket.ATRIBUTO_DESCRICAO, this.getTicket().getDescricao());
	filtro.put(Ticket.ATRIBUTO_TIPO_CHAMADO, this.getTicket().getTipoChamado());
	filtro.put(Ticket.ATRIBUTO_STATUS_COPERGAS, this.getTicket().getStatusCopergas());
	filtro.put(Ticket.ATRIBUTO_STATUS_FORNECEDOR, this.getTicket().getStatusFornecedor());
	filtro.put(Ticket.ATRIBUTO_TIPO_ATENDIMENTO, this.getTicket().getTipoAtendimento());
	filtro.put(Ticket.ATRIBUTO_PRIORIDADE, this.getTicket().getPrioridade());
	filtro.put(Constantes.FILTRO_DATA_INICIAL, this.getDataInicial());
	filtro.put(Constantes.FILTRO_DATA_FINAL, this.getDataFinal());
	filtro.put(Constantes.FILTRO_DATA_INICIAL_VENCIMENTO, this.getDataInicialVencimento());
	filtro.put(Constantes.FILTRO_DATA_FINAL_VENCIMENTO, this.getDataFinalVencimento());
	filtro.put(Constantes.FILTRO_DATA_INICIAL_FECHAMENTO, this.getDataInicialFechamento());
	filtro.put(Constantes.FILTRO_DATA_FINAL_FECHAMENTO, this.getDataFinalFechamento());
	
	this.setListaTicket(fachada.listarTicket(filtro));
	gerarGraficosPorCategorias();
    }

    public void limpar() throws ProjetoException {

	setAttributeSession(ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA, null);
	setAttributeSession(ATRIBUTO_SESSAO_HABILITA_EDICAO, null);
	setAttributeSession(ATRIBUTO_SESSAO_EXIBE_BOTOES_ALTERACAO, null);
	this.setTicket(fachada.criarTicket());
	this.setTicketSelecionado(fachada.criarTicket());
	setDataInicial(null);
	setDataFinal(null);
	setDataInicialVencimento(null);
	setDataFinalVencimento(null);
	setDataInicialFechamento(null);
	setDataFinalFechamento(null);
	this.setListaTicket(fachada.listarTicket(new HashMap<String, Object>()));
	gerarGraficosPorCategorias();
    }
    
    public void limparTelaCadastro() throws ProjetoException {

	setAttributeSession(ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA, null);
	setAttributeSession(ATRIBUTO_SESSAO_HABILITA_EDICAO, null);
	setAttributeSession(ATRIBUTO_SESSAO_EXIBE_BOTOES_ALTERACAO, null);
	this.setTicket(fachada.criarTicket());
	this.setTicketSelecionado(fachada.criarTicket());
    }
    
    public String cancelar() throws ProjetoException {

	this.limpar();
	this.pesquisar();
	setAttributeSession(ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA, null);
	setAttributeSession(ATRIBUTO_SESSAO_HABILITA_EDICAO, null);
	setAttributeSession(ATRIBUTO_SESSAO_EXIBE_BOTOES_ALTERACAO, null);
	return Constantes.ARQUIVO_XHTML_PESQUISAR_TICKET;
    }
    
    public void gerarGraficosProdutividades() throws ProjetoException {
	
	gerarGraficoAbertosFechadosSemana();
	gerarGraficoAbertosFechadosMes();
    }
    
    public void gerarGraficosPorCategorias() throws ProjetoException {

	gerarGraficoPorTipoAtendimento();
	gerarGraficoPorSetor();
	gerarGraficoPorPrioridade();
	gerarGraficoPorOrigemChamado();
	gerarGraficoPorTipo();
	gerarGraficoPorSistema();
	gerarGraficoPorStatusCopergas();
	gerarGraficoPorStatusFornecedor();
	gerarGraficoPorDataVencimento();
	gerarGraficoSLA();
    }
    
    private void gerarGraficoSLA() {
	
	graficoSLA = new PieChartModel();  

	int qtdDentroSLA = 0;
	int qtdForaSLA = 0;
	int qtdNaoClassificado = 0;
	
	for (Ticket ticket : this.listaTicket) {
	    if (ticket.getUltrapassouSLA() != null) {
		if (ticket.getUltrapassouSLA() == Boolean.TRUE) {
		    qtdForaSLA = qtdForaSLA + 1;
		} else {
		    qtdDentroSLA = qtdDentroSLA + 1;
		} 
	    } else {
		qtdNaoClassificado = qtdNaoClassificado + 1;
	    }
	}
	
	graficoSLA.set("Pendente de classificação", qtdNaoClassificado);
	graficoSLA.set("Dentro do SLA", qtdDentroSLA); 
	graficoSLA.set("Fora do SLA", qtdForaSLA);
    }


    private void gerarGraficoPorStatusFornecedor() {
	
	graficoPorStatusFornecedor = new PieChartModel();  

	int qtdEncerrada = 0;
	int qtdNovaFuncionalidade = 0;
	int qtdAguardandoAtualizacao = 0;
	int qtdBug = 0;
	int qtdMelhoria = 0;
	int qtdNaoInformado = 0;
	
	for (Ticket ticket : this.listaTicket) {
	    if (ticket.getStatusFornecedor() != null) {
		if (StatusFornecedor.VALOR_STATUS_ENCERRADA.equals(ticket.getStatusFornecedor().getDescricao())) {
		    qtdEncerrada = qtdEncerrada + 1;
		} else if (StatusFornecedor.VALOR_STATUS_NOVA_FUNCIONALIDADE.equals(ticket.getStatusFornecedor().getDescricao())) {
		    qtdNovaFuncionalidade = qtdNovaFuncionalidade + 1;
		} else if (StatusFornecedor.VALOR_STATUS_AGUARDANDO_ATUALIZACAO.equals(ticket.getStatusFornecedor().getDescricao())) {
		    qtdAguardandoAtualizacao = qtdAguardandoAtualizacao + 1;
		} else if (StatusFornecedor.VALOR_STATUS_BUG.equals(ticket.getStatusFornecedor().getDescricao())) {
		    qtdBug = qtdBug + 1;
		} else if (StatusFornecedor.VALOR_STATUS_MELHORIA.equals(ticket.getStatusFornecedor().getDescricao())) {
		    qtdMelhoria = qtdMelhoria + 1;
		}
	    } else {
		qtdNaoInformado = qtdNaoInformado + 1;
	    }
	}
	
	graficoPorStatusFornecedor.set("Não Informado", qtdNaoInformado);
	graficoPorStatusFornecedor.set(StatusFornecedor.VALOR_STATUS_ENCERRADA, qtdEncerrada); 
	graficoPorStatusFornecedor.set(StatusFornecedor.VALOR_STATUS_NOVA_FUNCIONALIDADE, qtdNovaFuncionalidade);
	graficoPorStatusFornecedor.set(StatusFornecedor.VALOR_STATUS_AGUARDANDO_ATUALIZACAO, qtdAguardandoAtualizacao);
	graficoPorStatusFornecedor.set(StatusFornecedor.VALOR_STATUS_BUG, qtdBug);
	graficoPorStatusFornecedor.set(StatusFornecedor.VALOR_STATUS_MELHORIA, qtdMelhoria);
    }


    private void gerarGraficoPorStatusCopergas() {
	
	graficoPorStatusCopergas = new PieChartModel();  

	int qtdSemResposta = 0;
	int qtdRespostaPositiva = 0;
	int qtdAnalisandoEncerramento = 0;
	int qtdSolicitacaoEncerramento = 0;
	int qtdNaoInformado = 0;
	
	for (Ticket ticket : this.listaTicket) {
	    if (ticket.getStatusCopergas() != null) {
		if (StatusCopergas.VALOR_STATUS_SEM_RESPOSTA.equals(ticket.getStatusCopergas().getDescricao())) {
		    qtdSemResposta = qtdSemResposta + 1;
		} else if (StatusCopergas.VALOR_STATUS_RESPOSTA_POSITIVA.equals(ticket.getStatusCopergas().getDescricao())) {
		    qtdRespostaPositiva = qtdRespostaPositiva + 1;
		} else if (StatusCopergas.VALOR_STATUS_ANALISANDO_ENCERRAMENTO.equals(ticket.getStatusCopergas().getDescricao())) {
		    qtdAnalisandoEncerramento = qtdAnalisandoEncerramento + 1;
		} else if (StatusCopergas.VALOR_STATUS_SOLICITACAO_ENCERRAMENTO.equals(ticket.getStatusCopergas().getDescricao())) {
		    qtdSolicitacaoEncerramento = qtdSolicitacaoEncerramento + 1;
		} 
	    } else {
		qtdNaoInformado = qtdNaoInformado + 1;
	    }
	}
	
	graficoPorStatusCopergas.set("Não Informado", qtdNaoInformado);
	graficoPorStatusCopergas.set(StatusCopergas.VALOR_STATUS_SEM_RESPOSTA, qtdSemResposta); 
	graficoPorStatusCopergas.set(StatusCopergas.VALOR_STATUS_RESPOSTA_POSITIVA, qtdRespostaPositiva);
	graficoPorStatusCopergas.set(StatusCopergas.VALOR_STATUS_ANALISANDO_ENCERRAMENTO, qtdAnalisandoEncerramento);
	graficoPorStatusCopergas.set(StatusCopergas.VALOR_STATUS_SOLICITACAO_ENCERRAMENTO, qtdSolicitacaoEncerramento);
    }


    private void gerarGraficoPorSistema() {
	
	graficoPorSistema = new PieChartModel();  

	int qtdERPManutencao = 0;
	int qtdUnigas = 0;
	int qtdERPContasReceber = 0;
	int qtdERPContasPagar = 0;
	int qtdERPAdministrador = 0;
	int qtdSGM = 0;
	int qtdERPEstoque = 0;
	int qtdERPCompras = 0;
	int qtdERPIntegracao = 0;
	int qtdERPFluxoCaixa = 0;
	int qtdERPContabilidade = 0;
	int qtdERPOrcamento = 0;
	int qtdERPRH = 0;
	int qtdERPFiscal = 0;
	int qtdERPControleContratos = 0;
	int qtdERPTesouraria = 0;
	int qtdERPCobranca = 0;
	int qtdERPObras = 0;
	int qtdERPAtivoFixo = 0;
	int qtdNaoInformado = 0;
	
	for (Ticket ticket : this.listaTicket) {
	    if (ticket.getSistema() != null) {
		if (Sistema.VALOR_SISTEMA_ERP_MANUTENCAO.equals(ticket.getSistema().getDescricao())) {
		    qtdERPManutencao = qtdERPManutencao + 1;
		} else if (Sistema.VALOR_SISTEMA_UNIGAS.equals(ticket.getSistema().getDescricao())) {
		    qtdUnigas = qtdUnigas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_CONTAS_RECEBER.equals(ticket.getSistema().getDescricao())) {
		    qtdERPContasReceber = qtdERPContasReceber + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_CONTAS_PAGAR.equals(ticket.getSistema().getDescricao())) {
		    qtdERPContasPagar = qtdERPContasPagar + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_ADMINISTRADOR.equals(ticket.getSistema().getDescricao())) {
		    qtdERPAdministrador = qtdERPAdministrador + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_SGM.equals(ticket.getSistema().getDescricao())) {
		    qtdSGM = qtdSGM + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_ESTOQUE.equals(ticket.getSistema().getDescricao())) {
		    qtdERPEstoque = qtdERPEstoque + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_COMPRAS.equals(ticket.getSistema().getDescricao())) {
		    qtdERPCompras = qtdERPCompras + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_INTEGRACAO.equals(ticket.getSistema().getDescricao())) {
		    qtdERPIntegracao = qtdERPIntegracao + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_FLUXO_CAIXA.equals(ticket.getSistema().getDescricao())) {
		    qtdERPFluxoCaixa = qtdERPFluxoCaixa + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_CONTABILIDADE.equals(ticket.getSistema().getDescricao())) {
		    qtdERPContabilidade = qtdERPContabilidade + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_ORCAMENTO.equals(ticket.getSistema().getDescricao())) {
		    qtdERPOrcamento = qtdERPOrcamento + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_RH.equals(ticket.getSistema().getDescricao())) {
		    qtdERPRH = qtdERPRH + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_FISCAL.equals(ticket.getSistema().getDescricao())) {
		    qtdERPFiscal = qtdERPFiscal + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_CONTROLE_CONTRATOS.equals(ticket.getSistema().getDescricao())) {
		    qtdERPControleContratos = qtdERPControleContratos + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_TESOURARIA.equals(ticket.getSistema().getDescricao())) {
		    qtdERPTesouraria = qtdERPTesouraria + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_COBRANCA.equals(ticket.getSistema().getDescricao())) {
		    qtdERPCobranca = qtdERPCobranca + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_OBRAS.equals(ticket.getSistema().getDescricao())) {
		    qtdERPObras = qtdERPObras + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_ATIVO_FIXO.equals(ticket.getSistema().getDescricao())) {
		    qtdERPAtivoFixo = qtdERPAtivoFixo + 1;
		} 
	    } else {
		qtdNaoInformado = qtdNaoInformado + 1;
	    }
	}
	
	graficoPorSistema.set("Não Informado", qtdNaoInformado);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_MANUTENCAO, qtdERPManutencao); 
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_UNIGAS, qtdUnigas);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_CONTAS_RECEBER, qtdERPContasReceber);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_CONTAS_PAGAR, qtdERPContasPagar);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_ADMINISTRADOR, qtdERPAdministrador);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_SGM, qtdSGM);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_ESTOQUE, qtdERPEstoque);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_COMPRAS, qtdERPCompras);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_INTEGRACAO, qtdERPIntegracao);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_FLUXO_CAIXA, qtdERPFluxoCaixa);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_CONTABILIDADE, qtdERPContabilidade);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_ORCAMENTO, qtdERPOrcamento);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_RH, qtdERPRH);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_FISCAL, qtdERPFiscal);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_CONTROLE_CONTRATOS, qtdERPControleContratos);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_TESOURARIA, qtdERPTesouraria);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_COBRANCA, qtdERPCobranca);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_OBRAS, qtdERPObras);
	graficoPorSistema.set(Sistema.VALOR_SISTEMA_ERP_ATIVO_FIXO, qtdERPAtivoFixo);
    }


    private void gerarGraficoPorTipo() {

	graficoPorTipo = new PieChartModel();  

	int qtdChamadoIncidente = 0;
	int qtdChamadoReclamacao = 0;
	int qtdSolicitacaoInformacao = 0;
	int qtdSolicitacaoServico = 0;
	int qtdNaoInformado = 0;
	
	for (Ticket ticket : this.listaTicket) {
	    if (ticket.getTipoChamado() != null) {
		if (TipoChamado.VALOR_TIPO_CHAMADO_INCIDENTE.equals(ticket.getTipoChamado().getDescricao())) {
		    qtdChamadoIncidente = qtdChamadoIncidente + 1;
		} else if (TipoChamado.VALOR_TIPO_CHAMADO_RECLAMACAO.equals(ticket.getTipoChamado().getDescricao())) {
		    qtdChamadoReclamacao = qtdChamadoReclamacao + 1;
		} else if (TipoChamado.VALOR_TIPO_CHAMADO_SOLICITACAO_INFORMACAO.equals(ticket.getTipoChamado().getDescricao())) {
		    qtdSolicitacaoInformacao = qtdSolicitacaoInformacao + 1;
		} else if (TipoChamado.VALOR_TIPO_CHAMADO_SOLICITACAO_SERVICO.equals(ticket.getTipoChamado().getDescricao())) {
		    qtdSolicitacaoServico = qtdSolicitacaoServico + 1;
		} 
	    } else {
		qtdNaoInformado = qtdNaoInformado + 1;
	    }
	}
	
	graficoPorTipo.set("Não Informado", qtdNaoInformado);
	graficoPorTipo.set(TipoChamado.VALOR_TIPO_CHAMADO_INCIDENTE, qtdChamadoIncidente); 
	graficoPorTipo.set(TipoChamado.VALOR_TIPO_CHAMADO_RECLAMACAO, qtdChamadoReclamacao);
	graficoPorTipo.set(TipoChamado.VALOR_TIPO_CHAMADO_SOLICITACAO_INFORMACAO, qtdSolicitacaoInformacao);
	graficoPorTipo.set(TipoChamado.VALOR_TIPO_CHAMADO_SOLICITACAO_SERVICO, qtdSolicitacaoServico);
    }

    public void gerarGraficoAbertosFechadosSemana() throws ProjetoException {
	
	graficoAbertosFechadosSemana = new CartesianChartModel();  
        
        int qtdAbertosSegunda = 0;
        int qtdAbertosTerca = 0;
        int qtdAbertosQuarta = 0;
        int qtdAbertosQuinta = 0;
        int qtdAbertosSexta = 0;
        int qtdFechadosSegunda = 0;
        int qtdFechadosTerca = 0;
        int qtdFechadosQuarta = 0;
        int qtdFechadosQuinta = 0;
        int qtdFechadosSexta = 0;
        
        Calendar c = GregorianCalendar.getInstance();
        c.set(Calendar.WEEK_OF_MONTH, this.semanaReferencia);
        
	c.setFirstDayOfWeek(Calendar.SUNDAY);  
        int diaSemana = c.get(Calendar.DAY_OF_WEEK);  
        c.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - diaSemana);  
	Date dataInicial = c.getTime();
	
	c.add(Calendar.DAY_OF_MONTH, c.getFirstDayOfWeek()+6);
	Date dataFinal = c.getTime();
	
	Map<String, Object> filtro = new HashMap<String, Object>();
	filtro.put(Constantes.FILTRO_DATA_INICIAL, dataInicial);
	filtro.put(Constantes.FILTRO_DATA_FINAL, dataFinal);
	List<Ticket> listaTicket = fachada.listarTicket(filtro);
        
	c.setTime(dataInicial);
	Calendar diasSemana = Calendar.getInstance();
	diasSemana.clear();
	diasSemana.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
	
	for (int i = 1; i < 7; i++) {
	    diasSemana.set(Calendar.DAY_OF_MONTH, diasSemana.get(Calendar.DAY_OF_MONTH)+1);
	    for (Ticket ticket : listaTicket) {
		
		if (i == 1) {
		    
		    if (ticket.getDataCriacao().compareTo(diasSemana.getTime()) == 0) {
			qtdAbertosSegunda = qtdAbertosSegunda + 1;
		    }
		    if (ticket.getDataFechamento() != null) {
			if (ticket.getDataFechamento().compareTo(diasSemana.getTime()) == 0) {
			    qtdFechadosSegunda = qtdFechadosSegunda + 1;
			}
		    }
		    
		} else if (i == 2) {
		    
		    if (ticket.getDataCriacao().compareTo(diasSemana.getTime()) == 0) {
			qtdAbertosTerca = qtdAbertosTerca + 1;
		    }
		    if (ticket.getDataFechamento() != null) {
			if (ticket.getDataFechamento().compareTo(diasSemana.getTime()) == 0) {
			    qtdFechadosTerca = qtdFechadosTerca + 1;
			}
		    }
		    
		} else if (i == 3) {
		    
		    if (ticket.getDataCriacao().compareTo(diasSemana.getTime()) == 0) {
			qtdAbertosQuarta = qtdAbertosQuarta + 1;
		    }
		    if (ticket.getDataFechamento() != null) {
			if (ticket.getDataFechamento().compareTo(diasSemana.getTime()) == 0) {
			    qtdFechadosQuarta = qtdFechadosQuarta + 1;
			}
		    }
		    
		} else if (i == 4) {
		    
		    if (ticket.getDataCriacao().compareTo(diasSemana.getTime()) == 0) {
			qtdAbertosQuinta = qtdAbertosQuinta + 1;
		    }
		    if (ticket.getDataFechamento() != null) {
			if (ticket.getDataFechamento().compareTo(diasSemana.getTime()) == 0) {
			    qtdFechadosQuinta = qtdFechadosQuinta + 1;
			}
		    }
		    
		} else if (i == 5) {
		    
		    if (ticket.getDataCriacao().compareTo(diasSemana.getTime()) == 0) {
			qtdAbertosSexta = qtdAbertosSexta + 1;
		    }
		    if (ticket.getDataFechamento() != null) {
			if (ticket.getDataFechamento().compareTo(diasSemana.getTime()) == 0) {
			    qtdFechadosSexta = qtdFechadosSexta + 1;
			}
		    }
		}
	    }
	}
        
        ChartSeries abertos = new ChartSeries();  
        abertos.setLabel("Abertos");  
  
        abertos.set("Segunda", qtdAbertosSegunda);  
        abertos.set("Terça", qtdAbertosTerca);  
        abertos.set("Quarta", qtdAbertosQuarta);  
        abertos.set("Quinta", qtdAbertosQuinta);  
        abertos.set("Sexta", qtdAbertosSexta);  
  
        ChartSeries fechados = new ChartSeries();  
        fechados.setLabel("Fechados");  
  
        fechados.set("Segunda", qtdFechadosSegunda);  
        fechados.set("Terça", qtdFechadosTerca);  
        fechados.set("Quarta", qtdFechadosQuarta);  
        fechados.set("Quinta", qtdFechadosQuinta);  
        fechados.set("Sexta", qtdFechadosSexta);  
  
        graficoAbertosFechadosSemana.addSeries(abertos);  
        graficoAbertosFechadosSemana.addSeries(fechados);
    }
    
    public void gerarGraficoAbertosFechadosMes() throws ProjetoException {
        
        graficoAbertosFechadosMes = new CartesianChartModel();  
        
        int totalAbertosSemana1 = 0;
        int totalAbertosSemana2 = 0;
        int totalAbertosSemana3 = 0;
        int totalAbertosSemana4 = 0;
        int totalAbertosSemana5 = 0;
        int totalFechadosSemana1 = 0;
        int totalFechadosSemana2 = 0;
        int totalFechadosSemana3 = 0;
        int totalFechadosSemana4 = 0;
        int totalFechadosSemana5 = 0;
        
        Calendar dataFiltro = GregorianCalendar.getInstance();
        dataFiltro.set(Calendar.MONTH, this.mesReferencia);
        
        dataFiltro.set(Calendar.DAY_OF_MONTH, dataFiltro.getActualMinimum(Calendar.DAY_OF_MONTH));
	Date dataInicial = dataFiltro.getTime();
	
	dataFiltro.set(Calendar.DAY_OF_MONTH, dataFiltro.getActualMaximum(Calendar.DAY_OF_MONTH));  
	Date dataFinal = dataFiltro.getTime();
        
	Map<String, Object> filtro = new HashMap<String, Object>();
	filtro.put(Constantes.FILTRO_DATA_INICIAL, dataInicial);
	filtro.put(Constantes.FILTRO_DATA_FINAL, dataFinal);
	List<Ticket> listaTicket = fachada.listarTicket(filtro);
        
        for (Ticket ticket : listaTicket) {
            obterInicioFimDaSemana(1);
            if (ticket.getDataCriacao().after(dataInicialRelatorio) && ticket.getDataCriacao().before(dataFinalRelatorio)) {
        	totalAbertosSemana1 = totalAbertosSemana1 + 1;
            }
	    if (ticket.getDataFechamento() != null && ticket.getDataFechamento().after(dataInicialRelatorio)
		    && ticket.getDataFechamento().before(dataFinalRelatorio)) {
        	totalFechadosSemana1 = totalFechadosSemana1 + 1;
            }
            
            obterInicioFimDaSemana(2);
            if (ticket.getDataCriacao().after(dataInicialRelatorio) && ticket.getDataCriacao().before(dataFinalRelatorio)) {
        	totalAbertosSemana2 = totalAbertosSemana2 + 1;
            }
            if (ticket.getDataFechamento() != null && ticket.getDataFechamento().after(dataInicialRelatorio)
		    && ticket.getDataFechamento().before(dataFinalRelatorio)) {
        	totalFechadosSemana2 = totalFechadosSemana2 + 1;
            }
            
            obterInicioFimDaSemana(3);
            if (ticket.getDataCriacao().after(dataInicialRelatorio) && ticket.getDataCriacao().before(dataFinalRelatorio)) {
        	totalAbertosSemana3 = totalAbertosSemana3 + 1;
            }
            if (ticket.getDataFechamento() != null && ticket.getDataFechamento().after(dataInicialRelatorio)
		    && ticket.getDataFechamento().before(dataFinalRelatorio)) {
        	totalFechadosSemana3 = totalFechadosSemana3 + 1;
            }
            
            obterInicioFimDaSemana(4);
            if (ticket.getDataCriacao().after(dataInicialRelatorio) && ticket.getDataCriacao().before(dataFinalRelatorio)) {
        	totalAbertosSemana4 = totalAbertosSemana4 + 1;
            }
            if (ticket.getDataFechamento() != null && ticket.getDataFechamento().after(dataInicialRelatorio)
		    && ticket.getDataFechamento().before(dataFinalRelatorio)) {
        	totalFechadosSemana4 = totalFechadosSemana4 + 1;
            }
            
            obterInicioFimDaSemana(5);
            if (ticket.getDataCriacao().after(dataInicialRelatorio) && ticket.getDataCriacao().before(dataFinalRelatorio)) {
        	totalAbertosSemana5 = totalAbertosSemana5 + 1;
            }
            if (ticket.getDataFechamento() != null && ticket.getDataFechamento().after(dataInicialRelatorio)
		    && ticket.getDataFechamento().before(dataFinalRelatorio)) {
        	totalFechadosSemana5 = totalFechadosSemana5 + 1;
            }
	}
        
        ChartSeries abertos = new ChartSeries();  
        abertos.setLabel("Abertos");  
  
        abertos.set("Semana 1", totalAbertosSemana1);  
        abertos.set("Semana 2", totalAbertosSemana2);  
        abertos.set("Semana 3", totalAbertosSemana3);  
        abertos.set("Semana 4", totalAbertosSemana4);  
        abertos.set("Semana 5", totalAbertosSemana5);  
  
        ChartSeries fechados = new ChartSeries();  
        fechados.setLabel("Fechados");  
  
        fechados.set("Semana 1", totalFechadosSemana1);  
        fechados.set("Semana 2", totalFechadosSemana2);  
        fechados.set("Semana 3", totalFechadosSemana3);  
        fechados.set("Semana 4", totalFechadosSemana4);
        fechados.set("Semana 5", totalFechadosSemana5);
  
        graficoAbertosFechadosMes.addSeries(abertos);  
        graficoAbertosFechadosMes.addSeries(fechados); 
    }
    
    private void gerarGraficoPorTipoAtendimento() throws ProjetoException {

	graficoPorTipoAtendimento = new PieChartModel();  

	int qtdSuporteInterno = 0;
	int qtdSuporteExterno = 0;
	int qtdBug = 0;
	int qtdSuporteConfiguracao = 0;
	int qtdNaoInformado = 0;
	
	for (Ticket ticket : this.listaTicket) {
	    if (ticket.getTipoAtendimento() != null) {
		if (TipoAtendimento.VALOR_TIPO_ATENDIMENTO_SUPORTE_INTERNO.equals(ticket.getTipoAtendimento().getDescricao())) {
		    qtdSuporteInterno = qtdSuporteInterno + 1;
		} else if (TipoAtendimento.VALOR_TIPO_ATENDIMENTO_SUPORTE_EXTERNO.equals(ticket.getTipoAtendimento().getDescricao())) {
		    qtdSuporteExterno = qtdSuporteExterno + 1;
		} else if (TipoAtendimento.VALOR_TIPO_ATENDIMENTO_BUG.equals(ticket.getTipoAtendimento().getDescricao())) {
		    qtdBug = qtdBug + 1;
		} else if (TipoAtendimento.VALOR_TIPO_ATENDIMENTO_SUPORTE_CONFIGURACAO.equals(ticket.getTipoAtendimento().getDescricao())) {
		    qtdSuporteConfiguracao = qtdSuporteConfiguracao + 1;
		}
	    } else {
		qtdNaoInformado = qtdNaoInformado + 1;
	    }
	}
	
	graficoPorTipoAtendimento.set("Não Informado", qtdNaoInformado);
	graficoPorTipoAtendimento.set(TipoAtendimento.VALOR_TIPO_ATENDIMENTO_SUPORTE_INTERNO, qtdSuporteInterno); 
	graficoPorTipoAtendimento.set(TipoAtendimento.VALOR_TIPO_ATENDIMENTO_SUPORTE_EXTERNO, qtdSuporteExterno);
	graficoPorTipoAtendimento.set(TipoAtendimento.VALOR_TIPO_ATENDIMENTO_BUG, qtdBug);
	graficoPorTipoAtendimento.set(TipoAtendimento.VALOR_TIPO_ATENDIMENTO_SUPORTE_CONFIGURACAO, qtdSuporteConfiguracao);
    }
    
    private void gerarGraficoPorSetor() throws ProjetoException {

	graficoPorSetor = new PieChartModel();  

	int qtdGERE = 0;
	int qtdGDIS = 0;
	int qtdGFIN = 0;
	int qtdGETI = 0;
	int qtdGCRC = 0;
	int qtdGMTC = 0;
	int qtdGADS = 0;
	int qtdDAF = 0;
	int qtdGERH = 0;
	int qtdCOMUN = 0;
	int qtdPRE = 0;
	int qtdQSMS = 0;
	int qtdGCVI = 0;
	int qtdCJUR = 0;
	int qtdDTC = 0;
	int qtdCAF = 0;
	int qtdSEGE = 0;
	int qtdGCON = 0;
	int qtdNaoInformado = 0;
	
	for (Ticket ticket : this.listaTicket) {
	    if (ticket.getSetor() != null) {
		if (Setor.VALOR_SETOR_GERE.equals(ticket.getSetor().getDescricao())) {
		    qtdGERE = qtdGERE + 1;
		} else if (Setor.VALOR_SETOR_GDIS.equals(ticket.getSetor().getDescricao())) {
		    qtdGDIS = qtdGDIS + 1;
		} else if (Setor.VALOR_SETOR_GFIN.equals(ticket.getSetor().getDescricao())) {
		    qtdGFIN = qtdGFIN + 1;
		} else if (Setor.VALOR_SETOR_GETI.equals(ticket.getSetor().getDescricao())) {
		    qtdGETI = qtdGETI + 1;
		} else if (Setor.VALOR_SETOR_GCRC.equals(ticket.getSetor().getDescricao())) {
		    qtdGCRC = qtdGCRC + 1;
		} else if (Setor.VALOR_SETOR_GMAR.equals(ticket.getSetor().getDescricao())) {
		    qtdGMTC = qtdGMTC + 1;
		} else if (Setor.VALOR_SETOR_GADS.equals(ticket.getSetor().getDescricao())) {
		    qtdGADS = qtdGADS + 1;
		} else if (Setor.VALOR_SETOR_DAF.equals(ticket.getSetor().getDescricao())) {
		    qtdDAF = qtdDAF + 1;
		} else if (Setor.VALOR_SETOR_GERH.equals(ticket.getSetor().getDescricao())) {
		    qtdGERH = qtdGERH + 1;
		} else if (Setor.VALOR_SETOR_COMUN.equals(ticket.getSetor().getDescricao())) {
		    qtdCOMUN = qtdCOMUN + 1;
		} else if (Setor.VALOR_SETOR_PRE.equals(ticket.getSetor().getDescricao())) {
		    qtdPRE = qtdPRE + 1;
		} else if (Setor.VALOR_SETOR_QSMS.equals(ticket.getSetor().getDescricao())) {
		    qtdQSMS = qtdQSMS + 1;
		} else if (Setor.VALOR_SETOR_GCVI.equals(ticket.getSetor().getDescricao())) {
		    qtdGCVI = qtdGCVI + 1;
		} else if (Setor.VALOR_SETOR_CJUR.equals(ticket.getSetor().getDescricao())) {
		    qtdCJUR = qtdCJUR + 1;
		} else if (Setor.VALOR_SETOR_DTC.equals(ticket.getSetor().getDescricao())) {
		    qtdDTC = qtdDTC + 1;
		} else if (Setor.VALOR_SETOR_CAF.equals(ticket.getSetor().getDescricao())) {
		    qtdCAF = qtdCAF + 1;
		} else if (Setor.VALOR_SETOR_SEGE.equals(ticket.getSetor().getDescricao())) {
		    qtdSEGE = qtdSEGE + 1;
		} else if (Setor.VALOR_SETOR_GCON.equals(ticket.getSetor().getDescricao())) {
		    qtdGCON = qtdGCON + 1;
		}
	    } else {
		qtdNaoInformado = qtdNaoInformado + 1;
	    }
	}
	
	graficoPorSetor.set("Não Informado", qtdNaoInformado);
	graficoPorSetor.set(Setor.VALOR_SETOR_GERE, qtdGERE); 
	graficoPorSetor.set(Setor.VALOR_SETOR_GDIS, qtdGDIS);
	graficoPorSetor.set(Setor.VALOR_SETOR_GFIN, qtdGFIN);
	graficoPorSetor.set(Setor.VALOR_SETOR_GETI, qtdGETI);
	graficoPorSetor.set(Setor.VALOR_SETOR_GCRC, qtdGCRC);
	graficoPorSetor.set(Setor.VALOR_SETOR_GMAR, qtdGMTC);
	graficoPorSetor.set(Setor.VALOR_SETOR_GADS, qtdGADS);
	graficoPorSetor.set(Setor.VALOR_SETOR_DAF, qtdDAF);
	graficoPorSetor.set(Setor.VALOR_SETOR_GERH, qtdGERH);
	graficoPorSetor.set(Setor.VALOR_SETOR_COMUN, qtdCOMUN);
	graficoPorSetor.set(Setor.VALOR_SETOR_PRE, qtdPRE);	
	graficoPorSetor.set(Setor.VALOR_SETOR_QSMS, qtdQSMS);
	graficoPorSetor.set(Setor.VALOR_SETOR_GCVI, qtdGCVI);
	graficoPorSetor.set(Setor.VALOR_SETOR_CJUR, qtdCJUR);
	graficoPorSetor.set(Setor.VALOR_SETOR_DTC, qtdDTC);
	graficoPorSetor.set(Setor.VALOR_SETOR_CAF, qtdCAF);
	graficoPorSetor.set(Setor.VALOR_SETOR_SEGE, qtdSEGE);
	graficoPorSetor.set(Setor.VALOR_SETOR_GCON, qtdGCON);
    }
    
    private void gerarGraficoPorPrioridade() throws ProjetoException {

	graficoPorPrioridade = new PieChartModel();  

	int qtdAlta = 0;
	int qtdMedia = 0;
	int qtdBaixa = 0;
	int qtdNaoInformado = 0;
	
	for (Ticket ticket : this.listaTicket) {
	    if (ticket.getPrioridade() != null) {
		if (Prioridade.VALOR_PRIORIDADE_HIGH.equals(ticket.getPrioridade().getDescricao())) {
		    qtdAlta = qtdAlta + 1;
		} else if (Prioridade.VALOR_PRIORIDADE_MED.equals(ticket.getPrioridade().getDescricao())) {
		    qtdMedia = qtdMedia + 1;
		} else if (Prioridade.VALOR_PRIORIDADE_LOW.equals(ticket.getPrioridade().getDescricao())) {
		    qtdBaixa = qtdBaixa + 1;
		} 
	    } else {
		qtdNaoInformado = qtdNaoInformado + 1;
	    }
	}
	
	graficoPorPrioridade.set("Não Informado", qtdNaoInformado);
	graficoPorPrioridade.set(Prioridade.VALOR_PRIORIDADE_HIGH, qtdAlta); 
	graficoPorPrioridade.set(Prioridade.VALOR_PRIORIDADE_MED, qtdMedia);
	graficoPorPrioridade.set(Prioridade.VALOR_PRIORIDADE_LOW, qtdBaixa);
    }
    
    private void gerarGraficoPorDataVencimento() throws ProjetoException {

	graficoPorDataVencimento = new PieChartModel();  

	int qtdNoPrazo = 0;
	int qtdVencido = 0;
	int qtdSemPrazo = 0;
	
	Calendar c = Calendar.getInstance();
	c.clear();
	
	Calendar c2 = Calendar.getInstance();
	c.set(c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DAY_OF_MONTH));
	
	for (Ticket ticket : this.listaTicket) {
	    
	    if (ticket.getDataVencimento() == null) {
		qtdSemPrazo = qtdSemPrazo + 1;
	    } else {
		if (c.getTime().compareTo(ticket.getDataVencimento()) > 0) {		    
		    qtdVencido = qtdVencido + 1;
		} else {
		    qtdNoPrazo = qtdNoPrazo + 1;
		}
	    }
	}
	
	graficoPorDataVencimento.set("Dentro do Prazo", qtdNoPrazo); 
	graficoPorDataVencimento.set("Vencidos", qtdVencido);
	graficoPorDataVencimento.set("Sem Prazo", qtdSemPrazo);
    }
    
    private void gerarGraficoPorOrigemChamado() throws ProjetoException {

	graficoPorOrigemChamado = new PieChartModel();  

	int qtdEmail = 0;
	int qtdPortal = 0;
	int qtdPresencial = 0;
	int qtdProAtivo = 0;
	int qtdTelefone = 0;
	int qtdNaoInformado = 0;
	
	for (Ticket ticket : this.listaTicket) {
	    if (ticket.getOrigemChamado() != null) {
		if (OrigemChamado.VALOR_ORIGEM_CHAMADO_EMAIL.equals(ticket.getOrigemChamado().getDescricao())) {
		    qtdEmail = qtdEmail + 1;
		} else if (OrigemChamado.VALOR_ORIGEM_CHAMADO_PORTAL.equals(ticket.getOrigemChamado().getDescricao())) {
		    qtdPortal = qtdPortal + 1;
		} else if (OrigemChamado.VALOR_ORIGEM_CHAMADO_PRESENCIAL.equals(ticket.getOrigemChamado().getDescricao())) {
		    qtdPresencial = qtdPresencial + 1;
		} else if (OrigemChamado.VALOR_ORIGEM_CHAMADO_PRO_ATIVO.equals(ticket.getOrigemChamado().getDescricao())) {
		    qtdProAtivo = qtdProAtivo + 1;
		} else if (OrigemChamado.VALOR_ORIGEM_CHAMADO_TELEFONE.equals(ticket.getOrigemChamado().getDescricao())) {
		    qtdTelefone = qtdTelefone + 1;
		} 
	    } else {
		qtdNaoInformado = qtdNaoInformado + 1;
	    }
	}
	
	graficoPorOrigemChamado.set("Não Informado", qtdNaoInformado);
	graficoPorOrigemChamado.set(OrigemChamado.VALOR_ORIGEM_CHAMADO_EMAIL, qtdEmail); 
	graficoPorOrigemChamado.set(OrigemChamado.VALOR_ORIGEM_CHAMADO_PORTAL, qtdPortal);
	graficoPorOrigemChamado.set(OrigemChamado.VALOR_ORIGEM_CHAMADO_PRESENCIAL, qtdPresencial);
	graficoPorOrigemChamado.set(OrigemChamado.VALOR_ORIGEM_CHAMADO_PRO_ATIVO, qtdProAtivo);
	graficoPorOrigemChamado.set(OrigemChamado.VALOR_ORIGEM_CHAMADO_TELEFONE, qtdTelefone);
    }
    
    public void inserirTicketUsuario() throws ProjetoException {
	
	ehCadastroUsuario = true;
	inserirTicket();
    }
    
    public String inserirTicketPesquisar() throws ProjetoException {

	this.inserirTicket();
	this.pesquisar();
	return Constantes.ARQUIVO_XHTML_PESQUISAR_TICKET;
    }

    public void inserirTicket() throws ProjetoException {

	Ticket novoTicket = montarObjeto(fachada.criarTicket());
	fachada.inserirTicket(novoTicket);
	setTicket(novoTicket);
	setAttributeSession(ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA, novoTicket);
	Util.exibeMensagemTela(FacesMessage.SEVERITY_INFO, Constantes.PARAMETRO_MSG_SUCESSO_INCLUSAO,
		novoTicket.toString());
    }
    
    public String exibirTelaAtualizacao() throws ProjetoException {
	
	setAttributeSession(ATRIBUTO_SESSAO_ENTIDADE_SELECIONADA, ticketSelecionado);
	setAttributeSession(ATRIBUTO_SESSAO_HABILITA_EDICAO, Boolean.FALSE);
	setAttributeSession(ATRIBUTO_SESSAO_EXIBE_BOTOES_ALTERACAO, Boolean.TRUE);
	return Constantes.ARQUIVO_XHTML_SALVAR_TICKET;
    }
    
    public void habilitaEdicao() throws ProjetoException {
	
	setAttributeSession(ATRIBUTO_SESSAO_HABILITA_EDICAO, Boolean.TRUE);
	setHabilitaEdicao(Boolean.TRUE);
    }
    
    public void removerTicket() throws ProjetoException {

	Ticket ticket = (Ticket) fachada.obterTicket(this.ticketSelecionado.getChavePrimaria());
	fachada.removerTicket(ticket);
	this.pesquisar();
	Util.exibeMensagemTela(FacesMessage.SEVERITY_INFO, Constantes.PARAMETRO_MSG_SUCESSO_EXCLUSAO,
		this.ticketSelecionado.toString());
    }
    
    public void preencherDataAtualFechamento() throws ProjetoException {
	
	Calendar c = GregorianCalendar.getInstance();
	dataInicialFechamento = c.getTime();
	dataFinalFechamento = c.getTime();
    }
    
    public void preencherSemanaAtualFechamento() throws ProjetoException {
	
	Calendar c = GregorianCalendar.getInstance();

	c.setFirstDayOfWeek(Calendar.SUNDAY);
	int diaSemana = c.get(Calendar.DAY_OF_WEEK);
	c.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - diaSemana);
	dataInicialFechamento = c.getTime();

	c.add(Calendar.DAY_OF_MONTH, c.getFirstDayOfWeek() + 6);
	dataFinalFechamento = c.getTime();
    }
    
    public void preencherMesAtualFechamento() throws ProjetoException {
	
	Calendar c = GregorianCalendar.getInstance();

	c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
	dataInicialFechamento = c.getTime();

	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
	dataFinalFechamento = c.getTime();
    }
    
    public void preencherDataAtualCadastro() throws ProjetoException {
	
	Calendar c = GregorianCalendar.getInstance();
	dataInicial = c.getTime();
	dataFinal = c.getTime();
    }
    
    public void preencherSemanaAtualCadastro() throws ProjetoException {
	
	Calendar c = GregorianCalendar.getInstance();

	c.setFirstDayOfWeek(Calendar.SUNDAY);
	int diaSemana = c.get(Calendar.DAY_OF_WEEK);
	c.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - diaSemana);
	dataInicial = c.getTime();

	c.add(Calendar.DAY_OF_MONTH, c.getFirstDayOfWeek() + 6);
	dataFinal = c.getTime();
    }
    
    public void preencherMesAtualCadastro() throws ProjetoException {
	
	Calendar c = GregorianCalendar.getInstance();

	c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
	dataInicial = c.getTime();

	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
	dataFinal = c.getTime();
    }
    
    public void preencherMesAtualVencimento() throws ProjetoException {
	
	Calendar c = GregorianCalendar.getInstance();

	c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
	dataInicialVencimento = c.getTime();

	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
	dataFinalVencimento = c.getTime();
    }
    
    public void preencherDataAtualVencimento() throws ProjetoException {
	
	Calendar c = GregorianCalendar.getInstance();
	dataInicialVencimento = c.getTime();
	dataFinalVencimento = c.getTime();
    }
    
    public void preencherSemanaAtualVencimento() throws ProjetoException {
	
	Calendar c = GregorianCalendar.getInstance();

	c.setFirstDayOfWeek(Calendar.SUNDAY);
	int diaSemana = c.get(Calendar.DAY_OF_WEEK);
	c.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - diaSemana);
	dataInicialVencimento = c.getTime();

	c.add(Calendar.DAY_OF_MONTH, c.getFirstDayOfWeek() + 6);
	dataFinalVencimento = c.getTime();
    }
    
    private void obterInicioFimDaSemana(int semana) throws ProjetoException {
	
	Calendar c = GregorianCalendar.getInstance();
	c.set(Calendar.DAY_OF_MONTH, 1);
	c.set(Calendar.MONTH, mesReferencia);
	
	if (semana == 2) {
	    c.set(Calendar.DAY_OF_MONTH, (c.get(Calendar.DAY_OF_MONTH)+7));
	} else if (semana == 3) {
	    c.set(Calendar.DAY_OF_MONTH, (c.get(Calendar.DAY_OF_MONTH)+14));
	} else if (semana == 4) {
	    c.set(Calendar.DAY_OF_MONTH, (c.get(Calendar.DAY_OF_MONTH)+21));
	} else if (semana == 5) {
	    c.set(Calendar.DAY_OF_MONTH, (c.get(Calendar.DAY_OF_MONTH)+28));
	}
	
	c.setFirstDayOfWeek(Calendar.SUNDAY);  
        int diaSemana = c.get(Calendar.DAY_OF_WEEK);  
        c.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - diaSemana);  
	dataInicialRelatorio = c.getTime();
	
	c.add(Calendar.DAY_OF_MONTH, c.getFirstDayOfWeek()+6);
	dataFinalRelatorio = c.getTime();
    }
    
    private Ticket montarObjeto(Ticket obj) throws ProjetoException {
	
	obj.setSetor(this.ticket.getSetor());
	obj.setSolicitante(this.ticket.getSolicitante());
	obj.setSistema(this.ticket.getSistema());
	obj.setTipoAtendimento(this.ticket.getTipoAtendimento());
	obj.setTipoChamado(this.ticket.getTipoChamado());
	obj.setOrigemChamado(this.ticket.getOrigemChamado());
	obj.setFechamentoEncaminhado(this.ticket.getFechamentoEncaminhado());
	obj.setMaquinaOrigem(this.ticket.getMaquinaOrigem());
	obj.setCategoria(this.ticket.getCategoria());
	obj.setDataVencimento(this.ticket.getDataVencimento());
	obj.setSubCategoria(this.ticket.getSubCategoria());
	obj.setNumero(this.ticket.getNumero());
	obj.setTitulo(this.ticket.getTitulo());
	obj.setDescricao(this.ticket.getDescricao());
	obj.setStatusCopergas(this.ticket.getStatusCopergas());
	obj.setStatusFornecedor(this.ticket.getStatusFornecedor());
	obj.setPrioridade(this.ticket.getPrioridade());
	obj.setFoiReaberto(this.ticket.getFoiReaberto());

	obj.setDataCriacao(Calendar.getInstance().getTime());
	obj.setStatus(fachada.obterStatus(Status.VALOR_STATUS_OPEN));
	
	if (ehCadastroUsuario) {
	    obj.setUsuario(fachada.obterUsuario(Usuario.VALOR_USUARIO_UNASSIGNED));
	} else {
	    obj.setUsuario(this.ticket.getUsuario());
	}
	
	return obj;
    }

    private void carregaCamposTelaAlteracao() throws ProjetoException {

	if (getAttributeSession(ATRIBUTO_SESSAO_HABILITA_EDICAO) != null) {
	    setHabilitaEdicao((boolean) getAttributeSession(ATRIBUTO_SESSAO_HABILITA_EDICAO));
	} else {
	    setHabilitaEdicao(Boolean.TRUE);
	}
	
	if (getAttributeSession(ATRIBUTO_SESSAO_EXIBE_BOTOES_ALTERACAO) != null) {
	    setExibeBotoesAlteracao((boolean) getAttributeSession(ATRIBUTO_SESSAO_EXIBE_BOTOES_ALTERACAO));
	}
    }
}