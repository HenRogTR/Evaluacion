package pe.edu.unu.evaluacion.util;

import java.util.ArrayList;
import java.util.List;

import pe.edu.unu.evaluacion.bean.RegistroBean;

public class UtilRegistro {
	
	/**
	 * Genera un string de la forma 0:[formato.fecha.1]:23
	 * @author Henrri Trujillo
	 * @param estado 0:habilitado/activo, 1:deshabilitado/inactivo, 2:borrado/no usado
	 * @param idTransaccion idGenerado por el método que invoca al método
	 * @param idUsuario usuario actual que genera el registro
	 * @return
	 */
	public static String getRegistro(String estado, String idTransaccion, String idUsuario){
		return estado + ":" + UtilFecha.getDateToString(null, Constantes.FECHA_FORMATO_1) + ":" + idTransaccion + ":" + idUsuario;
	}
	
	public static List<RegistroBean> registroBeans(String registroHistorial){
		String[] regArray = registroHistorial.split(Constantes.SEPARADOR_BARRA_VERTICAL);
		
		List<RegistroBean> regBeanLista = new ArrayList<RegistroBean>();
		
		for(int i=0 ; i<regArray.length; i++){
			String[] registroItem = regArray[i].split(Constantes.SEPARADOR_DOS_PUNTOS);			
			
			RegistroBean registroBean = new RegistroBean();
			registroBean.setEstado(registroItem[0]);
			registroBean.setFechaRegistroActualizacion(
					UtilFecha.getStringToString(
							registroItem[1],
							Constantes.FECHA_FORMATO_1,
							Constantes.FECHA_FORMATO_2));
			
			registroBean.setIdTransaccion(registroItem[2]);
			registroBean.setIdUsuario(Integer.parseInt(registroItem[3]));
			registroBean.setUsuario(UtilSesion.getUsuario("", registroBean.getIdUsuario()));
			
			regBeanLista.add(registroBean);
		}
		
		return regBeanLista;
	}
}
