package br.com.wofsolutions.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import br.com.wofsolutions.dao.ExameDAOImpl;
import br.com.wofsolutions.dominio.Exame;
import br.com.wofsolutions.gui.componentes.WOFPeriodo;
import br.com.wofsolutions.interfaces.ExameDAO;
import br.com.wofsolutions.util.Configuracao;
import br.com.wofsolutions.util.RelatorioUtil;

public class FrmRelQTDMensalDeProcedimentoPorExames extends JInternalFrame implements Observer {
    private ExameDAO exameDAO = new ExameDAOImpl();
    private JComboBox comboBox = new JComboBox();
    private JCheckBox cbExames = new JCheckBox("");
    private RelatorioUtil relatorioUtil= new RelatorioUtil();
    private static  BarraDeProgresso barraDeProgresso = new BarraDeProgresso();
	private WOFPeriodo periodo = new WOFPeriodo(); 
	  //Variavel de controle da thread do processo  
    private Thread processo;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRelQTDMensalDeProcedimentoPorExames frame = new FrmRelQTDMensalDeProcedimentoPorExames();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
  /*
	 * Executa o processo da aplicação 
     */  
    private void executaProcesso( URL relatorio, HashMap parametros) {  
        if(processo==null) { //Instancia a thread SE não existir uma  
        	barraDeProgresso.setVisible(true);
            processo = new Thread(new RelatorioUtil(this,relatorio,parametros ));  
            processo.start();  
        } else {  
            System.out.println("O processo ainda está em execução");  
        }     
    }  
	
	/** 
     * Atualiza a tela 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object) 
     * @param o Objeto que sofreu uma atualização 
     * @param arg Argumento passado pelo objeto para seus observadores 
     */  
    public void update(Observable o, Object arg) {  
        if(arg instanceof Integer) {  
            //Seta o valor do progresso  
        	barraDeProgresso.getProgressBar().setValue( ((Integer) arg).intValue());  
        	System.out.println("O ");
        } else if(arg instanceof Boolean) { 
        	
            if( ((Boolean) arg).booleanValue() ) {  
            	System.out.println("X ");
            	barraDeProgresso.getProgressBar().setValue(0);  
            	barraDeProgresso.setVisible(false);
            }  
        }  
    }  
	
	/**
	 * Create the frame.
	 */
	public FrmRelQTDMensalDeProcedimentoPorExames() {
		setTitle("QTD. Mensal Procedimentos por Exames");
		setBounds(100, 100, 324, 184);
		getContentPane().setLayout(null);
		
		periodo.setBounds(10, 11, 292, 49);
		getContentPane().add(periodo);
		
		comboBox.setEnabled(false);	
		comboBox.setBounds(38, 81, 264, 20);
		getContentPane().add(comboBox);
		cbExames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.setEnabled(cbExames.isSelected());
			}
		});
		
		
		cbExames.setBounds(10, 81, 21, 23);
		getContentPane().add(cbExames);
		
		JLabel lblMdicos = new JLabel("Exames:");
		lblMdicos.setBounds(10, 60, 46, 14);
		getContentPane().add(lblMdicos);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aguarde aguarde = new Aguarde();
				aguarde.setVisible(true);
				 HashMap<String, Object> parametros =  new HashMap<String, Object>();
				
				 parametros.put("exameId", cbExames.isSelected()?exameDAO.getExamePeloNome(String.valueOf(comboBox.getSelectedItem())).getExameId():0);
				 
				 parametros.put("dataDe", periodo.getDataDe());
				 parametros.put("dataAte",periodo.getDataAte());
				 
				 
				 //executaProcesso( Configuracao.REL_QTD_PROCEDIMENTOS_POR_EXAMES, parametros);
				 
				relatorioUtil.ImprimirRelatorio(Configuracao.REL_QTD_MENSAL_PROCEDIMENTOS_POR_EXAMES, parametros);
				
				aguarde.setVisible(false);
			}
		});
		btnPesquisar.setBounds(185, 112, 113, 33);
		btnPesquisar.setIcon(new ImageIcon(Configuracao.ICONE_PESQUISA));
		
		getContentPane().add(btnPesquisar);

		populaComboExame();
	}
	
	
	public void populaComboExame(){
		comboBox.removeAllItems();
		List<Exame> list=exameDAO.getTotosExames();
		for(Exame exame : list){			
			comboBox.addItem(exame.getNome());	
		}
	}
}
