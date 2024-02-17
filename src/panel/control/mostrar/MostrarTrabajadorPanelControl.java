package panel.control.mostrar;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
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
import interfaz.EnPeticionBBDD;
import objeto.Trabajador;
import panel.vista.mostrar.MostrarTrabajadorPanel;

public class MostrarTrabajadorPanelControl {
	public class BotonDetallesEditor extends DefaultCellEditor {

		public BotonDetallesEditor(JCheckBox checkbox) {
			super(checkbox);
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if (isSelected) {
				botonDetalles.setForeground(table.getSelectionForeground());
				botonDetalles.setBackground(table.getSelectionBackground());
			} else {
				botonDetalles.setForeground(table.getForeground());
				botonDetalles.setBackground(table.getBackground());
			}
			botonDetalles.setText(Messages.getString("MostrarTrabajadorPanelControl.0")); //$NON-NLS-1$
			return botonDetalles;
		}
	}

	public class BotonDetallesRenderer extends JButton implements TableCellRenderer {

		public BotonDetallesRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor(Messages.getString("MostrarTrabajadorPanelControl.1"))); //$NON-NLS-1$
			}
			setText(Messages.getString("MostrarTrabajadorPanelControl.2")); //$NON-NLS-1$
			return this;
		}
	}

	private static final int COLUMNA_DETALLES = 6;
	private static final int COLUMNA_ESTADO = 5;
	private static final int COLUMNA_FNACIMIENTO = 4;
	private static final int COLUMNA_ID = 0;

	private static final int COLUMNA_NACIONALIDAD = 3;

	private static final int COLUMNA_NIF = 1;

	private static final int COLUMNA_NOMBRE_COMPLETO = 2;
	private Trabajador[] aux;
	private JButton botonDetalles;
	private EnPeticionBBDD enPeticionBBDD;
	private DefaultTableModel model;
	private TableRowSorter<TableModel> modeloOrdenado;
	private int posicio = 0;

	private ArrayList<Trabajador> trabajadores;

	private MostrarTrabajadorPanel vista;

	public MostrarTrabajadorPanelControl(MostrarTrabajadorPanel vista, EnPeticionBBDD enPeticionBBDD) {
		this.vista = vista;
		this.enPeticionBBDD = enPeticionBBDD;
		this.trabajadores = new ArrayList<>();
		this.botonDetalles = new JButton();

		Autocompletado.enable(this.vista.getComboBoxNIF());
		Autocompletado.enable(this.vista.getComboBoxNombreCompleto());

		this.vista.getBttnFiltrar().addActionListener(e -> filtrar());
		this.vista.getBttnQuitarFiltros().addActionListener(e -> quitarFiltros());
		this.vista.getChckbxDisponible().addItemListener(e -> enDisponibleSeleccionado(e));
		this.vista.getChckbxOcupado().addItemListener(e -> enOcupadoSeleccionado(e));
		this.botonDetalles.addActionListener(e -> abrirDetallesTrabajador());
		this.vista.getTable().addMouseListener(abrirDetalles());

	}

	public MouseAdapter abrirDetalles() {
		MouseAdapter open = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					abrirDetallesTrabajador();
				}
			}
		};
		return open;

	}

	private void abrirDetallesTrabajador() {
		String id = (String) this.vista.getTable().getValueAt(this.vista.getTable().getSelectedRow(), COLUMNA_ID);
		try {
			enPeticionBBDD.buscarTrabajadorParaDetalles(id, this.vista);
		} catch (SQLException | NIFNoValidoException | DatoNoValidoException | TrabajadorOcupadoException
				| VehiculoOcupadoExcepcion | RemolqueNoCompatibleException | RemolqueYaAsignadoException
				| CamionOcupadoException | TrabajadorNoAsignadoException | MatriculaNoValidaException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	private void actualizarFila(Trabajador t) {
		for (int x = 0; x < this.vista.getTable().getModel().getRowCount(); x++) {
			if (this.vista.getTable().getValueAt(x, COLUMNA_ID).toString().contentEquals(Integer.toString(t.getId()))) {
				String estado = Messages.getString("VACIO"); //$NON-NLS-1$
				if (t.esDisponible())
					estado = Messages.getString("MostrarTrabajadorPanelControl.5"); //$NON-NLS-1$
				else if (t.esOcupado())
					estado = Messages.getString("MostrarTrabajadorPanelControl.6"); //$NON-NLS-1$
				model = (DefaultTableModel) this.vista.getTable().getModel();
				model.setValueAt(t.getIdentidad().getNif(), x, COLUMNA_NIF);
				model.setValueAt(t.getIdentidad().getNombre() + Messages.getString("BARRAINVERTIDA") //$NON-NLS-1$
						+ t.getIdentidad().getApellido() + Messages.getString("BARRAINVERTIDA") //$NON-NLS-1$
						+ t.getIdentidad().getSegundoApellido(), x, COLUMNA_NOMBRE_COMPLETO);
				model.setValueAt(t.getIdentidad().getNacionalidad(), x, COLUMNA_NACIONALIDAD);
				model.setValueAt(t.getIdentidad().getFechaNacimiento(), x, COLUMNA_FNACIMIENTO);
				model.setValueAt(estado, x, COLUMNA_ESTADO);
				break;
			}
		}
	}

	public void addRow(Object... ob) {
		String[] string = new String[ob.length];

		for (int i = 0; i < ob.length; i++) {
			string[i] = ob[i].toString();
		}
		this.vista.getTable().setRowHeight(30);
		if (this.vista.getTable().getModel().getColumnCount() == string.length) {
			((DefaultTableModel) this.vista.getTable().getModel()).addRow(string);
		} else {
			JOptionPane.showMessageDialog(vista, Messages.getString("MostrarTrabajadorPanelControl.9"), //$NON-NLS-1$
					Messages.getString("ERROR"), 0, null); //$NON-NLS-1$
		}
		this.vista.getTable().getColumnModel().getColumn(0).setMaxWidth(50);
		this.vista.getTable().getColumnModel().getColumn(0).setMinWidth(50);
		this.vista.getTable().getColumnModel().getColumn(1).setMaxWidth(90);
		this.vista.getTable().getColumnModel().getColumn(1).setMinWidth(90);
		this.vista.getTable().getColumnModel().getColumn(5).setMaxWidth(90);
		this.vista.getTable().getColumnModel().getColumn(5).setMinWidth(90);
	}

	public Object[] addTrabajadorMostrar(Trabajador t) {
		String estado = Messages.getString("VACIO"); //$NON-NLS-1$
		if (t.getEstado() == Trabajador.DISPONIBLE)
			estado = Messages.getString("MostrarTrabajadorPanelControl.12"); //$NON-NLS-1$
		else if (t.getEstado() == Trabajador.OCUPADO)
			estado = Messages.getString("MostrarTrabajadorPanelControl.13"); //$NON-NLS-1$

		return new Object[] { t.getId() + Messages.getString("VACIO"), t.getIdentidad().getNif(), //$NON-NLS-1$
				t.getIdentidad().getNombre() + Messages.getString("BARRAINVERTIDA") //$NON-NLS-1$
						+ t.getIdentidad().getApellido() + Messages.getString("BARRAINVERTIDA") //$NON-NLS-1$
						+ t.getIdentidad().getSegundoApellido(),
				t.getIdentidad().getNacionalidad(), t.getIdentidad().getFechaNacimiento(), estado,
				Messages.getString("BARRAINVERTIDA") }; //$NON-NLS-1$

	}

	private void anadirComboBoxTrabajador(Trabajador t) {
		this.vista.getComboBoxNIF().addItem(t.getIdentidad().getNif());
		this.vista.getComboBoxNombreCompleto()
				.addItem(t.getIdentidad().getNombre() + Messages.getString("BARRAINVERTIDA") //$NON-NLS-1$
						+ t.getIdentidad().getApellido() + Messages.getString("BARRAINVERTIDA") //$NON-NLS-1$
						+ t.getIdentidad().getSegundoApellido());
	}

	private void borrarTrabajador(Trabajador t) {
		for (int x = 0; x < this.vista.getTable().getModel().getRowCount(); x++) {
			if (this.vista.getTable().getValueAt(x, COLUMNA_ID).toString().contentEquals(Integer.toString(t.getId()))) {
				model = (DefaultTableModel) this.vista.getTable().getModel();
				model.removeRow(x);
			}
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

	private void filtrar() {
		this.vista.getTable().setRowSorter(null);
		model = (DefaultTableModel) this.vista.getTable().getModel();
		modeloOrdenado = new TableRowSorter<TableModel>(model);
		List<RowFilter<Object, Object>> filtros = new ArrayList<RowFilter<Object, Object>>();
		this.vista.getTable().setRowSorter(modeloOrdenado);

		if (this.vista.getChckbxOcupado().isSelected()) {
			filtros.add(RowFilter.regexFilter(Messages.getString("MostrarTrabajadorPanelControl.20"), COLUMNA_ESTADO)); //$NON-NLS-1$
		} else if (this.vista.getChckbxDisponible().isSelected()) {
			filtros.add(RowFilter.regexFilter(Messages.getString("MostrarTrabajadorPanelControl.21"), COLUMNA_ESTADO)); //$NON-NLS-1$
		}

		if (this.vista.getComboBoxNIF().getSelectedIndex() != 0)
			filtros.add(RowFilter.regexFilter(this.vista.getComboBoxNIF().getSelectedItem().toString(), COLUMNA_NIF));
		if (this.vista.getComboBoxNombreCompleto().getSelectedIndex() != 0)
			filtros.add(RowFilter.regexFilter(this.vista.getComboBoxNombreCompleto().getSelectedItem().toString(),
					COLUMNA_NOMBRE_COMPLETO));

		RowFilter<Object, Object> servicioFiltros = RowFilter.andFilter(filtros);
		this.modeloOrdenado.setRowFilter(servicioFiltros);
	}

	private String[] getString() {
		Field[] field = Trabajador.class.getDeclaredFields();
		ArrayList<String> auxArr = new ArrayList<String>();
		// auxArr.add("Boto");
		for (int i = 0; i < field.length; i++) {
			if (field[i].getType().getSimpleName().equals(Messages.getString("MostrarTrabajadorPanelControl.22")) //$NON-NLS-1$
					|| field[i].getType().getSimpleName().equals(Messages.getString("MostrarTrabajadorPanelControl.23")) //$NON-NLS-1$
					|| field[i].getType().getSimpleName().equals(Messages.getString("MostrarTrabajadorPanelControl.24")) //$NON-NLS-1$
					|| field[i].getType().getSimpleName()
							.equals(Messages.getString("MostrarTrabajadorPanelControl.25"))) { //$NON-NLS-1$
				if (Modifier.toString(field[i].getModifiers())
						.equals(Messages.getString("MostrarTrabajadorPanelControl.26"))) { //$NON-NLS-1$
					auxArr.add(field[i].getName());
				}
			}
		}
		String[] arr = new String[auxArr.size()];
		for (int i = 0; i < auxArr.size(); i++) {
			arr[i] = auxArr.get(i);
		}
		return arr;
	}

	private void quitarFiltros() {
		this.vista.getTable().setRowSorter(null);
		if (this.vista.getChckbxOcupado().isSelected())
			this.vista.getChckbxOcupado().setSelected(false);
		if (this.vista.getChckbxDisponible().isSelected())
			this.vista.getChckbxDisponible().setSelected(false);
		this.vista.getComboBoxNIF().setSelectedIndex(0);
		this.vista.getComboBoxNombreCompleto().setSelectedIndex(0);
	}

	public void refrescarTrabajador(Trabajador t, boolean nuevo) {
		if (t.esDadoBaja())
			borrarTrabajador(t);
		if (!nuevo)
			actualizarFila(t);
		else {
			addRow(addTrabajadorMostrar(t));
			anadirComboBoxTrabajador(t);
		}
	}

	public void setHeader(String[] s) {
		this.vista.getTable().setModel(new DefaultTableModel(s, 0));
	}

	public void setTrabajadores(ArrayList<Trabajador> trabajadores) {
		this.model = (DefaultTableModel) this.vista.getTable().getModel();
		this.model.getDataVector().removeAllElements();
		this.modeloOrdenado = new TableRowSorter<TableModel>(this.model);
		this.vista.getTable().setRowSorter(null);
		setHeader(new String[] { Messages.getString("MostrarTrabajadorPanelControl.31"), //$NON-NLS-1$
				Messages.getString("MostrarTrabajadorPanelControl.32"), //$NON-NLS-1$
				Messages.getString("MostrarTrabajadorPanelControl.33"), //$NON-NLS-1$
				Messages.getString("MostrarTrabajadorPanelControl.34"), //$NON-NLS-1$
				Messages.getString("MostrarTrabajadorPanelControl.35"), //$NON-NLS-1$
				Messages.getString("MostrarTrabajadorPanelControl.36"), //$NON-NLS-1$
				Messages.getString("VACIO") }); //$NON-NLS-1$
		if (trabajadores != null) {
			this.vista.getComboBoxNIF().addItem(Messages.getString("TODO")); //$NON-NLS-1$
			this.vista.getComboBoxNombreCompleto().addItem(Messages.getString("TODO")); //$NON-NLS-1$
			if (trabajadores.size() <= 100) {
				for (Trabajador t : trabajadores) {
					addRow(addTrabajadorMostrar(t));
					anadirComboBoxTrabajador(t);
				}
			} else {
				for (Trabajador t : trabajadores) {
					addRow(addTrabajadorMostrar(t));
					anadirComboBoxTrabajador(t);
				}
			}
		}
		this.vista.getTable().getColumnModel().getColumn(COLUMNA_DETALLES).setMaxWidth(80);
		this.vista.getTable().getColumnModel().getColumn(COLUMNA_DETALLES).setCellRenderer(new BotonDetallesRenderer());
		this.vista.getTable().getColumnModel().getColumn(COLUMNA_DETALLES)
				.setCellEditor(new BotonDetallesEditor(new JCheckBox()));
	}
}
