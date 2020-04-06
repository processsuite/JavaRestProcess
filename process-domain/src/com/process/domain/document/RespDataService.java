package com.process.domain.document;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class RespDataService {
	
	private String msgError;
	private String codError; 
	private Object[][] arrayResult;
	
	public String getMsgError() {
		return msgError;
	}
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}
	public String getCodError() {
		return codError;
	}
	public void setCodError(String codError) {
		this.codError = codError;
	}
	public Object[][] getArrayResult() {
		return arrayResult;
	}
	public void setArrayResult (Object[][] arrayResult) {
		this.arrayResult = arrayResult;
	}
	
	
	
}
