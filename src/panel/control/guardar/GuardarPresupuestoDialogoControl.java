package panel.control.guardar;

import java.awt.Dialog;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import constante.Messages;
import exception.DatoNoValidoException;
import exception.DialogoError;
import exception.EntidadYaExisteException;
import interfaz.EnActualizadaBBDD;
import interfaz.EnPeticionBBDD;
import objeto.Cliente;
import objeto.Presupuesto;
import objeto.Presupuesto.Builder;
import objeto.Remolque;
import objeto.RemolqueFrigorifico;
import objeto.RemolqueLona;
import objeto.RemolquePisoMovil;
import objeto.Ubicacion;
import panel.vista.guardar.GuardarPresupuestoDialogo;
import panel.vista.guardar.GuardarRemolqueRequeridoDialogo;
import panel.vista.guardar.GuardarUbicacionDialogo;
public class GuardarPresupuestoDialogoControl {

	private Cliente cliente;
	private EnActualizadaBBDD enActualizadaBBDD;
	private EnPeticionBBDD enPeticionBBDD;
	private Remolque r = null;
	private ArrayList<Ubicacion> ubicaciones;
	private GuardarPresupuestoDialogo vista;

	public GuardarPresupuestoDialogoControl(EnPeticionBBDD enPeticionBBDD, EnActualizadaBBDD enActualizadaBBDD,
			ArrayList<Ubicacion> ubicaciones, Cliente cliente) {
		this.vista = new GuardarPresupuestoDialogo();
		this.enPeticionBBDD = enPeticionBBDD;
		this.enActualizadaBBDD = enActualizadaBBDD;
		this.ubicaciones = ubicaciones;
		this.cliente = cliente;

		this.vista.getBtnCrearPresupuesto().addActionListener(e -> crearPresupuesto());
		this.vista.getBtnOptionsOrigen().addActionListener(e -> guardarUbicacionOrigen());
		this.vista.getBtnOptionsDestino().addActionListener(e -> guardarUbicacionDestino());
		this.vista.getBtnNueRemolque().addActionListener(e -> nuevoRemolque());
	}

	private void crearPresupuesto() {
		Presupuesto p = null;

		Pattern pa = Pattern.compile(Messages.getString("GuardarPresupuestoDialogoControl.14")); //$NON-NLS-1$
		if (!this.vista.getTxtOrigen().getText().isBlank() && !this.vista.getTxtDestino().getText().isBlank()) {
			String[] ubicacionOrigen = this.vista.getTxtOrigen().getText()
					.split(Messages.getString("GuardarPresupuestoDialogoControl.15")); //$NON-NLS-1$
			String[] ubicacionDestino = this.vista.getTxtDestino().getText()
					.split(Messages.getString("GuardarPresupuestoDialogoControl.15")); //$NON-NLS-1$
			if ((!this.vista.getTxtOrigen().getText().contentEquals(this.vista.getTxtDestino().getText()))) {
				if (!vista.getTxtPrecio().getText().isBlank()) {
					if (pa.matcher(vista.getTxtPrecio().getText()).find()) {
						if (vista.getTxtPrecio().getText().length() < 9) {
							try {
								Builder bP;
								if (r != null) {
									bP = new Presupuesto.Builder(
											enPeticionBBDD.buscarUbicacionPorAtributos(ubicacionOrigen[0],
													ubicacionOrigen[1], ubicacionOrigen[2], ubicacionOrigen[3],
													ubicacionOrigen[4]),
											enPeticionBBDD.buscarUbicacionPorAtributos(ubicacionDestino[0],
													ubicacionDestino[1], ubicacionDestino[2], ubicacionDestino[3],
													ubicacionDestino[4]),
											Double.parseDouble(vista.getTxtPrecio().getText()), this.cliente, r);
								} else {
									r = RemolqueLona.remolqueNoRequerido();
									bP = new Presupuesto.Builder(
											enPeticionBBDD.buscarUbicacionPorAtributos(ubicacionOrigen[0],
													ubicacionOrigen[1], ubicacionOrigen[2], ubicacionOrigen[3],
													ubicacionOrigen[4]),
											enPeticionBBDD.buscarUbicacionPorAtributos(ubicacionDestino[0],
													ubicacionDestino[1], ubicacionDestino[2], ubicacionDestino[3],
													ubicacionDestino[4]),
											Double.parseDouble(vista.getTxtPrecio().getText()), this.cliente, r);
								}
								if (!this.vista.getTextFieldDistancia().getText().isBlank())
									bP.distancia(Integer.parseInt(this.vista.getTextFieldDistancia().getText()));
								if (!this.vista.getTextFieldPeso().getText().isBlank())
									bP.pesoCarga(Double.parseDouble(this.vista.getTextFieldPeso().getText()));
								if (!this.vista.getTextFieldMercancia().getText().isBlank())
									bP.mercancia(this.vista.getTextFieldMercancia().getText());

								p = bP.build();
							} catch (NumberFormatException | DatoNoValidoException | SQLException e) {
								new DialogoError(e).showErrorMessage();
							}
							try {
								this.enPeticionBBDD.guardaPresupuesto(p);
								this.enActualizadaBBDD.actualizarPresupuesto(p, true);
								this.vista.getBtnCrearPresupuesto().setEnabled(false);
								JOptionPane.showMessageDialog(vista,
										Messages.getString("GuardarPresupuestoDialogoControl.17"), //$NON-NLS-1$
										Messages.getString("GuardarPresupuestoDialogoControl.18"), 1, null); //$NON-NLS-1$
								this.vista.dispose();
							} catch (SQLException | DatoNoValidoException | EntidadYaExisteException e) {
								new DialogoError(e).showErrorMessage();
							}
						} else
							new DialogoError(new Exception(Messages.getString("GuardarPresupuestoDialogoControl.19"))) //$NON-NLS-1$
									.showErrorMessage();
					} else
						new DialogoError(new Exception(Messages.getString("GuardarPresupuestoDialogoControl.20"))) //$NON-NLS-1$
								.showErrorMessage();
				} else
					new DialogoError(new Exception(Messages.getString("GuardarPresupuestoDialogoControl.21"))) //$NON-NLS-1$
							.showErrorMessage();
			}
		} else {
			new DialogoError(new Exception(Messages.getString("GuardarPresupuestoDialogoControl.22"))) //$NON-NLS-1$
					.showErrorMessage();
		}
	}

