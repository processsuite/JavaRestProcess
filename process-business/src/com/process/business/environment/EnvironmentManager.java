/**
 * EnvironmentManager.java 
 * 
 */
package com.process.business.environment;

import java.util.List;

import com.process.domain.environment.Environment;

/**
 * Business Interface EnvironmentManager for Environment Module 
 * @author Oswel Sanchez
 * 
 */
public interface EnvironmentManager {

	/**
	 * Obtiene lista de Ambientes
	 * @return List<Environment>
	 */
	public List<Environment> getEnvironments(); 
	
	/**
	 * Obtiene detalle de un ambiente
	 * @param name nombre del ambiente
	 * @return List<Environment>
	 */
	public Environment getEnvironment(String name);

	public String getDatoAmbiente(String amb, String string);	
	
	
   
}
