package bbdd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import exception.DatoNoValidoException;
import exception.MatriculaNoValidaException;
import exception.NIFNoValidoException;
import objeto.Cliente;
import objeto.DimensionesRemolque;
import objeto.Presupuesto;
import objeto.Presupuesto.Builder;
import objeto.Remolque;
import objeto.RemolqueFrigorifico;
import objeto.RemolqueLona;
import objeto.RemolquePisoMovil;
import objeto.Ubicacion;

class GestorPresupuesto {

	private GestorCliente clienteManager;
	private GestorUbicacion ubicacionManager;
	private GestorRemolque remolqueManager;

	public GestorPresupuesto(GestorCliente clienteManager, GestorUbicacion ubicacionManager,
			GestorRemolque remolqueManager) {
		this.clienteManager = clienteManager;
		this.ubicacionManager = ubicacionManager;
		this.remolqueManager = remolqueManager;
	}

	ArrayList<Presupuesto> buscar()
			throws SQLException, DatoNoValidoException, NIFNoValidoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorPresupuesto.0"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			ResultSet rs = pst.executeQuery();

			return getPresupuestos(rs);
		}
	}

	Presupuesto buscarPorId(int id)
			throws SQLException, DatoNoValidoException, NIFNoValidoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorPresupuesto.1"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			ArrayList<Presupuesto> presupuestos = getPresupuestos(rs);

			if (presupuestos.size() > 0)
				return presupuestos.get(0);

			return null;
		}
	}

	Presupuesto buscar(Ubicacion origen, Ubicacion destino, double precio, Cliente cliente)
			throws SQLException, DatoNoValidoException, NIFNoValidoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorPresupuesto.2"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, origen.getId());
			pst.setInt(2, destino.getId());
			pst.setDouble(3, precio);
			pst.setInt(4, cliente.getId());

			ResultSet rs = pst.executeQuery();

			ArrayList<Presupuesto> presupuestos = getPresupuestos(rs);

			if (presupuestos.size() > 0)
				return presupuestos.get(0);

			return null;
		}
	}

	ArrayList<Presupuesto> buscarPorCliente(int idCliente)
			throws SQLException, DatoNoValidoException, NIFNoValidoException, MatriculaNoValidaException {
		String consulta = Messages.getString("GestorPresupuesto.3"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(consulta);) {

			pst.setInt(1, idCliente);

			ResultSet rs = pst.executeQuery();

			return getPresupuestos(rs);
		}
	}

	void guardar(Presupuesto presupuesto) throws SQLException, DatoNoValidoException {
		String sentencia = Messages.getString("GestorPresupuesto.4"); //$NON-NLS-1$

		Remolque requerido = presupuesto.getRemolqueRequerido();

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {

			pst.setInt(1, presupuesto.getOrigen().getId());
			pst.setInt(2, presupuesto.getDestino().getId());
			pst.setDouble(3, presupuesto.getPrecio());
			pst.setInt(4, presupuesto.getCliente().getId());

			DimensionesRemolque dimensiones = requerido.getDimensionesRemolque();

			pst.setDouble(5, dimensiones.getAltura());
			pst.setDouble(6, dimensiones.getAnchura());
			pst.setDouble(7, dimensiones.getLongitud());

			if (presupuesto.getDistancia() > 0)
				pst.setInt(9, presupuesto.getDistancia());
			else
				pst.setNull(9, Types.INTEGER);

			if (presupuesto.getPesoCarga() > 0)
				pst.setDouble(10, presupuesto.getPesoCarga());
			else
				pst.setNull(10, Types.DOUBLE);

			if (presupuesto.getMercancia() != null)
				pst.setString(11, presupuesto.getMercancia());
			else
				pst.setNull(11, Types.VARCHAR);

			pst.setBoolean(12, false);
			pst.setBoolean(13, false);
			pst.setBoolean(14, false);

			pst.setNull(15, Types.INTEGER);

			pst.setNull(16, Types.INTEGER);

			if (requerido instanceof RemolqueLona) {
				pst.setInt(8, Remolque.TIPO_LONA);
				pst.setBoolean(12, ((RemolqueLona) requerido).tieneCinchas());
				pst.setBoolean(13, ((RemolqueLona) requerido).abreArriba());
				pst.setBoolean(14, ((RemolqueLona) requerido).tieneEngancheRemolque());
			} else if (requerido instanceof RemolqueFrigorifico) {
				pst.setInt(8, Remolque.TIPO_FRIGORIFICO);
				pst.setInt(16, ((RemolqueFrigorifico) requerido).getCapacidadPalettes());
			} else {
				pst.setInt(8, Remolque.TIPO_PISOMOVIL);
				pst.setInt(15, ((RemolquePisoMovil) requerido).getVolumen());
			}

			if (presupuesto.getPathRemoto() != null)
				pst.setString(17, presupuesto.getPathRemoto());
			else
				pst.setNull(17, Types.VARCHAR);

			ResultSet rs = null;

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1) {
				con.commit();

				int id = 0;

				rs = pst.getGeneratedKeys();

				if (rs.next())
					id = rs.getInt(1);

				presupuesto.setId(id);
			} else
				con.rollback();
		}
	}

	void modificar(Presupuesto presupuesto) throws SQLException {
		String sentencia = Messages.getString("GestorPresupuesto.5"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {

			pst.setInt(1, presupuesto.getOrigen().getId());
			pst.setInt(2, presupuesto.getDestino().getId());
			pst.setDouble(3, presupuesto.getPrecio());
			pst.setInt(4, presupuesto.getCliente().getId());

			Remolque requerido = presupuesto.getRemolqueRequerido();

			DimensionesRemolque dimensiones = requerido.getDimensionesRemolque();

			pst.setDouble(5, dimensiones.getAltura());
			pst.setDouble(6, dimensiones.getAnchura());
			pst.setDouble(7, dimensiones.getLongitud());

			if (presupuesto.getDistancia() > 0)
				pst.setInt(9, presupuesto.getDistancia());
			else
				pst.setNull(9, Types.INTEGER);

			if (presupuesto.getPesoCarga() > 0)
				pst.setDouble(10, presupuesto.getPesoCarga());
			else
				pst.setNull(10, Types.DOUBLE);

			if (presupuesto.getMercancia() != null)
				pst.setString(11, presupuesto.getMercancia());
			else
				pst.setNull(11, Types.VARCHAR);

			pst.setBoolean(12, false);
			pst.setBoolean(13, false);
			pst.setBoolean(14, false);

			if (requerido instanceof RemolqueLona) {
				pst.setInt(8, Remolque.TIPO_LONA);
				pst.setBoolean(12, ((RemolqueLona) requerido).tieneCinchas());
				pst.setBoolean(13, ((RemolqueLona) requerido).abreArriba());
				pst.setBoolean(14, ((RemolqueLona) requerido).tieneEngancheRemolque());
			} else if (requerido instanceof RemolqueFrigorifico) {
				pst.setInt(8, Remolque.TIPO_FRIGORIFICO);
				pst.setInt(16, ((RemolqueFrigorifico) requerido).getCapacidadPalettes());
			} else {
				pst.setInt(8, Remolque.TIPO_PISOMOVIL);
				pst.setInt(15, ((RemolquePisoMovil) requerido).getVolumen());
			}

			if (presupuesto.getPathRemoto() != null)
				pst.setString(17, presupuesto.getPathRemoto());
			else
				pst.setNull(17, Types.VARCHAR);

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	void eliminar(Presupuesto presupuesto) throws SQLException {
		String sentencia = Messages.getString("GestorPresupuesto.6"); //$NON-NLS-1$

		try (Connection con = DatabaseControl.getConnection();
				PreparedStatement pst = con.prepareStatement(sentencia);) {

			pst.setInt(1, presupuesto.getId());

			con.setAutoCommit(false);

			int rowAffected = pst.executeUpdate();

			if (rowAffected == 1)
				con.commit();
			else
				con.rollback();
		}
	}

	private ArrayList<Presupuesto> getPresupuestos(ResultSet rs)
			throws DatoNoValidoException, SQLException, NIFNoValidoException, MatriculaNoValidaException {

		ArrayList<Presupuesto> presupuestos = new ArrayList<Presupuesto>();

		while (rs.next()) {
			Cliente cliente = clienteManager.buscarPorId(rs.getInt(Messages.getString("GestorPresupuesto.7"))); //$NON-NLS-1$

			Ubicacion origen = ubicacionManager.buscarPorId(rs.getInt(Messages.getString("GestorPresupuesto.8"))); //$NON-NLS-1$

			Ubicacion destino = ubicacionManager.buscarPorId(rs.getInt(Messages.getString("GestorPresupuesto.9"))); //$NON-NLS-1$

			double altura = rs.getDouble(Messages.getString("GestorPresupuesto.10")); //$NON-NLS-1$

			double anchura = rs.getDouble(Messages.getString("GestorPresupuesto.11")); //$NON-NLS-1$

			double longitud = rs.getDouble(Messages.getString("GestorPresupuesto.12")); //$NON-NLS-1$

			DimensionesRemolque dimensiones = new DimensionesRemolque(altura, anchura, longitud);

			Integer distancia = rs.getInt(Messages.getString("GestorPresupuesto.13")); //$NON-NLS-1$

			Double peso = rs.getDouble(Messages.getString("GestorPresupuesto.14")); //$NON-NLS-1$

			String mercancia = rs.getString(Messages.getString("GestorPresupuesto.15")); //$NON-NLS-1$

			boolean cinchas = rs.getBoolean(Messages.getString("GestorPresupuesto.16")); //$NON-NLS-1$

			boolean aperturaSuperior = rs.getBoolean(Messages.getString("GestorPresupuesto.17")); //$NON-NLS-1$

			boolean enganche = rs.getBoolean(Messages.getString("GestorPresupuesto.18")); //$NON-NLS-1$

			int volumen = rs.getInt(Messages.getString("GestorPresupuesto.19")); //$NON-NLS-1$

			int capacidadPales = rs.getInt(Messages.getString("GestorPresupuesto.20")); //$NON-NLS-1$

			int tipo = rs.getInt(Messages.getString("GestorPresupuesto.21")); //$NON-NLS-1$

			String pathRemoto = rs.getString(Messages.getString("GestorPresupuesto.22")); //$NON-NLS-1$

			Remolque requerido = remolqueManager.construyeRemolqueTipo(Messages.getString("GestorPresupuesto.23"), 1.0, dimensiones, 0, //$NON-NLS-1$
					new Date(System.currentTimeMillis()), tipo, cinchas, aperturaSuperior, enganche, capacidadPales,
					volumen);

			Builder b = new Presupuesto.Builder(origen, destino, rs.getDouble(Messages.getString("GestorPresupuesto.24")), cliente, requerido); //$NON-NLS-1$

			if (distancia > 0 && distancia != null)
				b.distancia(distancia);

			if (peso > 0 && peso != null)
				b.pesoCarga(peso);

			if (mercancia != null)
				b.mercancia(mercancia);

			if (pathRemoto != null)
				b.pathRemoto(pathRemoto);

			b.id(rs.getInt(Messages.getString("GestorPresupuesto.25"))); //$NON-NLS-1$

			Presupuesto presupuesto = b.build();

			presupuestos.add(presupuesto);
		}

		return presupuestos;
	}
}
