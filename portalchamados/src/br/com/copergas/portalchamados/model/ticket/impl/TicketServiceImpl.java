package br.com.copergas.portalchamados.model.ticket.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.copergas.portalchamados.model.comum.excecoes.ConcorrenciaException;
import br.com.copergas.portalchamados.model.comum.excecoes.NegocioException;
import br.com.copergas.portalchamados.model.comum.negocio.EntidadeNegocio;
import br.com.copergas.portalchamados.model.comum.negocio.EntidadeRelacionamento;
import br.com.copergas.portalchamados.model.comum.negocio.impl.ServiceImpl;
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
import br.com.copergas.portalchamados.model.util.BeanFactory;
import br.com.copergas.portalchamados.model.util.Constantes;
import br.com.copergas.portalchamados.model.util.MensagemUtil;
import br.com.copergas.portalchamados.model.util.Util;

/**
 * 
 * @author Roberto Alencar
 */
class TicketServiceImpl extends ServiceImpl implements TicketService {

    @Override
    public EntidadeNegocio criar() {
	return (EntidadeNegocio) BeanFactory.getBean(Ticket.BEAN_NAME);
    }

    @Override
    public Class<?> getClasseEntidade() {
	return BeanFactory.getClassPorID(Ticket.BEAN_NAME);
    }

    @Override
    public EntidadeNegocio criarTicketCategoria() {
	return (EntidadeNegocio) BeanFactory.getBean(TicketCategoria.BEAN_NAME);
    }

    @Override
    public Class<?> getClasseEntidadeTicketCategoria() {
	return BeanFactory.getClassPorID(TicketCategoria.BEAN_NAME);
    }
    
    @Override
    public EntidadeNegocio criarTicketSubCategoria() {
	return (EntidadeNegocio) BeanFactory.getBean(TicketSubCategoria.BEAN_NAME);
    }
    
    @Override
    public EntidadeNegocio criarStatusCopergas() {
	return (EntidadeNegocio) BeanFactory.getBean(StatusCopergas.BEAN_NAME);
    }

    @Override
    public Class<?> getClasseEntidadeTicketSubCategoria() {
	return BeanFactory.getClassPorID(TicketSubCategoria.BEAN_NAME);
    }
    
    @Override
    public EntidadeNegocio criarSetor() {
	return (EntidadeNegocio) BeanFactory.getBean(Setor.BEAN_NAME);
    }

    @Override
    public Class<?> getClasseEntidadeSetor() {
	return BeanFactory.getClassPorID(Setor.BEAN_NAME);
    }
    
    @Override
    public EntidadeNegocio criarTipoChamado() {
	return (EntidadeNegocio) BeanFactory.getBean(TipoChamado.BEAN_NAME);
    }

    @Override
    public Class<?> getClasseEntidadeTipoChamado() {
	return BeanFactory.getClassPorID(TipoChamado.BEAN_NAME);
    }
    
    @Override
    public EntidadeNegocio criarOrigemChamado() {
	return (EntidadeNegocio) BeanFactory.getBean(OrigemChamado.BEAN_NAME);
    }
    
    @Override
    public EntidadeNegocio criarStatusFornecedor() {
	return (EntidadeNegocio) BeanFactory.getBean(StatusFornecedor.BEAN_NAME);
    }
    
    @Override
    public EntidadeNegocio criarRegistroImportacao() {
	return (EntidadeNegocio) BeanFactory.getBean(RegistroImportacao.BEAN_NAME);
    }
    
    @Override
    public Class<?> getClasseEntidadeRegistroImportacao() {
	return BeanFactory.getClassPorID(RegistroImportacao.BEAN_NAME);
    }
    
    @Override
    public Class<?> getClasseEntidadeStatusFornecedor() {
	return BeanFactory.getClassPorID(StatusFornecedor.BEAN_NAME);
    }

    @Override
    public Class<?> getClasseEntidadeOrigemChamado() {
	return BeanFactory.getClassPorID(OrigemChamado.BEAN_NAME);
    }
    
    @Override
    public EntidadeNegocio criarSistema() {
	return (EntidadeNegocio) BeanFactory.getBean(Sistema.BEAN_NAME);
    }

    @Override
    public Class<?> getClasseEntidadeSistema() {
	return BeanFactory.getClassPorID(Sistema.BEAN_NAME);
    }
    
    @Override
    public EntidadeNegocio criarStatus() {
	return (EntidadeNegocio) BeanFactory.getBean(Status.BEAN_NAME);
    }

    @Override
    public Class<?> getClasseEntidadeStatus() {
	return BeanFactory.getClassPorID(Status.BEAN_NAME);
    }
    
    @Override
    public EntidadeNegocio criarPrioridade() {
	return (EntidadeNegocio) BeanFactory.getBean(Prioridade.BEAN_NAME);
    }

    @Override
    public Class<?> getClasseEntidadePrioridade() {
	return BeanFactory.getClassPorID(Prioridade.BEAN_NAME);
    }
    
