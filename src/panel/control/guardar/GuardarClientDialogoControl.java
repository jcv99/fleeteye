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
public class GuardarClientDialogoControl {

	private Cliente c;
	private EnActualizadaBBDD enActualizadaBBDD;
	private EnPeticionBBDD enPeticionBBDD;
	private GuardarClientDialogo vista;

	public GuardarClientDialogoControl(GuardarClientDialogo vista, EnPeticionBBDD enPeticionBBDD,
			EnActualizadaBBDD enActualizadaBBDD) {
		this.vista = vista;
		this.enPeticionBBDD = enPeticionBBDD;
		this.enActualizadaBBDD = enActualizadaBBDD;
		this.vista.getBtnGuardar().addActionListener(e -> crearClient());
	}

	public void crearClient() {

		try {
			c = new Cliente(
					new IdentidadPersonaJuridica(new Cif(this.vista.getGuardarPersonaJuridica().getTxtNif().getText()),
							this.vista.getGuardarPersonaJuridica().getTxtRazon().getText(),
							this.vista.getGuardarPersonaJuridica().getTxtActividad().getText()));
			try {
				enPeticionBBDD.guardaCliente(c);
				enActualizadaBBDD.actualizarCliente(c, true);
				JOptionPane.showMessageDialog(vista, Messages.getString("GuardarClientDialogoControl.0"), Messages.getString("GuardarClientDialogoControl.1"), 1, null); //$NON-NLS-1$ //$NON-NLS-2$
				this.vista.dispose();
			} catch (SQLException | EntidadYaExisteException | DatoNoValidoException e) {
				new DialogoError(e).showErrorMessage();
			}

		} catch (NIFNoValidoException | DatoNoValidoException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	public GuardarClientDialogo getVista() {
		return vista;
	}

}
