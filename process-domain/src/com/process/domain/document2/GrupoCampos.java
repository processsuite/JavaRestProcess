package com.process.domain.document2;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="GRUPO_CAMPOS")
@XmlAccessorType(XmlAccessType.NONE)
public class GrupoCampos {

	@XmlAttribute(name="nombre")
	private String nombre;
	
	@XmlElement(name="CAMPO")
	private List<Campo> campo;
	
	@XmlElement(name="visible")
	private Boolean visible;
	 
	
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public List<Campo> getCampo() {
		return campo;
	}

	public void setCampo(List<Campo> campo) {
		this.campo = campo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
