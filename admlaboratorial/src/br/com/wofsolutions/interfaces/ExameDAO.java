package br.com.wofsolutions.interfaces;

import java.util.List;

import br.com.wofsolutions.dominio.Exame;
import br.com.wofsolutions.dominio.ExameConvenio;

public interface ExameDAO {
	/**
	 * 
	 * @param cxame
	 * @return
	 */
	public boolean salvar(Exame cxame);
	public boolean excluir(Exame cxame);
	
	public Exame getExamePeloNome(String nome);
	
	public List<Exame> getTotosExames();
	public List<ExameConvenio> getTotosExamesConvenios();
	public List<String> getTotosExamesAsString();
	public List<Exame> getTotosExamesPelaPesquisa(String pesq);
	
}
