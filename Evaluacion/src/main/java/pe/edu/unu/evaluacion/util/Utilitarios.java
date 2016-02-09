package pe.edu.unu.evaluacion.util;

public class Utilitarios {

	public static String getIdTransaccion(String idTransaccion) {
		
		if(idTransaccion != null && !idTransaccion.equals("") 
				&& !idTransaccion.equals("0")){
			return idTransaccion;
		}else{
			return generateIdTransaccion();
		}
	}
	
	private static String generateIdTransaccion() {
		String idEAI = Constantes.NOMBRE_APLICACION;
		Identificador identificadorEAI = Identificador.getInstance(idEAI);
		String idTransaccion = identificadorEAI.getCode();

		return idTransaccion;
	}
	
	public static String rolValido(String todosRol){
		String rolRespuesta = "";
	
		String[] rol = todosRol.split(",");
		for(int i=0; i<rol.length; i++){
			
			String[] rolPermiso = rol[i].split(Constantes.SEPARADOR_DOS_PUNTOS);
			if(rolPermiso[0].equals(Constantes.ESTADO_UNO)){
				if(i == 0){
					rolRespuesta = rolPermiso[1];
				}else{
					rolRespuesta += ":" +rolPermiso[1];
				}
			}
		}		
		return rolRespuesta;
	}
	
	/**
     * Devuelve una cadena con barras invertidas delante de los carácteres que
     * necesitan escaparse en situaciones como consultas de bases de datos, etc.
     * Los carácteres que se escapan son la comilla simple (<b>'</b>), comilla
     * doble (<b>"</b>), barra invertida (<b>\</b>). String= "' \ En lugar de la
     * mancha\ " '"
     *
     * @param cadena \ 'En lugar de la mancha' "a"
     * @return \\ \'En lugar de la mancha\' \"a\"
     */
    public static String escapeCaracteres(Object cadena) {
        if (cadena == null) {
            return "";
        }
        return cadena.toString()
        		.replace("\\", "\\\\")
        		.replace("\"", "\\\"")
        		.replace("'", "\\\"")
        		.replace("\r", " ")
        		.replace("\n", "<br>");
    }

}
