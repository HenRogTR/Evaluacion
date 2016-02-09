package pe.edu.unu.evaluacion.ejb;

import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.edu.unu.evaluacion.dao.FacultadDao;
import pe.edu.unu.evaluacion.dao.FacultadDaoImpl;
import pe.edu.unu.evaluacion.exception.FacultadException;
import pe.edu.unu.evaluacion.pojo.Facultad;
import pe.edu.unu.evaluacion.util.HibernateUtil;

@Stateless
public class FacultadEjbImpl implements FacultadEjb {

	private static Logger logger = Logger.getLogger(FacultadEjbImpl.class);
	
	private String msjTx;
	
	private Session session = null;
	private Transaction transaction = null;
	
	private Facultad facultad;
	private List<Facultad> facultadLista;
	
	public FacultadEjbImpl() {
		facultad = new Facultad();
	}
	
	public FacultadEjbImpl(String msjTx) {
		this.msjTx = msjTx;
		facultad = new Facultad();
	}
	
	@Override
	public int crear() throws Exception{
		
		String msjTx= this.msjTx + "[crear] ";
		logger.info(msjTx + "Datos a registrar: "
							+"idFacultad2=" + facultad.getIdFacultad2()
							+", nombreFacultad=" + facultad.getNombreFacultad()
							+", registro=" +facultad.getRegistro());
		int id = 0;
		try{
			FacultadDao facultadDao = new FacultadDaoImpl();			
			
			session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            id = facultadDao.registrar(session, facultad);
            
            transaction.commit();
            
            logger.info(msjTx + "idFacultad=" +id);
		}catch(Exception e){
			
			logger.error(msjTx + "Error al registar: "+e.getMessage(),e);
			
			if(transaction != null){
				transaction.rollback();
			}
			
			throw e;
			
		}finally{
			if(session != null){
				session.close();
				session= null;
			}			
			transaction = null;
		}
		
		return id;
	}

	@Override
	public List<Facultad> leerTodos() throws FacultadException{
		String msjTx= this.msjTx + "[leerTodos] ";
		
		FacultadDao facultadDao = new FacultadDaoImpl();
		try{			
			this.session = HibernateUtil.getSessionFactory().openSession();
	        this.transaction = this.session.beginTransaction();
	        
	        this.facultadLista = facultadDao.listar(this.session);
	        
	        this.transaction.commit();
	        
	        logger.info(msjTx + "Datos de salida:");
	        
	        for(Facultad facultad: this.facultadLista){
	        	logger.info(msjTx + "idFacultad=" + facultad.getIdFacultad() 
	        			+ ", idFacultad2=" + facultad.getIdFacultad2() 
	        			+ ", nombreFacultad=" + facultad.getNombreFacultad()
	        			+ ", registro=" + facultad.getRegistro());
	        }
		}catch(Exception e){
			logger.error(msjTx + "Error al leerTodos: "+ e.getMessage(), e);			
			if (this.transaction != null) {				
				try{
					this.transaction.rollback();
				}catch(Exception e1){
					logger.error(msjTx + "Error al realizar rollback.");
				}
            }
			
			throw new FacultadException("-1", String.format("%s, error: %s", 
							"Error al leerTodos", e.getMessage()));
		}finally{
			if(session != null){
				session.close();
				session= null;
			}			
			transaction = null;
		}
		return this.facultadLista;
	}

	@Override
	public Facultad leerPorId(int idFacultad) throws Exception {
		String msjTx= this.msjTx + "[leerPorId] ";
		
		FacultadDao facultadDao = new FacultadDaoImpl();
		try{
			logger.info(msjTx + "Datos de entrada: "
					+"idFacultad=" + idFacultad);
			this.session = HibernateUtil.getSessionFactory().openSession();
	        this.transaction = this.session.beginTransaction();
	        
	        this.facultad = facultadDao.leerId(this.session, idFacultad);
	        
	        if(this.facultad == null){
	        	throw new FacultadException("1", "Facultad con id=" + idFacultad + " no encontrado.");
	        }
	        
	        logger.info(msjTx + "Datos de salida:");	        
	        
        	logger.info(msjTx + "idFacultad=" + this.facultad.getIdFacultad() 
        			+ ", idFacultad2=" + this.facultad.getIdFacultad2() 
        			+ ", nombreFacultad=" + this.facultad.getNombreFacultad()
        			+ ", registro=" + this.facultad.getRegistro());
	        
	        this.transaction.commit();
		
		}catch(FacultadException fe){
			logger.info(msjTx + fe.getMessage());			
			if (this.transaction != null) {				
				try{
					this.transaction.rollback();
				}catch(Exception e1){
					logger.error(msjTx + "Error al realizar rollback:" +e1);
				}
            }
			
			throw fe;
		}catch(Exception e){
			logger.error(msjTx + "Error al leerPorId: "+ e.getMessage(), e);			
			if (this.transaction != null) {				
				try{
					this.transaction.rollback();
				}catch(Exception e1){
					logger.error(msjTx + "Error al realizar rollback:" +e1);
				}
            }
			
			throw new FacultadException("-1", String.format("%s, error: %s", 
							"Error al leerPorId", e.getCause()));
		}finally{
			if(this.session != null){
				this.session.close();
				this.session= null;
			}			
			this.transaction = null;
		}
		return this.facultad;
	}

	@Override
	public boolean actualizar() throws Exception {
		String msjTx= this.msjTx + "[actualizar] ";
		logger.info(msjTx + "Datos a registrar: "
							+ "idFacultad=" + this.facultad.getIdFacultad()
							+ ", idFacultad2=" + this.facultad.getIdFacultad2()
							+ ", nombreFacultad=" + this.facultad.getNombreFacultad()
							+ ", registro=" + this.facultad.getRegistro());
		try{
			FacultadDao facultadDao = new FacultadDaoImpl();			
			
			this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            facultadDao.actualizar(this.session, this.facultad);
            
            this.transaction.commit();
            
            logger.info(msjTx + "facultad actualizada correctamente");
            
		}catch(Exception e){			
			logger.error(msjTx + "Error al actualizar: "+e.getMessage(),e);
			
			if (this.transaction != null) {
				logger.info(msjTx + "Realizando rollback");
				try{
					this.transaction.rollback();
				}catch(Exception e1){
					logger.error(msjTx + "Error al realizar rollback: " + e1.getMessage(), e1);
				}
            }
			
			throw e;
			
		}finally{
			if (this.session != null) {
				try{
	                this.session.close();
				}catch(Exception e){
					logger.error(msjTx + "Error al cerrar sesión.");
				}
                this.session = null;
            }
		}
		return true;
	}
	
	@Override
	public Facultad getFacultad() {
		// TODO Auto-generated method stub
		return this.facultad;
	}

	@Override
	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
		
	}

	@Override
	public List<Facultad> getFacultadLista() {
		// TODO Auto-generated method stub
		return this.facultadLista;
	}

	@Override
	public void setFacultadLista(List<Facultad> facultadLista) {
		this.facultadLista = facultadLista;
		
	}
}
