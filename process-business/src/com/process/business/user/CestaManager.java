/**
 * CestaManager.java 
 * 
 */
package com.process.business.user;

import java.util.List;

import com.process.domain.auth.Engine;
import com.process.domain.user.Cesta;
import com.process.domain.user.CestaAnexo;
import com.process.domain.user.Proceso;
import com.process.domain.user.RelatedSeg;
import com.process.domain.user.Seguimiento;
import com.process.domain.user1.Cesta1;

/**
 * Business Interface CestaManager for user Module 
 * @author Oswel Sanchez
 * 
 */
public interface CestaManager extends Engine  {

	/**
	 * Obtiene la cesta de un usuario Process
	 * @param tipo
	 * @param desde
	 * @param nuDoc
	 * @param wfp
	 * @param wfa
	 * @param feIni
	 * @param feFin
	 * @param detalle
	 * @return Cesta
	 */
	public Cesta obtenerCesta(Integer tipo, Integer desde, Integer nuDoc, Integer wfp, Integer wfa, String feIni, String feFin, String detalle);
	
	public Cesta1 obtenerCesta1(Integer tipo, Integer desde, Integer nuDoc, Integer wfp, Integer wfa, String feIni, String feFin, String detalle);
	/**
	 * Obtiene el segumiento de un documento Process
	 * @param tipo (1-Transito, 9-Historico)
	 * @param nuDoc
	 * @return
	 */
	public Seguimiento obtenerSeguimiento(Integer tipo, Integer nuDoc);  
	
	/**
	 * Obtiene el segumiento de un documento Process para Gantt
	 * @param tipo (2-Transito, 10-Historico)
	 * @param nuDoc
	 * @return
	 */
	public Seguimiento obtenerSeguimientoGantt(Integer tipo, Integer nuDoc); 	
	
	/**
	 * Obtiene las tareas por ejecutar en el seguimiento
	 * @param wfp
	 * @param nuDoc  
	 * @return
	 */
	public List<String> obtenerActividadesPorEjecutar(Integer wfp, Integer nudoc);
	
	/**
	 * Obtiene los anexos de un documento
	 * @param nuDoc
	 * @return List<CestaAnexo>
	 */
	public List<CestaAnexo> obtenerAnexos(Integer nuDoc);
	
	/**
	 * Obtiene los procesos en los cuales el usuario tiene tareas segun el tipo  
	 * @param numCla 
	 * @return
	 */
	public List<Proceso> obtenerClasificacion(Integer numCla);
	
	/**
	 * Obtiene los documentos relacionados para un documento en el seguimiento
	 * @param nuDoc
	 * @return RelatedSeg
	 */
	public RelatedSeg obtenerDocumentosRelacionados(Integer nuDoc);	
}
