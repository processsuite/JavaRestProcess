/**
 * Destino.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Destino class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Destino implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean evalua;
	
	private Boolean firma;

	List<WfDest> destinos;
	
	private List<String> messages;
	
	private String msgObligatorios;
	
	private String focus;
	
	private Boolean allSelected;

	public Boolean getEvalua() {
		return evalua;
	}

	public void setEvalua(Boolean evalua) {
		this.evalua = evalua;
	}

	public Boolean getFirma() {
		return firma;
	}

	public void setFirma(Boolean firma) {
		this.firma = firma;
	}

	public List<WfDest> getDestinos() {
		if (destinos==null){
			destinos = new ArrayList<WfDest>();
		}
		return destinos;
	}

	public void setDestinos(List<WfDest> destinos) {
		this.destinos = destinos;
	}

	public List<String> getMessages() {
		if (messages==null){
			messages = new ArrayList<String>();
		}		
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public String getMsgObligatorios() {
		return msgObligatorios;
	}

	public void setMsgObligatorios(String msgObligatorios) {
		this.msgObligatorios = msgObligatorios;
	}

	public String getFocus() {
		return focus;
	}

	public void setFocus(String focus) {
		this.focus = focus;
	}

	public Boolean getAllSelected() {
		return allSelected;
	}

	public void setAllSelected(Boolean allSelected) {
		this.allSelected = allSelected;
	}

	@Override
	public String toString() {
		return "Destino [evalua=" + evalua + ", firma=" + firma + ", destinos="
				+ destinos + ", messages=" + messages + ", msgObligatorios="
				+ msgObligatorios + ", focus=" + focus + ", allSelected="
				+ allSelected + "]";
	}
	
}
