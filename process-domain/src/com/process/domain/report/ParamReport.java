/**
 * ParamReport.java 
 *
 */
package com.process.domain.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.process.domain.document.ItemOption;

/**
 * Domain Param Report class for report Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class ParamReport implements Serializable {

	private static final long serialVersionUID = 1L;

	private String campoBd;
	
	private String descripcion;
	
	private String tipo;
	
	private String operador;
	
	private Boolean obligatorio;
	
	private Boolean funcion;
	
	//use for tipo=L --------
	private List<ItemOption> opciones;

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public Boolean getObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(Boolean obligatorio) {
		this.obligatorio = obligatorio;
	}

	public Boolean getFuncion() {
		return funcion;
	}

	public void setFuncion(Boolean funcion) {
		this.funcion = funcion;
	}

	public List<ItemOption> getOpciones() {
		if (opciones==null){
			opciones = new ArrayList<ItemOption>();
		}
		return opciones;
	}

	public void setOpciones(List<ItemOption> opciones) {
		this.opciones = opciones;
	}

	@Override
	public String toString() {
		return "ParamReport [campoBd=" + campoBd + ", descripcion="
				+ descripcion + ", tipo=" + tipo + ", operador=" + operador
				+ ", obligatorio=" + obligatorio + ", funcion=" + funcion
				+ ", opciones=" + opciones + "]";
	}
	
}
