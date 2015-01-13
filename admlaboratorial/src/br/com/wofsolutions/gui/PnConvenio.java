package br.com.wofsolutions.gui;

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
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.com.wofsolutions.dao.ConvenioDAOImpl;
import br.com.wofsolutions.dominio.Convenio;
import br.com.wofsolutions.mensagens.Mensagens;
import br.com.wofsolutions.util.Configuracao;
import br.com.wofsolutions.util.MyCellRenderer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuItem;
import java.awt.ComponentOrientation;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class PnConvenio extends JPanel {
	private JTextField txtNome;
	private JTextField txtPesq;
	private ConvenioDAOImpl convenioDAOImpl = new ConvenioDAOImpl();
	private JTable table;
	private List<Convenio> list = new ArrayList<Convenio>();
	private Convenio convenio = new Convenio();
	private Convenio convenioExcluir = new Convenio();
	
	/**
	 * Create the panel.
	 */
	public PnConvenio() {
		setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		add(menuBar, BorderLayout.NORTH);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Novo");
		mntmNewMenuItem.setIcon(new ImageIcon(Configuracao.ICONE_NOVO));
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.setIcon(new ImageIcon(Configuracao.ICONE_SALVAR));
		menuBar.add(mntmSalvar);
		
		JMenuItem mntmEditar = new JMenuItem("Editar");
		mntmEditar.setIcon(new ImageIcon(Configuracao.ICONE_PESQUISA));
		menuBar.add(mntmEditar);
		
		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.setIcon(new ImageIcon(Configuracao.ICONE_LIXEIRA));
		menuBar.add(mntmExcluir);
		
		JMenuItem menuItem_2 = new JMenuItem("                                                                                                                                                     ");
		menuBar.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("                             ");
		menuBar.add(menuItem_3);
		
		JMenuItem menuItem_5 = new JMenuItem("                                                                               ");
		menuBar.add(menuItem_5);
		
		JMenuItem menuItem = new JMenuItem("                                                                                                                                                ");
		menuBar.add(menuItem);
		
		JMenuItem menuItem_4 = new JMenuItem("                                                                                                                                          ");
		menuBar.add(menuItem_4);
		
		JMenuItem menuItem_1 = new JMenuItem("                                                                                                                                                         ");
		menuBar.add(menuItem_1);
		
		
		
		
		
		JPanel panel_1 = new JPanel();
		
		add(panel_1, BorderLayout.CENTER);
		
		
		
		
		JPanel pnForm = new JPanel();
		pnForm.setBounds(0, 0, 999, 118);
		pnForm.setAlignmentY(Component.TOP_ALIGNMENT);
		pnForm.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		pnForm.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 30, 325, 20);
		pnForm.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel label = new JLabel("Nome:");
		label.setBounds(10, 11, 31, 14);
		pnForm.add(label);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(0, 116, 1045, 555);
		
				
				JPanel panel = new JPanel();
			
				tabbedPane.addTab("Pesquisa", null, panel, null);
				panel.setLayout(null);
				
				JLabel lblPesquisar = new JLabel("Pesquisar:");
				lblPesquisar.setBounds(10, 13, 59, 14);
				panel.add(lblPesquisar);
				
				txtPesq = new JTextField();
				txtPesq.setBounds(10, 34, 308, 20);
				panel.add(txtPesq);
				txtPesq.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						txtPesq.setText(txtPesq.getText().toUpperCase());
					}
				});
				
				
				txtPesq.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {				
						atualizaTabela();
					}
				});
				txtPesq.setColumns(10);
				
				
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JTable tb = (JTable) e.getSource();
						if (e.getClickCount() == 2) {
							setConvenio(list.get(tb.getSelectedRow()));
							//tabbedPane.setSelectedIndex(0);
						} else {
							convenioExcluir = list.get(tb.getSelectedRow());
						}
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
						table.setModel(new DefaultTableModel(getColunas(), new String[] {
								"Nome" }) {
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
						
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(10, 65, 1020, 442);
						panel.add(scrollPane);
						panel_1.setLayout(null);
						panel_1.add(pnForm);
						panel_1.add(tabbedPane);
				
				

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

		Convenio tmp = convenioDAOImpl.getConvenioPeloNome(txtNome.getText());
		if (tmp != null && !tmp.getConvenioId().equals(convenio.getConvenioId())) {
			Mensagens.addWarningMenssage(this, "Este Tipo Já existe",
					"Atenção!!");
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
			objects[i][0] = convenio.getNome();			
			i++;
		}
		return objects;
	}

	public void atualizaTabela() {
		table.setModel(new DefaultTableModel(getColunas(), new String[] {
				"Nome" }) {
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


	public ConvenioDAOImpl getConvenioDAOImpl() {
		return convenioDAOImpl;
	}


	public void setConvenioDAOImpl(ConvenioDAOImpl convenioDAOImpl) {
		this.convenioDAOImpl = convenioDAOImpl;
	}


	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
	}


	public List<Convenio> getList() {
		return list;
	}


	public void setList(List<Convenio> list) {
		this.list = list;
	}




	public Convenio getConvenioExcluir() {
		return convenioExcluir;
	}


	public void setConvenioExcluir(Convenio convenioExcluir) {
		this.convenioExcluir = convenioExcluir;
	}
}

