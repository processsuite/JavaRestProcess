/**
 * SimpleDocumentManager.java 
 *
 */
package com.process.business.document.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.process.business.document.DocumentManager;
import com.process.business.environment.EnvironmentManager;
import com.process.business.helper.ClassFactory;
import com.process.business.helper.ConexionBD;
import com.process.business.helper.c_Process;
import com.process.domain.document.Agent;
import com.process.domain.document.Anexo;
import com.process.domain.document.Destino;
import com.process.domain.document.Doc;
import com.process.domain.document.EventAgent;
import com.process.domain.document.Field;
import com.process.domain.document.FileItem;
import com.process.domain.document.FileMatriz;
import com.process.domain.document.Form;
import com.process.domain.document.Group;
import com.process.domain.document.ItemOption;
import com.process.domain.document.ListEvent;
import com.process.domain.document.Matriz;
import com.process.domain.document.Puesto;
import com.process.domain.document.RespDataService;
import com.process.domain.document.SendMsg;
import com.process.domain.document.WfDest;
import com.process.domain.document2.Campo;
import com.process.domain.document2.Doc2;
import com.process.domain.document2.Fila;
import com.process.domain.document2.Forma;
import com.process.domain.document2.GrupoCampos;
import com.process.domain.environment.Environment;

import org.eclipse.persistence.jaxb.JAXBContextFactory;

/**
 * Business Implementation SimpleDocumentManager for document Module 
 * @author Oswel Sanchez
 * 
 */
@Service("documentManager")
public class SimpleDocumentManager implements DocumentManager {
    
	private static final Logger logger = Logger.getLogger(SimpleDocumentManager.class);
	
	private c_Process motor;
	
	private Integer engineP;
	
    @Autowired
	private EnvironmentManager environmentManager;		
	
	@Override
	public void setEngineId(Integer engineId) {
		engineP = engineId;
	}	
	
	@Override
	public Doc crearDocumento(Integer wfp, Integer frmn, String environment) {
		Doc doc = new Doc();
		try{
			motor = ClassFactory.getProcess(engineP);
			Integer result =   motor.p4bCrearDocumento(wfp);
			if (result == -1){
				doc = obtenerDocumento(frmn);	
				//manejo cliente   
				Environment env = environmentManager.getEnvironment(environment);
				if (env.getMatriz().getWebAmbientes().toUpperCase().equals("S")){
					//archivo= Matriz_Ambiente["i_RepInclude"] + "/" + strAmbiente+ "/" + NroConversacion +"/ManejoCliente-"+NroFormulario+"-"+wf_amc+"-"+Estado+".js";
					doc.setFileClient(env.getMatriz().getiRepInclude()+"/"+environment+"/"+doc.getWfp()+"/ManejoCliente-"+doc.getForms().get(0).getFrmn()+"-"+doc.getWfa()+"-"+doc.getEstado()+".js");
					//archivo= Matriz_Ambiente["i_RepInclude"] + "/" + strAmbiente+ "/" + NroConversacionPadre +"/ManejoCliente-"+NroConversacionPadre+".js";
					doc.setFileClientGeneral(env.getMatriz().getiRepInclude()+"/"+environment+"/"+doc.getWfp()+"/ManejoCliente-"+doc.getWfp()+".js");
				}else{
					//archivo= "include/" + NroConversacion +"/ManejoCliente-"+NroFormulario+"-"+wf_amc+"-"+Estado+".js";
					doc.setFileClient("include/"+doc.getWfp()+"/ManejoCliente-"+doc.getForms().get(0).getFrmn()+"-"+doc.getWfa()+"-"+doc.getEstado()+".js");
					//archivo= "include/" + NroConversacionPadre +"/ManejoCliente-"+NroConversacionPadre+".js";
					doc.setFileClientGeneral("include/"+doc.getWfp()+"/ManejoCliente-"+doc.getWfp()+".js");
				}
				doc.setRepAgentes(env.getMatriz().getRepAgentes());
			}			
		}catch(Exception e){
			logger.error("crearDocumento:", e);
		}
		return doc;
	}
	
	@Override
	public Doc2 crearDocumento1(Integer wfp, Integer frmn, String environment){
		Doc2 doc = new Doc2();
		try {
			motor = ClassFactory.getProcess(engineP);
			Integer result =   motor.p4bCrearDocumento(wfp);
			if (result == -1){
				doc = obtenerDocumento1(frmn);	
				
				Environment env = environmentManager.getEnvironment(environment);
				doc.setRepAgentes(env.getMatriz().getRepAgentes());
			}
		} catch (Exception e) {
			logger.error("crearDocumento1:", e);
		}
		return doc;
	}

	@Override
	public Doc abrirDocumento(Integer nuDoc, Integer nuInst, Integer wfa, String environment) {
		Doc doc = new Doc();		
		try{
			motor = ClassFactory.getProcess(engineP);
			Integer result = motor.p4bAbrirDocumento(nuDoc, nuInst, wfa);	
			if (result == -1){
				doc = obtenerDocumento(0);
				//manejo cliente   
				Environment env = environmentManager.getEnvironment(environment);
				if (env.getMatriz().getWebAmbientes().toUpperCase().equals("S")){
					//archivo= Matriz_Ambiente["i_RepInclude"] + "/" + strAmbiente+ "/" + NroConversacion +"/ManejoCliente-"+NroFormulario+"-"+wf_amc+"-"+Estado+".js";
					doc.setFileClient(env.getMatriz().getiRepInclude()+"/"+environment+"/"+doc.getWfp()+"/ManejoCliente-"+doc.getForms().get(0).getFrmn()+"-"+doc.getWfa()+"-"+doc.getEstado()+".js");
					//archivo= Matriz_Ambiente["i_RepInclude"] + "/" + strAmbiente+ "/" + NroConversacionPadre +"/ManejoCliente-"+NroConversacionPadre+".js";
					doc.setFileClientGeneral(env.getMatriz().getiRepInclude()+"/"+environment+"/"+doc.getWfp()+"/ManejoCliente-"+doc.getWfp()+".js");
				}else{
					//archivo= "include/" + NroConversacion +"/ManejoCliente-"+NroFormulario+"-"+wf_amc+"-"+Estado+".js";
					doc.setFileClient("include/"+doc.getWfp()+"/ManejoCliente-"+doc.getForms().get(0).getFrmn()+"-"+doc.getWfa()+"-"+doc.getEstado()+".js");
					//archivo= "include/" + NroConversacionPadre +"/ManejoCliente-"+NroConversacionPadre+".js";
					doc.setFileClientGeneral("include/"+doc.getWfp()+"/ManejoCliente-"+doc.getWfp()+".js");
				}
				doc.setRepAgentes(env.getMatriz().getRepAgentes());
			}			
		}catch(Exception e){
			logger.error("abrirDocumento:", e);
		}
		return doc;
	}	
	
	public Doc2 abrirDocumento1(Integer nuDoc, Integer nuInst, Integer wfa, String environment) {
		Doc2 doc = new Doc2();
		try{
			motor = ClassFactory.getProcess(engineP);
			Integer result = motor.p4bAbrirDocumento(nuDoc, nuInst, wfa);	
			if (result == -1){
				doc = obtenerDocumento1(0);
			}
		}catch(Exception e){
			logger.error("abrirDocumento:", e);
		}			
		return doc;
	}
	
	@Override
	public Doc abrirDocumentoLectura(Integer nuDoc, Integer nuInst, String environment) {
		Doc doc = new Doc();		
		try{
			motor = ClassFactory.getProcess(engineP);
			Boolean result = motor.p4bAbrirDocumentoLectura(nuDoc, nuInst);	
			if (result){
				//logger.info("----->p4bAbrirDocumentoLectura:true");
				doc = obtenerDocumentoLectura(0);
				//manejo cliente   
				Environment env = environmentManager.getEnvironment(environment);
				if (env.getMatriz().getWebAmbientes().toUpperCase().equals("S")){
					//archivo= Matriz_Ambiente["i_RepInclude"] + "/" + strAmbiente+ "/" + NroConversacion +"/ManejoCliente-"+NroFormulario+"-"+wf_amc+"-"+Estado+".js";
					doc.setFileClient(env.getMatriz().getiRepInclude()+"/"+environment+"/"+doc.getWfp()+"/ManejoCliente-"+doc.getForms().get(0).getFrmn()+"-"+doc.getWfa()+"-"+doc.getEstado()+".js");
					//archivo= Matriz_Ambiente["i_RepInclude"] + "/" + strAmbiente+ "/" + NroConversacionPadre +"/ManejoCliente-"+NroConversacionPadre+".js";
					doc.setFileClientGeneral(env.getMatriz().getiRepInclude()+"/"+environment+"/"+doc.getWfp()+"/ManejoCliente-"+doc.getWfp()+".js");
				}else{
					//archivo= "include/" + NroConversacion +"/ManejoCliente-"+NroFormulario+"-"+wf_amc+"-"+Estado+".js";
					doc.setFileClient("include/"+doc.getWfp()+"/ManejoCliente-"+doc.getForms().get(0).getFrmn()+"-"+doc.getWfa()+"-"+doc.getEstado()+".js");
					//archivo= "include/" + NroConversacionPadre +"/ManejoCliente-"+NroConversacionPadre+".js";
					doc.setFileClientGeneral("include/"+doc.getWfp()+"/ManejoCliente-"+doc.getWfp()+".js");
				}
				doc.setRepAgentes(env.getMatriz().getRepAgentes());
			}			
		}catch(Exception e){
			logger.error("abrirDocumentoLectura:", e);
		}
		return doc;
	}
	
	@Override
	public Doc2 abrirDocumentoLectura1(Integer nuDoc, Integer nuInst, String environment) {
		Doc2 doc = new Doc2();
		try{
			motor = ClassFactory.getProcess(engineP);
			Boolean result = motor.p4bAbrirDocumentoLectura(nuDoc, nuInst);
			//logger.info("resultado abrirDocumentoLectura1= "+ result);
			if (result){
				doc = obtenerDocumentoLectura1(0);
			}
		}catch(Exception e){
			logger.error("abrirDocumento:", e);
		}			
		return doc;
	}

	@Override
	public Doc2 obtenerDocumentoLectura1(Integer frmn) {
		Doc2 doc = new Doc2();
		try {
			motor = ClassFactory.getProcess(engineP);
			String contextDocxml = motor.p4bObtenerDocumento(0, 1, -1);
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(contextDocxml)));
			
			JAXBContext context = JAXBContextFactory.createContext(new Class[] {Doc2.class}, null);
			//JAXBContext context = JAXBContext.newInstance(Doc2.class);
			Unmarshaller un = context.createUnmarshaller();
			doc = (Doc2) un.unmarshal(document);
			
			if (frmn==0){
				for(int x = 0; x<doc.getFormas().getFormas().size();x++){
					if(doc.getFormas().getFormas().get(x).getDefult()== true){
						frmn = doc.getFormas().getFormas().get(x).getFrmn();
					}
				}
			}
			
			String dataDocXml = motor.p4bObtenerCamposDoc(frmn, 0, -1);
			
			//logger.info("forma de  obtenerDocumentoLectura1 "+dataDocXml);
			document = builder.parse(new InputSource(new StringReader(dataDocXml)));
			
			JAXBContext jc = JAXBContextFactory.createContext(new Class[] {Forma.class}, null);
			un = jc.createUnmarshaller();
			
			Forma forma = new Forma();
			forma = (Forma) un.unmarshal(document);
			
