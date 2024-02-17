package panel.control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jcraft.jsch.JSchException;

import exception.CamionOcupadoException;
import exception.DatoNoValidoException;
import exception.DialogoError;
import exception.ErrorConexionServidorException;
import exception.MatriculaNoValidaException;
import exception.NIFNoValidoException;
import exception.RemolqueYaAsignadoException;
import exception.TrabajadorDadoDeBajaException;
import exception.TrabajadorOcupadoException;
import herramienta.GestorSCP;
import interfaz.EnActualizadaBBDD;
import interfaz.EnPeticionBBDD;
import objeto.Camion;
import objeto.Remolque;
import objeto.Trabajador;
import panel.vista.DetallesCamionPanel;
import panel.vista.VisorPDF;

public class DetallesCamionPanelControl {

	private Camion camion;
	private File contrato;
	private EnActualizadaBBDD enActualizadaBBDD;
	private EnPeticionBBDD enPeticionBBDD;
	private ArrayList<Remolque> remolquesDispo;
	private ArrayList<Trabajador> trabajadoresDispo;
	private DetallesCamionPanel vista;

	public DetallesCamionPanelControl(Camion camion, ArrayList<Remolque> remolquesDispo,
			ArrayList<Trabajador> trabajadoresDispo, EnPeticionBBDD enPeticionBBDD,
			EnActualizadaBBDD enActualizadaBBDD) {
		this.vista = new DetallesCamionPanel();
		this.camion = camion;
		this.enPeticionBBDD = enPeticionBBDD;
		this.enActualizadaBBDD = enActualizadaBBDD;

		this.remolquesDispo = remolquesDispo;
		this.trabajadoresDispo = trabajadoresDispo;

		if (this.camion.getPathRemoto() == null) {
			this.vista.getBtnConsultarDocumentacion().setEnabled(false);
		}

		cargarDatosCamion();

		this.vista.getBtnAsignarRemolque().addActionListener(e -> asignarRemolque());
		this.vista.getBtnAsignarTrabajador().addActionListener(e -> asignarTrabajador());
		this.vista.getBtnConsultarDocumentacion().addActionListener(e -> abrirContrato());
	}

