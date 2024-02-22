package constante;

//import java.util.MissingResourceException;
//import java.util.ResourceBundle;
//
//public class Messages {
//	private static final String BUNDLE_NAME = "constante.messages"; //$NON-NLS-1$
//
//	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
//
//	private Messages() {
//	}
//
//	public static String getString(String key) {
//		try {
//			return RESOURCE_BUNDLE.getString(key);
//		} catch (MissingResourceException e) {
//			return '!' + key + '!';
//		}
//	}
//}
//
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {

	/**
	 * This class provides localized messages from a ResourceBundle.
	 */
	private static final String BUNDLE_NAME = "constante.messages";
	private static final String DEFAULT_MISSING_MESSAGE = "!{key}!"; // Use a constant for the default message

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Messages() {
		// Private constructor to prevent instantiation
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return DEFAULT_MISSING_MESSAGE.replace("{key}", key); // Use the constant and replace the placeholder
		}
	}
}
