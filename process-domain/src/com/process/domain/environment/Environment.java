/**
 * Environment.java 
 *
 */
package com.process.domain.environment;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Environment class for Environment Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Environment implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	
	private MatrizEnvironment matriz;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MatrizEnvironment getMatriz() {
		return matriz;
	}

	public void setMatriz(MatrizEnvironment matriz) {
		this.matriz = matriz;
	}

	@Override
	public String toString() {
		return "Environment [name=" + name + ", matriz=" + matriz + "]";
	}
	
}
