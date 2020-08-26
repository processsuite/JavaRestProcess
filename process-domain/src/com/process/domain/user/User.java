/**
 * User.java 
 *
 */
package com.process.domain.user;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain User class for user Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private String email;
	
	private String formatoFecha;
	
	private Integer activoD;
	
	private String nbConversacion; //only use if activoD is open
	
	private String nuDocLect;
	
	private Integer CantDiasVencLic;
	
	private Boolean preguntaVencida;
	
	private String Pregunta;
	
	private String ambiente;
	
	private String formatoMiles;
	
	private String formatoDecimal;
	
	private Boolean reempl;
	
	private Boolean cambfr;
	
	private Boolean cambpr;
	
	private Boolean veresta;
	
	private Boolean admusr;
	
	private Boolean seg;
	
	private Boolean imprimir;
	
	private Boolean anexar;
	
	private Boolean desanexar;
	
	private Boolean anular;
	
	private Boolean decidir;
	
	private Boolean adm;
	
	private Boolean primaraVez;
	
	private String ip;
	
	private String fchultconex;
	
	
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFchultconex() {
		return fchultconex;
	}

	public void setFchultconex(String fchultconex) {
		this.fchultconex = fchultconex;
	}

	public Boolean getPrimaraVez() {
		return primaraVez;
	}

	public void setPrimaraVez(Boolean primaraVez) {
		this.primaraVez = primaraVez;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFormatoFecha() {
		return formatoFecha;
	}

	public void setFormatoFecha(String formatoFecha) {
		this.formatoFecha = formatoFecha;
	}

	public Integer getActivoD() {
		return activoD;
	}

	public void setActivoD(Integer activoD) {
		this.activoD = activoD;
	}

	public String getNbConversacion() {
		return nbConversacion;
	}

	public void setNbConversacion(String nbConversacion) {
		this.nbConversacion = nbConversacion;
	}

	public String getNuDocLect() {
		return nuDocLect;
	}

	public void setNuDocLect(String nuDocLect) {
		this.nuDocLect = nuDocLect;
	}

	public Integer getCantDiasVencLic() {
		return CantDiasVencLic;
	}

	public void setCantDiasVencLic(Integer cantDiasVencLic) {
		CantDiasVencLic = cantDiasVencLic;
	}

	public Boolean getPreguntaVencida() {
		return preguntaVencida;
	}

	public void setPreguntaVencida(Boolean preguntaVencida) {
		this.preguntaVencida = preguntaVencida;
	}

	public String getPregunta() {
		return Pregunta;
	}

	public void setPregunta(String pregunta) {
		Pregunta = pregunta;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public String getFormatoMiles() {
		return formatoMiles;
	}

	public void setFormatoMiles(String formatoMiles) {
		this.formatoMiles = formatoMiles;
	}

	public String getFormatoDecimal() {
		return formatoDecimal;
	}

	public void setFormatoDecimal(String formatoDecimal) {
		this.formatoDecimal = formatoDecimal;
	}

	public Boolean getReempl() {
		return reempl;
	}

	public void setReempl(Boolean reempl) {
		this.reempl = reempl;
	}

	public Boolean getCambfr() {
		return cambfr;
	}

	public void setCambfr(Boolean cambfr) {
		this.cambfr = cambfr;
	}

	public Boolean getCambpr() {
		return cambpr;
	}

	public void setCambpr(Boolean cambpr) {
		this.cambpr = cambpr;
	}

	public Boolean getVeresta() {
		return veresta;
	}

	public void setVeresta(Boolean veresta) {
		this.veresta = veresta;
	}

	public Boolean getAdmusr() {
		return admusr;
	}

	public void setAdmusr(Boolean admusr) {
		this.admusr = admusr;
	}

	public Boolean getSeg() {
		return seg;
	}

	public void setSeg(Boolean seg) {
		this.seg = seg;
	}

	public Boolean getImprimir() {
		return imprimir;
	}

	public void setImprimir(Boolean imprimir) {
		this.imprimir = imprimir;
	}

	public Boolean getAnexar() {
		return anexar;
	}

	public void setAnexar(Boolean anexar) {
		this.anexar = anexar;
	}

	public Boolean getDesanexar() {
		return desanexar;
	}

	public void setDesanexar(Boolean desanexar) {
		this.desanexar = desanexar;
	}

	public Boolean getAnular() {
		return anular;
	}

	public void setAnular(Boolean anular) {
		this.anular = anular;
	}

	public Boolean getDecidir() {
		return decidir;
	}

	public void setDecidir(Boolean decidir) {
		this.decidir = decidir;
	}

	public Boolean getAdm() {
		return adm;
	}

	public void setAdm(Boolean adm) {
		this.adm = adm;
	}

	@Override
	public String toString() {
		return "User [nombre=" + nombre + ", email=" + email
				+ ", formatoFecha=" + formatoFecha + ", activoD=" + activoD
				+ ", nbConversacion=" + nbConversacion + ", nuDocLect="
				+ nuDocLect + ", CantDiasVencLic=" + CantDiasVencLic
				+ ", preguntaVencida=" + preguntaVencida + ", Pregunta="
				+ Pregunta + ", ambiente=" + ambiente + ", formatoMiles="
				+ formatoMiles + ", formatoDecimal=" + formatoDecimal
				+ ", reempl=" + reempl + ", cambfr=" + cambfr + ", cambpr="
				+ cambpr + ", veresta=" + veresta + ", admusr=" + admusr
				+ ", seg=" + seg + ", imprimir=" + imprimir + ", anexar="
				+ anexar + ", desanexar=" + desanexar + ", anular=" + anular
				+ ", decidir=" + decidir + ", adm=" + adm + "]";
	}
	
}
