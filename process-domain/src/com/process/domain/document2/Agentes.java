package com.process.domain.document2;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AGENTES")
@XmlAccessorType(XmlAccessType.NONE)
public class Agentes {
	@XmlElement(name="AGENTE")
	private List<Agente> agente;

	public List<Agente> getAgente() {
		return agente;
	}

	public void setAgente(List<Agente> agente) {
		this.agente = agente;
	}
	
	
}
