package panel.control;

import java.sql.SQLException;

import exception.CamionOcupadoException;
import exception.DatoNoValidoException;
import exception.DialogoError;
import exception.MatriculaNoValidaException;
import exception.NIFNoValidoException;
import exception.RemolqueYaAsignadoException;
import exception.TrabajadorOcupadoException;
import interfaz.EnActualizadaBBDD;
import interfaz.EnPeticionBBDD;
import objeto.Presupuesto;
import objeto.RemolqueFrigorifico;
import objeto.RemolqueLona;
import objeto.RemolquePisoMovil;
import panel.vista.DetallesPresupuestoPanel;

public class DetallesPresupuestoPanelControl {

	private EnActualizadaBBDD enActualizadaBBDD;
	private EnPeticionBBDD enPeticionBBDD;
	private Presupuesto presupuesto;
	private DetallesPresupuestoPanel vista;

	public DetallesPresupuestoPanelControl(Presupuesto presupuesto, EnPeticionBBDD enPeticionBBDD,
			EnActualizadaBBDD enActualizadaBBDD) {
		this.vista = new DetallesPresupuestoPanel();
		this.presupuesto = presupuesto;
		this.enPeticionBBDD = enPeticionBBDD;
		this.enActualizadaBBDD = enActualizadaBBDD;

		this.vista.getTextFieldID().setText(presupuesto.getId() + "");
		this.vista.getTextFieldPrecio().setText(presupuesto.getPrecio() + "");
		this.vista.getTextFieldDistancia().setText(presupuesto.getDistancia() + "");
		this.vista.getTextFieldPeso().setText(presupuesto.getPesoCarga() + "");
		this.vista.getTextFieldMercancia().setText(presupuesto.getMercancia());
		this.vista.getTextFieldCliente()
				.setText(presupuesto.getCliente().getIdentidad().getRazonSocial());

		this.vista.getTextFieldDireccionOrigen().setText(presupuesto.getOrigen().getDireccion());
		this.vista.getTextFieldLocalidadOrigen().setText(presupuesto.getOrigen().getLocalidad());
		this.vista.getTextFieldCodigoPostalOrigen().setText(presupuesto.getOrigen().getCodigopostal());
		this.vista.getTextFieldProvinciaOrigen().setText(presupuesto.getOrigen().getProvincia());
		this.vista.getTextFieldPaisOrigen().setText(presupuesto.getOrigen().getPais());

		this.vista.getTextFieldDireccionDestino().setText(presupuesto.getDestino().getDireccion());
		this.vista.getTextFieldLocalidadDestino().setText(presupuesto.getDestino().getLocalidad());
		this.vista.getTextFieldCodigoPostalDestino().setText(presupuesto.getDestino().getCodigopostal());
		this.vista.getTextFieldProvinciaDestino().setText(presupuesto.getDestino().getProvincia());
		this.vista.getTextFieldPaisDestino().setText(presupuesto.getDestino().getPais());

		if (presupuesto.getRemolqueRequerido() instanceof RemolqueLona) {
			this.vista.getTextFieldClase().setText("Lona");
			if (((RemolqueLona) presupuesto.getRemolqueRequerido()).tieneCinchas())
				this.vista.getChckbxCinchas().setSelected(true);
			if (((RemolqueLona) presupuesto.getRemolqueRequerido()).abreArriba())
				this.vista.getChckbxAbrePorArriba().setSelected(true);
			if (((RemolqueLona) presupuesto.getRemolqueRequerido()).tieneEngancheRemolque())
				this.vista.getChckbxEngancheDeRemolque().setSelected(true);
		} else if (presupuesto.getRemolqueRequerido() instanceof RemolqueFrigorifico) {
			this.vista.getTextFieldClase().setText("Frigorifico");
			this.vista.getTextFieldCapacidadPalettes()
					.setText(((RemolqueFrigorifico) presupuesto.getRemolqueRequerido()).getCapacidadPalettes() + "");
		} else if (presupuesto.getRemolqueRequerido() instanceof RemolquePisoMovil) {
			this.vista.getTextFieldClase().setText("Piso movil");
			this.vista.getTextFieldVolumen()
					.setText(((RemolquePisoMovil) presupuesto.getRemolqueRequerido()).getVolumen() + "");
		}
		this.vista.getTextFieldDimensiones()
				.setText(presupuesto.getRemolqueRequerido().getDimensionesRemolque().toString());

		this.vista.getBtnGenerarEncargo().addActionListener(e -> generarEncargo());
	}

	private void generarEncargo() {
		try {
			this.enPeticionBBDD.abrirGuardarEncargoDialogoControl(this.presupuesto, this.vista);
		} catch (SQLException | NIFNoValidoException | DatoNoValidoException | TrabajadorOcupadoException
				| RemolqueYaAsignadoException | CamionOcupadoException | MatriculaNoValidaException e) {
			new DialogoError(e).showErrorMessage();
		}

	}

	public DetallesPresupuestoPanel getDetallesPresupuestoPanel() {
		return this.vista;
	}
}