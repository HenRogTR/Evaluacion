package pe.edu.unu.evaluacion.ejb;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.edu.unu.evaluacion.dao.EscuelaDao;
import pe.edu.unu.evaluacion.dao.EscuelaDaoImpl;
import pe.edu.unu.evaluacion.exception.EscuelaException;
import pe.edu.unu.evaluacion.pojo.Escuela;
import pe.edu.unu.evaluacion.pojo.Facultad;
import pe.edu.unu.evaluacion.util.HibernateUtil;

@Stateless
public class EscuelaEjbImpl implements EscuelaEjb {

	private final Logger wlLogger = LoggerFactory.getLogger(this.getClass().getName());
	
	private String msjTx;
	
	private Session session = null;
	private Transaction transaction = null;
	
	private Escuela escuela;
	private List<Escuela> escuelaLista;
	
	public EscuelaEjbImpl() {
		this.escuela = new Escuela();
		this.escuela.setFacultad(new Facultad());
	}
	
	public EscuelaEjbImpl(String msjTx) {
		this.msjTx = msjTx;
		this.escuela = new Escuela();
		this.escuela.setFacultad(new Facultad());
	}

	@Override
	public void crear() throws Exception{
		String msjTx= this.msjTx + "[crear] ";
		try{
			wlLogger.info(msjTx + "Datos a registrar: idEscuela2={}, "
					+ "nombreEscuela={}, registro={}, idEscuela={}", 
					this.escuela.getIdEscuela2(), 
					this.escuela.getNombreEscuela(), this.escuela.getRegistro(), 
					this.escuela.getFacultad().getIdFacultad());
			
			EscuelaDao escuelaDao = new EscuelaDaoImpl();
			
			this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            int idEscuela = escuelaDao.crear(this.session, this.escuela);
            this.escuela.setIdEscuela(idEscuela);
            
            wlLogger.info(msjTx + "idEscuela=" +idEscuela);
            
            this.transaction.commit();			
		}catch(Exception e){
			wlLogger.error(msjTx + "Error al crear: "+ e.getMessage(), e);			
			if (this.transaction != null) {				
				try{
					this.transaction.rollback();
				}catch(Exception e1){
					wlLogger.error(msjTx + "Error al realizar rollback.");
				}
            }
			
			throw e;		
			
		}finally{
			if(this.session != null){
				this.session.close();
				this.session= null;
			}			
			this.transaction = null;
		}
	}

	@Override
	public void leerPorId(int idEscuela) throws Exception {
		String msjTx= this.msjTx + "[leerPorId] ";
		
		EscuelaDao escuelaDao = new EscuelaDaoImpl();
		try{
			wlLogger.info(msjTx + "Datos de entrada: "
					+"idEscuela=" + idEscuela);
			
			this.session = HibernateUtil.getSessionFactory().openSession();
	        this.transaction = this.session.beginTransaction();
	        
	        this.escuela = escuelaDao.leerId(this.session, idEscuela);
	        
	        if(this.escuela == null){
	        	throw new EscuelaException("1", "Escuela con id=" + idEscuela + " no encontrado.");
	        }
	        
	        wlLogger.info(msjTx + "Datos de salida:");	        
	        
	        wlLogger.info(msjTx + "idEscuela={}, idEscuela2={}, " 
					+ "nombreEscuela={}, registro={}, idFacultad={}, ", 
					escuela.getIdEscuela(),	escuela.getIdEscuela2(), 
					escuela.getNombreEscuela(), escuela.getRegistro(),
					escuela.getFacultad().getIdFacultad());
	        
	        this.transaction.commit();
		
		}catch(EscuelaException fe){
			wlLogger.info(msjTx + fe.getMessage());			
			if (this.transaction != null) {				
				try{
					this.transaction.rollback();
				}catch(Exception e1){
					wlLogger.error(msjTx + "Error al realizar rollback:" +e1);
				}
            }
			
			throw fe;
		}catch(Exception e){
			wlLogger.error(msjTx + "Error al leerPorId: "+ e.getMessage(), e);			
			if (this.transaction != null) {				
				try{
					this.transaction.rollback();
				}catch(Exception e1){
					wlLogger.error(msjTx + "Error al realizar rollback:" +e1);
				}
            }
			
			throw new EscuelaException("-1", String.format("%s, error: %s", 
							"Error al leerPorId", e.getCause()));
		}finally{
			if(this.session != null){
				this.session.close();
				this.session= null;
			}			
			this.transaction = null;
		}
		
	}
	
