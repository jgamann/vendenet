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

import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoAnuncio;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.UtilidadesNumericos;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class VerAnuncioIntranet implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(VerAnuncioIntranet.class);
	private VerAnuncioIntranet inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public VerAnuncioIntranet(){
	}
	
	public VerAnuncioIntranet(HttpServletRequest req) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			HttpSession sesion = req.getSession();
			String idAnuncio=req.getParameter("idAnuncio");
			NegoAnuncio negoAnuncio = new NegoAnuncio();
			if(sesion.getAttribute(TextConstant.KEY_USUARIO)!=null){
				if(UtilidadesNumericos.isNumberInteger(idAnuncio)){
					transaction = session.beginTransaction();
					Anuncio anuncio = negoAnuncio.obtenerAnuncioPorId(new Integer(idAnuncio),session);
					if(anuncio!=null){
						hsResultados = negoAnuncio.getHsResultados();
						strPaginaDestino="jsp/fichaAnuncio_intranet.jsp";
					}else strPaginaDestino="jsp/anuncio_no_encontrado.jsp";
					transaction.commit();
				}else strPaginaDestino="jsp/anuncio_no_encontrado.jsp";
			}else strPaginaDestino="jsp/anuncio_no_encontrado.jsp";
		session.close();
		}catch(ErrorVendenet e){
			if(transaction!=null)transaction.rollback();
			logger.error(e);
			strPaginaDestino=strErrPagina;
		}
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new VerAnuncioIntranet(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
