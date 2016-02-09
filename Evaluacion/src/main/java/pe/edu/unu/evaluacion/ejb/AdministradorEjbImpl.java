package pe.edu.unu.evaluacion.ejb;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pe.edu.unu.evaluacion.exception.AdministradorException;
import pe.edu.unu.evaluacion.pojo.Administrador;

public class AdministradorEjbImpl implements AdministradorEjb {

	private static Logger logger = Logger.getLogger(AdministradorEjbImpl.class);
	private String idTx;
	private String msgTx;
	
	private Session session = null;
	private Transaction transaction = null;
	
	private Administrador administrador;
	private List<Administrador> administradorLista;
	
	public AdministradorEjbImpl() {
		this.administrador = new Administrador();
	}
	
	@Override
	public void registrar(String msgTx) throws AdministradorException {
		// TODO Auto-generated method stub

	}

	@Override
	public Administrador getAdministrador() {
		return this.administrador;
	}

	@Override
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	@Override
	public List<Administrador> getAdministradorListas() {
		return this.administradorLista;
	}

	@Override
	public void setAdministradorListas(List<Administrador> administradorListas) {
		this.administradorLista = administradorListas;
	}

	public String getIdTx() {
		return idTx;
	}

	public void setIdTx(String idTx) {
		this.idTx = idTx;
	}

	public String getMsgTx() {
		return msgTx;
	}

	public void setMsgTx(String msgTx) {
		this.msgTx = msgTx;
	}

}
