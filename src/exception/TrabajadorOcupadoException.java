package exception;

import constante.ConstantesExcepciones;

/**
 * Esta excepcion salta cuando intentamos asignar a un camion un trabajador ya ocupado
 */
public class TrabajadorOcupadoException extends Exception {

    public TrabajadorOcupadoException () 
    {
    	super(ConstantesExcepciones.ERROR_TRABAJADOR_YA_ASIGNADO);
    }
}