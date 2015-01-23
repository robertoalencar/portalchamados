package br.com.copergas.portalchamados.controller.ticket;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.copergas.portalchamados.controller.util.ComumController;
import br.com.copergas.portalchamados.model.comum.excecoes.ProjetoException;
import br.com.copergas.portalchamados.model.ticket.RegistroImportacao;
import br.com.copergas.portalchamados.model.ticket.Sistema;
import br.com.copergas.portalchamados.model.ticket.Ticket;
import br.com.copergas.portalchamados.model.util.Constantes;

@ManagedBean
@RequestScoped
public class IndicadoresTotaisAnoController extends ComumController {

    private RegistroImportacao registroImportacao;
    
    private int anoReferencia;
    private int totalAbertos;
    private int totalFechados;
    private int totalAbertosSistemas = 0;
    private int totalAbertosSuporte = 0;
    private int mediaMensalSistemas = 0;
    private int mediaMensalSuporte = 0;
    
    public IndicadoresTotaisAnoController() throws ProjetoException {
	setRegistroImportacao(fachada.obterRegistroImportacao(RegistroImportacao.VALOR_UNICO_REGISTRO_IMPORTACAO));
	this.anoReferencia = 2010;
	calculaAbertosFechados();
	calculaAbertosSistemasSuporte();
    }
    
    
    public RegistroImportacao getRegistroImportacao() {
        return registroImportacao;
    }
    public void setRegistroImportacao(RegistroImportacao registroImportacao) {
        this.registroImportacao = registroImportacao;
    }
    public int getAnoReferencia() {
        return anoReferencia;
    }
    public void setAnoReferencia(int anoReferencia) {
        this.anoReferencia = anoReferencia;
    }
    public int getTotalAbertos() {
        return totalAbertos;
    }
    public void setTotalAbertos(int totalAbertos) {
        this.totalAbertos = totalAbertos;
    }
    public int getTotalFechados() {
        return totalFechados;
    }
    public void setTotalFechados(int totalFechados) {
        this.totalFechados = totalFechados;
    }
    public int getTotalAbertosSistemas() {
        return totalAbertosSistemas;
    }
    public void setTotalAbertosSistemas(int totalAbertosSistemas) {
        this.totalAbertosSistemas = totalAbertosSistemas;
    }
    public int getTotalAbertosSuporte() {
        return totalAbertosSuporte;
    }
    public void setTotalAbertosSuporte(int totalAbertosSuporte) {
        this.totalAbertosSuporte = totalAbertosSuporte;
    }
    public int getMediaMensalSistemas() {
        return mediaMensalSistemas;
    }
    public void setMediaMensalSistemas(int mediaMensalSistemas) {
        this.mediaMensalSistemas = mediaMensalSistemas;
    }
    public int getMediaMensalSuporte() {
        return mediaMensalSuporte;
    }
    public void setMediaMensalSuporte(int mediaMensalSuporte) {
        this.mediaMensalSuporte = mediaMensalSuporte;
    }


    public void calculaDados() throws ProjetoException {
	
	calculaAbertosFechados();
	calculaAbertosSistemasSuporte();
    }
    
    public void calculaAbertosFechados() throws ProjetoException {

	Calendar dataFiltro = GregorianCalendar.getInstance();
	dataFiltro.set(Calendar.YEAR, this.anoReferencia);
	
	dataFiltro.set(Calendar.DAY_OF_MONTH, 01);
	dataFiltro.set(Calendar.MONTH, 00);
	Date dataInicial = dataFiltro.getTime();
	
	dataFiltro.set(Calendar.DAY_OF_MONTH, 31);
	dataFiltro.set(Calendar.MONTH, 11);
	Date dataFinal = dataFiltro.getTime();
	
	Map<String, Object> filtro;
	List<Ticket> listaTicket;

	filtro = new HashMap<String, Object>();
	filtro.put(Constantes.FILTRO_DATA_INICIAL, dataInicial);
	filtro.put(Constantes.FILTRO_DATA_FINAL, dataFinal);
	listaTicket = fachada.listarTicketIndicadores(filtro);
	this.totalAbertos = listaTicket.size();

	filtro = new HashMap<String, Object>();
	filtro.put(Constantes.FILTRO_DATA_INICIAL_FECHAMENTO, dataInicial);
	filtro.put(Constantes.FILTRO_DATA_FINAL_FECHAMENTO, dataFinal);
	listaTicket = fachada.listarTicketIndicadores(filtro);
	this.totalFechados = listaTicket.size();
    }
    
    public void calculaAbertosSistemasSuporte() throws ProjetoException {

	Calendar dataFiltro = GregorianCalendar.getInstance();
	dataFiltro.set(Calendar.YEAR, this.anoReferencia);
	
	dataFiltro.set(Calendar.DAY_OF_MONTH, 01);
	dataFiltro.set(Calendar.MONTH, 00);
	Date dataInicial = dataFiltro.getTime();
	
	dataFiltro.set(Calendar.DAY_OF_MONTH, 31);
	dataFiltro.set(Calendar.MONTH, 11);
	Date dataFinal = dataFiltro.getTime();
	
	Map<String, Object> filtro;
	List<Ticket> listaTicket;

	filtro = new HashMap<String, Object>();
	filtro.put(Constantes.FILTRO_DATA_INICIAL, dataInicial);
	filtro.put(Constantes.FILTRO_DATA_FINAL, dataFinal);
	listaTicket = fachada.listarTicketIndicadores(filtro);
	calculaTotaisPorSistemas(listaTicket);
    }
    
    private void calculaTotaisPorSistemas(List<Ticket> listaTicket) {

	totalAbertosSistemas = 0;
	totalAbertosSuporte = 0;
	
	for (Ticket ticket : listaTicket) {
	    if (ticket.getSistema() != null) {
		if (Sistema.VALOR_SISTEMA_ERP_MANUTENCAO.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_UNIGAS.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_SESUIT.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_PINIVOLARE.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_GEOGAS.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_SUPERVISORIO.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_SITE.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_CONTAS_RECEBER.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_CONTAS_PAGAR.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_ADMINISTRADOR.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_SGM.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_ESTOQUE.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_COMPRAS.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_INTEGRACAO.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_FLUXO_CAIXA.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_CONTABILIDADE.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_ORCAMENTO.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_RH.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_FISCAL.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_CONTROLE_CONTRATOS.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_TESOURARIA.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_COBRANCA.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_OBRAS.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (Sistema.VALOR_SISTEMA_ERP_ATIVO_FIXO.equals(ticket.getSistema().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		} else if (ticket.getCategoria() != null && "Suporte ao ERP Pirâmide".equals(ticket.getCategoria().getDescricao())) {
		    totalAbertosSistemas = totalAbertosSistemas + 1;
		}
	    } else {
		totalAbertosSuporte = totalAbertosSuporte + 1;
	    }
	}
	
	calculaMediaMensal();
    }
    
    private void calculaMediaMensal() {

	if (anoReferencia == 2013) {
	    mediaMensalSistemas = totalAbertosSistemas / 10;
	    mediaMensalSuporte = totalAbertosSuporte / 10;
	} else {
	    mediaMensalSistemas = totalAbertosSistemas / 12;
	    mediaMensalSuporte = totalAbertosSuporte / 12;
	}

    }
}