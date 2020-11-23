package g16.model;

import java.util.*;

public class InformacionProperties {

	/*
	 * Esta clase tiene las propiedades de JMS
	 */
	
	private static String strQCF;

	private static String strQueue;

	private static String strQueueAsincrona;

	private static final String nombreProperties = "InfoAplicacion";

	// **************************************************
	public InformacionProperties() {
		super();
	}

	// **************************************************
	public static String getQCF() {

		if (strQCF == null)
			cagarProperties();

		return strQCF;
	}

	// **************************************************
	public static String getQueue() {

		if (strQueue == null)
			cagarProperties();

		return strQueue;
	}

	// **************************************************
	

	// **************************************************
	private static void cagarProperties() throws MissingResourceException {

		PropertyResourceBundle appProperties = null;

		try {

			appProperties = (PropertyResourceBundle) PropertyResourceBundle
					.getBundle(nombreProperties);

			strQCF = appProperties.getString("Info.strQCF");
			strQueue = appProperties.getString("Info.strQueue");
			

		} catch (MissingResourceException e) {

			throw e;
		}

	}
}