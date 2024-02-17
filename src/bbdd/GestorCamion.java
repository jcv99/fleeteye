package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.CamionOcupadoException;
import exception.DatoNoValidoException;
import exception.MatriculaNoValidaException;
import exception.NIFNoValidoException;
import exception.RemolqueYaAsignadoException;
import exception.TrabajadorOcupadoException;
import objeto.Camion;
import objeto.Camion.Builder;
import objeto.Remolque;
import objeto.Trabajador;

public class GestorCamion {

	GestorTrabajador trabajadorManager;
	GestorRemolque remolqueManager;

	GestorCamion(GestorTrabajador trabajadorManager, GestorRemolque remolqueManager) {
		this.trabajadorManager = trabajadorManager;
		this.remolqueManager = remolqueManager;
	}

	ArrayList<Camion> buscar() throws SQLException, NIFNoValidoException, DatoNoValidoException,
			TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorCamion.0"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			ResultSet rs = pst.executeQuery();

			return getCamiones(rs);
		}
	}

	Camion buscarPorId(int id) throws SQLException, TrabajadorOcupadoException, DatoNoValidoException,
			NIFNoValidoException, RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorCamion.1"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			ArrayList<Camion> camiones = getCamiones(rs);
			if (camiones.size() > 0)
				return camiones.get(0);

			return null;
		}
	}

	ArrayList<Camion> buscarPorEstado(int estado) throws SQLException, DatoNoValidoException, NIFNoValidoException,
			TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorCamion.2"); //$NON-NLS-1$

		switch (estado) {
		case Remolque.OCUPADO:
			consulta += Messages.getString("GestorCamion.3"); //$NON-NLS-1$
			break;
		default:
			consulta += Messages.getString("GestorCamion.4"); //$NON-NLS-1$
			break;
		}

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			ResultSet rs = pst.executeQuery();

			return getCamiones(rs);
		}
	}

	ArrayList<Camion> buscarDisponiblesConRemolqueTrabajador() throws SQLException, NIFNoValidoException,
			DatoNoValidoException, TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorCamion.5"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			ResultSet rs = pst.executeQuery();

			return getCamiones(rs);
		}
	}

	void actualizarEstado(Camion camion) throws SQLException {
		String sentencia = Messages.getString("GestorCamion.6"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {
			pst.setInt(1, camion.getEstado());
			pst.setInt(2, camion.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	void asignarTrabajador(Camion camion) throws SQLException {
		String sentencia = Messages.getString("GestorCamion.7"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {
			pst.setInt(1, camion.getTrabajador().getId());
			pst.setInt(2, camion.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	void quitarTrabajador(Camion camion) throws SQLException {
		String sentencia = Messages.getString("GestorCamion.8"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {
			pst.setInt(1, camion.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	void asignarRemolque(Camion camion) throws SQLException {
		String sentencia = Messages.getString("GestorCamion.9"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {
			pst.setInt(1, camion.getRemolque().getId());
			pst.setInt(2, camion.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}

	}

	void quitarRemolque(Camion camion) throws SQLException {
		String sentencia = Messages.getString("GestorCamion.10"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {
			pst.setInt(1, camion.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	void guardar(Camion camion) throws SQLException, DatoNoValidoException {
		String sentencia = Messages.getString("GestorCamion.11"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {

			pst.setString(1, camion.getMatricula());
			pst.setDouble(2, camion.getTara());
			pst.setString(3, camion.getMarca());
			pst.setString(4, camion.getModelo());
			pst.setString(5, camion.getCombustible());
			pst.setInt(6, camion.getPotencia());
			pst.setString(7, camion.getNormativa());
			pst.setInt(8, camion.getKilometraje());
			pst.setDate(9, camion.getFechaMatriculacion());
			pst.setString(10, camion.getConfiguracionEje());
			pst.setString(11, camion.getPathRemoto());

			ResultSet rs = null;

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1) {
				con.commit();

				int id = 0;

				rs = pst.getGeneratedKeys();

				if (rs.next())
					id = rs.getInt(1);

				camion.setId(id);
			} else
				con.rollback();
		}
	}

	void eliminar(Camion camion) throws SQLException {
		String sentencia = Messages.getString("GestorCamion.12"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {

			pst.setInt(1, camion.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1) {
				con.commit();
			} else
				con.rollback();
		}
	}

	private ArrayList<Camion> getCamiones(ResultSet rs) throws SQLException, NIFNoValidoException,
			DatoNoValidoException, TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException {

		ArrayList<Camion> camiones = new ArrayList<Camion>();

		while (rs.next()) {
			Builder b = new Camion.Builder(rs.getString(Messages.getString("GestorCamion.13")), rs.getInt(Messages.getString("GestorCamion.14")), rs.getString(Messages.getString("GestorCamion.15")), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					rs.getString(Messages.getString("GestorCamion.16"))); //$NON-NLS-1$

			if (rs.getString(Messages.getString("GestorCamion.17")) != null) //$NON-NLS-1$
				b.combustible(rs.getString(Messages.getString("GestorCamion.18"))); //$NON-NLS-1$

			if (rs.getInt(Messages.getString("GestorCamion.19")) > 0) //$NON-NLS-1$
				b.potencia(rs.getInt(Messages.getString("GestorCamion.20"))); //$NON-NLS-1$

			if (rs.getString(Messages.getString("GestorCamion.21")) != null) //$NON-NLS-1$
				b.normativa(rs.getString(Messages.getString("GestorCamion.22"))); //$NON-NLS-1$

			if (rs.getInt(Messages.getString("GestorCamion.23")) > 0) //$NON-NLS-1$
				b.kilometraje(rs.getInt(Messages.getString("GestorCamion.24"))); //$NON-NLS-1$

			if ((rs.getDate(Messages.getString("GestorCamion.25"))) != null) //$NON-NLS-1$
				b.fechaMatriculacion(rs.getDate(Messages.getString("GestorCamion.26"))); //$NON-NLS-1$

			if (rs.getString(Messages.getString("GestorCamion.27")) != null) //$NON-NLS-1$
				b.configuracionEje(rs.getString(Messages.getString("GestorCamion.28"))); //$NON-NLS-1$

			if (rs.getInt(Messages.getString("GestorCamion.29")) == Camion.DISPONIBLE) //$NON-NLS-1$
				b.estado(Camion.DISPONIBLE);
			else
				b.estado(Camion.OCUPADO);

			if (rs.getString(Messages.getString("GestorCamion.30")) != null) //$NON-NLS-1$
				b.pathRemoto(rs.getString(Messages.getString("GestorCamion.31"))); //$NON-NLS-1$
			
			Camion camion = b.build();
			
			Trabajador trabajador = null;
			if (rs.getInt(Messages.getString("GestorCamion.32")) > 0) { //$NON-NLS-1$
				trabajador = trabajadorManager.buscarPorId(rs.getInt(Messages.getString("GestorCamion.33"))); //$NON-NLS-1$
				camion.trabajadorBBDD(trabajador);
			}

			Remolque remolque = null;
			if (rs.getInt(Messages.getString("GestorCamion.34")) > 0) { //$NON-NLS-1$
				remolque = remolqueManager.buscarPorId(rs.getInt(Messages.getString("GestorCamion.35"))); //$NON-NLS-1$
				camion.remolqueBBDD(remolque);
			}

			camion.setId(rs.getInt(Messages.getString("GestorCamion.36"))); //$NON-NLS-1$

			camiones.add(camion);
		}

		return camiones;
	}
}