    @Override
    public EntidadeNegocio criarTipoAtendimento() {
	return (EntidadeNegocio) BeanFactory.getBean(TipoAtendimento.BEAN_NAME);
    }

    @Override
    public Class<?> getClasseEntidadeTipoAtendimento() {
	return BeanFactory.getClassPorID(TipoAtendimento.BEAN_NAME);
    }
    
    @Override
    public Class<?> getClasseEntidadeStatusCopergas() {
	return BeanFactory.getClassPorID(StatusCopergas.BEAN_NAME);
    }
    
    @Override
    public void importarTicketsSpiceworks(InputStream arquivo) throws NegocioException, ConcorrenciaException {

	// Variável que irá o arquivo da planilha
	Workbook planilha = null;

	// Variável que irá quardar a aba que será utilizada da planilha
	Sheet sheet;

	try {
	    // Obtem a planilha de acordo com um caminho específico
	    planilha = Workbook.getWorkbook(arquivo);

	    // Indica qual aba será usada. Nesse caso a primera (0)
	    sheet = planilha.getSheet(0);

	    // Pega a quantiddade de linhas
	    int qtdLinhas = sheet.getRows();
	    int qtdTicketInseridos = 0;
	    int qtdTicketAlterados = 0;
	    
	    //Prepara os arrays para controle se já foi importado ou não
	    List<Ticket> ticketsJaExistente = (List<Ticket>) listarTodasEntidadeNegocio(getClasseEntidade(), Boolean.TRUE);
	    List<Usuario> usuariosJaExistente = (List<Usuario>) listarTodasEntidadeNegocio(BeanFactory.getClassPorID(Usuario.BEAN_NAME), Boolean.FALSE);
	    List<EntidadeRelacionamento> categoriasJaExistente = listarTodasEntidadeRelacionamento(getClasseEntidadeTicketCategoria(), Boolean.FALSE);
	    List<EntidadeRelacionamento> subCategoriasJaExistente = listarTodasEntidadeRelacionamento(getClasseEntidadeTicketSubCategoria(), Boolean.FALSE);
	    List<EntidadeRelacionamento> setoresJaExistente = listarTodasEntidadeRelacionamento(getClasseEntidadeSetor(), Boolean.FALSE);
	    List<EntidadeRelacionamento> tiposChamadoJaExistente = listarTodasEntidadeRelacionamento(getClasseEntidadeTipoChamado(), Boolean.FALSE);
	    List<EntidadeRelacionamento> origensChamadoJaExistente = listarTodasEntidadeRelacionamento(getClasseEntidadeOrigemChamado(), Boolean.FALSE);
	    List<EntidadeRelacionamento> sistemasJaExistente = listarTodasEntidadeRelacionamento(getClasseEntidadeSistema(), Boolean.FALSE);
	    List<EntidadeRelacionamento> statusCopergasJaExistente = listarTodasEntidadeRelacionamento(getClasseEntidadeStatusCopergas(), Boolean.FALSE);
	    List<EntidadeRelacionamento> statusFornecedorJaExistente = listarTodasEntidadeRelacionamento(getClasseEntidadeStatusFornecedor(), Boolean.FALSE);
	    List<EntidadeRelacionamento> statusJaExistente = listarTodasEntidadeRelacionamento(getClasseEntidadeStatus(), Boolean.FALSE);
	    List<EntidadeRelacionamento> prioridadesJaExistente = listarTodasEntidadeRelacionamento(getClasseEntidadePrioridade(), Boolean.FALSE);
	    List<EntidadeRelacionamento> tiposAtendimentoJaExistente = listarTodasEntidadeRelacionamento(getClasseEntidadeTipoAtendimento(), Boolean.FALSE);
	    
	    for (int i = 0; i < qtdLinhas; i++) {
		
		System.out.println("Linhas processadas: " + qtdLinhas + " - " + i);

		// Esse IF é necessário para pular o cabeçalho do arquivo
		if (i >= 6) {
		    
		    Ticket ticket = new TicketImpl();

		    Cell numTicket = sheet.getCell(0, i);
		    Cell relatedTo = sheet.getCell(1, i);
		    Cell assignedTo = sheet.getCell(2, i);
		    Cell category = sheet.getCell(3, i);
		    Cell subCategoria = sheet.getCell(4, i);
		    Cell createDate = sheet.getCell(5, i);
		    Cell dueDate = sheet.getCell(6, i);
		    Cell closeDate = sheet.getCell(7, i);
		    Cell summary = sheet.getCell(8, i);
		    Cell description = sheet.getCell(9, i);
		    Cell fechamentoEncaminhado = sheet.getCell(10, i);
		    Cell setor = sheet.getCell(11, i);
		    Cell solicitante = sheet.getCell(12, i);
		    Cell tipoDeChamado = sheet.getCell(13, i);
		    Cell origem = sheet.getCell(14, i);
		    Cell sistema = sheet.getCell(15, i);
		    Cell statusCopergas = sheet.getCell(16, i);
		    Cell statusFornecedor = sheet.getCell(17, i);
		    Cell status = sheet.getCell(18, i);
		    Cell priority = sheet.getCell(19, i);
		    Cell reopened = sheet.getCell(20, i);
		    Cell tipoAtendimento = sheet.getCell(21, i);

		    ticket.setNumero(numTicket.getContents());
		    ticket.setMaquinaOrigem(relatedTo.getContents());
		    ticket.setUsuario(inserirUsuarioNaImportacao(usuariosJaExistente, assignedTo.getContents()));
		    ticket.setCategoria((TicketCategoria)inserirRelacionamentoNaImportacao(categoriasJaExistente, getClasseEntidadeTicketCategoria(), category.getContents()));
		    ticket.setSubCategoria((TicketSubCategoria)inserirRelacionamentoNaImportacao(subCategoriasJaExistente, getClasseEntidadeTicketSubCategoria(), subCategoria.getContents()));
		    ticket.setDataCriacao(transformarEmData(createDate.getContents()));
		    ticket.setDataVencimento(transformarEmData(dueDate.getContents()));
		    ticket.setDataFechamento(transformarEmData(closeDate.getContents()));
		    ticket.setTitulo(summary.getContents());
		    
		    String descricao = description.getContents().replace('{',' ');
		    descricao = descricao.replace('}',' ');
		    ticket.setDescricao(descricao);
		    
		    ticket.setFechamentoEncaminhado(obterValorBooleano(fechamentoEncaminhado.getContents()));
		    ticket.setSetor((Setor)inserirRelacionamentoNaImportacao(setoresJaExistente, getClasseEntidadeSetor(), setor.getContents()));
		    ticket.setSolicitante(solicitante.getContents());
		    ticket.setTipoChamado((TipoChamado)inserirRelacionamentoNaImportacao(tiposChamadoJaExistente, getClasseEntidadeTipoChamado(), tipoDeChamado.getContents()));
		    ticket.setOrigemChamado((OrigemChamado)inserirRelacionamentoNaImportacao(origensChamadoJaExistente, getClasseEntidadeOrigemChamado(), origem.getContents()));
		    ticket.setSistema((Sistema)inserirRelacionamentoNaImportacao(sistemasJaExistente, getClasseEntidadeSistema(), sistema.getContents()));
		    ticket.setStatusCopergas((StatusCopergas)inserirRelacionamentoNaImportacao(statusCopergasJaExistente, getClasseEntidadeStatusCopergas(), statusCopergas.getContents()));
		    ticket.setStatusFornecedor((StatusFornecedor)inserirRelacionamentoNaImportacao(statusFornecedorJaExistente, getClasseEntidadeStatusFornecedor(), statusFornecedor.getContents()));
		    ticket.setStatus((Status)inserirRelacionamentoNaImportacao(statusJaExistente, getClasseEntidadeStatus(), status.getContents()));
		    ticket.setPrioridade((Prioridade)inserirRelacionamentoNaImportacao(prioridadesJaExistente, getClasseEntidadePrioridade(), priority.getContents()));
		    ticket.setFoiReaberto(obterValorBooleano(reopened.getContents()));
		    ticket.setTipoAtendimento((TipoAtendimento)inserirRelacionamentoNaImportacao(tiposAtendimentoJaExistente, getClasseEntidadeTipoAtendimento(), tipoAtendimento.getContents()));
		    
		    Ticket ticketConsultado = obterTicketPorNumero(ticketsJaExistente, ticket.getNumero());
		    if (ticketConsultado == null) {
			ticket.setUltimaAlteracao(Calendar.getInstance().getTime());
			ticket.setHabilitado(Boolean.TRUE);
			getHibernateTemplate().save(ticket);
			ticketsJaExistente.add(ticket);
			qtdTicketInseridos = qtdTicketInseridos + 1;
		    } else {
			preencherTicket(ticketConsultado, ticket);
			ticketConsultado.incrementarVersao();
			ticketConsultado.setUltimaAlteracao(Calendar.getInstance().getTime());
			getHibernateTemplate().update(ticketConsultado);
			qtdTicketAlterados = qtdTicketAlterados + 1;
		    }
		}
	    }
	    
	    RegistroImportacao registroImportacao = (RegistroImportacao) obter(RegistroImportacao.VALOR_UNICO_REGISTRO_IMPORTACAO, getClasseEntidadeRegistroImportacao());
	    registroImportacao.setQtdTicketProcessados(qtdLinhas);
	    registroImportacao.setQtdTicketInseridos(qtdTicketInseridos);
	    registroImportacao.setQtdTicketAlterados(qtdTicketAlterados);
	    registroImportacao.incrementarVersao();
	    registroImportacao.setDataUltimaImportacao(Calendar.getInstance().getTime());
	    registroImportacao.setUltimaAlteracao(Calendar.getInstance().getTime());
	    getHibernateTemplate().update(registroImportacao);

	} catch (BiffException e) {
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if (planilha != null) {
		planilha.close();
	    }
	}
    }
    
