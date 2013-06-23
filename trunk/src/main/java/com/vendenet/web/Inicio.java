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

import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoInicio;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.UtilidadesTexto;
import com.vendenet.utilidades.UtilidadesVendenet;
import com.vendenet.utilidades.UtilidadesWeb;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class Inicio implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(Inicio.class);
	private Inicio inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";	
	private ErrorVendenet err;
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public Inicio(){
	}
	
	public Inicio(HttpServletRequest req) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			HttpSession sesion = req.getSession();
			transaction = session.beginTransaction();
			if(sesion.getAttribute(TextConstant.KEY_VISITA_REGISTRADA)==null){
				UtilidadesWeb.aumentarVisitasWeb(session, req.getRemoteAddr(),req.getHeader(TextConstant.USER_AGENT));
				sesion.setAttribute(TextConstant.KEY_VISITA_REGISTRADA,true);
			}
			boolean isMobile=UtilidadesTexto.IsMobile(req.getHeader(TextConstant.USER_AGENT));
			isMobile=true;
			String ipaddress = req.getRemoteAddr();
//			ipaddress="88.7.255.204";
			StringBuffer sburl = new StringBuffer(ConstantesVendenet.URL_LOCALIZADOR_IP);
			sburl.append(ipaddress);
			//TEMPORAL - VOY REGISTRANDO CIUDADES DIFERENTES PARA PODER DETERMINAR LA PROVINCIA
//			if(sesion.getAttribute(TextConstant.KEY_CITYREGISTRED)==null){
				UtilidadesVendenet.guardarCiudad(UtilidadesVendenet.obtenerCiudadYComunidadCliente(sburl),session);
				sesion.setAttribute(TextConstant.KEY_CITYREGISTRED,true);
//			}
			//FIN TEMPORAL - VOY REGISTRANDO CIUDADES DIFERENTES PARA PODER DETERMINAR LA PROVINCIA
			NegoInicio negoInicio = new NegoInicio();
			String patron=req.getParameter("nombre");
			String pagina=req.getParameter("pagina");
			String subAccion=req.getParameter("subAccion");
			String subAccionFormu=req.getParameter("subAccionFormu");
			if(patron!=null && !patron.equals(TextConstant.BLANK)){
				negoInicio.recogerContenido(patron,pagina,sesion,session);
			}else{
				if((subAccionFormu==null)&&(subAccion!=null)&&(subAccion.equals(TextConstant.INICIO))){
					sesion.removeAttribute(TextConstant.KEY_PROVINCIA);
					sesion.removeAttribute(TextConstant.KEY_CATEGORIA);
					sesion.removeAttribute(TextConstant.KEY_CRITERIO_ORDEN);
					sesion.removeAttribute(TextConstant.KEY_TIPO_ANUNCIO);
					sesion.removeAttribute(TextConstant.KEY_TIPO_VENDEDOR);
					sesion.removeAttribute(TextConstant.KEY_PAGINA_ACTUAL);
					negoInicio.recogerContenido(TextConstant.BLANK,pagina,sesion,session);
				}else{
					if(patron==null)patron="";
					negoInicio.recogerContenido(patron,pagina,sesion,session);
				}
			}
			boolean isIphone=false;
			if(isMobile){
				strPaginaDestino="jsp/index_mobile.jsp";
				isIphone=UtilidadesTexto.IsIPhone(req.getHeader(TextConstant.USER_AGENT));
				sesion.setAttribute(TextConstant.KEY_IPHONE,isIphone);
			}else strPaginaDestino="jsp/index.jsp";
			transaction.commit();
			session.close();
			hsResultados = negoInicio.getHsResultados();
		}catch(ErrorVendenet e){
			if(transaction!=null)transaction.rollback();
			System.err.println(e);
			logger.error(e);
			strPaginaDestino=strErrPagina;
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new Inicio(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
