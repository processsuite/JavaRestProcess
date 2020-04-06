/**
 * EventAgent.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain EventAgent class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class EventAgent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<String> cabeceras;
	
	private List<ListEvent> filas;
	
	private String xmlData;
	
	private Boolean msgError;
	
	private List<String> messages;

	public void setFilas(List<ListEvent> filas) {
		this.filas = filas;
	}
	
	public List<ListEvent> getFilas() {
		if (filas==null){
			filas = new ArrayList<ListEvent>();
		}
		return filas;
	}
	
	public List<String> getCabeceras() {
		if (cabeceras==null){
			cabeceras = new ArrayList<String>();
		}		
		return cabeceras;
	}

	public void setCabeceras(List<String> cabeceras) {
		this.cabeceras = cabeceras;
	}

	public String getXmlData() {
		return xmlData;
	}

	public void setXmlData(String xmlData) {
		this.xmlData = xmlData;
	}

	public Boolean getMsgError() {
		return msgError;
	}

	public void setMsgError(Boolean msgError) {
		this.msgError = msgError;
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

	@Override
	public String toString() {
		return "EventAgent [cabeceras=" + cabeceras + ", filas=" + filas
				+ ", xmlData=" + xmlData + ", msgError=" + msgError
				+ ", messages=" + messages + "]";
	}
	
}
