package com.vendenet.negocio.entidad;


public class CategoriaAnuncio extends BasicBean{

	private CategoriaAnuncio categoriapadre;
	private int orden;
	/**
	 * 
	 */
	public CategoriaAnuncio(){
		super();
		// TODO Apéndice de constructor generado automáticamente
	}
	public CategoriaAnuncio getCategoriapadre() {
		return categoriapadre;
	}
	public void setCategoriapadre(CategoriaAnuncio categoriapadre) {
		this.categoriapadre = categoriapadre;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
		

}
