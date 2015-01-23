package br.com.copergas.portalchamados.model.ticket;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeRelacionamento;

/**
 * @author roberto.alencar
 * 
 */
public interface TipoChamado extends EntidadeRelacionamento {

    String BEAN_NAME = "tipoChamado";

    String VALOR_TIPO_CHAMADO_INCIDENTE = "(IN) Incidente";
    String VALOR_TIPO_CHAMADO_SOLICITACAO_SERVICO = "(SS) Solicitação de Serviço";
    String VALOR_TIPO_CHAMADO_SOLICITACAO_INFORMACAO = "(SI) Solicitação de Informação";
    String VALOR_TIPO_CHAMADO_RECLAMACAO = "(RE) Reclamação";
    
}