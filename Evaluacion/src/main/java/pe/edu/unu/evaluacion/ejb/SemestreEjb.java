package pe.edu.unu.evaluacion.ejb;

import java.util.List;
import javax.ejb.Local;

import pe.edu.unu.evaluacion.exception.SemestreException;
import pe.edu.unu.evaluacion.pojo.Semestre;

@Local
public interface SemestreEjb {

    public void crear() throws SemestreException, Exception;

    public void leerPorId(int idSemestre) throws SemestreException, Exception;

    public void leerTodos() throws SemestreException, Exception;

    public void actualizar() throws SemestreException, Exception;

    public Semestre getSemestre();

    public void setSemestre(Semestre semestre);

    public List<Semestre> getSemestreLista();

    public void setSemestreLista(List<Semestre> listaSemestre);

}
