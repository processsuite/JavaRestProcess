package com.process.business.component;

import java.util.Map;

import com.process.domain.generadordocument.Plantilla;

public interface ComponentIreportManager {

	String pdfGenerico(String nomArch, String ruta, Object[][] arreglo);

	String pdfGenericoQuery(String nomArch, String destino, Object[][] arreglo, String ambiente);

}
