package panel.control.guardar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import constante.Messages;
import exception.DatoNoValidoException;
import exception.DialogoError;
import exception.EntidadYaExisteException;
import interfaz.EnActualizadaBBDD;
import interfaz.EnPeticionBBDD;
import objeto.Ubicacion;
import panel.vista.guardar.GuardarPresupuestoDialogo;
import panel.vista.guardar.GuardarUbicacionDialogo;
public class GuardarUbicacionDialogoControl {

	private boolean bool[] = { true, true, true, true, true };
	private EnActualizadaBBDD enActualizadaBBDD;
	private EnPeticionBBDD enPeticionBBDD;
	private String jtext;
	private KeyAdapter keyAdapterComboCod;

	private KeyAdapter keyAdapterComboDirr;
	private KeyAdapter keyAdapterComboLoc;

	private KeyAdapter keyAdapterComboPais;
	private KeyAdapter keyAdapterComboPro;

	private String textkeyadapterCod;
	private String textkeyadapterDirr;

	private String textkeyadapterLoc;
	private String textkeyadapterPais;
	private String textkeyadapterPro;
	private ArrayList<Ubicacion> ubicaciones;

	private GuardarUbicacionDialogo vista;
	private GuardarPresupuestoDialogoControl vistaArriba;

	public GuardarUbicacionDialogoControl(GuardarUbicacionDialogo vista, ArrayList<Ubicacion> ubicaciones,
			GuardarPresupuestoDialogoControl vistaArriba, String jtext, EnPeticionBBDD enPeticionBBDD,
			EnActualizadaBBDD enActualizadaBBDD) {
		this.vista = vista;
		this.vistaArriba = vistaArriba;
		this.jtext = jtext;
		this.enPeticionBBDD = enPeticionBBDD;
		this.enActualizadaBBDD = enActualizadaBBDD;
		if (ubicaciones != null) {
			this.ubicaciones = ubicaciones;
		}
		start();
		this.vista.getBtnSeleccionarUbi().addActionListener(e -> seleccionarNueva());
		this.vista.getBtnSeleccionarUbi().setEnabled(false);
		this.vista.getTable().getSelectionModel().addListSelectionListener(x -> listenerRow());
		this.vista.getBtnGuardarNuevaUbicacion().addActionListener(e -> guardarNuevaUbi());

	}

	public void addRow(Object... ob) {
		String[] string = new String[ob.length];

		for (int i = 0; i < ob.length; i++) {
			string[i] = ob[i].toString();
		}
		if (this.vista.getTable().getModel().getColumnCount() == string.length) {
			((DefaultTableModel) this.vista.getTable().getModel()).addRow(string);
		} else {
			JOptionPane.showMessageDialog(vista, Messages.getString("GuardarUbicacionDialogoControl.27"), //$NON-NLS-1$
					Messages.getString("ERROR"), 0, null); //$NON-NLS-1$
		}
	}

	private void colocarFila() {
		removeAllTable();
		for (Ubicacion ubicacion : ubicaciones) {
			if (ubicacion.getDireccion().toLowerCase().contains(textkeyadapterDirr.toLowerCase())
					&& ubicacion.getLocalidad().toLowerCase().contains(textkeyadapterLoc.toLowerCase())
					&& ubicacion.getProvincia().toLowerCase().contains(textkeyadapterPro.toLowerCase())
					&& ubicacion.getCodigopostal().toLowerCase().contains(textkeyadapterCod.toLowerCase())
					&& ubicacion.getPais().toLowerCase().contains(textkeyadapterPais.toLowerCase())) {
				addRow(new Object[] { ubicacion.getDireccion(), ubicacion.getLocalidad(), ubicacion.getProvincia(),
						ubicacion.getCodigopostal(), ubicacion.getPais() });
			}
		}
	}

	private void fillTable() {
		removeAllTable();
		if (ubicaciones != null) {
			for (int i = 0; i < ubicaciones.size(); i++) {
				addRow(new Object[] { ubicaciones.get(i).getDireccion(), ubicaciones.get(i).getLocalidad(),
						ubicaciones.get(i).getProvincia(), ubicaciones.get(i).getCodigopostal(),
						ubicaciones.get(i).getPais() });
			}
		}
	}

