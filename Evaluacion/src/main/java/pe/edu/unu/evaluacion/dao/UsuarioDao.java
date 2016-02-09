package pe.edu.unu.evaluacion.dao;

import java.util.List;

import org.hibernate.Session;

import pe.edu.unu.evaluacion.pojo.Usuario;

public interface UsuarioDao {
	
	public Usuario getByUsuario(Session session, String usuario);
	public int registrar(Session session, Usuario usuario) throws Exception;
	
	public List<Usuario> leerTodos(Session session) throws Exception;

}
