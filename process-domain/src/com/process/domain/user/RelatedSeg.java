/**
 * RelatedSeg.java 
 *
 */
package com.process.domain.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain RelatedSeg class for user Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class RelatedSeg implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer nuDoc;

	private String nbWfp;
	
	private String nbWf;
	
	private Integer status;
	
	private String fecha;

	private List<RelatedSeg> childrens;

	public Integer getNuDoc() {
		return nuDoc;
	}

	public void setNuDoc(Integer nuDoc) {
		this.nuDoc = nuDoc;
	}

	public String getNbWfp() {
		return nbWfp;
	}

	public void setNbWfp(String nbWfp) {
		this.nbWfp = nbWfp;
	}

	public String getNbWf() {
		return nbWf;
	}

	public void setNbWf(String nbWf) {
		this.nbWf = nbWf;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<RelatedSeg> getChildrens() {
		if (childrens==null){
			childrens = new ArrayList<RelatedSeg>();
		}
		return childrens;
	}

	public void setChildrens(List<RelatedSeg> childrens) {
		this.childrens = childrens;
	}

	@Override
	public String toString() {
		return "RelatedSeg [nuDoc=" + nuDoc + ", nbWfp=" + nbWfp + ", nbWf="
				+ nbWf + ", status=" + status + ", fecha=" + fecha
				+ ", childrens=" + childrens + "]";
	}
	
}
