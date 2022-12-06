package com.process.business.helper;

import org.apache.log4j.Logger;


public class EncriptBin {
	
	private C_Bin bin;
	
	private String COPY_RIGHT = "M&B Solution (C) 1999";
	
	
	private static final Logger logger = Logger.getLogger(EncriptBin.class);
	
	public  String GenerarPassword(String pass){
		String resp = "";
		logger.info(" ingresa generar password");
		try {
			bin = ClassFactory.createC_Bin();
			logger.info(" instancia objeto ");
			int a = 1;
			char b = (char)a;
			String pwEncript = "19700101"+b+pass;
			logger.info("pwEncript "+pwEncript);
			//bin.clave(COPY_RIGHT);
			//Holder<String> password = new Holder<String>();
			//password.value = pwEncript;
			logger.info(" Pasa constante de clave ");
			resp = bin.transforma(pwEncript, 1);
			logger.info(" Genera contraseña ");
			//bin.dispose();
			logger.info(" Elimina objeto ");
		} catch (Exception e) {
			logger.info("Error ", e);
		}
		
		
		return resp;
	}

}
