package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import exception.DatoNoValidoException;
import exception.NIFNoValidoException;
import objeto.IdentidadPersonaFisica;
import objeto.Nif;
import objeto.Trabajador;

class GestorTrabajador {

	ArrayList<Trabajador> buscar() throws SQLException, NIFNoValidoException, DatoNoValidoException {

		String consulta = Messages.getString("GestorTrabajador.0"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			ResultSet rs = pst.executeQuery();

			return getTrabajadores(rs);
		}
	}

	Trabajador buscarPorId(int id) throws SQLException, NIFNoValidoException, DatoNoValidoException {

		String consulta = Messages.getString("GestorTrabajador.1"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			ArrayList<Trabajador> trabajadores = getTrabajadores(rs);

			if (trabajadores.size() > 0)
				return trabajadores.get(0);

			return null;
		}
	}

	Trabajador buscarPorNif(String nif) throws SQLException, NIFNoValidoException, DatoNoValidoException {

		String consulta = Messages.getString("GestorTrabajador.2"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setString(1, nif);

			ResultSet rs = pst.executeQuery();

			ArrayList<Trabajador> trabajadores = getTrabajadores(rs);

			if (trabajadores.size() > 0)
				return trabajadores.get(0);

			return null;
		}
	}

	ArrayList<Trabajador> buscarPorEstado(int estado) throws SQLException, NIFNoValidoException, DatoNoValidoException {

		String consulta = Messages.getString("GestorTrabajador.3"); //$NON-NLS-1$

		switch (estado) {
		case Trabajador.DISPONIBLE:
			consulta += Messages.getString("GestorTrabajador.4"); //$NON-NLS-1$
			break;

		case Trabajador.OCUPADO:
			consulta += Messages.getString("GestorTrabajador.5"); //$NON-NLS-1$
			break;
		}

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			ResultSet rs = pst.executeQuery();

			return getTrabajadores(rs);
		}
	}

	void actualizarEstado(Trabajador trabajador) throws SQLException {
		String sentencia = Messages.getString("GestorTrabajador.6"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {
			pst.setInt(1, trabajador.getEstado());
			pst.setInt(2, trabajador.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	void guardar(Trabajador trabajador) throws SQLException, DatoNoValidoException {

		String sentencia = Messages.getString("GestorTrabajador.7"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {

			IdentidadPersonaFisica identidad = trabajador.getIdentidad();

			pst.setString(1, identidad.getNif());
			pst.setString(2, identidad.getNombre());
			pst.setString(3, identidad.getApellido());
			pst.setString(4, identidad.getSegundoApellido());
			pst.setString(5, identidad.getNacionalidad());
			pst.setDate(6, identidad.getFechaNacimiento());
			if (trabajador.getPathRemotoContrato() != null)
				pst.setString(7, trabajador.getPathRemotoContrato());
			else
				pst.setNull(7, Types.VARCHAR);

			ResultSet rs = null;

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1) {
				con.commit();

				int id = 0;

				rs = pst.getGeneratedKeys();

				if (rs.next())
					id = rs.getInt(1);

				trabajador.setId(id);
			} else
				con.rollback();
		}
	}

	void eliminar(Trabajador trabajador) throws SQLException {
		String sentencia = Messages.getString("GestorTrabajador.8"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {

			pst.setInt(1, trabajador.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1) {
				con.commit();
			} else
				con.rollback();
		}
	}

	private ArrayList<Trabajador> getTrabajadores(ResultSet rs)
			throws NIFNoValidoException, SQLException, DatoNoValidoException {

		ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();

		while (rs.next()) {
			Trabajador trabajador = new Trabajador(new IdentidadPersonaFisica(new Nif(rs.getString(Messages.getString("GestorTrabajador.9"))), //$NON-NLS-1$
					rs.getString(Messages.getString("GestorTrabajador.10")), rs.getString(Messages.getString("GestorTrabajador.11")), rs.getString(Messages.getString("GestorTrabajador.12")), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					rs.getString(Messages.getString("GestorTrabajador.13")), rs.getDate(Messages.getString("GestorTrabajador.14")))); //$NON-NLS-1$ //$NON-NLS-2$

			if (rs.getString(Messages.getString("GestorTrabajador.15")) != null) //$NON-NLS-1$
				trabajador.setPathRemotoContrato(rs.getString(Messages.getString("GestorTrabajador.16"))); //$NON-NLS-1$

			if (rs.getInt(Messages.getString("GestorTrabajador.17")) == Trabajador.DISPONIBLE) //$NON-NLS-1$
				trabajador.setEstadoDisponible();
			else
				trabajador.setEstadoOcupado();

			trabajador.setId(rs.getInt(Messages.getString("GestorTrabajador.18"))); //$NON-NLS-1$

			trabajadores.add(trabajador);
		}

		return trabajadores;
	}
}
