package com.process.domain.document2;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "OPCIONES")
@XmlAccessorType(XmlAccessType.NONE)
public class Opciones {
	
	@XmlElement(name = "OPCION")
	private List<Opcion> opcion;
	
	@XmlAttribute(name = "multiple")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean multiple;

	@XmlAttribute(name = "origen")
	private String origen;
	
	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public List<Opcion> getOpcion() {
		return opcion;
	}

	public void setOpcion(List<Opcion> opcion) {
		this.opcion = opcion;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}
	
	

}
