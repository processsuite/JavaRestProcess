package com.process.services.rest.dataServices;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.document.DocumentManager;
import com.process.domain.document.RespDataService;

@Path("/dataServices")
public class DataServicesResource {

    private static final Logger logger = Logger.getLogger(DataServicesResource.class);

    @Autowired
	private DocumentManager documentManager;
 	/**
	 * Consulme servicio de datos desde ajax, consulta a un dataSourse
	 * <p>
	 * 
	 * url: GET http://localhost:9090/process/api/document/dataServices
	 * 
	 * @return List<Agent>
	 */
	@POST
	public Response dataServicesAjax(@QueryParam("ambiente") String ambiente, @QueryParam("idQuery") String idQuery, Object[][] param ) {
		Response response = null;
		try {			
			RespDataService responseService = documentManager.dataServices(ambiente, idQuery, param);			
			GenericEntity<RespDataService> entity = new GenericEntity<RespDataService>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("dataServicesAjax", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
}
