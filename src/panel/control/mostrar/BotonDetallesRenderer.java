package panel.control.mostrar;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import constante.Messages;

public class BotonDetallesRenderer extends JButton implements TableCellRenderer {

	public BotonDetallesRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor(Messages.getString("MostrarEncargoPanelControl.1"))); //$NON-NLS-1$
		}
		setText(Messages.getString("MostrarEncargoPanelControl.2")); //$NON-NLS-1$
		return this;
	}
}
