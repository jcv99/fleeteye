package panel.control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
import interfaz.EnPeticionBBDD;
import objeto.Cliente;
import objeto.Encargo;
import objeto.Presupuesto;
import panel.control.mostrar.MostrarGraficaDialogoControl;
import panel.vista.DetallesClientePanel;

public class DetallesClientePanelControl {

	private static final int ECOLUMNA_CAMION = 2;
	private static final int ECOLUMNA_CLIENTE = 11;
	private static final int ECOLUMNA_CPDESTINO = 6;
	private static final int ECOLUMNA_CPORIGEN = 3;
	private static final int ECOLUMNA_DETALLES = 12;

	private static final int ECOLUMNA_ESTADO = 1;
	private static final int ECOLUMNA_FECHAFIN = 10;
	private static final int ECOLUMNA_FECHAINICIO = 9;
	private static final int ECOLUMNA_ID = 0;
	private static final int ECOLUMNA_LOCALIDADDESTINO = 7;
	private static final int ECOLUMNA_LOCALIDADORIGEN = 4;
	private static final int ECOLUMNA_PAISDESTINO = 8;
	private static final int ECOLUMNA_PAISORIGEN = 5;
	private static final int PCOLUMNA_CLIENTE = 8;
	private static final int PCOLUMNA_CPDESTINO = 4;
	private static final int PCOLUMNA_CPORIGEN = 1;
	private static final int PCOLUMNA_DETALLES = 9;
	private static final int PCOLUMNA_ID = 0;

	private static final int PCOLUMNA_LOCALIDADDESTINO = 5;
	private static final int PCOLUMNA_LOCALIDADORIGEN = 2;
	private static final int PCOLUMNA_PAISDESTINO = 6;
	private static final int PCOLUMNA_PAISORIGEN = 3;
	private static final int PCOLUMNA_PRECIO = 7;
	private Cliente cliente;
	private ArrayList<Encargo> encargos;
	private EnPeticionBBDD enPeticionBBDD;
	private ArrayList<Presupuesto> presupuestos;
	private DetallesClientePanel vista;

	public DetallesClientePanelControl(Cliente cliente, EnPeticionBBDD enPeticionBBDD,
			ArrayList<Presupuesto> presupuestos, ArrayList<Encargo> encargos) {
		this.vista = new DetallesClientePanel();
		this.cliente = cliente;
		this.enPeticionBBDD = enPeticionBBDD;
		this.encargos = encargos;
		this.presupuestos = presupuestos;
		this.vista.getTextFieldID().setText(cliente.getId() + "");
		this.vista.getTextFieldNIF().setText(cliente.getIdentidad().getNif());
		this.vista.getTextFieldRazonSocial()
				.setText(cliente.getIdentidad().getRazonSocial());
		this.vista.getTextFieldActividadEco()
				.setText(cliente.getIdentidad().getActividadEconomica());

		this.vista.getBtnConsultaDato().addActionListener(e -> consultarDatos());
		this.vista.getBtnGuardarPresupuesto().addActionListener(e -> guardarPresupuesto());
		setEncargos(this.encargos);
		setPresupuestos(this.presupuestos);
		this.vista.getTableEncargos().addMouseListener(abrirDetallesE());
		this.vista.getTablePresupuestos().addMouseListener(abrirDetallesP());
	}

