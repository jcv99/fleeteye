package exception;

public class EncargoEstadoNoValidoException extends Exception {

	public EncargoEstadoNoValidoException() {

	}

	public EncargoEstadoNoValidoException(String mensaje) {
		super(mensaje);
    }
}