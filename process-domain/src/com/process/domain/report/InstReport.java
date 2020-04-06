/**
 * InstReport.java 
 *
 */
package com.process.domain.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Inst Report class for report Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class InstReport implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<FieldReport> camposOcultos;
	
	private List<FieldReport> camposMostrar;

	public List<FieldReport> getCamposOcultos() {
		if (camposOcultos==null){
			camposOcultos = new ArrayList<FieldReport>();
		}
		return camposOcultos;
	}

	public void setCamposOcultos(List<FieldReport> camposOcultos) {
		this.camposOcultos = camposOcultos;
	}

	public List<FieldReport> getCamposMostrar() {
		if (camposMostrar==null){
			camposMostrar = new ArrayList<FieldReport>();
		}		
		return camposMostrar;
	}

	public void setCamposMostrar(List<FieldReport> camposMostrar) {
		this.camposMostrar = camposMostrar;
	}

	@Override
	public String toString() {
		return "InstReport [camposOcultos=" + camposOcultos
				+ ", camposMostrar=" + camposMostrar + "]";
	}

}
