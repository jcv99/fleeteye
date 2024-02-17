package panel.vista.mostrar;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import constante.Messages;
import herramienta.ConfiguracionPantalla;
public class MostrarClientePanel extends JPanel {
	private JButton btnFiltrar;
	private JButton btnQuitarFiltros;

	private JComboBox<String> comboBoxCIF;
	private JComboBox<String> comboBoxRazonSocial;
	private ConfiguracionPantalla configuracionPantalla = new ConfiguracionPantalla();
	private JLabel lblCifNif;
	private JTable table;
	private JScrollPane tableScroll;

	public MostrarClientePanel() {

		setLayout(null);
		JScrollPane scrolpane = new JScrollPane();
		scrolpane.setBounds(260, 11, amplada(85), altura(85));
		add(scrolpane);

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setAutoCreateRowSorter(true);

		scrolpane.setViewportView(table);

		// BOTONES
		JScrollPane scrolpaneFiltro = new JScrollPane();
		scrolpaneFiltro.setBounds(10, 11, 240, altura(85));
		add(scrolpaneFiltro);

		JPanel panelFiltros = new JPanel();
		panelFiltros.setBorder(new LineBorder(Color.LIGHT_GRAY));
		scrolpaneFiltro.setViewportView(panelFiltros);
		panelFiltros.setLayout(null);

		btnFiltrar = new JButton(Messages.getString("MostrarClientePanel.0")); //$NON-NLS-1$
		btnFiltrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFiltrar.setBounds(51, 737, 123, 25);
		panelFiltros.add(btnFiltrar);

		btnQuitarFiltros = new JButton(Messages.getString("MostrarClientePanel.1")); //$NON-NLS-1$
		btnQuitarFiltros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuitarFiltros.setBounds(51, 793, 126, 25);
		panelFiltros.add(btnQuitarFiltros);

		lblCifNif = new JLabel(Messages.getString("MostrarClientePanel.2")); //$NON-NLS-1$
		lblCifNif.setFont(new Font("Dialog", Font.PLAIN, 19)); //$NON-NLS-1$
		lblCifNif.setBounds(74, 30, 83, 15);
		panelFiltros.add(lblCifNif);

		comboBoxCIF = new JComboBox<String>();
		comboBoxCIF.setBounds(31, 74, 169, 25);
		panelFiltros.add(comboBoxCIF);

		JLabel lblRazonSocial = new JLabel(Messages.getString("MostrarClientePanel.4")); //$NON-NLS-1$
		lblRazonSocial.setFont(new Font("Dialog", Font.PLAIN, 19)); //$NON-NLS-1$
		lblRazonSocial.setBounds(59, 141, 118, 15);
		panelFiltros.add(lblRazonSocial);

		comboBoxRazonSocial = new JComboBox<String>();
		comboBoxRazonSocial.setBounds(31, 180, 169, 25);
		panelFiltros.add(comboBoxRazonSocial);
	}

	private int altura(int a) {
		return ((int) configuracionPantalla.getHeight() * a) / 100;
	}

	private int amplada(int a) {
		return ((int) configuracionPantalla.getWidth() * a) / 100;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public JButton getBtnQuitarFiltros() {
		return btnQuitarFiltros;
	}

	public JComboBox<String> getComboBoxCIF() {
		return comboBoxCIF;
	}

	public JComboBox<String> getComboBoxRazonSocial() {
		return comboBoxRazonSocial;
	}

	public JTable getTable() {
		return table;
	}

	public JScrollPane getTableScroll() {
		return tableScroll;
	}
}
