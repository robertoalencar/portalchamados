package br.com.copergas.portalchamados.model.ticket;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeRelacionamento;

/**
 * @author roberto.alencar
 *
 */
public interface StatusFornecedor extends EntidadeRelacionamento {

    String BEAN_NAME = "statusFornecedor";
    
    String VALOR_STATUS_ENCERRADA = "Encerrada";
    String VALOR_STATUS_NOVA_FUNCIONALIDADE = "Nova Funcionalidade";
    String VALOR_STATUS_AGUARDANDO_ATUALIZACAO = "Aguardando Atualização";
    String VALOR_STATUS_BUG = "Bug";
    String VALOR_STATUS_MELHORIA = "Melhoria";
    
}