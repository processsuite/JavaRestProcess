/**
 * SimpleReportManager.java 
 *
 */
package com.process.business.report.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.process.business.helper.ClassFactory;
import com.process.business.helper.c_Process;
import com.process.business.report.ReportManager;
import com.process.domain.document.ItemOption;
import com.process.domain.report.FieldReport;
import com.process.domain.report.InstReport;
import com.process.domain.report.ParamReport;
import com.process.domain.report.Report;
import com.process.domain.report.ResultReport;

/**
 * Business Implementation SimpleReportManager for report Module 
 * @author Oswel Sanchez
 * 
 */
@Service("reportManager")
public class SimpleReportManager implements ReportManager {
    
	private static final Logger logger = Logger.getLogger(SimpleReportManager.class);
	
	private c_Process motor;
	
	private Integer engineP;
	
	@Override
	public void setEngineId(Integer engineId) {
		engineP = engineId;
	}	
	
	@Override
	public List<Report> obtenerConsultas(List<String> tipos) {
		List<Report> list = new ArrayList<Report>();
		try{
			motor = ClassFactory.getProcess(engineP);
			String resultXml = motor.p4bObtenerConsultas();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			
			NodeList nodes = document.getElementsByTagName("proceso");
			for(int i=0;i < nodes.getLength(); i++){				
				Report report = new Report();
				Boolean child = false;
				report.setNombre(xpath.compile("/vistas/proceso[" + (i+1) + "]/@nb_wf").evaluate(document, XPathConstants.STRING).toString());
				report.setWfp(Integer.valueOf(xpath.compile("/vistas/proceso[" + (i+1) + "]/@wfp").evaluate(document, XPathConstants.STRING).toString()));
				report.setWf(Integer.valueOf(xpath.compile("/vistas/proceso[" + (i+1) + "]/@wfp").evaluate(document, XPathConstants.STRING).toString()));
				NodeList nodesChild = nodes.item(i).getChildNodes();
				for(int j=0;j < nodesChild.getLength(); j++){
					//Validar tipo
					String tipo = xpath.compile("/vistas/proceso[" + (i+1) + "]/vista[" + (j+1) + "]/@tipo").evaluate(document, XPathConstants.STRING).toString(); 
					if (tipos.contains(tipo)){
						child = true;
						Report reportChild = new Report();
						reportChild.setNombre(xpath.compile("/vistas/proceso[" + (i+1) + "]/vista[" + (j+1) + "]/@nb_wf").evaluate(document, XPathConstants.STRING).toString());
						reportChild.setWfp(Integer.valueOf(xpath.compile("/vistas/proceso[" + (i+1) + "]/vista["+ (j+1) +"]/@wfp").evaluate(document, XPathConstants.STRING).toString()));						
						reportChild.setWf(Integer.valueOf(xpath.compile("/vistas/proceso[" + (i+1) + "]/vista["+ (j+1) +"]/@wf").evaluate(document, XPathConstants.STRING).toString()));
						reportChild.setTipo(tipo);
						report.getChildrens().add(reportChild);						
					}
				}
				if (child){
					list.add(report);
				}				
			}				

		}catch(Exception e){
			logger.error("obtenerConsultas:", e);
		}
		return list;
	}

