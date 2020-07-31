/**
 * DocumentManager.java 
 * 
 */
package com.process.business.document;

import java.util.List;

import com.process.domain.auth.Engine;
import com.process.domain.document.Agent;
import com.process.domain.document.Anexo;
import com.process.domain.document.Destino;
import com.process.domain.document.Doc;
import com.process.domain.document.EventAgent;
import com.process.domain.document.Form;
import com.process.domain.document.RespDataService;
import com.process.domain.document.SendMsg;
import com.process.domain.document.WfDest;
import com.process.domain.document2.Doc2;
import com.process.domain.document2.Forma;

/**
 * Business Interface DocumentManager for document Module 
 * @author Oswel Sanchez
 * 
 */
/**
 * @author Oswel
 *
 */
public interface DocumentManager extends Engine {

	/**
	 * Crea un documento 
	 * @param wfp 
	 * @param frmn 
	 * @param environment 
	 * @return Doc
	 */
	public Doc crearDocumento(Integer wfp, Integer frmn, String environment); 
	
	public Doc2 crearDocumento1(Integer wfp, Integer frmn, String environment);	
	/**
	 * Abrir un documento en modo lectura
	 * @param nuDoc
	 * @param nuInst
	 * @param environment 
	 * @return Doc
	 */
	public Doc abrirDocumentoLectura(Integer nuDoc, Integer nuInst, String environment);
	
	
	public Doc2 abrirDocumentoLectura1(Integer nuDoc, Integer nuInst, String environment);

	/**
	 * Abrir un documento
	 * @param nuDoc
	 * @param nuInst
	 * @param wfa
	 * @param environment  
	 * @return Doc
	 */
	public Doc abrirDocumento(Integer nuDoc, Integer nuInst, Integer wfa, String environment);	
	
	public Doc2 abrirDocumento1(Integer nuDoc, Integer nuInst, Integer wfa, String environment);
	
	/**
	 * Obtiene un documento
	 * @param frmn 
	 * @return Doc
	 */
	public Doc obtenerDocumento(Integer frmn);	
	
	/**
	 * Obtiene un documento prueba para rapidez
	 * @param frmn 
	 * @return Doc
	 */
	public Doc2 obtenerDocumento1(Integer frmn);	
	
	
	
	/**
	 * Obtiene un documento en modo lectura
	 * @param frmn 
	 * @return Doc
	 */
	public Doc obtenerDocumentoLectura(Integer frmn);	
	
	public Doc2 obtenerDocumentoLectura1(Integer frmn);
	/**
	 * Asigna un valor a un campo del documento
	 * @param campo
	 * @param valor
	 * @param fila
	 * @param columna
	 */
	public void asignarValorCampoDocumento(String campo, String valor, Integer fila, Integer columna);		

	/**
	 * Guarda un form de un documento
	 * @param frmn
	 * @param form
	 */
	public void guardarform(Integer frmn, Form form);
	
	public void guardarForm1(Integer frmn, Forma forma);
	
	/**
	 * Guarda el documento en sesion de usuario
	 * @param observacion
	 */
	public void guardarDocumento(String observacion);	
	
	/**
	 * Cierra un documento
	 */
	public void cerrarDocumento();
	
	/**
	 * Cierra un documento en modo lectura
	 */
	public void cerrarDocumentoLectura();	
	
	/**
	 * Adquiere un documento para un usuario
	 * @param observacion
	 * @param email
	 * @return SendMsg  
	 */
	public SendMsg adquirirDocumento(String observacion, Boolean email);
	
	/**
	 * Recupera un documento para usuario
	 */
	public void recuperarDocumento();
	
	/**
	 * Anula un documento para un usuario
	 * @param observacion
	 * @param email
	 * @return SendMsg 
	 */
	public SendMsg anularDocumento(String observacion, Boolean email);
	
	/**
	 * Rechaza un documento para un usuario
	 * @param observacion
	 * @param urgente
	 * @param email
	 * @param conCopiaEmailA
	 * @param frmnCopia
	 * @return SendMsg 
	 */
	public SendMsg objetarDocumento(String observacion, Boolean urgente, Boolean email, String conCopiaEmailA, Integer frmnCopia);
	
	/**
	 * Avanza un documento para un usuario
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
	 * @return SendMsg
	 */
	public SendMsg avanzarDocumento(String firma, String pregunta, String respuesta, String observacion, Boolean urgente, Boolean email, Integer gradoSatisfaccion, String seleccionResultadoXml, String conCopiaEmailA, Integer frmnCopia, List<WfDest> destinos);
	
	/**
	 * Anexa un documento 
	 * @param tipoSource
	 * @param source
	 * @param ext
	 * @param descripcion
	 * @param asunto
	 * @param autor
	 */
	public void anexarDocumento(Integer tipoSource, String source, String ext, String descripcion, String asunto, String autor);
	
	/**
	 * Marca un listados de anexos como borrado o no borrado
	 * @param anexos
	 * @param borrado
	 */
	public void marcarAnexosDocumento(List<Anexo> anexos, Boolean borrado);
	
	/**
	 * Ejecuta un evento process
	 * @param campo
	 * @param fila
	 * @return EventAgent
	 */
	public EventAgent ejecutarEventoCampo(String campo, Integer fila);
	
	/**
	 * Ejecuta un agente process
	 * @param codigo
	 * @param contexto
	 * @return EventAgent
	 */
	public EventAgent ejecutarAgente(String codigo, String contexto);	
	
	/**
	 * Resuelve la lista de valores evento/agente
	 * @param campo
	 * @param fila
	 * @param resultadoXml
	 * @param seleccion
	 * @param contexto
	 * @return EventAgent
	 */
	public EventAgent resolverResultadoListaSQL(String campo, Integer fila, String resultadoXml, Integer seleccion, String contexto, String listaSelect);
	
	/**
	 * Calcula los proximos destinos
	 * @return Destino
	 */
	public Destino calcularProximosDestinos();
	
	/**
	 * Agrega puesto destino para envio
	 * @param wfa
	 * @param e
	 * @param puesto
	 */
	public void agregarProximoDestino(Integer wfa, String e, String puesto);
	
	/**
	 * Obtiene los anexos de un documento
	 * @return List<Anexo>
	 */
	public List<Anexo> obtenerAnexos();
	
	/**
	 * Obtiene los agentes generales
	 * @return List<Agent>
	 */
	public List<Agent> obtenerAgentesGenerales();
	
	/**
	 *Consulta servicios configurados a partir e un XML 
	 *@return RespDaraService 
	 */
	public RespDataService dataServices(String ambiente, String idQuery,Object[][] param);

	/*
	 * Crea un documento desde servicios externos al cliente comun
	 * @return Integer nuDoc
	 */
	public Integer crearDocumentExterno(String ambiente, Integer wfa, Object[][] param, String observacion);

}
