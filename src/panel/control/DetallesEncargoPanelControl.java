package panel.control;

import java.sql.SQLException;

import exception.CamionOcupadoException;
import exception.DatoNoValidoException;
import exception.DialogoError;
import exception.EncargoEstadoNoValidoException;
import exception.MatriculaNoValidaException;
import exception.NIFNoValidoException;
import exception.RemolqueNoCompatibleException;
import exception.RemolqueYaAsignadoException;
import exception.TrabajadorNoAsignadoException;
import exception.TrabajadorOcupadoException;
import exception.VehiculoOcupadoExcepcion;
import interfaz.EnActualizadaBBDD;
import interfaz.EnPeticionBBDD;
import objeto.Encargo;
import panel.vista.DetallesEncargoPanel;

public class DetallesEncargoPanelControl {

	private EnActualizadaBBDD enActualizadaBBDD;
	private Encargo encargo;
	private EnPeticionBBDD enPeticionBBDD;
	private DetallesEncargoPanel vista;

	public DetallesEncargoPanelControl(Encargo encargo, EnPeticionBBDD enPeticionBBDD,
			EnActualizadaBBDD enActualizadaBBDD) {
		this.vista = new DetallesEncargoPanel();
		this.encargo = encargo;
		this.enPeticionBBDD = enPeticionBBDD;
		this.enActualizadaBBDD = enActualizadaBBDD;

		cargarDatosEncargo();

		this.vista.getBtnDetallesPresupuesto().addActionListener(e -> abrirPresupuesto());
		this.vista.getBtnDetallesCamion().addActionListener(e -> abrirCamion());
		this.vista.getBtnFinalizarEncargo().addActionListener(e -> finalizarEncargo());
		this.vista.getBtnIniciarEncargo().addActionListener(e -> iniciarEncargo());
		this.vista.getBtnAsignarCamion().addActionListener(e -> asignarCamion());
	}