			for(int i=0;i < forma.getListGrupoCampos().size(); i++){					
				Boolean visible = false;
				for(int j=0;j < forma.getListGrupoCampos().get(i).getCampo().size(); j++){
					if (forma.getListGrupoCampos().get(i).getCampo().get(j).getLectura() != null && forma.getListGrupoCampos().get(i).getCampo().get(j).getLectura()){
						visible = true;
					}
					if (forma.getListGrupoCampos().get(i).getCampo().get(j).getTipo().equals("M") || forma.getListGrupoCampos().get(i).getCampo().get(j).getTipo().equals("S")){
						for(int y=0;y < forma.getListGrupoCampos().get(i).getCampo().get(j).getCampos().size(); y++){
							if(forma.getListGrupoCampos().get(i).getCampo().get(j).getCampos().get(y).getLectura()){
								visible = true;
							}
						}			
					}
					if(visible == true){
						break;
					}
				}
				//validar visibilidad del grupo
				if (visible && forma.getListGrupoCampos().get(i).getNombre()!=""){
					forma.getListGrupoCampos().get(i).setVisible(true);
				}else{
					forma.getListGrupoCampos().get(i).setVisible(false);
				}
			}
			doc.setForma(forma);
			
		} catch (Exception e) {
			logger.error("obtenerDocumentoLectura:", e);
		}
		
		return doc;
	}
	
	@Override
	public Doc obtenerDocumentoLectura(Integer frmn) {
		Doc doc = new Doc();		
		try{
			motor = ClassFactory.getProcess(engineP);
			String contextDocxml = motor.p4bObtenerDocumento(0, 1, -1);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(contextDocxml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();	
			
			doc.setLectura(true);		
			doc.setStatus(Integer.valueOf(xpath.compile("/FOLDER/@status").evaluate(document, XPathConstants.STRING).toString()));
			doc.setEstado(xpath.compile("/FOLDER/@estado").evaluate(document, XPathConstants.STRING).toString());
			doc.setRemitente(xpath.compile("/FOLDER/@cliente").evaluate(document, XPathConstants.STRING).toString());
			doc.setFechaEnvio(xpath.compile("/FOLDER/@fecha").evaluate(document, XPathConstants.STRING).toString());
			doc.setResumen(xpath.compile("/FOLDER/@resumen").evaluate(document, XPathConstants.STRING).toString());
			doc.setObservacion(xpath.compile("/FOLDER/@observacion").evaluate(document, XPathConstants.STRING).toString());
			doc.setNbProceso(xpath.compile("/FOLDER/@nb_proceso").evaluate(document, XPathConstants.STRING).toString());
			doc.setNbConversacion(xpath.compile("/FOLDER/@nb_conversacion").evaluate(document, XPathConstants.STRING).toString());
			doc.setWfp(Integer.valueOf(xpath.compile("/FOLDER/@wfp").evaluate(document, XPathConstants.STRING).toString()));
			doc.setWfa(Integer.valueOf(xpath.compile("/FOLDER/@wfa").evaluate(document, XPathConstants.STRING).toString()));
			doc.setNuDoc(Integer.valueOf(xpath.compile("/FOLDER/@nu_doc").evaluate(document, XPathConstants.STRING).toString()));
			doc.setNuDocP(Integer.valueOf(xpath.compile("/FOLDER/@nu_doc_p").evaluate(document, XPathConstants.STRING).toString()));
			if (frmn==0){
				frmn = Integer.valueOf(xpath.compile("/FOLDER/FORMAS/FORMA[@default='S']/@frmn").evaluate(document, XPathConstants.STRING).toString());
			}
			String dataDocXml = motor.p4bObtenerCamposDoc(frmn, 0, -1);
			document = builder.parse(new InputSource(new StringReader(dataDocXml)));
			
			Form frm = new Form();
			frm.setNuDoc(Integer.valueOf(xpath.compile("/FORMA/@nu_doc").evaluate(document, XPathConstants.STRING).toString()));
			frm.setNombre(xpath.compile("/FORMA/@nombre").evaluate(document, XPathConstants.STRING).toString());
			frm.setImagen(xpath.compile("/FORMA/@imagen").evaluate(document, XPathConstants.STRING).toString());
			frm.setAncho(Double.valueOf(xpath.compile("/FORMA/@ancho").evaluate(document, XPathConstants.STRING).toString()));
			frm.setAlto(Double.valueOf(xpath.compile("/FORMA/@alto").evaluate(document, XPathConstants.STRING).toString()));
			frm.setFrmn(Integer.valueOf(xpath.compile("/FORMA/@frmn").evaluate(document, XPathConstants.STRING).toString()));
			frm.setFrmv(Integer.valueOf(xpath.compile("/FORMA/@frmv").evaluate(document, XPathConstants.STRING).toString()));
			if (xpath.compile("/FORMA/@default").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				frm.setDefult(true);
			}else{
				frm.setDefult(false);
			}				
			NodeList nodes = document.getElementsByTagName("GRUPO_CAMPOS");
			for(int i=0;i < nodes.getLength(); i++){					
				Group grp = new Group();
				Boolean visible = false;
				grp.setNombre(xpath.compile("/FORMA/GRUPO_CAMPOS[" + (i+1) +"]/@nombre").evaluate(document, XPathConstants.STRING).toString());
				NodeList nodesChild = nodes.item(i).getChildNodes();
				for(int j=0;j < nodesChild.getLength(); j++){						
					Field field = loadField(nodesChild.item(j), false);	
					if (field.getLectura()){
						visible = true;
					}
					String tipo = field.getTipo();
					if (tipo.equals("M")){
						field.setMatriz(loadMatriz(nodesChild.item(j)));
						if (field.getMatriz().getVisible()){
							field.setLectura(true); 
							visible = true;
						}else{
							field.setLectura(false);
						}
						if (field.getMatriz().getEscritura()){
							field.setEscritura(true); 
						}else{
							field.setEscritura(false);
						}						
					}
					grp.getFields().add(field);
				}
				//validar visibilidad del grupo
				if (visible && grp.getNombre()!=""){
					grp.setVisible(true);
				}else{
					grp.setVisible(false);
				}
				frm.getGroups().add(grp);
			}
			doc.getForms().add(frm);
		}catch(Exception e){
			logger.error("obtenerDocumentoLectura:", e);
		}
		return doc;

	}
	
	@Override
	public Doc2 obtenerDocumento1(Integer frmn) {
		Doc2 doc = new Doc2();
		try{
			motor = ClassFactory.getProcess(engineP);
			String contextDocxml = motor.p4bObtenerDocumento(0, 1, -1);
			//logger.info("folder de  obtenerDocumento1 "+contextDocxml);
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(contextDocxml)));
			
			JAXBContext context = JAXBContextFactory.createContext(new Class[] {Doc2.class}, null);
			//JAXBContext context = JAXBContext.newInstance(Doc2.class);
			Unmarshaller un = context.createUnmarshaller();
			doc = (Doc2) un.unmarshal(document);
			
			if (frmn==0){
				for(int x = 0; x<doc.getFormas().getFormas().size();x++){
					if(doc.getFormas().getFormas().get(x).getDefult()== true){
						frmn = doc.getFormas().getFormas().get(x).getFrmn();
					}
				}
			}	
			
			String dataDocXml = motor.p4bObtenerDocumento(frmn, 0, -1);
			//logger.info("forma de  obtenerDocumento1 "+dataDocXml);
			document = builder.parse(new InputSource(new StringReader(dataDocXml)));
			
			JAXBContext jc = JAXBContextFactory.createContext(new Class[] {Forma.class}, null);
			un = jc.createUnmarshaller();
			
			Forma forma = new Forma();
			forma = (Forma) un.unmarshal(document);
			
			for(int i=0;i < forma.getListGrupoCampos().size(); i++){					
				Boolean visible = false;
				for(int j=0;j < forma.getListGrupoCampos().get(i).getCampo().size(); j++){
					if (forma.getListGrupoCampos().get(i).getCampo().get(j).getLectura() != null && forma.getListGrupoCampos().get(i).getCampo().get(j).getLectura()){
						visible = true;
					}
					if (forma.getListGrupoCampos().get(i).getCampo().get(j).getTipo().equals("M") || forma.getListGrupoCampos().get(i).getCampo().get(j).getTipo().equals("S")){
						for(int y=0;y < forma.getListGrupoCampos().get(i).getCampo().get(j).getCampos().size(); y++){
							if(forma.getListGrupoCampos().get(i).getCampo().get(j).getCampos().get(y).getLectura()){
								visible = true;
							}
						}			
					}
					if(visible == true){
						break;
					}
				}
				//validar visibilidad del grupo
				if (visible && forma.getListGrupoCampos().get(i).getNombre()!=""){
					forma.getListGrupoCampos().get(i).setVisible(true);
				}else{
					forma.getListGrupoCampos().get(i).setVisible(false);
				}
			}
			doc.setForma(forma);
		}catch(Exception e){
			logger.error("obtenerDocumento1:", e);
		}
		return doc;
	}
	
	
	
	
	@Override
	public Doc obtenerDocumento(Integer frmn) {
		Doc doc = new Doc();		
		try{
			motor = ClassFactory.getProcess(engineP);
			String contextDocxml = motor.p4bObtenerDocumento(0, 1, -1);
			//logger.info(contextDocxml);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(contextDocxml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();				
			
			if (xpath.compile("/FOLDER/@adquirible").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				doc.setAdquirible(true);
			}else{
				doc.setAdquirible(false);
			}
			if (xpath.compile("/FOLDER/@lectura").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				doc.setLectura(true);
			}else{
				doc.setLectura(false);
			}				
			if (xpath.compile("/FOLDER/@recuperable").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				doc.setRecuperable(true);
			}else{
				doc.setRecuperable(false);
			}	
			if (xpath.compile("/FOLDER/@notificacion").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				doc.setNotificacion(true);
			}else{
				doc.setNotificacion(false);
			}
			if (xpath.compile("/FOLDER/@espera").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				doc.setEspera(true);
			}else{
				doc.setEspera(false);
			}	
			if (doc.getEspera()){
				NodeList nodesEspera = document.getElementsByTagName("origen");
				for(int i=0;i < nodesEspera.getLength(); i++){
					doc.getEsperaOrigen().add(xpath.compile("@nb_wf").evaluate(nodesEspera.item(i), XPathConstants.STRING).toString());
				}
			}
			doc.setStatus(Integer.valueOf(xpath.compile("/FOLDER/@status").evaluate(document, XPathConstants.STRING).toString()));
			doc.setEstado(xpath.compile("/FOLDER/@estado").evaluate(document, XPathConstants.STRING).toString());
			doc.setRemitente(xpath.compile("/FOLDER/@cliente").evaluate(document, XPathConstants.STRING).toString());
			doc.setFechaEnvio(xpath.compile("/FOLDER/@fecha").evaluate(document, XPathConstants.STRING).toString());
			doc.setResumen(xpath.compile("/FOLDER/@resumen").evaluate(document, XPathConstants.STRING).toString());
			doc.setObservacion(xpath.compile("/FOLDER/@observacion").evaluate(document, XPathConstants.STRING).toString());
			doc.setNbProceso(xpath.compile("/FOLDER/@nb_proceso").evaluate(document, XPathConstants.STRING).toString());
			doc.setNbConversacion(xpath.compile("/FOLDER/@nb_conversacion").evaluate(document, XPathConstants.STRING).toString());
			doc.setWfp(Integer.valueOf(xpath.compile("/FOLDER/@wfp").evaluate(document, XPathConstants.STRING).toString()));
			doc.setWfa(Integer.valueOf(xpath.compile("/FOLDER/@wfa").evaluate(document, XPathConstants.STRING).toString()));
			doc.setNuDoc(Integer.valueOf(xpath.compile("/FOLDER/@nu_doc").evaluate(document, XPathConstants.STRING).toString()));
			doc.setNuDocP(Integer.valueOf(xpath.compile("/FOLDER/@nu_doc_p").evaluate(document, XPathConstants.STRING).toString()));
			NodeList nodesFormContext = document.getElementsByTagName("FORMA");
			for(int i=0;i < nodesFormContext.getLength(); i++){
				Form frm = new Form();				
				frm.setNombre(xpath.compile("/FOLDER/FORMAS/FORMA[" + (i+1) + "]/@nombre").evaluate(document, XPathConstants.STRING).toString());
				frm.setImagen(xpath.compile("/FOLDER/FORMAS/FORMA[" + (i+1) + "]/@imagen").evaluate(document, XPathConstants.STRING).toString());
				frm.setAncho(Double.valueOf(xpath.compile("/FOLDER/FORMAS/FORMA[" + (i+1) + "]/@ancho").evaluate(document, XPathConstants.STRING).toString()));
				frm.setAlto(Double.valueOf(xpath.compile("/FOLDER/FORMAS/FORMA[" + (i+1) + "]/@alto").evaluate(document, XPathConstants.STRING).toString()));
				frm.setFrmn(Integer.valueOf(xpath.compile("/FOLDER/FORMAS/FORMA[" + (i+1) + "]/@frmn").evaluate(document, XPathConstants.STRING).toString()));
				frm.setFrmv(Integer.valueOf(xpath.compile("/FOLDER/FORMAS/FORMA[" + (i+1) + "]/@frmv").evaluate(document, XPathConstants.STRING).toString()));
				doc.getFormsContext().add(frm);
			}
			NodeList nodesAgentContext = document.getElementsByTagName("AGENTE");
			for(int i=0;i < nodesAgentContext.getLength(); i++){
				Agent agent = new Agent();	
				agent.setCdAg(xpath.compile("/FOLDER/AGENTES/AGENTE[" + (i+1) + "]/@cd_ag").evaluate(document, XPathConstants.STRING).toString());
				agent.setContexto(xpath.compile("/FOLDER/AGENTES/AGENTE[" + (i+1) + "]/@contexto").evaluate(document, XPathConstants.STRING).toString());
				agent.setDescripcion(xpath.compile("/FOLDER/AGENTES/AGENTE[" + (i+1) + "]/@descripcion").evaluate(document, XPathConstants.STRING).toString());
				if (xpath.compile("/FOLDER/AGENTES/AGENTE[" + (i+1) + "]/@href").evaluate(document, XPathConstants.STRING)!=null){
					agent.setHref(xpath.compile("/FOLDER/AGENTES/AGENTE[" + (i+1) + "]/@href").evaluate(document, XPathConstants.STRING).toString());	
				}else{
					agent.setHref(null);
				}				
				agent.setTipo(xpath.compile("/FOLDER/AGENTES/AGENTE[" + (i+1) + "]/@tipo").evaluate(document, XPathConstants.STRING).toString());
				doc.getAgents().add(agent);
			}			
			if (frmn==0){
				frmn = Integer.valueOf(xpath.compile("/FOLDER/FORMAS/FORMA[@default='S']/@frmn").evaluate(document, XPathConstants.STRING).toString());
			}
			String dataDocXml = motor.p4bObtenerDocumento(frmn, 0, -1);
			//logger.info(dataDocXml);
			document = builder.parse(new InputSource(new StringReader(dataDocXml)));
			
			Form frm = new Form();
			frm.setNuDoc(Integer.valueOf(xpath.compile("/FORMA/@nu_doc").evaluate(document, XPathConstants.STRING).toString()));
			frm.setNombre(xpath.compile("/FORMA/@nombre").evaluate(document, XPathConstants.STRING).toString());
			frm.setImagen(xpath.compile("/FORMA/@imagen").evaluate(document, XPathConstants.STRING).toString());
			frm.setAncho(Double.valueOf(xpath.compile("/FORMA/@ancho").evaluate(document, XPathConstants.STRING).toString()));
			frm.setAlto(Double.valueOf(xpath.compile("/FORMA/@alto").evaluate(document, XPathConstants.STRING).toString()));
			frm.setFrmn(Integer.valueOf(xpath.compile("/FORMA/@frmn").evaluate(document, XPathConstants.STRING).toString()));
			frm.setFrmv(Integer.valueOf(xpath.compile("/FORMA/@frmv").evaluate(document, XPathConstants.STRING).toString()));
			if (xpath.compile("/FORMA/@default").evaluate(document, XPathConstants.STRING).toString().equals("S")){
				frm.setDefult(true);
			}else{
				frm.setDefult(false);
			}				
			NodeList nodes = document.getElementsByTagName("GRUPO_CAMPOS");
			for(int i=0;i < nodes.getLength(); i++){					
				Group grp = new Group();
				Boolean visible = false;
				grp.setNombre(xpath.compile("/FORMA/GRUPO_CAMPOS[" + (i+1) +"]/@nombre").evaluate(document, XPathConstants.STRING).toString());
				NodeList nodesChild = nodes.item(i).getChildNodes();
				for(int j=0;j < nodesChild.getLength(); j++){						
					Field field = loadField(nodesChild.item(j), false);	
					if (field.getLectura()){
						visible = true;
					}
					String tipo = field.getTipo();
					if (tipo.equals("M")){
						field.setMatriz(loadMatriz(nodesChild.item(j)));
						if (field.getMatriz().getVisible()){
							field.setLectura(true); 
							visible = true;
						}else{
							field.setLectura(false);
						}
						if (field.getMatriz().getEscritura()){
							field.setEscritura(true); 
						}else{
							field.setEscritura(false);
						}						
					}
					grp.getFields().add(field);
				}
				//validar visibilidad del grupo
				if (visible && grp.getNombre()!=""){
					grp.setVisible(true);
				}else{
					grp.setVisible(false);
				}
				frm.getGroups().add(grp);
			}
			doc.getForms().add(frm);
		}catch(Exception e){
			logger.error("obtenerDocumento:", e);
		}
		return doc;
	}

	private Matriz loadMatriz(Node node){
		Matriz matriz = new Matriz();
		try{
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			Boolean visible = false;
			Boolean escritura = false;
			
			NodeList nodesChild = node.getChildNodes();
			for(int k=0;k < nodesChild.getLength(); k++){
				if (nodesChild.item(k).getNodeName().equals("CAMPO")){
					Field field = loadField(nodesChild.item(k),true);
					if (field.getLectura()){
						visible = true;
					}
					if (field.getEscritura()){
						escritura = true;
					}					
					matriz.getFields().add(field);
				}else{
					NodeList filesChild = nodesChild.item(k).getChildNodes();
					for(int j=0;j < filesChild.getLength(); j++){
						FileMatriz fileMatriz = new FileMatriz();
						//fileMatriz.setNumero(Integer.valueOf(xpath.compile("@numero").evaluate(filesChild.item(j), XPathConstants.STRING).toString()));
						NodeList itemsChild = filesChild.item(j).getChildNodes();
						for(int i=0;i < itemsChild.getLength(); i++){
							FileItem fileItem = new FileItem();
							fileItem.setName(xpath.compile("@nombre").evaluate(itemsChild.item(i), XPathConstants.STRING).toString());
							fileItem.setValue(itemsChild.item(i).getTextContent());
							fileItem.setChange(false);
							if (fileItem.getValue().equals("T")){
								fileItem.setChecked(true);
							}else{
								fileItem.setChecked(false);
							}
							fileMatriz.getFileItems().add(fileItem);
						}
						matriz.getFiles().add(fileMatriz);						
					}
				}
			}
			//validar visibilidad de la matriz
			if (visible){
				matriz.setVisible(true);
			}else{
				matriz.setVisible(false);
			}
			//validar escritura de la matriz
			if (escritura){
				matriz.setEscritura(true);
			}else{
				matriz.setEscritura(false);
			}
		}catch(Exception e){	
			logger.error("loadMatriz:", e);
		}
	
		return matriz;
	}
	
	private Field loadField(Node node, Boolean matriz){
		Field field = new Field();
		try{
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();	
			
			field.setChange(false);
			field.setTipo(xpath.compile("@tipo").evaluate(node, XPathConstants.STRING).toString());	
			String tipo = field.getTipo();
			field.setId(Integer.valueOf(xpath.compile("@id").evaluate(node, XPathConstants.STRING).toString()));
			field.setNombre(xpath.compile("@nombre").evaluate(node, XPathConstants.STRING).toString());
			field.setLeft(Double.valueOf(xpath.compile("@left").evaluate(node, XPathConstants.STRING).toString()));
			field.setTop(Double.valueOf(xpath.compile("@top").evaluate(node, XPathConstants.STRING).toString()));						
			field.setRight(Double.valueOf(xpath.compile("@right").evaluate(node, XPathConstants.STRING).toString()));
			field.setBottom(Double.valueOf(xpath.compile("@bottom").evaluate(node, XPathConstants.STRING).toString()));
			field.setDescripcion(xpath.compile("@descripcion").evaluate(node, XPathConstants.STRING).toString());
			field.setHref(xpath.compile("@href").evaluate(node, XPathConstants.STRING).toString());
			if (xpath.compile("@lectura").evaluate(node, XPathConstants.STRING).toString().equals("S")){
				field.setLectura(true);
			}else{
				field.setLectura(false);
			}				
			if (xpath.compile("@escritura").evaluate(node, XPathConstants.STRING).toString().equals("S")){
				field.setEscritura(true);
			}else{
				field.setEscritura(false);
			}	
			if (xpath.compile("@obligatorio").evaluate(node, XPathConstants.STRING).toString().equals("S")){
				field.setObligatorio(true);
			}else{
				field.setObligatorio(false);
			}
			if (xpath.compile("@evento").evaluate(node, XPathConstants.STRING).toString().equals("S")){
				field.setEvento(true);
			}else{
				field.setEvento(false);
			}		
			if (xpath.compile("@listaajax").evaluate(node, XPathConstants.STRING).toString().equals("S")){
				field.setListaAjax(true);
			}else{
				field.setListaAjax(false);
			}			
			field.setDesEvento(xpath.compile("@desevento").evaluate(node, XPathConstants.STRING).toString());
			if ((tipo.equals("T")) || (tipo.equals("O")) || (tipo.equals("Q"))){
				String longMax = xpath.compile("@longitudmaxima").evaluate(node, XPathConstants.STRING).toString();
				if (longMax!=""){
					field.setLongitudMaxima(Integer.valueOf(longMax));
				}				
				field.setValue(node.getTextContent());
			}else if ((tipo.equals("F")) || (tipo.equals("H"))){
				field.setValue(node.getTextContent());	
			}else if ((tipo.equals("N")) || (tipo.equals("C"))){
				field.setMinimo(Double.valueOf(xpath.compile("@minimo").evaluate(node, XPathConstants.STRING).toString()));
				field.setMaximo(Double.valueOf(xpath.compile("@maximo").evaluate(node, XPathConstants.STRING).toString()));
				String longMax = xpath.compile("@longitudmaxima").evaluate(node, XPathConstants.STRING).toString();
				if (longMax!=""){
					field.setLongitudMaxima(Integer.valueOf(longMax));
				}				
				field.setValue(node.getTextContent());
				if (node.getTextContent()!=""){
					//field.setValueNumber(Double.valueOf(node.getTextContent()));	
				}else{
					//field.setValueNumber(0.0);
				}
				
			}else if (tipo.equals("L") || (matriz && tipo.equals("A"))){							
				if (node.getChildNodes().getLength()>0){
					if (xpath.compile("@multiple").evaluate(node.getChildNodes().item(0), XPathConstants.STRING).toString().equals("S")){
						field.setMultiple(true);
					}else{
						field.setMultiple(false);
					}								
					NodeList nodesChildOptions = node.getChildNodes().item(0).getChildNodes();
					ItemOption optSel = null; 
					for(int k=0;k < nodesChildOptions.getLength(); k++){
						ItemOption opt = new ItemOption();
						if (xpath.compile("@seleccionado").evaluate(nodesChildOptions.item(k), XPathConstants.STRING).toString().equals("S")){
							opt.setSelected(true);
						}else{
							opt.setSelected(false);
						}
						if (xpath.compile("@codigo").evaluate(nodesChildOptions.item(k), XPathConstants.STRING).toString()!=""){
							opt.setKey(xpath.compile("@codigo").evaluate(nodesChildOptions.item(k), XPathConstants.STRING).toString());	
						}else{
							opt.setKey(nodesChildOptions.item(k).getTextContent());
						}						
						opt.setValue(nodesChildOptions.item(k).getTextContent());
						if (opt.getSelected()){
							optSel = opt;
						}
						field.getOptions().add(opt);
						//marcar opcion seleccionada
						if (field.getMultiple()){
							if (optSel!=null){
								if (optSel.getKey()!=""){
									field.getValueM().add(optSel.getKey());
								}else if (optSel.getValue()!=""){
									field.getValueM().add(optSel.getValue());
								}else{
									field.getValueM().add("");
								}
							}else{
								field.getValueM().add("");
							}							
						}else{
							if (optSel!=null){
								if (optSel.getKey()!=""){
									field.setValue(optSel.getKey());
								}else if (optSel.getValue()!=""){
									field.setValue(optSel.getValue());
								}else{
									field.setValue("");
								}
							}else{
								field.setValue("");
							}							
						}						
					}					
				}
			}else if (tipo.equals("S")){
				Boolean visible = false;
				Boolean obligatorio = false;
				for(int k=0;k <  node.getChildNodes().getLength(); k++){								
					Field altField = loadField(node.getChildNodes().item(k), matriz);
					altField.setIdPadre(field.getId());
					field.getAlternativas().add(altField);
					if (altField.getLectura()){
						visible = true;
					}
					if (altField.getObligatorio()){
						obligatorio = true;
					}
					field.setExclusivo(altField.getExclusivo());
					if (altField.getExclusivo() && altField.getChecked()){
						field.setValue(altField.getNombre());
					}
				}
				//validar visibilidad de las alternativas
				if (visible){
					field.setVisible(true);
				}else{
					field.setVisible(false);
				}
				if (obligatorio){
					field.setObligatorio(true);
				}else{
					field.setObligatorio(false);
				}
			}else if (tipo.equals("A") && !matriz){
				if (xpath.compile("@exclusivo").evaluate(node, XPathConstants.STRING).toString().equals("S")){
					field.setExclusivo(true);
				}else{
					field.setExclusivo(false);
				}					
				field.setValue(node.getTextContent());
				if (node.getTextContent().equals("T")){						
					field.setChecked(true);
				}else{
					field.setChecked(false);
				}
			}else if (tipo.equals("V")){
				field.setExclusivo(false);
				field.setValue(node.getTextContent());
				if (node.getTextContent().equals("T")){
					field.setChecked(true);
				}else{
					field.setChecked(false);
				}				
			}
		}catch(Exception e){	
			logger.error("loadField:", e);
		}
		return field;
	}
	
	@Override
	public void asignarValorCampoDocumento(String campo, String valor, Integer fila, Integer columna) {
		try{
			motor = ClassFactory.getProcess(engineP);
			motor.p4bAsignarValorCampoDocumento(campo, valor, fila, columna);		
		}catch(Exception e){
			logger.error("asignarValorCampoDocumento:", e);
		}
	}
	
	@Override
	public void guardarForm1(Integer frmn, Forma forma){
		try{
			motor = ClassFactory.getProcess(engineP);
			for(GrupoCampos gc:forma.getListGrupoCampos()){
				for(Campo campo:gc.getCampo()){
					if(!campo.getTipo().equals("M")){
						if(campo.getChange() != null && campo.getChange()){//--verifica si el campo tiene cambios
							if(campo.getTipo().equals("S")){ //alternativ
								for(Campo alt:campo.getCampos()){
									if(alt.getExclusivo()){
										motor.p4bAsignarValorCampoDocumento(campo.getValue(), "T", 0, 0);
										break;
									}else{
										if(alt.getChecked() != null && alt.getChecked()){
											motor.p4bAsignarValorCampoDocumento(alt.getNombre(), "T", 0, 0);
										}else{
											motor.p4bAsignarValorCampoDocumento(alt.getNombre(), "", 0, 0);
										}
									}
								}//finde alternativos exclusivos e inclusivos
							}else if(campo.getTipo().equals("L") && campo.getOpciones().getMultiple()){
								motor.p4bAsignarValorCampoDocumento(campo.getNombre(), StringUtils.collectionToDelimitedString(campo.getValueM(), ","), 0, 0); //se guarda campo multiselect	
							}else{
								motor.p4bAsignarValorCampoDocumento(campo.getNombre(), campo.getValue(), 0, 0);//se guardan campos select y texto
							}
						}
					}else{
						if (campo.getFilasEliminadas()!=""){//elminar xml de filas eliminadas en la vista
							motor.p4bBorrarFilasPaginaMatriz(frmn, campo.getNombre(), 1, campo.getFilasEliminadas());	
						}	
						int indexFil = 1;
						for(Fila fila:campo.getFilas().getFila()){
							int indexCol = 0;
							for(Campo col:campo.getCampos()){
								if(fila.getCampo().get(indexCol).getChange() != null && fila.getCampo().get(indexCol).getChange()){
									if(col.getTipo().equals("V")){// matriz alternativa
										if(fila.getCampo().get(indexCol).getChecked()){
											motor.p4bAsignarValorCampoDocumento(campo.getNombre(), "T", indexFil, indexCol+1);											
										}else{
											motor.p4bAsignarValorCampoDocumento(campo.getNombre(), "", indexFil, indexCol+1);
										}

									}else if(col.getTipo().equals("A") && col.getOpciones().getMultiple()){
										motor.p4bAsignarValorCampoDocumento(campo.getNombre(), StringUtils.collectionToDelimitedString(fila.getCampo().get(indexCol).getValueM(), ","), indexFil, indexCol+1); //se guarda campo multiselect
									}else{
										motor.p4bAsignarValorCampoDocumento(campo.getNombre(), fila.getCampo().get(indexCol).getValue(), indexFil, indexCol+1);
									}
								}
								indexCol++;
							}
							indexFil++;
						}
						
					}
				}
			}
		}catch(Exception e){
			logger.error("guardarform:", e);
		}	
	}

	@Override
	public void guardarform(Integer frmn, Form form) {
		try{
			motor = ClassFactory.getProcess(engineP);
			for(Group gr:form.getGroups()){
				for(Field f:gr.getFields()){
					if (!f.getTipo().equals("M")){
						if (f.getChange()){
							if (f.getTipo().equals("S")){//tipo alternativa
								if (f.getExclusivo()){
									motor.p4bAsignarValorCampoDocumento(f.getValue(), "T", 0, 0);
								}else{
									for(Field alt:f.getAlternativas()){
										if(alt.getChecked()){
											motor.p4bAsignarValorCampoDocumento(alt.getNombre(), "T", 0, 0);
										}else{
											motor.p4bAsignarValorCampoDocumento(alt.getNombre(), "", 0, 0);
										}
									}
								}
							}else if (f.getTipo().equals("L") && f.getMultiple()){
								motor.p4bAsignarValorCampoDocumento(f.getNombre(), StringUtils.collectionToDelimitedString(f.getValueM(), ","), 0, 0);	
							}else{
								motor.p4bAsignarValorCampoDocumento(f.getNombre(), f.getValue(), 0, 0);
							}
						}
					}else{//Matriz
						//borrar filas de la matriz 
						if (f.getMatriz().getFilesDelete()!=""){
							motor.p4bBorrarFilasPaginaMatriz(frmn, f.getNombre(), 1, f.getMatriz().getFilesDelete());	
						}						
						int indexFil = 1;
						for(FileMatriz fil:f.getMatriz().getFiles()){
							int indexCol = 0;							
							for(Field col:f.getMatriz().getFields()){
								if (fil.getFileItems().get(indexCol).getChange()){
									logger.error(f.getNombre() +"---->change:" + fil.getFileItems().get(indexCol).getName() + ":" +fil.getFileItems().get(indexCol).getValue() + "(" + indexFil + "," + (indexCol+1) );
									if (col.getTipo().equals("V")){//tipo alternativa
										if (fil.getFileItems().get(indexCol).getChecked()){
											motor.p4bAsignarValorCampoDocumento(f.getNombre(), "T", indexFil, indexCol+1);	
										}else{
											motor.p4bAsignarValorCampoDocumento(f.getNombre(), "", indexFil, indexCol+1);
										}											
									}else if (col.getTipo().equals("A") && col.getMultiple()){//tipo select multiple
										motor.p4bAsignarValorCampoDocumento(f.getNombre(), StringUtils.collectionToDelimitedString(fil.getFileItems().get(indexCol).getValueM(), ","), indexFil, indexCol+1);
									}else{
										motor.p4bAsignarValorCampoDocumento(f.getNombre(), fil.getFileItems().get(indexCol).getValue(), indexFil, indexCol+1);
									}
								}
								indexCol++;
							}
							indexFil++;
						}
					}
				}
			}			
		}catch(Exception e){
			logger.error("guardarform:", e);
		}
	}

	@Override
	public void guardarDocumento(String observacion) {
		try{
			motor = ClassFactory.getProcess(engineP);
			motor.p4bGuardarDocumento(observacion);		
		}catch(Exception e){
			logger.error("guardarDocumento:", e);
		}
	}

	@Override
	public void cerrarDocumento() {
		try{
			motor = ClassFactory.getProcess(engineP);
			motor.p4bCerrarDocumento();		
		}catch(Exception e){
			logger.error("cerrarDocumento:", e);
		}		
	}

	@Override
	public void cerrarDocumentoLectura() {
		try{
			motor = ClassFactory.getProcess(engineP);
			motor.p4bCerrarDocumentoLectura();		
		}catch(Exception e){
			logger.error("cerrarDocumentoLectura:", e);
		}
	}

	@Override
	public SendMsg adquirirDocumento(String observacion, Boolean email) {
		SendMsg sendMsg = new SendMsg();
		try{
			motor = ClassFactory.getProcess(engineP);
			Integer emailInt = 0;
			if (email){
				emailInt = -1;
			}
			Integer result = motor.p4bAdquirir(observacion, emailInt);
			if (result == -1) {
				sendMsg.setSendError(false);
				sendMsg.setMsgObligatorios("");
				String resultadoXML =  motor.resultadoXML(); 
				if (!resultadoXML.equals("")){					
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document document = builder.parse(new InputSource(new StringReader(resultadoXML)));
					
					XPathFactory xPathfactory = XPathFactory.newInstance();
					XPath xpath = xPathfactory.newXPath();
					
					sendMsg.setAcc(xpath.compile("/wfdestinos/@acc").evaluate(document, XPathConstants.STRING).toString());
					NodeList nodesDest = document.getElementsByTagName("wfdest");
					
					for(int i=0;i < nodesDest.getLength(); i++){
						WfDest wfDest = new WfDest();
						wfDest.setNbProceso(xpath.compile("@nb_proceso").evaluate(nodesDest.item(i), XPathConstants.STRING).toString());
						wfDest.setNbWf(xpath.compile("@nb_wf").evaluate(nodesDest.item(i), XPathConstants.STRING).toString());
						wfDest.setWfa(Integer.valueOf(xpath.compile("@wfa").evaluate(nodesDest.item(i), XPathConstants.STRING).toString()));
						if (xpath.compile("@copia").evaluate(nodesDest.item(i), XPathConstants.STRING).toString().equals("N")){
							wfDest.setCopia(false);
						}else{
							wfDest.setCopia(true);
						}	
						if (xpath.compile("@copiaporemail").evaluate(nodesDest.item(i), XPathConstants.STRING).toString().equals("N")){
							wfDest.setCopiaPorEmail(false);
						}else{
							wfDest.setCopiaPorEmail(true);
						}		
						NodeList nodesChildDest = nodesDest.item(i).getChildNodes();
						for(int k=0;k < nodesChildDest.getLength(); k++){
							Puesto puesto = new Puesto();
							puesto.setNbUsrReemp(xpath.compile("@nb_usr_reemp").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setNbUsrAct(xpath.compile("@nb_usr_act").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setNbPuesto(xpath.compile("@nb_puesto").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setMail(xpath.compile("@mail").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setNuDoc(Integer.valueOf(xpath.compile("@nu_doc").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString()));
							puesto.setNuInstn(Integer.valueOf(xpath.compile("@nu_instn").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString()));							
							wfDest.getPuestos().add(puesto);
						}
						sendMsg.getDestinos().add(wfDest);
					}
					NodeList nodesStMsg = document.getElementsByTagName("StatusMessage");
					for(int j=0;j < nodesStMsg.getLength(); j++){
						sendMsg.getMessages().add(nodesStMsg.item(j).getTextContent());
					}					
				}				
			}else{
				sendMsg.setSendError(true);
				String xmlMessages =  motor.p4bStatusAll(); 
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(new InputSource(new StringReader(xmlMessages)));				
			
				NodeList nodesMsg = document.getElementsByTagName("StatusMessage");
				for(int i=0;i < nodesMsg.getLength(); i++){
					sendMsg.getMessages().add(nodesMsg.item(i).getTextContent());
				}
				
				XPathFactory xPathfactory = XPathFactory.newInstance();
				XPath xpath = xPathfactory.newXPath();
				Integer errorProcess = motor.p4bStatus();
				if (errorProcess == 17360){//campos obligatorios
					ClassFactory.setErrorCode(errorProcess);
					String obligatoriosXml = motor.resultadoXML();
					document = builder.parse(new InputSource(new StringReader(obligatoriosXml)));
					String formXml = motor.p4bObtenerDocumento(0,2,-1);
					Document documentForm = builder.parse(new InputSource(new StringReader(formXml)));
					
					Node nodesFather = document.getElementsByTagName("obligatorios").item(0);
					NodeList nodesCampos = nodesFather.getChildNodes();
					List<String> camposDesc = new ArrayList<String>();
					for(int k=0;k < nodesCampos.getLength(); k++){	
						String nombre = xpath.compile("@nombre").evaluate(nodesCampos.item(k), XPathConstants.STRING).toString();
						if (nodesCampos.item(k).getChildNodes().getLength()>0){//Matriz
							NodeList nodesChildCampos = nodesCampos.item(k).getChildNodes();
							List<String> camposChildDesc = new ArrayList<String>();
							for(int j=0;j < nodesChildCampos.getLength(); j++){	
								String nombreChild = xpath.compile("@nombre").evaluate(nodesChildCampos.item(j), XPathConstants.STRING).toString();
								if (sendMsg.getFocus().equals("")){
									sendMsg.setFocus("M" + xpath.compile("@fila").evaluate(nodesChildCampos.item(j), XPathConstants.STRING).toString() + nombreChild);
								}
								camposChildDesc.add(xpath.compile("/FORMAS/FORMA/GRUPO_CAMPOS/CAMPO/CAMPO[@nombre='" + nombreChild + "']/@descripcion").evaluate(documentForm, XPathConstants.STRING).toString());
							}
							String nombreM = xpath.compile("/FORMAS/FORMA/GRUPO_CAMPOS/CAMPO[@nombre='" + nombre + "']/@descripcion").evaluate(documentForm, XPathConstants.STRING).toString();
							camposDesc.add(nombreM + "( " + StringUtils.collectionToDelimitedString(camposChildDesc, ",") + " )");
						}else{							
							if (sendMsg.getFocus().equals("")){
								sendMsg.setFocus(nombre);
							}
							camposDesc.add(xpath.compile("/FORMAS/FORMA/GRUPO_CAMPOS/CAMPO[@nombre='" + nombre + "']/@descripcion").evaluate(documentForm, XPathConstants.STRING).toString());							
						}
					}
					if (camposDesc.size()>0){
						sendMsg.setMsgObligatorios(StringUtils.collectionToDelimitedString(camposDesc, ","));
					}
				}				
			}			
		}catch(Exception e){
			logger.error("adquirirDocumento:", e);
		}
		return sendMsg;
	}

	@Override
	public void recuperarDocumento() {
		try{
			motor = ClassFactory.getProcess(engineP);
			motor.p4bDeshacerEnvio();		
		}catch(Exception e){
			logger.error("recuperarDocumento:", e);
		}		
	}

	@Override
	public SendMsg anularDocumento(String observacion, Boolean email) {
		SendMsg sendMsg = new SendMsg();		
		try{
			motor = ClassFactory.getProcess(engineP);
			Integer emailInt = 0;
			if (email){
				emailInt = -1;
			}
			Integer result = motor.p4bCancelar(observacion, emailInt);	
			if (result == -1) {
				sendMsg.setSendError(false);
				sendMsg.setMsgObligatorios("");
				String resultadoXML =  motor.resultadoXML(); 
				if (!resultadoXML.equals("")){					
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document document = builder.parse(new InputSource(new StringReader(resultadoXML)));
					
					XPathFactory xPathfactory = XPathFactory.newInstance();
					XPath xpath = xPathfactory.newXPath();
					
					sendMsg.setAcc(xpath.compile("/wfdestinos/@acc").evaluate(document, XPathConstants.STRING).toString());
					NodeList nodesDest = document.getElementsByTagName("wfdest");
					
					for(int i=0;i < nodesDest.getLength(); i++){
						WfDest wfDest = new WfDest();
						wfDest.setNbProceso(xpath.compile("@nb_proceso").evaluate(nodesDest.item(i), XPathConstants.STRING).toString());
						wfDest.setNbWf(xpath.compile("@nb_wf").evaluate(nodesDest.item(i), XPathConstants.STRING).toString());
						wfDest.setWfa(Integer.valueOf(xpath.compile("@wfa").evaluate(nodesDest.item(i), XPathConstants.STRING).toString()));
						if (xpath.compile("@copia").evaluate(nodesDest.item(i), XPathConstants.STRING).toString().equals("N")){
							wfDest.setCopia(false);
						}else{
							wfDest.setCopia(true);
						}	
						if (xpath.compile("@copiaporemail").evaluate(nodesDest.item(i), XPathConstants.STRING).toString().equals("N")){
							wfDest.setCopiaPorEmail(false);
						}else{
							wfDest.setCopiaPorEmail(true);
						}		
						NodeList nodesChildDest = nodesDest.item(i).getChildNodes();
						for(int k=0;k < nodesChildDest.getLength(); k++){
							Puesto puesto = new Puesto();
							puesto.setNbUsrReemp(xpath.compile("@nb_usr_reemp").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setNbUsrAct(xpath.compile("@nb_usr_act").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setNbPuesto(xpath.compile("@nb_puesto").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setMail(xpath.compile("@mail").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setNuDoc(Integer.valueOf(xpath.compile("@nu_doc").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString()));
							puesto.setNuInstn(Integer.valueOf(xpath.compile("@nu_instn").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString()));							
							wfDest.getPuestos().add(puesto);
						}
						sendMsg.getDestinos().add(wfDest);
					}
					NodeList nodesStMsg = document.getElementsByTagName("StatusMessage");
					for(int j=0;j < nodesStMsg.getLength(); j++){
						sendMsg.getMessages().add(nodesStMsg.item(j).getTextContent());
					}					
				}				
			}else{
				sendMsg.setSendError(true);
				String xmlMessages =  motor.p4bStatusAll(); 
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(new InputSource(new StringReader(xmlMessages)));				
			
				NodeList nodesMsg = document.getElementsByTagName("StatusMessage");
				for(int i=0;i < nodesMsg.getLength(); i++){
					sendMsg.getMessages().add(nodesMsg.item(i).getTextContent());
				}
				
				XPathFactory xPathfactory = XPathFactory.newInstance();
				XPath xpath = xPathfactory.newXPath();
				Integer errorProcess = motor.p4bStatus();
				if (errorProcess == 17360){//campos obligatorios
					ClassFactory.setErrorCode(errorProcess);
					String obligatoriosXml = motor.resultadoXML();
					document = builder.parse(new InputSource(new StringReader(obligatoriosXml)));
					String formXml = motor.p4bObtenerDocumento(0,2,-1);
					Document documentForm = builder.parse(new InputSource(new StringReader(formXml)));
					
					Node nodesFather = document.getElementsByTagName("obligatorios").item(0);
					NodeList nodesCampos = nodesFather.getChildNodes();
					List<String> camposDesc = new ArrayList<String>();
					for(int k=0;k < nodesCampos.getLength(); k++){	
						String nombre = xpath.compile("@nombre").evaluate(nodesCampos.item(k), XPathConstants.STRING).toString();
						if (nodesCampos.item(k).getChildNodes().getLength()>0){//Matriz
							NodeList nodesChildCampos = nodesCampos.item(k).getChildNodes();
							List<String> camposChildDesc = new ArrayList<String>();
							for(int j=0;j < nodesChildCampos.getLength(); j++){	
								String nombreChild = xpath.compile("@nombre").evaluate(nodesChildCampos.item(j), XPathConstants.STRING).toString();
								if (sendMsg.getFocus().equals("")){
									sendMsg.setFocus("M" + xpath.compile("@fila").evaluate(nodesChildCampos.item(j), XPathConstants.STRING).toString() + nombreChild);
								}
								camposChildDesc.add(xpath.compile("/FORMAS/FORMA/GRUPO_CAMPOS/CAMPO/CAMPO[@nombre='" + nombreChild + "']/@descripcion").evaluate(documentForm, XPathConstants.STRING).toString());
							}
							String nombreM = xpath.compile("/FORMAS/FORMA/GRUPO_CAMPOS/CAMPO[@nombre='" + nombre + "']/@descripcion").evaluate(documentForm, XPathConstants.STRING).toString();
							camposDesc.add(nombreM + "( " + StringUtils.collectionToDelimitedString(camposChildDesc, ",") + " )");
						}else{							
							if (sendMsg.getFocus().equals("")){
								sendMsg.setFocus(nombre);
							}
							camposDesc.add(xpath.compile("/FORMAS/FORMA/GRUPO_CAMPOS/CAMPO[@nombre='" + nombre + "']/@descripcion").evaluate(documentForm, XPathConstants.STRING).toString());							
						}
					}
					if (camposDesc.size()>0){
						sendMsg.setMsgObligatorios(StringUtils.collectionToDelimitedString(camposDesc, ","));
					}
				}				
			}			
		}catch(Exception e){
			logger.error("anularDocumento:", e);
		}
		return sendMsg;
	}

	@Override
	public SendMsg objetarDocumento(String observacion, Boolean urgente, Boolean email, String conCopiaEmailA, Integer frmnCopia) {
		SendMsg sendMsg = new SendMsg();
		try{
			motor = ClassFactory.getProcess(engineP);
			Integer emailInt = 0;
			if (email){
				emailInt = -1;
			}
			Integer urgenteInt = 0;
			if (urgente){
				urgenteInt = 1;
			}			
			Integer result = motor.p4bRechazar(observacion, urgenteInt, emailInt, conCopiaEmailA, frmnCopia);
			if (result == -1) {
				sendMsg.setSendError(false);
				sendMsg.setMsgObligatorios("");
				String resultadoXML =  motor.resultadoXML(); 
				if (!resultadoXML.equals("")){					
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document document = builder.parse(new InputSource(new StringReader(resultadoXML)));
					
					XPathFactory xPathfactory = XPathFactory.newInstance();
					XPath xpath = xPathfactory.newXPath();
					
					sendMsg.setAcc(xpath.compile("/wfdestinos/@acc").evaluate(document, XPathConstants.STRING).toString());
					NodeList nodesDest = document.getElementsByTagName("wfdest");
					
					for(int i=0;i < nodesDest.getLength(); i++){
						WfDest wfDest = new WfDest();
						wfDest.setNbProceso(xpath.compile("@nb_proceso").evaluate(nodesDest.item(i), XPathConstants.STRING).toString());
						wfDest.setNbWf(xpath.compile("@nb_wf").evaluate(nodesDest.item(i), XPathConstants.STRING).toString());
						wfDest.setWfa(Integer.valueOf(xpath.compile("@wfa").evaluate(nodesDest.item(i), XPathConstants.STRING).toString()));
						if (xpath.compile("@copia").evaluate(nodesDest.item(i), XPathConstants.STRING).toString().equals("N")){
							wfDest.setCopia(false);
						}else{
							wfDest.setCopia(true);
						}	
						if (xpath.compile("@copiaporemail").evaluate(nodesDest.item(i), XPathConstants.STRING).toString().equals("N")){
							wfDest.setCopiaPorEmail(false);
						}else{
							wfDest.setCopiaPorEmail(true);
						}		
						NodeList nodesChildDest = nodesDest.item(i).getChildNodes();
						for(int k=0;k < nodesChildDest.getLength(); k++){
							Puesto puesto = new Puesto();
							puesto.setNbUsrReemp(xpath.compile("@nb_usr_reemp").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setNbUsrAct(xpath.compile("@nb_usr_act").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setNbPuesto(xpath.compile("@nb_puesto").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setMail(xpath.compile("@mail").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setNuDoc(Integer.valueOf(xpath.compile("@nu_doc").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString()));
							puesto.setNuInstn(Integer.valueOf(xpath.compile("@nu_instn").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString()));							
							wfDest.getPuestos().add(puesto);
						}
						sendMsg.getDestinos().add(wfDest);
					}
					NodeList nodesStMsg = document.getElementsByTagName("StatusMessage");
					for(int j=0;j < nodesStMsg.getLength(); j++){
						sendMsg.getMessages().add(nodesStMsg.item(j).getTextContent());
					}					
				}				
			}else{
				sendMsg.setSendError(true);
				String xmlMessages =  motor.p4bStatusAll(); 
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(new InputSource(new StringReader(xmlMessages)));				
			
				NodeList nodesMsg = document.getElementsByTagName("StatusMessage");
				for(int i=0;i < nodesMsg.getLength(); i++){
					sendMsg.getMessages().add(nodesMsg.item(i).getTextContent());
				}
				
				XPathFactory xPathfactory = XPathFactory.newInstance();
				XPath xpath = xPathfactory.newXPath();
				Integer errorProcess = motor.p4bStatus();
				if (errorProcess == 17360){//campos obligatorios
					ClassFactory.setErrorCode(errorProcess);
					String obligatoriosXml = motor.resultadoXML();
					document = builder.parse(new InputSource(new StringReader(obligatoriosXml)));
					String formXml = motor.p4bObtenerDocumento(0,2,-1);
					Document documentForm = builder.parse(new InputSource(new StringReader(formXml)));
					
					Node nodesFather = document.getElementsByTagName("obligatorios").item(0);
					NodeList nodesCampos = nodesFather.getChildNodes();
					List<String> camposDesc = new ArrayList<String>();
					for(int k=0;k < nodesCampos.getLength(); k++){	
						String nombre = xpath.compile("@nombre").evaluate(nodesCampos.item(k), XPathConstants.STRING).toString();
						if (nodesCampos.item(k).getChildNodes().getLength()>0){//Matriz
							NodeList nodesChildCampos = nodesCampos.item(k).getChildNodes();
							List<String> camposChildDesc = new ArrayList<String>();
							for(int j=0;j < nodesChildCampos.getLength(); j++){	
								String nombreChild = xpath.compile("@nombre").evaluate(nodesChildCampos.item(j), XPathConstants.STRING).toString();
								if (sendMsg.getFocus().equals("")){
									sendMsg.setFocus("M" + xpath.compile("@fila").evaluate(nodesChildCampos.item(j), XPathConstants.STRING).toString() + nombreChild);
								}
								camposChildDesc.add(xpath.compile("/FORMAS/FORMA/GRUPO_CAMPOS/CAMPO/CAMPO[@nombre='" + nombreChild + "']/@descripcion").evaluate(documentForm, XPathConstants.STRING).toString());
							}
							String nombreM = xpath.compile("/FORMAS/FORMA/GRUPO_CAMPOS/CAMPO[@nombre='" + nombre + "']/@descripcion").evaluate(documentForm, XPathConstants.STRING).toString();
							camposDesc.add(nombreM + "( " + StringUtils.collectionToDelimitedString(camposChildDesc, ",") + " )");
						}else{							
							if (sendMsg.getFocus().equals("")){
								sendMsg.setFocus(nombre);
							}
							camposDesc.add(xpath.compile("/FORMAS/FORMA/GRUPO_CAMPOS/CAMPO[@nombre='" + nombre + "']/@descripcion").evaluate(documentForm, XPathConstants.STRING).toString());							
						}
					}
					if (camposDesc.size()>0){
						sendMsg.setMsgObligatorios(StringUtils.collectionToDelimitedString(camposDesc, ","));
					}
				}				
			}
		}catch(Exception e){
			logger.error("objetarDocumento:", e);
		}
		return sendMsg;
	}

	@Override
	public SendMsg avanzarDocumento(String firma, String pregunta,
			String respuesta, String observacion, Boolean urgente,
			Boolean email, Integer gradoSatisfaccion,
			String seleccionResultadoXml, String conCopiaEmailA,
			Integer frmnCopia,List<WfDest> destinos) {
		SendMsg sendMsg = new SendMsg(); 
		try{
			motor = ClassFactory.getProcess(engineP);
			Integer emailInt = 0;
			if (email){
				emailInt = -1;
			}
			Integer urgenteInt = 0;
			if (urgente){
				urgenteInt = 1;
			}	
			for(WfDest d:destinos){
				String eDest = "";
				if (d.getE()){
					eDest = "S";
				}else{
					eDest = "N";
				}
				motor.p4bAgregarProximoDestino(d.getWfa(), eDest, d.getValueSelected());
			}
			Integer result = motor.p4bAvanzar(firma, pregunta, respuesta, observacion, urgenteInt, emailInt, gradoSatisfaccion, seleccionResultadoXml, conCopiaEmailA, frmnCopia);
			if (result == -1) {
				sendMsg.setSendError(false);
				String resultadoXML =  motor.resultadoXML(); 
				//logger.info("avanzarDocumento: xml "+ resultadoXML);
				
				if (!resultadoXML.equals("")){					
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document document = builder.parse(new InputSource(new StringReader(resultadoXML)));
					
					XPathFactory xPathfactory = XPathFactory.newInstance();
					XPath xpath = xPathfactory.newXPath();
					
					sendMsg.setAcc(xpath.compile("/wfdestinos/@acc").evaluate(document, XPathConstants.STRING).toString());
					NodeList nodesDest = document.getElementsByTagName("wfdest");
					
					for(int i=0;i < nodesDest.getLength(); i++){
						WfDest wfDest = new WfDest();
						wfDest.setNbProceso(xpath.compile("@nb_proceso").evaluate(nodesDest.item(i), XPathConstants.STRING).toString());
						wfDest.setNbWf(xpath.compile("@nb_wf").evaluate(nodesDest.item(i), XPathConstants.STRING).toString());
						wfDest.setWfa(Integer.valueOf(xpath.compile("@wfa").evaluate(nodesDest.item(i), XPathConstants.STRING).toString()));
						if (xpath.compile("@copia").evaluate(nodesDest.item(i), XPathConstants.STRING).toString().equals("N")){
							wfDest.setCopia(false);
						}else{
							wfDest.setCopia(true);
						}	
						if (xpath.compile("@copiaporemail").evaluate(nodesDest.item(i), XPathConstants.STRING).toString().equals("N")){
							wfDest.setCopiaPorEmail(false);
						}else{
							wfDest.setCopiaPorEmail(true);
						}		
						NodeList nodesChildDest = nodesDest.item(i).getChildNodes();
						for(int k=0;k < nodesChildDest.getLength(); k++){
							Puesto puesto = new Puesto();
							puesto.setNbUsrReemp(xpath.compile("@nb_usr_reemp").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setNbUsrAct(xpath.compile("@nb_usr_act").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setNbPuesto(xpath.compile("@nb_puesto").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setMail(xpath.compile("@mail").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
							puesto.setNuDoc(Integer.valueOf(xpath.compile("@nu_doc").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString()));
							puesto.setNuInstn(Integer.valueOf(xpath.compile("@nu_instn").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString()));							
							wfDest.getPuestos().add(puesto);
						}
						sendMsg.getDestinos().add(wfDest);
					}
					
					String xmlMessages =  motor.p4bStatusAll();
					Document document1 = builder.parse(new InputSource(new StringReader(xmlMessages)));	
					NodeList nodesStMsg = document1.getElementsByTagName("StatusMessage");
					for(int j=0;j < nodesStMsg.getLength(); j++){
						sendMsg.getMessages().add(nodesStMsg.item(j).getTextContent());
					}					
				}				
			}else{
				sendMsg.setSendError(true);
				String xmlMessages =  motor.p4bStatusAll(); 
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(new InputSource(new StringReader(xmlMessages)));				
			
				NodeList nodesMsg = document.getElementsByTagName("StatusMessage");
				for(int i=0;i < nodesMsg.getLength(); i++){
					sendMsg.getMessages().add(nodesMsg.item(i).getTextContent());
				}				
			}
		}catch(Exception e){
			logger.error("avanzarDocumento:", e);
		}
		return sendMsg;
	}

	@Override
	public void anexarDocumento(Integer tipoSource, String source, String ext, String descripcion, String asunto, String autor) {
		try{
			motor = ClassFactory.getProcess(engineP);
			motor.p4bAnexarDocumento(tipoSource, source, ext, descripcion, asunto, autor);	
			//Delete File
			deleteFile(source);
		}catch(Exception e){
			logger.error("anexarDocumento:", e);
		}	
		
	}
	
	private static void deleteFile(String filePath) throws IOException{
		Path path = Paths.get(filePath);
		Files.delete(path);
	}	

	@Override
	public void marcarAnexosDocumento(List<Anexo> anexos, Boolean borrado) {
		try{
			motor = ClassFactory.getProcess(engineP);
			Integer borradoInt = 0;
			if (borrado){
				borradoInt = -1;
			}		
			for(Anexo anx:anexos){
				if (anx.getSelected()){
					motor.p4bMarcarAnexoDocumento(0, anx.getNumero().toString(), borradoInt);		
				}
			}			
		}catch(Exception e){
			logger.error("marcarAnexoDocumento:", e);
		}
	}

	@Override
	public EventAgent ejecutarEventoCampo(String campo, Integer fila) {
		EventAgent eventAgent = new EventAgent();
		try{
			motor = ClassFactory.getProcess(engineP);	
			Integer result = motor.p4bEjecutarEventoCampo(campo, fila);
			if (result == -1) {//load xml list
				String xmlList = motor.resultadoXML();
				//logger.info("ejecutarEventoCampo "+xmlList);
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document;				
				if (xmlList != null && !xmlList.equals("")){
					eventAgent.setXmlData(xmlList);//guardar el xml para luego resolver la lista de valores
					document = builder.parse(new InputSource(new StringReader(xmlList)));
					XPathFactory xPathfactory = XPathFactory.newInstance();
					XPath xpath = xPathfactory.newXPath();				
					
					NodeList nodesCabecera = document.getElementsByTagName("NOMBRE");
					for(int i=0;i < nodesCabecera.getLength(); i++){
						eventAgent.getCabeceras().add(nodesCabecera.item(i).getTextContent());
					}
					
					NodeList nodesFila = document.getElementsByTagName("FILA");
					for(int i=0;i < nodesFila.getLength(); i++){
						ListEvent listEvent = new ListEvent();
						listEvent.setNumero(Integer.valueOf(xpath.compile("@numero").evaluate(nodesFila.item(i), XPathConstants.STRING).toString()));
						for(int j=0;j < nodesFila.item(i).getChildNodes().getLength(); j++){
							listEvent.getValores().add(nodesFila.item(i).getChildNodes().item(j).getTextContent());
						}
						eventAgent.getFilas().add(listEvent);
					}
				}
				eventAgent.setMsgError(false);	
				
				String xmlMessages =  motor.p4bStatusAll();
				//logger.info("result == -1 "+xmlMessages);
				document = builder.parse(new InputSource(new StringReader(xmlMessages)));					
				NodeList nodesMsg = document.getElementsByTagName("StatusMessage");
				for(int i=0;i < nodesMsg.getLength(); i++){
					eventAgent.getMessages().add(nodesMsg.item(i).getTextContent());
				}					
			}else{//error
				eventAgent.setMsgError(true);
				String xmlMessages =  motor.p4bStatusAll(); 
				//logger.info("result <> -1 "+xmlMessages);
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(new InputSource(new StringReader(xmlMessages)));
				
				NodeList nodesMsg = document.getElementsByTagName("StatusMessage");
				for(int i=0;i < nodesMsg.getLength(); i++){
					eventAgent.getMessages().add(nodesMsg.item(i).getTextContent());
				}				
			}			
		}catch(Exception e){
			logger.error("ejecutarEventoCampo:", e);
		}	
		return eventAgent;
	}
	
	@Override
	public EventAgent ejecutarAgente(String codigo, String contexto) {
		EventAgent eventAgent = new EventAgent();
		try{
			motor = ClassFactory.getProcess(engineP);	
			Integer result = motor.p4bEjecutarAgente(codigo, contexto);
			if (result == -1) {//load xml list
				String xmlList = motor.resultadoXML();
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document;				
				if (!xmlList.equals("")){
					eventAgent.setXmlData(xmlList);//guardar el xml para luego resolver la lista de valores
					document = builder.parse(new InputSource(new StringReader(xmlList)));
					XPathFactory xPathfactory = XPathFactory.newInstance();
					XPath xpath = xPathfactory.newXPath();				
					
					NodeList nodesCabecera = document.getElementsByTagName("NOMBRE");
					for(int i=0;i < nodesCabecera.getLength(); i++){
						eventAgent.getCabeceras().add(nodesCabecera.item(i).getTextContent());
					}
					
					NodeList nodesFila = document.getElementsByTagName("FILA");
					for(int i=0;i < nodesFila.getLength(); i++){
						ListEvent listEvent = new ListEvent();
						listEvent.setNumero(Integer.valueOf(xpath.compile("@numero").evaluate(nodesFila.item(i), XPathConstants.STRING).toString()));
						for(int j=0;j < nodesFila.item(i).getChildNodes().getLength(); j++){
							listEvent.getValores().add(nodesFila.item(i).getChildNodes().item(j).getTextContent());
						}
						eventAgent.getFilas().add(listEvent);
					}
				}
				eventAgent.setMsgError(false);	
				
				String xmlMessages =  motor.p4bStatusAll(); 
				document = builder.parse(new InputSource(new StringReader(xmlMessages)));					
				NodeList nodesMsg = document.getElementsByTagName("StatusMessage");
				for(int i=0;i < nodesMsg.getLength(); i++){
					eventAgent.getMessages().add(nodesMsg.item(i).getTextContent());
				}					
			}else{//error
				eventAgent.setMsgError(true);
				String xmlMessages =  motor.p4bStatusAll(); 
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(new InputSource(new StringReader(xmlMessages)));
				
				NodeList nodesMsg = document.getElementsByTagName("StatusMessage");
				for(int i=0;i < nodesMsg.getLength(); i++){
					eventAgent.getMessages().add(nodesMsg.item(i).getTextContent());
				}				
			}			
		}catch(Exception e){
			logger.error("ejecutarAgente:", e);
		}	
		return eventAgent;
	}

	@Override
	public EventAgent resolverResultadoListaSQL(String campo, Integer fila, String resultadoXml, Integer seleccion, String contexto, String listaSelect) {
		EventAgent eventAgent = new EventAgent();
		try{
			motor = ClassFactory.getProcess(engineP);
			String[] selecciones = null;
			if(listaSelect != ""){
				selecciones = listaSelect.split(",");
			}
			
			if(seleccion == 0){
				for(int x=0;x<listaSelect.split(",").length;x++){
					
					Integer result = motor.p4bResolverResultadoListaSQL(campo, fila+x, resultadoXml, Integer.parseInt(selecciones[x]), contexto);
					if (result == -1) {//load message agents
						eventAgent.setMsgError(false);				
					}else{//error //load message agents
						eventAgent.setMsgError(true);
					}
				}
				
			}else{
				Integer result = motor.p4bResolverResultadoListaSQL(campo, fila, resultadoXml, seleccion, contexto);
				if (result == -1) {//load message agents
					eventAgent.setMsgError(false);				
				}else{//error //load message agents
					eventAgent.setMsgError(true);
				}
			}
			

			String xmlMessages =  motor.p4bStatusAll(); 
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(xmlMessages)));
			
			NodeList nodesMsg = document.getElementsByTagName("StatusMessage");
			for(int i=0;i < nodesMsg.getLength(); i++){
				eventAgent.getMessages().add(nodesMsg.item(i).getTextContent());
			}			
		}catch(Exception e){
			logger.error("resolverResultadoListaSQL:", e);
		}
		return eventAgent;
	}

	@Override
	public Destino calcularProximosDestinos() {
		Destino destino = new Destino();		
		try{
			motor = ClassFactory.getProcess(engineP);
			String destinosXml = motor.p4bCalcularProximosDestinos();
			if (!destinosXml.equals("")){
				destino.setMsgObligatorios("");
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(new InputSource(new StringReader(destinosXml)));	

				XPathFactory xPathfactory = XPathFactory.newInstance();
				XPath xpath = xPathfactory.newXPath();
				
				if (xpath.compile("/wfdestinos/@evalua").evaluate(document, XPathConstants.STRING).toString().equals("N")){
					destino.setEvalua(false);
				}else{
					destino.setEvalua(true);
				}
				if (xpath.compile("/wfdestinos/@firma").evaluate(document, XPathConstants.STRING).toString().equals("N")){
					destino.setFirma(false);
				}else{
					destino.setFirma(true);
				}		
				NodeList nodesDest = document.getElementsByTagName("wfdest");
				Integer allSelected = 0;
				for(int i=0;i < nodesDest.getLength(); i++){
					WfDest wfDest = new WfDest();
					wfDest.setNbProceso(xpath.compile("@nb_proceso").evaluate(nodesDest.item(i), XPathConstants.STRING).toString());
					wfDest.setNbWf(xpath.compile("@nb_wf").evaluate(nodesDest.item(i), XPathConstants.STRING).toString());
					wfDest.setWfa(Integer.valueOf(xpath.compile("@wfa").evaluate(nodesDest.item(i), XPathConstants.STRING).toString()));
					if (xpath.compile("@e").evaluate(nodesDest.item(i), XPathConstants.STRING).toString().equals("N")){
						wfDest.setE(false);
					}else{
						wfDest.setE(true);
					}	
					if (xpath.compile("@visible").evaluate(nodesDest.item(i), XPathConstants.STRING).toString().equals("S")){
						wfDest.setVisible(true);
					}else{
						wfDest.setVisible(false);
					}		
					NodeList nodesChildDest = nodesDest.item(i).getChildNodes();
					Boolean selected = false;
					for(int k=0;k < nodesChildDest.getLength(); k++){
						Puesto puesto = new Puesto();
						puesto.setNbUsrReemp(xpath.compile("@nb_usr_reemp").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
						puesto.setNbUsrAct(xpath.compile("@nb_usr_act").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
						puesto.setNbPuesto(xpath.compile("@nb_puesto").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString());
						if (xpath.compile("@seleccionado").evaluate(nodesChildDest.item(k), XPathConstants.STRING).toString().equals("N")){
							puesto.setSeleccionado(false);
						}else{
							puesto.setSeleccionado(true);
							wfDest.setValueSelected(nodesChildDest.item(k).getTextContent());
							selected = true;							
						}
						
						puesto.setValue(nodesChildDest.item(k).getTextContent());
						wfDest.getPuestos().add(puesto);
					}
					if (selected) {
						allSelected++;
						wfDest.setAnySelected(true);
					}else{
						wfDest.setAnySelected(false);
					}
					destino.getDestinos().add(wfDest);
				}	
				if ( nodesDest.getLength()==allSelected){
					destino.setAllSelected(true);
				}else{
					destino.setAllSelected(false);
				}
			}else{//buscar error
				//mensajes de agentes
				destino.setFocus("");
				String xmlMessages =  motor.p4bStatusAll(); 
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(new InputSource(new StringReader(xmlMessages)));
				
				XPathFactory xPathfactory = XPathFactory.newInstance();
				XPath xpath = xPathfactory.newXPath();
				
				NodeList nodesMsg = document.getElementsByTagName("StatusMessage");
				for(int i=0;i < nodesMsg.getLength(); i++){
					destino.getMessages().add(nodesMsg.item(i).getTextContent());
				}
				Integer errorProcess = motor.p4bStatus();
				if (errorProcess == 17360){//campos obligatorios
					ClassFactory.setErrorCode(errorProcess);
					String obligatoriosXml = motor.resultadoXML();
					document = builder.parse(new InputSource(new StringReader(obligatoriosXml)));
					String formXml = motor.p4bObtenerDocumento(0,2,-1);
					//logger.info("formxml "+formXml);
					Document documentForm = builder.parse(new InputSource(new StringReader(formXml)));
					
					Node nodesFather = document.getElementsByTagName("obligatorios").item(0);
					NodeList nodesCampos = nodesFather.getChildNodes();
					List<String> camposDesc = new ArrayList<String>();
					for(int k=0;k < nodesCampos.getLength(); k++){	
						String nombre = xpath.compile("@nombre").evaluate(nodesCampos.item(k), XPathConstants.STRING).toString();
						if (nodesCampos.item(k).getChildNodes().getLength()>0){//Matriz
							NodeList nodesChildCampos = nodesCampos.item(k).getChildNodes();
							List<String> camposChildDesc = new ArrayList<String>();
							for(int j=0;j < nodesChildCampos.getLength(); j++){	
								String nombreChild = xpath.compile("@nombre").evaluate(nodesChildCampos.item(j), XPathConstants.STRING).toString();
								if (destino.getFocus().equals("")){
									destino.setFocus("M" + xpath.compile("@fila").evaluate(nodesChildCampos.item(j), XPathConstants.STRING).toString() + nombreChild);
								}
								camposChildDesc.add(xpath.compile("/FORMAS/FORMA/GRUPO_CAMPOS/CAMPO/CAMPO[@nombre='" + nombreChild + "']/@descripcion").evaluate(documentForm, XPathConstants.STRING).toString());
							}
							String nombreM = xpath.compile("/FORMAS/FORMA/GRUPO_CAMPOS/CAMPO[@nombre='" + nombre + "']/@descripcion").evaluate(documentForm, XPathConstants.STRING).toString();
							camposDesc.add(nombreM + "( " + StringUtils.collectionToDelimitedString(camposChildDesc, ",") + " )");
						}else{							
							if (destino.getFocus().equals("")){
								destino.setFocus(nombre);
							}
							camposDesc.add(xpath.compile("/FORMAS/FORMA/GRUPO_CAMPOS/CAMPO[@nombre='" + nombre + "']/@descripcion").evaluate(documentForm, XPathConstants.STRING).toString());							
						}
					}
					if (camposDesc.size()>0){
						destino.setMsgObligatorios(StringUtils.collectionToDelimitedString(camposDesc, ","));
					}
				}
			}			
		}catch(Exception e){
			logger.error("calcularProximosDestinos:", e);
		}
		return destino;
	}

	@Override
	public void agregarProximoDestino(Integer wfa, String e, String puesto) {
		try{
			motor = ClassFactory.getProcess(engineP);
			motor.p4bAgregarProximoDestino(wfa, e, puesto);	
		}catch(Exception ex){
			logger.error("agregarProximoDestino:", ex);
		}		
	}

	@Override
	public List<Anexo> obtenerAnexos() {
		List<Anexo> listAnexos = new ArrayList<Anexo>();
		try{
			motor = ClassFactory.getProcess(engineP);
			String xmlAnexos = motor.p4bObtenerDocumento(0, 3, -1);	
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(xmlAnexos)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			
			NodeList nodesAnexos = document.getElementsByTagName("ANEXO");
			for(int i=0;i < nodesAnexos.getLength(); i++){
				Anexo anexo = new Anexo();
				anexo.setAsunto(xpath.compile("@asunto").evaluate(nodesAnexos.item(i), XPathConstants.STRING).toString());
				anexo.setAutor(xpath.compile("@autor").evaluate(nodesAnexos.item(i), XPathConstants.STRING).toString());
				anexo.setBorrado(xpath.compile("@borrado").evaluate(nodesAnexos.item(i), XPathConstants.STRING).toString());
				anexo.setDescripcion(xpath.compile("@descripcion").evaluate(nodesAnexos.item(i), XPathConstants.STRING).toString());
				anexo.setHref(xpath.compile("@href").evaluate(nodesAnexos.item(i), XPathConstants.STRING).toString());
				anexo.setNuevo(xpath.compile("@nuevo").evaluate(nodesAnexos.item(i), XPathConstants.STRING).toString());
				anexo.setNumero(Integer.valueOf(xpath.compile("@numero").evaluate(nodesAnexos.item(i), XPathConstants.STRING).toString()));
				anexo.setSelected(false);
				listAnexos.add(anexo);				
			}
		}catch(Exception ex){
			logger.error("obtenerAnexos:", ex);
		}
		return listAnexos;
	}

	@Override
	public List<Agent> obtenerAgentesGenerales() {
		List<Agent> result = new ArrayList<Agent>();
		try{
			motor = ClassFactory.getProcess(engineP);
			String agentXml = motor.p4bObtenerAgentesGenerales();			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			String cleanXml = agentXml.replaceAll("(&(?!amp;))", "&amp;");
			Document document = builder.parse(new InputSource(new StringReader(cleanXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();				
			
			NodeList nodesAgentContext = document.getElementsByTagName("AGENTE");
			for(int i=0;i < nodesAgentContext.getLength(); i++){
				Agent agent = new Agent();	
				agent.setCdAg(xpath.compile("@cd_ag").evaluate(nodesAgentContext.item(i), XPathConstants.STRING).toString());
				agent.setContexto(xpath.compile("@contexto").evaluate(nodesAgentContext.item(i), XPathConstants.STRING).toString());
				agent.setDescripcion(xpath.compile("@descripcion").evaluate(nodesAgentContext.item(i), XPathConstants.STRING).toString());
				if (xpath.compile("@href").evaluate(nodesAgentContext.item(i), XPathConstants.STRING)!=null){
					agent.setHref(xpath.compile("@href").evaluate(nodesAgentContext.item(i), XPathConstants.STRING).toString());	
				}else{
					agent.setHref(null);
				}				
				agent.setTipo(xpath.compile("@tipo").evaluate(nodesAgentContext.item(i), XPathConstants.STRING).toString());
				result.add(agent);
			}			
		}catch(Exception e){
			logger.error("obtenerAgentesGenerales:", e);
		}
		return result;
	}
	@Override
	public RespDataService dataServices(String ambiente, String idQuery,Object[][] param){
		String sql = "";
		URL fileLocation = getClass().getClassLoader().getResource("DataServicesQuerysProcess.xml");
		File archivo = new File(fileLocation.getFile());
		 Object[][] arrayResult = null;
		 RespDataService resp = new RespDataService();
		 //logger.info("localizacion ambiente "+fileLocation.getFile());
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
	        //logger.info("Nodo ambiente existe archivo "+archivo.exists());
	        Document document = documentBuilder.parse(archivo);
	        document.getDocumentElement().normalize();
			 NodeList listServ = document.getElementsByTagName(ambiente.toUpperCase());
			 //logger.info("Nodo ambiente "+listServ.getLength());
			 Node nodo = listServ.item(0);
			 //logger.info("Nodo ambiente "+nodo.toString());
			 Element elemtServi = (Element) nodo;
			 
			 NodeList listQuery = elemtServi.getElementsByTagName("Query");

			 for (int temp = 0; temp < listQuery.getLength(); temp++) {
				 Node nodo1 = listQuery.item(temp);
				 if(nodo1.getNodeType() == Node.ELEMENT_NODE){
					 Element elemQuery = (Element) nodo1;
					 if(elemQuery.getAttribute("id").equals(idQuery)){
						 sql = elemQuery.getElementsByTagName("sql").item(0).getTextContent();
						 break;
					 }
				 }
			 }
		}catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.error("Error archivo ParserConfigurationException dataServices "+e.getMessage()+" causa "+e.getLocalizedMessage());
			resp.setCodError("700");
			resp.setMsgError("Error archivo "+e.getMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			logger.error("Error archivo SAXException dataServices "+e.getMessage()+" causa "+e.getLocalizedMessage());
			resp.setCodError("700");
			resp.setMsgError("Error archivo "+e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error archivo IOException dataServices "+e.getMessage()+" causa "+e.getLocalizedMessage());
			resp.setCodError("700");
			resp.setMsgError("Error archivo "+e.getMessage());
		}
		
		if(sql.equals("")){
			resp.setCodError("600");
			resp.setMsgError("id de query no existe");
		}else{
			if(param.length != 0){
				 String regex = "\\{([^\\{}]*[^\\{}]*)\\}";
				 final Pattern pattern = Pattern.compile(regex);
				 final Matcher matcher = pattern.matcher(sql);
				 
				 while (matcher.find()) {
					 	//matcher.group(1)
					 	 for (int c = 0; c < param.length; c++) {
					 		 String campo = (String) param[c][0];
							 String valor = (String) param[c][1];
							 //logger.info("Full match: " + matcher.group(1)+" = "+campo);
					 		 if(matcher.group(1).equals(campo)){
					 			 sql = sql.replace(matcher.group(0), "'"+valor+"'");
					 			 break;
					 		 }
					 	 }
					}
			 }
			 
			 Connection con =  null;
			 
			 ConexionBD bd = new ConexionBD(ambiente.toUpperCase());
			 
			 
			try {
				
				con =  bd.getDataSource().getConnection();
				Statement consulta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        ResultSet resultado = consulta.executeQuery(sql);
		        arrayResult = bd.ResultSetToArray(resultado);
		        
		        resp.setArrayResult(arrayResult);
		        resp.setCodError("200");
			} catch (SQLException e) {
				logger.error("Error dataServices ",e);
				resp.setCodError("800");
				resp.setMsgError("Error BD "+e.getMessage());
				//return false;
			}finally{
				try {
		        	 if (null != con) {
		        		 con.close();
		        		 //logger.info("cierra conexion");
		        		 }
				} catch (SQLException e) {
					logger.error("Error dataServices close ",e);
					resp.setCodError("801");
					resp.setMsgError("Cerrar cadena de conexión "+e.getMessage());
				}
			}
		}
		return resp;
	}
	
	@Override
	public Integer crearDocumentExterno(String ambiente, Integer wfa,Object[][] param, String observacion, Boolean envio){
		motor = ClassFactory.getProcess(engineP);
		Doc2 doc = new Doc2();
		try{
			//Se instancia el objeto del motor con la sesion creada
			
			//Se crea el documento
			Integer result =   motor.p4bCrearDocumento(wfa);
			
			//Se obtiene el documento para sacar el numero
			String contextDocxml = motor.p4bObtenerDocumento(0, 1, -1);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(contextDocxml)));
			JAXBContext context = JAXBContextFactory.createContext(new Class[] {Doc2.class}, null);
			Unmarshaller un = context.createUnmarshaller();
			doc = (Doc2) un.unmarshal(document);
			
			if (result == -1){
				if(param.length >0) {
					for (int c = 0; c < param.length; c++) {
				 		 String campo = (String) param[c][0];
						 String valor = (String) param[c][1];
						 //Guardar campos enviados
						 motor.p4bAsignarValorCampoDocumento(campo, valor, 0, 0); 
				 		}
				}
				//Se guarda el documento
				motor.p4bGuardarDocumento(observacion);	
				
				//Enviar documento
				if(envio) {
					Destino destinos = calcularProximosDestinos();
					for(WfDest d:destinos.getDestinos()){
						String eDest = "";
						if (d.getE()){
							eDest = "S";
						}else{
							eDest = "N";
						}
						motor.p4bAgregarProximoDestino(d.getWfa(), eDest, d.getValueSelected());
					}
					
					result = motor.p4bAvanzar("", "", "", observacion, 0, 0, 0, "", "", 0);
					//logger.info("Resultado de avanzar "+result);
					String xmlMessages =  motor.p4bStatusAll();
					//logger.info("Estatus de avanzar "+xmlMessages);					
				}
				
				
				
			}else {
				//logger.info("error creado document");
			}
		}catch (Exception e) {
			logger.error("Error createDocumentExterno",e);
		}finally {
			//se cierra sesion
			try{
				//Se cierra documento
				//motor.p4bCerrarDocumento();		
				//logger.info("Cerrar sesion "+motor.p4bStatus());
			}catch(Exception e){
				logger.error("cerrarSesion:", e);
			}
		}
		return doc.getNuDoc();
	}
	
}
