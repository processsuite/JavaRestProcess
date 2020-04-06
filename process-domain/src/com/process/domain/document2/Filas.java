package com.process.domain.document2;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "FILAS")
@XmlAccessorType(XmlAccessType.NONE)
public class Filas {
	
	@XmlAttribute(name = "total")
	private Integer total;
	
	@XmlAttribute(name = "pagina")
	private Integer pagina;
	
	@XmlAttribute(name = "desde")
	private Integer desde;
	
	@XmlElement(name = "FILA")
	private List<Fila> fila;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
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

	public List<Fila> getFila() {
		return fila;
	}

	public void setFila(List<Fila> fila) {
		this.fila = fila;
	}
	
	
	
}
