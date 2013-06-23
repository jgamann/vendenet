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
import com.vendenet.negocio.entidad.Cliente;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoModificarAnuncio;
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
public class ModificarAnuncio implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(ModificarAnuncio.class);
	private ModificarAnuncio inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";	
	private ErrorVendenet err;
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public ModificarAnuncio(){
	}
	
	public ModificarAnuncio(HttpServletRequest req) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		HttpSession sesion = req.getSession();
		try{
			NegoModificarAnuncio negoModificarAnuncio = new NegoModificarAnuncio();
			String idAnuncio=req.getParameter("idAnuncio");
			transaction = session.beginTransaction();
			String pass=req.getParameter("pass");
			if(pass!=null){
				Cliente cliente = null;
				cliente=negoModificarAnuncio.validarClave(idAnuncio, pass, session);
				if(cliente!=null){
					sesion.setAttribute(TextConstant.KEY_CLIENTE, cliente);
				}
			}
			if(sesion.getAttribute(TextConstant.KEY_CLIENTE)!=null){
				if(idAnuncio!=null && UtilidadesNumericos.isNumberInteger(idAnuncio)){
					Anuncio anuncio = negoModificarAnuncio.obtenerAnuncioPorId(new Integer(idAnuncio),session);
					if(anuncio!=null && anuncio.getCliente()!= null ){
						if(sesion.getAttribute(TextConstant.KEY_CLIENTE) instanceof Cliente){
							Cliente cli = (Cliente)sesion.getAttribute(TextConstant.KEY_CLIENTE);
							if(anuncio.getCliente().equals(cli)){//El cliente de la sesion coincide con el cliente del anuncio. Luz verde para modificar el anuncio
								negoModificarAnuncio.recogerContenido(session);
								hsResultados = negoModificarAnuncio.getHsResultados();
								strPaginaDestino="jsp/modificacion_anuncio.jsp";
							}else strPaginaDestino=strErrPagina;
						}else strPaginaDestino=strErrPagina;	
					}else strPaginaDestino=strErrPagina;
				}else strPaginaDestino=strErrPagina;
			}else strPaginaDestino=strErrPagina;
			transaction.commit();
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
		inicio = new ModificarAnuncio(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
	
}
