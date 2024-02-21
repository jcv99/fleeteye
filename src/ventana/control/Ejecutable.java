package ventana.control;

import java.awt.EventQueue;
import javax.swing.UIManager;
import constante.Messages;
import ventana.vista.LoginFrame;

public class Ejecutable {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				setLookAndFeel();
				LoginFrame vista = createLoginFrame();
				displayLoginFrame(vista);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private static void setLookAndFeel() throws Exception {
		UIManager.setLookAndFeel(Messages.getString("Ejecutable.0")); //$NON-NLS-1$
	}

	private static LoginFrame createLoginFrame() {
		return new LoginFrame();
	}

	private static void displayLoginFrame(LoginFrame vista) {
		vista.setVisible(true);
		try {
			new LoginFrameControl(vista);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}