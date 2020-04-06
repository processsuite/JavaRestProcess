/**
 * Servicio.java 
 *
 */
package com.process.domain.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Servicio class for user Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Servicio implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private Integer wf;
	
	private Integer frmn;
	
	private List<Servicio> childrens;

	private String info;
	
	
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getWf() {
		return wf;
	}

	public void setWf(Integer wf) {
		this.wf = wf;
	}

	public Integer getFrmn() {
		return frmn;
	}

	public void setFrmn(Integer frmn) {
		this.frmn = frmn;
	}

	public List<Servicio> getChildrens() {
		if (childrens==null){
			childrens = new ArrayList<Servicio>();
		}
		return childrens;
	}

	public void setChildrens(List<Servicio> childrens) {
		this.childrens = childrens;
	}

	@Override
	public String toString() {
		return "Servicio [nombre=" + nombre + ", wf=" + wf + ", frmn=" + frmn
				+ ", children=" + childrens + "]";
	}	
	
}