    private void preencherTicket(Ticket ticketConsultado, Ticket ticket) {
	ticketConsultado.setNumero(ticket.getNumero());
	ticketConsultado.setMaquinaOrigem(ticket.getMaquinaOrigem());
	ticketConsultado.setUsuario(ticket.getUsuario());
	ticketConsultado.setCategoria(ticket.getCategoria());
	ticketConsultado.setSubCategoria(ticket.getSubCategoria());
	ticketConsultado.setDataCriacao(ticket.getDataCriacao());
	ticketConsultado.setDataVencimento(ticket.getDataVencimento());
	ticketConsultado.setDataFechamento(ticket.getDataFechamento());
	ticketConsultado.setTitulo(ticket.getTitulo());
	ticketConsultado.setDescricao(ticket.getDescricao());
	ticketConsultado.setFechamentoEncaminhado(ticket.getFechamentoEncaminhado());
	ticketConsultado.setSetor(ticket.getSetor());
	ticketConsultado.setSolicitante(ticket.getSolicitante());
	ticketConsultado.setTipoChamado(ticket.getTipoChamado());
	ticketConsultado.setOrigemChamado(ticket.getOrigemChamado());
	ticketConsultado.setSistema(ticket.getSistema());
	ticketConsultado.setStatus(ticket.getStatus());
	ticketConsultado.setPrioridade(ticket.getPrioridade());
	ticketConsultado.setFoiReaberto(ticket.getFoiReaberto());
	ticketConsultado.setTipoAtendimento(ticket.getTipoAtendimento());
	ticketConsultado.setStatusCopergas(ticket.getStatusCopergas());
	ticketConsultado.setStatusFornecedor(ticket.getStatusFornecedor());
    }

