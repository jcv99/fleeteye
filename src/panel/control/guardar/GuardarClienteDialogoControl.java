package panel.control.guardar;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import constante.Messages;
import exception.DatoNoValidoException;
import exception.DialogoError;
import exception.EntidadYaExisteException;
import exception.NIFNoValidoException;
import interfaz.EnActualizadaBBDD;
import interfaz.EnPeticionBBDD;
import objeto.Cif;
import objeto.Cliente;
import objeto.IdentidadPersonaJuridica;
import panel.vista.guardar.GuardarClientDialogo;

public class GuardarClienteDialogoControl {

	private final EnPeticionBBDD enPeticionBBDD;
	private final EnActualizadaBBDD enActualizadaBBDD;
	private final GuardarClientDialogo vista;

	public GuardarClienteDialogoControl(EnPeticionBBDD enPeticionBBDD, EnActualizadaBBDD enActualizadaBBDD) {
		this.vista = new GuardarClientDialogo();
		this.enPeticionBBDD = enPeticionBBDD;
		this.enActualizadaBBDD = enActualizadaBBDD;

		this.vista.getBtnGuardar().addActionListener(e -> crearCliente());
	}

	private void crearCliente() {
		try {
			IdentidadPersonaJuridica IdentidadPersonaJuridica = crearPersonaJuridica();
			Cliente cliente = new Cliente(IdentidadPersonaJuridica);
			guardarCliente(cliente);
		} catch (Exception e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	private IdentidadPersonaJuridica crearPersonaJuridica() throws NIFNoValidoException, DatoNoValidoException {
		return new IdentidadPersonaJuridica(
				new Cif(validarNIF(vista.getGuardarPersonaJuridica().getTxtNif().getText())),
				validarRazonSocial(vista.getGuardarPersonaJuridica().getTxtRazon().getText()),
				validarActividad(vista.getGuardarPersonaJuridica().getTxtActividad().getText()));
	}

	private String validarNIF(String nif) throws NIFNoValidoException {
		// Add your NIF validation logic here
		return nif;
	}

	private String validarRazonSocial(String razonSocial) {
		// Add your validation logic for razon social here
		return razonSocial;
	}

	private String validarActividad(String actividad) {
		// Add your validation logic for actividad here
		return actividad;
	}

	private void guardarCliente(Cliente cliente) throws SQLException, EntidadYaExisteException, DatoNoValidoException {
		enPeticionBBDD.guardaCliente(cliente);
		enActualizadaBBDD.actualizarCliente(cliente, true);
		JOptionPane.showMessageDialog(vista, Messages.getString("GuardarClientDialogoControl.0"), //$NON-NLS-1$
				Messages.getString("GuardarClientDialogoControl.1"), JOptionPane.INFORMATION_MESSAGE); //$NON-NLS-1$
		vista.dispose();
	}

	public GuardarClientDialogo getVista() {
		return vista;
	}
}