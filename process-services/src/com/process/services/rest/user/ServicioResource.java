/**
 * ServicioResource.java 
 *
 */
package com.process.services.rest.user;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.user.ServicioManager;
import com.process.domain.user.Servicio;


/**
 * Resource Servicio REST for user module
 * @author  Oswel Sanchez
 *    
 */
@Path("/service")
public class ServicioResource {
    
    private static final Logger logger = Logger.getLogger(ServicioResource.class);

    @Autowired
	private ServicioManager servicioManager;
    
	/**
	 * Obtiene los servicios para un usuario Process 
	 * <p>
	 * url: GET http://localhost:9090/process/api/service
	 * 
	 * @return List<Servicio> Object
	 */
	@GET
	public synchronized Response obtenerServicios() {
		Response response = null;
		try {			
			servicioManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			List<Servicio> responseService = servicioManager.obtenerServicios();			
			GenericEntity<List<Servicio>> entity = new GenericEntity<List<Servicio>>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerServicios", e);
		    return Response.serverError().build();
		}
		return response;
	}

}
