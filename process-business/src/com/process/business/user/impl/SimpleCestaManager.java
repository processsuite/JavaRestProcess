/**
 * SimpleCestaManager.java 
 *
 */
package com.process.business.user.impl;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.process.business.helper.ClassFactory;
import com.process.business.helper.c_Process;
import com.process.business.user.CestaManager;
import com.process.domain.user.Anexo;
import com.process.domain.user.Cesta;
import com.process.domain.user1.Cesta1;
import com.process.domain.user.CestaAnexo;
import com.process.domain.user.Conversacion;
import com.process.domain.user.Inst;
import com.process.domain.user.InstSeg;
import com.process.domain.user.Proceso;
import com.process.domain.user.RelatedSeg;
import com.process.domain.user.Seguimiento;

/**
 * Business Implementation SimpleCestaManager for user Module 
 * @author Oswel Sanchez
 * 
 */
@Service("cestaManager")
public class SimpleCestaManager implements CestaManager  {
    
	private static final Logger logger = Logger.getLogger(SimpleCestaManager.class);
	
	private c_Process motor;
	
	private Integer engineP;
	
	@Override
	public void setEngineId(Integer engineId) {
		engineP = engineId;
	}	
	
	@Override
	public Cesta obtenerCesta(Integer tipo, Integer desde, Integer nuDoc, Integer wfp, Integer wfa, String feIni, String feFin, String detalle) {
		Cesta cesta = new Cesta();
		try{
			motor = ClassFactory.getProcess(engineP);
			String resultXml = motor.p4bObtenerCesta(tipo, desde, nuDoc, wfp, wfa, feIni, feFin, detalle);
			//logger.info("obtenerCesta : "+resultXml);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			
			cesta.setFecha(xpath.compile("/cesta/@fecha").evaluate(document, XPathConstants.STRING).toString());
			cesta.setTipo(Integer.valueOf(xpath.compile("/cesta/@tipo").evaluate(document, XPathConstants.STRING).toString()));
			cesta.setPagina(Integer.valueOf(xpath.compile("/cesta/@pagina").evaluate(document, XPathConstants.STRING).toString()));
			cesta.setDesde(Integer.valueOf(xpath.compile("/cesta/@desde").evaluate(document, XPathConstants.STRING).toString()));
			cesta.setTotal(Integer.valueOf(xpath.compile("/cesta/@total").evaluate(document, XPathConstants.STRING).toString()));
			
			NodeList nodes = document.getElementsByTagName("inst");
			for(int i=0;i < nodes.getLength(); i++){				
				Inst inst = new Inst();
				if (xpath.compile("/cesta /inst[" + (i+1) + "]/nu_doc_opened").evaluate(document, XPathConstants.STRING).toString().trim().equals("N")){
					inst.setNuDocOpened(false);
				}else{
					inst.setNuDocOpened(true);
				}
				inst.setNuDoc(Integer.valueOf(xpath.compile("/cesta /inst[" + (i+1) + "]/nu_doc").evaluate(document, XPathConstants.STRING).toString()));
				inst.setNuInst(Integer.valueOf(xpath.compile("/cesta /inst[" + (i+1) + "]/nu_inst").evaluate(document, XPathConstants.STRING).toString()));
				inst.setWfp(Integer.valueOf(xpath.compile("/cesta /inst[" + (i+1) + "]/wfp").evaluate(document, XPathConstants.STRING).toString()));
				inst.setWfa(Integer.valueOf(xpath.compile("/cesta /inst[" + (i+1) + "]/wfa").evaluate(document, XPathConstants.STRING).toString()));
				inst.setNbWf(xpath.compile("/cesta /inst[" + (i+1) + "]/nb_wf").evaluate(document, XPathConstants.STRING).toString());
				inst.setE(xpath.compile("/cesta /inst[" + (i+1) + "]/e").evaluate(document, XPathConstants.STRING).toString());
				inst.setEp(xpath.compile("/cesta /inst[" + (i+1) + "]/ep").evaluate(document, XPathConstants.STRING).toString());
				inst.setFeIni(xpath.compile("/cesta /inst[" + (i+1) + "]/fe_ini").evaluate(document, XPathConstants.STRING).toString());
				inst.setTxObserva(xpath.compile("/cesta /inst[" + (i+1) + "]/txobserva").evaluate(document, XPathConstants.STRING).toString());
				inst.setDetalle(xpath.compile("/cesta /inst[" + (i+1) + "]/detalle").evaluate(document, XPathConstants.STRING).toString());
				inst.setDias(xpath.compile("/cesta /inst[" + (i+1) + "]/dias").evaluate(document, XPathConstants.STRING).toString());
				inst.setTiempo(xpath.compile("/cesta /inst[" + (i+1) + "]/tiempo").evaluate(document, XPathConstants.STRING).toString());
				if (xpath.compile("/cesta /inst[" + (i+1) + "]/retraso").evaluate(document, XPathConstants.STRING).toString().trim().equals("N")){
					inst.setRetraso(false);
				}else{
					inst.setRetraso(true);
				}
				inst.setOrigen(xpath.compile("/cesta /inst[" + (i+1) + "]/origen").evaluate(document, XPathConstants.STRING).toString());
				inst.setNbPuesto(xpath.compile("/cesta /inst[" + (i+1) + "]/nb_puesto").evaluate(document, XPathConstants.STRING).toString());
				inst.setNbPersona(xpath.compile("/cesta /inst[" + (i+1) + "]/nb_persona").evaluate(document, XPathConstants.STRING).toString());
				inst.setAlerta(Integer.valueOf(xpath.compile("/cesta /inst[" + (i+1) + "]/alerta").evaluate(document, XPathConstants.STRING).toString()));
				if (xpath.compile("/cesta /inst[" + (i+1) + "]/semaforo").evaluate(document, XPathConstants.STRING).toString()!=""){
					inst.setSemaforo(Integer.valueOf(xpath.compile("/cesta /inst[" + (i+1) + "]/semaforo").evaluate(document, XPathConstants.STRING).toString()));
				}				
				if (xpath.compile("/cesta /inst[" + (i+1) + "]/in_espera").evaluate(document, XPathConstants.STRING).toString().trim().equals("N")){
					inst.setInEspera(false);
				}else{
					inst.setInEspera(true);
				}				
				if (xpath.compile("/cesta /inst[" + (i+1) + "]/in_urgente").evaluate(document, XPathConstants.STRING).toString().trim().equals("N")){
					inst.setInUrgente(false);
				}else{
					inst.setInUrgente(true);
				}	
				inst.setFever(xpath.compile("/cesta /inst[" + (i+1) + "]/fever").evaluate(document, XPathConstants.STRING).toString());
				if (xpath.compile("/cesta /inst[" + (i+1) + "]/frmn").evaluate(document, XPathConstants.STRING).toString()!=""){
					inst.setFrmn(Integer.valueOf(xpath.compile("/cesta /inst[" + (i+1) + "]/frmn").evaluate(document, XPathConstants.STRING).toString()));					
				}				
				cesta.getInsts().add(inst);
			}				
		}catch(Exception e){
			logger.error("obtenerCesta:", e);
		}
		return cesta;
	}
	
