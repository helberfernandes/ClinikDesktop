package br.com.wofsolutions.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.wofsolutions.dominio.Convenio;
import br.com.wofsolutions.dominio.Medico;
import br.com.wofsolutions.interfaces.MedicoDAO;

public class MedicoDAOImpl extends HibernateDAOImpl<Medico, Object, Object> implements MedicoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Medico getMedicoPeloNome(String nome) {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Medico.class);
		criteria.add(Restrictions.eq("nome", nome));
		Medico usuario=(Medico) criteria.uniqueResult();
		hibernateUtil.getSession().close();
		return usuario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medico> getTotosMedicos() {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Medico.class);
		criteria.addOrder(Order.asc("nome"));
		List<Medico> list=criteria.list();
		hibernateUtil.getSession().close();
		return list;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTotosMedicosAsString() {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Medico.class);
		criteria.addOrder(Order.asc("nome"));
		List<Medico> list=criteria.list();
		hibernateUtil.getSession().close();
	
		List<String> strings = new ArrayList<String>();
		for(Medico medico : list){
			strings.add(medico.getNome());
		}
		return strings;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTotosMedicosEquipeAsString(Integer exameId) {
		Criteria criteria = hibernateUtil.getSession().createCriteria(Medico.class);
		criteria.createAlias("exames", "e");
		criteria.add(Restrictions.and(Restrictions.eq("equipe", true), Restrictions.eq("e.exameId", exameId)));
		criteria.addOrder(Order.asc("nome"));
		List<Medico> list=criteria.list();
		hibernateUtil.getSession().close();
	
		List<String> strings = new ArrayList<String>();
		for(Medico medico : list){
			strings.add(medico.getNome());
		}
		return strings;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTotosMedicosEquipeAsString2() {
		Criteria criteria = hibernateUtil.getSession().createCriteria(Medico.class);
		criteria.createAlias("exames", "e");
		criteria.add(Restrictions.eq("equipe", true));
		criteria.addOrder(Order.asc("nome"));
		List<Medico> list=criteria.list();
		hibernateUtil.getSession().close();
	
		List<String> strings = new ArrayList<String>();
		for(Medico medico : list){
			strings.add(medico.getNome());
		}
		return strings;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTotosMedicosSolicitanteAsString() {
		Criteria criteria = hibernateUtil.getSession().createCriteria(Medico.class);
		criteria.add(Restrictions.eq("solicitante", true));
		criteria.addOrder(Order.asc("nome"));
		List<Medico> list=criteria.list();
		hibernateUtil.getSession().close();
	
		List<String> strings = new ArrayList<String>();
		for(Medico medico : list){
			strings.add(medico.getNome());
		}
		return strings;
	}
	
	
	
	@Override
	public List<Medico> getTotosMedicosPelaPesquisa(String pesq) {
		Criteria criteria = hibernateUtil.getSession().createCriteria(Medico.class);
		criteria.add(Restrictions.or(Restrictions.like("nome", "%"+pesq+"%"), Restrictions.like("nome", "%"+pesq+"%")));
		criteria.addOrder(Order.asc("nome"));
		List<Medico> list=criteria.list();
		hibernateUtil.getSession().close();
		return list;
	}

	@Override
	public List<Medico> getTotosMedicosSolicitante() {
		Criteria criteria = hibernateUtil.getSession().createCriteria(Medico.class);
		criteria.add(Restrictions.eq("solicitante", true));
		criteria.addOrder(Order.asc("nome"));
		return criteria.list();
	}
	
	@Override
	public List<Medico> getTotosMedicosEquipe() {
		Criteria criteria = hibernateUtil.getSession().createCriteria(Medico.class);
		criteria.add(Restrictions.eq("equipe", true));
		criteria.addOrder(Order.asc("nome"));
		return criteria.list();
	}
}
