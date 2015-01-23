package br.com.copergas.portalchamados.model.util;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import br.com.copergas.portalchamados.model.comum.excecoes.NegocioException;
import br.com.copergas.portalchamados.model.comum.excecoes.ProjetoException;
import br.com.copergas.portalchamados.model.produto.Produto;
import br.com.copergas.portalchamados.model.produto.ProdutoCategoria;
import br.com.copergas.portalchamados.model.produto.ProdutoService;
import br.com.copergas.portalchamados.model.ticket.OrigemChamado;
import br.com.copergas.portalchamados.model.ticket.Prioridade;
import br.com.copergas.portalchamados.model.ticket.RegistroImportacao;
import br.com.copergas.portalchamados.model.ticket.Setor;
import br.com.copergas.portalchamados.model.ticket.Sistema;
import br.com.copergas.portalchamados.model.ticket.Status;
import br.com.copergas.portalchamados.model.ticket.StatusCopergas;
import br.com.copergas.portalchamados.model.ticket.StatusFornecedor;
import br.com.copergas.portalchamados.model.ticket.Ticket;
import br.com.copergas.portalchamados.model.ticket.TicketCategoria;
import br.com.copergas.portalchamados.model.ticket.TicketService;
import br.com.copergas.portalchamados.model.ticket.TicketSubCategoria;
import br.com.copergas.portalchamados.model.ticket.TipoAtendimento;
import br.com.copergas.portalchamados.model.ticket.TipoChamado;
import br.com.copergas.portalchamados.model.usuario.Usuario;
import br.com.copergas.portalchamados.model.usuario.UsuarioService;

/**
 * @author Roberto Alencar
 * 
 */
public final class Fachada {

    private static Fachada instancia = new Fachada();

    public static Fachada getInstancia() {
	return instancia;
    }
    
    
    // Services -------------------------------------------------------------

    
    public Produto criarProduto() throws ProjetoException {
	return (Produto) this.getProdutoService().criar();
    }
    
    public ProdutoCategoria criarProdutoCategoria() throws ProjetoException {
	return (ProdutoCategoria) this.getProdutoService().criarProdutoCategoria();
    }
    
    public Produto obterProduto(long chavePrimaria) throws ProjetoException {
	return (Produto) this.getProdutoService().obter(chavePrimaria);
    }
    
    public Ticket obterTicket(long chavePrimaria) throws ProjetoException {
	return (Ticket) this.getTicketService().obter(chavePrimaria);
    }
    
    public ProdutoCategoria obterProdutoCategoria(long chavePrimaria) throws ProjetoException {
	return (ProdutoCategoria) this.getProdutoService().obter(chavePrimaria,
		this.getProdutoService().getClasseEntidadeProdutoCategoria());
    }
    
    public Status obterStatus(long chavePrimaria) throws ProjetoException {
	return (Status) this.getTicketService().obter(chavePrimaria, this.getTicketService().getClasseEntidadeStatus());
    }
    
    public RegistroImportacao obterRegistroImportacao(long chavePrimaria) throws ProjetoException {
	return (RegistroImportacao) this.getTicketService().obter(chavePrimaria, this.getTicketService().getClasseEntidadeRegistroImportacao());
    }
    
    public Usuario obterUsuario(long chavePrimaria) throws ProjetoException {
	return (Usuario) this.getUsuarioService().obter(chavePrimaria);
    }

    public Long inserirProduto(Produto produto) throws ProjetoException {
	return this.getProdutoService().inserir(produto);
    }
    
    public Long inserirTicket(Ticket ticket) throws ProjetoException {
	return this.getTicketService().inserir(ticket);
    }
    
    public void atualizarProduto(Produto produto) throws ProjetoException {
	this.getProdutoService().atualizar(produto);
    }
    
    public void removerProduto(Produto produto) throws ProjetoException {
	this.getProdutoService().remover(produto);
    }
    
