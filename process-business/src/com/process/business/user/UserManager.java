/**
 * UserManager.java 
 * 
 */
package com.process.business.user;

import com.process.domain.auth.Engine;
import com.process.domain.user.User;

/**
 * Business Interface UserManager for user Module 
 * @author Oswel Sanchez
 * 
 */
public interface UserManager extends Engine {

	/**
	 * Obtiene datos de usuario Process
	 * @return User
	 */
	public User obtenerDatosUsuario(); 
	
	/**
	 * Actualiza datos personales de un usuario Process 
	 * @param name nombre del usuario a cambiar
	 * @param email	correo del usuario a cambiar  
	 * @return User
	 */
	public User actualizarDatosUsuario(String name, String email);
	
 
	/**
	 * Actualiza aspectos de seguridad para un usuario Process 
	 * @param contexto el tipo de contexto a actualizar
	 * @param clave1
	 * @param clave2
	 * @param clave3
	 * @return void
	 */
	public void actualizarSeguridadUsuario(Integer contexto, String clave1, String clave2, String clave3);
	
	/**
	 * Recupera clave para un usuario Process
	 * @param email
	 * @param pregunta
	 * @param respuesta
	 */
	public void recuperarClave(String email, String pregunta, String respuesta);

	
	/**
	 * Evaluar pregunta de seguridad luego de recuperar contraseña.
	 * @param email
	 * @param pregunta
	 * @param respuesta
	 */
	void validarRespuestaRecuperar(String email, String pregunta, String respuesta);
	
}