	@Override
	public Cesta1 obtenerCesta1(Integer tipo, Integer desde, Integer nuDoc, Integer wfp, Integer wfa, String feIni, String feFin, String detalle) {
		Cesta1 cesta = new Cesta1();
		try{
			motor = ClassFactory.getProcess(engineP);
			String resultXml = motor.p4bObtenerCesta(tipo, desde, nuDoc, wfp, wfa, feIni, feFin, detalle);
			//logger.info("obtenerCesta : "+resultXml);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			JAXBContext context = JAXBContextFactory.createContext(new Class[] {Cesta1.class}, null);
			
			Unmarshaller un = context.createUnmarshaller();
			cesta = (Cesta1) un.unmarshal(document);

		}catch(Exception e){
			logger.error("obtenerCesta:", e);
		}
		return cesta;
	}



	@Override
	public Seguimiento obtenerSeguimiento(Integer tipo, Integer nuDoc) {
		Seguimiento seg = new Seguimiento();
		try{
			motor = ClassFactory.getProcess(engineP);
			String resultXml = motor.p4bObtenerSeguimiento(tipo, nuDoc);			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			
			seg.setFeIni(xpath.compile("/seguimiento/@fe_ini").evaluate(document, XPathConstants.STRING).toString());
			seg.setFeFin(xpath.compile("/seguimiento/@fe_fin").evaluate(document, XPathConstants.STRING).toString());
			seg.setFecha(xpath.compile("/seguimiento/@fecha").evaluate(document, XPathConstants.STRING).toString());
			
			NodeList nodes = document.getElementsByTagName("inst");
			for(int i=0;i < nodes.getLength(); i++){				
				InstSeg inst = new InstSeg();
				if (xpath.compile("/cesta /inst[" + (i+1) + "]/nu_doc_opened").evaluate(document, XPathConstants.STRING).toString().trim().equals("N")){
					inst.setNuDocOpened(false);
				}else{
					inst.setNuDocOpened(true);
				}
				inst.setNuDoc(Integer.valueOf(xpath.compile("/seguimiento/inst[" + (i+1) + "]/nu_doc").evaluate(document, XPathConstants.STRING).toString()));
				inst.setNuInst(Integer.valueOf(xpath.compile("/seguimiento /inst[" + (i+1) + "]/nu_inst").evaluate(document, XPathConstants.STRING).toString()));
				inst.setWfp(Integer.valueOf(xpath.compile("/seguimiento /inst[" + (i+1) + "]/wfp").evaluate(document, XPathConstants.STRING).toString()));
				inst.setWfa(Integer.valueOf(xpath.compile("/seguimiento /inst[" + (i+1) + "]/wfa").evaluate(document, XPathConstants.STRING).toString()));
				inst.setNbWfp(xpath.compile("/seguimiento /inst[" + (i+1) + "]/nb_wfp").evaluate(document, XPathConstants.STRING).toString());
				inst.setNbWfDest(xpath.compile("/seguimiento /inst[" + (i+1) + "]/nb_wf_dest").evaluate(document, XPathConstants.STRING).toString());
				inst.setNbWf(xpath.compile("/seguimiento /inst[" + (i+1) + "]/nb_wf").evaluate(document, XPathConstants.STRING).toString());
				inst.setE(xpath.compile("/seguimiento /inst[" + (i+1) + "]/e").evaluate(document, XPathConstants.STRING).toString());
				inst.setEp(xpath.compile("/seguimiento /inst[" + (i+1) + "]/ep").evaluate(document, XPathConstants.STRING).toString());
				inst.setFeIni(xpath.compile("/seguimiento /inst[" + (i+1) + "]/fe_ini").evaluate(document, XPathConstants.STRING).toString());
				inst.setFeFin(xpath.compile("/seguimiento /inst[" + (i+1) + "]/fe_fin").evaluate(document, XPathConstants.STRING).toString());
				inst.setTxObserva(xpath.compile("/seguimiento /inst[" + (i+1) + "]/txobserva").evaluate(document, XPathConstants.STRING).toString());
				inst.setDetalle(xpath.compile("/seguimiento /inst[" + (i+1) + "]/detalle").evaluate(document, XPathConstants.STRING).toString());
				inst.setDias(xpath.compile("/seguimiento /inst[" + (i+1) + "]/dias").evaluate(document, XPathConstants.STRING).toString());
				inst.setTiempo(xpath.compile("/seguimiento /inst[" + (i+1) + "]/tiempo").evaluate(document, XPathConstants.STRING).toString());
				inst.setOrigen(xpath.compile("/seguimiento /inst[" + (i+1) + "]/origen").evaluate(document, XPathConstants.STRING).toString());				
				inst.setNbPuestoOrg(xpath.compile("/seguimiento /inst[" + (i+1) + "]/nb_puestoorg").evaluate(document, XPathConstants.STRING).toString());
				inst.setNbPersonaOrg(xpath.compile("/seguimiento /inst[" + (i+1) + "]/nb_personaorg").evaluate(document, XPathConstants.STRING).toString());				
				inst.setNbPuesto(xpath.compile("/seguimiento /inst[" + (i+1) + "]/nb_puesto").evaluate(document, XPathConstants.STRING).toString());
				inst.setNbPersona(xpath.compile("/seguimiento /inst[" + (i+1) + "]/nb_persona").evaluate(document, XPathConstants.STRING).toString());
				if (xpath.compile("/seguimiento /inst[" + (i+1) + "]/tpinst").evaluate(document, XPathConstants.STRING).toString().trim().equals("N")){
					inst.setTpInst(false);
				}else{
					inst.setTpInst(true);
				}
				inst.setTpc(xpath.compile("/seguimiento /inst[" + (i+1) + "]/tp_c").evaluate(document, XPathConstants.STRING).toString());				
				if (xpath.compile("/seguimiento /inst[" + (i+1) + "]/in_espera").evaluate(document, XPathConstants.STRING).toString().trim().equals("N")){
					inst.setInEspera(false);
				}else{
					inst.setInEspera(true);
				}				
				if (xpath.compile("/seguimiento /inst[" + (i+1) + "]/in_urgente").evaluate(document, XPathConstants.STRING).toString().trim().equals("N")){
					inst.setInUrgente(false);
				}else{
					inst.setInUrgente(true);
				}
				if (xpath.compile("/seguimiento /inst[" + (i+1) + "]/in_visible").evaluate(document, XPathConstants.STRING).toString().trim().equals("S")){
					inst.setInVisible(true);
				}else{
					inst.setInVisible(false);
				}
				if (xpath.compile("/seguimiento /inst[" + (i+1) + "]/in_clone").evaluate(document, XPathConstants.STRING).toString().trim().equals("S")){
					inst.setInClone(true);
				}else{
					inst.setInClone(false);
				}					
				inst.setReposito(xpath.compile("/seguimiento /inst[" + (i+1) + "]/reposito").evaluate(document, XPathConstants.STRING).toString());
				inst.setFrmn(Integer.valueOf(xpath.compile("/seguimiento /inst[" + (i+1) + "]/frmn").evaluate(document, XPathConstants.STRING).toString()));
				seg.getInst().add(inst);
			}				

		}catch(Exception e){
			logger.error("obtenerSeguimiento:", e);
		}
		return seg;
	}

