package pe.edu.unu.evaluacion.util;

public class UtilValidaciones {

	public static boolean valido(String valor){
		
		if(valor == null){
			return false;
		}		
		
		return !valor.trim().equals("");
	}
	
	public static boolean numero(String valor){
		if(valido(valor)){
			try{
				Integer.parseInt(valor);
				return true;
			}catch(Exception e){
				return false;
			}
		}else {
			return false;
		}
	}
}
