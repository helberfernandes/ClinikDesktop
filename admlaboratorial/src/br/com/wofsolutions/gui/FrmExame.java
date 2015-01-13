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
import javax.swing.JCheckBox;
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
import br.com.wofsolutions.dao.ExameDAOImpl;
import br.com.wofsolutions.dao.MedicoDAOImpl;
import br.com.wofsolutions.dominio.Convenio;
import br.com.wofsolutions.dominio.Exame;
import br.com.wofsolutions.dominio.ExameConvenio;
import br.com.wofsolutions.dominio.ExameConvenioPK;
import br.com.wofsolutions.dominio.Medico;
import br.com.wofsolutions.mensagens.Mensagens;
import br.com.wofsolutions.util.Configuracao;
import br.com.wofsolutions.util.MyCellRenderer;

public class FrmExame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JToolBar toolBar = new JToolBar();
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnNovo = new JButton("Novo");
	private Exame exame = new Exame();
	private Exame exameExcluir = new Exame();
	private ExameDAOImpl exameDAOImpl = new ExameDAOImpl();
	private MedicoDAOImpl medicoDAOImpl = new MedicoDAOImpl();
	private ConvenioDAOImpl convenioDAOImpl = new ConvenioDAOImpl();
	private JLabel lblNome = new JLabel("Nome:");
    private List<ExameConvenio> exameConvenios = new ArrayList<ExameConvenio>();
	private JSeparator separator;
	private JTextField txtPesq;
	private JTable table;
	private JButton btnExcluir;
	private JScrollPane scroll;
	private List<Exame> list = new ArrayList<Exame>();
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    private FrmAtendimento frmAtendimento ;
	private JPanel panel = new JPanel();
	private JPanel pnCadastro = new JPanel();
	private JPanel pnPesquisa = new JPanel();
	private final JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel pnConvenio = new JPanel();
	private final JScrollPane scConvenio;
	private JTable tbConvenios;
	private List<Medico> medicos = new ArrayList<Medico>();
	private JTextField ftxtHonorario;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmExame frame = new FrmExame();
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
	public FrmExame() {

		setTitle("Exames");
		setBounds(0, 0, 1018, 511);

		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		pnCadastro.setLayout(null);

		lblNome.setBounds(10, 13, 31, 14);
		pnCadastro.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(10, 32, 306, 20);
		pnCadastro.add(txtNome);
		txtNome.setColumns(10);
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtNome.setText(txtNome.getText().toUpperCase());
			}
		});
		toolBar.setFloatable(false);
		toolBar.setBounds(861, 410, 126, 33);
		pnCadastro.add(toolBar);
		
		
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!verificaSeExameExiste()) {
					boolean resp = exameDAOImpl.salvar(getExame());
List<Convenio>convenios=convenioDAOImpl.getTotosConvenios();
					
					 
					
					int i = 0;
					for (Convenio convenio : convenios) {	
						double valor=0.0;
						try{
							valor=Double.parseDouble(String.valueOf(tbConvenios.getModel().getValueAt(i, 1)));
						}catch (NumberFormatException  e) {
							valor=0.0;
						}
						
						exameDAOImpl.salvarObjeto(new ExameConvenio(new ExameConvenioPK(exame, convenio), valor));
						i++;
					}
					
					limpaCampos();
					Mensagens.setMensagemSucessOuFalha(resp, pnCadastro);
					atualizaTabela();
					
					frmAtendimento.getAtxtExame().setDataList(exameDAOImpl.getTotosExamesAsString());
					
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
		tabbedPane_1.setBounds(10, 56, 977, 342);

		pnCadastro.add(tabbedPane_1);

		tabbedPane_1.addTab("Médicos", null, scrollPane, null);

		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		tabbedPane_1.addTab("Convênio", null, pnConvenio, null);
		pnConvenio.setLayout(null);

		tbConvenios = new JTable();
	
		
		tbConvenios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//exameConvenios.add(new ExameConvenio(, new ExameConvenioPK(exame, convenio)));
				
				JTable tbConvenios=(JTable) e.getSource();
				//tbConvenios.
			}
		});
		tbConvenios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbConvenios.setDefaultRenderer(Object.class, new MyCellRenderer());
		atualizaConvenios();
		
		scConvenio = new JScrollPane(tbConvenios);
		
		
		
		scConvenio.setBounds(10, 5, 952, 298);
		pnConvenio.add(scConvenio);
		
		ftxtHonorario = new JTextField();
		ftxtHonorario.setBounds(326, 32, 138, 20);
		
		ftxtHonorario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				ftxtHonorario.setText(ftxtHonorario.getText().toUpperCase());
			}
		});
		
		pnCadastro.add(ftxtHonorario);
		
		JLabel lblHonorrios = new JLabel("Honorários:");
		lblHonorrios.setBounds(326, 13, 81, 14);
		pnCadastro.add(lblHonorrios);

		montaListaDeMedicos();

		pnPesquisa.setLayout(null);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable tb = (JTable) e.getSource();
				if (e.getClickCount() == 2) {
					setExame(list.get(tb.getSelectedRow()));
					tabbedPane.setSelectedIndex(0);
					montaListaDeMedicos();
					atualizaConvenios();
					
				} else {
					exameExcluir = list.get(tb.getSelectedRow());
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

			boolean[] columnEditables = new boolean[] { false, true };

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
//				boolean resp=false;
//				List<ExameConvenio> list= exameDAOImpl.getTotosExamesConveniosPeloExameId(exameExcluir.getExameId());
//				for(ExameConvenio e: list){
//				 resp= exameDAOImpl.excluirObjeto(e);
//				}
				
				
				boolean resp = exameDAOImpl.excluir(exameExcluir);
				Mensagens.setMensagemSucessOuFalha(resp, pnPesquisa);
				atualizaTabela();
				limpaCampos();
				atualizaConvenios();
				montaListaDeMedicos();
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
				txtPesq.setText(txtPesq.getText().toUpperCase());
			}
		});
		txtPesq.setColumns(10);
		tabbedPane.addTab("Pesquisa", null, pnPesquisa, null);
	}

	public void limpaCampos() {
		exame = new Exame();
		txtNome.setText("");
		ftxtHonorario.setText("");
	}

	public Exame getExame() {
		exame.setNome(txtNome.getText());
		
		try{
		exame.setHonorarios(Double.parseDouble(String.valueOf(ftxtHonorario.getText())));
		}catch (NumberFormatException e) {
			exame.setHonorarios(0.0);
		}

		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
		txtNome.setText(exame.getNome());
		ftxtHonorario.setText(String.valueOf(exame.getHonorarios()));
	}

	public boolean verificaSeExameExiste() {

		if(txtNome.getText().trim().isEmpty()){
			Mensagens.addWarningMenssage(pnPesquisa, "Não pode cadastrar um exame em branco",
					"Atenção!!");
			return true;
		}
		
		Exame tmp = exameDAOImpl.getExamePeloNome(txtNome.getText());
		if (tmp != null && !tmp.getExameId().equals(exame.getExameId())) {
			Mensagens.addWarningMenssage(pnPesquisa, "Este Tipo Já existe",
					"Atenção!!");
			return true;
		}
		return false;
	}

	public Object[][] getColunas() {
		list.clear();
		if (txtPesq != null) {
			list = exameDAOImpl.getTotosExamesPelaPesquisa(txtPesq.getText());
		} else {
			list = exameDAOImpl.getTotosExames();
		}

		Object[][] objects = new Object[list.size()][2];
		int i = 0;
		for (Exame exame : list) {
			objects[i][0] = exame.getNome();
			i++;
		}
		return objects;
	}

	
	public Object[][] getColunasConvenios() {		
		exameConvenios= exameDAOImpl.getTotosExamesConveniosPeloExameId(exame.getExameId());
		List<Convenio>convenios=convenioDAOImpl.getTotosConvenios();

		Object[][] objects = new Object[convenios.size()][2];
		int i = 0;
		for (Convenio convenio : convenios) {
			
			objects[i][0] = convenio.getNome();

			if(exameConvenios.size()==0){
				objects[i][1] = 0;	
			}
			for (ExameConvenio e : exameConvenios) {
				if(e.getExameConvenioPK().getConvenio().getConvenioId().equals(convenio.getConvenioId())){
					objects[i][1] = e.getValor();
				}
			}
			
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

			boolean[] columnEditables = new boolean[] { false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}

	public void montaListaDeMedicos() {
		panel = null;
		panel = new JPanel();
		panel.setLayout(null);
		scrollPane.setViewportView(panel);
		medicos = medicoDAOImpl.getTotosMedicos();
		JCheckBox chckbxNewCheckBox;
		int espaco = 0;
		int i = 0;
		int linha = 0;
		for (Medico medico : medicos) {

			if (i >= 9) {
				i = 0;
				linha += 20;
				espaco = 0;
			}
			
			chckbxNewCheckBox = new JCheckBox(medico.getNome());
			chckbxNewCheckBox.setBounds(6 + espaco, 7 + linha, 100, 23);
			chckbxNewCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JCheckBox chckbxNewCheckBox = (JCheckBox) e.getSource();		
					if(chckbxNewCheckBox.isSelected()){
						exame.getMedicos().add(medicos.get(medicos.indexOf(new Medico(chckbxNewCheckBox.getText()))));
					}else{
						exame.getMedicos().remove(new Medico(chckbxNewCheckBox.getText()));
					}
				}
			});

			// mrcando os médicos já escolhidos
			List<Medico> list = exame.getMedicos();
			for (Medico m : list) {
				if (medico.getMedicoId().equals(m.getMedicoId())) {
					chckbxNewCheckBox.setSelected(true);
				}
			}

			panel.add(chckbxNewCheckBox);
			espaco += 100;
			i++;
		}

	}
	
	
	
	public void atualizaConvenios(){
		
		tbConvenios.setModel(new DefaultTableModel(getColunasConvenios(),
				new String[] { "Convênio", "Valor" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, Double.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbConvenios.getColumnModel().getColumn(1).setMaxWidth(75);
		
//		
//		MaskFormatter cep = null;  
//		  JFormattedTextField jftf; 
//		  try{  
//		      cep = new MaskFormatter("#####-###");  
//		      cep.setPlaceholderCharacter('_');  
//		    }  
//		    catch(ParseException ex){  
//		      ex.printStackTrace();  
//		    }
//		// Jogando a máscara no JFTF  
//		    jftf = new JFormattedTextField(cep);
//		    
//		    TableColumn col = tbConvenios.getColumnModel().getColumn(1);  
//		    // Aqui a mágica acontece!  
//		    col.setCellEditor(new DefaultCellEditor(jftf));      
	}
	

	public FrmAtendimento getFrmAtendimento() {
		return frmAtendimento;
	}

	public void setFrmAtendimento(FrmAtendimento frmAtendimento) {
		this.frmAtendimento = frmAtendimento;
	}
}
