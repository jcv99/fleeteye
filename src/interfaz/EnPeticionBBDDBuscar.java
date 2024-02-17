package interfaz;

import java.sql.SQLException;

import exception.CamionOcupadoException;
import exception.DatoNoValidoException;
import exception.NIFNoValidoException;
import exception.RemolqueNoCompatibleException;
import exception.RemolqueYaAsignadoException;
import exception.TrabajadorNoAsignadoException;
import exception.TrabajadorOcupadoException;
import exception.VehiculoOcupadoExcepcion;

public interface EnPeticionBBDDBuscar {
	void buscarCamiones() throws SQLException, NIFNoValidoException, DatoNoValidoException, TrabajadorOcupadoException,
			RemolqueYaAsignadoException, CamionOcupadoException;

	void buscarClientes() throws SQLException, DatoNoValidoException, NIFNoValidoException;

	void buscarEncargos() throws SQLException, TrabajadorOcupadoException, NIFNoValidoException, DatoNoValidoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorNoAsignadoException;

	void buscarEncargosPorEstado(int estado)
			throws SQLException, TrabajadorOcupadoException, NIFNoValidoException, DatoNoValidoException;

	void buscarPresupuestos() throws SQLException, DatoNoValidoException, NIFNoValidoException;

	void buscarRemolques() throws SQLException, DatoNoValidoException;

	void buscarTrabajadores() throws SQLException, NIFNoValidoException, DatoNoValidoException;

	void buscarTrabajadoresPorEstado(int estado) throws SQLException, NIFNoValidoException;

	void buscarUbicaciones() throws SQLException, DatoNoValidoException;
}
