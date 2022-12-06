package com.process.business.component.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.process.business.component.ComponentIreportManager;
import com.process.business.environment.EnvironmentManager;
import com.process.business.environment.impl.SimpleEnvironmentManager;
import com.process.business.helper.ConexionBD;
import com.process.domain.environment.Environment;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Service("componentIreportManager")
public class SimpleComponentIreportManager implements ComponentIreportManager {
	
private static final Logger logger = Logger.getLogger(SimpleComponentIreportManager.class);

	@Override
	public String pdfGenerico(String nomArch, String ruta, Object[][] arreglo){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		for(int x = 0; x < arreglo.length; x++){
			map.put((String) arreglo[x][0],(String) arreglo[x][1]);
		}
		
		String archivo = ruta+nomArch+".jrxml";		
			
		String resp = "";
		
		String name = ruta+Integer.toString((int) (100000 * Math.random()))+".pdf";
		
		
		File archivoJrxml = new File(archivo);
		try {
			if(archivoJrxml.exists()) {
								
				JRPdfExporter exporter = new JRPdfExporter();
				JasperReport report = JasperCompileManager.compileReport(archivo);
				JasperPrint print = JasperFillManager.fillReport(report, map);	
				exporter.setExporterInput(new SimpleExporterInput(print));
			    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(name));
			    SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			    configuration.setMetadataAuthor("BPMProcess");
			    exporter.setConfiguration(configuration);
			    exporter.exportReport();
				resp = name;
			}else {
				logger.error("El archivo JRXML No ha sido creado, [No posee plantilla]");
				resp = "Plantilla no existe";
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("emailToPdf", e);
			resp = "Error revise log";
		}
		
		return resp;
	}
	
	@Override
	public String pdfGenericoQuery(String nomArch, String destino, Object[][] arreglo, String ambiente) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		for(int x = 0; x < arreglo.length; x++){
			map.put((String) arreglo[x][0],(String) arreglo[x][1]);
		}
		SimpleEnvironmentManager sem = new SimpleEnvironmentManager();	
		
		String ruta = sem.getDatoAmbiente(ambiente, "RepAgentes")+"\\";
		String archivo = ruta+nomArch+".jrxml";		
		logger.info("archivo "+archivo);
		String destinoArchivo = sem.getDatoAmbiente(ambiente, destino);

		String resp = Integer.toString((int) (100000 * Math.random()))+".pdf";
		String name = destinoArchivo+resp;
		
		
		
		File archivoJrxml = new File(archivo);
		try {
			if(archivoJrxml.exists()) {
								
				JRPdfExporter exporter = new JRPdfExporter();
				JasperReport report = JasperCompileManager.compileReport(archivo);
				
				ConexionBD cdb = new ConexionBD(ambiente.toUpperCase());
				Class.forName(cdb.getDriverJDBC());
				Connection conn = DriverManager.getConnection(cdb.getCadena(),cdb.getUser(),cdb.getClave());
				JasperPrint print = JasperFillManager.fillReport(report, map, conn);	
				exporter.setExporterInput(new SimpleExporterInput(print));
			    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(name));
			    SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			    configuration.setMetadataAuthor("BPMProcess");
			    exporter.setConfiguration(configuration);
			    exporter.exportReport();
				//resp = name;
			}else {
				logger.error("El archivo JRXML No ha sido creado, [No posee plantilla]");
				resp = "Plantilla no existe";
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("pdfGenericoQuery", e);
			resp = "Error revise log";
		}
		
		return resp;
	}

}
