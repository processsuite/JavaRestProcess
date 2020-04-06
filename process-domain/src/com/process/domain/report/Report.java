/**
 * Report.java 
 *
 */
package com.process.domain.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Report class for report Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Report implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private Integer wfp;
	
	private Integer wf;
	
	private String tipo;
	
	private List<Report> childrens;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getWfp() {
		return wfp;
	}

	public void setWfp(Integer wfp) {
		this.wfp = wfp;
	}

	public Integer getWf() {
		return wf;
	}

	public void setWf(Integer wf) {
		this.wf = wf;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Report> getChildrens() {
		if (childrens==null){
			childrens = new ArrayList<Report>();
		}
		return childrens;
	}

	public void setChildrens(List<Report> childrens) {
		this.childrens = childrens;
	}

	@Override
	public String toString() {
		return "Report [nombre=" + nombre + ", wfp=" + wfp + ", wf=" + wf
				+ ", tipo=" + tipo + ", childrens=" + childrens + "]";
	}	
	
}