	private void abrirContrato() {

		if (this.contrato == null) {
			try (GestorSCP scp = new GestorSCP()) {
				this.contrato = scp.bajarFichero(this.camion.getPathRemoto());
			} catch (IOException | JSchException | ErrorConexionServidorException e) {
				new DialogoError(e).showErrorMessage();
			} catch (Exception e) {
				new DialogoError(e).showErrorMessage();
			}
		}
		try {
			VisorPDF vistaPdf = new VisorPDF();
			VisorPDFControl controlPdf = new VisorPDFControl(vistaPdf, this.contrato);
			vistaPdf.setLocationRelativeTo(this.vista);
			vistaPdf.setVisible(true);
		} catch (IOException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	private void asignarRemolque() {
		Remolque rAntiguo = null;
		Remolque rNuevo = null;
		if (this.vista.getComboBoxRemolque().getSelectedIndex() == 0)
			try {
				if (this.camion.getRemolque() != null) {
					rAntiguo = this.camion.quitarRemolque();
					this.enPeticionBBDD.quitarRemolqueCamion(this.camion);
					this.enActualizadaBBDD.actualizarCamion(this.camion, false);
					this.enPeticionBBDD.cambiarEstadoRemolqueDisponible(rAntiguo);
					this.enActualizadaBBDD.actualizarRemolque(rAntiguo, false);
				}
			} catch (CamionOcupadoException | SQLException | TrabajadorOcupadoException | DatoNoValidoException
					| NIFNoValidoException | RemolqueYaAsignadoException | MatriculaNoValidaException e) {
			}
		else {
			try {
				if (this.camion.getRemolque() != null) {
					rAntiguo = this.camion.quitarRemolque();
					this.enPeticionBBDD.quitarRemolqueCamion(this.camion);
					this.enActualizadaBBDD.actualizarCamion(this.camion, false);
					this.enPeticionBBDD.cambiarEstadoRemolqueDisponible(rAntiguo);
					this.enActualizadaBBDD.actualizarRemolque(rAntiguo, false);
				}
				rNuevo = (Remolque) this.vista.getComboBoxRemolque().getSelectedItem();
				this.camion.asignRemolque(rNuevo);
				this.enPeticionBBDD.asignarRemolqueParaCamion(this.camion);
				this.enActualizadaBBDD.actualizarCamion(this.camion, false);
			} catch (CamionOcupadoException | SQLException | TrabajadorOcupadoException | DatoNoValidoException
					| NIFNoValidoException | RemolqueYaAsignadoException | MatriculaNoValidaException e) {
			}

		}
	}

	private void asignarTrabajador() {
		boolean cambioHecho = false;
		Trabajador t = null;
		if (this.vista.getComboBoxTrabajador().getSelectedIndex() == 0)
			try {
				t = this.camion.quitarTrabajador();
				cambioHecho = true;
			} catch (CamionOcupadoException e) {
				new DialogoError(e).showErrorMessage();
			}
		else {
			try {
				if (this.camion.getTrabajador() != null)
					t = this.camion.quitarTrabajador();
				this.camion.trabajador((Trabajador) this.vista.getComboBoxTrabajador().getSelectedItem());
				cambioHecho = true;
			} catch (TrabajadorOcupadoException | TrabajadorDadoDeBajaException | CamionOcupadoException e) {
				new DialogoError(e).showErrorMessage();
			}
		}
		if (cambioHecho) {
			try {
				this.enPeticionBBDD.quitarTrabajadorParaCamion(this.camion);
				this.enPeticionBBDD.asignarTrabajadorParaCamion(this.camion);
				if (t != null)
					this.enActualizadaBBDD.actualizarTrabajador(t, false);
				if (this.camion.getTrabajador() != null)
					this.enActualizadaBBDD.actualizarTrabajador(this.camion.getTrabajador(), false);
				this.enActualizadaBBDD.actualizarCamion(this.camion, false);
				cargarDatosCamion();
			} catch (SQLException | TrabajadorOcupadoException | DatoNoValidoException | NIFNoValidoException
					| RemolqueYaAsignadoException | CamionOcupadoException | MatriculaNoValidaException e) {
				new DialogoError(e).showErrorMessage();
			}
		}
	}

	private void cargarDatosCamion() {
		this.vista.getTextFieldMatricula().setText(this.camion.getMatricula());
		this.vista.getTxtMarca().setText(this.camion.getMarca());
		this.vista.getTxtModelo().setText(this.camion.getModelo());
		this.vista.getTxtTara().setText(this.camion.getTara() + "");

		String combustible = this.camion.getCombustible() != null ? this.camion.getCombustible() : "";
		String potencia = this.camion.getPotencia() > 0 ? this.camion.getPotencia() + "" : "";
		String normativa = this.camion.getNormativa() != null ? this.camion.getNormativa() : "";
		String kilometraje = this.camion.getKilometraje() > 0 ? this.camion.getKilometraje() + "" : "";
		String eje = this.camion.getConfiguracionEje() != null ? this.camion.getConfiguracionEje() : "";
		String matriculacion = this.camion.getFechaMatriculacion() != null
				? this.camion.getFechaMatriculacion().toString()
				: "";

		this.vista.getTextFieldCombustible().setText(combustible);
		this.vista.getTxtPotencia().setText(potencia);
		this.vista.getTextFieldNormativa().setText(normativa);
		this.vista.getTxtKilometraje().setText(kilometraje);
		this.vista.getTxtEje().setText(eje);
		this.vista.getTextFieldMatriculacion().setText(matriculacion);

		if (this.camion.esOcupado()) {
			this.vista.getComboBoxRemolque().addItem(this.camion.getRemolque());
			this.vista.getComboBoxRemolque().setEnabled(false);
			this.vista.getComboBoxTrabajador().addItem(this.camion.getTrabajador());
			this.vista.getComboBoxTrabajador().setEnabled(false);
			this.vista.getBtnAsignarRemolque().setEnabled(false);
			this.vista.getBtnAsignarTrabajador().setEnabled(false);
		} else if (this.camion.esDisponible()) {
			setRemolques(this.remolquesDispo);
			setTrabajadores(this.trabajadoresDispo);
			if (this.camion.getRemolque() != null) {
				this.vista.getComboBoxRemolque().addItem(this.camion.getRemolque());
				this.vista.getComboBoxRemolque().setSelectedIndex(this.vista.getComboBoxRemolque().getItemCount() - 1);
			}
			if (this.camion.getTrabajador() != null) {
				this.vista.getComboBoxTrabajador().addItem(this.camion.getTrabajador());
				this.vista.getComboBoxTrabajador()
						.setSelectedIndex(this.vista.getComboBoxTrabajador().getItemCount() - 1);
			}
		}
	}

	public DetallesCamionPanel getDetallesCamionPanel() {
		return this.vista;
	}

	private void setRemolques(ArrayList<Remolque> remolquesDispo) {
		this.vista.getComboBoxRemolque().removeAll();
		this.vista.getComboBoxRemolque().addItem("Sin remolque");
		remolquesDispo.forEach(item -> {
			this.vista.getComboBoxRemolque().addItem(item);
		});
	}

	private void setTrabajadores(ArrayList<Trabajador> trabajadoresDispo) {
		this.vista.getComboBoxTrabajador().removeAll();
		this.vista.getComboBoxTrabajador().addItem("Sin trabajador");
		trabajadoresDispo.forEach(item -> {
			this.vista.getComboBoxTrabajador().addItem(item);
		});
	}
}