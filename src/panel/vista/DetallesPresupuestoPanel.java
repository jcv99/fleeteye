package panel.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import constante.Messages;
public class DetallesPresupuestoPanel extends JPanel {
    
	private JButton btnGenerarEncargo;
	private JCheckBox chckbxAbrePorArriba;
	private JCheckBox chckbxCinchas;
	private JCheckBox chckbxEngancheDeRemolque;
	private JTextField textFieldCapacidadPalettes;
	private JTextField textFieldClase;
	private JTextField textFieldCliente;
	private JTextField textFieldCodigoPostalDestino;
	private JTextField textFieldCodigoPostalOrigen;
	private JTextField textFieldDimensiones;
	private JTextField textFieldDireccionDestino;
	private JTextField textFieldDireccionOrigen;
	private JTextField textFieldDistancia;
	private JTextField textFieldID;
	private JTextField textFieldLocalidadDestino;
	private JTextField textFieldLocalidadOrigen;
	private JTextField textFieldMercancia;
	private JTextField textFieldPaisDestino;
	private JTextField textFieldPaisOrigen;
	private JTextField textFieldPeso;
	private JTextField textFieldPrecio;
	private JTextField textFieldProvinciaDestino;
	private JTextField textFieldProvinciaOrigen;
	private JTextField textFieldVolumen;
	
