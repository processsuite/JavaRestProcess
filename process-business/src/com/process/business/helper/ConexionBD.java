package com.process.business.helper;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.dbcp2.BasicDataSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.log4j.Logger;


public class ConexionBD {
	private static final Logger logger = Logger.getLogger(ConexionBD.class);
	private static BasicDataSource basicDataSource=null;
	private String driverJDBC;// = "oracle.jdbc.driver.OracleDriver"; 
	private String cadena;// = "jdbc:oracle:thin://localhost:1521:orcl";
	private String user;// = "USUARIO_SENIAT";
	private String clave;// = "root";
	
	
	
	public ConexionBD(String ambiente){
		URL fileLocation = getClass().getClassLoader().getResource("DataSourceServicesProcess.xml");
		File archivo = new File(fileLocation.getFile());
		try{
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
		        Document document = documentBuilder.parse(archivo);
		        document.getDocumentElement().normalize();
		        
				// Element element = document.getDocumentElement();
				NodeList listServ = document.getElementsByTagName(ambiente);
		        Node nodo = listServ.item(0);
		        Element element = (Element) nodo;
			        
			        clave = element.getElementsByTagName("clave").item(0).getTextContent();
			        user = element.getElementsByTagName("usuario").item(0).getTextContent();
			        driverJDBC = element.getElementsByTagName("driverJdbc").item(0).getTextContent();
			        cadena = element.getElementsByTagName("cadena").item(0).getTextContent();
			        
			        //logger.info("element driverJDBC "+driverJDBC+" "+clave+" "+user+" "+cadena);
			}catch(Exception e){
				logger.error("error  ConexionBD "+e.getMessage()+" codigo "+e.getLocalizedMessage());
			}
		
		if (null==basicDataSource){
            basicDataSource = new BasicDataSource();
            basicDataSource.setDriverClassName(driverJDBC);
            basicDataSource.setUsername(user);
            basicDataSource.setPassword(clave);
            basicDataSource.setUrl(cadena);
           // basicDataSource.setMaxActive(200);
            basicDataSource.setMinIdle(50);
            basicDataSource.setMaxIdle(100);
        }
	}
	
	public BasicDataSource getDataSource(){
        return basicDataSource;
    }
	
    public Object[][] ResultSetToArray(ResultSet rs)
    {
        Object obj[][]=null;
         try
        {
         rs.last();
         ResultSetMetaData rsmd = rs.getMetaData();
         int numCols = rsmd.getColumnCount();
         int numFils =rs.getRow();
         obj=new Object[numFils][numCols];
         int j = 0;
         rs.beforeFirst();
         while (rs.next())
        {
           for (int i=0;i<numCols;i++)
            {
                 obj[j][i]=rs.getObject(i+1);
            }
            j++;
         }
        }
        catch(Exception e)
        {
        	logger.error("error  ResultSetToArray "+e.getMessage()+" codigo "+e.getLocalizedMessage());
        }
        return obj;
    }

		public String getDriverJDBC() {
			return driverJDBC;
		}
	
		public void setDriverJDBC(String driverJDBC) {
			this.driverJDBC = driverJDBC;
		}
	
		public String getCadena() {
			return cadena;
		}
	
		public void setCadena(String cadena) {
			this.cadena = cadena;
		}
	
		public String getUser() {
			return user;
		}
	
		public void setUser(String user) {
			this.user = user;
		}
	
		public String getClave() {
			return clave;
		}
	
		public void setClave(String clave) {
			this.clave = clave;
		}   
}
