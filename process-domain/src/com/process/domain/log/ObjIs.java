package com.process.domain.log;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="is")
public class ObjIs implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@XmlElement(name="i")
	private List<ObjI> listI;

	public List<ObjI> getListI() {
		return listI;
	}

	public void setListI(List<ObjI> listI) {
		this.listI = listI;
	}
	
	
}
