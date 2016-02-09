package pe.edu.unu.evaluacion.bean;

import pe.edu.unu.evaluacion.pojo.Usuario;

public class CacheUsuarioBean {
	private long lastUpdate;
	private Usuario usuario;

	public CacheUsuarioBean(long lastUpdate, Usuario usuario) {
		this.lastUpdate = lastUpdate;
		this.usuario = usuario;
	}

	public long getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
