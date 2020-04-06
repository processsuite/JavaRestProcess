/**
 * EmailResource.java 
 *
 */
package com.process.services.rest.user;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.user.EmailManager;
import com.process.domain.user.Email;


/**
 * Resource Email REST for user module
 * @author  Oswel Sanchez
 *    
 */
@Path("/email")
public class EmailResource {
    
    private static final Logger logger = Logger.getLogger(EmailResource.class);

    @Autowired
	private EmailManager emailManager;
    
	/**
	 * Obtiene todos los correos de un usuario Process
	 * <p>
	 * url: GET http://localhost:9090/process/api/email
	 * 
	 * @return List<Email> Object
	 */
	@GET
	public Response obtenerTodosCorreos() {
		Response response = null;
		try {
			emailManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			List<Email> responseService = emailManager.obtenerCorreos(0,0,9);			
			GenericEntity<List<Email>> entity = new GenericEntity<List<Email>>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerTodosCorreos", e);
		    return Response.serverError().build();
		}
		return response;
	}

	/**
	 * Obtiene el contenido de un correo dado su identificador
	 * <p>
	 * url: GET http://localhost:9090/process/api/email/{nuMessage}/content
	 * 
	 * @return String
	 */
	@GET
	@Path("/{nuMessage}/content")
	public Response obtenerContenidoCorreo(@PathParam("nuMessage") Integer nuMessage) {
		Response response = null;
		try {
			emailManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			String responseService = emailManager.obtenerContenidoCorreo(nuMessage);			
			GenericEntity<String> entity = new GenericEntity<String>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerContenidoCorreo", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Obtiene todos los correos de seguimiento de un documento
	 * <p>
	 * url: GET http://localhost:9090/process/api/email/seg?nudoc={nuDoc}&desde={desde}&opc={opcPropios}
	 * 
	 * @return List<Email> Object
	 */
	@GET
	@Path("/seg")
	public Response obtenerSegCorreos(@QueryParam("nudoc") Integer nuDoc,
			  						  @QueryParam("desde") Integer desde,
			  						  @QueryParam("opc") Integer opcPropios) {
		Response response = null;
		try {
			if (nuDoc==null) nuDoc = 0;
			if (desde==null) desde = 0;
			if (opcPropios==null) opcPropios = 9;	
			emailManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			List<Email> responseService = emailManager.obtenerCorreos(nuDoc,desde,opcPropios);			
			GenericEntity<List<Email>> entity = new GenericEntity<List<Email>>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerSegCorreos", e);
		    return Response.serverError().build();
		}
		return response;
	}	
}
