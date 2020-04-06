/**
 * EmailManager.java 
 * 
 */
package com.process.business.user;

import java.util.List;

import com.process.domain.auth.Engine;
import com.process.domain.user.Email;


/**
 * Business Interface EmailManager for user Module 
 * @author Oswel Sanchez
 * 
 */
public interface EmailManager extends Engine  {

	/**
	 * Obtiene todos los correos de un usuario Process
	 * @param nuDoc
	 * @param desde
	 * @param opcPropios
	 * @return List<Email>
	 */
	public List<Email> obtenerCorreos(Integer nuDoc, Integer desde, Integer opcPropios); 	
	
    /**
     * Obtiene el contenido de un correo dado su identificador
     * @param nuMessage
     * @return String
     */
    public String obtenerContenidoCorreo(Integer nuMessage);
}
