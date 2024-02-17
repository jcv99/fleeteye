package objeto;

import exception.DatoNoValidoException;
import herramienta.Comprobador;

public class Cif {
	private String cif;

	public Cif(String cif) throws DatoNoValidoException {
		this.cif = Comprobador.esStringValido(cif);
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) throws DatoNoValidoException {
		this.cif = Comprobador.esStringValido(cif);
	}
}
