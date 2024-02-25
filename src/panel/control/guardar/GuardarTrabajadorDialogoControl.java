package panel.control.guardar;

import java.awt.Dialog;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.jcraft.jsch.JSchException;

import constante.ConstantesStrings;
import constante.Messages;
import exception.DatoNoValidoException;
import exception.DialogoError;
import exception.EntidadYaExisteException;
import exception.ErrorConexionServidorException;
import exception.NIFNoValidoException;
import herramienta.ConfiguracionServidor;
import herramienta.GestorSCP;
import herramienta.PDFEscogerFichero;
import interfaz.EnActualizadaBBDD;
import interfaz.EnPeticionBBDD;
import objeto.IdentidadPersonaFisica;
import objeto.Nif;
import objeto.Trabajador;
import panel.vista.guardar.GuardarTrabajadorDialogo;
public class GuardarTrabajadorDialogoControl {

	private File contrato = null;
	private EnActualizadaBBDD enActualizadaBBDD;
	private EnPeticionBBDD enPeticionBBDD;
	private Trabajador t;
	private GuardarTrabajadorDialogo vista;

	public GuardarTrabajadorDialogoControl(GuardarTrabajadorDialogo vista, EnPeticionBBDD enPeticionBBDD,
			EnActualizadaBBDD enActualizadaBBDD) {
		this.vista = vista;
		this.enPeticionBBDD = enPeticionBBDD;
		this.enActualizadaBBDD = enActualizadaBBDD;
		this.vista.getBtnGuardar().addActionListener(e -> crearTrabajador());
		this.vista.getBtnCurriculumSubir().addActionListener(e -> obtenerFichero());
	}

	private void crearTrabajador() {
		if (!(this.vista.getGuardarPersonaJuridica().getTxtNif().getText().isBlank()
				&& this.vista.getGuardarPersonaJuridica().getTxtNombre().getText().isBlank()
				&& this.vista.getGuardarPersonaJuridica().getTxtApellido().getText().isBlank()
				&& this.vista.getGuardarPersonaJuridica().getTxtSegApellido().getText().isBlank()
				&& this.vista.getGuardarPersonaJuridica().getTxtNacional().getText().isBlank())) {

			try {
				t = new Trabajador(new IdentidadPersonaFisica(
						new Nif(this.vista.getGuardarPersonaJuridica().getTxtNif().getText()),
						this.vista.getGuardarPersonaJuridica().getTxtNombre().getText(),
						this.vista.getGuardarPersonaJuridica().getTxtApellido().getText(),
						this.vista.getGuardarPersonaJuridica().getTxtSegApellido().getText(),
						this.vista.getGuardarPersonaJuridica().getTxtNacional().getText(),
						new java.sql.Date(((java.util.Date) this.vista.getGuardarPersonaJuridica()
								.getDatePickerFechaNacimiento().getModel().getValue()).getTime())));
				if (this.contrato != null) {
					t.setPathRemotoContrato(
							ConfiguracionServidor.PATH_REMOTO_TRABAJADOR + t.getIdentidad().getNif() + Messages.getString("GuardarTrabajadorDialogoControl.0")); //$NON-NLS-1$
					enPeticionBBDD.guardaTrabajador(t);
					try (GestorSCP scp = new GestorSCP()) {
						scp.subirFichero(this.contrato.getAbsolutePath(), t.getPathRemotoContrato());
					} catch (JSchException | ErrorConexionServidorException | IOException e) {
						new DialogoError(e).showErrorMessage();
					} catch (Exception e) {
					}
				} else {
					enPeticionBBDD.guardaTrabajador(t);
				}
				enActualizadaBBDD.actualizarTrabajador(t, true);
				JOptionPane.showMessageDialog(vista, Messages.getString("GuardarTrabajadorDialogoControl.1"), Messages.getString("GuardarTrabajadorDialogoControl.2"), 1, null); //$NON-NLS-1$ //$NON-NLS-2$
				this.vista.dispose();
			} catch (NIFNoValidoException | DatoNoValidoException | SQLException | EntidadYaExisteException e) {
				new DialogoError(e).showErrorMessage();
			}
		} else {
			new DialogoError(new Exception(ConstantesStrings.CAMPOSVACIOS)).showErrorMessage();
		}
	}

	public Dialog getVista() {
		return vista;
	}

	private void obtenerFichero() {
		this.contrato = new PDFEscogerFichero().obtenerOrigen();
		if (this.contrato != null) {
			if (this.contrato.exists())
				this.vista.getTxtCurriculum().setText(this.contrato.getAbsolutePath());
			else
				this.contrato = null;
		}
	}

}