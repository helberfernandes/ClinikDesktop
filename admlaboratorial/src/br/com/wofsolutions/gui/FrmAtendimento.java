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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.MaskFormatter;

import br.com.wofsolutions.dao.AtendimentoDAOImpl;
import br.com.wofsolutions.dao.ConvenioDAOImpl;
import br.com.wofsolutions.dao.ExameDAOImpl;
import br.com.wofsolutions.dao.MedicoDAOImpl;
import br.com.wofsolutions.dominio.Atendimento;
import br.com.wofsolutions.dominio.Exame;
import br.com.wofsolutions.dominio.ExameConvenio;
import br.com.wofsolutions.dominio.Usuario;
import br.com.wofsolutions.gui.componentes.Java2sAutoTextField;
import br.com.wofsolutions.mensagens.Mensagens;
import br.com.wofsolutions.util.Configuracao;
import br.com.wofsolutions.util.MyCellRenderer;

public class FrmAtendimento extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JToolBar toolBar = new JToolBar();
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnNovo = new JButton("Novo");
	private Atendimento atendimento = new Atendimento();
	private Atendimento atendimentoExcluir = new Atendimento();
	private AtendimentoDAOImpl atendimentoDAOImpl = new AtendimentoDAOImpl();
	private ConvenioDAOImpl convenioDAOImpl = new ConvenioDAOImpl();
	private MedicoDAOImpl medicoDAOImpl = new MedicoDAOImpl();
	private ExameDAOImpl exameDAOImpl = new ExameDAOImpl();

	private JLabel lblNome = new JLabel("Paciente:");

	private JSeparator separator;
	private JTextField txtPesq;
	private JTable table;
	private JButton btnExcluir;
	private JScrollPane scroll;
	private List<Atendimento> list = new ArrayList<Atendimento>();
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private Java2sAutoTextField atxtConvenio;
	private Java2sAutoTextField atxtExame;
	private Java2sAutoTextField atxtMedico;
	private Java2sAutoTextField atxtSolicitante;
	private JPanel pnCadastro = new JPanel();
	private JPanel pnPesquisa = new JPanel();
	private JFormattedTextField txtData;
	private JLabel lblExame;
	private JTextField txtValor;
	private JCheckBox chckbxFaltaGuia;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Exame exame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAtendimento frame = new FrmAtendimento();
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
	public FrmAtendimento() {

		setTitle("Atendimento");
		setBounds(0, 0, 1018, 511);
		//tabbedPane.setSelectedIndex(0);

		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		pnCadastro.setLayout(null);

		lblNome.setBounds(106, 11, 97, 14);
		pnCadastro.add(lblNome);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtNome.setText(txtNome.getText().toUpperCase());
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					atxtConvenio.requestFocus();
				}
			}
		});
		txtNome.setBounds(106, 26, 306, 20);
		pnCadastro.add(txtNome);
		txtNome.setColumns(10);

		toolBar.setFloatable(false);
		toolBar.setBounds(861, 410, 126, 33);
		pnCadastro.add(toolBar);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (atxtExame.getText().trim().equals("")) {
					Mensagens.addInformationMenssage(pnCadastro,
							"Escolha um exame", "Atenção!!");
					atxtExame.requestFocus();
					return;
				}

				if (atxtConvenio.getText().trim().equals("")) {
					Mensagens.addInformationMenssage(pnCadastro,
							"Escolha um convênio", "Atenção!!");
					atxtConvenio.requestFocus();
					return;
				}

				if (getAtendimento().getPaciente().trim().equals("")) {
					Mensagens
							.addInformationMenssage(
									pnCadastro,
									"Não é permitido fazer lancamento para este paciente em branco!!",
									"Atenção!!");
					txtNome.requestFocus();
					return;
				}
				Atendimento tmp = atendimentoDAOImpl
						.getAtendimentoPeloNome(getAtendimento());
				if (tmp != null
						&& !tmp.getAtendimentoId().equals(
								getAtendimento().getAtendimentoId())) {
					Mensagens
							.addInformationMenssage(
									pnCadastro,
									"Não é permitido fazer outro lancamento para este paciente para este exame nesta data!!",
									"Atenção!!");

				} else {

					if (Mensagens.showConfirmDialog(null,
							"Deseja realmente salvar este registro?") == 0) {

						boolean resp = atendimentoDAOImpl
								.salvar(getAtendimento());
						limpaCampos();
						Mensagens.setMensagemSucessOuFalha(resp, pnCadastro);
						atualizaTabela();
					}
				}
				txtNome.requestFocus();
			}
		});
		btnSalvar.setIcon(new ImageIcon(Configuracao.ICONE_SALVAR));
		toolBar.add(btnSalvar);

		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpaCampos();
				txtNome.requestFocus();
			}
		});
		btnNovo.setToolTipText("Novo");
		btnNovo.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNovo.setHorizontalAlignment(SwingConstants.LEFT);
		btnNovo.setIcon(new ImageIcon(Configuracao.ICONE_NOVO));
		toolBar.add(btnNovo);

		tabbedPane.addTab("Cadastro", null, pnCadastro, null);

		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(10, 11, 46, 14);
		pnCadastro.add(lblData);

		txtData = new JFormattedTextField(mascara("##/##/####"));
		txtData.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtNome.requestFocus();
				}
			}
		});
		txtData.setEnabled(true);

		txtData.setText(dateFormat.format(new Date()));
		txtData.setEditable(true);
		txtData.setBounds(10, 26, 86, 20);
		pnCadastro.add(txtData);
		txtData.setColumns(10);

		JLabel lblConvnio = new JLabel("Convênio:");
		lblConvnio.setBounds(422, 11, 65, 14);
		pnCadastro.add(lblConvnio);

		atxtConvenio = new Java2sAutoTextField(
				convenioDAOImpl.getTotosConveniosAsString());
		atxtConvenio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					atxtExame.requestFocus();
				}
			}
		});
		atxtConvenio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				atxtConvenio.selectAll();
			}
		});

		atxtConvenio.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			//	if (atxtConvenio.getText().equals("PARTICULAR")) {
					txtValor.setEditable(true);
