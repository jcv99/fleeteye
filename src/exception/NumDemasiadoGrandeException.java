package exception;

import java.awt.Component;

import javax.swing.JOptionPane;

import constante.Messages;
public class NumDemasiadoGrandeException extends Exception {

	public NumDemasiadoGrandeException() {

	}

	public NumDemasiadoGrandeException(String mensaje, Component vista) {

		JOptionPane.showMessageDialog(vista, mensaje, Messages.getString("ERROR"), 0, null); //$NON-NLS-1$
	}
}