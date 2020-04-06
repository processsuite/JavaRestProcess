package com.process.domain.document2;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



@XmlRootElement(name = "FORMA")
@XmlAccessorType(XmlAccessType.NONE)
public class Forma {

	@XmlAttribute(name="nu_doc")
	private Integer nuDoc;
	
	@XmlAttribute(name="nombre")
	private String nombre;
	
	@XmlAttribute(name="imagen")
	private String imagen;

	@XmlAttribute(name="ancho")	
	private Double ancho;

	@XmlAttribute(name="alto")
	private Double alto;

	@XmlAttribute(name="frmn")
	private Integer frmn;

	@XmlAttribute(name="frmv")
	private Integer frmv;

	@XmlAttribute(name="default")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean defult;
	
	@XmlElement(name = "GRUPO_CAMPOS")
	private List<GrupoCampos> ListGrupoCampos;
	
	
	public List<GrupoCampos> getListGrupoCampos(){
		return ListGrupoCampos;
	}
	
	public void setListGrupoCampos(List<GrupoCampos> ListGrupoCampos){
		this.ListGrupoCampos = ListGrupoCampos;
	}
	
	public Integer getNuDoc() {
		return nuDoc;
	}

	public void setNuDoc(Integer nuDoc) {
		this.nuDoc = nuDoc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Double getAncho() {
		return ancho;
	}

	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

	public Double getAlto() {
		return alto;
	}

	public void setAlto(Double alto) {
		this.alto = alto;
	}

	public Integer getFrmn() {
		return frmn;
	}

	public void setFrmn(Integer frmn) {
		this.frmn = frmn;
	}

	public Integer getFrmv() {
		return frmv;
	}

	public void setFrmv(Integer frmv) {
		this.frmv = frmv;
	}

	public Boolean getDefult() {
		return defult;
	}

	public void setDefult(String s) {
		this.defult =  "S".equals(s)||"true".equals(s);;
	}

	@Override
	public String toString() {
		return "Forma [nuDoc=" + nuDoc + ", nombre=" + nombre + ", imagen="
				+ imagen + ", ancho=" + ancho + ", alto=" + alto + ", frmn="
				+ frmn + ", frmv=" + frmv + ", defult=" + defult
				+ ", ListGrupoCampos=" + ListGrupoCampos + "]";
	}
	
	
}
