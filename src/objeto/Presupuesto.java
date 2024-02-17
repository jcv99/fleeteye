package objeto;

import java.util.Objects;

import exception.DatoNoValidoException;
import herramienta.Comprobador;

public class Presupuesto {

	public static class Builder {
		private Cliente cliente;
		private Ubicacion destino;
		private int distancia = 0;
		private int id = 0;
		private String mercancia = null;

		private Ubicacion origen;
		private String pathRemoto = null;
		private double pesoCarga = 24;		
		private double precio;
		private Remolque remolqueRequerido;

		public Builder(Ubicacion origen, Ubicacion destino, double precio, Cliente cliente, Remolque remolqueRequerido)
				throws DatoNoValidoException {
			this.origen = Objects.requireNonNull(origen);
			this.destino = Objects.requireNonNull(destino);
			this.precio = Comprobador.esDecimalValido(precio);
			this.cliente = Objects.requireNonNull(cliente);
			this.remolqueRequerido = Objects.requireNonNull(remolqueRequerido);
		}

		public Presupuesto build() throws DatoNoValidoException {
			return new Presupuesto(this);
		}

		public Builder distancia(int distancia) throws DatoNoValidoException {
			this.distancia = Comprobador.esEnteroValido(distancia);
			return this;
		}

		public Builder id(int id) throws DatoNoValidoException {
			this.id = Comprobador.esEnteroValido(id);
			return this;
		}	

		public Builder mercancia(String mercancia) throws DatoNoValidoException {
			this.mercancia = Comprobador.esStringValido(mercancia);
			return this;
		}

		public Builder pathRemoto(String pathRemoto) throws DatoNoValidoException {
			this.pathRemoto = Comprobador.esStringValido(pathRemoto);
			return this;
		}
		
		public Builder pesoCarga(double pesoCarga) throws DatoNoValidoException {
			this.pesoCarga = Comprobador.esDecimalValido(pesoCarga);
			return this;
		}
	}
	private Cliente cliente;
	private Ubicacion destino;
	private int distancia;
	private int id;
	private String mercancia;
	private Ubicacion origen;
	private String pathRemoto;
	private double pesoCarga;
	private double precio;

	private Remolque remolqueRequerido;

	private Presupuesto(Builder builder) throws DatoNoValidoException {
		this.origen = builder.origen;
		this.destino = builder.destino;
		this.precio = builder.precio;
		this.cliente = builder.cliente;
		this.id = builder.id;
		this.distancia = builder.distancia;
		this.mercancia = builder.mercancia;
		this.remolqueRequerido = builder.remolqueRequerido;
		this.pesoCarga = builder.pesoCarga;
		this.pathRemoto = builder.pathRemoto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Ubicacion getDestino() {
		return destino;
	}

	public int getDistancia() {
		return distancia;
	}

	public int getId() {
		return id;
	}

	public String getMercancia() {
		return mercancia;
	}

	public Ubicacion getOrigen() {
		return origen;
	}

	public String getPathRemoto() {
		return pathRemoto;
	}

	public double getPesoCarga() {
		return pesoCarga;
	}

	public double getPrecio() {
		return precio;
	}

	public Remolque getRemolqueRequerido() {
		return remolqueRequerido;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = Objects.requireNonNull(cliente);
	}

	public void setDestino(Ubicacion destino) {
		this.destino = Objects.requireNonNull(destino);
	}

	public void setDistancia(int distancia) throws DatoNoValidoException {
		this.distancia = Comprobador.esEnteroValido(distancia);
	}

	public void setId(int id) throws DatoNoValidoException {
		this.id = Comprobador.esEnteroValido(id);
	}

	public void setMercancia(String mercancia) throws DatoNoValidoException {
		this.mercancia = Comprobador.esStringValido(mercancia);
	}

	public void setOrigen(Ubicacion origen) {
		this.origen = Objects.requireNonNull(origen);
	}

	public void setPathRemoto(String pathRemoto) throws DatoNoValidoException {
		this.pathRemoto = Comprobador.esStringValido(pathRemoto);
	}

	public void setPesoCarga(double pesoCarga) throws DatoNoValidoException {
		this.pesoCarga = Comprobador.esDecimalValido(pesoCarga);
	}

	public void setPrecio(double precio) throws DatoNoValidoException {
		this.precio = Comprobador.esDecimalValido(precio);
	}

	public void setRemolqueRequerido(Remolque remolqueRequerido) {
		this.remolqueRequerido = Objects.requireNonNull(remolqueRequerido);
	}

	@Override
	public String toString() {
		return cliente.getIdentidad().getNif() + ", " + origen.getDireccion() + " " + origen.getCodigopostal() + ", "
				+ destino.getDireccion() + " " + destino.getCodigopostal();
	}
}
