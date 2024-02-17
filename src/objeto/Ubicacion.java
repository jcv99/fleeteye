package objeto;

import java.util.Objects;

import exception.DatoNoValidoException;
import herramienta.Comprobador;

public class Ubicacion {

	private String codigopostal;
	private String direccion;
	private int id;
	private String localidad;
	private String pais;
	private String provincia;

	public Ubicacion(String direccion, String localidad, String provincia, String codigopostal, String pais)
			throws DatoNoValidoException {
		this.direccion = Comprobador.esStringValido(direccion);
		this.localidad = Comprobador.esStringValido(localidad);
		this.provincia = Comprobador.esStringValido(provincia);
		this.codigopostal = Comprobador.esStringValido(codigopostal);
		this.pais = Comprobador.esStringValido(pais);
	}

	public String getCodigopostal() {
		return codigopostal;
	}

	public String getDireccion() {
		return direccion;
	}

	public int getId() {
		return id;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getPais() {
		return pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setCodigopostal(String codigopostal) throws DatoNoValidoException {
		this.codigopostal = Comprobador.esStringValido(codigopostal);
	}

	public void setDireccion(String direccion) throws DatoNoValidoException {
		this.direccion = Comprobador.esStringValido(direccion);
	}

	public void setId(int id) throws DatoNoValidoException {
		this.id = Comprobador.esEnteroValido(id);
	}

	public void setLocalidad(String localidad) throws DatoNoValidoException {
		this.localidad = Comprobador.esStringValido(localidad);
	}

	public void setPais(String pais) {
		this.pais = Objects.requireNonNull(pais);
	}

	public void setProvincia(String provincia) throws DatoNoValidoException {
		this.provincia = Comprobador.esStringValido(provincia);
	}

	@Override
	public String toString() {
		return direccion + "," + localidad + "," + provincia + "," + codigopostal + "," + pais;
	}
}
