/**
 * SimpleEnvironmentManager.java 
 *
 */
package com.process.business.environment.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.process.business.console.impl.SimpleConsoleManager;
import com.process.business.environment.EnvironmentManager;
import com.process.business.helper.ClassFactory;
import com.process.business.helper._Ambientes;
import com.process.domain.environment.Environment;
import com.process.domain.environment.MatrizEnvironment;

import com4j.Holder;

/**
 * Business Implementation SimpleEnvironmentManager for Environment Module 
 * @author Oswel Sanchez
 * 
 */
@Service("environmentManager")
public class SimpleEnvironmentManager implements EnvironmentManager {
    
	private static final Logger logger = Logger.getLogger(SimpleEnvironmentManager.class);
	
	private _Ambientes motor;	

	@Override
	public List<Environment> getEnvironments() {
		SimpleConsoleManager ambientesXml = new SimpleConsoleManager();
		List<Environment> list = new ArrayList<Environment>();
		list = ambientesXml.getListAmbientes();
		if(list.size() > 0){
			return list;
		}
		try{
			motor = ClassFactory.createAmbientes();
			Holder<String> app = new Holder<String>();
			motor.init(app);
			for(int k=1;k<=motor.count();k++){
				Environment env = new Environment();
				env.setName(motor.item(k).toString());
				MatrizEnvironment matriz = new MatrizEnvironment();
				matriz.setIdioma(motor.leerVarAmbienteEx(motor.item(k).toString(), "Idioma"));
				matriz.setWebtipoConexion(motor.leerVarAmbienteEx(motor.item(k).toString(), "WebtipoConexion"));
				matriz.setNivelSeg(motor.leerVarAmbienteEx(motor.item(k).toString(), "NivelSeg"));
				matriz.setWebAmbientes(motor.leerVarAmbienteEx(motor.item(k).toString(), "WebAmbientes"));
				matriz.setDominio(motor.leerVarAmbienteEx(motor.item(k).toString(), "Dominio"));	
				matriz.setiRepInclude(motor.leerVarAmbienteEx(motor.item(k).toString(), "i_RepInclude"));
				matriz.setFormatoFecha(motor.leerVarAmbienteEx(motor.item(k).toString(), "FormatoFecha"));
				matriz.setiRepAnexos(motor.leerVarAmbienteEx(motor.item(k).toString(), "i_RepAnexos"));
				matriz.setTamanoAnexo(motor.leerVarAmbienteEx(motor.item(k).toString(), "tamanoAnexo"));
				matriz.setTimeSave(motor.leerVarAmbienteEx(motor.item(k).toString(), "timeSave"));
				
				if (matriz.getFormatoFecha().equals("")){
					matriz.setFormatoFecha("DD/MM/YYYY");
				}
				String fdm = motor.leerVarAmbienteEx(motor.item(k).toString(), "FormatoMilesDecimal");
				if (fdm.equals("")){
					matriz.setFormatoMiles(".");
					matriz.setFormatoDecimal(",");
				}else{
					matriz.setFormatoMiles(fdm.substring(0, 0));
					matriz.setFormatoDecimal(fdm.substring(1, 1));					
				}
				env.setMatriz(matriz);
				list.add(env);
			}
		}catch(Exception e){
			logger.error("getEnvironments:", e);
		}finally{
			if (motor!=null){
				motor.dispose();
			}
		}
		return list;
	}

	@Override
	public Environment getEnvironment(String name) {
		
		SimpleConsoleManager ambientesXml = new SimpleConsoleManager();
		
		
		Environment env = new Environment();
		
		env = 	ambientesXml.getAmbienteXml(name);
			if(env != null){
				return env;
			}else{
				env = new Environment();
			}
		try{
			motor = ClassFactory.createAmbientes();
			Holder<String> app = new Holder<String>();
			motor.init(app);
			env.setName(name);
			MatrizEnvironment matriz = new MatrizEnvironment();
			matriz.setIdioma(motor.leerVarAmbienteEx(name, "Idioma"));
			matriz.setWebtipoConexion(motor.leerVarAmbienteEx(name, "WebtipoConexion"));
			matriz.setNivelSeg(motor.leerVarAmbienteEx(name, "NivelSeg"));
			matriz.setWebAmbientes(motor.leerVarAmbienteEx(name, "WebAmbientes"));
			matriz.setDominio(motor.leerVarAmbienteEx(name, "Dominio"));	
			matriz.setiRepInclude(motor.leerVarAmbienteEx(name, "i_RepInclude"));
			matriz.setFormatoFecha(motor.leerVarAmbienteEx(name, "FormatoFecha"));
			matriz.setRepAgentes(motor.leerVarAmbienteEx(name, "RepAgentes"));
			matriz.setiRepAnexos(motor.leerVarAmbienteEx(name, "i_RepAnexos"));
			matriz.setTamanoAnexo(motor.leerVarAmbienteEx(name, "tamanoAnexo"));
			matriz.setTimeSave(motor.leerVarAmbienteEx(name, "timeSave"));
			
			if (matriz.getFormatoFecha().equals("")){
				matriz.setFormatoFecha("DD/MM/YYYY");
			}
			String fdm = motor.leerVarAmbienteEx(name, "FormatoMilesDecimal");
			if (fdm.equals("")){
				matriz.setFormatoMiles(".");
				matriz.setFormatoDecimal(",");
			}else{
				matriz.setFormatoMiles(fdm.substring(0, 0));
				matriz.setFormatoDecimal(fdm.substring(1, 1));					
			}			
			env.setMatriz(matriz);
		}catch(Exception e){
			logger.error("getEnvironment:", e);
		}finally{
			if (motor!=null){
				motor.dispose();
			}
		}
		return env;
	}
	
	public String getDatoAmbiente(String amb, String variable){
		SimpleConsoleManager ambientesXml = new SimpleConsoleManager();
		String valor;
		valor =  ambientesXml.leerVarAmbienteXml(amb,  variable);
		if(valor.equals("")) {
			motor = ClassFactory.createAmbientes();
			Holder<String> app = new Holder<String>();
			motor.init(app);
			valor = motor.leerVarAmbienteEx(amb, variable);
		}		
		return valor;
	} 

	
}
