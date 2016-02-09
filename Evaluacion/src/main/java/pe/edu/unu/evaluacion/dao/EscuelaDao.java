package pe.edu.unu.evaluacion.dao;

import java.util.List;

import org.hibernate.Session;

import pe.edu.unu.evaluacion.pojo.Escuela;

public interface EscuelaDao {

	public int crear(Session session, Escuela escuela) throws Exception;

	public Escuela leerId(Session session, Integer idEscuela) throws Exception;

	public List<Escuela> leerTodos(Session session) throws Exception;

	public void actualizar(Session session, Escuela escuela) throws Exception;
}