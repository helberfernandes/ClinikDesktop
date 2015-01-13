package br.com.wofsolutions.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="wof_papeis")
public class Papel {

	@Id
	@GeneratedValue
	@Column(name="papel_id")
	private Integer papelId;
	@Column(length=80)
	private String nome;
	@Column(name="pode_cadastrar_usuario")
	private boolean podeCadastrarUsuaio=false;
	@Column(name="pode_definir_papeis")
	private boolean podeDefinirPapeis=false;
	@Column(name="pode_ver__relatorio_de_Atendimentos")
	private boolean podeVerRelatorioDeAtendimentos=false;
	@Column(name="pode_ver__relatorio_do_juan")
	private boolean podeVerRelatorioDoJuan=false;
	@Column(name="pode_ver__relatorio_do_kalil")
	private boolean podeVerRelatorioDoKalil=false;

	@Column(name="pode_ver__relatorio__exames_realizados_por_medicos")
	private boolean podeVerExamesRealizadosPorMedicos=false;
	
	@Column(name="pode_ver__relatorio_qtd_procedimentos_por_exames")
	private boolean podeVerQTDProcedimentoPorExames=false;
	
	@Column(name="pode_ver__relatorio_qtd_mensal_procedimentos_por_exames")
	private boolean podeVerQTDMensalProcedimentoPorExames=false;
	
	@Column(name="pode_ver__relatorio_qtd_procedimentos_por_solicitante")
	private boolean podeVerQTDProcedimentosPorSolicitante=false;
	
	@Column(name="pode_ver__relatorio_total_procedimentos_por_medicos")
	private boolean podeVerTotalProcedimentosPorMedico=false;

	@OneToMany
	@JoinColumn(name = "papel_id", referencedColumnName = "papel_id")
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public Integer getPapelId() {
		return papelId;
	}
	public void setPapelId(Integer papelId) {
		this.papelId = papelId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	public boolean isPodeCadastrarUsuaio() {
		return podeCadastrarUsuaio;
	}
	public void setPodeCadastrarUsuaio(boolean podeCadastrarUsuaio) {
		this.podeCadastrarUsuaio = podeCadastrarUsuaio;
	}
	public boolean isPodeDefinirPapeis() {
		return podeDefinirPapeis;
	}
	public void setPodeDefinirPapeis(boolean podeDefinirPapeis) {
		this.podeDefinirPapeis = podeDefinirPapeis;
	}
	public boolean isPodeVerRelatorioDeAtendimentos() {
		return podeVerRelatorioDeAtendimentos;
	}
	public void setPodeVerRelatorioDeAtendimentos(
			boolean podeVerRelatorioDeAtendimentos) {
		this.podeVerRelatorioDeAtendimentos = podeVerRelatorioDeAtendimentos;
	}
	public boolean isPodeVerRelatorioDoJuan() {
		return podeVerRelatorioDoJuan;
	}
	public void setPodeVerRelatorioDoJuan(boolean podeVerRelatorioDoJuan) {
		this.podeVerRelatorioDoJuan = podeVerRelatorioDoJuan;
	}
	public boolean isPodeVerRelatorioDoKalil() {
		return podeVerRelatorioDoKalil;
	}
	public void setPodeVerRelatorioDoKalil(boolean podeVerRelatorioDoKalil) {
		this.podeVerRelatorioDoKalil = podeVerRelatorioDoKalil;
	}
	
	public boolean isPodeVerExamesRealizadosPorMedicos() {
		return podeVerExamesRealizadosPorMedicos;
	}
	public void setPodeVerExamesRealizadosPorMedicos(
			boolean podeVerExamesRealizadosPorMedicos) {
		this.podeVerExamesRealizadosPorMedicos = podeVerExamesRealizadosPorMedicos;
	}
	public boolean isPodeVerQTDProcedimentoPorExames() {
		return podeVerQTDProcedimentoPorExames;
	}
	public void setPodeVerQTDProcedimentoPorExames(
			boolean podeVerQTDProcedimentoPorExames) {
		this.podeVerQTDProcedimentoPorExames = podeVerQTDProcedimentoPorExames;
	}
	public boolean isPodeVerQTDProcedimentosPorSolicitante() {
		return podeVerQTDProcedimentosPorSolicitante;
	}
	public void setPodeVerQTDProcedimentosPorSolicitante(
			boolean podeVerQTDProcedimentosPorSolicitante) {
		this.podeVerQTDProcedimentosPorSolicitante = podeVerQTDProcedimentosPorSolicitante;
	}
	public boolean isPodeVerTotalProcedimentosPorMedico() {
		return podeVerTotalProcedimentosPorMedico;
	}
	public void setPodeVerTotalProcedimentosPorMedico(
			boolean podeVerTotalProcedimentosPorMedico) {
		this.podeVerTotalProcedimentosPorMedico = podeVerTotalProcedimentosPorMedico;
	}
	
	
	public boolean isPodeVerQTDMensalProcedimentoPorExames() {
		return podeVerQTDMensalProcedimentoPorExames;
	}
	public void setPodeVerQTDMensalProcedimentoPorExames(
			boolean podeVerQTDMensalProcedimentoPorExames) {
		this.podeVerQTDMensalProcedimentoPorExames = podeVerQTDMensalProcedimentoPorExames;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((papelId == null) ? 0 : papelId.hashCode());
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
		Papel other = (Papel) obj;
		if (papelId == null) {
			if (other.papelId != null)
				return false;
		} else if (!papelId.equals(other.papelId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Papel [papelId=" + papelId + "]";
	}	
}
