package objeto;

import java.sql.Date;
import java.util.Objects;

import exception.DatoNoValidoException;
import exception.MatriculaNoValidaException;
import exception.RemolqueNoCompatibleException;
import herramienta.Comprobador;

public abstract class Remolque {

	public final static int DISPONIBLE = 1;
	public final static int OCUPADO = 0;

	public static final int TIPO_FRIGORIFICO = 2;
	public static final int TIPO_LONA = 1;
	public static final int TIPO_PISOMOVIL = 3;

	private DimensionesRemolque dimensiones = null;
	private int ejes = 0;
	private int estado;
	private Date fechaCompra = null;
	private int id = 0;
	private String matricula = null;
	private String pathRemoto;
	private double tara = 0;

	protected Remolque(DimensionesRemolque dimensiones) {
		this.dimensiones = Objects.requireNonNull(dimensiones);
	}

	public Remolque(String matricula, Date fechaCompra, double tara, DimensionesRemolque dimensiones)
			throws DatoNoValidoException, MatriculaNoValidaException {
		this.matricula = Comprobador.esMatriculaRemolqueValida(matricula);
		this.fechaCompra = Objects.requireNonNull(fechaCompra);
		this.tara = Comprobador.esDecimalValido(tara);
		this.dimensiones = Objects.requireNonNull(dimensiones);
		this.estado = DISPONIBLE;
	}

	public Remolque(String matricula, Date fechaCompra, double tara, int ejes, DimensionesRemolque dimensiones)
			throws DatoNoValidoException {
		this.matricula = Comprobador.esStringValido(matricula);
		this.fechaCompra = Objects.requireNonNull(fechaCompra);
		this.tara = Comprobador.esDecimalValido(tara);
		this.dimensiones = Objects.requireNonNull(dimensiones);
		this.ejes = Comprobador.esEnteroValido(ejes);
		this.estado = DISPONIBLE;
	}

	public abstract Remolque compatible(Object remolque) throws RemolqueNoCompatibleException;

	public abstract String descripcion();

	public String descripcionDebug() {
		if (matricula == null) {
			if (fechaCompra == null) {
				if (estado == 1) {
					return "Tara: " + tara + ", ejes: " + ejes + ", dimensiones:  " + dimensiones + ", diponible";
				}
				if (estado == 0) {
					return "Tara: " + tara + ", ejes: " + ejes + ", dimensiones:  " + dimensiones + ", ocupado";
				}
				return "Tara: " + tara + ", ejes: " + ejes + ", dimensiones:  " + dimensiones;
			}
			return fechaCompra + ", " + tara + ", ejes: " + ejes + ", " + dimensiones;
		}
		return matricula + ", " + fechaCompra + ", " + tara + ", ejes: " + ejes + ", " + dimensiones;
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

	public DimensionesRemolque getDimensionesRemolque() {
		return this.dimensiones;
	}

	public int getEjes() {
		return ejes;
	}

	public int getEstado() {
		return this.estado;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public int getId() {
		return id;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getPathRemoto() {
		return pathRemoto;
	}

	public double getTara() {
		return tara;
	}

	public void setDimensionesRemolque(DimensionesRemolque dimensiones) {
		this.dimensiones = Objects.requireNonNull(dimensiones);
	}

	public void setEjes(int ejes) throws DatoNoValidoException {
		this.ejes = Comprobador.esEnteroValido(ejes);
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = Objects.requireNonNull(fechaCompra);
	}

	public void setId(int id) throws DatoNoValidoException {
		this.id = Comprobador.esEnteroValido(id);
	}

	public void setMatricula(String matricula) throws DatoNoValidoException, MatriculaNoValidaException {
		this.matricula = Comprobador.esMatriculaRemolqueValida(matricula);
	}

	public void setPathRemoto(String pathRemoto) {
		this.pathRemoto = pathRemoto;
	}

	public void setTara(double tara) throws DatoNoValidoException {
		this.tara = Comprobador.esDecimalValido(tara);
	}

// Antiguo toString
//	if (matricula == null) {
//		if (fechaCompra == null) {
//			if (estado == 1) {
//				return "Tara: " + tara + ", ejes: " + ejes + ", dimensiones:  " + dimensiones + ", diponible";
//			}
//			if (estado == 0) {
//				return "Tara: " + tara + ", ejes: " + ejes + ", dimensiones:  " + dimensiones + ", ocupado";
//			}
//			return "Tara: " + tara + ", ejes: " + ejes + ", dimensiones:  " + dimensiones;
//		}
//		return fechaCompra + ", " + tara + ", ejes: " + ejes + ", " + dimensiones;
//	}
//	return matricula + ", " + fechaCompra + ", " + tara + ", ejes: " + ejes + ", " + dimensiones;
//}

	@Override
	public String toString() {
		return this.matricula + " ";
	}
}
