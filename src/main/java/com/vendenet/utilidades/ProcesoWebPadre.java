/*
 * Creado el 06-jul-06
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package com.vendenet.utilidades;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public interface ProcesoWebPadre {
	
	public ProcesoWebPadre getInstance(HttpServletRequest req);

	public Hashtable getResultados();

	public String getPagina();
}

