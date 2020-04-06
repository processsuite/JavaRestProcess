package com.process.domain.log;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;


@XmlRootElement(name="i")
public class ObjI implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@XmlAttribute(name="t")
	private String t;
	
	@XmlValue
	private String value;

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
