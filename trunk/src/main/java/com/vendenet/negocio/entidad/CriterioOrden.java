package com.vendenet.negocio.entidad;


public class CriterioOrden extends BasicBean{

	private String clave;
	private boolean asc;
		
	/**
	 * 
	 */
	public CriterioOrden(){
		super();
		// TODO Apéndice de constructor generado automáticamente
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	

}
