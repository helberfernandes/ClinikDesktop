package br.com.wofsolutions.interfaces;

import java.util.Date;
import java.util.List;

import br.com.wofsolutions.dominio.Atendimento;

public interface AtendimentoDAO {
	/**
	 * 
	 * @param atendimento
	 * @return
	 */
	public boolean salvar(Atendimento atendimento);
	public boolean excluir(Atendimento atendimento);
	
	public Atendimento getAtendimentoPeloNome(String nome);
	
	public List<Atendimento> getTotosAtendimentos();
	
	public List<Atendimento> getTotosAtendimentosPelaPesquisa(String pesq);
	public Atendimento getAtendimentoPeloNome(Atendimento atendimento);
}
