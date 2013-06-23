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
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoAreaGestion;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.constantes.ConstantesVendenet;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class AreaGestion implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(AreaGestion.class);
	private AreaGestion inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";	
	private ErrorVendenet err;
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public AreaGestion(){
	}
	
	public AreaGestion(HttpServletRequest req) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			NegoAreaGestion negoAreaGestion = new NegoAreaGestion();
			String subAccion=req.getParameter("subAccion");
			String idAnuncio=req.getParameter("idAnuncio");
			transaction = session.beginTransaction();
			if(idAnuncio!=null){
				negoAreaGestion.recogerContenido(idAnuncio,subAccion,session);
				strPaginaDestino="jsp/area_gestion.jsp";
				hsResultados = negoAreaGestion.getHsResultados();
				transaction.commit();
			}else{
				strPaginaDestino=strErrPagina;
			}
			session.close();
		}catch(ErrorVendenet e){
			if(transaction!=null)transaction.rollback();
			System.err.println(e);
			logger.error(e);
			strPaginaDestino=strErrPagina;
		}
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new AreaGestion(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
