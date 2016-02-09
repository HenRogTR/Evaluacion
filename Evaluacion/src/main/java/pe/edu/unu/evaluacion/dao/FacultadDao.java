package pe.edu.unu.evaluacion.dao;

import java.util.List;

import org.hibernate.Session;

import pe.edu.unu.evaluacion.pojo.Facultad;

public interface FacultadDao {

	public int registrar(Session session, Facultad facultad) throws Exception;
	
	public Facultad leerId(Session session, Integer idFacultad) throws Exception;
	
	public List<Facultad> listar(Session session) throws Exception;
	
	public boolean actualizar(Session session, Facultad facultad) throws Exception;
}
