package com.process.domain.console;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "repFisico")
@XmlAccessorType(XmlAccessType.NONE)
public class RepFisico implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement
	private List<Item> item;
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}
}
