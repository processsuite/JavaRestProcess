/**
 * ItemOption.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain ItemOption class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class ItemOption implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean selected;
	
	private String key;
	
	private String value;

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ItemOption [selected=" + selected + ", key=" + key + ", value="
				+ value + "]";
	}
	
}
