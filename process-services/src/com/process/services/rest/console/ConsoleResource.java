package com.process.services.rest.console;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.console.ConsoleManager;
import com.process.domain.console.Registro;



@Path("/console")
public class ConsoleResource {
	private static final Logger logger = Logger.getLogger(ConsoleResource.class);
	
	@Autowired
	private ConsoleManager consoleManager;
	
	@GET
	public Response getDatos(@QueryParam("ambiente") String ambiente){
		Response response = null;
		try {			
			//logManager.initLog(ambiente);
			Registro responseService = consoleManager.obtenerDatosConsole(ambiente);		
			GenericEntity<Registro> entity = new GenericEntity<Registro>(responseService) {};
			//String responseService = ambiente;
			//GenericEntity<String> entity = new GenericEntity<String>(responseService) {};
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("getDatos", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	@PUT
	@Path("/update")
	public Response putConsole(@QueryParam("ambiente") String ambiente,@QueryParam("nameObj") String nameObj, Registro registro){
		Response response = null;
		try {			
			//logManager.initLog(ambiente);
			Boolean responseService = consoleManager.modificarObjetoConsole(ambiente, registro, nameObj);		
			GenericEntity<Boolean> entity = new GenericEntity<Boolean>(responseService) {};
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("getDatos", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	@POST
	@Path("/migrateAmbiente")
	public Response postMigrateA(@QueryParam("ambiente") String ambiente){
		Response response = null;
		try {		
			Boolean responseService = consoleManager.migradorAmbientRegToXml(ambiente);
			GenericEntity<Boolean> entity = new GenericEntity<Boolean>(responseService) {};
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("migrateAmbiente", e);
		    return Response.serverError().build();
		}
		return response;
	}
}
