/**
 * Proceso.java 
 *
 */
package com.process.domain.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Proceso class for user Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Proceso implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nbWf;
	
	private Integer wfp;
	
	private Integer wfa;
	
	private List<Conversacion> conversaciones;
	
	public String getNbWf() {
		return nbWf;
	}

	public void setNbWf(String nbWf) {
		this.nbWf = nbWf;
	}

	public Integer getWfp() {
		return wfp;
	}

	public void setWfp(Integer wfp) {
		this.wfp = wfp;
	}

	public Integer getWfa() {
		return wfa;
	}

	public void setWfa(Integer wfa) {
		this.wfa = wfa;
	}

	public List<Conversacion> getConversaciones() {
		if (conversaciones==null){
			conversaciones = new ArrayList<Conversacion>();
		}
		return conversaciones;
	}

	public void setConversaciones(List<Conversacion> conversaciones) {
		this.conversaciones = conversaciones;
	}

	@Override
	public String toString() {
		return "Proceso [nbWf=" + nbWf + ", wfp=" + wfp + ", wfa=" + wfa
				+ ", conversaciones=" + conversaciones + "]";
	}	
	
}
