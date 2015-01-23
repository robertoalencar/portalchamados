package br.com.copergas.portalchamados.model.ticket;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeRelacionamento;

/**
 * @author roberto.alencar
 * 
 */
public interface TipoChamado extends EntidadeRelacionamento {

    String BEAN_NAME = "tipoChamado";

    String VALOR_TIPO_CHAMADO_INCIDENTE = "(IN) Incidente";
    String VALOR_TIPO_CHAMADO_SOLICITACAO_SERVICO = "(SS) Solicita��o de Servi�o";
    String VALOR_TIPO_CHAMADO_SOLICITACAO_INFORMACAO = "(SI) Solicita��o de Informa��o";
    String VALOR_TIPO_CHAMADO_RECLAMACAO = "(RE) Reclama��o";
    
}