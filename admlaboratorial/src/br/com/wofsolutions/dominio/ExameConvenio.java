package br.com.wofsolutions.dominio;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="wof_exame_convenio")
public class ExameConvenio {

	
	@EmbeddedId
	private ExameConvenioPK exameConvenioPK = new ExameConvenioPK();
	
	
	private double valor;


	public ExameConvenio() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ExameConvenio(ExameConvenioPK exameConvenioPK, double valor) {
		super();
		this.exameConvenioPK = exameConvenioPK;
		this.valor = valor;
	}


	public ExameConvenioPK getExameConvenioPK() {
		return exameConvenioPK;
	}


	public void setExameConvenioPK(ExameConvenioPK exameConvenioPK) {
		this.exameConvenioPK = exameConvenioPK;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}


	
	
	
}
