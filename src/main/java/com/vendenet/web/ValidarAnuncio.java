/*
 * Creado el 06-jul-06
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package com.vendenet.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoValidarAnuncio;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.UtilidadesTexto;
import com.vendenet.utilidades.constantes.CarEspecialesConstant;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.NumericConstant;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class ValidarAnuncio implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(ValidarAnuncio.class);
	private ValidarAnuncio inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public ValidarAnuncio(){
	}
	
	public ValidarAnuncio(HttpServletRequest req) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			NegoValidarAnuncio negoValidarAnuncio = new NegoValidarAnuncio();
			String provincia_selected=req.getParameter("provincia_selected");
			String categoria_selected=req.getParameter("categoria_selected");
			String tipo_vendedor_selected=req.getParameter("tipo_vendedor_selected");
			String tipo_anuncio_selected=req.getParameter("tipo_anuncio_selected");
			List lstFotos=new ArrayList();
			for(int i=0;i<NumericConstant.FOTO_POR_ANUNCIO;i++){
				if((req.getParameter("foto"+(i+1)+"_selected")!=null)&&!req.getParameter("foto"+(i+1)+"_selected").equals(TextConstant.BLANK)&&!req.getParameter("foto"+(i+1)+"_selected").equals(TextConstant.PUNTO)){
					lstFotos.add(req.getParameter("foto"+(i+1)+"_selected"));
				}
			}
			String nombre=UtilidadesTexto.buscarCaracteresFormato(req.getParameter("formulario_nombre"));
			String email=req.getParameter("formulario_email");
			String telefono=req.getParameter("formulario_tfno");
			String titulo=UtilidadesTexto.buscarCaracteresFormato(req.getParameter("formulario_titulo"));
			String cuerpo=UtilidadesTexto.buscarCaracteresFormato(req.getParameter("formulario_cuerpo"));
			String precio=req.getParameter("formulario_precio");
			/*********************************** SIMULADOR DE ALTA DE ANUNCIO **********************************
			provincia_selected="48";
			categoria_selected="11";
			tipo_vendedor_selected="1";
			tipo_anuncio_selected="1";
			nombre="Jesus";
			email="jgamann@gmail.com";
			telefono="660278002";
			titulo="Titulo del anuncio";
			cuerpo="Cuerpo del anuncio";
			precio="1";
			/***************************************************************************************************/
			if(negoValidarAnuncio.validarCampos()){
				transaction = session.beginTransaction();
				Anuncio anuncio = negoValidarAnuncio.construirAnuncio(session,nombre,email,telefono,titulo,cuerpo,precio,provincia_selected,categoria_selected,tipo_vendedor_selected,tipo_anuncio_selected,lstFotos);
				HttpSession sesion = req.getSession();
				sesion.setAttribute(TextConstant.KEY_ANUNCIO, anuncio);
				strPaginaDestino="jsp/anuncio_construido.jsp";
			}else strPaginaDestino=strErrPagina;
			transaction.commit();
			session.close();
			
//			//**************************** hasta que este hecha la previsualizacion de anuncio ***********************
//			transaction = session.beginTransaction();
//			Anuncio anuncio = negoValidarAnuncio.obtenerUnAnuncioCualquiera(session);
//			HttpSession sesion = req.getSession();
//			sesion.setAttribute(TextConstant.KEY_ANUNCIO, anuncio);
//			transaction.commit();
//			strPaginaDestino="jsp/anuncio_construido.jsp";
//			//**************************** hasta que este hecha la previsualizacion de anuncio ***********************
			hsResultados = negoValidarAnuncio.getHsResultados();
		}catch(ErrorVendenet e){
			if(transaction!=null)transaction.rollback();
			logger.error(e);
			strPaginaDestino=strErrPagina;
		}		
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new ValidarAnuncio(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
}
