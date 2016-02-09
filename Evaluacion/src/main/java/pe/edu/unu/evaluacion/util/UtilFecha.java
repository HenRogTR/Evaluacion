package pe.edu.unu.evaluacion.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class UtilFecha {
	private static Logger logger = Logger.getLogger(UtilFecha.class);
	
	/**
	 * Genera un String a partir de un Date de acuerdo al fomrato enviado, si fecha es NULL toma la fecha actual.
	 * @param fecha
	 * @param formato
	 * @return
	 */
	public static String getDateToString(Date fecha, String formato){
		if(fecha == null){
			fecha = new Date();
		}
		SimpleDateFormat formatoDF = new SimpleDateFormat(formato);
		return formatoDF.format(fecha);
	}
	
	public static Date getStringToDate(String fecha, String formato){
		SimpleDateFormat formtatoString = new SimpleDateFormat(formato);
		Date fechaDate = null;
		try{
			fechaDate = formtatoString.parse(fecha);
		}catch(ParseException pe){
			logger.error(String.format("Error al parsear caracter %s a Date con formato %s", fecha, formato));
		}		
		return fechaDate;
	}
	
	public static String getStringToString(String fecha, String formatoOrigen, String formatoDestino){
		Date fechaDate = getStringToDate(fecha, formatoOrigen);
		return getDateToString(fechaDate, formatoDestino);
	}
}
