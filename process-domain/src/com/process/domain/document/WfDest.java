/**
 * WfDest.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain WfDest class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class WfDest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nbProceso;
	
	private String nbWf;
	
	private Integer wfa;
	
	private Boolean e;
	
	private Boolean visible;

	List<Puesto> puestos;
	
	private Boolean anySelected;
	
	private String valueSelected;
	
	// ****** use only for send document 
	private Boolean copia;
	
	private Boolean copiaPorEmail;
	// ******

	public String getNbProceso() {
		return nbProceso;
	}

	public void setNbProceso(String nbProceso) {
		this.nbProceso = nbProceso;
	}

	public String getNbWf() {
		return nbWf;
	}

	public void setNbWf(String nbWf) {
		this.nbWf = nbWf;
	}

	public Integer getWfa() {
		return wfa;
	}

	public void setWfa(Integer wfa) {
		this.wfa = wfa;
	}

	public Boolean getE() {
		return e;
	}

	public void setE(Boolean e) {
		this.e = e;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public List<Puesto> getPuestos() {
		if (puestos==null){
			puestos = new ArrayList<Puesto>();
		}
		return puestos;
	}

	public void setPuestos(List<Puesto> puestos) {
		this.puestos = puestos;
	}

	public Boolean getAnySelected() {
		return anySelected;
	}

	public void setAnySelected(Boolean anySelected) {
		this.anySelected = anySelected;
	}

	public String getValueSelected() {
		return valueSelected;
	}

	public void setValueSelected(String valueSelected) {
		this.valueSelected = valueSelected;
	}

	public Boolean getCopia() {
		return copia;
	}

	public void setCopia(Boolean copia) {
		this.copia = copia;
	}

	public Boolean getCopiaPorEmail() {
		return copiaPorEmail;
	}

	public void setCopiaPorEmail(Boolean copiaPorEmail) {
		this.copiaPorEmail = copiaPorEmail;
	}

	@Override
	public String toString() {
		return "WfDest [nbProceso=" + nbProceso + ", nbWf=" + nbWf + ", wfa="
				+ wfa + ", e=" + e + ", visible=" + visible + ", puestos="
				+ puestos + ", anySelected=" + anySelected + ", valueSelected="
				+ valueSelected + ", copia=" + copia + ", copiaPorEmail="
				+ copiaPorEmail + "]";
	}
	
}
