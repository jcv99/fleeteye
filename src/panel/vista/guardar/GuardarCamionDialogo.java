package panel.vista.guardar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import constante.Messages;
import herramienta.DateLabelFormatter;
public class GuardarCamionDialogo extends JDialog {

	private JButton btnInsertar;
	private JComboBox<String> comboBoxCombustible;
	private JComboBox<String> comboBoxNormativa;
	private JDatePickerImpl datePickerMatriculacion;
	private JPanel panelMatriculacion;
	private JTextField textFieldEje2;
	private JTextField textFieldMatricula1;
	private JTextField textFieldMatricula2;
	private JTextField txtEje;
	private JTextField txtKilometraje;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtPotencia;
	private JTextField txtTara;

	public GuardarCamionDialogo() {
		setBounds(new Rectangle(1000, 2000, 0, 0));
		setAlwaysOnTop(true);
		setLocation(new Point(1000, 2000));
		setTitle(Messages.getString("GuardarCamionDialogo.0")); //$NON-NLS-1$
		setName(Messages.getString("GuardarCamionDialogo.1")); //$NON-NLS-1$
		setModal(true);
		setBounds(100, 100, 592, 430);
		getContentPane().setLayout(null);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(12, 25, 550, 100);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblMatricula = new JLabel(Messages.getString("GuardarCamionDialogo.2")); //$NON-NLS-1$
		lblMatricula.setBounds(12, 22, 69, 15);
		panel.add(lblMatricula);

		textFieldMatricula1 = new JTextField();
		textFieldMatricula1.setBounds(86, 16, 63, 27);
		panel.add(textFieldMatricula1);
		textFieldMatricula1.setColumns(10);

		JLabel label = new JLabel(Messages.getString("GuardarCamionDialogo.3")); //$NON-NLS-1$
		label.setFont(new Font(Messages.getString("GuardarCamionDialogo.4"), Font.BOLD, 20)); //$NON-NLS-1$
		label.setBounds(153, 21, 18, 15);
		panel.add(label);

		textFieldMatricula2 = new JTextField();
		textFieldMatricula2.setColumns(10);
		textFieldMatricula2.setBounds(167, 16, 55, 27);
		panel.add(textFieldMatricula2);

		JLabel lblMarca = new JLabel(Messages.getString("GuardarCamionDialogo.5")); //$NON-NLS-1$
		lblMarca.setBounds(230, 22, 55, 15);
		panel.add(lblMarca);

		txtMarca = new JTextField();
		txtMarca.setBounds(288, 16, 240, 27);
		panel.add(txtMarca);
		txtMarca.setColumns(10);

		JLabel lblModelo = new JLabel(Messages.getString("GuardarCamionDialogo.6")); //$NON-NLS-1$
		lblModelo.setBounds(12, 62, 55, 15);
		panel.add(lblModelo);

		txtModelo = new JTextField();
		txtModelo.setBounds(73, 56, 286, 27);
		panel.add(txtModelo);
		txtModelo.setColumns(10);

		JLabel lblTara = new JLabel(Messages.getString("GuardarCamionDialogo.7")); //$NON-NLS-1$
		lblTara.setBounds(377, 62, 45, 15);
		panel.add(lblTara);

		txtTara = new JTextField();
		txtTara.setBounds(426, 56, 69, 27);
		panel.add(txtTara);
		txtTara.setColumns(10);

		JLabel lblTn = new JLabel(Messages.getString("GuardarCamionDialogo.8")); //$NON-NLS-1$
		lblTn.setBounds(500, 62, 28, 15);
		panel.add(lblTn);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBounds(12, 137, 550, 169);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblCombustible = new JLabel(Messages.getString("GuardarCamionDialogo.9")); //$NON-NLS-1$
		lblCombustible.setBounds(12, 25, 98, 15);
		panel_1.add(lblCombustible);

		comboBoxCombustible = new JComboBox<String>();
		comboBoxCombustible.setBounds(111, 20, 188, 27);
		comboBoxCombustible.addItem(Messages.getString("GuardarCamionDialogo.10")); //$NON-NLS-1$
		comboBoxCombustible.addItem(Messages.getString("GuardarCamionDialogo.11")); //$NON-NLS-1$
		comboBoxCombustible.addItem(Messages.getString("GuardarCamionDialogo.12")); //$NON-NLS-1$
		comboBoxCombustible.addItem(Messages.getString("GuardarCamionDialogo.13")); //$NON-NLS-1$
		comboBoxCombustible.addItem(Messages.getString("GuardarCamionDialogo.14")); //$NON-NLS-1$
		panel_1.add(comboBoxCombustible);

		JLabel lblPotencia = new JLabel(Messages.getString("GuardarCamionDialogo.15")); //$NON-NLS-1$
		lblPotencia.setBounds(334, 25, 74, 15);
		panel_1.add(lblPotencia);

		txtPotencia = new JTextField();
		txtPotencia.setBounds(409, 19, 84, 27);
		panel_1.add(txtPotencia);
		txtPotencia.setColumns(10);

		JLabel lblCv = new JLabel(Messages.getString("GuardarCamionDialogo.16")); //$NON-NLS-1$
		lblCv.setBounds(500, 25, 28, 15);
		panel_1.add(lblCv);

		JLabel lblNormativa = new JLabel(Messages.getString("GuardarCamionDialogo.17")); //$NON-NLS-1$
		lblNormativa.setBounds(12, 76, 84, 15);
		panel_1.add(lblNormativa);

		comboBoxNormativa = new JComboBox<String>();
		comboBoxNormativa.setBounds(97, 71, 166, 27);
		comboBoxNormativa.addItem(Messages.getString("GuardarCamionDialogo.18")); //$NON-NLS-1$
		comboBoxNormativa.addItem(Messages.getString("GuardarCamionDialogo.19")); //$NON-NLS-1$
		comboBoxNormativa.addItem(Messages.getString("GuardarCamionDialogo.20")); //$NON-NLS-1$
		comboBoxNormativa.addItem(Messages.getString("GuardarCamionDialogo.21")); //$NON-NLS-1$
		comboBoxNormativa.addItem(Messages.getString("GuardarCamionDialogo.22")); //$NON-NLS-1$
		comboBoxNormativa.addItem(Messages.getString("GuardarCamionDialogo.23")); //$NON-NLS-1$
		comboBoxNormativa.addItem(Messages.getString("GuardarCamionDialogo.24")); //$NON-NLS-1$
		comboBoxNormativa.addItem(Messages.getString("GuardarCamionDialogo.25")); //$NON-NLS-1$
		panel_1.add(comboBoxNormativa);

		JLabel lblKilometraje = new JLabel(Messages.getString("GuardarCamionDialogo.26")); //$NON-NLS-1$
		lblKilometraje.setBounds(281, 76, 98, 15);
		panel_1.add(lblKilometraje);

		txtKilometraje = new JTextField();
		txtKilometraje.setBounds(370, 70, 123, 27);
		panel_1.add(txtKilometraje);
		txtKilometraje.setColumns(10);

		JLabel lblKm = new JLabel(Messages.getString("GuardarCamionDialogo.27")); //$NON-NLS-1$
		lblKm.setBounds(500, 76, 28, 15);
		panel_1.add(lblKm);

		JLabel lblConfiguracionEje = new JLabel(Messages.getString("GuardarCamionDialogo.28")); //$NON-NLS-1$
		lblConfiguracionEje.setBounds(12, 131, 135, 15);
		panel_1.add(lblConfiguracionEje);

		txtEje = new JTextField();
		txtEje.setBounds(137, 125, 28, 27);
		panel_1.add(txtEje);
		txtEje.setColumns(10);

		textFieldEje2 = new JTextField();
		textFieldEje2.setColumns(10);
		textFieldEje2.setBounds(188, 125, 28, 27);
		panel_1.add(textFieldEje2);

		JLabel lblX = new JLabel(Messages.getString("GuardarCamionDialogo.29")); //$NON-NLS-1$
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(149, 131, 55, 15);
		panel_1.add(lblX);

		UtilDateModel modelMatriculacion = new UtilDateModel();
		Properties pMatriculacion = new Properties();
		pMatriculacion.put(Messages.getString("GuardarCamionDialogo.30"), Messages.getString("GuardarCamionDialogo.31")); //$NON-NLS-1$ //$NON-NLS-2$
		pMatriculacion.put(Messages.getString("GuardarCamionDialogo.32"), Messages.getString("GuardarCamionDialogo.33")); //$NON-NLS-1$ //$NON-NLS-2$
		pMatriculacion.put(Messages.getString("GuardarCamionDialogo.34"), Messages.getString("GuardarCamionDialogo.35")); //$NON-NLS-1$ //$NON-NLS-2$
		JDatePanelImpl datePanelMatriculacion = new JDatePanelImpl(modelMatriculacion, pMatriculacion);

		panelMatriculacion = new JPanel();
		panelMatriculacion.setBounds(328, 122, 212, 35);
		panel_1.add(panelMatriculacion);

		JLabel lblMatriculacion = new JLabel(Messages.getString("GuardarCamionDialogo.36")); //$NON-NLS-1$
		lblMatriculacion.setBounds(234, 131, 105, 15);
		panel_1.add(lblMatriculacion);

		datePickerMatriculacion = new JDatePickerImpl(datePanelMatriculacion, new DateLabelFormatter());
		datePickerMatriculacion.setBounds(0, 59, 55, -59);
		panelMatriculacion.add(datePickerMatriculacion);

		btnInsertar = new JButton(Messages.getString("GuardarCamionDialogo.37")); //$NON-NLS-1$
		btnInsertar.setBounds(235, 340, 103, 25);
		getContentPane().add(btnInsertar);
	}

