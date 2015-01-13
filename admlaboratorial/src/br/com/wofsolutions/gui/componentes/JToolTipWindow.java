package br.com.wofsolutions.gui.componentes;

import javax.swing.JInternalFrame;

import java.awt.GridLayout;

import javax.swing.*;

class JToolTipWindow extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	public JToolTipWindow() {
		super("Encontrados", true, true, true, true);
		this.setSize(100, 100);
	}

	public void add(String[] dados) {
		getContentPane().removeAll();
		setLayout(new GridLayout(dados.length, 1));
		for (int i = 0; i < dados.length; i++)
			add(new JLabel(dados[i]));

	}
}