    public void removerTicket(Ticket ticket) throws ProjetoException {
	this.getTicketService().remover(ticket);
    }
    
    public List<Produto> listarProduto(Map<String, Object> filtro) throws NegocioException {
	return this.getProdutoService().listarProduto(filtro);
    }
    
    public List<ProdutoCategoria> listarProdutoCategoria(boolean habilitado) throws NegocioException {
	return this.getProdutoService().listarProdutoCategoria(habilitado);
    }
    
    public Ticket criarTicket() throws ProjetoException {
	return (Ticket) this.getTicketService().criar();
    }
    
    public List<Ticket> listarTicket(Map<String, Object> filtro) throws NegocioException {
	return this.getTicketService().listarTicket(filtro);
    }
    
    public List<Ticket> listarTicketIndicadores(Map<String, Object> filtro) throws NegocioException {
	return this.getTicketService().listarTicketIndicadores(filtro);
    }
    
    public List<Usuario> listarUsuario(Map<String, Object> filtro) throws NegocioException {
	return this.getUsuarioService().listarUsuario(filtro);
    }
    
    @SuppressWarnings("unchecked")
    public List<Status> listarStatus(boolean habilitado) throws NegocioException {
	return (List<Status>) this.getTicketService().carregarCombobox(habilitado,
		getTicketService().getClasseEntidadeStatus());
    }
    
    @SuppressWarnings("unchecked")
    public List<Sistema> listarSistema(boolean habilitado) throws NegocioException {
	return (List<Sistema>) this.getTicketService().carregarCombobox(habilitado,
		getTicketService().getClasseEntidadeSistema());
    }
    
    @SuppressWarnings("unchecked")
    public List<StatusCopergas> listarStatusCopergas(boolean habilitado) throws NegocioException {
	return (List<StatusCopergas>) this.getTicketService().carregarCombobox(habilitado,
		getTicketService().getClasseEntidadeStatusCopergas());
    }
    
    @SuppressWarnings("unchecked")
    public List<StatusFornecedor> listarStatusFornecedor(boolean habilitado) throws NegocioException {
	return (List<StatusFornecedor>) this.getTicketService().carregarCombobox(habilitado,
		getTicketService().getClasseEntidadeStatusFornecedor());
    }
    
    @SuppressWarnings("unchecked")
    public List<Prioridade> listarPrioridade(boolean habilitado) throws NegocioException {
	return (List<Prioridade>) this.getTicketService().carregarCombobox(habilitado,
		getTicketService().getClasseEntidadePrioridade());
    }
    
    @SuppressWarnings("unchecked")
    public List<TipoAtendimento> listarTipoAtendimento(boolean habilitado) throws NegocioException {
	return (List<TipoAtendimento>) this.getTicketService().carregarCombobox(habilitado,
		getTicketService().getClasseEntidadeTipoAtendimento());
    }
    
    @SuppressWarnings("unchecked")
    public List<TicketCategoria> listarCategoria(boolean habilitado) throws NegocioException {
	return (List<TicketCategoria>) this.getTicketService().carregarCombobox(habilitado,
		getTicketService().getClasseEntidadeTicketCategoria());
    }
    
    @SuppressWarnings("unchecked")
    public List<TicketSubCategoria> listarSubCategoria(boolean habilitado) throws NegocioException {
	return (List<TicketSubCategoria>) this.getTicketService().carregarCombobox(habilitado,
		getTicketService().getClasseEntidadeTicketSubCategoria());
    }
    
    @SuppressWarnings("unchecked")
    public List<Setor> listarSetor(boolean habilitado) throws NegocioException {
	return (List<Setor>) this.getTicketService().carregarCombobox(habilitado,
		getTicketService().getClasseEntidadeSetor());
    }
    
