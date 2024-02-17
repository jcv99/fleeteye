package panel.vista.guardar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constante.Messages;
import herramienta.ConfiguracionPantalla;
public class GuardarPersonaJuridicaPanel extends JPanel {

	private ConfiguracionPantalla configuracionPantalla = new ConfiguracionPantalla();

	private JTextField txtActividad;
	private JTextField txtNif;
	private JTextField txtRazon;

	public GuardarPersonaJuridicaPanel() {
		setBounds(50, 50, 450, 339);
		setLayout(null);

		JLabel lblNif = new JLabel(Messages.getString("GuardarPersonaJuridicaPanel.0")); //$NON-NLS-1$
		lblNif.setBounds(22, 47, 125, 14);
		add(lblNif);

		txtNif = new JTextField();
		txtNif.setBounds(193, 39, 96, 31);
		add(txtNif);
		txtNif.setColumns(10);

		JLabel lblRazon = new JLabel(Messages.getString("GuardarPersonaJuridicaPanel.1")); //$NON-NLS-1$
		lblRazon.setBounds(22, 89, 125, 14);
		add(lblRazon);

		txtRazon = new JTextField();
		txtRazon.setBounds(193, 81, 96, 31);
		add(txtRazon);
		txtRazon.setColumns(10);

		JLabel lblActividad = new JLabel(Messages.getString("GuardarPersonaJuridicaPanel.2")); //$NON-NLS-1$
		lblActividad.setBounds(22, 139, 125, 14);
		add(lblActividad);

		txtActividad = new JTextField();
		txtActividad.setBounds(193, 131, 96, 31);
		add(txtActividad);
		txtActividad.setColumns(10);

	}

	private int altura(int a) {
		return ((int) configuracionPantalla.getHeight() * a) / 100;
	}

	private int amplada(int a) {
		return ((int) configuracionPantalla.getWidth() * a) / 100;
	}

	public JTextField getTxtActividad() {
		return txtActividad;
	}

	public JTextField getTxtNif() {
		return txtNif;
	}

	public JTextField getTxtRazon() {
		return txtRazon;
	}
}
