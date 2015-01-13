package br.com.wofsolutions.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.wofsolutions.dominio.Usuario;
import br.com.wofsolutions.interfaces.UsuarioDAO;

public class UsuarioDAOImpl extends HibernateDAOImpl<Usuario, Object, Object> implements UsuarioDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Usuario getUsuarioPeloLogin(String login) {
		Criteria criteria = hibernateUtil.getSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("login", login));
		Usuario usuario=(Usuario) criteria.uniqueResult();
		hibernateUtil.getSession().close();
		return usuario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> getTotosUsuarios() {
		Criteria criteria = hibernateUtil.getSession().createCriteria(Usuario.class);
		criteria.addOrder(Order.asc("nome"));
		List<Usuario> list=criteria.list();
		hibernateUtil.getSession().close();
		return list;
	}

	@Override
	public List<Usuario> getTotosUsuariosPelaPesquisa(String pesq) {
		Criteria criteria = hibernateUtil.getSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.or(Restrictions.like("nome", "%"+pesq+"%"), Restrictions.like("login", "%"+pesq+"%")));
		criteria.addOrder(Order.asc("nome"));
		List<Usuario> list=criteria.list();
		hibernateUtil.getSession().close();
		return list;
	}
}
