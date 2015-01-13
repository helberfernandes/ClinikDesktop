package br.com.wofsolutions.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.wofsolutions.dominio.Atendimento;
import br.com.wofsolutions.interfaces.AtendimentoDAO;

public class AtendimentoDAOImpl extends HibernateDAOImpl<Atendimento, Object, Object> implements AtendimentoDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Atendimento getAtendimentoPeloNome(String paciente) {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Atendimento.class);
		criteria.add(Restrictions.and(Restrictions.eq("paciente", paciente),Restrictions.eq("deletado", false)));
		Atendimento usuario=(Atendimento) criteria.uniqueResult();
		hibernateUtil.getSession().close();
		
		return usuario;
	}
	
	
	public Atendimento getAtendimentoPeloNome(Atendimento atendimento) {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Atendimento.class);
		criteria.add(Restrictions.and(Restrictions.eq("paciente", atendimento.getPaciente()), Restrictions.and(Restrictions.eq("deletado", false), Restrictions.and(Restrictions.eq("dataLancamento", atendimento.getDataLancamento()), Restrictions.eq("exame.exameId", atendimento.getExame().getExameId())))));
		Atendimento usuario=(Atendimento) criteria.uniqueResult();
		hibernateUtil.getSession().close();
		
		return usuario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Atendimento> getTotosAtendimentos() {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Atendimento.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		
		
		   Calendar inicioMes = Calendar.getInstance();
	        inicioMes.set(Calendar.DAY_OF_MONTH, 1);
	        Calendar fimMes = Calendar.getInstance();
	        fimMes.set(Calendar.DAY_OF_MONTH,fimMes.getActualMaximum(Calendar.DAY_OF_MONTH));

	            
		
		criteria.add(Restrictions.and(Restrictions.eq("deletado", false),Restrictions.between("dataLancamento", inicioMes.getTime(),fimMes.getTime())));
		criteria.addOrder(Order.asc("paciente"));
		List<Atendimento> list=criteria.list();
		hibernateUtil.getSession().close();
		return list;
	}

	@Override
	public List<Atendimento> getTotosAtendimentosPelaPesquisa(String pesq) {
		hibernateUtil.getSession().beginTransaction();
		Criteria criteria = hibernateUtil.getSession().createCriteria(Atendimento.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.and(Restrictions.eq("deletado", false), Restrictions.or(Restrictions.like("paciente", "%"+pesq+"%"), Restrictions.like("paciente", "%"+pesq+"%"))));
		criteria.addOrder(Order.asc("paciente"));
		List<Atendimento> list=criteria.list();
		hibernateUtil.getSession().close();
		return list;
	}
}
