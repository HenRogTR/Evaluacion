package pe.edu.unu.evaluacion.dao;

import org.hibernate.Session;

import pe.edu.unu.evaluacion.pojo.Administrador;

public class AdministradorDaoImpl implements AdministradorDao {

	@Override
	public int crear(Session session, Administrador administrador) throws Exception {
		int codigo = (int) session.save(administrador);
		return codigo;
	}

}
