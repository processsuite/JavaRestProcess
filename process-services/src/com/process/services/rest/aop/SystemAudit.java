/**
 * SystemAudit.java
 *
 */
package com.process.services.rest.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Business Aspect SystemArchitecture for Admin Module 
 * @author Oswel Sanchez
 * 
 */

public class SystemAudit {
    
    private static final Logger logger = Logger.getLogger(SystemAudit.class);
    
    public Object injectAround(ProceedingJoinPoint thisJoinPoint) throws Throwable {
    	//logger.info("running :" + thisJoinPoint.toString());
    	Object retVal = thisJoinPoint.proceed();
		return retVal;
	}
 
}