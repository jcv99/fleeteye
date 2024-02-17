package panel.control;

import java.io.File;
import java.io.IOException;

import com.jcraft.jsch.JSchException;

import exception.DialogoError;
import exception.ErrorConexionServidorException;
import herramienta.GestorSCP;
import objeto.Remolque;
import objeto.RemolqueFrigorifico;
import objeto.RemolqueLona;
import objeto.RemolquePisoMovil;
import panel.vista.DetallesRemolquePanel;
import panel.vista.VisorPDF;

public class DetallesRemolquePanelControl {

	private File contrato;
	private Remolque remolque;
	private DetallesRemolquePanel vista;

	public DetallesRemolquePanelControl(Remolque remolque) {
		this.vista = new DetallesRemolquePanel();
		this.remolque = remolque;
		if (this.remolque.getPathRemoto() == null) {
			this.vista.getBtnConsultarDatos().setEnabled(false);
		}
		cargarDatosRemolque();

		this.vista.getBtnConsultarDatos().addActionListener(e -> abrirContrato());
	}

	private void abrirContrato() {

		if (this.contrato == null) {
			try (GestorSCP scp = new GestorSCP()) {
				this.contrato = scp.bajarFichero(this.remolque.getPathRemoto());
			} catch (IOException | JSchException | ErrorConexionServidorException e) {
				new DialogoError(e).showErrorMessage();
			} catch (Exception e) {
				new DialogoError(e).showErrorMessage();
			}
		}
		try {
			VisorPDF vistaPdf = new VisorPDF();
			VisorPDFControl controlPdf = new VisorPDFControl(vistaPdf, this.contrato);
			vistaPdf.setLocationRelativeTo(this.vista);
			vistaPdf.setVisible(true);
		} catch (IOException e) {
			new DialogoError(e).showErrorMessage();
		}
	}

	private void cargarDatosRemolque() {
		this.vista.getTextFieldMatricula().setText(this.remolque.getMatricula());
		this.vista.getTextFieldCompra().setText(this.remolque.getFechaCompra().toString());
		this.vista.getTextFieldEjes().setText(this.remolque.getEjes() + "");
		this.vista.getTextFieldAltura().setText(this.remolque.getDimensionesRemolque().getAltura() + "");
		this.vista.getTextFieldAnchura().setText(this.remolque.getDimensionesRemolque().getAnchura() + "");
		this.vista.getTextFieldLongitud().setText(this.remolque.getDimensionesRemolque().getLongitud() + "");
		this.vista.getTxtTara().setText(this.remolque.getTara() + "");

		if (this.remolque instanceof RemolqueLona) {
			this.vista.getPanelRemolquesLayout().show(this.vista.getPanelRemolques(), "lona");
			this.vista.getTextFieldTipoRemolque().setText("Lona");
			if (((RemolqueLona) this.remolque).tieneCinchas())
				this.vista.getChckbxCinchas().setSelected(true);
			if (((RemolqueLona) this.remolque).abreArriba())
				this.vista.getChckbxAbrePorArriba().setSelected(true);
			if (((RemolqueLona) this.remolque).tieneEngancheRemolque())
				this.vista.getChckbxEngancheDeRemolque().setSelected(true);
		} else if (this.remolque instanceof RemolqueFrigorifico) {
			this.vista.getPanelRemolquesLayout().show(this.vista.getPanelRemolques(), "frigorifico");
			this.vista.getTextFieldTipoRemolque().setText("Frigorifico");
			this.vista.getTxtCapacidadpalettes()
					.setText(((RemolqueFrigorifico) this.remolque).getCapacidadPalettes() + "");
		} else if (this.remolque instanceof RemolquePisoMovil) {
			this.vista.getPanelRemolquesLayout().show(this.vista.getPanelRemolques(), "pisomobil");
			this.vista.getTextFieldTipoRemolque().setText("Piso mobil");
			this.vista.getTxtVolumen().setText(((RemolquePisoMovil) this.remolque).getVolumen() + "");
		}
	}

	public DetallesRemolquePanel getDetallesRemolquePanel() {
		return this.vista;
	}
}