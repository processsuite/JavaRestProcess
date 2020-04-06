/**
 * FileResource.java 
 *
 */
package com.process.services.rest.user;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.report.ReportManager;
import com.process.domain.report.Report;


/**
 * Resource Colaborar REST for user module
 * @author  Oswel Sanchez
 *    
 */
@Path("/colaborar")
public class ColaborarResource {
    
    private static final Logger logger = Logger.getLogger(ColaborarResource.class);

    @Autowired
	private ReportManager reportManager;
    
	/**
	 * Obtiene las consultas de colaborar(cartelera) para un usuario Process 
	 * <p>
	 * url: GET http://localhost:9090/process/api/colaborar
	 * 
	 * @return List<Report> Object
	 */
	@GET
	public Response obtenerConsultasColaborar() {
		Response response = null;
		try {
			List<String> tipos = new ArrayList<String>();
			tipos.add("C");
			reportManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			List<Report> responseService = reportManager.obtenerConsultas(tipos);			
			GenericEntity<List<Report>> entity = new GenericEntity<List<Report>>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerConsultasColaborar", e);
		    return Response.serverError().build();
		}
		return response;
	}

}
