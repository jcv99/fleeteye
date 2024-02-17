package panel.vista.mostrar;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import constante.Messages;
import herramienta.ConfiguracionPantalla;

public class MostrarTrabajadorPanel extends JPanel {
	private JButton bttnFiltrar;
	private JButton bttnQuitarFiltros;

	private JCheckBox chckbxDisponible;
	private JCheckBox chckbxOcupado;
	private JComboBox<String> comboBoxNIF;
	private JComboBox<String> comboBoxNombreCompleto;
	private ConfiguracionPantalla configuracionPantalla = new ConfiguracionPantalla();
	private JTable table;
	private JScrollPane tableScroll;

	public MostrarTrabajadorPanel() {
		setLayout(null);
		JScrollPane scrolpane = new JScrollPane();
		scrolpane.setBounds(260, 11, amplada(85), altura(85));
		add(scrolpane);

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setAutoCreateRowSorter(true);

		scrolpane.setViewportView(table);

		JScrollPane scrolpaneFiltro = new JScrollPane();
		scrolpaneFiltro.setBounds(10, 11, 240, altura(85));
		add(scrolpaneFiltro);

		JPanel panelFiltros = new JPanel();
		panelFiltros.setBorder(new LineBorder(Color.LIGHT_GRAY));
		scrolpaneFiltro.setViewportView(panelFiltros);
		panelFiltros.setLayout(null);

		bttnFiltrar = new JButton(Messages.getString("MostrarTrabajadorPanel.0")); //$NON-NLS-1$
		bttnFiltrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bttnFiltrar.setBounds(60, 752, 109, 23);
		panelFiltros.add(bttnFiltrar);

		bttnQuitarFiltros = new JButton(Messages.getString("MostrarTrabajadorPanel.1")); //$NON-NLS-1$
		bttnQuitarFiltros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bttnQuitarFiltros.setBounds(60, 804, 112, 23);
		panelFiltros.add(bttnQuitarFiltros);

		JLabel lblEstados = new JLabel(Messages.getString("MostrarTrabajadorPanel.2")); //$NON-NLS-1$
		lblEstados.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstados.setFont(new Font("Dialog", Font.PLAIN, 19)); //$NON-NLS-1$
		lblEstados.setBounds(66, 23, 87, 32);
		panelFiltros.add(lblEstados);

		chckbxDisponible = new JCheckBox(Messages.getString("MostrarTrabajadorPanel.4")); //$NON-NLS-1$
		chckbxDisponible.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxDisponible.setBounds(32, 71, 112, 23);
		panelFiltros.add(chckbxDisponible);

		chckbxOcupado = new JCheckBox(Messages.getString("MostrarTrabajadorPanel.5")); //$NON-NLS-1$
		chckbxOcupado.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxOcupado.setBounds(32, 99, 112, 23);
		panelFiltros.add(chckbxOcupado);
		
		JLabel lblNif = new JLabel(Messages.getString("MostrarTrabajadorPanel.6")); //$NON-NLS-1$
		lblNif.setFont(new Font("Dialog", Font.PLAIN, 19)); //$NON-NLS-1$
		lblNif.setBounds(95, 166, 38, 15);
		panelFiltros.add(lblNif);
		
		comboBoxNIF = new JComboBox<String>();
		comboBoxNIF.setBounds(24, 208, 193, 23);
		panelFiltros.add(comboBoxNIF);
		
		JLabel lblNombre = new JLabel(Messages.getString("MostrarTrabajadorPanel.8")); //$NON-NLS-1$
		lblNombre.setFont(new Font("Dialog", Font.PLAIN, 19)); //$NON-NLS-1$
		lblNombre.setBounds(42, 278, 158, 15);
		panelFiltros.add(lblNombre);
		
		comboBoxNombreCompleto = new JComboBox<String>();
		comboBoxNombreCompleto.setBounds(24, 321, 193, 23);
		panelFiltros.add(comboBoxNombreCompleto);

	}

	private int altura(int a) {
		return ((int) configuracionPantalla.getHeight() * a) / 100;
	}

	private int amplada(int a) {
		return ((int) configuracionPantalla.getWidth() * a) / 100;
	}

	public JButton getBttnFiltrar() {
		return bttnFiltrar;
	}

	public JButton getBttnQuitarFiltros() {
		return bttnQuitarFiltros;
	}

	public JCheckBox getChckbxDisponible() {
		return chckbxDisponible;
	}

	public JCheckBox getChckbxOcupado() {
		return chckbxOcupado;
	}

	public JComboBox<String> getComboBoxNIF() {
		return comboBoxNIF;
	}

	public JComboBox<String> getComboBoxNombreCompleto() {
		return comboBoxNombreCompleto;
	}

	public JTable getTable() {
		return table;
	}
	public JScrollPane getTableScroll() {
		return tableScroll;
	}
}
