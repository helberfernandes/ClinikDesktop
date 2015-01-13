package br.com.wofsolutions.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import br.com.wofsolutions.dao.ExameDAOImpl;
import br.com.wofsolutions.dao.MedicoDAOImpl;
import br.com.wofsolutions.dominio.Exame;
import br.com.wofsolutions.dominio.Medico;
import br.com.wofsolutions.gui.componentes.WOFPeriodo;
import br.com.wofsolutions.interfaces.ExameDAO;
import br.com.wofsolutions.interfaces.MedicoDAO;
import br.com.wofsolutions.util.Configuracao;
import br.com.wofsolutions.util.RelatorioUtil;

public class FrmRelTotalProcedimentosPorMedico extends JInternalFrame {
    private MedicoDAO medicoDAO = new MedicoDAOImpl();
    private ExameDAO exameDAO = new ExameDAOImpl();
    private JComboBox comboBox = new JComboBox();
    private JCheckBox cbMedicos = new JCheckBox("");
    private JComboBox comboBoxExames = new JComboBox();
    private JCheckBox cbExames = new JCheckBox("");
    private RelatorioUtil relatorioUtil = new RelatorioUtil();

	private WOFPeriodo periodo = new WOFPeriodo(); 
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRelTotalProcedimentosPorMedico frame = new FrmRelTotalProcedimentosPorMedico();
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
	public FrmRelTotalProcedimentosPorMedico() {
		setTitle("Total de Procedimentos por Medico");
		setBounds(100, 100, 324, 261);
		getContentPane().setLayout(null);
		
		periodo.setBounds(10, 10, 292, 61);
		getContentPane().add(periodo);
		
		comboBox.setEnabled(false);	
		comboBox.setBounds(40, 106, 258, 20);
		getContentPane().add(comboBox);
		cbMedicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.setEnabled(cbMedicos.isSelected());
			}
		});
		
		
		cbMedicos.setBounds(10, 103, 21, 23);
		getContentPane().add(cbMedicos);
		
		JLabel lblMedico = new JLabel("Médicos");
		lblMedico.setBounds(10, 82, 46, 14);
		getContentPane().add(lblMedico);
		
		
		
		
		
		
		comboBoxExames.setEnabled(false);	
		comboBoxExames.setBounds(37, 150, 264, 20);
		getContentPane().add(comboBoxExames);
		cbExames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxExames.setEnabled(cbExames.isSelected());
			}
		});
		
		
		cbExames.setBounds(10, 147, 21, 23);
		getContentPane().add(cbExames);
		
		JLabel lblExame = new JLabel("Exames:");
		lblExame.setBounds(10, 133, 46, 14);
		getContentPane().add(lblExame);
		
		
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aguarde aguarde = new Aguarde();
				aguarde.setVisible(true);
				 HashMap<String, Object> parametros =  new HashMap<String, Object>();
				
				 parametros.put("medicoId", cbMedicos.isSelected()?medicoDAO.getMedicoPeloNome(String.valueOf(comboBox.getSelectedItem())).getMedicoId():0);
				 parametros.put("exameId", cbExames.isSelected()?exameDAO.getExamePeloNome(String.valueOf(comboBoxExames.getSelectedItem())).getExameId():0);
				 parametros.put("dataDe", periodo.getDataDe());
				 parametros.put("dataAte",periodo.getDataAte());
				 
				 
				relatorioUtil.ImprimirRelatorio(Configuracao.REL_TOTAL_PROCEDIMENTOS_POR_MEDICO, parametros);
				
				aguarde.setVisible(false);
			}
		});
		btnPesquisar.setBounds(189, 188, 113, 33);
		btnPesquisar.setIcon(new ImageIcon(Configuracao.ICONE_PESQUISA));
		
		getContentPane().add(btnPesquisar);

		populaComboMedico();
		populaComboExame();
	}
	
	
	public void populaComboMedico(){
		comboBox.removeAllItems();
		List<Medico> list=medicoDAO.getTotosMedicosSolicitante();
		for(Medico medico : list){			
			comboBox.addItem(medico.getNome());	
		}
	}
	public void populaComboExame(){
		comboBoxExames.removeAllItems();
		List<Exame> list=exameDAO.getTotosExames();
		for(Exame exame : list){			
			comboBoxExames.addItem(exame.getNome());	
		}
	}
}