	@Override
	public List<ParamReport> obtenerParametrosConsulta(Integer wfPadre, Integer wfHijo) {
		List<ParamReport> list = new ArrayList<ParamReport>();
		try{
			motor = ClassFactory.getProcess(engineP);
			String resultXml = motor.p4bObtenerParametrosConsulta(wfPadre, wfHijo);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();	
			XPathExpression expr = xpath.compile("//camposBuscar/campo");
			Object result = expr.evaluate(document, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;			
			
			for(int i=0;i < nodes.getLength(); i++){	
				ParamReport paramReport = new ParamReport();
				paramReport.setCampoBd(xpath.compile("campoBD").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				paramReport.setDescripcion(xpath.compile("descripcion").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				paramReport.setTipo(xpath.compile("tipo").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				paramReport.setOperador(xpath.compile("operador").evaluate(nodes.item(i), XPathConstants.STRING).toString());
				if (xpath.compile("campoBD/@obligatorio").evaluate(nodes.item(i), XPathConstants.STRING).toString().equals("N")){
					paramReport.setObligatorio(false);
				}else{
					paramReport.setObligatorio(true);
				}
				if (xpath.compile("funcion").evaluate(nodes.item(i), XPathConstants.STRING).toString().equals("S")){
					paramReport.setFuncion(true);
				}else{
					paramReport.setFuncion(false);
				}	
				if (paramReport.getTipo().equals("L")){
					XPathExpression expr2 = xpath.compile("//camposBuscar/campo[" + (i+1) +  "]/opciones/opcion");
					Object result2 = expr2.evaluate(document, XPathConstants.NODESET);
					NodeList nodesList = (NodeList) result2;						
					for(int j=0;j < nodesList.getLength(); j++){
						ItemOption opt = new ItemOption();
						opt.setKey(xpath.compile("@codigo").evaluate(nodesList.item(j), XPathConstants.STRING).toString());
						opt.setValue(nodesList.item(j).getTextContent());						
						opt.setSelected(false);
						paramReport.getOpciones().add(opt);
					}
				}
				list.add(paramReport);			
			}				

		}catch(Exception e){
			logger.error("obtenerParametrosConsulta:", e);
		}
		return list;
	}

	@Override
	public ResultReport ejecutarConsulta(Integer wfPadre, Integer wfHijo, Integer tipoOpcion, Integer desde, List<FieldReport> camposBuscar, String campoOrden) {
		ResultReport resultReport = new ResultReport();
		try{
			motor = ClassFactory.getProcess(engineP);
			String resultXml = motor.p4bEjecutarConsulta(wfPadre, wfHijo, tipoOpcion, desde, getXmlParam(camposBuscar), campoOrden);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(resultXml)));
			
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();	
			
			resultReport.setWfa(Integer.valueOf(xpath.compile("/consulta/@wfa").evaluate(document, XPathConstants.STRING).toString()));
			resultReport.setTipo(Integer.valueOf(xpath.compile("/consulta/@tipo").evaluate(document, XPathConstants.STRING).toString()));
			resultReport.setPagina(Integer.valueOf(xpath.compile("/consulta/@pagina").evaluate(document, XPathConstants.STRING).toString()));
			resultReport.setDesde(Integer.valueOf(xpath.compile("/consulta/@desde").evaluate(document, XPathConstants.STRING).toString()));
			resultReport.setTotal(Integer.valueOf(xpath.compile("/consulta/@total").evaluate(document, XPathConstants.STRING).toString()));
			//validar si es grafica 
			if (resultReport.getTipo()>7){
				resultReport.setTipoGrafico(Integer.valueOf(xpath.compile("/consulta/@tipografico").evaluate(document, XPathConstants.STRING).toString()));
				resultReport.setTotalserie(Integer.valueOf(xpath.compile("/consulta/@totalserie").evaluate(document, XPathConstants.STRING).toString()));
				
				XPathExpression expr = xpath.compile("//inst");
				Object result = expr.evaluate(document, XPathConstants.NODESET);
				NodeList nodes = (NodeList) result;					
				for(int j=0;j < nodes.getLength(); j++){
					InstReport inst = new InstReport();
					
					FieldReport campo = new FieldReport();					
					campo.setValor(xpath.compile("etiqueta").evaluate(nodes.item(j), XPathConstants.STRING).toString());
					campo.setDescripcion(xpath.compile("/consulta/series/ejex").evaluate(document, XPathConstants.STRING).toString());
					inst.getCamposMostrar().add(campo);
					
					for(int i=0;i < resultReport.getTotalserie(); i++){
						FieldReport campoS = new FieldReport();					
						campoS.setValor(xpath.compile("data["+ (i+1) +"]").evaluate(nodes.item(j), XPathConstants.STRING).toString());
						campoS.setDescripcion(xpath.compile("/consulta/series/serie["+ (i+1) +"]").evaluate(document, XPathConstants.STRING).toString());
						campoS.setColor(xpath.compile("/consulta/series/serie["+ (i+1) +"]/@color").evaluate(document, XPathConstants.STRING).toString());
						inst.getCamposMostrar().add(campoS);						
					}
					resultReport.getInstReports().add(inst);
				}
				
			}else{
				XPathExpression expr = xpath.compile("//inst");
				Object result = expr.evaluate(document, XPathConstants.NODESET);
				NodeList nodes = (NodeList) result;				
				
				for(int i=0;i < nodes.getLength(); i++){
					InstReport inst = new InstReport();
					XPathExpression exprOcultos = xpath.compile("//inst[" + (i+1) + "]/camposOcultos/campo");
					Object resultOcultos = exprOcultos.evaluate(document, XPathConstants.NODESET);
					NodeList nodesOcultos = (NodeList) resultOcultos;	
					for(int j=0;j < nodesOcultos.getLength(); j++){
						FieldReport campo = new FieldReport();				
						campo.setCampoBd(xpath.compile("campoBD").evaluate(nodesOcultos.item(j), XPathConstants.STRING).toString());
						campo.setValor(xpath.compile("valor").evaluate(nodesOcultos.item(j), XPathConstants.STRING).toString());
						inst.getCamposOcultos().add(campo);
					}
					XPathExpression exprMostrar = xpath.compile("//inst[" + (i+1) + "]/camposMostrar/campo");
					Object resultMostrar = exprMostrar.evaluate(document, XPathConstants.NODESET);
					NodeList nodesMostrar = (NodeList) resultMostrar;				
					for(int j=0;j < nodesMostrar.getLength(); j++){
						FieldReport campo = new FieldReport();				
						campo.setCampoBd(xpath.compile("campoBD").evaluate(nodesMostrar.item(j), XPathConstants.STRING).toString());
						campo.setValor(xpath.compile("valor").evaluate(nodesMostrar.item(j), XPathConstants.STRING).toString());
						campo.setDescripcion(xpath.compile("descripcion").evaluate(nodesMostrar.item(j), XPathConstants.STRING).toString());
						inst.getCamposMostrar().add(campo);
					}				
					resultReport.getInstReports().add(inst);
				}					
			}		
		}catch(Exception e){
			logger.error("ejecutarConsulta:", e);
		}
		return resultReport;
	}
	
	private String getXmlParam(List<FieldReport> camposBuscar){
		String xml = "<?xml version='1.0' encoding='ISO-8859-1'?>";
		xml += "<camposBuscar>";
		Integer i = 0;
		for(FieldReport field:camposBuscar){
			xml += "<campo>";
			xml += "<campoBD>" + field.getCampoBd() + "</campoBD>";
			xml += "<descripcion>" + "<![CDATA[" + field.getDescripcion() + "]]>" + "</descripcion>";						
			xml += "<valor>" + "<![CDATA[" + field.getValor() + "]]>" + "</valor>";
			xml += "<pos>"+(i)+"</pos>";									
			xml += "</campo>";	
			i++;
		}
		xml += "</camposBuscar>";
		return xml;
	}

}
