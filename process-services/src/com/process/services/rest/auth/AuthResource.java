/**
 * AuthResource.java 
 *
 */
package com.process.services.rest.auth;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.auth.AuthManager;
import com.process.business.helper.BasicAuth;
import com.process.domain.auth.Login;
import com.process.domain.auth.Session;


/**
 * Resource Auth REST for Auth module
 * @author  Oswel Sanchez
 *    
 */
@Path("/sessions")
public class AuthResource {
    
    private static final Logger logger = Logger.getLogger(AuthResource.class);

    @Autowired
	private AuthManager authManager;

	/**
	 * Establece una sesion de usuario Process 
	 * <p>
	 * url: POST http://localhost:9090/process/api/sessions/{ambiente}
	 * 
	 * @param auth parametro header que contiene credenciales del usuario
	 * @param ip parametro header que contiene el ip cliente
	 * @param ambiente parametro Path que contiene el nombre del ambiente
	 * @return Session Object
	 */
	@POST
	@Path("{ambiente}")
	public synchronized Response establecerSesion(@HeaderParam("authorization") String auth,
									 @HeaderParam("http.remote.address") String ip,
									 @PathParam("ambiente") String ambiente) {
		Response response = null;
		try {	
			
			if(auth.length() < 125) {
				String[] varData = BasicAuth.decode(auth);
				Login login = new Login();
				login.setUsuario(varData[0]);
				login.setClave(varData[1]);
				login.setAmbiente(ambiente);
				login.setSessionId(0);
				login.setIp(ip);
				login.setValidaExt(0);
				login.setSesionCaida(0);
				authManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
				//logger.info("Establecer session resource "+varData[0]+" hash "+org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString());
				Session responseService = authManager.establecerSesion(login);			
				GenericEntity<Session> entity = new GenericEntity<Session>(responseService) {};			
				response = Response.ok(entity).build();
			}
			
		} catch (Exception e) {
		    logger.error("establecerSesion", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	/**
	 * Establece una sesion de usuario Process 
	 * <p>
	 * url: POST http://localhost:9090/process/api/sessions/{ambiente}
	 * 
	 * @param auth parametro header que contiene credenciales del usuario
	 * @param ip parametro header que contiene el ip cliente
	 * @param ambiente parametro Path que contiene el nombre del ambiente
	 * @return Session Object
	 */
	@POST
	@Path("impersonal/{ambiente}")
	public synchronized Response establecerSesionImpersonal(@HeaderParam("authorization") String auth,
									 @HeaderParam("http.remote.address") String ip,
									 @PathParam("ambiente") String ambiente) {
		Response response = null;
		try {	
			;
			String[] varData = BasicAuth.decode(auth);
			Login login = new Login();
			login.setUsuario(varData[0]);
			login.setClave(varData[1]);
			login.setAmbiente(ambiente);
			login.setSessionId(0);
			login.setIp(ip);
			login.setValidaExt(1);
			login.setSesionCaida(0);
			authManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Session responseService = authManager.establecerSesion(login);			
			GenericEntity<Session> entity = new GenericEntity<Session>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("establecerSesion", e);
		    return Response.serverError().build();
		}
		return response;
	}

	/**
	 * Recupera una  de usuario Process 
	 * <p>
	 * url: GET http://localhost:9090/process/api/sessions/{ambiente}
	 * 
	 * @param auth parametro header que contiene credenciales del usuario
	 * @param ip parametro header que contiene el ip cliente
	 * @param ambiente parametro Path que contiene el nombre del ambiente
	 * @param session parametro Path que contiene la session a recuperar
	 * @return Session Object
	 */
	@GET
	@Path("{ambiente}/sc/{session}")
	public synchronized Response recuperarSesion(@HeaderParam("authorization") String auth,
									@HeaderParam("http.remote.address") String ip,
									@PathParam("ambiente") String ambiente,
									@PathParam("session") String session,
									@HeaderParam("engineId") Integer engineId) {
		Response response = null;
		try {			
			String[] varData = BasicAuth.decode(auth);
			Login login = new Login();
			login.setUsuario(varData[0]);
			login.setClave(varData[1]);
			login.setAmbiente(ambiente);
			login.setSessionId(0);
			login.setIp(ip);
			login.setSesionCaida(Integer.valueOf(session));	
			authManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Session responseService = authManager.recuperarSesion(login);			
			GenericEntity<Session> entity = new GenericEntity<Session>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("recuperarSesion", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	/*
	 * Elimina una sesion de usuario Process 
	 * <p>
	 * url: PUT http://localhost:9090/process/api/sessions/{ambiente}
	 * 
	 * @param auth parametro header que contiene credenciales del usuario
	 * @param ip parametro header que contiene el ip cliente
	 * @param ambiente parametro Path que contiene el nombre del ambiente
	 * @param session parametro Path que contiene la session a eliminar
	 * @return Session Object
	 */
	@PUT
	@Path("{ambiente}/sc/{session}")
	public synchronized Response eliminarSesion(@HeaderParam("authorization") String auth,
									@HeaderParam("http.remote.address") String ip,
									@PathParam("ambiente") String ambiente,
									@PathParam("session") String session,
									@HeaderParam("engineId") Integer engineId) {
		Response response = null;
		try {			
			String[] varData = BasicAuth.decode(auth);
			Login login = new Login();
			login.setUsuario(varData[0]);
			login.setClave(varData[1]);
			login.setAmbiente(ambiente);
			login.setSessionId(0);
			login.setIp(ip);
			login.setSesionCaida(Integer.valueOf(session));	
			authManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Session responseService = authManager.eliminarSesion(login);			
			GenericEntity<Session> entity = new GenericEntity<Session>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("eliminarSesion", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Cierra una sesion de usuario Process 
	 * <p>
	 * url: DELETE http://localhost:9090/process/api/sessions
	 * 
	 * @return Session Object
	 */
	@DELETE
	public synchronized Response cerrarSesion(@HeaderParam("engineId") Integer engineId) {
		Response response = null;
		try {			
			authManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Session responseService = authManager.cerrarSesion();			
			GenericEntity<Session> entity = new GenericEntity<Session>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("cerrarSesion", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Establece una sesin de usuario Process para recuperar la clave
	 * <p>
	 * url: POST http://localhost:9090/process/api/sessions/rc/{ambiente}
	 * 
	 * @param auth parametro header que contiene credenciales del usuario
	 * @param ip parametro header que contiene el ip cliente
	 * @param ambiente parametro Path que contiene el nombre del ambiente
	 * @return Session Object
	 */
	@POST
	@Path("/rc/{ambiente}")
	public synchronized Response establecerSesionRc(@HeaderParam("authorization") String auth,
									   @HeaderParam("http.remote.address") String ip,
									   @PathParam("ambiente") String ambiente,
									   @HeaderParam("engineId") Integer engineId) {
		Response response = null;
		try {			
			String[] varData = BasicAuth.decode(auth);
			Login login = new Login();
			login.setUsuario(varData[0]);
			login.setClave("");
			login.setAmbiente(ambiente);
			login.setSessionId(0);
			login.setIp(ip);
			login.setValidaExt(2);
			login.setSesionCaida(0);
			authManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Session responseService = authManager.establecerSesion(login);
			//validar
			responseService.setStatusCode(0);
//			if ((responseService.getSessionCaida()!=0)&&(responseService.getSessionCaida()!=null)){
//				login.setSesionCaida(responseService.getSessionCaida());
//				responseService = authManager.eliminarSesion(login);
//			}
			GenericEntity<Session> entity = new GenericEntity<Session>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("establecerSesionRc", e);
		    return Response.serverError().build();
		}
		return response;
	}	
}
