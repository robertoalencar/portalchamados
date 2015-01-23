package br.com.copergas.portalchamados.model.ticket;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeRelacionamento;

/**
 * @author roberto.alencar
 * 
 */
public interface StatusCopergas extends EntidadeRelacionamento {

    String BEAN_NAME = "statusCopergas";
    
    String VALOR_STATUS_SEM_RESPOSTA = "Sem Resposta do Usu�rio";
    String VALOR_STATUS_RESPOSTA_POSITIVA = "Resposta Positiva do Usu�rio";
    String VALOR_STATUS_ANALISANDO_ENCERRAMENTO = "Analisando Encerramento";
    String VALOR_STATUS_SOLICITACAO_ENCERRAMENTO = "Solicita��o de Encerramento Enviada";

}