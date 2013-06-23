/*
 * Creado el 06-jul-06
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package com.vendenet.web;

import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoGestorAjax;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.UtilidadesCritica;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class GestorAjaxIntranet implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(GestorAjaxIntranet.class);
	private GestorAjaxIntranet inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public GestorAjaxIntranet(){
	}
	
	public GestorAjaxIntranet(HttpServletRequest req) {
		Transaction transaction = null;
		try{
			String subAccion=req.getParameter("subAccion");
			NegoGestorAjax negoGestorAjax = new NegoGestorAjax();
			HttpSession sesion = req.getSession();
			if(sesion.getAttribute(TextConstant.KEY_USUARIO)!=null){
				Session session=HibernateUtil.getSessionFactory().openSession();
				transaction=session.beginTransaction();
				if(subAccion.equalsIgnoreCase("activarAnuncio")){
					String idAnuncio=req.getParameter("idAnuncio");
					if(idAnuncio!=null){//Si es un unico idAnuncio se llamara a la funcion
						negoGestorAjax.cambiarEstadoAnuncio(idAnuncio,session,true);
						negoGestorAjax.guardarEnvioMailAceptado(idAnuncio,session);
					}
					String idsAnuncio=req.getParameter("idsAnuncio");
					if(idsAnuncio!=null){//Si es un grupo de ids de anuncio, habra que hacer el bucle.
						StringTokenizer st = new StringTokenizer(idsAnuncio,";",false);
						while(st.hasMoreTokens()){
							String idAnuncio__ = st.nextToken();
							negoGestorAjax.cambiarEstadoAnuncio(idAnuncio__,session,true);
							negoGestorAjax.guardarEnvioMailAceptado(idAnuncio__,session);
						}
					}
					hsResultados = negoGestorAjax.getHsResultados();
					UtilidadesCritica.meterOpinionesVendenet(session);
				}else if(subAccion.equalsIgnoreCase("despublicarAnuncio")){
					String idAnuncio=req.getParameter("idAnuncio");
					if(idAnuncio!=null){//Si es un unico idAnuncio se llamara a la funcion
						negoGestorAjax.cambiarEstadoAnuncio(idAnuncio,session,false);
					}
					String idsAnuncio=req.getParameter("idsAnuncio");
					if(idsAnuncio!=null){//Si es un grupo de ids de anuncio, habra que hacer el bucle.
						StringTokenizer st = new StringTokenizer(idsAnuncio,";",false);
						while(st.hasMoreTokens()){
							negoGestorAjax.cambiarEstadoAnuncio(st.nextToken(),session,false);
						}
					}
					hsResultados = negoGestorAjax.getHsResultados();
				}else if(subAccion.equalsIgnoreCase("denegarAnuncio")){
					String idAnuncio=req.getParameter("idAnuncio");
					if(idAnuncio!=null){//Si es un unico idAnuncio se llamara a la funcion
						negoGestorAjax.cambiarEstadoAnuncio(idAnuncio,session,false);
						negoGestorAjax.borrarAnuncio(idAnuncio,session);
					}
					String idsAnuncio=req.getParameter("idsAnuncio");
					if(idsAnuncio!=null){//Si es un grupo de ids de anuncio, habra que hacer el bucle.
						StringTokenizer st = new StringTokenizer(idsAnuncio,";",false);
						while(st.hasMoreTokens()){
							String idAnuncioTmp = st.nextToken();
							negoGestorAjax.cambiarEstadoAnuncio(idAnuncioTmp,session,false);
							negoGestorAjax.borrarAnuncio(idAnuncioTmp,session);
						}
					}
					hsResultados = negoGestorAjax.getHsResultados();
				}else if(subAccion.equalsIgnoreCase("devolverAnuncio")){
					String idAnuncio=req.getParameter("idAnuncio");
					String motivo=req.getParameter("motivo");
					if(idAnuncio!=null){//Si es un unico idAnuncio se llamara a la funcion
						negoGestorAjax.devolverAnuncio(idAnuncio,session,false);
						negoGestorAjax.guardarEnvioMailDevuelto(idAnuncio,session,motivo);
					}
					String idsAnuncio=req.getParameter("idsAnuncio");
					if(idsAnuncio!=null){//Si es un grupo de ids de anuncio, habra que hacer el bucle.
						StringTokenizer st = new StringTokenizer(idsAnuncio,";",false);
						while(st.hasMoreTokens()){
							String idAnuncio__ = st.nextToken();
							negoGestorAjax.devolverAnuncio(idAnuncio__,session,false);
							negoGestorAjax.guardarEnvioMailDevuelto(idAnuncio__,session,motivo);
						}
					}
					hsResultados = negoGestorAjax.getHsResultados();
				}else if(subAccion.equalsIgnoreCase("cerrarSesion")){
					sesion.removeAttribute(TextConstant.KEY_USUARIO);
					hsResultados.put(TextConstant.KEY_RESPUESTA, TextConstant.OK);
					strPaginaDestino="jsp/respuestaGeneral.jsp";
				}
				transaction.commit();
				session.close();
			}else hsResultados.put(TextConstant.KEY_RESPUESTA, TextConstant.SESION_CADUCADA);
			strPaginaDestino="jsp/respuestaGeneral.jsp";
		}catch(ErrorVendenet e){
			if(transaction!=null)transaction.rollback();
			logger.error(e);
			strPaginaDestino=strErrPagina;
		}
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new GestorAjaxIntranet(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
