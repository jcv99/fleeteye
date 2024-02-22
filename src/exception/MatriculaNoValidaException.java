package exception;

import constante.Messages;
public class MatriculaNoValidaException extends Exception {

	public MatriculaNoValidaException(String matricula) {
		super(Messages.getString("MatriculaNoValidaException.0") + matricula //$NON-NLS-1$
				+ Messages.getString("MatriculaNoValidaException.1")); //$NON-NLS-1$
	}
}