	public GuardarUbicacionDialogo getVista() {
		return vista;
	}

	private void guardarNuevaUbi() {
		Ubicacion u = null;
		try {
			u = new Ubicacion(this.vista.getTxtDirr().getSelectedItem().toString(),
					this.vista.getTxtLocal().getSelectedItem().toString(),
					this.vista.getTxtProv().getSelectedItem().toString(),
					this.vista.getTxtCodig().getSelectedItem().toString(),
					this.vista.getTxtPais().getSelectedItem().toString());
		} catch (DatoNoValidoException e) {
			new DialogoError(e).showErrorMessage();
		}
		try {
			enPeticionBBDD.guardaUbicacion(u);
			enActualizadaBBDD.actualizarUbicacion(u, true);
		} catch (SQLException | EntidadYaExisteException | DatoNoValidoException e) {
			new DialogoError(e).showErrorMessage();
		}
		((JTextField) vista.getTxtDirr().getEditor().getEditorComponent()).setText(Messages.getString("VACIO")); //$NON-NLS-1$
		((JTextField) vista.getTxtLocal().getEditor().getEditorComponent()).setText(Messages.getString("VACIO")); //$NON-NLS-1$
		((JTextField) vista.getTxtProv().getEditor().getEditorComponent()).setText(Messages.getString("VACIO")); //$NON-NLS-1$
		((JTextField) vista.getTxtCodig().getEditor().getEditorComponent()).setText(Messages.getString("VACIO")); //$NON-NLS-1$
		((JTextField) vista.getTxtPais().getEditor().getEditorComponent()).setText(Messages.getString("VACIO")); //$NON-NLS-1$
		refesh();
	}

	@SuppressWarnings("unchecked")
	private void initCombo() {
		ArrayList<String> arrDirr = new ArrayList<String>();
		boolean boDirr = true;
		ArrayList<String> arCodi = new ArrayList<String>();
		boolean boCodi = true;
		ArrayList<String> arrLoc = new ArrayList<String>();
		boolean boLoc = true;
		ArrayList<String> arrProv = new ArrayList<String>();
		boolean boProv = true;
		ArrayList<String> arrPais = new ArrayList<String>();
		boolean boPais = true;
		for (Ubicacion ubicacion : ubicaciones) {
			for (int i = 0; i < arrDirr.size(); i++) {
				if (arrDirr.get(i).contentEquals(ubicacion.getDireccion())) {
					boDirr = false;
					break;
				}
			}
			if (boDirr) {
				this.vista.getTxtDirr().addItem(ubicacion.getDireccion());
				arrDirr.add(ubicacion.getDireccion());
			}
			boDirr = true;
			for (int i = 0; i < arCodi.size(); i++) {
				if (arCodi.get(i).contentEquals(ubicacion.getCodigopostal())) {
					boCodi = false;
					break;
				}
			}
			if (boCodi) {
				this.vista.getTxtCodig().addItem(ubicacion.getCodigopostal());
				arCodi.add(ubicacion.getCodigopostal());
			}
			boCodi = true;
			for (int i = 0; i < arrLoc.size(); i++) {
				if (arrLoc.get(i).contentEquals(ubicacion.getLocalidad())) {
					boLoc = false;
					break;
				}
			}
			if (boLoc) {
				this.vista.getTxtLocal().addItem(ubicacion.getLocalidad());
				arrLoc.add(ubicacion.getLocalidad());
			}
			boLoc = true;
			for (int i = 0; i < arrProv.size(); i++) {
				if (arrProv.get(i).contentEquals(ubicacion.getProvincia())) {
					boProv = false;
					break;
				}
			}
			if (boProv) {
				this.vista.getTxtProv().addItem(ubicacion.getProvincia());
				arrProv.add(ubicacion.getProvincia());
			}
			boProv = true;
			for (int i = 0; i < arrPais.size(); i++) {
				if (arrPais.get(i).contentEquals(ubicacion.getPais())) {
					boPais = false;
					break;
				}
			}
			if (boPais) {
				this.vista.getTxtPais().addItem(ubicacion.getPais());
				arrPais.add(ubicacion.getPais());
			}
			boPais = true;
		}

	}

