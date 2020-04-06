package com.process.business.console;

import com.process.domain.auth.Engine;
import com.process.domain.console.Registro;

public interface ConsoleManager extends Engine{

	Registro obtenerDatosConsole(String ambiente);
	
	/*Modifica solo el objeto indicado*/
	Boolean modificarObjetoConsole(String ambiente, Registro registro,String nameObj);

	Boolean migradorAmbientRegToXml(String ambiente);
}
