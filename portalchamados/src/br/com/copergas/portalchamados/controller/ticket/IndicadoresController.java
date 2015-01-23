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
import br.com.copergas.portalchamados.model.ticket.Setor;
import br.com.copergas.portalchamados.model.ticket.Sistema;
import br.com.copergas.portalchamados.model.ticket.Status;
import br.com.copergas.portalchamados.model.ticket.Ticket;
import br.com.copergas.portalchamados.model.util.Constantes;

/**
 * @author roberto.alencar
 *
 */
@ManagedBean
@RequestScoped
public class IndicadoresController  extends ComumController {
    
    private int anoReferencia;
    private int mesReferencia;
    
    private int totalAbertos;
    private int totalFechados;
    
    private int qtdGERE;
    private int qtdGDIS;
    private int qtdGFIN;
    private int qtdGETI;
    private int qtdGCRC;
    private int qtdGMAR;
    private int qtdGADS;
    private int qtdDAF;
    private int qtdGERH;
    private int qtdCOMUN;
    private int qtdPRE;
    private int qtdQSMS;
    private int qtdGCVI;
    private int qtdCJUR;
    private int qtdDTC;
    private int qtdCAF;
    private int qtdSEGE;
    private int qtdGCON;
    private int qtdNaoInformadoSetor;
    private String chamadosSetorNaoInformado; 
    
    private int qtdERP;
    private int qtdERPManutencao;
    private int qtdUnigas;
    private int qtdERPContasReceber;
    private int qtdERPContasPagar;
    private int qtdERPAdministrador;
    private int qtdSGM;
    private int qtdERPEstoque;
    private int qtdERPCompras;
    private int qtdERPIntegracao;
    private int qtdERPFluxoCaixa;
    private int qtdERPContabilidade;
    private int qtdERPOrcamento;
    private int qtdERPRH;
    private int qtdERPFiscal;
    private int qtdERPControleContratos;
    private int qtdERPTesouraria;
    private int qtdERPCobranca;
    private int qtdERPObras;
    private int qtdERPAtivoFixo;
    private int qtdNaoInformado;
    private int qtdTotalGeral;
    private int qtdSESuit;
    private int qtdVolare;
    private int qtdGeogas;
    private int qtdSupervisorio;
    private int qtdSite;
    private int qtdNasajon;
    private int qtdIPiramide;

    
    
    public IndicadoresController() throws ProjetoException {
	
	Map<String, Object> filtro = new HashMap<String, Object>();
	filtro.put(Ticket.ATRIBUTO_STATUS, Status.VALOR_STATUS_OPEN);
	List<Ticket> listaTicket = fachada.listarTicketIndicadores(filtro);
	setQtdTotalGeral(listaTicket.size()); 
	
	calculaAbertosFechados();
	calcularTotaisPorSetor(listaTicket);
	calcularTotaisPorSistemas(listaTicket);
    }
    
    
    
    public int getAnoReferencia() {
        return anoReferencia;
    }
    public void setAnoReferencia(int anoReferencia) {
        this.anoReferencia = anoReferencia;
    }
    public int getQtdNasajon() {
        return qtdNasajon;
    }
    public void setQtdNasajon(int qtdNasajon) {
        this.qtdNasajon = qtdNasajon;
    }
    public int getQtdIPiramide() {
        return qtdIPiramide;
    }
    public void setQtdIPiramide(int qtdIPiramide) {
        this.qtdIPiramide = qtdIPiramide;
    }

