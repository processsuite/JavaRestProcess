/**
 * AuthManager.java 
 * 
 */
package com.process.business.auth;

import com.process.domain.auth.Engine;
import com.process.domain.auth.Login;
import com.process.domain.auth.Session;

/**
 * Business Interface AuthManager for Auth Module 
 * @author Oswel Sanchez
 * 
 */
public interface AuthManager extends Engine {

	/**
	 * Establece una sesion de usuario Process
	 * @param login object
	 * @return Session
	 */
	public Session establecerSesion(Login login); 
	
	/**
	 * Recupera una sesion de usuario Process
	 * @param login object session a recuperar
	 * @return Session
	 */
	public Session recuperarSesion(Login login);
	
	/**
	 * Elimina una sesion de usuario Process
	 * @param login object session a eliminar
	 * @return Session
	 */
	public Session eliminarSesion(Login login); 
	
	/**
	 * Cierra una sesion de usuario Process
	 * @return Session 
	 */
	public Session cerrarSesion(); 	
    
}

