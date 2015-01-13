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

import br.com.wofsolutions.dao.ConvenioDAOImpl;
import br.com.wofsolutions.dominio.Convenio;
import br.com.wofsolutions.idioma.Idioma;
import br.com.wofsolutions.idioma.IdiomaKey;
import br.com.wofsolutions.mensagens.Mensagens;
import br.com.wofsolutions.util.Configuracao;
import br.com.wofsolutions.util.MyCellRenderer;

public class FrmConvenio extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JToolBar toolBar = new JToolBar();
	private JButton btnSalvar = new JButton(Idioma.getMensagem(IdiomaKey.WOF_LABEL_SALVAR));
	private JButton btnNovo = new JButton(Idioma.getMensagem(IdiomaKey.WOF_LABEL_NOVO));
	private Convenio convenio = new Convenio();
	private Convenio convenioExcluir = new Convenio();
	private ConvenioDAOImpl convenioDAOImpl = new ConvenioDAOImpl();
	private JLabel lblNome = new JLabel(Idioma.getMensagem(IdiomaKey.WOF_LABEL_NOME));
	private FrmAtendimento frmAtendimento;
	private FrmExame frmExame;
	private JSeparator separator;
	private JTextField txtPesq;
	private JTable table;
	private JButton btnExcluir;
	private JScrollPane scroll;
	private List<Convenio> list = new ArrayList<Convenio>();
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
					FrmConvenio frame = new FrmConvenio();
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
	public FrmConvenio() {

		setTitle(Idioma.getMensagem(IdiomaKey.WOF_TITULO_CONVENIO));
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
				if (!verificaSeConvenioExiste()) {
					boolean resp = convenioDAOImpl.salvar(getConvenio());
					limpaCampos();
					Mensagens.setMensagemSucessOuFalha(resp, pnCadastro);
					atualizaTabela();
					frmAtendimento.getAtxtConvenio().setDataList(convenioDAOImpl.getTotosConveniosAsString());
					frmExame.atualizaConvenios();
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
		btnNovo.setToolTipText(Idioma.getMensagem(IdiomaKey.WOF_LABEL_NOVO));
		btnNovo.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNovo.setHorizontalAlignment(SwingConstants.LEFT);
		btnNovo.setIcon(new ImageIcon(Configuracao.ICONE_NOVO));
		toolBar.add(btnNovo);

		tabbedPane.addTab(Idioma.getMensagem(IdiomaKey.WOF_LABEL_CADASTRO), null, pnCadastro, null);

		pnPesquisa.setLayout(null);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable tb = (JTable) e.getSource();
				if (e.getClickCount() == 2) {
					setConvenio(list.get(tb.getSelectedRow()));
					tabbedPane.setSelectedIndex(0);
				} else {
					convenioExcluir = list.get(tb.getSelectedRow());
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		atualizaTabela();

		scroll = new JScrollPane(table);
		scroll.setBounds(10, 41, 977, 402);
		pnPesquisa.add(scroll);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(10, 11, 647, 22);
		pnPesquisa.add(toolBar);

		btnExcluir = new JButton(Idioma.getMensagem(IdiomaKey.WOF_LABEL_EXCLUIR));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean resp = convenioDAOImpl.excluir(convenioExcluir);
				Mensagens.setMensagemSucessOuFalha(resp, pnPesquisa);
				atualizaTabela();
				limpaCampos();
			}
		});
		btnExcluir.setIcon(new ImageIcon(Configuracao.ICONE_LIXEIRA));
		toolBar.add(btnExcluir);

		separator = new JSeparator();
		toolBar.add(separator);

		JLabel lblPesquisar = new JLabel(Idioma.getMensagem(IdiomaKey.WOF_LABEL_PESQUISAR));
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
		tabbedPane.addTab(Idioma.getMensagem(IdiomaKey.WOF_LABEL_PESQUISAR), null, pnPesquisa, null);
	}

	public void limpaCampos() {
		convenio = new Convenio();
		txtNome.setText("");
	}

	public Convenio getConvenio() {
		convenio.setNome(txtNome.getText());
		
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
		txtNome.setText(convenio.getNome());
	}

	public boolean verificaSeConvenioExiste() {
		if(txtNome.getText().trim().isEmpty()){
			Mensagens.addWarningMenssage(pnPesquisa, Idioma.getMensagem(IdiomaKey.WOF_MSG_CONVENIO_EM_BRANCO),
					Idioma.getMensagem(IdiomaKey.WOF_LABEL_ATENCAO));
			return true;
		}
		
		Convenio tmp = convenioDAOImpl.getConvenioPeloNome(txtNome.getText());
		if (tmp != null && !tmp.getConvenioId().equals(convenio.getConvenioId())) {
			Mensagens.addWarningMenssage(pnPesquisa, Idioma.getMensagem(IdiomaKey.WOF_MSG_TIPO_EXISTENTE),
					Idioma.getMensagem(IdiomaKey.WOF_LABEL_ATENCAO));
			return true;
		}
		return false;
	}

	public Object[][] getColunas() {
		list.clear();
		if (txtPesq != null) {
			list = convenioDAOImpl.getTotosConveniosPelaPesquisa(txtPesq.getText());
		} else {
			list = convenioDAOImpl.getTotosConvenios();
		}

		Object[][] objects = new Object[list.size()][2];
		int i = 0;
		for (Convenio convenio : list) {
			objects[i][0] = (i+1);
			objects[i][1] = convenio.getNome();			
			i++;
		}
		return objects;
	}

	public void atualizaTabela() {
		table.setModel(new DefaultTableModel(getColunas(), new String[] {
			Idioma.getMensagem(IdiomaKey.WOF_LABEL_NUMERO),Idioma.getMensagem(IdiomaKey.WOF_LABEL_NOME) }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Integer.class, String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(128);	
		table.setDefaultRenderer(Object.class, new MyCellRenderer());
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
	
}
