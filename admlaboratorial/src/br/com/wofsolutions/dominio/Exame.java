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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="wof_exames")
public class Exame {
	@Id
	@GeneratedValue
	@Column(name="exame_id")
	private Integer exameId;
	@Column(length=80)
	private String nome;
	
	private double honorarios;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	@JoinTable(name="wof_exames_medicos",
	joinColumns=@JoinColumn(name="exame_id"),
	inverseJoinColumns=@JoinColumn(name="medico_id"))	
	private List<Medico> medicos = new ArrayList<Medico>();
	
	
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "exame_id", referencedColumnName = "exame_id")
	private List<ExameConvenio> exameConvenios = new ArrayList<ExameConvenio>();
	
	public Integer getExameId() {
		return exameId;
	}
	public void setExameId(Integer exameId) {
		this.exameId = exameId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Medico> getMedicos() {
		return medicos;
	}
	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}
	public List<ExameConvenio> getExameConvenios() {
		return exameConvenios;
	}
	public void setExameConvenios(List<ExameConvenio> exameConvenios) {
		this.exameConvenios = exameConvenios;
	}
	public double getHonorarios() {
		return honorarios;
	}
	public void setHonorarios(double honorarios) {
		this.honorarios = honorarios;
	}

	
}
