package com.process.domain.generadordocument;



//import java.io.Serializable;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain plantilla class for user Module 
 * @author Junior Molina
 * 
 */
@XmlRootElement
public class Plantilla implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String pathArch;
	
	private String pNombreArch; 
    
    private String pExtArch;
    
    private String pDescripcion;
    
    private int pAnexar;
    
    private String pDelimitador;
    
    private String pConsulta;
    
    private String pCampoInd;
    
    private String pConsultabool;
    
    

	public String getpConsultabool() {
		return pConsultabool;
	}

	public void setpConsultabool(String pConsultabool) {
		this.pConsultabool = pConsultabool;
	}

	public String getPathArch() {
		return pathArch;
	}

	public void setPathArch(String pathArch) {
		this.pathArch = pathArch;
	}

	public String getpNombreArch() {
		return pNombreArch;
	}

	public void setpNombreArch(String pNombreArch) {
		this.pNombreArch = pNombreArch;
	}

	public String getpExtArch() {
		return pExtArch;
	}

	public void setpExtArch(String pExtArch) {
		this.pExtArch = pExtArch;
	}

	public String getpDescripcion() {
		return pDescripcion;
	}

	public void setpDescripcion(String pDescripcion) {
		this.pDescripcion = pDescripcion;
	}

	public int getpAnexar() {
		return pAnexar;
	}

	public void setpAnexar(int pAnexar) {
		this.pAnexar = pAnexar;
	}

	public String getpDelimitador() {
		return pDelimitador;
	}

	public void setpDelimitador(String pDelimitador) {
		this.pDelimitador = pDelimitador;
	}

	public String getpConsulta() {
		return pConsulta;
	}

	public void setpConsulta(String pConsulta) {
		this.pConsulta = pConsulta;
	}

	public String getpCampoInd() {
		return pCampoInd;
	}

	public void setpCampoInd(String pCampoInd) {
		this.pCampoInd = pCampoInd;
	}

	@Override
	public String toString() {
		return "Plantilla [pathArch=" + pathArch + ", pNombreArch="
				+ pNombreArch + ", pExtArch=" + pExtArch + ", pDescripcion="
				+ pDescripcion + ", pAnexar=" + pAnexar + ", pDelimitador="
				+ pDelimitador + ", pConsulta=" + pConsulta + ", pCampoInd="
				+ pCampoInd + "]";
	}
    
    

}