    @SuppressWarnings("unchecked")
    public List<Ticket> listarTicket(Map<String, Object> filtro) throws NegocioException {
	
	DetachedCriteria criteria = DetachedCriteria.forClass(getClasseEntidade());
	criteria.add(Restrictions.eq(EntidadeNegocio.ATRIBUTO_HABILITADO, Boolean.TRUE));
	criteria.addOrder(Order.desc(Ticket.ATRIBUTO_DATA_CRIACAO));
	
	if (!filtro.isEmpty()) {
	    if (filtro.get(Ticket.ATRIBUTO_NUMERO) != null && filtro.get(Ticket.ATRIBUTO_NUMERO) != "") {
		criteria.add(Restrictions.ilike(Ticket.ATRIBUTO_NUMERO, "%" + filtro.get(Ticket.ATRIBUTO_NUMERO) + "%"));
	    }
	    if (filtro.get(Ticket.ATRIBUTO_TITULO) != null && filtro.get(Ticket.ATRIBUTO_TITULO) != "") {
		criteria.add(Restrictions.ilike(Ticket.ATRIBUTO_TITULO, "%" + filtro.get(Ticket.ATRIBUTO_TITULO) + "%"));
	    }
	    if (filtro.get(Ticket.ATRIBUTO_STATUS) != null) {
		if (filtro.get(Ticket.ATRIBUTO_STATUS) instanceof Long) {
		    criteria.add(Restrictions.eq(Ticket.ATRIBUTO_STATUS + "." + Ticket.ATRIBUTO_CHAVE_PRIMARIA,
			    filtro.get(Ticket.ATRIBUTO_STATUS)));
		} else {
		    criteria.add(Restrictions.eq(Ticket.ATRIBUTO_STATUS, filtro.get(Ticket.ATRIBUTO_STATUS)));
		}
	    }
	    if (filtro.get(Ticket.ATRIBUTO_USUARIO) != null) {
		if (filtro.get(Ticket.ATRIBUTO_USUARIO) instanceof Long) {
		    criteria.add(Restrictions.eq(Ticket.ATRIBUTO_USUARIO + "." + Ticket.ATRIBUTO_CHAVE_PRIMARIA,
			    filtro.get(Ticket.ATRIBUTO_USUARIO)));
		} else {
		    criteria.add(Restrictions.eq(Ticket.ATRIBUTO_USUARIO, filtro.get(Ticket.ATRIBUTO_USUARIO)));
		}
	    }
	    if (filtro.get(Constantes.FILTRO_DATA_INICIAL) != null || filtro.get(Constantes.FILTRO_DATA_FINAL) != null) {
		criteria.add(Restrictions.between(Ticket.ATRIBUTO_DATA_CRIACAO, filtro.get(Constantes.FILTRO_DATA_INICIAL),
			filtro.get(Constantes.FILTRO_DATA_FINAL)));
	    }
	    if (filtro.get(Constantes.FILTRO_DATA_INICIAL_VENCIMENTO) != null || filtro.get(Constantes.FILTRO_DATA_FINAL_VENCIMENTO) != null) {
		criteria.add(Restrictions.between(Ticket.ATRIBUTO_DATA_VENCIMENTO, filtro.get(Constantes.FILTRO_DATA_INICIAL_VENCIMENTO),
			filtro.get(Constantes.FILTRO_DATA_FINAL_VENCIMENTO)));
	    }
	    if (filtro.get(Constantes.FILTRO_DATA_INICIAL_FECHAMENTO) != null || filtro.get(Constantes.FILTRO_DATA_FINAL_FECHAMENTO) != null) {
		criteria.add(Restrictions.between(Ticket.ATRIBUTO_DATA_FECHAMENTO, filtro.get(Constantes.FILTRO_DATA_INICIAL_FECHAMENTO),
			filtro.get(Constantes.FILTRO_DATA_FINAL_FECHAMENTO)));
	    }
	    if (filtro.get(Ticket.ATRIBUTO_SISTEMA) != null) {
		criteria.add(Restrictions.eq(Ticket.ATRIBUTO_SISTEMA, filtro.get(Ticket.ATRIBUTO_SISTEMA)));
	    }
	    if (filtro.get(Ticket.ATRIBUTO_CATEGORIA) != null) {
		criteria.add(Restrictions.eq(Ticket.ATRIBUTO_CATEGORIA, filtro.get(Ticket.ATRIBUTO_CATEGORIA)));
	    }
	    if (filtro.get(Ticket.ATRIBUTO_SUB_CATEGORIA) != null) {
		criteria.add(Restrictions.eq(Ticket.ATRIBUTO_SUB_CATEGORIA, filtro.get(Ticket.ATRIBUTO_SUB_CATEGORIA)));
	    }
	    if (filtro.get(Ticket.ATRIBUTO_SETOR) != null) {
		criteria.add(Restrictions.eq(Ticket.ATRIBUTO_SETOR, filtro.get(Ticket.ATRIBUTO_SETOR)));
	    }
	    if (filtro.get(Ticket.ATRIBUTO_ORIGEM_CHAMADO) != null) {
		criteria.add(Restrictions.eq(Ticket.ATRIBUTO_ORIGEM_CHAMADO, filtro.get(Ticket.ATRIBUTO_ORIGEM_CHAMADO)));
	    }
	    if (filtro.get(Ticket.ATRIBUTO_DESCRICAO) != null && filtro.get(Ticket.ATRIBUTO_DESCRICAO) != "") {
		criteria.add(Restrictions.ilike(Ticket.ATRIBUTO_DESCRICAO, "%" + filtro.get(Ticket.ATRIBUTO_DESCRICAO) + "%"));
	    }
	    if (filtro.get(Ticket.ATRIBUTO_TIPO_CHAMADO) != null) {
		criteria.add(Restrictions.eq(Ticket.ATRIBUTO_TIPO_CHAMADO, filtro.get(Ticket.ATRIBUTO_TIPO_CHAMADO)));
	    }
	    if (filtro.get(Ticket.ATRIBUTO_STATUS_COPERGAS) != null) {
		criteria.add(Restrictions.eq(Ticket.ATRIBUTO_STATUS_COPERGAS, filtro.get(Ticket.ATRIBUTO_STATUS_COPERGAS)));
	    }
	    if (filtro.get(Ticket.ATRIBUTO_STATUS_FORNECEDOR) != null) {
		criteria.add(Restrictions.eq(Ticket.ATRIBUTO_STATUS_FORNECEDOR, filtro.get(Ticket.ATRIBUTO_STATUS_FORNECEDOR)));
	    }
	    if (filtro.get(Ticket.ATRIBUTO_TIPO_ATENDIMENTO) != null) {
		criteria.add(Restrictions.eq(Ticket.ATRIBUTO_TIPO_ATENDIMENTO, filtro.get(Ticket.ATRIBUTO_TIPO_ATENDIMENTO)));
	    }
	    if (filtro.get(Ticket.ATRIBUTO_PRIORIDADE) != null) {
		criteria.add(Restrictions.eq(Ticket.ATRIBUTO_PRIORIDADE, filtro.get(Ticket.ATRIBUTO_PRIORIDADE)));
	    }
	}
	
	List<Ticket> listaTicket = getHibernateTemplate().findByCriteria(criteria); 
	calculaSLAIndicadores(listaTicket);
	return listaTicket;
    }
    
