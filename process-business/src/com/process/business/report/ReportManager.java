/**
 * ReportManager.java 
 * 
 */
package com.process.business.report;

import java.util.List;

import com.process.domain.auth.Engine;
import com.process.domain.report.FieldReport;
import com.process.domain.report.ParamReport;
import com.process.domain.report.Report;
import com.process.domain.report.ResultReport;

/**
 * Business Interface ReportManager for report Module 
 * @author Oswel Sanchez
 * 
 */
public interface ReportManager extends Engine {

	/**
	 * Obtiene las consultas para un usuario Process
	 * @param tipos tipos de valores  para los consulta (E,V,G,C)
	 * @return List<Report>
	 */
	public List<Report> obtenerConsultas(List<String> tipos); 
	
	/**
	 * Obtiene la definici���n los parametros para una consulta
	 * @param wfPadre
	 * @param wfHijo
	 * @return List<ParamReport>
	 */
	public List<ParamReport> obtenerParametrosConsulta(Integer wfPadre, Integer wfHijo);
	
    
	/**
	 * Ejecuta una consulta Process espec���fica
	 * @param wfPadre
	 * @param wfHijo
	 * @param tipoOpcion
	 * @param desde
	 * @param camposBuscar
	 * @param campoOrden
	 * @return ResultReport
	 */
	public ResultReport ejecutarConsulta(Integer wfPadre, Integer wfHijo, Integer tipoOpcion, Integer desde, List<FieldReport> camposBuscar, String campoOrden);
}
