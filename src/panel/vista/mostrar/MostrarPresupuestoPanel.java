package panel.vista.mostrar;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import constante.Messages;
import herramienta.ConfiguracionPantalla;
public class MostrarPresupuestoPanel extends JPanel {
	private JButton btnFiltrar;
	private JButton btnQuitarFiltros;

	private JComboBox<String> comboBoxCliente;
	private JComboBox<String> comboBoxPaisDestino;
	private JComboBox<String> comboBoxPaisOrigen;
	private JComboBox<String> comboBoxProvinciaDestino;
	private JComboBox<String> comboBoxProvinciaOrigen;
	private ConfiguracionPantalla configuracionPantalla = new ConfiguracionPantalla();
	private JLabel lblCliente;
	private JLabel lblPaisDestino;
	private JLabel lblPaisOrigen;
	private JLabel lblProvinciaDestino;
	private JLabel lblProvinciaOrigen;
	private JTable table;
	private JScrollPane tableScroll;

	public MostrarPresupuestoPanel() {
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

		btnFiltrar = new JButton(Messages.getString("MostrarPresupuestoPanel.0")); //$NON-NLS-1$
		btnFiltrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFiltrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFiltrar.setBounds(59, 751, 117, 25);
		panelFiltros.add(btnFiltrar);

		btnQuitarFiltros = new JButton(Messages.getString("MostrarPresupuestoPanel.1")); //$NON-NLS-1$
		btnQuitarFiltros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuitarFiltros.setBounds(59, 799, 117, 25);
		panelFiltros.add(btnQuitarFiltros);
		
		lblProvinciaOrigen = new JLabel(Messages.getString("MostrarPresupuestoPanel.2")); //$NON-NLS-1$
		lblProvinciaOrigen.setFont(new Font("Dialog", Font.PLAIN, 19)); //$NON-NLS-1$
		lblProvinciaOrigen.setBounds(48, 39, 159, 30);
		panelFiltros.add(lblProvinciaOrigen);
		
		comboBoxProvinciaOrigen = new JComboBox<String>();
		comboBoxProvinciaOrigen.setBounds(12, 81, 213, 25);
		panelFiltros.add(comboBoxProvinciaOrigen);
		
		lblPaisOrigen = new JLabel(Messages.getString("MostrarPresupuestoPanel.4")); //$NON-NLS-1$
		lblPaisOrigen.setFont(new Font("Dialog", Font.PLAIN, 19)); //$NON-NLS-1$
		lblPaisOrigen.setBounds(66, 124, 110, 30);
		panelFiltros.add(lblPaisOrigen);
		
		comboBoxPaisOrigen = new JComboBox<String>();
		comboBoxPaisOrigen.setBounds(12, 166, 213, 25);
		panelFiltros.add(comboBoxPaisOrigen);
		
		lblProvinciaDestino = new JLabel(Messages.getString("MostrarPresupuestoPanel.6")); //$NON-NLS-1$
		lblProvinciaDestino.setFont(new Font("Dialog", Font.PLAIN, 19)); //$NON-NLS-1$
		lblProvinciaDestino.setBounds(48, 213, 159, 30);
		panelFiltros.add(lblProvinciaDestino);
		
		comboBoxProvinciaDestino = new JComboBox<String>();
		comboBoxProvinciaDestino.setBounds(12, 256, 213, 25);
		panelFiltros.add(comboBoxProvinciaDestino);
		
		lblPaisDestino = new JLabel(Messages.getString("MostrarPresupuestoPanel.8")); //$NON-NLS-1$
		lblPaisDestino.setFont(new Font("Dialog", Font.PLAIN, 19)); //$NON-NLS-1$
		lblPaisDestino.setBounds(69, 307, 117, 30);
		panelFiltros.add(lblPaisDestino);
		
		comboBoxPaisDestino = new JComboBox<String>();
		comboBoxPaisDestino.setBounds(12, 350, 213, 25);
		panelFiltros.add(comboBoxPaisDestino);
		
		lblCliente = new JLabel(Messages.getString("MostrarPresupuestoPanel.10")); //$NON-NLS-1$
		lblCliente.setFont(new Font("Dialog", Font.PLAIN, 19)); //$NON-NLS-1$
		lblCliente.setBounds(84, 402, 67, 30);
		panelFiltros.add(lblCliente);
		
		comboBoxCliente = new JComboBox<String>();
		comboBoxCliente.setBounds(12, 444, 213, 25);
		panelFiltros.add(comboBoxCliente);

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

	public JComboBox<String> getComboBoxCliente() {
		return comboBoxCliente;
	}

	public JComboBox<String> getComboBoxPaisDestino() {
		return comboBoxPaisDestino;
	}

	public JComboBox<String> getComboBoxPaisOrigen() {
		return comboBoxPaisOrigen;
	}

	public JComboBox<String> getComboBoxProvinciaDestino() {
		return comboBoxProvinciaDestino;
	}
	public JComboBox<String> getComboBoxProvinciaOrigen() {
		return comboBoxProvinciaOrigen;
	}
	public JTable getTable() {
		return table;
	}
	public JScrollPane getTableScroll() {
		return tableScroll;
	}
}
