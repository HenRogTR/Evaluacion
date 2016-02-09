package pe.edu.unu.evaluacion.exception;

public class UsuarioException extends BaseException {

	private static final long serialVersionUID = 1L;

	public UsuarioException(String message, Exception exception) {
		super(message, exception);
	}

	public UsuarioException(String code, String message) {
		super(code, message);
	}

	public UsuarioException(String code, String message, Exception exception) {
		super(code, message, exception);
	}

	public UsuarioException(Exception exception) {
		super(exception);
	}

	public UsuarioException(String message) {
		super(message);
	}
}
