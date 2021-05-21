/**
 * InterceptorAuthorizationMule.java
 *
 */
package com.process.services.rest.aop;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.interceptor.AbstractEnvelopeInterceptor;
import org.mule.management.stats.ProcessingTime;

import com.process.business.helper.BasicAuth;
import com.process.business.helper.ClassFactory;
import com.process.business.helper.ProcessEngine;
import com.process.business.helper.c_Process;

/**
 * Interceptor Authorization MULE ESB 
 * @author Oswel Sanchez
 * 
 */

public class InterceptorAuthorizationMule extends AbstractEnvelopeInterceptor {
    
    private static final Logger logger = Logger.getLogger(InterceptorAuthorizationMule.class);
    
	@Override
	public MuleEvent after(MuleEvent arg0) throws MuleException {
		try{
			cerrarSession(arg0);	
		}catch(Exception e){
			logger.error("after", e);
		}		
		return arg0;
	}

	@Override
	public MuleEvent before(MuleEvent arg0) throws MuleException {
		String varTicket = "";
		
	    if (arg0.getMessage().getInboundProperty("http.method")!="OPTIONS"){//verifica si el servicio es un metodo option para que no ingrese en la validacion del token
			if (arg0.getMessage().getInboundProperty("authorization")==null){ //verifica si tiene cabecera de autorizacion
				throw new NotAuthorizedException("Missing Credentials", Response.Status.UNAUTHORIZED);
			}else if (arg0.getMessage().getInboundProperty("authorization").toString()==""){//verifica si tiene cabecera de autorizacion
				throw new NotAuthorizedException("Missing Credentials", Response.Status.UNAUTHORIZED);
			}else if (!arg0.getMessage().getInboundProperty("authorization").toString().startsWith("Bearer ") && !arg0.getMessage().getInboundProperty("authorization").toString().startsWith("Basic ")){//verifica que la cabecera de autorizacion tenga berear o basic 
				throw new NotAuthorizedException("Wrong Sheme.", Response.Status.UNAUTHORIZED);
			}else if (arg0.getMessage().getInboundProperty("authorization").toString().startsWith("Bearer ")){//validacion si es para validar ticket o para iniciar sesion
				Integer result = 0;
				if(arg0.getMessage().getInboundProperty("nu_doc") != null) {
					ClassFactory.setNudoc(arg0.getMessage().getInboundProperty("nu_doc").toString());
				}
				
				try{
					varTicket = BasicAuth.decodeParameter(arg0.getMessage().getInboundProperty("authorization").toString());
					result = obtenerSession(varTicket);
				}catch(Exception e){
					logger.error("decodificar parametros", e);
				}
				if (result == 0) {
					throw new NotAuthorizedException("Ticket Credentials Invalid.", Response.Status.UNAUTHORIZED);
				}else{
					arg0.getMessage().setOutboundProperty("engineId", result);
				}
				//logger.info("servicio llamado: "+arg0.getMessage().getInboundProperty("http.relative.path").toString()+" "+arg0.getMessage().getInboundProperty("authorization").toString() );
				//logger.info("Ticket de entrada para el servicio:( " + arg0.getMessage().getInboundProperty("authorization").toString() + ")");
			}else{
				if(arg0.getMessage().getInboundProperty("authorization").toString().length() < 125) {
					ProcessEngine process = new ProcessEngine();				
					c_Process motor = ClassFactory.createProcess();
					process.setEngine(motor);
					varTicket = BasicAuth.decodeParameter(arg0.getMessage().getInboundProperty("authorization").toString());
					process.setTicket(varTicket);
					ClassFactory.getListProcess().add(process);//add engine to list
					//logger.info("Create session engineId para inicio de sesion to list:(" + motor.hashCode() + ")");
					arg0.getMessage().setOutboundProperty("engineId", motor.hashCode());
				}else {
					ClassFactory.setErrorCode(406);
					//logger.info("Autorizacion excede cantidad de caracteres permitida");
					
				}
				
			}
	    }		
		return arg0;
	}

