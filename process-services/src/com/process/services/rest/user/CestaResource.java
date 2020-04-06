/**
 * CestaResource.java 
 *
 */
package com.process.services.rest.user;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.user.CestaManager;
import com.process.domain.user.Cesta;
import com.process.domain.user.CestaAnexo;
import com.process.domain.user.Inst;
import com.process.domain.user.Proceso;
import com.process.domain.user.RelatedSeg;
import com.process.domain.user.Seguimiento;


/**
 * Resource Cesta REST for user module
 * @author  Oswel Sanchez
 *    
 */
@Path("/cesta")
public class CestaResource {
    
    private static final Logger logger = Logger.getLogger(CestaResource.class);

    @Autowired
	private CestaManager cestaManager;
    
	/**
	 * Obtiene la cesta de un usuario Process 
	 * <p>
	 * url: GET http://localhost:9090/process/api/cesta?tipo={tipo}&desde={desde}&nudoc={nuDoc}&wfp={wfp}&wfa={wfa}&feini={feIni}&fefin={feFin}&detalle={detalle}
	 * 
	 * @param tipo
	 * @param desde
	 * @param nuDoc
	 * @param wfp
	 * @param wfa
	 * @param feIni
	 * @param feFin
	 * @param detalle	  
	 * @return Cesta Object
	 */
	@GET
	public Response obtenerCesta(@QueryParam("tipo") Integer tipo,
								 @QueryParam("desde") Integer desde,
								 @QueryParam("nudoc") Integer nuDoc,
								 @QueryParam("wfp") Integer wfp,
								 @QueryParam("wfa") Integer wfa,
								 @QueryParam("feini") String feIni,
								 @QueryParam("fefin") String feFin,
								 @QueryParam("detalle") String detalle) {
		Response response = null;
		try {
			if (tipo==null) tipo = 9;
			if (desde==null) desde = 0;
			if (nuDoc==null) nuDoc = 0;
			if (wfp==null) wfp = 0;
			if (wfa==null) wfa = 0;
			if (feIni==null) feIni = "";
			if (feFin==null) feFin = "";
			if (detalle==null) detalle = "";
			cestaManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Cesta responseService = cestaManager.obtenerCesta(tipo, desde, nuDoc, wfp, wfa, feIni, feFin, detalle);			
			GenericEntity<Cesta> entity = new GenericEntity<Cesta>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerCesta", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	/**
	 * Obtiene la cesta de vencidos de un usuario Process 
	 * <p>
	 * url: GET http://localhost:9090/process/api/cesta/vencidos?tipo={tipo}&desde={desde}&nudoc={nuDoc}&wfp={wfp}&wfa={wfa}&feini={feIni}&fefin={feFin}&detalle={detalle}
	 * 
	 * @param tipo
	 * @param desde
	 * @param nuDoc
	 * @param wfp
	 * @param wfa
	 * @param feIni
	 * @param feFin
	 * @param detalle	  
	 * @return List<Inst> Object
	 */
	@GET
	@Path("/vencidos")
	public Response obtenerCestaVencidos(@QueryParam("tipo") Integer tipo,
								 @QueryParam("desde") Integer desde,
								 @QueryParam("nudoc") Integer nuDoc,
								 @QueryParam("wfp") Integer wfp,
								 @QueryParam("wfa") Integer wfa,
								 @QueryParam("feini") String feIni,
								 @QueryParam("fefin") String feFin,
								 @QueryParam("detalle") String detalle) {
		Response response = null;
		try {
			if (tipo==null) tipo = 9;
			if (desde==null) desde = 0;
			if (nuDoc==null) nuDoc = 0;
			if (wfp==null) wfp = 0;
			if (wfa==null) wfa = 0;
			if (feIni==null) feIni = "";
			if (feFin==null) feFin = "";
			if (detalle==null) detalle = "";			
			List<Inst> filterList = new ArrayList<Inst>();
			cestaManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Cesta responseService = cestaManager.obtenerCesta(tipo, desde, nuDoc, wfp, wfa, feIni, feFin, detalle);
			for(Inst i:responseService.getInsts()){
				if (i.getSemaforo() != 2){
					filterList.add(i);
				}
			}
			responseService.getInsts().removeAll(filterList);
			GenericEntity<Cesta> entity = new GenericEntity<Cesta>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerCestaVencidos", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Obtiene la cesta en riesgo de un usuario Process 
	 * <p>
	 * url: GET http://localhost:9090/process/api/cesta/riesgo?tipo={tipo}&desde={desde}&nudoc={nuDoc}&wfp={wfp}&wfa={wfa}&feini={feIni}&fefin={feFin}&detalle={detalle}
	 * 
	 * @param tipo
	 * @param desde
	 * @param nuDoc
	 * @param wfp
	 * @param wfa
	 * @param feIni
	 * @param feFin
	 * @param detalle	  
	 * @return List<Inst> Object
	 */
	@GET
	@Path("/riesgo")
	public Response obtenerCestaRiesgo(@QueryParam("tipo") Integer tipo,
								 @QueryParam("desde") Integer desde,
								 @QueryParam("nudoc") Integer nuDoc,
								 @QueryParam("wfp") Integer wfp,
								 @QueryParam("wfa") Integer wfa,
								 @QueryParam("feini") String feIni,
								 @QueryParam("fefin") String feFin,
								 @QueryParam("detalle") String detalle) {
		Response response = null;
		try {
			if (tipo==null) tipo = 9;
			if (desde==null) desde = 0;
			if (nuDoc==null) nuDoc = 0;
			if (wfp==null) wfp = 0;
			if (wfa==null) wfa = 0;
			if (feIni==null) feIni = "";
			if (feFin==null) feFin = "";
			if (detalle==null) detalle = "";
			List<Inst> filterList = new ArrayList<Inst>();	
			cestaManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Cesta responseService = cestaManager.obtenerCesta(tipo, desde, nuDoc, wfp, wfa, feIni, feFin, detalle);
			for(Inst i:responseService.getInsts()){
				if (i.getSemaforo() != 1){
					filterList.add(i);
				}
			}
			responseService.getInsts().removeAll(filterList);
			GenericEntity<Cesta> entity = new GenericEntity<Cesta>(responseService) {};				
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerCestaRiesgo", e);
		    return Response.serverError().build();
		}
		return response;
	}		

	/**
	 * Obtiene la cesta vigente de un usuario Process 
	 * <p>
	 * url: GET http://localhost:9090/process/api/cesta/vigente?tipo={tipo}&desde={desde}&nudoc={nuDoc}&wfp={wfp}&wfa={wfa}&feini={feIni}&fefin={feFin}&detalle={detalle}
	 * 
	 * @param tipo
	 * @param desde
	 * @param nuDoc
	 * @param wfp
	 * @param wfa
	 * @param feIni
	 * @param feFin
	 * @param detalle	  
	 * @return List<Inst> Object
	 */
	@GET
	@Path("/vigente")
	public Response obtenerCestaVigente(@QueryParam("tipo") Integer tipo,
								 @QueryParam("desde") Integer desde,
								 @QueryParam("nudoc") Integer nuDoc,
								 @QueryParam("wfp") Integer wfp,
								 @QueryParam("wfa") Integer wfa,
								 @QueryParam("feini") String feIni,
								 @QueryParam("fefin") String feFin,
								 @QueryParam("detalle") String detalle) {
		Response response = null;
		try {
			if (tipo==null) tipo = 9;
			if (desde==null) desde = 0;
			if (nuDoc==null) nuDoc = 0;
			if (wfp==null) wfp = 0;
			if (wfa==null) wfa = 0;
			if (feIni==null) feIni = "";
			if (feFin==null) feFin = "";
			if (detalle==null) detalle = "";
			List<Inst> filterList = new ArrayList<Inst>();	
			cestaManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Cesta responseService = cestaManager.obtenerCesta(tipo, desde, nuDoc, wfp, wfa, feIni, feFin, detalle);
			for(Inst i:responseService.getInsts()){
				if (i.getSemaforo() != 0){
					filterList.add(i);
				}
			}
			responseService.getInsts().removeAll(filterList);
			GenericEntity<Cesta> entity = new GenericEntity<Cesta>(responseService) {};	
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerCestaVigente", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Obtiene la cesta de borradores de un usuario Process 
	 * <p>
	 * url: GET http://localhost:9090/process/api/cesta/borrador?tipo={tipo}&desde={desde}&nudoc={nuDoc}&wfp={wfp}&wfa={wfa}&feini={feIni}&fefin={feFin}&detalle={detalle}
	 * 
	 * @param tipo
	 * @param desde
	 * @param nuDoc
	 * @param wfp
	 * @param wfa
	 * @param feIni
	 * @param feFin
	 * @param detalle	  
	 * @return List<Inst> Object
	 */
	@GET
	@Path("/borrador")
	public Response obtenerCestaBorrador(@QueryParam("tipo") Integer tipo,
								 @QueryParam("desde") Integer desde,
								 @QueryParam("nudoc") Integer nuDoc,
								 @QueryParam("wfp") Integer wfp,
								 @QueryParam("wfa") Integer wfa,
								 @QueryParam("feini") String feIni,
								 @QueryParam("fefin") String feFin,
								 @QueryParam("detalle") String detalle) {
		Response response = null;
		try {
			if (tipo==null) tipo = 9;
			if (desde==null) desde = 0;
			if (nuDoc==null) nuDoc = 0;
			if (wfp==null) wfp = 0;
			if (wfa==null) wfa = 0;
			if (feIni==null) feIni = "";
			if (feFin==null) feFin = "";
			if (detalle==null) detalle = "";
			List<Inst> filterList = new ArrayList<Inst>();	
			cestaManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Cesta responseService = cestaManager.obtenerCesta(tipo, desde, nuDoc, wfp, wfa, feIni, feFin, detalle);
			for(Inst i:responseService.getInsts()){
				if ((!i.getE().equals("I")) && (!i.getEp().equals("I"))){
					filterList.add(i);
				}
			}
			responseService.getInsts().removeAll(filterList);
			GenericEntity<Cesta> entity = new GenericEntity<Cesta>(responseService) {};	
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerCestaBorrador", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Obtiene el segumiento de un documento Process
	 * <p>
	 * url: GET http://localhost:9090/process/api/cesta/seguimiento?tipo={tipo}&nudoc={nuDoc}
	 * 
	 * @param tipo
	 * @param nuDoc
	 * @return Seguimiento Object
	 */
	@GET
	@Path("/seguimiento")	
	public Response obtenerSeguimiento(@QueryParam("tipo") Integer tipo,
			 					       @QueryParam("nudoc") Integer nuDoc){
		Response response = null;
		try {
			if (tipo==null) tipo = 1;
			if (nuDoc==null) nuDoc = 0;
			cestaManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
		    Seguimiento responseService = cestaManager.obtenerSeguimiento(tipo, nuDoc);
			GenericEntity<Seguimiento> entity = new GenericEntity<Seguimiento>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerSeguimiento", e);
		    return Response.serverError().build();
		}
		return response;		
	}
	
	/**
	 * Obtiene el segumiento de un documento Process para Gantt
	 * <p>
	 * url: GET http://localhost:9090/process/api/cesta/seguimientogantt?tipo={tipo}&nudoc={nuDoc}
	 * 
	 * @param tipo
	 * @param nuDoc
	 * @return Seguimiento Object
	 */
	@GET
	@Path("/seguimientogantt")	
	public Response obtenerSeguimientoGantt(@QueryParam("tipo") Integer tipo,
			 					            @QueryParam("nudoc") Integer nuDoc){
		Response response = null;
		try {
			if (tipo==null) tipo = 1;
			if (nuDoc==null) nuDoc = 0;
			cestaManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
		    Seguimiento responseService = cestaManager.obtenerSeguimientoGantt(tipo, nuDoc);
			GenericEntity<Seguimiento> entity = new GenericEntity<Seguimiento>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerSeguimientoGantt", e);
		    return Response.serverError().build();
		}
		return response;		
	}	
	
	/**
	 * Obtiene las tareas por ejecutar en el seguimiento 
	 * <p>
	 * url: GET http://localhost:9090/process/api/cesta/seguimiento/porejecutar?wfp={wfp}&nudoc={nuDoc}
	 * 
	 * @param wfp 
	 * @param nuDoc
	 * @return List<String> Object
	 */
	@GET
	@Path("/seguimiento/porejecutar")	
	public Response obtenerActividadesPorEjecutar(@QueryParam("wfp") Integer wfp,
								                  @QueryParam("nudoc") Integer nuDoc) {
		Response response = null;
		try {
			if (wfp==null) wfp = 0;
			if (nuDoc==null) nuDoc = 0;
			cestaManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
		    List<String> responseService = cestaManager.obtenerActividadesPorEjecutar(wfp, nuDoc);
			GenericEntity<List<String>> entity = new GenericEntity<List<String>>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerActividadesPorEjecutar", e);
		    return Response.serverError().build();
		}		
		return response;
	}
	
	/**
	 * Obtiene los anexos de un documento 
	 * <p>
	 * url: GET http://localhost:9090/process/api/cesta/seguimiento/anexos?nudoc={nuDoc}
	 * 
	 * @param nuDoc
	 * @return List<String> Object
	 */
	@GET
	@Path("/seguimiento/anexos")	
	public Response obtenerAnexos(@QueryParam("nudoc") Integer nuDoc) {
		Response response = null;
		try {
			if (nuDoc==null) nuDoc = 0;
			cestaManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
		    List<CestaAnexo> responseService = cestaManager.obtenerAnexos(nuDoc);
			GenericEntity<List<CestaAnexo>> entity = new GenericEntity<List<CestaAnexo>>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerAnexos", e);
		    return Response.serverError().build();
		}		
		return response;
	}	
	
	/**
	 * Obtiene los procesos en los cuales el usuario tiene tareas segun el tipo 
	 * <p>
	 * url: GET http://localhost:9090/process/api/cesta/clasificacion?numcla={numCla}
	 * 
	 * @param numCla
	 * @return List<String> Object
	 */
	@GET
	@Path("/clasificacion")	
	public Response obtenerClasificacion(@QueryParam("numcla") Integer numCla) {
		Response response = null;
		try {
			if (numCla==null) numCla = 0;
			cestaManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
		    List<Proceso> responseService = cestaManager.obtenerClasificacion(numCla);
			GenericEntity<List<Proceso>> entity = new GenericEntity<List<Proceso>>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerClasificacion", e);
		    return Response.serverError().build();
		}		
		return response;
	}	
	
	/**
	 * Obtiene los documentos relacionados para un documento en el seguimiento 
	 * <p>
	 * url: GET http://localhost:9090/process/api/cesta/seguimiento/relacionados?nudoc={nuDoc}
	 * 
	 * @param nuDoc
	 * @return RelatedSeg Object
	 */
	@GET
	@Path("/seguimiento/relacionados")	
	public Response obtenerDocumentosRelacionados(@QueryParam("nudoc") Integer nuDoc) {
		Response response = null;
		try {
			if (nuDoc==null) nuDoc = 0;
			cestaManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			RelatedSeg responseService = cestaManager.obtenerDocumentosRelacionados(nuDoc);
			GenericEntity<RelatedSeg> entity = new GenericEntity<RelatedSeg>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerDocumentosRelacionados", e);
		    return Response.serverError().build();
		}		
		return response;
	}	
}
