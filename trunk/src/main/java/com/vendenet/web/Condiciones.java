/*
 * Creado el 06-jul-06
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package com.vendenet.web;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoCondiciones;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.constantes.ConstantesVendenet;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class Condiciones implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(Condiciones.class);
	private Condiciones inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";	
	private ErrorVendenet err;
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public Condiciones(){
	}
	
	public Condiciones(HttpServletRequest req) {
		NegoCondiciones negoCondiciones = new NegoCondiciones();
		try{
			negoCondiciones.obtenerTexto();
			strPaginaDestino="jsp/mensaje_vendenet.jsp";
			hsResultados = negoCondiciones.getHsResultados();
		}catch(ErrorVendenet e){
			logger.error(e);
			hsResultados = negoCondiciones.getHsResultados();
			strPaginaDestino="jsp/mensaje_vendenet.jsp";
		}
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new Condiciones(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
