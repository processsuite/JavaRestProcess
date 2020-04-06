/**
 * Anexo.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Anexo class for Document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Anexo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nuevo;
	
	private String autor;
	
	private String asunto;
	
	private String descripcion;
	
	private String borrado;
	
	private String href;
	
	private Integer numero;
	
	private Boolean selected;

	public String getNuevo() {
		return nuevo;
	}

	public void setNuevo(String nuevo) {
		this.nuevo = nuevo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getBorrado() {
		return borrado;
	}

	public void setBorrado(String borrado) {
		this.borrado = borrado;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "Anexo [nuevo=" + nuevo + ", autor=" + autor + ", asunto="
				+ asunto + ", descripcion=" + descripcion + ", borrado="
				+ borrado + ", href=" + href + ", numero=" + numero
				+ ", selected=" + selected + "]";
	}
	
}
