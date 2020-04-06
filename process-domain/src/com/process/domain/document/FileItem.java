/**
 * FileItem.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain FileItem class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class FileItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	
	private String value;
	
	private List<String> valueM;	
	
	private Boolean change;
	
	private Boolean checked;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<String> getValueM() {
		if (valueM==null){
			valueM = new ArrayList<String>();
		}
		return valueM;
	}

	public void setValueM(List<String> valueM) {
		this.valueM = valueM;
	}

	public Boolean getChange() {
		return change;
	}

	public void setChange(Boolean change) {
		this.change = change;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "FileItem [name=" + name + ", value=" + value + ", change="
				+ change + ", checked=" + checked + "]";
	}
	
}
