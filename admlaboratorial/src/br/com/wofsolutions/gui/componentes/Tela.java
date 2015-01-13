package br.com.wofsolutions.gui.componentes;

	import java.awt.event.FocusEvent;  
	import java.awt.event.FocusListener;  
	import java.awt.event.KeyEvent;  
	import java.awt.event.KeyListener;  
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
	  
	import javax.swing.JComboBox;  
	import javax.swing.JFrame;  
	import javax.swing.JInternalFrame;  
import javax.swing.JTextField;  

import br.com.wofsolutions.dao.MedicoDAOImpl;
import java.awt.event.KeyAdapter;
	
	  
	public class Tela extends JFrame {  
	  
	   private JTextField txtBusca;  
	   private MedicoDAOImpl daoImpl;
	   private JInternalFrame frmSug;  
	   private JComboBox listSug;  
	  private Java2sAutoTextField autoTextField;
	   public Tela() {  
	      super("Exemplo de auto complete");  
	      setDefaultCloseOperation(EXIT_ON_CLOSE);  
	      setSize(300, 300);  
	      getContentPane().setLayout(null);  
	      txtBusca = new JTextField();  
	      txtBusca.setBounds(100, 10, 150, 20);  
	      
	    
	      getContentPane().add(txtBusca);
	      List<String> list = new ArrayList<String>();
	      list.add("opa");
	      list.add("sss");
	      list.add("aaaa");
	      
	      autoTextField = new Java2sAutoTextField(list);
	      
	      autoTextField.setBounds(100, 80, 150, 20); 
	      getContentPane().add(autoTextField);
	      listSug = new JComboBox();  
	      listSug.addKeyListener(new KeyAdapter() {
	      	@Override
	      	public void keyReleased(KeyEvent arg0) {
	      		 if (listSug.getSelectedItem().equals("")) {  
		               listSug.setVisible(false);  
		               return;  
		            }  
		            Object[] dados =   daoImpl.getTotosMedicosPelaPesquisa(String.valueOf(listSug.getSelectedItem())).toArray();  
		            listSug.removeAllItems();  
		            listSug.setVisible(true);  
		            if (dados[0] == null){  
		               dados[0] = "Não encontrado!!";  
		            }              
		                 
		            for (int i = 0; i < dados.length; i++) {  
		               listSug.addItem(dados[i]);  
		            }  
	      	}
	      });
	      listSug.setEditable(true);
	      listSug.setBounds(100, 30, 150, 20);  
	      listSug.setMaximumRowCount(4);  
	      listSug.setVisible(false);  
	      getContentPane().add(listSug);        
	     daoImpl = new MedicoDAOImpl();
	  
	      txtBusca.addFocusListener(new FocusListener() {  
	  
	         @Override  
	         public void focusGained(FocusEvent arg0) {  
	         }  
	  
	         @Override  
	         public void focusLost(FocusEvent arg0) {  
	            // listSug.setVisible(false);  
	  
	         }  
	      });  
	  
	      txtBusca.addKeyListener(new KeyListener() {  
	         @Override  
	         public void keyPressed(KeyEvent e) {  
	  
	         }  
	  
	         @Override  
	         public void keyReleased(KeyEvent e) {  
	            if (txtBusca.getText().equals("")) {  
	               listSug.setVisible(false);  
	               return;  
	            }  
	            Object[] dados =   daoImpl.getTotosMedicosPelaPesquisa(txtBusca.getText()).toArray();  
	            listSug.removeAllItems();  
	            listSug.setVisible(true);  
	            if (dados[0] == null){  
	               dados[0] = "Não encontrado!!";  
	            }              
	                 
	            for (int i = 0; i < dados.length; i++) {  
	               listSug.addItem(dados[i]);  
	            }  
	         }  
	  
	         @Override  
	         public void keyTyped(KeyEvent e) {  
	         }  
	  
	      });  
	        
	      listSug.addFocusListener(new FocusListener(){  
	         @Override  
	         public void focusGained(FocusEvent arg0) {              
	         }  
	         @Override  
	         public void focusLost(FocusEvent arg0) {  
	            txtBusca.setText((String)listSug.getSelectedItem());              
	         }});  
	   }  
	  
	   public static void main(String[] args) {  
	      Tela teste = new Tela();  
	      teste.setVisible(true);  
	   }  
	  
	}  
