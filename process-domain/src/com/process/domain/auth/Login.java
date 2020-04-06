/**
 * Login.java 
 *
 */
package com.process.domain.auth;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Login class for auth Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;

	private String usuario;

	private String clave;

	private String ambiente;

	private Integer sessionId;
	
	private String ip;
	
	private Integer validaExt;
	
	private Integer sesionCaida;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getValidaExt() {
		return validaExt;
	}

	public void setValidaExt(Integer validaExt) {
		this.validaExt = validaExt;
	}

	public Integer getSesionCaida() {
		return sesionCaida;
	}

	public void setSesionCaida(Integer sesionCaida) {
		this.sesionCaida = sesionCaida;
	}

	@Override
	public String toString() {
		return "Login [usuario=" + usuario + ", clave=" + clave + ", ambiente="
				+ ambiente + ", sessionId=" + sessionId + ", ip=" + ip
				+ ", validaExt=" + validaExt + ", sesionCaida=" + sesionCaida
				+ "]";
	}
}
