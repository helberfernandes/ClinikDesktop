package br.com.wofsolutions.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.com.wofsolutions.dao.PapelDAOImpl;
import br.com.wofsolutions.dao.UsuarioDAOImpl;
import br.com.wofsolutions.dominio.Papel;
import br.com.wofsolutions.dominio.Usuario;
import br.com.wofsolutions.mensagens.Mensagens;
import br.com.wofsolutions.util.Configuracao;
import br.com.wofsolutions.util.WofUtil;

public class FrmUsuario extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtLogin;
	private JToolBar toolBar = new JToolBar();
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnNovo = new JButton("Novo");
	private Usuario usuario = new Usuario();
	private Usuario usuarioExcluir = new Usuario();
	
	private UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
	private JPasswordField pswSenha;
	private JLabel lblNome = new JLabel("Nome:");
    private JComboBox cbPapeis = new JComboBox();
	private PapelDAOImpl papelDAOImpl = new PapelDAOImpl();
	private JSeparator separator;
	private JTextField txtPesq;
	private JTable table;
	private JButton btnExcluir;	
    private JScrollPane scroll;  
    private List<Usuario> list = new ArrayList<Usuario>();
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
    private JPanel pnCadastro = new JPanel();
    private JPanel pnPesquisa = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmUsuario frame = new FrmUsuario();
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
	public FrmUsuario() {
	
			
	
		setTitle("Usuários");
		setBounds(0, 0, 1018, 511); 
		
		
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		
		
		
		

		pnCadastro.setLayout(null);
		
		lblNome.setBounds(10, 14, 31, 14);
		pnCadastro.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(10, 25, 306, 20);
		pnCadastro.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 46, 29, 14);
		pnCadastro.add(lblLogin);

		txtLogin = new JTextField();
		txtLogin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				verificaSeUsuarioExiste();
			}
		});
		txtLogin.setBounds(10, 59, 306, 20);
		pnCadastro.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(326, 14, 34, 14);
		pnCadastro.add(lblSenha);

		toolBar.setFloatable(false);
		toolBar.setBounds(861, 410, 126, 33);
		pnCadastro.add(toolBar);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!verificaSeUsuarioExiste()){
					boolean resp = usuarioDAOImpl.salvar(getUsuario());
					limpaCampos();
					Mensagens.setMensagemSucessOuFalha(resp, pnCadastro);	
					atualizaTabela();					
				}
				
			}
		});
		btnSalvar.setIcon(new ImageIcon(Configuracao.ICONE_SALVAR));
		toolBar.add(btnSalvar);

		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaCampos();
			}
		});
		btnNovo.setToolTipText("Novo");
		btnNovo.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNovo.setHorizontalAlignment(SwingConstants.LEFT);
		btnNovo.setIcon(new ImageIcon(Configuracao.ICONE_NOVO));
		toolBar.add(btnNovo);

		pswSenha = new JPasswordField();
		pswSenha.setBounds(326, 25, 183, 20);
		pnCadastro.add(pswSenha);

		
		populaComboPapeis();
		cbPapeis.setBounds(326, 59, 183, 20);
		pnCadastro.add(cbPapeis);
		
		JLabel lblPapel = new JLabel("Papel:");
		lblPapel.setBounds(326, 46, 30, 14);
		pnCadastro.add(lblPapel);
		
		tabbedPane.addTab("Cadastro", null, pnCadastro, null);
		
		
		
		
		pnPesquisa.setLayout(null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable tb=(JTable) e.getSource();
				if(e.getClickCount()==2){		
					setUsuario(list.get(tb.getSelectedRow()));
					tabbedPane.setSelectedIndex(0);
				}else{				
					usuarioExcluir =list.get(tb.getSelectedRow());
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		table.setModel(new DefaultTableModel(
				getColunas(),
			new String[] {
				"Nome", "Login"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(128);
		table.getColumnModel().getColumn(1).setPreferredWidth(132);
		
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 41, 977, 402);
		pnPesquisa.add(scroll);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(10, 11, 647, 22);
		pnPesquisa.add(toolBar);
		
        btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		boolean resp=usuarioDAOImpl.excluir(usuarioExcluir);
        		Mensagens.setMensagemSucessOuFalha(resp, pnPesquisa);
        		atualizaTabela();
        		limpaCampos();
        	}
        });
		btnExcluir.setIcon(new ImageIcon(Configuracao.ICONE_LIXEIRA));
		toolBar.add(btnExcluir);
		
		separator = new JSeparator();
		toolBar.add(separator);
		
		JLabel lblPesquisar = new JLabel("Pesquisar");
		toolBar.add(lblPesquisar);
		
		txtPesq = new JTextField();
		toolBar.add(txtPesq);
		txtPesq.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				atualizaTabela();
			}
		});
		txtPesq.setColumns(10);
		
		
		tabbedPane.addTab("Pesquisa", null, pnPesquisa, null);
		
	}
	
	
	public void limpaCampos() {
		usuario = new Usuario();
		txtNome.setText("");
		txtLogin.setText("");
		pswSenha.setText("");
		populaComboPapeis();
	}

	public Usuario getUsuario() {
		usuario.setNome(txtNome.getText());
		usuario.setLogin(txtLogin.getText());
		usuario.setSenha(WofUtil.md5(pswSenha.getText()));
		usuario.setPapel(papelDAOImpl.getPapelPeloNome(String.valueOf(cbPapeis.getSelectedItem())));
		return usuario;
	}

	
	public void setUsuario(Usuario usuario) {		
		this.usuario = usuario;
		txtNome.setText(usuario.getNome());
		txtLogin.setText(usuario.getLogin());
		pswSenha.setText("");
		populaComboPapeis();
	}

	public void populaComboPapeis(){
		cbPapeis.removeAllItems();
		List<Papel> list=papelDAOImpl.getTodosOsPapeis();
		for(Papel papel : list){			
			cbPapeis.addItem(papel.getNome());	
		}
		
		if(usuario.getPapel()!=null){		
			cbPapeis.setSelectedItem(usuario.getPapel().getNome());
		}
		
	}
	
	public boolean  verificaSeUsuarioExiste(){
		
	Usuario tmp= usuarioDAOImpl.getUsuarioPeloLogin(txtLogin.getText());
	if(tmp!=null && !tmp.getUsuarioId().equals(usuario.getUsuarioId())){
		Mensagens.addWarningMenssage(pnPesquisa, "Este Login Já existe", "Atenção!!");
		return true;
	}
	return false;
	}
	
	public Object[][] getColunas(){
		list.clear();
		if(txtPesq!=null){
			
				list= usuarioDAOImpl.getTotosUsuariosPelaPesquisa(txtPesq.getText());
			
		}else{
			list= usuarioDAOImpl.getTotosUsuarios();
		}
	    
		Object[][] objects = new Object[list.size()][2];
		int i=0;
		for(Usuario usuario: list){
			objects[i][0]=usuario.getNome();
			objects[i][1]=usuario.getLogin();
			i++;
		}
		return objects;
	}
	
	public void atualizaTabela(){
		table.setModel(new DefaultTableModel(
				getColunas(),
			new String[] {
				"Nome", "Login"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}

}
