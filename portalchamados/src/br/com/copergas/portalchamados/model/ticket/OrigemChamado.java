package br.com.copergas.portalchamados.model.ticket;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeRelacionamento;

/**
 * @author roberto.alencar
 * 
 */
public interface OrigemChamado extends EntidadeRelacionamento {

    String BEAN_NAME = "origemChamado";

    String VALOR_ORIGEM_CHAMADO_PORTAL = "Portal";
    String VALOR_ORIGEM_CHAMADO_EMAIL = "Email";
    String VALOR_ORIGEM_CHAMADO_PRO_ATIVO = "Pró-Ativo";
    String VALOR_ORIGEM_CHAMADO_PRESENCIAL = "Presencial";
    String VALOR_ORIGEM_CHAMADO_TELEFONE = "Telefone";
    
}