package br.com.wofsolutions.mensagens;

import java.awt.Component;

import javax.swing.JOptionPane;

import br.com.wofsolutions.idioma.Idioma;
import br.com.wofsolutions.idioma.IdiomaKey;

public class Mensagens {

	
	public static void setMensagemSucessOuFalha(boolean resp, Component component){
		if(resp){
			JOptionPane.showMessageDialog(component, Idioma.getMensagem(IdiomaKey.WOF_MSG_OPERACAO_COM_SUCESSO), Idioma.getMensagem(IdiomaKey.WOF_TITULO_AVISO), JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(component, Idioma.getMensagem(IdiomaKey.WOF_MSG_OPERACAO_FALHOU), Idioma.getMensagem(IdiomaKey.WOF_TITULO_AVISO), JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void addInformationMenssage(Component component, String msg, String titulo){
		JOptionPane.showMessageDialog(component, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void addWarningMenssage(Component component, String msg, String titulo){
		JOptionPane.showMessageDialog(component, msg, titulo, JOptionPane.WARNING_MESSAGE);
	}
	
	
	public static void addErrorMenssage(Component component, String msg, String titulo){
		JOptionPane.showMessageDialog(component, msg, titulo, JOptionPane.ERROR_MESSAGE);
	}
	
	
	public static int showConfirmDialog(Component component, String msg){
		return JOptionPane.showConfirmDialog(component, msg,"",JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
	}
	
}
