package panel.vista.mostrar;

import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import constante.Messages;
import herramienta.ConfiguracionPantalla;
public class MostrarFlotaPanel extends JPanel {

	private JButton btnFiltrarCamion;

	private JButton btnFiltrarRemolque;
	private JButton btnQuitarFiltroCamion;
	private JButton btnQuitarFiltroRemolque;
	private JCheckBox chckbxConRemolque;
	private JCheckBox chckbxConTrabajador;
	private JCheckBox chckbxDisponible;

	private JCheckBox chckbxDisponibleRemolque;
	private JCheckBox chckbxOcupado;
	private JCheckBox chckbxOcupadoRemolque;
	private JCheckBox chckbxSinremolque;
	private JCheckBox chckbxSintrabajador;
	private JComboBox<String> comboBoxMatricula;
	private JComboBox<String> comboBoxMatriculaRemolque;
	private JComboBox<String> comboBoxTipoRemolque;
	private ConfiguracionPantalla configuracionPantalla = new ConfiguracionPantalla();
	private JScrollPane scrolpaneCamion;
	private JScrollPane scrolpaneFiltroCamion;
	private JScrollPane scrolpaneFiltroRemolque;
	private JScrollPane scrolpaneRemolque;
	private JTabbedPane tabbedPane;
	private JTable tablaCamion;
	private JTable tablaRemolque;
	private JScrollPane tableScroll;

