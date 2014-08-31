/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yesifly.airportstatus.usfaa.provider;

import com.yesifly.airportstatus.AirportStatusProvider;
import com.yesifly.airportstatus.AirportStatusRuntimeException;
import com.yesifly.airportstatus.usfaa.data.AirportStatus;
import org.apache.cxf.jaxrs.client.WebClient;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author brtaylor
 */
public class FAAAirportStatusDataProvider implements AirportStatusProvider<AirportStatus> {
	private static String URL_FORMAT = "http://services.faa.gov/airport/status/";
	private static String PROP_URL = "com.yesifly.airportstatus.usfaa.provider.URL";
	
	private Properties config;
	private WebClient webClient;
	private String airportIdentifier;

	public FAAAirportStatusDataProvider() {
		config = new Properties();
		configureDefaultProperties();
	}

	public FAAAirportStatusDataProvider(String airportIdentifier) {
		this();
		setAirportIdentifier(airportIdentifier);
	}

	private void configureDefaultProperties() {
		config.put(PROP_URL, URL_FORMAT);
	}

	@Override
	public void configure(Properties props) {
		config.putAll(props);
	}

	@Override
	public void setAirportIdentifier(String identifier) {
		if (null != airportIdentifier) {
			throw new AirportStatusRuntimeException("Cannot change the airport identifier once set.");
		}
		this.airportIdentifier = identifier;
		webClient = WebClient.create(config.getProperty(PROP_URL)).path(airportIdentifier.toUpperCase()).accept("application/xml");
	}

	@Override
	public AirportStatus update() throws IOException {
		AirportStatus status = null;

		status = webClient.get(AirportStatus.class);

		return status;
	}
}
