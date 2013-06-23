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
import com.vendenet.negocio.negocio.NegoEnviarConsulta;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.constantes.ConstantesVendenet;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class EnviarConsulta implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(EnviarConsulta.class);
	private EnviarConsulta inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";	
	private ErrorVendenet err;
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public EnviarConsulta(){
	}
	
	public EnviarConsulta(HttpServletRequest req) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		NegoEnviarConsulta negoEnviarConsulta = new NegoEnviarConsulta();
		try{
			String idAnuncio=req.getParameter("idAnuncio");
			String nombre=req.getParameter("txtNombre");
			String email=req.getParameter("txtEmail");
			String tfno=req.getParameter("txtTfno");
			String pregunta= req.getParameter("txtPregunta");
			if(pregunta!=null && pregunta.length()>300)pregunta=pregunta.substring(0,300);
			if(negoEnviarConsulta.validarCampos()){
				transaction = session.beginTransaction();
				negoEnviarConsulta.registrarEmail(session,nombre,email,tfno,pregunta,idAnuncio);
				HttpSession sesion = req.getSession();
				strPaginaDestino="jsp/consulta_enviada.jsp";
			}else strPaginaDestino=strErrPagina;
			transaction.commit();
			session.close();
			hsResultados = negoEnviarConsulta.getHsResultados();
		}catch(ErrorVendenet e){
			if(transaction!=null)transaction.rollback();
			logger.error(e);
			hsResultados = negoEnviarConsulta.getHsResultados();
			strPaginaDestino="jsp/consulta_enviada.jsp";
		}
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new EnviarConsulta(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
