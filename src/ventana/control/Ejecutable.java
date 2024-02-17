package ventana.control;

import java.awt.EventQueue;

import javax.swing.UIManager;

import constante.Messages;
import ventana.vista.LoginFrame;
public class Ejecutable {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			private LoginFrame vista;

			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(Messages.getString("Ejecutable.0")); //$NON-NLS-1$
					vista = new LoginFrame();
					vista.setVisible(true);
					new LoginFrameControl(vista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
