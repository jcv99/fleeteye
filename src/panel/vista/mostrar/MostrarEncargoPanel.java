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

public class MostrarEncargoPanel extends JPanel {
	private JButton btnFiltrar;
	private JButton btnQuitaFiltros;

	private JCheckBox chckbxCompletado;
	private JCheckBox chckbxEnCurso;
	private JCheckBox chckbxPorHacer;
	private JComboBox<String> combCamion;
	private JComboBox<String> combCliente;
	private ConfiguracionPantalla configuracionPantalla = new ConfiguracionPantalla();
	private JLabel lblCamion;
	private JLabel lblCliente;
	private JTable table;
	private JScrollPane tableScroll;

	public MostrarEncargoPanel() {
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

		JLabel lblEstados = new JLabel(Messages.getString("MostrarEncargoPanel.16")); //$NON-NLS-1$
		lblEstados.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstados.setFont(new Font("Dialog", Font.PLAIN, 19)); //$NON-NLS-1$
		lblEstados.setBounds(74, 12, 87, 32);
		panelFiltros.add(lblEstados);

		chckbxCompletado = new JCheckBox(Messages.getString("MostrarEncargoPanel.18")); //$NON-NLS-1$
		chckbxCompletado.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxCompletado.setBounds(22, 59, 112, 23);
		panelFiltros.add(chckbxCompletado);

		chckbxEnCurso = new JCheckBox(Messages.getString("MostrarEncargoPanel.19")); //$NON-NLS-1$
		chckbxEnCurso.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxEnCurso.setBounds(22, 86, 112, 23);
		panelFiltros.add(chckbxEnCurso);

		lblCliente = new JLabel(Messages.getString("MostrarEncargoPanel.20")); //$NON-NLS-1$
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente.setFont(new Font("Dialog", Font.PLAIN, 19)); //$NON-NLS-1$
		lblCliente.setBounds(77, 160, 87, 32);
		panelFiltros.add(lblCliente);

		lblCamion = new JLabel(Messages.getString("MostrarEncargoPanel.22")); //$NON-NLS-1$
		lblCamion.setHorizontalAlignment(SwingConstants.CENTER);
		lblCamion.setFont(new Font("Dialog", Font.PLAIN, 19)); //$NON-NLS-1$
		lblCamion.setBounds(72, 247, 112, 32);
		panelFiltros.add(lblCamion);

		combCliente = new JComboBox<String>();
		combCliente.setBounds(12, 204, 210, 24);
		panelFiltros.add(combCliente);

		combCamion = new JComboBox<String>();
		combCamion.setBounds(12, 290, 210, 24);
		panelFiltros.add(combCamion);
		table.setAutoCreateRowSorter(true);

		btnFiltrar = new JButton(Messages.getString("MostrarEncargoPanel.27")); //$NON-NLS-1$
		btnFiltrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFiltrar.setBounds(69, 753, 103, 25);
		panelFiltros.add(btnFiltrar);

		btnQuitaFiltros = new JButton(Messages.getString("MostrarEncargoPanel.28")); //$NON-NLS-1$
		btnQuitaFiltros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuitaFiltros.setBounds(60, 812, 119, 25);
		panelFiltros.add(btnQuitaFiltros);

		chckbxPorHacer = new JCheckBox(Messages.getString("MostrarEncargoPanel.29")); //$NON-NLS-1$
		chckbxPorHacer.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxPorHacer.setBounds(22, 113, 112, 23);
		panelFiltros.add(chckbxPorHacer);

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

	public JButton getBtnQuitaFiltros() {
		return btnQuitaFiltros;
	}

	public JCheckBox getChckbxCompletado() {
		return chckbxCompletado;
	}

	public JCheckBox getChckbxEnCurso() {
		return chckbxEnCurso;
	}

	public JCheckBox getChckbxPorHacer() {
		return chckbxPorHacer;
	}

	public JComboBox<String> getCombCamion() {
		return combCamion;
	}

	public JComboBox<String> getCombCliente() {
		return combCliente;
	}

	public JTable getTable() {
		return table;
	}

	public JScrollPane getTableScroll() {
		return tableScroll;
	}
}