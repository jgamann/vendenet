
package com.vendenet.negocio.entidad;

import java.util.Date;

import com.vendenet.utilidades.constantes.TextConstant;

public class Adjunto extends BasicBean{
	
	private String path;
	private CategoriaAdjunto categoriaAdjunto;
	private Date fecha;
	private Anuncio anuncio;
	private boolean principal;
	
	public Adjunto() {
		super();
		setName(TextConstant.BLANK);
		this.path=TextConstant.BLANK;
		// TODO Apéndice de constructor generado automáticamente
	}

	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public CategoriaAdjunto getCategoriaAdjunto() {
		return categoriaAdjunto;
	}


	public void setCategoriaAdjunto(CategoriaAdjunto categoriaAdjunto) {
		this.categoriaAdjunto = categoriaAdjunto;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
	
	
	
}
