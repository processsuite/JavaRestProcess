package com.process.services.rest.user;
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

import com.process.business.plantilla.GeneradorIreportManager;
import com.process.business.plantilla.GenerarPlantilla;
import com.process.domain.generadordocument.Plantilla;
import com.process.domain.report.DataParamReport;

@Path("/generarReporte")
public class GenerarReporte {
	private static final Logger logger = Logger.getLogger(GenerarReporte.class);
	@Autowired
	private GenerarPlantilla gePlantilla;
	@Autowired
	private GeneradorIreportManager gi;
	/**
	 * Obtiene la cesta de un usuario Process 
	 * <p>
	 * url: post http://localhost:9090/process/api/generarReporte
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response generarArchivo(Plantilla plantilla){
		Response response = null;
		try {
			gePlantilla.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			String resp = gePlantilla.generarArchivoDoc(plantilla);
			GenericEntity<String> entity = new GenericEntity<String>(resp) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("generarArchivo", e);
		    return Response.serverError().build();
		}
		return response;		
	}
	
	@POST
	@Path("/consulta")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response generarArchivoReporte(@QueryParam("wfp") Integer wfPadre,
										 @QueryParam("wfh") Integer wfHijo,
										 @QueryParam("tipo") Integer tipo,
										 @QueryParam("desde") Integer desde,
										 @QueryParam("order") String campoOrden,
										 DataParamReport camposBuscar){
		Response response = null;
		try {
			gePlantilla.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			String resp = gePlantilla.generarArchivoConsulta(wfPadre, wfHijo, tipo, desde, campoOrden, camposBuscar.getCamposBuscar(), camposBuscar.getRutaAgent());
			GenericEntity<String> entity = new GenericEntity<String>(resp) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("generarArchivo", e);
		    return Response.serverError().build();
		}
		
		
		return response;
	}
	
	@POST
	@Path("/ireport")
	public Response prueba(@QueryParam("nombreForm") String nombreForm, @QueryParam("wfa") String wfa, Plantilla plantilla){
		Response response = null;
		try{
			gi.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			GenericEntity<String> entity = new GenericEntity<String>(gi.ireportGenerator(nombreForm,wfa,plantilla)) {};			
			response = Response.ok(entity).build();
		}catch (Exception e){
			logger.error("generarArchivo", e);
		    return Response.serverError().build();
		}
		return response;		
	}

}