	private MouseAdapter abrirDetallesE() {
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
		String id = (String) this.vista.getTableEncargos().getValueAt(this.vista.getTableEncargos().getSelectedRow(),
				ECOLUMNA_ID);
		try {
			enPeticionBBDD.buscarEncargoParaDetalles(id, this.vista);
		} catch (NumberFormatException | SQLException | DatoNoValidoException | NIFNoValidoException
				| TrabajadorOcupadoException | RemolqueYaAsignadoException | CamionOcupadoException
				| VehiculoOcupadoExcepcion | RemolqueNoCompatibleException | TrabajadorNoAsignadoException
				| MatriculaNoValidaException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	private MouseAdapter abrirDetallesP() {
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
		String id = (String) this.vista.getTablePresupuestos()
				.getValueAt(this.vista.getTablePresupuestos().getSelectedRow(), PCOLUMNA_ID);
		try {
			this.enPeticionBBDD.buscarPresupuestoParaDetalles(id, this.vista);
		} catch (NumberFormatException | SQLException | DatoNoValidoException | NIFNoValidoException
				| TrabajadorOcupadoException | RemolqueYaAsignadoException | CamionOcupadoException
				| MatriculaNoValidaException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	private Object[] addEncMostrar(Encargo e) {

		String fechafin;
		String estado = null;
		String fechainicio;
		String camion;
		if (e.getFechaFin() == null)
			fechafin = "Sin acabar";
		else
			fechafin = e.getFechaFin().toString();
		if (e.getEstado() == Encargo.COMPLETADO)
			estado = "Completado";
		else if (e.getEstado() == Encargo.ENCURSO)
			estado = "En curso";
		else if (e.getEstado() == Encargo.PORHACER)
			estado = "Por hacer";
		else if (e.getEstado() == Encargo.PREPARADO)
			estado = "Preparado";

		camion = e.getCamion() != null ? e.getCamion().getMatricula() + "/" + e.getCamion().getRemolque().getMatricula()
				: "Sin camion";
		fechainicio = e.getFechaInicio() != null ? e.getFechaInicio().toString() : "Sin iniciar";

		return new Object[] { e.getId() + "", estado, camion, e.getPresupuesto().getOrigen().getCodigopostal(),
				e.getPresupuesto().getOrigen().getLocalidad(), e.getPresupuesto().getOrigen().getPais(),
				e.getPresupuesto().getDestino().getCodigopostal(), e.getPresupuesto().getDestino().getLocalidad(),
				e.getPresupuesto().getDestino().getPais(), fechainicio, fechafin };
	}

	private void addRowEncargo(Object[] ob) {

		String[] string = new String[ob.length];

		for (int i = 0; i < ob.length; i++) {
			string[i] = ob[i].toString();
		}
		this.vista.getTableEncargos().setRowHeight(30);
		if (this.vista.getTableEncargos().getModel().getColumnCount() == string.length) {
			((DefaultTableModel) this.vista.getTableEncargos().getModel()).addRow(string);
		} else {
			for (int i = 0; i < string.length; i++) {
				System.out.print(string[i] + " ");
			}
			JOptionPane.showMessageDialog(new JFrame(), "Cuidado que hay diferenctes columnas en la tabla", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		this.vista.getTableEncargos().getColumnModel().getColumn(0).setMaxWidth(40);
		this.vista.getTableEncargos().getColumnModel().getColumn(0).setMinWidth(40);
		this.vista.getTableEncargos().getColumnModel().getColumn(1).setMaxWidth(80);
		this.vista.getTableEncargos().getColumnModel().getColumn(1).setMinWidth(80);
		this.vista.getTableEncargos().getColumnModel().getColumn(3).setMaxWidth(60);
		this.vista.getTableEncargos().getColumnModel().getColumn(3).setMinWidth(60);
		this.vista.getTableEncargos().getColumnModel().getColumn(6).setMaxWidth(60);
		this.vista.getTableEncargos().getColumnModel().getColumn(6).setMinWidth(60);

	}

	private void addRowPresu(Object[] ob) {

		String[] string = new String[ob.length];

		for (int i = 0; i < ob.length; i++) {
			string[i] = ob[i].toString();
		}
		this.vista.getTablePresupuestos().setRowHeight(30);
		if (this.vista.getTablePresupuestos().getModel().getColumnCount() == string.length) {
			((DefaultTableModel) this.vista.getTablePresupuestos().getModel()).addRow(string);
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Cuidado que hay diferenctes columnas en la tabla", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		this.vista.getTablePresupuestos().getColumnModel().getColumn(0).setMaxWidth(40);
		this.vista.getTablePresupuestos().getColumnModel().getColumn(0).setMinWidth(40);
		this.vista.getTablePresupuestos().getColumnModel().getColumn(1).setMaxWidth(60);
		this.vista.getTablePresupuestos().getColumnModel().getColumn(1).setMinWidth(60);
		this.vista.getTablePresupuestos().getColumnModel().getColumn(4).setMaxWidth(60);
		this.vista.getTablePresupuestos().getColumnModel().getColumn(4).setMinWidth(60);
	}

	private void consultarDatos() {
		Double[][] datos = new Double[2][2];
		datos[0][0] = 1.0;
		datos[1][0] = 2.0;

		MostrarGraficaDialogoControl graficaControl = new MostrarGraficaDialogoControl(datos, "Viajes",
				"Viajes por mes en un ano", "Mes", "Viajes");

		graficaControl.setVisible(true);
	}

	public DetallesClientePanel getDetallesClientePanel() {
		return this.vista;
	}

	private void guardarPresupuesto() {
		this.enPeticionBBDD.buscarParaGuardarPresupuesto(this.cliente, this.vista);
	}

	public void setEncargos(ArrayList<Encargo> encargos) {
		setHeaderEncargos(new String[] { "Id", "Estado", "Camion", "CP origen ", "Localidad origen", "Pais origen",
				"CP destino", "Localidad destino", "Pais destino", "Fecha Inicio", "Fecha Fin" });
		if (encargos != null) {
			encargos.forEach(item -> {
				if (item != null) {
					addRowEncargo(addEncMostrar(item));
				}
			});
		}
	}

	private void setHeaderEncargos(String[] s) {
		DefaultTableModel model = new DefaultTableModel(s, 0);
		this.vista.getTableEncargos().setModel(model);
	}

	private void setHeaderPresupuestos(String[] s) {
		DefaultTableModel model = new DefaultTableModel(s, 0);
		this.vista.getTablePresupuestos().setModel(model);

	}

	public void setPresupuestos(ArrayList<Presupuesto> presupuesto) {
		setHeaderPresupuestos(new String[] { "Id", "CP origen ", "Localidad origen", "Pais origen", "CP destino",
				"Localidad destino", "Pais destino", "Precio" });
		if (presupuesto != null) {
			presupuesto.forEach(item -> {
				addRowPresu(new Object[] { item.getId(), item.getOrigen().getCodigopostal(),
						item.getOrigen().getLocalidad(), item.getOrigen().getPais(),
						item.getDestino().getCodigopostal(), item.getDestino().getLocalidad(),
						item.getDestino().getPais(), item.getPrecio() });
			});
		}

	}

}