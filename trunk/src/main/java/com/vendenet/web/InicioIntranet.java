/*
 * Creado el 06-jul-06
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package com.vendenet.web;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.constantes.ConstantesVendenet;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class InicioIntranet implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(InicioIntranet.class);
	private InicioIntranet inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";	
	private ErrorVendenet err;
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public InicioIntranet(){
	}
	
	public InicioIntranet(HttpServletRequest req) {
		strPaginaDestino="jsp/login_intranet.jsp";
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new InicioIntranet(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
