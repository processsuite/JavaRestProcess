/**
 * DataParamReport.java 
 *
 */
package com.process.domain.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Data Param Report class for report Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class DataParamReport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String rutaAgent;
	private List<FieldReport> camposBuscar = new ArrayList<FieldReport>();;

	public List<FieldReport> getCamposBuscar() {
		return camposBuscar;
	}

	public void setCamposBuscar(List<FieldReport> camposBuscar) {
		this.camposBuscar = camposBuscar;
	}

	public String getRutaAgent() {
		return rutaAgent;
	}

	public void setRutaAgent(String rutaAgent) {
		this.rutaAgent = rutaAgent;
	}

	@Override
	public String toString() {
		return "DataParamReport [rutaAgent=" + rutaAgent + ", camposBuscar="
				+ camposBuscar + "]";
	}

	


}
