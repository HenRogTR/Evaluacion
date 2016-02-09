package pe.edu.unu.evaluacion.bean;

public class TransaccionBean {

	private String idTx;
	private String msgTx;	
	
	public TransaccionBean(String idTx, String msgTx) {
		this.idTx = idTx;
		this.msgTx = msgTx;
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
