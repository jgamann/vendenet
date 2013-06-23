package com.vendenet.negocio.entidad;

import java.util.Date;
import java.util.List;

import com.vendenet.utilidades.UtilidadesFecha;
import com.vendenet.utilidades.constantes.TextConstant;

public class Anuncio extends BasicBean {

	private Cliente cliente;
	private Date fechaAlta;
	private TipoVendedor tipoVendedor;
	private String cuerpo;
	private double precio;
	private CategoriaAnuncio categoria;
	private TipoAnuncio tipoAnuncio;
	private boolean destacado;
	private Date fechaCaducidad;
	private Date fechaBorrado;
	private List adjuntos;
	private Provincia provincia;
	private List visitas;
	private boolean publicado;
	private boolean revisado;
	private double mediaCriticas;
	private int numCriticas;
	
	public Anuncio() {
		super();
		// TODO Ap�ndice de constructor generado autom�ticamente
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public TipoVendedor getTipoVendedor() {
		return tipoVendedor;
	}

	public void setTipoVendedor(TipoVendedor tipoVendedor) {
		this.tipoVendedor = tipoVendedor;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public CategoriaAnuncio getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaAnuncio categoria) {
		this.categoria = categoria;
	}

	public TipoAnuncio getTipoAnuncio() {
		return tipoAnuncio;
	}

	public void setTipoAnuncio(TipoAnuncio tipoAnuncio) {
		this.tipoAnuncio = tipoAnuncio;
	}

	public boolean isDestacado() {
		return destacado;
	}

	public void setDestacado(boolean destacado) {
		this.destacado = destacado;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Date getFechaBorrado() {
		return fechaBorrado;
	}

	public void setFechaBorrado(Date fechaBorrado) {
		this.fechaBorrado = fechaBorrado;
	}

	public List getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(List adjuntos) {
		this.adjuntos = adjuntos;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public List getVisitas() {
		return visitas;
	}

	public void setVisitas(List visitas) {
		this.visitas = visitas;
	}

	public boolean isPublicado() {
		return publicado;
	}

	public void setPublicado(boolean publicado) {
		this.publicado = publicado;
	}
	
	private String meterAsterisco(){
		StringBuffer dev = new StringBuffer();
		//if(!isPublicado())dev.append(TextConstant.FLAG_NO_PUBLICADO);
		if(!isRevisado())dev.append(TextConstant.FLAG_REVISION);
		return dev.toString();
	}
	
	public boolean isRevisado() {
		return revisado;
	}

	public void setRevisado(boolean revisado) {
		this.revisado = revisado;
	}

	public String toString(){
		return new StringBuffer(meterAsterisco()).append(UtilidadesFecha.formatearFechaDDMMYY(fechaAlta)).append(TextConstant.SPACE)
			.append(getName()).append(TextConstant.SPACE)
			.append(categoria.getName()).append(TextConstant.SPACE)
			.append(provincia.getName()).append(TextConstant.SPACE)
			.append(precio).toString();
	}

	public double getMediaCriticas() {
		return mediaCriticas;
	}

	public void setMediaCriticas(double mediaCriticas) {
		this.mediaCriticas = mediaCriticas;
	}

	public int getNumCriticas() {
		return numCriticas;
	}

	public void setNumCriticas(int numCriticas) {
		this.numCriticas = numCriticas;
	}

	

}
