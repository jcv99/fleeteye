package herramienta;

import constante.Messages;

public class ComprobadorSO {

	private static String OS = System.getProperty(Messages.getString("ComprobadorSO.0")).toLowerCase(); //$NON-NLS-1$

	public static String getOS() {
		if (isWindows()) {
			return Messages.getString("ComprobadorSO.1"); //$NON-NLS-1$
		} else if (isMac()) {
			return Messages.getString("ComprobadorSO.2"); //$NON-NLS-1$
		} else if (isUnix()) {
			return Messages.getString("ComprobadorSO.3"); //$NON-NLS-1$
		} else if (isSolaris()) {
			return Messages.getString("ComprobadorSO.4"); //$NON-NLS-1$
		} else {
			return Messages.getString("ComprobadorSO.5"); //$NON-NLS-1$
		}
	}

	public static boolean isMac() {
		return OS.contains(Messages.getString("ComprobadorSO.6")); //$NON-NLS-1$
	}

	public static boolean isSolaris() {
		return OS.contains(Messages.getString("ComprobadorSO.7")); //$NON-NLS-1$
	}

	public static boolean isUnix() {
		return (OS.contains(Messages.getString("ComprobadorSO.8")) || OS.contains(Messages.getString("ComprobadorSO.9")) //$NON-NLS-1$ //$NON-NLS-2$
				|| OS.contains(Messages.getString("ComprobadorSO.10"))); //$NON-NLS-1$
	}

	public static boolean isWindows() {
		return OS.contains(Messages.getString("ComprobadorSO.11")); //$NON-NLS-1$
	}

}