	public DetallesPresupuestoPanel() {
		setBorder(new LineBorder(Color.GRAY));
		setLayout(null);
		
		textFieldMercancia = new JTextField();
		textFieldMercancia.setDisabledTextColor(Color.WHITE);
		textFieldMercancia.setEditable(false);
		textFieldMercancia.setBounds(108, 72, 600, 27);
		add(textFieldMercancia);
		textFieldMercancia.setColumns(10);
		
		textFieldCliente = new JTextField();
		textFieldCliente.setEditable(false);
		textFieldCliente.setBounds(88, 114, 620, 27);
		add(textFieldCliente);
		textFieldCliente.setColumns(10);
		
		JLabel lblDistancia = new JLabel(Messages.getString("DetallesPresupuestoPanel.0")); //$NON-NLS-1$
		lblDistancia.setBounds(338, 38, 75, 15);
		add(lblDistancia);
		
		textFieldDistancia = new JTextField();
		textFieldDistancia.setEditable(false);
		textFieldDistancia.setBounds(414, 32, 86, 27);
		add(textFieldDistancia);
		textFieldDistancia.setColumns(10);
		
		btnGenerarEncargo = new JButton(Messages.getString("DetallesPresupuestoPanel.1"));		 //$NON-NLS-1$
		btnGenerarEncargo.setBounds(288, 738, 138, 25);
		add(btnGenerarEncargo);
		
		JLabel lblMoneda = new JLabel(Messages.getString("DetallesPresupuestoPanel.2")); //$NON-NLS-1$
		lblMoneda.setBounds(308, 38, 33, 15);
		add(lblMoneda);
		
		JLabel lblKm = new JLabel(Messages.getString("DetallesPresupuestoPanel.3")); //$NON-NLS-1$
		lblKm.setBounds(506, 38, 33, 15);
		add(lblKm);
		
		JLabel lblPeso = new JLabel(Messages.getString("DetallesPresupuestoPanel.4")); //$NON-NLS-1$
		lblPeso.setBounds(551, 38, 55, 15);
		add(lblPeso);
		
		textFieldPeso = new JTextField();
		textFieldPeso.setEditable(false);
		textFieldPeso.setColumns(10);
		textFieldPeso.setBounds(598, 32, 86, 27);
		add(textFieldPeso);
		
		JLabel lblTn = new JLabel(Messages.getString("DetallesPresupuestoPanel.5")); //$NON-NLS-1$
		lblTn.setBounds(690, 38, 33, 15);
		add(lblTn);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(12, 12, 719, 149);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel(Messages.getString("DetallesPresupuestoPanel.6")); //$NON-NLS-1$
		lblId.setBounds(12, 26, 18, 15);
		panel.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(44, 20, 55, 27);
		panel.add(textFieldID);
		textFieldID.setEditable(false);
		textFieldID.setColumns(10);
		
		JLabel lblPrecio = new JLabel(Messages.getString("DetallesPresupuestoPanel.7")); //$NON-NLS-1$
		lblPrecio.setBounds(117, 26, 55, 15);
		panel.add(lblPrecio);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBounds(176, 20, 114, 27);
		panel.add(textFieldPrecio);
		textFieldPrecio.setEditable(false);
		textFieldPrecio.setColumns(10);
		
		JLabel lblMercancia = new JLabel(Messages.getString("DetallesPresupuestoPanel.8")); //$NON-NLS-1$
		lblMercancia.setBounds(12, 66, 86, 15);
		panel.add(lblMercancia);
		
		JLabel lblRazonSocialCliente = new JLabel(Messages.getString("DetallesPresupuestoPanel.9")); //$NON-NLS-1$
		lblRazonSocialCliente.setBounds(12, 108, 138, 15);
		panel.add(lblRazonSocialCliente);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBounds(12, 198, 719, 149);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDireccion = new JLabel(Messages.getString("DetallesPresupuestoPanel.10")); //$NON-NLS-1$
		lblDireccion.setBounds(12, 16, 83, 15);
		panel_1.add(lblDireccion);
		
		textFieldDireccionOrigen = new JTextField();
		textFieldDireccionOrigen.setEditable(false);
		textFieldDireccionOrigen.setColumns(10);
		textFieldDireccionOrigen.setBounds(87, 10, 614, 27);
		panel_1.add(textFieldDireccionOrigen);
		
		JLabel lblLocalidad = new JLabel(Messages.getString("DetallesPresupuestoPanel.11")); //$NON-NLS-1$
		lblLocalidad.setBounds(12, 59, 70, 15);
		panel_1.add(lblLocalidad);
		
		textFieldLocalidadOrigen = new JTextField();
		textFieldLocalidadOrigen.setEditable(false);
		textFieldLocalidadOrigen.setColumns(10);
		textFieldLocalidadOrigen.setBounds(87, 53, 350, 27);
		panel_1.add(textFieldLocalidadOrigen);
		
		JLabel lblCodigoPostal = new JLabel(Messages.getString("DetallesPresupuestoPanel.12")); //$NON-NLS-1$
		lblCodigoPostal.setBounds(455, 59, 107, 15);
		panel_1.add(lblCodigoPostal);
		
		textFieldCodigoPostalOrigen = new JTextField();
		textFieldCodigoPostalOrigen.setEditable(false);
		textFieldCodigoPostalOrigen.setColumns(10);
		textFieldCodigoPostalOrigen.setBounds(565, 53, 136, 27);
		panel_1.add(textFieldCodigoPostalOrigen);
		
		JLabel lblProvincia = new JLabel(Messages.getString("DetallesPresupuestoPanel.13")); //$NON-NLS-1$
		lblProvincia.setBounds(12, 107, 70, 15);
		panel_1.add(lblProvincia);
		
		textFieldProvinciaOrigen = new JTextField();
		textFieldProvinciaOrigen.setEditable(false);
		textFieldProvinciaOrigen.setColumns(10);
		textFieldProvinciaOrigen.setBounds(87, 101, 350, 27);
		panel_1.add(textFieldProvinciaOrigen);
		
		JLabel lblPais = new JLabel(Messages.getString("DetallesPresupuestoPanel.14")); //$NON-NLS-1$
		lblPais.setBounds(455, 107, 55, 15);
		panel_1.add(lblPais);
		
		textFieldPaisOrigen = new JTextField();
		textFieldPaisOrigen.setEditable(false);
		textFieldPaisOrigen.setColumns(10);
		textFieldPaisOrigen.setBounds(503, 101, 198, 27);
		panel_1.add(textFieldPaisOrigen);
		
		JLabel lblOrigen = new JLabel(Messages.getString("DetallesPresupuestoPanel.15")); //$NON-NLS-1$
		lblOrigen.setFont(new Font(Messages.getString("DetallesPresupuestoPanel.16"), Font.BOLD, 13)); //$NON-NLS-1$
		lblOrigen.setBounds(22, 171, 75, 15);
		add(lblOrigen);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1_1.setBounds(12, 386, 719, 149);
		add(panel_1_1);
		
		JLabel lblDireccion_1 = new JLabel(Messages.getString("DetallesPresupuestoPanel.17")); //$NON-NLS-1$
		lblDireccion_1.setBounds(12, 16, 83, 15);
		panel_1_1.add(lblDireccion_1);
		
		textFieldDireccionDestino = new JTextField();
		textFieldDireccionDestino.setEditable(false);
		textFieldDireccionDestino.setColumns(10);
		textFieldDireccionDestino.setBounds(87, 10, 614, 27);
		panel_1_1.add(textFieldDireccionDestino);
		
		JLabel lblLocalidad_1 = new JLabel(Messages.getString("DetallesPresupuestoPanel.18")); //$NON-NLS-1$
		lblLocalidad_1.setBounds(12, 59, 70, 15);
		panel_1_1.add(lblLocalidad_1);
		
		textFieldLocalidadDestino = new JTextField();
		textFieldLocalidadDestino.setEditable(false);
		textFieldLocalidadDestino.setColumns(10);
		textFieldLocalidadDestino.setBounds(87, 53, 350, 27);
		panel_1_1.add(textFieldLocalidadDestino);
		
		JLabel lblCodigoPostal_1 = new JLabel(Messages.getString("DetallesPresupuestoPanel.19")); //$NON-NLS-1$
		lblCodigoPostal_1.setBounds(455, 59, 107, 15);
		panel_1_1.add(lblCodigoPostal_1);
		
		textFieldCodigoPostalDestino = new JTextField();
		textFieldCodigoPostalDestino.setEditable(false);
		textFieldCodigoPostalDestino.setColumns(10);
		textFieldCodigoPostalDestino.setBounds(565, 53, 136, 27);
		panel_1_1.add(textFieldCodigoPostalDestino);
		
		JLabel lblProvincia_1 = new JLabel(Messages.getString("DetallesPresupuestoPanel.20")); //$NON-NLS-1$
		lblProvincia_1.setBounds(12, 107, 70, 15);
		panel_1_1.add(lblProvincia_1);
		
		textFieldProvinciaDestino = new JTextField();
		textFieldProvinciaDestino.setEditable(false);
		textFieldProvinciaDestino.setColumns(10);
		textFieldProvinciaDestino.setBounds(87, 101, 350, 27);
		panel_1_1.add(textFieldProvinciaDestino);
		
		JLabel lblPais_1 = new JLabel(Messages.getString("DetallesPresupuestoPanel.21")); //$NON-NLS-1$
		lblPais_1.setBounds(455, 107, 55, 15);
		panel_1_1.add(lblPais_1);
		
		textFieldPaisDestino = new JTextField();
		textFieldPaisDestino.setEditable(false);
		textFieldPaisDestino.setColumns(10);
		textFieldPaisDestino.setBounds(503, 101, 198, 27);
		panel_1_1.add(textFieldPaisDestino);
		
		JLabel lblDestino = new JLabel(Messages.getString("DetallesPresupuestoPanel.22")); //$NON-NLS-1$
		lblDestino.setFont(new Font(Messages.getString("DetallesPresupuestoPanel.23"), Font.BOLD, 13)); //$NON-NLS-1$
		lblDestino.setBounds(22, 359, 75, 15);
		add(lblDestino);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1_1_1.setBounds(12, 574, 719, 141);
		add(panel_1_1_1);
		
		JLabel lblClase = new JLabel(Messages.getString("DetallesPresupuestoPanel.24")); //$NON-NLS-1$
		lblClase.setBounds(12, 16, 83, 15);
		panel_1_1_1.add(lblClase);
		
		textFieldClase = new JTextField();
		textFieldClase.setEditable(false);
		textFieldClase.setColumns(10);
		textFieldClase.setBounds(87, 10, 271, 27);
		panel_1_1_1.add(textFieldClase);
		
		JLabel lblVolumen = new JLabel(Messages.getString("DetallesPresupuestoPanel.25")); //$NON-NLS-1$
		lblVolumen.setBounds(12, 57, 70, 15);
		panel_1_1_1.add(lblVolumen);
		
		textFieldVolumen = new JTextField();
		textFieldVolumen.setEditable(false);
		textFieldVolumen.setColumns(10);
		textFieldVolumen.setBounds(87, 51, 186, 27);
		panel_1_1_1.add(textFieldVolumen);
		
		JLabel lblCapacidadPalettes = new JLabel(Messages.getString("DetallesPresupuestoPanel.26")); //$NON-NLS-1$
		lblCapacidadPalettes.setBounds(343, 57, 153, 15);
		panel_1_1_1.add(lblCapacidadPalettes);
		
		textFieldCapacidadPalettes = new JTextField();
		textFieldCapacidadPalettes.setEditable(false);
		textFieldCapacidadPalettes.setColumns(10);
		textFieldCapacidadPalettes.setBounds(503, 51, 198, 27);
		panel_1_1_1.add(textFieldCapacidadPalettes);
		
		JLabel lblDimensiones = new JLabel(Messages.getString("DetallesPresupuestoPanel.27")); //$NON-NLS-1$
		lblDimensiones.setBounds(376, 16, 83, 15);
		panel_1_1_1.add(lblDimensiones);
		
		textFieldDimensiones = new JTextField();
		textFieldDimensiones.setEditable(false);
		textFieldDimensiones.setColumns(10);
		textFieldDimensiones.setBounds(477, 10, 224, 27);
		panel_1_1_1.add(textFieldDimensiones);
		
		JLabel lblM3 = new JLabel(Messages.getString("DetallesPresupuestoPanel.28")); //$NON-NLS-1$
		lblM3.setBounds(279, 57, 55, 15);
		panel_1_1_1.add(lblM3);
		
		chckbxCinchas = new JCheckBox(Messages.getString("DetallesPresupuestoPanel.29")); //$NON-NLS-1$
		chckbxCinchas.setBounds(147, 100, 112, 23);
		panel_1_1_1.add(chckbxCinchas);
		
		chckbxAbrePorArriba = new JCheckBox(Messages.getString("DetallesPresupuestoPanel.30")); //$NON-NLS-1$
		chckbxAbrePorArriba.setBounds(282, 100, 112, 23);
		panel_1_1_1.add(chckbxAbrePorArriba);
		
		chckbxEngancheDeRemolque = new JCheckBox(Messages.getString("DetallesPresupuestoPanel.31")); //$NON-NLS-1$
		chckbxEngancheDeRemolque.setBounds(431, 100, 162, 23);
		panel_1_1_1.add(chckbxEngancheDeRemolque);
		
		JLabel lblRemolqueRequerido = new JLabel(Messages.getString("DetallesPresupuestoPanel.32")); //$NON-NLS-1$
		lblRemolqueRequerido.setFont(new Font(Messages.getString("DetallesPresupuestoPanel.33"), Font.BOLD, 13)); //$NON-NLS-1$
		lblRemolqueRequerido.setBounds(22, 547, 148, 15);
		add(lblRemolqueRequerido);
	}
	public JButton getBtnGenerarEncargo() {
		return btnGenerarEncargo;
	}

