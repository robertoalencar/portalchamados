package br.com.copergas.portalchamados.model.comum.negocio;

/**
 * @author Roberto Alencar
 *
 */
public interface EntidadeRelacionamento extends EntidadeNegocio {

    String ATRIBUTO_DESCRICAO = "descricao";
    String LABEL_DESCRICAO = "Descri��o";

    String getDescricao();
    void setDescricao(String descricao);
    
}