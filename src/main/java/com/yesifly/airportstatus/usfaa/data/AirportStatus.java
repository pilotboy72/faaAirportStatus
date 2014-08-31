/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yesifly.airportstatus.usfaa.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.yesifly.util.BeanComparator;

/**
 * 
 * @author brtaylor
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name="AirportStatus")
public class AirportStatus implements Serializable, Cloneable {
	private static final long serialVersionUID = 1375598365405801983L;
	private String name;
	private String icao;
	private boolean delay;
	private String iata;
	private Status status;
	private Weather weather;

	public AirportStatus() {
	}

	@XmlElement(name = "Delay")
	public boolean isDelay() {
		return delay;
	}

	public void setDelay(boolean delay) {
		this.delay = delay;
	}

	public void setDelay(String delay) {
		this.delay = Boolean.parseBoolean(delay);
	}

	@XmlElement(name = "IATA")
	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	@XmlElement(name = "ICAO")
	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "Status")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@XmlElement(name = "Weather")
	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public boolean compareDelayStatus(AirportStatus obj) {
		if (obj == null)
			return false;
		return BeanComparator.compare(status, obj.getStatus());
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public String toString() {
		return String.format("Airport %s delay status: %s", getIata(), isDelay());
	}
}
