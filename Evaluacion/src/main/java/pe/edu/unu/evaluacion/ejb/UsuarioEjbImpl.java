package pe.edu.unu.evaluacion.ejb;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.edu.unu.evaluacion.bean.TransaccionBean;
import pe.edu.unu.evaluacion.dao.AdministradorDao;
import pe.edu.unu.evaluacion.dao.AdministradorDaoImpl;
import pe.edu.unu.evaluacion.dao.UsuarioDao;
import pe.edu.unu.evaluacion.dao.UsuarioDaoImpl;
import pe.edu.unu.evaluacion.exception.UsuarioException;
import pe.edu.unu.evaluacion.pojo.Administrador;
import pe.edu.unu.evaluacion.pojo.Usuario;
import pe.edu.unu.evaluacion.util.HibernateUtil;
import pe.edu.unu.evaluacion.util.Constantes;
import pe.edu.unu.evaluacion.util.UtilMD5;
import pe.edu.unu.evaluacion.util.UtilRegistro;
import pe.edu.unu.evaluacion.util.UtilSesion;

public class UsuarioEjbImpl implements UsuarioEjb {

	private static Logger logger = Logger.getLogger(UsuarioEjbImpl.class);
	
	private Session session = null;
	private Transaction transaction = null;
	
	private Usuario usuario;
	private List<Usuario> usuarioLista;
	
	public UsuarioEjbImpl() {
		this.usuario = new Usuario();
	}
	
