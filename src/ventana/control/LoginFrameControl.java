package ventana.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import bbdd.DatabaseControl;
import constante.ConstantesStrings;
import constante.Messages;
import exception.DialogoError;
import exception.NIFNoValidoException;
import panel.vista.DialogCargando;
import ventana.vista.LoginFrame;
import ventana.vista.MainFramePanel;

public class LoginFrameControl {

	private JPasswordField campoContrasena;
	private JTextField campoUsuario;
	private MainFrameControl mainControl;
	private CargarDatosTask cargarDatosTask;
	private LoginFrame vista;
	private MainFramePanel vistaMain;

	public LoginFrameControl(LoginFrame vista) throws InterruptedException {
		this.vista = vista;
		this.vistaMain = new MainFramePanel();
		mainControl = new MainFrameControl(this.vistaMain);
		campoUsuario = this.vista.getTxtUser();
		campoContrasena = this.vista.getTxtPassword();
		agregarListeners();

	}

	private void iniciarCargarDatosTask() {
		cargarDatosTask = new CargarDatosTask(mainControl);
		cargarDatosTask.run();
	}

	private void agregarListeners() {
		this.vista.getBtnLogin().addActionListener(e -> compruebaCampos());
		this.vista.getBtnExit().addActionListener(e -> tancarFrame());
		campoUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					compruebaCampos();
				}
			}
		});
		campoContrasena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					compruebaCampos();
				}
			}
		});
	}

	public void tancarFrame() {
		this.vista.dispose();
	}

	private void compruebaCampos() {
		if (camposVacios()) {
			new DialogoError(new Exception(ConstantesStrings.CAMPOSVACIOS)).showErrorMessage();
		} else {
			login();
		}
	}

	private boolean camposVacios() {
		return campoUsuario.getText().isBlank() || String.valueOf(campoContrasena.getPassword()).isBlank();
	}

	private void login() {
//		if (DatabaseControl.login(campoUsuario.getText(), new String(campoContrasena.getPassword()))) {
			iniciarCargarDatosTask();
			mostrarMainFrame();
//		} else {
//			new DialogoError(new Exception(Messages.getString("LoginFrameControl.2"))).showErrorMessage(); //$NON-NLS-1$
//			campoContrasena.setText(Messages.getString("VACIO")); //$NON-NLS-1$
//		}
	}

	private void mostrarMainFrame() {
		mainControl.setVisible(true);
		tancarFrame();
	}

	public void setVisible(boolean estado) {
		this.vista.setVisible(estado);
	}

	public class CargarDatosTask extends Thread {
		private MainFrameControl mainControl;
		private DialogCargando dialogCargando;

		public CargarDatosTask(MainFrameControl mainControl) {
			this.mainControl = mainControl;
		}

		@Override
		public void run() {
			dialogCargando = new DialogCargando();
			dialogCargando.setVisible(true);
			try {
				mainControl.cargarTodosLosDatos();
			} catch (NIFNoValidoException e) {
				new DialogoError(e).showErrorMessage();
			} finally {
				dialogCargando.dispose();
			}
		}
	}
}