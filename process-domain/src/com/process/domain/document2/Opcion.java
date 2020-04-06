package com.process.domain.document2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "OPCION")
@XmlAccessorType(XmlAccessType.NONE)
public class Opcion {

	@XmlAttribute(name = "seleccionado")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean seleccionado;
	
	@XmlAttribute(name = "codigo")
	private String codigo;
	
	@XmlValue
	private String value;
	
	public Boolean getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(Boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	
	
}