	@Override
	public Seguimiento obtenerSeguimientoGantt(Integer tipo, Integer nuDoc) {
		Seguimiento seg = new Seguimiento();
		try{
			motor = ClassFactory.getProcess(engineP);
			String resultXml = motor.p4bObtenerSeguimiento(tipo, nuDoc);			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			
			seg.setFeIni(xpath.compile("/seguimiento/@fe_ini").evaluate(document, XPathConstants.STRING).toString());
			seg.setFeFin(xpath.compile("/seguimiento/@fe_fin").evaluate(document, XPathConstants.STRING).toString());
			seg.setFecha(xpath.compile("/seguimiento/@fecha").evaluate(document, XPathConstants.STRING).toString());
			
			NodeList nodes = document.getElementsByTagName("inst");
			for(int i=0;i < nodes.getLength(); i++){				
				InstSeg inst = new InstSeg();
				if (xpath.compile("/nu_doc_opened").evaluate(nodes.item(i), XPathConstants.STRING).toString().trim().equals("N")){
					inst.setNuDocOpened(false);
				}else{
					inst.setNuDocOpened(true);
				}
				inst.setNuDoc(Integer.valueOf(xpath.compile("nu_doc").evaluate(nodes.item(i), XPathConstants.STRING).toString()));
				inst.setNuInst(Integer.valueOf(xpath.compile("nu_inst").evaluate(nodes.item(i), XPathConstants.STRING).toString()));
				inst.setWfp(Integer.valueOf(xpath.compile("wfp").evaluate(nodes.item(i), XPathConstants.STRING).toString()));
				inst.setWfa(Integer.valueOf(xpath.compile("wfa").evaluate(nodes.item(i), XPathConstants.STRING).toString()));
				inst.setNbWfp(xpath.compile("nb_wfp").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				inst.setNbWfDest(xpath.compile("nb_wf_dest").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				inst.setNbWf(xpath.compile("nb_wf").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				inst.setE(xpath.compile("e").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				inst.setEp(xpath.compile("ep").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				inst.setFeIni(xpath.compile("fe_ini").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				inst.setFeFin(xpath.compile("fe_fin").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				inst.setTxObserva(xpath.compile("txobserva").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				inst.setDetalle(xpath.compile("detalle").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				inst.setDias(xpath.compile("dias").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				inst.setTiempo(xpath.compile("tiempo").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				inst.setOrigen(xpath.compile("origen").evaluate(nodes.item(i), XPathConstants.STRING).toString());				
				inst.setNbPuestoOrg(xpath.compile("nb_puestoorg").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				inst.setNbPersonaOrg(xpath.compile("nb_personaorg").evaluate(nodes.item(i), XPathConstants.STRING).toString());				
				inst.setNbPuesto(xpath.compile("nb_puesto").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				inst.setNbPersona(xpath.compile("nb_persona").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				if (xpath.compile("tpinst").evaluate(nodes.item(i), XPathConstants.STRING).toString().trim().equals("N")){
					inst.setTpInst(false);
				}else{
					inst.setTpInst(true);
				}
				inst.setTpc(xpath.compile("tp_c").evaluate(nodes.item(i), XPathConstants.STRING).toString());				
				if (xpath.compile("in_espera").evaluate(nodes.item(i), XPathConstants.STRING).toString().trim().equals("N")){
					inst.setInEspera(false);
				}else{
					inst.setInEspera(true);
				}				
				if (xpath.compile("in_urgente").evaluate(nodes.item(i), XPathConstants.STRING).toString().trim().equals("N")){
					inst.setInUrgente(false);
				}else{
					inst.setInUrgente(true);
				}
				if (xpath.compile("in_visible").evaluate(nodes.item(i), XPathConstants.STRING).toString().trim().equals("S")){
					inst.setInVisible(true);
				}else{
					inst.setInVisible(false);
				}
				if (xpath.compile("in_clone").evaluate(nodes.item(i), XPathConstants.STRING).toString().trim().equals("S")){
					inst.setInClone(true);
				}else{
					inst.setInClone(false);
				}					
				inst.setReposito(xpath.compile("reposito").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				inst.setFrmn(Integer.valueOf(xpath.compile("frmn").evaluate(nodes.item(i), XPathConstants.STRING).toString()));
				seg.getInst().add(inst);
			}				

		}catch(Exception e){
			logger.error("obtenerSeguimientoGantt:", e);
		}
		return seg;
	}


	@Override
	public List<String> obtenerActividadesPorEjecutar(Integer wfp, Integer nudoc) {
		List<String> list = new ArrayList<String>();
		try{
			motor = ClassFactory.getProcess(engineP);
			String resultXml = motor.p4bObtenerActividadesPorEjecutar(wfp, nudoc);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			
			NodeList nodes = document.getElementsByTagName("nb_wf");
			for(int i=0;i < nodes.getLength(); i++){
				list.add(xpath.compile("/activporejec /nb_wf[" + (i+1) + "]").evaluate(document, XPathConstants.STRING).toString());				
			}
		}catch(Exception e){
			logger.error("obtenerActividadesPorEjecutar:", e);
		}
		return list;					
	}


	@Override
	public List<CestaAnexo> obtenerAnexos(Integer nuDoc) {
		List<CestaAnexo> cesta = new ArrayList<CestaAnexo>();		
		try{
			motor = ClassFactory.getProcess(engineP);
			String resultXml = motor.p4bObtenerAnexos(nuDoc);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			
			NodeList nodes = document.getElementsByTagName("anexo");
			String fechaTemp = "";
			CestaAnexo cestaAnexo = null;
			List<Anexo> list = new ArrayList<Anexo>();
			for(int i=0;i < nodes.getLength(); i++){
				if (fechaTemp.equals("")){
					fechaTemp = xpath.compile("@fecha").evaluate(nodes.item(i), XPathConstants.STRING).toString();
				}
				if (!fechaTemp.equals(xpath.compile("@fecha").evaluate(nodes.item(i), XPathConstants.STRING).toString())){
					cestaAnexo = new CestaAnexo();
					cestaAnexo.setFecha(xpath.compile("@fecha").evaluate(nodes.item(i), XPathConstants.STRING).toString());
					DateFormat formatter;
					formatter = new SimpleDateFormat("dd/MM/yyyy");
					cestaAnexo.setDate(formatter.parse(xpath.compile("@fecha").evaluate(nodes.item(i), XPathConstants.STRING).toString()));
					cestaAnexo.setAnexos(list);
					cesta.add(cestaAnexo);
					list = new ArrayList<Anexo>();
					Anexo anexo = new Anexo();
					anexo.setFecha(xpath.compile("@fecha").evaluate(nodes.item(i), XPathConstants.STRING).toString());
					anexo.setDes(xpath.compile("@desc").evaluate(nodes.item(i), XPathConstants.STRING).toString());
					anexo.setAct(xpath.compile("@act").evaluate(nodes.item(i), XPathConstants.STRING).toString());
					anexo.setArchivo(xpath.compile("@archivo").evaluate(nodes.item(i), XPathConstants.STRING).toString());
					anexo.setUsuario(xpath.compile("@usuario").evaluate(nodes.item(i), XPathConstants.STRING).toString());
					anexo.setExt(xpath.compile("@ext").evaluate(nodes.item(i), XPathConstants.STRING).toString());
					list.add(anexo);	
					fechaTemp = xpath.compile("@fecha").evaluate(nodes.item(i), XPathConstants.STRING).toString();					
				}else{
					Anexo anexo = new Anexo();
					anexo.setFecha(xpath.compile("@fecha").evaluate(nodes.item(i), XPathConstants.STRING).toString());
					anexo.setDes(xpath.compile("@desc").evaluate(nodes.item(i), XPathConstants.STRING).toString());
					anexo.setAct(xpath.compile("@act").evaluate(nodes.item(i), XPathConstants.STRING).toString());
					anexo.setArchivo(xpath.compile("@archivo").evaluate(nodes.item(i), XPathConstants.STRING).toString());
					anexo.setUsuario(xpath.compile("@usuario").evaluate(nodes.item(i), XPathConstants.STRING).toString());
					anexo.setExt(xpath.compile("@ext").evaluate(nodes.item(i), XPathConstants.STRING).toString());
					list.add(anexo);					
				}				
			}
			if ((cesta.size()==0)&&(list.size()>0)){
				cestaAnexo = new CestaAnexo();
				cestaAnexo.setFecha(list.get(0).getFecha());
				DateFormat formatter;
				formatter = new SimpleDateFormat("dd/MM/yyyy");
				cestaAnexo.setDate(formatter.parse(list.get(0).getFecha()));
				cestaAnexo.setAnexos(list);
				cesta.add(cestaAnexo);				
			}
					
		}catch(Exception e){
			logger.error("obtenerAnexos:", e);
		}		
		//ordenar cesta por fecha
	    Collections.sort(cesta, new Comparator<CestaAnexo>() {
	        public int compare(CestaAnexo m1, CestaAnexo m2) {
	        	return m1.getDate().compareTo(m2.getDate());
	        }
	    });		
		return cesta;
	}

	@Override
	public List<Proceso> obtenerClasificacion(Integer numCla) {
		List<Proceso> list = new ArrayList<Proceso>();
		try{
			motor = ClassFactory.getProcess(engineP);
			String resultXml = motor.p4bObtenerClasificacion(numCla);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			
			NodeList nodes = document.getElementsByTagName("proceso");
			for(int i=0;i < nodes.getLength(); i++){
				Proceso proceso = new Proceso();
				proceso.setWfp(Integer.valueOf(xpath.compile("@wfp").evaluate(nodes.item(i), XPathConstants.STRING).toString()));
				proceso.setWfa(Integer.valueOf(xpath.compile("@wfa").evaluate(nodes.item(i), XPathConstants.STRING).toString()));
				proceso.setNbWf(xpath.compile("@nb_wf").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				NodeList nodesChild = nodes.item(i).getChildNodes();
				for(int j=0;j < nodesChild.getLength(); j++){
					Conversacion conversacion = new Conversacion();
					conversacion.setWfp(Integer.valueOf(xpath.compile("@wfp").evaluate(nodesChild.item(j), XPathConstants.STRING).toString()));
					conversacion.setWfa(Integer.valueOf(xpath.compile("@wfa").evaluate(nodesChild.item(j), XPathConstants.STRING).toString()));
					conversacion.setNbWf(xpath.compile("@nb_wf").evaluate(nodesChild.item(j), XPathConstants.STRING).toString());			
					conversacion.setWfa(Integer.valueOf(xpath.compile("@wfa").evaluate(nodesChild.item(j), XPathConstants.STRING).toString()));					
					proceso.getConversaciones().add(conversacion);
				}				
				list.add(proceso);
			}
		}catch(Exception e){
			logger.error("obtenerClasificacion:", e);
		}
		//filtrar listas por repetidos
		List<Proceso> listFilter = new ArrayList<Proceso>();
		for(Proceso p:list){
			Integer index = findProces(listFilter, p.getNbWf());
			if (index==-1){
				listFilter.add(p);
			}else{
				listFilter.get(index).getConversaciones().addAll(p.getConversaciones());
			}
		}
		return listFilter;
	}
	
	private Integer findProces(List<Proceso> list, String nameProcess){
		Integer index = -1;
		int i = 0;
		for(Proceso p:list){
			if (p.getNbWf().equals(nameProcess)){
				index = i;
				break;
			}
			i++;
		}
		return index;
	}

	@Override
	public RelatedSeg obtenerDocumentosRelacionados(Integer nuDoc) {
		RelatedSeg relatedSeg = new RelatedSeg();
		try{
			motor = ClassFactory.getProcess(engineP);
			String resultXml = motor.p4bObtenerDocumentosRelacionados(nuDoc);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			
			relatedSeg.setNuDoc(Integer.valueOf(xpath.compile("/docs/@padre").evaluate(document, XPathConstants.STRING).toString()));
			if (xpath.compile("/docs/@status").evaluate(document, XPathConstants.STRING).toString()!=""){
				relatedSeg.setStatus(Integer.valueOf(xpath.compile("/docs/@status").evaluate(document, XPathConstants.STRING).toString()));	
			}			
			relatedSeg.setFecha(xpath.compile("/docs/@fecha").evaluate(document, XPathConstants.STRING).toString());
			relatedSeg.setNbWf(xpath.compile("/docs/@nb_wf").evaluate(document, XPathConstants.STRING).toString());
			relatedSeg.setNbWfp(xpath.compile("/docs/@nb_wfp").evaluate(document, XPathConstants.STRING).toString());			
			
			NodeList nodes = document.getElementsByTagName("doc");
			for(int i=0;i < nodes.getLength(); i++){
				RelatedSeg relatedSegchild = new RelatedSeg();
				relatedSegchild.setNuDoc(Integer.valueOf(nodes.item(i).getTextContent()));
				if (xpath.compile("@status").evaluate(nodes.item(i), XPathConstants.STRING).toString()!=""){
					relatedSegchild.setStatus(Integer.valueOf(xpath.compile("@status").evaluate(nodes.item(i), XPathConstants.STRING).toString()));	
				}
				relatedSegchild.setFecha(xpath.compile("@fecha").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				relatedSegchild.setNbWf(xpath.compile("@nb_wf").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				relatedSegchild.setNbWfp(xpath.compile("@nb_wfp").evaluate(nodes.item(i), XPathConstants.STRING).toString());				
				relatedSeg.getChildrens().add(relatedSegchild);
			}
		}catch(Exception e){
			logger.error("obtenerDocumentosRelacionados:", e);
		}
		return relatedSeg;
	}

	
}
