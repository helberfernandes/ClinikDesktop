package br.com.wofsolutions.util;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.com.wofsolutions.gui.BarraDeProgresso;

public class RelatorioUtil extends Observable  
implements Runnable{
	private static  BarraDeProgresso barraDeProgresso = new BarraDeProgresso();
	 public static int TAM_PROCESSO = 1000000; 
	private URL relatorio;
	private  HashMap parametros;
	 static JasperPrint jasperPrint;
	 
	 public RelatorioUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	/** 
	     * Construtor que recebe um objeto que irá observa-lo 
	     * @param observador Objeto que irá acompanhar as mudanças deste objeto 
	     */  
	    public RelatorioUtil(Observer observador, URL relatorio, HashMap parametros) {  
	        //Adiciona o objeto observador a lista de observadores  
	        addObserver(observador);  
	       this.parametros=parametros;
	       this.relatorio=relatorio;
	    }  
	 
	/** 
     * Ponto de entrada da Thread. 
     * @see java.lang.Runnable#run() 
     */  
    public void run() {  
        
    	
    	int i;
    	boolean terminou=false;
    	
    	
    	ImprimirRelatorio(relatorio, parametros);
        for(i=0; i<= TAM_PROCESSO; i++) {  
            //Notifica o processamento a cada 10 iterações
//        	if(terminou==false){
//        		ImprimirRelatorio(relatorio, parametros);
//        		terminou=true;
//        	}
            if((i % 10 == 0)) {  
                notifyObservers(new Integer(i));  
                setChanged();  
            }  
        }  
        
        
        JasperViewer.viewReport(jasperPrint, false);
          
        //Notifica fim do processo  
        notifyObservers(new Boolean(true));  
        setChanged();   
    }  
	
	
	
	
	   public static synchronized void ImprimirRelatorio(URL relatorio, HashMap parametros) {
		 //Nro de iterações para o loop de simulação do processo  
		   

	      try {
	    	  
	    	 
	    	  Class.forName("org.gjt.mm.mysql.Driver");
	  		Connection db = DriverManager.getConnection("jdbc:mysql://localhost/laboratorio", "root", "st1215");
	       
	         parametros.put("logoEmpresa",Configuracao.LOGO_HOLTER);
	       
	    
	         
	         jasperPrint = JasperFillManager.fillReport(relatorio.openStream(),
	                                                                parametros, db);
	         
	         JasperViewer.viewReport(jasperPrint, false);
	              // JasperPrintManager.printReport(jasperPrint, true);
	        
	      }
	      catch (Exception ex) {
	        ex.printStackTrace();
	      }
	   }
}
