package com.process.business.console.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sun.util.logging.resources.logging;

import com.process.business.console.ConsoleManager;
import com.process.business.helper.ClassFactory;
import com.process.business.helper._Ambientes;
import com.process.domain.console.Item;
import com.process.domain.console.Registro;
import com.process.domain.environment.Environment;
import com.process.domain.environment.MatrizEnvironment;
import com.sun.org.apache.xml.internal.security.utils.JavaUtils;

import com4j.Holder;

@Service("ConsoleManager")
public class SimpleConsoleManager implements ConsoleManager {
	@Override
	public void setEngineId(Integer engineId) {
		// TODO Auto-generated method stub
		
	}
	private static final Logger logger = Logger.getLogger(SimpleConsoleManager.class);
	private Registro reg;
	private File archivo;
	private JAXBContext context;
	private boolean correcto;
	private String pathMotor;
	@Override
	public Registro obtenerDatosConsole(String ambiente){
		reg = null;
		pathMotor = obtenerPathMotor();
		archivo = new File(pathMotor+ambiente+".xml");
		//logger.info("archivo "+pathMotor+ambiente+".xml");
		if(archivo.exists()) {
			try {
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
		        Document document = documentBuilder.parse(archivo);
		        context = JAXBContextFactory.createContext(new Class[] {Registro.class}, null);
		        Unmarshaller un = context.createUnmarshaller();
		        un = context.createUnmarshaller();
		        reg = (Registro) un.unmarshal(document);
				
			} catch (SAXException e) {
				logger.error("Error archivo SAXException obtenerDatosConsole "+e.getMessage()+" causa "+e.getLocalizedMessage());
				return reg;
			} catch (IOException e) {
				logger.error("Error archivo IOException obtenerDatosConsole "+e.getMessage()+" causa "+e.getLocalizedMessage());
				return reg;
			} catch (ParserConfigurationException e) {
				logger.error("Error archivo ParserConfigurationException obtenerDatosConsole "+e.getMessage()+" causa "+e.getLocalizedMessage());
				return reg;
			} catch (JAXBException e) {
				logger.error("Error archivo JAXBException obtenerDatosConsole "+e.getMessage()+" causa "+e.getLocalizedMessage());
				return reg;
			}
		}
		
		return reg;
	}
	
	public Boolean crearDatosConsole(String ambiente,Registro registro){
		pathMotor = obtenerPathMotor();
		String fileLocation = pathMotor+ambiente+".xml";
		String archivo1 = pathMotor+ambiente+".xml";
		//String archivo2 = pathMotor+"ambientes/backup/"+ambiente+".back";
		
		 File origin = new File(archivo1);
		//verificar si el archivo esta creado
		boolean correcto = origin.exists();
				//moveFile(archivo1, archivo2);
		//logger.info("Ruta "+fileLocation);
		if(!correcto){
		try {
				archivo = new File(fileLocation);
				
				context = JAXBContextFactory.createContext(new Class[] {Registro.class}, null);
				Marshaller ms = context.createMarshaller();
				ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
				ms.setProperty(Marshaller.JAXB_ENCODING,"ISO-8859-1");
				ms.marshal(registro, archivo);
				return true;
				//logger.info("ingresa 12s"+registro.getDirecInternet().getItem().get(0).getId());
			} catch (JAXBException e) {
				logger.error("Error archivo JAXBException crearDatosConsole "+e.getMessage()+" causa "+e.getLocalizedMessage());
				return false;
			} catch (Exception e) {
				logger.error("Error archivo error crearDatosConsole "+e.getMessage()+" causa "+e.getLocalizedMessage());
				return false;
			}
			//Realiza la convercion de los objetos java a xml
		}else{
			logger.error("El archivo ya esta creado "+ambiente );
			return false;
		}
		
		
		
	}
	@Override
	public Boolean modificarObjetoConsole(String ambiente,Registro registro, String nameObj){
		
		String fileLocation = pathMotor+ambiente+".xml";
		String archivo1 = pathMotor+ambiente+".xml";
		String archivo2 = pathMotor+"backup/"+ambiente+".back";
		
		/*backup*/
		correcto = moveFile(archivo1, archivo2);
		if(correcto){
			/*Se genera un objeto tempotal del archivo*/
			Registro regTemp = obtenerDatosConsole(ambiente);
			
			switch (nameObj) {
			case "Administrador":
				regTemp.setAdministrador(registro.getAdministrador());
				break;
			case "Configuracion":
				regTemp.setConfiguracion(registro.getConfiguracion());
				break;
			case "DirecInternet":
				regTemp.setDirecInternet(registro.getDirecInternet());				
				break;
			case "Email":
				regTemp.setEmail(registro.getEmail());				
				break;
			case "FuenteDatos":
				regTemp.setFuenteDatos(registro.getFuenteDatos());				
				break;
			case "Inspeccion":
				regTemp.setInspeccion(registro.getInspeccion());				
				break;
			case "RepFisico":
				regTemp.setRepFisico(registro.getRepFisico());				
				break;
			case "Seguridad":
				regTemp.setSeguridad(registro.getSeguridad());				
				break;
			case "VarAmbiente":
				regTemp.setVarAmbiente(registro.getVarAmbiente());				
				break;
			default:
				break;
			}
			
			try {
				archivo = new File(fileLocation);
				context = JAXBContextFactory.createContext(new Class[] {Registro.class}, null);
				Marshaller ms = context.createMarshaller();
				ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
				ms.setProperty(Marshaller.JAXB_ENCODING,"ISO-8859-1");
				ms.marshal(regTemp, archivo);
			} catch (JAXBException e) {
				logger.error("Error archivo JAXBException ModificarDatosConsole "+e.getMessage()+" causa "+e.getLocalizedMessage());
				return false;
			}
			
		}else{
			logger.error("Error realizando backup en el archivo "+ambiente );
			return false;
		}
		
		
		return correcto;
	}
	