    public void calculaSLAIndicadores(List<Ticket> listaTicket) {

	for (Ticket ticket : listaTicket) {

	    int tempoMaximoBaixa = 0;
	    int tempoMaximoMedia = 0;
	    int tempoMaximoAlta = 0;
	    
	    if (ticket.getTipoAtendimento() != null) {
		if (ticket.getTipoAtendimento().getChavePrimaria() == 1) {

		    // Tipo de Atendimento = Suporte Interno
		    if (ticket.getSistema() != null) {
			// Chamados de sistemas
			tempoMaximoBaixa = Util.obterSLA("suporteInterno.sistemas.tempoMaximoBaixa");
			tempoMaximoMedia = Util.obterSLA("suporteInterno.sistemas.tempoMaximoMedia");
			tempoMaximoAlta = Util.obterSLA("suporteInterno.sistemas.tempoMaximoAlta");
		    } else {
			// Chamados de infra
			tempoMaximoBaixa = Util.obterSLA("suporteInterno.infra.tempoMaximoBaixa");
			tempoMaximoMedia = Util.obterSLA("suporteInterno.infra.tempoMaximoMedia");
			tempoMaximoAlta = Util.obterSLA("suporteInterno.infra.tempoMaximoAlta");
		    }

		} else {

		    // Tipo de Atendimento = Suporte Externo

		    // Unigás
		    if ((ticket.getSistema() != null)
			    && (Sistema.VALOR_SISTEMA_UNIGAS.equals(ticket.getSistema().getDescricao()))) {
			tempoMaximoBaixa = Util.obterSLA("suporteExterno.sistemas.UNIGAS.nivel03");
			tempoMaximoMedia = Util.obterSLA("suporteExterno.sistemas.UNIGAS.nivel02");
			tempoMaximoAlta = Util.obterSLA("suporteExterno.sistemas.UNIGAS.nivel01");
		    }

		    // ERP
		    if ((ticket.getCategoria() != null)
			    && ("Suporte ao ERP Pirâmide".equals(ticket.getCategoria().getDescricao()))) {

			tempoMaximoBaixa = Util.obterSLA("suporteExterno.sistemas.ERP.nivel03");
			tempoMaximoMedia = Util.obterSLA("suporteExterno.sistemas.ERP.nivel02");
			tempoMaximoAlta = Util.obterSLA("suporteExterno.sistemas.ERP.nivel01");
		    }
		}

		Calendar dataCriacao = Calendar.getInstance();
		dataCriacao.setTime(ticket.getDataCriacao());

		Calendar dataLimiteSLA = Calendar.getInstance();
		dataLimiteSLA.setTime(ticket.getDataCriacao());
		if (ticket.getPrioridade().getChavePrimaria() == 3) {
		    // Prioridade: "Low"
		    dataLimiteSLA.add(Calendar.HOUR_OF_DAY, tempoMaximoAlta);
		} else if (ticket.getPrioridade().getChavePrimaria() == 1) {
		    // Prioridade: "Med"
		    dataLimiteSLA.add(Calendar.HOUR_OF_DAY, tempoMaximoMedia);
		} else {
		    // Prioridade: "High"
		    dataLimiteSLA.add(Calendar.HOUR_OF_DAY, tempoMaximoBaixa);
		}

		Calendar dataReferencia = Calendar.getInstance();
		if (Status.VALOR_STATUS_CLOSE == ticket.getStatus().getChavePrimaria()) {
		    dataReferencia.setTime(ticket.getDataFechamento());
		}

		if (dataReferencia.compareTo(dataLimiteSLA) > 0) {
		    ticket.setUltrapassouSLA(Boolean.TRUE);
		} else {
		    ticket.setUltrapassouSLA(Boolean.FALSE);
		}
	    } else {
		ticket.setUltrapassouSLA(null);
	    }
	}
    }
    
