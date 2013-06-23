package com.vendenet.negocio.entidad;

import java.util.Date;
import java.util.List;

public class Cliente extends BasicBean{

	private String email;
	private String pass;
	private String telefono;
	private List anuncios;
	private Date fechaAlta;
	/**
	 * 
	 */
	public Cliente(){
		super();
		// TODO Apéndice de constructor generado automáticamente
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List anuncios) {
		this.anuncios = anuncios;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	

}
