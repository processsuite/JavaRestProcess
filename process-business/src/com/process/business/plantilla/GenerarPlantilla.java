package com.process.business.plantilla;

import java.util.List;

import com.process.domain.auth.Engine;
import com.process.domain.generadordocument.Plantilla;
import com.process.domain.report.FieldReport;

public interface GenerarPlantilla extends  Engine{

	public String generarArchivoDoc(Plantilla plantillaPj);
	
	public String generarArchivoConsulta(Integer wfPadre, Integer wfHijo, Integer tipo, Integer desde, String campoOrden, List<FieldReport> camposBuscar, String rutaAgentes);
	 
}
