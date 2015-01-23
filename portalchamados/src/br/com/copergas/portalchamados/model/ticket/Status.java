package br.com.copergas.portalchamados.model.ticket;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeRelacionamento;

/**
 * @author roberto.alencar
 * 
 */
public interface Status extends EntidadeRelacionamento {

    String BEAN_NAME = "status";

    Long VALOR_STATUS_OPEN = 1L;
    Long VALOR_STATUS_CLOSE = 2L;

}