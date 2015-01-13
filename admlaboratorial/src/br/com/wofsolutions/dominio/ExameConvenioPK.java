package br.com.wofsolutions.dominio;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.FilterJoinTable;

@Embeddable
public class ExameConvenioPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "exame_id", referencedColumnName = "exame_id")
	private Exame exame = new Exame();
	
	@ManyToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "convenio_id", referencedColumnName = "convenio_id")
	private Convenio convenio = new Convenio();
	
	

	public ExameConvenioPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExameConvenioPK(Exame exame, Convenio convenio) {
		super();
		this.exame = exame;
		this.convenio = convenio;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
}
