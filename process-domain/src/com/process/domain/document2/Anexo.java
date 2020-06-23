package com.process.domain.document2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ANEXO")
@XmlAccessorType(XmlAccessType.NONE)
public class Anexo {
	@XmlAttribute(name="nuevo")
	private String nuevo;
	
	@XmlAttribute(name="autor")
	private String autor;
	
	@XmlAttribute(name="asunto")
	private String asunto;
	
	@XmlAttribute(name="descripcion")
	private String descripcion;
	
	@XmlAttribute(name="borrado")
	private String borrado;
	
	@XmlAttribute(name="href")
	private String href;
	
	@XmlAttribute(name="numero")
	private String numero;

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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numerO) {
		this.numero = numerO;
	}
	
		

}
