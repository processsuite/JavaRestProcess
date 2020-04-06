/**
 * SimpleEmailManager.java 
 *
 */
package com.process.business.user.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.process.business.helper.ClassFactory;
import com.process.business.helper.c_Process;
import com.process.business.user.EmailManager;
import com.process.domain.user.Email;

/**
 * Business Implementation SimpleEmailManager for user Module 
 * @author Oswel Sanchez
 * 
 */
@Service("emailManager")
public class SimpleEmailManager implements EmailManager {
    
	private static final Logger logger = Logger.getLogger(SimpleEmailManager.class);
	
	private c_Process motor;
	
	private Integer engineP;
	
	@Override
	public void setEngineId(Integer engineId) {
		engineP = engineId;
	}	
	
	@Override
	public List<Email> obtenerCorreos(Integer nuDoc, Integer desde, Integer opcPropios) {
		List<Email> listEmail = new ArrayList<Email>();
		try{
			motor = ClassFactory.getProcess(engineP);
			String resultXml = motor.p4bObtenerCorreosDocumento(nuDoc, desde, opcPropios);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			
			NodeList nodes = document.getElementsByTagName("mensaje");
			for(int i=0;i < nodes.getLength(); i++){				
				Email email = new Email();
				email.setNuMens(Integer.valueOf(xpath.compile("/mensajes/mensaje[" + (i+1) + "]/@nu_mes").evaluate(document, XPathConstants.STRING).toString()));
				email.setNuDoc(Integer.valueOf(xpath.compile("/mensajes/mensaje[" + (i+1) + "]/@nu_doc").evaluate(document, XPathConstants.STRING).toString()));
				email.setStatus(Integer.valueOf(xpath.compile("/mensajes/mensaje[" + (i+1) + "]/status").evaluate(document, XPathConstants.STRING).toString()));
				email.setFecha(xpath.compile("/mensajes/mensaje[" + (i+1) + "]/fecha").evaluate(document, XPathConstants.STRING).toString());
				email.setDe(xpath.compile("/mensajes/mensaje[" + (i+1) + "]/de").evaluate(document, XPathConstants.STRING).toString());
				email.setPara(xpath.compile("/mensajes/mensaje[" + (i+1) + "]/para").evaluate(document, XPathConstants.STRING).toString());
				email.setAsunto(xpath.compile("/mensajes/mensaje[" + (i+1) + "]/asunto").evaluate(document, XPathConstants.STRING).toString());
				email.setError(xpath.compile("/mensajes/mensaje[" + (i+1) + "]/error").evaluate(document, XPathConstants.STRING).toString());
				listEmail.add(email);
			}			
			
		}catch(Exception e){
			logger.error("obtenerCorreos:", e);
		}
		return listEmail;
	}

	@Override
	public String obtenerContenidoCorreo(Integer nuMessage) {
		String result = "";
		try{
			motor = ClassFactory.getProcess(engineP);
			result = motor.p4bObtenerContenidoCorreo(nuMessage);
		}catch(Exception e){
			logger.error("obtenerContenidoCorreo:", e);
		}
		return result;
	}
	
}
