package br.com.copergas.portalchamados.model.produto;

import java.util.Date;

import br.com.copergas.portalchamados.model.comum.negocio.EntidadeNegocio;

/**
 * @author Roberto Alencar
 * 
 */
public interface Produto extends EntidadeNegocio {

    String BEAN_NAME = "produto";

    String ATRIBUTO_PRODUTO_CATEGORIA = "produtoCategoria";
    String ATRIBUTO_CODIGO = "codigo";
    String ATRIBUTO_DESCRICAO = "descricao";
    String ATRIBUTO_DATA = "data";
    String ATRIBUTO_VALOR = "valor";
    String LABEL_PRODUTO_CATEGORIA = "Categoria";
    String LABEL_CODIGO = "Código";
    String LABEL_DESCRICAO = "Descrição";
    String LABEL_DATA = "Data";
    String LABEL_VALOR = "Valor";

    
    ProdutoCategoria getProdutoCategoria();

    void setProdutoCategoria(ProdutoCategoria produtoCategoria);
    
    String getCodigo();

    void setCodigo(String codigo);

    Date getData();

    void setData(Date data);

    String getDescricao();

    void setDescricao(String descricao);

    Double getValor();

    void setValor(Double valor);

}