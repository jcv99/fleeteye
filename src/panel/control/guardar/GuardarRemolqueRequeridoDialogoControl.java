package panel.control.guardar;

import java.awt.Dialog;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import constante.Messages;
import exception.DatoNoValidoException;
import exception.DialogoError;
import objeto.DimensionesRemolque;
import objeto.Remolque;
import objeto.RemolqueFrigorifico;
import objeto.RemolqueLona;
import objeto.RemolquePisoMovil;
import panel.vista.guardar.GuardarRemolqueRequeridoDialogo;

public class GuardarRemolqueRequeridoDialogoControl {
	private Remolque r = null;
	private ArrayList<String> tipoRemolque = new ArrayList<String>();
	private String tipoSeleccionado;
	private GuardarRemolqueRequeridoDialogo vista;

	public GuardarRemolqueRequeridoDialogoControl(GuardarRemolqueRequeridoDialogo vista) {
		this.vista = vista;

		agregarRemolques();
		this.vista.getComboBoxTipoRemolque().setSelectedIndex(-1);
		this.vista.getComboBoxTipoRemolque().addItemListener(e -> canviarPanelRemolque());
		this.vista.getBtnGuardar().addActionListener(e -> guardarRemolque());

	}

	private void agregarRemolques() {
		Field[] field = Remolque.class.getDeclaredFields();

		for (int i = 0; i < field.length; i++) {
			if (field[i].getType().getSimpleName()
					.equals(Messages.getString("GuardarRemolqueRequeridoDialogoControl.0"))) { //$NON-NLS-1$
				if (Modifier.toString(field[i].getModifiers())
						.equals(Messages.getString("GuardarRemolqueRequeridoDialogoControl.1"))) { //$NON-NLS-1$
					if (field[i].getName().contains(Messages.getString("GuardarRemolqueRequeridoDialogoControl.2"))) { //$NON-NLS-1$
						tipoRemolque.add(field[i].getName().substring(5).substring(0, 1).toUpperCase()
								+ field[i].getName().substring(5).substring(1).toLowerCase());
						this.vista.getComboBoxTipoRemolque()
								.addItem(field[i].getName().substring(5).substring(0, 1).toUpperCase()
										+ field[i].getName().substring(5).substring(1).toLowerCase());
					}
				}
			}
		}
	}

	private void canviarPanelRemolque() {
		if (this.vista.getComboBoxTipoRemolque().getSelectedItem().toString().contentEquals(tipoRemolque.get(0))) {
			this.vista.getPanelRemolquesLayout().show(this.vista.getPanelRemolques(),
					tipoRemolque.get(0).toLowerCase());
			tipoSeleccionado = tipoRemolque.get(0).toLowerCase();
		} else if (this.vista.getComboBoxTipoRemolque().getSelectedItem().toString()
				.contentEquals(tipoRemolque.get(1))) {
			this.vista.getPanelRemolquesLayout().show(this.vista.getPanelRemolques(),
					tipoRemolque.get(1).toLowerCase());
			tipoSeleccionado = tipoRemolque.get(1).toLowerCase();
		} else if (this.vista.getComboBoxTipoRemolque().getSelectedItem().toString()
				.contentEquals(tipoRemolque.get(2))) {
			this.vista.getPanelRemolquesLayout().show(this.vista.getPanelRemolques(),
					tipoRemolque.get(2).toLowerCase());
			tipoSeleccionado = tipoRemolque.get(2).toLowerCase();
		}
	}

	public Remolque getRemolque() {
		return r;
	}

	public Dialog getVista() {
		return vista;
	}

	public void guardarRemolque() {

		try {
			DimensionesRemolque dimensiones = new DimensionesRemolque(
					Double.parseDouble(this.vista.getTextFieldAltura().getText()),
					Double.parseDouble(this.vista.getTextFieldAnchura().getText()),
					Double.parseDouble(this.vista.getTextFieldLongitud().getText()));
			// Remolque lona
			if (tipoSeleccionado.contentEquals(tipoRemolque.get(1).toLowerCase())) {
				r = RemolqueLona.remolqueRequerido(dimensiones, this.vista.getChckbxCinchas().isSelected(),
						this.vista.getChckbxAbrePorArriba().isSelected(),
						this.vista.getChckbxEngancheDeRemolque().isSelected());
				// Remolque Frigorifico
			}
			if (tipoSeleccionado.contentEquals(tipoRemolque.get(0).toLowerCase())) {
				r = RemolqueFrigorifico.remolqueRequerido(dimensiones,
						Integer.parseInt(this.vista.getTxtCapacidadpalettes().getText()));
				// Remolque Piso movil
			}
			if (tipoSeleccionado.contentEquals(tipoRemolque.get(2).toLowerCase())) {
				r = RemolquePisoMovil.remolqueRequerido(dimensiones,
						Integer.parseInt(this.vista.getTxtVolumen().getText()));
			}
			if (!this.vista.getTextFieldEjes().getText().isBlank()) {
				int ejes = Integer.parseInt(this.vista.getTextFieldEjes().getText());
				try {
					r.setEjes(ejes);
				} catch (DatoNoValidoException e) {
					new DialogoError(new Exception(Messages.getString("GuardarRemolqueRequeridoDialogoControl.3") + e)) //$NON-NLS-1$
							.showErrorMessage();
				}
			}
			if (!this.vista.getTxtTara().getText().isBlank()) {
				try {
					double tara = Double.parseDouble(this.vista.getTxtTara().getText());
					r.setTara(tara);
				} catch (DatoNoValidoException e) {
					new DialogoError(new Exception(Messages.getString("GuardarRemolqueRequeridoDialogoControl.4") + e)) //$NON-NLS-1$
							.showErrorMessage();
				}
			}
			this.vista.dispose();
		} catch (Exception e) {
			new DialogoError(new Exception(Messages.getString("GuardarRemolqueRequeridoDialogoControl.5"))) //$NON-NLS-1$
					.showErrorMessage();
		}
	}
}
