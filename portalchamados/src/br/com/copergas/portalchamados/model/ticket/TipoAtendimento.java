package br.com.copergas.portalchamados.model.ticket;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeRelacionamento;

/**
 * @author roberto.alencar
 *
 */
public interface TipoAtendimento extends EntidadeRelacionamento {

    String BEAN_NAME = "tipoAtendimento";

    String VALOR_TIPO_ATENDIMENTO_SUPORTE_INTERNO = "Suporte Interno";
    String VALOR_TIPO_ATENDIMENTO_SUPORTE_EXTERNO = "Suporte Externo";
    String VALOR_TIPO_ATENDIMENTO_BUG = "BUG";
    String VALOR_TIPO_ATENDIMENTO_SUPORTE_CONFIGURACAO = "Suporte/Configuração";
    
}