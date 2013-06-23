/*
 * Creado el 06-jul-06
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package com.vendenet.web;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vendenet.negocio.entidad.Cliente;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoGestorAreaGestion;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class GestorAjaxAreaGestion implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(GestorAjaxAreaGestion.class);
	private GestorAjaxAreaGestion inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public GestorAjaxAreaGestion(){
	}
	
	public GestorAjaxAreaGestion(HttpServletRequest req) {
		Transaction transaction = null;
		try{
			String subAccion=req.getParameter("subAccion");
			NegoGestorAreaGestion negoGestorAreaGestion = new NegoGestorAreaGestion();
			HttpSession sesion = req.getSession();
			Session session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			if(subAccion.equalsIgnoreCase("recordarClave")){
				String idAnuncio=req.getParameter("idAnuncio");
				if(idAnuncio!=null){//Si es un unico idAnuncio se llamara a la funcion
					negoGestorAreaGestion.recordarClave(idAnuncio,session);
				}
				hsResultados = negoGestorAreaGestion.getHsResultados();
			}else if(subAccion.equalsIgnoreCase("eliminarAnuncio")){
				String idAnuncio=req.getParameter("idAnuncio");
				String pass=req.getParameter("pass");
				negoGestorAreaGestion.borrarAnuncio(idAnuncio, pass, session);
			}else if(subAccion.equalsIgnoreCase("modificarAnuncio")){
				String idAnuncio=req.getParameter("idAnuncio");
				String pass=req.getParameter("pass");
				Cliente cliente = null;
				cliente=negoGestorAreaGestion.validarClave(idAnuncio, pass, session);
				if(cliente!=null){
					sesion.setAttribute(TextConstant.KEY_CLIENTE, cliente);
				}
			}
			hsResultados = negoGestorAreaGestion.getHsResultados();
			transaction.commit();
			session.close();
			strPaginaDestino="jsp/respuestaGeneral.jsp";
		}catch(ErrorVendenet e){
			if(transaction!=null)transaction.rollback();
			logger.error(e);
			strPaginaDestino=strErrPagina;
		}
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new GestorAjaxAreaGestion(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
