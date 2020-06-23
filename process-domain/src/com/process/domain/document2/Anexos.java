package com.process.domain.document2;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ANEXOS")
@XmlAccessorType(XmlAccessType.NONE)
public class Anexos {
	@XmlElement(name="ANEXO")
	private List<Anexo> anexo;

	public List<Anexo> getAnexo() {
		return anexo;
	}

	public void setAnexo(List<Anexo> anexo) {
		this.anexo = anexo;
	}
	
	
}