	public String leerVarAmbienteXml(String ambiente, String var ){
		/*pathMotor = obtenerPathMotor();
		archivo = new File(pathMotor+"\\"+ambiente+".xml");*/
		Registro regTemp = obtenerDatosConsole(ambiente);
		String r = "";
		if(regTemp != null){
			ArrayList<Item> arrayItem = new ArrayList<Item>();
			
			arrayItem.addAll((ArrayList<Item>) regTemp.getAdministrador().getItem());
			arrayItem.addAll((ArrayList<Item>) regTemp.getConfiguracion().getItem());
			arrayItem.addAll((ArrayList<Item>) regTemp.getDirecInternet().getItem());
			arrayItem.addAll((ArrayList<Item>) regTemp.getEmail().getItem());
			arrayItem.addAll((ArrayList<Item>) regTemp.getFuenteDatos().getItem());
			arrayItem.addAll((ArrayList<Item>) regTemp.getInspeccion().getItem());
			arrayItem.addAll((ArrayList<Item>) regTemp.getRepFisico().getItem());
			arrayItem.addAll((ArrayList<Item>) regTemp.getSeguridad().getItem());
			arrayItem.addAll((ArrayList<Item>) regTemp.getVarAmbiente().getItem());
			for(Item i : arrayItem){
				if(i.getId().toUpperCase().equals(var.toUpperCase()) && i.getType().equals("T")){
					r = i.getValue();
					break;
				}
			}	
		}
			
		return r;
	}
	
	public List<Environment> getListAmbientes(){
		pathMotor = obtenerPathMotor();
		List<Environment> list = new ArrayList<Environment>();
		String ambienteTemp = "";
		File[] ambientes = new File(pathMotor).listFiles();
		if(ambientes != null && ambientes.length != 0){
			for (int x=0; x< ambientes.length; x++) {
				//logger.info(ambientes[x].getName());
					
				if(ambientes[x] != null && ambientes[x].isFile() && ambientes[x].getName().endsWith(".xml")){
					Environment env = new Environment();
					ambienteTemp = ambientes[x].getName().replace(".xml", "");
					env.setName(ambienteTemp);
					
					MatrizEnvironment matriz = new MatrizEnvironment();
					matriz.setIdioma(leerVarAmbienteXml(ambienteTemp, "Idioma"));
					matriz.setWebtipoConexion(leerVarAmbienteXml(ambienteTemp, "WebtipoConexion"));
					matriz.setNivelSeg(leerVarAmbienteXml(ambienteTemp, "NivelSeg"));
					matriz.setWebAmbientes(leerVarAmbienteXml(ambienteTemp, "WebAmbientes"));
					matriz.setDominio(leerVarAmbienteXml(ambienteTemp, "Dominio"));	
					matriz.setiRepInclude(leerVarAmbienteXml(ambienteTemp, "i_RepInclude"));
					matriz.setFormatoFecha(leerVarAmbienteXml(ambienteTemp, "FormatoFecha"));
					matriz.setiRepAnexos(leerVarAmbienteXml(ambienteTemp, "i_RepAnexos"));
					matriz.setTamanoAnexo(leerVarAmbienteXml(ambienteTemp, "tamanoAnexo"));
					matriz.setTimeSave(leerVarAmbienteXml(ambienteTemp, "timeSave"));
					
					if (matriz.getFormatoFecha().equals("")){
						matriz.setFormatoFecha("DD/MM/YYYY");
					}
					String fdm = leerVarAmbienteXml(ambienteTemp, "FormatoMilesDecimal");
					if (fdm.equals("")){
						matriz.setFormatoMiles(".");
						matriz.setFormatoDecimal(",");
					}else{
						matriz.setFormatoMiles(fdm.substring(0, 0));
						matriz.setFormatoDecimal(fdm.substring(1, 1));					
					}
					env.setMatriz(matriz);
					list.add(env);
				}
			}
		}else{
			logger.error("Error no existe ambientes XML");
		}
		return list;
	}
	
