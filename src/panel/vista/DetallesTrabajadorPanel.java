package panel.vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import constante.Messages;
import herramienta.AutoFitLabel;

public class DetallesTrabajadorPanel extends JPanel {
	private JButton btnContrato;
	private JButton btnDarBaja;
	private JTable textAreaEncargos;
	private JLabel textFieldApellidos;
	private JLabel textFieldDNI;
	private JLabel textFieldEstado;
	private JLabel textFieldFechaNacimiento;
	private JLabel textFieldID;
	private JLabel textFieldNombre;
	private JLabel textFieldPais;

	public DetallesTrabajadorPanel() {
		setBorder(new LineBorder(Color.GRAY));
		setLayout(null);

		// Text fields
		textFieldID = createTextField(74, 31, 71);
		textFieldNombre = createTextField(226, 72, 417);
		textFieldApellidos = createTextField(226, 114, 417);
		textFieldDNI = createTextField(226, 31, 183);
		textFieldPais = createTextField(477, 151, 166);
		textFieldEstado = createTextField(505, 31, 138);
		textFieldFechaNacimiento = createTextField(295, 150, 114);

		// Buttons
		btnContrato = createButton("Contrato", 153, 440, 138, 25);
		btnDarBaja = createButton("Dar Baja", 431, 440, 103, 25);

		// Labels
		addLabel("1", 42, 31);
		addLabel("2", 153, 72);
		addLabel("3", 153, 114);
		addLabel("4", 153, 158);
		addLabel("5", 178, 38);
		addLabel("6", 445, 38);
		addLabel("7", 431, 158);
		addLabel("8", 42, 195);

		// Text area
		addTextArea(42, 225, 600, 175);
	}

	private JLabel createTextField(int x, int y, int width) {
		JLabel textField = new AutoFitLabel("");
		textField.setBounds(x, y, width, 27);
		add(textField);
		return textField;
	}

	private JButton createButton(String text, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBounds(x, y, width, height);
		add(button);
		return button;
	}

	private void addLabel(String text, int x, int y) {
		JLabel label = new AutoFitLabel(Messages.getString("DetallesTrabajadorPanel." + text));
		label.setLocation(x, y);
		add(label);
	}

	private void addTextArea(int x, int y, int width, int height) {
		ScrollPane scroll = new ScrollPane();
		scroll.setSize(width, height);
		scroll.setLocation(x, y);

		textAreaEncargos = new JTable();
		textAreaEncargos.setBorder(new LineBorder(Color.LIGHT_GRAY));
		textAreaEncargos.setBounds(160, 219, 98, 98);
		textAreaEncargos.setDefaultEditor(Object.class, null);
		scroll.add(textAreaEncargos);
		add(scroll);
	}

	public JButton getBtnContrato() {
		return btnContrato;
	}

	public JButton getBtnDarBaja() {
		return btnDarBaja;
	}

	public JTable getTextAreaEncargos() {
		return textAreaEncargos;
	}

	public JLabel getTextFieldApellidos() {
		return textFieldApellidos;
	}

	public JLabel getTextFieldDNI() {
		return textFieldDNI;
	}

	public JLabel getTextFieldEstado() {
		return textFieldEstado;
	}

	public JLabel getTextFieldFechaNacimiento() {
		return textFieldFechaNacimiento;
	}

	public JLabel getTextFieldID() {
		return textFieldID;
	}

	public JLabel getTextFieldNombre() {
		return textFieldNombre;
	}

	public JLabel getTextFieldPais() {
		return textFieldPais;
	}
}