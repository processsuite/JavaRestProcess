package com.process.business.component.impl;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.process.business.component.ComponentEmailManager;
import com.process.domain.component.Email;
import com.process.domain.component.FileEmail;


@Service("componentEmailManager")
public class SimpleComponentEmailManager implements ComponentEmailManager{

	private static final Logger logger = Logger.getLogger(SimpleComponentEmailManager.class);
	
	@Override
	public Boolean sendMail(Email email){
		Boolean resp= false;
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.

	    		//process2b.smtp@gmail.com;process2b*;true;465
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", email.getSmtp());  //El servidor SMTP de Google
	    props.put("mail.smtp.auth", email.getAuthUsuClave());    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", email.getStartTls()); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.mail.sender",email.getRemitente());
	    props.put("mail.smtp.port", email.getPort()); //El puerto SMTP seguro de Google
	    
	    props.put("mail.smtp.user", email.getUsuario());
	    //props.put("mail.smtp.clave", email.getClave());    //La clave de la cuenta

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);
	    
	    String[] recipientList = email.getDestinatario().split(";");
	    InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
	    int counter = 0;

	    try {
	    	/*si son multiple destinatarios*/
	    	for (String recipient : recipientList) {
		        recipientAddress[counter] = new InternetAddress(recipient.trim());
		        counter++;
		    }
	    	
	    	// Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setText(email.getCuerpo());
        
         // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            //multiParte.addBodyPart(adjunto);
            //Multipart multipart = new MimeMultipart("mixed");
            if(email.getListFile().size() > 0){
            	for (FileEmail listFile : email.getListFile()) {
                    MimeBodyPart messageBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(listFile.getRutaArchivo());
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(listFile.getNombreArchivo());
                    multiParte.addBodyPart(messageBodyPart);
                }
            }
            
            
            
	    	
	        /*Correo*/
            message.setFrom(new InternetAddress(email.getRemitente(),email.getRemitente() ));
	        message.addRecipients(Message.RecipientType.TO, recipientAddress);   //Se podrían añadir varios de la misma manera
	        message.setSubject(email.getAsunto());
	        message.setText(email.getCuerpo());
	        message.setContent(multiParte);
	        
	     
            
	        //envio de correo
	        Transport transport = session.getTransport("smtp");
	        transport.connect(email.getSmtp(), email.getUsuario(),email.getClave());
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	        resp=true;
	    }
	    catch (MessagingException | UnsupportedEncodingException me) {
	        logger.error(me.getMessage()+" "+me.getCause());   //Si se produce un error
	         resp=false;
	    }
	    return resp;
	}
}