	private void abrirCamion() {
		try {
			this.enPeticionBBDD.buscarCamionParaDetalles(this.encargo.getCamion().getId() + "", this.vista);
		} catch (NumberFormatException | SQLException | DatoNoValidoException | NIFNoValidoException
				| TrabajadorOcupadoException | RemolqueYaAsignadoException | CamionOcupadoException
				| MatriculaNoValidaException | VehiculoOcupadoExcepcion | RemolqueNoCompatibleException | TrabajadorNoAsignadoException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	private void abrirPresupuesto() {
		try {
			this.enPeticionBBDD.buscarPresupuestoParaDetalles(this.encargo.getPresupuesto().getId() + "", this.vista);
		} catch (NumberFormatException | SQLException | DatoNoValidoException | NIFNoValidoException
				| TrabajadorOcupadoException | RemolqueYaAsignadoException | CamionOcupadoException
				| MatriculaNoValidaException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	private void asignarCamion() {
		try {
			this.enPeticionBBDD.asignarCamionEncargo(this.encargo, this.vista);
			this.enActualizadaBBDD.actualizarCamion(this.encargo.getCamion(), false);
			cargarDatosEncargo();
		} catch (SQLException | NIFNoValidoException | DatoNoValidoException | TrabajadorOcupadoException
				| RemolqueYaAsignadoException | CamionOcupadoException | MatriculaNoValidaException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	private void cargarDatosEncargo() {
		if (this.encargo.esPorHacer()) {
			this.vista.getBtnIniciarEncargo().setEnabled(false);
			this.vista.getBtnFinalizarEncargo().setEnabled(false);
			this.vista.getBtnDetallesCamion().setEnabled(false);
		} else if (this.encargo.esPreparado()) {
			this.vista.getBtnIniciarEncargo().setEnabled(true);
			this.vista.getBtnFinalizarEncargo().setEnabled(false);
			this.vista.getBtnAsignarCamion().setEnabled(false);
			this.vista.getBtnDetallesCamion().setEnabled(true);
		} else if (this.encargo.esEnCurso()) {
			this.vista.getBtnIniciarEncargo().setEnabled(false);
			this.vista.getBtnFinalizarEncargo().setEnabled(true);
			this.vista.getBtnAsignarCamion().setEnabled(false);
		} else if (this.encargo.esCompletado()) {
			this.vista.getBtnIniciarEncargo().setEnabled(false);
			this.vista.getBtnFinalizarEncargo().setEnabled(false);
			this.vista.getBtnAsignarCamion().setEnabled(false);
		}

		this.vista.getTextFieldID().setText(this.encargo.getId() + "");
		if (this.encargo.esPorHacer())
			this.vista.getTextFieldEstado().setText("Pendiente");
		else if (this.encargo.esPreparado())
			this.vista.getTextFieldEstado().setText("Preparado");
		else if (this.encargo.esEnCurso())
			this.vista.getTextFieldEstado().setText("En curso");
		else if (this.encargo.esCompletado())
			this.vista.getTextFieldEstado().setText("Completado");

		if (this.encargo.getFechaEntrega() != null)
			this.vista.getTextFieldFechaEntrega().setText(this.encargo.getFechaEntrega().toString());
		else
			this.vista.getTextFieldFechaEntrega().setText("");

		this.vista.getTextFieldCliente()
				.setText(this.encargo.getPresupuesto().getCliente().getIdentidad()
						.getRazonSocial());

		if (this.encargo.getFechaInicio() != null)
			this.vista.getTextFieldFechaInicio().setText(this.encargo.getFechaInicio().toString());

		if (this.encargo.getFechaFin() != null)
			this.vista.getTextFieldFechaFin().setText(this.encargo.getFechaFin().toString());

		this.vista.getTextFieldIdPresupuesto().setText(this.encargo.getPresupuesto().getId() + "");
		this.vista.getTextFieldMercancia().setText(this.encargo.getPresupuesto().getMercancia());
		this.vista.getTextFieldPeso().setText(this.encargo.getPresupuesto().getPesoCarga() + "");
		this.vista.getTextFieldOrigen().setText(this.encargo.getPresupuesto().getOrigen().getProvincia());
		this.vista.getTextFieldDestino().setText(this.encargo.getPresupuesto().getDestino().getProvincia());
		this.vista.getTextFieldPrecio().setText(this.encargo.getPresupuesto().getPrecio() + "");
		this.vista.getTextFieldDistancia().setText(this.encargo.getPresupuesto().getDistancia() + "");

		if (this.encargo.getCamion() != null) {
			this.vista.getTextFieldIdCamion().setText(this.encargo.getCamion().getId() + "");
			this.vista.getTextFieldTractora().setText(this.encargo.getCamion().getMatricula());
			this.vista.getTextFieldRemolque().setText(this.encargo.getCamion().getRemolque().getMatricula());
			this.vista.getTextFieldDNI().setText(this.encargo.getCamion().getTrabajador().getIdentidad().getNif());
			this.vista.getTextFieldNombre()
					.setText(this.encargo.getCamion().getTrabajador().getIdentidad().getNombre());
			this.vista.getTextFieldApellidos().setText(
					this.encargo.getCamion().getTrabajador().getIdentidad().getApellido()
							+ " " + this.encargo.getCamion().getTrabajador().getIdentidad()
									.getSegundoApellido());
		}
	}

	private void finalizarEncargo() {
		try {
			this.encargo.completarEncargo();
			this.enPeticionBBDD.completarEncargo(this.encargo);
			this.enActualizadaBBDD.actualizarEncargo(this.encargo, false);
			this.enActualizadaBBDD.actualizarCamion(this.encargo.getCamion(), false);
			cargarDatosEncargo();
		} catch (EncargoEstadoNoValidoException e) {
			new DialogoError(e).showErrorMessage();
		} catch (SQLException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	public DetallesEncargoPanel getDetallesEncargoPanel() {
		return this.vista;
	}

	private void iniciarEncargo() {
		try {
			this.encargo.inicializar();
			this.enPeticionBBDD.iniciarEncargo(this.encargo);
			this.enActualizadaBBDD.actualizarEncargo(this.encargo, false);
			cargarDatosEncargo();
		} catch (EncargoEstadoNoValidoException e) {
			new DialogoError(e).showErrorMessage();
		} catch (SQLException e) {
			new DialogoError(e).showErrorMessage();
		}
	}
}