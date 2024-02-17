package panel.vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import constante.Messages;

public class DetallesCamionPanel extends JPanel {

	private JButton btnAsignarRemolque;
	private JButton btnAsignarTrabajador;
	private JButton btnConsultarDocumentacion;
	private JComboBox<Object> comboBoxRemolque;
	private JComboBox<Object> comboBoxTrabajador;
	private JTextField textFieldCombustible;
	private JTextField textFieldMatricula;
	private JTextField textFieldMatriculacion;
	private JTextField textFieldNormativa;
	private JTextField txtEje;
	private JTextField txtKilometraje;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtPotencia;
	private JTextField txtTara;

	public DetallesCamionPanel() {
		setBorder(new LineBorder(Color.GRAY));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(12, 25, 540, 100);
		add(panel);
		panel.setLayout(null);

		JLabel lblMatricula = new JLabel(Messages.getString("DetallesCamionPanel.0")); //$NON-NLS-1$
		lblMatricula.setBounds(12, 22, 69, 15);
		panel.add(lblMatricula);

		textFieldMatricula = new JTextField();
		textFieldMatricula.setEditable(false);
		textFieldMatricula.setBounds(86, 16, 119, 27);
		panel.add(textFieldMatricula);
		textFieldMatricula.setColumns(10);

		JLabel lblMarca = new JLabel(Messages.getString("DetallesCamionPanel.1")); //$NON-NLS-1$
		lblMarca.setBounds(223, 22, 55, 15);
		panel.add(lblMarca);

		txtMarca = new JTextField();
		txtMarca.setEditable(false);
		txtMarca.setBounds(280, 16, 248, 27);
		panel.add(txtMarca);
		txtMarca.setColumns(10);

		JLabel lblModelo = new JLabel(Messages.getString("DetallesCamionPanel.2")); //$NON-NLS-1$
		lblModelo.setBounds(12, 62, 55, 15);
		panel.add(lblModelo);

		txtModelo = new JTextField();
		txtModelo.setEditable(false);
		txtModelo.setBounds(73, 56, 286, 27);
		panel.add(txtModelo);
		txtModelo.setColumns(10);

		JLabel lblTara = new JLabel(Messages.getString("DetallesCamionPanel.3")); //$NON-NLS-1$
		lblTara.setBounds(377, 62, 45, 15);
		panel.add(lblTara);

		txtTara = new JTextField();
		txtTara.setEditable(false);
		txtTara.setBounds(426, 56, 69, 27);
		panel.add(txtTara);
		txtTara.setColumns(10);

		JLabel lblTn = new JLabel(Messages.getString("DetallesCamionPanel.4")); //$NON-NLS-1$
		lblTn.setBounds(500, 62, 28, 15);
		panel.add(lblTn);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBounds(12, 137, 540, 169);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblCombustible = new JLabel(Messages.getString("DetallesCamionPanel.5")); //$NON-NLS-1$
		lblCombustible.setBounds(12, 25, 98, 15);
		panel_1.add(lblCombustible);

		textFieldCombustible = new JTextField();
		textFieldCombustible.setEditable(false);
		textFieldCombustible.setBounds(111, 20, 188, 27);
		panel_1.add(textFieldCombustible);

		JLabel lblPotencia = new JLabel(Messages.getString("DetallesCamionPanel.6")); //$NON-NLS-1$
		lblPotencia.setBounds(334, 25, 74, 15);
		panel_1.add(lblPotencia);

		txtPotencia = new JTextField();
		txtPotencia.setEditable(false);
		txtPotencia.setBounds(409, 19, 84, 27);
		panel_1.add(txtPotencia);
		txtPotencia.setColumns(10);

		JLabel lblCv = new JLabel(Messages.getString("DetallesCamionPanel.7")); //$NON-NLS-1$
		lblCv.setBounds(500, 25, 28, 15);
		panel_1.add(lblCv);

		JLabel lblNormativa = new JLabel(Messages.getString("DetallesCamionPanel.8")); //$NON-NLS-1$
		lblNormativa.setBounds(12, 76, 84, 15);
		panel_1.add(lblNormativa);

		textFieldNormativa = new JTextField();
		textFieldNormativa.setEditable(false);
		textFieldNormativa.setBounds(97, 71, 166, 27);
		panel_1.add(textFieldNormativa);

		JLabel lblKilometraje = new JLabel(Messages.getString("DetallesCamionPanel.9")); //$NON-NLS-1$
		lblKilometraje.setBounds(281, 76, 98, 15);
		panel_1.add(lblKilometraje);

		txtKilometraje = new JTextField();
		txtKilometraje.setEditable(false);
		txtKilometraje.setBounds(370, 70, 123, 27);
		panel_1.add(txtKilometraje);
		txtKilometraje.setColumns(10);

		JLabel lblKm = new JLabel(Messages.getString("DetallesCamionPanel.10")); //$NON-NLS-1$
		lblKm.setBounds(500, 76, 28, 15);
		panel_1.add(lblKm);

		JLabel lblConfiguracionEje = new JLabel(Messages.getString("DetallesCamionPanel.11")); //$NON-NLS-1$
		lblConfiguracionEje.setBounds(12, 131, 135, 15);
		panel_1.add(lblConfiguracionEje);

		txtEje = new JTextField();
		txtEje.setEditable(false);
		txtEje.setBounds(137, 125, 79, 27);
		panel_1.add(txtEje);
		txtEje.setColumns(10);

		JLabel lblMatriculacion = new JLabel(Messages.getString("DetallesCamionPanel.12")); //$NON-NLS-1$
		lblMatriculacion.setBounds(234, 131, 94, 15);
		panel_1.add(lblMatriculacion);

		textFieldMatriculacion = new JTextField();
		textFieldMatriculacion.setEditable(false);
		textFieldMatriculacion.setColumns(10);
		textFieldMatriculacion.setBounds(340, 125, 174, 27);
		panel_1.add(textFieldMatriculacion);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 328, 540, 49);
		add(panel_2);
		panel_2.setLayout(null);

