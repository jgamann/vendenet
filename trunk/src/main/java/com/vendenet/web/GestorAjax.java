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

import com.vendenet.negocio.entidad.CriterioOrden;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoGestorAjax;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.UtilidadesNumericos;
import com.vendenet.utilidades.UtilidadesTexto;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class GestorAjax implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(GestorAjax.class);
	private GestorAjax inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public GestorAjax(){
	}
	
	public GestorAjax(HttpServletRequest req) {
		try{
			String subAccion=req.getParameter("subAccion");
			NegoGestorAjax negoGestorAjax = new NegoGestorAjax();
			HttpSession sesion = req.getSession();
			if (subAccion.equalsIgnoreCase("buscar")){
				String patron=req.getParameter("patron");
				negoGestorAjax.buscar(patron,sesion);
				hsResultados = negoGestorAjax.getHsResultados();
				strPaginaDestino="jsp/imprimirResultados.jsp";
			}else if(subAccion.equalsIgnoreCase("cambiarProvincia")){
				String idProvincia=req.getParameter("idFiltro");
				if((idProvincia!=null)&&(UtilidadesNumericos.isNumber(idProvincia))){
					if(Integer.parseInt(idProvincia)==0)sesion.removeAttribute(TextConstant.KEY_PROVINCIA);
					else sesion.setAttribute(TextConstant.KEY_PROVINCIA, negoGestorAjax.obtenerProvincia(idProvincia));
				}else sesion.removeAttribute(TextConstant.KEY_PROVINCIA);
				hsResultados.put(TextConstant.KEY_RESPUESTA, TextConstant.OK);
				strPaginaDestino="jsp/respuestaGeneral.jsp";
			}else if(subAccion.equalsIgnoreCase("cambiarCategoria")){
				String idCategoria=req.getParameter("idFiltro");
				if((idCategoria!=null)&&(UtilidadesNumericos.isNumber(idCategoria))){
					if(Integer.parseInt(idCategoria)==0)sesion.removeAttribute(TextConstant.KEY_CATEGORIA);
					else sesion.setAttribute(TextConstant.KEY_CATEGORIA, negoGestorAjax.obtenerCategoria(idCategoria));
				}else sesion.removeAttribute(TextConstant.KEY_CATEGORIA);
				hsResultados.put(TextConstant.KEY_RESPUESTA, TextConstant.OK);
				strPaginaDestino="jsp/respuestaGeneral.jsp";
			}else if(subAccion.equalsIgnoreCase("cambiarCriterioOrden")){
				String idOrden=req.getParameter("idFiltro");
				String asc=req.getParameter("asc");
				CriterioOrden ordenTemp=null;
				if((idOrden!=null)&&(UtilidadesNumericos.isNumber(idOrden))){
					if(UtilidadesTexto.isTrue(asc))ordenTemp=negoGestorAjax.obtenerOrden(idOrden,true);
					else ordenTemp=negoGestorAjax.obtenerOrden(idOrden,false);
				}else ordenTemp=negoGestorAjax.obtenerOrden(TextConstant.ID_CRITERIO_ORDEN_FECHA, false);
				sesion.setAttribute(TextConstant.KEY_CRITERIO_ORDEN, ordenTemp);
				hsResultados.put(TextConstant.KEY_RESPUESTA, TextConstant.OK);
				strPaginaDestino="jsp/respuestaGeneral.jsp";
			}else if(subAccion.equalsIgnoreCase("cambiarTipoAnuncio")){
				String idTipoAnuncio=req.getParameter("idFiltro");
				if((idTipoAnuncio!=null)&&(UtilidadesNumericos.isNumber(idTipoAnuncio))){
					if(Integer.parseInt(idTipoAnuncio)==0)sesion.removeAttribute(TextConstant.KEY_TIPO_ANUNCIO);
					else sesion.setAttribute(TextConstant.KEY_TIPO_ANUNCIO, negoGestorAjax.obtenerTipoAnuncio(idTipoAnuncio));
				}else sesion.removeAttribute(TextConstant.KEY_TIPO_ANUNCIO);
				hsResultados.put(TextConstant.KEY_RESPUESTA, TextConstant.OK);
				strPaginaDestino="jsp/respuestaGeneral.jsp";
			}else if(subAccion.equalsIgnoreCase("cambiarTipoVendedor")){
				String idTipoVendedor=req.getParameter("idFiltro");
				if((idTipoVendedor!=null)&&(UtilidadesNumericos.isNumber(idTipoVendedor))){
					if(Integer.parseInt(idTipoVendedor)==0)sesion.removeAttribute(TextConstant.KEY_TIPO_VENDEDOR);
					else sesion.setAttribute(TextConstant.KEY_TIPO_VENDEDOR, negoGestorAjax.obtenerTipoVendedor(idTipoVendedor));
				}else sesion.removeAttribute(TextConstant.KEY_TIPO_VENDEDOR);
				hsResultados.put(TextConstant.KEY_RESPUESTA, TextConstant.OK);
				strPaginaDestino="jsp/respuestaGeneral.jsp";
			}
		}catch(ErrorVendenet e){
			logger.error(e);
			strPaginaDestino=strErrPagina;
		}
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new GestorAjax(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