	public Environment getAmbienteXml(String ambiente){
		
		Environment env = new Environment();
		pathMotor = obtenerPathMotor();
		String archivo1 = pathMotor+ambiente+".xml";
		
		 File origin = new File(archivo1);
		//verificar si el archivo esta creado
		boolean correcto = origin.exists();
		if(correcto){
			env.setName(ambiente);
			MatrizEnvironment matriz = new MatrizEnvironment();
			matriz.setIdioma(leerVarAmbienteXml(ambiente, "Idioma"));
			matriz.setWebtipoConexion(leerVarAmbienteXml(ambiente, "WebtipoConexion"));
			matriz.setNivelSeg(leerVarAmbienteXml(ambiente, "NivelSeg"));
			matriz.setWebAmbientes(leerVarAmbienteXml(ambiente, "WebAmbientes"));
			matriz.setDominio(leerVarAmbienteXml(ambiente, "Dominio"));	
			matriz.setiRepInclude(leerVarAmbienteXml(ambiente, "i_RepInclude"));
			matriz.setFormatoFecha(leerVarAmbienteXml(ambiente, "FormatoFecha"));
			matriz.setRepAgentes(leerVarAmbienteXml(ambiente, "RepAgentes"));
			matriz.setiRepAnexos(leerVarAmbienteXml(ambiente, "i_RepAnexos"));
			matriz.setTamanoAnexo(leerVarAmbienteXml(ambiente, "tamanoAnexo"));
			matriz.setTimeSave(leerVarAmbienteXml(ambiente, "timeSave"));
			
			if (matriz.getFormatoFecha().equals("")){
				matriz.setFormatoFecha("DD/MM/YYYY");
			}
			String fdm = leerVarAmbienteXml(ambiente, "FormatoMilesDecimal");
			if (fdm.equals("")){
				matriz.setFormatoMiles(".");
				matriz.setFormatoDecimal(",");
			}else{
				matriz.setFormatoMiles(fdm.substring(0, 0));
				matriz.setFormatoDecimal(fdm.substring(1, 1));					
			}
			env.setMatriz(matriz);
		}else{
			env = null;
		}
		
		return env;
	}
	
