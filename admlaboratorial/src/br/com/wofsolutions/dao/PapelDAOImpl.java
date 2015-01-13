package br.com.wofsolutions.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.wofsolutions.dominio.Papel;
import br.com.wofsolutions.interfaces.PapelDAO;

public class PapelDAOImpl extends HibernateDAOImpl<Papel, Object, Object>
		implements PapelDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Papel> getTodosOsPapeis() {
		Criteria criteria = hibernateUtil.getSession().createCriteria(
				Papel.class);
		criteria.addOrder(Order.asc("nome"));

		List list = criteria.list();
		hibernateUtil.getSession().close();
		return list;
	}

	@Override
	public Papel getPapelPeloNome(String nome) {
		Criteria criteria = hibernateUtil.getSession().createCriteria(
				Papel.class);
		criteria.add(Restrictions.eq("nome", nome));
		Papel papel = (Papel) criteria.uniqueResult();
		hibernateUtil.getSession().close();
		return papel;
	}

	@Override
	public List<Papel> getTodosOsPapeisPelaPesquisa(String pesq) {
		Criteria criteria = hibernateUtil.getSession().createCriteria(
				Papel.class);
		
		
		criteria.addOrder(Order.asc("nome"));

		List list = criteria.list();
		hibernateUtil.getSession().close();
		return list;
	
	}

}
