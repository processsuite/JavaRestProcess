/**
 * ServicioManager.java 
 * 
 */
package com.process.business.user;

import java.util.List;

import com.process.domain.auth.Engine;
import com.process.domain.user.Servicio;

/**
 * Business Interface UserManager for user Module 
 * @author Oswel Sanchez
 * 
 */
public interface ServicioManager extends Engine  {

	/**
	 * Obtiene los servicios para un usuario Process
	 * @return List<Servicio>
	 */
	public List<Servicio> obtenerServicios(); 	
	
    
}
