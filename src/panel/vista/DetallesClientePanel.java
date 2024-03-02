package panel.vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import constante.Messages;

public class DetallesClientePanel extends JPanel {

	private JButton btnConsultaDato;
	private JButton btnGuardarPresupuesto;
	private JTable tableEncargos;
	private JTable tablePresupuestos;
	private JTextField textFieldActividadEco;
	private JTextField textFieldFechaAlta;
	private JTextField textFieldID;
	private JTextField textFieldNIF;
	private JTextField textFieldRazonSocial;

	public DetallesClientePanel() {
		setBorder(new LineBorder(Color.GRAY));
		setLayout(null);

		JLabel lblId = new JLabel(Messages.getString("DetallesClientePanel.0")); //$NON-NLS-1$
		lblId.setBounds(42, 38, 55, 15);
		add(lblId);

		JLabel lblRazonSocial = new JLabel(Messages.getString("DetallesClientePanel.1")); //$NON-NLS-1$
		lblRazonSocial.setBounds(42, 78, 86, 15);
		add(lblRazonSocial);

		JLabel lblActividadEconomica = new JLabel(Messages.getString("DetallesClientePanel.2")); //$NON-NLS-1$
		lblActividadEconomica.setBounds(42, 120, 138, 15);
		add(lblActividadEconomica);

		textFieldID = new JTextField();
		textFieldID.setEditable(false);
		textFieldID.setBounds(74, 31, 55, 27);
		add(textFieldID);
		textFieldID.setColumns(10);

		textFieldRazonSocial = new JTextField();
		textFieldRazonSocial.setDisabledTextColor(Color.WHITE);
		textFieldRazonSocial.setEditable(false);
		textFieldRazonSocial.setBounds(146, 72, 497, 27);
		add(textFieldRazonSocial);
		textFieldRazonSocial.setColumns(10);

		textFieldActividadEco = new JTextField();
		textFieldActividadEco.setEditable(false);
		textFieldActividadEco.setBounds(198, 114, 445, 27);
		add(textFieldActividadEco);
		textFieldActividadEco.setColumns(10);

		JLabel lblNIF = new JLabel(Messages.getString("DetallesClientePanel.3")); //$NON-NLS-1$
		lblNIF.setBounds(147, 38, 86, 15);
		add(lblNIF);

		textFieldNIF = new JTextField();
		textFieldNIF.setEditable(false);
		textFieldNIF.setBounds(237, 32, 183, 27);
		add(textFieldNIF);
		textFieldNIF.setColumns(10);

		JLabel lblEncargos = new JLabel(Messages.getString("DetallesClientePanel.4")); //$NON-NLS-1$
		lblEncargos.setBounds(42, 171, 55, 15);
		add(lblEncargos);

		JScrollPane scrollEncargos = new JScrollPane();
		scrollEncargos.setSize(600, 150);
		scrollEncargos.setLocation(42, 200);

		tableEncargos = new JTable();

		tableEncargos.setBorder(new LineBorder(Color.LIGHT_GRAY));
		tableEncargos.setBounds(160, 219, 98, 98);
		tableEncargos.setDefaultEditor(Object.class, null);
		scrollEncargos.setViewportView(tableEncargos);
		add(scrollEncargos);

		JScrollPane scrollPresupuestos = new JScrollPane();
		scrollPresupuestos.setSize(600, 150);
		scrollPresupuestos.setLocation(42, 400);

		tablePresupuestos = new JTable();

		tablePresupuestos.setBorder(new LineBorder(Color.LIGHT_GRAY));
		tablePresupuestos.setBounds(160, 219, 98, 98);
		tablePresupuestos.setDefaultEditor(Object.class, null);
		scrollPresupuestos.setViewportView(tablePresupuestos);
		add(scrollPresupuestos);

		JLabel lblPresupuestos = new JLabel(Messages.getString("DetallesClientePanel.5")); //$NON-NLS-1$
		lblPresupuestos.setBounds(42, 368, 86, 15);
		add(lblPresupuestos);

		btnConsultaDato = new JButton(Messages.getString("DetallesClientePanel.6")); //$NON-NLS-1$
		btnConsultaDato.setBounds(120, 621, 138, 25);
		add(btnConsultaDato);

		btnGuardarPresupuesto = new JButton(Messages.getString("DetallesClientePanel.7")); //$NON-NLS-1$
		btnGuardarPresupuesto.setBounds(368, 621, 168, 25);
		add(btnGuardarPresupuesto);
	}

	public JButton getBtnConsultaDato() {
		return btnConsultaDato;
	}

	public JButton getBtnGuardarPresupuesto() {
		return btnGuardarPresupuesto;
	}

	public JTable getTableEncargos() {
		return tableEncargos;
	}

	public JTable getTablePresupuestos() {
		return tablePresupuestos;
	}

	public JTextField getTextFieldActividadEco() {
		return textFieldActividadEco;
	}

	public JTextField getTextFieldID() {
		return textFieldID;
	}

	public JTextField getTextFieldNIF() {
		return textFieldNIF;
	}

	public JTextField getTextFieldRazonSocial() {
		return textFieldRazonSocial;
	}
}