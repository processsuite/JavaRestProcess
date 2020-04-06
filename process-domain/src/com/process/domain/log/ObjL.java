package com.process.domain.log;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "logs")
@XmlAccessorType(XmlAccessType.NONE)
public class ObjL implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "c")
	private List<ObjC> objc;
	
	@XmlElement(name = "codError")
	public String CodError;
	@XmlElement(name = "descError")
	public String DescError;

	public List<ObjC> getObjc() {
		return objc;
	}

	public void setObjc(List<ObjC> objc) {
		this.objc = objc;
	}

	public String getCodError() {
		return CodError;
	}

	public void setCodError(String codError) {
		CodError = codError;
	}

	public String getDescError() {
		return DescError;
	}

	public void setDescError(String descError) {
		DescError = descError;
	}
	
	
	
	
}
