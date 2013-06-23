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
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.UtilidadesMail;
import com.vendenet.utilidades.UtilidadesWeb;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class InvocarEnvioEmail implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(InvocarEnvioEmail.class);
	private InvocarEnvioEmail inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";	
	private ErrorVendenet err;
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public InvocarEnvioEmail(){
	}
	
	public InvocarEnvioEmail(HttpServletRequest req) {
		boolean resultado=UtilidadesMail.enviarEmailsPendientes();
		hsResultados.put(TextConstant.KEY_RESPUESTA, new Boolean(resultado).toString());
		strPaginaDestino="jsp/respuestaGeneral.jsp";
	}
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new InvocarEnvioEmail(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
