package panel.control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.jcraft.jsch.JSchException;

import exception.DialogoError;
import exception.ErrorConexionServidorException;
import exception.TrabajadorDadoDeBajaException;
import herramienta.GestorSCP;
import interfaz.EnActualizadaBBDD;
import interfaz.EnPeticionBBDD;
import objeto.Trabajador;
import panel.vista.DetallesTrabajadorPanel;
import panel.vista.VisorPDF;

public class DetallesTrabajadorPanelControl {

	private File contrato = null;
	private EnActualizadaBBDD enActualizadaBBDD;
	private EnPeticionBBDD enPeticionBBDD;
	private Trabajador trabajador;
	private DetallesTrabajadorPanel vista;

	public DetallesTrabajadorPanelControl(Trabajador trabajador, EnPeticionBBDD enPeticionBBDD,
			EnActualizadaBBDD enActualizadaBBDD) {
		this.vista = new DetallesTrabajadorPanel();
		this.trabajador = trabajador;
		this.enPeticionBBDD = enPeticionBBDD;
		this.enActualizadaBBDD = enActualizadaBBDD;

		cargarDatosTrabajador();

		this.vista.getBtnContrato().addActionListener(e -> abrirContrato());
		this.vista.getBtnDarBaja().addActionListener(e -> despedirTrabajador());
	}

	private void abrirContrato() {

		if (this.contrato == null) {
			try (GestorSCP scp = new GestorSCP()) {
				this.contrato = scp.bajarFichero(trabajador.getPathRemotoContrato());
			} catch (Exception e) {
				new DialogoError(e).showErrorMessage();
			}
		}
		try {
			VisorPDF vistaPdf = new VisorPDF();
			new VisorPDFControl(vistaPdf, this.contrato);
			vistaPdf.setLocationRelativeTo(this.vista);
			vistaPdf.setVisible(true);
		} catch (IOException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	private void cargarDatosTrabajador() {
		this.vista.getTextFieldID().setText(trabajador.getId() + "");
		this.vista.getTextFieldDNI().setText(trabajador.getIdentidad().getNif());
		if (trabajador.esDisponible())
			this.vista.getTextFieldEstado().setText("Disponible");
		else
			this.vista.getTextFieldEstado().setText("Ocupado");
		this.vista.getTextFieldNombre().setText(trabajador.getIdentidad().getNombre());
		this.vista.getTextFieldApellidos().setText(
				trabajador.getIdentidad().getApellido() + " " + trabajador.getIdentidad().getSegundoApellido());
		this.vista.getTextFieldFechaNacimiento().setText(trabajador.getIdentidad().getFechaNacimiento().toString());
		this.vista.getTextFieldPais().setText(trabajador.getIdentidad().getNacionalidad());
		this.vista.getTextAreaEncargos().setText(" ");
		if (trabajador.getPathRemotoContrato() == null)
			this.vista.getBtnContrato().setEnabled(false);

	}

	private void despedirTrabajador() {
		Object[] possibleValues = { "Si", "No", };
		Object selectedValue = JOptionPane.showInputDialog(vista,
				"Estas seguro que quieres dar de baja este trabajador", "Dar de baja", JOptionPane.WARNING_MESSAGE,
				null, possibleValues, possibleValues[0]);
		if (selectedValue.equals("Si")) {
			try {
				this.trabajador.darDeBaja();
				this.enPeticionBBDD.actualizarEstadoTrabajador(this.trabajador);
				this.enActualizadaBBDD.actualizarTrabajador(this.trabajador, false);
			} catch (Exception e) {
				new DialogoError(e).showErrorMessage();
			}
		}
	}

	public DetallesTrabajadorPanel getDetallesTrabajadorPanel() {
		return this.vista;
	}
}