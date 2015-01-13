package br.com.wofsolutions.dao;

import java.io.Serializable;

import org.hibernate.Transaction;

import br.com.wofsolutions.util.HibernateUtil;

public class HibernateDAOImpl<E, H, I> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected  HibernateUtil hibernateUtil = new HibernateUtil();

	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public boolean salvar(E obj) {		
		Transaction tx = null;
		boolean resp = false;
		try {
			hibernateUtil.getSession().flush();
			tx = hibernateUtil.getSession().beginTransaction();

			hibernateUtil.getSession().saveOrUpdate(obj);
			tx.commit();
			resp = tx.wasCommitted();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			hibernateUtil.closeSession();
		}

		return resp;
	}
	
	public boolean salvarObjeto(H obj) {		
		Transaction tx = null;
		boolean resp = false;
		try {
			tx = hibernateUtil.getSession().beginTransaction();

		
			hibernateUtil.getSession().saveOrUpdate(obj);
			tx.commit();
			resp = tx.wasCommitted();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			hibernateUtil.closeSession();
		}

		return resp;
	}
	
	public boolean excluir(E obj){		
		Transaction tx  = null;
		boolean resp=false;
		try {
			tx = hibernateUtil.getSession().beginTransaction();
			hibernateUtil.getSession().delete(obj);			
			tx.commit();	
			resp=tx.wasCommitted();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally{
			//hibernateUtil.closeSession();
		}		 
		
		return resp;		
	}


        public boolean excluirObjeto(H obj){
		Transaction tx  = null;
		boolean resp=false;
		try {
			tx = hibernateUtil.getSession().beginTransaction();
			hibernateUtil.getSession().delete(obj);
			tx.commit();
			resp=tx.wasCommitted();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally{
			//hibernateUtil.closeSession();
		}		 
		
		return resp;	
	}

		public HibernateUtil getHibernateUtil() {
			return hibernateUtil;
		}


}