    @SuppressWarnings("unchecked")
    public List<Ticket> listarTicketIndicadores(Map<String, Object> filtro) throws NegocioException {
	
	DetachedCriteria criteria = DetachedCriteria.forClass(getClasseEntidade());
	criteria.add(Restrictions.eq(EntidadeNegocio.ATRIBUTO_HABILITADO, Boolean.TRUE));
	
	if (!filtro.isEmpty()) {

	    if (filtro.get(Ticket.ATRIBUTO_STATUS) != null) {
		if (filtro.get(Ticket.ATRIBUTO_STATUS) instanceof Long) {
		    criteria.add(Restrictions.eq(Ticket.ATRIBUTO_STATUS + "." + Ticket.ATRIBUTO_CHAVE_PRIMARIA,
			    filtro.get(Ticket.ATRIBUTO_STATUS)));
		} else {
		    criteria.add(Restrictions.eq(Ticket.ATRIBUTO_STATUS, filtro.get(Ticket.ATRIBUTO_STATUS)));
		}
	    }
	    
	    if (filtro.get(Constantes.FILTRO_DATA_INICIAL) != null || filtro.get(Constantes.FILTRO_DATA_FINAL) != null) {
		criteria.add(Restrictions.between(Ticket.ATRIBUTO_DATA_CRIACAO,
			filtro.get(Constantes.FILTRO_DATA_INICIAL), filtro.get(Constantes.FILTRO_DATA_FINAL)));
	    }
	    
	    if (filtro.get(Constantes.FILTRO_DATA_INICIAL_FECHAMENTO) != null || filtro.get(Constantes.FILTRO_DATA_FINAL_FECHAMENTO) != null) {
		criteria.add(Restrictions.between(Ticket.ATRIBUTO_DATA_FECHAMENTO,
			filtro.get(Constantes.FILTRO_DATA_INICIAL_FECHAMENTO),
			filtro.get(Constantes.FILTRO_DATA_FINAL_FECHAMENTO)));
	    }
	    
	    if (filtro.get(Ticket.ATRIBUTO_SISTEMA) != null) {
		criteria.add(Restrictions.eq(Ticket.ATRIBUTO_SISTEMA, filtro.get(Ticket.ATRIBUTO_SISTEMA)));
	    }
	    
	    if (filtro.get(Ticket.ATRIBUTO_SETOR) != null) {
		criteria.add(Restrictions.eq(Ticket.ATRIBUTO_SETOR, filtro.get(Ticket.ATRIBUTO_SETOR)));
	    }
	}
	
	return getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public void preInsercao(EntidadeNegocio entidadeNegocio) throws NegocioException { 
	
	obterNumeroTicket((Ticket) entidadeNegocio);
	verificaCamposObrigatorios((Ticket) entidadeNegocio);
    }
    
    private void obterNumeroTicket(Ticket ticketInserido) {
	DetachedCriteria criteria = DetachedCriteria.forClass(getClasseEntidade());
	criteria.addOrder(Order.desc(Ticket.ATRIBUTO_CHAVE_PRIMARIA));
	List<Ticket> listarTicket = getHibernateTemplate().findByCriteria(criteria);
	
	String numero = "";
	if ((listarTicket != null) && (!listarTicket.isEmpty())) {
	    Ticket ticket = listarTicket.get(0);
	    numero = String.valueOf(ticket.getChavePrimaria() + 1);
	} else {
	    numero = "1";
	}
	
	ticketInserido.setNumero(numero);
    }

    private void verificaCamposObrigatorios(Ticket ticket) throws NegocioException {
	StringBuilder sb = new StringBuilder();
	String camposObrigatorios = null;

	if ((ticket.getTitulo()) == null || ("".equals(ticket.getTitulo()))) {
	    sb.append(Ticket.LABEL_TITULO);
	    sb.append(", ");
	}

	if ((ticket.getDescricao() == null) || ("".equals(ticket.getDescricao()))) {
	    sb.append(Ticket.LABEL_DESCRICAO);
	    sb.append(", ");
	}

	camposObrigatorios = sb.toString();

	if (camposObrigatorios.length() > 0) {
	    throw new NegocioException(MensagemUtil.obterMensagem(Constantes.RESOURCE_BUNDLE,
		    Constantes.PARAMETRO_MSG_CAMPOS_OBRIGATORIOS,
		    camposObrigatorios.substring(0, sb.toString().length() - 2)));
	}
    }
    
    private Ticket obterTicketPorNumero(List<Ticket> ticketsJaExistente, String numero) throws NegocioException {

	Ticket ticket = null;

	for (Ticket ticketObj : ticketsJaExistente) {
	    if (numero.equals(ticketObj.getNumero())) {
		System.out.println("-------------------------------------------- Número repedito encontrado: "+numero);
		ticket = ticketObj;
		break;
	    }
	}

	return ticket;
    }
    
    private Boolean obterValorBooleano(String param) {
	Boolean valorBooleano = null;
	
	if ("Não".equals(param)) {
	    valorBooleano = Boolean.FALSE;;
	} else if ("Sim".equals(param)) {
	    valorBooleano = Boolean.TRUE;
	}
	
	return valorBooleano;
    }
    
    private EntidadeRelacionamento inserirRelacionamentoNaImportacao(List<EntidadeRelacionamento> lista,
	    Class<?> classe, String descricao) throws NegocioException {

	EntidadeRelacionamento entidadeRelacionamento = null;
	
	if ((descricao != null) && (!"".equals(descricao))) {

	    for (EntidadeRelacionamento entidadeRelacionamentoObj : lista) {
		if (descricao.equals(entidadeRelacionamentoObj.getDescricao())) {
		    entidadeRelacionamento = entidadeRelacionamentoObj;
		    break;
		}
	    }
	    
	    if (entidadeRelacionamento == null) {
		if (getClasseEntidadeSetor().equals(classe)) {
		    Setor setor = (Setor) criarSetor();
		    setor.setDescricao(descricao);
		    inserirRelacionamento(setor);
		    lista.add(setor);
		    entidadeRelacionamento = setor;
		} else if (getClasseEntidadeTipoChamado().equals(classe)) {
		    TipoChamado tipoChamado = (TipoChamado) criarTipoChamado();
		    tipoChamado.setDescricao(descricao);
		    inserirRelacionamento(tipoChamado);
		    lista.add(tipoChamado);
		    entidadeRelacionamento = tipoChamado;
		} else if (getClasseEntidadeOrigemChamado().equals(classe)) {
		    OrigemChamado origemChamado = (OrigemChamado) criarOrigemChamado();
		    origemChamado.setDescricao(descricao);
		    inserirRelacionamento(origemChamado);
		    lista.add(origemChamado);
		    entidadeRelacionamento = origemChamado;
		} else if (getClasseEntidadeSistema().equals(classe)) {
		    Sistema sistema = (Sistema) criarSistema();
		    sistema.setDescricao(descricao);
		    inserirRelacionamento(sistema);
		    lista.add(sistema);
		    entidadeRelacionamento = sistema;
		} else if (getClasseEntidadeStatusCopergas().equals(classe)) {
		    StatusCopergas statusCopergas = (StatusCopergas) criarStatusCopergas();
		    statusCopergas.setDescricao(descricao);
		    inserirRelacionamento(statusCopergas);
		    lista.add(statusCopergas);
		    entidadeRelacionamento = statusCopergas;
		} else if (getClasseEntidadeStatusFornecedor().equals(classe)) {
		    StatusFornecedor statusFornecedor = (StatusFornecedor) criarStatusFornecedor();
		    statusFornecedor.setDescricao(descricao);
		    inserirRelacionamento(statusFornecedor);
		    lista.add(statusFornecedor);
		    entidadeRelacionamento = statusFornecedor;
		} else if (getClasseEntidadeStatus().equals(classe)) {
		    Status status = (Status) criarStatus();
		    status.setDescricao(descricao);
		    inserirRelacionamento(status);
		    lista.add(status);
		    entidadeRelacionamento = status;
		} else if (getClasseEntidadePrioridade().equals(classe)) {
		    Prioridade prioridade = (Prioridade) criarPrioridade();
		    prioridade.setDescricao(descricao);
		    inserirRelacionamento(prioridade);
		    lista.add(prioridade);
		    entidadeRelacionamento = prioridade;
		} else if (getClasseEntidadeTipoAtendimento().equals(classe)) {
		    TipoAtendimento tipoAtendimento = (TipoAtendimento) criarTipoAtendimento();
		    tipoAtendimento.setDescricao(descricao);
		    inserirRelacionamento(tipoAtendimento);
		    lista.add(tipoAtendimento);
		    entidadeRelacionamento = tipoAtendimento;
		} else if (getClasseEntidadeTicketCategoria().equals(classe)) {
		    TicketCategoria ticketCategoria = (TicketCategoria) criarTicketCategoria();
		    ticketCategoria.setDescricao(descricao);
		    inserirRelacionamento(ticketCategoria);
		    lista.add(ticketCategoria);
		    entidadeRelacionamento = ticketCategoria;
		} else if (getClasseEntidadeTicketSubCategoria().equals(classe)) {
		    TicketSubCategoria ticketSubCategoria = (TicketSubCategoria) criarTicketSubCategoria();
		    ticketSubCategoria.setDescricao(descricao);
		    inserirRelacionamento(ticketSubCategoria);
		    lista.add(ticketSubCategoria);
		    entidadeRelacionamento = ticketSubCategoria;
		}
	    }
	}
	
	return entidadeRelacionamento;
    }
    
    private void inserirRelacionamento(EntidadeNegocio entidadeNegocio) throws NegocioException {
	entidadeNegocio.setUltimaAlteracao(Calendar.getInstance().getTime());
	entidadeNegocio.setHabilitado(Boolean.TRUE);
	Long chavePrimaria = (Long) getHibernateTemplate().save(entidadeNegocio);
	entidadeNegocio.setChavePrimaria(chavePrimaria);
    }
    
    private Usuario inserirUsuarioNaImportacao(List<Usuario> usuariosJaExistente, String nome) throws NegocioException {

	Usuario usuario = null;
	
	if ((nome != null) && (!"".equals(nome))) {

	    for (Usuario usuarioObj : usuariosJaExistente) {
		if (nome.equals(usuarioObj.getNome())) {
		    usuario = usuarioObj;
		    break;
		}
	    }
	    
	    if (usuario == null) {
		Usuario novoUsuario = (Usuario) (EntidadeNegocio) BeanFactory.getBean(Usuario.BEAN_NAME);
		novoUsuario.setNome(nome);
		inserirRelacionamento(novoUsuario);
		usuariosJaExistente.add(novoUsuario);
		usuario = novoUsuario;
	    }
	} else {
	    for (Usuario usuarioObj : usuariosJaExistente) {
		if (Usuario.VALOR_USUARIO_UNASSIGNED.equals(usuarioObj.getChavePrimaria())) {
		    usuario = usuarioObj;
		    break;
		}
	    }
	}
	
	return usuario;
    }
    
    private Date transformarEmData(String conteudo) {

	Date data = null;

	if ((conteudo != null) && (!"".equals(conteudo))) {

	    String dia = conteudo.substring(0, 2);
	    String mes = conteudo.substring(3, 5);
	    String ano = conteudo.substring(6, 10);
	    String hora = conteudo.substring(13, 15);
	    String minuto = conteudo.substring(16, 18);
	    
	    String periodo = conteudo.substring(19, 21);
	    Integer horaConvertida = null;
	    horaConvertida = Integer.parseInt(hora);
	    if (periodo == "PM") {
		if (hora == "12") {
		    horaConvertida = 0;
		} else {
		    horaConvertida = horaConvertida + 12;
		}
	    }

	    GregorianCalendar calendar = new GregorianCalendar();
	    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
	    calendar.set(Calendar.MONTH, (Integer.parseInt(mes)-1));
	    calendar.set(Calendar.YEAR, Integer.parseInt(ano));
	    calendar.set(Calendar.HOUR_OF_DAY, horaConvertida);
	    calendar.set(Calendar.MINUTE, Integer.parseInt(minuto));
	    data = calendar.getTime();
	}

	return data;
    }

}