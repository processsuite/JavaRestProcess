package com.process.domain.console;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "registro")
@XmlAccessorType(XmlAccessType.NONE)
public class Registro implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "direcInternet")
	private DirecInternet direcInternet;
	@XmlElement(name = "administrador")
    private Administrador administrador;
	@XmlElement(name = "seguridad")
    private Seguridad seguridad;
	@XmlElement(name = "inspeccion")
    private Inspeccion inspeccion;
	@XmlElement(name = "repFisico")
    private RepFisico repFisico;
	@XmlElement(name = "fuenteDatos")
    private FuenteDatos fuenteDatos;
	@XmlElement(name = "configuracion")
    private Configuracion configuracion;
	@XmlElement(name = "email")
    private Email email;
	@XmlElement(name="varAmbiente")
	private VarAmbiente varAmbiente;
	
	
    public VarAmbiente getVarAmbiente() {
		return varAmbiente;
	}

	public void setVarAmbiente(VarAmbiente varAmbiente) {
		this.varAmbiente = varAmbiente;
	}

	public DirecInternet getDirecInternet ()
    {
        return direcInternet;
    }

    public void setDirecInternet (DirecInternet direcInternet)
    {
        this.direcInternet = direcInternet;
    }

    public Administrador getAdministrador ()
    {
        return administrador;
    }

    public void setAdministrador (Administrador administrador)
    {
        this.administrador = administrador;
    }

    public Seguridad getSeguridad ()
    {
        return seguridad;
    }

    public void setSeguridad (Seguridad seguridad)
    {
        this.seguridad = seguridad;
    }

    public Inspeccion getInspeccion ()
    {
        return inspeccion;
    }

    public void setInspeccion (Inspeccion inspeccion)
    {
        this.inspeccion = inspeccion;
    }

    public RepFisico getRepFisico ()
    {
        return repFisico;
    }

    public void setRepFisico (RepFisico repFisico)
    {
        this.repFisico = repFisico;
    }

    public FuenteDatos getFuenteDatos ()
    {
        return fuenteDatos;
    }

    public void setFuenteDatos (FuenteDatos fuenteDatos)
    {
        this.fuenteDatos = fuenteDatos;
    }

    public Configuracion getConfiguracion ()
    {
        return configuracion;
    }

    public void setConfiguracion (Configuracion configuracion)
    {
        this.configuracion = configuracion;
    }

    public Email getEmail ()
    {
        return email;
    }

    public void setEmail (Email email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [direcInternet = "+direcInternet+", administrador = "+administrador+", seguridad = "+seguridad+", inspeccion = "+inspeccion+", repFisico = "+repFisico+", fuenteDatos = "+fuenteDatos+", configuracion = "+configuracion+", email = "+email+"]";
    }
}
