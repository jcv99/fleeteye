package test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bbdd.DatabaseControl;
import exception.CamionOcupadoException;
import exception.DatoNoValidoException;
import exception.EncargoEstadoNoValidoException;
import exception.EntidadYaExisteException;
import exception.MatriculaNoValidaException;
import exception.NIFNoValidoException;
import exception.RemolqueNoCompatibleException;
import exception.RemolqueYaAsignadoException;
import exception.TrabajadorDadoDeBajaException;
import exception.TrabajadorNoAsignadoException;
import exception.TrabajadorOcupadoException;
import exception.VehiculoOcupadoExcepcion;
import objeto.Camion;
import objeto.Cif;
import objeto.Cliente;
import objeto.DimensionesRemolque;
import objeto.Encargo;
import objeto.IdentidadPersonaFisica;
import objeto.IdentidadPersonaJuridica;
import objeto.Nif;
import objeto.Presupuesto;
import objeto.Remolque;
import objeto.RemolqueFrigorifico;
import objeto.RemolqueLona;
import objeto.RemolquePisoMovil;
import objeto.Trabajador;
import objeto.Ubicacion;

class ModelTests {

	private static DatabaseControl dbm = new DatabaseControl();

	//	@Test
//	void basicoTrabajador() throws NIFNoValidoException, DatoNoValidoException, SQLException, EntidadYaExisteException,
//			EntidadNoExisteException, RemolqueYaAsignadoException, CamionOcupadoException, TrabajadorOcupadoException {
//		// Crear 4 trabajadores, dos con cada identidad.
//		Trabajador t1 = new Trabajador(new IdentidadPersonaFisica("54175092H", "Javi", "Cipote", "pote", "Espa�ola",
//				Date.valueOf("1980-10-06")));
//		Trabajador t2 = new Trabajador(new IdentidadPersonaFisica("17086615F", "Aleix", "Cipote", "pote", "Espa�ola",
//				Date.valueOf("1980-09-06")));
//
//		t1.setPathRemotoContrato("holaSoyUnPathRemoto");
//		dbm.guardarTrabajador(t1);
//		t1 = dbm.buscarTrabajadorPorNif("54175092H");
//		System.out.println(t1.getPathRemotoContrato());
//		dbm.guardarTrabajador(t2);
//
//		assertNotNull(t1.getId());
//		assertNotNull(t2.getId());
//
//		Trabajador t3 = new Trabajador(new IdentidadPersonaFisica("17086615F", "Aleix", "Cipote", "pote", "Espa�ola",
//				Date.valueOf("1980-09-06")));
//
//		try {
//			dbm.guardarTrabajador(t3);
//		} catch (EntidadYaExisteException e) {
//			System.out.println("No ha dejado crear uno igual, todo en orden\n");
//		}
//
//		// Buscar y asegurar que estan los trabajadores en la bbdd.
//		// por identidad. ( de a 1 )
//
//		t1.setEstadoOcupado();
//		dbm.actualizarEstadoTrabajador(t1);
//
//		ArrayList<Trabajador> trabajadores;
//
//		// por estado
//
//		trabajadores = dbm.buscarTrabajadores();
//
//		assertTrue(trabajadores.size() == 2);
//
//		trabajadores = null;
//
//		trabajadores = dbm.buscarTrabajadoresPorEstado(Trabajador.DISPONIBLE);
//
//		assertTrue(trabajadores.size() == 1);
//		assertTrue(trabajadores.get(0).getId() == t2.getId());
//
//		trabajadores = null;
//
//		trabajadores = dbm.buscarTrabajadoresPorEstado(Trabajador.OCUPADO);
//
//		assertTrue(trabajadores.size() == 1);
//		assertTrue(trabajadores.get(0).getId() == t1.getId());
//
//		// Borrar los 2 trabajadores.
//
//		dbm.eliminarTrabajador(t1);
//		dbm.eliminarTrabajador(t2);
//
//		// Crearlos de nuevo exactamente iguales.
//
//		t1 = new Trabajador(new IdentidadPersonaFisica("54175092H", "Javi", "Cipote", "pote", "Espa�ola",
//				Date.valueOf("1980-10-06")));
//		t2 = new Trabajador(new IdentidadPersonaFisica("17086615F", "Aleix", "Cipote", "pote", "Espa�ola",
//				Date.valueOf("1980-09-06")));
//
//		dbm.guardarTrabajador(t1);
//		dbm.guardarTrabajador(t2);
//
//		// Borrarlo todo.
//
//		dbm.eliminarTrabajador(t1);
//		dbm.eliminarTrabajador(t2);
//		
//
//	}
//
//	@Test
//	void basicoCliente() throws NIFNoValidoException, DatoNoValidoException, SQLException, EntidadYaExisteException,
//			EntidadNoExisteException {
//		// Crear 4 clientes, dos con cada identidad.
//
//		Cliente c1 = new Cliente(new IdentidadPersonaJuridica("C24460271", "paco sl", "vendo castanyas"));
//
//		dbm.guardarCliente(c1);
//		assertNotNull(c1.getId());
//		System.out.println(c1.getId());
//
//		Cliente c2 = new Cliente(new IdentidadPersonaJuridica("J85908465", "zas sl", "vendo boniatos"));
//		dbm.guardarCliente(c2);
//		assertNotNull(c2.getId());
//
//		Cliente c3 = new Cliente(new IdentidadPersonaJuridica("J85908465", "zas sl", "vendo boniatos"));
//
//		try {
//			dbm.guardarCliente(c3);
//		} catch (EntidadYaExisteException e) {
//			System.out.println("No ha dejado crear uno igual, todo en orden\n");
//		}
//		assertNotNull(c2.getId());
//
//		// Buscar y asegurar que estan los clientes en la bbdd.
//		// por identidad. ( de a 1 )
//
//		c1 = null;
//		int idC2 = c2.getId();
//		c2 = null;
//
//		c1 = dbm.buscarClientePorNif("C24460271");
//		c2 = dbm.buscarClientePorId(idC2);
//
//		assertNotNull(c1);
//		assertNotNull(c2);
//
//		// Borrar los 2 clientes.
//
//		dbm.eliminarCliente(c1);
//		dbm.eliminarCliente(c2);
//
//		// Asegurar que no estan en la base de datos.
//		c1 = null;
//		c2 = null;
//
//		// Crearlos de nuevo exactamente iguales.
//
//		c1 = new Cliente(new IdentidadPersonaJuridica("C24460271", "paco sl", "vendo castanyas"));
//		c2 = new Cliente(new IdentidadPersonaJuridica("J85908465", "zas sl", "vendo boniatos"));
//		dbm.guardarCliente(c1);
//		dbm.guardarCliente(c2);
//
//		// Borrarlo todo.
//
//		dbm.eliminarCliente(c1);
//		dbm.eliminarCliente(c2);
//
//	}
//
//	@Test
//	void basicoUbicacion()
//			throws SQLException, EntidadYaExisteException, DatoNoValidoException, EntidadNoExisteException {
//
//		// Crear 2 ubicaciones.
//
//		Ubicacion u1 = new Ubicacion("Calle piruleta", "sntk", "bcn", "00032", "Espa�a");
//
//		dbm.guardarUbicacion(u1);
//		assertNotNull(u1.getId());
//
//		Ubicacion u2 = new Ubicacion("Calle pradera 44", "olesa", "bcn", "00050", "Espa�a");
//		dbm.guardarUbicacion(u2);
//		assertNotNull(u2.getId());
//
//		// Tratar de crear 1 exactamente igual. Lidiar con la excepcion.
//
//		Ubicacion u3 = new Ubicacion("Calle pradera 44", "tacataca", "weedland", "00050", "Francia");
//
//		try {
//			dbm.guardarUbicacion(u3);
//		} catch (EntidadYaExisteException e) {
//			System.out.println("No ha dejado crear uno igual, todo en orden\n");
//		}
//
//		// Buscarlas.
//
//		int idU1 = u1.getId();
//		int idU2 = u2.getId();
//
//		u1 = null;
//		u2 = null;
//
//		u1 = dbm.buscarUbicacionPorId(idU1);
//		u2 = dbm.buscarUbicacionPorId(idU2);
//
//		// Para mirar que todos los componentes estan correctamente situados.
//		u1.toString();
//
//		// Eliminarlas.
//
//		dbm.eliminarUbicacion(u1);
//		dbm.eliminarUbicacion(u2);
//		// Buscarlas.
//
//		u1 = null;
//		u2 = null;
//
//		u1 = dbm.buscarUbicacionPorId(idU1);
//		u2 = dbm.buscarUbicacionPorId(idU2);
//
//		assertNull(u1);
//		assertNull(u2);
//
//		// Crearlas exactamente iguales.
//
//		u1 = new Ubicacion("Calle piruleta", "sntk", "bcn", "00032", "Espa�a");
//
//		dbm.guardarUbicacion(u1);
//		assertNotNull(u1.getId());
//
//		u2 = new Ubicacion("Calle pradera 44", "olesa", "bcn", "00050", "Espa�a");
//		dbm.guardarUbicacion(u2);
//		assertNotNull(u2.getId());
//
//		// Borrarlo todo.
//
//		dbm.eliminarUbicacion(u1);
//		dbm.eliminarUbicacion(u2);
//
//	}
//
//	@Test
//	void basicoRemolque()
//			throws DatoNoValidoException, EntidadNoExisteException, SQLException, EntidadYaExisteException {
//		// Crear dos remolques de cada tipo.
//		RemolqueFrigorifico frigo = new RemolqueFrigorifico("matricula",
//				new Date(Calendar.getInstance().getTime().getTime()), 2000, new DimensionesRemolque(4, 2, 3));
//
//		RemolqueLona lona = new RemolqueLona("matLona", new Date(Calendar.getInstance().getTime().getTime()), 2200,
//				new DimensionesRemolque(3, 3, 1));
//
//		RemolquePisoMovil pisoMovil = new RemolquePisoMovil("matMovil",
//				new Date(Calendar.getInstance().getTime().getTime()), 2500, new DimensionesRemolque(2, 2, 2));
//
//		RemolqueFrigorifico frigo2 = new RemolqueFrigorifico("matfrigo2",
//				new Date(Calendar.getInstance().getTime().getTime()), 2000, new DimensionesRemolque(4, 2, 3));
//
//		RemolqueLona lona2 = new RemolqueLona("matLona2", new Date(Calendar.getInstance().getTime().getTime()), 2200,
//				new DimensionesRemolque(3, 3, 1));
//
//		RemolquePisoMovil pisoMovil2 = new RemolquePisoMovil("matMovil2",
//				new Date(Calendar.getInstance().getTime().getTime()), 2500, new DimensionesRemolque(2, 2, 2));
//
//		// Guardarlos, recuperarlos.
//
//		dbm.guardarRemolque(frigo);
//		dbm.guardarRemolque(frigo2);
//		dbm.guardarRemolque(lona);
//		dbm.guardarRemolque(lona2);
//		dbm.guardarRemolque(pisoMovil);
//		dbm.guardarRemolque(pisoMovil2);
//
//		int idFrigo = frigo.getId();
//		int idFrigo2 = frigo2.getId();
//		int idLona = lona.getId();
//		int idLona2 = lona2.getId();
//		int idPisoMovil = pisoMovil.getId();
//		int idPisoMovil2 = pisoMovil2.getId();
//
//		assertNotNull(idFrigo);
//		assertNotNull(idFrigo2);
//		assertNotNull(idLona);
//		assertNotNull(idLona2);
//		assertNotNull(idPisoMovil);
//		assertNotNull(idPisoMovil2);
//
//		frigo = null;
//		frigo2 = null;
//		lona = null;
//		lona2 = null;
//		pisoMovil = null;
//		pisoMovil2 = null;
//
//		frigo = (RemolqueFrigorifico) dbm.buscarRemolquePorId(idFrigo);
//		frigo2 = (RemolqueFrigorifico) dbm.buscarRemolquePorId(idFrigo2);
//
//		lona = (RemolqueLona) dbm.buscarRemolquePorId(idLona);
//		lona2 = (RemolqueLona) dbm.buscarRemolquePorId(idLona2);
//
//		pisoMovil = (RemolquePisoMovil) dbm.buscarRemolquePorId(idPisoMovil);
//		pisoMovil2 = (RemolquePisoMovil) dbm.buscarRemolquePorId(idPisoMovil2);
//
//		assertNotNull(frigo);
//		assertNotNull(frigo2);
//		assertNotNull(lona);
//		assertNotNull(lona2);
//		assertNotNull(pisoMovil);
//		assertNotNull(pisoMovil2);
//		// Tratar de Crear uno con matricula ya existente.
//
//		RemolqueFrigorifico frigo3 = new RemolqueFrigorifico("matricula",
//				new Date(Calendar.getInstance().getTime().getTime()), 2111, new DimensionesRemolque(5, 2, 3));
//
//		try {
//			dbm.guardarRemolque(frigo3);
//		} catch (EntidadYaExisteException e) {
//			System.out.println("No ha dejado, tal y como deberia");
//		}
//		// Borrarlo todo.
//
//		dbm.eliminarRemolque(frigo);
//		dbm.eliminarRemolque(frigo2);
//		dbm.eliminarRemolque(lona);
//		dbm.eliminarRemolque(lona2);
//		dbm.eliminarRemolque(pisoMovil);
//		dbm.eliminarRemolque(pisoMovil2);
//
//		frigo = null;
//		frigo2 = null;
//		lona = null;
//		lona2 = null;
//		pisoMovil = null;
//		pisoMovil2 = null;
//
//		frigo = (RemolqueFrigorifico) dbm.buscarRemolquePorId(idFrigo);
//		lona2 = (RemolqueLona) dbm.buscarRemolquePorId(idLona2);
//
//		assertNull(frigo);
//		assertNull(frigo2);
//
//		// Falta buscar remolques por tipo.
//	}
//
//	@Test
//	void basicoCamion()
//			throws DatoNoValidoException, RemolqueYaAsignadoException, CamionOcupadoException, EntidadYaExisteException,
//			SQLException, TrabajadorOcupadoException, NIFNoValidoException, EntidadNoExisteException {
//		Camion c = new Camion.Builder("maila", 3000, "marca", "modelo").build();
//		Camion c2 = new Camion.Builder("matla3", 300, "marca3", "modelo3").build();
//		Camion c3 = new Camion.Builder("maiula3", 300, "marca3", "modelo3").build();
//		RemolqueFrigorifico frigo = new RemolqueFrigorifico("matricula",
//				new Date(Calendar.getInstance().getTime().getTime()), 2000, new DimensionesRemolque(4, 2, 3));
//		RemolqueLona lona = new RemolqueLona("matLona", new Date(Calendar.getInstance().getTime().getTime()), 2200,
//				new DimensionesRemolque(3, 3, 1));
//		RemolquePisoMovil pisoMovil = new RemolquePisoMovil("matMovil",
//				new Date(Calendar.getInstance().getTime().getTime()), 2500, new DimensionesRemolque(2, 2, 2));
//		c.asignRemolque(frigo);
//		dbm.guardarRemolque(pisoMovil);
//		int idMovil = pisoMovil.getId();
//		try {
//			c2.asignRemolque(frigo);
//		} catch (RemolqueYaAsignadoException e) {
//			System.out.println("No ha dejado, todo en orden.");
//		}
//
//		try {
//			c.asignRemolque(lona);
//		}
//
//		catch (CamionOcupadoException e) {
//			System.out.println("No ha dejado, todo en orden.");
//		}
//
//		c2.asignRemolque(lona);
//		assertFalse(frigo.esDisponible());
//		assertFalse(lona.esDisponible());
//
//		dbm.guardarCamion(c);
//		dbm.guardarCamion(c2);
//		c3.asignRemolque(pisoMovil);
//		dbm.guardarCamion(c3);
//		// Al guardar un camion con un remolque, si el remolque no existia en la base de
//		// datos, lo crea. Y si ya existia, deberia NO crearlo.
//		// Si salta excepcion probablemente es que el remolque ya existe.
//		assertNotNull(c.getId());
//		assertNotNull(c2.getId());
//		assertNotNull(frigo.getId());
//		assertNotNull(lona.getId());
//		assertNotNull(c3.getId());
//		// Esta comprobacion mira que no se le haya cambiado al id al tratar de guardar
//		// un remolque ya existente o algo asi.
//		assertEquals(idMovil, pisoMovil.getId());
//
//		// Ahora tratamos de guardar un camion ya existente.
//
//		try {
//			dbm.guardarCamion(c2);
//		}
//
//		catch (EntidadYaExisteException e) {
//			System.out.println("No ha dejado crearlo por repetido, alright");
//		}
//
//		// Dejamos los camiones a null y tratamos de recuperarlos de la bbdd
//
//		int idC = c.getId();
//		int idC2 = c2.getId();
//		int idC3 = c3.getId();
//
//		c = null;
//		c2 = null;
//		c3 = null;
//
//		c = dbm.buscarCamionPorId(idC);
//		c2 = dbm.buscarCamionPorId(idC2);
//		c3 = dbm.buscarCamionPorId(idC3);
//
//		assertNotNull(c.getId());
//		assertNotNull(c2.getId());
//		assertNotNull(c3.getId());
//
//		// Miramos que esten completos.
//		System.out.println(c.toString());
//
//		ArrayList<Camion> camiones = dbm.buscarCamiones();
//
//		assertTrue(camiones.size() == 3);
//
//		for (int i = 0; i < camiones.size(); i++) {
//			System.out.println(camiones.get(i).toString());
//		}
//
//		// Los borramos todos.
//
//		dbm.eliminarCamion(c);
//		dbm.eliminarCamion(c2);
//		dbm.eliminarCamion(c3);
//
//		// Tratamos de volver a guardarlos, si no se hubiesen eliminado bien, daria
//		// error.
//
//		dbm.guardarCamion(c3);
//		dbm.guardarCamion(c);
//		dbm.guardarCamion(c2);
//
//		// Miramos que no tenga la misma id que anteriormente
//
//		assertNotEquals(idC, c.getId());
//		assertNotEquals(idC2, c2.getId());
//		assertNotEquals(idC3, c3.getId());
//
//		// Limpiamos
//		dbm.eliminarCamion(c);
//		dbm.eliminarCamion(c2);
//		dbm.eliminarCamion(c3);
//
//	}
//	
//	
//	@Test
//	void basicoPresupuesto() throws DatoNoValidoException, SQLException, EntidadYaExisteException, NIFNoValidoException,
//			EntidadNoExisteException {
//
//		Ubicacion u1 = new Ubicacion("Calle piruleta", "sntk", "bcn", "00032", "Espa�a");
//		dbm.guardarUbicacion(u1);
//
//		Ubicacion u2 = new Ubicacion("Calle pradera 44", "olesa", "bcn", "00050", "Espa�a");
//		dbm.guardarUbicacion(u2);
//
//		Cliente c1 = new Cliente(new IdentidadPersonaJuridica("C24460271", "paco sl", "vendo castanyas"));
//		dbm.guardarCliente(c1);
//
//		RemolqueLona rLona = RemolqueLona.remolqueNoRequerido();
//
//		RemolqueLona rLonaConDatos = RemolqueLona.remolqueRequerido(new DimensionesRemolque(2.7, 2.48, 13.6), true,
//				true, true);
//
//		RemolqueFrigorifico rFrigo = RemolqueFrigorifico.remolqueRequerido(new DimensionesRemolque(2.7, 2.48, 13.6),
//				25);
//
//		RemolquePisoMovil rPisoMovil = RemolquePisoMovil.remolqueRequerido(new DimensionesRemolque(2.7, 2.48, 13.6),
//				213);
//
//		Presupuesto p1 = new Presupuesto.Builder(u1, u2, 100, c1, rLona).build();
//		dbm.guardarPresupuesto(p1);
//
//		Presupuesto p2 = new Presupuesto.Builder(u2, u1, 250, c1, rLonaConDatos).build();
//		dbm.guardarPresupuesto(p2);
//
//		Presupuesto p3 = new Presupuesto.Builder(u2, u1, 250, c1, rFrigo).build();
//		dbm.guardarPresupuesto(p3);
//
//		Presupuesto p4 = new Presupuesto.Builder(u2, u1, 250, c1, rPisoMovil).build();
//		dbm.guardarPresupuesto(p4);
//
//		assertNotNull(p1.getId());
//		assertNotNull(p2.getId());
//		assertNotNull(p3.getId());
//		assertNotNull(p4.getId());
//
//		// Tratamos de guardar uno igual
//
//		try {
//			dbm.guardarPresupuesto(p4);
//		} catch (EntidadYaExisteException e) {
//			System.out.println("No ha dejado, todo en orden");
//		}
//
//		// Buscamos los presupuestos en la bbdd
//
//		int idP1 = p1.getId();
//		int idP2 = p2.getId();
//		int idP3 = p3.getId();
//		int idP4 = p4.getId();
//
//		p1 = null;
//		p2 = null;
//		p3 = null;
//		p4 = null;
//
//		p1 = dbm.buscarPresupuestoPorId(idP1);
//		p2 = dbm.buscarPresupuestoPorId(idP2);
//		p3 = dbm.buscarPresupuestoPorId(idP3);
//		p4 = dbm.buscarPresupuestoPorId(idP4);
//
//		assertNotNull(p1.getId());
//		assertNotNull(p2.getId());
//		assertNotNull(p3.getId());
//		assertNotNull(p4.getId());
//
//		ArrayList<Presupuesto> presupuestos = dbm.buscarPresupuestos();
//
//		assertTrue(presupuestos.size() == 4);
//
//		// Hay que asegurarse de que devuelve los items correctamente.
//
//		for (int i = 0; i < presupuestos.size(); i++) {
//			System.out.println(presupuestos.get(i).toString());
//		}
//
//		// eliminamos todos los presupuestos.
//
//		dbm.eliminarPresupuesto(p1);
//		dbm.eliminarPresupuesto(p2);
//		dbm.eliminarPresupuesto(p3);
//		dbm.eliminarPresupuesto(p4);
//
//		// Tratamos de guardarlos de nuevo
//
//		dbm.guardarPresupuesto(p1);
//		dbm.guardarPresupuesto(p2);
//		dbm.guardarPresupuesto(p3);
//		dbm.guardarPresupuesto(p4);
//
//		// Miramos que no tenga la misma id que anteriormente
//
//		assertNotEquals(idP1, p1.getId());
//		assertNotEquals(idP2, p2.getId());
//		assertNotEquals(idP3, p3.getId());
//		assertNotEquals(idP4, p4.getId());
//
//		// Limpiamos
//
//		dbm.eliminarPresupuesto(p1);
//		dbm.eliminarPresupuesto(p2);
//		dbm.eliminarPresupuesto(p3);
//		dbm.eliminarPresupuesto(p4);
//
//	}
//
//	
	@Test
	void basicoEncargo() throws DatoNoValidoException, SQLException, EntidadYaExisteException, NIFNoValidoException,
			VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, RemolqueYaAsignadoException,
			CamionOcupadoException, TrabajadorOcupadoException, TrabajadorNoAsignadoException,
			TrabajadorDadoDeBajaException, EncargoEstadoNoValidoException, MatriculaNoValidaException {

		Ubicacion u1 = new Ubicacion("Calle piruleta", "sntk", "bcn", "00032", "Espa�a");
		dbm.guardarUbicacion(u1);

		Ubicacion u2 = new Ubicacion("Calle pradera 44", "olesa", "bcn", "00050", "Espa�a");
		dbm.guardarUbicacion(u2);

		Cliente c1 = new Cliente(new IdentidadPersonaJuridica(new Cif("C24460271"), "paco sl", "vendo castanyas"));
		dbm.guardarCliente(c1);

		RemolqueLona rLona = RemolqueLona.remolqueNoRequerido();

		RemolqueLona rLonaConDatos = RemolqueLona.remolqueRequerido(new DimensionesRemolque(2.7, 2.48, 13.6), true,
				true, true);

		RemolqueFrigorifico rFrigo = RemolqueFrigorifico.remolqueRequerido(new DimensionesRemolque(2.7, 2.48, 13.6),
				25);

		RemolquePisoMovil rPisoMovil = RemolquePisoMovil.remolqueRequerido(new DimensionesRemolque(2.7, 2.48, 13.6),
				213);

		Presupuesto p1 = new Presupuesto.Builder(u1, u2, 100, c1, rLona).build();
		dbm.guardarPresupuesto(p1);

		Presupuesto p2 = new Presupuesto.Builder(u2, u1, 250, c1, rLonaConDatos).build();
		dbm.guardarPresupuesto(p2);

		Presupuesto p3 = new Presupuesto.Builder(u2, u1, 250, c1, rFrigo).build();
		dbm.guardarPresupuesto(p3);

		Presupuesto p4 = new Presupuesto.Builder(u2, u1, 250, c1, rPisoMovil).build();
		dbm.guardarPresupuesto(p4);

		Camion c = new Camion.Builder("mtcula", 3000, "marca", "modelo").build();
		Camion c2 = new Camion.Builder("maiula2", 300, "marca2", "modelo2").build();
		Camion c3 = new Camion.Builder("maiula3", 300, "marca3", "modelo3").build();

		RemolqueFrigorifico frigo = new RemolqueFrigorifico("matricula",
				new Date(Calendar.getInstance().getTime().getTime()), 2000, new DimensionesRemolque(4, 3, 18));

		dbm.guardarRemolque(frigo);

		RemolqueLona lona = new RemolqueLona("matLona", new Date(Calendar.getInstance().getTime().getTime()), 2200,
				new DimensionesRemolque(3, 3, 20));

		lona.setAbreArriba(true);
		lona.setCinchas(true);
		lona.setEngancheRemolque(true);

		dbm.guardarRemolque(lona);

		RemolquePisoMovil pisoMovil = new RemolquePisoMovil("matMovil",
				new Date(Calendar.getInstance().getTime().getTime()), 2500, new DimensionesRemolque(3.0, 3.0, 18.0));

		dbm.guardarRemolque(pisoMovil);

		Encargo e1 = new Encargo.Builder(p1).build();

		// Camion sin tractora ???
		try {
			Encargo e2 = new Encargo.Builder(p2).tractora(c).build();
		}

		catch (RemolqueNoCompatibleException e) {
			System.out.println("No ha dejado, todo en orden");
		}

		// Asignamos los camiones con las tractores y probamos de meterle remolques no
		// compatibles y camiones ocupados.

		// OJITO CON EL VOLUMEN QUE NO ENTRA DESDE CONSTRUCTOR.
		// LOS PALETTES TAMPOCO ENTRAN DESDE CONSTRUCTOR
		frigo.setCapacidadPalettes(100);
		pisoMovil.setVolumen(300);

		dbm.guardarCamion(c);
		dbm.guardarCamion(c2);
		dbm.guardarCamion(c3);

		c.asignRemolque(frigo);
		c2.asignRemolque(lona);
		c3.asignRemolque(pisoMovil);

		dbm.asignarRemolqueCamion(c);
		dbm.asignarRemolqueCamion(c2);
		dbm.asignarRemolqueCamion(c3);

		Trabajador t1 = new Trabajador(new IdentidadPersonaFisica(new Nif("54175092H"), "Javi", "Cipote", "pote",
				"Espa�ola", Date.valueOf("1980-10-06")));
		Trabajador t2 = new Trabajador(new IdentidadPersonaFisica(new Nif("17086615F"), "Aleix", "Cipote", "pote",
				"Espa�ola", Date.valueOf("1980-09-06")));
		Trabajador t3 = new Trabajador(new IdentidadPersonaFisica(new Nif("171235F"), "jaja", "Cipote", "pote",
				"Espa�ola", Date.valueOf("1980-09-06")));

		dbm.guardarTrabajador(t1);
		dbm.guardarTrabajador(t2);
		dbm.guardarTrabajador(t3);

		c.trabajador(t1);
		c2.trabajador(t2);
		c3.trabajador(t3);

		dbm.asignarTrabajadorCamion(c);
		dbm.asignarTrabajadorCamion(c2);
		dbm.asignarTrabajadorCamion(c3);

		try {
			Encargo e2 = new Encargo.Builder(p2).tractora(c).build();
		}

		catch (RemolqueNoCompatibleException e) {
			System.out.println("No ha dejado, todo en orden");
		}

		System.out.println(p2.getRemolqueRequerido().toString());
		System.out.println(c2.getRemolque().toString());
		System.out.println(c2.esOcupado());
		Encargo e2 = new Encargo.Builder(p2).tractora(c2).build();

		try {
			Encargo e3 = new Encargo.Builder(p2).tractora(c2).build();
		}

		catch (VehiculoOcupadoExcepcion e) {
			System.out.println("El camion esta ocupado, no ha dejado, todo en orden");
		}

		// Vamos a cambiar las dimensiones requeridas del remolque para que no cuadren,
		// y gestionar la excepcion

		p4.getRemolqueRequerido().setDimensionesRemolque(new DimensionesRemolque(5, 5, 2));
		try {
			Encargo e3 = new Encargo.Builder(p4).tractora(c3).build();
		}

		catch (RemolqueNoCompatibleException e) {
			System.out.println(p4.getRemolqueRequerido().getDimensionesRemolque().toString());
			System.out.println(c3.getRemolque().getDimensionesRemolque().toString());
			System.out.println("No deja porque las medidas no son compatibles");
		}

		// cinchas es tema de true o false. Lo apartamos de momento.

		p4.getRemolqueRequerido().setDimensionesRemolque(new DimensionesRemolque(1, 1, 1));

		Encargo e3 = new Encargo.Builder(p4).tractora(c3).build();

		Encargo e4 = new Encargo.Builder(p3).tractora(c).build();

		dbm.guardarEncargo(e1);
		dbm.guardarEncargo(e2);
		dbm.guardarEncargo(e3);
		dbm.guardarEncargo(e4);

		assertNotNull(e1.getId());
		assertNotNull(e2.getId());
		assertNotNull(e3.getId());
		assertNotNull(e4.getId());

		int idE1 = e1.getId();
		int idE2 = e2.getId();
		int idE3 = e3.getId();
		int idE4 = e4.getId();

		e1 = null;
		e2 = null;
		e3 = null;
		e4 = null;

		e1 = dbm.buscarEncargoPorId(idE1);
		e2 = dbm.buscarEncargoPorId(idE2);
		e3 = dbm.buscarEncargoPorId(idE3);
		e4 = dbm.buscarEncargoPorId(idE4);

		assertNotNull(e1.getId());
		assertNotNull(e2.getId());
		assertNotNull(e3.getId());
		assertNotNull(e4.getId());

		dbm.eliminarEncargo(e1);
		dbm.eliminarEncargo(e2);
		dbm.eliminarEncargo(e3);
		dbm.eliminarEncargo(e4);

		// Tratamos de guardar encargos previamente eliminados, para asegurarnos que se
		// han borrado correctamente.

		dbm.guardarEncargo(e1);
		dbm.guardarEncargo(e2);
		dbm.guardarEncargo(e3);
		dbm.guardarEncargo(e4);

		assertNotEquals(idE1, e1.getId());
		assertNotEquals(idE2, e2.getId());
		assertNotEquals(idE3, e3.getId());
		assertNotEquals(idE4, e4.getId());

		// NO miramos lo que pasa si tratas de completar un encargo NO en curso o cosas
		// asi porque va a ser imposible desde la gui.

		e2.inicializar();
		e3.inicializar();
		e4.inicializar();

		dbm.iniciarEncargo(e2);
		dbm.iniciarEncargo(e3);
		dbm.iniciarEncargo(e4);

		e2.completarEncargo();
		e3.completarEncargo();
		e4.completarEncargo();

		dbm.completarEncargo(e2);
		dbm.completarEncargo(e3);
		dbm.completarEncargo(e4);

		ArrayList<Encargo> encargosCompletados = dbm.buscarEncargosCompletados();
		System.out.println();

		assertTrue(encargosCompletados.size() == 3);

		assertTrue(dbm.buscarEncargosPorCliente(c1.getId()).size() == 4);

		assertTrue(dbm.buscarEncargosPorTrabajador(t1.getId()).size() == 1);

		assertTrue(dbm.buscarPresupuestosPorCliente(c1.getId()).size() == 4);

		assertTrue(dbm.buscarEncargosPorCamion(c2.getId()).size() == 1);

		ArrayList<Encargo> encargos = new ArrayList<Encargo>();

		encargos.addAll(dbm.buscarEncargos());

		encargos.addAll(dbm.buscarEncargosCompletados());

		assertTrue(encargos.size() == 4);

		// Limpiamos

		dbm.eliminarEncargo(e1);
		dbm.eliminarEncargo(e2);
		dbm.eliminarEncargo(e3);
		dbm.eliminarEncargo(e4);

	}

