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

import com.vendenet.negocio.entidad.Usuario;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoValidarLoginIntranet;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.UtilidadesVendenet;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class ValidarLoginIntranet implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(ValidarLoginIntranet.class);
	private ValidarLoginIntranet inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public ValidarLoginIntranet(){
	}
	
	public ValidarLoginIntranet(HttpServletRequest req) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			
			HttpSession sesion = req.getSession();
			NegoValidarLoginIntranet negoValidarLoginIntranet = new NegoValidarLoginIntranet();
			String user=req.getParameter("usuarioform");
			String pass=req.getParameter("passform");
			transaction = session.beginTransaction();
			Usuario usuario = null;
			if(sesion.getAttribute(TextConstant.KEY_USUARIO)==null)
				usuario = negoValidarLoginIntranet.validarUsuario(session,user,pass);
			else usuario=(Usuario)sesion.getAttribute(TextConstant.KEY_USUARIO);
			if(usuario!=null){
				negoValidarLoginIntranet.recogerAnunciosParaValidar(session);
				negoValidarLoginIntranet.calcularAnuncioPorRevisar(session);
				hsResultados = negoValidarLoginIntranet.getHsResultados();
				sesion.setAttribute(TextConstant.KEY_USUARIO, usuario);
				UtilidadesVendenet.registrarVisitaIntranet(session, req, true, user, pass);
				strPaginaDestino="jsp/intranet.jsp";
			}else{
				UtilidadesVendenet.registrarVisitaIntranet(session, req, false, user, pass);
				strPaginaDestino="jsp/login_intranet.jsp";
			}
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
		inicio = new ValidarLoginIntranet(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
}