	public Dialog getVista() {
		return vista;
	}

	private void guardarUbicacionDestino() {
		GuardarUbicacionDialogoControl guardarUbicacion = new GuardarUbicacionDialogoControl(
				new GuardarUbicacionDialogo(), ubicaciones, this,
				Messages.getString("GuardarPresupuestoDialogoControl.13"), enPeticionBBDD, enActualizadaBBDD); //$NON-NLS-1$
		guardarUbicacion.getVista().setLocationRelativeTo(this.vista);
		guardarUbicacion.getVista().setVisible(true);
	}

	private void guardarUbicacionOrigen() {
		GuardarUbicacionDialogoControl guardarUbicacion = new GuardarUbicacionDialogoControl(
				new GuardarUbicacionDialogo(), ubicaciones, this,
				Messages.getString("ORIGEN"), enPeticionBBDD, enActualizadaBBDD); //$NON-NLS-1$
		guardarUbicacion.getVista().setLocationRelativeTo(this.vista);
		guardarUbicacion.getVista().setVisible(true);
	}

	private void nuevoRemolque() {
		GuardarRemolqueRequeridoDialogo guardRemolque = new GuardarRemolqueRequeridoDialogo();
		GuardarRemolqueRequeridoDialogoControl guardarRemolqueControl = new GuardarRemolqueRequeridoDialogoControl(
				guardRemolque);
		guardarRemolqueControl.getVista().setLocationRelativeTo(this.vista);
		guardarRemolqueControl.getVista().setVisible(true);
		if (guardarRemolqueControl.getRemolque() != null) {
			r = guardarRemolqueControl.getRemolque();
			String texto = r.getDimensionesRemolque().getAltura()
					+ Messages.getString("GuardarPresupuestoDialogoControl.0") //$NON-NLS-1$
					+ r.getDimensionesRemolque().getLongitud()
					+ Messages.getString("GuardarPresupuestoDialogoControl.0") //$NON-NLS-1$
					+ r.getDimensionesRemolque().getAnchura() + Messages.getString("BARRAINVERTIDA") //$NON-NLS-1$
					+ r.getTara();

			if (r instanceof RemolqueLona) {
				String atributos = Messages.getString("BARRAINVERTIDA"); //$NON-NLS-1$
				if (((RemolqueLona) r).tieneCinchas()) {
					atributos += Messages.getString("GuardarPresupuestoDialogoControl.4"); //$NON-NLS-1$
				}
				if (((RemolqueLona) r).tieneEngancheRemolque()) {
					atributos += Messages.getString("GuardarPresupuestoDialogoControl.5"); //$NON-NLS-1$
				}
				if (((RemolqueLona) r).abreArriba()) {
					atributos += Messages.getString("GuardarPresupuestoDialogoControl.6"); //$NON-NLS-1$
				}
				this.vista.getTxtRemolque()
						.setText(Messages.getString("GuardarPresupuestoDialogoControl.7") + texto + atributos); //$NON-NLS-1$
			}
			if (r instanceof RemolqueFrigorifico) {
				this.vista.getTxtRemolque()
						.setText(Messages.getString("GuardarPresupuestoDialogoControl.8") + texto //$NON-NLS-1$
								+ Messages.getString("GuardarPresupuestoDialogoControl.9") //$NON-NLS-1$
								+ ((RemolqueFrigorifico) r).getCapacidadPalettes());
			}
			if (r instanceof RemolquePisoMovil) {
				this.vista.getTxtRemolque()
						.setText(Messages.getString("GuardarPresupuestoDialogoControl.10") + texto //$NON-NLS-1$
								+ Messages.getString("GuardarPresupuestoDialogoControl.11") //$NON-NLS-1$
								+ ((RemolquePisoMovil) r).getVolumen());
			}

		}
	}
}
