package pe.edu.unu.evaluacion.dao;

import org.hibernate.Session;

import pe.edu.unu.evaluacion.pojo.Administrador;

public interface AdministradorDao {

	public int crear(Session session, Administrador administrador) throws Exception;
}
