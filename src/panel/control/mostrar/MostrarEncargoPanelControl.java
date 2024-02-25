package panel.control.mostrar;

import java.awt.event.ItemEvent;
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
import objeto.Camion;
import objeto.Cliente;
import objeto.Encargo;
import panel.vista.mostrar.MostrarClientePanel;
import panel.vista.mostrar.MostrarEncargoPanel;

public class MostrarEncargoPanelControl {

	private static final int COLUMNA_ID = 0;
	private static final int COLUMNA_ESTADO = 1;
	private static final int COLUMNA_CAMION = 2;
	private static final int COLUMNA_CPORIGEN = 3;
	private static final int COLUMNA_LOCALIDADORIGEN = 4;
	private static final int COLUMNA_PAISORIGEN = 5;
	private static final int COLUMNA_CPDESTINO = 6;
	private static final int COLUMNA_LOCALIDADDESTINO = 7;
	private static final int COLUMNA_PAISDESTINO = 8;
	private static final int COLUMNA_FECHAINICIO = 9;
	private static final int COLUMNA_FECHAFIN = 10;
	private static final int COLUMNA_CLIENTE = 11;
	private static final int COLUMNA_DETALLES = 12;

	private JButton botonDetalles;
	private EnPeticionBBDD enPeticionBBDD;
	private DefaultTableModel model;
	private TableRowSorter<TableModel> modeloOrdenado;

	private MostrarEncargoPanel vista;

	/**
	 * @wbp.parser.entryPoint
	 */
	public MostrarEncargoPanelControl(MostrarEncargoPanel vista, EnPeticionBBDD enPeticionBBDD) {
		this.vista = vista;
		this.enPeticionBBDD = enPeticionBBDD;

		this.botonDetalles = new JButton();

		Autocompletado.enable(this.vista.getCombCliente());
		Autocompletado.enable(this.vista.getCombCamion());

		this.vista.getBtnFiltrar().addActionListener(e -> filtrar());
		this.vista.getBtnQuitaFiltros().addActionListener(e -> quitarFiltros());
		this.vista.getChckbxCompletado().addItemListener(e -> enCompletadoSeleccionado(e));
		this.vista.getChckbxEnCurso().addItemListener(e -> enEnCursoSeleccionado(e));
		this.vista.getChckbxPorHacer().addItemListener(e -> enPorHacerSeleccionado(e));
		this.botonDetalles.addActionListener(e -> abrirDetallesEncargo());

		this.vista.getTable().addMouseListener(abrirDetalles());
	}

