package ventana.control;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import bbdd.DatabaseControl;
import constante.Messages;
import exception.CamionOcupadoException;
import exception.DatoNoValidoException;
import exception.EntidadYaExisteException;
import exception.MatriculaNoValidaException;
import exception.NIFNoValidoException;
import exception.RemolqueNoCompatibleException;
import exception.RemolqueYaAsignadoException;
import exception.TrabajadorNoAsignadoException;
import exception.TrabajadorOcupadoException;
import exception.VehiculoOcupadoExcepcion;
import interfaz.EnActualizadaBBDD;
import interfaz.EnPeticionBBDD;
import objeto.Camion;
import objeto.Cliente;
import objeto.Encargo;
import objeto.Presupuesto;
import objeto.Remolque;
import objeto.Trabajador;
import objeto.Ubicacion;
import panel.control.AcercaDeDialogoControl;
import panel.control.guardar.GuardarCamionDialogoControl;
import panel.control.guardar.GuardarClienteDialogoControl;
import panel.control.guardar.GuardarEncargoDialogoControl;
import panel.control.guardar.GuardarPresupuestoDialogoControl;
import panel.control.guardar.GuardarRemolqueDialogoControl;
import panel.control.guardar.GuardarTrabajadorDialogoControl;
import panel.control.mostrar.MostrarClientePanelControl;
import panel.control.mostrar.MostrarEncargoPanelControl;
import panel.control.mostrar.MostrarFlotaPanelControl;
import panel.control.mostrar.MostrarPresupuestoPanelControl;
import panel.control.mostrar.MostrarTrabajadorPanelControl;
import panel.vista.AcercaDeDialogo;
import panel.vista.DialogoDetalles;
import panel.vista.guardar.GuardarClientDialogo;
import panel.vista.guardar.GuardarTrabajadorDialogo;
import ventana.vista.MainFramePanel;

public class MainFrameControl implements EnActualizadaBBDD, EnPeticionBBDD {

	private DatabaseControl dbm;

	private ArrayList<Encargo> encargosClientes;
//	private GuardarCamionDialogoControl guardarCamionDialogoControl;
//	private GuardarClienteDialogoControl guardarClienteDialogoControl;
//	private GuardarRemolqueDialogoControl guardarRemolqueDialogoControl;
//	private GuardarTrabajadorDialogoControl insertarTrabajadorDialogoControl;
	private MostrarClientePanelControl mostrarClientePanelControl;
	private MostrarEncargoPanelControl mostrarEncargoPanelControl;
	private MostrarFlotaPanelControl mostrarFlotaPanelControl;

	private MostrarPresupuestoPanelControl mostrarPresupuestoPanelControl;

	private MostrarTrabajadorPanelControl mostrarTrabajadorPanelControl;

//	private ArrayList<Presupuesto> presupuestoClientes;

	private ArrayList<Ubicacion> ubicaciones;

	private MainFramePanel vista;

	public MainFrameControl(MainFramePanel vista) {

		this.vista = vista;
		this.dbm = new DatabaseControl();
		initControls();
		initListeners();

	}

	private void initControls() {
		mostrarTrabajadorPanelControl = new MostrarTrabajadorPanelControl(vista.getMostrarTrabajadorPanel(), this);
		mostrarClientePanelControl = new MostrarClientePanelControl(vista.getMostrarClientePanel(), this);
		mostrarPresupuestoPanelControl = new MostrarPresupuestoPanelControl(vista.getMostrarPresupuestoPanel(), this);
		mostrarEncargoPanelControl = new MostrarEncargoPanelControl(vista.getMostrarEncargoPanel(), this);
		mostrarFlotaPanelControl = new MostrarFlotaPanelControl(vista.getMostrarFlotaPanel(), this);
	}

	private void initListeners() {
		vista.getMntmGuardarTrabajador().addActionListener(e -> crearTrabajadorDialogo());
		vista.getMntmGuardarCamion().addActionListener(e -> crearCamionDialogo());
		vista.getMntmGuardarRemolque().addActionListener(e -> crearRemolqueDialogo());
		vista.getMntmAbout().addActionListener(
				e -> new Thread(() -> new AcercaDeDialogoControl(new AcercaDeDialogo()).setVisible(true)).start());
		vista.getMntmGuardarCliente().addActionListener(e -> crearClienteDialogo());
	}

