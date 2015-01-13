package br.com.wofsolutions.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.wofsolutions.dao.UsuarioDAOImpl;
import br.com.wofsolutions.dominio.Usuario;
import br.com.wofsolutions.mensagens.Mensagens;
import br.com.wofsolutions.util.Configuracao;
import br.com.wofsolutions.util.GUIUtil;
import br.com.wofsolutions.util.SplashScreen;
import br.com.wofsolutions.util.WofUtil;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;

public class FrmLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField pwSenha;
	private UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
    private  DeskTop deskTop;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		setTitle("Clinik 1.0.0");
		setBounds(100, 100, 247, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		GUIUtil.center(this);
		GUIUtil.setLookAndFeel(this);
		
		JLabel lblLogin = new JLabel("Nome de Usuário");
		lblLogin.setBounds(10, 42, 89, 14);
		contentPane.add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(10, 59, 224, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 79, 46, 14);
		contentPane.add(lblSenha);
		
		pwSenha = new JPasswordField();
		pwSenha.setBounds(10, 98, 224, 20);
		contentPane.add(pwSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setMnemonic(KeyEvent.VK_ENTER);
		
		getRootPane().setDefaultButton(btnEntrar); 
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 final Usuario tmp= usuarioDAOImpl.getUsuarioPeloLogin(txtLogin.getText());
				if(tmp!=null){
					if(!tmp.getSenha().equals(WofUtil.md5(pwSenha.getText()))){
						Mensagens.addWarningMenssage(getContentPane(), "Login ou senha inválidos!!!", "Atenção!!");
					}else{
						
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
								            
									Principal window = new Principal(tmp);
									window.getFrmAtendimento().setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
						setVisible(false);
					}
					
				}else{
					Mensagens.addWarningMenssage(getContentPane(), "Login ou senha inválidos!!!", "Atenção!!");
				}
				
				
			}
		});
		btnEntrar.setBounds(145, 129, 89, 23);
		contentPane.add(btnEntrar);
		
		JLabel lblLogin_1 = new JLabel("Login");
		lblLogin_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogin_1.setBounds(10, 11, 55, 20);
		contentPane.add(lblLogin_1);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(Configuracao.LOGO_HOLTER4));
		lblNewLabel.setBounds(134, 11, 100, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblSuporte = new JLabel("Suporte:");
		lblSuporte.setBounds(10, 138, 46, 14);
		contentPane.add(lblSuporte);
		
		JLabel lblHelber = new JLabel("Helber   8485-5643");
		lblHelber.setBounds(10, 154, 125, 14);
		contentPane.add(lblHelber);
		
		JLabel lblCesar = new JLabel("Cesar    8431-1357");
		lblCesar.setBounds(10, 167, 125, 14);
		contentPane.add(lblCesar);
		
	}

	public DeskTop getDeskTop() {
		return deskTop;
	}

	public void setDeskTop(DeskTop deskTop) {
		this.deskTop = deskTop;
	}
}