	@Override
	public void getIniciarSesion(TransaccionBean bean) throws UsuarioException {
		String msjTx= bean.getMsgTx().concat("[iniciarSesion] ");
		try{
			UsuarioDao usuarioDao = new UsuarioDaoImpl();
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			Usuario usuario = usuarioDao.getByUsuario(this.session, this.usuario.getUsuario());
			
			if(usuario != null){
				if(!usuario.getContrasenia().equals(UtilMD5.md5(this.usuario.getContrasenia()))){
					logger.warn(msjTx + "El usuario:" +this.usuario.getUsuario() + " ha ingresado una contraseña incorrecta");
					String cod = "USUARIO0001";
					String msg = "El usuario %s ha ingresado una contraseña incorrecta.";
					throw new UsuarioException(cod, String.format(msg, this.usuario.getUsuario()));
				}
			}else{
				logger.warn(msjTx + "No existe usuario:" +this.usuario.getUsuario());
				String cod = "USUARIO0002";
				String msg = "El usuario %s no está registrado.";
				throw new UsuarioException(cod, String.format(msg, this.usuario.getUsuario()));
			}
			this.usuario = usuario;
			this.transaction.commit();
			
		}catch(UsuarioException ue){
			throw ue;
			
		}catch(Exception e){
			logger.error(msjTx + "Error al obtener usuario: "+ e.getMessage(), e);
			if (this.transaction != null) {
				logger.info(msjTx + "Realizando rollback");
				try{
					this.transaction.rollback();
				}catch(Exception e1){
					logger.error(msjTx + "Error al realizar rollback: " + e1.getMessage(), e1);
				}
            }
			String cod = "USUARIO1001";
			String msg = "%s, error: %s";
			throw new UsuarioException(cod, String.format(msg, "Error al obtener usuario", "contacte al administrador indicando idTx=" + bean.getIdTx() ));
			
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
	}
	
	@Override
	public void setPrimeraVez(TransaccionBean bean) throws UsuarioException {
		String msgTx= bean.getMsgTx().concat("[setPrimeraVez] ");
		try{
			UsuarioDao usuarioDao = new UsuarioDaoImpl();
			AdministradorDao administradorDao = new AdministradorDaoImpl();
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			String administrador[] = Constantes.TABLA_ADMINISTRADOR_PRIMER_USO;
			String usuario[] = Constantes.TABLA_USARIO_PRIMER_USO;
			
			Usuario usuarioAdmin = usuarioDao.getByUsuario(this.session, usuario[2]);
			if(usuarioAdmin != null){
				logger.info(msgTx + "Ya se ha registrado datos de primer uso de aplicación.");								
				throw new UsuarioException(
						"USUARIO0003", 
						"Ya se ha registrado datos de primer uso de aplicación."
						);
			}
			
			this.usuario.setIdUsuario2(usuario[0]);
			this.usuario.setRol(usuario[1]);
			this.usuario.setUsuario(usuario[2]);
			this.usuario.setContrasenia(UtilMD5.md5(usuario[3]));
			this.usuario.setRegistro(UtilRegistro.getRegistro(Constantes.ESTADO_UNO, bean.getIdTx(), usuario[0]));
			
			int idUsuario = usuarioDao.registrar(this.session, this.usuario);
			this.usuario.setIdUsuario(idUsuario);
			
			Administrador administrador2 = new Administrador();
			
			administrador2.setUsuario(this.usuario);
			administrador2.setNombres(administrador[0]);
			administrador2.setApellidoPaterno(administrador[0]);
			administrador2.setApellidoMaterno(administrador[0]);
			administrador2.setSexo(administrador[1].equals(Constantes.NUMERO_UNO));
			administrador2.setDni(administrador[2]);
			administrador2.setRegistro(UtilRegistro.getRegistro(Constantes.ESTADO_UNO, bean.getIdTx(), usuario[0]));
			
			int idAdministrador = administradorDao.crear(this.session, administrador2);
			this.transaction.commit();
			logger.info(msgTx + "Registro exitoso, idAdministrador=" + idAdministrador);
		}catch(UsuarioException ue){
			throw ue;
		}catch(Exception e){			
			logger.error(msgTx + "Error al registrar datos de primer uso: "+ e.getMessage(), e);			
			if (this.transaction != null) {				
				try{
					this.transaction.rollback();
				}catch(Exception e1){
					logger.error(msgTx + "Error al realizar rollback.");
				}
            }
			String cod = "USUARIO1001";
			String msg = "%s, error: %s";
			throw new UsuarioException(
					cod, 
					String.format(
							msg, 
							"Error al registrar datos de primer uso", 
							e.getMessage()));
			
			
		}finally{
			if (this.session != null) {
				try{
	                this.session.close();
				}catch(Exception e){
					logger.error(msgTx + "Error al cerrar sesión.");
				}
                this.session = null;
            }
		}
	}	

	@Override
	public Usuario leerPorUsuario(TransaccionBean bean, String usuario) 
			throws UsuarioException {
		
		String msgTx= bean.getMsgTx().concat("[leerPorUsuario] ");		
		
		Usuario usuarioObj = null;		
		try{
			UsuarioDao usuarioDao = new UsuarioDaoImpl();
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			usuarioObj = usuarioDao.getByUsuario(this.session, usuario);
			
			if(usuarioObj == null){
				logger.info(msgTx + "No existe usuario: " + usuario);
				throw new UsuarioException("1", String.format("El usuario %s no está registrado.", usuario));
			}
			logger.info(msgTx + "Usuario leído correctamente");
			this.transaction.commit();
			
			UtilSesion.setSesion(msgTx, usuarioObj);
			
		}catch(UsuarioException ue){
			throw ue;
		}catch(Exception e){
			logger.error(msgTx + "Error al leer usuario: "+ e.getMessage(), e);			
			if (this.transaction != null) {				
				try{
					this.transaction.rollback();
				}catch(Exception e1){
					logger.error(msgTx + "Error al realizar rollback.");
				}
            }
			
			throw new UsuarioException(
					"-1", String.format("%s, error: %s", 
							"Error al leer usuario", e.getMessage()));
		}finally{
			if (this.session != null) {
				try{
	                this.session.close();
				}catch(Exception e){
					logger.error(msgTx + "Error al cerrar sesión.");
				}
                this.session = null;
            }			
		}
		return usuarioObj;
	}

	@Override
	public List<Usuario> leerTodos(TransaccionBean bean)
			throws UsuarioException {
		String msgTx= bean.getMsgTx().concat("[leerTodos] ");
		List<Usuario> lista = null;
		
		try{
			
			logger.info(msgTx + "Inicio de método.");
			UsuarioDao usuarioDao = new UsuarioDaoImpl();
			
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			
			lista = usuarioDao.leerTodos(this.session);
			this.transaction = this.session.beginTransaction();
			
			this.transaction.commit();
			logger.info(msgTx + "Éxito al leer todos los usuarios");
		}catch(Exception e){
			logger.error(
					msgTx + "Error al leer los usuarios: "+ e.getMessage(), e);			
			if (this.transaction != null) {				
				try{
					this.transaction.rollback();
				}catch(Exception e1){
					logger.error(msgTx + "Error al realizar rollback.");
				}
            }			
			throw new UsuarioException(
					"-1", 
					String.format("%s, error: %s",	"Error al leer usuario", 
							e.getMessage()));
		}finally{
			if (this.session != null) {
				try{
	                this.session.close();
				}catch(Exception e){
					logger.error(msgTx + "Error al cerrar sesión.");
				}
                this.session = null;
			}
			logger.info(msgTx + "Fin de método.");
		}
		
		return lista;
	}	
	@Override
	public Usuario getUsuario() {
		return this.usuario;
	}

	@Override
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public List<Usuario> getUsuarioList() {
		return this.usuarioLista;
	}

	@Override
	public void getUsuarioLista(List<Usuario> usuarioLista) {
		this.usuarioLista = usuarioLista;
	}
	
}
