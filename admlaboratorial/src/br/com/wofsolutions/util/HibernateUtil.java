package br.com.wofsolutions.util;

import java.io.File;
import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;

@SuppressWarnings("deprecation")
public class HibernateUtil implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static  SessionFactory factory;
	private  Session session;
	
	static{
		//new File("C:\\Arquivos de Programas\\WOF Solutions\\clinik\\banco\\hibernate.cfg.xml")
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}	
	
	
	public  Session getSession(){
		if(session == null || !session.isOpen() || !session.isConnected()){
			session =factory.openSession();
		}
		return session;
	}
	
	public  void closeSession(){
		session.close();
	}
	
	public static void geraBanco(){
		AnnotationConfiguration configuration = new AnnotationConfiguration().configure();
		SchemaExport export = new SchemaExport(configuration);
		export.create(true, true);
	}



	public  void setFactory(SessionFactory factory) {
		HibernateUtil.factory = factory;
	}
	
}
