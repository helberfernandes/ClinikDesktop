package br.com.wofsolutions.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="wof_convenios")
public class Convenio {
	@Id
	@GeneratedValue
	@Column(name="convenio_id")
	private Integer convenioId;
	@Column(length=80)
	private String nome;
	@OneToMany(fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "convenio_id", referencedColumnName = "convenio_id")
	private List<ExameConvenio> exameConvenios = new ArrayList<ExameConvenio>(); 
	@OneToMany(fetch=FetchType.LAZY, targetEntity=Atendimento.class)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "convenio_id", referencedColumnName = "convenio_id")
	private List<Atendimento> atendimentos = new ArrayList<Atendimento>();
	
	
	
	public Integer getConvenioId() {
		return convenioId;
	}
	public void setConvenioId(Integer convenioId) {
		this.convenioId = convenioId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<ExameConvenio> getExameConvenios() {
		return exameConvenios;
	}
	public void setExameConvenios(List<ExameConvenio> exameConvenios) {
		this.exameConvenios = exameConvenios;
	}
	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}
	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
	
	
}
