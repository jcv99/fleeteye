package panel.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import constante.Messages;
public class DetallesEncargoPanel extends JPanel {
	
	private JButton btnAsignarCamion;
	private JButton btnDetallesCamion;
	private JButton btnDetallesPresupuesto;
	private JButton btnFinalizarEncargo;
	private JButton btnIniciarEncargo;
	private JTextField textFieldApellidos;
	private JTextField textFieldCliente;
	private JTextField textFieldDestino;
	private JTextField textFieldDistancia;
	private JTextField textFieldDNI;
	private JTextField textFieldEstado;
	private JTextField textFieldFechaEntrega;
	private JTextField textFieldFechaFin;
	private JTextField textFieldFechaInicio;
	private JTextField textFieldID;
	private JTextField textFieldIdCamion;
	private JTextField textFieldIdPresupuesto;
	private JTextField textFieldMercancia;
	private JTextField textFieldNombre;
	private JTextField textFieldOrigen;
	private JTextField textFieldPeso;
	private JTextField textFieldPrecio;
	private JTextField textFieldRemolque;
	private JTextField textFieldTractora;
	
	public DetallesEncargoPanel() {
		setBorder(new LineBorder(Color.GRAY));
		setLayout(null);
		
		JLabel lblId = new JLabel(Messages.getString("DetallesEncargoPanel.0")); //$NON-NLS-1$
		lblId.setBounds(40, 38, 55, 15);
		add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setEditable(false);
		textFieldID.setBounds(74, 31, 55, 27);
		add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldCliente = new JTextField();
		textFieldCliente.setDisabledTextColor(Color.WHITE);
		textFieldCliente.setEditable(false);
		textFieldCliente.setBounds(108, 72, 492, 27);
		add(textFieldCliente);
		textFieldCliente.setColumns(10);
		
		JLabel lblEstado = new JLabel(Messages.getString("DetallesEncargoPanel.1")); //$NON-NLS-1$
		lblEstado.setBounds(147, 38, 86, 15);
		add(lblEstado);
		
		textFieldEstado = new JTextField();
		textFieldEstado.setEditable(false);
		textFieldEstado.setBounds(210, 32, 123, 27);
		add(textFieldEstado);
		textFieldEstado.setColumns(10);
		
		JLabel lblPresupuesto = new JLabel(Messages.getString("DetallesEncargoPanel.2")); //$NON-NLS-1$
		lblPresupuesto.setFont(new Font(Messages.getString("DetallesEncargoPanel.3"), Font.BOLD, 13)); //$NON-NLS-1$
		lblPresupuesto.setBounds(25, 183, 86, 15);
		add(lblPresupuesto);
		
		JLabel lblFechaEntrega = new JLabel(Messages.getString("DetallesEncargoPanel.4")); //$NON-NLS-1$
		lblFechaEntrega.setBounds(360, 38, 113, 15);
		add(lblFechaEntrega);
		
		textFieldFechaEntrega = new JTextField();
		textFieldFechaEntrega.setEditable(false);
		textFieldFechaEntrega.setBounds(480, 32, 114, 27);
		add(textFieldFechaEntrega);
		textFieldFechaEntrega.setColumns(10);
		
		JLabel lblCamion = new JLabel(Messages.getString("DetallesEncargoPanel.5")); //$NON-NLS-1$
		lblCamion.setFont(new Font(Messages.getString("DetallesEncargoPanel.6"), Font.BOLD, 13)); //$NON-NLS-1$
		lblCamion.setBounds(25, 394, 86, 15);
		add(lblCamion);
		
		btnDetallesPresupuesto = new JButton(Messages.getString("DetallesEncargoPanel.7"));		 //$NON-NLS-1$
		btnDetallesPresupuesto.setBounds(129, 178, 86, 25);
		add(btnDetallesPresupuesto);
		
		btnFinalizarEncargo = new JButton(Messages.getString("DetallesEncargoPanel.8")); //$NON-NLS-1$
		btnFinalizarEncargo.setBounds(348, 664, 168, 25);
		add(btnFinalizarEncargo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(25, 22, 587, 137);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblFechaDeInicio = new JLabel(Messages.getString("DetallesEncargoPanel.9")); //$NON-NLS-1$
		lblFechaDeInicio.setBounds(12, 99, 104, 15);
		panel.add(lblFechaDeInicio);
		
		JLabel lblFechaDeFin = new JLabel(Messages.getString("DetallesEncargoPanel.10")); //$NON-NLS-1$
		lblFechaDeFin.setBounds(303, 99, 93, 15);
		panel.add(lblFechaDeFin);
		
		textFieldFechaInicio = new JTextField();
		textFieldFechaInicio.setEditable(false);
		textFieldFechaInicio.setColumns(10);
		textFieldFechaInicio.setBounds(112, 93, 183, 27);
		panel.add(textFieldFechaInicio);
		
		textFieldFechaFin = new JTextField();
		textFieldFechaFin.setEditable(false);
		textFieldFechaFin.setColumns(10);
		textFieldFechaFin.setBounds(392, 93, 183, 27);
		panel.add(textFieldFechaFin);
		
		JLabel lblCliente = new JLabel(Messages.getString("DetallesEncargoPanel.11")); //$NON-NLS-1$
		lblCliente.setBounds(12, 55, 86, 15);
		panel.add(lblCliente);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBounds(25, 210, 587, 165);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblId_1 = new JLabel(Messages.getString("DetallesEncargoPanel.12")); //$NON-NLS-1$
		lblId_1.setBounds(12, 18, 55, 15);
		panel_1.add(lblId_1);
		
		textFieldIdPresupuesto = new JTextField();
		textFieldIdPresupuesto.setEditable(false);
		textFieldIdPresupuesto.setColumns(10);
		textFieldIdPresupuesto.setBounds(46, 12, 55, 27);
		panel_1.add(textFieldIdPresupuesto);
		
		JLabel lblOrigen = new JLabel(Messages.getString("DetallesEncargoPanel.13")); //$NON-NLS-1$
		lblOrigen.setBounds(12, 65, 55, 15);
		panel_1.add(lblOrigen);
		
		textFieldOrigen = new JTextField();
		textFieldOrigen.setEditable(false);
		textFieldOrigen.setColumns(10);
		textFieldOrigen.setBounds(72, 59, 250, 27);
		panel_1.add(textFieldOrigen);
		
		textFieldDestino = new JTextField();
		textFieldDestino.setEditable(false);
		textFieldDestino.setColumns(10);
		textFieldDestino.setBounds(72, 114, 250, 27);
		panel_1.add(textFieldDestino);
		
		textFieldMercancia = new JTextField();
		textFieldMercancia.setEditable(false);
		textFieldMercancia.setColumns(10);
		textFieldMercancia.setBounds(198, 12, 226, 27);
		panel_1.add(textFieldMercancia);
		
		textFieldPeso = new JTextField();
		textFieldPeso.setEditable(false);
		textFieldPeso.setColumns(10);
		textFieldPeso.setBounds(486, 12, 62, 27);
		panel_1.add(textFieldPeso);
		
		JLabel lblDestino = new JLabel(Messages.getString("DetallesEncargoPanel.14")); //$NON-NLS-1$
		lblDestino.setBounds(12, 120, 55, 15);
		panel_1.add(lblDestino);
		
		JLabel lblMercancia = new JLabel(Messages.getString("DetallesEncargoPanel.15")); //$NON-NLS-1$
		lblMercancia.setBounds(119, 18, 91, 15);
		panel_1.add(lblMercancia);
		
		JLabel lblId_1_1_3 = new JLabel(Messages.getString("DetallesEncargoPanel.16")); //$NON-NLS-1$
		lblId_1_1_3.setBounds(442, 18, 55, 15);
		panel_1.add(lblId_1_1_3);
		
		JLabel lblTn = new JLabel(Messages.getString("DetallesEncargoPanel.17")); //$NON-NLS-1$
		lblTn.setBounds(556, 18, 55, 15);
		panel_1.add(lblTn);
		
		JLabel lblPrecio = new JLabel(Messages.getString("DetallesEncargoPanel.18")); //$NON-NLS-1$
		lblPrecio.setBounds(340, 65, 55, 15);
		panel_1.add(lblPrecio);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setEditable(false);
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(398, 59, 99, 27);
		panel_1.add(textFieldPrecio);
		
		textFieldDistancia = new JTextField();
		textFieldDistancia.setEditable(false);
		textFieldDistancia.setColumns(10);
		textFieldDistancia.setBounds(416, 114, 114, 27);
		panel_1.add(textFieldDistancia);
		
		JLabel lblDistancia = new JLabel(Messages.getString("DetallesEncargoPanel.19")); //$NON-NLS-1$
		lblDistancia.setBounds(340, 120, 69, 15);
		panel_1.add(lblDistancia);
		
		JLabel lblEuros = new JLabel(Messages.getString("DetallesEncargoPanel.20")); //$NON-NLS-1$
		lblEuros.setBounds(503, 65, 55, 15);
		panel_1.add(lblEuros);
		
		JLabel lblKm = new JLabel(Messages.getString("DetallesEncargoPanel.21")); //$NON-NLS-1$
		lblKm.setBounds(542, 120, 55, 15);
		panel_1.add(lblKm);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(25, 426, 587, 200);
		add(panel_1_1);
		
		JLabel lblId_1_1 = new JLabel(Messages.getString("DetallesEncargoPanel.22")); //$NON-NLS-1$
		lblId_1_1.setBounds(12, 18, 55, 15);
		panel_1_1.add(lblId_1_1);
		
		textFieldIdCamion = new JTextField();
		textFieldIdCamion.setEditable(false);
		textFieldIdCamion.setColumns(10);
		textFieldIdCamion.setBounds(46, 12, 55, 27);
		panel_1_1.add(textFieldIdCamion);
		
		textFieldTractora = new JTextField();
		textFieldTractora.setEditable(false);
		textFieldTractora.setColumns(10);
		textFieldTractora.setBounds(198, 12, 142, 27);
		panel_1_1.add(textFieldTractora);
		
		textFieldRemolque = new JTextField();
		textFieldRemolque.setEditable(false);
		textFieldRemolque.setColumns(10);
		textFieldRemolque.setBounds(433, 12, 142, 27);
		panel_1_1.add(textFieldRemolque);
		
		JLabel lblTractora = new JLabel(Messages.getString("DetallesEncargoPanel.23")); //$NON-NLS-1$
		lblTractora.setBounds(119, 18, 91, 15);
		panel_1_1.add(lblTractora);
		
		JLabel lblRemolque = new JLabel(Messages.getString("DetallesEncargoPanel.24")); //$NON-NLS-1$
		lblRemolque.setBounds(358, 18, 68, 15);
		panel_1_1.add(lblRemolque);
		
		JLabel lblChofer = new JLabel(Messages.getString("DetallesEncargoPanel.25")); //$NON-NLS-1$
		lblChofer.setBounds(12, 51, 55, 15);
		panel_1_1.add(lblChofer);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_2.setBounds(12, 78, 563, 110);
		panel_1_1.add(panel_2);
		panel_2.setLayout(null);
		
		textFieldApellidos = new JTextField();
		textFieldApellidos.setBounds(86, 64, 465, 27);
		panel_2.add(textFieldApellidos);
		textFieldApellidos.setEditable(false);
		textFieldApellidos.setColumns(10);
		
		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(54, 24, 163, 27);
		panel_2.add(textFieldDNI);
		textFieldDNI.setEditable(false);
		textFieldDNI.setColumns(10);
		
		JLabel lblDNI = new JLabel(Messages.getString("DetallesEncargoPanel.26")); //$NON-NLS-1$
		lblDNI.setBounds(12, 30, 55, 15);
		panel_2.add(lblDNI);
		
		JLabel lblNombre = new JLabel(Messages.getString("DetallesEncargoPanel.27")); //$NON-NLS-1$
		lblNombre.setBounds(235, 30, 55, 15);
		panel_2.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(308, 24, 243, 27);
		panel_2.add(textFieldNombre);
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel(Messages.getString("DetallesEncargoPanel.28")); //$NON-NLS-1$
		lblApellidos.setBounds(12, 70, 79, 15);
		panel_2.add(lblApellidos);
		
		btnDetallesCamion = new JButton(Messages.getString("DetallesEncargoPanel.29")); //$NON-NLS-1$
		btnDetallesCamion.setBounds(93, 389, 86, 25);
		add(btnDetallesCamion);
		
		btnAsignarCamion = new JButton(Messages.getString("DetallesEncargoPanel.30"));		 //$NON-NLS-1$
		btnAsignarCamion.setBounds(210, 389, 142, 25);
		add(btnAsignarCamion);
		
		btnIniciarEncargo = new JButton(Messages.getString("DetallesEncargoPanel.31")); //$NON-NLS-1$
		btnIniciarEncargo.setBounds(108, 664, 168, 25);
		add(btnIniciarEncargo);
	}
	public JButton getBtnAsignarCamion() {
		return btnAsignarCamion;
	}
	public JButton getBtnDetallesCamion() {
		return btnDetallesCamion;
	}
	public JButton getBtnDetallesPresupuesto() {
		return btnDetallesPresupuesto;
	}
	public JButton getBtnFinalizarEncargo() {
		return btnFinalizarEncargo;
	}
	public JButton getBtnIniciarEncargo() {
		return btnIniciarEncargo;
	}
	public JTextField getTextFieldApellidos() {
		return textFieldApellidos;
	}
	public JTextField getTextFieldCliente() {
		return textFieldCliente;
	}
	public JTextField getTextFieldDestino() {
		return textFieldDestino;
	}
	public JTextField getTextFieldDistancia() {
		return textFieldDistancia;
	}
	public JTextField getTextFieldDNI() {
		return textFieldDNI;
	}
	public JTextField getTextFieldEstado() {
		return textFieldEstado;
	}
	public JTextField getTextFieldFechaEntrega() {
		return textFieldFechaEntrega;
	}
	public JTextField getTextFieldFechaFin() {
		return textFieldFechaFin;
	}
	public JTextField getTextFieldFechaInicio() {
		return textFieldFechaInicio;
	}
	public JTextField getTextFieldID() {
		return textFieldID;
	}
	public JTextField getTextFieldIdCamion() {
		return textFieldIdCamion;
	}
	public JTextField getTextFieldIdPresupuesto() {
		return textFieldIdPresupuesto;
	}
	public JTextField getTextFieldMercancia() {
		return textFieldMercancia;
	}
	public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}
	public JTextField getTextFieldOrigen() {
		return textFieldOrigen;
	}
	public JTextField getTextFieldPeso() {
		return textFieldPeso;
	}
	public JTextField getTextFieldPrecio() {
		return textFieldPrecio;
	}
	public JTextField getTextFieldRemolque() {
		return textFieldRemolque;
	}
	public JTextField getTextFieldTractora() {
		return textFieldTractora;
	}
}