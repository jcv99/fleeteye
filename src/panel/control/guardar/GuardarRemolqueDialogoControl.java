package panel.control.guardar;

import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import constante.Messages;
import exception.DatoNoValidoException;
import exception.DialogoError;
import exception.EntidadYaExisteException;
import interfaz.EnActualizadaBBDD;
import interfaz.EnPeticionBBDD;
import objeto.DimensionesRemolque;
import objeto.Remolque;
import objeto.RemolqueFrigorifico;
import objeto.RemolqueLona;
import objeto.RemolquePisoMovil;
import panel.vista.guardar.GuardarRemolqueDialogo;
public class GuardarRemolqueDialogoControl {

	private EnActualizadaBBDD enActualizadaBBDD;

	private EnPeticionBBDD enPeticionBBDD;
	private Remolque remolque;

	private String tipoSeleccionado = Messages.getString("GuardarRemolqueDialogoControl.0"); //$NON-NLS-1$

	private GuardarRemolqueDialogo vista;

	public GuardarRemolqueDialogoControl(EnPeticionBBDD enPeticionBBDD, EnActualizadaBBDD enActualizadaBBDD) {
		this.vista = new GuardarRemolqueDialogo();
		this.enPeticionBBDD = enPeticionBBDD;
		this.enActualizadaBBDD = enActualizadaBBDD;

		this.vista.getComboBoxRemolque().addItemListener(e -> cambiarPanelRemolque());
		this.vista.getBtnInsertar().addActionListener(e -> guardarRemolque());
	}

	private void cambiarPanelRemolque() {
		if (this.vista.getComboBoxRemolque().getSelectedItem().toString()
				.contentEquals(Messages.getString("GuardarRemolqueDialogoControl.10"))) { //$NON-NLS-1$
			this.vista.getPanelRemolquesLayout().show(this.vista.getPanelRemolques(),
					Messages.getString("GuardarRemolqueDialogoControl.0")); //$NON-NLS-1$
			tipoSeleccionado = Messages.getString("GuardarRemolqueDialogoControl.0"); //$NON-NLS-1$
		} else if (this.vista.getComboBoxRemolque().getSelectedItem().toString()
				.contentEquals(Messages.getString("GuardarRemolqueDialogoControl.13"))) { //$NON-NLS-1$
			this.vista.getPanelRemolquesLayout().show(this.vista.getPanelRemolques(),
					Messages.getString("GuardarRemolqueDialogoControl.16")); //$NON-NLS-1$
			tipoSeleccionado = Messages.getString("GuardarRemolqueDialogoControl.16"); //$NON-NLS-1$
		} else if (this.vista.getComboBoxRemolque().getSelectedItem().toString()
				.contentEquals(Messages.getString("GuardarRemolqueDialogoControl.16"))) { //$NON-NLS-1$
			this.vista.getPanelRemolquesLayout().show(this.vista.getPanelRemolques(),
					Messages.getString("GuardarRemolqueDialogoControl.7")); //$NON-NLS-1$
			tipoSeleccionado = Messages.getString("GuardarRemolqueDialogoControl.7"); //$NON-NLS-1$
		}
	}

	public GuardarRemolqueDialogo getGuardarRemolquePanel() {
		return this.vista;
	}

	private void guardarRemolque() {

		try {
			String matricula = Messages.getString("GuardarRemolqueDialogoControl.1") //$NON-NLS-1$
					+ this.vista.getTextFieldMatricula1().getText() + this.vista.getTextFieldMatricula2().getText();
			Date fechaCompra = new java.sql.Date(
					((java.util.Date) this.vista.getFechaCompra().getModel().getValue()).getTime());
			double tara = 0;
			try {
				tara = Double.parseDouble(this.vista.getTxtTara().getText());
			} catch (Exception e) {
				new DialogoError(new Exception(Messages.getString("GuardarRemolqueDialogoControl.2"))) //$NON-NLS-1$
						.showErrorMessage();
			}
			DimensionesRemolque dimensiones = null;
			try {
				dimensiones = new DimensionesRemolque(Double.parseDouble(this.vista.getTextFieldAltura().getText()),
						Double.parseDouble(this.vista.getTextFieldAnchura().getText()),
						Double.parseDouble(this.vista.getTextFieldLongitud().getText()));
			} catch (Exception e) {
				new DialogoError(new Exception(Messages.getString("GuardarRemolqueDialogoControl.3"))) //$NON-NLS-1$
						.showErrorMessage();
			}
			int ejes = 0;
			if (!this.vista.getTextFieldEjes().getText().isBlank()) {
				try {
					ejes = Integer.parseInt(this.vista.getTextFieldEjes().getText());
				} catch (Exception e) {
					new DialogoError(new Exception(Messages.getString("GuardarRemolqueDialogoControl.4"))) //$NON-NLS-1$
							.showErrorMessage();
				}
			}
			if (tipoSeleccionado.contentEquals(Messages.getString("GuardarRemolqueDialogoControl.0"))) { //$NON-NLS-1$
				RemolqueLona remolque = new RemolqueLona(matricula, fechaCompra, tara, dimensiones, ejes);
				if (this.vista.getChckbxCinchas().isSelected())
					remolque.setCinchas(true);
				if (this.vista.getChckbxAbrePorArriba().isSelected())
					remolque.setAbreArriba(true);
				if (this.vista.getChckbxEngancheDeRemolque().isSelected())
					remolque.setEngancheRemolque(true);
				this.remolque = remolque;

			} else if (tipoSeleccionado.contentEquals(Messages.getString("GuardarRemolqueDialogoControl.6"))) { //$NON-NLS-1$
				RemolqueFrigorifico remolque = new RemolqueFrigorifico(matricula, fechaCompra, tara, dimensiones, ejes);
				if (!this.vista.getTxtCapacidadpalettes().getText().isBlank())
					remolque.setCapacidadPalettes(Integer.parseInt(this.vista.getTxtCapacidadpalettes().getText()));

				this.remolque = remolque;
			} else if (tipoSeleccionado.contentEquals(Messages.getString("GuardarRemolqueDialogoControl.7"))) { //$NON-NLS-1$
				RemolquePisoMovil remolque = new RemolquePisoMovil(matricula, fechaCompra, tara, dimensiones, ejes);
				if (!this.vista.getTxtVolumen().getText().isBlank())
					remolque.setVolumen(Integer.parseInt(this.vista.getTxtVolumen().getText()));

				this.remolque = remolque;
			}
			this.enPeticionBBDD.guardaRemolque(this.remolque);
			this.enActualizadaBBDD.actualizarRemolque(this.remolque, true);
			JOptionPane.showMessageDialog(vista, Messages.getString("GuardarRemolqueDialogoControl.8"), //$NON-NLS-1$
					Messages.getString("GuardarRemolqueDialogoControl.9"), 1, null); //$NON-NLS-1$
			this.vista.dispose();

		} catch (NumberFormatException | DatoNoValidoException | SQLException | EntidadYaExisteException e) {
			new DialogoError(e).showErrorMessage();
		}
	}
}