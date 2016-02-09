package pe.edu.unu.evaluacion.exception;

public class SemestreException extends BaseException {

	private static final long serialVersionUID = 1L;

	public SemestreException(String message, Exception exception) {
		super(message, exception);
	}

	public SemestreException(String code, String message) {
		super(code, message);
	}

	public SemestreException(String code, String message, Exception exception) {
		super(code, message, exception);
	}

	public SemestreException(Exception exception) {
		super(exception);
	}

	public SemestreException(String message) {
		super(message);
	}
}
