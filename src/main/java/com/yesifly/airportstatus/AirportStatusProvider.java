/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yesifly.airportstatus;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author brtaylor
 */
public interface AirportStatusProvider<T> {

	public void setAirportIdentifier(String identifier);

	/**
	 * Returns the raw XML that will be parsed.
	 *
	 * @return
	 * @throws java.io.IOException
	 */
	public T update() throws IOException;
	public void configure(Properties props);
}
