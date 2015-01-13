package br.com.wofsolutions.gui;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import br.com.wofsolutions.util.Configuracao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class FrmRelatoriosdoSistema extends JFrame {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRelatoriosdoSistema frame = new FrmRelatoriosdoSistema();
					frame.setVisible(true);
					JLabel lab = new JLabel();

					lab.setIcon(new ImageIcon(Configuracao.LOGO_HOLTER4));
					frame.getContentPane().add(lab);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void FrmRelatoriosdoSistema() {
				// TODO Auto-generated method stub

			}
		});
	}

	public FrmRelatoriosdoSistema() {
		getContentPane().setEnabled(false);

		setTitle("Relatórios do Sistema");
		setBounds(100, 100, 547, 330);
		getContentPane().setLayout(null);
		
		JButton btnPrximo = new JButton("Próximo >>>");
		ButtonGroup relatorios = new ButtonGroup();

		JRadioButton rdbtnRelatrioDeAtendimentos = new JRadioButton(
				"Atendimentos");
		JRadioButton rdbtnRelatrioDeProcedimentos = new JRadioButton(
				"Relatório de Procedimentos por Exames");
		JRadioButton rdbtnRelatrioDrJuan = new JRadioButton(
				"Relatório Dr. Juan");
		JRadioButton rdbtnRelatrioDrKalil = new JRadioButton(
				"Relatório de Exames por Solicitantes");
		JRadioButton rdbtnRelatrioGeralDe = new JRadioButton(
				"Relatório Geral de Procedimentos");
		JRadioButton rdbtnRelatrioDeMovimentos = new JRadioButton(
				"Quantidade de Exames por Médicos");
		JRadioButton rdbtnQuantidadeMensalDe = new JRadioButton(
				"Quantidade Mensal de Exames");
		JRadioButton rdbtnQuantidadeDeProcedimentos = new JRadioButton(
				"Quantidade de Procedimentos por Solicitante");
		JRadioButton rdbtnQTDProcedimentosPorExames = new JRadioButton(
				"Total de Procedimentos por Médicos");

		rdbtnRelatrioDeAtendimentos.setBounds(176, 50, 270, 15);
		rdbtnRelatrioDeProcedimentos.setBounds(99, 98, 100, 15);
		rdbtnRelatrioDrJuan.setBounds(176, 90, 200, 15);
		rdbtnRelatrioDrKalil.setBounds(176, 110, 200, 15);
		rdbtnRelatrioGeralDe.setBounds(176, 130, 270, 15);
		rdbtnQuantidadeMensalDe.setBounds(176, 150, 270, 15);
		rdbtnQuantidadeDeProcedimentos.setBounds(176, 170, 283, 15);
		rdbtnQTDProcedimentosPorExames.setBounds(176, 190, 270, 15);
		rdbtnRelatrioDeMovimentos.setBounds(176, 70, 270, 15);
		btnPrximo.setBounds(376, 251, 112, 30);
	//	lab.setBounds(176, 351, 100, 100);

		relatorios.add(rdbtnRelatrioDeAtendimentos);
		relatorios.add(rdbtnRelatrioDeProcedimentos);
		relatorios.add(rdbtnRelatrioDrJuan);
		relatorios.add(rdbtnRelatrioDrKalil);
		relatorios.add(rdbtnRelatrioGeralDe);
		relatorios.add(rdbtnQuantidadeMensalDe);
		relatorios.add(rdbtnQuantidadeDeProcedimentos);
		relatorios.add(rdbtnQTDProcedimentosPorExames);
		relatorios.add(rdbtnRelatrioDeMovimentos);
		relatorios.add(btnPrximo);
	//	relatorios.add(lab);
		
		
		//getContentPane().add(lab);
		getContentPane().add(rdbtnRelatrioDeAtendimentos);
		getContentPane().add(rdbtnRelatrioDrJuan);
		getContentPane().add(rdbtnRelatrioDrKalil);
		getContentPane().add(rdbtnRelatrioGeralDe);
		getContentPane().add(rdbtnRelatrioDeMovimentos);
		getContentPane().add(rdbtnQuantidadeMensalDe);
		getContentPane().add(rdbtnQuantidadeDeProcedimentos);
		getContentPane().add(rdbtnQTDProcedimentosPorExames);
		getContentPane().add(btnPrximo);
		
		

		

		btnPrximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		

	}
}
