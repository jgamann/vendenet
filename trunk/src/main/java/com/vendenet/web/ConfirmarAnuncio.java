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
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.UtilidadesAnuncio;
import com.vendenet.utilidades.UtilidadesMail;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class ConfirmarAnuncio implements ProcesoWebPadre {
	Logger  logger = Logger.getLogger(ConfirmarAnuncio.class); 
	private ConfirmarAnuncio inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public ConfirmarAnuncio(){
	}
	
	public ConfirmarAnuncio(HttpServletRequest req) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			HttpSession sesion = req.getSession();
			Anuncio anuncio = (Anuncio)sesion.getAttribute(TextConstant.KEY_ANUNCIO);
			//sesion.removeAttribute(TextConstant.KEY_ANUNCIO);
			NegoAnuncio negoAnuncio = new NegoAnuncio();
			String password=req.getParameter("formulario_password_1");
			transaction = session.beginTransaction();
			negoAnuncio.grabarAnuncio(session,anuncio,password);
			UtilidadesMail.emailAnuncioNuevo(session,anuncio);
			//********************************Comprobar palabrotas****************************************
			if(UtilidadesAnuncio.tienePalabrotas(session,anuncio)){
				//Registrar email de devolucion y poner el anuncio como revisado con la fecha de caducidad breve (como si se hubiese devuelto al cliente)
				negoAnuncio.devolverAnuncioPalabrota(session,anuncio);
			}
			//********************************************************************************************
			strPaginaDestino="jsp/anuncio_en_revision.jsp";
			/*if(negoConfirmarAnuncio.validarCampos()){
				transaction = session.beginTransaction();
				negoConfirmarAnuncio.construirAnuncio(session,nombre,email,telefono,titulo,cuerpo,precio,provincia_selected,categoria_selected,tipo_vendedor_selected,tipo_anuncio_selected,lstFotos);
				strPaginaDestino="jsp/anuncio_construido.jsp";
			}*/
			
			transaction.commit();
			session.close();
			hsResultados = negoAnuncio.getHsResultados();
		}catch(ErrorVendenet e){
			if(transaction!=null)transaction.rollback();
			logger.error(e);
			strPaginaDestino=strErrPagina;
		}
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new ConfirmarAnuncio(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
