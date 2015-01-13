package br.com.wofsolutions.gui.componentes;

import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

public class WOFPeriodo extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private  JDateChooser dataDe = new JDateChooser("dd/MM/yyyy",false); ;
  private JDateChooser dataAte = new JDateChooser("dd/MM/yyyy",false);
	/**
	 * Create the panel.
	 */
	public WOFPeriodo() {
			setLayout(null);
			dataDe.setDateFormatString("dd/MM/yyyy");
			dataDe.setBounds(10, 23, 130, 20);
			add(dataDe);
			
			JLabel lblDe = new JLabel("De:");
			lblDe.setBounds(10, 11, 23, 14);
			add(lblDe);
			
			
			dataAte.setDateFormatString("dd/MM/yyyy");
			dataAte.setBounds(150, 23, 130, 20);
			add(dataAte);
			
			JLabel lblAt = new JLabel("Até:");
			lblAt.setBounds(150, 11, 46, 14);
			add(lblAt);
	}
	
	public Date getDataDe(){
		return  dataDe.getDate();
	}
	
	public Date getDataAte(){
		return  dataAte.getDate();
	}
}
