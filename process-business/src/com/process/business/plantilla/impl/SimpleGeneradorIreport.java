package com.process.business.plantilla.impl;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
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

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import org.apache.log4j.Logger;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.process.business.helper.ClassFactory;
import com.process.business.helper.c_Process;
import com.process.business.plantilla.GeneradorIreportManager;
import com.process.domain.document2.Anexos;
import com.process.domain.document2.Campo;
import com.process.domain.document2.Doc2;
import com.process.domain.document2.Fila;
import com.process.domain.document2.Forma;
import com.process.domain.document2.Formas;
import com.process.domain.generadordocument.Plantilla;





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
	public String ireportGenerator(String nombreForm, String wfa, Plantilla plantilla){
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put(titulo, valor); // se agrega la variable mapeada.
		String archivo = plantilla.getPathArch()+plantilla.getpNombreArch()+".jrxml";
		motor = ClassFactory.getProcess(engineP); //--> se instancia motor
		String xmlDatosDoc = motor.p4bObtenerCamposDoc(0,0,0);//Obtener xml de documento
		
		//map = jsonToJasper(nombreForm, wfa, plantilla.getPathArch(), xmlDatosDoc);
		JsonObjectBuilder json = jsonToJasper(nombreForm, wfa, plantilla.getPathArch(), xmlDatosDoc);
		
		
		//map.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, is);
		
		String rutaTemp = "";
		//map.put("SUBREPORT_DIR", "C:/BPMprocess/Reps/angular/reportes/");
		
		File archivoJrxml = new File(archivo);
		
		//logger.info(map.toString());
		try {
			if(archivoJrxml.exists()) {
				InputStream is =new ByteArrayInputStream(json.build().toString().getBytes("UTF-8"));
				JRDataSource dataSource = new JsonDataSource(is);
				JasperReport report = JasperCompileManager.compileReport(archivo);
				//JasperPrint print = JasperFillManager.fillReport(report, map);
				JasperPrint print = JasperFillManager.fillReport(report, map, dataSource);				
				//JasperExportManager.exportReportToPdfFile(print, plantilla.getPathArch()+plantilla.getpNombreArch()+plantilla.getpExtArch());
				 JRPdfExporter exporter = new JRPdfExporter();
				 exporter.setExporterInput(new SimpleExporterInput(print));
			     exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(plantilla.getPathArch()+plantilla.getpNombreArch()+plantilla.getpExtArch()));
			     SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			     configuration.setMetadataAuthor("BPMProcess");
			     exporter.setConfiguration(configuration);
			     exporter.exportReport();
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
			
		} catch (JRException | ParserConfigurationException | SAXException | IOException | JAXBException e) {
			// TODO Auto-generated catch block
			logger.error("pdfPrueba", e);
		}
		
		return rutaTemp;
	}
	
	private JsonObjectBuilder jsonToJasper(String nombreForm, String wfa, String ruta, String docForm){
		Map<String, Object> map = new HashMap<String, Object>(); //variable para pasar metodos
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
										jsonFila.add(fila.getCampo().get(f).getNombre(), fila.getCampo().get(f).getValue());
									}
									jsonMatriz.add(jsonFila);
								}
								json.add(campos.get(j).getNombre(), jsonMatriz);
								
							}else if (campos.get(j).getTipo().equals("S")){ //Padre alternativa
								String valorPA= "";
								for(int c=0; c < campos.get(j).getCampos().size(); c++){
									Campo campoPA = campos.get(j).getCampos().get(c);
									if(campoPA.getValue().equals("T")){
										valorPA = valorPA+campoPA.getDescripcion()+", ";
									}								
								}
								if(valorPA.length() > 1) {
									valorPA = valorPA.substring(0, valorPA.length() - 2);
								}
								json.add(campos.get(j).getNombre(), valorPA);
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
}
