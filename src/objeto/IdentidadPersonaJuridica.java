package objeto;

import exception.DatoNoValidoException;
import exception.NIFNoValidoException;
import herramienta.Comprobador;

public class IdentidadPersonaJuridica extends Identidad {

	private String actividadEconomica;
	private String razonSocial;

	public IdentidadPersonaJuridica(Cif cif, String razonSocial, String actividadEconomica)
			throws NIFNoValidoException, DatoNoValidoException {
		super(cif);
		this.razonSocial = Comprobador.esStringValido(razonSocial);
		this.actividadEconomica = Comprobador.esStringValido(actividadEconomica);
	}

	public String getActividadEconomica() {
		return actividadEconomica;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setActividadEconomica(String actividadEconomica) throws DatoNoValidoException {
		this.actividadEconomica = Comprobador.esStringValido(actividadEconomica);
	}

	public void setRazonSocial(String razonSocial) throws DatoNoValidoException {
		this.razonSocial = Comprobador.esStringValido(razonSocial);
	}

	@Override
	public String toString() {
		return super.getNif() + " " + razonSocial + " " + actividadEconomica;
	}
}
