/**
 * Agent.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Agent class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Agent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String cdAg;
	
	private String tipo;
	
	private String descripcion;
	
	private String contexto;
	
	private String href;

	public String getCdAg() {
		return cdAg;
	}

	public void setCdAg(String cdAg) {
		this.cdAg = cdAg;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getContexto() {
		return contexto;
	}

	public void setContexto(String contexto) {
		this.contexto = contexto;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public String toString() {
		return "Agent [cdAg=" + cdAg + ", tipo=" + tipo + ", descripcion="
				+ descripcion + ", contexto=" + contexto + ", href=" + href
				+ "]";
	}
	
}
