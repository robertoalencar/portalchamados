package br.com.copergas.portalchamados.model.usuario.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.copergas.portalchamados.model.comum.excecoes.NegocioException;
import br.com.copergas.portalchamados.model.comum.negocio.EntidadeNegocio;
import br.com.copergas.portalchamados.model.comum.negocio.impl.ServiceImpl;
import br.com.copergas.portalchamados.model.usuario.Usuario;
import br.com.copergas.portalchamados.model.usuario.UsuarioService;
import br.com.copergas.portalchamados.model.util.BeanFactory;

/**
 * @author Roberto Alencar
 * 
 */
class UsuarioServiceImpl extends ServiceImpl implements UsuarioService {

    @Override
    public EntidadeNegocio criar() {
	return (EntidadeNegocio) BeanFactory.getBean(Usuario.BEAN_NAME);
    }

    @Override
    public Class<?> getClasseEntidade() {
	return BeanFactory.getClassPorID(Usuario.BEAN_NAME);
    }
 
    @SuppressWarnings("unchecked")
    public List<Usuario> listarUsuario(Map<String, Object> filtro) throws NegocioException {
	
	DetachedCriteria criteria = DetachedCriteria.forClass(getClasseEntidade());
	criteria.add(Restrictions.eq(EntidadeNegocio.ATRIBUTO_HABILITADO, Boolean.TRUE));
	criteria.addOrder(Order.asc(Usuario.ATRIBUTO_NOME));
	
	if (!filtro.isEmpty()) {
	    if (filtro.get(Usuario.ATRIBUTO_NOME) != null) {
		criteria.add(Restrictions.ilike(Usuario.ATRIBUTO_NOME, "%" + filtro.get(Usuario.ATRIBUTO_NOME) + "%"));
	    }
	}
	
	return getHibernateTemplate().findByCriteria(criteria);
    }

}