package panel.vista.guardar;

import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constante.Messages;
public class GuardarRemolqueRequeridoDialogo extends JDialog {
	private JButton btnGuardar;
	private JCheckBox chckbxAbrePorArriba;
	private JCheckBox chckbxCinchas;
	private JCheckBox chckbxEngancheDeRemolque;
	private JComboBox<String> comboBoxTipoRemolque;
	private JPanel frigorifico;
	private JPanel lona;
	private JPanel panelRemolques;
	private JPanel pisomovil;
	private JTextField textFieldAltura;
	private JTextField textFieldAnchura;
	private JTextField textFieldEjes;
	private JTextField textFieldLongitud;
	private JTextField txtCapacidadpalettes;
	private JTextField txtTara;
	private JTextField txtVolumen;

	public GuardarRemolqueRequeridoDialogo() {
		setResizable(false);
		setModal(true);
		setTitle(Messages.getString("GuardarRemolqueRequeridoDialogo.0")); //$NON-NLS-1$
		getContentPane().setLayout(null);
		setBounds(50, 50, 503, 384);

		JLabel lblTipoDeRemolque = new JLabel(Messages.getString("GuardarRemolqueRequeridoDialogo.1")); //$NON-NLS-1$
		lblTipoDeRemolque.setFont(new Font(Messages.getString("GuardarRemolqueRequeridoDialogo.2"), Font.BOLD, 13)); //$NON-NLS-1$
		lblTipoDeRemolque.setBounds(81, 17, 138, 15);
		getContentPane().add(lblTipoDeRemolque);

		comboBoxTipoRemolque = new JComboBox<String>();
		comboBoxTipoRemolque.setBounds(233, 12, 138, 27);
		getContentPane().add(comboBoxTipoRemolque);

		JLabel lblEjes = new JLabel(Messages.getString("GuardarRemolqueRequeridoDialogo.3")); //$NON-NLS-1$
		lblEjes.setBounds(43, 83, 45, 15);
		getContentPane().add(lblEjes);

		textFieldEjes = new JTextField();
		textFieldEjes.setColumns(10);
		textFieldEjes.setBounds(81, 77, 38, 27);
		getContentPane().add(textFieldEjes);

		JLabel lblAltura = new JLabel(Messages.getString("GuardarRemolqueRequeridoDialogo.4")); //$NON-NLS-1$
		lblAltura.setBounds(233, 119, 45, 15);
		getContentPane().add(lblAltura);

		textFieldAltura = new JTextField();
		textFieldAltura.setColumns(10);
		textFieldAltura.setBounds(287, 113, 55, 27);
		getContentPane().add(textFieldAltura);

		JLabel lblm = new JLabel(Messages.getString("GuardarRemolqueDialogo.18")); //$NON-NLS-1$
		lblm.setBounds(352, 119, 12, 15);
		getContentPane().add(lblm);

		JLabel lblAnchura = new JLabel(Messages.getString("GuardarRemolqueRequeridoDialogo.6")); //$NON-NLS-1$
		lblAnchura.setBounds(223, 82, 63, 15);
		getContentPane().add(lblAnchura);

		textFieldAnchura = new JTextField();
		textFieldAnchura.setColumns(10);
		textFieldAnchura.setBounds(287, 76, 55, 27);
		getContentPane().add(textFieldAnchura);

		JLabel lblm_1 = new JLabel(Messages.getString("GuardarRemolqueDialogo.18")); //$NON-NLS-1$
		lblm_1.setBounds(352, 82, 12, 15);
		getContentPane().add(lblm_1);

		JLabel lblLongitud = new JLabel(Messages.getString("GuardarRemolqueRequeridoDialogo.8")); //$NON-NLS-1$
		lblLongitud.setBounds(223, 157, 63, 15);
		getContentPane().add(lblLongitud);

		textFieldLongitud = new JTextField();
		textFieldLongitud.setColumns(10);
		textFieldLongitud.setBounds(287, 151, 55, 27);
		getContentPane().add(textFieldLongitud);

		JLabel lblm_2 = new JLabel(Messages.getString("GuardarRemolqueDialogo.18")); //$NON-NLS-1$
		lblm_2.setBounds(352, 157, 12, 15);
		getContentPane().add(lblm_2);

		JLabel lblTara = new JLabel(Messages.getString("GuardarRemolqueRequeridoDialogo.10")); //$NON-NLS-1$
		lblTara.setBounds(31, 119, 45, 15);
		getContentPane().add(lblTara);

		txtTara = new JTextField();
		txtTara.setBounds(76, 113, 69, 27);
		getContentPane().add(txtTara);
		txtTara.setColumns(10);

		JLabel lblTn = new JLabel(Messages.getString("GuardarRemolqueRequeridoDialogo.11")); //$NON-NLS-1$
		lblTn.setBounds(155, 119, 29, 15);
		getContentPane().add(lblTn);

		panelRemolques = new JPanel(new CardLayout());
		panelRemolques.setBounds(10, 189, 452, 68);
		getContentPane().add(panelRemolques);

		addLona();
		addFrigorifico();
		addPisoMovil();

		btnGuardar = new JButton(Messages.getString("GuardarRemolqueRequeridoDialogo.12")); //$NON-NLS-1$
		btnGuardar.setBounds(313, 282, 131, 35);
		getContentPane().add(btnGuardar);
	}

