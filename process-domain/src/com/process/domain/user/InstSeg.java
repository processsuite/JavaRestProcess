/**
 * InstSeg.java 
 *
 */
package com.process.domain.user;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain InstSeg class for user Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class InstSeg implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean nuDocOpened;
	
	private Integer nuDoc;
	
	private Integer nuInst;
	
	private Integer wfp;
	
	private Integer wfa;

	private String nbWfp;
	
	private String nbWfDest;
	
	private String nbWf;
	
	private String e;
	
	private String ep;
	
	private String feIni;
	
	private String feFin;	
	
	private String txObserva;
	
	private String detalle;
	
	private String dias;
	
	private String tiempo;
	
	private String origen;
	
	private String nbPuestoOrg;
	
	private String nbPersonaOrg;
	
	private String destino;
	
	private String nbPuesto;
	
	private String nbPersona;
	
	private Boolean tpInst;
	
	private String tpc;
	
	private Boolean inEspera;
	
	private Boolean inUrgente;
	
	private Integer frmn;
	
	private Boolean inClone;
	
	private Boolean inVisible;
	
	private String reposito;

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

	public Integer getFrmn() {
		return frmn;
	}

	public void setFrmn(Integer frmn) {
		this.frmn = frmn;
	}

	public String getNbWfp() {
		return nbWfp;
	}

	public void setNbWfp(String nbWfp) {
		this.nbWfp = nbWfp;
	}

	public String getNbWfDest() {
		return nbWfDest;
	}

	public void setNbWfDest(String nbWfDest) {
		this.nbWfDest = nbWfDest;
	}

	public String getFeFin() {
		return feFin;
	}

	public void setFeFin(String feFin) {
		this.feFin = feFin;
	}

	public String getNbPuestoOrg() {
		return nbPuestoOrg;
	}

	public void setNbPuestoOrg(String nbPuestoOrg) {
		this.nbPuestoOrg = nbPuestoOrg;
	}

	public String getNbPersonaOrg() {
		return nbPersonaOrg;
	}

	public void setNbPersonaOrg(String nbPersonaOrg) {
		this.nbPersonaOrg = nbPersonaOrg;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Boolean getTpInst() {
		return tpInst;
	}

	public void setTpInst(Boolean tpInst) {
		this.tpInst = tpInst;
	}

	public String getTpc() {
		return tpc;
	}

	public void setTpc(String tpc) {
		this.tpc = tpc;
	}

	public Boolean getInClone() {
		return inClone;
	}

	public void setInClone(Boolean inClone) {
		this.inClone = inClone;
	}

	public Boolean getInVisible() {
		return inVisible;
	}

	public void setInVisible(Boolean inVisible) {
		this.inVisible = inVisible;
	}

	public String getReposito() {
		return reposito;
	}

	public void setReposito(String reposito) {
		this.reposito = reposito;
	}

	@Override
	public String toString() {
		return "InstSeg [nuDocOpened=" + nuDocOpened + ", nuDoc=" + nuDoc
				+ ", nuInst=" + nuInst + ", wfp=" + wfp + ", wfa=" + wfa
				+ ", nbWfp=" + nbWfp + ", nbWfDest=" + nbWfDest + ", nbWf="
				+ nbWf + ", e=" + e + ", ep=" + ep + ", feIni=" + feIni
				+ ", feFin=" + feFin + ", txObserva=" + txObserva
				+ ", detalle=" + detalle + ", dias=" + dias + ", tiempo="
				+ tiempo + ", origen=" + origen + ", nbPuestoOrg="
				+ nbPuestoOrg + ", nbPersonaOrg=" + nbPersonaOrg + ", destino="
				+ destino + ", nbPuesto=" + nbPuesto + ", nbPersona="
				+ nbPersona + ", tpInst=" + tpInst + ", tpc=" + tpc
				+ ", inEspera=" + inEspera + ", inUrgente=" + inUrgente
				+ ", frmn=" + frmn + ", inClone=" + inClone + ", inVisible="
				+ inVisible + ", reposito=" + reposito + "]";
	}
	
}
