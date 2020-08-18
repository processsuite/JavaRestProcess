/**
 * ResultReport.java 
 *
 */
package com.process.domain.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Result Report class for report Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class ResultReport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer wfa;
	
	private Integer tipo;
	
	private Integer pagina;
	
	private Integer desde;
	
	private Integer total;
	
	private Integer totalserie;
	
	private Integer tipoGrafico;
	
	private Integer archReport; 
	
	private List<InstReport> instReports;

	public Integer getWfa() {
		return wfa;
	}

	public void setWfa(Integer wfa) {
		this.wfa = wfa;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	public Integer getDesde() {
		return desde;
	}

	public void setDesde(Integer desde) {
		this.desde = desde;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<InstReport> getInstReports() {
		if (instReports==null){
			instReports = new ArrayList<InstReport>();
		}
		return instReports;
	}

	public void setInstReports(List<InstReport> instReports) {
		this.instReports = instReports;
	}

	public Integer getTotalserie() {
		return totalserie;
	}

	public void setTotalserie(Integer totalserie) {
		this.totalserie = totalserie;
	}

	public Integer getTipoGrafico() {
		return tipoGrafico;
	}

	public void setTipoGrafico(Integer tipoGrafico) {
		this.tipoGrafico = tipoGrafico;
	}
	
	
	
	public Integer getArchReport() {
		return archReport;
	}

	public void setArchReport(Integer archReport) {
		this.archReport = archReport;
	}

	@Override
	public String toString() {
		return "ResultReport [wfa=" + wfa + ", tipo=" + tipo + ", pagina="
				+ pagina + ", desde=" + desde + ", total=" + total
				+ ", totalserie=" + totalserie + ", tipoGrafico=" + tipoGrafico
				+ ", instReports=" + instReports + "]";
	}
	
}
