package br.com.wofsolutions.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import br.com.wofsolutions.dominio.Usuario;
import br.com.wofsolutions.util.Configuracao;
import br.com.wofsolutions.util.GUIUtil;

public class DeskTop extends JFrame {


	
	private FrmPapel frmPapeis = new FrmPapel();
//	private final JLabel lblNewLabel = new JLabel("New label");
//	private FrmUsuario frmUsuario = new FrmUsuario();
	

	
	


	public DeskTop() throws HeadlessException {
		super();
		initialize();
	}







	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		getContentPane().setBackground(Color.RED);
		setExtendedState(Frame.MAXIMIZED_BOTH);
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setTitle("Clinik 1.0.0");
		setBounds(100, 100, 1024, 584);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Toolkit kit = Toolkit.getDefaultToolkit();
		// Image icone = kit.getImage(Configuracao.ICONE_WOF);
		// frmAtendimento.setIconImage((Image) icone);
		setIconImage(getToolkit().createImage(getClass().getResource("/imagens/axialis-square-light-grey-png/png/48x48/clinik.png")));

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		desktopPane.setAutoscrolls(true);

		desktopPane.setForeground(SystemColor.activeCaptionBorder);
		JLabel lab = new JLabel();
		lab.setBounds(0, 0, 0, 0);

		//double alt = frmAtendimento.getBounds().getHeight();
	//	double larg = frmAtendimento.getBounds().getWidth();

//		int altura = (int) alt;
//		int largura = (int) larg;
//		lab.setIcon(new ImageIcon(Configuracao.LOGO_HOLTER4));
//		lab.setBounds((int) larg, 0, 300, 100);
		desktopPane.setBackground(new Color(27, 49, 59));
		// desktopPane.setBackground(Color.white);
		desktopPane.setLayout(null);
		desktopPane.add(lab);

