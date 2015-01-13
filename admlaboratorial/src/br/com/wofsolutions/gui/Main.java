package br.com.wofsolutions.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;

import br.com.wofsolutions.util.GUIUtil;
import java.awt.Frame;

public class Main {

	private JFrame frame;
	private PnConvenio pnConvenio = new PnConvenio();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setBounds(100, 100, 1024, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		frame.getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		JLabel lblUsurio = new JLabel("Usuário:");
		toolBar.add(lblUsurio);
		
		JLabel lblNewLabel = new JLabel("                                                  ");
		toolBar.add(lblNewLabel);
		
		JLabel label = new JLabel("                                        ");
		toolBar.add(label);
		
		JLabel lblVerso = new JLabel("                                                                                                                                                                                                         Versão:   2.0.0");
		toolBar.add(lblVerso);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		desktopPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane, BorderLayout.CENTER);
		
		
		
		
		JTree tree = new JTree();
		splitPane.setLeftComponent(tree);
		
		
		splitPane.setRightComponent(pnConvenio);
		splitPane.setDividerLocation(180);
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		GUIUtil.setLookAndFeel(frame);
		GUIUtil.center(frame);
	}
}
