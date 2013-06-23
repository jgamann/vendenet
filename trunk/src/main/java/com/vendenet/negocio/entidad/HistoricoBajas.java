package com.vendenet.negocio.entidad;

import java.util.Date;

public class HistoricoBajas extends BasicBean {

	private String emailCliente;
	private String tituloAnuncio;
	private Date fechaBorrado;
	
	private String cuerpoAnuncio;
	private double precio;
	private CategoriaAnuncio categoria;
	private TipoAnuncio tipoAnuncio;
	private Provincia provincia;
	
	public HistoricoBajas() {
		super();
		// TODO Ap�ndice de constructor generado autom�ticamente
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getTituloAnuncio() {
		return tituloAnuncio;
	}

	public void setTituloAnuncio(String tituloAnuncio) {
		this.tituloAnuncio = tituloAnuncio;
	}

	public Date getFechaBorrado() {
		return fechaBorrado;
	}

	public void setFechaBorrado(Date fechaBorrado) {
		this.fechaBorrado = fechaBorrado;
	}

	public String getCuerpoAnuncio() {
		return cuerpoAnuncio;
	}

	public void setCuerpoAnuncio(String cuerpoAnuncio) {
		this.cuerpoAnuncio = cuerpoAnuncio;
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

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	
	
	
}
