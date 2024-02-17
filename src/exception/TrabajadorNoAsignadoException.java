package exception;

/**
 *  Esta excepcion salta cuando se intenta asignar un remolque a una tractora que ya tiene 
 *  un remolque asignado
 */
public class TrabajadorNoAsignadoException extends Exception {

    public TrabajadorNoAsignadoException() {}

	public TrabajadorNoAsignadoException(String mensaje) {
		super(mensaje);
    }    
}