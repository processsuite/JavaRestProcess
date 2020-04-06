/**
 * EnvironmentResource.java 
 *
 */
package com.process.services.rest.environment;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.environment.EnvironmentManager;
import com.process.domain.environment.Environment;


/**
 * Resource Enviroment REST for Environment module
 * @author  Oswel Sanchez
 *    
 */
@Path("/environments")
public class EnvironmentResource {
    
    private static final Logger logger = Logger.getLogger(EnvironmentResource.class);

    @Autowired
	private EnvironmentManager environmentManager;
    
	/**
	 * Obtiene lista de Ambientes
	 * <p>
	 * url: GET http://localhost:9090/process/api/environments
	 * 
	 * @return List<String>
	 */
	@GET
	public Response getEnvironments() {
		Response response = null;
		try {			
			List<Environment> responseService = environmentManager.getEnvironments();			
			GenericEntity<List<Environment>> entity = new GenericEntity<List<Environment>>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("getEnvironments", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	/**
	 * Obtiene informacion de un ambiente
	 * @param name nombre del ambiente
	 * <p>
	 * url: GET http://localhost:9090/process/api/environments/load?name={name}
	 * 
	 * @return Environment
	 */
	@GET
	@Path("/load")
	
	public Response getEnvironment(@QueryParam("name") String name) {
		Response response = null;
		try {			
			Environment responseService = environmentManager.getEnvironment(name);			
			GenericEntity<Environment> entity = new GenericEntity<Environment>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("getEnvironment", e);
		    return Response.serverError().build();
		}
		return response;
	}	

}
