package com.process.business.plantilla.impl;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.process.business.environment.EnvironmentManager;
import com.process.business.helper.ClassFactory;
import com.process.business.helper.c_Process;
import com.process.business.plantilla.GenerarPlantilla;
import com.process.domain.generadordocument.Plantilla;
import com.process.domain.report.FieldReport;



@Service("generarplantilla")
public class SimpleGenerarPlantilla implements GenerarPlantilla {

	private static final Logger logger = Logger.getLogger(SimpleGenerarPlantilla.class);
	
	private c_Process motor;
	
	private Integer engineP;
	
	@Autowired
	private EnvironmentManager environmentManager;		
	
	@Override
	public void setEngineId(Integer engineId) {
		engineP = engineId;
	}
	
	@Override
	public synchronized String generarArchivoDoc(Plantilla plantillaPj){

		String resp = "";
		try{
			motor = ClassFactory.getProcess(engineP);
			resp = motor.p4bGenerarReporte(plantillaPj.getPathArch(), plantillaPj.getpNombreArch(), plantillaPj.getpExtArch(), plantillaPj.getpDescripcion(), plantillaPj.getpAnexar(), plantillaPj.getpDelimitador(), plantillaPj.getpConsulta(), plantillaPj.getpCampoInd(), "");	
		}catch(Exception e){
			logger.error("generarArchivo:", e);
		}
		return resp;
	}
	
	@Override
	public synchronized String generarArchivoConsulta(Integer wfPadre, Integer wfHijo, Integer tipo, Integer desde, String campoOrden, List<FieldReport> camposBuscar, String rutaAgentes){
		
		String respuesta = "";
		
		try{
			motor = ClassFactory.getProcess(engineP);
			//logger.info(getXmlParam(wfPadre, wfHijo, tipo, desde, campoOrden, camposBuscar));
			respuesta = motor.p4bGenerarReporte(rutaAgentes, wfHijo.toString(), ".xls", "", 0, "", "", "", getXmlParam(wfPadre, wfHijo, tipo, desde, campoOrden, camposBuscar));	
		}catch(Exception e){
			logger.error("generarArchivo:", e);
		}		
		return respuesta;
	}
	
	
	
	
	
	
	private String getXmlParam(Integer wfPadre, Integer wfHijo, Integer tipo, Integer desde, String campoOrden, List<FieldReport> camposBuscar){
		
		//wl_wfc="(Número proceso padre)" wl_wfac="(Número proceso)" wl_tipoopcion="(Número de opción)" wl_orden="(Nombre columna ordenamiento)"
		
		String xml = "<?xml version='1.0' encoding='ISO-8859-1'?>";
		xml += "<camposBuscar wl_wfc=\""+wfPadre+"\" wl_wfac=\""+wfHijo+"\"  wl_tipoopcion=\""+tipo+"\" wl_orden=\""+campoOrden+"\">";
		Integer i = 0;
		for(FieldReport field:camposBuscar){
			xml += "<campo>";
			xml += "<campoBD>" + field.getCampoBd() + "</campoBD>";
			xml += "<descripcion>" + "<![CDATA[" + field.getDescripcion() + "]]>" + "</descripcion>";						
			xml += "<valor>" + "<![CDATA[" + field.getValor() + "]]>" + "</valor>";
			xml += "<pos>"+(i)+"</pos>";									
			xml += "</campo>";	
			i++;
		}
		xml += "</camposBuscar>";
		return xml;
	}

}
