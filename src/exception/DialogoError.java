package exception;

import javax.swing.JOptionPane;

import constante.Messages;

public class DialogoError {

	private JOptionPane dialog;
	private Exception e;
	private String titulo = Messages.getString("DialogoError.0"); //$NON-NLS-1$

	public DialogoError(Exception e) {
		this.dialog = new JOptionPane();
		this.e = e;
	}

	@SuppressWarnings("static-access")
	public void showErrorMessage() {
		if (e.getCause() == null)
			JOptionPane.showMessageDialog(null, e.getMessage(), titulo, JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getCause().getMessage(), JOptionPane.ERROR_MESSAGE);
	}
}
