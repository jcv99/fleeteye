package objeto;

import java.sql.Date;

import exception.DatoNoValidoException;
import exception.MatriculaNoValidaException;
import exception.RemolqueNoCompatibleException;
import herramienta.Comprobador;

public class RemolquePisoMovil extends Remolque {

	public static RemolquePisoMovil remolqueRequerido(DimensionesRemolque dimensiones, int volumen)
			throws DatoNoValidoException {
		return new RemolquePisoMovil(dimensiones, volumen);
	}

	private int volumen = 0;

	private RemolquePisoMovil(DimensionesRemolque dimensiones, int volumen) throws DatoNoValidoException {
		super(dimensiones);
		this.volumen = Comprobador.esEnteroValido(volumen);
	}

	public RemolquePisoMovil(String matricula, Date fechaCompra, double tara, DimensionesRemolque dimensiones)
			throws DatoNoValidoException, MatriculaNoValidaException {
		super(matricula, fechaCompra, tara, dimensiones);
	}

	public RemolquePisoMovil(String matricula, Date fechaCompra, double tara, DimensionesRemolque dimensiones, int ejes)
			throws DatoNoValidoException {
		super(matricula, fechaCompra, tara, ejes, dimensiones);
	}

	@Override
	public Remolque compatible(Object remolque) throws RemolqueNoCompatibleException {
		if (remolque == null)
			throw new RemolqueNoCompatibleException("La tractora no tiene remolque");
		if (!(remolque instanceof RemolquePisoMovil))
			throw new RemolqueNoCompatibleException("El cliente nos pide un remolque piso movil");
		if (!this.getDimensionesRemolque().compararMedidas(((Remolque) remolque).getDimensionesRemolque()))
			throw new RemolqueNoCompatibleException("Las medidas del remolque no son las que nos pide el cliente");
		if (((RemolquePisoMovil) remolque).getVolumen() < this.volumen)
			throw new RemolqueNoCompatibleException("La capacidad es inferior a la que nos pide el cliente");
		return (Remolque) remolque;
	}

	@Override
	public String descripcion() {
		return super.toString() + "- Piso Movil - " + getDimensionesRemolque().getAltura() + "x"
				+ getDimensionesRemolque().getLongitud() + "x" + getDimensionesRemolque().getAnchura() + " " + volumen
				+ " m3";
	}

	public int getVolumen() {
		return volumen;
	}

	public void setVolumen(int volumen) throws DatoNoValidoException {
		this.volumen = Comprobador.esEnteroValido(volumen);
	}

	@Override
	public String toString() {
		return super.toString() + "- Piso Movil - " + getDimensionesRemolque().getAltura() + "x"
				+ getDimensionesRemolque().getLongitud() + "x" + getDimensionesRemolque().getAnchura() + " " + volumen
				+ " m3";
	}

}