/**
 * UploadResource.java
 *
 * Copyright (c) 2011 Homboo, C.A. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Homboo, C.A. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Homboo.
 */
package com.process.services.rest.document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.process.business.document.DocumentManager;

/**
 *
 * Resource modules REST for Admin module
 * @author Ing. Oswel Sanchez
 */
@Path("/upload")
public class UploadResource {
    
    private static final Logger logger = Logger.getLogger(UploadResource.class);
    
    @Autowired
	private DocumentManager documentManager;    

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("text/html")
    public Response uploadFile(
    	      @FormDataParam("file") final InputStream fileInputString,
    	      @FormDataParam("file") FormDataContentDisposition fileInputDetails) {
    	  
    	    String fileLocation = "c://Temp/" + fileInputDetails.getFileName();
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
    	     }
    	     out.flush();  
    	     out.close();
    	             
    	     logger.info(String.format("Inside uploadFile==> fileName: %s,  fileSize: %s", 
    	          fileInputDetails.getFileName(), myFormat.format(file_size)));
    	             
    	     status = "File has been uploaded to:" + fileLocation 
    	                 + ", size: " + myFormat.format(file_size) + " bytes";
    	    } catch (IOException ex) {
    	      logger.error("Unable to save file: "  + fileLocation);
    	      ex.printStackTrace();
    	    }
    	 
    	    return Response.status(200).entity(status).build();
    }


}