	private String obtenerPathMotor(){
		URL fileLocation = getClass().getClassLoader().getResource("configuracionProcess.xml");
		archivo = new File(fileLocation.getFile());
		String pathAmbiente = "";
		try {
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
	        Document document = documentBuilder.parse(archivo);
	        document.getDocumentElement().normalize();
	        NodeList nodoListPath=  document.getElementsByTagName("PathAmbientes");
			Element elemtPath = (Element) nodoListPath.item(0);
			pathAmbiente = elemtPath.getTextContent();
		}catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.error("Error archivo ParserConfigurationException obtenerPathMotor "+e.getMessage()+" causa "+e.getLocalizedMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			logger.error("Error archivo SAXException obtenerPathMotor "+e.getMessage()+" causa "+e.getLocalizedMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error archivo IOException obtenerPathMotor "+e.getMessage()+" causa "+e.getLocalizedMessage());
		}
		return pathAmbiente;
	}
	
	private boolean moveFile(String fromFile, String toFile) {
        File origin = new File(fromFile);
        File destination = new File(toFile);

        if (origin.exists()) {
            try {
                InputStream in = new FileInputStream(origin);
                OutputStream out = new FileOutputStream(destination);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                return true;
                //return origin.delete();
            } catch (IOException e) {
            	logger.error("Error archivo JAXBException moveFile "+e.getMessage()+" causa "+e.getLocalizedMessage());
                return false;
            }
        } else {
            return false;
        }
    }
	
	
	@Override
	public Boolean migradorAmbientRegToXml(String ambiente){
		String esquema = "../ambientes"; 
		Registro regTemp = obtenerDatosConsole(esquema);
		
		_Ambientes motor = ClassFactory.createAmbientes();
		Holder<String> app = new Holder<String>();
		motor.init(app);
		/*Administrador*/
		for (int i = 0; i < regTemp.getAdministrador().getItem().size(); i++) {
			regTemp.getAdministrador().getItem().get(i).setValue(motor.leerVarAmbienteEx(ambiente,regTemp.getAdministrador().getItem().get(i).getId()));
			//logger.info(i+" "+regTemp.getAdministrador().getItem().get(i).getId()+" "+motor.leerVarAmbienteEx(ambiente,regTemp.getAdministrador().getItem().get(i).getId()));
		}
		/*configuracion*/
		for (int i = 0; i < regTemp.getConfiguracion().getItem().size(); i++) {
			regTemp.getConfiguracion().getItem().get(i).setValue(motor.leerVarAmbienteEx(ambiente,regTemp.getConfiguracion().getItem().get(i).getId()));
			//logger.info(i+" "+regTemp.getConfiguracion().getItem().get(i).getId()+" "+motor.leerVarAmbienteEx(ambiente,regTemp.getConfiguracion().getItem().get(i).getId()));
		}
		/*DirecInternet*/
		for (int i = 0; i < regTemp.getDirecInternet().getItem().size(); i++) {
			regTemp.getDirecInternet().getItem().get(i).setValue(motor.leerVarAmbienteEx(ambiente,regTemp.getDirecInternet().getItem().get(i).getId()));
			//logger.info(i+" "+regTemp.getDirecInternet().getItem().get(i).getId()+" "+motor.leerVarAmbienteEx(ambiente,regTemp.getDirecInternet().getItem().get(i).getId()));
		}
		/*Email*/
		for (int i = 0; i < regTemp.getEmail().getItem().size(); i++) {
			regTemp.getEmail().getItem().get(i).setValue(motor.leerVarAmbienteEx(ambiente,regTemp.getEmail().getItem().get(i).getId()));
			//logger.info(i+" "+regTemp.getEmail().getItem().get(i).getId()+" "+motor.leerVarAmbienteEx(ambiente,regTemp.getEmail().getItem().get(i).getId()));
		}
		/*Fuente de datos*/
		for (int i = 0; i < regTemp.getFuenteDatos().getItem().size(); i++) {
			regTemp.getFuenteDatos().getItem().get(i).setValue(motor.leerVarAmbienteEx(ambiente,regTemp.getFuenteDatos().getItem().get(i).getId()));
			//logger.info(i+" "+regTemp.getFuenteDatos().getItem().get(i).getId()+" "+motor.leerVarAmbienteEx(ambiente,regTemp.getFuenteDatos().getItem().get(i).getId()));
		}
		/*Inspeccion*/
		for (int i = 0; i < regTemp.getInspeccion().getItem().size(); i++) {
			regTemp.getInspeccion().getItem().get(i).setValue(motor.leerVarAmbienteEx(ambiente,regTemp.getInspeccion().getItem().get(i).getId()));
			//logger.info(i+" "+regTemp.getInspeccion().getItem().get(i).getId()+" "+motor.leerVarAmbienteEx(ambiente,regTemp.getInspeccion().getItem().get(i).getId()));
		}
		/*Repositorio fisico*/
		for (int i = 0; i < regTemp.getRepFisico().getItem().size(); i++) {
			regTemp.getRepFisico().getItem().get(i).setValue(motor.leerVarAmbienteEx(ambiente,regTemp.getRepFisico().getItem().get(i).getId()));
			//logger.info(i+" "+regTemp.getRepFisico().getItem().get(i).getId()+" "+motor.leerVarAmbienteEx(ambiente,regTemp.getRepFisico().getItem().get(i).getId()));
		}
		/*Var ambiente*/
		for (int i = 0; i < regTemp.getVarAmbiente().getItem().size(); i++) {
			regTemp.getVarAmbiente().getItem().get(i).setValue(motor.leerVarAmbienteEx(ambiente,regTemp.getVarAmbiente().getItem().get(i).getId()));
			//logger.info(i+" "+regTemp.getVarAmbiente().getItem().get(i).getId()+" "+motor.leerVarAmbienteEx(ambiente,regTemp.getVarAmbiente().getItem().get(i).getId()));
		}
		Boolean flag =  crearDatosConsole(ambiente,regTemp);
		return flag;
	}

}
