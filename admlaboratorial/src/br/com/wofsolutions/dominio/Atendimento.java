package br.com.wofsolutions.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="wof_atendimento")
public class Atendimento {
	@Id
	@GeneratedValue
	@Column(name="atendimento_id")
	private Integer atendimentoId;
	@Temporal(TemporalType.DATE)
	@Column(name="data_lancamento")
	private Date dataLancamento = new Date();
	@Column(length=80)
	private String paciente;
	@Column(name="falta_guia")
	private boolean faltaGuia=false;
	private boolean deletado=false;
	
	
	@ManyToOne
	@JoinColumn(name = "convenio_id", referencedColumnName = "convenio_id")
	private Convenio  convenio = new Convenio();
	@ManyToOne
	@JoinColumn(name = "exame_id", referencedColumnName = "exame_id")
	private Exame  exame = new Exame();
	
	@ManyToOne
	@JoinColumn(name = "medico_id", referencedColumnName = "medico_id")
	private Medico medico = new Medico();
	
	@ManyToOne
	@JoinColumn(name = "solicitante_id", referencedColumnName = "medico_id")
	private Medico solicitante = new Medico();
	
	private double valor;

	public Integer getAtendimentoId() {
		return atendimentoId;
	}

	public void setAtendimentoId(Integer atendimentoId) {
		this.atendimentoId = atendimentoId;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Medico getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Medico solicitante) {
		this.solicitante = solicitante;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isFaltaGuia() {
		return faltaGuia;
	}

	public void setFaltaGuia(boolean faltaGuia) {
		this.faltaGuia = faltaGuia;
	}
	public boolean isDeletado() {
		return deletado;
	}

	public void setDeletado(boolean deletado) {
		this.deletado = deletado;
	}

	
}
