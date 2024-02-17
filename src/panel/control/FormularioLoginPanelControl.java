package panel.control;

import javax.swing.JButton;

import panel.vista.FormularioLoginPanel;

public class FormularioLoginPanelControl {

	private FormularioLoginPanel vista;

	public FormularioLoginPanelControl(FormularioLoginPanel vista) {
		this.vista = vista;
	}

	public JButton getBtnLogin() {
		return vista.getBtnLogin();
	}

}
