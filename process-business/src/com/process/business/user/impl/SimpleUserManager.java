/**
 * SimpleUserManager.java 
 *
 */
package com.process.business.user.impl;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.process.business.helper.ClassFactory;
import com.process.business.helper.c_Process;
import com.process.business.user.UserManager;
import com.process.domain.user.User;

/**
 * Business Implementation SimpleUserManager for user Module 
 * @author Oswel Sanchez
 * 
 */
@Service("userManager")
public class SimpleUserManager implements UserManager {
    
	private static final Logger logger = Logger.getLogger(SimpleUserManager.class);
	
	private c_Process motor;	
	
	private Integer engineP;
	
	@Override
	public void setEngineId(Integer engineId) {
		engineP = engineId;
	}

	@Override
	public User obtenerDatosUsuario() {
		User user = new User();
		try{
			motor = ClassFactory.getProcess(engineP);
			String resultXml = motor.p4bObtenerDatosUsuario();
			logger.info("ObtenerDatosUsuario: "+ resultXml);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
		
			user.setNombre(xpath.compile("/usr/nb_usr_act").evaluate(document, XPathConstants.STRING).toString());
			user.setApellido(xpath.compile("/usr/ap_usr_act").evaluate(document, XPathConstants.STRING).toString());
			user.setEmail(xpath.compile("/usr/email").evaluate(document, XPathConstants.STRING).toString());
			user.setFchultconex(xpath.compile("/usr/fchultconex").evaluate(document, XPathConstants.STRING).toString());
			logger.info("revisar prueba "+ xpath.compile("/usr/fchultconex").evaluate(document, XPathConstants.STRING).toString());
			
			user.setFormatoFecha(xpath.compile("/usr/@formatofecha").evaluate(document, XPathConstants.STRING).toString());
			user.setActivoD(Integer.valueOf(xpath.compile("/usr/@nu_doc").evaluate(document, XPathConstants.STRING).toString()));
			user.setNuDocLect(xpath.compile("/usr/@nu_doc_lect").evaluate(document, XPathConstants.STRING).toString());
			user.setIp(xpath.compile("/usr/@ip").evaluate(document, XPathConstants.STRING).toString());
			
			
			
			if (xpath.compile("/usr/pregunta/@vencida").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				user.setPreguntaVencida(true);
			}else{
				user.setPreguntaVencida(false);
			}
			if (xpath.compile("/usr/pregunta/@vencida").evaluate(document, XPathConstants.STRING).toString().equals("P")){
				user.setPrimaraVez(true);
			}else{
				user.setPrimaraVez(false);
			}
			user.setCantDiasVencLic(Integer.valueOf(xpath.compile("/usr/@CantDiasVencLic").evaluate(document, XPathConstants.STRING).toString()));
			user.setPregunta(xpath.compile("/usr/pregunta").evaluate(document, XPathConstants.STRING).toString());
			user.setAmbiente(xpath.compile("/usr/@ambiente").evaluate(document, XPathConstants.STRING).toString());
			user.setFormatoDecimal(xpath.compile("/usr/@formatodecimal").evaluate(document, XPathConstants.STRING).toString());
			user.setFormatoMiles(xpath.compile("/usr/@formatomiles").evaluate(document, XPathConstants.STRING).toString());
			if (xpath.compile("/usr/permisos/in_reempl").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				user.setReempl(true);
			}else{
				user.setReempl(false);
			}	
			if (xpath.compile("/usr/permisos/in_cambfr").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				user.setCambfr(true);
			}else{
				user.setCambfr(false);
			}
			if (xpath.compile("/usr/permisos/in_cambpr").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				user.setCambpr(true);
			}else{
				user.setCambpr(false);
			}
			if (xpath.compile("/usr/permisos/in_veresta").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				user.setVeresta(true);
			}else{
				user.setVeresta(false);
			}
			if (xpath.compile("/usr/permisos/in_admusr").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				user.setAdmusr(true);
			}else{
				user.setAdmusr(false);
			}
			if (xpath.compile("/usr/permisos/in_seg").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				user.setSeg(true);
			}else{
				user.setSeg(false);
			}
			if (xpath.compile("/usr/permisos/in_imprimir").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				user.setImprimir(true);
			}else{
				user.setImprimir(false);
			}
			if (xpath.compile("/usr/permisos/in_anexar").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				user.setAnexar(true);
			}else{
				user.setAnexar(false);
			}
			if (xpath.compile("/usr/permisos/in_desanexar").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				user.setDesanexar(true);
			}else{
				user.setDesanexar(false);
			}
			if (xpath.compile("/usr/permisos/in_anular").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				user.setAnular(true);
			}else{
				user.setAnular(false);
			}
			if (xpath.compile("/usr/permisos/in_decidir").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				user.setDecidir(true);
			}else{
				user.setDecidir(false);
			}
			if (xpath.compile("/usr/permisos/in_adm").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				user.setAdm(true);
			}else{
				user.setAdm(false);
			}	
			//validar si hay documento abierto.
			if (user.getActivoD()!=0){
				String contextDocxml = motor.p4bObtenerDocumento(0, 1, -1);
				document = builder.parse(new InputSource(new StringReader(contextDocxml)));
				user.setNbConversacion(xpath.compile("/FOLDER/@nb_conversacion").evaluate(document, XPathConstants.STRING).toString());
			}
			
			/*document.getDocumentElement().normalize();
			NodeList listServ =document.getElementsByTagName("usr");
			Node nodo = (Node) listServ.item(0);
	        Element element = (Element) nodo;
	        
	        user.setFchultconex(element.getElementsByTagName("fchultconex").item(0).getTextContent());*/
	        
			
		}catch(Exception e){
			logger.error("ObtenerDatosUsuario:", e);
		}
		return user;
	}

	@Override
	public User actualizarDatosUsuario(String name, String apellido,String email) {
		User user = new User();
		try{
			logger.info("actualizarDatosUsuario "+apellido);
			motor = ClassFactory.getProcess(engineP);
			motor.p4bActualizarDatosUsuario(name, email, apellido);		
			Integer result = motor.p4bStatus();
			if (result == 0){
				user = obtenerDatosUsuario();
				user.setNombre(name);
				user.setEmail(email);
				user.setApellido(apellido);
			}		
		}catch(Exception e){
			logger.error("actualizarDatosUsuario:", e);
		}
		return user;
	}

	@Override
	public void actualizarSeguridadUsuario(Integer contexto, String clave1, String clave2, String clave3) {
		try{
			motor = ClassFactory.getProcess(engineP);
			motor.p4bActualizarSeguridadUsuario(contexto, clave1, clave2, clave3);		
		}catch(Exception e){
			logger.error("actualizarSeguridadUsuario:", e);
		}
	}

	@Override
	public void recuperarClave(String email, String pregunta, String respuesta) {
		try{
			motor = ClassFactory.getProcess(engineP);
			motor.p4bObtenerClave(email, pregunta, respuesta, 0);		
		}catch(Exception e){
			logger.error("recuperarClave:", e);
		}		
	}
	
	@Override
	public void validarRespuestaRecuperar(String email, String pregunta, String respuesta){
		
		try{
			motor = ClassFactory.getProcess(engineP);
			motor.p4bObtenerClave(email, pregunta, respuesta, -1);		
		}catch(Exception e){
			logger.error("validarRespuestaRecuperar:", e);
		}		
	}
	
}
