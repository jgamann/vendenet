
package com.vendenet.negocio.entidad;

import java.util.Date;


public class VisitaWeb extends BasicBean{
	
	private Date fechaVisita;
	private String navegador;
		
	public VisitaWeb() {
		super();
		// TODO Ap�ndice de constructor generado autom�ticamente
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
	
}
