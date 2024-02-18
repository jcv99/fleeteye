package panel.vista;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import constante.Messages;
import herramienta.ConfiguracionPantalla;
public class FormularioLoginPanel extends JPanel {

	private static int DISPONIBLE = 1;
	private JPanel a = new JPanel();
	private JButton btnLogin;
	private ConfiguracionPantalla configuracionPantalla = new ConfiguracionPantalla();
	private JPasswordField passwordField;
	private JTextField textField;
	private int X = ((int) (configuracionPantalla.getWidth() / 2 - amplada(38) / 2));
	private int Y = ((int) (configuracionPantalla.getHeight() / 2 - altura(27) / 2));

	public FormularioLoginPanel(Component c) {
		setLayout(null);

		ImageIcon gif = new ImageIcon(Messages.getString("FormularioLoginPanel.0")); //$NON-NLS-1$
		JLabel gifLabel = new JLabel(Messages.getString("VACIO")); //$NON-NLS-1$
		gifLabel.setIcon(gif);
//		System.out.println(gif.getImage());
		gifLabel.setBounds(39, 54, 223, 180);
		add(gifLabel);
		textField = new JTextField();

		textField.setBounds(X + amplada(12), Y + altura(3), amplada(23), altura(4));
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel(Messages.getString("FormularioLoginPanel.2")); //$NON-NLS-1$
		lblNewLabel.setBounds(X + amplada(2), Y + altura(3), amplada(8), altura(4));
		add(lblNewLabel);

		JLabel lblPassword = new JLabel(Messages.getString("FormularioLoginPanel.3")); //$NON-NLS-1$
		lblPassword.setBounds(X + amplada(2), Y + altura(10), amplada(8), altura(4));
		add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(X + amplada(12), Y + altura(10), amplada(23), altura(4));
		add(passwordField);

		btnLogin = new JButton(Messages.getString("FormularioLoginPanel.4")); //$NON-NLS-1$
		btnLogin.setBounds(X + amplada(27), Y + altura(19), amplada(8), altura(5));
		add(btnLogin);

		a.setBounds(X, Y, amplada(38), altura(27));
		add(a);
		a.setBackground(new Color(211, 220, 227));

	}

	private int altura(int a) {
		return ((int) configuracionPantalla.getHeight() * a) / 100;
	}

	private int amplada(int a) {
		return ((int) configuracionPantalla.getWidth() * a) / 100;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}
}
