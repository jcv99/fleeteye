package objeto;

import java.sql.Date;
import java.util.Objects;

import exception.CamionOcupadoException;
import exception.DatoNoValidoException;
import exception.MatriculaNoValidaException;
import exception.RemolqueYaAsignadoException;
import exception.TrabajadorDadoDeBajaException;
import exception.TrabajadorOcupadoException;
import herramienta.Comprobador;

public class Camion {

	public static class Builder {

		private String combustible = "";
		private String configuracionEje = "";
		private int estado = DISPONIBLE;
		private Date fechaMatriculacion = null;
		private int id = 0;

		private int kilometraje = 0;
		private final String marca;
		private final String matricula;
		private final String modelo;
		private String normativa = "";
		private String pathRemoto;
		private int potencia = 0;
		private Remolque remolque = null;
		private final double tara;
		private double taraTotal = 0;
		private Trabajador trabajador = null;

		public Builder(String matricula, double tara, String marca, String modelo) throws DatoNoValidoException, MatriculaNoValidaException {
			this.matricula = Comprobador.esMatriculaCamionValida(Objects.requireNonNull(matricula));
			this.tara = Comprobador.esDecimalValido(tara);
			this.marca = Objects.requireNonNull(marca);
			this.modelo = Objects.requireNonNull(modelo);
			this.taraTotal = tara;
		}

		public Camion build() {
			return new Camion(this);
		}

		public Builder combustible(String valor) {
			this.combustible = Objects.requireNonNull(valor);
			return this;
		}

		public Builder configuracionEje(String valor) {
			this.configuracionEje = Objects.requireNonNull(valor);
			return this;
		}

		public Builder estado(int estado) {
			this.estado = estado;
			return this;
		}

		public Builder fechaMatriculacion(Date valor) {
			this.fechaMatriculacion = Objects.requireNonNull(valor);
			return this;
		}

		public Builder kilometraje(int valor) throws DatoNoValidoException {
			this.kilometraje = Comprobador.esEnteroValido(valor);
			return this;
		}

		public Builder normativa(String valor) {
			this.normativa = Objects.requireNonNull(valor);
			return this;
		}

		public Builder pathRemoto(String pathRemoto) {
			this.pathRemoto = pathRemoto;
			return this;
		}

		public Builder potencia(int valor) throws DatoNoValidoException {
			this.potencia = Comprobador.esEnteroValido(valor);
			return this;
		}
		
		public Builder remolque(Remolque remolque) throws RemolqueYaAsignadoException {
			if (Objects.requireNonNull(remolque).esOcupado())
				throw new RemolqueYaAsignadoException();
			remolque.setEstado(OCUPADO);
			this.remolque = remolque;
			this.taraTotal += remolque.getTara();
			return this;
		}

		public Builder trabajador(Trabajador trabajador) throws TrabajadorOcupadoException {
			if (Objects.requireNonNull(trabajador).esOcupado())
				throw new TrabajadorOcupadoException();
			trabajador.setEstadoOcupado();
			this.trabajador = trabajador;
			return this;
		}
	}
	public final static int DISPONIBLE = 1;

	public final static int OCUPADO = 0;

	private String combustible;
	private String configuracionEje;
	private int estado;
	private Date fechaMatriculacion;
	private int id;
	private int kilometraje;
	private String marca;
	private String matricula;
	private String modelo;
	private String normativa;

	private String pathRemoto;

	private int potencia;
	private Remolque remolque;
	private double tara;

	private double taraTotal = 0;

	private Trabajador trabajador;

	private Camion(Builder builder) {
		id = builder.id;
		matricula = builder.matricula;
		tara = builder.tara;
		marca = builder.marca;
		modelo = builder.modelo;
		combustible = builder.combustible;
		potencia = builder.potencia;
		normativa = builder.normativa;
		kilometraje = builder.kilometraje;
		fechaMatriculacion = builder.fechaMatriculacion;
		configuracionEje = builder.configuracionEje;
		remolque = builder.remolque;
		estado = builder.estado;
		trabajador = builder.trabajador;
		taraTotal = builder.taraTotal;
		pathRemoto = builder.pathRemoto;
	}

	public void asignRemolque(Remolque remolque) throws RemolqueYaAsignadoException, CamionOcupadoException {
		if (Objects.requireNonNull(remolque).esOcupado())
			throw new RemolqueYaAsignadoException();
		if (esOcupado())
			throw new CamionOcupadoException("El camion esta ocupado en un encargo.");
		if (this.remolque != null)
			throw new CamionOcupadoException("El camion ya tiene un remolque asignado");
		if (remolque.getEstado() != DISPONIBLE) {
			throw new RemolqueYaAsignadoException();
		}
		remolque.setEstado(OCUPADO);
		this.remolque = remolque;
		this.taraTotal += remolque.getTara();
	}

