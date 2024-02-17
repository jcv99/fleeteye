package panel.vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import constante.Messages;

public class DetallesTrabajadorPanel extends JPanel {
	private JButton btnContrato;
	private JButton btnDarBaja;
	private JTextArea textAreaEncargos;
	private JTextField textFieldApellidos;
	private JTextField textFieldDNI;
	private JTextField textFieldEstado;
	private JTextField textFieldFechaNacimiento;
	private JTextField textFieldID;
	private JTextField textFieldNombre;
	private JTextField textFieldPais;

	public DetallesTrabajadorPanel() {

		setBorder(new LineBorder(Color.GRAY));
		setLayout(null);

		JLabel lblFoto = new JLabel(Messages.getString("VACIO")); //$NON-NLS-1$
		lblFoto.setBorder(new LineBorder(Color.GRAY));
		lblFoto.setBounds(42, 73, 86, 100);
		add(lblFoto);

		JLabel lblId = new JLabel(Messages.getString("DetallesTrabajadorPanel.1")); //$NON-NLS-1$
		lblId.setBounds(42, 38, 55, 15);
		add(lblId);

		JLabel lblNombre = new JLabel(Messages.getString("DetallesTrabajadorPanel.2")); //$NON-NLS-1$
		lblNombre.setBounds(153, 79, 55, 15);
		add(lblNombre);

		JLabel lblApellidos = new JLabel(Messages.getString("DetallesTrabajadorPanel.3")); //$NON-NLS-1$
		lblApellidos.setBounds(153, 121, 68, 15);
		add(lblApellidos);

		JLabel lblFechaNacimiento = new JLabel(Messages.getString("DetallesTrabajadorPanel.4")); //$NON-NLS-1$
		lblFechaNacimiento.setBounds(153, 158, 138, 15);
		add(lblFechaNacimiento);

		textFieldID = new JTextField();
		textFieldID.setEditable(false);
		textFieldID.setBounds(74, 31, 71, 27);
		add(textFieldID);
		textFieldID.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setDisabledTextColor(Color.WHITE);
		textFieldNombre.setEditable(false);
		textFieldNombre.setBounds(226, 72, 417, 27);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setEditable(false);
		textFieldApellidos.setBounds(226, 114, 417, 27);
		add(textFieldApellidos);
		textFieldApellidos.setColumns(10);

		JLabel lblDNI = new JLabel(Messages.getString("DetallesTrabajadorPanel.5")); //$NON-NLS-1$
		lblDNI.setBounds(178, 38, 55, 15);
		add(lblDNI);

		textFieldDNI = new JTextField();
		textFieldDNI.setEditable(false);
		textFieldDNI.setBounds(226, 31, 183, 27);
		add(textFieldDNI);
		textFieldDNI.setColumns(10);

		JLabel lblEstado = new JLabel(Messages.getString("DetallesTrabajadorPanel.6")); //$NON-NLS-1$
		lblEstado.setBounds(445, 38, 55, 15);
		add(lblEstado);

		textFieldEstado = new JTextField();
		textFieldEstado.setEditable(false);
		textFieldEstado.setBounds(505, 31, 138, 27);
		add(textFieldEstado);
		textFieldEstado.setColumns(10);

		textFieldFechaNacimiento = new JTextField();
		textFieldFechaNacimiento.setEditable(false);
		textFieldFechaNacimiento.setBounds(295, 150, 114, 27);
		add(textFieldFechaNacimiento);
		textFieldFechaNacimiento.setColumns(10);

		JLabel lblPais = new JLabel(Messages.getString("DetallesTrabajadorPanel.7")); //$NON-NLS-1$
		lblPais.setBounds(431, 158, 55, 15);
		add(lblPais);

		textFieldPais = new JTextField();
		textFieldPais.setEditable(false);
		textFieldPais.setBounds(477, 151, 166, 27);
		add(textFieldPais);
		textFieldPais.setColumns(10);

		JLabel lblEncargos = new JLabel(Messages.getString("DetallesTrabajadorPanel.8")); //$NON-NLS-1$
		lblEncargos.setBounds(42, 195, 55, 15);
		add(lblEncargos);

		btnContrato = new JButton(Messages.getString("DetallesTrabajadorPanel.9")); //$NON-NLS-1$
		btnContrato.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnContrato.setBounds(153, 440, 138, 25);
		add(btnContrato);

		btnDarBaja = new JButton(Messages.getString("DetallesTrabajadorPanel.10")); //$NON-NLS-1$
		btnDarBaja.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDarBaja.setBounds(431, 440, 103, 25);
		add(btnDarBaja);

		ScrollPane scroll = new ScrollPane();
		scroll.setSize(600, 175);
		scroll.setLocation(42, 225);
		textAreaEncargos = new JTextArea();

		textAreaEncargos.setWrapStyleWord(true);
		textAreaEncargos.setBorder(new LineBorder(Color.LIGHT_GRAY));
		textAreaEncargos.setEditable(false);
		textAreaEncargos.setBounds(160, 219, 98, 98);

		scroll.add(textAreaEncargos);
		add(scroll);
	}

	public JButton getBtnContrato() {
		return btnContrato;
	}

	public JButton getBtnDarBaja() {
		return btnDarBaja;
	}

	public JTextArea getTextAreaEncargos() {
		return textAreaEncargos;
	}

	public JTextField getTextFieldApellidos() {
		return textFieldApellidos;
	}

	public JTextField getTextFieldDNI() {
		return textFieldDNI;
	}

	public JTextField getTextFieldEstado() {
		return textFieldEstado;
	}

	public JTextField getTextFieldFechaNacimiento() {
		return textFieldFechaNacimiento;
	}

	public JTextField getTextFieldID() {
		return textFieldID;
	}

	public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}

	public JTextField getTextFieldPais() {
		return textFieldPais;
	}
}