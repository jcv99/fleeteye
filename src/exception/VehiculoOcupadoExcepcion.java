package exception;

/**
 * Esta excepcion salta cuando hay errores de asignacion de vehiculos a encargos
 */
public class VehiculoOcupadoExcepcion extends Exception {

	public VehiculoOcupadoExcepcion () 
    {

    }

    public VehiculoOcupadoExcepcion (String mensaje) 
    {
        super(mensaje);
    }
}
