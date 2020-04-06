/**
 * CestaAnexo.java 
 *
 */
package com.process.domain.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain CestaAnexo class for user Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class CestaAnexo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fecha;
	
	private Date date;
	
	private List<Anexo> anexos;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Anexo> getAnexos() {
		if (anexos==null){
			anexos = new ArrayList<Anexo>();
		}
		return anexos;
	}

	public void setAnexos(List<Anexo> anexos) {
		this.anexos = anexos;
	}

	@Override
	public String toString() {
		return "CestaAnexo [fecha=" + fecha + ", date=" + date + ", anexos="
				+ anexos + "]";
	}
	
}
