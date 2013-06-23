package com.vendenet.negocio.entidad;

import java.util.Date;

public class Critica extends BasicBean {

	
	private String emailCliente;
	private String emailCriticon;
	private String nombreCriticon;
	private String texto;
	private Date fechaAlta;
	private double valoracion;
	private boolean confirmada;
	private String codigo;
	
	public Critica() {
		super();
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getEmailCriticon() {
		return emailCriticon;
	}

	public void setEmailCriticon(String emailCriticon) {
		this.emailCriticon = emailCriticon;
	}

	public String getNombreCriticon() {
		return nombreCriticon;
	}

	public void setNombreCriticon(String nombreCriticon) {
		this.nombreCriticon = nombreCriticon;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public double getValoracion() {
		return valoracion;
	}

	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}

	public boolean isConfirmada() {
		return confirmada;
	}

	public void setConfirmada(boolean confirmada) {
		this.confirmada = confirmada;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	
}
