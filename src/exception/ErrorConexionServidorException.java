package exception;

/**
 * Esta excepcion salta cuando se corta la conexion entre el programa y el servidor remoto
 */
public class ErrorConexionServidorException extends Exception {
	
	public ErrorConexionServidorException() {

	}

	public ErrorConexionServidorException(String mensaje) {
		super(mensaje);
	}

}
