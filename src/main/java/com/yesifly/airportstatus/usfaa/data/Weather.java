/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yesifly.airportstatus.usfaa.data;

import java.beans.Transient;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import com.yesifly.util.BeanComparator;

/**
 * 
 * @author brtaylor
 */
public class Weather implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -953482552073007630L;
	private String weather;
	private String updated;
	private String wind;
	private String temp;
	private String visibility;
	private String error;

	public Weather() {

	}

	@XmlElement(name = "Updated")
	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	@XmlElement(name = "Weather")
	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	@XmlElement(name="Temp")
	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	@XmlElement(name="Visibility")
	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	@XmlElement(name="Wind")
	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	@Override
	public boolean equals(Object obj) {
		return BeanComparator.compare(this, obj);
	}

	@XmlElement(name="Error")
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	@Transient
	public boolean isError()  {
		return (error != null) && (error.trim().length() > 0);
	}
}
