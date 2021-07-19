/**
 * ReportResource.java 
 *
 */
package com.process.services.rest.user;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.report.ReportManager;
import com.process.domain.report.DataParamReport;
import com.process.domain.report.ParamReport;
import com.process.domain.report.Report;
import com.process.domain.report.ResultReport;



@Path("/report")
public class ReportResource {
    
    private static final Logger logger = Logger.getLogger(ReportResource.class);

    @Autowired
	private ReportManager reportManager;    
    
	/**
	 * Obtiene las consultas para un usuario Process 
	 * <p>
	 * url: GET http://localhost:9090/process/api/report
	 * 
	 * 	 * @return List<Report> Object
	 */
	@GET
	public synchronized Response obtenerConsultas() {
		Response response = null;
		try {
			List<String> tipos = new ArrayList<String>();
			tipos.add("E");
			tipos.add("G");
			reportManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			List<Report> responseService = reportManager.obtenerConsultas(tipos);			
			GenericEntity<List<Report>> entity = new GenericEntity<List<Report>>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerConsultas", e);
		    return Response.serverError().build();
		}
		return response;
	}

	/**
	 * Obtiene la definici���n los parametros para una consulta
	 * <p>
	 * url: GET http://localhost:9090/process/api/report/param?wfp={wfPadre}&wfh={wfHijo}
	 * 
	 * @return List<ParamReport> Object
	 * 
	 */
	@GET
	@Path("/param")
	public synchronized Response obtenerParametrosConsulta(@QueryParam("wfp") Integer wfPadre,
											  @QueryParam("wfh") Integer wfHijo) {
		Response response = null;
		try {
			reportManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			List<ParamReport> responseService = reportManager.obtenerParametrosConsulta(wfPadre, wfHijo);			
			GenericEntity<List<ParamReport>> entity = new GenericEntity<List<ParamReport>>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerParametrosConsulta", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Ejecuta una consulta Process especifica
	 * <p>
	 * url: POST http://localhost:9090/process/api/report/exe?wfp={wfPadre}&wfh={wfHijo}&tipo={tipo}&desde={desde}&order={campoOrden}
	 * 
	 * @return ResultReport Object
	 */
	@POST
	@Path("/exe")
	@Consumes({ MediaType.APPLICATION_JSON })
	public synchronized Response ejecutarConsulta(@QueryParam("wfp") Integer wfPadre,
									 @QueryParam("wfh") Integer wfHijo,
									 @QueryParam("tipo") Integer tipo,
									 @QueryParam("desde") Integer desde,
									 @QueryParam("order") String campoOrden,
									 @QueryParam("ambiente") String ambiente,
									 DataParamReport camposBuscar) {
		Response response = null;
		try {
			if (desde==null) desde = 1;
			if ((campoOrden==null)) campoOrden = "";
			
			reportManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			ResultReport responseService = reportManager.ejecutarConsulta(wfPadre, wfHijo, tipo, desde, camposBuscar.getCamposBuscar(), campoOrden, ambiente);			
			GenericEntity<ResultReport> entity = new GenericEntity<ResultReport>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("ejecutarConsulta", e);
		    return Response.serverError().build();
		}
		return response;
	}	
}
