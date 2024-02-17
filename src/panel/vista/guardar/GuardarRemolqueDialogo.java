package panel.vista.guardar;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import constante.Messages;
import herramienta.DateLabelFormatter;

public class GuardarRemolqueDialogo extends JDialog {

	private JButton btnInsertar;
	private JCheckBox chckbxAbrePorArriba;
	private JCheckBox chckbxCinchas;
	private JCheckBox chckbxEngancheDeRemolque;
	private JComboBox<String> comboBoxTipoRemolque;
	private JDatePickerImpl datePickerCompra;
	private JPanel frigorifico;
	private JLabel lblm_1;
	private JLabel lblm_2;
	private JPanel lona;
	private JPanel panelCompra;
	private JPanel panelRemolques;
	private JPanel pisomobil;
	private JTextField textFieldAltura;
	private JTextField textFieldAnchura;
	private JTextField textFieldEjes;
	private JTextField textFieldLongitud;
	private JTextField textFieldMatricula1;
	private JTextField textFieldMatricula2;
	private JTextField txtCapacidadpalettes;
	private JTextField txtTara;
	private JTextField txtVolumen;

	public GuardarRemolqueDialogo() {
		setBounds(new Rectangle(1000, 2000, 0, 0));
		setAlwaysOnTop(true);
		setLocation(new Point(1000, 2000));
		setTitle(Messages.getString("GuardarRemolqueDialogo.0")); //$NON-NLS-1$
		setName(Messages.getString("GuardarRemolqueDialogo.1")); //$NON-NLS-1$
		setModal(true);
		setBounds(100, 100, 741, 315);
		getContentPane().setLayout(null);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(12, 25, 713, 100);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblMatricula = new JLabel(Messages.getString("GuardarRemolqueDialogo.2")); //$NON-NLS-1$
		lblMatricula.setBounds(12, 22, 69, 15);
		panel.add(lblMatricula);

		textFieldMatricula1 = new JTextField();
		textFieldMatricula1.setBounds(86, 16, 63, 27);
		panel.add(textFieldMatricula1);
		textFieldMatricula1.setColumns(10);

		JLabel label = new JLabel(Messages.getString("GuardarRemolqueDialogo.3")); //$NON-NLS-1$
		label.setFont(new Font(Messages.getString("GuardarRemolqueDialogo.4"), Font.BOLD, 20)); //$NON-NLS-1$
		label.setBounds(154, 20, 18, 15);
		panel.add(label);

		textFieldMatricula2 = new JTextField();
		textFieldMatricula2.setColumns(10);
		textFieldMatricula2.setBounds(174, 16, 55, 27);
		panel.add(textFieldMatricula2);

		JLabel lblTara = new JLabel(Messages.getString("GuardarRemolqueDialogo.5")); //$NON-NLS-1$
		lblTara.setBounds(558, 64, 45, 15);
		panel.add(lblTara);

		txtTara = new JTextField();
		txtTara.setBounds(603, 58, 69, 27);
		panel.add(txtTara);
		txtTara.setColumns(10);

		JLabel lblTn = new JLabel(Messages.getString("GuardarRemolqueDialogo.6")); //$NON-NLS-1$
		lblTn.setBounds(675, 64, 28, 15);
		panel.add(lblTn);

		panelCompra = new JPanel();
		panelCompra.setBounds(359, 12, 253, 35);
		panel.add(panelCompra);

		JLabel lblFechaCompra = new JLabel(Messages.getString("GuardarRemolqueDialogo.7")); //$NON-NLS-1$
		lblFechaCompra.setBounds(259, 22, 94, 15);
		panel.add(lblFechaCompra);

		UtilDateModel modelMatriculacion = new UtilDateModel();
		Properties pMatriculacion = new Properties();
		pMatriculacion.put(Messages.getString("GuardarRemolqueDialogo.8"), //$NON-NLS-1$
				Messages.getString("GuardarRemolqueDialogo.9")); //$NON-NLS-1$
		pMatriculacion.put(Messages.getString("GuardarRemolqueDialogo.10"), //$NON-NLS-1$
				Messages.getString("GuardarRemolqueDialogo.11")); //$NON-NLS-1$
		pMatriculacion.put(Messages.getString("GuardarRemolqueDialogo.12"), //$NON-NLS-1$
				Messages.getString("GuardarRemolqueDialogo.13")); //$NON-NLS-1$
		JDatePanelImpl datePanelMatriculacion = new JDatePanelImpl(modelMatriculacion, pMatriculacion);

		datePickerCompra = new JDatePickerImpl(datePanelMatriculacion, new DateLabelFormatter());
		datePickerCompra.setBounds(0, 59, 66, -59);
		panelCompra.add(datePickerCompra);

		JLabel lblEjes = new JLabel(Messages.getString("GuardarRemolqueDialogo.14")); //$NON-NLS-1$
		lblEjes.setBounds(22, 64, 45, 15);
		panel.add(lblEjes);

		textFieldEjes = new JTextField();
		textFieldEjes.setColumns(10);
		textFieldEjes.setBounds(60, 58, 38, 27);
		panel.add(textFieldEjes);

		JLabel lblAltura = new JLabel(Messages.getString("GuardarRemolqueDialogo.15")); //$NON-NLS-1$
		lblAltura.setBounds(113, 64, 45, 15);
		panel.add(lblAltura);

		textFieldAltura = new JTextField();
		textFieldAltura.setColumns(10);
		textFieldAltura.setBounds(159, 58, 55, 27);
		panel.add(textFieldAltura);

		JLabel lblAnchura = new JLabel(Messages.getString("GuardarRemolqueDialogo.16")); //$NON-NLS-1$
		lblAnchura.setBounds(243, 64, 63, 15);
		panel.add(lblAnchura);

		textFieldAnchura = new JTextField();
		textFieldAnchura.setColumns(10);
		textFieldAnchura.setBounds(307, 58, 55, 27);
		panel.add(textFieldAnchura);

		JLabel lblLongitud = new JLabel(Messages.getString("GuardarRemolqueDialogo.17")); //$NON-NLS-1$
		lblLongitud.setBounds(390, 64, 63, 15);
		panel.add(lblLongitud);

		textFieldLongitud = new JTextField();
		textFieldLongitud.setColumns(10);
		textFieldLongitud.setBounds(460, 58, 69, 27);
		panel.add(textFieldLongitud);

		JLabel lblm = new JLabel(Messages.getString("GuardarRemolqueDialogo.18")); //$NON-NLS-1$
		lblm.setBounds(223, 64, 13, 14);
		panel.add(lblm);

		lblm_1 = new JLabel(Messages.getString("GuardarRemolqueDialogo.18")); //$NON-NLS-1$
		lblm_1.setBounds(372, 64, 13, 14);
		panel.add(lblm_1);

		lblm_2 = new JLabel(Messages.getString("GuardarRemolqueDialogo.18")); //$NON-NLS-1$
		lblm_2.setBounds(539, 64, 13, 14);
		panel.add(lblm_2);

		btnInsertar = new JButton(Messages.getString("GuardarRemolqueDialogo.21")); //$NON-NLS-1$
		btnInsertar.setBounds(585, 230, 115, 37);
		getContentPane().add(btnInsertar);

		lona = new JPanel();
		lona.setBounds(12, 199, 624, 68);
		frigorifico = new JPanel();
		frigorifico.setBounds(12, 199, 624, 68);
		pisomobil = new JPanel();
		pisomobil.setBounds(12, 199, 624, 68);

		panelRemolques = new JPanel(new CardLayout());
		panelRemolques.setBounds(12, 199, 489, 68);
		getContentPane().add(panelRemolques);

		panelRemolques.add(lona, Messages.getString("GuardarRemolqueDialogo.22")); //$NON-NLS-1$
		lona.setLayout(null);

		chckbxCinchas = new JCheckBox(Messages.getString("GuardarRemolqueDialogo.23")); //$NON-NLS-1$
		chckbxCinchas.setBounds(20, 24, 112, 23);
		lona.add(chckbxCinchas);

		chckbxAbrePorArriba = new JCheckBox(Messages.getString("GuardarRemolqueDialogo.24")); //$NON-NLS-1$
		chckbxAbrePorArriba.setBounds(129, 24, 112, 23);
		lona.add(chckbxAbrePorArriba);

		chckbxEngancheDeRemolque = new JCheckBox(Messages.getString("GuardarRemolqueDialogo.25")); //$NON-NLS-1$
		chckbxEngancheDeRemolque.setBounds(266, 24, 163, 23);
		lona.add(chckbxEngancheDeRemolque);
		panelRemolques.add(frigorifico, Messages.getString("GuardarRemolqueDialogo.26")); //$NON-NLS-1$
		frigorifico.setLayout(null);

		JLabel lblCapacidadDePalettes = new JLabel(Messages.getString("GuardarRemolqueDialogo.27")); //$NON-NLS-1$
		lblCapacidadDePalettes.setBounds(22, 27, 157, 15);
		frigorifico.add(lblCapacidadDePalettes);

		txtCapacidadpalettes = new JTextField();
		txtCapacidadpalettes.setBounds(175, 21, 94, 27);
		frigorifico.add(txtCapacidadpalettes);
		txtCapacidadpalettes.setColumns(10);
		panelRemolques.add(pisomobil, Messages.getString("GuardarRemolqueDialogo.28")); //$NON-NLS-1$
		pisomobil.setLayout(null);

		JLabel lblVolumen = new JLabel(Messages.getString("GuardarRemolqueDialogo.29")); //$NON-NLS-1$
		lblVolumen.setBounds(12, 26, 80, 15);
		pisomobil.add(lblVolumen);

		txtVolumen = new JTextField();
		txtVolumen.setBounds(80, 20, 114, 27);
		pisomobil.add(txtVolumen);
		txtVolumen.setColumns(10);

		JLabel lblM3 = new JLabel(Messages.getString("GuardarRemolqueDialogo.30")); //$NON-NLS-1$
		lblM3.setBounds(205, 26, 55, 15);
		pisomobil.add(lblM3);

		JLabel lblTipoDeRemolque = new JLabel(Messages.getString("GuardarRemolqueDialogo.31")); //$NON-NLS-1$
		lblTipoDeRemolque.setFont(new Font(Messages.getString("GuardarRemolqueDialogo.32"), Font.BOLD, 13)); //$NON-NLS-1$
		lblTipoDeRemolque.setBounds(12, 155, 138, 15);
		getContentPane().add(lblTipoDeRemolque);
		comboBoxTipoRemolque = new JComboBox<String>();
		comboBoxTipoRemolque.addItem("Lona");
		comboBoxTipoRemolque.addItem("Frigorifico");
		comboBoxTipoRemolque.addItem("Piso Movil");
		comboBoxTipoRemolque.setBounds(136, 149, 138, 27);
		comboBoxTipoRemolque.addItem(Messages.getString("GuardarRemolqueDialogo.33")); //$NON-NLS-1$
		comboBoxTipoRemolque.addItem(Messages.getString("GuardarRemolqueDialogo.34")); //$NON-NLS-1$
		comboBoxTipoRemolque.addItem(Messages.getString("GuardarRemolqueDialogo.35")); //$NON-NLS-1$
		getContentPane().add(comboBoxTipoRemolque);
	}

	public JButton getBtnInsertar() {
		return btnInsertar;
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

	public JComboBox<String> getComboBoxRemolque() {
		return comboBoxTipoRemolque;
	}

	public JDatePickerImpl getFechaCompra() {
		return this.datePickerCompra;
	}

	public JPanel getPanelFrigorifico() {
		return this.frigorifico;
	}

	public JPanel getPanelLona() {
		return this.lona;
	}

	public JPanel getPanelMatriculacion() {
		return panelCompra;
	}

	public JPanel getPanelPisoMobil() {
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

	public JTextField getTextFieldEjes() {
		return textFieldEjes;
	}

	public JTextField getTextFieldLongitud() {
		return textFieldLongitud;
	}

	public JTextField getTextFieldMatricula1() {
		return textFieldMatricula1;
	}

	public JTextField getTextFieldMatricula2() {
		return textFieldMatricula2;
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