package interfaz;

import java.sql.SQLException;

import javax.swing.JPanel;

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
import objeto.Camion;
import objeto.Cliente;
import objeto.Encargo;
import objeto.Presupuesto;
import objeto.Remolque;
import objeto.Trabajador;
import objeto.Ubicacion;

public interface EnPeticionBBDD {

	void abrirGuardarEncargoDialogoControl(Presupuesto presupuesto, JPanel origen)
			throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException;

	void actualizarCamion(Camion cam);

	void actualizarEncargo(Encargo e)
			throws SQLException, TrabajadorOcupadoException, NIFNoValidoException, DatoNoValidoException;

	void actualizarEstadoEncargo(Encargo encargo) throws SQLException;

	void actualizarEstadoRemolque(Remolque remolque) throws SQLException;

	void actualizarEstadoTrabajador(Trabajador trabajador) throws SQLException;

	void actualizarRemolque(Remolque rem);

	void actualizarTrabajador(Trabajador t) throws SQLException;

	void asignarCamionEncargo(Encargo encargo, JPanel vista)
			throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException;

	void asignarCamionParaEncargo(Encargo encargo) throws SQLException;

	void asignarRemolqueParaCamion(Camion camion) throws SQLException;

	void asignarTrabajadorParaCamion(Camion camion) throws SQLException;

	void borrarTrabajador(Trabajador trabajador) throws SQLException;

	void buscarCamiones() throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException;

	void buscarCamionParaDetalles(String id, JPanel origen)
			throws NumberFormatException, SQLException, TrabajadorOcupadoException, DatoNoValidoException,
			NIFNoValidoException, RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, TrabajadorNoAsignadoException;

	void buscarClienteParaDetalles(String id, JPanel origen)
			throws NumberFormatException, SQLException, DatoNoValidoException, NIFNoValidoException,
			TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException, VehiculoOcupadoExcepcion,
			RemolqueNoCompatibleException, TrabajadorNoAsignadoException, MatriculaNoValidaException;

	void buscarClientes() throws SQLException, DatoNoValidoException, NIFNoValidoException, MatriculaNoValidaException, TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, TrabajadorNoAsignadoException;

	void buscarEncargoParaDetalles(String id, JPanel origen)
			throws NumberFormatException, SQLException, TrabajadorOcupadoException, NIFNoValidoException,
			DatoNoValidoException, RemolqueYaAsignadoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException;

	void buscarEncargos() throws SQLException, TrabajadorOcupadoException, NIFNoValidoException, DatoNoValidoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException;

	void buscarEncargosPorCliente(Cliente cliente) throws SQLException, DatoNoValidoException, NIFNoValidoException,
			TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException, VehiculoOcupadoExcepcion,
			RemolqueNoCompatibleException, TrabajadorNoAsignadoException, MatriculaNoValidaException;

	void buscarEncargosPorEstado(int estado)
			throws SQLException, TrabajadorOcupadoException, NIFNoValidoException, DatoNoValidoException;

	void buscarEncargosPorPresupuesto() throws SQLException, TrabajadorOcupadoException, NIFNoValidoException,
			DatoNoValidoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException;

	void buscarLocalidadesUbicacion() throws SQLException;

	void buscarPaisesUbicacion() throws SQLException;

	void buscarParaGuardarPresupuesto(Cliente cliente, JPanel origen);

	void buscarPresupuestoParaDetalles(String id, JPanel origen)
			throws NumberFormatException, SQLException, DatoNoValidoException, NIFNoValidoException,
			TrabajadorOcupadoException, RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException;

	void buscarPresupuestos()
			throws SQLException, DatoNoValidoException, NIFNoValidoException, MatriculaNoValidaException;

	void buscarPresupuestosPorCliente(Cliente cliente)
			throws SQLException, DatoNoValidoException, NIFNoValidoException, MatriculaNoValidaException;

	void buscarRemolqueParaDetalles(String id, JPanel origen)
			throws NumberFormatException, SQLException, TrabajadorOcupadoException, DatoNoValidoException,
			NIFNoValidoException, RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException;

	void buscarRemolques() throws SQLException, DatoNoValidoException, MatriculaNoValidaException;

	void buscarTiposRemolque() throws SQLException;

	void buscarTrabajadores() throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException, CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException;

	void buscarTrabajadoresPorEstado(int estado) throws SQLException, NIFNoValidoException;

	void buscarTrabajadorParaDetalles(String id, JPanel origen)
			throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException, VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException, CamionOcupadoException, TrabajadorNoAsignadoException, MatriculaNoValidaException;

	void buscarUbicaciones() throws SQLException, DatoNoValidoException;

	Ubicacion buscarUbicacionPorAtributos(String direccion, String localidad, String provincia, String cp, String pais)
			throws SQLException, DatoNoValidoException;

	void cambiarEstadoRemolqueDisponible(Remolque r) throws SQLException;

	void completarEncargo(Encargo encargo) throws SQLException;

	void guardaCamion(Camion cam) throws SQLException, EntidadYaExisteException, DatoNoValidoException;

	void guardaCliente(Cliente c) throws SQLException, EntidadYaExisteException, DatoNoValidoException;

	void guardaEncargo(Encargo e) throws SQLException, TrabajadorOcupadoException, NIFNoValidoException,
			DatoNoValidoException, EntidadYaExisteException;

	void guardaPresupuesto(Presupuesto p) throws SQLException, DatoNoValidoException, EntidadYaExisteException;

	void guardaRemolque(Remolque rem) throws SQLException, EntidadYaExisteException, DatoNoValidoException;

	void guardaTrabajador(Trabajador t)
			throws SQLException, NIFNoValidoException, EntidadYaExisteException, DatoNoValidoException;

	void guardaUbicacion(Ubicacion u) throws SQLException, EntidadYaExisteException, DatoNoValidoException;

	void iniciarEncargo(Encargo encargo) throws SQLException;

	void quitarRemolqueCamion(Camion camion) throws SQLException, TrabajadorOcupadoException, DatoNoValidoException,
			NIFNoValidoException, RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException;
	
	void quitarTrabajadorParaCamion(Camion camion)
			throws SQLException, TrabajadorOcupadoException, DatoNoValidoException, NIFNoValidoException,
			RemolqueYaAsignadoException, CamionOcupadoException, MatriculaNoValidaException;
}
