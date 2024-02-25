package herramienta;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Utils {

	public static void setHeader(String[] s, JTable table) {
		table.setModel(new DefaultTableModel(s, 0));
	}

}
