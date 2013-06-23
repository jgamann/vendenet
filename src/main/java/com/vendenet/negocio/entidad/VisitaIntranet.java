
package com.vendenet.negocio.entidad;

import java.util.Date;


public class VisitaIntranet extends BasicBean{
	
	private boolean loginOk;
	private Date fechaVisita;
	private String navegador;
	private String ip;

	public VisitaIntranet() {
		super();
		// TODO Apéndice de constructor generado automáticamente
	}

	public Date getFechaVisita() {
		return fechaVisita;
	}

	public void setFechaVisita(Date fechaVisita) {
		this.fechaVisita = fechaVisita;
	}

	public String getNavegador() {
		return navegador;
	}

	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	public boolean isLoginOk() {
		return loginOk;
	}

	public void setLoginOk(boolean loginOk) {
		this.loginOk = loginOk;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	
}
