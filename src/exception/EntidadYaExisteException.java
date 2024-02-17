package exception;

/**
 * Esta excepcion salta cuando se intenta guardar en la base de datos una entidad ya guardada
 */
public class EntidadYaExisteException extends Exception {

	public EntidadYaExisteException() {

	}

	public EntidadYaExisteException(String mensaje) {
		super(mensaje);
	}
	
	public EntidadYaExisteException(String mensaje, String cause) {
		super(mensaje, new Throwable(cause));
	}
	
}
