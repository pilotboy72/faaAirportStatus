/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yesifly.airportstatus.usfaa.data;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

import com.yesifly.util.BeanComparator;

/**
 * 
 * @author brtaylor
 */
public class Status implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8825120949057847123L;

	@XmlEnum(String.class)
	public enum Trend {
		@XmlEnumValue("Increasing")
		INCREASING, @XmlEnumValue("Decreasing")
		DECREASING, UNKNOWN
	};

	private String avgDelay;
	private String closureEnd;
	private String closureBegin;
	private String type;
	private String minDelay;
	private Trend trend = Trend.UNKNOWN;
	private String reason;
	private String maxDelay;
	private String endTime;

	@XmlElement(name = "AvgDelay")
	public String getAvgDelay() {
		return avgDelay;
	}

	public void setAvgDelay(String avgDelay) {
		this.avgDelay = avgDelay;
	}

	@XmlElement(name = "ClosureBegin")
	public String getClosureBegin() {
		return closureBegin;
	}

	public void setClosureBegin(String closureBegin) {
		this.closureBegin = closureBegin;
	}

	@XmlElement(name = "ClosureEnd")
	public String getClosureEnd() {
		return closureEnd;
	}

	public void setClosureEnd(String closureEnd) {
		this.closureEnd = closureEnd;
	}

	@XmlElement(name = "EndTime")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@XmlElement(name = "MaxDelay")
	public String getMaxDelay() {
		return maxDelay;
	}

	public void setMaxDelay(String maxDelay) {
		this.maxDelay = maxDelay;
	}

	@XmlElement(name = "MinDelay")
	public String getMinDelay() {
		return minDelay;
	}

	public void setMinDelay(String minDelay) {
		this.minDelay = minDelay;
	}

	@XmlElement(name = "Reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@XmlElement(name = "Trend")
	public Trend getTrend() {
		return trend;
	}

	public void setTrend(Trend trend) {
		this.trend = trend;
	}

	@XmlElement(name = "Type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		return BeanComparator.compare(this, obj);
	}

	public String delayDescription() {
		StringBuilder desc = new StringBuilder();

		if (isPopulated(getAvgDelay())) {
			desc.append("Average delay is ").append(getAvgDelay());
			if (isPopulated(getTrend().toString()) && getTrend() != Trend.UNKNOWN) {
				desc.append(" and ").append(getTrend());
			desc.append(".");
			}
		} else {
			if (isPopulated(getMinDelay()) && isPopulated(getMaxDelay())) {
				desc.append("Delays are between ").append(getMinDelay())
						.append(" and ").append(getMaxDelay());
				if (isPopulated(getTrend().toString())&& getTrend() != Trend.UNKNOWN) {
					desc.append(" and ").append(getTrend());
				}
				desc.append(".");
			}
		}
		return desc.toString();
	}

	private boolean isPopulated(String string) {
		return (string != null) && (string.length() > 0);
	}
}
