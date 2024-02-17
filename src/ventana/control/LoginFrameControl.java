package ventana.control;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bbdd.DatabaseControl;
import constante.ConstantesStrings;
import constante.Messages;
import exception.CamionOcupadoException;
import exception.DatoNoValidoException;
import exception.DialogoError;
import exception.MatriculaNoValidaException;
import exception.NIFNoValidoException;
import exception.RemolqueNoCompatibleException;
import exception.RemolqueYaAsignadoException;
import exception.TrabajadorNoAsignadoException;
import exception.TrabajadorOcupadoException;
import exception.VehiculoOcupadoExcepcion;
import herramienta.ConfiguracionPantalla;
import objeto.Encargo;
import objeto.Trabajador;
import panel.vista.DialogCargando;
import ventana.vista.LoginFrame;
import ventana.vista.MainFramePanel;

public class LoginFrameControl implements KeyListener {

	public class cargarDatos extends Thread {
		private ConfiguracionPantalla configuracionPantalla = new ConfiguracionPantalla();
		private DialogCargando frame;
		private JLabel gifLabel;
		private JLabel cargando;
		private MainFrameControl vista;

		public cargarDatos(MainFrameControl vista) {
			this.vista = vista;
			frameCargar();
		}

		public void frameCargar() {
			frame = new DialogCargando();
			frame.setLayout(null);
			frame.setTitle(Messages.getString("LoginFrameControl.4"));
			frame.setResizable(false);
			frame.setUndecorated(true);
			frame.setAlwaysOnTop(true);
			ImageIcon gif = new ImageIcon();
			cargando = new JLabel();
			gifLabel = new JLabel(gif);
			gifLabel.setBounds(0, 0, gif.getIconWidth(), gif.getIconHeight());
			frame.getContentPane().add(gifLabel);
			frame.getContentPane().add(cargando);
			frame.setBounds((int) (ConfiguracionPantalla.getWidthS() / 2) - 200, (int) ConfiguracionPantalla.alturaS(5),
					gif.getIconWidth(), gif.getIconHeight());
			frame.setVisible(true);
		}

		public JDialog getFrame() {
			return frame;
		}

		public JLabel getLabel() {
			return gifLabel;
		}
		
		public JLabel getCargando() {
			return cargando;
		}

		@Override
		public void run() {
			super.run();
			try {
				this.vista.buscarEncargos();
				this.vista.buscarEncargosPorEstado(Encargo.ENCURSO);
				this.vista.buscarPresupuestos();
				this.vista.buscarTrabajadoresPorEstado(Trabajador.DISPONIBLE);
				this.vista.buscarClientes();
				this.vista.buscarUbicaciones();
				this.vista.buscarCamiones();
				this.vista.buscarRemolques();
				this.vista.buscarTrabajadores();
				this.vista.buscarTiposRemolque();
				frame.setVisible(false);
			} catch (SQLException | TrabajadorOcupadoException | NIFNoValidoException | DatoNoValidoException
					| VehiculoOcupadoExcepcion | RemolqueNoCompatibleException | RemolqueYaAsignadoException
					| CamionOcupadoException | TrabajadorNoAsignadoException | MatriculaNoValidaException e) {
				frame.setVisible(false);
				frame.dispose();
				new DialogoError(e).showErrorMessage();

			}
			frame.dispose();
		}

	}

	private JPasswordField campoContrasena;
	private JTextField campoUsuario;
	private MainFrameControl mainControl;
	private cargarDatos t1;
	private LoginFrame vista;

	private MainFramePanel vistaMain;

	public LoginFrameControl(LoginFrame vista) throws InterruptedException {
		this.vista = vista;
		this.vistaMain = new MainFramePanel();
		mainControl = new MainFrameControl(this.vistaMain);
		t1 = new cargarDatos(mainControl);
		t1.start();
		this.vista.getBtnLogin().addActionListener(e -> compruebaCampos());
		this.vista.getBtnExit().addActionListener(e -> tancarFrame());

		campoUsuario = this.vista.getTxtUser();
		campoUsuario.addKeyListener(this);
		campoContrasena = this.vista.getTxtPassword();
		campoContrasena.addKeyListener(this);

	}

	private void compruebaCampos() {
		boolean usuarioVacio = campoUsuario.getText().isBlank();
		boolean contrasenaVacia = String.valueOf(campoContrasena.getPassword()).isBlank();

//		if (usuarioVacio || contrasenaVacia)
//			new DialogoError(new Exception(ConstantesStrings.CAMPOSVACIOS)).showErrorMessage();
//		else
			login();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int codigo = e.getKeyCode();

		if (codigo == KeyEvent.VK_ENTER) {
			compruebaCampos();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void login() {
		if (DatabaseControl.login(this.vista.getTxtUser().getText(),
				new String(this.vista.getTxtPassword().getPassword()))) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
//						t1.getFrame().setBounds(0, 0, 500,500);
						mainControl.setVisible(true);
//						t1.getFrame().setLocationRelativeTo(mainControl.getVista());
						tancarFrame();
					} catch (Exception e) {
						new DialogoError(e).showErrorMessage();
					}
				}
			});
		} else {
			new DialogoError(new Exception(Messages.getString("LoginFrameControl.2"))).showErrorMessage(); //$NON-NLS-1$
			campoContrasena.setText(Messages.getString("VACIO")); //$NON-NLS-1$
		}

	}

	public void setVisible(boolean estado) {
		this.vista.setVisible(estado);
	}

	public void tancarFrame() {
		this.vista.dispose();
	}
}
