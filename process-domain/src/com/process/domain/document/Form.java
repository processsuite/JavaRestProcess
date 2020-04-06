/**
 * Form.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Form class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Form implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer nuDoc;
	
	private String nombre;
	
	private String imagen;
	
	private Double ancho;
	
	private Double alto;
	
	private Integer frmn;
	
	private Integer frmv;
	
	private Boolean defult;
	
	private List<Group> groups;

	public Integer getNuDoc() {
		return nuDoc;
	}

	public void setNuDoc(Integer nuDoc) {
		this.nuDoc = nuDoc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Double getAncho() {
		return ancho;
	}

	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

	public Double getAlto() {
		return alto;
	}

	public void setAlto(Double alto) {
		this.alto = alto;
	}

	public Integer getFrmn() {
		return frmn;
	}

	public void setFrmn(Integer frmn) {
		this.frmn = frmn;
	}

	public Integer getFrmv() {
		return frmv;
	}

	public void setFrmv(Integer frmv) {
		this.frmv = frmv;
	}

	public Boolean getDefult() {
		return defult;
	}

	public void setDefult(Boolean defult) {
		this.defult = defult;
	}

	public List<Group> getGroups() {
		if (groups==null){
			groups = new ArrayList<Group>();
		}
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "Form [nuDoc=" + nuDoc + ", nombre=" + nombre + ", imagen="
				+ imagen + ", ancho=" + ancho + ", alto=" + alto + ", frmn="
				+ frmn + ", frmv=" + frmv + ", defult=" + defult + ", groups="
				+ groups + "]";
	}
	
}
