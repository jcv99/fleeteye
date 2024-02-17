package exception;

/**
 * Esta excepcion salta cuando se intenta asignar un valor que no corresponde con el valor 
 * esperado
 */
public class DatoNoValidoException extends Exception {

	public DatoNoValidoException() {

	}

	public DatoNoValidoException(String mensaje) {
		super(mensaje);
	}
}
