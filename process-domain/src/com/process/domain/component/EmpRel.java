package com.process.domain.component;

public class EmpRel {
	//private String codUser;
	private Integer codProd;
	private Integer codPerfil;
	private String codTpoEmpRel;
	private Integer tpoNITRel;
	private String nitRel;
	private Integer tpoNitClte;
	private String nitClte;
	private String cedula;//nchar12
	private String codTpoIdent;//varchar2
	private String tipoDoc;//nchar4
	private String celular;//char20
	
	
	/*public String getCodUser() {
		return codUser;
	}
	public void setCodUser(String codUser) {
		this.codUser = codUser;
	}*/
	
	
	
	public Integer getCodProd() {
		return codProd;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getCodTpoIdent() {
		return codTpoIdent;
	}
	public void setCodTpoIdent(String codTpoIdent) {
		this.codTpoIdent = codTpoIdent;
	}
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public void setCodProd(Integer codProd) {
		this.codProd = codProd;
	}
	public Integer getCodPerfil() {
		return codPerfil;
	}
	public void setCodPerfil(Integer codPerfil) {
		this.codPerfil = codPerfil;
	}
	public String getCodTpoEmpRel() {
		return codTpoEmpRel;
	}
	public void setCodTpoEmpRel(String codTpoEmpRel) {
		this.codTpoEmpRel = codTpoEmpRel;
	}
	public Integer getTpoNITRel() {
		return tpoNITRel;
	}
	public void setTpoNITRel(Integer tpoNITRel) {
		this.tpoNITRel = tpoNITRel;
	}
	public String getNitRel() {
		return nitRel;
	}
	public void setNitRel(String nitRel) {
		this.nitRel = nitRel;
	}
	public Integer getTpoNitClte() {
		return tpoNitClte;
	}
	public void setTpoNitClte(Integer tpoNitClte) {
		this.tpoNitClte = tpoNitClte;
	}
	public String getNitClte() {
		return nitClte;
	}
	public void setNitClte(String nitClte) {
		this.nitClte = nitClte;
	}
	
	
}
