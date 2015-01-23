package br.com.copergas.portalchamados.model.ticket;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeRelacionamento;

/**
 * @author roberto.alencar
 *
 */
public interface Prioridade extends EntidadeRelacionamento {

    String BEAN_NAME = "prioridade";

    String VALOR_PRIORIDADE_HIGH = "Med";
    String VALOR_PRIORIDADE_MED = "High";
    String VALOR_PRIORIDADE_LOW = "Low";
    
}