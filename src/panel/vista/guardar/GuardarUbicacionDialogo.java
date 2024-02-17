package panel.vista.guardar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import constante.Messages;
public class GuardarUbicacionDialogo extends JDialog {
	private JButton btnGuardarNuevaUbicacion;
	private JButton btnSeleccionar;
	private JScrollPane paneTable;
	private JTable table;
	private JComboBox<String> txtCodig;
	private JComboBox<String> txtDirr;
	private JComboBox<String> txtLocal;
	private JComboBox<String> txtPais;
	private JComboBox<String> txtProv;

	public GuardarUbicacionDialogo() {
		setModal(true);
		setResizable(false);
		setBounds(50, 50, 1100, 600);
		getContentPane().setLayout(null);
		setTitle(Messages.getString("GuardarUbicacionDialogo.0")); //$NON-NLS-1$

		JLabel lblDireccion = new JLabel(Messages.getString("GuardarUbicacionDialogo.1")); //$NON-NLS-1$
		lblDireccion.setBounds(667, 46, 125, 14);
		getContentPane().add(lblDireccion);

		txtDirr = new JComboBox<String>();
		txtDirr.setEditable(true);
		txtDirr.setBounds(761, 38, 253, 31);
		getContentPane().add(txtDirr);

		JLabel lblLocalidad = new JLabel(Messages.getString("GuardarUbicacionDialogo.2")); //$NON-NLS-1$
		lblLocalidad.setBounds(667, 122, 125, 14);
		getContentPane().add(lblLocalidad);

		txtLocal = new JComboBox<String>();
		txtLocal.setEditable(true);
		txtLocal.setBounds(761, 114, 253, 31);
		getContentPane().add(txtLocal);

		JLabel lblProvincia = new JLabel(Messages.getString("GuardarUbicacionDialogo.3")); //$NON-NLS-1$
		lblProvincia.setBounds(667, 204, 125, 14);
		getContentPane().add(lblProvincia);

		txtProv = new JComboBox<String>();
		txtProv.setEditable(true);
		txtProv.setBounds(761, 196, 253, 31);
		getContentPane().add(txtProv);

		JLabel lblCodigoP = new JLabel(Messages.getString("GuardarUbicacionDialogo.4")); //$NON-NLS-1$
		lblCodigoP.setBounds(667, 285, 125, 14);
		getContentPane().add(lblCodigoP);

		txtCodig = new JComboBox<String>();
		txtCodig.setEditable(true);
		txtCodig.setBounds(761, 277, 253, 31);
		getContentPane().add(txtCodig);

		JLabel lblPais = new JLabel(Messages.getString("GuardarUbicacionDialogo.5")); //$NON-NLS-1$
		lblPais.setBounds(667, 366, 125, 14);
		getContentPane().add(lblPais);

		txtPais = new JComboBox<String>();
		txtPais.setEditable(true);
		txtPais.setBounds(761, 358, 253, 31);
		getContentPane().add(txtPais);

		btnSeleccionar = new JButton(Messages.getString("GuardarUbicacionDialogo.6")); //$NON-NLS-1$
		btnSeleccionar.setBounds(815, 490, 143, 37);
		getContentPane().add(btnSeleccionar);

		paneTable = new JScrollPane();
		paneTable.setBounds(10, 11, 622, 528);
		getContentPane().add(paneTable);

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setAutoCreateRowSorter(true);
		paneTable.setViewportView(table);

		btnGuardarNuevaUbicacion = new JButton(Messages.getString("GuardarUbicacionDialogo.7")); //$NON-NLS-1$
		btnGuardarNuevaUbicacion.setEnabled(false);
		btnGuardarNuevaUbicacion.setBounds(787, 427, 194, 37);
		getContentPane().add(btnGuardarNuevaUbicacion);
	}

	public JButton getBtnGuardarNuevaUbicacion() {
		return btnGuardarNuevaUbicacion;
	}

	public JButton getBtnSeleccionarUbi() {
		return btnSeleccionar;
	}

	public JScrollPane getPaneTable() {
		return paneTable;
	}

	public JTable getTable() {
		return table;
	}

	public JComboBox getTxtCodig() {
		return txtCodig;
	}

	public JComboBox getTxtDirr() {
		return txtDirr;
	}

	public JComboBox getTxtLocal() {
		return txtLocal;
	}

	public JComboBox getTxtPais() {
		return txtPais;
	}

	public JComboBox getTxtProv() {
		return txtProv;
	}
}
