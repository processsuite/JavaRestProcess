/**
 * SimpleAuthManager.java 
 *
 */
package com.process.business.auth.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.process.business.auth.AuthManager;
import com.process.business.environment.EnvironmentManager;
import com.process.business.helper.ClassFactory;
import com.process.business.helper.c_Process;
import com.process.domain.auth.Login;
import com.process.domain.auth.Session;

@Service("authManager")
public class SimpleAuthManager implements AuthManager {
    
	private static final Logger logger = Logger.getLogger(SimpleAuthManager.class);
	
	private c_Process motor;
	
	private Integer engineP;
	
	@Override
	public void setEngineId(Integer engineId) {
		engineP = engineId;
	}	
	
    @Autowired
	private EnvironmentManager environmentManager;	

	@Override
	public Session establecerSesion(Login login) {
		Session session = new Session();
		try{
			motor = ClassFactory.getProcess(engineP);
			Integer result = motor.p4bEstablecerSesion(login.getUsuario(), login.getClave(), login.getAmbiente(), login.getSessionId(), login.getIp(), login.getValidaExt(), login.getSesionCaida());
			if (result == -1) {
				//session.setTicket(motor.p4bGuardarSesion(0));
				session.setStatusCode(motor.p4bStatus());
			}else{
				session.setTicket("");
				session.setStatusCode(motor.p4bStatus());
				session.setSessionCaida(motor.sesionCaida());
			}
		}catch(Exception e){
			logger.error("establecerSesion:", e);
		}
		logger.info("establecer session");
		return session;
	}
	
	@Override
	public Session establecerSesionImpersonal(Login login) {
		Session session = new Session();
		try{
			motor = ClassFactory.getProcess(engineP);
			Integer result = motor.p4bEstablecerSesion(login.getUsuario(), login.getClave(), login.getAmbiente(), login.getSessionId(), login.getIp(), login.getValidaExt(), login.getSesionCaida());
			if (result == -1) {
				//session.setTicket(motor.p4bGuardarSesion(0));
				session.setStatusCode(motor.p4bStatus());
			}else{
				session.setTicket("");
				session.setStatusCode(motor.p4bStatus());
				session.setSessionCaida(motor.sesionCaida());
			}
		}catch(Exception e){
			logger.error("establecerSesion:", e);
		}
		logger.info("establecer session");
		return session;
	}

	@Override
	public Session recuperarSesion(Login login) {
		Session session = new Session();
		try{
			motor = ClassFactory.getProcess(engineP);
			login.setValidaExt(0);
			Integer result = motor.p4bEstablecerSesion(login.getUsuario(), login.getClave(), login.getAmbiente(), login.getSessionId(), login.getIp(), login.getValidaExt(), login.getSesionCaida());
			if (result == -1) {
				//session.setTicket(motor.p4bGuardarSesion(0));
				session.setStatusCode(motor.p4bStatus());
			}else{
				session.setTicket("");
				session.setStatusCode(motor.p4bStatus());
				session.setSessionCaida(motor.sesionCaida());
			}
		}catch(Exception e){
			logger.error("recuperarSesion:", e);
		}
		return session;
	}

	@Override
	public Session eliminarSesion(Login login) {
		Session session = new Session();
		//login.setValidaExt(Integer.valueOf(environmentManager.getEnvironment(login.getAmbiente()).getMatriz().getWebtipoConexion()));
		try{
			motor = ClassFactory.getProcess(engineP);
			login.setValidaExt(3);
			Integer result = motor.p4bEstablecerSesion(login.getUsuario(), login.getClave(), login.getAmbiente(), login.getSessionId(), login.getIp(), login.getValidaExt(), login.getSesionCaida());
			if (result == -1) {
				//session.setTicket(motor.p4bGuardarSesion(0));
				session.setStatusCode(motor.p4bStatus());
			}else{
				session.setTicket("");
				session.setStatusCode(motor.p4bStatus());
				session.setSessionCaida(motor.sesionCaida());
			}
		}catch(Exception e){
			logger.error("eliminarSesion:", e);
		}
		return session;
	}

	@Override
	public Session cerrarSesion() {
		Session session = new Session();
		try{
			motor = ClassFactory.getProcess(engineP);
			motor.p4bCerrarSesion();			
			session.setStatusCode(motor.p4bStatus());
		}catch(Exception e){
			logger.error("cerrarSesion:", e);
		}
		return session;
	}

	public EnvironmentManager getEnvironmentManager() {
		return environmentManager;
	}

	public void setEnvironmentManager(EnvironmentManager environmentManager) {
		this.environmentManager = environmentManager;
	}	
	
}
