/**
 * Conversacion.java 
 *
 */
package com.process.domain.user;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Conversacion class for user Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Conversacion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nbWf;
	
	private Integer wfp;
	
	private Integer wfa;
	
	private Integer total;

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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Conversacion [nbWf=" + nbWf + ", wfp=" + wfp + ", wfa=" + wfa
				+ ", total=" + total + "]";
	}	
	
}
