package com.process.domain.document2;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.persistence.oxm.annotations.XmlPath;


@XmlRootElement(name="CAMPO")
@XmlAccessorType(XmlAccessType.NONE)
public class Campo {

	@XmlAttribute(name = "id")
	private Integer id;
	
	@XmlAttribute(name = "idpadre")
	private Integer idpadre;

	@XmlAttribute(name = "nombre")
	private String nombre;
	
	@XmlAttribute(name = "tipo")
	private String tipo;
	
	@XmlAttribute(name = "formula")
	private String formula;

/*	@XmlAttribute(name="left")
	private Double left;
	
	@XmlAttribute(name="top")
	private Double top;
	
	@XmlAttribute(name="right")
	private Double right;
	
	@XmlAttribute(name="bottom")
	private Double bottom;*/
	
	@XmlAttribute(name="descripcion")
	private String descripcion;

	@XmlAttribute(name="lectura")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean lectura;
	
	@XmlAttribute(name="escritura")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean escritura;
	
	@XmlAttribute(name="obligatorio")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean obligatorio;
	
	@XmlAttribute(name="longitudmaxima")
	private Integer longitudmaxima;
	
	@XmlAttribute(name="evento")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean evento;
	
	@XmlAttribute(name="desevento")
	private String desevento;
	
	@XmlAttribute(name="listaajax")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean listaAjax;
	
	@XmlAttribute(name="exclusivo")
	@XmlJavaTypeAdapter(BooleanAdapter.class)
	private Boolean exclusivo;
	
	@XmlAttribute(name ="opcionesmultiples")
	private String opcionesmultiples;

	@XmlElement(name = "CAMPO")
	private List<Campo> campos;
	
	@XmlElement(name = "FILAS")
	private Filas filas;
	
	@XmlElement(name = "OPCIONES")
	private Opciones opciones;
	
	@XmlAttribute(name = "value")
	private String value;
	
	@XmlAttribute(name = "href")
	private String href; 
	
	@XmlAttribute(name = "ocultarCliente") //variable para ocultar los campos a nivel de manejo cliente
	private Boolean ocultarCliente;
	
	@XmlAttribute(name = "decimales")
	private Integer decimales;
	
	@XmlAttribute(name = "minimo")
	private String minimo;
	
	@XmlAttribute(name = "maximo")
	private String maximo;
	
	@XmlAttribute(name = "info")
	private String info;
	
	
	
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getMinimo() {
		return minimo;
	}

	public void setMinimo(String minimo) {
		this.minimo = minimo;
	}

	public String getMaximo() {
		return maximo;
	}

	public void setMaximo(String maximo) {
		this.maximo = maximo;
	}

	public Integer getDecimales() {
		return decimales;
	}

	public void setDecimales(Integer decimales) {
		this.decimales = decimales;
	}

	public Boolean getOcultarCliente() {
		return ocultarCliente;
	}

	public void setOcultarCliente(Boolean ocultarCliente) {
		this.ocultarCliente = ocultarCliente;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@XmlPath("text()")
	public String getValue() {
		return value;
	}
	
	@XmlAttribute(name="change")
	private Boolean change;
	
	@XmlAttribute(name="valueM")
	private List<String> valueM;
	
	@XmlAttribute(name="checked")
	private Boolean checked;
	
	@XmlAttribute(name="visible")
	private Boolean visible;
	
	@XmlAttribute(name="filasEliminadas")
	private String filasEliminadas;
	
	
	public String getFilasEliminadas() {
		return filasEliminadas;
	}

	public void setFilasEliminadas(String filasEliminadas) {
		this.filasEliminadas = filasEliminadas;
	}

	public Boolean getChange() {
		return change;
	}

	public void setChange(Boolean change) {
		this.change = change;
	}

	public List<String> getValueM() {
		return valueM;
	}

	public void setValueM(List<String> valueM) {
		this.valueM = valueM;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Opciones getOpciones() {
		return opciones;
	}

	public void setOpciones(Opciones opciones) {
		this.opciones = opciones;
	}

	public Filas getFilas() {
		return filas;
	}

	public void setFilas(Filas filas) {
		this.filas = filas;
	}

	public List<Campo> getCampos() {
		return campos;
	}

	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdpadre() {
		return idpadre;
	}

	public void setIdpadre(Integer idpadre) {
		this.idpadre = idpadre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*public Double getLeft() {
		return left;
	}

	public void setLeft(Double left) {
		this.left = left;
	}

	public Double getTop() {
		return top;
	}

	public void setTop(Double top) {
		this.top = top;
	}

	public Double getRight() {
		return right;
	}

	public void setRight(Double right) {
		this.right = right;
	}

	public Double getBottom() {
		return bottom;
	}

	public void setBottom(Double bottom) {
		this.bottom = bottom;
	}*/

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEscritura() {
		return escritura;
	}

	public void setEscritura(String s) {
		this.escritura = "S".equals(s)||"true".equals(s);;
	}
	
	public Boolean getLectura() {
		return lectura;
	}

	public void setLectura(String s) {
		this.lectura = "S".equals(s)||"true".equals(s);;
	}

	public Boolean getObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(String s) {
		this.obligatorio = "S".equals(s)||"true".equals(s);
	}

	public Integer getLongitudMaxima() {
		return longitudmaxima;
	}

	public void setLongitudMaxima(Integer longitudmaxima) {
		this.longitudmaxima = longitudmaxima;
	}

	public Boolean getEvento() {
		return evento;
	}

	public void setEvento(String s) {
		this.evento = "S".equals(s)||"true".equals(s);
	}

	public String getDesEvento() {
		return desevento;
	}

	public void setDesEvento(String desevento) {
		this.desevento = desevento;
	}

	public Boolean getListaAjax() {
		return listaAjax;
	}

	public void setListaAjax(String s) {
		this.listaAjax = "S".equals(s)||"true".equals(s);
	}
	
	public Boolean getExclusivo() {
		return exclusivo;
	}

	public void setExclusivo(String s) {
		this.exclusivo = "S".equals(s)||"true".equals(s);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getOpcionesmultiples() {
		return opcionesmultiples;
	}

	public void setOpcionesmultiples(String opcionesmultiples) {
		this.opcionesmultiples = opcionesmultiples;
	}
	
	
}
