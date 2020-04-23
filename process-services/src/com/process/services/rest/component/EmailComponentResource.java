package com.process.services.rest.component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.component.ComponentEmailManager;
import com.process.domain.component.Email;



@Path("/email")
public class EmailComponentResource {
	private static final Logger logger = Logger.getLogger(EmailComponentResource.class);
	
	@Autowired
	private ComponentEmailManager componentEmailManager;
	
	@POST
	public Response sendMail(Email email){
		Response response = null;
		try {			
			Boolean responseService = componentEmailManager.sendMail(email);			
			GenericEntity<Boolean> entity = new GenericEntity<Boolean>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("sendMail", e);
		    return Response.serverError().build();
		}
		return response;
	}
}
