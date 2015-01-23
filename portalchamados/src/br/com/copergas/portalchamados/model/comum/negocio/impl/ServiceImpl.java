package br.com.copergas.portalchamados.model.comum.negocio.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.copergas.portalchamados.model.comum.excecoes.ConcorrenciaException;
import br.com.copergas.portalchamados.model.comum.excecoes.NegocioException;
import br.com.copergas.portalchamados.model.comum.negocio.EntidadeNegocio;
import br.com.copergas.portalchamados.model.comum.negocio.EntidadeRelacionamento;
import br.com.copergas.portalchamados.model.comum.negocio.Service;
import br.com.copergas.portalchamados.model.util.Constantes;

/**
 * @author Roberto Alencar
 * 
 */
public abstract class ServiceImpl extends HibernateDaoSupport implements Service {

    String FILTRO_PESQUISA_DESCRICAO = "descricao";
    
    public abstract EntidadeNegocio criar();

    public abstract Class<?> getClasseEntidade();

    public EntidadeNegocio obter(long chavePrimaria) throws NegocioException {
	return this.obter(chavePrimaria, getClasseEntidade());
    }

    public EntidadeNegocio obter(long chavePrimaria, Class<?> classe) throws NegocioException {
	EntidadeNegocio entidade = null;

	entidade = (EntidadeNegocio) getHibernateTemplate().get(classe, chavePrimaria);

	if (entidade == null) {
	    throw new NegocioException(Constantes.ERRO_ENTIDADE_NAO_ENCONTRADA, classe.getName());
	}

	return entidade;
    }
    
    @SuppressWarnings("unchecked")
    public List<EntidadeRelacionamento> listarTodasEntidadeRelacionamento(Class<?> classe, boolean apenasHabilitados) {
	DetachedCriteria criteria = DetachedCriteria.forClass(classe);
	if (apenasHabilitados) {
	    criteria.add(Restrictions.eq(EntidadeNegocio.ATRIBUTO_HABILITADO, Boolean.TRUE));
	}
	return getHibernateTemplate().findByCriteria(criteria);
    }
    
    @SuppressWarnings("unchecked")
    public List<? extends EntidadeNegocio> listarTodasEntidadeNegocio(Class<?> classe, boolean apenasHabilitados) {
	DetachedCriteria criteria = DetachedCriteria.forClass(classe);
	if (apenasHabilitados) {
	    criteria.add(Restrictions.eq(EntidadeNegocio.ATRIBUTO_HABILITADO, Boolean.TRUE));
	}
	return getHibernateTemplate().findByCriteria(criteria);
    }
    
    public EntidadeNegocio consultarPorDescricao(String descricao, Class<?> classe) throws NegocioException {

	EntidadeNegocio entidadeNegocio = null;
	
	DetachedCriteria criteria = DetachedCriteria.forClass(classe);
	criteria.add(Restrictions.eq(EntidadeNegocio.ATRIBUTO_HABILITADO, Boolean.TRUE));
	criteria.add(Restrictions.eq(FILTRO_PESQUISA_DESCRICAO, descricao));
	List<EntidadeNegocio> lista = getHibernateTemplate().findByCriteria(criteria);
	
	if ((lista != null) && (!lista.isEmpty())) {
	    entidadeNegocio = lista.get(0);
	}
	
	return entidadeNegocio;
    }
    
    public List<? extends EntidadeNegocio> carregarCombobox(Boolean habilitado, Class<?> classe) throws NegocioException {
	
	DetachedCriteria criteria = DetachedCriteria.forClass(classe);
	criteria.add(Restrictions.eq(EntidadeNegocio.ATRIBUTO_HABILITADO, habilitado));
	criteria.addOrder(Order.asc(FILTRO_PESQUISA_DESCRICAO));
	return getHibernateTemplate().findByCriteria(criteria);
    }

    public long inserir(EntidadeNegocio entidadeNegocio) throws NegocioException {
	Long chavePrimaria = null;

	preInsercao(entidadeNegocio);
	entidadeNegocio.setUltimaAlteracao(Calendar.getInstance().getTime());
	entidadeNegocio.setHabilitado(Boolean.TRUE);
	chavePrimaria = (Long) getHibernateTemplate().save(entidadeNegocio);
	entidadeNegocio.setChavePrimaria(chavePrimaria);
	posInsercao(entidadeNegocio);

	return chavePrimaria.longValue();
    }

    public void atualizar(EntidadeNegocio entidadeNegocio) throws ConcorrenciaException, NegocioException {
	atualizar(entidadeNegocio, getClasseEntidade());
    }

    public void atualizar(EntidadeNegocio entidadeNegocio, Class<?> classe) throws ConcorrenciaException,
	    NegocioException {

	preAtualizacao(entidadeNegocio);
	validarVersaoEntidade(entidadeNegocio, classe);
	entidadeNegocio.incrementarVersao();
	entidadeNegocio.setUltimaAlteracao(Calendar.getInstance().getTime());
	getHibernateTemplate().update(entidadeNegocio);
	posAtualizacao(entidadeNegocio);
    }

    public void remover(EntidadeNegocio entidadeNegocio) throws NegocioException {
	this.remover(entidadeNegocio, Boolean.TRUE);
    }

    public void remover(EntidadeNegocio entidadeNegocio, boolean fisicamente) throws NegocioException {
	preRemocao(entidadeNegocio);

	if (fisicamente) {
	    getHibernateTemplate().delete(entidadeNegocio);
	} else {
	    entidadeNegocio.setHabilitado(Boolean.FALSE);
	    getHibernateTemplate().update(entidadeNegocio);
	}

	posRemocao(entidadeNegocio);
    }

    public void validarVersaoEntidade(EntidadeNegocio entidadeNova, Class<?> classe) throws ConcorrenciaException,
	    NegocioException {

	DetachedCriteria criteria = DetachedCriteria.forClass(classe);
	criteria.add(Restrictions.eq(EntidadeNegocio.ATRIBUTO_CHAVE_PRIMARIA, entidadeNova.getChavePrimaria()));
	EntidadeNegocio entidadeConsultada = (EntidadeNegocio) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
	
	if (entidadeNova.getVersao() != entidadeConsultada.getVersao()) {
	    throw new ConcorrenciaException(Constantes.ERRO_ENTIDADE_VERSAO_DESATUALIZADA, classe.getName());
	}
    }

    public void preInsercao(EntidadeNegocio entidadeNegocio) throws NegocioException { }
    
    public void posInsercao(EntidadeNegocio entidadeNegocio) throws NegocioException { }
    
    public void posAtualizacao(EntidadeNegocio entidadeNegocio) throws NegocioException { }

    public void preAtualizacao(EntidadeNegocio entidadeNegocio) throws NegocioException { }

    public void preRemocao(EntidadeNegocio entidadeNegocio) throws NegocioException { }
    
    public void posRemocao(EntidadeNegocio entidadeNegocio) throws NegocioException { }

}