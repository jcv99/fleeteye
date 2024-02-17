package objeto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import exception.DatoNoValidoException;
import exception.TrabajadorDadoDeBajaException;
import herramienta.Comprobador;

public class Trabajador {

	public final static int DADO_DE_BAJA = 2;
	public final static int DISPONIBLE = 1;
	public final static int OCUPADO = 0;

	private Set<Encargo> encargos;
	private int estado;
	private int id;
	private IdentidadPersonaFisica identidad;
	private String pathRemotoContrato;

	public Trabajador(Identidad IdentidadPersonaFisica, String pathRemotoContrato) {
		this.identidad = Objects.requireNonNull(identidad);
		this.estado = DISPONIBLE;
		this.encargos = new HashSet<Encargo>();
		this.pathRemotoContrato = pathRemotoContrato;
	}

	public Trabajador(IdentidadPersonaFisica identidad) {
		this.identidad = Objects.requireNonNull(identidad);
		this.estado = DISPONIBLE;
		this.encargos = new HashSet<Encargo>();
		this.pathRemotoContrato = null;
	}

	public void darDeBaja() throws TrabajadorDadoDeBajaException {
		if (esOcupado())
			throw new TrabajadorDadoDeBajaException("El trabajador esta ocupado");
		this.estado = DADO_DE_BAJA;
	}

	public String descripcion() {
		return identidad.toString();
	}

	public boolean esDadoBaja() {
		if (this.estado == DADO_DE_BAJA)
			return true;
		else
			return false;
	}

	public boolean esDisponible() {
		if (this.estado == DISPONIBLE)
			return true;
		else
			return false;
	}

	public boolean esOcupado() {
		if (this.estado == OCUPADO)
			return true;
		else
			return false;
	}

	public Set<Encargo> getEncargos() {
		return encargos;
	}

	public int getEstado() {
		return estado;
	}

	public int getId() {
		return id;
	}

	public IdentidadPersonaFisica getIdentidad() {
		return identidad;
	}

	public String getPathRemotoContrato() {
		return pathRemotoContrato;
	}

	public void setEncargos(Set<Encargo> encargos) {
		this.encargos = Objects.requireNonNull(encargos);
	}

	public void setEstadoDisponible() {
		this.estado = DISPONIBLE;
	}
	
	public void setEstadoOcupado() {
		this.estado = OCUPADO;
	}

	public void setId(int id) throws DatoNoValidoException {
		this.id = Comprobador.esEnteroValido(id);
	}

	public void setIdentidad(IdentidadPersonaFisica identidad) {
		this.identidad = Objects.requireNonNull(identidad);
	}
	
	public void setPathRemotoContrato(String pathRemotoContrato) {
		this.pathRemotoContrato = pathRemotoContrato;
	}

	@Override
	public String toString() {
		return identidad.getNombre() + " " + identidad.getApellido();
	}
}