	@Override
	public void leerTodos() throws EscuelaException {
		
		String msjTx= this.msjTx + "[leerTodos] ";
		
		EscuelaDao escuelaDao = new EscuelaDaoImpl();
		try{
			this.session = HibernateUtil.getSessionFactory().openSession();
	        this.transaction = this.session.beginTransaction();
	        
	        this.escuelaLista = escuelaDao.leerTodos(this.session);
			
	        wlLogger.info(msjTx + "Datos de salida:");
			
			for(Escuela escuela: this.escuelaLista){
				wlLogger.info(msjTx + "idEscuela={}, idEscuela2={}, " 
						+ "nombreEscuela={}, registro={}, idFacultad={}, ", 
						escuela.getIdEscuela(),
						escuela.getIdEscuela2(), escuela.getNombreEscuela(),
						escuela.getRegistro(),
						escuela.getFacultad().getIdFacultad());
			}	        
	        
			this.transaction.commit();	        
		}catch(Exception e){
			wlLogger.error(msjTx + "Error al leerTodos: "+ e.getMessage(), e);			
			if (this.transaction != null) {				
				try{
					this.transaction.rollback();
				}catch(Exception e1){
					wlLogger.error(msjTx + "Error al realizar rollback.");
				}
            }
			
			throw new EscuelaException("-1", String.format("%s, error: %s", 
							"Error al leerTodos: ", e.getMessage()));
		}finally{
			if(this.session != null){
				this.session.close();
				this.session= null;
			}			
			this.transaction = null;
		}
	}
	
	@Override
	public void actualizar(int idEscuela, String idEscuela2,
			String nombreEscuela, String abreviatura, String descripcion,
			String registro, int idFacultad)
			throws EscuelaException, Exception {
		
		String msjTx= this.msjTx + "[actualizar] ";
		try{
			
			EscuelaDao escuelaDao = new EscuelaDaoImpl();			
			
			this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            this.escuela = escuelaDao.leerId(this.session, idEscuela);
            
            if(this.escuela == null){
	        	throw new EscuelaException("1", "Escuela con id=" + idEscuela + " no encontrado.");
	        }
            
            this.escuela.setIdEscuela2(idEscuela2);
            this.escuela.setNombreEscuela(nombreEscuela);
            this.escuela.setAbreviatura(abreviatura);
            this.escuela.setDescripcion(descripcion);
            this.escuela.setRegistro(registro + "_" + this.escuela.getRegistro());
            this.escuela.getFacultad().setIdFacultad(idFacultad);
            
			wlLogger.info(msjTx + "Datos a actualizar: idEscuela={}, "
					+ "idEscuela2={}, nombreEscuela={}, registro={}, "
					+ "idFacultad={}", this.escuela.getIdEscuela(),
					this.escuela.getIdEscuela2(), 
					this.escuela.getNombreEscuela(), this.escuela.getRegistro(), 
					this.escuela.getFacultad().getIdFacultad());
			
            escuelaDao.actualizar(this.session, this.escuela);
            
            this.transaction.commit();
            
            wlLogger.info(msjTx + "escuela actualizada correctamente");         
		}catch(EscuelaException fe){
			wlLogger.info(msjTx + fe.getMessage());			
			if (this.transaction != null) {				
				try{
					this.transaction.rollback();
				}catch(Exception e1){
					wlLogger.error(msjTx + "Error al realizar rollback:" +e1);
				}
            }			
			throw fe;
			
		}catch(Exception e){			
			wlLogger.error(msjTx + "Error al actualizar: "+e.getMessage(),e);
			
			if (this.transaction != null) {
				wlLogger.info(msjTx + "Realizando rollback");
				try{
					this.transaction.rollback();
				}catch(Exception e1){
					wlLogger.error(msjTx + "Error al realizar rollback: " + e1.getMessage(), e1);
				}
            }			
			throw e;
			
		}finally{
			if (this.session != null) {
				try{
	                this.session.close();
				}catch(Exception e){
					wlLogger.error(msjTx + "Error al cerrar sesión.");
				}
                this.session = null;
            }
		}		
	}
	
	public Escuela getEscuela() {
		return escuela;
	}

	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}

	public List<Escuela> getEscuelaLista() {
		return escuelaLista;
	}

	public void setEscuelaLista(List<Escuela> escuelaLista) {
		this.escuelaLista = escuelaLista;
	}

}
