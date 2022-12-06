/**
 * BasicAuth.java
 *
 */
package com.process.business.helper;

import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

/**
 * Util BasicAuth class for All Modules 
 * @author Oswel Sanchez
 */
public class BasicAuth {

    public synchronized static String[] decode(String auth) {
        //Replacing "Basic THE_BASE_64" to "THE_BASE_64" directly
        auth = auth.replaceFirst("[B|b]asic ", "");
 
        //Decode the Base64 into byte[]
        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(auth);
 
        //If the decode fails in any case
        if(decodedBytes == null || decodedBytes.length == 0){
            return null;
        }
 
        //Now we can convert the byte[] into a splitted array :
        //  - the first one is login,
        //  - the second one password
        return new String(decodedBytes).split(":", 2);
    }
    
    public synchronized static String encode(String auth) { 
        byte[] message = null;
        try {
            message = auth.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
              e.printStackTrace();
            }
        String encode = DatatypeConverter.printBase64Binary(message);
        return encode;
    }

    public synchronized static String decodeParameter(String auth) {
        //Replacing "Basic THE_BASE_64" to "THE_BASE_64" directly
        auth = auth.replaceFirst("[B|b]earer ", "");
 
        //If the decode fails in any case
        if(auth == null || auth.length() == 0){
            return null;
        }

        return new String(auth);
    }    
    
    public synchronized static String decodeString(String auth) {
 
        //Decode the Base64 into byte[]
        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(auth);
 
        //If the decode fails in any case
        if(decodedBytes == null || decodedBytes.length == 0){
            return null;
        }
 
        //Now we can convert the byte[] into a splitted array :
        //  - the first one is login,
        //  - the second one password
        return new String(decodedBytes);
    }
}
