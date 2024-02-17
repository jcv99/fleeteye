package objeto;

import java.sql.Date;
import java.util.Objects;

import exception.DatoNoValidoException;
import exception.NIFNoValidoException;
import herramienta.Comprobador;

public class IdentidadPersonaFisica extends Identidad {

	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String nombre;
	private String segundoApellido;

	public IdentidadPersonaFisica(Nif nif, String nombre, String apellido, String segundoApellido, String nacionalidad,
			Date fechaNacimiento) throws NIFNoValidoException, DatoNoValidoException {
		super(nif);
		this.nombre = Comprobador.esStringValido(nombre);
		this.apellido = Comprobador.esStringValido(apellido);
		this.segundoApellido = Comprobador.esStringValido(segundoApellido);
		this.nacionalidad = Comprobador.esStringValido(nacionalidad);
		this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento);
	}

	public String getApellido() {
		return apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setApellido(String apellido) throws DatoNoValidoException {
		this.apellido = Comprobador.esStringValido(apellido);
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento);
	}

	public void setNacionalidad(String nacionalidad) throws DatoNoValidoException {
		this.nacionalidad = Comprobador.esStringValido(nacionalidad);
	}

	public void setNombre(String nombre) throws DatoNoValidoException {
		this.nombre = Comprobador.esStringValido(nombre);
	}

	public void setSegundoApellido(String segundoApellido) throws DatoNoValidoException {
		this.segundoApellido = Comprobador.esStringValido(segundoApellido);
	}

	@Override
	public String toString() {
		return super.getNif() + " " + nombre + " " + apellido + " " + segundoApellido + " " + nacionalidad + " "
				+ fechaNacimiento;
	}
}
