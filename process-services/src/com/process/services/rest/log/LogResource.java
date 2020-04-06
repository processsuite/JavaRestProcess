package com.process.services.rest.log;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.log.LogManager;
import com.process.domain.log.ListFechaUsaurio;
import com.process.domain.log.ObjL;



@Path("/logs")
public class LogResource {
	
	private static final Logger logger = Logger.getLogger(LogResource.class);
	
	@Autowired
	private LogManager logManager;
	 
	 
	@GET
	public Response getDatos(@QueryParam("ambiente") String ambiente){
		
		Response response = null;
		try {			
			List<ListFechaUsaurio> responseService = logManager.initLog(ambiente);		
			GenericEntity<List<ListFechaUsaurio>> entity = new GenericEntity<List<ListFechaUsaurio>>(responseService) {};
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("getDatos", e);
		    return Response.serverError().build();
		}
		return response;
		
	}
	
	@GET
	@Path("/getTraza")
	public Response getTraza(@QueryParam("ambiente") String ambiente,@QueryParam("fecha") String fecha ,@QueryParam("usuario") String usuario){
		
		Response response = null;
		try {			
			ObjL responseService = logManager.obtenerTraza(ambiente, fecha,usuario);
			GenericEntity<ObjL> entity = new GenericEntity<ObjL>(responseService) {};
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("getTraza", e);
		    return Response.serverError().build();
		}
		return response;
		
	}
	@GET
	@Path("/getTrazasql")
	public Response getTrazaSql(@QueryParam("ambiente") String ambiente,@QueryParam("fecha") String fecha ,@QueryParam("usuario") String usuario){
		
		Response response = null;
		try {			
			ObjL responseService = logManager.obtenerTrazaSql(ambiente, fecha,usuario);
			GenericEntity<ObjL> entity = new GenericEntity<ObjL>(responseService) {};
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("getTraza", e);
		    return Response.serverError().build();
		}
		return response;
		
	}
	@GET
	@Path("/getTrazaEmail")
	public Response getTrazaEmail(@QueryParam("ambiente") String ambiente,@QueryParam("fecha") String fecha ,@QueryParam("usuario") String usuario){
		
		Response response = null;
		try {			
			ObjL responseService = logManager.obtenerTrazaEmail(ambiente, fecha,usuario);
			GenericEntity<ObjL> entity = new GenericEntity<ObjL>(responseService) {};
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("getTraza", e);
		    return Response.serverError().build();
		}
		return response;
		
	}
	
	@GET
	@Path("/getTrazaRobot")
	public Response getTrazaRobot(@QueryParam("ambiente") String ambiente,@QueryParam("fecha") String fecha ,@QueryParam("usuario") String usuario){
		
		Response response = null;
		try {			
			ObjL responseService = logManager.obtenerTrazaRobot(ambiente, fecha,usuario);
			GenericEntity<ObjL> entity = new GenericEntity<ObjL>(responseService) {};
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("getTraza", e);
		    return Response.serverError().build();
		}
		return response;
		
	}
}
