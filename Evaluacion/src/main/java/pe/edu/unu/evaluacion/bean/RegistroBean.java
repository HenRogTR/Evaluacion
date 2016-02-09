package pe.edu.unu.evaluacion.bean;

import pe.edu.unu.evaluacion.pojo.Usuario;

public class RegistroBean {
	private String estado;
	private String fechaRegistroActualizacion;
	private String idTransaccion;
	private int idUsuario;
	private Usuario usuario;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaRegistroActualizacion() {
		return fechaRegistroActualizacion;
	}

	public void setFechaRegistroActualizacion(String fechaRegistroActualizacion) {
		this.fechaRegistroActualizacion = fechaRegistroActualizacion;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
