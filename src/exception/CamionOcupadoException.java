package exception;

/**
 *  Esta excepcion salta cuando se intenta asignar un remolque a una tractora que ya tiene 
 *  un remolque asignado
 */
public class CamionOcupadoException extends Exception {

    public CamionOcupadoException() {}

	public CamionOcupadoException(String mensaje) {
		super(mensaje);
    }    
}