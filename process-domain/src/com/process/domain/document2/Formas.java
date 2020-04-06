package com.process.domain.document2;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FORMAS")
@XmlAccessorType(XmlAccessType.NONE)
public class Formas {
	@XmlElement(name="FORMA")
	private List<Forma> forma;
	
	public List<Forma> getFormas() {
		return forma;
	}

	public void setFormas(List<Forma> forma) {
		this.forma = forma;
	}
}
