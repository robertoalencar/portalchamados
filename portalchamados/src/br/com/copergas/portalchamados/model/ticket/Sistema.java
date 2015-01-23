package br.com.copergas.portalchamados.model.ticket;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeRelacionamento;

/**
 * @author roberto.alencar
 *
 */
public interface Sistema extends EntidadeRelacionamento {

    String BEAN_NAME = "sistema";
    
    String VALOR_SISTEMA_SESUIT = "SE-Suite";
    String VALOR_SISTEMA_PINIVOLARE = "Pini Volare";
    String VALOR_SISTEMA_GEOGAS = "GeoGas";
    String VALOR_SISTEMA_SUPERVISORIO = "Supervisorio";
    String VALOR_SISTEMA_SITE = "Site Copergás";
    
    
    String VALOR_SISTEMA_ERP_MANUTENCAO = "ERP Manutenção";
    String VALOR_SISTEMA_UNIGAS = "Unigás";
    String VALOR_SISTEMA_ERP_CONTAS_RECEBER = "ERP Contas a Receber";
    String VALOR_SISTEMA_ERP_CONTAS_PAGAR = "ERP Contas a Pagar";
    String VALOR_SISTEMA_ERP_ADMINISTRADOR = "ERP Administrador";
    String VALOR_SISTEMA_ERP_SGM = "SGM";
    String VALOR_SISTEMA_ERP_ESTOQUE = "ERP Estoque";
    String VALOR_SISTEMA_ERP_COMPRAS = "ERP Compras";
    String VALOR_SISTEMA_ERP_INTEGRACAO = "ERP Integração";
    String VALOR_SISTEMA_ERP_FLUXO_CAIXA = "ERP Fluxo de Caixa";
    String VALOR_SISTEMA_ERP_CONTABILIDADE = "ERP Contabilidade";
    String VALOR_SISTEMA_ERP_ORCAMENTO = "ERP Orçamento";
    String VALOR_SISTEMA_ERP_RH = "ERP RH";
    String VALOR_SISTEMA_ERP_FISCAL = "ERP Fiscal";
    String VALOR_SISTEMA_ERP_CONTROLE_CONTRATOS = "ERP Controle de Contratos";
    String VALOR_SISTEMA_ERP_TESOURARIA = "ERP Tesouraria";
    String VALOR_SISTEMA_ERP_COBRANCA = "ERP Cobrança";
    String VALOR_SISTEMA_ERP_OBRAS = "ERP Obras";
    String VALOR_SISTEMA_ERP_ATIVO_FIXO = "ERP Ativo Fixo";
    String VALOR_SISTEMA_NASAJON = "Nasajon";
    String VALOR_SISTEMA_IPIRAMIDE = "iPirâmide";

}