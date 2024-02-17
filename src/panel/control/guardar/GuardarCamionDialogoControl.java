package panel.control.guardar;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import constante.Messages;
import exception.DatoNoValidoException;
import exception.DialogoError;
import exception.EntidadYaExisteException;
import exception.MatriculaNoValidaException;
import interfaz.EnActualizadaBBDD;
import interfaz.EnPeticionBBDD;
import objeto.Camion;
import objeto.Camion.Builder;
import panel.vista.guardar.GuardarCamionDialogo;
public class GuardarCamionDialogoControl {

	private Camion camion = null;
	private EnActualizadaBBDD enActualizadaBBDD;
	private EnPeticionBBDD enPeticionBBDD;

	private GuardarCamionDialogo vista;

	public GuardarCamionDialogoControl(EnPeticionBBDD enPeticionBBDD, EnActualizadaBBDD enActualizadaBBDD) {
		this.vista = new GuardarCamionDialogo();
		this.enPeticionBBDD = enPeticionBBDD;
		this.enActualizadaBBDD = enActualizadaBBDD;

		this.vista.getBtnInsertar().addActionListener(e -> guardarCamion());
	}

	public GuardarCamionDialogo getGuardarCamionPanel() {
		return this.vista;
	}

	private void guardarCamion() {
		if (this.vista.camposObligatorios()) {
			try {
				Builder camionBuilder;
				camionBuilder = new Camion.Builder(
						this.vista.getTextFieldMatricula1().getText() + this.vista.getTextFieldMatricula2().getText(),
						Double.parseDouble(this.vista.getTxtTara().getText()), this.vista.getTxtMarca().getText(),
						this.vista.getTxtModelo().getText());

				if (this.vista.getComboBoxCombustible().getSelectedItem().toString() != Messages
						.getString("GuardarCamionDialogoControl.0")) //$NON-NLS-1$
					camionBuilder.combustible(this.vista.getComboBoxCombustible().getSelectedItem().toString());

				if (!this.vista.getTxtPotencia().getText().isBlank())
					camionBuilder.potencia(Integer.parseInt(this.vista.getTxtPotencia().getText()));

				if (this.vista.getComboBoxNormativa().getSelectedItem().toString() != Messages
						.getString("GuardarCamionDialogoControl.0")) //$NON-NLS-1$
					camionBuilder.normativa(this.vista.getComboBoxNormativa().getSelectedItem().toString());

				if (!this.vista.getTxtKilometraje().getText().isBlank())
					camionBuilder.kilometraje(Integer.parseInt(this.vista.getTxtKilometraje().getText()));

				if (!this.vista.getTxtEje().getText().isBlank() && !this.vista.getTextFieldEje2().getText().isBlank())
					camionBuilder.configuracionEje(
							this.vista.getTxtEje().getText() + Messages.getString("GuardarCamionDialogoControl.2") //$NON-NLS-1$
									+ this.vista.getTextFieldEje2().getText());

				if (this.vista.getDatePickerMatriculacion().getModel().getValue() != null) {
					System.out.println(this.vista.getDatePickerMatriculacion().getModel());
					if (!this.vista.getDatePickerMatriculacion().getModel().getValue().toString().isBlank())
						camionBuilder.fechaMatriculacion(new java.sql.Date(
								((java.util.Date) this.vista.getDatePickerMatriculacion().getModel().getValue())
										.getTime()));
				}

				this.camion = camionBuilder.build();

				this.enPeticionBBDD.guardaCamion(this.camion);
				this.enActualizadaBBDD.actualizarCamion(this.camion, true);
				JOptionPane.showMessageDialog(vista, Messages.getString("GuardarCamionDialogoControl.3"), //$NON-NLS-1$
						Messages.getString("GuardarCamionDialogoControl.4"), 1, null); //$NON-NLS-1$
				this.vista.dispose();

			} catch (NumberFormatException | DatoNoValidoException | SQLException | EntidadYaExisteException
					| MatriculaNoValidaException e) {
				new DialogoError(e).showErrorMessage();
			}
		}
	}
}