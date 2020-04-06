/**
 * Email.java 
 *
 */
package com.process.domain.user;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Email class for user Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Email implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer nuMens;
	
	private Integer nuDoc;
	
	private String fecha;
	
	private Integer status;
	
	private String de;
	
	private String para;
	
	private String asunto;
	
	private String error;

	public Integer getNuMens() {
		return nuMens;
	}

	public void setNuMens(Integer nuMens) {
		this.nuMens = nuMens;
	}

	public Integer getNuDoc() {
		return nuDoc;
	}

	public void setNuDoc(Integer nuDoc) {
		this.nuDoc = nuDoc;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "Email [nuMens=" + nuMens + ", nuDoc=" + nuDoc + ", fecha="
				+ fecha + ", status=" + status + ", de=" + de + ", para="
				+ para + ", asunto=" + asunto + ", error=" + error + "]";
	}
	
}
