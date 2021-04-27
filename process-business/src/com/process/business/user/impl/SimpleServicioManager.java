/**
 * SimpleServicioManager.java 
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
import com.process.business.user.ServicioManager;
import com.process.domain.user.Servicio;

/**
 * Business Implementation SimpleServicioManager for user Module 
 * @author Oswel Sanchez
 * 
 */
@Service("servicioManager")
public class SimpleServicioManager implements ServicioManager {
    
	private static final Logger logger = Logger.getLogger(SimpleServicioManager.class);
	
	private c_Process motor;
	
	private Integer engineP;
	
	@Override
	public void setEngineId(Integer engineId) {
		engineP = engineId;
	}	
	
	@Override
	public List<Servicio> obtenerServicios() {
		List<Servicio> list = new ArrayList<Servicio>();
		try{
			motor = ClassFactory.getProcess(engineP);
			String resultXml = motor.p4bObtenerServicios();
			//logger.info("obtenerServicios :"+resultXml);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			
			NodeList nodes = document.getElementsByTagName("procesoPadre");
			for(int i=0;i < nodes.getLength(); i++){
				Servicio servicio = new Servicio();
				servicio.setNombre(xpath.compile("/servicios/procesoPadre[" + (i+1) + "]/@nb_wf").evaluate(document, XPathConstants.STRING).toString());
				servicio.setWf(Integer.valueOf(xpath.compile("/servicios/procesoPadre[" + (i+1) + "]/@wfp").evaluate(document, XPathConstants.STRING).toString()));
				servicio.setInfo(String.valueOf(xpath.compile("/servicios/procesoPadre[" + (i+1) + "]/@info").evaluate(document, XPathConstants.STRING).toString()));
				
				NodeList nodesChild = nodes.item(i).getChildNodes();
				for(int j=0;j < nodesChild.getLength(); j++){
					Servicio servicioChild = new Servicio();
					servicioChild.setNombre(xpath.compile("/servicios/procesoPadre[" + (i+1) + "]/proceso[" + (j+1) + "]/@nb_wf").evaluate(document, XPathConstants.STRING).toString());
					servicioChild.setWf(Integer.valueOf(xpath.compile("/servicios/procesoPadre[" + (i+1) + "]/proceso["+ (j+1) +"]/@wf").evaluate(document, XPathConstants.STRING).toString()));
					servicioChild.setFrmn(Integer.valueOf(xpath.compile("/servicios/procesoPadre[" + (i+1) + "]/proceso["+ (j+1) +"]/@frmn").evaluate(document, XPathConstants.STRING).toString()));
					servicioChild.setInfo(String.valueOf(xpath.compile("/servicios/procesoPadre[" + (i+1) + "]/proceso["+ (j+1) +"]/@info").evaluate(document, XPathConstants.STRING).toString()));
					servicio.getChildrens().add(servicioChild);
				}					
				list.add(servicio);
			}				

		}catch(Exception e){
			logger.error("obtenerServicios:", e);
		}
		return list;
	}

}
