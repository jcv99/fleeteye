package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.DatoNoValidoException;
import objeto.Ubicacion;

public class GestorUbicacion {

	GestorUbicacion() {
	}

	ArrayList<Ubicacion> buscar() throws SQLException, DatoNoValidoException {
		String consulta = Messages.getString("GestorUbicacion.0"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			ResultSet rs = pst.executeQuery();

			return getUbicaciones(rs);
		}
	}

	Ubicacion buscarPorId(int id) throws SQLException, DatoNoValidoException {

		String consulta = Messages.getString("GestorUbicacion.1"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			ArrayList<Ubicacion> ubicaciones = getUbicaciones(rs);

			if (ubicaciones.size() > 0)
				return ubicaciones.get(0);

			return null;
		}
	}
	
	ArrayList<String> buscarDistintasLocalidades() throws SQLException {
		String consulta = Messages.getString("GestorUbicacion.2"); //$NON-NLS-1$

		ArrayList<String> localidades = new ArrayList<String>();
		
		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				localidades.add(rs.getString(Messages.getString("GestorUbicacion.3"))); //$NON-NLS-1$
			}
			
			if (localidades.size() < 0)
				localidades = null;
		}
		
		return localidades;
	}
	
	ArrayList<String> buscarDistintosPaises() throws SQLException {
		String consulta = Messages.getString("GestorUbicacion.4"); //$NON-NLS-1$

		ArrayList<String> paises = new ArrayList<String>();
		
		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				paises.add(rs.getString(Messages.getString("GestorUbicacion.5"))); //$NON-NLS-1$
			}
			
			if (paises.size() < 0)
				paises = null;
		}
		
		return paises;
	}

	Ubicacion buscar(String direccion, String localidad, String provincia, String cp, String pais)
			throws SQLException, DatoNoValidoException {
		String consulta = Messages.getString("GestorUbicacion.6"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setString(1, direccion);
			pst.setString(2, localidad);
			pst.setString(3, provincia);
			pst.setString(4, cp);
			pst.setString(5, pais);

			ResultSet rs = pst.executeQuery();

			ArrayList<Ubicacion> ubicaciones = getUbicaciones(rs);

			if (ubicaciones.size() > 0)
				return ubicaciones.get(0);

			return null;
		}
	}

	void guardar(Ubicacion ubicacion) throws SQLException, DatoNoValidoException {

		String sentencia = Messages.getString("GestorUbicacion.7"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {

			pst.setString(1, ubicacion.getDireccion());
			pst.setString(2, ubicacion.getLocalidad());
			pst.setString(3, ubicacion.getProvincia());
			pst.setString(4, ubicacion.getCodigopostal());
			pst.setString(5, ubicacion.getPais());

			ResultSet rs = null;

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1) {
				con.commit();

				int id = 0;

				rs = pst.getGeneratedKeys();

				if (rs.next())
					id = rs.getInt(1);

				ubicacion.setId(id);
			} else
				con.rollback();
		}
	}

	void modificar(Ubicacion ubicacion) throws SQLException {
		String sentencia = Messages.getString("GestorUbicacion.8"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {

			pst.setString(1, ubicacion.getDireccion());
			pst.setString(2, ubicacion.getLocalidad());
			pst.setString(3, ubicacion.getProvincia());
			pst.setString(4, ubicacion.getCodigopostal());
			pst.setString(5, ubicacion.getPais());
			pst.setInt(6, ubicacion.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	void eliminar(Ubicacion ubicacion) throws SQLException {
		String sentencia = Messages.getString("GestorUbicacion.9"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {

			pst.setInt(1, ubicacion.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	ArrayList<Ubicacion> getUbicaciones(ResultSet rs) throws SQLException, DatoNoValidoException {
		ArrayList<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();

		while (rs.next()) {
			Ubicacion ubicacion = new Ubicacion(rs.getString(Messages.getString("GestorUbicacion.10")), rs.getString(Messages.getString("GestorUbicacion.11")), //$NON-NLS-1$ //$NON-NLS-2$
					rs.getString(Messages.getString("GestorUbicacion.12")), rs.getString(Messages.getString("GestorUbicacion.13")), rs.getString(Messages.getString("GestorUbicacion.14"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			ubicacion.setId(rs.getInt(Messages.getString("GestorUbicacion.15"))); //$NON-NLS-1$

			ubicaciones.add(ubicacion);
		}

		return ubicaciones;
	}
}
