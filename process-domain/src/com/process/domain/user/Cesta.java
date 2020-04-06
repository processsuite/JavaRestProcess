/**
 * Cesta.java 
 *
 */
package com.process.domain.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Cesta class for user Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Cesta implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fecha;
	
	private Integer tipo;
	
	private Integer pagina;
	
	private Integer desde;
	
	private Integer total;	
	
	private List<Inst> insts;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
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

	public List<Inst> getInsts() {
		if (insts==null){
			insts = new ArrayList<Inst>();
		}
		return insts;
	}

	public void setInsts(List<Inst> insts) {
		this.insts = insts;
	}

	@Override
	public String toString() {
		return "Cesta [fecha=" + fecha + ", tipo=" + tipo + ", pagina="
				+ pagina + ", desde=" + desde + ", total=" + total + ", insts="
				+ insts + "]";
	}
	
}
