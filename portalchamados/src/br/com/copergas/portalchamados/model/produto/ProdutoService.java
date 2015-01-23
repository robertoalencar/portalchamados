package br.com.copergas.portalchamados.model.produto;

import java.util.List;
import java.util.Map;

import br.com.copergas.portalchamados.model.comum.excecoes.NegocioException;
import br.com.copergas.portalchamados.model.comum.negocio.EntidadeNegocio;
import br.com.copergas.portalchamados.model.comum.negocio.Service;

/**
 * @author Roberto Alencar
 * 
 */
public interface ProdutoService extends Service {

    String BEAN_NAME = "produtoService";

    List<Produto> listarProduto(Map<String, Object> filtro) throws NegocioException;
    
    List<ProdutoCategoria> listarProdutoCategoria(Boolean habilitado) throws NegocioException;
    
    EntidadeNegocio criarProdutoCategoria();

    Class<?> getClasseEntidadeProdutoCategoria();
}