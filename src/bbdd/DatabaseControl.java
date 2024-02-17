package bbdd;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TimeZone;

import constante.ConstantesExcepciones;
import exception.CamionOcupadoException;
import exception.DatoNoValidoException;
import exception.DialogoError;
import exception.EntidadYaExisteException;
import exception.MatriculaNoValidaException;
import exception.NIFNoValidoException;
import exception.RemolqueNoCompatibleException;
import exception.RemolqueYaAsignadoException;
import exception.TrabajadorNoAsignadoException;
import exception.TrabajadorOcupadoException;
import exception.VehiculoOcupadoExcepcion;
import herramienta.ConfiguracionServidor;
import objeto.Camion;
import objeto.Cliente;
import objeto.Encargo;
import objeto.Presupuesto;
import objeto.Remolque;
import objeto.Trabajador;
import objeto.Ubicacion;

public class DatabaseControl {
// jdbc:mysql://fleeteye.ddns.net/fleeteye\ ?serverTimezone=TimeZone.getDefault().getID()
	/*private static final String urlCon = Messages.getString("DatabaseControl.0") + ConfiguracionServidor.HOST
			+ Messages.getString("BARRA") + ConfiguracionServidor.BBDD // $NON-NLS-1$ //$NON-NLS-2$
			+ Messages.getString("DatabaseControl.2") + TimeZone.getDefault().getID(); //$NON-NLS-1$
*/
	private static final String urlCon ="jdbc:mysql://localhost/fleeteye";
//	private static final String urlCon = "jdbc:mysql://labs.iam.cat/a18danbargar_prototipo3?serverTimezone="
//			+ TimeZone.getDefault().getID();
//	private static final String user = "a18danbargar_1";
//	private static final String pass = "ausias";

	private GestorCliente gestorCliente;
	private GestorUbicacion gestorUbicacion;
	private GestorPresupuesto gestorPresupuesto;
	private GestorTrabajador gestorTrabajador;
	private GestorCamion gestorCamion;
	private GestorRemolque gestorRemolque;
	private GestorEncargo gestorEncargo;

	public DatabaseControl() {
		gestorCliente = new GestorCliente();
		gestorUbicacion = new GestorUbicacion();
		gestorTrabajador = new GestorTrabajador();
		gestorRemolque = new GestorRemolque();
		gestorPresupuesto = new GestorPresupuesto(gestorCliente, gestorUbicacion, gestorRemolque);
		gestorCamion = new GestorCamion(gestorTrabajador, gestorRemolque);
		gestorEncargo = new GestorEncargo(gestorCamion, gestorTrabajador, gestorPresupuesto);
	}

	static Connection getConnection() {
		try {
			return DriverManager.getConnection(urlCon, ConfiguracionServidor.USUARIO, ConfiguracionServidor.PASSWORD);
		} catch (SQLException e) {
			new DialogoError(new SQLException(ConstantesExcepciones.DBC_ERROR_CONEXION));
		}
		return null;
	}

