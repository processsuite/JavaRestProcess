/**
 * Doc.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Doc class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Doc implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Context Document----------	
	private Boolean lectura;
	
	private Boolean adquirible;
	
	private Boolean recuperable;
	
	private Boolean notificacion;
	
	private Boolean espera;
	
	private Integer status;
	
	private String estado;
	
	private String remitente;
	
	private String fechaEnvio; 
	
	private String resumen;
	
	private String observacion;
	
	private String nbProceso;
	
	private String nbConversacion;
	
	private Integer wfp;
	
	private Integer wfa;
	
	private Integer nuDoc;
	
	private Integer nuDocP;
	
	private String repAgentes;
	
	private List<Form> formsContext;
	
	private List<Agent> agents;	
	//--------------------------
	
	private List<Form> forms;
	
	//---Client file-------------
	private String fileClient;
	
	private String fileClientGeneral;
	//------------------------------
	
	List<String> esperaOrigen;

	public Boolean getLectura() {
		return lectura;
	}

	public void setLectura(Boolean lectura) {
		this.lectura = lectura;
	}

	public Boolean getAdquirible() {
		return adquirible;
	}

	public void setAdquirible(Boolean adquirible) {
		this.adquirible = adquirible;
	}

	public Boolean getRecuperable() {
		return recuperable;
	}

	public void setRecuperable(Boolean recuperable) {
		this.recuperable = recuperable;
	}

	public Boolean getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Boolean notificacion) {
		this.notificacion = notificacion;
	}

	public Boolean getEspera() {
		return espera;
	}

	public void setEspera(Boolean espera) {
		this.espera = espera;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public String getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getNbProceso() {
		return nbProceso;
	}

	public void setNbProceso(String nbProceso) {
		this.nbProceso = nbProceso;
	}

	public String getNbConversacion() {
		return nbConversacion;
	}

	public void setNbConversacion(String nbConversacion) {
		this.nbConversacion = nbConversacion;
	}

	public Integer getWfp() {
		return wfp;
	}

	public void setWfp(Integer wfp) {
		this.wfp = wfp;
	}

	public Integer getWfa() {
		return wfa;
	}

	public void setWfa(Integer wfa) {
		this.wfa = wfa;
	}

	public Integer getNuDoc() {
		return nuDoc;
	}

	public void setNuDoc(Integer nuDoc) {
		this.nuDoc = nuDoc;
	}

	public Integer getNuDocP() {
		return nuDocP;
	}

	public void setNuDocP(Integer nuDocP) {
		this.nuDocP = nuDocP;
	}

	public List<Form> getForms() {
		if (forms==null){
			forms = new ArrayList<Form>();
		}
		return forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

	public List<Form> getFormsContext() {
		if (formsContext==null){
			formsContext = new ArrayList<Form>();
		}		
		return formsContext;
	}

	public void setFormsContext(List<Form> formsContext) {
		this.formsContext = formsContext;
	}

	public List<Agent> getAgents() {
		if (agents==null){
			agents = new ArrayList<Agent>();
		}		
		return agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}

	public String getFileClient() {
		return fileClient;
	}

	public void setFileClient(String fileClient) {
		this.fileClient = fileClient;
	}

	public String getFileClientGeneral() {
		return fileClientGeneral;
	}

	public void setFileClientGeneral(String fileClientGeneral) {
		this.fileClientGeneral = fileClientGeneral;
	}

	public List<String> getEsperaOrigen() {
		if (esperaOrigen==null){
			esperaOrigen = new ArrayList<String>();
		}
		return esperaOrigen;
	}

	public void setEsperaOrigen(List<String> esperaOrigen) {
		this.esperaOrigen = esperaOrigen;
	}
	
	
	public String getRepAgentes() {
		return repAgentes;
	}

	public void setRepAgentes(String repAgentes) {
		this.repAgentes = repAgentes;
	}

	@Override
	public String toString() {
		return "Doc [lectura=" + lectura + ", adquirible=" + adquirible
				+ ", recuperable=" + recuperable + ", notificacion="
				+ notificacion + ", espera=" + espera + ", status=" + status
				+ ", estado=" + estado + ", remitente=" + remitente
				+ ", fechaEnvio=" + fechaEnvio + ", resumen=" + resumen
				+ ", observacion=" + observacion + ", nbProceso=" + nbProceso
				+ ", nbConversacion=" + nbConversacion + ", wfp=" + wfp
				+ ", wfa=" + wfa + ", nuDoc=" + nuDoc + ", nuDocP=" + nuDocP
				+ ", repAgentes=" + repAgentes + ", formsContext="
				+ formsContext + ", agents=" + agents + ", forms=" + forms
				+ ", fileClient=" + fileClient + ", fileClientGeneral="
				+ fileClientGeneral + ", esperaOrigen=" + esperaOrigen + "]";
	}

	
	
}
