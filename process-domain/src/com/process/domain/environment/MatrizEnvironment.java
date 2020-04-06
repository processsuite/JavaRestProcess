/**
 * MatrizEnvironment.java 
 *
 */
package com.process.domain.environment;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain MatrizEnvironment class for auth Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class MatrizEnvironment implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idioma;
	
	private String webtipoConexion;
	
	private String nivelSeg;
	
	private String webAmbientes;
	
	private String dominio;
	
	private String iRepInclude;
	
	private String formatoFecha;
	
	private String FormatoMiles;
	
	private String FormatoDecimal;
	
	private String repAgentes;
	
	private String iRepAnexos;

	private String tamanoAnexo; //variable para colocar el tamaño de los anexos
	
	private String timeSave; // variable para guardar el tiempo donde se decea implementar el autoGuardado.
	
		
	
	public String getTimeSave() {
		return timeSave;
	}

	public void setTimeSave(String timeSave) {
		this.timeSave = timeSave;
	}

	public String getTamanoAnexo() {
		return tamanoAnexo;
	}

	public void setTamanoAnexo(String tamanoAnexo) {
		this.tamanoAnexo = tamanoAnexo;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getWebtipoConexion() {
		return webtipoConexion;
	}

	public void setWebtipoConexion(String webtipoConexion) {
		this.webtipoConexion = webtipoConexion;
	}

	public String getNivelSeg() {
		return nivelSeg;
	}

	public void setNivelSeg(String nivelSeg) {
		this.nivelSeg = nivelSeg;
	}

	public String getWebAmbientes() {
		return webAmbientes;
	}

	public void setWebAmbientes(String webAmbientes) {
		this.webAmbientes = webAmbientes;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getiRepInclude() {
		return iRepInclude;
	}

	public void setiRepInclude(String iRepInclude) {
		this.iRepInclude = iRepInclude;
	}

	public String getFormatoFecha() {
		return formatoFecha;
	}

	public void setFormatoFecha(String formatoFecha) {
		this.formatoFecha = formatoFecha;
	}

	public String getFormatoMiles() {
		return FormatoMiles;
	}

	public void setFormatoMiles(String formatoMiles) {
		FormatoMiles = formatoMiles;
	}

	public String getFormatoDecimal() {
		return FormatoDecimal;
	}

	public void setFormatoDecimal(String formatoDecimal) {
		FormatoDecimal = formatoDecimal;
	}

		
	public String getRepAgentes() {
		return repAgentes;
	}

	public void setRepAgentes(String repAgentes) {
		this.repAgentes = repAgentes;
	}
	public String getiRepAnexos() {
		return iRepAnexos;
	}

	public void setiRepAnexos(String iRepAnexos) {
		this.iRepAnexos = iRepAnexos;
	}

	@Override
	public String toString() {
		return "MatrizEnvironment [idioma=" + idioma + ", webtipoConexion="
				+ webtipoConexion + ", nivelSeg=" + nivelSeg
				+ ", webAmbientes=" + webAmbientes + ", dominio=" + dominio
				+ ", iRepInclude=" + iRepInclude + ", formatoFecha="
				+ formatoFecha + ", FormatoMiles=" + FormatoMiles
				+ ", FormatoDecimal=" + FormatoDecimal + ", repAgentes="
				+ repAgentes + ", iRepAnexos=" + iRepAnexos + "]";
	}

	

	
	
}
