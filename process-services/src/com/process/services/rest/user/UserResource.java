/**
 * UserResource.java 
 *
 */
package com.process.services.rest.user;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.helper.BasicAuth;
import com.process.business.user.UserManager;
import com.process.domain.user.User;



@Path("/user")
public class UserResource {
    
    private static final Logger logger = Logger.getLogger(UserResource.class);

    @Autowired
	private UserManager userManager;
    
	/**q
	 * Obtiene datos de usuario Process 
	 * <p>
	 * url: GET http://localhost:9090/process/api/user
	 * 
	 * @return User Object
	 * 	 */
	@GET
	public Response obtenerDatosUsuario() {
		Response response = null;
		try {
			userManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			User responseService = userManager.obtenerDatosUsuario();			
			GenericEntity<User> entity = new GenericEntity<User>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerDatosUsuario", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	/**
	 * Actualiza datos personales de un usuario Process 
	 * <p>
	 * url: PUT http://localhost:9090/process/api/user/updatedata?name={name}&email={email}
	 * 
	 * @param name nombre del usuario a cambiar
	 * @param email	correo del usuario a cambiar  
	 * @return User Object
	 */
	@PUT
	@Path("/updatedata")
	public Response actualizarDatosUsuario(@QueryParam("name") String name,
										   @QueryParam("apellido") String apellido,
									       @QueryParam("email") String email) {
		Response response = null;
		try {		
			userManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			logger.info("actualizarDatosUsuario resource "+apellido);
			User responseService = userManager.actualizarDatosUsuario(name, apellido, email);			
			GenericEntity<User> entity = new GenericEntity<User>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("actualizarDatosUsuario", e);
		    return Response.serverError().build();
		}
		return response;
	}	

	/**
	 * Actualiza aspectos de seguridad para un usuario Process  
	 * <p>
	 * url: PUT http://localhost:9090/process/api/user/updatesecurity?ctx={contexto}&clave1={clave1}&clave2={clave2}&clave3={clave3}
	 * 
	 * @param contexto el tipo de contexto a actualizar
	 * @param clave1
	 * @param clave2
	 * @param clave3 
	 * @return void
	 */
	@PUT
	@Path("/updatesecurity")
	public Response actualizarSeguridadUsuario(@QueryParam("ctx") Integer contexto,
									           @QueryParam("clave1") String clave1,
									           @QueryParam("clave2") String clave2,
									           @QueryParam("clave3") String clave3) {
		Response response = null;
		try {		
			clave1 = clave1!=null?BasicAuth.decodeString(clave1):clave1;
			clave2 = clave2!=null?BasicAuth.decodeString(clave2):clave2;
			clave3 = clave3!=null?BasicAuth.decodeString(clave3):clave3;
			userManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			userManager.actualizarSeguridadUsuario(contexto, clave1, clave2, clave3);			
			response = Response.ok().build();
		} catch (Exception e) {
		    logger.error("actualizarSeguridadUsuario", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	/**		
	 * Recupera clave para un usuario Process
	 * <p>
	 * url: POST http://localhost:9090/process/api/user/rc?email={email}&prg={pregunta}&resp={respuesta}
	 * 
	 * 
	 * @param email
	 * @param pregunta
	 * @param respuesta
	 * @return User Object
	 */
	@POST
	@Path("/rc")
	public Response recuperarClave(@QueryParam("email") String email,
								   @QueryParam("prg") String prg,
								   @QueryParam("resp") String resp) {
		Response response = null;
		try {	
			userManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			userManager.recuperarClave(email, prg, resp);			
			response = Response.ok().build();
		} catch (Exception e) {
		    logger.error("recuperarClave", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	
	/**		
	 * Validar preguntas de seguridad y correo luego de recuperar contraseña
	 * <p>
	 * url: POST http://localhost:9090/process/api/user/rc?email={email}&prg={pregunta}&resp={respuesta}
	 * 
	 * 
	 * @param email
	 * @param pregunta
	 * @param respuesta
	 * @return User Object
	 */
	@POST
	@Path("/vpe")
	public Response validarPreguntaEmail(@QueryParam("email") String email,
								   @QueryParam("prg") String prg,
								   @QueryParam("resp") String resp) {
		Response response = null;
		try {	
			userManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			userManager.validarRespuestaRecuperar(email, prg, resp);			
			response = Response.ok().build();
		} catch (Exception e) {
		    logger.error("validarRespuestaRecuperar", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
}
