package pe.edu.unu.evaluacion.bean;

public class AjaxBean {
	
	private String nombre;
	private Object valor;
	private String caracterValor;
	
	public AjaxBean(String nombre, Object valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}
	
	public AjaxBean(String nombre, Object valor, String caracterValor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
		this.caracterValor = caracterValor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getCaracterValor() {
		return caracterValor;
	}

	public void setCaracterValor(String caracterValor) {
		this.caracterValor = caracterValor;
	}

}
