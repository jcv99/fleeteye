package panel.control.mostrar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import exception.DialogoError;
import herramienta.Autocompletado;
import herramienta.Utils;
import interfaz.EnPeticionBBDD;
import objeto.Cliente;
import panel.vista.mostrar.MostrarClientePanel;

public class MostrarClientePanelControl {
	private static final int COLUMNA_ACTIVIDADECONOMICA = 3;
	private static final int COLUMNA_DETALLES = 4;
	private static final int COLUMNA_ID = 0;
	private static final int COLUMNA_NIF = 1;

	private static final int COLUMNA_RAZONSOCIAL = 2;

	private JButton botonDetalles;
	private ArrayList<Cliente> clientes;
	private EnPeticionBBDD enPeticionBBDD;
	private DefaultTableModel model;
	private TableRowSorter<TableModel> modeloOrdenado;

	int posicio = 0;

	private MostrarClientePanel vista;

	public MostrarClientePanelControl(MostrarClientePanel vista, EnPeticionBBDD enPeticionBBDD) {
		this.vista = vista;
		this.enPeticionBBDD = enPeticionBBDD;
		this.clientes = new ArrayList<>();
		this.botonDetalles = new JButton();

		Autocompletado.enable(this.vista.getComboBoxCIF());
		Autocompletado.enable(this.vista.getComboBoxRazonSocial());

		this.vista.getBtnFiltrar().addActionListener(e -> filtrar());
		this.vista.getBtnQuitarFiltros().addActionListener(e -> quitarFiltros());
		this.botonDetalles.addActionListener(e -> abrirDetallesCliente());
		this.vista.getTable().addMouseListener(abrirDetalles());
	}

