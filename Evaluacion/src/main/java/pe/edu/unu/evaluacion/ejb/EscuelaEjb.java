package pe.edu.unu.evaluacion.ejb;

import java.util.List;

import javax.ejb.Local;

import pe.edu.unu.evaluacion.exception.EscuelaException;
import pe.edu.unu.evaluacion.pojo.Escuela;

@Local
public interface EscuelaEjb {

	public void crear() throws Exception;

	public void leerPorId(int idEscuela) throws Exception;

	public void leerTodos() throws EscuelaException;

	public void actualizar(int idEscuela, String idEscuela2,
			String nombreEscuela, String abreviatura, String descripcion,
			String registro, int idFacultad) throws EscuelaException, Exception;

	public Escuela getEscuela();

	public void setEscuela(Escuela escuela);

	public List<Escuela> getEscuelaLista();

	public void setEscuelaLista(List<Escuela> escuelaLista);
}
