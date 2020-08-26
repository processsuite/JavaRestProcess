package com.process.business.plantilla.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsxExporterConfiguration;

import org.apache.log4j.Logger;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.process.business.environment.impl.SimpleEnvironmentManager;
import com.process.business.helper.ClassFactory;
import com.process.business.helper.ConexionBD;
import com.process.business.helper.c_Process;
import com.process.business.plantilla.GeneradorIreportManager;
import com.process.business.report.impl.SimpleReportManager;
import com.process.domain.document2.Campo;
import com.process.domain.document2.Doc2;
import com.process.domain.document2.Fila;
import com.process.domain.document2.Forma;
import com.process.domain.document2.Formas;
import com.process.domain.generadordocument.Plantilla;
import com.process.domain.report.FieldReport;





@Service("generadorIreportManager")
public class SimpleGeneradorIreport implements GeneradorIreportManager{
	private static final Logger logger = Logger.getLogger(SimpleGeneradorIreport.class);
	private c_Process motor;
	
	private Integer engineP;
	
	
	@Override
	public void setEngineId(Integer engineId) {
		engineP = engineId;
	}	
	
	@Override
	public String ireportGenerator(String nombreForm, String wfa, Plantilla plantilla, String ambiente){
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put(titulo, valor); // se agrega la variable mapeada.
		String archivo = plantilla.getPathArch()+plantilla.getpNombreArch()+".jrxml";
		motor = ClassFactory.getProcess(engineP); //--> se instancia motor
		String xmlDatosDoc = motor.p4bObtenerCamposDoc(0,0,0);//Obtener xml de documento
		JsonObjectBuilder json = null;
		//map = jsonToJasper(nombreForm, wfa, plantilla.getPathArch(), xmlDatosDoc);
		if(plantilla.getpConsultabool().equals("1")) {
			map = jrxmlToPAram(plantilla);
			logger.info(map);
		}else {
			json = jsonToJasper(nombreForm, wfa, plantilla.getPathArch(), xmlDatosDoc);
		}
		String rutaTemp = "";
		File archivoJrxml = new File(archivo);
		try {
			if(archivoJrxml.exists()) {
				JasperReport report = JasperCompileManager.compileReport(archivo);
				JasperPrint print;
				if(plantilla.getpConsultabool().equals("1")){
					ConexionBD cdb = new ConexionBD(ambiente);
					Class.forName(cdb.getDriverJDBC());
					Connection conn = DriverManager.getConnection(cdb.getCadena(),cdb.getUser(),cdb.getClave());
					print = JasperFillManager.fillReport(report, map, conn);	
				}else { //aplica si solo es json
					InputStream is =new ByteArrayInputStream(json.build().toString().getBytes("UTF-8"));
					JRDataSource dataSource = new JsonDataSource(is);
					print = JasperFillManager.fillReport(report, map, dataSource);	
				}
								
				JRPdfExporter exporter = new JRPdfExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
			    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(plantilla.getPathArch()+plantilla.getpNombreArch()+plantilla.getpExtArch()));
			    SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			    configuration.setMetadataAuthor("BPMProcess");
			    exporter.setConfiguration(configuration);
			    exporter.exportReport();
				
			     
			     /*Anexar reporte generado*/
			     if(plantilla.getpAnexar() == 1) {
					
					String anexosv = motor.p4bObtenerDocumento(0, 1, 0);
					logger.info("anexos datos "+anexosv);
					/*Obtener anexos creados para eliminarlos*/
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			        
			        Document document = documentBuilder.parse(new InputSource(new StringReader(anexosv))); //cargar documento de process
					JAXBContext context = JAXBContextFactory.createContext(new Class[] {Doc2.class}, null);
					Unmarshaller un = context.createUnmarshaller();
					Doc2 folder = (Doc2) un.unmarshal(document);
					
					if(folder.getAnexos() != null) {
						for(int x=0; x < folder.getAnexos().getAnexo().size();x++) {
							if(folder.getAnexos().getAnexo().get(x).getDescripcion().toUpperCase().equals(plantilla.getpDescripcion().toUpperCase())){
								logger.info("cantidad "+folder.getAnexos().getAnexo().size()+" descripcion "+x+" "+folder.getAnexos().getAnexo().get(x).getDescripcion());
								motor.p4bMarcarAnexoDocumento(0, folder.getAnexos().getAnexo().get(x).getNumero(), -1);
							}
						}
					}	
						
										
					 motor.p4bAnexarDocumento(0, plantilla.getPathArch()+plantilla.getpNombreArch()+plantilla.getpExtArch(), "", plantilla.getpDescripcion(), "", "");
					 
					 String anexosv2 = motor.p4bObtenerDocumento(0, 1, 0);
					 document = documentBuilder.parse(new InputSource(new StringReader(anexosv2)));
					 folder = (Doc2) un.unmarshal(document);
					 
					 for(int x=0; x < folder.getAnexos().getAnexo().size();x++) {
							if(folder.getAnexos().getAnexo().get(x).getDescripcion().toUpperCase().equals(plantilla.getpDescripcion().toUpperCase()) && folder.getAnexos().getAnexo().get(x).getBorrado().equals("N")){
								rutaTemp = folder.getAnexos().getAnexo().get(x).getHref();
							}
						}
					 
					 logger.info("respuesta p4bAnexar "+rutaTemp);
					 
					 /*Eliminar archivo*/
					 File archivoDel = new File(plantilla.getPathArch()+plantilla.getpNombreArch()+plantilla.getpExtArch());
					 if(archivoDel.delete()) {
						 logger.info("Se elimino el archivo exitosamente");
					 }
				}else {
					logger.info("No se anexa archivo");
				}		    
				logger.info("xml archivos 2 "+motor.p4bObtenerDocumento(0, 1, 0));
			}else {
				logger.info("El archivo JRXML No ha sido creado");
			}
			motor.p4bAsignarValorCampoDocumento(plantilla.getpCampoInd(), "X", 0, 0);
			
		} catch (JRException | ParserConfigurationException | SAXException | IOException | JAXBException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			logger.error("pdfPrueba", e);
			motor.p4bAsignarValorCampoDocumento(plantilla.getpCampoInd(), "", 0, 0);
		}
		
