/**
 * Puesto.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Puesto class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Puesto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nbUsrReemp;
	
	private String nbUsrAct;
	
	private String nbPuesto;
	
	private Boolean seleccionado;
	
	private String value;
	
	// ****** use only for send document 
	private String mail;
	
	private Integer nuDoc;
	
	private Integer nuInstn;
	// ******	
	
	public String getNbUsrReemp() {
		return nbUsrReemp;
	}

	public void setNbUsrReemp(String nbUsrReemp) {
		this.nbUsrReemp = nbUsrReemp;
	}

	public String getNbUsrAct() {
		return nbUsrAct;
	}

	public void setNbUsrAct(String nbUsrAct) {
		this.nbUsrAct = nbUsrAct;
	}

	public String getNbPuesto() {
		return nbPuesto;
	}

	public void setNbPuesto(String nbPuesto) {
		this.nbPuesto = nbPuesto;
	}

	public Boolean getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(Boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getNuDoc() {
		return nuDoc;
	}

	public void setNuDoc(Integer nuDoc) {
		this.nuDoc = nuDoc;
	}

	public Integer getNuInstn() {
		return nuInstn;
	}

	public void setNuInstn(Integer nuInstn) {
		this.nuInstn = nuInstn;
	}

	@Override
	public String toString() {
		return "Puesto [nbUsrReemp=" + nbUsrReemp + ", nbUsrAct=" + nbUsrAct
				+ ", nbPuesto=" + nbPuesto + ", seleccionado=" + seleccionado
				+ ", value=" + value + ", mail=" + mail + ", nuDoc=" + nuDoc
				+ ", nuInstn=" + nuInstn + "]";
	}
	
}