		getContentPane().add(desktopPane, BorderLayout.CENTER);
//		BorderLayout borderLayout = (BorderLayout) frmUsuario.getContentPane()
//				.getLayout();
//		borderLayout.setVgap(2);
//		borderLayout.setHgap(4);
		
//		
//		Dimension desktopSize = desktopPane.getSize();
//		
//		
//		frmUsuario.setMaximizable(true);
//		frmUsuario.setBounds(0, 0, 1018, 511);
//		frmUsuario.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		frmUsuario.setClosable(true);		
//		GUIUtil.center(frmUsuario);		
//		desktopPane.add(frmUsuario);
//		
//		
//		
//		
//		
//		frmMedico.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		frmMedico.setClosable(true);
//		frmMedico.setFrmExame(frmExame);
//		frmMedico.setFrmAtendimento(frmAtendimentoPaciente);
//		desktopPane.add(frmMedico);
//		frmConvenio.setBounds(0, 0, 1018, 511);
//
//		frmConvenio.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		frmConvenio.setClosable(true);
//		frmConvenio.setFrmAtendimento(frmAtendimentoPaciente);
//		frmConvenio.setFrmExame(frmExame);
//
//		desktopPane.add(frmConvenio);
//		frmExame.setBounds(0, 0, 1018, 511);
//
//		frmExame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		frmExame.setClosable(true);
//		frmExame.setFrmAtendimento(frmAtendimentoPaciente);
//		desktopPane.add(frmExame);
//		frmAtendimentoPaciente.setBounds(0, 0, 1018, 511);
//
//		frmAtendimentoPaciente.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		frmAtendimentoPaciente.setClosable(true);
//		desktopPane.add(frmAtendimentoPaciente);
//		examesRealizadosPorMedicos.setBounds(100, 100, 324, 202);
//
//		examesRealizadosPorMedicos
//				.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		examesRealizadosPorMedicos.setClosable(true);
//		desktopPane.add(examesRealizadosPorMedicos);
//
//		frmRelAtendimento.setBounds(100, 100, 324, 202);
//		frmRelAtendimento.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		frmRelAtendimento.setClosable(true);
//		desktopPane.add(frmRelAtendimento);
//		
//		
//		frmRelQTDMensalDeProcedimentoPorExames.setBounds(100, 100, 324, 202);
//		frmRelQTDMensalDeProcedimentoPorExames.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		frmRelQTDMensalDeProcedimentoPorExames.setClosable(true);
//		desktopPane.add(frmRelQTDMensalDeProcedimentoPorExames);
//		
//
//		frmRelQTDProcedimentoPorExames.setBounds(100, 100, 324, 184);
//		frmRelQTDProcedimentoPorExames
//				.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		frmRelQTDProcedimentoPorExames.setClosable(true);
//		desktopPane.add(frmRelQTDProcedimentoPorExames);
//
//		frmRelQTDProcedimentosPorSolicitante.setBounds(100, 100, 324, 184);
//		frmRelQTDProcedimentosPorSolicitante
//				.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		frmRelQTDProcedimentosPorSolicitante.setClosable(true);
//		desktopPane.add(frmRelQTDProcedimentosPorSolicitante);
//
//		frmRelTotalProcedimentosPorMedico.setBounds(100, 100, 324, 261);
//		frmRelTotalProcedimentosPorMedico
//				.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		frmRelTotalProcedimentosPorMedico.setClosable(true);
//		desktopPane.add(frmRelTotalProcedimentosPorMedico);
//
//		
//	
//		
//		
//		
//		relQTDProcedimentosPeloDRJuan.setBounds(100, 100, 324, 261);
//		relQTDProcedimentosPeloDRJuan
//				.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		relQTDProcedimentosPeloDRJuan.setClosable(true);
//		desktopPane.add(relQTDProcedimentosPeloDRJuan);
		
		
		frmPapeis.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmPapeis.setClosable(true);
	//	frmPapeis.setFrmUsuario(frmUsuario);
		//frmPapeis.setFrmAtendimento(frmAtendimentoPaciente);		
		desktopPane.add(frmPapeis);
		
		
		getContentPane().setBackground(new Color(65, 73, 39));

		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		JLabel lblUsurio = new JLabel("Usuário: ");
		toolBar.add(lblUsurio);
		
//		JLabel lblNewLabel = new JLabel(usuario.getLogin());
//		toolBar.add(lblNewLabel);
		
		
		
		GUIUtil.setLookAndFeel(this);
		GUIUtil.center(this);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAdministrar = new JMenu("Administrar");
	//	mnAdministrar.setVisible(usuario.getPapel().isPodeCadastrarUsuaio());
		menuBar.add(mnAdministrar);

