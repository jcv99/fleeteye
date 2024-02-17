package ventana.vista;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import constante.ConstantesStrings;
import constante.Messages;
import herramienta.ConfiguracionPantalla;
import panel.vista.mostrar.MostrarClientePanel;
import panel.vista.mostrar.MostrarEncargoPanel;
import panel.vista.mostrar.MostrarFlotaPanel;
import panel.vista.mostrar.MostrarPresupuestoPanel;
import panel.vista.mostrar.MostrarTrabajadorPanel;

public class MainFramePanel extends JFrame {

	private ConfiguracionPantalla configuracionPantalla = new ConfiguracionPantalla();

	private JMenu mnClientes;
	private JMenu mnDesing;
	private JMenu mnFlota_1;
	private JMenu mnNuevo;
	private JMenu mnPersonal;

	private JMenuItem mntmAbout;

	private JMenuItem mntmGuardarCamion;
	private JMenuItem mntmGuardarCliente;
	private JMenuItem mntmGuardarRemolque;
	private JMenuItem mntmGuardarTrabajador;
	private JMenuItem mntmRedo;

	private JMenuItem mntmUndo;

	private MostrarClientePanel mostrarClientePanel = new MostrarClientePanel();

	private MostrarEncargoPanel mostrarEncargoPanel = new MostrarEncargoPanel();
	private MostrarFlotaPanel mostrarFlotaPanel = new MostrarFlotaPanel();

	private MostrarPresupuestoPanel mostrarPresupuestoPanel = new MostrarPresupuestoPanel();
	private MostrarTrabajadorPanel mostrarTrabajadorPanel = new MostrarTrabajadorPanel();

	public MainFramePanel() {
		setTitle(ConstantesStrings.NOMBREPROYECTO);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1600, 900));
		setMaximumSize(new Dimension(1600, 900));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);

		setExtendedState(Frame.MAXIMIZED_BOTH);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnNuevo = new JMenu(Messages.getString("MainFrame.0")); //$NON-NLS-1$
		menuBar.add(mnNuevo);

		mnClientes = new JMenu(Messages.getString("MainFrame.1")); //$NON-NLS-1$
		mnNuevo.add(mnClientes);

		mntmGuardarCliente = new JMenuItem(Messages.getString("MainFrame.2")); //$NON-NLS-1$
		mnClientes.add(mntmGuardarCliente);

		mnPersonal = new JMenu(Messages.getString("MainFrame.3")); //$NON-NLS-1$
		mnNuevo.add(mnPersonal);

		mntmGuardarTrabajador = new JMenuItem(Messages.getString("MainFrame.4")); //$NON-NLS-1$
		mnPersonal.add(mntmGuardarTrabajador);

		mnFlota_1 = new JMenu(Messages.getString("MainFrame.5")); //$NON-NLS-1$
		mnNuevo.add(mnFlota_1);

		mntmGuardarCamion = new JMenuItem(Messages.getString("MainFrame.6")); //$NON-NLS-1$
		mnFlota_1.add(mntmGuardarCamion);

		mntmGuardarRemolque = new JMenuItem(Messages.getString("MainFrame.7")); //$NON-NLS-1$
		mnFlota_1.add(mntmGuardarRemolque);

		JMenu mnAyuda = new JMenu(Messages.getString("MainFrame.8")); //$NON-NLS-1$
		menuBar.add(mnAyuda);

		mntmAbout = new JMenuItem(Messages.getString("MainFrame.9")); //$NON-NLS-1$
		mnAyuda.add(mntmAbout);

		JTabbedPane tp = new JTabbedPane();
		tp.add(Messages.getString("MainFrame.10"), mostrarTrabajadorPanel); //$NON-NLS-1$
		tp.add(Messages.getString("MainFrame.11"), mostrarClientePanel); //$NON-NLS-1$
		tp.add(Messages.getString("MainFrame.12"), mostrarEncargoPanel); //$NON-NLS-1$
		tp.add(Messages.getString("MainFrame.13"), mostrarPresupuestoPanel); //$NON-NLS-1$
		tp.add(Messages.getString("MainFrame.14"), mostrarFlotaPanel); //$NON-NLS-1$
		getContentPane().add(tp);
		pack();
	}

	private int altura(int a) {
		return ((int) configuracionPantalla.getHeight() * a) / 100;
	}

	private int amplada(int a) {
		return ((int) configuracionPantalla.getWidth() * a) / 100;
	}

	public JMenu getMnDesing() {
		return mnDesing;
	}

	public JMenuItem getMntmAbout() {
		return mntmAbout;
	}

	public JMenuItem getMntmGuardarCamion() {
		return mntmGuardarCamion;
	}

	public JMenuItem getMntmGuardarCliente() {
		return mntmGuardarCliente;
	}

	public JMenuItem getMntmGuardarRemolque() {
		return mntmGuardarRemolque;
	}

	public JMenuItem getMntmGuardarTrabajador() {
		return mntmGuardarTrabajador;
	}

	public JMenuItem getMntmRedo() {
		return mntmRedo;
	}

	public JMenuItem getMntmUndo() {
		return mntmUndo;
	}

	public MostrarClientePanel getMostrarClientePanel() {
		return mostrarClientePanel;
	}

	public MostrarEncargoPanel getMostrarEncargoPanel() {
		return mostrarEncargoPanel;
	}

	public MostrarFlotaPanel getMostrarFlotaPanel() {
		return mostrarFlotaPanel;
	}

	public MostrarPresupuestoPanel getMostrarPresupuestoPanel() {
		return mostrarPresupuestoPanel;
	}

	public MostrarTrabajadorPanel getMostrarTrabajadorPanel() {
		return mostrarTrabajadorPanel;
	}

	public void setMostrarClientePanel(MostrarClientePanel mostrarClientePanel) {
		this.mostrarClientePanel = mostrarClientePanel;
	}

	public void setMostrarEncargoPanel(MostrarEncargoPanel mostrarEncargoPanel) {
		this.mostrarEncargoPanel = mostrarEncargoPanel;
	}

	public void setMostrarPresupuestoPanel(MostrarPresupuestoPanel mostrarPresupuestoPanel) {
		this.mostrarPresupuestoPanel = mostrarPresupuestoPanel;
	}

	public void setMostrarTrabajadorPanel(MostrarTrabajadorPanel mostrarTrabajadorPanel) {
		this.mostrarTrabajadorPanel = mostrarTrabajadorPanel;
	}

}
