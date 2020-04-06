package com.process.domain.document2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AGENTE")
@XmlAccessorType(XmlAccessType.NONE)
public class Agente {
	
	@XmlAttribute(name="cd_ag")
	private String cdAg;
	
	@XmlAttribute(name="tipo")
	private String tipo;

	@XmlAttribute(name="descripcion")
	private String descripcion;

	@XmlAttribute(name="contexto")
	private String contexto;

	@XmlAttribute(name="href")
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
