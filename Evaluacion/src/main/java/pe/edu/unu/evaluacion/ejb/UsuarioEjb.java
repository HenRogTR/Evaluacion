package pe.edu.unu.evaluacion.ejb;

import java.util.List;

import javax.ejb.Local;

import pe.edu.unu.evaluacion.bean.TransaccionBean;
import pe.edu.unu.evaluacion.exception.UsuarioException;
import pe.edu.unu.evaluacion.pojo.Usuario;

@Local
public interface UsuarioEjb {

	public void getIniciarSesion(TransaccionBean bean) throws UsuarioException;
	public void setPrimeraVez(TransaccionBean bean) throws UsuarioException;	
	public Usuario leerPorUsuario(TransaccionBean bean, String usuario) throws UsuarioException;
	public List<Usuario> leerTodos(TransaccionBean bean) throws UsuarioException;
	
	public Usuario getUsuario();
	public void setUsuario(Usuario usuario);
	public List<Usuario> getUsuarioList();
	public void getUsuarioLista(List<Usuario> usuarioLista);
}
