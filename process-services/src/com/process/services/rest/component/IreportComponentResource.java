package com.process.services.rest.component;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.process.business.component.ComponentIreportManager;


@Path("/ireport")
public class IreportComponentResource {
	private static final Logger logger = Logger.getLogger(IreportComponentResource.class);
	
	@Autowired
	private ComponentIreportManager cim;
	
	@POST
	@Path("/generarIreport")
	public Response generarIreport(@QueryParam("nomArch") String nomArch, @QueryParam("ruta") String ruta, Object[][] arreglo){
		Response response = null;
		try {			
			String responseService = cim.pdfGenerico(nomArch, ruta, arreglo);			
			GenericEntity<String> entity = new GenericEntity<String>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("sendMail", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	@POST
	@Path("/generarIreportQuery")
	public Response generarIreportQuery(@QueryParam("nomArch") String nomArch, @QueryParam("destino") String destino,@QueryParam("ambiente") String ambiente, Object[][] arreglo){
		Response response = null;
		try {			
			String responseService = cim.pdfGenericoQuery(nomArch, destino, arreglo, ambiente);			
			GenericEntity<String> entity = new GenericEntity<String>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("generarIreportQuery", e);
		    return Response.serverError().build();
		}
		return response;
	}
}
