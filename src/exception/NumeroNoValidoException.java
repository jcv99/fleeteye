package exception;

import java.awt.Component;

import javax.swing.JOptionPane;

import constante.Messages;

public class NumeroNoValidoException extends Exception {

	public NumeroNoValidoException() {

	}

	public NumeroNoValidoException(String mensaje, Component vista) {

		JOptionPane.showMessageDialog(vista, mensaje, Messages.getString("ERROR"), 0, null); //$NON-NLS-1$
	}
}