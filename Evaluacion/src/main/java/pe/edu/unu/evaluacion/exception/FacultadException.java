package pe.edu.unu.evaluacion.exception;

public class FacultadException extends BaseException {

	private static final long serialVersionUID = 1L;

	public FacultadException(String message, Exception exception) {
		super(message, exception);
	}

	public FacultadException(String code, String message) {
		super(code, message);
	}

	public FacultadException(String code, String message, Exception exception) {
		super(code, message, exception);
	}

	public FacultadException(Exception exception) {
		super(exception);
	}

	public FacultadException(String message) {
		super(message);
	}
}
