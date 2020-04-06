package com.process.business.log.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.process.business.console.impl.SimpleConsoleManager;
import com.process.business.environment.EnvironmentManager;
import com.process.business.log.LogManager;
import com.process.domain.log.ListFechaUsaurio;
import com.process.domain.log.ObjL;

@Service("logManager")
public class SimpleLogManager implements LogManager {
	private static final Logger logger = Logger.getLogger(SimpleLogManager.class);
	@Autowired
	private EnvironmentManager environmentManager;
	private ArrayList<String> usuarioObj = null;
	private ArrayList<String> eventoObj = null;
	private ArrayList<String> emailObj = null;
	private String rutaTemp;
	private String ruta;
	private File directorio;
	private BufferedReader br;
	private BufferedReader br2;
	SimpleConsoleManager scm = new SimpleConsoleManager();
	@Override
	public void setEngineId(Integer engineId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<ListFechaUsaurio> initLog(String amb){
		logger.info("ambiente: "+amb);
		
		
		
		ruta = scm.leerVarAmbienteXml(amb, "RepError");
		if(ruta.equals("")){
			ruta = environmentManager.getDatoAmbiente(amb, "RepError");
		}
		logger.info("ruta: "+ruta);
		/*Ir al directorio*/
		directorio = new File(ruta);
		/*Listar carpetas */
		String[] fechas = directorio.list();
		
		List<ListFechaUsaurio>  listResult= new ArrayList<ListFechaUsaurio>();
		
		if(fechas != null && fechas.length != 0){
			for (int x=0; x< fechas.length; x++) {
				ListFechaUsaurio feUsu = new ListFechaUsaurio() ;
				usuarioObj = new ArrayList<String>();
				eventoObj = new ArrayList<String>();
				emailObj = new ArrayList<String>();
				rutaTemp = ruta+"/"+fechas[x];
					//logger.info("fecha dir : "+fechas[x]);
				File archivoUsuario = new File(rutaTemp);
					/*verificar si es un directorio*/
				if(archivoUsuario != null && archivoUsuario.isDirectory() && !fechas[x].equals("temp")){
					feUsu.setFecha(fechas[x]);
					
					File[] archivosLogs = archivoUsuario.listFiles();

					File archivoLog = null;
					for (int i=0; i< archivosLogs.length; i++) {
						archivoLog = archivosLogs[i];
						if(archivoLog.getName().endsWith(".log") && !archivoLog.getName().equals("ProcessW.log") ){
							usuarioObj.add(archivoLog.getName().replace(".log", ""));
						}
						if(archivoLog.getName().endsWith(".clg") && !archivoLog.getName().equals("ProcessW.log") ){
							eventoObj.add(archivoLog.getName().replace(".clg", ""));
						}
						if(archivoLog.getName().endsWith(".mlg") && !archivoLog.getName().equals("ProcessW.log") ){
							emailObj.add(archivoLog.getName().replace(".mlg", ""));
						}
					}
					feUsu.setUsuarios(usuarioObj);
					feUsu.setListEvent(eventoObj);
					feUsu.setLisEmail(emailObj);
					listResult.add(feUsu);
				}
				//logger.info("Termino primera fecha ------------------------------------------------------------------------");
			}
		}
		
		
		
		return listResult;
	}
	@Override
	public ObjL obtenerTraza(String amb, String fecha, String usuario){
		ObjL objl = new ObjL();
		
		ruta = scm.leerVarAmbienteXml(amb, "RepError");
		if(ruta.equals("")){
			ruta = environmentManager.getDatoAmbiente(amb, "RepError");
		}
		
		rutaTemp = ruta+"/"+fecha+"/"+usuario+".log";
		directorio = new File(rutaTemp);
		FileReader fr;
		try {
			fr = new FileReader(directorio);
			br2 = new BufferedReader(fr);
			
			String line;
			String datosTraza = "";
			while ((line = br2.readLine()) != null) {
				datosTraza += line;
		    }
			logger.info("xml log "+datosTraza);
			datosTraza = "<logs>"+datosTraza+"</logs>";
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(datosTraza)));

	        JAXBContext context = JAXBContextFactory.createContext(new Class[] {ObjL.class}, null);
	        
	        Unmarshaller un = context.createUnmarshaller();
	        un = context.createUnmarshaller();
	        
	        
	        objl = (ObjL) un.unmarshal(document);
	        
		} catch (FileNotFoundException e) {
			logger.error("error  obtenerTraza LOG FileNotFoundException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//error 1000
			objl.setCodError("1000");
			objl.setDescError("error  obtenerTraza LOG FileNotFoundException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (IOException e){
			logger.error("error  obtenerTraza LOG  IOException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//Error 1001
			objl.setCodError("1001");
			objl.setDescError("error  obtenerTraza LOG IOException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.error("error  obtenerTraza LOG  ParserConfigurationException "+e.getMessage()+" codigo "+e.getLocalizedMessage());
			//Error 1002
			objl.setCodError("1002");
			objl.setDescError("error  obtenerTraza LOG ParserConfigurationException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			logger.error("error  obtenerTraza LOG  SAXException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//Error 1003
			objl.setCodError("1003");
			objl.setDescError("error  obtenerTraza LOG  SAXException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			logger.error("error  obtenerTraza LOG  JAXBException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//Error 1004
			objl.setCodError("1004");
			objl.setDescError("error  obtenerTraza LOG  JAXBException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
		}
		return objl;
		
	}
	
	@Override
	public ObjL obtenerTrazaSql(String amb, String fecha, String usuario){
		ObjL objl = new ObjL();
		ruta = scm.leerVarAmbienteXml(amb, "RepError");
		if(ruta.equals("")){
			ruta = environmentManager.getDatoAmbiente(amb, "RepError");
		}
		rutaTemp = ruta+"/"+fecha+"/"+usuario+".trc";
		directorio = new File(rutaTemp);
		FileReader fr;
		try {
			fr = new FileReader(directorio);
			br = new BufferedReader(fr);
			
			String line;
			String datosTraza = "";
			while ((line = br.readLine()) != null) {
				datosTraza += line;
		    }
			
			datosTraza = "<logs>"+datosTraza+"</logs>";
			//logger.info("xml log "+datosTraza);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(datosTraza)));

	        JAXBContext context = JAXBContextFactory.createContext(new Class[] {ObjL.class}, null);
	        
	        Unmarshaller un = context.createUnmarshaller();
	        un = context.createUnmarshaller();
	        
	        
	        objl = (ObjL) un.unmarshal(document);
	        
		} catch (FileNotFoundException e) {
			logger.error("error  obtenerTraza LOG FileNotFoundException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//error 1000
			objl.setCodError("1000");
			objl.setDescError("error  obtenerTraza LOG FileNotFoundException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (IOException e){
			logger.error("error  obtenerTraza LOG  IOException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//Error 1001
			objl.setCodError("1001");
			objl.setDescError("error  obtenerTraza LOG IOException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.error("error  obtenerTraza LOG  ParserConfigurationException "+e.getMessage()+" codigo "+e.getLocalizedMessage());
			//Error 1002
			objl.setCodError("1002");
			objl.setDescError("error  obtenerTraza LOG ParserConfigurationException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			logger.error("error  obtenerTraza LOG  SAXException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//Error 1003
			objl.setCodError("1003");
			objl.setDescError("error  obtenerTraza LOG  SAXException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			logger.error("error  obtenerTraza LOG  JAXBException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//Error 1004
			objl.setCodError("1004");
			objl.setDescError("error  obtenerTraza LOG  JAXBException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
		}
		return objl;
	}
	
	@Override	
	public ObjL obtenerTrazaRobot(String amb, String fecha, String usuario){
		
		ObjL objl = new ObjL();
		ruta = scm.leerVarAmbienteXml(amb, "RepError");
		if(ruta.equals("")){
			ruta = environmentManager.getDatoAmbiente(amb, "RepError");
		}
		rutaTemp = ruta+"/"+fecha+"/"+usuario+".clg";
		directorio = new File(rutaTemp);
		FileReader fr;
		try {
			fr = new FileReader(directorio);
			br = new BufferedReader(fr);
			
			String line;
			String datosTraza = "";
			while ((line = br.readLine()) != null) {
				datosTraza += line;
		    }
			
			datosTraza = "<logs>"+datosTraza+"</logs>";
			//logger.info("xml log "+datosTraza);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(datosTraza)));

	        JAXBContext context = JAXBContextFactory.createContext(new Class[] {ObjL.class}, null);
	        
	        Unmarshaller un = context.createUnmarshaller();
	        un = context.createUnmarshaller();
	        
	        
	        objl = (ObjL) un.unmarshal(document);
	        
		} catch (FileNotFoundException e) {
			logger.error("error  obtenerTraza LOG FileNotFoundException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//error 1000
			objl.setCodError("1000");
			objl.setDescError("error  obtenerTraza LOG FileNotFoundException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (IOException e){
			logger.error("error  obtenerTraza LOG  IOException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//Error 1001
			objl.setCodError("1001");
			objl.setDescError("error  obtenerTraza LOG IOException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.error("error  obtenerTraza LOG  ParserConfigurationException "+e.getMessage()+" codigo "+e.getLocalizedMessage());
			//Error 1002
			objl.setCodError("1002");
			objl.setDescError("error  obtenerTraza LOG ParserConfigurationException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			logger.error("error  obtenerTraza LOG  SAXException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//Error 1003
			objl.setCodError("1003");
			objl.setDescError("error  obtenerTraza LOG  SAXException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			logger.error("error  obtenerTraza LOG  JAXBException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//Error 1004
			objl.setCodError("1004");
			objl.setDescError("error  obtenerTraza LOG  JAXBException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
		}
		return objl;
	}
	
	@Override
	public ObjL obtenerTrazaEmail(String amb, String fecha, String usuario){
		
		ObjL objl = new ObjL();
		ruta = scm.leerVarAmbienteXml(amb, "RepError");
		if(ruta.equals("")){
			ruta = environmentManager.getDatoAmbiente(amb, "RepError");
		}
		rutaTemp = ruta+"/"+fecha+"/"+usuario+".mlg";
		directorio = new File(rutaTemp);
		FileReader fr;
		try {
			fr = new FileReader(directorio);
			br = new BufferedReader(fr);
			
			String line;
			String datosTraza = "";
			while ((line = br.readLine()) != null) {
				datosTraza += line;
		    }
			
			datosTraza = "<logs>"+datosTraza+"</logs>";
			//logger.info("xml log "+datosTraza);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(datosTraza)));

	        JAXBContext context = JAXBContextFactory.createContext(new Class[] {ObjL.class}, null);
	        
	        Unmarshaller un = context.createUnmarshaller();
	        un = context.createUnmarshaller();
	        
	        
	        objl = (ObjL) un.unmarshal(document);
	        
		} catch (FileNotFoundException e) {
			logger.error("error  obtenerTraza LOG FileNotFoundException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//error 1000
			objl.setCodError("1000");
			objl.setDescError("error  obtenerTraza LOG FileNotFoundException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (IOException e){
			logger.error("error  obtenerTraza LOG  IOException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//Error 1001
			objl.setCodError("1001");
			objl.setDescError("error  obtenerTraza LOG IOException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.error("error  obtenerTraza LOG  ParserConfigurationException "+e.getMessage()+" codigo "+e.getLocalizedMessage());
			//Error 1002
			objl.setCodError("1002");
			objl.setDescError("error  obtenerTraza LOG ParserConfigurationException"+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			logger.error("error  obtenerTraza LOG  SAXException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//Error 1003
			objl.setCodError("1003");
			objl.setDescError("error  obtenerTraza LOG  SAXException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			logger.error("error  obtenerTraza LOG  JAXBException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
			//Error 1004
			objl.setCodError("1004");
			objl.setDescError("error  obtenerTraza LOG  JAXBException "+e.getMessage() +" codigo "+e.getLocalizedMessage());
		}
		return objl;
	}
}
