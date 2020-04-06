package com.process.business.log;

import java.util.List;

import com.process.domain.auth.Engine;
import com.process.domain.log.ListFechaUsaurio;
import com.process.domain.log.ObjL;

public interface LogManager extends Engine{

	public List<ListFechaUsaurio> initLog(String amb);
	
	public ObjL obtenerTraza(String amb, String fecha, String usuario);
	
	public ObjL obtenerTrazaSql(String amb, String fecha, String usuario);

	ObjL obtenerTrazaRobot(String amb, String fecha, String usuario);

	ObjL obtenerTrazaEmail(String amb, String fecha, String usuario);

}
