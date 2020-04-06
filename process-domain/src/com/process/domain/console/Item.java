package com.process.domain.console;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.NONE)
public class Item implements Serializable{/**
{
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
@XmlAttribute
private String valorDef;
@XmlAttribute
private String descrip;
@XmlAttribute
private String id;
@XmlAttribute
private String lectura;
@XmlAttribute
private String type;
@XmlElement(name="opciones")
private Opciones opciones;
//@XmlValue
@XmlAttribute(name = "value")
private String value;



public Opciones getOpciones() {
	return opciones;
}

public void setOpciones(Opciones opciones) {
	this.opciones = opciones;
}

public String getLectura() {
	return lectura;
}

public void setLectura(String lectura) {
	this.lectura = lectura;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}
@XmlPath("text()")
public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}

	public String getValorDef ()
    {
        return valorDef;
    }

    public void setValorDef (String valorDef)
    {
        this.valorDef = valorDef;
    }

    public String getDescrip ()
    {
        return descrip;
    }

    public void setDescrip (String descrip)
    {
        this.descrip = descrip;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [valorDef = "+valorDef+", descrip = "+descrip+", id = "+id+"]";
    }	
}
