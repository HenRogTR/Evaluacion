package pe.edu.unu.evaluacion.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pe.edu.unu.evaluacion.pojo.Escuela;

public class EscuelaDaoImpl implements EscuelaDao {

	@Override
	public int crear(Session session, Escuela escuela) throws Exception {
		int codigo = (int) session.save(escuela);
        return codigo;
	}

	@Override
	public Escuela leerId(Session session, Integer idEscuela) throws Exception {
		return (Escuela)session.get(Escuela.class, idEscuela);
	}
	
	@Override
	public List<Escuela> leerTodos(Session session) throws Exception {
		String hql = "from Escuela as e left join fetch e.facultad";
        Query q = session.createQuery(hql);
        List<Escuela> list = (List<Escuela>) q.list();
		return list;
	}

	@Override
	public void actualizar(Session session, Escuela escuela) throws Exception {
		session.update(escuela);		
	}

}
