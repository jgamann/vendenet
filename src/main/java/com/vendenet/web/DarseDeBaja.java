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
import com.vendenet.negocio.negocio.NegoDarseDeBaja;
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
public class DarseDeBaja implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(DarseDeBaja.class);
	private DarseDeBaja inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";	
	private ErrorVendenet err;
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public DarseDeBaja(){
	}
	
	public DarseDeBaja(HttpServletRequest req) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		NegoDarseDeBaja negoDarseDeBaja = new NegoDarseDeBaja();
		try{
			HttpSession sesion = req.getSession();
			String idCliente=req.getParameter("idCliente");
			String email=req.getParameter("email");
			String pass=req.getParameter("pass");
			String confirmacion=req.getParameter("confirmacion");
			transaction = session.beginTransaction();
			if(sesion.getAttribute(TextConstant.KEY_VISITA_REGISTRADA)==null){
				UtilidadesWeb.aumentarVisitasWeb(session, req.getRemoteAddr(),req.getHeader(TextConstant.USER_AGENT));
				sesion.setAttribute(TextConstant.KEY_VISITA_REGISTRADA,true);
			}
			if(confirmacion==null){
				strPaginaDestino="jsp/confirmacion_baja.jsp";	
			}else{
				negoDarseDeBaja.baja(session,idCliente,email,pass);
				strPaginaDestino="jsp/confirmacion.jsp";
			}
			
			transaction.commit();
			session.close();
			hsResultados = negoDarseDeBaja.getHsResultados();
		}catch(ErrorVendenet e){
			if(transaction!=null)transaction.rollback();
			logger.error(e);
			hsResultados = negoDarseDeBaja.getHsResultados();
			strPaginaDestino="jsp/confirmacion.jsp";
		}
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new DarseDeBaja(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
