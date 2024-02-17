package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.DatoNoValidoException;
import exception.NIFNoValidoException;
import objeto.Cif;
import objeto.Cliente;
import objeto.IdentidadPersonaJuridica;

class GestorCliente {

	ArrayList<Cliente> buscar() throws SQLException, DatoNoValidoException, NIFNoValidoException {

		String consulta = Messages.getString("GestorCliente.0"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			ResultSet rs = pst.executeQuery();

			return getClientes(rs);
		}
	}

	Cliente buscarPorId(int id) throws SQLException, DatoNoValidoException, NIFNoValidoException {

		String consulta = Messages.getString("GestorCliente.1"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			ArrayList<Cliente> clientes = getClientes(rs);

			if (clientes.size() > 0)
				return clientes.get(0);
			else
				return null;
		}

	}

	Cliente buscarPorNif(String nif) throws SQLException, DatoNoValidoException, NIFNoValidoException {

		String consulta = Messages.getString("GestorCliente.2"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setString(1, nif);

			ResultSet rs = pst.executeQuery();

			ArrayList<Cliente> clientes = getClientes(rs);

			if (clientes.size() > 0)
				return clientes.get(0);

			return null;
		}
	}

	void guardar(Cliente cliente) throws SQLException {

		String sentencia = Messages.getString("GestorCliente.3"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {

			IdentidadPersonaJuridica identidad = cliente.getIdentidad();

			pst.setString(1, identidad.getNif());
			pst.setString(2, identidad.getRazonSocial());
			pst.setString(3, identidad.getActividadEconomica());
			pst.setString(4, cliente.getPathRemoto());

			ResultSet rs = null;

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1) {
				con.commit();

				int id = 0;

				rs = pst.getGeneratedKeys();

				if (rs.next())
					id = rs.getInt(1);

				cliente.setId(id);
			} else
				con.rollback();
		}

	}

	void eliminar(Cliente cliente) throws SQLException {

		String sentencia = Messages.getString("GestorCliente.4"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {

			pst.setInt(1, cliente.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	private ArrayList<Cliente> getClientes(ResultSet rs)
			throws DatoNoValidoException, SQLException, NIFNoValidoException {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		while (rs.next()) {
			Cliente cliente = new Cliente(new IdentidadPersonaJuridica(new Cif(rs.getString(Messages.getString("GestorCliente.5"))), //$NON-NLS-1$
					rs.getString(Messages.getString("GestorCliente.6")), rs.getString(Messages.getString("GestorCliente.7")))); //$NON-NLS-1$ //$NON-NLS-2$

			if (rs.getString(Messages.getString("GestorCliente.8")) != null) //$NON-NLS-1$
				cliente.setPathRemoto(rs.getString(Messages.getString("GestorCliente.9"))); //$NON-NLS-1$

			cliente.setId(rs.getInt(Messages.getString("GestorCliente.10"))); //$NON-NLS-1$

			clientes.add(cliente);
		}

		return clientes;
	}
}
