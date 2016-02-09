package pe.edu.unu.evaluacion.util;

import pe.edu.unu.evaluacion.bean.AjaxBean;

public class Ajax {

	public static String[] etiquetasValor = {"\"", "[", "]", "{", "}", ""};
	
	public static String jsonFormato(AjaxBean... args) {
		StringBuilder ajaxResponse = new StringBuilder();

		ajaxResponse.append("{");

		int a = 0;
		for (AjaxBean obj : args) {
			if (a > 0) {
				ajaxResponse.append(", ");
			}

			int inicio = 0;
			int fin = 0;
			
			if(obj.getCaracterValor()!=null){
				inicio = Integer.parseInt(obj.getCaracterValor().substring(0, 1));
				fin = Integer.parseInt(obj.getCaracterValor().substring(1, 2));
			}
			
			ajaxResponse.append("\"");
			ajaxResponse.append(obj.getNombre());
			ajaxResponse.append("\":");
			ajaxResponse.append(etiquetasValor[inicio]);
			ajaxResponse.append(obj.getValor());
			ajaxResponse.append(etiquetasValor[fin]);
			a++;
		}
		ajaxResponse.append("}");

		return ajaxResponse.toString();
	}
}
