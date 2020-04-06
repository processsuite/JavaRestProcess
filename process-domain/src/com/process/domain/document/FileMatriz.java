/**
 * FileMatriz.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain FileMatriz class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class FileMatriz implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<FileItem> fileItems;
	
	private Boolean selChk;
	
	public List<FileItem> getFileItems() {
		if (fileItems==null){
			fileItems = new ArrayList<FileItem>();
		}
		return fileItems;
	}

	public void setFileItems(List<FileItem> fileItems) {
		this.fileItems = fileItems;
	}

	public Boolean getSelChk() {
		return selChk;
	}

	public void setSelChk(Boolean selChk) {
		this.selChk = selChk;
	}

	@Override
	public String toString() {
		return "FileMatriz [fileItems=" + fileItems + ", selChk=" + selChk
				+ "]";
	}
	
}
