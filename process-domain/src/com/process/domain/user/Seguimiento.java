/**
 * Seguimiento.java 
 *
 */
package com.process.domain.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Seguimiento class for user Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Seguimiento implements Serializable {

	private static final long serialVersionUID = 1L;

	private String feIni;
	
	private String feFin;
	
	private String fecha;
	
	private List<InstSeg> inst;
		
	public String getFeIni() {
		return feIni;
	}

	public void setFeIni(String feIni) {
		this.feIni = feIni;
	}

	public String getFeFin() {
		return feFin;
	}

	public void setFeFin(String feFin) {
		this.feFin = feFin;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<InstSeg> getInst() {
		if (inst==null){
			inst = new ArrayList<InstSeg>();
		}
		return inst;
	}

	public void setInst(List<InstSeg> inst) {
		this.inst = inst;
	}

	@Override
	public String toString() {
		return "Seguimiento [feIni=" + feIni + ", feFin=" + feFin + ", fecha="
				+ fecha + ", inst=" + inst + "]";
	}	
	
}
