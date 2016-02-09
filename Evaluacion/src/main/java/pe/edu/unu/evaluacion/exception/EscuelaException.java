package pe.edu.unu.evaluacion.exception;

public class EscuelaException extends BaseException {

	private static final long serialVersionUID = 1L;

	public EscuelaException(String message, Exception exception) {
		super(message, exception);
	}

	public EscuelaException(String code, String message) {
		super(code, message);
	}

	public EscuelaException(String code, String message, Exception exception) {
		super(code, message, exception);
	}

	public EscuelaException(Exception exception) {
		super(exception);
	}

	public EscuelaException(String message) {
		super(message);
	}
}
