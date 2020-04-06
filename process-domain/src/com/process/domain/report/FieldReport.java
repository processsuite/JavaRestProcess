/**
 * FieldReport.java 
 *
 */
package com.process.domain.report;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Field Report class for report Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class FieldReport implements Serializable {

	private static final long serialVersionUID = 1L;

	private String campoBd;
	
	private String descripcion;
	
	private String valor;
	
	private Integer pos;
	
	private String color; //only report graph

	public String getCampoBd() {
		return campoBd;
	}

	public void setCampoBd(String campoBd) {
		this.campoBd = campoBd;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "FieldReport [campoBd=" + campoBd + ", descripcion="
				+ descripcion + ", valor=" + valor + ", pos=" + pos
				+ ", color=" + color + "]";
	}
	
}
