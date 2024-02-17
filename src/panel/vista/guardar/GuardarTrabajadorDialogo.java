package panel.vista.guardar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import constante.Messages;
import herramienta.ConfiguracionPantalla;
public class GuardarTrabajadorDialogo extends JDialog {

	private JButton btnCurriculumSubir;

	private JButton btnGuardar;

	private ConfiguracionPantalla configuracionPantalla = new ConfiguracionPantalla();
	private GuardarPersonaFisicaPanel guardarPersonaJuridica;
	private JTextField txtContrato;

	public GuardarTrabajadorDialogo() {
		setResizable(false);
		setModal(true);
		setTitle(Messages.getString("GuardarTrabajadorDialogo.0")); //$NON-NLS-1$
		setBounds(50, 50, 506, 463);
		getContentPane().setLayout(null);

		JScrollPane pane = new JScrollPane();
		getContentPane().add(pane);

		guardarPersonaJuridica = new GuardarPersonaFisicaPanel();
		pane.setBounds(131, 102, 347, 296);
		pane.setViewportView(guardarPersonaJuridica);

		btnGuardar = new JButton(Messages.getString("GuardarTrabajadorDialogo.1")); //$NON-NLS-1$
		btnGuardar.setBounds(24, 37, 83, 34);
		getContentPane().add(btnGuardar);

		JLabel lblContrato = new JLabel(Messages.getString("GuardarTrabajadorDialogo.2")); //$NON-NLS-1$
		lblContrato.setBounds(147, 53, 67, 18);
		getContentPane().add(lblContrato);

		txtContrato = new JTextField();
		txtContrato.setBounds(220, 48, 169, 26);
		getContentPane().add(txtContrato);
		txtContrato.setColumns(10);

		btnCurriculumSubir = new JButton(Messages.getString("GuardarTrabajadorDialogo.3")); //$NON-NLS-1$
		btnCurriculumSubir.setBounds(399, 48, 79, 26);
		getContentPane().add(btnCurriculumSubir);

	}

	private int altura(int a) {
		return ((int) configuracionPantalla.getHeight() * a) / 100;
	}

	private int amplada(int a) {
		return ((int) configuracionPantalla.getWidth() * a) / 100;
	}

	public JButton getBtnCurriculumSubir() {
		return btnCurriculumSubir;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public GuardarPersonaFisicaPanel getGuardarPersonaJuridica() {
		return guardarPersonaJuridica;
	}

	public JTextField getTxtCurriculum() {
		return txtContrato;
	}
}
