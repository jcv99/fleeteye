package interfaz;

import objeto.Camion;
import objeto.Cliente;
import objeto.Encargo;
import objeto.Presupuesto;
import objeto.Remolque;
import objeto.Trabajador;
import objeto.Ubicacion;

public interface EnActualizadaBBDD {
	
	void actualizarCamion(Camion cam, boolean nuevo);
	void actualizarCliente(Cliente c, boolean nuevo);
	void actualizarEncargo(Encargo e, boolean nuevo);
	void actualizarPresupuesto(Presupuesto p, boolean nuevo);
	void actualizarRemolque(Remolque rem, boolean nuevo);
	void actualizarTrabajador(Trabajador t, boolean nuevo);
	void actualizarUbicacion(Ubicacion u, boolean nuevo);
}
