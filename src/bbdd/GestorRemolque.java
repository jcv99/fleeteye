package bbdd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import constante.Messages;
import exception.DatoNoValidoException;
import exception.MatriculaNoValidaException;
import objeto.DimensionesRemolque;
import objeto.Remolque;
import objeto.RemolqueFrigorifico;
import objeto.RemolqueLona;
import objeto.RemolquePisoMovil;

public class GestorRemolque {

	ArrayList<Remolque> buscar() throws SQLException, DatoNoValidoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorRemolque.0"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			ResultSet rs = pst.executeQuery();

			return getRemolques(rs);
		}
	}

	Remolque buscarPorId(int id) throws SQLException, DatoNoValidoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorRemolque.1"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			ArrayList<Remolque> remolques = getRemolques(rs);

			if (remolques.size() > 0)
				return remolques.get(0);

			return null;
		}
	}

	ArrayList<Remolque> buscarPorTipo(int tipo) throws SQLException, DatoNoValidoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorRemolque.2"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, tipo);

			ResultSet rs = pst.executeQuery();

			return getRemolques(rs);
		}
	}

	ArrayList<Remolque> buscarPorEstado(int estado)
			throws SQLException, DatoNoValidoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorRemolque.3"); //$NON-NLS-1$

		switch (estado) {
		case Remolque.OCUPADO:
			consulta += Messages.getString("GestorRemolque.4"); //$NON-NLS-1$
			break;
		default:
			consulta += Messages.getString("GestorRemolque.5"); //$NON-NLS-1$
			break;
		}

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			ResultSet rs = pst.executeQuery();

			return getRemolques(rs);
		}
	}
	
	ArrayList<String> buscarTiposRemolque() throws SQLException {
		String consulta = Messages.getString("GestorRemolque.6"); //$NON-NLS-1$
		
		ArrayList<String> tipos = new ArrayList<String>();
		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				tipos.add(rs.getString(Messages.getString("GestorRemolque.7"))); //$NON-NLS-1$
			}
			
			if(tipos.size() == 0)
				tipos = null;
		}
		return tipos;
	}

	void actualizarEstado(Remolque remolque) throws SQLException {
		String sentencia = Messages.getString("GestorRemolque.8"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {
			pst.setInt(1, remolque.getEstado());
			pst.setInt(2, remolque.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	void guardar(Remolque remolque) throws SQLException, DatoNoValidoException {
		// Se le pone el tipo 1 para luego modificarlo
		String sentencia = Messages.getString("GestorRemolque.9"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {

			DimensionesRemolque dimensiones = remolque.getDimensionesRemolque();

			pst.setString(1, remolque.getMatricula());
			pst.setDouble(2, remolque.getTara());
			pst.setDouble(3, dimensiones.getAltura());
			pst.setDouble(4, dimensiones.getAnchura());
			pst.setDouble(5, dimensiones.getLongitud());
			pst.setInt(6, remolque.getEjes());
			pst.setDate(7, remolque.getFechaCompra());

			String pathRemoto = remolque.getPathRemoto();

			if (pathRemoto != null)
				pst.setString(8, pathRemoto);
			else
				pst.setNull(8, Types.VARCHAR);

			ResultSet rs = null;

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1) {
				con.commit();

				int id = 0;

				rs = pst.getGeneratedKeys();

				if (rs.next())
					id = rs.getInt(1);

				remolque.setId(id);

				PreparedStatement pstUpdate = null;

				sentencia = Messages.getString("GestorRemolque.10"); //$NON-NLS-1$

				if (remolque instanceof RemolqueLona) {
					sentencia += Messages.getString("GestorRemolque.11"); //$NON-NLS-1$

					pstUpdate = con.prepareStatement(sentencia);

					pstUpdate.setInt(1, Remolque.TIPO_LONA);
					pstUpdate.setBoolean(2, ((RemolqueLona) remolque).tieneCinchas());
					pstUpdate.setBoolean(3, ((RemolqueLona) remolque).abreArriba());
					pstUpdate.setBoolean(4, ((RemolqueLona) remolque).tieneEngancheRemolque());
					pstUpdate.setInt(5, remolque.getId());
				} else if (remolque instanceof RemolqueFrigorifico) {
					sentencia += Messages.getString("GestorRemolque.12"); //$NON-NLS-1$

					pstUpdate = con.prepareStatement(sentencia);
					pstUpdate.setInt(1, Remolque.TIPO_FRIGORIFICO);
					pstUpdate.setInt(2, ((RemolqueFrigorifico) remolque).getCapacidadPalettes());
					pstUpdate.setInt(3, remolque.getId());
				} else {
					sentencia += Messages.getString("GestorRemolque.13"); //$NON-NLS-1$

					pstUpdate = con.prepareStatement(sentencia);

					pstUpdate.setInt(1, Remolque.TIPO_PISOMOVIL);
					pstUpdate.setInt(2, ((RemolquePisoMovil) remolque).getVolumen());
					pstUpdate.setInt(3, remolque.getId());
				}

				rowAffected = pstUpdate.executeUpdate();

				if (rowAffected == 1) {
					pstUpdate.close();
					con.commit();
				} else
					con.rollback();
			} else
				con.rollback();
		}
	}

	void eliminar(Remolque remolque) throws SQLException {
		String sentencia = Messages.getString("GestorRemolque.14"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {

			pst.setInt(1, remolque.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	private ArrayList<Remolque> getRemolques(ResultSet rs)
			throws SQLException, DatoNoValidoException, MatriculaNoValidaException {

		ArrayList<Remolque> remolques = new ArrayList<Remolque>();

		while (rs.next()) {
			String matricula = rs.getString(Messages.getString("GestorRemolque.15")); //$NON-NLS-1$

			double tara = rs.getDouble(Messages.getString("GestorRemolque.16")); //$NON-NLS-1$

			double altura = rs.getDouble(Messages.getString("GestorRemolque.17")); //$NON-NLS-1$
			double anchura = rs.getDouble(Messages.getString("GestorRemolque.18")); //$NON-NLS-1$
			double longitud = rs.getDouble(Messages.getString("GestorRemolque.19")); //$NON-NLS-1$

			DimensionesRemolque dimensiones = new DimensionesRemolque(altura, anchura, longitud);

			int ejes = rs.getInt(Messages.getString("GestorRemolque.20")); //$NON-NLS-1$
			Date fechaCompra = rs.getDate(Messages.getString("GestorRemolque.21")); //$NON-NLS-1$

			int tipo = rs.getInt(Messages.getString("GestorRemolque.22")); //$NON-NLS-1$

			boolean cinchas = rs.getBoolean(Messages.getString("GestorRemolque.23")); //$NON-NLS-1$
			boolean aperturaSuperior = rs.getBoolean(Messages.getString("GestorRemolque.24")); //$NON-NLS-1$
			boolean enganche = rs.getBoolean(Messages.getString("GestorRemolque.25")); //$NON-NLS-1$

			int capacidadPales = rs.getInt(Messages.getString("GestorRemolque.26")); //$NON-NLS-1$

			int volumen = rs.getInt(Messages.getString("GestorRemolque.27")); //$NON-NLS-1$

			Remolque remolque = construyeRemolqueTipo(matricula, tara, dimensiones, ejes, fechaCompra, tipo, cinchas,
					aperturaSuperior, enganche, capacidadPales, volumen);

			if (rs.getInt(Messages.getString("GestorRemolque.28")) == Remolque.DISPONIBLE) //$NON-NLS-1$
				remolque.setEstado(Remolque.DISPONIBLE);
			else
				remolque.setEstado(Remolque.OCUPADO);
			if (rs.getString(Messages.getString("GestorRemolque.29")) != null) //$NON-NLS-1$
				remolque.setPathRemoto(remolque.getPathRemoto());

			remolque.setId(rs.getInt(Messages.getString("GestorRemolque.30"))); //$NON-NLS-1$

			remolques.add(remolque);
		}

		return remolques;
	}

	Remolque construyeRemolqueTipo(String matricula, double tara, DimensionesRemolque dimensiones, int ejes,
			Date fechaCompra, int tipo, boolean cinchas, boolean aperturaSuperior, boolean enganche, int capacidadPales,
			int volumen) throws DatoNoValidoException, SQLException, MatriculaNoValidaException {

		switch (tipo) {
		case Remolque.TIPO_LONA:
			RemolqueLona remolqueL = new RemolqueLona(matricula, fechaCompra, tara, dimensiones);

			if (ejes > 0)
				remolqueL.setEjes(ejes);

			remolqueL.setCinchas(cinchas);
			remolqueL.setAbreArriba(aperturaSuperior);
			remolqueL.setEngancheRemolque(enganche);
			
			
			if (fechaCompra != null)
				remolqueL.setFechaCompra(fechaCompra);

			return remolqueL;
		case Remolque.TIPO_FRIGORIFICO:
			RemolqueFrigorifico remolqueF = new RemolqueFrigorifico(matricula, fechaCompra, tara, dimensiones);

			if (ejes > 0)
				remolqueF.setEjes(ejes);

			if (capacidadPales > 0)
				remolqueF.setCapacidadPalettes(capacidadPales);

			if (fechaCompra != null)
				remolqueF.setFechaCompra(fechaCompra);

			return remolqueF;
		case Remolque.TIPO_PISOMOVIL:
			RemolquePisoMovil remolqueP = new RemolquePisoMovil(matricula, fechaCompra, tara, dimensiones);

			if (ejes > 0)
				remolqueP.setEjes(ejes);

			if (volumen > 0)
				remolqueP.setVolumen(volumen);

			if (fechaCompra != null)
				remolqueP.setFechaCompra(fechaCompra);
			
			return remolqueP;
		default:
			return null;
		}
	}
}
