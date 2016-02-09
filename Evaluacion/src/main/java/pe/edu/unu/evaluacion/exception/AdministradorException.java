package pe.edu.unu.evaluacion.exception;

public class AdministradorException extends BaseException {

	private static final long serialVersionUID = 1L;

	public AdministradorException(String message, Exception exception) {
		super(message, exception);
	}

	public AdministradorException(String code, String message) {
		super(code, message);
	}

	public AdministradorException(String code, String message, Exception exception) {
		super(code, message, exception);
	}

	public AdministradorException(Exception exception) {
		super(exception);
	}

	public AdministradorException(String message) {
		super(message);
	}
}
