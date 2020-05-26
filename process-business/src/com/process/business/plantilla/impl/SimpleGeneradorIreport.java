package com.process.business.plantilla.impl;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;






import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import org.apache.log4j.Logger;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.process.business.helper.ClassFactory;
import com.process.business.helper.c_Process;
import com.process.business.plantilla.GeneradorIreportManager;
import com.process.domain.document2.Campo;
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
	public Boolean ireportGenerator(String nombreForm, String wfa, Plantilla plantilla){
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put(titulo, valor); // se agrega la variable mapeada.
		String archivo = plantilla.getPathArch()+plantilla.getpNombreArch()+".jrxml";
		motor = ClassFactory.getProcess(engineP); //--> se instancia motor
		String xmlDatosDoc = motor.p4bObtenerCamposDoc(0,0,0);//Obtener xml de documento
		
		map = jsonToJasper(nombreForm, wfa, plantilla.getPathArch(), xmlDatosDoc);
		//map.put("SUBREPORT_DIR", "C:/BPMprocess/Reps/angular/reportes/");
		
		File archivoJrxml = new File(archivo);
		
		//logger.info(map.toString());
		try {
			if(archivoJrxml.exists()) {
				JasperReport report = JasperCompileManager.compileReport(archivo);
				JasperPrint print = JasperFillManager.fillReport(report, map);
				//JasperExportManager.exportReportToPdfFile(print, plantilla.getPathArch()+plantilla.getpNombreArch()+plantilla.getpExtArch());
				 JRPdfExporter exporter = new JRPdfExporter();
				 exporter.setExporterInput(new SimpleExporterInput(print));
			     exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(plantilla.getPathArch()+plantilla.getpNombreArch()+plantilla.getpExtArch()));
			     SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			     configuration.setMetadataAuthor("BPMProcess");
			     exporter.setConfiguration(configuration);
			     exporter.exportReport();
				if(plantilla.getpAnexar() == 1) {
					 int res = motor.p4bAnexarDocumento(0, plantilla.getPathArch()+plantilla.getpNombreArch()+plantilla.getpExtArch(), "", plantilla.getpDescripcion(), "", "");
					 logger.info("respuesta p4bAnexar "+res);
				}else {
					logger.info("No se anexa archivo");
				}		    
			
			}else {
				logger.info("El archivo JRXML No ha sido creado");
			}
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			logger.error("pdfPrueba", e);
			return false;
		}
		
		return true;
	}
	
	private Map<String, Object>  jsonToJasper(String nombreForm, String wfa, String ruta, String docForm){
		Map<String, Object> map = new HashMap<String, Object>(); //variable para pasar metodos
		
		//logger.info("xml atributo "+xmlDatosDoc);
		String nombreArchivoJson = nombreForm.replace(" ", "_")+"_"+wfa;
		
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
	        Document document = documentBuilder.parse(new InputSource(new StringReader(docForm))); //cargar documento de process
			JAXBContext context = JAXBContextFactory.createContext(new Class[] {Formas.class}, null);
			Unmarshaller un = context.createUnmarshaller();
			Formas formularios = (Formas) un.unmarshal(document);
			
			JsonObjectBuilder json = Json.createObjectBuilder();
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
								if(campos.get(j).getOpciones().getMultiple()){
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
								valorPA = valorPA.substring(0, valorPA.length() - 2);
								json.add(campos.get(j).getNombre(), valorPA);
							}else{//campo normal
								json.add(campos.get(j).getNombre(), campos.get(j).getValue());
							} 
					}
				}
				
			}
			
			
			InputStream is =new ByteArrayInputStream(json.build().toString().getBytes("UTF-8"));
			map.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, is);
	        //logger.info("Json "+json.build().toString());
	        jsonToFile(json, nombreArchivoJson, ruta);
		}catch(Exception e){
			logger.error("mapeoVariablesJrxml error "+e.getMessage()+" codigo "+e.getLocalizedMessage());
		}
		
		return map;
	}
	
	private Boolean jsonToFile(JsonObjectBuilder json, String nombre, String ruta) {
		
		 	File archivo = new File(ruta+"/json/"+nombre+".json");
	        BufferedWriter bw;
	        
	        
	        try {
	        	if(archivo.exists()) {
		        	bw = new BufferedWriter(new FileWriter(archivo));
		            
		            bw.write(json.build().toString());
		        } else {
		            bw = new BufferedWriter(new FileWriter(archivo));
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
	
}