	private void listenerRow() {
		if (vista.getTable().getSelectionModel().isSelectionEmpty()) {
			vista.getBtnSeleccionarUbi().setEnabled(false);
		} else {
			vista.getBtnSeleccionarUbi().setEnabled(true);
		}
	}

	private void refesh() {
		// quan larrai suigui tot true(tots estan buits) llavors omples tota la taula
		if (!Arrays.asList(bool).contains(false))
			fillTable();

		textkeyadapterDirr = ((JTextField) vista.getTxtDirr().getEditor().getEditorComponent()).getText();
		textkeyadapterLoc = ((JTextField) vista.getTxtLocal().getEditor().getEditorComponent()).getText();
		textkeyadapterPro = ((JTextField) vista.getTxtProv().getEditor().getEditorComponent()).getText();
		textkeyadapterCod = ((JTextField) vista.getTxtCodig().getEditor().getEditorComponent()).getText();
		textkeyadapterPais = ((JTextField) vista.getTxtPais().getEditor().getEditorComponent()).getText();
		colocarFila();
		if ((vista.getTable().getModel().getRowCount() == 0) && bool[0] == false && bool[1] == false && bool[2] == false
				&& bool[3] == false && bool[4] == false) {
			vista.getBtnGuardarNuevaUbicacion().setEnabled(true);
		} else {
			vista.getBtnGuardarNuevaUbicacion().setEnabled(false);
		}
	}

	private void removeAllTable() {
		DefaultTableModel dm = (DefaultTableModel) this.vista.getTable().getModel();
		dm.getDataVector().removeAllElements();
		dm.addRow(new Vector<>());
		dm.setRowCount(0);

	}

