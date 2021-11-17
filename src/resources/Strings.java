package resources;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Strings {
	
	private static String BUNDLE_NAME ;
	private static ResourceBundle RESOURCE_BUNDLE;

	private Strings() {
	}
	
	public static String getTemplate(String key) {
		BUNDLE_NAME = "resources.templates";
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
		return getStringFromResources(key);
	}
	
	public static String getLatexCommand(String key) {
		BUNDLE_NAME = "resources.latex_commands";
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
		return getStringFromResources(key);
	}
	
	private static String getStringFromResources(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return "Missing String: <" + key + "> should be here...";
		}
	}
}
