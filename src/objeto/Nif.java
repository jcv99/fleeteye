package objeto;

import exception.DatoNoValidoException;
import exception.NIFNoValidoException;
import herramienta.Comprobador;

public class Nif {
	private String nif;

	public Nif(String nif) throws NIFNoValidoException, DatoNoValidoException {
		this.nif = Comprobador.esNifValido(nif);
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) throws NIFNoValidoException, DatoNoValidoException {
		this.nif = Comprobador.esNifValido(nif);
	}
}
