/**
 * Group.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Group class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Group implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private Boolean visible;
	
	private List<Field> fields;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Field> getFields() {
		if (fields==null){
			fields = new ArrayList<Field>();
		}
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	@Override
	public String toString() {
		return "Group [nombre=" + nombre + ", visible=" + visible + ", fields="
				+ fields + "]";
	}
	
}
