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
@Table(name="wof_medicos")
public class Medico {
  public static final int EQUIPE =0;
  public static final int SOLICITANTE =1;
	@Id
	@GeneratedValue
	@Column(name="medico_id")
	private Integer medicoId;
	@Column(length=80)
	private String nome;
	private boolean equipe=true;
	private boolean solicitante;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinTable(name="wof_exames_medicos",
	joinColumns=@JoinColumn(name="medico_id"),
	inverseJoinColumns=@JoinColumn(name="exame_id"))	
	private List<Exame> exames = new ArrayList<Exame>();
	@OneToMany(targetEntity=Atendimento.class)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "medico_id", referencedColumnName = "medico_id")
	private List<Atendimento> atendimentos = new ArrayList<Atendimento>();
	
	@OneToMany(targetEntity=Atendimento.class)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "solicitante_id", referencedColumnName = "medico_id")
	private List<Atendimento> atendimentosSolicitante = new ArrayList<Atendimento>();
	
	public Medico(String nome) {
		super();
		this.nome = nome;
	}
	public Medico() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getMedicoId() {
		return medicoId;
	}
	public void setMedicoId(Integer medicoId) {
		this.medicoId = medicoId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isEquipe() {
		return equipe;
	}
	public void setEquipe(boolean equipe) {
		this.equipe = equipe;
	}
	public boolean isSolicitante() {
		return solicitante;
	}
	public void setSolicitante(boolean solicitante) {
		this.solicitante = solicitante;
	}
	public List<Exame> getExames() {
		return exames;
	}
	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}
	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
	public List<Atendimento> getAtendimentosSolicitante() {
		return atendimentosSolicitante;
	}
	public void setAtendimentosSolicitante(List<Atendimento> atendimentosSolicitante) {
		this.atendimentosSolicitante = atendimentosSolicitante;
	}
	
	
	
}
