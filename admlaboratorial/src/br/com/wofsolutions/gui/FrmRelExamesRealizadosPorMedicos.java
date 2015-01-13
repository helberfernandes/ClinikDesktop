package br.com.wofsolutions.gui;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JButton;

import br.com.wofsolutions.dao.MedicoDAOImpl;
import br.com.wofsolutions.dominio.Medico;
import br.com.wofsolutions.gui.componentes.WOFPeriodo;
import br.com.wofsolutions.interfaces.MedicoDAO;
import br.com.wofsolutions.util.Configuracao;
import br.com.wofsolutions.util.RelatorioUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRelExamesRealizadosPorMedicos extends JInternalFrame {
    private MedicoDAO medicoDAO = new MedicoDAOImpl();
    private JComboBox comboBox = new JComboBox();
    private JCheckBox cbMedicos = new JCheckBox("");
    private RelatorioUtil relatorioUtil = new RelatorioUtil();

	private WOFPeriodo periodo = new WOFPeriodo(); 
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRelExamesRealizadosPorMedicos frame = new FrmRelExamesRealizadosPorMedicos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmRelExamesRealizadosPorMedicos() {
		setTitle("Exames Realizados Por Médicos");
		setBounds(100, 100, 324, 202);
		getContentPane().setLayout(null);
		
		periodo.setBounds(10, 10, 292, 61);
		getContentPane().add(periodo);
		
		comboBox.setEnabled(false);	
		comboBox.setBounds(38, 97, 237, 20);
		getContentPane().add(comboBox);
		cbMedicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.setEnabled(cbMedicos.isSelected());
			}
		});
		
		
		cbMedicos.setBounds(10, 97, 21, 23);
		getContentPane().add(cbMedicos);
		
		JLabel lblMdicos = new JLabel("Médicos");
		lblMdicos.setBounds(10, 76, 46, 14);
		getContentPane().add(lblMdicos);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aguarde aguarde = new Aguarde();
				aguarde.setVisible(true);
				 HashMap<String, Object> parametros =  new HashMap<String, Object>();
				
				 parametros.put("medicoId", cbMedicos.isSelected()?medicoDAO.getMedicoPeloNome(String.valueOf(comboBox.getSelectedItem())).getMedicoId():0);
				 
				 parametros.put("dataDe", periodo.getDataDe());
				 parametros.put("dataAte",periodo.getDataAte());
				 
				 
				relatorioUtil.ImprimirRelatorio(Configuracao.REL_EXAME_RREALIZADOS_POR_MEDICOS, parametros);
				
				aguarde.setVisible(false);
			}
		});
		btnPesquisar.setBounds(189, 128, 113, 33);
		btnPesquisar.setIcon(new ImageIcon(Configuracao.ICONE_PESQUISA));
		
		getContentPane().add(btnPesquisar);

		populaComboMedico();
	}
	
	
	public void populaComboMedico(){
		comboBox.removeAllItems();
		List<Medico> list=medicoDAO.getTotosMedicos();
		for(Medico medico : list){			
			comboBox.addItem(medico.getNome());	
		}
	}
}
