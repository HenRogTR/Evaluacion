package pe.edu.unu.evaluacion.util;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import pe.edu.unu.evaluacion.bean.CacheUsuarioBean;
import pe.edu.unu.evaluacion.pojo.Usuario;

public class UtilSesion {
	private static Logger logger = Logger.getLogger(UtilSesion.class);
	
	private static Hashtable<String, CacheUsuarioBean> cacheUsuario;
	
	/**
     * Valida si una sesión está activa o ha terminado por superar el máximo tiempo permitido.
     * @param msgTx
     * @param usuario
     * @return
     */
    public static Usuario obtenerUsuarioCache(String msgTx, String usuario){
    	if(cacheUsuario == null){
    		cacheUsuario = new Hashtable<String, CacheUsuarioBean>();
    	}    	
    	CacheUsuarioBean cacheUsuarioBean = cacheUsuario.get(usuario);
    	if(cacheUsuarioBean == null){
    		logger.warn(msgTx + "No hay datos en caché para usuario=" + usuario);
    		return null;
    	}
    	
    	if( ( Calendar.getInstance().getTimeInMillis() - cacheUsuarioBean.getLastUpdate() ) >= ParametrosExternos.TIEMPO_VIDA_SESION_USUARIO ){
			logger.info(msgTx + "Ha terminado la sesión para el usuario=" + usuario);
			cacheUsuario.remove(usuario);
			logger.info(msgTx + "Se ha removido datos de usuario=" + usuario);
			return null;
			
		}else{
			logger.info(msgTx + "Sesión válida, se obtuvo datos de caché.");
			return cacheUsuarioBean.getUsuario();
		}
    }
    
    /**
     * Registra/graba los datos de un usuario en caché
     * @param msgTx
     * @param usuario
     */
    public static void setSesion(String msgTx, Usuario usuario){
    	if(cacheUsuario == null){
    		cacheUsuario = new Hashtable<String, CacheUsuarioBean>();
    	}
    	
    	logger.info(msgTx + "Se han grabado/actualizado los datos de usario en caché");
    	cacheUsuario.put(usuario.getUsuario(), new CacheUsuarioBean(Calendar.getInstance().getTimeInMillis(), usuario));
    }
    
    public static Usuario getUsuario(String msgTx, int idUsuario){
    	if(cacheUsuario == null){
    		return null;
    	}
    	
    	Enumeration<CacheUsuarioBean> e = cacheUsuario.elements();    	
    	while( e.hasMoreElements() ){
    		CacheUsuarioBean cacheUsuarioBean = e.nextElement();    		
    		Usuario usuario = cacheUsuarioBean.getUsuario();    		
    		if(usuario.getIdUsuario() == idUsuario){
    			logger.info(msgTx + String.format("idUsuario=%s encontrado en caché", idUsuario));
    			return usuario;
    		}
    	}
    	logger.info(msgTx + String.format("No se ha encontrado idUsuario=%s en caché", idUsuario));
    	return null; 	
    }	
}
