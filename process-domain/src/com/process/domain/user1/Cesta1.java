package com.process.domain.user1;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "cesta")
@XmlAccessorType(XmlAccessType.NONE)
public class Cesta1 {

	@XmlAttribute(name="fecha")
	private String fecha;
	
	@XmlAttribute(name="tipo")
	private Integer tipo;
	
	@XmlAttribute(name="pagina")
	private Integer pagina;
	
	@XmlAttribute(name="desde")
	private Integer desde;
	
	@XmlAttribute(name="total")
	private Integer total;
	
	@XmlElement(name="inst")
	private List<Inst1> listInst;

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

	public List<Inst1> getListInst() {
		return listInst;
	}

	public void setListInst(List<Inst1> listInst) {
		this.listInst = listInst;
	}
	
	
}