	public MouseAdapter abrirDetalles() {
		MouseAdapter open = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					abrirDetallesEncargo();
				}
			}
		};
		return open;

	}

	private void abrirDetallesEncargo() {
		try {
			String id = (String) this.vista.getTable().getValueAt(this.vista.getTable().getSelectedRow(), COLUMNA_ID);
			this.enPeticionBBDD.buscarEncargoParaDetalles(id, this.vista);
		} catch (Exception e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	private void actualizarFila(Encargo e) {
		for (int x = 0; x < this.vista.getTable().getModel().getRowCount(); x++) {
			if (this.vista.getTable().getValueAt(x, COLUMNA_ID).toString().contentEquals(Integer.toString(e.getId()))) {
				model = (DefaultTableModel) this.vista.getTable().getModel();
				String fechafin;
				String estado = null;
				String fechainicio;
				String camion;
				if (e.getFechaFin() == null)
					fechafin = Messages.getString("MostrarEncargoPanelControl.8"); //$NON-NLS-1$
				else
					fechafin = e.getFechaFin().toString();
				if (e.getEstado() == Encargo.COMPLETADO)
					estado = Messages.getString("MostrarEncargoPanelControl.9"); //$NON-NLS-1$
				else if (e.getEstado() == Encargo.ENCURSO)
					estado = Messages.getString("MostrarEncargoPanelControl.10"); //$NON-NLS-1$
				else if (e.getEstado() == Encargo.PORHACER)
					estado = Messages.getString("MostrarEncargoPanelControl.11"); //$NON-NLS-1$
				else if (e.getEstado() == Encargo.PREPARADO)
					estado = Messages.getString("MostrarEncargoPanelControl.12"); //$NON-NLS-1$
				camion = e.getCamion() != null ? e.getCamion().getMatricula() + Messages.getString("BARRA") //$NON-NLS-1$
						+ e.getCamion().getRemolque().getMatricula()
						: Messages.getString("MostrarEncargoPanelControl.14"); //$NON-NLS-1$
				fechainicio = e.getFechaInicio() != null ? e.getFechaInicio().toString()
						: Messages.getString("MostrarEncargoPanelControl.15"); //$NON-NLS-1$
				model.setValueAt(estado, x, COLUMNA_ESTADO);
				model.setValueAt(camion, x, COLUMNA_CAMION);
				model.setValueAt(e.getPresupuesto().getOrigen().getCodigopostal(), x, COLUMNA_CPORIGEN);
				model.setValueAt(e.getPresupuesto().getOrigen().getLocalidad(), x, COLUMNA_LOCALIDADORIGEN);
				model.setValueAt(e.getPresupuesto().getOrigen().getPais(), x, COLUMNA_PAISORIGEN);
				model.setValueAt(e.getPresupuesto().getDestino().getCodigopostal(), x, COLUMNA_CPDESTINO);
				model.setValueAt(e.getPresupuesto().getDestino().getLocalidad(), x, COLUMNA_LOCALIDADDESTINO);
				model.setValueAt(e.getPresupuesto().getDestino().getPais(), x, COLUMNA_PAISDESTINO);
				model.setValueAt(e.getPresupuesto().getCliente().getIdentidad().getRazonSocial(), x, COLUMNA_CLIENTE);
				model.setValueAt(fechainicio, x, COLUMNA_FECHAINICIO);
				model.setValueAt(fechafin, x, COLUMNA_FECHAFIN);
				break;
			}
		}
	}

	private Object[] addEncMostrar(Encargo e) {

		String fechafin;
		String estado = null;
		String fechainicio;
		String camion;
		if (e.getFechaFin() == null)
			fechafin = Messages.getString("MostrarEncargoPanelControl.16"); //$NON-NLS-1$
		else
			fechafin = e.getFechaFin().toString();
		if (e.getEstado() == Encargo.COMPLETADO)
			estado = Messages.getString("MostrarEncargoPanelControl.17"); //$NON-NLS-1$
		else if (e.getEstado() == Encargo.ENCURSO)
			estado = Messages.getString("MostrarEncargoPanelControl.18"); //$NON-NLS-1$
		else if (e.getEstado() == Encargo.PORHACER)
			estado = Messages.getString("MostrarEncargoPanelControl.19"); //$NON-NLS-1$
		else if (e.getEstado() == Encargo.PREPARADO)
			estado = Messages.getString("MostrarEncargoPanelControl.20"); //$NON-NLS-1$

		camion = e.getCamion() != null && e.getCamion().getRemolque() != null
				? e.getCamion().getMatricula() + Messages.getString("BARRA") //$NON-NLS-1$
						+ e.getCamion().getRemolque().getMatricula()
				: Messages.getString("MostrarEncargoPanelControl.22"); //$NON-NLS-1$
		fechainicio = e.getFechaInicio() != null ? e.getFechaInicio().toString()
				: Messages.getString("MostrarEncargoPanelControl.23"); //$NON-NLS-1$

		return new Object[] { e.getId() + Messages.getString("VACIO"), estado, camion, //$NON-NLS-1$
				e.getPresupuesto().getOrigen().getCodigopostal(), e.getPresupuesto().getOrigen().getLocalidad(),
				e.getPresupuesto().getOrigen().getPais(), e.getPresupuesto().getDestino().getCodigopostal(),
				e.getPresupuesto().getDestino().getLocalidad(), e.getPresupuesto().getDestino().getPais(), fechainicio,
				fechafin, e.getPresupuesto().getCliente().getIdentidad().getRazonSocial(),
				Messages.getString("BARRAINVERTIDA") }; //$NON-NLS-1$
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
			JOptionPane.showMessageDialog(new JFrame(), Messages.getString("MostrarEncargoPanelControl.26"), //$NON-NLS-1$
					Messages.getString("ERROR"), //$NON-NLS-1$
					JOptionPane.ERROR_MESSAGE);
		}
		this.vista.getTable().getColumnModel().getColumn(0).setMaxWidth(50);
		this.vista.getTable().getColumnModel().getColumn(0).setMinWidth(50);
		this.vista.getTable().getColumnModel().getColumn(1).setMaxWidth(90);
		this.vista.getTable().getColumnModel().getColumn(1).setMinWidth(90);
		this.vista.getTable().getColumnModel().getColumn(3).setMaxWidth(70);
		this.vista.getTable().getColumnModel().getColumn(3).setMinWidth(70);
		this.vista.getTable().getColumnModel().getColumn(6).setMaxWidth(80);
		this.vista.getTable().getColumnModel().getColumn(6).setMinWidth(80);
	}

	private void borrarItemCamion(Camion c) {
		this.vista.getCombCamion().removeItem(c.getMatricula());
	}

	private void borrarItemCliente(Cliente c) {
		this.vista.getCombCliente().removeItem(c.getIdentidad().getRazonSocial());
	}

	private void enCompletadoSeleccionado(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (this.vista.getChckbxEnCurso().isSelected())
				this.vista.getChckbxEnCurso().setSelected(false);
			this.vista.getChckbxPorHacer().setSelected(false);
		}
	}

	private void enEnCursoSeleccionado(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (this.vista.getChckbxCompletado().isSelected())
				this.vista.getChckbxCompletado().setSelected(false);
			this.vista.getChckbxPorHacer().setSelected(false);
		}
	}

	private void enPorHacerSeleccionado(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (this.vista.getChckbxPorHacer().isSelected())
				this.vista.getChckbxCompletado().setSelected(false);
			this.vista.getChckbxEnCurso().setSelected(false);
		}
	}

	private void filtrar() {
		this.vista.getTable().setRowSorter(null);
		modeloOrdenado = new TableRowSorter<TableModel>(model);
		List<RowFilter<Object, Object>> filtros = new ArrayList<RowFilter<Object, Object>>();
		this.vista.getTable().setRowSorter(modeloOrdenado);

		if (this.vista.getCombCamion().getSelectedIndex() != 0) {
			String t = this.vista.getCombCamion().getSelectedItem().toString();
			filtros.add(RowFilter.regexFilter(t, COLUMNA_CAMION));
		}
		if (this.vista.getCombCliente().getSelectedIndex() != 0) {
			String c = this.vista.getCombCliente().getSelectedItem().toString();
			filtros.add(RowFilter.regexFilter(c, COLUMNA_CLIENTE));
		}
		if (this.vista.getChckbxEnCurso().isSelected()) {
			filtros.add(RowFilter.regexFilter(Messages.getString("MostrarEncargoPanelControl.28"), COLUMNA_ESTADO)); //$NON-NLS-1$
		} else if (this.vista.getChckbxCompletado().isSelected()) {
			filtros.add(RowFilter.regexFilter(Messages.getString("MostrarEncargoPanelControl.29"), COLUMNA_ESTADO)); //$NON-NLS-1$
		} else if (this.vista.getChckbxPorHacer().isSelected()) {
			filtros.add(RowFilter.regexFilter(Messages.getString("MostrarEncargoPanelControl.30"), COLUMNA_ESTADO)); //$NON-NLS-1$
		}

		RowFilter<Object, Object> servicioFiltros = RowFilter.andFilter(filtros);
		this.modeloOrdenado.setRowFilter(servicioFiltros);
	}

	private void quitarFiltros() {
		this.vista.getTable().setRowSorter(null);
		this.vista.getChckbxEnCurso().setSelected(false);
		this.vista.getChckbxCompletado().setSelected(false);
		this.vista.getChckbxCompletado().setSelected(false);
		this.vista.getCombCliente().setSelectedIndex(0);
		this.vista.getCombCamion().setSelectedIndex(0);
	}

	public void refrescarCamion(Camion c, boolean nuevo) {
		if (!nuevo)
			borrarItemCamion(c);
		this.vista.getCombCamion().addItem(c.getMatricula());
	}

	public void refrescarCliente(Cliente c, boolean nuevo) {
		if (!nuevo)
			borrarItemCliente(c);
		this.vista.getCombCliente().addItem(c.getIdentidad().getRazonSocial());
	}

	public void refrescarEncargo(Encargo e, boolean nuevo) {
		if (!nuevo)
			actualizarFila(e);
		else
			addRow(addEncMostrar(e));
	}

	public void setCamiones(ArrayList<Camion> camiones) {
		this.vista.getCombCamion().removeAllItems();
		this.vista.getCombCamion().addItem(Messages.getString("TODO")); //$NON-NLS-1$
		if (camiones != null) {
			for (Camion t : camiones)
				this.vista.getCombCamion().addItem(t.getMatricula());
		}
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.vista.getCombCliente().removeAllItems();
		this.vista.getCombCliente().addItem(Messages.getString("TODO")); //$NON-NLS-1$
		if (clientes != null) {
			for (Cliente c : clientes)
				this.vista.getCombCliente().addItem(c.getIdentidad().getRazonSocial());
		}
	}

	public void setEncargos(ArrayList<Encargo> encargos) {
		model = (DefaultTableModel) vista.getTable().getModel();
		model.getDataVector().removeAllElements();
		modeloOrdenado = new TableRowSorter<TableModel>(model);
		vista.getTable().setRowSorter(null);
		Utils.setHeader(new String[] { Messages.getString("MostrarEncargoPanelControl.37"), //$NON-NLS-1$
				Messages.getString("MostrarEncargoPanelControl.38"), //$NON-NLS-1$
				Messages.getString("MostrarEncargoPanelControl.39"), //$NON-NLS-1$
				Messages.getString("MostrarEncargoPanelControl.40"), //$NON-NLS-1$
				Messages.getString("MostrarEncargoPanelControl.41"), //$NON-NLS-1$
				Messages.getString("MostrarEncargoPanelControl.42"), //$NON-NLS-1$
				Messages.getString("MostrarEncargoPanelControl.43"), //$NON-NLS-1$
				Messages.getString("MostrarEncargoPanelControl.44"), //$NON-NLS-1$
				Messages.getString("MostrarEncargoPanelControl.45"), //$NON-NLS-1$
				Messages.getString("MostrarEncargoPanelControl.46"), //$NON-NLS-1$
				Messages.getString("MostrarEncargoPanelControl.47"), //$NON-NLS-1$
				Messages.getString("MostrarEncargoPanelControl.48"), Messages.getString("VACIO") }, //$NON-NLS-1$ //$NON-NLS-2$
				this.vista.getTable());
		if (encargos != null) {
			for (Encargo e : encargos) {
				addRow(addEncMostrar(e));
			}
			vista.getTable().getColumnModel().getColumn(COLUMNA_DETALLES).setMaxWidth(80);
			vista.getTable().getColumnModel().getColumn(COLUMNA_DETALLES).setCellRenderer(new BotonDetallesRenderer());
			vista.getTable().getColumnModel().getColumn(COLUMNA_DETALLES)
					.setCellEditor(new BotonDetallesEditor(new JCheckBox(), botonDetalles));
		}
	}

}