    @SuppressWarnings("unchecked")
    public List<OrigemChamado> listarOrigemChamado(boolean habilitado) throws NegocioException {
	return (List<OrigemChamado>) this.getTicketService().carregarCombobox(habilitado,
		getTicketService().getClasseEntidadeOrigemChamado());
    }
    
    @SuppressWarnings("unchecked")
    public List<TipoChamado> listarTipoChamado(boolean habilitado) throws NegocioException {
	return (List<TipoChamado>) this.getTicketService().carregarCombobox(habilitado,
		getTicketService().getClasseEntidadeTipoChamado());
    }
    
    public Status consultarStatusPorDescricao(String descricao) throws NegocioException {
	return (Status) getTicketService().consultarPorDescricao(descricao,
		getTicketService().getClasseEntidadeStatus());
    }
    
    public Sistema consultarSistemaPorDescricao(String descricao) throws NegocioException {
	return (Sistema) getTicketService().consultarPorDescricao(descricao,
		getTicketService().getClasseEntidadeSistema());
    }
    
    public StatusCopergas consultarStatusCopergasPorDescricao(String descricao) throws NegocioException {
	return (StatusCopergas) getTicketService().consultarPorDescricao(descricao,
		getTicketService().getClasseEntidadeStatusCopergas());
    }
    
    public StatusFornecedor consultarStatusFornecedorPorDescricao(String descricao) throws NegocioException {
	return (StatusFornecedor) getTicketService().consultarPorDescricao(descricao,
		getTicketService().getClasseEntidadeStatusFornecedor());
    }
    
    public OrigemChamado consultarOrigemChamadoPorDescricao(String descricao) throws NegocioException {
	return (OrigemChamado) getTicketService().consultarPorDescricao(descricao,
		getTicketService().getClasseEntidadeOrigemChamado());
    }
    
    public TipoChamado consultarTipoChamadoPorDescricao(String descricao) throws NegocioException {
	return (TipoChamado) getTicketService().consultarPorDescricao(descricao,
		getTicketService().getClasseEntidadeTipoChamado());
    }
    
    public Setor consultarSetorPorDescricao(String descricao) throws NegocioException {
	return (Setor) getTicketService().consultarPorDescricao(descricao, getTicketService().getClasseEntidadeSetor());
    }
    
    public Prioridade consultarPrioridadePorDescricao(String descricao) throws NegocioException {
	return (Prioridade) getTicketService().consultarPorDescricao(descricao,
		getTicketService().getClasseEntidadePrioridade());
    }
    
    public TipoAtendimento consultarTipoAtendimentoPorDescricao(String descricao) throws NegocioException {
	return (TipoAtendimento) getTicketService().consultarPorDescricao(descricao,
		getTicketService().getClasseEntidadeTipoAtendimento());
    }
    
    public TicketCategoria consultarTicketCategoriaPorDescricao(String descricao) throws NegocioException {
	return (TicketCategoria) getTicketService().consultarPorDescricao(descricao,
		getTicketService().getClasseEntidadeTicketCategoria());
    }
    
    public TicketSubCategoria consultarTicketSubCategoriaPorDescricao(String descricao) throws NegocioException {
	return (TicketSubCategoria) getTicketService().consultarPorDescricao(descricao,
		getTicketService().getClasseEntidadeTicketSubCategoria());
    }
    
    public void importarTicketsSpiceworks(InputStream arquivo) throws ProjetoException {
	this.getTicketService().importarTicketsSpiceworks(arquivo);
    }
    
    // Gets Services -------------------------------------------------------------
    
    
    private ProdutoService getProdutoService() {
	return (ProdutoService) BeanFactory.getBean(ProdutoService.BEAN_NAME);
    }
    
    private TicketService getTicketService() {
	return (TicketService) BeanFactory.getBean(TicketService.BEAN_NAME);
    }
    
    private UsuarioService getUsuarioService() {
	return (UsuarioService) BeanFactory.getBean(UsuarioService.BEAN_NAME);
    }

}