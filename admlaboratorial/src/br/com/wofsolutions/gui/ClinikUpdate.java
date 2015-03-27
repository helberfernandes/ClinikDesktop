package br.com.wofsolutions.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.JLabel;

import br.com.wofsolutions.util.Configuracao;
import br.com.wofsolutions.util.Update;

import java.awt.Font;

public class ClinikUpdate extends JFrame {

	private JPanel contentPane;
	private Update update = new Update();
	private String urlUpdateFile = "http://192.168.0.74/clinik.jar"; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClinikUpdate frame = new ClinikUpdate();
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
	public ClinikUpdate() {
		setTitle("Atualizando...");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 60, 414, 14);
		contentPane.add(progressBar);
		
		JLabel lblAtualizandoOSistema = new JLabel("Atualizando o sistema, aguarde ...");
		lblAtualizandoOSistema.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAtualizandoOSistema.setBounds(24, 23, 365, 39);
		contentPane.add(lblAtualizandoOSistema);
		update.download(urlUpdateFile, Configuracao.class.getResource("/imagens/clinik.jar").getFile().toString(), null, 0);
	}
}