	public MostrarFlotaPanel() {

		setLayout(null);

		tabbedPane = new JTabbedPane(SwingConstants.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
		tabbedPane.setBounds(0, 0, 1900, 1000);
		add(tabbedPane);

		JPanel panelCamion = new JPanel();

		panelCamion.setLayout(null);

		JPanel panelRemolque = new JPanel();

		panelRemolque.setLayout(null);

		scrolpaneCamion = new JScrollPane();
		scrolpaneCamion.setBounds(260, 11, amplada(85), altura(85));
		panelCamion.add(scrolpaneCamion);

		scrolpaneRemolque = new JScrollPane();
		scrolpaneRemolque.setBounds(260, 11, amplada(85), altura(85));
		panelRemolque.add(scrolpaneRemolque);

		tablaRemolque = new JTable();
		tablaRemolque.setDefaultEditor(Object.class, null);
		tablaRemolque.setAutoCreateRowSorter(true);

		scrolpaneRemolque.setViewportView(tablaRemolque);

		tablaCamion = new JTable();
		tablaCamion.setDefaultEditor(Object.class, null);
		tablaCamion.setAutoCreateRowSorter(true);

		scrolpaneCamion.setViewportView(tablaCamion);

		scrolpaneFiltroCamion = new JScrollPane();
		scrolpaneFiltroCamion.setBounds(10, 11, 240, altura(85));
		panelCamion.add(scrolpaneFiltroCamion);

		scrolpaneFiltroRemolque = new JScrollPane();
		scrolpaneFiltroRemolque.setBounds(10, 11, 240, altura(85));
		panelRemolque.add(scrolpaneFiltroRemolque);

		JPanel panelFiltroRemolque = new JPanel();
		panelFiltroRemolque.setBounds(0, 0, 10, 10);
		scrolpaneFiltroRemolque.setViewportView(panelFiltroRemolque);
		panelFiltroRemolque.setLayout(null);

		btnFiltrarRemolque = new JButton(Messages.getString("MostrarFlotaPanel.0")); //$NON-NLS-1$
		btnFiltrarRemolque.setBounds(60, 695, 107, 23);
		panelFiltroRemolque.add(btnFiltrarRemolque);

		btnQuitarFiltroRemolque = new JButton(Messages.getString("MostrarFlotaPanel.1")); //$NON-NLS-1$
		btnQuitarFiltroRemolque.setBounds(60, 748, 107, 25);
		panelFiltroRemolque.add(btnQuitarFiltroRemolque);

		JLabel lblEstado_1 = new JLabel(Messages.getString("MostrarFlotaPanel.2")); //$NON-NLS-1$
		lblEstado_1.setFont(new Font(Messages.getString("MostrarFlotaPanel.3"), Font.PLAIN, 19)); //$NON-NLS-1$
		lblEstado_1.setBounds(95, 12, 72, 23);
		panelFiltroRemolque.add(lblEstado_1);

		chckbxDisponibleRemolque = new JCheckBox(Messages.getString("MostrarFlotaPanel.4")); //$NON-NLS-1$
		chckbxDisponibleRemolque.setBounds(32, 66, 97, 23);
		panelFiltroRemolque.add(chckbxDisponibleRemolque);

		chckbxOcupadoRemolque = new JCheckBox(Messages.getString("MostrarFlotaPanel.5")); //$NON-NLS-1$
		chckbxOcupadoRemolque.setBounds(32, 93, 97, 23);
		panelFiltroRemolque.add(chckbxOcupadoRemolque);

		JLabel lblRemolque_1 = new JLabel(Messages.getString("MostrarFlotaPanel.6")); //$NON-NLS-1$
		lblRemolque_1.setFont(new Font(Messages.getString("MostrarFlotaPanel.7"), Font.PLAIN, 19)); //$NON-NLS-1$
		lblRemolque_1.setBounds(78, 162, 97, 23);
		panelFiltroRemolque.add(lblRemolque_1);

		JLabel lblMatricula_1 = new JLabel(Messages.getString("MostrarFlotaPanel.8")); //$NON-NLS-1$
		lblMatricula_1.setFont(new Font(Messages.getString("MostrarFlotaPanel.9"), Font.PLAIN, 19)); //$NON-NLS-1$
		lblMatricula_1.setBounds(70, 270, 97, 23);
		panelFiltroRemolque.add(lblMatricula_1);

		comboBoxMatriculaRemolque = new JComboBox<String>();
		comboBoxMatriculaRemolque.setBounds(12, 316, 213, 23);
		panelFiltroRemolque.add(comboBoxMatriculaRemolque);

		comboBoxTipoRemolque = new JComboBox<String>();
		comboBoxTipoRemolque.setBounds(12, 207, 213, 23);
		panelFiltroRemolque.add(comboBoxTipoRemolque);

		tabbedPane.addTab(Messages.getString("MostrarFlotaPanel.10"), panelCamion); //$NON-NLS-1$

		JPanel panelFiltros = new JPanel();
		panelFiltros.setBounds(0, 0, 10, 10);
		scrolpaneFiltroCamion.setViewportView(panelFiltros);
		panelFiltros.setLayout(null);

		btnQuitarFiltroCamion = new JButton(Messages.getString("MostrarFlotaPanel.11")); //$NON-NLS-1$
		btnQuitarFiltroCamion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuitarFiltroCamion.setBounds(54, 749, 107, 23);
		panelFiltros.add(btnQuitarFiltroCamion);

		btnFiltrarCamion = new JButton(Messages.getString("MostrarFlotaPanel.12")); //$NON-NLS-1$
		btnFiltrarCamion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFiltrarCamion.setBounds(54, 695, 107, 23);
		panelFiltros.add(btnFiltrarCamion);

		JLabel lblEstado = new JLabel(Messages.getString("MostrarFlotaPanel.13")); //$NON-NLS-1$
		lblEstado.setFont(new Font(Messages.getString("MostrarFlotaPanel.14"), Font.PLAIN, 19)); //$NON-NLS-1$
		lblEstado.setBounds(81, 24, 72, 23);
		panelFiltros.add(lblEstado);

		chckbxDisponible = new JCheckBox(Messages.getString("MostrarFlotaPanel.15")); //$NON-NLS-1$
		chckbxDisponible.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxDisponible.setBounds(18, 62, 97, 23);
		panelFiltros.add(chckbxDisponible);

		chckbxOcupado = new JCheckBox(Messages.getString("MostrarFlotaPanel.16")); //$NON-NLS-1$
		chckbxOcupado.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxOcupado.setBounds(18, 88, 97, 23);
		panelFiltros.add(chckbxOcupado);

		JLabel lblTrabajador = new JLabel(Messages.getString("MostrarFlotaPanel.17")); //$NON-NLS-1$
		lblTrabajador.setFont(new Font(Messages.getString("MostrarFlotaPanel.18"), Font.PLAIN, 19)); //$NON-NLS-1$
		lblTrabajador.setBounds(64, 128, 97, 23);
		panelFiltros.add(lblTrabajador);

		chckbxConTrabajador = new JCheckBox(Messages.getString("MostrarFlotaPanel.19")); //$NON-NLS-1$
		chckbxConTrabajador.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxConTrabajador.setBounds(18, 164, 107, 23);
		panelFiltros.add(chckbxConTrabajador);

		chckbxSintrabajador = new JCheckBox(Messages.getString("MostrarFlotaPanel.20")); //$NON-NLS-1$
		chckbxSintrabajador.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxSintrabajador.setBounds(18, 190, 120, 23);
		panelFiltros.add(chckbxSintrabajador);

		JLabel lblRemolque = new JLabel(Messages.getString("MostrarFlotaPanel.21")); //$NON-NLS-1$
		lblRemolque.setFont(new Font(Messages.getString("MostrarFlotaPanel.22"), Font.PLAIN, 19)); //$NON-NLS-1$
		lblRemolque.setBounds(64, 232, 97, 23);
		panelFiltros.add(lblRemolque);

		chckbxConRemolque = new JCheckBox(Messages.getString("MostrarFlotaPanel.23")); //$NON-NLS-1$
		chckbxConRemolque.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxConRemolque.setBounds(18, 268, 107, 23);
		panelFiltros.add(chckbxConRemolque);

		chckbxSinremolque = new JCheckBox(Messages.getString("MostrarFlotaPanel.24")); //$NON-NLS-1$
		chckbxSinremolque.setFont(new Font("Dialog", Font.PLAIN, 12));
		chckbxSinremolque.setBounds(18, 294, 97, 23);
		panelFiltros.add(chckbxSinremolque);

		JLabel lblMatricula = new JLabel(Messages.getString("MostrarFlotaPanel.25")); //$NON-NLS-1$
		lblMatricula.setFont(new Font(Messages.getString("MostrarFlotaPanel.26"), Font.PLAIN, 19)); //$NON-NLS-1$
		lblMatricula.setBounds(64, 335, 97, 23);
		panelFiltros.add(lblMatricula);

		comboBoxMatricula = new JComboBox<String>();
		comboBoxMatricula.setBounds(18, 377, 193, 23);
		panelFiltros.add(comboBoxMatricula);

		tabbedPane.addTab(Messages.getString("MostrarFlotaPanel.27"), panelRemolque); //$NON-NLS-1$

	}

	private int altura(int a) {
		return ((int) configuracionPantalla.getHeight() * a) / 100;
	}

	private int amplada(int a) {
		return ((int) configuracionPantalla.getWidth() * a) / 100;
	}

	public JButton getBtnFiltrarCamion() {
		return btnFiltrarCamion;
	}

	public JButton getBtnFiltrarRemolque() {
		return btnFiltrarRemolque;
	}

	public JButton getBtnQuitarFiltroCamion() {
		return btnQuitarFiltroCamion;
	}

	public JButton getBtnQuitarFiltroRemolque() {
		return btnQuitarFiltroRemolque;
	}

	public JCheckBox getChckbxConRemolque() {
		return chckbxConRemolque;
	}

	public JCheckBox getChckbxConTrabajador() {
		return chckbxConTrabajador;
	}

	public JCheckBox getChckbxDisponible() {
		return chckbxDisponible;
	}

	public JCheckBox getChckbxDisponibleRemolque() {
		return chckbxDisponibleRemolque;
	}

	public JCheckBox getChckbxOcupado() {
		return chckbxOcupado;
	}

	public JCheckBox getChckbxOcupadoRemolque() {
		return chckbxOcupadoRemolque;
	}

	public JCheckBox getChckbxSinremolque() {
		return chckbxSinremolque;
	}

	public JCheckBox getChckbxSintrabajador() {
		return chckbxSintrabajador;
	}

	public JComboBox<String> getComboBoxMatricula() {
		return comboBoxMatricula;
	}

	public JComboBox<String> getComboBoxMatriculaRemolque() {
		return comboBoxMatriculaRemolque;
	}

	public JComboBox<String> getComboBoxTipoRemolque() {
		return comboBoxTipoRemolque;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public JTable getTableCamion() {
		return tablaCamion;
	}

	public JTable getTableRemolque() {
		return tablaRemolque;
	}

	public JScrollPane getTableScroll() {
		return tableScroll;
	}
}