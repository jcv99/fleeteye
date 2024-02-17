package panel.control.mostrar;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
import exception.RemolqueYaAsignadoException;
import exception.TrabajadorOcupadoException;
import herramienta.Autocompletado;
import interfaz.EnPeticionBBDD;
import objeto.Cliente;
import objeto.Presupuesto;
import panel.vista.mostrar.MostrarPresupuestoPanel;
public class MostrarPresupuestoPanelControl {
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
			botonDetalles.setText(Messages.getString("MostrarPresupuestoPanelControl.0")); //$NON-NLS-1$
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
				setBackground(UIManager.getColor(Messages.getString("MostrarPresupuestoPanelControl.1"))); //$NON-NLS-1$
			}
			setText(Messages.getString("MostrarPresupuestoPanelControl.2")); //$NON-NLS-1$
			return this;
		}
	}
	private static final int COLUMNA_CLIENTE = 8;
	private static final int COLUMNA_CPDESTINO = 4;
	private static final int COLUMNA_CPORIGEN = 1;
	private static final int COLUMNA_DETALLES = 9;
	private static final int COLUMNA_ID = 0;

	private static final int COLUMNA_LOCALIDADDESTINO = 5;

	private static final int COLUMNA_LOCALIDADORIGEN = 2;
	private static final int COLUMNA_PAISDESTINO = 6;

	private static final int COLUMNA_PAISORIGEN = 3;
	private static final int COLUMNA_PRECIO = 7;

	private Presupuesto[] aux;
	private JButton botonDetalles;
	private ArrayList<String> cliente = new ArrayList<String>();
	private EnPeticionBBDD enPeticionBBDD;
	private ArrayList<String> localidadDestino = new ArrayList<String>();
	private ArrayList<String> localidadOrigen = new ArrayList<String>();
	private DefaultTableModel model;
	private TableRowSorter<TableModel> modeloOrdenado;
	private ArrayList<String> paisDestino = new ArrayList<String>();
	private ArrayList<String> paisOrigen = new ArrayList<String>();
	int posicio = 0;

	private ArrayList<?> presupuestos;

	private MostrarPresupuestoPanel vista;

	/**
	 * @wbp.parser.entryPoint
	 */
	public MostrarPresupuestoPanelControl(MostrarPresupuestoPanel vista, EnPeticionBBDD enPeticionBBDD) {
		this.vista = vista;
		this.enPeticionBBDD = enPeticionBBDD;
		this.presupuestos = new ArrayList<Presupuesto>();
		this.botonDetalles = new JButton();

		Autocompletado.enable(this.vista.getComboBoxProvinciaOrigen());
		Autocompletado.enable(this.vista.getComboBoxPaisOrigen());
		Autocompletado.enable(this.vista.getComboBoxProvinciaDestino());
		Autocompletado.enable(this.vista.getComboBoxPaisDestino());
		Autocompletado.enable(this.vista.getComboBoxCliente());

		this.vista.getBtnFiltrar().addActionListener(e -> filtrar());
		this.vista.getBtnQuitarFiltros().addActionListener(e -> quitarFiltros());
		this.botonDetalles.addActionListener(e -> abrirDetallesPresupuesto());
		this.vista.getTable().addMouseListener(abrirDetalles());
	}

	private MouseAdapter abrirDetalles() {
		MouseAdapter open = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					abrirDetallesPresupuesto();
				}
			}
		};
		return open;

	}

	private void abrirDetallesPresupuesto() {
		String id = (String) this.vista.getTable().getValueAt(this.vista.getTable().getSelectedRow(), COLUMNA_ID);
		try {
			this.enPeticionBBDD.buscarPresupuestoParaDetalles(id, this.vista);
		} catch (NumberFormatException | SQLException | DatoNoValidoException | NIFNoValidoException
				| TrabajadorOcupadoException | RemolqueYaAsignadoException | CamionOcupadoException
				| MatriculaNoValidaException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	private void actualizarFila(Presupuesto p) {
		for (int x = 0; x < this.vista.getTable().getModel().getRowCount(); x++) {
			if (this.vista.getTable().getValueAt(x, COLUMNA_ID).toString().contentEquals(Integer.toString(p.getId()))) {
				model = (DefaultTableModel) this.vista.getTable().getModel();
				model.setValueAt(p.getOrigen().getCodigopostal(), x, COLUMNA_CPORIGEN);
				model.setValueAt(p.getOrigen().getLocalidad(), x, COLUMNA_LOCALIDADORIGEN);
				model.setValueAt(p.getOrigen().getPais(), x, COLUMNA_PAISORIGEN);
				model.setValueAt(p.getDestino().getCodigopostal(), x, COLUMNA_CPDESTINO);
				model.setValueAt(p.getDestino().getLocalidad(), x, COLUMNA_LOCALIDADDESTINO);
				model.setValueAt(p.getDestino().getPais(), x, COLUMNA_PAISDESTINO);
				model.setValueAt(p.getPrecio(), x, COLUMNA_PRECIO);
				model.setValueAt(p.getCliente().getIdentidad().getRazonSocial(), x, COLUMNA_CLIENTE);
				break;
			}
		}
	}

	public Object[] addPresMostrar(Presupuesto p) {
		return new Object[] { p.getId(), p.getOrigen().getCodigopostal(), p.getOrigen().getLocalidad(),
				p.getOrigen().getPais(), p.getDestino().getCodigopostal(), p.getDestino().getLocalidad(),
				p.getDestino().getPais(), p.getPrecio(), p.getCliente().getIdentidad().getRazonSocial(), Messages.getString("BARRAINVERTIDA") }; //$NON-NLS-1$
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
			JOptionPane.showMessageDialog(new JFrame(), Messages.getString("MostrarPresupuestoPanelControl.5"), Messages.getString("ERROR"), //$NON-NLS-1$ //$NON-NLS-2$
					JOptionPane.ERROR_MESSAGE);
		}
		this.vista.getTable().getColumnModel().getColumn(0).setMaxWidth(50);
		this.vista.getTable().getColumnModel().getColumn(0).setMinWidth(50);
		this.vista.getTable().getColumnModel().getColumn(1).setMaxWidth(70);
		this.vista.getTable().getColumnModel().getColumn(1).setMinWidth(70);
		this.vista.getTable().getColumnModel().getColumn(4).setMaxWidth(70);
		this.vista.getTable().getColumnModel().getColumn(4).setMinWidth(70);
		this.vista.getTable().getColumnModel().getColumn(7).setMaxWidth(90);
		this.vista.getTable().getColumnModel().getColumn(7).setMinWidth(90);
	}

	private void filtrar() {
		this.vista.getTable().setRowSorter(null);
		this.model = (DefaultTableModel) this.vista.getTable().getModel();
		this.modeloOrdenado = new TableRowSorter<TableModel>(this.model);
		List<RowFilter<Object, Object>> filtros = new ArrayList<RowFilter<Object, Object>>();
		this.vista.getTable().setRowSorter(this.modeloOrdenado);

		if (this.vista.getComboBoxProvinciaOrigen().getSelectedIndex() != 0) {
			String t = this.vista.getComboBoxProvinciaOrigen().getSelectedItem().toString();
			filtros.add(RowFilter.regexFilter(t, COLUMNA_LOCALIDADORIGEN));
		}
		if (this.vista.getComboBoxPaisOrigen().getSelectedIndex() != 0) {
			String t = this.vista.getComboBoxPaisOrigen().getSelectedItem().toString();
			filtros.add(RowFilter.regexFilter(t, COLUMNA_PAISORIGEN));
		}
		if (this.vista.getComboBoxProvinciaDestino().getSelectedIndex() != 0) {
			String t = this.vista.getComboBoxProvinciaDestino().getSelectedItem().toString();
			filtros.add(RowFilter.regexFilter(t, COLUMNA_LOCALIDADDESTINO));
		}
		if (this.vista.getComboBoxPaisDestino().getSelectedIndex() != 0) {
			String t = this.vista.getComboBoxPaisDestino().getSelectedItem().toString();
			filtros.add(RowFilter.regexFilter(t, COLUMNA_PAISDESTINO));
		}
		if (this.vista.getComboBoxCliente().getSelectedIndex() != 0) {
			String t = this.vista.getComboBoxCliente().getSelectedItem().toString();
			filtros.add(RowFilter.regexFilter(t, COLUMNA_CLIENTE));
		}

		RowFilter<Object, Object> servicioFiltros = RowFilter.andFilter(filtros);
		this.modeloOrdenado.setRowFilter(servicioFiltros);
	}

	private void preparaComboboxes(ArrayList<String> item, JComboBox<String> combobox) {
		ArrayList<String> itemFiltrado = new ArrayList<String>();
		
		item.forEach(i -> {
			if (!itemFiltrado.contains(i)) itemFiltrado.add(i);
		});
		itemFiltrado.forEach(i -> {
			combobox.addItem(i);
		});
		
		item = null;
	}

	private void quitarFiltros() {
		this.vista.getTable().setRowSorter(null);
		this.vista.getComboBoxProvinciaOrigen().setSelectedIndex(0);
		this.vista.getComboBoxPaisOrigen().setSelectedIndex(0);
		this.vista.getComboBoxProvinciaDestino().setSelectedIndex(0);
		this.vista.getComboBoxPaisDestino().setSelectedIndex(0);
		this.vista.getComboBoxCliente().setSelectedIndex(0);
	}

	public void refrescarPresupuesto(Presupuesto p, boolean nuevo) {
		if (!nuevo)
			actualizarFila(p);
		else
			addRow(addPresMostrar(p));
	}
	
	public void refrescarCliente(Cliente p, boolean nuevo) {
		if (nuevo)
			this.vista.getComboBoxCliente().addItem(p.getIdentidad().getRazonSocial());
	}

	public void setHeader(String[] s) {
		DefaultTableModel model = new DefaultTableModel(s, 0);
		this.vista.getTable().setModel(model);
	}

	public void setModelArr(Object[] ob) {
		for (int i = 0; i < ob.length; i++) {
			addRow(ob[i]);
		}
	}

	@SuppressWarnings("unchecked")
	public void setPresupuestos(ArrayList<Presupuesto> presupuestos) {
		this.presupuestos = presupuestos;
		model = (DefaultTableModel) this.vista.getTable().getModel();
		model.getDataVector().removeAllElements();
		modeloOrdenado = new TableRowSorter<TableModel>(model);
		this.vista.getTable().setRowSorter(null);
		this.vista.getComboBoxProvinciaOrigen().addItem(Messages.getString("TODO")); //$NON-NLS-1$
		this.vista.getComboBoxProvinciaDestino().addItem(Messages.getString("TODO")); //$NON-NLS-1$
		this.vista.getComboBoxPaisOrigen().addItem(Messages.getString("TODO")); //$NON-NLS-1$
		this.vista.getComboBoxPaisDestino().addItem(Messages.getString("TODO")); //$NON-NLS-1$
		this.vista.getComboBoxCliente().addItem(Messages.getString("TODO")); //$NON-NLS-1$

		setHeader(new String[] { Messages.getString("MostrarPresupuestoPanelControl.16"), Messages.getString("MostrarPresupuestoPanelControl.17"), Messages.getString("MostrarPresupuestoPanelControl.18"), Messages.getString("MostrarPresupuestoPanelControl.19"), Messages.getString("MostrarPresupuestoPanelControl.20"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				Messages.getString("MostrarPresupuestoPanelControl.21"), Messages.getString("MostrarPresupuestoPanelControl.22"), Messages.getString("MostrarPresupuestoPanelControl.23"), Messages.getString("MostrarPresupuestoPanelControl.24"), Messages.getString("VACIO") }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		if (this.presupuestos != null) {
			for (Presupuesto p : presupuestos) {
				localidadOrigen.add(p.getOrigen().getLocalidad());
				localidadDestino.add(p.getDestino().getLocalidad());
				paisOrigen.add(p.getOrigen().getPais());
				paisDestino.add(p.getDestino().getPais());
				cliente.add(p.getCliente().getIdentidad().getRazonSocial());
				addRow(addPresMostrar(p));
			}
			
			preparaComboboxes(localidadOrigen, this.vista.getComboBoxProvinciaOrigen());
			preparaComboboxes(localidadDestino, this.vista.getComboBoxProvinciaDestino());
			preparaComboboxes(paisOrigen, this.vista.getComboBoxPaisOrigen());
			preparaComboboxes(paisDestino, this.vista.getComboBoxPaisDestino());
			preparaComboboxes(cliente, this.vista.getComboBoxCliente());
			
			this.vista.getTable().getColumnModel().getColumn(COLUMNA_DETALLES).setMaxWidth(80);
			this.vista.getTable().getColumnModel().getColumn(COLUMNA_DETALLES)
					.setCellRenderer(new BotonDetallesRenderer());
			this.vista.getTable().getColumnModel().getColumn(COLUMNA_DETALLES)
					.setCellEditor(new BotonDetallesEditor(new JCheckBox()));

		}
	}
}