//				} else {
//					txtValor.setEditable(false);
//				}
				ExameConvenio ec = exameDAOImpl.getTotosExamesConveniosPeloExameIdEPeloConvenioId(
						exameDAOImpl.getExamePeloNome(atxtExame.getText())
								.getExameId(), convenioDAOImpl
								.getConvenioPeloNome(atxtConvenio.getText())
								.getConvenioId());

				Double valor = ec == null ? 0 : ec.getValor();
				txtValor.setText(String.valueOf(valor));
			}

			@Override
			public void focusGained(FocusEvent e) {
				atxtConvenio.selectAll();
			}
		});

		atxtConvenio.setBounds(422, 26, 154, 20);
		pnCadastro.add(atxtConvenio);

		lblExame = new JLabel("Exame:");
		lblExame.setBounds(586, 11, 46, 14);
		pnCadastro.add(lblExame);

		atxtExame = new Java2sAutoTextField(
				exameDAOImpl.getTotosExamesAsString());
		atxtExame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					atxtMedico.requestFocus();
				}
			}
		});
		atxtExame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				atxtExame.selectAll();
			}
		});

		atxtExame.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

				ExameConvenio ec = exameDAOImpl.getTotosExamesConveniosPeloExameIdEPeloConvenioId(
						exameDAOImpl.getExamePeloNome(atxtExame.getText())
								.getExameId(), convenioDAOImpl
								.getConvenioPeloNome(atxtConvenio.getText())
								.getConvenioId());

				// atxtMedico.setDataList(medicoDAOImpl.getTotosMedicosEquipeAsString(ec.getExameConvenioPK().getExame().getExameId()));

				Double valor = ec == null ? 0 : ec.getValor();
				txtValor.setText(String.valueOf(valor));
			}

			@Override
			public void focusGained(FocusEvent e) {
				atxtExame.selectAll();
			}
		});

		atxtExame.setBounds(586, 26, 154, 20);
		pnCadastro.add(atxtExame);

		JLabel lblMdico = new JLabel("Médico:");
		lblMdico.setBounds(106, 48, 65, 14);
		pnCadastro.add(lblMdico);

		atxtMedico = new Java2sAutoTextField(
				medicoDAOImpl.getTotosMedicosEquipeAsString2());
		atxtMedico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					atxtSolicitante.requestFocus();
				}
			}
		});
		atxtMedico.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				atxtMedico.selectAll();
			}
		});
		atxtMedico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				atxtMedico.selectAll();
			}
		});
		atxtMedico.setBounds(106, 66, 306, 20);
		pnCadastro.add(atxtMedico);

		JLabel lblSolicitante = new JLabel("Solicitante:");
		lblSolicitante.setBounds(422, 48, 65, 14);
		pnCadastro.add(lblSolicitante);

		atxtSolicitante = new Java2sAutoTextField(
				medicoDAOImpl.getTotosMedicosSolicitanteAsString());

		atxtSolicitante.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				atxtSolicitante.selectAll();
			}
		});
		atxtSolicitante.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				atxtSolicitante.selectAll();
			}
		});
		atxtSolicitante.setBounds(422, 66, 318, 20);
		pnCadastro.add(atxtSolicitante);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(10, 48, 86, 14);
		pnCadastro.add(lblValor);

		txtValor = new JTextField();
		txtValor.setFocusable(false);
		txtValor.setEditable(true);
		txtValor.setBounds(10, 66, 86, 20);
		txtValor.setText("0.0");
		// txtValor.setText(String.valueOf(exameDAOImpl.getTotosExamesConveniosPeloExameIdEPeloConvenioId(exameDAOImpl.getExamePeloNome(atxtExame.getText()).getExameId()
		// ,
		// convenioDAOImpl.getConvenioPeloNome(atxtConvenio.getText()).getConvenioId()).getValor()));
		pnCadastro.add(txtValor);
		txtValor.setColumns(10);

		chckbxFaltaGuia = new JCheckBox("Falta guia");
		chckbxFaltaGuia.setBounds(10, 92, 97, 23);
		pnCadastro.add(chckbxFaltaGuia);

		pnPesquisa.setLayout(null);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable tb = (JTable) e.getSource();
				if (e.getClickCount() == 2) {
					setAtendimento(list.get(tb.getSelectedRow()));
					tabbedPane.setSelectedIndex(0);
				} else {
					atendimentoExcluir = list.get(tb.getSelectedRow());
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setModel(new DefaultTableModel(getColunas(), new String[] {
				"Data", "Nome", "Convênio", "Exame", "Médico", "Solicitante",
				"Falta Guia" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Date.class, String.class,
					String.class, String.class, String.class, String.class,
					String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(128);
		table.getColumnModel().getColumn(2).setPreferredWidth(128);
		table.getColumnModel().getColumn(3).setPreferredWidth(128);
		table.getColumnModel().getColumn(4).setPreferredWidth(128);
		table.getColumnModel().getColumn(5).setPreferredWidth(128);

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
				boolean resp = atendimentoDAOImpl.excluir(atendimentoExcluir);
				Mensagens.setMensagemSucessOuFalha(resp, pnPesquisa);
				atualizaTabela();
				limpaCampos2();
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

	public void limpaCampos2() {
		atendimento = new Atendimento();

	
			txtNome.setText("");
			txtData.setText(dateFormat.format(new Date()));
	

		atxtConvenio.setDataList(convenioDAOImpl.getTotosConveniosAsString());

		atxtExame.setDataList(exameDAOImpl.getTotosExamesAsString());

		atxtMedico.setDataList(medicoDAOImpl.getTotosMedicosEquipeAsString2());
		atxtSolicitante.setDataList(medicoDAOImpl
				.getTotosMedicosSolicitanteAsString());
		txtValor.setText("0.0");
		chckbxFaltaGuia.setSelected(false);
	}
	
	
	public void limpaCampos() {
		atendimento = new Atendimento();

		if (Mensagens.showConfirmDialog(this,
				"Deseja lançar mais um procedimento para este paciente?") == 1) {
			txtNome.setText("");
			txtData.setText(dateFormat.format(new Date()));
		}

		atxtConvenio.setDataList(convenioDAOImpl.getTotosConveniosAsString());

		atxtExame.setDataList(exameDAOImpl.getTotosExamesAsString());

		atxtMedico.setDataList(medicoDAOImpl.getTotosMedicosEquipeAsString2());
		atxtSolicitante.setDataList(medicoDAOImpl
				.getTotosMedicosSolicitanteAsString());
		txtValor.setText("0.0");
		chckbxFaltaGuia.setSelected(false);
	}

	public Atendimento getAtendimento() {

		try {
			atendimento.setDataLancamento(dateFormat.parse(txtData.getText()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		atendimento.setPaciente(txtNome.getText());
		atendimento.setConvenio(convenioDAOImpl
				.getConvenioPeloNome(atxtConvenio.getText()));
		atendimento
				.setExame(exameDAOImpl.getExamePeloNome(atxtExame.getText()));
		atendimento.setMedico(medicoDAOImpl.getMedicoPeloNome(atxtMedico
				.getText()));
		atendimento.setSolicitante(medicoDAOImpl
				.getMedicoPeloNome(atxtSolicitante.getText()));
		atendimento.setValor(Double.parseDouble(txtValor.getText()));

		atendimento.setFaltaGuia(chckbxFaltaGuia.isSelected());
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
		txtNome.setText(atendimento.getPaciente());
		txtData.setText(dateFormat.format(atendimento.getDataLancamento()));
		atxtConvenio.setText(atendimento.getConvenio().getNome());
		atxtExame.setText(atendimento.getExame().getNome());
		atxtMedico.setText(atendimento.getMedico().getNome());
		atxtSolicitante.setText(atendimento.getSolicitante().getNome());
		txtValor.setText(String.valueOf(atendimento.getValor()));
		chckbxFaltaGuia.setSelected(atendimento.isFaltaGuia());

	}

	public Object[][] getColunas() {
		list.clear();
		if (txtPesq != null) {

			list = atendimentoDAOImpl.getTotosAtendimentosPelaPesquisa(txtPesq
					.getText());

		} else {
			list = atendimentoDAOImpl.getTotosAtendimentos();
		}

		Object[][] objects = new Object[list.size()][7];
		int i = 0;
		for (Atendimento atendimento : list) {
			objects[i][0] = atendimento.getDataLancamento();
			objects[i][1] = atendimento.getPaciente();
			objects[i][2] = atendimento.getConvenio().getNome();
			objects[i][3] = atendimento.getExame().getNome();
			objects[i][4] = atendimento.getMedico().getNome();
			objects[i][5] = atendimento.getSolicitante().getNome();
			objects[i][6] = atendimento.isFaltaGuia() ? "Sim" : "Não";
			i++;
		}
		return objects;
	}

	public void atualizaTabela() {
		table.setModel(new DefaultTableModel(getColunas(), new String[] {
				"Data", "Nome", "Convênio", "Exame", "Médico", "Solicitante",
				"Falta Guia" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Date.class, String.class,
					String.class, String.class, String.class, String.class,
					String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}

	public Java2sAutoTextField getAtxtConvenio() {
		return atxtConvenio;
	}

	public void setAtxtConvenio(Java2sAutoTextField atxtConvenio) {
		this.atxtConvenio = atxtConvenio;

	}

	public Java2sAutoTextField getAtxtExame() {
		return atxtExame;
	}

	public void setAtxtExame(Java2sAutoTextField atxtExame) {
		this.atxtExame = atxtExame;
	}

	public Java2sAutoTextField getAtxtMedico() {
		return atxtMedico;
	}

	public void setAtxtMedico(Java2sAutoTextField atxtMedico) {
		this.atxtMedico = atxtMedico;
	}

	public Java2sAutoTextField getAtxtSolicitante() {
		return atxtSolicitante;
	}

	public void setAtxtSolicitante(Java2sAutoTextField atxtSolicitante) {
		this.atxtSolicitante = atxtSolicitante;
	}

	public MaskFormatter mascara(String Mascara) {

		MaskFormatter F_Mascara = new MaskFormatter();
		try {
			F_Mascara.setMask(Mascara); // Atribui a mascara
			F_Mascara.setPlaceholderCharacter('_'); // Caracter para
													// preencimento
		} catch (Exception excecao) {
			excecao.printStackTrace();
		}
		return F_Mascara;
	}
}
