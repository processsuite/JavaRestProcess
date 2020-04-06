package com.process.domain.log;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement(name = "c")
@XmlAccessorType(XmlAccessType.NONE)
public class ObjC implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(name="f")
	private String f; 
	@XmlElement(name="e")
	private String e;
	@XmlElement(name="t")
	private String t;
	@XmlElement(name="r")
	private String r;
	@XmlElement(name="u")
	private String u;
	@XmlElement(name="s")
	private String s;
	@XmlElement(name="d")
	private String d;
	@XmlElement(name="v")
	private String v;
	/*@XmlElementWrapper(name = "is")
	@XmlElement(name="i")
	private  String[] is;*/
	
	@XmlElement(name="is")
	private ObjIs is;
	/*Hasta aqui es .log los elemenos que se agregan a continuacion son para trc*/
	@XmlElement(name="o")
	private String o;
	
	@XmlElement(name="q")
	private String q;
	@XmlElement(name="n")
	private String n;
	
	@XmlElement(name="l")
	private String l;
	
	/*atributos de eventos */
	
	@XmlAttribute(name="tEvent")
	@XmlPath("@t")
	private String tEvent;
	
	@XmlElement(name="fEvent")
	@XmlPath("@f")
	private String fEvent;
	
	
	
	public String gettEvent() {
		return tEvent;
	}
	public void settEvent(String tEvent) {
		this.tEvent = tEvent;
	}
	public String getfEvent() {
		return fEvent;
	}
	public void setfEvent(String fEvent) {
		this.fEvent = fEvent;
	}
	public String getL() {
		return l;
	}
	public void setL(String l) {
		this.l = l;
	}
	public String getO() {
		return o;
	}
	public void setO(String o) {
		this.o = o;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}

	
	public ObjIs getIs() {
		return is;
	}
	public void setIs(ObjIs is) {
		this.is = is;
	}
	public String getF() {
		return f;
	}
	public void setF(String f) {
		this.f = f;
	}
	public String getE() {
		return e;
	}
	public void setE(String e) {
		this.e = e;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public String getR() {
		return r;
	}
	public void setR(String r) {
		this.r = r;
	}
	public String getU() {
		return u;
	}
	public void setU(String u) {
		this.u = u;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}

	
	
}
