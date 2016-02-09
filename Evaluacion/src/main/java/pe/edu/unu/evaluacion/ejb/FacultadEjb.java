package pe.edu.unu.evaluacion.ejb;

import java.util.List;

import javax.ejb.Local;

import pe.edu.unu.evaluacion.exception.FacultadException;
import pe.edu.unu.evaluacion.pojo.Facultad;

@Local
public interface FacultadEjb {

	public int crear() throws Exception;	
	public List<Facultad> leerTodos() throws FacultadException;
	public Facultad leerPorId(int idFacultad) throws Exception;
	public boolean actualizar() throws Exception;
	
	public Facultad getFacultad();
	public void setFacultad(Facultad facultad);
	public List<Facultad> getFacultadLista();
	public void setFacultadLista(List<Facultad> facultadLista);
	
}
