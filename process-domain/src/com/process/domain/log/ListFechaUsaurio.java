package com.process.domain.log;


import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListFechaUsaurio implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fecha;
	private ArrayList<String> usuarios;
	private ArrayList<String> listEvent;
	private ArrayList<String> lisEmail;
	
	public String getfecha() {
		return fecha;
	}
	public void setFecha(String nombre) {
		this.fecha = nombre;
	}
	public ArrayList<String> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(ArrayList<String> usuarios) {
		this.usuarios = usuarios;
	}
	public ArrayList<String> getListEvent() {
		return listEvent;
	}
	public void setListEvent(ArrayList<String> listEvent) {
		this.listEvent = listEvent;
	}
	public ArrayList<String> getLisEmail() {
		return lisEmail;
	}
	public void setLisEmail(ArrayList<String> lisEmail) {
		this.lisEmail = lisEmail;
	}
	
	
	
	
	
	
	
	
}