	@Override
	public void abrirGuardarEncargoDialogoControl(Presupuesto presupuesto, JPanel origen)
			throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		GuardarEncargoDialogoControl guardarEncargo = new GuardarEncargoDialogoControl(presupuesto, this, this,
				this.dbm.buscarCamionesDisponiblesConRemolqueTrabajador());
		guardarEncargo.getVista().setLocationRelativeTo(this.vista);
		guardarEncargo.getVista().setVisible(true);
	}

	@Override
	public void actualizarCamion(Camion cam) {

	}

	@Override
	public void actualizarCamion(Camion cam, boolean nuevo) {
		this.mostrarFlotaPanelControl.refrescarCamion(cam, nuevo);
		this.mostrarEncargoPanelControl.refrescarCamion(cam, nuevo);
	}

	@Override
	public void actualizarCliente(Cliente c, boolean nuevo) {
		this.mostrarClientePanelControl.refrescarCliente(c, nuevo);
		this.mostrarEncargoPanelControl.refrescarCliente(c, nuevo);
	}

	//
	@Override
	public void actualizarEncargo(Encargo e)
			throws SQLException, TrabajadorOcupadoException, NIFNoValidoException, DatoNoValidoException {
		// this.dbm.actualizarEncargo(e);
	}

	@Override
	public void actualizarEncargo(Encargo e, boolean nuevo) {
		this.mostrarEncargoPanelControl.refrescarEncargo(e, nuevo);
	}

	@Override
	public void actualizarEstadoEncargo(Encargo encargo) throws SQLException {
//		 this.dbm.actualizarEstadoEncargo(encargo);
	}

	@Override
	public void actualizarEstadoRemolque(Remolque remolque) throws SQLException {
		this.dbm.actualizarEstadoRemolque(remolque);
	}

	@Override
	public void actualizarEstadoTrabajador(Trabajador trabajador) throws SQLException {
		this.dbm.actualizarEstadoTrabajador(trabajador);
	}

	@Override
	public void actualizarPresupuesto(Presupuesto p, boolean nuevo) {
		this.mostrarPresupuestoPanelControl.refrescarPresupuesto(p, nuevo);
	}

	@Override
	public void actualizarRemolque(Remolque rem) {
	}

	@Override
	public void actualizarRemolque(Remolque rem, boolean nuevo) {
		this.mostrarFlotaPanelControl.refrescarRemolque(rem, nuevo);
	}

	@Override
	public void actualizarTrabajador(Trabajador t) throws SQLException {
		// this.dbm.actualizarTrabajador(t);
	}

	@Override
	public void actualizarTrabajador(Trabajador t, boolean nuevo) {
		this.mostrarTrabajadorPanelControl.refrescarTrabajador(t, nuevo);
	}

	@Override
	public void actualizarUbicacion(Ubicacion u, boolean nuevo) {
		this.ubicaciones.add(u);
	}

	@Override
	public void asignarCamionEncargo(Encargo encargo, JPanel vista)
			throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		GuardarEncargoDialogoControl guardarEncargo = new GuardarEncargoDialogoControl(encargo, this, this,
				this.dbm.buscarCamionesDisponiblesConRemolqueTrabajador());
		guardarEncargo.getVista().setLocationRelativeTo(this.vista);
		guardarEncargo.getVista().setVisible(true);

	}

	@Override
	public void asignarCamionParaEncargo(Encargo encargo) throws SQLException {
		this.dbm.asignarCamionEncargo(encargo);
	}

	@Override
	public void asignarRemolqueParaCamion(Camion camion) throws SQLException {
		this.dbm.asignarRemolqueCamion(camion);
	}

	@Override
	public void asignarTrabajadorParaCamion(Camion camion) throws SQLException {
		this.dbm.asignarTrabajadorCamion(camion);
	}

	@Override
	public void borrarTrabajador(Trabajador trabajador) throws SQLException {
		this.dbm.eliminarTrabajador(trabajador);
	}

	@Override
	public void buscarCamiones()
			throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		ArrayList<Camion> camiones = this.dbm.buscarCamiones();
		this.mostrarFlotaPanelControl.setCamiones(camiones);
		this.mostrarEncargoPanelControl.setCamiones(camiones);
	}

	@Override
	public void buscarCamionParaDetalles(String id, JPanel origen)
			throws NumberFormatException, SQLException, TrabajadorOcupadoException, DatoNoValidoException,
			NIFNoValidoException, RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, TrabajadorNoAsignadoException {
		DialogoDetalles detalles = new DialogoDetalles(this, this);
		detalles.setTrabajadores(dbm.buscarTrabajadoresPorEstado(Trabajador.DISPONIBLE));
		detalles.setRemolques(dbm.buscarRemolquePorEstado(Remolque.DISPONIBLE));
		detalles.abrirDetalles(dbm.buscarCamionPorId(Integer.parseInt(id)));
		detalles.setLocationRelativeTo(origen);
		detalles.setVisible(true);
	}

	@Override
	public void buscarClienteParaDetalles(String id, JPanel origen)
			throws NumberFormatException, SQLException, DatoNoValidoException, NIFNoValidoException,
			TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException, VehiculoOcupadoExcepcion,
			RemolqueNoCompatibleException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		DialogoDetalles detalles = new DialogoDetalles(this, this);
		Cliente c = dbm.buscarClientePorId(Integer.parseInt(id));
		detalles.setEncargoClientes(this.dbm.buscarEncargosPorCliente(c.getId()));
		detalles.setPresupuestosClientes(this.dbm.buscarPresupuestosPorCliente(c.getId()));
		detalles.abrirDetalles(c);
		detalles.setLocationRelativeTo(origen);
		detalles.setVisible(true);
	}

	@Override
	public void buscarClientes() throws SQLException, DatoNoValidoException, NIFNoValidoException,
			MatriculaNoValidaException, TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, TrabajadorNoAsignadoException {
		ArrayList<Cliente> clientes = this.dbm.buscarClientes();
		this.mostrarClientePanelControl.setCIFCliente(clientes);
		this.mostrarClientePanelControl.setRazonSocial(clientes);
		this.mostrarClientePanelControl.setClientes(clientes);
		this.mostrarEncargoPanelControl.setClientes(clientes);
	}

	@Override
	public void buscarEncargoParaDetalles(String id, JPanel origen)
			throws NumberFormatException, SQLException, TrabajadorOcupadoException, NIFNoValidoException,
			DatoNoValidoException, RemolqueYaAsignadoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		DialogoDetalles detalles = new DialogoDetalles(this, this);
		detalles.setCamiones(dbm.buscarCamionesDisponiblesConRemolqueTrabajador());
		detalles.abrirDetalles(dbm.buscarEncargoPorId(Integer.parseInt(id)));
		detalles.setLocationRelativeTo(origen);
		detalles.setVisible(true);
	}

	@Override
	public void buscarEncargos() throws SQLException, TrabajadorOcupadoException, NIFNoValidoException,
			DatoNoValidoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		this.mostrarEncargoPanelControl.setEncargos(this.dbm.buscarEncargos());
	}

	@Override
	public void buscarEncargosPorCliente(Cliente cliente)
			throws SQLException, DatoNoValidoException, NIFNoValidoException, TrabajadorOcupadoException,
			RemolqueYaAsignadoException, CamionOcupadoException, VehiculoOcupadoExcepcion,
			RemolqueNoCompatibleException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		this.dbm.buscarEncargosPorCliente(cliente.getId());
	}

	//
	@Override
	public void buscarEncargosPorEstado(int estado)
			throws SQLException, TrabajadorOcupadoException, NIFNoValidoException, DatoNoValidoException {
		// ArrayList<Encargo> encargos = this.dbm.buscarEncargosPorEstado(estado);
		// this.finalizarEncargoPanelControl.setEncargos(encargos);
	}

	@Override
	public void buscarEncargosPorPresupuesto() throws SQLException, TrabajadorOcupadoException, NIFNoValidoException,
			DatoNoValidoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException {

	}

	@Override
	public void buscarLocalidadesUbicacion() throws SQLException {

	}

	@Override
	public void buscarPaisesUbicacion() throws SQLException {
	}

	@Override
	public void buscarParaGuardarPresupuesto(Cliente cliente, JPanel origen) {
		GuardarPresupuestoDialogoControl guardarPresupuestoDialogoControl = new GuardarPresupuestoDialogoControl(this,
				this, this.ubicaciones, cliente);
		guardarPresupuestoDialogoControl.getVista()
				.setTitle(Messages.getString("MainFrameControl.0") + cliente.getIdentidad().getRazonSocial()); //$NON-NLS-1$
		guardarPresupuestoDialogoControl.getVista().setLocationRelativeTo(this.vista);
		guardarPresupuestoDialogoControl.getVista().setVisible(true);
	}

	@Override
	public void buscarPresupuestoParaDetalles(String id, JPanel origen) throws NumberFormatException, SQLException,
			DatoNoValidoException, NIFNoValidoException, TrabajadorOcupadoException, RemolqueYaAsignadoException,
			CamionOcupadoException, MatriculaNoValidaException {
		DialogoDetalles detalles = new DialogoDetalles(this, this);
		detalles.abrirDetalles(dbm.buscarPresupuestoPorId(Integer.parseInt(id)));
		detalles.setLocationRelativeTo(origen);
		detalles.setVisible(true);
	}

	//
	@Override
	public void buscarPresupuestos()
			throws SQLException, DatoNoValidoException, NIFNoValidoException, MatriculaNoValidaException {
		ArrayList<Presupuesto> presupuestos = this.dbm.buscarPresupuestos();
		this.mostrarPresupuestoPanelControl.setPresupuestos(presupuestos);
	}

	@Override
	public void buscarPresupuestosPorCliente(Cliente cliente)
			throws SQLException, DatoNoValidoException, NIFNoValidoException, MatriculaNoValidaException {
		this.dbm.buscarPresupuestosPorCliente(cliente.getId());
	}

	@Override
	public void buscarRemolqueParaDetalles(String id, JPanel origen)
			throws NumberFormatException, SQLException, TrabajadorOcupadoException, DatoNoValidoException,
			NIFNoValidoException, RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		DialogoDetalles detalles = new DialogoDetalles(this, this);
		detalles.abrirDetalles(dbm.buscarRemolquePorId(Integer.parseInt(id)));
		detalles.setLocationRelativeTo(origen);
		detalles.setVisible(true);
	}

	@Override
	public void buscarRemolques() throws SQLException, DatoNoValidoException, MatriculaNoValidaException {
		ArrayList<Remolque> remolques = this.dbm.buscarRemolques();
		this.mostrarFlotaPanelControl.setRemolque(remolques);
	}

	@Override
	public void buscarTiposRemolque() throws SQLException {
		this.mostrarFlotaPanelControl.setTiposRemolque(this.dbm.buscarTiposRemolque());
	}

	@Override
	public void buscarTrabajadores()
			throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		ArrayList<Trabajador> trabajadores = this.dbm.buscarTrabajadores();
		this.mostrarTrabajadorPanelControl.setTrabajadores(trabajadores);

	}

	@Override
	public void buscarTrabajadoresPorEstado(int estado) throws SQLException, NIFNoValidoException {
		// ArrayList<Trabajador> trabajadores =
		// this.dbm.buscarTrabajadoresPorEstado(estado);
	}

	@Override
	public void buscarTrabajadorParaDetalles(String id, JPanel origen)
			throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		DialogoDetalles detalles = new DialogoDetalles(this, this);
		detalles.abrirDetalles(dbm.buscarTrabajadorPorId(id));
		detalles.setLocationRelativeTo(origen);
		detalles.setVisible(true);
	}

	@Override
	public void buscarUbicaciones() throws SQLException, DatoNoValidoException {
		this.ubicaciones = this.dbm.buscarUbicaciones();
//		this.mostrarPresupuestoPanelControl.setLocalidades(ubicaciones);
	}

	@Override
	public Ubicacion buscarUbicacionPorAtributos(String direccion, String localidad, String provincia, String cp,
			String pais) throws SQLException, DatoNoValidoException {
		return this.dbm.buscarUbicacion(direccion, localidad, provincia, cp, pais);
	}

	@Override
	public void cambiarEstadoRemolqueDisponible(Remolque r) throws SQLException {
		this.dbm.actualizarEstadoRemolque(r);
	}

	@Override
	public void completarEncargo(Encargo encargo) throws SQLException {
		this.dbm.completarEncargo(encargo);
	}

	private void crearCamionDialogo() {
		new Thread(() -> new GuardarCamionDialogoControl(this, this).getGuardarCamionPanel().setVisible(true)).start();
	}

	private void crearClienteDialogo() {
		new Thread(() -> new GuardarClienteDialogoControl(this, this).getVista().setVisible(true)).start();
	}

	private void crearRemolqueDialogo() {
		new Thread(() -> new GuardarRemolqueDialogoControl(this, this).getGuardarRemolquePanel().setVisible(true))
				.start();
	}

	private void crearTrabajadorDialogo() {
		new Thread(() -> new GuardarTrabajadorDialogoControl(new GuardarTrabajadorDialogo(), this, this).getVista()
				.setVisible(true)).start();
	}

	@Override
	public void guardaCamion(Camion cam) throws SQLException, EntidadYaExisteException, DatoNoValidoException {
		this.dbm.guardarCamion(cam);
	}

	@Override
	public void guardaCliente(Cliente c) throws SQLException, EntidadYaExisteException, DatoNoValidoException {
		this.dbm.guardarCliente(c);
	}

	//
	@Override
	public void guardaEncargo(Encargo e) throws SQLException, TrabajadorOcupadoException, NIFNoValidoException,
			DatoNoValidoException, EntidadYaExisteException {
		this.dbm.guardarEncargo(e);
	}

	//
	@Override
	public void guardaPresupuesto(Presupuesto p) throws SQLException, DatoNoValidoException, EntidadYaExisteException {
		this.dbm.guardarPresupuesto(p);
	}

	@Override
	public void guardaRemolque(Remolque rem) throws SQLException, EntidadYaExisteException, DatoNoValidoException {
		this.dbm.guardarRemolque(rem);
	}

	//
	@Override
	public void guardaTrabajador(Trabajador t)
			throws SQLException, NIFNoValidoException, EntidadYaExisteException, DatoNoValidoException {
		this.dbm.guardarTrabajador(t);
	}

	@Override
	public void guardaUbicacion(Ubicacion u) throws SQLException, EntidadYaExisteException, DatoNoValidoException {
		this.dbm.guardarUbicacion(u);
	}

	@Override
	public void iniciarEncargo(Encargo encargo) throws SQLException {
		this.dbm.iniciarEncargo(encargo);
	}

	@Override
	public void quitarRemolqueCamion(Camion camion)
			throws SQLException, TrabajadorOcupadoException, DatoNoValidoException, NIFNoValidoException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		this.dbm.quitarRemolqueCamion(camion);
	}

	@Override
	public void quitarTrabajadorParaCamion(Camion camion)
			throws SQLException, TrabajadorOcupadoException, DatoNoValidoException, NIFNoValidoException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		this.dbm.quitarTrabajadorCamion(camion);
	}

	public void setVisible(boolean b) {
		this.vista.setVisible(b);
	}

	private void tancarFrame() {
		this.vista.dispose();
	}

	public MainFramePanel getVista() {
		return this.vista;
	}

	public void cargarTodosLosDatos() throws NIFNoValidoException {

		try {
			buscarEncargos();
			buscarEncargosPorEstado(Encargo.ENCURSO);
			buscarPresupuestos();
			buscarTrabajadoresPorEstado(Trabajador.DISPONIBLE);
			buscarClientes();
			buscarUbicaciones();
			buscarCamiones();
			buscarRemolques();
			buscarTrabajadores();
			buscarTiposRemolque();
		} catch (Exception e) {
		}

	}
}
