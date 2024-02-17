package panel.vista;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import constante.Messages;
public class DetallesRemolquePanel extends JPanel {
	
	private JButton btnConsultarDatos;
	private JCheckBox chckbxAbrePorArriba;
	private JCheckBox chckbxCinchas;
	private JCheckBox chckbxEngancheDeRemolque;
	private JPanel frigorifico;
	private JPanel lona;
    private JPanel panelRemolques;
    private JPanel pisomobil;
	private JTextField textFieldAltura;
	private JTextField textFieldAnchura;
	private JTextField textFieldCompra;
	private JTextField textFieldEjes;
	private JTextField textFieldLongitud;
	private JTextField textFieldMatricula;
    private JTextField textFieldTipoRemolque;
    private JTextField txtCapacidadpalettes;
	private JTextField txtTara;
	private JTextField txtVolumen;
	
	public DetallesRemolquePanel() {
		setBorder(new LineBorder(Color.GRAY));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(12, 25, 624, 100);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblMatricula = new JLabel(Messages.getString("DetallesRemolquePanel.0")); //$NON-NLS-1$
		lblMatricula.setBounds(12, 22, 69, 15);
		panel.add(lblMatricula);
		
		textFieldMatricula = new JTextField();
		textFieldMatricula.setEditable(false);
		textFieldMatricula.setBounds(99, 16, 155, 27);
		panel.add(textFieldMatricula);
		textFieldMatricula.setColumns(10);
		
		JLabel lblTara = new JLabel(Messages.getString("DetallesRemolquePanel.1")); //$NON-NLS-1$
		lblTara.setBounds(479, 62, 45, 15);
		panel.add(lblTara);
		
		txtTara = new JTextField();
		txtTara.setEditable(false);
		txtTara.setBounds(524, 56, 69, 27);
		panel.add(txtTara);
		txtTara.setColumns(10);
		
		JLabel lblTn = new JLabel(Messages.getString("DetallesRemolquePanel.2")); //$NON-NLS-1$
		lblTn.setBounds(596, 62, 28, 15);
		panel.add(lblTn);
		
		JLabel lblFechaCompra = new JLabel(Messages.getString("DetallesRemolquePanel.3")); //$NON-NLS-1$
		lblFechaCompra.setBounds(272, 22, 94, 15);
		panel.add(lblFechaCompra);
		
		JLabel lblEjes = new JLabel(Messages.getString("DetallesRemolquePanel.4")); //$NON-NLS-1$
		lblEjes.setBounds(12, 61, 45, 15);
		panel.add(lblEjes);
		
		textFieldEjes = new JTextField();
		textFieldEjes.setEditable(false);
		textFieldEjes.setColumns(10);
		textFieldEjes.setBounds(50, 55, 38, 27);
		panel.add(textFieldEjes);
		
		JLabel lblAltura = new JLabel(Messages.getString("DetallesRemolquePanel.5")); //$NON-NLS-1$
		lblAltura.setBounds(96, 62, 45, 15);
		panel.add(lblAltura);
		
		textFieldAltura = new JTextField();
		textFieldAltura.setEditable(false);
		textFieldAltura.setColumns(10);
		textFieldAltura.setBounds(142, 56, 55, 27);
		panel.add(textFieldAltura);
		
		JLabel lblAnchura = new JLabel(Messages.getString("DetallesRemolquePanel.6")); //$NON-NLS-1$
		lblAnchura.setBounds(210, 62, 63, 15);
		panel.add(lblAnchura);
		
		textFieldAnchura = new JTextField();
		textFieldAnchura.setEditable(false);
		textFieldAnchura.setColumns(10);
		textFieldAnchura.setBounds(274, 56, 55, 27);
		panel.add(textFieldAnchura);
		
		JLabel lblLongitud = new JLabel(Messages.getString("DetallesRemolquePanel.7")); //$NON-NLS-1$
		lblLongitud.setBounds(333, 62, 63, 15);
		panel.add(lblLongitud);
		
		textFieldLongitud = new JTextField();
		textFieldLongitud.setEditable(false);
		textFieldLongitud.setColumns(10);
		textFieldLongitud.setBounds(403, 56, 69, 27);
		panel.add(textFieldLongitud);
		
		textFieldCompra = new JTextField();
		textFieldCompra.setEditable(false);
		textFieldCompra.setColumns(10);
		textFieldCompra.setBounds(381, 16, 212, 27);
		panel.add(textFieldCompra);
		
		btnConsultarDatos = new JButton(Messages.getString("DetallesRemolquePanel.8")); //$NON-NLS-1$
		btnConsultarDatos.setBounds(226, 301, 182, 25);
		add(btnConsultarDatos);
		
		lona = new JPanel();
		lona.setBounds(12, 199, 624, 68);
		frigorifico = new JPanel();
		frigorifico.setBounds(12, 199, 624, 68);
		pisomobil = new JPanel();
		pisomobil.setBounds(12, 199, 624, 68);
		
		panelRemolques = new JPanel(new CardLayout());
		panelRemolques.setBounds(12, 199, 624, 68);
		add(panelRemolques);
		
		panelRemolques.add(lona,Messages.getString("DetallesRemolquePanel.9")); //$NON-NLS-1$
		lona.setLayout(null);
		
		chckbxCinchas = new JCheckBox(Messages.getString("DetallesRemolquePanel.10")); //$NON-NLS-1$
		chckbxCinchas.setBounds(20, 24, 112, 23);
		lona.add(chckbxCinchas);
		
		chckbxAbrePorArriba = new JCheckBox(Messages.getString("DetallesRemolquePanel.11")); //$NON-NLS-1$
		chckbxAbrePorArriba.setBounds(129, 24, 112, 23);
		lona.add(chckbxAbrePorArriba);
		
		chckbxEngancheDeRemolque = new JCheckBox(Messages.getString("DetallesRemolquePanel.12")); //$NON-NLS-1$
		chckbxEngancheDeRemolque.setBounds(266, 24, 163, 23);
		lona.add(chckbxEngancheDeRemolque);
		panelRemolques.add(frigorifico,Messages.getString("DetallesRemolquePanel.13")); //$NON-NLS-1$
		frigorifico.setLayout(null);
		
		JLabel lblCapacidadDePalettes = new JLabel(Messages.getString("DetallesRemolquePanel.14")); //$NON-NLS-1$
		lblCapacidadDePalettes.setBounds(22, 27, 157, 15);
		frigorifico.add(lblCapacidadDePalettes);
		
		txtCapacidadpalettes = new JTextField();
		txtCapacidadpalettes.setEditable(false);
		txtCapacidadpalettes.setBounds(175, 21, 94, 27);
		frigorifico.add(txtCapacidadpalettes);
		txtCapacidadpalettes.setColumns(10);
		panelRemolques.add(pisomobil,Messages.getString("DetallesRemolquePanel.15")); //$NON-NLS-1$
		pisomobil.setLayout(null);
		
		JLabel lblVolumen = new JLabel(Messages.getString("DetallesRemolquePanel.16")); //$NON-NLS-1$
		lblVolumen.setBounds(12, 26, 80, 15);
		pisomobil.add(lblVolumen);
		
		txtVolumen = new JTextField();
		txtVolumen.setEditable(false);
		txtVolumen.setBounds(80, 20, 114, 27);
		pisomobil.add(txtVolumen);
		txtVolumen.setColumns(10);
		
		JLabel lblM = new JLabel(Messages.getString("DetallesRemolquePanel.17")); //$NON-NLS-1$
		lblM.setBounds(205, 26, 55, 15);
		pisomobil.add(lblM);
		
		JLabel lblTipoDeRemolque = new JLabel(Messages.getString("DetallesRemolquePanel.18")); //$NON-NLS-1$
		lblTipoDeRemolque.setFont(new Font(Messages.getString("DetallesRemolquePanel.19"), Font.BOLD, 13)); //$NON-NLS-1$
		lblTipoDeRemolque.setBounds(12, 155, 138, 15);
		add(lblTipoDeRemolque);
		
		textFieldTipoRemolque = new JTextField();
		textFieldTipoRemolque.setEditable(false);
		textFieldTipoRemolque.setBounds(136, 149, 138, 27);
		add(textFieldTipoRemolque);
    }
    public JButton getBtnConsultarDatos() {
		return btnConsultarDatos;
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
	public JPanel getPanelFrigorifico() 
	{
		return this.frigorifico;
	}
	public JPanel getPanelLona() 
	{
		return this.lona;
	}
	public JPanel getPanelPisoMobil() 
	{
		return this.pisomobil;
    }
	public JPanel getPanelRemolques() {
		return panelRemolques;
	}
	public CardLayout getPanelRemolquesLayout() {
		return (CardLayout) panelRemolques.getLayout();
	}
	public JTextField getTextFieldAltura() {
		return textFieldAltura;
	}
	public JTextField getTextFieldAnchura() {
		return textFieldAnchura;
	}
	public JTextField getTextFieldCompra() {
		return textFieldCompra;
    }
	public JTextField getTextFieldEjes() {
		return textFieldEjes;
	}
	public JTextField getTextFieldLongitud() {
		return textFieldLongitud;
	}
	public JTextField getTextFieldMatricula() {
		return textFieldMatricula;
	}
    public JTextField getTextFieldTipoRemolque() {
		return textFieldTipoRemolque;
	}
	public JTextField getTxtCapacidadpalettes() {
		return txtCapacidadpalettes;
	}
	public JTextField getTxtTara() {
		return txtTara;
	}   
	public JTextField getTxtVolumen() {
		return txtVolumen;
	}
}