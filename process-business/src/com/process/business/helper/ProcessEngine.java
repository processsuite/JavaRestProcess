/**
 * ProcessEngine.java 
 *
 */
package com.process.business.helper;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain ProcessEngine class for auth Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class ProcessEngine implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ticket;

	private c_Process engine;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public c_Process getEngine() {
		return engine;
	}

	public void setEngine(c_Process engine) {
		this.engine = engine;
	}

	@Override
	public String toString() {
		return "ProcessEngine [ticket=" + ticket + ", engine=" + engine.hashCode() + "]";
	}

}
