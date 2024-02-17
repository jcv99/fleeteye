package objeto;

import java.sql.Date;
import java.util.Objects;

import exception.DatoNoValidoException;
import exception.MatriculaNoValidaException;
import exception.RemolqueNoCompatibleException;

public class RemolqueLona extends Remolque {

	public static RemolqueLona remolqueNoRequerido() throws DatoNoValidoException {
		return new RemolqueLona();
	}
	public static RemolqueLona remolqueRequerido(DimensionesRemolque dimensiones, boolean cinchas, boolean abreArriba,
			boolean engancheRemolque) {
		return new RemolqueLona(dimensiones, cinchas, abreArriba, engancheRemolque);
	}
	private boolean abreArriba = false;

	private boolean cinchas = false;

	private boolean engancheRemolque = false;

	private RemolqueLona() throws DatoNoValidoException {
		super(new DimensionesRemolque(2.7, 2.48, 13.6));
	}

	private RemolqueLona(DimensionesRemolque dimensiones, boolean cinchas, boolean abreArriba,
			boolean engancheRemolque) {
		super(dimensiones);
		this.cinchas = Objects.requireNonNull(cinchas);
		this.abreArriba = Objects.requireNonNull(abreArriba);
		this.engancheRemolque = Objects.requireNonNull(engancheRemolque);
	}

	public RemolqueLona(String matricula, Date fechaCompra, double tara, DimensionesRemolque dimensiones)
			throws DatoNoValidoException, MatriculaNoValidaException {
		super(matricula, fechaCompra, tara, dimensiones);
	};

	public RemolqueLona(String matricula, Date fechaCompra, double tara, DimensionesRemolque dimensiones, int ejes)
			throws DatoNoValidoException {
		super(matricula, fechaCompra, tara, ejes, dimensiones);
	}

	public boolean abreArriba() {
		return abreArriba;
	}

	@Override
	public Remolque compatible(Object remolqueRequerido) throws RemolqueNoCompatibleException {
		if (remolqueRequerido == null)
			throw new RemolqueNoCompatibleException("La tractora no tiene remolque");
		if (!(remolqueRequerido instanceof RemolqueLona))
			throw new RemolqueNoCompatibleException("El cliente nos pide un remolque lona");

		if (!this.getDimensionesRemolque().compararMedidas(((Remolque) remolqueRequerido).getDimensionesRemolque()))
			throw new RemolqueNoCompatibleException("Las medidas del remolque no son las que nos pide el cliente");

		if (tieneCinchas() && !((RemolqueLona) remolqueRequerido).tieneCinchas())
			throw new RemolqueNoCompatibleException("El cliente nos pide cinchas");

		if (abreArriba() && !((RemolqueLona) remolqueRequerido).abreArriba())
			throw new RemolqueNoCompatibleException("El cliente nos pide que se pueda abrir la lona por arriba");

		if (tieneEngancheRemolque() && !((RemolqueLona) remolqueRequerido).tieneEngancheRemolque())
			throw new RemolqueNoCompatibleException("El cliente nos pide un camion de carretera");

		return (Remolque) remolqueRequerido;
	}

	@Override
	public String descripcion() {
		String texto = "";

		if (cinchas) {
			texto += texto + " Chinchas";
		}
		if (abreArriba) {
			texto += texto + " Abre arriba";
		}
		if (engancheRemolque) {
			texto += texto + " Enganche remolque";
		}
		return super.toString() + "-Lona - " + getDimensionesRemolque().getAltura() + "x"
				+ getDimensionesRemolque().getLongitud() + "x" + getDimensionesRemolque().getAnchura() + " " + texto;
	}

	public void setAbreArriba(boolean abreArriba) {
		this.abreArriba = Objects.requireNonNull(abreArriba);
	}

	public void setCinchas(boolean cinchas) {
		this.cinchas = Objects.requireNonNull(cinchas);
	}

	public void setEngancheRemolque(boolean engancheRemolque) {
		this.engancheRemolque = Objects.requireNonNull(engancheRemolque);
	}

	public boolean tieneCinchas() {
		return cinchas;
	}

	public boolean tieneEngancheRemolque() {
		return engancheRemolque;
	}

	@Override
	public String toString() {
		String texto = "";

		if (cinchas) {
			texto += " Chinchas";
		}
		if (abreArriba) {
			texto += " Abre arriba";
		}
		if (engancheRemolque) {
			texto += " Enganche remolque";
		}
		return super.toString() + "- Lona - " + getDimensionesRemolque().getAltura() + "x"
				+ getDimensionesRemolque().getLongitud() + "x" + getDimensionesRemolque().getAnchura() + " " + texto;
	}
}
