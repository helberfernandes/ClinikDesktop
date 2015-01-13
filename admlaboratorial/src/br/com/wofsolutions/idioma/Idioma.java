package br.com.wofsolutions.idioma;

import java.util.Locale;
import java.util.ResourceBundle;

public class Idioma {
	private static  ResourceBundle resourceBundle;
	
	static{
		resourceBundle=ResourceBundle.getBundle("br.com.wofsolutions.idioma.mensagens", new Locale("pt","BR"));
	}
	
	public static String getMensagem(IdiomaKey idiomaKey){
		return resourceBundle.getString(idiomaKey.toString());
	}
}
