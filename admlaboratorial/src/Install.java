import br.com.wofsolutions.dao.UsuarioDAOImpl;
import br.com.wofsolutions.dominio.Usuario;
import br.com.wofsolutions.util.HibernateUtil;
import br.com.wofsolutions.util.WofUtil;


public class Install {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HibernateUtil.geraBanco();
       UsuarioDAOImpl daoImpl = new UsuarioDAOImpl();
       
       Usuario usuario = new Usuario();
       
       usuario.setNome("Administrador");
       usuario.setLogin("admin");
       usuario.setSenha(WofUtil.md5("admin"));
       daoImpl.salvar(usuario);
	}

}
