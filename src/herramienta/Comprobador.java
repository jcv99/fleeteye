package herramienta;

import java.util.regex.Pattern;

import com.aeat.valida.Validador;

import constante.ConstantesExcepciones;
import exception.DatoNoValidoException;
import exception.MatriculaNoValidaException;
import exception.NIFNoValidoException;

public class Comprobador {

	private static String caracteres_de_control = "JABCDEFGHI";
	private static String letras_validas = "ABCDEFGHJPQRSUV";
	private static final Pattern patternClaveEntidadLetra = Pattern.compile("[ABCDEFGHJNPQRSUVW]");
	private static final Pattern patternClaveEntidadNumero = Pattern.compile("[ABEH]");
	private static final Pattern patternLetraControl = Pattern.compile("[PQRSW]");
	private static final Pattern patternLetras = Pattern.compile("[A-Z]");
	private static final Pattern patternMatriculaCamion = Pattern.compile("^[0-9]{1,4}(?!.*(LL|CH))[BCDFGHJKLMNPQRSTVWXYZ]{3}");

	private static final Pattern patternMatriculaRemolque = Pattern.compile("^R[0-9]{1,4}(?!.*(LL|CH))[BCDFGHJKLMNPQRSTVWXYZ]{3}");

	private static final Pattern patternNumeros = Pattern.compile("[0-9]");

	private static String tipo_de_letra = "PQS";

	private static String tipo_de_nombre = "ABEH";

	public static Boolean esCIFValido(String cif) throws DatoNoValidoException, NIFNoValidoException {

		return validarCIF(cif);
	}

	public static double esDecimalValido(double numero) throws DatoNoValidoException {
		if (!(numero >= 0))
			throw new DatoNoValidoException(ConstantesExcepciones.COMPROBADOR_ERROR_DECIMAL_NO_VALIDO);
		else
			return numero;
	}

	public static long esEnteroExtensoValido(long numero) throws DatoNoValidoException {
		if (!(numero > 0))
			throw new DatoNoValidoException(ConstantesExcepciones.COMPROBADOR_ERROR_ENTERO_NO_VALIDO);
		else
			return numero;
	}

	public static int esEnteroValido(int numero) throws DatoNoValidoException {
		if (!(numero >= 0))
			throw new DatoNoValidoException(ConstantesExcepciones.COMPROBADOR_ERROR_ENTERO_NO_VALIDO);
		else
			return numero;
	}

	public static String esMatriculaCamionValida(String matricula) throws MatriculaNoValidaException {
		if (!patternMatriculaCamion.matcher(matricula).matches())
			throw new MatriculaNoValidaException(matricula);
		return matricula;
	}
	
	public static String esMatriculaRemolqueValida(String matricula) throws MatriculaNoValidaException {
		if (!patternMatriculaRemolque.matcher(matricula).matches())
			throw new MatriculaNoValidaException(matricula);
		return matricula;
	}
	
	public static String esNifValido(String nif) throws NIFNoValidoException, DatoNoValidoException {
		nif = Comprobador.esStringValido(nif).trim().toUpperCase();
		validaTamanoNIF(nif);
		validaFormatoNIF(nif);
		validaLetraNIF(nif);
		return nif;
	}

	public static String esStringValido(String cadena) throws DatoNoValidoException {
		if (cadena == null || cadena.isBlank())
			throw new DatoNoValidoException(ConstantesExcepciones.COMPROBADOR_ERROR_CAMPO_VACIO);
		else
			return cadena;
	}

	private static void validaFormatoNIF(String nif) throws NIFNoValidoException {
		Pattern pattern = Pattern.compile("[0-9]+");
		if (!pattern.matcher(nif.substring(0, 8)).matches()) 
			throw new NIFNoValidoException(ConstantesExcepciones.COMPROBADOR_ERROR_NIF_FORMATO);
	}

	private static void validaLetraNIF(String nif) throws NIFNoValidoException {
		String[] letras = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V",
				"H", "L", "C", "K", "E" };
		int i;
		try {
			i = (Integer.parseInt(nif.substring(0, 8))) % 23;
		} catch (Exception e) {
			throw new NIFNoValidoException(ConstantesExcepciones.COMPROBADOR_ERROR_NIF_FORMATO);
		}
		if (!nif.endsWith(letras[i])) 
			throw new NIFNoValidoException(ConstantesExcepciones.COMPROBADOR_ERROR_NIF_FORMATO);
	}

	private static boolean validarCIF(String cif) {
		Validador valid = new Validador();
		if (valid.checkNif(cif) > 0) {
			return true;
		} else {
			return false;
		}
//		boolean resultado = false;
//		int digito_de_control;
//
//		/* Un CIF tiene que tener nueve d�gitos */
//		System.out.println(cif.length());
//		if (cif.length() == 9) {
//			System.out.println(".length bien");
//			/* Toma la primera letra del CIF */
//			char letra_CIF = cif.charAt(0);
//
//			/* Comprueba si la primera letra del CIF es v�lida */
//			if (letras_validas.indexOf(letra_CIF) >= 0) {
//				System.out.println("letras_validas.indexOf(letra_CIF)");
//				if (Character.isDigit(cif.charAt(8))) {
//					digito_de_control = Character.getNumericValue(cif.charAt(8));
//					if (tipo_de_letra.indexOf(letra_CIF) >= 0)
//						digito_de_control = 100;
//				} else {
//					digito_de_control = caracteres_de_control.indexOf(cif.charAt(8));
//					if (tipo_de_nombre.indexOf(letra_CIF) >= 0)
//						digito_de_control = 100;
//				}
//
//				int a = 0, b = 0, c = 0;
//				byte[] impares = { 0, 2, 4, 6, 8, 1, 3, 5, 7, 9 };
//
//				/* Calcula A y B. */
//				for (int t = 1; t <= 6; t = t + 2) {
//
//					/* Suma los pares */
//					a = a + Character.getNumericValue(cif.charAt(t + 1));
//					b = b + impares[Character.getNumericValue(cif.charAt(t))];
//				}
//				b = b + impares[Character.getNumericValue(cif.charAt(7))];
//				/* Calcula C */
//				c = 10 - ((a + b) % 10);
//				/* Compara C con los d�gitos de control */
//				resultado = (c == digito_de_control);
//			}
//		}
//		if (!resultado)
//			return false;
//		return resultado;
	}

	private static void validaTamanoNIF(String nif) throws NIFNoValidoException {
		if (nif.length() != 9) 
			throw new NIFNoValidoException(ConstantesExcepciones.COMPROBADOR_ERROR_NIF_LONGITUD);
	}
}
