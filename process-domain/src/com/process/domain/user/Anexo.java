/**
 * Anexo.java 
 *
 */
package com.process.domain.user;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Anexo class for user Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Anexo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fecha;
	
	private String des;
	
	private String archivo;
	
	private String act;
	
	private String usuario;
	
	private String ext;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@Override
	public String toString() {
		return "Anexo [fecha=" + fecha + ", des=" + des + ", archivo="
				+ archivo + ", act=" + act + ", usuario=" + usuario + ", ext="
				+ ext + "]";
	}
	
}
