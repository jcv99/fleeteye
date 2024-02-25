package panel.control.mostrar;

import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import constante.Messages;
import exception.CamionOcupadoException;
import exception.DatoNoValidoException;
import exception.DialogoError;
import exception.MatriculaNoValidaException;
import exception.NIFNoValidoException;
import exception.RemolqueNoCompatibleException;
import exception.RemolqueYaAsignadoException;
import exception.TrabajadorNoAsignadoException;
import exception.TrabajadorOcupadoException;
import exception.VehiculoOcupadoExcepcion;
import herramienta.Autocompletado;
import herramienta.Utils;
import interfaz.EnPeticionBBDD;
import objeto.Camion;
import objeto.Remolque;
import objeto.RemolqueFrigorifico;
import objeto.RemolqueLona;
import objeto.RemolquePisoMovil;
import panel.vista.mostrar.MostrarFlotaPanel;

public class MostrarFlotaPanelControl {

	private static final int COLUMNA_ALTURA_REMOLQUE = 3;

	private static final int COLUMNA_CONDUCTOR_CAMION = 5;
	private static final int COLUMNA_DETALLES_CAMION = 7;
	private static final int COLUMNA_DETALLES_REMOLQUE = 5;
	private static final int COLUMNA_ESTADO_CAMION = 6;

	private static final int COLUMNA_ESTADO_REMOLQUE = 4;
	private static final int COLUMNA_ID = 0;
	private static final int COLUMNA_MARCA_CAMION = 2;
	private static final int COLUMNA_MATRICULA_CAMION = 1;

	private static final int COLUMNA_MATRICULA_REMOLQUE = 1;

	private static final int COLUMNA_MATRICULA_REMOLQUE_CAMION = 4;
	private static final int COLUMNA_MODELO_CAMION = 3;
	private static final int COLUMNA_TIPO_REMOLQUE = 2;

	private JButton botonDetallesCamion;
	private JButton botonDetallesRemolque;

	private EnPeticionBBDD enPeticionBBDD;
	private DefaultTableModel modelCamion;
	private TableRowSorter<TableModel> modeloOrdenadoCamion;
	private TableRowSorter<TableModel> modeloOrdenadoRemolque;
	private DefaultTableModel modelRemolque;

	private ArrayList<Camion> objectsCamion;

	private ArrayList<Remolque> objectsRemolque;

	private MostrarFlotaPanel vista;

	public MostrarFlotaPanelControl(MostrarFlotaPanel vista, EnPeticionBBDD enPeticionBBDD) {
		this.vista = vista;
		this.enPeticionBBDD = enPeticionBBDD;

		this.botonDetallesCamion = new JButton();
		this.botonDetallesRemolque = new JButton();

		Autocompletado.enable(this.vista.getComboBoxMatricula());
		Autocompletado.enable(this.vista.getComboBoxMatriculaRemolque());
		Autocompletado.enable(this.vista.getComboBoxTipoRemolque());
		Addlisteners();

	}

	private void Addlisteners() {
		this.botonDetallesCamion.addActionListener(e -> abrirDetallesCamion());
		this.botonDetallesRemolque.addActionListener(e -> abrirDetallesRemolque());

		this.vista.getBtnFiltrarCamion().addActionListener(e -> filtrarCamion());
		this.vista.getBtnFiltrarRemolque().addActionListener(e -> filtrarRemolque());

		this.vista.getBtnQuitarFiltroCamion().addActionListener(e -> quitarFiltroCamion());
		this.vista.getBtnQuitarFiltroRemolque().addActionListener(e -> quitarFiltroRemolques());

		this.vista.getChckbxDisponible().addItemListener(e -> enDisponibleSeleccionado(e));
		this.vista.getChckbxOcupado().addItemListener(e -> enOcupadoSeleccionado(e));

		this.vista.getChckbxConRemolque().addItemListener(e -> conRemolqueSeleccionado(e));
		this.vista.getChckbxSinremolque().addItemListener(e -> sinRemolqueSeleccionado(e));

		this.vista.getChckbxDisponibleRemolque().addItemListener(e -> esDisponibleRemolqueSeleccionado(e));
		this.vista.getChckbxOcupadoRemolque().addItemListener(e -> esOcupadoRemolqueSeleccionado(e));

		this.vista.getChckbxConTrabajador().addItemListener(e -> conTrabajadorSeleccionado(e));
		this.vista.getChckbxSintrabajador().addItemListener(e -> sinTrabajadorSeleccionado(e));
		this.vista.getTableCamion().addMouseListener(abrirDetallesC());
		this.vista.getTableRemolque().addMouseListener(abrirDetallesR());
	}

