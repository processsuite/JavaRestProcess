/**
 * Matriz.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Matriz class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Matriz implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean visible;
	
	private Boolean escritura;

	private List<Field> fields;
	
	private List<FileMatriz> files;
	
	private String filesDelete;

	public List<Field> getFields() {
		if (fields==null){
			fields = new ArrayList<Field>();
		}
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<FileMatriz> getFiles() {
		if (files==null){
			files = new ArrayList<FileMatriz>(); 
		}
		return files;
	}

	public void setFiles(List<FileMatriz> files) {
		this.files = files;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getFilesDelete() {
		return filesDelete;
	}

	public void setFilesDelete(String filesDelete) {
		this.filesDelete = filesDelete;
	}

	public Boolean getEscritura() {
		return escritura;
	}

	public void setEscritura(Boolean escritura) {
		this.escritura = escritura;
	}

	@Override
	public String toString() {
		return "Matriz [visible=" + visible + ", escritura=" + escritura
				+ ", fields=" + fields + ", files=" + files + ", filesDelete="
				+ filesDelete + "]";
	}
	
}