    public JCheckBox getChckbxAbrePorArriba() {
		return chckbxAbrePorArriba;
	}    
    
	public JCheckBox getChckbxCinchas() {
		return chckbxCinchas;
	}
	public JCheckBox getChckbxEngancheDeRemolque() {
		return chckbxEngancheDeRemolque;
	}
	public JTextField getTextFieldCapacidadPalettes() {
		return textFieldCapacidadPalettes;
	}
	public JTextField getTextFieldClase() {
		return textFieldClase;
	}
	public JTextField getTextFieldCliente() {
		return textFieldCliente;
	}
	public JTextField getTextFieldCodigoPostalDestino() {
		return textFieldCodigoPostalDestino;
	}
	public JTextField getTextFieldCodigoPostalOrigen() {
		return textFieldCodigoPostalOrigen;
	}
	public JTextField getTextFieldDimensiones() {
		return textFieldDimensiones;
	}
	public JTextField getTextFieldDireccionDestino() {
		return textFieldDireccionDestino;
	}
	public JTextField getTextFieldDireccionOrigen() {
		return textFieldDireccionOrigen;
	}
	public JTextField getTextFieldDistancia() {
		return textFieldDistancia;
	}
	public JTextField getTextFieldID() {
		return textFieldID;
    }
	public JTextField getTextFieldLocalidadDestino() {
		return textFieldLocalidadDestino;
	}
	public JTextField getTextFieldLocalidadOrigen() {
		return textFieldLocalidadOrigen;
	}
	public JTextField getTextFieldMercancia() {
		return textFieldMercancia;
	}
	public JTextField getTextFieldPaisDestino() {
		return textFieldPaisDestino;
	}
	public JTextField getTextFieldPaisOrigen() {
		return textFieldPaisOrigen;
	}
	public JTextField getTextFieldPeso() {
		return textFieldPeso;
    }
	public JTextField getTextFieldPrecio() {
		return textFieldPrecio;
	}
	public JTextField getTextFieldProvinciaDestino() {
		return textFieldProvinciaDestino;
	}
	public JTextField getTextFieldProvinciaOrigen() {
		return textFieldProvinciaOrigen;
	}
	public JTextField getTextFieldVolumen() {
		return textFieldVolumen;
	}
}