package com.process.domain.console;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "opciones")
@XmlAccessorType(XmlAccessType.NONE)
public class Opciones {
	
	@XmlElement(name = "opcion")
	private List<Opcion> opcion;
	
	public List<Opcion> getOpcion() {
		return opcion;
	}

	public void setOpcion(List<Opcion> opcion) {
		this.opcion = opcion;
	}	

}