		return rutaTemp;
	}
	
	private JsonObjectBuilder jsonToJasper(String nombreForm, String wfa, String ruta, String docForm){
		JsonObjectBuilder json = Json.createObjectBuilder();
		//logger.info("xml atributo "+xmlDatosDoc);
		//String nombreArchivoJson = nombreForm.replace(" ", "_")+"_"+wfa;
		
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
	        Document document = documentBuilder.parse(new InputSource(new StringReader(docForm))); //cargar documento de process
			JAXBContext context = JAXBContextFactory.createContext(new Class[] {Formas.class}, null);
			Unmarshaller un = context.createUnmarshaller();
			Formas formularios = (Formas) un.unmarshal(document);
			
			
			for(int i=0;i < formularios.getFormas().size(); i++){// se recorre la cantidad de formularios
				Forma forma = formularios.getFormas().get(i);
				for(int b=0;b < forma.getListGrupoCampos().size(); b++){//se recorre los grupos
					List<Campo> campos = forma.getListGrupoCampos().get(b).getCampo();
					for(int j=0;j < campos.size(); j++){ //recorro los Campos 
						
							if (campos.get(j).getTipo().equals("L")){//Campos listas
								String valorSelect = "";
								for(int a =0; a < campos.get(j).getOpciones().getOpcion().size(); a++ ){
									if(campos.get(j).getOpciones().getOpcion().get(a).getSeleccionado()){
										valorSelect = valorSelect+""+campos.get(j).getOpciones().getOpcion().get(a).getValue();
										if(campos.get(j).getOpciones().getMultiple()){
											valorSelect = valorSelect+", ";
										}
									}									
								}
								if(campos.get(j).getOpciones().getMultiple() && valorSelect.length() > 1){
									valorSelect = valorSelect.substring(0, valorSelect.length() - 2);
								}
								json.add(campos.get(j).getNombre(), valorSelect);
								//json.add(campos.get(j).getNombre(), campos.get(j).getValue());
							}else if (campos.get(j).getTipo().equals("M")){ //campos matriz
								JsonArrayBuilder jsonMatriz = Json.createArrayBuilder();
								for(int m=0; m < campos.get(j).getFilas().getFila().size(); m++){
									JsonObjectBuilder jsonFila = Json.createObjectBuilder();
									Fila fila = campos.get(j).getFilas().getFila().get(m);
									for(int f=0;f<fila.getCampo().size();f++){
										
										if(campos.get(j).getCampos().get(f).getTipo().equals("V")) {
											jsonFila.add(fila.getCampo().get(f).getNombre(), fila.getCampo().get(f).getValue().equals("T")?"X":"");
										}else {
											jsonFila.add(fila.getCampo().get(f).getNombre(), fila.getCampo().get(f).getValue());
										}
										
									}
									jsonMatriz.add(jsonFila);
								}
								json.add(campos.get(j).getNombre(), jsonMatriz);
								
							}else if (campos.get(j).getTipo().equals("S")){ //Padre alternativa
								for(int c=0; c < campos.get(j).getCampos().size(); c++){
									Campo campoPA = campos.get(j).getCampos().get(c);
									if(campoPA.getValue().equals("T")){
										json.add(campoPA.getNombre(), "X");
									}else {
										json.add(campoPA.getNombre(), "");
									}								
								}
								
							}else{//campo normal
								json.add(campos.get(j).getNombre(), campos.get(j).getValue());
							} 
					}
				}
				
			}
			
			
			
			
	        logger.info("Json "+json.build().toString());
	       // jsonToFile(json, nombreArchivoJson, ruta);
		}catch(Exception e){
			logger.error("mapeoVariablesJrxml error ",e);
		}
		
		return json;
	}
	/*
	private Boolean jsonToFile(JsonObjectBuilder json, String nombre, String ruta) {
		
		 	File archivo = new File(ruta+"/json/"+nombre+".json");
	        BufferedWriter bw;
	        
	        try {
	        	if(archivo.exists()) {
		        	bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo), "utf-8"));
		            bw.write(json.build().toString());
		        } else {
		            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo), "utf-8"));
		            bw.write(json.build().toString());
		        }
	        	 bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("jsonToFile", e);
				return false;
			}
	        
	       
		return true;
	}
	*/
	
	private  Map<String, Object> jrxmlToPAram(Plantilla plantilla) {
		Map<String, Object> map = new HashMap<String, Object>();
		File archivo  = new File(plantilla.getPathArch()+plantilla.getpNombreArch()+".jrxml");
		motor = ClassFactory.getProcess(engineP); //--> se instancia motor
		
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
	        Document document = documentBuilder.parse(archivo);
	        document.getDocumentElement().normalize();
	        NodeList listServ = document.getElementsByTagName("parameter");
	        for(int x = 0; x < listServ.getLength();x++) {
	        	Node nodo = listServ.item(x);
	        	Element element = (Element) nodo;
	        	logger.info(element.getAttribute("name").toString()+" "+motor.p4bObtenerValorCampoDocumento(element.getAttribute("name").toString(),0,0,0));
	        	map.put( element.getAttribute("name").toString(),motor.p4bObtenerValorCampoDocumento(element.getAttribute("name").toString(), 0, 0, 0)); 
	        }
	        
	        
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			logger.error("jrxmlToPAram ParserConfigurationException",e);
			e.printStackTrace();
		} catch (SAXException e) {
			logger.error("jrxmlToPAram SAXException",e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("jrxmlToPAram IOException",e);
		}
       
			
		return map;
	}
	
	@Override
	public String ejecutarConsultaReport(Integer wfPadre, Integer wfHijo, Integer tipoOpcion, Integer desde, List<FieldReport> camposBuscar, String campoOrden, String rutaAgentes, String ext, String ambiente) {
		String archivo = rutaAgentes+wfHijo+".jrxml";
		String ruta= "";
		motor = ClassFactory.getProcess(engineP);
		SimpleEnvironmentManager am = new SimpleEnvironmentManager();
		String repAnexos =am.getDatoAmbiente(ambiente, "RepAnexosVirtual");
		String finalArchivo = repAnexos+"\\"+engineP+"_"+wfHijo+ext;
		logger.info("finalArchivo "+finalArchivo);
		SimpleReportManager rm = new SimpleReportManager();
		JsonObjectBuilder json = Json.createObjectBuilder();
		try{
			String resultXml = motor.p4bEjecutarConsulta(wfPadre, wfHijo, tipoOpcion, desde,  rm.getXmlParam(camposBuscar), campoOrden);
			logger.info("filtros "+camposBuscar.size());
				for(int x=0; x<camposBuscar.size(); x++ ) {
					//String f = "filtro"+Integer.toString(x+1);
					json.add(camposBuscar.get(x).getCampoBd(), camposBuscar.get(x).getValor());
					
				}
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();	
			
			XPathExpression expr = xpath.compile("//inst");
			Object result = expr.evaluate(document, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;				
			

			JsonArrayBuilder jsonMatriz = Json.createArrayBuilder(); //Matriz			
				for(int i=0;i < nodes.getLength(); i++){
					XPathExpression exprMostrar = xpath.compile("//inst[" + (i+1) + "]/camposMostrar/campo");
					Object resultMostrar = exprMostrar.evaluate(document, XPathConstants.NODESET);
					NodeList nodesMostrar = (NodeList) resultMostrar;
					JsonObjectBuilder jsonFila = Json.createObjectBuilder(); //fila
					for(int j=0;j < nodesMostrar.getLength(); j++){
						jsonFila.add(xpath.compile("campoBD").evaluate(nodesMostrar.item(j), XPathConstants.STRING).toString(), xpath.compile("valor").evaluate(nodesMostrar.item(j), XPathConstants.STRING).toString());
					}		
					jsonMatriz.add(jsonFila);
				}
				json.add("reporte", jsonMatriz);
				logger.info("json "+json.build().toString());
				HashMap<String, Object>	map = new HashMap<String, Object>();
				File archivoJrxml = new File(archivo);
					if(archivoJrxml.exists()) {
						InputStream is =new ByteArrayInputStream(json.build().toString().getBytes("UTF-8"));
						JRDataSource dataSource = new JsonDataSource(is);
						JasperReport report = JasperCompileManager.compileReport(archivo);
						//JasperPrint print = JasperFillManager.fillReport(report, map);
						JasperPrint print = JasperFillManager.fillReport(report, map, dataSource);				
						if(ext.equals(".pdf")) {
							 JRPdfExporter exporter = new JRPdfExporter();
							 exporter.setExporterInput(new SimpleExporterInput(print));
						     exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(finalArchivo));
						     SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
						     configuration.setMetadataAuthor("BPMProcess");
						     exporter.setConfiguration(configuration);
						     exporter.exportReport();
							
						}else if(ext.equals(".xlsx")) {
							JRXlsxExporter exporter = new JRXlsxExporter();
							 exporter.setExporterInput(new SimpleExporterInput(print));
						     exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(finalArchivo));
						     SimpleXlsxExporterConfiguration configuration = new SimpleXlsxExporterConfiguration();
						     configuration.setMetadataAuthor("BPMProcess");
						     exporter.setConfiguration(configuration);
						     exporter.exportReport();
						     logger.info("impresion xls");
						     
						}
						ruta =engineP+"_"+wfHijo+ext;
					 
				}else {
					logger.info("El archivo JRXML No ha sido creado, [No posee plantilla]");
				}
			
		}catch(Exception e){
			logger.error("ejecutarConsulta:", e);
		}
		
		return ruta;
	}
}
