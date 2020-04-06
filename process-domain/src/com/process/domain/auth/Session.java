/**
 * Session.java 
 *
 */
package com.process.domain.auth;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Session class for auth Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ticket;
	private Integer statusCode;
	private Integer sessionCaida;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getSessionCaida() {
		return sessionCaida;
	}

	public void setSessionCaida(Integer sessionCaida) {
		this.sessionCaida = sessionCaida;
	}

	@Override
	public String toString() {
		return "Session [ticket=" + ticket + ", statusCode=" + statusCode
				+ ", sessionCaida=" + sessionCaida + "]";
	}
	
}