	Connection conecta() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mysql://labs.iam.cat/a18danbargar_prototipo3?serverTimezone=" + TimeZone.getDefault().getID(),
				"a18danbargar_1", "ausias");
	}

	public void insertaDatosPrueba() throws NIFNoValidoException, DatoNoValidoException, EntidadYaExisteException,
			SQLException, MatriculaNoValidaException {
		// rellena de cosas
		Trabajador t1 = new Trabajador(new IdentidadPersonaFisica(new Nif("54175092H"), "Javi", "Ciruela", "pote",
				"Española", Date.valueOf("1980-10-06")));
		Trabajador t2 = new Trabajador(new IdentidadPersonaFisica(new Nif("17086615F"), "Aleix", "Barrera", "pote",
				"Española", Date.valueOf("1980-09-06")));
		Trabajador t3 = new Trabajador(new IdentidadPersonaFisica(new Nif("47131725X"), "Jordi", "Casals", "pote",
				"Española", Date.valueOf("1990-09-06")));

		Cliente c1 = new Cliente(new IdentidadPersonaJuridica(new Cif("54175092H"), "paco sl", "vendo castanyas"));
		Cliente c2 = new Cliente(new IdentidadPersonaJuridica(new Cif("47131725X"), "zas sl", "vendo boniatos"));
		Cliente c3 = new Cliente(new IdentidadPersonaJuridica(new Cif("17086615F"), "Pote sl", "vendo patatas"));

		Ubicacion u1 = new Ubicacion("Calle1", "sntk", "bcn", "00032", "España");
		Ubicacion u2 = new Ubicacion("Calle2", "Castilla", "bcn", "08888", "España");
		Ubicacion u3 = new Ubicacion("3Calholale", "sntk", "bcn", "00032", "Francia");
		Ubicacion u4 = new Ubicacion("Calle1", "sntk", "bcn", "01324", "España");
		Ubicacion u5 = new Ubicacion("Calle1", "sntk", "bcn", "00032", "Francia");

		Remolque r = new RemolqueFrigorifico("matricula", Date.valueOf("1970-01-01"), 40000,
				new DimensionesRemolque(3.0, 2.1, 7.8));

		Presupuesto p1 = new Presupuesto.Builder(u1, u2, 25.36, c1, r).build();

		dbm.guardarTrabajador(t1);
		dbm.guardarTrabajador(t2);
		dbm.guardarTrabajador(t3);

		dbm.guardarCliente(c1);
		dbm.guardarCliente(c2);
		dbm.guardarCliente(c3);

		dbm.guardarUbicacion(u1);
		dbm.guardarUbicacion(u2);
		dbm.guardarUbicacion(u3);
		dbm.guardarUbicacion(u4);
		dbm.guardarUbicacion(u5);

		dbm.guardarPresupuesto(p1);

	}

	void reiniciarDB(boolean seed) throws SQLException, MatriculaNoValidaException {
		try (Connection con = conecta(); Statement st = con.createStatement();) {

			con.setAutoCommit(false);

			// Eliminación de las tablas anteriores

			st.execute("DROP TABLE IF EXISTS historico_encargo");

			st.execute("DROP TABLE IF EXISTS encargo");

			st.execute("DROP TABLE IF EXISTS presupuesto");

			st.execute("DROP TABLE IF EXISTS camion");

			st.execute("DROP TABLE IF EXISTS remolque");

			st.execute("DROP TABLE IF EXISTS trabajador");

			st.execute("DROP TABLE IF EXISTS tipo_remolque");

			st.execute("DROP TABLE IF EXISTS ubicacion");

			st.execute("DROP TABLE IF EXISTS cliente");

			st.execute("DROP TABLE IF EXISTS usuario");

			// Creación de tablas

			st.execute(
					"CREATE TABLE usuario (id INT NOT NULL AUTO_INCREMENT, nombre VARCHAR(20) UNIQUE NOT NULL, contrasena VARCHAR(32) UNIQUE NOT NULL, PRIMARY KEY (id))");

			st.execute(
					"CREATE TABLE cliente (id INT NOT NULL AUTO_INCREMENT, nif VARCHAR(9) UNIQUE NOT NULL, razon_social VARCHAR(100) UNIQUE NOT NULL, actividad_economica VARCHAR(100) NOT NULL, ruta_documento VARCHAR(255), PRIMARY KEY (id))");

			st.execute(
					"CREATE TABLE ubicacion (id int NOT NULL AUTO_INCREMENT, direccion VARCHAR(50) NOT NULL, localidad VARCHAR(50) NOT NULL, provincia VARCHAR(50) NOT NULL, cp VARCHAR(10) NOT NULL, pais VARCHAR(30) NOT NULL, PRIMARY KEY (id))");

			st.execute(
					"CREATE TABLE tipo_remolque (id INT NOT NULL AUTO_INCREMENT, tipo VARCHAR(20) UNIQUE NOT NULL, PRIMARY KEY (id))");

			st.execute(
					"CREATE TABLE presupuesto (id INT NOT NULL AUTO_INCREMENT, origen INT NOT NULL, destino INT NOT NULL, precio DECIMAL(7,2) NOT NULL, cliente INT NOT NULL, altura_req DECIMAL(5, 2) NOT NULL, anchura_req DECIMAL(5, 2) NOT NULL, longitud_req DECIMAL(5, 2) NOT NULL, tipo_req INT NOT NULL, distancia INT NOT NULL, peso DECIMAL(4,2) NOT NULL,	mercancia VARCHAR(100), cinchas_req BOOLEAN NOT NULL, apertura_superior_req BOOLEAN NOT NULL, enganche_req BOOLEAN NOT NULL, volumen_req INT, capacidad_pales_req INT, CONSTRAINT fk_origen FOREIGN KEY (origen) REFERENCES ubicacion (id), CONSTRAINT fk_destino FOREIGN KEY (destino) REFERENCES ubicacion (id), FOREIGN KEY (cliente) REFERENCES cliente (id), FOREIGN KEY (tipo_req) REFERENCES tipo_remolque (id), UNIQUE KEY datos (origen, destino, precio, cliente, altura_req, anchura_req, longitud_req, tipo_req), ruta_documento VARCHAR(255) , PRIMARY KEY (id))");

			st.execute(
					"CREATE TABLE trabajador (id INT NOT NULL AUTO_INCREMENT, nif VARCHAR(10) UNIQUE NOT NULL, nombre VARCHAR(25) NOT NULL,	primer_apellido VARCHAR(25) NOT NULL, segundo_apellido VARCHAR(25) NOT NULL, nacionalidad VARCHAR(25) NOT NULL, fecha_nacimiento DATE NOT NULL, estado TINYINT NOT NULL DEFAULT 1, ruta_contrato VARCHAR(255), PRIMARY KEY (id))");

			st.execute(
					"CREATE TABLE remolque (id INT NOT NULL AUTO_INCREMENT, matricula VARCHAR(10) UNIQUE NOT NULL, tara DECIMAL(7, 2) NOT NULL, altura DECIMAL(5, 2) NOT NULL, anchura DECIMAL(5, 2) NOT NULL, longitud DECIMAL(5, 2) NOT NULL, ejes TINYINT, fecha_compra DATE NOT NULL, tipo INT NOT NULL, cinchas BOOLEAN, apertura_superior BOOLEAN, enganche BOOLEAN, volumen INT, capacidad_pales INT, estado TINYINT NOT NULL DEFAULT 1, ruta_documento VARCHAR(255) , FOREIGN KEY (tipo) REFERENCES tipo_remolque (id), PRIMARY KEY (id))");

			st.execute(
					"CREATE TABLE camion (id INT NOT NULL AUTO_INCREMENT, matricula VARCHAR(8) UNIQUE NOT NULL, trabajador INT, tara DECIMAL(7, 2) NOT NULL, marca VARCHAR(20) NOT NULL, modelo VARCHAR(20) NOT NULL, combustible VARCHAR(20), potencia INT, normativa VARCHAR(20), kilometraje INT, fecha_mat DATE NOT NULL, config_eje VARCHAR(20), remolque INT, estado TINYINT NOT NULL DEFAULT 1, ruta_documento VARCHAR(255) , FOREIGN KEY (trabajador) REFERENCES trabajador (id), FOREIGN KEY (remolque) REFERENCES remolque (id), PRIMARY KEY (id))");

			st.execute(
					"CREATE TABLE encargo (id INT NOT NULL AUTO_INCREMENT, presupuesto INT NOT NULL, camion INT, estado TINYINT NOT NULL DEFAULT 1, fecha_inicio DATETIME, fecha_fin DATETIME, fecha_entrega DATE, ruta_documento VARCHAR(255) , FOREIGN KEY (presupuesto) REFERENCES presupuesto (id), FOREIGN KEY (camion) REFERENCES camion (id), PRIMARY KEY (id))");

			st.execute(
					"CREATE TABLE historico_encargo (id INT NOT NULL, presupuesto INT NOT NULL, trabajador INT NOT NULL, camion INT NOT NULL, remolque INT NOT NULL, estado TINYINT NOT NULL DEFAULT 2, fecha_inicio DATETIME NOT NULL, fecha_fin DATETIME NOT NULL, fecha_entrega DATE, ruta_documento VARCHAR(255) , FOREIGN KEY (presupuesto) REFERENCES presupuesto (id), FOREIGN KEY (trabajador) REFERENCES trabajador (id), FOREIGN KEY (camion) REFERENCES camion (id), FOREIGN KEY (remolque) REFERENCES remolque (id), PRIMARY KEY (id))");

			// Trigger para crear historico_encargo

			st.executeUpdate(
					"CREATE OR REPLACE TRIGGER al_completar_encargo AFTER UPDATE ON encargo FOR EACH ROW BEGIN IF NEW.estado = 3 THEN INSERT INTO historico_encargo (SELECT e.id, e.presupuesto, c.trabajador, e.camion, c.remolque, e.estado, e.fecha_inicio, e.fecha_fin, e.fecha_entrega, e.ruta_documento FROM encargo e JOIN camion c ON e.camion = c.id WHERE e.id = NEW.id); END IF; END");

			// Vistas

			st.execute(
					"CREATE OR REPLACE VIEW trabajador_disponible_vista AS SELECT * FROM trabajador WHERE estado = 1");

			st.execute("CREATE OR REPLACE VIEW trabajador_ocupado_vista AS SELECT * FROM trabajador WHERE estado = 0");

			st.executeUpdate(
					"CREATE OR REPLACE VIEW remolque_disponible_vista AS SELECT * FROM remolque WHERE estado = 1");

			st.executeUpdate(
					"CREATE OR REPLACE VIEW remolque_ocupado_vista AS SELECT * FROM remolque WHERE estado = 0");

			st.executeUpdate("CREATE OR REPLACE VIEW camion_disponible_vista AS SELECT * FROM camion WHERE estado = 1");

			st.executeUpdate(
					"CREATE OR REPLACE VIEW camion_disponible_remolque_trabajador_vista AS SELECT * FROM camion WHERE remolque IS NOT NULL AND trabajador IS NOT NULL AND estado = 1");

			st.executeUpdate("CREATE OR REPLACE VIEW camion_ocupado_vista AS SELECT * FROM camion WHERE estado = 0");

			st.execute("CREATE OR REPLACE VIEW encargo_por_hacer_vista AS SELECT * FROM encargo WHERE estado = 0");

			st.execute("CREATE OR REPLACE VIEW encargo_preparado_vista AS SELECT * FROM encargo WHERE estado = 1");

			st.execute("CREATE OR REPLACE VIEW encargo_en_curso_vista AS SELECT * FROM encargo WHERE estado = 2");

			st.executeUpdate(
					"CREATE OR REPLACE VIEW encargo_trabajador_vista AS SELECT e.*, c.trabajador FROM (encargo e JOIN camion c ON e.camion = c.id) JOIN trabajador t ON c.trabajador = t.id;");

			st.executeUpdate(
					"CREATE OR REPLACE VIEW encargo_cliente_vista AS SELECT e.*, p.cliente FROM (encargo e JOIN presupuesto p ON e.presupuesto = p.id) JOIN cliente c ON p.cliente = c.id");

			st.executeUpdate(
					"CREATE OR REPLACE VIEW encargo_completado_cliente_vista AS SELECT h.*, p.cliente FROM (historico_encargo h JOIN presupuesto p ON h.presupuesto = p.id) JOIN cliente c ON p.cliente = c.id");

			st.executeUpdate("INSERT INTO tipo_remolque (tipo) VALUES ('Lona'), ('Frigorífico'), ('Pisomóvil')");

			st.executeUpdate("INSERT INTO usuario (nombre, contrasena) VALUES ('ausias', MD5('password'))");

			con.commit();

			if (seed)
				insertaDatosPrueba();
		} catch (NIFNoValidoException | DatoNoValidoException | EntidadYaExisteException e) {
			e.printStackTrace();
		}
	}

	@BeforeEach
	void reset() {
		try {
			reiniciarDB(false);
		} catch (SQLException | MatriculaNoValidaException e) {
			e.printStackTrace();
		}
	}

@AfterAll
	void resetWithData() {
		try {
			reiniciarDB(true);
		} catch (SQLException | MatriculaNoValidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
