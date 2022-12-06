package com.process.business.component.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.process.business.component.AdmuserExt;
import com.process.business.helper.BasicAuth;
import com.process.business.helper.ClassFactory;
import com.process.business.helper.ConexionBD;
import com.process.domain.component.Perfil;
import com.process.domain.component.Puesto;


@Service("admuserExt")
public class SimpletAdmUserExt implements AdmuserExt{
	
	private static final Logger logger = Logger.getLogger(SimpletAdmUserExt.class);
	

	
	
	@Override
	public List<Puesto> obtenerUsuarios(String usuario, String ambiente){
		List<Puesto> listp = new ArrayList<Puesto>();
		
		String sql = "select PUESTO, CD_USR_ACT, NB_USR_ACT, AP_USR_ACT, PATHANEXOS from puesto  where PUESTO like 'usersw-%'";
		
		if(usuario !=  null && !usuario.equals("")) {
			sql = sql+" and cd_usr_act = '"+usuario+"'";
		}
		
		
		Connection con =  null;
		 
		ConexionBD bd = new ConexionBD(ambiente);
		 
		 
		try {
			
			con =  bd.getDataSource().getConnection();
			Statement consulta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ResultSet resultado = consulta.executeQuery(sql);
	        //arrayResult = bd.ResultSetToArray(resultado);
	        while(resultado.next()){
	        	Puesto p = new Puesto();
	        	p.setCd_usr_act(resultado.getString("CD_USR_ACT"));
	        	p.setNb_usr_act(resultado.getString("NB_USR_ACT"));
	        	p.setAp_usr_act(resultado.getString("AP_USR_ACT"));
	        	p.setCodigoAcceso(resultado.getString("PATHANEXOS"));
	        	p.setListPerfil(obtenerPerfil(resultado.getString("PUESTO"), ambiente));
	        	listp.add(p);
	        }
	        
	        
	        //resp.setArrayResult(arrayResult);
	        //resp.setCodError("200");
		} catch (SQLException e) {
			logger.error("Error dataServices ",e);
			//resp.setCodError("800");
			//resp.setMsgError("Error BD "+e.getMessage());
			ClassFactory.setErrorCode(800);
			//return false;
		}finally{
			try {
	        	 if (null != con) {
	        		 con.close();
	        		 //logger.info("cierra conexion");
	        		 }
			} catch (SQLException e) {
				logger.error("Error dataServices close ",e);
				//resp.setCodError("801");
				ClassFactory.setErrorCode(801);
				//resp.setMsgError("Cerrar cadena de conexión "+e.getMessage());
			}
		}
		
		return listp;
	} 
	public List<Perfil> obtenerPerfil(String puesto, String ambiente){
		List<Perfil> listp = new ArrayList<Perfil>();
		
		String sql = "select NB_PRF, FE_DESDE, FE_HASTA ,IN_ACTIVO from PRF inner join PRF_PSTO psto on PRF.PRF = psto.PRF where psto.PUESTO = '"+puesto+"'";
		logger.info("sql perfil "+sql);
		if(puesto.equals("")) {
			return listp;
		}
		
		
		Connection con =  null;
		 
		ConexionBD bd = new ConexionBD(ambiente);
		 
		 
		try {
			
			con =  bd.getDataSource().getConnection();
			Statement consulta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ResultSet resultado = consulta.executeQuery(sql);
	        //arrayResult = bd.ResultSetToArray(resultado);
	        while(resultado.next()){
	        	Perfil p = new Perfil();
	        	p.setNb_prf(resultado.getString("NB_PRF"));
	        	p.setFe_desde(resultado.getString("FE_DESDE"));
	        	p.setFe_hasta(resultado.getString("FE_HASTA"));
	        	p.setIn_activo(resultado.getString("IN_ACTIVO"));
	        	listp.add(p);
	        }
	        
	        
	        //resp.setArrayResult(arrayResult);
	        //resp.setCodError("200");
		} catch (SQLException e) {
			logger.error("Error dataServices ",e);
			//resp.setCodError("800");
			//resp.setMsgError("Error BD "+e.getMessage());
			ClassFactory.setErrorCode(800);
			//return false;
		}finally{
			try {
	        	 if (null != con) {
	        		 con.close();
	        		 //logger.info("cierra conexion");
	        		 }
			} catch (SQLException e) {
				logger.error("Error dataServices close ",e);
				//resp.setCodError("801");
				ClassFactory.setErrorCode(801);
				//resp.setMsgError("Cerrar cadena de conexión "+e.getMessage());
			}
		}
		
		return listp;
	}
	
