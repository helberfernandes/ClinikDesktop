package br.com.wofsolutions.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.wofsolutions.dominio.Convenio;
import br.com.wofsolutions.dominio.Exame;
import br.com.wofsolutions.dominio.ExameConvenio;
import br.com.wofsolutions.interfaces.ExameDAO;

public class ExameDAOImpl extends HibernateDAOImpl<Exame, ExameConvenio, Object> implements ExameDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Exame getExamePeloNome(String nome) {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Exame.class);
		criteria.add(Restrictions.eq("nome", nome));
		Exame usuario=(Exame) criteria.uniqueResult();
		hibernateUtil.getSession().close();
		return usuario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exame> getTotosExames() {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Exame.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) ;
		criteria.addOrder(Order.asc("nome"));
		List<Exame> list=criteria.list();
		hibernateUtil.getSession().close();
		return list;
	}
	
	
	public List<String> getTotosExamesAsString() {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Exame.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) ;
		criteria.addOrder(Order.asc("nome"));
		List<Exame> list=criteria.list();
		hibernateUtil.getSession().close();
		
		
		List<String> strings = new ArrayList<String>();
		for(Exame exame : list){
			strings.add(exame.getNome());
		}
		return strings;
	}

	@Override
	public List<Exame> getTotosExamesPelaPesquisa(String pesq) {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Exame.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.or(Restrictions.like("nome", "%"+pesq+"%"), Restrictions.like("nome", "%"+pesq+"%")));
		criteria.addOrder(Order.asc("nome"));
		List<Exame> list=criteria.list();
		hibernateUtil.getSession().close();
		return list;
	}

	@Override
	public List<ExameConvenio> getTotosExamesConvenios() {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(ExameConvenio.class);
//		criteria.createAlias("exameConvenioPK.convenio","convenio",CriteriaSpecification.LEFT_JOIN);
//	
		
		//Query query = hibernateUtil.getSession().createQuery("SELECT e FROM ExameConvenio e RIGHT JOIN fetch e.exameConvenioPK.convenio convenio");
		
		//criteria.addOrder(Order.asc("nome"));
		List<ExameConvenio> list=criteria.list();
		
		
		hibernateUtil.getSession().close();
		return list;
	}
	
	
	public List<ExameConvenio> getTotosExamesConveniosPeloExameId(Integer exameId) {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(ExameConvenio.class);
		criteria.add(Restrictions.eq("exameConvenioPK.exame.exameId", exameId));
		List<ExameConvenio> list=criteria.list();
		hibernateUtil.getSession().close();
		return list;
	}
	
	public ExameConvenio getTotosExamesConveniosPeloExameIdEPeloConvenioId(Integer exameId, Integer convenioId) {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(ExameConvenio.class);
		criteria.add(Restrictions.and(Restrictions.eq("exameConvenioPK.exame.exameId", exameId), Restrictions.eq("exameConvenioPK.convenio.convenioId", convenioId)));
		ExameConvenio exameConvenio=(ExameConvenio) criteria.uniqueResult();
		hibernateUtil.getSession().close();
		return exameConvenio;
	}
}
