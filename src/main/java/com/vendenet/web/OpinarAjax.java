/*
 * Creado el 06-jul-06
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package com.vendenet.web;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoOpinarAjax;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.UtilidadesTexto;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class OpinarAjax implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(OpinarAjax.class);
	private OpinarAjax inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public OpinarAjax(){
	}
	
	public OpinarAjax(HttpServletRequest req) {
		try{
			String subAccion=req.getParameter("subAccion");
			NegoOpinarAjax negoOpinarAjax = new NegoOpinarAjax();
			String valoracion=req.getParameter("valoracion");
			String nombre=UtilidadesTexto.buscarCaracteresFormato(req.getParameter("nombre"));
			String email=req.getParameter("email");
			String texto=UtilidadesTexto.buscarCaracteresFormato(req.getParameter("texto"));
			String idAnuncio=req.getParameter("idAnuncio");
			if(valoracion != null &&
					nombre != null &&
						email != null &&
							texto != null &&
								idAnuncio != null){
				Integer intIdAnuncio = new Integer(idAnuncio);
				Integer intValoracion = new Integer(valoracion);
				negoOpinarAjax.opinar(intValoracion,nombre,email,texto,intIdAnuncio);
				hsResultados = negoOpinarAjax.getHsResultados();
			}else hsResultados.put(TextConstant.KEY_RESPUESTA, "Algun campo es incorrecto, reviselo por favor");
			strPaginaDestino="jsp/respuestaGeneral.jsp";
		}catch(ErrorVendenet e){
			hsResultados.put(TextConstant.KEY_RESPUESTA, "Algun campo es incorrecto, reviselo por favor");
			logger.error(e);
			strPaginaDestino="jsp/respuestaGeneral.jsp";
		}
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new OpinarAjax(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
