/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yesifly.airportstatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.yesifly.airportstatus.usfaa.provider.FAAAirportStatusDataProvider;

/**
 *
 * @author brtaylor
 */
public class AirportStatusProviderFactory {

	private static final String PROPERTIES_BASE = AirportStatusProviderFactory.class.getName();
	private static final String DELAY_DATA_PROVIDER_IMPL = PROPERTIES_BASE + ".Provider";

	static public AirportStatusProvider newFAAServiceDataProvider(String iataCode) {
		return new FAAAirportStatusDataProvider(iataCode);
	}

	static public AirportStatusProvider newDataProvider(String iataCode, Properties prop) throws AirportStatusException {
		String className;
		AirportStatusProvider provider = null;

		try {
			className = prop.getProperty(DELAY_DATA_PROVIDER_IMPL);
			provider = (AirportStatusProvider) AirportStatusProviderFactory.class.getClassLoader().loadClass(className).newInstance();
			provider.setAirportIdentifier(iataCode);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException reflectionExceptions) {
			throw new AirportStatusException(reflectionExceptions);
		}
		return provider;
	}

	static public AirportStatusProvider newDataProvider(String iataCode) throws AirportStatusException {
		InputStream stream = null;
		Properties props = new Properties();

		try {
			stream = AirportStatusProviderFactory.class.getClassLoader().getResourceAsStream("dataprovider.properties");
			props.load(stream);
		} catch (IOException ioEx) {
			throw new AirportStatusException(ioEx);
		}
		return newDataProvider(iataCode, props);
	}
}
