package panel.control.guardar;

import java.awt.Dialog;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import constante.Messages;
import exception.DatoNoValidoException;
import exception.DialogoError;
import exception.EntidadYaExisteException;
import exception.FechaIncorrectaException;
import exception.NIFNoValidoException;
import exception.RemolqueNoCompatibleException;
import exception.TrabajadorNoAsignadoException;
import exception.TrabajadorOcupadoException;
import exception.VehiculoOcupadoExcepcion;
import interfaz.EnActualizadaBBDD;
import interfaz.EnPeticionBBDD;
import objeto.Camion;
import objeto.Encargo;
import objeto.Encargo.Builder;
import objeto.Presupuesto;
import panel.vista.guardar.GuardarEncargoDialogo;

public class GuardarEncargoDialogoControl {

	private Encargo en;
	private EnActualizadaBBDD enActualizadaBBDD;
	private Encargo encargo;
	private EnPeticionBBDD enPeticionBBDD;
	private Presupuesto presupuesto;
	private GuardarEncargoDialogo vista;

	public GuardarEncargoDialogoControl(Encargo encargo, EnPeticionBBDD enPeticionBBDD,
			EnActualizadaBBDD enActualizadaBBDD, ArrayList<Camion> camiones) {
		this.vista = new GuardarEncargoDialogo();
		this.encargo = encargo;
		this.enPeticionBBDD = enPeticionBBDD;
		this.enActualizadaBBDD = enActualizadaBBDD;

		this.vista.getBuscaCamion().addItem(Messages.getString("GuardarEncargoDialogoControl.0")); //$NON-NLS-1$

		if (camiones != null) {
			camiones.forEach(item -> {
				try {
					encargo.getPresupuesto().getRemolqueRequerido().compatible(item.getRemolque());
					this.vista.getBuscaCamion().addItem(item);
				} catch (RemolqueNoCompatibleException e1) {}
			});
		}
		this.vista.getBtnGuardar().addActionListener(e -> modificarEncargo());
	}

	public GuardarEncargoDialogoControl(Presupuesto presupuesto, EnPeticionBBDD enPeticionBBDD,
			EnActualizadaBBDD enActualizadaBBDD, ArrayList<Camion> camiones) {
		this.vista = new GuardarEncargoDialogo();
		this.presupuesto = presupuesto;
		this.enPeticionBBDD = enPeticionBBDD;
		this.enActualizadaBBDD = enActualizadaBBDD;

		this.vista.getBuscaCamion().addItem(Messages.getString("GuardarEncargoDialogoControl.0")); //$NON-NLS-1$

		if (camiones != null) {
			camiones.forEach(item -> {
				this.vista.getBuscaCamion().addItem(item);
			});
		}
		this.vista.getBtnGuardar().addActionListener(e -> crearEncargo());
	}

	private void crearEncargo() {
		try {
			Builder encargoBuilder = new Encargo.Builder(this.presupuesto);
			if (this.vista.getBuscaCamion().getSelectedIndex() != 0)
				encargoBuilder.tractora(((Camion) this.vista.getBuscaCamion().getSelectedItem()));
//			System.out.println(Messages.getString("GuardarEncargoDialogoControl.4")); //$NON-NLS-1$
			if (this.vista.getEntrega().getModel().getValue() != null) {
				Date date = new java.sql.Date(
						((java.util.Date) this.vista.getEntrega().getModel().getValue()).getTime());
				if (date.before(new Date(System.currentTimeMillis())))
					throw new FechaIncorrectaException(Messages.getString("GuardarEncargoDialogoControl.5")); //$NON-NLS-1$
				else
					encargoBuilder.fechaEntrega(date);
			}
			this.en = encargoBuilder.build();
			this.enPeticionBBDD.guardaEncargo(this.en);
			this.enActualizadaBBDD.actualizarEncargo(this.en, true);
			if (this.en.getCamion() != null)
				this.enActualizadaBBDD.actualizarCamion(this.en.getCamion(), false);
			JOptionPane.showMessageDialog(vista, Messages.getString("GuardarEncargoDialogoControl.6"), //$NON-NLS-1$
					Messages.getString("GuardarEncargoDialogoControl.7"), 1, null); //$NON-NLS-1$
			this.vista.dispose();
		} catch (VehiculoOcupadoExcepcion | RemolqueNoCompatibleException | SQLException | TrabajadorOcupadoException
				| NIFNoValidoException | EntidadYaExisteException | DatoNoValidoException
				| TrabajadorNoAsignadoException | FechaIncorrectaException e) {
			new DialogoError(e).showErrorMessage();
		}
		
	}

	public Dialog getVista() {
		return vista;
	}

	private void modificarEncargo() {
		try {
			if (this.vista.getBuscaCamion().getSelectedIndex() != 0)
				this.encargo.setVehiculo(((Camion) this.vista.getBuscaCamion().getSelectedItem()));
			if (this.vista.getEntrega().getModel().getValue() != null)
				this.encargo.setFechaEntrega(
						new java.sql.Date(((java.util.Date) this.vista.getEntrega().getModel().getValue()).getTime()));
			this.enPeticionBBDD.asignarCamionParaEncargo(this.encargo);
			this.enActualizadaBBDD.actualizarEncargo(this.encargo, false);
			this.enActualizadaBBDD.actualizarCamion(this.encargo.getCamion(), false);
			JOptionPane.showMessageDialog(vista, Messages.getString("GuardarEncargoDialogoControl.2"), //$NON-NLS-1$
					Messages.getString("GuardarEncargoDialogoControl.3"), 1, null); //$NON-NLS-1$
			this.vista.dispose();
		} catch (VehiculoOcupadoExcepcion | RemolqueNoCompatibleException | TrabajadorNoAsignadoException
				| SQLException e) {
			new DialogoError(e).showErrorMessage();
		}
	}
}
