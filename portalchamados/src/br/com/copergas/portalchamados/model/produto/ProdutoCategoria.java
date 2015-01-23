package br.com.copergas.portalchamados.model.produto;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeNegocio;

public interface ProdutoCategoria extends EntidadeNegocio {
    
    String BEAN_NAME = "produtoCategoria";

    String ATRIBUTO_DESCRICAO = "descricao";
    String LABEL_DESCRICAO = "Descrição";

    String getDescricao();

    void setDescricao(String descricao);

}