		comboBoxRemolque = new JComboBox<Object>();
		comboBoxRemolque.setBounds(12, 12, 356, 27);
		panel_2.add(comboBoxRemolque);

		btnAsignarRemolque = new JButton(Messages.getString("DetallesCamionPanel.13")); //$NON-NLS-1$
		btnAsignarRemolque.setBounds(378, 13, 152, 25);
		panel_2.add(btnAsignarRemolque);

		btnConsultarDocumentacion = new JButton(Messages.getString("DetallesCamionPanel.14")); //$NON-NLS-1$
		btnConsultarDocumentacion.setBounds(178, 468, 212, 25);
		add(btnConsultarDocumentacion);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(12, 389, 540, 49);
		add(panel_2_1);

		comboBoxTrabajador = new JComboBox<Object>();
		comboBoxTrabajador.setBounds(12, 12, 353, 27);
		panel_2_1.add(comboBoxTrabajador);

		btnAsignarTrabajador = new JButton(Messages.getString("DetallesCamionPanel.15")); //$NON-NLS-1$
		btnAsignarTrabajador.setBounds(375, 13, 155, 25);
		panel_2_1.add(btnAsignarTrabajador);
	}

	public JButton getBtnAsignarRemolque() {
		return btnAsignarRemolque;
	}

	public JButton getBtnAsignarTrabajador() {
		return btnAsignarTrabajador;
	}

	public JButton getBtnConsultarDocumentacion() {
		return btnConsultarDocumentacion;
	}

	public JComboBox<Object> getComboBoxRemolque() {
		return comboBoxRemolque;
	}

	public JComboBox<Object> getComboBoxTrabajador() {
		return comboBoxTrabajador;
	}

	public JTextField getTextFieldCombustible() {
		return textFieldCombustible;
	}

	public JTextField getTextFieldMatricula() {
		return textFieldMatricula;
	}

	public JTextField getTextFieldMatriculacion() {
		return textFieldMatriculacion;
	}

	public JTextField getTextFieldNormativa() {
		return textFieldNormativa;
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