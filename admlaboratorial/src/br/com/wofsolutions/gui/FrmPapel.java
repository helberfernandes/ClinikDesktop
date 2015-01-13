package br.com.wofsolutions.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import br.com.wofsolutions.dominio.Papel;
import br.com.wofsolutions.mensagens.Mensagens;
import br.com.wofsolutions.util.Configuracao;
import br.com.wofsolutions.util.MyCellRenderer;
import javax.swing.JCheckBox;

public class FrmPapel extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JToolBar toolBar = new JToolBar();
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnNovo = new JButton("Novo");
	private Papel papel = new Papel();
	private Papel papelExcluir = new Papel();
	private PapelDAOImpl papelDAOImpl = new PapelDAOImpl();
	private JLabel lblNome = new JLabel("Nome:");
	private FrmAtendimento frmAtendimento;
	private FrmExame frmExame;
	private JSeparator separator;
	private JTextField txtPesq;
	private JTable table;
	private JButton btnExcluir;
	private JScrollPane scroll;
	private List<Papel> list = new ArrayList<Papel>();
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel pnCadastro = new JPanel();
	private JPanel pnPesquisa = new JPanel();
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxNewCheckBox_2;
	private JCheckBox chckbxNewCheckBox_3;
	private JCheckBox chckbxNewCheckBox_4;
	private JCheckBox chckbxNewCheckBox_5;
	private JCheckBox chckbxNewCheckBox_6;
	private JCheckBox chckbxNewCheckBox_7;
	private JCheckBox chckbxPodeverrelatorioqtdprocedimentosporsolicitante;
	private FrmUsuario frmUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPapel frame = new FrmPapel();
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
	public FrmPapel() {

		setTitle("Papéis");
		setBounds(0, 0, 1018, 511);

		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		pnCadastro.setLayout(null);

		lblNome.setBounds(10, 13, 31, 14);
		pnCadastro.add(lblNome);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtNome.setText(txtNome.getText().toUpperCase());
			}
		});

		txtNome.setBounds(10, 33, 306, 20);
		pnCadastro.add(txtNome);
		txtNome.setColumns(10);

		toolBar.setFloatable(false);
		toolBar.setBounds(861, 410, 126, 33);
		pnCadastro.add(toolBar);
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!verificaSePapelExiste()) {
					boolean resp = papelDAOImpl.salvar(getPapel());
					limpaCampos();
					Mensagens.setMensagemSucessOuFalha(resp, pnCadastro);
					atualizaTabela();
					frmUsuario.atualizaTabela();
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

		tabbedPane.addTab("Cadastro", null, pnCadastro, null);

		chckbxNewCheckBox = new JCheckBox("Pode cadastrar usuário");
		chckbxNewCheckBox.setBounds(10, 138, 152, 23);
		pnCadastro.add(chckbxNewCheckBox);

		chckbxNewCheckBox_1 = new JCheckBox("Pode definir papéis");
		chckbxNewCheckBox_1.setBounds(10, 164, 117, 23);
		pnCadastro.add(chckbxNewCheckBox_1);

		chckbxNewCheckBox_2 = new JCheckBox(
				"Pode ver relatório exames realizados por médicos");
		chckbxNewCheckBox_2.setBounds(10, 60, 269, 23);
		pnCadastro.add(chckbxNewCheckBox_2);

		chckbxNewCheckBox_3 = new JCheckBox(
				"Pode ver relatório qtd procedimentos por exames");
		chckbxNewCheckBox_3.setBounds(10, 86, 269, 23);
		pnCadastro.add(chckbxNewCheckBox_3);

		chckbxPodeverrelatorioqtdprocedimentosporsolicitante = new JCheckBox(
				"Pode ver relatório qtd procedimentos por solicitante");
		chckbxPodeverrelatorioqtdprocedimentosporsolicitante.setBounds(281, 60,
				276, 23);
		pnCadastro.add(chckbxPodeverrelatorioqtdprocedimentosporsolicitante);

		chckbxNewCheckBox_4 = new JCheckBox(
				"Pode ver relatório de atendimentos");
		chckbxNewCheckBox_4.setBounds(10, 112, 269, 23);
		pnCadastro.add(chckbxNewCheckBox_4);

		chckbxNewCheckBox_5 = new JCheckBox("Pode ver relatório do Juan");
		chckbxNewCheckBox_5.setBounds(281, 112, 158, 23);
		pnCadastro.add(chckbxNewCheckBox_5);

		chckbxNewCheckBox_6 = new JCheckBox(
				"Pode ver relatório total procedimentos por médicos");
		chckbxNewCheckBox_6.setBounds(281, 86, 276, 23);
		pnCadastro.add(chckbxNewCheckBox_6);
		
		chckbxNewCheckBox_7 = new JCheckBox("Pode ver relatório do Kalil");
		chckbxNewCheckBox_7.setBounds(281, 138, 158, 23);
		pnCadastro.add(chckbxNewCheckBox_7);

		pnPesquisa.setLayout(null);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable tb = (JTable) e.getSource();
				if (e.getClickCount() == 2) {
					setPapel(list.get(tb.getSelectedRow()));
					tabbedPane.setSelectedIndex(0);
				} else {
					papelExcluir = list.get(tb.getSelectedRow());
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setModel(new DefaultTableModel(getColunas(),
				new String[] { "Nome" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(128);
		table.setDefaultRenderer(Object.class, new MyCellRenderer());

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
				boolean resp = papelDAOImpl.excluir(papelExcluir);
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
		txtPesq.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtPesq.setText(txtPesq.getText().toUpperCase());
			}
		});

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
		papel = new Papel();
		txtNome.setText("");

		chckbxNewCheckBox.setSelected(false);
		chckbxNewCheckBox_1.setSelected(false);
		chckbxNewCheckBox_2.setSelected(false);
		chckbxNewCheckBox_3.setSelected(false);
		chckbxNewCheckBox_4.setSelected(false);
		chckbxNewCheckBox_5.setSelected(false);
		chckbxNewCheckBox_6.setSelected(false);
		chckbxNewCheckBox_7.setSelected(false);
		chckbxPodeverrelatorioqtdprocedimentosporsolicitante.setSelected(false);
	}

	public Papel getPapel() {
		papel.setNome(txtNome.getText());
		papel.setPodeCadastrarUsuaio(chckbxNewCheckBox.isSelected());
		papel.setPodeDefinirPapeis(chckbxNewCheckBox_1.isSelected());
		papel.setPodeVerExamesRealizadosPorMedicos(chckbxNewCheckBox_2.isSelected());		
		papel.setPodeVerQTDProcedimentoPorExames(chckbxNewCheckBox_3.isSelected());
		papel.setPodeVerQTDProcedimentosPorSolicitante(chckbxPodeverrelatorioqtdprocedimentosporsolicitante.isSelected());
		papel.setPodeVerRelatorioDeAtendimentos(chckbxNewCheckBox_4.isSelected());
		papel.setPodeVerRelatorioDoJuan(chckbxNewCheckBox_5.isSelected());
		papel.setPodeVerTotalProcedimentosPorMedico(chckbxNewCheckBox_6.isSelected());	
		papel.setPodeVerRelatorioDoKalil(chckbxNewCheckBox_7.isSelected());
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
		txtNome.setText(papel.getNome());
		
		chckbxNewCheckBox.setSelected(papel.isPodeCadastrarUsuaio());
		chckbxNewCheckBox_1.setSelected(papel.isPodeDefinirPapeis());
		chckbxNewCheckBox_2.setSelected(papel.isPodeVerExamesRealizadosPorMedicos());
		chckbxNewCheckBox_3.setSelected(papel.isPodeVerQTDProcedimentoPorExames());
		chckbxPodeverrelatorioqtdprocedimentosporsolicitante.setSelected(papel.isPodeVerQTDProcedimentosPorSolicitante());		
		chckbxNewCheckBox_4.setSelected(papel.isPodeVerRelatorioDeAtendimentos());
		chckbxNewCheckBox_5.setSelected(papel.isPodeVerRelatorioDoJuan());
		chckbxNewCheckBox_6.setSelected(papel.isPodeVerTotalProcedimentosPorMedico());
		chckbxNewCheckBox_7.setSelected(papel.isPodeVerRelatorioDoKalil());
		
	}

	public boolean verificaSePapelExiste() {
		if (txtNome.getText().trim().isEmpty()) {
			Mensagens.addWarningMenssage(pnPesquisa,
					"Não pode cadastrar um convênio em branco", "Atenção!!");
			return true;
		}

		Papel tmp = papelDAOImpl.getPapelPeloNome(txtNome.getText());
		if (tmp != null && !tmp.getPapelId().equals(papel.getPapelId())) {
			Mensagens.addWarningMenssage(pnPesquisa, "Este Tipo Já existe",
					"Atenção!!");
			return true;
		}
		return false;
	}

	public Object[][] getColunas() {
		list.clear();
		if (txtPesq != null) {
			list = papelDAOImpl.getTodosOsPapeisPelaPesquisa(txtPesq.getText());
		} else {
			list = papelDAOImpl.getTodosOsPapeis();
		}

		Object[][] objects = new Object[list.size()][2];
		int i = 0;
		for (Papel papel : list) {
			objects[i][0] = papel.getNome();
			i++;
		}
		return objects;
	}

	public void atualizaTabela() {
		table.setModel(new DefaultTableModel(getColunas(),
				new String[] { "Nome" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}

	public FrmAtendimento getFrmAtendimento() {
		return frmAtendimento;
	}

	public void setFrmAtendimento(FrmAtendimento frmAtendimento) {
		this.frmAtendimento = frmAtendimento;
	}

	public FrmExame getFrmExame() {
		return frmExame;
	}

	public void setFrmExame(FrmExame frmExame) {
		this.frmExame = frmExame;
	}

	public FrmUsuario getFrmUsuario() {
		return frmUsuario;
	}

	public void setFrmUsuario(FrmUsuario frmUsuario) {
		this.frmUsuario = frmUsuario;
	}
	
}
