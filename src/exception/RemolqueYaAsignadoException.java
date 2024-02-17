package exception;

import constante.ConstantesExcepciones;

/**
 * Esta excepcion salta cuando intentamos asignar un remolque ya ocupado
 */
public class RemolqueYaAsignadoException extends Exception {

	public RemolqueYaAsignadoException() {
		super(ConstantesExcepciones.ERROR_REMOLQUE_YA_ASIGNADO);
	}
}
