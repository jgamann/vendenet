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

import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoAnuncio;
import com.vendenet.negocio.negocio.NegoInicio;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.UtilidadesNumericos;
import com.vendenet.utilidades.UtilidadesTexto;
import com.vendenet.utilidades.UtilidadesWeb;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class VerAnuncio implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(VerAnuncio.class);
	private VerAnuncio inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public VerAnuncio(){
	}
	
	public VerAnuncio(HttpServletRequest req) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			String isSearch=req.getParameter("isSearch");
			String pagina=req.getParameter("pagina");
			HttpSession sesion = req.getSession();
			transaction = session.beginTransaction();
			boolean isMobile=UtilidadesTexto.IsMobile(req.getHeader(TextConstant.USER_AGENT));
//			isMobile=true;
			String idAnuncio=req.getParameter("idAnuncio");
			String idAnuncioFicha=req.getParameter("idAnuncioFicha");
			String patron=req.getParameter("nombre");
			String desde=req.getParameter("desde");
			if(sesion.getAttribute(TextConstant.KEY_VISITA_REGISTRADA)==null){//Aumentamos las visitas a la web globales
				UtilidadesWeb.aumentarVisitasWeb(session, req.getRemoteAddr(),req.getHeader(TextConstant.USER_AGENT));
				sesion.setAttribute(TextConstant.KEY_VISITA_REGISTRADA,true);
			}
			if(desde!=null && desde.equals("fichaAnuncio")){//Vengo de la web de ficha de anuncio
				if(idAnuncioFicha!=null && !idAnuncioFicha.equals("")){
					NegoAnuncio negoAnuncio = new NegoAnuncio();
					if(UtilidadesNumericos.isNumberInteger(idAnuncioFicha)){
						Anuncio anuncio = negoAnuncio.obtenerAnuncioActivoPorId(new Integer(idAnuncioFicha),session);
						if(anuncio!=null){
							negoAnuncio.aumentarVisitasAnuncio(anuncio,session,req.getRemoteAddr());//Aumentamos las visitas al anuncio por IP
							hsResultados = negoAnuncio.getHsResultados();
							if(isMobile)strPaginaDestino="jsp/fichaAnuncio_mobile.jsp";
							else strPaginaDestino="jsp/fichaAnuncio.jsp";
						}else strPaginaDestino="jsp/anuncio_no_encontrado.jsp";
						transaction.commit();
						session.close();
					}else strPaginaDestino="jsp/anuncio_no_encontrado.jsp";
				}else{//No he pinchado en una foto del ajax asi que es una busqueda
					NegoInicio negoInicio = new NegoInicio();
					negoInicio.recogerContenido(patron,"1",sesion,session);
					hsResultados = negoInicio.getHsResultados();
					if(isMobile==true)strPaginaDestino="jsp/index_mobile.jsp";
					else strPaginaDestino="jsp/index.jsp";
				}
			}else{//Estoy en la index.jsp
				if(isSearch!= null && isSearch.equals(TextConstant.STRING_TRUE)){
					NegoInicio negoInicio = new NegoInicio();
					negoInicio.recogerContenido(patron,pagina,sesion,session);
					hsResultados = negoInicio.getHsResultados();
					if(isMobile==true)strPaginaDestino="jsp/index_mobile.jsp";
					else strPaginaDestino="jsp/index.jsp";
				}else{
					NegoAnuncio negoAnuncio = new NegoAnuncio();
					if(UtilidadesNumericos.isNumberInteger(idAnuncio)){
						transaction = session.beginTransaction();
						Anuncio anuncio = negoAnuncio.obtenerAnuncioActivoPorId(new Integer(idAnuncio),session);
						if(anuncio!=null){
							negoAnuncio.aumentarVisitasAnuncio(anuncio,session,req.getRemoteAddr());//Aumentamos las visitas al anuncio por IP
							hsResultados = negoAnuncio.getHsResultados();
							if(isMobile)strPaginaDestino="jsp/fichaAnuncio_mobile.jsp";
							else strPaginaDestino="jsp/fichaAnuncio.jsp";
						}else strPaginaDestino="jsp/anuncio_no_encontrado.jsp";
						transaction.commit();
						session.close();
					}
					else strPaginaDestino="jsp/anuncio_no_encontrado.jsp";
				}
			}
		}catch(ErrorVendenet e){
			if(transaction!=null)transaction.rollback();
			logger.error(e);
			strPaginaDestino=strErrPagina;
		}
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new VerAnuncio(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
