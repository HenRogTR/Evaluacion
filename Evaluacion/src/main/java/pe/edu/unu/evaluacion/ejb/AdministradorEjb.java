package pe.edu.unu.evaluacion.ejb;

import java.util.List;

import javax.ejb.Local;

import pe.edu.unu.evaluacion.exception.AdministradorException;
import pe.edu.unu.evaluacion.pojo.Administrador;

@Local
public interface AdministradorEjb {
	
	public void registrar(String msgTx) throws AdministradorException;
	
	public Administrador getAdministrador();
	public void setAdministrador(Administrador administrador);
	public List<Administrador> getAdministradorListas();
	public void setAdministradorListas(List<Administrador> administradorListas);

}
