/**
 * SendMsg.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain SendMsg class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class SendMsg implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String acc;

	private List<String> messages;
	
	List<WfDest> destinos;
	
	private Boolean sendError;
	
	private String msgObligatorios;
	
	private String focus;	

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
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

	public List<WfDest> getDestinos() {
		if (destinos==null){
			destinos = new ArrayList<WfDest>();
		}		
		return destinos;
	}

	public void setDestinos(List<WfDest> destinos) {
		this.destinos = destinos;
	}

	public Boolean getSendError() {
		return sendError;
	}

	public void setSendError(Boolean sendError) {
		this.sendError = sendError;
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

	@Override
	public String toString() {
		return "SendMsg [acc=" + acc + ", messages=" + messages + ", destinos="
				+ destinos + ", sendError=" + sendError + ", msgObligatorios="
				+ msgObligatorios + ", focus=" + focus + "]";
	}
	
}
