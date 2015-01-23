package br.com.copergas.portalchamados.model.produto.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.copergas.portalchamados.model.comum.excecoes.NegocioException;
import br.com.copergas.portalchamados.model.comum.negocio.EntidadeNegocio;
import br.com.copergas.portalchamados.model.comum.negocio.impl.ServiceImpl;
import br.com.copergas.portalchamados.model.produto.Produto;
import br.com.copergas.portalchamados.model.produto.ProdutoCategoria;
import br.com.copergas.portalchamados.model.produto.ProdutoService;
import br.com.copergas.portalchamados.model.util.BeanFactory;
import br.com.copergas.portalchamados.model.util.Constantes;
import br.com.copergas.portalchamados.model.util.MensagemUtil;

/**
 * @author Roberto Alencar
 * 
 */
class ProdutoServiceImpl extends ServiceImpl implements ProdutoService {

    @Override
    public EntidadeNegocio criar() {
	return (EntidadeNegocio) BeanFactory.getBean(Produto.BEAN_NAME);
    }

    @Override
    public Class<?> getClasseEntidade() {
	return BeanFactory.getClassPorID(Produto.BEAN_NAME);
    }
    
    public EntidadeNegocio criarProdutoCategoria() {
	return (EntidadeNegocio) BeanFactory.getBean(ProdutoCategoria.BEAN_NAME);
    }

    public Class<?> getClasseEntidadeProdutoCategoria() {
	return BeanFactory.getClassPorID(ProdutoCategoria.BEAN_NAME);
    }
    
    public List<Produto> listarProduto(Map<String, Object> filtro) throws NegocioException {
	
	DetachedCriteria criteria = DetachedCriteria.forClass(getClasseEntidade());
	criteria.add(Restrictions.eq(EntidadeNegocio.ATRIBUTO_HABILITADO, Boolean.TRUE));
	criteria.addOrder(Order.asc(Produto.ATRIBUTO_DESCRICAO));
	
	if (!filtro.isEmpty()) {
	    if (filtro.get(Produto.ATRIBUTO_CODIGO) != null) {
		criteria.add(Restrictions.ilike(Produto.ATRIBUTO_CODIGO, "%" + filtro.get(Produto.ATRIBUTO_CODIGO) + "%"));
	    }
	    if (filtro.get(Produto.ATRIBUTO_DESCRICAO) != null) {
		criteria.add(Restrictions.ilike(Produto.ATRIBUTO_DESCRICAO, "%" + filtro.get(Produto.ATRIBUTO_DESCRICAO) + "%"));
	    }
	    if (filtro.get(Produto.ATRIBUTO_PRODUTO_CATEGORIA) != null) {
		criteria.add(Restrictions.eq(Produto.ATRIBUTO_PRODUTO_CATEGORIA, filtro.get(Produto.ATRIBUTO_PRODUTO_CATEGORIA)));
	    }
	    if (filtro.get(Constantes.FILTRO_DATA_INICIAL) != null || filtro.get(Constantes.FILTRO_DATA_FINAL) != null) {
		criteria.add(Restrictions.between(Produto.ATRIBUTO_DATA, filtro.get(Constantes.FILTRO_DATA_INICIAL),
			filtro.get(Constantes.FILTRO_DATA_FINAL)));
	    }
	}
	
	return getHibernateTemplate().findByCriteria(criteria);
    }
    
    public List<ProdutoCategoria> listarProdutoCategoria(Boolean habilitado) throws NegocioException {
	
	DetachedCriteria criteria = DetachedCriteria.forClass(getClasseEntidadeProdutoCategoria());
	criteria.add(Restrictions.eq(EntidadeNegocio.ATRIBUTO_HABILITADO, habilitado));
	criteria.addOrder(Order.asc(ProdutoCategoria.ATRIBUTO_DESCRICAO));
	return getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public void preInsercao(EntidadeNegocio entidadeNegocio) throws NegocioException {
	
	verificaCamposObrigatorios((Produto) entidadeNegocio);
	verificaDuplicidade((Produto) entidadeNegocio);
    }
    
    @Override
    public void preAtualizacao(EntidadeNegocio entidadeNegocio) throws NegocioException {
        
	verificaCamposObrigatorios((Produto) entidadeNegocio);
	verificaDuplicidade((Produto) entidadeNegocio);
    }
    
    private void verificaCamposObrigatorios(Produto produto) throws NegocioException {
	StringBuilder sb = new StringBuilder();
	String camposObrigatorios = null;

	if (produto.getCodigo() == null || produto.getCodigo().length() == 0) {
	    sb.append(Produto.LABEL_CODIGO);
	    sb.append(", ");
	}

	if (produto.getDescricao() == null || produto.getDescricao().length() == 0) {
	    sb.append(Produto.LABEL_DESCRICAO);
	    sb.append(", ");
	}

	if (produto.getValor() == null || produto.getValor() == 0) {
	    sb.append(Produto.LABEL_VALOR);
	    sb.append(", ");
	}

	camposObrigatorios = sb.toString();

	if (camposObrigatorios.length() > 0) {
	    throw new NegocioException(MensagemUtil.obterMensagem(Constantes.RESOURCE_BUNDLE,
		    Constantes.PARAMETRO_MSG_CAMPOS_OBRIGATORIOS,
		    camposObrigatorios.substring(0, sb.toString().length() - 2)));
	}
    }
    
    private void verificaDuplicidade(Produto produto) throws NegocioException {
	
	DetachedCriteria criteria = DetachedCriteria.forClass(getClasseEntidade());
	criteria.add(Restrictions.eq(EntidadeNegocio.ATRIBUTO_HABILITADO, Boolean.TRUE));
	criteria.add(Restrictions.eq(Produto.ATRIBUTO_CODIGO, produto.getCodigo()).ignoreCase());
	
	if (produto.getChavePrimaria() > 0) {
	    criteria.add(Restrictions.ne(Produto.ATRIBUTO_CHAVE_PRIMARIA, produto.getChavePrimaria()));
	}
	
	Collection<EntidadeNegocio> resultado = getHibernateTemplate().findByCriteria(criteria);
	if (resultado != null && resultado.size() > 0) {
	    Object argumentos[] = {Produto.BEAN_NAME, "Código "+produto.getCodigo(), "Código"};  
	    throw new NegocioException(MensagemUtil.obterMensagem(Constantes.RESOURCE_BUNDLE,
		    Constantes.PARAMETRO_MSG_OBJ_DUPLICADO, argumentos));
	}
    }

}