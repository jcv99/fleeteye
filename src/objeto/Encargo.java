package objeto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

import exception.DatoNoValidoException;
import exception.EncargoEstadoNoValidoException;
import exception.RemolqueNoCompatibleException;
import exception.TrabajadorNoAsignadoException;
import exception.VehiculoOcupadoExcepcion;
import herramienta.Comprobador;

public class Encargo {

	public static class Builder {
		
		private Camion camion = null;
		
		private int estado = PORHACER;
		private Date fechaEntrega = null;
		private Timestamp fechaFin = null;
		private int id = 0;
		private String pathRemoto;
		private Presupuesto presupuesto;
		
		public Builder(Presupuesto presupuesto) 
		{
			this.presupuesto = Objects.requireNonNull(presupuesto);
		}
		
		public Encargo build() throws VehiculoOcupadoExcepcion, RemolqueNoCompatibleException {return new Encargo(this);}
		
		public Builder fechaEntrega(Date fechaEntrega) { this.fechaEntrega = Objects.requireNonNull(fechaEntrega); return this;}
		public Builder id(int id) throws DatoNoValidoException {this.id = Comprobador.esEnteroValido(id); return this;}
		public Builder pathRemoto(String pathRemoto) { this.pathRemoto = pathRemoto; return this;}
		public Builder tractora(Camion vehiculo) throws RemolqueNoCompatibleException, VehiculoOcupadoExcepcion, TrabajadorNoAsignadoException {
			Objects.requireNonNull(vehiculo);
			if (vehiculo.esOcupado()) {
				throw new VehiculoOcupadoExcepcion("El vehiculo ya tiene un encargo");
			}			
			this.presupuesto.getRemolqueRequerido().compatible(vehiculo.getRemolque());
			if (vehiculo.getTrabajador() != null) {
				vehiculo.setEstadoOcupado();
				this.camion = vehiculo;				
				this.estado = PREPARADO;				
				return this;
			}				
			else throw new TrabajadorNoAsignadoException("El vehiculo no tiene un trabajador asignado");					
		}
	}
	public final static int COMPLETADO = 3;
	public final static int ENCURSO = 2;
	public final static int PORHACER = 0;

	public final static int PREPARADO = 1;
	private Camion camion;
	private int estado;
	private Date fechaEntrega;
	private Timestamp fechaFin;
	private Timestamp fechaInicio;
	private int id;
	private String pathRemoto;
	
	private Presupuesto presupuesto;	

	private Encargo(Builder builder) throws VehiculoOcupadoExcepcion, RemolqueNoCompatibleException 
	{
		this.id = builder.id;		
		this.camion = builder.camion;
		this.presupuesto = builder.presupuesto;
		this.fechaFin = builder.fechaFin;
		this.fechaEntrega = builder.fechaEntrega;
		this.estado = builder.estado;
		this.pathRemoto = builder.pathRemoto;
	}

	public void completarEncargo() throws EncargoEstadoNoValidoException {
		if (!esEnCurso())
			throw new EncargoEstadoNoValidoException("El encargo tiene que estar en curso para poderse finalizar");
		this.estado = COMPLETADO;
		this.camion.setEstadoDisponible();
		this.fechaFin = new Timestamp(System.currentTimeMillis());
	}

	public boolean esCompletado() {
		if (this.estado == COMPLETADO)
			return true;
		else
			return false;
	}
	
	public boolean esEnCurso() {
		if (this.estado == ENCURSO)
			return true;
		else
			return false;
	}

	public boolean esPorHacer() {
		if (this.estado == PORHACER)
			return true;
		else
			return false;
	}

	public boolean esPreparado() {
		if (this.estado == PREPARADO)
			return true;
		else
			return false;
	}

	public Camion getCamion() {
		return camion;
	}

	public int getEstado() {
		return estado;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public Timestamp getFechaFin() {
		return fechaFin;
	}

	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	public int getId() {
		return id;
	}

	public String getPathRemoto() {
		return pathRemoto;
	}

	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void inicializar() throws EncargoEstadoNoValidoException {
		if (!esPreparado())
			throw new EncargoEstadoNoValidoException("El encargo tiene que estar preparado para poderse iniciar");
		this.estado = ENCURSO;
		this.fechaInicio = new Timestamp(System.currentTimeMillis());
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = Objects.requireNonNull(fechaEntrega);
	}	
	
	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = Objects.requireNonNull(fechaFin);
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		if (this.estado == Encargo.ENCURSO)
			this.fechaInicio = Objects.requireNonNull(fechaInicio);
		else
			this.fechaInicio = fechaInicio;
	}	
	
	public void setId(int id) throws DatoNoValidoException {
		this.id = Comprobador.esEnteroValido(id);
	}

	public void setPathRemoto(String pathRemoto) {
		this.pathRemoto = pathRemoto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = Objects.requireNonNull(presupuesto);
	}
	
	public void setVehiculo(Camion vehiculo) throws VehiculoOcupadoExcepcion, RemolqueNoCompatibleException, TrabajadorNoAsignadoException {
		Objects.requireNonNull(vehiculo);
		if (vehiculo.esOcupado()) {
			throw new VehiculoOcupadoExcepcion("El vehiculo ya tiene un encargo");
		}			
		else {	
			this.presupuesto.getRemolqueRequerido().compatible(vehiculo.getRemolque()); 
			if (vehiculo.getTrabajador() != null) {
				vehiculo.setEstadoOcupado();
				this.camion = vehiculo;				
				this.estado = PREPARADO;				
			}			
			else {
				throw new TrabajadorNoAsignadoException("El vehiculo no tiene un trabajador asignado");
			}
		}
	}

	public void setVehiculoBBDD(Camion vehiculo) {
		this.camion = Objects.requireNonNull(vehiculo);		
	}

	@Override
	public String toString() 
	{
		return "ID: " + this.id;
	}
}
