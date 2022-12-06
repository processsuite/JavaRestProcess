package com.process.business.plantilla;

import java.util.List;

import com.process.domain.generadordocument.Plantilla;
import com.process.domain.report.FieldReport;

public interface GeneradorIreportManager {


	void setEngineId(Integer engineId);


	String ejecutarConsultaReport(Integer wfPadre, Integer wfHijo, Integer tipoOpcion, Integer desde,
			List<FieldReport> camposBuscar, String campoOrden, String rutaAgentes, String ext, String ambiente, String puesto);

	String ireportGenerator(String nombreForm, String wfa, Plantilla plantilla, String ambiente);

}
