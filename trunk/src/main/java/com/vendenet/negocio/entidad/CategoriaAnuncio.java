package com.vendenet.negocio.entidad;


public class CategoriaAnuncio extends BasicBean{

	private CategoriaAnuncio categoriapadre;
	private int orden;
	/**
	 * 
	 */
	public CategoriaAnuncio(){
		super();
		// TODO Ap�ndice de constructor generado autom�ticamente
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
