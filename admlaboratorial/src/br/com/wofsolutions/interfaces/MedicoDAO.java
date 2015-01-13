package br.com.wofsolutions.interfaces;

import java.util.List;

import br.com.wofsolutions.dominio.Medico;

public interface MedicoDAO {
	/**
	 * 
	 * @param medico
	 * @return
	 */
	public boolean salvar(Medico medico);
	public boolean excluir(Medico medico);
	
	public Medico getMedicoPeloNome(String nome);
	
	public List<Medico> getTotosMedicos();
	public List<String> getTotosMedicosAsString();
	public List<String> getTotosMedicosEquipeAsString(Integer exameId);
	public List<String> getTotosMedicosEquipeAsString2();
	public List<String> getTotosMedicosSolicitanteAsString();
	public List<Medico> getTotosMedicosPelaPesquisa(String pesq);
	public List<Medico> getTotosMedicosSolicitante();
	public List<Medico> getTotosMedicosEquipe();
}
