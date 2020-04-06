package com.process.domain.document2;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FILA")
@XmlAccessorType(XmlAccessType.NONE)
public class Fila {
	@XmlAttribute(name="numero")
	private int numero;
	
	@XmlAttribute(name = "real")
	private int real;
	
	@XmlElement(name = "CAMPO")
	private List<Campo> campo;

	@XmlAttribute(name="selChk")
	private Boolean selChk;
	
	public Boolean getSelChk() {
		return selChk;
	}

	public void setSelChk(Boolean selChk) {
		this.selChk = selChk;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getReal() {
		return real;
	}

	public void setReal(int real) {
		this.real = real;
	}

	public List<Campo> getCampo() {
		return campo;
	}

	public void setCampo(List<Campo> campo) {
		this.campo = campo;
	}

	
	
	
}
