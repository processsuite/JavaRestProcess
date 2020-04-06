/**
 * Field.java 
 *
 */
package com.process.domain.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Domain Field class for document Module 
 * @author Oswel Sanchez
 * 
 */
@XmlRootElement
public class Field implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer idPadre;
	
	private String nombre;
	
	private String value;
	
	private Double valueNumber;
	
	//T texto,O calculado,N numero,F fecha,C cantidad,H hora,Q multilinea,L lista, S alternativa 
	//M matriz,En matriz todos los anteriores y se sobreescribe A lista, V check
	private String tipo;
	
	private Double left;
	
	private Double top;
	
	private Double right;
	
	private Double Bottom;
	
	private String descripcion;
	
	private Boolean lectura;
	
	private Boolean escritura;
	
	private Boolean obligatorio;
	
	private Integer longitudMaxima;
	
	private Boolean evento;
	
	private String desEvento;
	
	private Boolean listaAjax;
	
	//use for tipo=L --------
	private List<ItemOption> options;
	
	private Boolean multiple;
	
	private List<String> valueM;
	//-----------------------
	
	//use for tipo=N --------
	private Integer decimal;
	
	private Double minimo;
	
	private Double maximo;
	//----------------------	
	
	//use for tipo=S,V ----------------
	private List<Field> alternativas;
	
	private Boolean exclusivo;
	
	private Boolean checked;
	
	private Boolean visible;
	//-------------------------------

	private Matriz matriz;//use for tipo=M
	
	private Boolean change;//use only for process saved doc
	
	private String href;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getLeft() {
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
		return Bottom;
	}

	public void setBottom(Double bottom) {
		Bottom = bottom;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getLectura() {
		return lectura;
	}

	public void setLectura(Boolean lectura) {
		this.lectura = lectura;
	}

	public Boolean getEscritura() {
		return escritura;
	}

	public void setEscritura(Boolean escritura) {
		this.escritura = escritura;
	}

	public Boolean getObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(Boolean obligatorio) {
		this.obligatorio = obligatorio;
	}

	public Integer getLongitudMaxima() {
		return longitudMaxima;
	}

	public void setLongitudMaxima(Integer longitudMaxima) {
		this.longitudMaxima = longitudMaxima;
	}

	public Boolean getEvento() {
		return evento;
	}

	public void setEvento(Boolean evento) {
		this.evento = evento;
	}

	public String getDesEvento() {
		return desEvento;
	}

	public void setDesEvento(String desEvento) {
		this.desEvento = desEvento;
	}

	public List<ItemOption> getOptions() {
		if (options==null){
			options = new ArrayList<ItemOption>();
		}
		return options;
	}

	public void setOptions(List<ItemOption> options) {
		this.options = options;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public List<String> getValueM() {
		if (valueM==null){
			valueM = new ArrayList<String>();
		}
		return valueM;
	}

	public void setValueM(List<String> valueM) {
		this.valueM = valueM;
	}

	public Integer getDecimal() {
		return decimal;
	}

	public void setDecimal(Integer decimal) {
		this.decimal = decimal;
	}

	public Double getMinimo() {
		return minimo;
	}

	public void setMinimo(Double minimo) {
		this.minimo = minimo;
	}

	public Double getMaximo() {
		return maximo;
	}

	public void setMaximo(Double maximo) {
		this.maximo = maximo;
	}

	public List<Field> getAlternativas() {
		if (alternativas==null){
			alternativas = new ArrayList<Field>();
		}
		return alternativas;
	}

	public void setAlternativas(List<Field> alternativas) {
		this.alternativas = alternativas;
	}

	public Boolean getExclusivo() {
		return exclusivo;
	}

	public void setExclusivo(Boolean exclusivo) {
		this.exclusivo = exclusivo;
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
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

	public Boolean getChange() {
		return change;
	}

	public void setChange(Boolean change) {
		this.change = change;
	}

	public Double getValueNumber() {
		return valueNumber;
	}

	public void setValueNumber(Double valueNumber) {
		this.valueNumber = valueNumber;
	}

	public Boolean getListaAjax() {
		return listaAjax;
	}

	public void setListaAjax(Boolean listaAjax) {
		this.listaAjax = listaAjax;
	}

	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	@Override
	public String toString() {
		return "Field [id=" + id + ", idPadre=" + idPadre + ", nombre="
				+ nombre + ", value=" + value + ", valueNumber=" + valueNumber
				+ ", tipo=" + tipo + ", left=" + left + ", top=" + top
				+ ", right=" + right + ", Bottom=" + Bottom + ", descripcion="
				+ descripcion + ", lectura=" + lectura + ", escritura="
				+ escritura + ", obligatorio=" + obligatorio
				+ ", longitudMaxima=" + longitudMaxima + ", evento=" + evento
				+ ", desEvento=" + desEvento + ", listaAjax=" + listaAjax
				+ ", options=" + options + ", multiple=" + multiple
				+ ", valueM=" + valueM + ", decimal=" + decimal + ", minimo="
				+ minimo + ", maximo=" + maximo + ", alternativas="
				+ alternativas + ", exclusivo=" + exclusivo + ", checked="
				+ checked + ", visible=" + visible + ", matriz=" + matriz
				+ ", change=" + change + ", href=" + href + "]";
	}

	
	
}
