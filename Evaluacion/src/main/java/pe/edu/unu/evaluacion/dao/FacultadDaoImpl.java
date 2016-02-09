package pe.edu.unu.evaluacion.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pe.edu.unu.evaluacion.pojo.Facultad;

public class FacultadDaoImpl implements FacultadDao {

	@Override
	public int registrar(Session session, Facultad facultad) throws Exception {
		int codigo = (int) session.save(facultad);
        return codigo;
	}

	@Override
	public Facultad leerId(Session session, Integer idFacultad) throws Exception {
		return (Facultad)session.get(Facultad.class, idFacultad);
	}

	@Override
	public List<Facultad> listar(Session session) throws Exception {
		String hql = "from Facultad";
        Query q = session.createQuery(hql);
        return (List<Facultad>) q.list();
	}

	@Override
	public boolean actualizar(Session session, Facultad facultad) throws Exception {
		session.update(facultad);
		return true;
	}

}
