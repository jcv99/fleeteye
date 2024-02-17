package objeto;

import java.util.Objects;

import constante.ConstantesExcepciones;
import exception.DatoNoValidoException;
import exception.NIFNoValidoException;
import herramienta.Comprobador;

public abstract class Identidad {

	private String nif;

	public Identidad(Cif cif) throws NIFNoValidoException, DatoNoValidoException {
		if (Comprobador.esCIFValido(cif.getCif())) {
			this.nif = cif.getCif();
		} else {
			throw new NIFNoValidoException(ConstantesExcepciones.COMPROBADOR_ERROR_CIF_FORMATO + cif.getCif());
		}
	}

	public Identidad(Nif nif) {
		this.nif = Objects.requireNonNull(nif).getNif();
	}

	public String getNif() {
		return nif;
	}

	public void setCif(Cif cif) throws DatoNoValidoException, NIFNoValidoException {
		if (Comprobador.esCIFValido(cif.getCif())) {
			this.nif = cif.getCif();
		} else {
			throw new NIFNoValidoException(ConstantesExcepciones.COMPROBADOR_ERROR_CIF_FORMATO);
		}
	}

	public void setNif(Nif nif) {
		this.nif = Objects.requireNonNull(nif).getNif();
	}

	@Override
	public String toString() {
		return nif;
	}
}