	public MouseAdapter abrirDetalles() {
		MouseAdapter open = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					abrirDetallesCliente();
				}
			}
		};
		return open;

	}

	private void abrirDetallesCliente() {
		String id = (String) this.vista.getTable().getValueAt(this.vista.getTable().getSelectedRow(), COLUMNA_ID);
		try {
			enPeticionBBDD.buscarClienteParaDetalles(id, this.vista);
		} catch (Exception e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	private void actualizarFila(Cliente c) {
		for (int x = 0; x < this.vista.getTable().getModel().getRowCount(); x++) {
			if (this.vista.getTable().getValueAt(x, COLUMNA_ID).toString().contentEquals(Integer.toString(c.getId()))) {
				model = (DefaultTableModel) this.vista.getTable().getModel();
				model.setValueAt(c.getIdentidad().getNif(), x, COLUMNA_NIF);
				model.setValueAt(c.getIdentidad().getRazonSocial(), x, COLUMNA_RAZONSOCIAL);
				model.setValueAt(c.getIdentidad().getActividadEconomica(), x, COLUMNA_ACTIVIDADECONOMICA);
				break;
			}
		}
	}

	public Object[] addClieMostrar(Cliente c) {
		return new Object[] { c.getId(), c.getIdentidad().getNif(), c.getIdentidad().getRazonSocial(),
				c.getIdentidad().getActividadEconomica(), Messages.getString("BARRAINVERTIDA") }; //$NON-NLS-1$
	}

	private void addRow(Object... ob) {
		String[] string = new String[ob.length];

		for (int i = 0; i < ob.length; i++) {
			string[i] = ob[i].toString();
		}
		this.vista.getTable().setRowHeight(30);
		if (this.vista.getTable().getModel().getColumnCount() == string.length) {
			((DefaultTableModel) this.vista.getTable().getModel()).addRow(string);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), Messages.getString("MostrarClientePanelControl.5"), //$NON-NLS-1$
					Messages.getString("ERROR"), //$NON-NLS-1$
					JOptionPane.ERROR_MESSAGE);
		}
		this.vista.getTable().getColumnModel().getColumn(0).setMaxWidth(50);
		this.vista.getTable().getColumnModel().getColumn(1).setMaxWidth(90);
		this.vista.getTable().getColumnModel().getColumn(0).setMinWidth(50);
		this.vista.getTable().getColumnModel().getColumn(1).setMinWidth(90);
	}

	private void anadirCombos(Cliente c) {
		this.vista.getComboBoxCIF().addItem(c.getIdentidad().getNif());
		this.vista.getComboBoxRazonSocial().addItem(c.getIdentidad().getRazonSocial());
	}

	private void filtrar() {
		this.vista.getTable().setRowSorter(null);
		this.model = (DefaultTableModel) this.vista.getTable().getModel();
		this.modeloOrdenado = new TableRowSorter<TableModel>(this.model);
		List<RowFilter<Object, Object>> filtros = new ArrayList<RowFilter<Object, Object>>();
		this.vista.getTable().setRowSorter(this.modeloOrdenado);

		if (this.vista.getComboBoxCIF().getSelectedIndex() != 0) {
			filtros.add(RowFilter.regexFilter(this.vista.getComboBoxCIF().getSelectedItem().toString(), COLUMNA_NIF));
		}
		if (this.vista.getComboBoxRazonSocial().getSelectedIndex() != 0) {
			filtros.add(RowFilter.regexFilter(this.vista.getComboBoxRazonSocial().getSelectedItem().toString(),
					COLUMNA_RAZONSOCIAL));
		}
		this.vista.getTable().getColumnModel().getColumn(0).setMaxWidth(10);
		RowFilter<Object, Object> servicioFiltros = RowFilter.andFilter(filtros);
		this.modeloOrdenado.setRowFilter(servicioFiltros);
	}

	private void quitarFiltros() {
		this.vista.getTable().setRowSorter(null);
		this.vista.getComboBoxCIF().setSelectedIndex(0);
		this.vista.getComboBoxRazonSocial().setSelectedIndex(0);
	}

	public void refrescarCliente(Cliente c, boolean nuevo) {
		if (!nuevo)
			actualizarFila(c);
		else {
			addRow(addClieMostrar(c));
			anadirCombos(c);
		}
	}

	public void setCIFCliente(ArrayList<Cliente> clientes) {
		this.vista.getComboBoxCIF().removeAllItems();
		this.vista.getComboBoxCIF().addItem(Messages.getString("TODO")); //$NON-NLS-1$
		if (clientes != null) {
			for (Cliente c : clientes)
				this.vista.getComboBoxCIF().addItem(c.getIdentidad().getNif());
		}
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
		this.model = (DefaultTableModel) this.vista.getTable().getModel();
		this.model.getDataVector().removeAllElements();
		this.modeloOrdenado = new TableRowSorter<TableModel>(this.model);
		this.vista.getTable().setRowSorter(null);
		Utils.setHeader(new String[] { Messages.getString("MostrarClientePanelControl.12"), //$NON-NLS-1$
				Messages.getString("MostrarClientePanelControl.13"), //$NON-NLS-1$
				Messages.getString("MostrarClientePanelControl.14"), //$NON-NLS-1$
				Messages.getString("MostrarClientePanelControl.15"), //$NON-NLS-1$
				Messages.getString("VACIO") }, this.vista.getTable()); //$NON-NLS-1$
		if (this.clientes != null) {
			for (Cliente c : clientes) {
				addRow(addClieMostrar(c));
			}

			this.vista.getTable().getColumnModel().getColumn(COLUMNA_DETALLES).setMaxWidth(80);
			this.vista.getTable().getColumnModel().getColumn(COLUMNA_DETALLES)
					.setCellRenderer(new BotonDetallesRenderer());
			this.vista.getTable().getColumnModel().getColumn(COLUMNA_DETALLES)
					.setCellEditor(new BotonDetallesEditor(new JCheckBox(), botonDetalles));
		}

	}

	public void setModelArr(Object[] ob) {
		for (int i = 0; i < ob.length; i++) {
			addRow(ob[i]);
		}
	}

	public void setRazonSocial(ArrayList<Cliente> clientes) {
		this.vista.getComboBoxRazonSocial().removeAllItems();
		this.vista.getComboBoxRazonSocial().addItem(Messages.getString("TODO")); //$NON-NLS-1$
		if (clientes != null) {
			for (Cliente c : clientes)
				this.vista.getComboBoxRazonSocial().addItem(c.getIdentidad().getRazonSocial());
		}
	}
}
