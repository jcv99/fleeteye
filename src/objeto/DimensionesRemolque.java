package objeto;

import exception.DatoNoValidoException;
import herramienta.Comprobador;

public class DimensionesRemolque {

	private double altura = 0;
	private double anchura = 0;
	private double longitud = 0;

	public DimensionesRemolque(double altura, double anchura, double longitud) throws DatoNoValidoException {
		this.altura = Comprobador.esDecimalValido(altura);
		this.anchura = Comprobador.esDecimalValido(anchura);
		this.longitud = Comprobador.esDecimalValido(longitud);
	}

	public boolean compararMedidas(DimensionesRemolque dimensiones) {
		if (dimensiones.getAltura() < this.altura || dimensiones.getAnchura() < this.anchura
				|| dimensiones.getLongitud() < this.longitud)
			return false;
		else
			return true;
	}

	public double getAltura() {
		return altura;
	}

	public double getAnchura() {
		return anchura;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setAltura(double altura) throws DatoNoValidoException {
		this.altura = Comprobador.esDecimalValido(altura);
	}

	public void setAnchura(double anchura) throws DatoNoValidoException {
		this.anchura = Comprobador.esDecimalValido(anchura);
	}

	public void setLongitud(double longitud) throws DatoNoValidoException {
		this.longitud = Comprobador.esDecimalValido(longitud);
	}

	@Override
	public String toString() {
		return altura + " " + anchura + " " + longitud;
	}
}
