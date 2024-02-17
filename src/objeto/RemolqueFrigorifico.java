package objeto;

import java.sql.Date;

import exception.DatoNoValidoException;
import exception.MatriculaNoValidaException;
import exception.RemolqueNoCompatibleException;
import herramienta.Comprobador;

public class RemolqueFrigorifico extends Remolque {

	public static RemolqueFrigorifico remolqueRequerido(DimensionesRemolque dimensiones, int capacidadPalettes)
			throws DatoNoValidoException {
		return new RemolqueFrigorifico(dimensiones, capacidadPalettes);
	}

	private int capacidadPalettes = 0;

	private RemolqueFrigorifico(DimensionesRemolque dimensiones, int capacidadPalettes) throws DatoNoValidoException {
		super(dimensiones);
		this.capacidadPalettes = Comprobador.esEnteroValido(capacidadPalettes);
	}

	public RemolqueFrigorifico(String matricula, Date fechaCompra, double tara, DimensionesRemolque dimensiones)
			throws DatoNoValidoException, MatriculaNoValidaException {
		super(matricula, fechaCompra, tara, dimensiones);
	}

	public RemolqueFrigorifico(String matricula, Date fechaCompra, double tara, DimensionesRemolque dimensiones,
			int ejes) throws DatoNoValidoException {
		super(matricula, fechaCompra, tara, ejes, dimensiones);
	}

	@Override
	public Remolque compatible(Object remolque) throws RemolqueNoCompatibleException {
		if (remolque == null)
			throw new RemolqueNoCompatibleException("La tractora no tiene remolque");
		if (!(remolque instanceof RemolqueFrigorifico))
			throw new RemolqueNoCompatibleException("El cliente nos pide un remolque frigorifico");
		if (!this.getDimensionesRemolque().compararMedidas(((Remolque) remolque).getDimensionesRemolque()))
			throw new RemolqueNoCompatibleException("Las medidas del remolque no son las que nos pide el cliente");
		if (((RemolqueFrigorifico) remolque).getCapacidadPalettes() < this.capacidadPalettes)
			throw new RemolqueNoCompatibleException("La capacidad de pales es inferior a la que pide el cliente");
		return (Remolque) remolque;
	}

	@Override
	public String descripcion() {
		return super.toString() + "- Frigorifico - " + getDimensionesRemolque().getAltura() + "x"
				+ getDimensionesRemolque().getLongitud() + "x" + getDimensionesRemolque().getAnchura() + " Capacidad de pales: "
				+ capacidadPalettes;
	}

	public int getCapacidadPalettes() {
		return capacidadPalettes;
	}

	public void setCapacidadPalettes(int capacidadPalettes) throws DatoNoValidoException {
		this.capacidadPalettes = Comprobador.esEnteroValido(capacidadPalettes);
	}

	@Override
	public String toString() {
		return super.toString() + "- Frigorifico - " + getDimensionesRemolque().getAltura() + "x"
				+ getDimensionesRemolque().getLongitud() + "x" + getDimensionesRemolque().getAnchura() + " Capacidad de pales: "
				+ capacidadPalettes;
	}
}