	@Override
	public MuleEvent last(MuleEvent arg0, ProcessingTime arg1, long arg2, boolean arg3) throws MuleException {
		return arg0;
	}
	
	
	private synchronized Integer obtenerSession(String ticket) throws InterruptedException{
		Integer result = 0;
		ProcessEngine process = new ProcessEngine();
		c_Process motor = ClassFactory.createProcess();
		process.setEngine(motor);//Inici instancia en el motor
		process.setTicket(ticket);//
		if (ClassFactory.isTicketList(ticket)){//Verifica que el ticket Exista en el arreglo de ticket abiertos
			result = motor.p4bObtenerSesionUnica(ticket,0);//Si el ticket existe genera uno
			//logger.info("ObtenerSesionUnica "+result);
		}else{
			result = motor.p4bObtenerSesion(ticket,0);//No entiendo porq no deberia existir un  ticket
			//result = motor.p4bObtenerSesionUnica(ticket,0);
			//logger.info("ObtenerSesion "+result);
		}
		if (result!=0){
			ClassFactory.getListProcess().add(process);//add engine to list
			result = motor.hashCode();
			//logger.info("Get session engine to list:(" + result + ")");		
		}
		//logger.info("Get session engineId to list:(" + result + ")");
		return result;		
	}
	
	private synchronized void cerrarSession(MuleEvent arg0) throws InterruptedException{//usado para guardar session y cerrar session segun sea el caso
		Integer errorCode = ClassFactory.getErrorCode();
		
		if (arg0.getMessage().getInboundProperty("authorization")!=null){
			if(arg0.getMessage().getOutboundProperty("engineId") != null) {
				c_Process motor = ClassFactory.getProcess(Integer.valueOf(arg0.getMessage().getOutboundProperty("engineId").toString()));
				//validar si hay Error Process se informa
				Integer result = motor.p4bStatus();
				if (result!=0){
					arg0.getMessage().setOutboundProperty("http.status", result);//se informa el codigo de error
				}else if (errorCode!=0){
					arg0.getMessage().setOutboundProperty("http.status", errorCode);//se informa el codigo de error
				}
				//logger.info("respuesta de servicio: "+arg0.getMessage().getInboundProperty("http.relative.path").toString()+" "+arg0.getMessage().getInboundProperty("authorization").toString());
				if(arg0.getMessage().getInboundProperty("http.relative.path").toString().indexOf("abrir1")>0 || arg0.getMessage().getInboundProperty("http.relative.path").toString().indexOf("crearDocumento")>0) {
					try {
						logger.info("respuesta de servicio: "+arg0.getMessage().getInboundProperty("http.relative.path").toString()+" Parametros de servicio "+arg0.getMessage().getInboundProperty("http.query.params").toString()+" Respúesta "+arg0.getMessage().getPayloadAsString()+" Token: "+arg0.getMessage().getInboundProperty("authorization").toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//validar que estamos cerrando session para no llamar al guardar session.
				if ((arg0.getMessage().getInboundProperty("http.method")=="DELETE") && (arg0.getMessage().getInboundProperty("http.relative.path").toString().equals("/process/api/sessions"))){
					//logger.info("End session:"+arg0.getMessage().getOutboundProperty("engineId").toString());	
				}else{
					if (arg0.getMessage().getInboundProperty("authorization").toString().startsWith("Bearer ")){
						//String ticket = motor.p4bGuardarSesion(0);
						String ticket = motor.p4bGuardarSesionUnica(0);
						arg0.getMessage().setOutboundProperty("Ticket", ticket);
						//logger.info("Ticket de respuestas " + ticket);
						//logger.info("Save session Bearer: " + arg0.getMessage().getOutboundProperty("engineId").toString());
					}else if (arg0.getMessage().getInboundProperty("authorization").toString().startsWith("Basic ")){
						//String ticket = motor.p4bGuardarSesion(0);
						String ticket = motor.p4bGuardarSesionUnica(0);
						arg0.getMessage().setOutboundProperty("Ticket", ticket);
						//logger.info("Ticket session basic " + ticket);
						//logger.info("Save session Basic: " + arg0.getMessage().getOutboundProperty("engineId").toString());
					}													
				}
				//delete from list engine
				ClassFactory.deleteProcesList(Integer.valueOf(arg0.getMessage().getOutboundProperty("engineId").toString()));			
				motor.dispose();//elimina la instancia del motor creada
			}else if(errorCode!=0){
				arg0.getMessage().setOutboundProperty("http.status", errorCode);//se informa el codigo de error
			}			
			
			
			
		}		
	}

}