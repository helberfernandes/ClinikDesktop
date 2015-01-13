package br.com.wofsolutions.interfaces;

import java.util.List;

import br.com.wofsolutions.dominio.Usuario;

public interface UsuarioDAO {
	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public boolean salvar(Usuario usuario);
	public boolean excluir(Usuario usuario);
	/**
	 * 
	 * @param login
	 * @return
	 */
	public Usuario getUsuarioPeloLogin(String login);
	
	public List<Usuario> getTotosUsuarios();
	
	public List<Usuario> getTotosUsuariosPelaPesquisa(String pesq);
}
