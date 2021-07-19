/**
 * DocumentResource.java 
 *
 */
package com.process.services.rest.document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.mule.util.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.document.DocumentManager;
import com.process.business.helper.ClassFactory;
import com.process.business.helper._Ambientes;
import com.process.business.helper.c_Process;
import com.process.domain.document.Agent;
import com.process.domain.document.Anexo;
import com.process.domain.document.Destino;
import com.process.domain.document.Doc;
import com.process.domain.document.EventAgent;
import com.process.domain.document.Form;
import com.process.domain.document.SendMsg;
import com.process.domain.document.WfDest;
import com.process.domain.document2.Doc2;
import com.process.domain.document2.Forma;


@Path("/document")
public class DocumentResource {
    
    private static final Logger logger = Logger.getLogger(DocumentResource.class);

    @Autowired
	private DocumentManager documentManager;
    
	/**
	 * Crea un documento
	 * @param wfp 
	 * @param frmn	 
	 * url: POST http://localhost:9090/process/api/document?wfp={wfp}&frmn={frmn}&env={env}
	 * 
	 * @return Doc Object
	 */
	@POST
	@Path("/crearDocumento")
	public synchronized Response crearDocumento1(@QueryParam("wfp") Integer wfp,
								   @QueryParam("frmn") Integer frmn,
								   @QueryParam("env") String environment) {
		Response response = null;
		try {			
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Doc2 responseService = documentManager.crearDocumento1(wfp, frmn, environment);			
			GenericEntity<Doc2> entity = new GenericEntity<Doc2>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("crearDocumento", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	
	/**
	 * Abrir un documento  
	 * <p>
	 * 
	 * @param nuDoc 
	 * @param nuInst	
	 * @param wfa
	 * @param environment
	 * url: GET http://localhost:9090/process/api/document/abrir?nudoc={nuDoc}&nuinst={nuInst}&wfa={wfa}&env={env}
	 * 
	 * @return Doc Object
	 */
	@GET
	@Path("/abrir1")	
	public synchronized Response abrirDocumento1(@QueryParam("nudoc") Integer nuDoc,
								   @QueryParam("nuinst") Integer nuInst,
								   @QueryParam("wfa") Integer wfa,
								   @QueryParam("env") String environment) {
		Response response = null;
		try {		
			String cod = org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString();
			documentManager.setEngineId(Integer.valueOf(cod));
			Doc2 responseService = documentManager.abrirDocumento1(nuDoc, nuInst, wfa, environment);			
			GenericEntity<Doc2> entity = new GenericEntity<Doc2>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("abrirDocumento", e);
		    return Response.serverError().build();
		}
		return response;
	}	

	/**
	 * Abrir un documento en modo lectura
	 * <p>
	 * 
	 * @param nuDoc 
	 * @param nuInst	
	 * @param environment
	 * url: GET http://localhost:9090/process/api/document/abrirlectura?nudoc={nuDoc}&nuinst={nuInst}&env={env}
	 * 
	 * @return Doc Object
	 */
	@GET
	@Path("/abrirlectura")	
	public synchronized Response abrirDocumentoLectura(@QueryParam("nudoc") Integer nuDoc,
								   		  @QueryParam("nuinst") Integer nuInst,
								   	      @QueryParam("env") String environment) {
		Response response = null;
		try {		
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Doc responseService = documentManager.abrirDocumentoLectura(nuDoc, nuInst, environment);			
			GenericEntity<Doc> entity = new GenericEntity<Doc>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("abrirDocumentoLectura", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	@GET
	@Path("/abrirlectura1")	
	public synchronized Response abrirDocumentoLectura1(@QueryParam("nudoc") Integer nuDoc,
								   		  @QueryParam("nuinst") Integer nuInst,
								   	      @QueryParam("env") String environment) {
		Response response = null;
		try {		
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Doc2 responseService = documentManager.abrirDocumentoLectura1(nuDoc, nuInst, environment);			
			GenericEntity<Doc2> entity = new GenericEntity<Doc2>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("abrirDocumentoLectura", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Obtener un documento  
	 * <p>
	 * 
	 * @param form
	 * url: GET http://localhost:9090/process/api/document?frmn={frmn}
	 * 
	 * @return Doc Object
	 */
	@GET
	public synchronized Response obtenerDocumento(@QueryParam("frmn") Integer frmn) {
		Response response = null;
		try {		
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Doc responseService = documentManager.obtenerDocumento(frmn);			
			GenericEntity<Doc> entity = new GenericEntity<Doc>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerDocumento", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	@GET
	@Path("/obtenerDocumento1")
	public synchronized Response obtenerDocumento1(@QueryParam("frmn") Integer frmn) {
		Response response = null;
		try {		
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Doc2 responseService = documentManager.obtenerDocumento1(frmn);		
			GenericEntity<Doc2> entity = new GenericEntity<Doc2>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerDocumento1", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	/**
	 * Obtener un documento  en modo lectura
	 * <p>
	 * 
	 * @param form
	 * url: GET http://localhost:9090/process/api/document/lectura?frmn={frmn}
	 * 
	 * @return Doc Object
	 */
	@GET
	@Path("/lectura")	
	public synchronized Response obtenerDocumentoLectura(@QueryParam("frmn") Integer frmn) {
		Response response = null;
		try {		
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Doc responseService = documentManager.obtenerDocumentoLectura(frmn);			
			GenericEntity<Doc> entity = new GenericEntity<Doc>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerDocumentoLectura", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Asigna un valor a un campo del documento  
	 * <p>
	 * 
	 * @param campo
	 * @param valor
	 * @param fila
	 * @param columna
	 * url: PUT http://localhost:9090/process/api/document/asignar?campo={campo}&valor={valor}&fila={fila}&columna={columna}
	 * 
	 * @return 200 ok
	 */
	@PUT
	@Path("/asignar")	
	public synchronized Response asignarValorCampoDocumento(@QueryParam("campo") String campo,
											   @QueryParam("valor") String valor,
											   @QueryParam("fila") Integer fila,
											   @QueryParam("columna") Integer columna) {
		Response response = null;
		try {			
			if (valor==null) valor = "";
			if (fila==null) fila = 0;
			if (columna==null) columna = 0;
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			documentManager.asignarValorCampoDocumento(campo, valor, fila, columna);			
			response = Response.ok().build();
		} catch (Exception e) {
		    logger.error("asignarValorCampoDocumento", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Guarda un form de un documento 
	 * <p>
	 * 
	 * @param form
	 * url: PUT http://localhost:9090/process/api/document/saveform?frmn={frmn}
	 * 
	 * @return 200 ok
	 */
	@PUT
	@Path("/saveform")	
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public synchronized Response guardarform(@QueryParam("frmn") Integer frmn, Form form) {
		Response response = null;
		try {			
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));			
			documentManager.guardarform(frmn, form);			
			response = Response.ok().build();
		} catch (Exception e) {
		    logger.error("guardarform", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	
	@PUT
	@Path("/saveform2")	
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public synchronized Response guardarform2(@QueryParam("frmn") Integer frmn, Forma forma) {
		Response response = null;
		try {
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));			
			documentManager.guardarForm1(frmn, forma);
			response = Response.ok().build();
		} catch (Exception e) {
		    logger.error("guardarform2", e);
		    //return Response.serverError().build();
		}
		return response;
	}
	
	/**
	 * Guarda el documento en sesion de usuario  
	 * <p>
	 * 
	 * @param observacion 
	 * url: PUT http://localhost:9090/process/api/document?observacion={observacion}
	 * 
	 * @return 200 ok
	 */
	@PUT
	public synchronized Response guardarDocumento(@QueryParam("observacion") String observacion) {
		Response response = null;
		try {			
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			documentManager.guardarDocumento(observacion);			
			response = Response.ok().build();
		} catch (Exception e) {
		    logger.error("guardarDocumento", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	/**
	 * Cierra un documento 
	 * <p>
	 * 
	 * @param observacion 
	 * url: POST http://localhost:9090/process/api/document
	 * 
	 * @return 200 ok
	 */
	@DELETE
	public synchronized Response cerrarDocumento() {
		Response response = null;
		try {		
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));			
			documentManager.cerrarDocumento();			
			response = Response.ok().build();
		} catch (Exception e) {
		    logger.error("cerrarDocumento", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Cierra un documento en modo lectura
	 * <p>
	 * 
	 * url: POST http://localhost:9090/process/api/document/lectura
	 * 
	 * @return 200 ok
	 */
	@DELETE
	@Path("/lectura")	
	public synchronized Response cerrarDocumentoLectura() {
		Response response = null;
		try {		
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));			
			documentManager.cerrarDocumentoLectura();			
			response = Response.ok().build();
		} catch (Exception e) {
		    logger.error("cerrarDocumentoLectura", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	
	/**
	 * Adquiere un documento para un usuario  
	 * <p>
	 * 
	 * @param observacion
	 * @param email
	 * url: GET http://localhost:9090/process/api/document/adquirir?observacion={observacion}&email={email}
	 * 
	 * @return 200 ok
	 */
	@GET
	@Path("/adquirir")	
	public synchronized Response adquirirDocumento(@QueryParam("observacion") String  observacion,
								      @QueryParam("email") Boolean email) {
		Response response = null;
		try {			
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));			
			SendMsg responseService = documentManager.adquirirDocumento(observacion, email);			
			GenericEntity<SendMsg> entity = new GenericEntity<SendMsg>(responseService) {};			
			response = Response.ok(entity).build();	
		} catch (Exception e) {
		    logger.error("adquirirDocumento", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	/**
	 * Recupera un documento para usuario  
	 * <p>
	 * 
	 * url: GET http://localhost:9090/process/api/document/recuperar
	 * 
	 * @return 200 ok
	 */
	@GET
	@Path("/recuperar")	
	public synchronized Response recuperarDocumento() {
		Response response = null;
		try {		
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));			
			documentManager.recuperarDocumento();			
			response = Response.ok().build();
		} catch (Exception e) {
		    logger.error("recuperarDocumento", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Anula un documento para un usuario 
	 * <p>
	 * 
	 * @param observacion
	 * @param email
	 * url: PUT http://localhost:9090/process/api/document/anular?observacion={observacion}&email={email}
	 * 
	 * @return 200 ok
	 */
	@PUT
	@Path("/anular")	
	public synchronized Response anularDocumento(@QueryParam("observacion") String  observacion,
								    @QueryParam("email") Boolean email) {
		Response response = null;
		try {		
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			SendMsg responseService = documentManager.anularDocumento(observacion, email);			
			GenericEntity<SendMsg> entity = new GenericEntity<SendMsg>(responseService) {};			
			response = Response.ok(entity).build();	
		} catch (Exception e) {
		    logger.error("anularDocumento", e);
		    return Response.serverError().build();
		}
		return response;		
	}	
	
	/**
	 * Rechaza un documento para un usuario
	 * <p>
	 * 
	 * @param observacion
	 * @param urgente
	 * @param email
	 * @param conCopiaEmailA
	 * @param frmnCopia
	 * url: PUT http://localhost:9090/process/api/document/objetar?observacion={observacion}&urgente={urgente}&email={email}&copiaemail={copiaemail}&frmncopia={frmnCopia}
	 * 
	 * @return 200 ok
	 */
	@PUT
	@Path("/objetar")	
	public synchronized Response objetarDocumento(@QueryParam("observacion") String  observacion,
			 						 @QueryParam("urgente") Boolean urgente,
								     @QueryParam("email") Boolean email,
								     @QueryParam("copiaemail") String conCopiaEmailA,
								     @QueryParam("frmncopia") Integer frmnCopia) {
		Response response = null;
		try {		
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			SendMsg responseService = documentManager.objetarDocumento(observacion, urgente, email, conCopiaEmailA, frmnCopia);			
			GenericEntity<SendMsg> entity = new GenericEntity<SendMsg>(responseService) {};			
			response = Response.ok(entity).build();	
		} catch (Exception e) {
		    logger.error("objetarDocumento", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	/**
	 * Avanza un documento para un usuario
	 * <p>
	 * 
	 * @param firma
	 * @param pregunta
	 * @param respuesta
	 * @param observacion
	 * @param urgente
	 * @param email
	 * @param gradoSatisfaccion
	 * @param seleccionResultadoXml
	 * @param conCopiaEmailA
	 * @param frmnCopia
	 * url: PUT http://localhost:9090/process/api/document/avanzar?firma={firma}&pregunta={pregunta}&respuesta={respuesta}&observacion={observacion}&urgente={urgente}&email={email}&satisfaccion={satisfaccion}&copiaemail={copiaemail}&frmncopia={frmnCopia}
	 * 
	 * @return SendMsg
	 */
	@PUT
	@Path("/avanzar")	
	public synchronized Response avanzarDocumento(@QueryParam("firma") String  firma,
									 @QueryParam("pregunta") String  pregunta,
									 @QueryParam("respuesta") String  respuesta,									 
			 						 @QueryParam("observacion") String  observacion,
			 						 @QueryParam("urgente") Boolean urgente,
								     @QueryParam("email") Boolean email,
								     @QueryParam("satisfaccion") Integer gradoSatisfaccion,
								     @QueryParam("copiaemail") String conCopiaEmailA,
								     @QueryParam("frmncopia") Integer frmnCopia,
								     List<WfDest> destinos) {
		Response response = null;
		try {		
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			SendMsg responseService = documentManager.avanzarDocumento(firma, pregunta, respuesta, observacion, urgente, email, gradoSatisfaccion, "", conCopiaEmailA, frmnCopia, destinos);			
			GenericEntity<SendMsg> entity = new GenericEntity<SendMsg>(responseService) {};			
			response = Response.ok(entity).build();	
		} catch (Exception e) {
		    logger.error("avanzarDocumento", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Anexa un archivo al documento
	 * <p>
	 * 
	 * @param fileInputString
	 * @param fileInputDetails
	 * @param amb
	 * @param descripcion
	 * url: POST http://localhost:9090/process/api/document/upload?amb={ambiente}&descripcion={descripcion}
	 * @return 200 ok
	 */	
	@POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("text/html")
    public Response uploadFile(
    	      @FormDataParam("file") final InputStream fileInputString,
    	      @FormDataParam("file") FormDataContentDisposition fileInputDetails,
    	      @QueryParam("amb") String ambiente,
    	      @QueryParam("descripcion") String descripcion) {
    	  
    		_Ambientes motorAmb = null;
    		motorAmb = ClassFactory.createAmbientes();    		
    	    String fileLocation = motorAmb.leerVarAmbienteEx(ambiente, "RepAnexosVirtual") + "\\" + fileInputDetails.getFileName();
    	    Float tamanoAnexo = (Float.parseFloat((motorAmb.leerVarAmbienteEx(ambiente, "tamanoAnexo")).isEmpty()?"0":motorAmb.leerVarAmbienteEx(ambiente, "tamanoAnexo")) * 1024 * 1024);
    	    boolean val = false;
    	    
    	    String status = null;
    	    NumberFormat myFormat = NumberFormat.getInstance();
    	    myFormat.setGroupingUsed(true);
    	   
    	     
    	    // Save the file 
    	    try {
    	     OutputStream out = new FileOutputStream(new File(fileLocation));
    	     byte[] buffer = new byte[1024];
    	     int bytes = 0;
    	     long file_size = 0; 
    	     while ((bytes = fileInputString.read(buffer)) != -1) {
    	      out.write(buffer, 0, bytes);
    	      file_size += bytes;
    	      
    	      if(tamanoAnexo != 0   && file_size > tamanoAnexo){
    	    	  //logger.info("tamaño "+file_size);
    	    	  val = true;
    	    	  break;
    	      }
    	     }
    	     out.flush();  
    	     out.close();
    	     
    	  
    	     if(val){ //eliminar archivo que exede el espacio 
    	    	 File archivo = new File(fileLocation);
    	    	 archivo.delete();
    	    	 status = "{\"tamanoAnexo\":\""+((tamanoAnexo/1024)/1024)+"\", \"error\":true}";
    	     }else{
        	     //llamar anexar documento.
        	     documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
        	     documentManager.anexarDocumento(0, fileLocation, "", descripcion, "", "");
        	     
        	    // logger.info("File has been uploaded to:" + fileLocation + ", size: " + myFormat.format(file_size) + " bytes");
        	     status = "{\"error\":false}";
    	     }
    	     


    	    } catch (IOException ex) {
    	    	logger.error("uploadFile", ex);
    	    	return Response.serverError().build();    	      
    	    }
    		finally{
    			if (motorAmb!=null){
    				motorAmb.dispose();
    			}
    		}
    	 
    	    return Response.status(200).entity(status).build();
	}	
    
	/**
	 * Anexa un archivo por consultoria al directorio virtual
	 * <p>
	 * 
	 * @param fileInputString
	 * @param fileInputDetails
	 * @param amb
	 * @param secuencia
	 * @param nuDoc
	 * @param sufijo
	 * @param directorioVir
	 * @param directorioFis
	 * url: POST http://localhost:9090/process/api/document/upload?amb={ambiente}&descripcion={descripcion}
	 * 
	 * @return 200 ok
	 */	
    
    @POST
    @Path("/upload/consultoria")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("text/html")
    public Response uploadFileConsultoria(
    	      @FormDataParam("file") final InputStream fileInputString,
    	      @FormDataParam("file") FormDataContentDisposition fileInputDetails,
    	      @QueryParam("amb") String ambiente,
    	      @FormDataParam("secuencia") String secuencia,
    	      @FormDataParam("nuDoc") String nudoc,
    	      @FormDataParam("sufijo") String sufijo,
    	      @FormDataParam("directorioVir") String directorioVir,
    	      @FormDataParam("directorioFis") String directorioFis) {
    	
    		_Ambientes motorAmb = null;
    		motorAmb = ClassFactory.createAmbientes();    		
    	    String fileLocation = directorioFis+ "\\" + fileInputDetails.getFileName();
    	    String ext =  FilenameUtils.getExtension(fileLocation);
    	    String newNombre = nudoc+"_"+sufijo+"_"+secuencia+"."+ext;
    	    Float tamanoAnexo = (Float.parseFloat((motorAmb.leerVarAmbienteEx(ambiente, "tamanoAnexo")).isEmpty()?"0":motorAmb.leerVarAmbienteEx(ambiente, "tamanoAnexo")) * 1024 * 1024);
    	    boolean val = false;
    	    
    	    
    	    String status = null;
    	    NumberFormat myFormat = NumberFormat.getInstance();
    	    myFormat.setGroupingUsed(true);
    	     
    	    // Save the file 
    	    try {
    	     OutputStream out = new FileOutputStream(new File(fileLocation));
    	     byte[] buffer = new byte[1024];
    	     int bytes = 0;
    	     long file_size = 0; 
    	     while ((bytes = fileInputString.read(buffer)) != -1) {
    	      out.write(buffer, 0, bytes);
    	      file_size += bytes;
    	      if(tamanoAnexo != 0  && file_size > tamanoAnexo){
    	    	  //logger.info("tamaño "+file_size);
    	    	  val = true;
    	    	  break;
    	      }
    	     }
    	     out.flush();  
    	     out.close();
     
    	     
    	     File archivo = new File(fileLocation);
    	 
    	     if(val){ //eliminar archivo que exede el espacio 
     	    	 archivo.delete();
     	    	 status = "{\"tamanoAnexo\":\""+((tamanoAnexo/1024)/1024)+"\", \"error\":true}";
    	     }else{
        	     //llamar anexar documento.  	
    	    	 
        	     File archivo2 = new File(directorioFis+"\\"+newNombre);
        	     
        	     /*Verificar si el archivo existe*/
        	     if(archivo2.exists()) {
        	    	 archivo2.delete();
        	     }
        	     
        	     archivo.renameTo(archivo2);
        	     
        	     status = "{\"rutaVirtual\":\""+directorioVir+"/"+newNombre+"\", \"error\":false}";
        	     //logger.info("File has been uploaded to:" + fileLocation + ", size: " + myFormat.format(file_size) + " bytes");
        	     
    	     }
    	     

    	    } catch (IOException ex) {
    	    	logger.error("uploadFile", ex);
    	    	return Response.serverError().build();    	      
    	    }
    	 
    	    return Response.status(200).entity(status).build();
    }	
    
	/**
	 * Marca un listado de anexos como borrado o no borrado
	 * <p>
	 * 
	 * @param anexos
	 * @param borrado
	 * url: PUT http://localhost:9090/process/api/document/marcaranexo?borrado={borrado}
	 * 
	 * @return 200 ok
	 */
	@PUT
	@Path("/marcaranexo")	
	public synchronized Response marcarAnexosDocumento(List<Anexo> anexos,
			 						     @QueryParam("borrado") Boolean borrado) {
		Response response = null;
		try {			
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			documentManager.marcarAnexosDocumento(anexos, borrado);			
			response = Response.ok().build();
		} catch (Exception e) {
		    logger.error("marcarAnexosDocumento", e);
		    return Response.serverError().build();
		}
		return response;
	} 
	
	/**
	 * Ejecuta un evento process
	 * <p>
	 * 
	 * @param campo
	 * @param fila
	 * @return EventAgent
	 * url: POST http://localhost:9090/process/api/document/ejecutarevento?campo={campo}&fila={fila}
	 * 
	 * @return 200 ok
	 */
	@POST
	@Path("/ejecutarevento")	
	public synchronized Response ejecutarEventoCampo(@QueryParam("campo") String  campo,
			 						    @QueryParam("fila") Integer fila) {
		Response response = null;
		try {		
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			EventAgent responseService = documentManager.ejecutarEventoCampo(campo, fila);			
			GenericEntity<EventAgent> entity = new GenericEntity<EventAgent>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("ejecutarEventoCampo", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	/**
	 * Ejecuta un agente process
	 * <p>
	 * 
	 * @param codigo
	 * @param contexto
	 * @return EventAgent
	 * url: POST http://localhost:9090/process/api/document/ejecutaragente?codigo={codigo}&ctx={ctx}
	 * 
	 * @return 200 ok
	 */
	@POST
	@Path("/ejecutaragente")	
	public synchronized Response ejecutarAgente(@QueryParam("codigo") String codigo,
			 					   @QueryParam("ctx") String contexto) {
		Response response = null;
		try {	
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));			
			EventAgent responseService = documentManager.ejecutarAgente(codigo, contexto);			
			GenericEntity<EventAgent> entity = new GenericEntity<EventAgent>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("ejecutarAgente", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Resuelve la lista de valores evento/agente
	 * <p>
	 * 
	 * @param campo
	 * @param fila
	 * @return EventAgent
	 * url: POST http://localhost:9090/process/api/document/resolverlista?campo={campo}&fila={fila}&sel={sel}&ctx={ctx}
	 * 
	 * @return 200 ok
	 */
	@POST
	@Path("/resolverlista")	
	public synchronized Response resolverResultadoListaSQL(@QueryParam("campo") String  campo,
			 						          @QueryParam("fila") Integer fila,			 						          
			 						          @QueryParam("sel") Integer seleccion,
			 						          @QueryParam("ctx") String contexto,
			 						          @QueryParam("listSeleccion") String listSeleccion,
			 						          String resultadoXml) {
		Response response = null;
		try {
			//logger.info(listSeleccion);
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));			
			EventAgent responseService = documentManager.resolverResultadoListaSQL(campo, fila, resultadoXml, seleccion, contexto, listSeleccion);			
			GenericEntity<EventAgent> entity = new GenericEntity<EventAgent>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("resolverResultadoListaSQL", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Calcula los proximos destinos
	 * <p>
	 * 
	 * url: GET http://localhost:9090/process/api/document/destinos
	 * 
	 * @return Destino
	 */
	@GET
	@Path("/destinos")	
	public synchronized Response calcularProximosDestinos() {
		Response response = null;
		try {	
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));			
			Destino responseService = documentManager.calcularProximosDestinos();			
			GenericEntity<Destino> entity = new GenericEntity<Destino>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("calcularProximosDestinos", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Agrega puesto destino para envio
	 * <p>
	 * 
	 * @param wfa
	 * @param e
	 * @param puesto
	 * url: POST http://localhost:9090/process/api/document/agregadestino?wfa={wfa}&e={e}&puesto={puesto}
	 * 
	 * @return 200 ok
	 */
	@POST
	@Path("/agregadestino")	
	public synchronized Response agregarProximoDestino(@QueryParam("wfa") Integer wfa,
			 						      @QueryParam("e") String e,			 						          
			 						      @QueryParam("puesto") String puesto) {
		Response response = null;
		try {		
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			documentManager.agregarProximoDestino(wfa, e, puesto);			
			response = Response.ok().build();
		} catch (Exception ex) {
		    logger.error("agregarProximoDestino", ex);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Obtiene los anexos de un documento
	 * <p>
	 * 
	 * url: GET http://localhost:9090/process/api/document/anexos
	 * 
	 * @return List<Anexo>
	 */
	@GET
	@Path("/anexos")	
	public synchronized Response obtenerAnexos() {
		Response response = null;
		try {	
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));			
			List<Anexo> responseService = documentManager.obtenerAnexos();			
			GenericEntity<List<Anexo>> entity = new GenericEntity<List<Anexo>>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerAnexos", e);
		    return Response.serverError().build();
		}
		return response;
	}	
	
	/**
	 * Obtiene los agentes generales
	 * <p>
	 * 
	 * url: GET http://localhost:9090/process/api/document/agentesgenerales
	 * 
	 * @return List<Agent>
	 */
	@GET
	@Path("/agentesgenerales")	
	public synchronized Response obtenerAgentesGenerales() {
		Response response = null;
		try {	
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));			
			List<Agent> responseService = documentManager.obtenerAgentesGenerales();			
			GenericEntity<List<Agent>> entity = new GenericEntity<List<Agent>>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("obtenerAgentesGenerales", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
	/**
	 * Crea un documento
	 * @param wfp 
	 * @param frmn	 
	 * url: POST http://localhost:9090/process/api/document?wfp={wfp}&frmn={frmn}&env={env}
	 * 
	 * @return Doc Object
	 */
	@POST
	@Path("/crearDocumentoExterno")
	public synchronized Response crearDocumentoExterno(@QueryParam("wfa") Integer wfa,
								   @QueryParam("observacion") String observacion,
								   @QueryParam("env") String ambiente,
								   @QueryParam("envio") Boolean envio, 
								   Object[][] param
									) {
		Response response = null;
		try {			
			documentManager.setEngineId(Integer.valueOf(org.mule.RequestContext.getEvent().getMessage().getOutboundProperty("engineId").toString()));
			Integer responseService = documentManager.crearDocumentExterno(ambiente, wfa, param, observacion, envio);		
			GenericEntity<Integer> entity = new GenericEntity<Integer>(responseService) {};			
			response = Response.ok(entity).build();
		} catch (Exception e) {
		    logger.error("crearDocumento", e);
		    return Response.serverError().build();
		}
		return response;
	}
	
}
