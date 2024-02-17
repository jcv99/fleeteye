package exception;

/**
 * Esta excepcion salta cuando se asigna un camion con un remolque que no cumple con los 
 * requisitos requeridos
 */
public class RemolqueNoCompatibleException extends Exception {
	
	public RemolqueNoCompatibleException() {

	}

	public RemolqueNoCompatibleException(String mensaje) {
		super(mensaje);
	}
}