	private static String passwordToMD5(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance(Messages.getString("DatabaseControl.3")); //$NON-NLS-1$

			md.update(password.getBytes());

			byte[] byteData = md.digest();

			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xFF) + 0x100, 16).substring(1));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			new DialogoError(e).showErrorMessage();
		}
		return password;
	}

	public static boolean login(String user, String password) {
		boolean success = true;

		/*try {
			String consulta = Messages.getString("DatabaseControl.4"); //$NON-NLS-1$

			Connection con = getConnection();

			PreparedStatement pst = con.prepareStatement(consulta);

			pst.setString(1, user);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				if (rs.getString(Messages.getString("DatabaseControl.5")).contentEquals(passwordToMD5(password))) //$NON-NLS-1$
					success = true;
			}

			rs.close();
			pst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return success;
	}

	// Clientes

	public ArrayList<Cliente> buscarClientes() throws SQLException, DatoNoValidoException, NIFNoValidoException,
			MatriculaNoValidaException, TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, TrabajadorNoAsignadoException {
		ArrayList<Cliente> clientes = gestorCliente.buscar();

		for (Cliente cliente : clientes) {
			ArrayList<Presupuesto> presupuestos = buscarPresupuestosPorCliente(cliente.getId());

			if (presupuestos != null)
				cliente.setPresupuestos(presupuestos);

			ArrayList<Encargo> encargos = buscarEncargosPorCliente(cliente.getId());

			if (encargos != null)
				cliente.setEncargos(encargos);
		}

		return clientes;
	}

	public Cliente buscarClientePorId(int id) throws SQLException, DatoNoValidoException, NIFNoValidoException,
			MatriculaNoValidaException, TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, TrabajadorNoAsignadoException {
		Cliente cliente = gestorCliente.buscarPorId(id);

		if (cliente != null) {
			ArrayList<Presupuesto> presupuestos = buscarPresupuestosPorCliente(cliente.getId());

			if (presupuestos != null)
				cliente.setPresupuestos(presupuestos);

			ArrayList<Encargo> encargos = buscarEncargosPorCliente(cliente.getId());

			if (encargos != null)
				cliente.setEncargos(encargos);
		}

		return cliente;
	}

	public Cliente buscarClientePorNif(String nif) throws SQLException, DatoNoValidoException, NIFNoValidoException,
			MatriculaNoValidaException, TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, TrabajadorNoAsignadoException {
		Cliente cliente = gestorCliente.buscarPorNif(nif);

		if (cliente != null) {
			ArrayList<Presupuesto> presupuestos = buscarPresupuestosPorCliente(cliente.getId());

			if (presupuestos != null)
				cliente.setPresupuestos(presupuestos);

			ArrayList<Encargo> encargos = buscarEncargosPorCliente(cliente.getId());

			if (encargos != null)
				cliente.setEncargos(encargos);
		}

		return cliente;
	}

	public void guardarCliente(Cliente cliente) throws SQLException, EntidadYaExisteException {
		try {
			gestorCliente.guardar(cliente);
		} catch (SQLException e) {
			if (e.getMessage().contains(ConstantesExcepciones.SQL_DUPLICATE_ENTRY))
				throw new EntidadYaExisteException(
						ConstantesExcepciones.DBC_CLIENTE_EXISTE + Messages.getString("DatabaseControl.6") //$NON-NLS-1$
								+ cliente.getIdentidad().getNif(), ConstantesExcepciones.DBC_ERROR_GUARDAR_CLIENTE);
			else
				throw e;
		}
	}

	public void eliminarCliente(Cliente cliente) throws SQLException {
		gestorCliente.eliminar(cliente);
	}

	// Ubicaciones

	public ArrayList<Ubicacion> buscarUbicaciones() throws SQLException, DatoNoValidoException {
		return gestorUbicacion.buscar();
	}

	public Ubicacion buscarUbicacionPorId(int id) throws SQLException, DatoNoValidoException {
		return gestorUbicacion.buscarPorId(id);
	}

	public ArrayList<String> buscarLocalidadesUbicacion() throws SQLException {
		return gestorUbicacion.buscarDistintasLocalidades();
	}

	public ArrayList<String> buscarPaisesUbicacion() throws SQLException {
		return gestorUbicacion.buscarDistintosPaises();
	}

	public Ubicacion buscarUbicacion(String direccion, String localidad, String provincia, String cp, String pais)
			throws SQLException, DatoNoValidoException {
		return gestorUbicacion.buscar(direccion, localidad, provincia, cp, pais);
	}

	public void guardarUbicacion(Ubicacion ubicacion)
			throws SQLException, EntidadYaExisteException, DatoNoValidoException {
		try {
			gestorUbicacion.guardar(ubicacion);
		} catch (SQLException e) {
			if (e.getMessage().contains(ConstantesExcepciones.SQL_DUPLICATE_ENTRY))
				throw new EntidadYaExisteException(
						ConstantesExcepciones.DBC_UBICACION_EXISTE + Messages.getString("DatabaseControl.7") //$NON-NLS-1$
								+ ubicacion.getDireccion() + Messages.getString("DatabaseControl.8") + ubicacion.getCodigopostal(), //$NON-NLS-1$
						ConstantesExcepciones.DBC_ERROR_GUARDAR_UBICACION);
			else
				throw e;
		}
	}

	public void modificarUbicacion(Ubicacion ubicacion) throws SQLException {
		gestorUbicacion.modificar(ubicacion);
	}

	public void eliminarUbicacion(Ubicacion ubicacion) throws SQLException {
		gestorUbicacion.eliminar(ubicacion);
	}

	// Trabajadores

	public ArrayList<Trabajador> buscarTrabajadores()
			throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		ArrayList<Trabajador> trabajadores = gestorTrabajador.buscar();

		if (trabajadores != null) {
			for (Trabajador trabajador : trabajadores) {
				ArrayList<Encargo> encargos = buscarEncargosPorTrabajador(trabajador.getId());
				if (encargos != null)
					trabajador.setEncargos(new HashSet<Encargo>(encargos));
			}
		}

		return trabajadores;
	}

	public Trabajador buscarTrabajadorPorId(String id)
			throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		Trabajador trabajador = gestorTrabajador.buscarPorId(Integer.parseInt(id));

		if (trabajador != null) {

			ArrayList<Encargo> encargos = buscarEncargosPorTrabajador(trabajador.getId());

			if (encargos != null)
				trabajador.setEncargos(new HashSet<Encargo>(encargos));
		}

		return trabajador;
	}

	public Trabajador buscarTrabajadorPorNif(String nif)
			throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		Trabajador trabajador = gestorTrabajador.buscarPorNif(nif);

		if (trabajador != null) {

			ArrayList<Encargo> encargos = buscarEncargosPorTrabajador(trabajador.getId());

			if (encargos != null)
				trabajador.setEncargos(new HashSet<Encargo>(encargos));
		}

		return trabajador;
	}

	public ArrayList<Trabajador> buscarTrabajadoresPorEstado(int estado)
			throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		ArrayList<Trabajador> trabajadores = gestorTrabajador.buscarPorEstado(estado);

		if (trabajadores != null) {
			for (Trabajador trabajador : trabajadores) {
				ArrayList<Encargo> encargos = buscarEncargosPorTrabajador(trabajador.getId());
				if (encargos != null)
					trabajador.setEncargos(new HashSet<Encargo>(encargos));
			}
		}

		return trabajadores;

	}

	public void actualizarEstadoTrabajador(Trabajador trabajador) throws SQLException {
		gestorTrabajador.actualizarEstado(trabajador);
	}

	public void guardarTrabajador(Trabajador trabajador)
			throws EntidadYaExisteException, DatoNoValidoException, SQLException {
		try {
			gestorTrabajador.guardar(trabajador);
		} catch (SQLException e) {
			if (e.getMessage().contains(ConstantesExcepciones.SQL_DUPLICATE_ENTRY))
				throw new EntidadYaExisteException(
						ConstantesExcepciones.DBC_TRABAJADOR_EXISTE + Messages.getString("DatabaseControl.9") //$NON-NLS-1$
								+ trabajador.getIdentidad().getNif(), ConstantesExcepciones.DBC_ERROR_GUARDAR_TRABAJADOR);
			else
				throw e;
		}
	}

	public void eliminarTrabajador(Trabajador trabajador) throws SQLException {
		gestorTrabajador.eliminar(trabajador);
	}

	// Camiones

	public ArrayList<Camion> buscarCamiones()
			throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		return gestorCamion.buscar();
	}

	public Camion buscarCamionPorId(int id) throws SQLException, TrabajadorOcupadoException, DatoNoValidoException,
			NIFNoValidoException, RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		return gestorCamion.buscarPorId(id);
	}

	public ArrayList<Camion> buscarCamionesPorEstado(int estado)
			throws SQLException, DatoNoValidoException, NIFNoValidoException, TrabajadorOcupadoException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		return gestorCamion.buscarPorEstado(estado);
	}

	public ArrayList<Camion> buscarCamionesDisponiblesConRemolqueTrabajador()
			throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		return gestorCamion.buscarDisponiblesConRemolqueTrabajador();
	}

	public void actualizarEstadoCamion(Camion camion) throws SQLException {
		gestorCamion.actualizarEstado(camion);
	}

	public void asignarTrabajadorCamion(Camion camion) throws SQLException {
		if (camion.getTrabajador() != null) {
			gestorCamion.asignarTrabajador(camion);
			actualizarEstadoTrabajador(camion.getTrabajador());
		}
	}

	public void quitarTrabajadorCamion(Camion camion)
			throws SQLException, TrabajadorOcupadoException, DatoNoValidoException, NIFNoValidoException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		Trabajador trabajador = gestorCamion.buscarPorId(camion.getId()).getTrabajador();
		if (trabajador != null) {
			trabajador.setEstadoDisponible();
			gestorCamion.quitarTrabajador(camion);
			actualizarEstadoTrabajador(trabajador);
		}
	}

	public void asignarRemolqueCamion(Camion camion) throws SQLException {
		if (camion.getRemolque() != null) {
			gestorCamion.asignarRemolque(camion);
			actualizarEstadoRemolque(camion.getRemolque());
		}
	}

	public void quitarRemolqueCamion(Camion camion)
			throws SQLException, TrabajadorOcupadoException, DatoNoValidoException, NIFNoValidoException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		Remolque remolque = gestorCamion.buscarPorId(camion.getId()).getRemolque();
		if (remolque != null) {
			remolque.setEstado(Remolque.DISPONIBLE);
			gestorCamion.quitarRemolque(camion);
			actualizarEstadoRemolque(remolque);
		}
	}

	public void guardarCamion(Camion camion) throws EntidadYaExisteException, DatoNoValidoException, SQLException {
		try {
			gestorCamion.guardar(camion);
		} catch (SQLException e) {
			if (e.getMessage().contains(ConstantesExcepciones.SQL_DUPLICATE_ENTRY))
				throw new EntidadYaExisteException(
						ConstantesExcepciones.DBC_CAMION_EXISTE + Messages.getString("DatabaseControl.10") //$NON-NLS-1$
								+ camion.getMatricula(), ConstantesExcepciones.DBC_ERROR_GUARDAR_CAMION);
			else
				throw e;
		}
	}

	public void eliminarCamion(Camion camion) throws SQLException {
		gestorCamion.eliminar(camion);
	}

	// Remolques

	public ArrayList<Remolque> buscarRemolques()
			throws SQLException, DatoNoValidoException, MatriculaNoValidaException {
		return gestorRemolque.buscar();
	}

	public Remolque buscarRemolquePorId(int id) throws SQLException, DatoNoValidoException, MatriculaNoValidaException {
		return gestorRemolque.buscarPorId(id);
	}

	public ArrayList<Remolque> buscarRemolquePorTipo(int tipo)
			throws SQLException, DatoNoValidoException, MatriculaNoValidaException {
		return gestorRemolque.buscarPorTipo(tipo);
	}

	public ArrayList<Remolque> buscarRemolquePorEstado(int estado)
			throws SQLException, DatoNoValidoException, MatriculaNoValidaException {
		return gestorRemolque.buscarPorEstado(estado);
	}

	public ArrayList<String> buscarTiposRemolque() throws SQLException {
		return gestorRemolque.buscarTiposRemolque();
	}

	public void actualizarEstadoRemolque(Remolque remolque) throws SQLException {
		gestorRemolque.actualizarEstado(remolque);
	}

	public void guardarRemolque(Remolque remolque)
			throws EntidadYaExisteException, DatoNoValidoException, SQLException {
		try {
			gestorRemolque.guardar(remolque);
		} catch (SQLException e) {
			if (e.getMessage().contains(ConstantesExcepciones.SQL_DUPLICATE_ENTRY))
				throw new EntidadYaExisteException(
						ConstantesExcepciones.DBC_REMOLQUE_EXISTE + Messages.getString("DatabaseControl.11") //$NON-NLS-1$
								+ remolque.getMatricula(), ConstantesExcepciones.DBC_ERROR_GUARDAR_REMOLQUE);
			else
				throw e;
		}
	}

	public void eliminarRemolque(Remolque remolque) throws SQLException {
		gestorRemolque.eliminar(remolque);
	}

	// Presupuestos

	public ArrayList<Presupuesto> buscarPresupuestos()
			throws SQLException, DatoNoValidoException, NIFNoValidoException, MatriculaNoValidaException {
		return gestorPresupuesto.buscar();
	}

	public Presupuesto buscarPresupuestoPorId(int id)
			throws SQLException, DatoNoValidoException, NIFNoValidoException, MatriculaNoValidaException {
		return gestorPresupuesto.buscarPorId(id);
	}

	public ArrayList<Presupuesto> buscarPresupuestosPorCliente(int idCliente)
			throws SQLException, DatoNoValidoException, NIFNoValidoException, MatriculaNoValidaException {
		return gestorPresupuesto.buscarPorCliente(idCliente);
	}

	public Presupuesto buscarPresupuesto(Ubicacion origen, Ubicacion destino, double precio, Cliente cliente)
			throws SQLException, DatoNoValidoException, NIFNoValidoException, MatriculaNoValidaException {
		return gestorPresupuesto.buscar(origen, destino, precio, cliente);
	}

	public void guardarPresupuesto(Presupuesto presupuesto)
			throws EntidadYaExisteException, SQLException, DatoNoValidoException {
		try {
			gestorPresupuesto.guardar(presupuesto);
		} catch (SQLException e) {
			if (e.getMessage().contains(ConstantesExcepciones.SQL_DUPLICATE_ENTRY))
				throw new EntidadYaExisteException(ConstantesExcepciones.DBC_PRESUPUESTO_EXISTE,
						ConstantesExcepciones.DBC_ERROR_GUARDAR_PRESUPUESTO);
			else
				throw e;
		}
	}

	public void modificarPresupuesto(Presupuesto presupuesto) throws SQLException {
		gestorPresupuesto.modificar(presupuesto);
	}

	public void eliminarPresupuesto(Presupuesto presupuesto) throws SQLException {
		gestorPresupuesto.eliminar(presupuesto);
	}

	// Encargos

	public ArrayList<Encargo> buscarEncargos() throws SQLException, TrabajadorOcupadoException, NIFNoValidoException,
			DatoNoValidoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		ArrayList<Encargo> encargos = gestorEncargo.buscar();

		encargos.addAll(gestorEncargo.buscarCompletados());

		return encargos;
	}

	public ArrayList<Encargo> buscarEncargosNoCompletados() throws SQLException, TrabajadorOcupadoException,
			NIFNoValidoException, DatoNoValidoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		return gestorEncargo.buscar();
	}

	public ArrayList<Encargo> buscarEncargosCompletados()
			throws SQLException, TrabajadorOcupadoException, NIFNoValidoException, DatoNoValidoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		return gestorEncargo.buscarCompletados();
	}

	public Encargo buscarEncargoPorId(int id) throws SQLException, TrabajadorOcupadoException, NIFNoValidoException,
			DatoNoValidoException, RemolqueYaAsignadoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		Encargo encargo = gestorEncargo.buscarPorId(id);

		if (encargo == null) {
			encargo = gestorEncargo.buscarCompletadoPorId(id);
		}
		return encargo;
	}

	public ArrayList<Encargo> buscarEncargosPorTrabajador(int idTrabajador)
			throws SQLException, TrabajadorOcupadoException, NIFNoValidoException, DatoNoValidoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		ArrayList<Encargo> encargos = gestorEncargo.buscarPorTrabajador(idTrabajador);

		encargos.addAll(gestorEncargo.buscarCompletadosPorTrabajador(idTrabajador));

		return encargos;
	}

	public ArrayList<Encargo> buscarEncargosPorPresupuesto(int idPresupuesto)
			throws SQLException, TrabajadorOcupadoException, NIFNoValidoException, DatoNoValidoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		ArrayList<Encargo> encargos = gestorEncargo.buscarPorPresupuesto(idPresupuesto);

		encargos.addAll(gestorEncargo.buscarCompletadosPorPresupuesto(idPresupuesto));

		return encargos;
	}

	public ArrayList<Encargo> buscarEncargosPorCliente(int idCliente)
			throws SQLException, DatoNoValidoException, NIFNoValidoException, TrabajadorOcupadoException,
			RemolqueYaAsignadoException, CamionOcupadoException, VehiculoOcupadoExcepcion,
			RemolqueNoCompatibleException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		ArrayList<Encargo> encargos = gestorEncargo.buscarPorCliente(idCliente);

		encargos.addAll(gestorEncargo.buscarCompletadosPorCliente(idCliente));

		return encargos;
	}

	public ArrayList<Encargo> buscarEncargosPorCamion(int idCamion)
			throws SQLException, DatoNoValidoException, NIFNoValidoException, TrabajadorOcupadoException,
			RemolqueYaAsignadoException, CamionOcupadoException, VehiculoOcupadoExcepcion,
			RemolqueNoCompatibleException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		ArrayList<Encargo> encargos = gestorEncargo.buscarPorCamion(idCamion);

		encargos.addAll(gestorEncargo.buscarCompletadosPorCamion(idCamion));

		return encargos;
	}

	public ArrayList<Encargo> buscarEncargosPorEstado(int estado)
			throws SQLException, TrabajadorOcupadoException, NIFNoValidoException, DatoNoValidoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		return gestorEncargo.buscarPorEstado(estado);
	}

	public void prepararEncargo(Encargo encargo) throws SQLException {
		gestorEncargo.preparar(encargo);
	}

	public void iniciarEncargo(Encargo encargo) throws SQLException {
		gestorEncargo.iniciar(encargo);
	}

	public void asignarCamionEncargo(Encargo encargo) throws SQLException {
		if (encargo.getCamion() != null) {
			gestorEncargo.asignarCamion(encargo);
			prepararEncargo(encargo);
			actualizarEstadoCamion(encargo.getCamion());
		}
	}

	public void quitarCamionEncargo(Encargo encargo) throws SQLException, TrabajadorOcupadoException,
			NIFNoValidoException, DatoNoValidoException, RemolqueYaAsignadoException, VehiculoOcupadoExcepcion,
			RemolqueNoCompatibleException, CamionOcupadoException, MatriculaNoValidaException {
		Camion camion = gestorEncargo.buscarPorId(encargo.getId()).getCamion();
		if (camion != null) {
			camion.setEstadoDisponible();
			gestorEncargo.quitarCamion(encargo);
			actualizarEstadoCamion(camion);
		}
	}

	public void guardarEncargo(Encargo encargo) throws EntidadYaExisteException, SQLException, DatoNoValidoException {
		try {
			gestorEncargo.guardar(encargo);
			if (encargo.getCamion() != null)
				actualizarEstadoCamion(encargo.getCamion());
		} catch (SQLException e) {
			if (e.getMessage().contains(ConstantesExcepciones.SQL_DUPLICATE_ENTRY))
				throw new EntidadYaExisteException(ConstantesExcepciones.DBC_ENCARGO_EXISTE,
						ConstantesExcepciones.DBC_ERROR_GUARDAR_ENCARGO);
			else
				throw e;
		}
	}

	public void eliminarEncargo(Encargo encargo) throws SQLException {
		gestorEncargo.eliminar(encargo);
	}

	public void completarEncargo(Encargo encargo) throws SQLException {
		gestorEncargo.completar(encargo);
		actualizarEstadoCamion(encargo.getCamion());
		gestorEncargo.eliminar(encargo);
	}
}
