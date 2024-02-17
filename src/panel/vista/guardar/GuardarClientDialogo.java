package panel.vista.guardar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

import constante.Messages;
import herramienta.ConfiguracionPantalla;
public class GuardarClientDialogo extends JDialog {

	private JButton btnGuardar;

	private ConfiguracionPantalla configuracionPantalla = new ConfiguracionPantalla();

	private GuardarPersonaJuridicaPanel guardarPersonaJuridica;

	public GuardarClientDialogo() {
		setResizable(false);
		setModal(true);
		setBounds(50, 50, 506, 463);
		getContentPane().setLayout(null);
		setTitle(Messages.getString("GuardarClientDialogo.0")); //$NON-NLS-1$
		JScrollPane pane = new JScrollPane();
		getContentPane().add(pane);

		guardarPersonaJuridica = new GuardarPersonaJuridicaPanel();
		pane.setBounds(144, 37, 317, 361);
		pane.setViewportView(guardarPersonaJuridica);

		btnGuardar = new JButton(Messages.getString("GuardarClientDialogo.1")); //$NON-NLS-1$
		btnGuardar.setBounds(24, 37, 83, 34);
		getContentPane().add(btnGuardar);

	}

	private int altura(int a) {
		return ((int) configuracionPantalla.getHeight() * a) / 100;
	}

	private int amplada(int a) {
		return ((int) configuracionPantalla.getWidth() * a) / 100;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public GuardarPersonaJuridicaPanel getGuardarPersonaJuridica() {
		return guardarPersonaJuridica;
	}

}
