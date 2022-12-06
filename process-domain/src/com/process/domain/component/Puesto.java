package com.process.domain.component;

import java.util.List;

public class Puesto {

	/*private String puesto;
	private String nb_puesto;*/
	private String cd_usr_act;
	private String nb_usr_act;
	//private String pw_usr_act;
	private String email;
	private String fe_desde;
	private String ap_usr_act;
	//private String in_admwg;
	//private String in_admusr;
	//private String in_veresta;
	private List<Perfil> listPerfil;
	private String codigoAcceso;
	private String msj;
	
	
	
	
	public String getMsj() {
		return msj;
	}
	public void setMsj(String msj) {
		this.msj = msj;
	}
	public String getCodigoAcceso() {
		return codigoAcceso;
	}
	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}
	public List<Perfil> getListPerfil() {
		return listPerfil;
	}
	public void setListPerfil(List<Perfil> listPerfil) {
		this.listPerfil = listPerfil;
	}
	/*public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getNb_puesto() {
		return nb_puesto;
	}
	public void setNb_puesto(String nb_puesto) {
		this.nb_puesto = nb_puesto;
	}*/
	public String getCd_usr_act() {
		return cd_usr_act;
	}
	public void setCd_usr_act(String cd_usr_act) {
		this.cd_usr_act = cd_usr_act;
	}
	public String getNb_usr_act() {
		return nb_usr_act;
	}
	public void setNb_usr_act(String nb_usr_act) {
		this.nb_usr_act = nb_usr_act;
	}
	/*public String getPw_usr_act() {
		return pw_usr_act;
	}
	public void setPw_usr_act(String pw_usr_act) {
		this.pw_usr_act = pw_usr_act;
	}*/
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFe_desde() {
		return fe_desde;
	}
	public void setFe_desde(String fe_desde) {
		this.fe_desde = fe_desde;
	}
	public String getAp_usr_act() {
		return ap_usr_act;
	}
	public void setAp_usr_act(String ap_usr_act) {
		this.ap_usr_act = ap_usr_act;
	}
	/*public String getIn_admwg() {
		return in_admwg;
	}
	public void setIn_admwg(String in_admwg) {
		this.in_admwg = in_admwg;
	}
	public String getIn_admusr() {
		return in_admusr;
	}
	public void setIn_admusr(String in_admusr) {
		this.in_admusr = in_admusr;
	}
	public String getIn_veresta() {
		return in_veresta;
	}
	public void setIn_veresta(String in_veresta) {
		this.in_veresta = in_veresta;
	}*/
	
}
