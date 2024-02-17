package exception;

/**
 * Esta excepcion salta cuando intentamos introducir un DNI que no cumple con el formato 
 * requerido
 */
public class NIFNoValidoException extends Exception {

	public NIFNoValidoException() {

	}

	public NIFNoValidoException(String mensaje) {
		super(mensaje);
	}
	
	public NIFNoValidoException(String mensaje, Throwable cause) {
		super(mensaje, cause);
	}
}