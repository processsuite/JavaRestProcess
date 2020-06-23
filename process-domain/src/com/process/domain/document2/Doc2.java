package com.process.domain.document2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



@XmlRootElement(name = "FOLDER")
@XmlAccessorType(XmlAccessType.NONE)
public class Doc2 {


	@XmlAttribute(name="lectura")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean lectura;
	
	@XmlAttribute(name="adquirible")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean adquirible;

	@XmlAttribute(name="recuperable")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean recuperable;

	@XmlAttribute(name="notificacion")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean notificacion;

	@XmlAttribute(name="espera")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean espera;

	@XmlAttribute(name="status")
	private Integer status;

	

	@XmlAttribute(name="estado")
	private String estado;

	private String remitente;

	@XmlAttribute(name="fecha")
	private String fechaEnvio;

	@XmlAttribute(name="resumen")
	private String resumen;

	@XmlAttribute(name="observacion")
	private String observacion;

	@XmlAttribute(name="nb_proceso")
	private String nbProceso;

	@XmlAttribute(name="nb_conversacion")
	private String nbConversacion;

	@XmlAttribute(name="wfp")
	private Integer wfp;

	@XmlAttribute(name="wfa")
	private Integer wfa;

	@XmlAttribute(name="nu_doc")
	private Integer nuDoc;

	@XmlAttribute(name="nu_doc_p")
	private Integer nuDocP;
	
	@XmlElement(name="FORMAS")
	private Formas formas;
	
	@XmlElement(name="ANEXOS")
	private Anexos anexos;
	
	@XmlElement(name="AGENTES")
	private Agentes agentes;
	
	@XmlElement(name="FORMA")
	private Forma forma;
	
	@XmlElement(name="repAgentes")
	private String repAgentes;
	
	public Forma getForma() {
		return forma;
	}

	public Anexos getAnexos() {
		return anexos;
	}

	public void setAnexos(Anexos anexos) {
		this.anexos = anexos;
	}
	public void setForma(Forma forma) {
		this.forma = forma;
	}

	public Agentes getAgentes() {
		return agentes;
	}

	public void setAgentes(Agentes agentes) {
		this.agentes = agentes;
	}

	public Boolean getLectura() {
		return lectura;
	}

	public void setLectura(String s) {
		this.lectura = "S".equals(s)||"true".equals(s);
	}

	public Boolean getAdquirible() {
		return adquirible;
	}

	public void setAdquirible(String s) {
		this.adquirible = "S".equals(s)||"true".equals(s);;
	}

	public Boolean getRecuperable() {
		return recuperable;
	}

	public void setRecuperable(String s) {
		this.recuperable = "S".equals(s)||"true".equals(s);;
	}

	public Boolean getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(String s) {
		this.notificacion = "S".equals(s)||"true".equals(s);;
	}

	public Boolean getEspera() {
		return espera;
	}

	public void setEspera(String s) {
		this.espera = "S".equals(s)||"true".equals(s);;
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
	
	public Formas getFormas() {
		return formas;
	}

	public void setFormas(Formas formas) {
		this.formas = formas;
	}
	
	public String getRepAgentes() {
		return repAgentes;
	}

	public void setRepAgentes(String repAgentes) {
		this.repAgentes = repAgentes;
	}
	
	
}
