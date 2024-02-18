package ventana.vista;

import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import constante.Messages;
import herramienta.ConfiguracionPantalla;

public class LoginFrame extends JFrame {

	private static int DISPONIBLE = 1;

	private JButton btnExit;

	private JButton btnLogin;
	private JLabel gifLabel;
	private JPasswordField txtPassword;
	private JTextField txtUser;
	private Double X = (ConfiguracionPantalla.getWidthS() / 2 - amplada(38) / 2);
	private Double Y = (ConfiguracionPantalla.getHeightS() / 2 - altura(27) / 2);

	public LoginFrame() {
		setTitle(Messages.getString("LoginFrame.0")); //$NON-NLS-1$
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		txtUser = new JTextField();

		txtUser.setBounds((int) (X + amplada(12)), (int) (Y + altura(3)), amplada(23), altura(4));
		getContentPane().add(txtUser);
		txtUser.setColumns(10);

		JLabel lblNewLabel = new JLabel(Messages.getString("LoginFrame.1")); //$NON-NLS-1$
		lblNewLabel.setBounds((int) (X + amplada(2)), (int) (Y + altura(3)), amplada(8), altura(4));
		getContentPane().add(lblNewLabel);

		JLabel lblPassword = new JLabel(Messages.getString("LoginFrame.2")); //$NON-NLS-1$
		lblPassword.setBounds((int) (X + amplada(2)), (int) (Y + altura(10)), amplada(8), altura(4));
		getContentPane().add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setBounds((int) (X + amplada(12)), (int) (Y + altura(10)), amplada(23), altura(4));
		getContentPane().add(txtPassword);

		btnLogin = new JButton(Messages.getString("LoginFrame.3")); //$NON-NLS-1$
		btnLogin.setBounds((int) (X + amplada(27)), (int) (Y + altura(19)), amplada(8), altura(5));
		getContentPane().add(btnLogin);

		btnExit = new JButton(Messages.getString("LoginFrame.4")); //$NON-NLS-1$

		btnExit.setBounds(amplada(85), altura(82), amplada(10), altura(7));
		getContentPane().add(btnExit);

		ImageIcon gif = new ImageIcon(this.getClass().getResource(Messages.getString("LoginFrameControl.5")));		
		gifLabel = new JLabel(gif);
		gifLabel.setBounds(X.intValue() + 200, Y.intValue() - 250, gif.getIconWidth(), gif.getIconHeight());
		getContentPane().add(gifLabel);
		setBounds((int) (ConfiguracionPantalla.getWidthS() / 2) - 200, (int) ConfiguracionPantalla.alturaS(5),
				gif.getIconWidth(), gif.getIconHeight());
//		System.out.println(X+Y);
		pack();
	}

	private int altura(int a) {
		return ((int) ConfiguracionPantalla.getHeightS() * a) / 100;
	}

	private int amplada(int a) {
		return ((int) ConfiguracionPantalla.getWidthS() * a) / 100;
	}

	public JButton getBtnExit() {
		return btnExit;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JLabel getGifLabel() {
		return gifLabel;
	}

	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	public JTextField getTxtUser() {
		return txtUser;
	}
}