	private void addFrigorifico() {
		frigorifico = new JPanel();
		frigorifico.setBounds(12, 199, 624, 68);
		panelRemolques.add(frigorifico, Messages.getString("GuardarRemolqueRequeridoDialogo.16")); //$NON-NLS-1$
		frigorifico.setLayout(null);

		JLabel lblCapacidadDePalettes = new JLabel(Messages.getString("GuardarRemolqueRequeridoDialogo.17")); //$NON-NLS-1$
		lblCapacidadDePalettes.setBounds(22, 27, 157, 15);
		frigorifico.add(lblCapacidadDePalettes);

		txtCapacidadpalettes = new JTextField();
		txtCapacidadpalettes.setBounds(175, 21, 94, 27);
		frigorifico.add(txtCapacidadpalettes);
		txtCapacidadpalettes.setColumns(10);

	}

	private void addLona() {
		lona = new JPanel();
		lona.setBounds(12, 199, 624, 68);
		panelRemolques.add(lona, Messages.getString("GuardarRemolqueRequeridoDialogo.18")); //$NON-NLS-1$
		lona.setLayout(null);

		chckbxCinchas = new JCheckBox(Messages.getString("GuardarRemolqueRequeridoDialogo.19")); //$NON-NLS-1$
		chckbxCinchas.setBounds(20, 24, 112, 23);
		lona.add(chckbxCinchas);

		chckbxAbrePorArriba = new JCheckBox(Messages.getString("GuardarRemolqueRequeridoDialogo.20")); //$NON-NLS-1$
		chckbxAbrePorArriba.setBounds(129, 24, 112, 23);
		lona.add(chckbxAbrePorArriba);

		chckbxEngancheDeRemolque = new JCheckBox(Messages.getString("GuardarRemolqueRequeridoDialogo.21")); //$NON-NLS-1$
		chckbxEngancheDeRemolque.setBounds(266, 24, 163, 23);
		lona.add(chckbxEngancheDeRemolque);

	}

	private void addPisoMovil() {
		pisomovil = new JPanel();
		pisomovil.setBounds(12, 199, 624, 68);
		panelRemolques.add(pisomovil, Messages.getString("GuardarRemolqueRequeridoDialogo.13")); //$NON-NLS-1$
		pisomovil.setLayout(null);

		JLabel lblVolumen = new JLabel(Messages.getString("GuardarRemolqueRequeridoDialogo.14")); //$NON-NLS-1$
		lblVolumen.setBounds(12, 26, 80, 15);
		pisomovil.add(lblVolumen);

		txtVolumen = new JTextField();
		txtVolumen.setBounds(80, 20, 114, 27);
		pisomovil.add(txtVolumen);
		txtVolumen.setColumns(10);

		JLabel lblM = new JLabel(Messages.getString("GuardarRemolqueRequeridoDialogo.15")); //$NON-NLS-1$
		lblM.setBounds(205, 26, 55, 15);
		pisomovil.add(lblM);

	}

	public JButton getBtnGuardar() {
		return btnGuardar;
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

	public JComboBox getComboBoxTipoRemolque() {
		return comboBoxTipoRemolque;
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
