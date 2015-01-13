package br.com.wofsolutions.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.wofsolutions.dominio.Convenio;
import br.com.wofsolutions.interfaces.ConvenioDAO;

public class ConvenioDAOImpl extends HibernateDAOImpl<Convenio, Object, Object> implements ConvenioDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Convenio getConvenioPeloNome(String nome) {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Convenio.class);
		criteria.add(Restrictions.eq("nome", nome));
		Convenio usuario=(Convenio) criteria.uniqueResult();
		hibernateUtil.getSession().close();
		return usuario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Convenio> getTotosConvenios() {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Convenio.class);
		criteria.addOrder(Order.asc("nome"));
		List<Convenio> list=criteria.list();
		hibernateUtil.getSession().close();
		return list;
	}

	
	public List<String> getTotosConveniosAsString() {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Convenio.class);
		criteria.addOrder(Order.asc("nome"));
		List<Convenio> list=criteria.list();
		hibernateUtil.getSession().close();
		List<String> strings = new ArrayList<String>();
		for(Convenio convenio : list){
			strings.add(convenio.getNome());
		}
		return strings;
	}
	
	@Override
	public List<Convenio> getTotosConveniosPelaPesquisa(String pesq) {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Convenio.class);
		criteria.add(Restrictions.or(Restrictions.like("nome", "%"+pesq+"%"), Restrictions.like("nome", "%"+pesq+"%")));
		criteria.addOrder(Order.asc("nome"));
		List<Convenio> list=criteria.list();
		hibernateUtil.getSession().close();
		return list;
	}
}
