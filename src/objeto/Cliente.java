package objeto;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente {

	private ArrayList<Encargo> encargos;
	private int id;
	private IdentidadPersonaJuridica identidad;
	private String pathRemoto;
	private ArrayList<Presupuesto> presupuestos;

	public Cliente(IdentidadPersonaJuridica identidad) {
		this.identidad = Objects.requireNonNull(identidad);
		this.presupuestos = new ArrayList<Presupuesto>();
		this.encargos = new ArrayList<Encargo>();
	}

	public void anadirEncargo(Encargo encargo) {
		this.encargos.add(Objects.requireNonNull(encargo));
	}

	public void anadirPresupuesto(Presupuesto presupuesto) {
		this.presupuestos.add(Objects.requireNonNull(presupuesto));
	}

	public void borrarEncargo(Encargo encargo) {
		this.encargos.remove(encargo);
	}

	public void borrarPresupuesto(Presupuesto presupuesto) {
		this.presupuestos.remove(presupuesto);
	}

	public String descripcion() {
		return identidad.toString();
	}

	public ArrayList<Encargo> getEncargos() {
		return encargos;
	}

	public int getId() {
		return id;
	}

	public IdentidadPersonaJuridica getIdentidad() {
		return identidad;
	}

	public String getPathRemoto() {
		return pathRemoto;
	}

	public ArrayList<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	public double mediaEuroPorKilometro() {
		double eurokm = 0;
		for (Encargo e : encargos) {
			eurokm += e.getPresupuesto().getPrecio() / e.getPresupuesto().getDistancia();
		}
		return eurokm / this.encargos.size();
	}

	public void setEncargos(ArrayList<Encargo> encargos) {
		this.encargos = Objects.requireNonNull(encargos);
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdentidad(IdentidadPersonaJuridica identidad) {
		this.identidad = Objects.requireNonNull(identidad);
	}

	public void setPathRemoto(String pathRemoto) {
		this.pathRemoto = pathRemoto;
	}
	
	public void setPresupuestos(ArrayList<Presupuesto> presupuestos) {
		this.presupuestos = Objects.requireNonNull(presupuestos);
	}

	@Override
	public String toString() {
		return identidad.getRazonSocial();
	}
}
