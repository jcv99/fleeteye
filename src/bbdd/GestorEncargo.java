package bbdd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import exception.CamionOcupadoException;
import exception.DatoNoValidoException;
import exception.MatriculaNoValidaException;
import exception.NIFNoValidoException;
import exception.RemolqueNoCompatibleException;
import exception.RemolqueYaAsignadoException;
import exception.TrabajadorNoAsignadoException;
import exception.TrabajadorOcupadoException;
import exception.VehiculoOcupadoExcepcion;
import objeto.Camion;
import objeto.Encargo;
import objeto.Presupuesto;

class GestorEncargo {

	private GestorCamion camionManager;
	private GestorTrabajador trabajadorManager;
	private GestorPresupuesto presupuestoManager;

	public GestorEncargo(GestorCamion camionManager, GestorTrabajador trabajadorManager,
			GestorPresupuesto presupuestoManager) {
		this.camionManager = camionManager;
		this.trabajadorManager = trabajadorManager;
		this.presupuestoManager = presupuestoManager;
	}

	ArrayList<Encargo> buscar() throws SQLException, TrabajadorOcupadoException, NIFNoValidoException,
			DatoNoValidoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorEncargo.0"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			ResultSet rs = pst.executeQuery();

			return getEncargos(rs);
		}
	}

	ArrayList<Encargo> buscarCompletados() throws SQLException, TrabajadorOcupadoException, NIFNoValidoException,
			DatoNoValidoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorEncargo.1"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			ResultSet rs = pst.executeQuery();

			return getCompletados(rs);
		}
	}

	Encargo buscarPorId(int id) throws SQLException, TrabajadorOcupadoException, NIFNoValidoException,
			DatoNoValidoException, RemolqueYaAsignadoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException,
			CamionOcupadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorEncargo.2"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			ArrayList<Encargo> encargos = getEncargos(rs);

			if (encargos.size() > 0)
				return encargos.get(0);

			return null;
		}
	}

	Encargo buscarCompletadoPorId(int id) throws SQLException, TrabajadorOcupadoException, NIFNoValidoException,
			DatoNoValidoException, RemolqueYaAsignadoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorEncargo.3"); //$NON-NLS-1$

		ArrayList<Encargo> completados = new ArrayList<Encargo>();

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			completados = getCompletados(rs);

			if (completados.size() > 0)
				return completados.get(0);

			return null;
		}
	}

	ArrayList<Encargo> buscarPorTrabajador(int idTrabajador) throws SQLException, TrabajadorOcupadoException,
			NIFNoValidoException, DatoNoValidoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorEncargo.4"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, idTrabajador);

			ResultSet rs = pst.executeQuery();

			return getEncargos(rs);
		}
	}

	ArrayList<Encargo> buscarCompletadosPorTrabajador(int idTrabajador) throws SQLException, TrabajadorOcupadoException,
			NIFNoValidoException, DatoNoValidoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException,
			RemolqueYaAsignadoException, CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorEncargo.5"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, idTrabajador);

			ResultSet rs = pst.executeQuery();

			return getCompletados(rs);
		}
	}

	ArrayList<Encargo> buscarPorPresupuesto(int idPresupuesto) throws SQLException, TrabajadorOcupadoException,
			NIFNoValidoException, DatoNoValidoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorEncargo.6"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, idPresupuesto);

			ResultSet rs = pst.executeQuery();

			return getEncargos(rs);
		}
	}

	ArrayList<Encargo> buscarCompletadosPorPresupuesto(int idPresupuesto)
			throws SQLException, TrabajadorOcupadoException, NIFNoValidoException, DatoNoValidoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorEncargo.7"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, idPresupuesto);

			ResultSet rs = pst.executeQuery();

			return getCompletados(rs);
		}
	}

	ArrayList<Encargo> buscarPorCliente(int idCliente) throws SQLException, TrabajadorOcupadoException,
			NIFNoValidoException, DatoNoValidoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException,
			RemolqueYaAsignadoException, CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorEncargo.8"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, idCliente);

			ResultSet rs = pst.executeQuery();

			return getEncargos(rs);
		}
	}

	ArrayList<Encargo> buscarCompletadosPorCliente(int idCliente) throws SQLException, DatoNoValidoException,
			NIFNoValidoException, TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorEncargo.9"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, idCliente);

			ResultSet rs = pst.executeQuery();

			return getCompletados(rs);
		}
	}

	ArrayList<Encargo> buscarPorCamion(int idCamion) throws SQLException, TrabajadorOcupadoException,
			NIFNoValidoException, DatoNoValidoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException,
			RemolqueYaAsignadoException, CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorEncargo.10"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, idCamion);

			ResultSet rs = pst.executeQuery();

			return getEncargos(rs);
		}
	}

	ArrayList<Encargo> buscarCompletadosPorCamion(int idCamion) throws SQLException, DatoNoValidoException,
			NIFNoValidoException, TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorEncargo.11"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, idCamion);

			ResultSet rs = pst.executeQuery();

			return getCompletados(rs);
		}
	}

	ArrayList<Encargo> buscarPorEstado(int estado) throws SQLException, TrabajadorOcupadoException,
			NIFNoValidoException, DatoNoValidoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException,
			RemolqueYaAsignadoException, CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorEncargo.12"); //$NON-NLS-1$

		switch (estado) {
		case Encargo.PORHACER:
			consulta += Messages.getString("GestorEncargo.13"); //$NON-NLS-1$
			break;
		case Encargo.PREPARADO:
			consulta += Messages.getString("GestorEncargo.14"); //$NON-NLS-1$
			break;
		case Encargo.ENCURSO:
			consulta += Messages.getString("GestorEncargo.15"); //$NON-NLS-1$
			break;

		case Encargo.COMPLETADO:
			consulta += Messages.getString("GestorEncargo.16"); //$NON-NLS-1$
			break;
		}

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			ResultSet rs = pst.executeQuery();

			if (estado != Encargo.COMPLETADO)
				return getEncargos(rs);
			return getCompletados(rs);
		}
	}

	void preparar(Encargo encargo) throws SQLException {
		String sentencia = Messages.getString("GestorEncargo.17"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
			pst.setInt(1, Encargo.PREPARADO);
			pst.setInt(2, encargo.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	void iniciar(Encargo encargo) throws SQLException {
		String sentencia = Messages.getString("GestorEncargo.18"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
			pst.setInt(1, Encargo.ENCURSO);
			pst.setTimestamp(2, encargo.getFechaInicio());
			pst.setInt(3, encargo.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	void guardar(Encargo encargo) throws SQLException, DatoNoValidoException {
		String sentencia = Messages.getString("GestorEncargo.19"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {

			pst.setInt(1, encargo.getPresupuesto().getId());

			Camion camion = encargo.getCamion();

			if (camion != null)
				pst.setInt(2, encargo.getCamion().getId());
			else
				pst.setNull(2, Types.INTEGER);

			pst.setInt(3, encargo.getEstado());

			if (encargo.getFechaInicio() != null)
				pst.setTimestamp(4, encargo.getFechaInicio());
			else
				pst.setNull(4, Types.TIMESTAMP);

			Date fechaEntrega = encargo.getFechaEntrega();

			if (fechaEntrega != null)
				pst.setDate(5, fechaEntrega);
			else
				pst.setNull(5, Types.DATE);

			if (encargo.getPathRemoto() != null)
				pst.setString(6, encargo.getPathRemoto());
			pst.setNull(6, Types.VARCHAR);

			ResultSet rs = null;

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1) {
				int id = 0;
				con.commit();

				rs = pst.getGeneratedKeys();

				if (rs.next())
					id = rs.getInt(1);

				encargo.setId(id);
			} else
				con.rollback();
		}
	}

	void asignarCamion(Encargo encargo) throws SQLException {
		String sentencia = Messages.getString("GestorEncargo.20"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {
			pst.setInt(1, encargo.getCamion().getId());
			pst.setInt(2, encargo.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	void quitarCamion(Encargo encargo) throws SQLException {
		String sentencia = Messages.getString("GestorEncargo.21"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {
			pst.setInt(1, Encargo.PORHACER);
			pst.setInt(2, encargo.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	void modificar(Encargo encargo) throws SQLException {
		String sentencia = Messages.getString("GestorEncargo.22"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {

			pst.setInt(1, encargo.getPresupuesto().getId());
//		pst.setInt(2, encargo.getTrabajador().getId());
			pst.setInt(3, encargo.getEstado());
			pst.setTimestamp(4, encargo.getFechaInicio());
			pst.setTimestamp(5, encargo.getFechaFin());
			pst.setInt(6, encargo.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1) {
				con.commit();

				eliminar(encargo);
			} else
				con.rollback();
		}
	}

	void completar(Encargo encargo) throws SQLException {
		String sentencia = Messages.getString("GestorEncargo.23"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {

			pst.setInt(1, Encargo.COMPLETADO);
			pst.setTimestamp(2, encargo.getFechaFin());
			pst.setInt(3, encargo.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	void eliminar(Encargo encargo) throws SQLException {
		String sentencia = Messages.getString("GestorEncargo.24"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {

			pst.setInt(1, encargo.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1) {
				con.commit();
			} else
				con.rollback();
		}
	}

	private ArrayList<Encargo> getEncargos(ResultSet rs) throws SQLException, TrabajadorOcupadoException,
			NIFNoValidoException, DatoNoValidoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {

		ArrayList<Encargo> encargos = new ArrayList<Encargo>();
		while (rs.next()) {
			Presupuesto presupuesto = presupuestoManager.buscarPorId(rs.getInt(Messages.getString("GestorEncargo.25"))); //$NON-NLS-1$

			Encargo encargo = new Encargo.Builder(presupuesto).build();

			encargo.setEstado(rs.getInt(Messages.getString("GestorEncargo.26"))); //$NON-NLS-1$

			if (rs.getTimestamp(Messages.getString("GestorEncargo.27")) != null) //$NON-NLS-1$
				encargo.setFechaInicio(rs.getTimestamp(Messages.getString("GestorEncargo.28"))); //$NON-NLS-1$

			Camion camion = null;

			if (rs.getInt(Messages.getString("GestorEncargo.29")) > 0) { //$NON-NLS-1$
				camion = camionManager.buscarPorId(rs.getInt(Messages.getString("GestorEncargo.30"))); //$NON-NLS-1$

				encargo.setVehiculoBBDD(camion);
			}

			if (rs.getDate(Messages.getString("GestorEncargo.31")) != null) //$NON-NLS-1$
				encargo.setFechaEntrega(rs.getDate(Messages.getString("GestorEncargo.32"))); //$NON-NLS-1$

			if (rs.getString(Messages.getString("GestorEncargo.33")) != null) //$NON-NLS-1$
				encargo.setPathRemoto(rs.getString(Messages.getString("GestorEncargo.34"))); //$NON-NLS-1$

			encargo.setId(rs.getInt(Messages.getString("GestorEncargo.35"))); //$NON-NLS-1$

			encargos.add(encargo);
		}
		return encargos;
	}

	private ArrayList<Encargo> getCompletados(ResultSet rs) throws SQLException, DatoNoValidoException,
			NIFNoValidoException, TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, TrabajadorNoAsignadoException, MatriculaNoValidaException {

		ArrayList<Encargo> completados = new ArrayList<Encargo>();

		while (rs.next()) {
			Presupuesto presupuesto = presupuestoManager.buscarPorId(rs.getInt(Messages.getString("GestorEncargo.36"))); //$NON-NLS-1$

			Camion camion = camionManager.buscarPorId(rs.getInt(Messages.getString("GestorEncargo.37"))); //$NON-NLS-1$

			camion.trabajadorBBDD(trabajadorManager.buscarPorId(rs.getInt(Messages.getString("GestorEncargo.38")))); //$NON-NLS-1$

			Encargo completado = new Encargo.Builder(presupuesto).build();

			completado.setVehiculoBBDD(camion);

			if (rs.getDate(Messages.getString("GestorEncargo.39")) != null) //$NON-NLS-1$
				completado.setFechaEntrega(rs.getDate(Messages.getString("GestorEncargo.40"))); //$NON-NLS-1$

			completado.setEstado(Encargo.COMPLETADO);

			completado.setFechaInicio(rs.getTimestamp(Messages.getString("GestorEncargo.41"))); //$NON-NLS-1$

			completado.setFechaFin(rs.getTimestamp(Messages.getString("GestorEncargo.42"))); //$NON-NLS-1$

			if (rs.getString(Messages.getString("GestorEncargo.43")) != null) //$NON-NLS-1$
				completado.setPathRemoto(rs.getString(Messages.getString("GestorEncargo.44"))); //$NON-NLS-1$

			completado.setId(rs.getInt(Messages.getString("GestorEncargo.45"))); //$NON-NLS-1$

			completados.add(completado);
		}

		return completados;
	}
}
