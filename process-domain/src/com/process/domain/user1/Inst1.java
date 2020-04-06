package com.process.domain.user1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.process.domain.document2.BooleanAdapter;

@XmlRootElement(name = "inst")
@XmlAccessorType(XmlAccessType.NONE)
public class Inst1 {
	
	@XmlElement(name="nu_doc_opened")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean nuDocOpened;
	
	@XmlElement(name="nu_doc")
	private Integer nuDoc;
	
	@XmlElement(name="nu_inst")
	private Integer nuInst;
	
	@XmlElement(name="wfp")
	private Integer wfp;
	
	@XmlElement(name="wfa")
	private Integer wfa;
	
	@XmlElement(name="nb_wf")
	private String nbWf;
	
	@XmlElement(name="e")
	private String e;
	
	@XmlElement(name="ep")
	private String ep;
	
	@XmlElement(name="fe_ini")
	private String feIni;
	
	@XmlElement(name="txobserva")
	private String txObserva;
	
	@XmlElement(name="detalle")
	private String detalle;
	
	@XmlElement(name="dias")
	private String dias;
	
	@XmlElement(name="tiempo")
	private String tiempo;
	
	@XmlElement(name="retraso")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean retraso;
	
	@XmlElement(name="origen")
	private String origen;
	
	@XmlElement(name="nb_puesto")
	private String nbPuesto;

	@XmlElement(name="nb_persona")
	private String nbPersona;
	
	@XmlElement(name="alerta")
	private Integer alerta;
	
	@XmlElement(name="semaforo")
	private Integer semaforo;
	
	@XmlElement(name="in_espera")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean inEspera;
	
	@XmlElement(name="in_urgente")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean inUrgente;
	
	@XmlElement(name="fever")
	private String fever;
	
	@XmlElement(name="frmn")
	private Integer frmn;

	public Integer getNuDoc() {
		return nuDoc;
	}

	public void setNuDoc(Integer nuDoc) {
		this.nuDoc = nuDoc;
	}

	public Integer getNuInst() {
		return nuInst;
	}

	public void setNuInst(Integer nuInst) {
		this.nuInst = nuInst;
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

	public String getNbWf() {
		return nbWf;
	}

	public void setNbWf(String nbWf) {
		this.nbWf = nbWf;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getEp() {
		return ep;
	}

	public void setEp(String ep) {
		this.ep = ep;
	}

	public String getFeIni() {
		return feIni;
	}

	public void setFeIni(String feIni) {
		this.feIni = feIni;
	}

	public String getTxObserva() {
		return txObserva;
	}

	public void setTxObserva(String txObserva) {
		this.txObserva = txObserva;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getNbPuesto() {
		return nbPuesto;
	}

	public void setNbPuesto(String nbPuesto) {
		this.nbPuesto = nbPuesto;
	}

	public String getNbPersona() {
		return nbPersona;
	}

	public void setNbPersona(String nbPersona) {
		this.nbPersona = nbPersona;
	}

	public Integer getAlerta() {
		return alerta;
	}

	public void setAlerta(Integer alerta) {
		this.alerta = alerta;
	}

	public Integer getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(Integer semaforo) {
		this.semaforo = semaforo;
	}

	public String getFever() {
		return fever;
	}

	public void setFever(String fever) {
		this.fever = fever;
	}

	public Integer getFrmn() {
		return frmn;
	}

	public void setFrmn(Integer frmn) {
		this.frmn = frmn;
	}
	
	public Boolean getInUrgente() {
		return inUrgente;
	}

	public void setInUrgente(String s) {
		this.inUrgente = "S".equals(s)||"true".equals(s);;
	}

	public void setInEspera(String s) {
		this.inEspera = "S".equals(s)||"true".equals(s);;
	}
	
	public Boolean getInEspera() {
		return inEspera;
	}

	public void setRetraso(String s) {
		this.retraso = "S".equals(s)||"true".equals(s);;
	}

	public Boolean getRetraso() {
		return retraso;
	}

	public void setNuDocOpened(String s) {
		this.nuDocOpened = "S".equals(s)||"true".equals(s);;
	}

	public Boolean getNuDocOpenede() {
		return nuDocOpened;
	}

	
}
