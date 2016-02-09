package pe.edu.unu.evaluacion.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pe.edu.unu.evaluacion.pojo.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

	@Override
	public Usuario getByUsuario(Session session, String usuario) {
		String hql = "from Usuario u where u.usuario= :usuario";
        Query q = session.createQuery(hql)
                .setString("usuario", usuario);
        return (Usuario) q.uniqueResult();
	}

	@Override
	public int registrar(Session session, Usuario usuario) throws Exception {
		int codigo = (int) session.save(usuario);
		return codigo;
	}

	@Override
	public List<Usuario> leerTodos(Session session) throws Exception {
		String hql = "from Usuario";
        Query q = session.createQuery(hql);
        return (List<Usuario>) q.list();
        
    }

}
