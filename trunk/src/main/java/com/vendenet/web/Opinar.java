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
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoOpinar;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.UtilidadesWeb;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class Opinar implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(Opinar.class);
	private Opinar inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";	
	private ErrorVendenet err;
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public Opinar(){
	}
	
	public Opinar(HttpServletRequest req) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			String idAnuncio=req.getParameter("idAnuncio");
			HttpSession sesion = req.getSession();
			NegoOpinar negoOpinar = new NegoOpinar();
			transaction = session.beginTransaction();
			if(sesion.getAttribute(TextConstant.KEY_VISITA_REGISTRADA)==null){
				UtilidadesWeb.aumentarVisitasWeb(session, req.getRemoteAddr(),req.getHeader(TextConstant.USER_AGENT));
				sesion.setAttribute(TextConstant.KEY_VISITA_REGISTRADA,true);
			}
			negoOpinar.recogerContenido(idAnuncio, session);
			strPaginaDestino="jsp/opinar.jsp";
			hsResultados=negoOpinar.getHsResultados();
			transaction.commit();
			session.close();
		}catch(ErrorVendenet e){
			if(transaction!=null)transaction.rollback();
			logger.error(e);
			strPaginaDestino=strErrPagina;
		}
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new Opinar(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
