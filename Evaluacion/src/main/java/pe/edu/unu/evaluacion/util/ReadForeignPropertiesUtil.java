package pe.edu.unu.evaluacion.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author Christiand Veramendi Granados, Henrri Trujillo
 * @version 1.2
 */
public class ReadForeignPropertiesUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(ReadForeignPropertiesUtil.class);

	static private Properties propertiesFile = null;
	static private InputStream is = null;
	static private Reader r = null;
	static private ReadForeignPropertiesUtil objetoClase = null;
	static private String charsetName = "UTF-8";
	
	/**
	 * Método que permite cargar parametros desde un propertiesFile externo. Para ello recibe la ruta de localización del archivo.
	 * @param rutaArchivProp Ruta de localización del archivo properties.
	 * @return objetoClase Objeto de esta clase: "ReadForeignPropertiesUtil".
	 * @throws Exception
	 */
	static public ReadForeignPropertiesUtil getSingleton(String rutaArchivProp) throws Exception {
		if(objetoClase == null){
			try{
				objetoClase = new ReadForeignPropertiesUtil();
				propertiesFile = new Properties();
				
				is = new FileInputStream(rutaArchivProp);
				r = new InputStreamReader(is, charsetName);
				propertiesFile.load(r);
				is.close();
				
				logger.info("Parámetros cargados de: " + rutaArchivProp);
			}
			catch(Exception e){
				logger.error("Error al cargar parametros del propertiesFile: " + rutaArchivProp + " . Excepcion: " + e.getMessage(),e);
				objetoClase = null;
				
				throw new Exception("Error al cargar parametros del propertiesFile: " + rutaArchivProp + " . Excepcion: " + e.getMessage());
			}
		}
		return objetoClase;
	}
	
	/**
	 * Método que permite leer el valor asociado a un key recibido. 
	 * El cual se encuentra contenido en el archivo properties cargado en la variable de clase "propertiesFile". 
	 * @param key nombre del key (llave)  .  
	 * @return keyValue
	 * @throws Exception
	 */
	public String getValor(String key){
		
		String keyValue = "";
	    try {
	        keyValue = propertiesFile.get(key).toString();
	    } catch (Exception e) {
	        logger.error("Error al leer key: " + key);
	    }
		
		return keyValue;
	}

}
