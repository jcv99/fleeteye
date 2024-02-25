package panel.control.mostrar;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import constante.Messages;

public class BotonDetallesEditor extends DefaultCellEditor {
	JButton botonDetalles;

	public BotonDetallesEditor(JCheckBox checkbox, JButton botonDetalles) {
		super(checkbox);
		this.botonDetalles = botonDetalles;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		if (isSelected) {
			botonDetalles.setForeground(table.getSelectionForeground());
			botonDetalles.setBackground(table.getSelectionBackground());
		} else {
			botonDetalles.setForeground(table.getForeground());
			botonDetalles.setBackground(table.getBackground());
		}
		botonDetalles.setText(Messages.getString("MostrarEncargoPanelControl.0")); //$NON-NLS-1$
		return botonDetalles;
	}
}
