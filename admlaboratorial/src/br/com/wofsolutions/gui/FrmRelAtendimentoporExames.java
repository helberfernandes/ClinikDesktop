package br.com.wofsolutions.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import br.com.wofsolutions.dominio.Usuario;
import br.com.wofsolutions.gui.componentes.WOFPeriodo;
import br.com.wofsolutions.interfaces.ExameDAO;
import br.com.wofsolutions.interfaces.MedicoDAO;
import br.com.wofsolutions.util.Configuracao;
import br.com.wofsolutions.util.RelatorioUtil;

public class FrmRelAtendimentoporExames extends JInternalFrame {
	private ExameDAO exameDAO = new ExameDAOImpl();
    private MedicoDAO medicoDAO = new MedicoDAOImpl();
    private RelatorioUtil relatorioUtil = new RelatorioUtil();
	private WOFPeriodo periodo = new WOFPeriodo(); 
	private final ExecutorService autoProgressExecutor = Executors.newFixedThreadPool(1);
private static Usuario  usuario = new Usuario();

private JComboBox comboBox = new JComboBox();
private JCheckBox cbExames = new JCheckBox("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRelAtendimentoporExames frame = new FrmRelAtendimentoporExames();
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
	public FrmRelAtendimentoporExames() {
		setTitle("Relatório de procedimentos por exames");
		setBounds(100, 100, 324, 182);
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
				 autoProgressExecutor.execute(new Runnable() {
					 
			         public void run() {
			        	 Aguarde aguarde = new Aguarde();
			        
				
				aguarde.setVisible(true);
				HashMap<String, Object> parametros =  new HashMap<String, Object>();
				
				// parametros.put("medicoId", cbMedicos.isSelected()?medicoDAO.getMedicoPeloNome(String.valueOf(comboBox.getSelectedItem())).getMedicoId():0);
			
				 parametros.put("exameId", cbExames.isSelected()?exameDAO.getExamePeloNome(String.valueOf(comboBox.getSelectedItem())).getExameId():0);
				 parametros.put("usuario", usuario.getNome());
				 
				 parametros.put("dataDe", periodo.getDataDe());
				 parametros.put("dataAte",periodo.getDataAte());
				
				 
				 
				 
				relatorioUtil.ImprimirRelatorio(Configuracao.REL_ATENDIMENTOS_POR_EXAMES, parametros);
				aguarde.setVisible(false);
			         }
				 });
			}
		});
		btnPesquisar.setBounds(185, 112, 113, 33);
		btnPesquisar.setIcon(new ImageIcon(Configuracao.ICONE_PESQUISA));
		
		getContentPane().add(btnPesquisar);
		populaComboExame();
	}
	
	

	public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		FrmRelAtendimentoporExames.usuario = usuario;
	}
	
	public void populaComboExame(){
		comboBox.removeAllItems();
		List<Exame> list=exameDAO.getTotosExames();
		for(Exame exame : list){			
			comboBox.addItem(exame.getNome());	
		}
	}
}
