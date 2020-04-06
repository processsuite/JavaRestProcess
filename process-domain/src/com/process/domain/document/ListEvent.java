/**
 * ListEvent.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain ListEvent class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class ListEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer numero;
	
	private List<String> valores;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<String> getValores() {
		if (valores==null){
			valores = new ArrayList<String>();
		}		
		return valores;
	}

	public void setValores(List<String> valores) {
		this.valores = valores;
	}

	@Override
	public String toString() {
		return "ListEvent [numero=" + numero + ", valores=" + valores + "]";
	}
	
}
