package br.com.wofsolutions.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import br.com.wofsolutions.dao.PapelDAOImpl;
import br.com.wofsolutions.dominio.Papel;
import br.com.wofsolutions.mensagens.Mensagens;
import br.com.wofsolutions.util.Configuracao;

public class Papeis extends JInternalFrame {
	private JTextField textField;
	private JCheckBox chckbxCadastrarUsurios = new JCheckBox("Cadastrar Usuários");
	private JCheckBox chckbxCadastrarPapis = new JCheckBox("Cadastrar Papéis"); 
	private JCheckBox chckbxCadastrarMdicos = new JCheckBox("Cadastrar Médicos");
	private JCheckBox chckbxCadastrarPlanoDe = new JCheckBox("Cadastrar Plano de Saúde");
	private JCheckBox chckbxCadastrarExames = new JCheckBox("Cadastrar Exames");
	private JCheckBox chckbxCadastrarConsultas = new JCheckBox("Cadastrar Aendimento");
	private JCheckBox chckbxVerRelatrio = new JCheckBox("Ver Relatório");
	private Papel papel = new Papel();
	private PapelDAOImpl papelDAOImpl = new PapelDAOImpl();
	private JToolBar toolBar = new JToolBar();
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnNovo = new JButton("Novo");
	private JButton btnPesquisar = new JButton("Pesquisar");
	private FrmUsuario frmUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Papeis frame = new Papeis();
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
	public Papeis() {
		setClosable(true);
		setTitle("Cadastro de Papéis");
		setBounds(100, 100, 436, 208);
		getContentPane().setLayout(null);
		
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setToolTipText("Nome");
		lblNome.setBounds(10, 11, 46, 14);
		getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(10, 25, 212, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		chckbxCadastrarUsurios.setBounds(10, 52, 117, 23);
		getContentPane().add(chckbxCadastrarUsurios);
		
		
		chckbxCadastrarPapis.setBounds(159, 52, 107, 23);
		getContentPane().add(chckbxCadastrarPapis);
		
		
		chckbxCadastrarMdicos.setBounds(278, 52, 117, 23);
		getContentPane().add(chckbxCadastrarMdicos);
		
		
		chckbxCadastrarPlanoDe.setBounds(10, 78, 151, 23);
		getContentPane().add(chckbxCadastrarPlanoDe);
		
		
		chckbxCadastrarExames.setBounds(159, 78, 117, 23);
		getContentPane().add(chckbxCadastrarExames);
		
		
		chckbxCadastrarConsultas.setBounds(278, 78, 133, 23);
		getContentPane().add(chckbxCadastrarConsultas);
		
		
		chckbxVerRelatrio.setBounds(10, 104, 97, 23);
		getContentPane().add(chckbxVerRelatrio);
		
		
		toolBar.setFloatable(false);
		toolBar.setBounds(199, 126, 212, 41);
		getContentPane().add(toolBar);
		
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				boolean resp=papelDAOImpl.salvar(getPapel());
				frmUsuario.populaComboPapeis();
				limpaCampos();
				Mensagens.setMensagemSucessOuFalha(resp, getContentPane());
			}
		});
		btnSalvar.setIcon(new  ImageIcon(Configuracao.ICONE_SALVAR));
		toolBar.add(btnSalvar);
		
		
		
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaCampos();
			}
		});
		btnNovo.setToolTipText("Novo");
		btnNovo.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNovo.setHorizontalAlignment(SwingConstants.LEFT);
		btnNovo.setIcon(new  ImageIcon(Configuracao.ICONE_NOVO));
		toolBar.add(btnNovo);
		
		
		
		toolBar.add(btnPesquisar);

	}
	
	
	public void limpaCampos(){
		papel = new Papel();
		textField.setText("");		
		chckbxCadastrarConsultas.setSelected(false);
		chckbxCadastrarExames.setSelected(false);
		chckbxCadastrarMdicos.setSelected(false);
		chckbxCadastrarPapis.setSelected(false);
		chckbxCadastrarPlanoDe.setSelected(false);
		chckbxCadastrarUsurios.setSelected(false);
		chckbxVerRelatrio.setSelected(false);
	}

	public Papel getPapel() {
		papel.setNome(textField.getText());		
		return papel;
	}

	public FrmUsuario getFrmUsuario() {
		return frmUsuario;
	}

	public void setFrmUsuario(FrmUsuario frmUsuario) {
		this.frmUsuario = frmUsuario;
	}
}