	public MouseAdapter abrirDetallesC() {
		MouseAdapter open = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					abrirDetallesCamion();
				}
			}
		};
		return open;

	}

	private void abrirDetallesCamion() {
		String id = (String) this.vista.getTableCamion().getValueAt(this.vista.getTableCamion().getSelectedRow(),
				COLUMNA_ID);
		try {
			enPeticionBBDD.buscarCamionParaDetalles(id, this.vista);
		} catch (NumberFormatException | SQLException | DatoNoValidoException | NIFNoValidoException
				| TrabajadorOcupadoException | RemolqueYaAsignadoException | CamionOcupadoException
				| MatriculaNoValidaException | VehiculoOcupadoExcepcion | RemolqueNoCompatibleException
				| TrabajadorNoAsignadoException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	public MouseAdapter abrirDetallesR() {
		MouseAdapter open = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					abrirDetallesRemolque();
				}
			}
		};
		return open;

	}

	private void abrirDetallesRemolque() {
		String id = (String) this.vista.getTableRemolque().getValueAt(this.vista.getTableRemolque().getSelectedRow(),
				COLUMNA_ID);
		try {
			enPeticionBBDD.buscarRemolqueParaDetalles(id, this.vista);
		} catch (NumberFormatException | SQLException | DatoNoValidoException | NIFNoValidoException
				| TrabajadorOcupadoException | RemolqueYaAsignadoException | CamionOcupadoException
				| MatriculaNoValidaException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	private void actualizarFilaCamion(Camion c) {
		for (int x = 0; x < this.vista.getTableCamion().getModel().getRowCount(); x++) {
			if (this.vista.getTableCamion().getValueAt(x, COLUMNA_ID).toString()
					.contentEquals(Integer.toString(c.getId()))) {
				String estado = c.esDisponible() ? Messages.getString("MostrarFlotaPanelControl.4") //$NON-NLS-1$
						: Messages.getString("MostrarFlotaPanelControl.5"); //$NON-NLS-1$
				String remolque = c.getRemolque() != null ? c.getRemolque().getMatricula()
						: Messages.getString("MostrarFlotaPanelControl.6"); //$NON-NLS-1$
				String trabajador = c.getTrabajador() != null
						? c.getTrabajador().getIdentidad().getNombre() + Messages.getString("BARRAINVERTIDA") //$NON-NLS-1$
								+ c.getTrabajador().getIdentidad().getApellido()
						: Messages.getString("MostrarFlotaPanelControl.8"); //$NON-NLS-1$
				modelCamion = (DefaultTableModel) this.vista.getTableCamion().getModel();
				modelCamion.setValueAt(c.getMatricula(), x, COLUMNA_MATRICULA_CAMION);
				modelCamion.setValueAt(c.getMarca(), x, COLUMNA_MARCA_CAMION);
				modelCamion.setValueAt(c.getModelo(), x, COLUMNA_MODELO_CAMION);
				modelCamion.setValueAt(remolque, x, COLUMNA_MATRICULA_REMOLQUE_CAMION);
				modelCamion.setValueAt(trabajador, x, COLUMNA_CONDUCTOR_CAMION);
				modelCamion.setValueAt(estado, x, COLUMNA_ESTADO_CAMION);
				break;
			}
		}
	}

	private void actualizarFilaRemolque(Remolque r) {
		for (int x = 0; x < this.vista.getTableRemolque().getModel().getRowCount(); x++) {
			if (this.vista.getTableRemolque().getValueAt(x, COLUMNA_ID).toString()
					.contentEquals(Integer.toString(r.getId()))) {
				String tipo = Messages.getString("VACIO"); //$NON-NLS-1$
				if (r instanceof RemolqueLona)
					tipo = Messages.getString("MostrarFlotaPanelControl.10"); //$NON-NLS-1$
				else if (r instanceof RemolqueFrigorifico)
					tipo = Messages.getString("MostrarFlotaPanelControl.11"); //$NON-NLS-1$
				else if (r instanceof RemolquePisoMovil)
					tipo = Messages.getString("MostrarFlotaPanelControl.12"); //$NON-NLS-1$
				String estado = r.esDisponible() ? Messages.getString("MostrarFlotaPanelControl.13") //$NON-NLS-1$
						: Messages.getString("MostrarFlotaPanelControl.14"); //$NON-NLS-1$
				modelRemolque = (DefaultTableModel) this.vista.getTableRemolque().getModel();
				modelRemolque.setValueAt(r.getMatricula(), x, COLUMNA_MATRICULA_REMOLQUE);
				modelRemolque.setValueAt(tipo, x, COLUMNA_TIPO_REMOLQUE);
				modelRemolque.setValueAt(r.getDimensionesRemolque().getAltura() + Messages.getString("VACIO"), x, //$NON-NLS-1$
						COLUMNA_ALTURA_REMOLQUE);
				modelRemolque.setValueAt(estado, x, COLUMNA_ESTADO_REMOLQUE);
				break;
			}
		}
	}

	public void addRowCamion(Object... ob) {
		String[] string = new String[ob.length];

		for (int i = 0; i < ob.length; i++) {
			string[i] = ob[i].toString();
		}
		this.vista.getTableCamion().setRowHeight(30);
		if (this.vista.getTableCamion().getModel().getColumnCount() == string.length) {
			((DefaultTableModel) this.vista.getTableCamion().getModel()).addRow(string);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), Messages.getString("MostrarFlotaPanelControl.16"), //$NON-NLS-1$
					Messages.getString("ERROR"), //$NON-NLS-1$
					JOptionPane.ERROR_MESSAGE);
		}
		this.vista.getTableCamion().getColumnModel().getColumn(0).setMaxWidth(50);
		this.vista.getTableCamion().getColumnModel().getColumn(0).setMinWidth(50);
		this.vista.getTableCamion().getColumnModel().getColumn(1).setMaxWidth(90);
		this.vista.getTableCamion().getColumnModel().getColumn(1).setMinWidth(90);
	}

	public void addRowRemolque(Object... ob) {
		String[] string = new String[ob.length];

		for (int i = 0; i < ob.length; i++) {
			string[i] = ob[i].toString();
		}
		this.vista.getTableRemolque().setRowHeight(30);
		if (this.vista.getTableRemolque().getModel().getColumnCount() == string.length) {
			((DefaultTableModel) this.vista.getTableRemolque().getModel()).addRow(string);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), Messages.getString("MostrarFlotaPanelControl.18"), //$NON-NLS-1$
					Messages.getString("ERROR"), //$NON-NLS-1$
					JOptionPane.ERROR_MESSAGE);
		}
		this.vista.getTableRemolque().getColumnModel().getColumn(0).setMaxWidth(50);
		this.vista.getTableRemolque().getColumnModel().getColumn(0).setMinWidth(50);
		this.vista.getTableRemolque().getColumnModel().getColumn(1).setMaxWidth(90);
		this.vista.getTableRemolque().getColumnModel().getColumn(1).setMinWidth(90);
		this.vista.getTableRemolque().getColumnModel().getColumn(3).setMaxWidth(50);
		this.vista.getTableRemolque().getColumnModel().getColumn(3).setMinWidth(50);
		this.vista.getTableRemolque().getColumnModel().getColumn(4).setMaxWidth(90);
		this.vista.getTableRemolque().getColumnModel().getColumn(4).setMinWidth(90);
	}

	private void conRemolqueSeleccionado(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (this.vista.getChckbxSinremolque().isSelected())
				this.vista.getChckbxSinremolque().setSelected(false);
		}
	}

	private void conTrabajadorSeleccionado(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (this.vista.getChckbxSintrabajador().isSelected())
				this.vista.getChckbxSintrabajador().setSelected(false);
		}
	}

	private void enDisponibleSeleccionado(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (this.vista.getChckbxOcupado().isSelected())
				this.vista.getChckbxOcupado().setSelected(false);
		}
	}

	private void enOcupadoSeleccionado(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (this.vista.getChckbxDisponible().isSelected())
				this.vista.getChckbxDisponible().setSelected(false);
		}
	}

	private void esDisponibleRemolqueSeleccionado(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (this.vista.getChckbxOcupadoRemolque().isSelected())
				this.vista.getChckbxOcupadoRemolque().setSelected(false);
		}
	}

	private void esOcupadoRemolqueSeleccionado(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (this.vista.getChckbxDisponibleRemolque().isSelected())
				this.vista.getChckbxDisponibleRemolque().setSelected(false);
		}
	}

	private void filtrarCamion() {
		this.vista.getTableCamion().setRowSorter(null);
		modelCamion = (DefaultTableModel) this.vista.getTableCamion().getModel();
		modeloOrdenadoCamion = new TableRowSorter<TableModel>(modelCamion);
		List<RowFilter<Object, Object>> filtrosCamion = new ArrayList<RowFilter<Object, Object>>();
		this.vista.getTableCamion().setRowSorter(modeloOrdenadoCamion);

		if (this.vista.getChckbxOcupado().isSelected()) {
			filtrosCamion.add(
					RowFilter.regexFilter(Messages.getString("MostrarFlotaPanelControl.20"), COLUMNA_ESTADO_CAMION)); //$NON-NLS-1$
		} else if (this.vista.getChckbxDisponible().isSelected()) {
			filtrosCamion.add(
					RowFilter.regexFilter(Messages.getString("MostrarFlotaPanelControl.21"), COLUMNA_ESTADO_CAMION)); //$NON-NLS-1$
		}

		if (this.vista.getChckbxConRemolque().isSelected()) {
			filtrosCamion.add(RowFilter.regexFilter(Messages.getString("MostrarFlotaPanelControl.22"), //$NON-NLS-1$
					COLUMNA_MATRICULA_REMOLQUE_CAMION));
		} else if (this.vista.getChckbxSinremolque().isSelected()) {
			filtrosCamion.add(RowFilter.regexFilter(Messages.getString("MostrarFlotaPanelControl.23"), //$NON-NLS-1$
					COLUMNA_MATRICULA_REMOLQUE_CAMION));
		}

		if (this.vista.getChckbxConTrabajador().isSelected()) {
			filtrosCamion.add(
					RowFilter.regexFilter(Messages.getString("MostrarFlotaPanelControl.24"), COLUMNA_CONDUCTOR_CAMION)); //$NON-NLS-1$
		} else if (this.vista.getChckbxSintrabajador().isSelected()) {
			filtrosCamion.add(
					RowFilter.regexFilter(Messages.getString("MostrarFlotaPanelControl.25"), COLUMNA_CONDUCTOR_CAMION)); //$NON-NLS-1$
		}

		if (this.vista.getComboBoxMatricula().getSelectedIndex() != 0) {
			filtrosCamion.add(RowFilter.regexFilter(this.vista.getComboBoxMatricula().getSelectedItem().toString(),
					COLUMNA_MATRICULA_CAMION));
		}

		RowFilter<Object, Object> servicioFiltrosCamion = RowFilter.andFilter(filtrosCamion);
		this.modeloOrdenadoCamion.setRowFilter(servicioFiltrosCamion);
	}

	private void filtrarRemolque() {
		this.vista.getTableRemolque().setRowSorter(null);
		modelRemolque = (DefaultTableModel) this.vista.getTableRemolque().getModel();
		modeloOrdenadoRemolque = new TableRowSorter<TableModel>(modelRemolque);
		List<RowFilter<Object, Object>> filtrosRemolque = new ArrayList<RowFilter<Object, Object>>();
		this.vista.getTableRemolque().setRowSorter(modeloOrdenadoRemolque);

		if (this.vista.getChckbxDisponibleRemolque().isSelected())
			filtrosRemolque.add(
					RowFilter.regexFilter(Messages.getString("MostrarFlotaPanelControl.26"), COLUMNA_ESTADO_REMOLQUE)); //$NON-NLS-1$
		else if (this.vista.getChckbxOcupadoRemolque().isSelected())
			filtrosRemolque.add(
					RowFilter.regexFilter(Messages.getString("MostrarFlotaPanelControl.27"), COLUMNA_ESTADO_REMOLQUE)); //$NON-NLS-1$

		if (this.vista.getComboBoxMatriculaRemolque().getSelectedIndex() != 0)
			filtrosRemolque
					.add(RowFilter.regexFilter(this.vista.getComboBoxMatriculaRemolque().getSelectedItem().toString(),
							COLUMNA_MATRICULA_REMOLQUE));

		if (this.vista.getComboBoxTipoRemolque().getSelectedIndex() != 0)
			filtrosRemolque.add(RowFilter.regexFilter(this.vista.getComboBoxTipoRemolque().getSelectedItem().toString(),
					COLUMNA_TIPO_REMOLQUE));

		RowFilter<Object, Object> servicioFiltrosRemolque = RowFilter.andFilter(filtrosRemolque);
		this.modeloOrdenadoRemolque.setRowFilter(servicioFiltrosRemolque);
	}

	public ArrayList<Camion> getCamiones() {
		return objectsCamion;
	}

	private void quitarFiltroCamion() {
		this.vista.getTableCamion().setRowSorter(null);
		this.vista.getComboBoxMatricula().setSelectedIndex(0);
		this.vista.getChckbxDisponible().setSelected(false);
		this.vista.getChckbxOcupado().setSelected(false);
		this.vista.getChckbxConRemolque().setSelected(false);
		this.vista.getChckbxSinremolque().setSelected(false);
		this.vista.getChckbxConTrabajador().setSelected(false);
		this.vista.getChckbxSintrabajador().setSelected(false);
	}

	private void quitarFiltroRemolques() {
		this.vista.getTableRemolque().setRowSorter(null);
		this.vista.getChckbxDisponibleRemolque().setSelected(false);
		this.vista.getChckbxOcupadoRemolque().setSelected(false);
		this.vista.getComboBoxMatriculaRemolque().setSelectedIndex(0);
		this.vista.getComboBoxTipoRemolque().setSelectedIndex(0);
	}

	public void refrescarCamion(Camion c, boolean nuevo) {
		if (!nuevo)
			actualizarFilaCamion(c);
		else {
			this.vista.getComboBoxMatricula().addItem(c.getMatricula());
			String estado = c.esDisponible() ? Messages.getString("MostrarFlotaPanelControl.28") //$NON-NLS-1$
					: Messages.getString("MostrarFlotaPanelControl.29"); //$NON-NLS-1$
			String remolque = c.getRemolque() != null ? c.getRemolque().getMatricula()
					: Messages.getString("MostrarFlotaPanelControl.30"); //$NON-NLS-1$
			String trabajador = c.getTrabajador() != null
					? c.getTrabajador().getIdentidad().getNombre() + Messages.getString("BARRAINVERTIDA") //$NON-NLS-1$
							+ c.getTrabajador().getIdentidad().getApellido()
					: Messages.getString("MostrarFlotaPanelControl.32"); //$NON-NLS-1$
			addRowCamion(new Object[] { c.getId() + Messages.getString("VACIO"), c.getMatricula(), c.getMarca(), //$NON-NLS-1$
					c.getModelo(), remolque, trabajador, estado, Messages.getString("BARRAINVERTIDA") }); //$NON-NLS-1$
		}

	}

	public void refrescarRemolque(Remolque r, boolean nuevo) {
		if (!nuevo)
			actualizarFilaRemolque(r);
		else {
			this.vista.getComboBoxMatriculaRemolque().addItem(r.getMatricula());
			String tipo = Messages.getString("VACIO"); //$NON-NLS-1$
			if (r instanceof RemolqueLona)
				tipo = Messages.getString("MostrarFlotaPanelControl.36"); //$NON-NLS-1$
			else if (r instanceof RemolqueFrigorifico)
				tipo = Messages.getString("MostrarFlotaPanelControl.37"); //$NON-NLS-1$
			else if (r instanceof RemolquePisoMovil)
				tipo = Messages.getString("MostrarFlotaPanelControl.38"); //$NON-NLS-1$
			String estado = r.esDisponible() ? Messages.getString("MostrarFlotaPanelControl.39") //$NON-NLS-1$
					: Messages.getString("MostrarFlotaPanelControl.40"); //$NON-NLS-1$
			addRowRemolque(new Object[] { r.getId() + Messages.getString("VACIO"), r.getMatricula(), tipo, //$NON-NLS-1$
					r.getDimensionesRemolque().getAltura() + Messages.getString("VACIO"), estado, //$NON-NLS-1$
					Messages.getString("BARRAINVERTIDA") }); //$NON-NLS-1$
		}
	}

	public void setCamiones(ArrayList<Camion> camiones) {
		this.objectsCamion = camiones;
		modelCamion = (DefaultTableModel) this.vista.getTableCamion().getModel();
		modelCamion.getDataVector().removeAllElements();
		modeloOrdenadoCamion = new TableRowSorter<TableModel>(modelCamion);
		this.vista.getTableCamion().setRowSorter(null);
		Utils.setHeader(new String[] { Messages.getString("MostrarFlotaPanelControl.44"), //$NON-NLS-1$
				Messages.getString("MostrarFlotaPanelControl.45"), Messages.getString("MostrarFlotaPanelControl.46"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("MostrarFlotaPanelControl.47"), Messages.getString("MostrarFlotaPanelControl.48"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("MostrarFlotaPanelControl.49"), //$NON-NLS-1$
				Messages.getString("MostrarFlotaPanelControl.50"), Messages.getString("BARRAINVERTIDA") }, //$NON-NLS-1$ //$NON-NLS-2$
				this.vista.getTableCamion());
		this.vista.getComboBoxMatricula().addItem(Messages.getString("TODO")); //$NON-NLS-1$
		if (this.objectsCamion != null) {
			for (int x = 0; x < objectsCamion.size(); x++) {
				this.vista.getComboBoxMatricula().addItem(objectsCamion.get(x).getMatricula());
				String estado = objectsCamion.get(x).esDisponible() ? Messages.getString("MostrarFlotaPanelControl.53") //$NON-NLS-1$
						: Messages.getString("MostrarFlotaPanelControl.54"); //$NON-NLS-1$
				String remolque = objectsCamion.get(x).getRemolque() != null
						? objectsCamion.get(x).getRemolque().getMatricula()
						: Messages.getString("MostrarFlotaPanelControl.55"); //$NON-NLS-1$
				String trabajador = objectsCamion.get(x).getTrabajador() != null
						? objectsCamion.get(x).getTrabajador().getIdentidad().getNombre()
								+ Messages.getString("BARRAINVERTIDA") //$NON-NLS-1$
								+ objectsCamion.get(x).getTrabajador().getIdentidad().getApellido()
						: Messages.getString("MostrarFlotaPanelControl.57"); //$NON-NLS-1$
				addRowCamion(new Object[] { objectsCamion.get(x).getId() + Messages.getString("VACIO"), //$NON-NLS-1$
						objectsCamion.get(x).getMatricula(), objectsCamion.get(x).getMarca(),
						objectsCamion.get(x).getModelo(), remolque, trabajador, estado,
						Messages.getString("BARRAINVERTIDA") }); //$NON-NLS-1$
			}
			this.vista.getTableCamion().getColumnModel().getColumn(COLUMNA_DETALLES_CAMION).setMaxWidth(80);
			this.vista.getTableCamion().getColumnModel().getColumn(COLUMNA_DETALLES_CAMION)
					.setCellRenderer(new BotonDetallesRenderer());
			this.vista.getTableCamion().getColumnModel().getColumn(COLUMNA_DETALLES_CAMION)
					.setCellEditor(new BotonDetallesEditor(new JCheckBox(), botonDetallesCamion));
		}
	}

	public void setRemolque(ArrayList<Remolque> remolques) {
		this.objectsRemolque = remolques;
		modelRemolque = (DefaultTableModel) this.vista.getTableRemolque().getModel();
		modelRemolque.getDataVector().removeAllElements();
		modeloOrdenadoRemolque = new TableRowSorter<TableModel>(modelRemolque);
		this.vista.getTableRemolque().setRowSorter(null);
		Utils.setHeader(new String[] { Messages.getString("MostrarFlotaPanelControl.70"), //$NON-NLS-1$
				Messages.getString("MostrarFlotaPanelControl.71"), Messages.getString("MostrarFlotaPanelControl.72"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("MostrarFlotaPanelControl.73"), Messages.getString("MostrarFlotaPanelControl.74"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("BARRAINVERTIDA") }, this.vista.getTableRemolque()); //$NON-NLS-1$
		this.vista.getComboBoxMatriculaRemolque().addItem(Messages.getString("TODO")); //$NON-NLS-1$
		if (this.objectsRemolque != null) {
			for (int x = 0; x < objectsRemolque.size(); x++) {
				this.vista.getComboBoxMatriculaRemolque().addItem(this.objectsRemolque.get(x).getMatricula());
				String tipo = Messages.getString("VACIO"); //$NON-NLS-1$
				if (this.objectsRemolque.get(x) instanceof RemolqueLona)
					tipo = Messages.getString("MostrarFlotaPanelControl.78"); //$NON-NLS-1$
				else if (this.objectsRemolque.get(x) instanceof RemolqueFrigorifico)
					tipo = Messages.getString("MostrarFlotaPanelControl.79"); //$NON-NLS-1$
				else if (this.objectsRemolque.get(x) instanceof RemolquePisoMovil)
					tipo = Messages.getString("MostrarFlotaPanelControl.80"); //$NON-NLS-1$
				String estado = objectsRemolque.get(x).esDisponible()
						? Messages.getString("MostrarFlotaPanelControl.81") //$NON-NLS-1$
						: Messages.getString("MostrarFlotaPanelControl.82"); //$NON-NLS-1$
				addRowRemolque(new Object[] { objectsRemolque.get(x).getId() + Messages.getString("VACIO"), //$NON-NLS-1$
						objectsRemolque.get(x).getMatricula(), tipo,
						objectsRemolque.get(x).getDimensionesRemolque().getAltura() + Messages.getString("VACIO"), //$NON-NLS-1$
						estado, Messages.getString("BARRAINVERTIDA") }); //$NON-NLS-1$
			}
			this.vista.getTableRemolque().getColumnModel().getColumn(COLUMNA_DETALLES_REMOLQUE).setMaxWidth(80);
			this.vista.getTableRemolque().getColumnModel().getColumn(COLUMNA_DETALLES_REMOLQUE)
					.setCellRenderer(new BotonDetallesRenderer());
			this.vista.getTableRemolque().getColumnModel().getColumn(COLUMNA_DETALLES_REMOLQUE)
					.setCellEditor(new BotonDetallesEditor(new JCheckBox(), botonDetallesCamion));
		}
	}

	public void setModelArrCamion(Object[] ob) {
		for (int i = 0; i < ob.length; i++) {
			addRowCamion(ob[i]);
		}
	}

	public void setModelArrRemolque(Object[] ob) {
		for (int i = 0; i < ob.length; i++) {
			addRowRemolque(ob[i]);
		}
	}

	public void setTiposRemolque(ArrayList<String> tipos) {
		this.vista.getComboBoxTipoRemolque().addItem(Messages.getString("TODO")); //$NON-NLS-1$
		tipos.forEach(tipo -> {
			this.vista.getComboBoxTipoRemolque().addItem(tipo);
		});
	}

	private void itemStateChanged(ItemEvent e, JCheckBox checkBox) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			checkBox.setSelected(false);
		}
	}

	private void sinRemolqueSeleccionado(ItemEvent e) {
		itemStateChanged(e, this.vista.getChckbxConRemolque());
	}

	private void sinTrabajadorSeleccionado(ItemEvent e) {
		itemStateChanged(e, this.vista.getChckbxConTrabajador());
	}
}