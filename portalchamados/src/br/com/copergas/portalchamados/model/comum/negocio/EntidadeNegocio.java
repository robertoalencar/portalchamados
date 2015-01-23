package br.com.copergas.portalchamados.model.comum.negocio;

import java.util.Date;

/**
 * @author Roberto Alencar
 * 
 */
public interface EntidadeNegocio {

    /**
     * ATRIBUTOS
     */
    String ATRIBUTO_CHAVE_PRIMARIA = "chavePrimaria";
    String ATRIBUTO_VERSAO = "versao";
    String ATRIBUTO_ULTIMA_ALTERACAO = "ultimaAlteracao";
    String ATRIBUTO_HABILITADO = "habilitado";

    long getChavePrimaria();

    void setChavePrimaria(long chavePrimaria);

    int getVersao();

    void setVersao(int versao);

    Date getUltimaAlteracao();

    void setUltimaAlteracao(Date ultimaAlteracao);

    boolean isHabilitado();

    void setHabilitado(boolean habilitado);

    void incrementarVersao();

}