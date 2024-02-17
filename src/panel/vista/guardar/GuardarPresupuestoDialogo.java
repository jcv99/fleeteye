package panel.vista.guardar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import constante.Messages;
import herramienta.ConfiguracionPantalla;
public class GuardarPresupuestoDialogo extends JDialog {

	private JButton btnCrearPresupuesto;

	private JButton btnNueRemolque;
	private JButton btnOptionsDestino;

	private JButton btnOptionsOrigen;
	private ConfiguracionPantalla configuracionPantalla = new ConfiguracionPantalla();
	private JLabel lblDestino;
	private JLabel lblPrecio;
	private JTextField textFieldDistancia;
	private JTextField textFieldMercancia;
	private JTextField textFieldPeso;
	private JTextField txtDestino;
	private JTextField txtOrigen;
	private JTextField txtPrecio;
	private JTextField txtRemolque;

	public GuardarPresupuestoDialogo() {
		setResizable(false);
		setModal(true);
		setTitle(Messages.getString("GuardarPresupuestoDialogo.0")); //$NON-NLS-1$
		getContentPane().setLayout(null);
		setBounds(50, 50, 597, 301);

		JLabel lblOrigen = new JLabel(Messages.getString("GuardarPresupuestoDialogo.1")); //$NON-NLS-1$
		lblOrigen.setBounds(12, 20, 70, 15);
		getContentPane().add(lblOrigen);

		txtOrigen = new JTextField();
		txtOrigen.setEditable(false);
		txtOrigen.setBounds(100, 12, 430, 30);
		getContentPane().add(txtOrigen);

		lblDestino = new JLabel(Messages.getString("GuardarPresupuestoDialogo.2")); //$NON-NLS-1$
		lblDestino.setBounds(12, 61, 70, 15);
		getContentPane().add(lblDestino);

		txtDestino = new JTextField();
		txtDestino.setEditable(false);
		txtDestino.setBounds(100, 53, 430, 30);
		getContentPane().add(txtDestino);

		lblPrecio = new JLabel(Messages.getString("GuardarPresupuestoDialogo.3")); //$NON-NLS-1$
		lblPrecio.setBounds(12, 99, 70, 15);
		getContentPane().add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(60, 94, 99, 30);
		getContentPane().add(txtPrecio);

		btnCrearPresupuesto = new JButton(Messages.getString("GuardarPresupuestoDialogo.4")); //$NON-NLS-1$

		btnCrearPresupuesto.setBounds(449, 224, 81, 30);
		getContentPane().add(btnCrearPresupuesto);

		btnOptionsOrigen = new JButton(Messages.getString("GuardarPresupuestoDialogo.5")); //$NON-NLS-1$
		btnOptionsOrigen.setBounds(540, 12, 30, 23);
		getContentPane().add(btnOptionsOrigen);

		btnOptionsDestino = new JButton(Messages.getString("GuardarPresupuestoDialogo.5")); //$NON-NLS-1$
		btnOptionsDestino.setBounds(540, 54, 30, 23);
		getContentPane().add(btnOptionsDestino);

		JLabel lblRemolque = new JLabel(Messages.getString("GuardarPresupuestoDialogo.7")); //$NON-NLS-1$
		lblRemolque.setBounds(12, 144, 121, 15);
		getContentPane().add(lblRemolque);

		txtRemolque = new JTextField();
		txtRemolque.setEditable(false);
		txtRemolque.setBounds(151, 136, 378, 30);
		getContentPane().add(txtRemolque);

		btnNueRemolque = new JButton(Messages.getString("GuardarPresupuestoDialogo.5")); //$NON-NLS-1$
		btnNueRemolque.setBounds(540, 140, 30, 23);
		getContentPane().add(btnNueRemolque);

		JLabel lblDistancia = new JLabel(Messages.getString("GuardarPresupuestoDialogo.9")); //$NON-NLS-1$
		lblDistancia.setBounds(211, 99, 70, 15);
		getContentPane().add(lblDistancia);

		textFieldDistancia = new JTextField();
		textFieldDistancia.setColumns(10);
		textFieldDistancia.setBounds(282, 95, 92, 30);
		getContentPane().add(textFieldDistancia);

		JLabel lblPeso = new JLabel(Messages.getString("GuardarPresupuestoDialogo.10")); //$NON-NLS-1$
		lblPeso.setBounds(421, 99, 47, 15);
		getContentPane().add(lblPeso);

		textFieldPeso = new JTextField();
		textFieldPeso.setColumns(10);
		textFieldPeso.setBounds(466, 96, 61, 30);
		getContentPane().add(textFieldPeso);

		JLabel lblKm = new JLabel(Messages.getString("GuardarPresupuestoDialogo.11")); //$NON-NLS-1$
		lblKm.setBounds(381, 99, 55, 15);
		getContentPane().add(lblKm);

		JLabel lblTn = new JLabel(Messages.getString("GuardarPresupuestoDialogo.12")); //$NON-NLS-1$
		lblTn.setBounds(529, 99, 55, 15);
		getContentPane().add(lblTn);

		JLabel lblMercancia = new JLabel(Messages.getString("GuardarPresupuestoDialogo.13")); //$NON-NLS-1$
		lblMercancia.setBounds(12, 191, 81, 15);
		getContentPane().add(lblMercancia);

		textFieldMercancia = new JTextField();
		textFieldMercancia.setBounds(100, 183, 430, 30);
		getContentPane().add(textFieldMercancia);

		JLabel lblNewLabel = new JLabel(Messages.getString("GuardarPresupuestoDialogo.14")); //$NON-NLS-1$
		lblNewLabel.setBounds(169, 99, 30, 14);
		getContentPane().add(lblNewLabel);

	}

	private int altura(int a) {
		return ((int) configuracionPantalla.getHeight() * a) / 100;
	}

	private int amplada(int a) {
		return ((int) configuracionPantalla.getWidth() * a) / 100;
	}

	public JButton getBtnCrearPresupuesto() {
		return btnCrearPresupuesto;
	}

	public JButton getBtnNueRemolque() {
		return btnNueRemolque;
	}

	public JButton getBtnOptionsDestino() {
		return btnOptionsDestino;
	}

	public JButton getBtnOptionsOrigen() {
		return btnOptionsOrigen;
	}

	public JTextField getTextFieldDistancia() {
		return textFieldDistancia;
	}

	public JTextField getTextFieldMercancia() {
		return textFieldMercancia;
	}

	public JTextField getTextFieldPeso() {
		return textFieldPeso;
	}

	public JTextField getTxtDestino() {
		return txtDestino;
	}

	public JTextField getTxtOrigen() {
		return txtOrigen;
	}

	public JTextField getTxtPrecio() {
		return txtPrecio;
	}

	public JTextField getTxtRemolque() {
		return txtRemolque;
	}
}