	public String descripcion() {
		return matricula + ", " + tara + ", " + ", " + marca + ", " + modelo;
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

	public String getCombustible() {
		return combustible;
	}

	public String getConfiguracionEje() {
		return configuracionEje;
	}

	public int getEstado() {
		return estado;
	}

	public Date getFechaMatriculacion() {
		return fechaMatriculacion;
	}

	public int getId() {
		return id;
	}

	public int getKilometraje() {
		return kilometraje;
	}

	public String getMarca() {
		return marca;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public String getNormativa() {
		return normativa;
	}

	public String getPathRemoto() {
		return pathRemoto;
	}

	public int getPotencia() {
		return potencia;
	}

	public Remolque getRemolque() {
		return remolque;
	}

	public double getTara() {
		return tara;
	}

	public double getTaraTotal() {
		return taraTotal;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public Remolque quitarRemolque() throws CamionOcupadoException 
	{
		if (esOcupado())
			throw new CamionOcupadoException("El camion esta ocupado en un encargo.");
		else {
			Remolque r = this.remolque;
			r.setEstado(Remolque.DISPONIBLE);
			this.remolque = null;
			return r;
		}		
	}

	public Trabajador quitarTrabajador() throws CamionOcupadoException 
	{
		if (esOcupado())
			throw new CamionOcupadoException("El camion esta ocupado en un encargo.");
		else {
			Trabajador t = this.trabajador;
			t.setEstadoDisponible();
			this.trabajador = null;
			return t;
		}		
	}

	public void remolqueBBDD(Remolque remolque) throws TrabajadorOcupadoException {
		this.remolque = Objects.requireNonNull(remolque);
	}

	public void setCombustible(String combustible) throws DatoNoValidoException {
		this.combustible = Comprobador.esStringValido(combustible);
	}
	
	public void setConfiguracionEje(String configuracionEje) throws DatoNoValidoException {
		this.configuracionEje = Comprobador.esStringValido(configuracionEje);
	}

	public void setEstadoDisponible() {
		this.estado = DISPONIBLE;
	}

	public void setEstadoOcupado() {
		this.estado = OCUPADO;
	}

	public void setFechaMatriculacion(Date fechaMatriculacion) {
		this.fechaMatriculacion = Objects.requireNonNull(fechaMatriculacion);
	}

	public void setId(int id) throws DatoNoValidoException {
		this.id = Comprobador.esEnteroValido(id);
	}

	public void setKilometraje(int kilometraje) throws DatoNoValidoException {
		this.kilometraje = Comprobador.esEnteroValido(kilometraje);
	}

	public void setMarca(String marca) throws DatoNoValidoException {
		this.marca = Comprobador.esStringValido(marca);
	}

	public void setMatricula(String matricula) throws DatoNoValidoException, MatriculaNoValidaException {
		this.matricula = Comprobador.esMatriculaCamionValida(matricula);
	}

	public void setModelo(String modelo) throws DatoNoValidoException {
		this.modelo = Comprobador.esStringValido(modelo);
	}

	public void setNormativa(String normativa) throws DatoNoValidoException {
		this.normativa = Comprobador.esStringValido(normativa);
	}
	
	public void setPathRemoto(String pathRemoto) {
		this.pathRemoto = pathRemoto;
	}
	
	public void setPotencia(int potencia) throws DatoNoValidoException {
		this.potencia = Comprobador.esEnteroValido(potencia);
	}

	public void setTara(double tara) throws DatoNoValidoException {
		this.tara = Comprobador.esDecimalValido(tara);
	}

	public void setTaraTotal(double taraTotal) {
		this.taraTotal = taraTotal;
	}

	@Override
	public String toString() {
		String camion = this.getRemolque() != null ? this.matricula + "/" + this.getRemolque().toString() : this.matricula;
		return camion;
	}
	
	public void trabajador(Trabajador trabajador) throws TrabajadorOcupadoException, TrabajadorDadoDeBajaException {
		if (Objects.requireNonNull(trabajador).esOcupado())
			throw new TrabajadorOcupadoException();
		
		if (Objects.requireNonNull(trabajador).getEstado() == Trabajador.DADO_DE_BAJA) {
			throw new TrabajadorDadoDeBajaException();
		}
		trabajador.setEstadoOcupado();
		this.trabajador = trabajador;
	}

	public void trabajadorBBDD(Trabajador trabajador) throws TrabajadorOcupadoException {
		this.trabajador = Objects.requireNonNull(trabajador);
	}
}
