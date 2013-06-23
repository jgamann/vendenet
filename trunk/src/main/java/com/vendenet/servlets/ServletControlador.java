package com.vendenet.servlets;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.vendenet.utilidades.ProcesoControlNegocio;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.PropiedadesSistema;
import com.vendenet.utilidades.UtilidadesNumericos;
import com.vendenet.utilidades.constantes.ConstantesVendenet;

/**
 * @version 	1.0
 * @author
 */
public class ServletControlador extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(ServletControlador.class);

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
			procesarPeticion(req,resp);
	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
			procesarPeticion(req,resp);
	}
	
	
	public void procesarPeticion(HttpServletRequest req, HttpServletResponse resp)
	{
		ProcesoControlNegocio procesoControlNegocio = null;
		PropiedadesSistema propiedadesSistema=null;
		ProcesoWebPadre procesoWeb=null;
		String strNomAccion = null;
		String strAccion = null;
		String claseWeb=null;
		Hashtable hsResultados=null;
		String strPaginaDestino;
		try
		{
			propiedadesSistema=new PropiedadesSistema(getServletContext().getRealPath(""));
			procesoControlNegocio=new ProcesoControlNegocio(getServletContext().getRealPath(""));
			//ConstantesVendenet.session = HibernateUtil.getSessionFactory().openSession();// getCurrentSession();
			strNomAccion = propiedadesSistema.getPropiedad("accion");
			strAccion = req.getParameter(strNomAccion);
			if(strAccion==null)req.getRequestDispatcher(ConstantesVendenet.paginaInicio).forward(req, resp);
			else{
				claseWeb=procesoControlNegocio.getPropiedad(strAccion);
				if (claseWeb==null){
					if(UtilidadesNumericos.isNumberInteger(strAccion)){
						Object objeto=Class.forName("com.vendenet.web.VerAnuncioDirecto").newInstance();
						procesoWeb=(ProcesoWebPadre)objeto;
						procesoWeb=((ProcesoWebPadre)Class.forName("com.vendenet.web.VerAnuncioDirecto").newInstance()).getInstance(req);
						hsResultados = procesoWeb.getResultados();
						strPaginaDestino = procesoWeb.getPagina();
						req.setAttribute("resultados", hsResultados);
						req.getRequestDispatcher(strPaginaDestino).forward(req, resp);
					}else req.getRequestDispatcher(ConstantesVendenet.paginaInicio).forward(req, resp);
				}else{
					Object objeto=Class.forName(claseWeb).newInstance();
					procesoWeb=(ProcesoWebPadre)objeto;
					procesoWeb=((ProcesoWebPadre)Class.forName(claseWeb).newInstance()).getInstance(req);
					hsResultados = procesoWeb.getResultados();
					strPaginaDestino = procesoWeb.getPagina();
					req.setAttribute("resultados", hsResultados);
					req.getRequestDispatcher(strPaginaDestino).forward(req, resp);
				}
			}
		}
		//implementar cualquier excepci�n posible.
		catch (Throwable t)
		{
			t.printStackTrace();
			logger.error("t.toString()=" + t.toString());
			logger.error("t.getMessage()=" + t.getMessage());
			logger.error("t.getClass()=" + t.getClass());
		}
		
	}

}
