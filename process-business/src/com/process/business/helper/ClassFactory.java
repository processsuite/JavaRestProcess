package com.process.business.helper;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.apache.log4j.Logger;

import com4j.COM4J;


/**
 * Defines methods to create COM objects
 */
public abstract class ClassFactory {
  private ClassFactory() {} // instanciation is not allowed
  private static List<ProcessEngine> listProcess;
  private static Integer errorCode = 0;
  private static String nudoc = "";
  
  private static final Logger logger = Logger.getLogger(ClassFactory.class);
  
  public static c_Process getProcess(Integer hash) {
	  c_Process process = null;
	  for(ProcessEngine p:getListProcess()){
		  if (p.getEngine().hashCode() == hash){
			  process = p.getEngine();
			  break;
		  }
	  }
	  //logger.info("getProcess "+hash +" = "+process.hashCode()+" "+(hash==process.hashCode()));
	  //logger.info("getProcess "+getListProcess().size());
	  return process;
  }
  

  public static c_Process createProcess() {
	  c_Process process = COM4J.createInstance( c_Process.class, "{4E97C91F-0FC7-4694-A8AC-C6C92587DC4B}" );
	  return process;
  }  
  
  public static Integer getErrorCode() {
	  Integer tmpCode = errorCode;
	  errorCode = 0;
	  return tmpCode;
  }

  public static void setErrorCode(Integer errorCode) {
	ClassFactory.errorCode = errorCode;
  }

  public static List<ProcessEngine> getListProcess() {
	if (listProcess==null){
		listProcess = new ArrayList<ProcessEngine>();
	}
	return listProcess;	  
  }

  public static Boolean isTicketList(String ticket) {
	  Boolean result = false;
	  for(ProcessEngine p:getListProcess()){
		  if (p.getTicket().equals(ticket)){
			  result = true;
			  break;
		  }
	  }
	  return result;  
  }  
  
  public static void setListProcess(List<ProcessEngine> listProcess) {
	ClassFactory.listProcess = listProcess;
  }

  public static void deleteProcesList(Integer hash){
	  for(ProcessEngine p:getListProcess()){
		  if (p.getEngine().hashCode() == hash){
			  getListProcess().remove(p);
			  break;
		  }
	  }
  }
  
  public static void imprimirArreglo(String a, String codigo) {
	  JsonObjectBuilder json = Json.createObjectBuilder();
	  JsonArrayBuilder jsonMatriz = Json.createArrayBuilder();
	  for(ProcessEngine p:getListProcess()){
		logger.info("Documento "+a+" hash "+codigo +" = "+p.getEngine().hashCode()+" "+(Integer.valueOf(codigo)==p.getEngine().hashCode()));
		JsonObjectBuilder jsonFila = Json.createObjectBuilder();
		jsonFila.add("token", p.getTicket());
		jsonFila.add("Hash", p.getEngine().hashCode());
		jsonMatriz.add(jsonFila);
	  }
	  json.add("ProcessEngine",jsonMatriz);
	  logger.info(a+" "+json.build());
  }

  //Ambientes
  public static _Ambientes createAmbientes() {
	    return COM4J.createInstance( _Ambientes.class, "{29BA01B9-E152-11D3-B24F-0040333E041A}" );
  }

  public static _cDiag createcDiag() {
    return COM4J.createInstance( _cDiag.class, "{E242701B-B0A5-43D7-B501-447A99C11277}" );
  } 
 
  
}
