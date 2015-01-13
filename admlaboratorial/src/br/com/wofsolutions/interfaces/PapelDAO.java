package br.com.wofsolutions.interfaces;

import java.util.List;

import br.com.wofsolutions.dominio.Papel;

public interface PapelDAO {
	public boolean salvar(Papel papel);
	
	public List<Papel> getTodosOsPapeis();
	
	public List<Papel> getTodosOsPapeisPelaPesquisa(String pesq);
	public Papel getPapelPeloNome(String nome);
}
