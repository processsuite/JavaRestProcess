/**
 * Inst.java 
 *
 */
package com.process.domain.user;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Inst class for user Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Inst implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean nuDocOpened;
	
	private Integer nuDoc;
	
	private Integer nuInst;
	
	private Integer wfp;
	
	private Integer wfa;
	
	private String nbWf;
	
	private String e;
	
	private String ep;
	
	private String feIni;
	
	private String txObserva;
	
	private String detalle;
	
	private String dias;
	
	private String tiempo;
	
	private Boolean retraso;
	
	private String origen;
	
	private String nbPuesto;
	
	private String nbPersona;
	
	private Integer alerta;
	
	private Integer semaforo;
	
	private Boolean inEspera;
	
	private Boolean inUrgente;
	
	private String fever;
	
	private Integer frmn;

	public Boolean getNuDocOpened() {
		return nuDocOpened;
	}

	public void setNuDocOpened(Boolean nuDocOpened) {
		this.nuDocOpened = nuDocOpened;
	}

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

	public Boolean getRetraso() {
		return retraso;
	}

	public void setRetraso(Boolean retraso) {
		this.retraso = retraso;
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

	public Boolean getInEspera() {
		return inEspera;
	}

	public void setInEspera(Boolean inEspera) {
		this.inEspera = inEspera;
	}

	public Boolean getInUrgente() {
		return inUrgente;
	}

	public void setInUrgente(Boolean inUrgente) {
		this.inUrgente = inUrgente;
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

	@Override
	public String toString() {
		return "Inst [nuDocOpened=" + nuDocOpened + ", nuDoc=" + nuDoc
				+ ", nuInst=" + nuInst + ", wfp=" + wfp + ", wfa=" + wfa
				+ ", nbWf=" + nbWf + ", e=" + e + ", ep=" + ep + ", feIni="
				+ feIni + ", txObserva=" + txObserva + ", detalle=" + detalle
				+ ", dias=" + dias + ", tiempo=" + tiempo + ", retraso="
				+ retraso + ", origen=" + origen + ", nbPuesto=" + nbPuesto
				+ ", nbPersona=" + nbPersona + ", alerta=" + alerta
				+ ", semaforo=" + semaforo + ", inEspera=" + inEspera
				+ ", inUrgente=" + inUrgente + ", fever=" + fever + ", frmn="
				+ frmn + "]";
	}
	
}
