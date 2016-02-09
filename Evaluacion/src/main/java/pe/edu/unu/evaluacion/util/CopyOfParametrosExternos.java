package pe.edu.unu.evaluacion.util;

import org.apache.log4j.Logger;

public class CopyOfParametrosExternos {
	
	private static Logger logger = Logger.getLogger(CopyOfParametrosExternos.class);

    private static ReadForeignPropertiesUtil PROP = null;
    
    private static final String DEFAULT_CONFIGURATION_FILE = System.getenv("unu.properties") + "EvaluacionProperties.properties";
    
    public static Integer TAMANIO_RANDOM_IDTRANSACCION = null;
    public static Long TIEMPO_VIDA_SESION_USUARIO = null;
    
    private static void cargarPropiedades(String location) throws Exception {
        PROP = ReadForeignPropertiesUtil.getSingleton(location);        
        
        TAMANIO_RANDOM_IDTRANSACCION = Integer.parseInt(PROP.getValor("tamanio.random.idTransaccion"));
        TIEMPO_VIDA_SESION_USUARIO = Long.parseLong(PROP.getValor("tiempo.vida.sesion.usuario"));
    }
    
    public static String obtieneCodigoError(String claveMensaje){
    	return PROP.getValor("error.cod." + claveMensaje);
    }
    
    public static String obtieneMensajeError(String claveMensaje){
    	return PROP.getValor("error.msg." + claveMensaje);
    }
    
    static {
        try {
            cargarPropiedades();
        } catch (Exception e) {
            logger.error("Error al cargar propiedades: " + e.getMessage(), e);
        }
    }
    
    private static void cargarPropiedades() throws Exception {
        if (PROP == null) {
            cargarPropiedades(DEFAULT_CONFIGURATION_FILE);
        }
    }
}
