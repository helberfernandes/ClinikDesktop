package br.com.wofsolutions.interfaces;

import java.util.List;

import br.com.wofsolutions.dominio.Convenio;

public interface ConvenioDAO {
	/**
	 * 
	 * @param convenio
	 * @return
	 */
	public boolean salvar(Convenio convenio);
	public boolean excluir(Convenio convenio);
	
	public Convenio getConvenioPeloNome(String nome);
	
	public List<Convenio> getTotosConvenios();
	
	public List<Convenio> getTotosConveniosPelaPesquisa(String pesq);
	public List<String> getTotosConveniosAsString();
}
