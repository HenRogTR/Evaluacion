package pe.edu.unu.evaluacion.ejb;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.edu.unu.evaluacion.exception.SemestreException;
import pe.edu.unu.evaluacion.pojo.Semestre;

public class SemestreEjbImpl implements SemestreEjb {

    private final Logger wlLogger = LoggerFactory.getLogger(this.getClass().getName());

    private String msjTx;

    private Session session = null;
    private Transaction transaction = null;

    private Semestre semestre;
    private List<Semestre> semestreLista;

    @Override
    public void crear() throws SemestreException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void leerPorId(int idSemestre) throws SemestreException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void leerTodos() throws SemestreException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar() throws SemestreException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public List<Semestre> getSemestreLista() {
        return semestreLista;
    }

    public void setSemestreLista(List<Semestre> semestreLista) {
        this.semestreLista = semestreLista;
    }

}