	@Override
	public List<Puesto> agregarUsuario(List<Puesto> lp, String ambiente){
		List<Puesto> listp = new ArrayList<Puesto>();		
		
		ConexionBD bd = new ConexionBD(ambiente.toUpperCase());
		Connection con = null;
		try {
			con =  bd.getDataSource().getConnection();
			Statement stmt = con.createStatement();
		    con.setAutoCommit(false);
		    
		     for(Puesto p : lp) {
		    	 	Puesto pr = new Puesto();
					Boolean flagPuesto = false;
					Boolean flagUser = true;
					Boolean flagUserH = true;
					Integer ultPuesto = 1;
					Integer nro=0;
					//EncriptBin bin = new EncriptBin();
					//20900101userWS123456789**-;
					String claveStatica = "userWS123456789**-";
					String pwd = "7700023AF136644656B4DCBA6799CC30969A4F3CD8B9BD657169D0";
					String puestoWS = "";
					String nbPuestoWS = "";
					//p.setPw_usr_act("7700023AF136644656B4DCBA6799CC30969A4F3CD8B9BD657169D0");
					
					String pregunta = "S>?J)#&J58$J4O)*+7+J$7-J2,8B787J4]>@$^H(LG! 5#./\"C4) ?:GHL&!J*2I*";
					//Valor de pregunta 01/01/2004^pregunta inactiva^aksdvkndff***-*-*
					//logger.info("Password "+bin.GenerarPassword("123456"));
					//p.setPw_usr_act(bin.GenerarPassword("generar"));
					 /*verificar cual es el ultino puesto creado con el servicio
					  * Acronimo 
					  * 	userweb-1
					  * 
					  * */
					 
					 String sqlValP = "select COUNT(*) as contador , max(nro_ident) as ultPuesto from (select PUESTO, NRO_IDENT from PUESTO where PUESTO like 'usersw%' union select PUESTO, NRO_IDENT from PUESTOh where PUESTO like 'usersw%' ) as a";
					 ResultSet r1 = stmt.executeQuery(sqlValP);
					 logger.info("puesto maximo "+sqlValP);
					 
					 while(r1.next()){
						 flagPuesto = r1.getInt("contador")>0?true:false;
						 ultPuesto = r1.getInt("ultPuesto");
				     }
					 
					 
					 if(flagPuesto) {
						nro = ultPuesto+1;
						logger.info("ver calculo "+"usersw-"+nro);
						//p.setPuesto("usersw-"+nro);
						puestoWS = "usersw-"+nro;
						logger.info("ver puesto asignado "+puestoWS+" usuario "+p.getCd_usr_act());
						nbPuestoWS = "usersw-"+nro;
						//p.setNb_puesto("usersw-"+nro);
					 }else {
						puestoWS = "usersw-1";
						nbPuestoWS = "usersw-1";
						nro = 1;
						 //p.setPuesto("usersw-1"); 
						 //p.setNb_puesto("usersw-1");
					 }
					 		    	 
					 String sqlCUA = "select count(*) as contadorUsuario from PUESTO where CD_USR_ACT = '"+p.getCd_usr_act()+"'";
					 logger.info("usuario "+sqlCUA);
					 ResultSet r2 = stmt.executeQuery(sqlCUA);
					 while(r2.next()){
						 flagUser = r2.getInt("contadorUsuario")>0?true:false;
				      }
					 
					 
					 if(!flagUser){//si los usuarios no existen se inserta
						 
						 String sqlCUAH = "select count(*) as contadorUsuarioh from PUESTOH where CD_USR_ACT = '"+p.getCd_usr_act()+"'";
						 ResultSet r3 = stmt.executeQuery(sqlCUAH);
						 while(r3.next()){
							 flagUserH = r3.getInt("contadorUsuarioh")>0?true:false;
					      }
						 
						 if(!flagUserH){//si los usuarios no existen se inserta
							 /*Codigo de acceso*/
							 p.setCodigoAcceso(BasicAuth.encode(p.getCd_usr_act()+":"+claveStatica));
							 String sqlPuesto = "INSERT into puesto (puesto,nb_puesto,cd_usr_act,nb_usr_act,pw_usr_act,email,fe_desde,ap_usr_act,IN_ADMWG,IN_ADMUSR,IN_VERESTA, PATHANEXOS, nro_ident, PREGUNTA, in_reempl, in_cambfr, in_cambpr) values (?,?,?,?,?,?,GETDATE()-1,?,?,?,?,?,?,?,'N','N','N')";
								
							 	PreparedStatement statement = con.prepareStatement(sqlPuesto, Statement.RETURN_GENERATED_KEYS);
							 	logger.info("puesto a registrar "+puestoWS);
								statement.setString(1, puestoWS);
								statement.setString(2, nbPuestoWS);
								statement.setString(3, p.getCd_usr_act());
								statement.setString(4, p.getNb_usr_act());
								statement.setString(5, pwd);
								statement.setString(6, p.getEmail());
								//statement.setString(7, "GETDATE()-1");
								statement.setString(7, p.getAp_usr_act());
								statement.setString(8, "N");
								statement.setString(9, "N");
								statement.setString(10, "N");
								statement.setInt(12, nro);
								statement.setString(11,  p.getCodigoAcceso());
								statement.setString(13,  pregunta);
								
								int affectedRows = statement.executeUpdate();
								logger.info("insert "+affectedRows);
								if (affectedRows == 0) {
						               throw new SQLException("Registrando de auditoria fallido");
						        }else {
						        	Boolean fp =registrarPerfil(p, ambiente, puestoWS);
						        	if(!fp) {
						        		 pr.setCd_usr_act(p.getCd_usr_act());
										 pr.setMsj("Registrando perfil del usuario ");
										 listp.add(pr);
										 con.rollback();
						        		throw new SQLException("Registrando perfil del usuario "+p.getCd_usr_act());
						        		
						        	}
						        }
								
								/*Armando respuesta de usuarios creados*/
								pr.setCd_usr_act(p.getCd_usr_act());
								pr.setCodigoAcceso(p.getCodigoAcceso());
								pr.setMsj("Usuario registrado con exito");
								con.commit();
						 }else {
							 logger.info("Usuario existe en historico "+p.getCd_usr_act());
							 pr.setCd_usr_act(p.getCd_usr_act());
							 pr.setMsj("Usuario Existe en la tabla historica");
						 }
						
					 }else {
						 logger.info("Usuario existe "+p.getCd_usr_act());
						 pr.setCd_usr_act(p.getCd_usr_act());
						 pr.setMsj("Usuario Existe");
					 }
					 
					 listp.add(pr);
				}
		     
		} catch (Exception e) {
			logger.info("Error insertando usuario servicio externo ", e);
			ClassFactory.setErrorCode(800);
			
		}finally{
			try {
	        	 if (null != con) {
	        		 con.close();
	        		 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.info("Error insertando usuario servicio externo ", e);
			}
		}
		
		return listp;
	}
	
	@Override
	public Boolean eliminarUsuairo(String usuario, String ambiente) {
		logger.info("Ingresa en metodo");
		Boolean flag = false;
		Connection con = null;
		ConexionBD bd = new ConexionBD(ambiente);
		
		//String sqlHistorico = "insert into PUESTOH select PUESTO, NB_PUESTO, CD_USR_ACT, NB_USR_ACT, '1', FE_DESDE, GETDATE(), null, EMAIL, AP_USR_ACT, CD_TPO_IDENT, NRO_IDENT, TLF_USR_ACT, FOTO, NU_DOC from puesto where puesto = ?";
		String sqlHistorico = "insert into PUESTOH (PUESTO, NB_PUESTO, CD_USR_ACT, NB_USR_ACT, PUESTO_S, FE_DESDE, FE_HASTA, TP_UP, EMAIL, AP_USR_ACT, CD_TPO_IDENT, NRO_IDENT, TLF_USR_ACT, FOTO, NU_DOC)  select PUESTO, NB_PUESTO, CD_USR_ACT, NB_USR_ACT, '1', FE_DESDE, GETDATE(), null, EMAIL, AP_USR_ACT, CD_TPO_IDENT, NRO_IDENT, TLF_USR_ACT, FOTO, NU_DOC from puesto where puesto = ?";		 
		
		String sqlDelete = "delete from puesto where puesto = ?";
		
		String puesto = obtenerPuestoToUsuario(usuario, ambiente);
		logger.info("Puesto a eliminar "+puesto);
		try {
			con =  bd.getDataSource().getConnection();
			con.setAutoCommit(false);
			
			
			PreparedStatement statement = con.prepareStatement(sqlHistorico, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, puesto);
			int affectedRows = statement.executeUpdate();
			logger.info("Inserta Puentoh "+affectedRows);
			if (affectedRows == 0) {
				   con.rollback();
	               throw new SQLException("Error Registrando historico de usuario");
	        }else {
	        	logger.info("Elimina puesto");
	        	PreparedStatement st = con.prepareStatement(sqlDelete);
	            st.setString(1,puesto);
	            st.executeUpdate(); 
	            eliminarPerfil("", ambiente,puesto);
	        }
			logger.info("Realizaa el commit");
			con.commit();
			flag = true;
		} catch (SQLException e) {
			logger.error("eliminarUsuairo error ",e);
			flag = false;
		}finally{
			try {
	        	 if (null != con) {
	        		 con.close();
	        		 }
			} catch (SQLException e) {
				logger.error("eliminarUsuairo error ",e);
				flag = false;
			}
		}
		
		return flag;
		
	}
	
	@Override
	public Boolean registrarPerfil(Puesto p, String ambiente, String puestoIns) {
		ConexionBD bd = new ConexionBD(ambiente.toUpperCase());
		Connection con = null;
		Boolean flag = false;
		String puesto = puestoIns;
		if(puestoIns.equals("")) {
			puesto = obtenerPuestoToUsuario(p.getCd_usr_act(), ambiente);
		}
		
		try {
			con =  bd.getDataSource().getConnection();
		    con.setAutoCommit(false);
		
		    String sqlPerfil = "INSERT INTO PRF_PSTO (PUESTO,IN_ACTIVO,FE_DESDE,GRP,PRF) VALUES (?,?, getdate()-1,?,?)";
		    for(Perfil per: p.getListPerfil()) {
		    	
				PreparedStatement statement = con.prepareStatement(sqlPerfil, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, puesto);
				statement.setString(2, "S");
				statement.setString(3, per.getGrp());
				statement.setString(4, per.getPrf());
				logger.info("prf_psto	\r\n"+statement);
				int affectedRows = statement.executeUpdate();
				if (affectedRows == 0) {
		               throw new SQLException("Error registrando de Perfiles");
		        }
			}
		    
		    flag = true;
		    con.commit();
		} catch (Exception e) {
			logger.info("Error registrarPerfil externo ", e);
			ClassFactory.setErrorCode(800);
			flag = false;
		}finally{
			try {
	        	 if (null != con) {
	        		 con.close();
	        		 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.info("Error registrarPerfil externo ", e);
			}
		}
		return flag;		
	}
	
	@Override
	public Boolean eliminarPerfil(String usuario, String ambiente, String puestoIns){
		Boolean flag = true;
		Connection con = null;
		ConexionBD bd = new ConexionBD(ambiente);
		String puesto = puestoIns;
		String sqlDelete = "delete from PRF_PSTO where PUESTO  = ?";
		
		if(puestoIns.equals("")) {
			puesto = obtenerPuestoToUsuario(usuario, ambiente);
		}
		
		try {
			con =  bd.getDataSource().getConnection();
			PreparedStatement st = con.prepareStatement(sqlDelete);
            st.setString(1,puesto);
            st.executeUpdate(); 
	        
			flag = true;
		} catch (SQLException e) {
			logger.error("eliminarPerfil error ",e);
			flag = false;
		}finally{
			try {
	        	 if (null != con) {
	        		 con.close();
	        		 }
			} catch (SQLException e) {
				logger.error("eliminarPerfil error ",e);
				flag = false;
			}
		}
		
		return flag;
	}
	
	
	public String obtenerPuestoToUsuario(String usurio, String ambiente){
		String puesto = "";
		
		String sql = "select PUESTO from puesto  where cd_usr_act = '"+usurio+"'";
		logger.info(sql);
		Connection con =  null;
		ConexionBD bd = new ConexionBD(ambiente);
		 
		try {
			
			con =  bd.getDataSource().getConnection();
			Statement consulta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ResultSet resultado = consulta.executeQuery(sql);
	        //arrayResult = bd.ResultSetToArray(resultado);
	        while(resultado.next()){
	        	puesto = resultado.getString("PUESTO");
	        }
		} catch (SQLException e) {
			logger.error("Error CONSULTANDO PUESTO ",e);
			ClassFactory.setErrorCode(800);
		}finally{
			try {
	        	 if (null != con) {
	        		 con.close();
	        		 }
			} catch (SQLException e) {
				logger.error("Error dataServices close ",e);
				ClassFactory.setErrorCode(801);
			}
		}
		logger.info("usuario "+usurio+" puesto "+puesto);
		return puesto;
	} 
	
}
