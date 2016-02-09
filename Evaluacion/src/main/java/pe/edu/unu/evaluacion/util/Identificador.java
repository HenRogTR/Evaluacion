package pe.edu.unu.evaluacion.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Henrri Rogger Trujillo Romero
 * @clase: IdentificadorEAI.java  
 * @descripcion Clase util para generar idTransaccion
 * @author_company: CLARO.
 * @fecha_de_creacion: 10-11-2015.
 * @fecha_de_ultima_actualizacion: dd-mm-yyyy.
 * @version 1.0
 */
public class Identificador {

	private static int conta;
	private String ccode;
	private SimpleDateFormat sdf;
	private static Identificador instancia = null;
	
	private String fommatoFecha = "yyyyMMddHHmmssSSS";
	private int tamanioContador = 4;
	private int limiteContador = 9999;
	private int cero = 0;
	private int uno = 1;

	/**
	 * 
	 * @param ControllerCode valor para identificar el servicio.
	 * @return
	 */
	public static Identificador getInstance(String ControllerCode) {
		if (instancia == null) {
			synchronized (Identificador.class) {
				if (instancia == null) {
					instancia = new Identificador(ControllerCode);
				}
			}
		}
		return instancia;
	}

	Identificador(String ControllerCode) {
		this.ccode  = ControllerCode;
		conta = cero;		

		this.sdf = new SimpleDateFormat(fommatoFecha);
	}

	public synchronized String getCode() {
		
		String df = this.sdf.format(new Date());
		String aux = this.ccode	+ df + StringUtils.leftPad(new StringBuffer().append("").append(conta).toString(), tamanioContador, String.valueOf(cero));

		conta += uno;
		if (conta > limiteContador) {
			conta = cero;
		}
		return aux;
	}
	
}