	public boolean camposObligatorios() {
		if (textFieldMatricula1.getText().isBlank() || textFieldMatricula2.getText().isBlank()
				|| txtMarca.getText().isBlank() || txtModelo.getText().isBlank() || txtTara.getText().isBlank())
			return false;
		else
			return true;
	}

	public JButton getBtnInsertar() {
		return btnInsertar;
	}

	public JComboBox<String> getComboBoxCombustible() {
		return comboBoxCombustible;
	}

	public JComboBox<String> getComboBoxNormativa() {
		return comboBoxNormativa;
	}

	public JDatePickerImpl getDatePickerMatriculacion() {
		return datePickerMatriculacion;
	}

	public JPanel getPanelMatriculacion() {
		return panelMatriculacion;
	}

	public JTextField getTextFieldEje2() {
		return textFieldEje2;
	}

	public JTextField getTextFieldMatricula1() {
		return textFieldMatricula1;
	}

	public JTextField getTextFieldMatricula2() {
		return textFieldMatricula2;
	}

	public JTextField getTxtEje() {
		return txtEje;
	}

	public JTextField getTxtKilometraje() {
		return txtKilometraje;
	}

	public JTextField getTxtMarca() {
		return txtMarca;
	}

	public JTextField getTxtModelo() {
		return txtModelo;
	}

	public JTextField getTxtPotencia() {
		return txtPotencia;
	}

	public JTextField getTxtTara() {
		return txtTara;
	}
}