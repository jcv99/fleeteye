package panel.vista.guardar;

import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import constante.Messages;
import herramienta.ConfiguracionPantalla;
import herramienta.DateLabelFormatter;
public class GuardarPersonaFisicaPanel extends JPanel {

	private ConfiguracionPantalla configuracionPantalla = new ConfiguracionPantalla();

	private JDatePickerImpl datePickerFechaNacimiento;
	private UtilDateModel model;
	private JTextField txtApellido;

	private JTextField txtNacional;

	private JTextField txtNif;

	private JTextField txtNombre;

	private JTextField txtSegApellido;

	public GuardarPersonaFisicaPanel() {
		setBounds(50, 50, 450, 339);
		setLayout(null);

		JLabel lblNif = new JLabel(Messages.getString("GuardarPersonaFisicaPanel.0")); //$NON-NLS-1$
		lblNif.setBounds(22, 39, 115, 14);
		add(lblNif);

		txtNif = new JTextField();
		txtNif.setBounds(157, 39, 143, 26);
		add(txtNif);
		txtNif.setColumns(10);

		JLabel lblNombre = new JLabel(Messages.getString("GuardarPersonaFisicaPanel.1")); //$NON-NLS-1$
		lblNombre.setBounds(22, 82, 115, 14);
		add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(157, 76, 143, 26);
		add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblApellido = new JLabel(Messages.getString("GuardarPersonaFisicaPanel.2")); //$NON-NLS-1$
		lblApellido.setBounds(22, 119, 115, 14);
		add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setBounds(157, 113, 143, 26);
		add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblsegApellido = new JLabel(Messages.getString("GuardarPersonaFisicaPanel.3")); //$NON-NLS-1$
		lblsegApellido.setBounds(22, 156, 115, 14);
		add(lblsegApellido);

		txtSegApellido = new JTextField();
		txtSegApellido.setBounds(157, 150, 143, 26);
		add(txtSegApellido);
		txtSegApellido.setColumns(10);

		JLabel lblNacional = new JLabel(Messages.getString("GuardarPersonaFisicaPanel.4")); //$NON-NLS-1$
		lblNacional.setBounds(22, 193, 115, 14);
		add(lblNacional);

		txtNacional = new JTextField();
		txtNacional.setBounds(157, 187, 143, 26);
		add(txtNacional);
		txtNacional.setColumns(10);

		JLabel lblFechaNaci = new JLabel(Messages.getString("GuardarPersonaFisicaPanel.5")); //$NON-NLS-1$
		lblFechaNaci.setBounds(22, 225, 115, 14);
		add(lblFechaNaci);

		model = new UtilDateModel();
		Properties p = new Properties();
		p.put(Messages.getString("GuardarPersonaFisicaPanel.6"), Messages.getString("GuardarPersonaFisicaPanel.7")); //$NON-NLS-1$ //$NON-NLS-2$
		p.put(Messages.getString("GuardarPersonaFisicaPanel.8"), Messages.getString("GuardarPersonaFisicaPanel.9")); //$NON-NLS-1$ //$NON-NLS-2$
		p.put(Messages.getString("GuardarPersonaFisicaPanel.10"), Messages.getString("GuardarPersonaFisicaPanel.11")); //$NON-NLS-1$ //$NON-NLS-2$

		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

		datePickerFechaNacimiento = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePickerFechaNacimiento.setBounds(157, 224, 143, 26);
		add(datePickerFechaNacimiento);

	}

	private int altura(int a) {
		return ((int) configuracionPantalla.getHeight() * a) / 100;
	}

	private int amplada(int a) {
		return ((int) configuracionPantalla.getWidth() * a) / 100;
	}

	public JDatePickerImpl getDatePickerFechaNacimiento() {
		return datePickerFechaNacimiento;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public JTextField getTxtNacional() {
		return txtNacional;
	}

	public JTextField getTxtNif() {
		return txtNif;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtSegApellido() {
		return txtSegApellido;
	}
}
