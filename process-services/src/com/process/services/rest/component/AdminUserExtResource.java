package com.process.services.rest.component;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.component.AdmuserExt;
import com.process.domain.component.Puesto;

@Path("/userExterno")
public class AdminUserExtResource {
	private static final Logger logger = Logger.getLogger(AdminUserExtResource.class);
	 @Autowired
	private AdmuserExt adm;
	
	@GET
	@Path("/getUsers")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public synchronized Response getUser(@QueryParam("usuario") String usuario, @QueryParam("ambiente") String ambiente){
		Response response = null;
		try {
			List<Puesto> resp = adm.obtenerUsuarios(usuario, ambiente);
			GenericEntity<List<Puesto>> entity = new GenericEntity<List<Puesto>>(resp) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("generarArchivo", e);
		    return Response.serverError().build();
		}
		return response;		
	}
	
	@POST
	@Path("/postUsers")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public synchronized Response postUsers(@QueryParam("ambiente") String ambiente, List<Puesto> puestos){
		Response response = null;
		try {
			List<Puesto> resp = adm.agregarUsuario(puestos, ambiente);
			GenericEntity<List<Puesto>> entity = new GenericEntity<List<Puesto>>(resp) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("generarArchivo", e);
		    return Response.serverError().build();
		}
		return response;		
	}
	
	@DELETE
	@Path("/delUsers")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public synchronized Response delUsers(@QueryParam("usuario") String usuario, @QueryParam("ambiente") String ambiente){
		Response response = null;
		try {
			Boolean resp = adm.eliminarUsuairo(usuario, ambiente);
			GenericEntity<Boolean> entity = new GenericEntity<Boolean>(resp) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("generarArchivo", e);
		    return Response.serverError().build();
		}
		return response;		
	}
	@POST
	@Path("/postPerfil")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public synchronized Response postPerfil(@QueryParam("ambiente") String ambiente, Puesto p){
		Response response = null;
		try {
			Boolean resp = adm.registrarPerfil(p, ambiente, "");
			GenericEntity<Boolean> entity = new GenericEntity<Boolean>(resp) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("generarArchivo", e);
		    return Response.serverError().build();
		}
		return response;		
	}
	
	@DELETE
	@Path("/delPerfil")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public synchronized Response delPerfil(@QueryParam("ambiente") String ambiente, @QueryParam("usuario") String usuario){
		Response response = null;
		try {
			Boolean resp = adm.eliminarPerfil(usuario, ambiente,"");
			GenericEntity<Boolean> entity = new GenericEntity<Boolean>(resp) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("generarArchivo", e);
		    return Response.serverError().build();
		}
		return response;		
	}
}