	private void seleccionarNueva() {

		Object[] options1 = { Messages.getString("GuardarUbicacionDialogoControl.5"), Messages.getString("GuardarUbicacionDialogoControl.6") }; //$NON-NLS-1$ //$NON-NLS-2$
		int res = JOptionPane.showOptionDialog(vista,
				Messages.getString("GuardarUbicacionDialogoControl.7") + vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 0) //$NON-NLS-1$
						+ Messages.getString("BARRAINVERTIDA") + vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 0) + Messages.getString("BARRAINVERTIDA") //$NON-NLS-1$ //$NON-NLS-2$
						+ vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 1) + Messages.getString("BARRAINVERTIDA") //$NON-NLS-1$
						+ vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 2) + Messages.getString("BARRAINVERTIDA") //$NON-NLS-1$
						+ vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 3) + Messages.getString("BARRAINVERTIDA") //$NON-NLS-1$
						+ vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 4) + Messages.getString("BARRAINVERTIDA"), //$NON-NLS-1$
				Messages.getString("GuardarUbicacionDialogoControl.14"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options1, null); //$NON-NLS-1$
		if (jtext.contentEquals(Messages.getString("ORIGEN"))) { //$NON-NLS-1$
			if (res == JOptionPane.YES_OPTION) {
				try {
					((GuardarPresupuestoDialogo) this.vistaArriba.getVista()).getTxtOrigen().setText(
							(new Ubicacion((String) vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 0),
									(String) vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 1),
									(String) vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 2),
									(String) vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 3),
									(String) vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 4)))
											.toString());
				} catch (DatoNoValidoException e) {
					new DialogoError(e).showErrorMessage();
				}
				vista.setVisible(false);
			}
		} else if (jtext.contentEquals(Messages.getString("GuardarUbicacionDialogoControl.16"))) { //$NON-NLS-1$
			if (res == JOptionPane.YES_OPTION) {
				try {
					((GuardarPresupuestoDialogo) this.vistaArriba.getVista()).getTxtDestino().setText(
							(new Ubicacion((String) vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 0),
									(String) vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 1),
									(String) vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 2),
									(String) vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 3),
									(String) vista.getTable().getValueAt(vista.getTable().getSelectedRow(), 4)))
											.toString());
				} catch (DatoNoValidoException e) {
					new DialogoError(e).showErrorMessage();
				}
				vista.setVisible(false);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void start() {

		if (ubicaciones != null) {
			initCombo();
		}
		this.vista.getTxtDirr().setSelectedIndex(-1);
		this.vista.getTxtCodig().setSelectedIndex(-1);
		this.vista.getTxtLocal().setSelectedIndex(-1);
		this.vista.getTxtPais().setSelectedIndex(-1);
		this.vista.getTxtProv().setSelectedIndex(-1);

		this.vista.getTable().setModel(new DefaultTableModel(
				new String[] { Messages.getString("GuardarUbicacionDialogoControl.17"), Messages.getString("GuardarUbicacionDialogoControl.18"), Messages.getString("GuardarUbicacionDialogoControl.19"), Messages.getString("GuardarUbicacionDialogoControl.20"), Messages.getString("GuardarUbicacionDialogoControl.21") }, 0)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

		fillTable();
		startComboDirr();
		startComboPais();
		startComboLoc();
		startComboCod();
		startComboPro();
		textkeyadapterDirr = Messages.getString("VACIO"); //$NON-NLS-1$

		textkeyadapterPais = Messages.getString("VACIO"); //$NON-NLS-1$

		textkeyadapterLoc = Messages.getString("VACIO"); //$NON-NLS-1$

		textkeyadapterCod = Messages.getString("VACIO"); //$NON-NLS-1$

		textkeyadapterPro = Messages.getString("VACIO"); //$NON-NLS-1$

		this.vista.getTxtDirr().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refesh();
			}
		});
		this.vista.getTxtCodig().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refesh();
			}
		});
		this.vista.getTxtLocal().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refesh();
			}
		});
		this.vista.getTxtPais().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refesh();
			}
		});
		this.vista.getTxtProv().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refesh();
			}
		});
	}

	private void startComboCod() {
		textkeyadapterCod = (String) this.vista.getTxtCodig().getSelectedItem();
		keyAdapterComboCod = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textkeyadapterCod.isBlank()) {
					bool[3] = true;
				} else {
					bool[3] = false;
				}
				refesh();
			}
		};
		this.vista.getTxtCodig().getEditor().getEditorComponent().addKeyListener(keyAdapterComboCod);
	}

	private void startComboDirr() {
		textkeyadapterDirr = (String) this.vista.getTxtDirr().getSelectedItem();
		keyAdapterComboDirr = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textkeyadapterDirr.isBlank()) {
					bool[0] = true;
				} else {
					bool[0] = false;
				}
				refesh();
			}
		};
		this.vista.getTxtDirr().getEditor().getEditorComponent().addKeyListener(keyAdapterComboDirr);
	}

	private void startComboLoc() {
		textkeyadapterLoc = (String) this.vista.getTxtLocal().getSelectedItem();
		keyAdapterComboLoc = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textkeyadapterLoc.isBlank()) {
					bool[1] = true;
				} else {
					bool[1] = false;
				}
				refesh();
			}
		};
		this.vista.getTxtLocal().getEditor().getEditorComponent().addKeyListener(keyAdapterComboLoc);
	}

	private void startComboPais() {
		textkeyadapterPais = (String) this.vista.getTxtPais().getSelectedItem();
		keyAdapterComboPais = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textkeyadapterPais.isBlank()) {
					bool[4] = true;
				} else {
					bool[4] = false;
				}
				refesh();
			}
		};
		this.vista.getTxtPais().getEditor().getEditorComponent().addKeyListener(keyAdapterComboPais);
	}

	private void startComboPro() {
		textkeyadapterPro = (String) this.vista.getTxtProv().getSelectedItem();
		keyAdapterComboPro = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textkeyadapterPro.isBlank()) {
					bool[2] = true;
				} else {
					bool[2] = false;
				}
				refesh();
			}
		};
		this.vista.getTxtProv().getEditor().getEditorComponent().addKeyListener(keyAdapterComboPro);
	}
}