		JMenuItem mntmUsurios = new JMenuItem("Usuários");
		//mntmUsurios.setVisible(usuario.getPapel().isPodeCadastrarUsuaio());
		mntmUsurios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				InputEvent.CTRL_MASK));
		mntmUsurios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	frmUsuario.setVisible(true);
			}
		});
		mnAdministrar.add(mntmUsurios);

		JMenuItem mntmPapis = new JMenuItem("Papéis");
	//	mntmPapis.setVisible(usuario.getPapel().isPodeDefinirPapeis());
		mntmPapis.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				InputEvent.CTRL_MASK));
		mntmPapis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	frmPapeis.setVisible(true);
			}
		});
		mnAdministrar.add(mntmPapis);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		JSeparator separator_1 = new JSeparator();
		mnAdministrar.add(separator_1);
		mntmSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		mnAdministrar.add(mntmSair);

		JMenu mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);

		JMenuItem mntmPlanosDeSade = new JMenuItem("Convênio");
	
		mntmPlanosDeSade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	//			frmConvenio.setVisible(true);
			}
		});
		mntmPlanosDeSade.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		mnCadastros.add(mntmPlanosDeSade);

		JMenuItem mntmExames = new JMenuItem("Exames");
		mntmExames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		//		frmExame.setVisible(true);
			}
		});
		mntmExames.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_MASK));
		mnCadastros.add(mntmExames);

		JMenuItem mntmMdicos = new JMenuItem("Médicos");
		mntmMdicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	frmMedico.setVisible(true);
			}
		});
		mntmMdicos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				InputEvent.CTRL_MASK));
		mnCadastros.add(mntmMdicos);

		JMenuItem mntmAtendimento = new JMenuItem("Atendimento");
		mntmAtendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//frmAtendimentoPaciente.setVisible(true);
			}
		});
		mntmAtendimento.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK));
		mnCadastros.add(mntmAtendimento);

		JMenu mnRelatrios = new JMenu("Relatórios");
		menuBar.add(mnRelatrios);

		JMenuItem mntmQuantidadeDeExames = new JMenuItem(
				"Quantidade de Exames Por Médico");
		
	//	mntmQuantidadeDeExames.setVisible(usuario.getPapel().isPodeVerExamesRealizadosPorMedicos());
		mntmQuantidadeDeExames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//examesRealizadosPorMedicos.setVisible(true);
			}
		});

		JMenuItem mntmAtendimentos = new JMenuItem("Atendimentos");
		//mntmAtendimentos.setVisible(usuario.getPapel().isPodeVerRelatorioDeAtendimentos());
		mntmAtendimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		//		frmRelAtendimento.setVisible(true);
			}
		});
		mnRelatrios.add(mntmAtendimentos);

		JSeparator separator_2 = new JSeparator();
		mnRelatrios.add(separator_2);
		 mnRelatrios.add(mntmQuantidadeDeExames);

		JMenuItem mntmQtdProcedimentosPor = new JMenuItem(
				"QTD. Procedimentos por Exame");
	//	mntmQtdProcedimentosPor.setVisible(usuario.getPapel().isPodeVerQTDProcedimentoPorExames());
		mntmQtdProcedimentosPor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		//		frmRelQTDProcedimentoPorExames.setVisible(true);
			}
		});
		 mnRelatrios.add(mntmQtdProcedimentosPor);

		JMenuItem mntmQtdProcedimentosPor_1 = new JMenuItem(
				"QTD. Procedimentos por Solicitante");
		//mntmQtdProcedimentosPor_1.setVisible(usuario.getPapel().isPodeVerQTDProcedimentosPorSolicitante());
		mntmQtdProcedimentosPor_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	frmRelQTDProcedimentosPorSolicitante.setVisible(true);
			}
		});
		 
		 JMenuItem mntmQtdMensalDe = new JMenuItem("QTD. Mensal de Exames");
		 mntmQtdMensalDe.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 //		frmRelQTDMensalDeProcedimentoPorExames.setVisible(true);
		 	}
		 });
		 mnRelatrios.add(mntmQtdMensalDe);
		// mnRelatrios.setVisible(usuario.getPapel().isPodeVerQTDMensalProcedimentoPorExames());
		 mnRelatrios.add(mntmQtdProcedimentosPor_1);

		JSeparator separator_3 = new JSeparator();
		 mnRelatrios.add(separator_3);

		JMenuItem mntmQuantidadeDeExames_2 = new JMenuItem(
				"Total Procedimentos por Medico");
		//mntmQuantidadeDeExames_2.setVisible(usuario.getPapel().isPodeVerTotalProcedimentosPorMedico());
		mntmQuantidadeDeExames_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//	frmRelTotalProcedimentosPorMedico.setVisible(true);
			}
		});
		 mnRelatrios.add(mntmQuantidadeDeExames_2);
		
		JMenuItem mntmrelQTDProcedimentosPeloDRJuan = new JMenuItem(
				"Total Procedimentos do Dr. Juan");
	//	mntmrelQTDProcedimentosPeloDRJuan.setVisible(usuario.getPapel().isPodeVerRelatorioDoJuan());
		mntmrelQTDProcedimentosPeloDRJuan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	//			relQTDProcedimentosPeloDRJuan.setVisible(true);
			}
		});
		
		
		
		 mnRelatrios.add(mntmrelQTDProcedimentosPeloDRJuan);

		JSeparator separator = new JSeparator();
		 mnRelatrios.add(separator);
		 
	}


	

//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
	
}
