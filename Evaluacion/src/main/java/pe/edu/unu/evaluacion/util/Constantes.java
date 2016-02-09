package pe.edu.unu.evaluacion.util;

import java.io.Serializable;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class Constantes implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Constantes.class);
	
	private static String CONFIGURATION_FILE = "aplicacion";
	
	private static ResourceBundle PROP = null;
	
    public static String FECHA_FORMATO_1 = null;
    public static String FECHA_FORMATO_2 = null;
    public static String CONSTANTE_GUION_MEDIO = null;
    public static String CONSTANTE_GUION_BAJO = null;
    public static String SEPARADOR_BARRA_VERTICAL = null;
    public static String SEPARADOR_DOS_PUNTOS = null;
        
    public static String ESTADO_CERO = null;
    public static String ESTADO_UNO = null;
    public static String ESTADO_DOS = null;
    
    public static String NUMERO_UNO = null;
    
    public static String NOMBRE_APLICACION = "EVAL";
    
	public static String[] TABLA_USARIO_PRIMER_USO = null;
	public static String[] TABLA_ADMINISTRADOR_PRIMER_USO = null;
    
    private static void cargarConstantes(String location) throws Exception {
    	PROP = ResourceBundle.getBundle(CONFIGURATION_FILE);
    	
    	FECHA_FORMATO_1 = PROP.getString("formato.fecha.1");
        FECHA_FORMATO_2 = PROP.getString("formato.fecha.2");
        CONSTANTE_GUION_MEDIO = PROP.getString("constante.guion.medio");
        CONSTANTE_GUION_BAJO = PROP.getString("constante.guion.bajo");
        SEPARADOR_BARRA_VERTICAL = PROP.getString("separador.barra.vertical");
        SEPARADOR_DOS_PUNTOS = PROP.getString("separador.dos.puntos");
        
        ESTADO_CERO = PROP.getString("estado.cero");
        ESTADO_UNO = PROP.getString("estado.uno");
        ESTADO_DOS = PROP.getString("estado.dos");
        
        NUMERO_UNO = PROP.getString("numero.uno");
    	
    	TABLA_USARIO_PRIMER_USO = PROP.getString("datos.usuario").split(SEPARADOR_BARRA_VERTICAL);
    	TABLA_ADMINISTRADOR_PRIMER_USO = PROP.getString("datos.administrador").split(SEPARADOR_BARRA_VERTICAL);
    }
    
    static {
        try {
            cargarConstantes();
        } catch (Exception e) {
            logger.error("Error al cargar constantes: " + e.getMessage(), e);
        }
    }
    
    private static void cargarConstantes() throws Exception {
        if (PROP == null) {
            cargarConstantes(CONFIGURATION_FILE);
        }
    }
    
}