    public int getQtdERP() {
        return qtdERP;
    }
    public void setQtdERP(int qtdERP) {
        this.qtdERP = qtdERP;
    }
    public int getMesReferencia() {
        return mesReferencia;
    }
    public void setMesReferencia(int mesReferencia) {
        this.mesReferencia = mesReferencia;
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
    public int getQtdGERE() {
        return qtdGERE;
    }
    public void setQtdGERE(int qtdGERE) {
        this.qtdGERE = qtdGERE;
    }
    public int getQtdGDIS() {
        return qtdGDIS;
    }
    public void setQtdGDIS(int qtdGDIS) {
        this.qtdGDIS = qtdGDIS;
    }
    public int getQtdGFIN() {
        return qtdGFIN;
    }
    public void setQtdGFIN(int qtdGFIN) {
        this.qtdGFIN = qtdGFIN;
    }
    public int getQtdGETI() {
        return qtdGETI;
    }
    public void setQtdGETI(int qtdGETI) {
        this.qtdGETI = qtdGETI;
    }
    public int getQtdGCRC() {
        return qtdGCRC;
    }
    public void setQtdGCRC(int qtdGCRC) {
        this.qtdGCRC = qtdGCRC;
    }
    public int getQtdGMAR() {
        return qtdGMAR;
    }
    public void setQtdGMAR(int qtdGMAR) {
        this.qtdGMAR = qtdGMAR;
    }
    public int getQtdGADS() {
        return qtdGADS;
    }
    public void setQtdGADS(int qtdGADS) {
        this.qtdGADS = qtdGADS;
    }
    public int getQtdDAF() {
        return qtdDAF;
    }
    public void setQtdDAF(int qtdDAF) {
        this.qtdDAF = qtdDAF;
    }
    public int getQtdGERH() {
        return qtdGERH;
    }
    public void setQtdGERH(int qtdGERH) {
        this.qtdGERH = qtdGERH;
    }
    public int getQtdCOMUN() {
        return qtdCOMUN;
    }
    public void setQtdCOMUN(int qtdCOMUN) {
        this.qtdCOMUN = qtdCOMUN;
    }
    public int getQtdPRE() {
        return qtdPRE;
    }
    public void setQtdPRE(int qtdPRE) {
        this.qtdPRE = qtdPRE;
    }
    public int getQtdQSMS() {
        return qtdQSMS;
    }
    public void setQtdQSMS(int qtdQSMS) {
        this.qtdQSMS = qtdQSMS;
    }
    public int getQtdGCVI() {
        return qtdGCVI;
    }
    public void setQtdGCVI(int qtdGCVI) {
        this.qtdGCVI = qtdGCVI;
    }
    public int getQtdCJUR() {
        return qtdCJUR;
    }
    public void setQtdCJUR(int qtdCJUR) {
        this.qtdCJUR = qtdCJUR;
    }
    public int getQtdDTC() {
        return qtdDTC;
    }
    public void setQtdDTC(int qtdDTC) {
        this.qtdDTC = qtdDTC;
    }
    public int getQtdCAF() {
        return qtdCAF;
    }
    public void setQtdCAF(int qtdCAF) {
        this.qtdCAF = qtdCAF;
    }
    public int getQtdSEGE() {
        return qtdSEGE;
    }
    public void setQtdSEGE(int qtdSEGE) {
        this.qtdSEGE = qtdSEGE;
    }
    public int getQtdGCON() {
        return qtdGCON;
    }
    public void setQtdGCON(int qtdGCON) {
        this.qtdGCON = qtdGCON;
    }
    public int getQtdNaoInformadoSetor() {
        return qtdNaoInformadoSetor;
    }
    public void setQtdNaoInformadoSetor(int qtdNaoInformadoSetor) {
        this.qtdNaoInformadoSetor = qtdNaoInformadoSetor;
    }
    public int getQtdERPManutencao() {
        return qtdERPManutencao;
    }
    public void setQtdERPManutencao(int qtdERPManutencao) {
        this.qtdERPManutencao = qtdERPManutencao;
    }
    public int getQtdUnigas() {
        return qtdUnigas;
    }
    public void setQtdUnigas(int qtdUnigas) {
        this.qtdUnigas = qtdUnigas;
    }
    public int getQtdERPContasReceber() {
        return qtdERPContasReceber;
    }
    public void setQtdERPContasReceber(int qtdERPContasReceber) {
        this.qtdERPContasReceber = qtdERPContasReceber;
    }
    public int getQtdERPContasPagar() {
        return qtdERPContasPagar;
    }
    public void setQtdERPContasPagar(int qtdERPContasPagar) {
        this.qtdERPContasPagar = qtdERPContasPagar;
    }
    public int getQtdERPAdministrador() {
        return qtdERPAdministrador;
    }
    public void setQtdERPAdministrador(int qtdERPAdministrador) {
        this.qtdERPAdministrador = qtdERPAdministrador;
    }
    public int getQtdSGM() {
        return qtdSGM;
    }
    public void setQtdSGM(int qtdSGM) {
        this.qtdSGM = qtdSGM;
    }
    public int getQtdERPEstoque() {
        return qtdERPEstoque;
    }
    public void setQtdERPEstoque(int qtdERPEstoque) {
        this.qtdERPEstoque = qtdERPEstoque;
    }
    public int getQtdERPCompras() {
        return qtdERPCompras;
    }
    public void setQtdERPCompras(int qtdERPCompras) {
        this.qtdERPCompras = qtdERPCompras;
    }
    public int getQtdERPIntegracao() {
        return qtdERPIntegracao;
    }
    public void setQtdERPIntegracao(int qtdERPIntegracao) {
        this.qtdERPIntegracao = qtdERPIntegracao;
    }
    public int getQtdERPFluxoCaixa() {
        return qtdERPFluxoCaixa;
    }
    public void setQtdERPFluxoCaixa(int qtdERPFluxoCaixa) {
        this.qtdERPFluxoCaixa = qtdERPFluxoCaixa;
    }
    public int getQtdERPContabilidade() {
        return qtdERPContabilidade;
    }
    public void setQtdERPContabilidade(int qtdERPContabilidade) {
        this.qtdERPContabilidade = qtdERPContabilidade;
    }
    public int getQtdERPOrcamento() {
        return qtdERPOrcamento;
    }
    public void setQtdERPOrcamento(int qtdERPOrcamento) {
        this.qtdERPOrcamento = qtdERPOrcamento;
    }
    public int getQtdERPRH() {
        return qtdERPRH;
    }
    public void setQtdERPRH(int qtdERPRH) {
        this.qtdERPRH = qtdERPRH;
    }
    public int getQtdERPFiscal() {
        return qtdERPFiscal;
    }
    public void setQtdERPFiscal(int qtdERPFiscal) {
        this.qtdERPFiscal = qtdERPFiscal;
    }
    public int getQtdERPControleContratos() {
        return qtdERPControleContratos;
    }
    public void setQtdERPControleContratos(int qtdERPControleContratos) {
        this.qtdERPControleContratos = qtdERPControleContratos;
    }
    public int getQtdERPTesouraria() {
        return qtdERPTesouraria;
    }
    public void setQtdERPTesouraria(int qtdERPTesouraria) {
        this.qtdERPTesouraria = qtdERPTesouraria;
    }
    public int getQtdERPCobranca() {
        return qtdERPCobranca;
    }
    public void setQtdERPCobranca(int qtdERPCobranca) {
        this.qtdERPCobranca = qtdERPCobranca;
    }
    public int getQtdERPObras() {
        return qtdERPObras;
    }
    public void setQtdERPObras(int qtdERPObras) {
        this.qtdERPObras = qtdERPObras;
    }
    public int getQtdERPAtivoFixo() {
        return qtdERPAtivoFixo;
    }
    public void setQtdERPAtivoFixo(int qtdERPAtivoFixo) {
        this.qtdERPAtivoFixo = qtdERPAtivoFixo;
    }
    public int getQtdNaoInformado() {
        return qtdNaoInformado;
    }
    public void setQtdNaoInformado(int qtdNaoInformado) {
        this.qtdNaoInformado = qtdNaoInformado;
    }
    public int getQtdTotalGeral() {
        return qtdTotalGeral;
    }
    public void setQtdTotalGeral(int qtdTotalGeral) {
        this.qtdTotalGeral = qtdTotalGeral;
    }
    public int getQtdSESuit() {
        return qtdSESuit;
    }
    public void setQtdSESuit(int qtdSESuit) {
        this.qtdSESuit = qtdSESuit;
    }
    public int getQtdVolare() {
        return qtdVolare;
    }
    public void setQtdVolare(int qtdVolare) {
        this.qtdVolare = qtdVolare;
    }
    public int getQtdGeogas() {
        return qtdGeogas;
    }
    public void setQtdGeogas(int qtdGeogas) {
        this.qtdGeogas = qtdGeogas;
    }
    public int getQtdSupervisorio() {
        return qtdSupervisorio;
    }
    public void setQtdSupervisorio(int qtdSupervisorio) {
        this.qtdSupervisorio = qtdSupervisorio;
    }
    public int getQtdSite() {
        return qtdSite;
    }
    public void setQtdSite(int qtdSite) {
        this.qtdSite = qtdSite;
    }
    public String getChamadosSetorNaoInformado() {
        return chamadosSetorNaoInformado;
    }
    public void setChamadosSetorNaoInformado(String chamadosSetorNaoInformado) {
        this.chamadosSetorNaoInformado = chamadosSetorNaoInformado;
    }

    
    public void calculaAbertosFechados() throws ProjetoException {
	
	Calendar dataFiltro = GregorianCalendar.getInstance();
	dataFiltro.set(Calendar.YEAR, this.anoReferencia);
	dataFiltro.set(Calendar.MONTH, this.mesReferencia);
        
        dataFiltro.set(Calendar.DAY_OF_MONTH, dataFiltro.getActualMinimum(Calendar.DAY_OF_MONTH));
	Date dataInicial = dataFiltro.getTime();
	
	dataFiltro.set(Calendar.DAY_OF_MONTH, dataFiltro.getActualMaximum(Calendar.DAY_OF_MONTH));  
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
    
    private void calcularTotaisPorSetor(List<Ticket> listaTicket) throws ProjetoException {

	StringBuffer sb = new StringBuffer();
	
	for (Ticket ticket : listaTicket) {
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
		    qtdGMAR = qtdGMAR + 1;
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
		qtdNaoInformadoSetor = qtdNaoInformadoSetor + 1;
		sb.append(ticket.getNumero());
		sb.append(" - ");
	    }
	}
	
	setChamadosSetorNaoInformado(sb.toString());
    }
    
    private void calcularTotaisPorSistemas(List<Ticket> listaTicket) {

	for (Ticket ticket : listaTicket) {
	    if (ticket.getSistema() != null) {
		if (Sistema.VALOR_SISTEMA_ERP_MANUTENCAO.equals(ticket.getSistema().getDescricao())) {
		    qtdERPManutencao = qtdERPManutencao + 1;
		} else if (Sistema.VALOR_SISTEMA_UNIGAS.equals(ticket.getSistema().getDescricao())) {
		    qtdUnigas = qtdUnigas + 1;
		} else if (Sistema.VALOR_SISTEMA_SESUIT.equals(ticket.getSistema().getDescricao())) {
		    qtdSESuit = qtdSESuit + 1;
		} else if (Sistema.VALOR_SISTEMA_PINIVOLARE.equals(ticket.getSistema().getDescricao())) {
		    qtdVolare = qtdVolare + 1;
		} else if (Sistema.VALOR_SISTEMA_GEOGAS.equals(ticket.getSistema().getDescricao())) {
		    qtdGeogas = qtdGeogas + 1;
		} else if (Sistema.VALOR_SISTEMA_SUPERVISORIO.equals(ticket.getSistema().getDescricao())) {
		    qtdSupervisorio = qtdSupervisorio + 1;
		} else if (Sistema.VALOR_SISTEMA_SITE.equals(ticket.getSistema().getDescricao())) {
		    qtdSite = qtdSite + 1;
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
		} else if (Sistema.VALOR_SISTEMA_NASAJON.equals(ticket.getSistema().getDescricao())) {
		    qtdNasajon = qtdNasajon + 1;
		} else if (Sistema.VALOR_SISTEMA_IPIRAMIDE.equals(ticket.getSistema().getDescricao())) {
		    qtdIPiramide = qtdIPiramide + 1;
		}
	    } else {
		qtdNaoInformado = qtdNaoInformado + 1;
	    }

	    if (ticket.getCategoria() != null) {
		if ("Suporte ao ERP Pirâmide".equals(ticket.getCategoria().getDescricao())) {
		    qtdERP = qtdERP + 1;
		}
	    }
	}
    }

}