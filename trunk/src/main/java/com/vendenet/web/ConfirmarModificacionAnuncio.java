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
import com.vendenet.negocio.entidad.Cliente;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.negocio.negocio.NegoModificarAnuncio;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.ProcesoWebPadre;
import com.vendenet.utilidades.UtilidadesAnuncio;
import com.vendenet.utilidades.UtilidadesMail;
import com.vendenet.utilidades.UtilidadesNumericos;
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
public class ConfirmarModificacionAnuncio implements ProcesoWebPadre {
	private Logger logger = Logger.getLogger(ConfirmarModificacionAnuncio.class);
	private ConfirmarModificacionAnuncio inicio=null;
	private Hashtable hsResultados = new Hashtable();
	private String strPaginaDestino="";
	private final String strErrPagina = ConstantesVendenet.PAG_ERRORES;
			
	public ConfirmarModificacionAnuncio(){
	}
	
	public ConfirmarModificacionAnuncio(HttpServletRequest req) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			HttpSession sesion = req.getSession();
			NegoModificarAnuncio negoModificarAnuncio = new NegoModificarAnuncio();
			if(sesion.getAttribute(TextConstant.KEY_CLIENTE)!=null && sesion.getAttribute(TextConstant.KEY_CLIENTE) instanceof Cliente){
				Cliente cli = (Cliente)sesion.getAttribute(TextConstant.KEY_CLIENTE);
				String idAnuncio=req.getParameter("idAnuncio");
				if(idAnuncio!= null && UtilidadesNumericos.isNumberInteger(idAnuncio)){
					transaction = session.beginTransaction();
					Anuncio anuncio = negoModificarAnuncio.obtenerAnuncioPorId(new Integer(idAnuncio),session);
					if(anuncio!=null && anuncio.getCliente()!= null ){
						if(anuncio.getCliente().equals(cli)){//El cliente de la sesion coincide con el cliente del anuncio. Luz verde para modificar el anuncio
							String provincia_selected=req.getParameter("provincia_selected");
							String categoria_selected=req.getParameter("categoria_selected");
							String tipo_vendedor_selected=req.getParameter("tipo_vendedor_selected");
							String tipo_anuncio_selected=req.getParameter("tipo_anuncio_selected");
							List lstFotos=new ArrayList();
							for(int i=0;i<NumericConstant.FOTO_POR_ANUNCIO;i++){
								if(!req.getParameter("foto"+(i+1)+"_selected").equals(TextConstant.BLANK)&&!req.getParameter("foto"+(i+1)+"_selected").equals(TextConstant.PUNTO)){
									lstFotos.add(req.getParameter("foto"+(i+1)+"_selected"));
								}
							}
							String nombre=UtilidadesTexto.buscarCaracteresFormato(req.getParameter("formulario_nombre"));
							String email=req.getParameter("formulario_email");
							String telefono=req.getParameter("formulario_tfno");
							String titulo=UtilidadesTexto.buscarCaracteresFormato(req.getParameter("formulario_titulo"));
							String cuerpo=UtilidadesTexto.buscarCaracteresFormato(req.getParameter("formulario_cuerpo"));
							String precio=req.getParameter("formulario_precio");
							anuncio=negoModificarAnuncio.modificarAnuncio(session,anuncio,nombre,email,telefono,titulo,cuerpo,precio,provincia_selected,categoria_selected,tipo_vendedor_selected,tipo_anuncio_selected,lstFotos);
							//********************************Comprobar palabrotas****************************************
							if(UtilidadesAnuncio.tienePalabrotas(session,anuncio)){
								//Registrar email de devolucion y poner el anuncio como revisado con la fecha de caducidad breve (como si se hubiese devuelto al cliente)
//								negoModificarAnuncio.devolverAnuncioPalabrota(session,anuncio);
								UtilidadesMail.devolverAnuncioPalabrota(session,anuncio);
							}else{
								UtilidadesMail.emailAnuncioModificado(session, anuncio);
							}
							//********************************************************************************************
						}else strPaginaDestino=strErrPagina;
					}else strPaginaDestino=strErrPagina;
				}else strPaginaDestino=strErrPagina;
			}else strPaginaDestino=strErrPagina;
			transaction.commit();
			session.close();
			strPaginaDestino="jsp/anuncio_en_revision.jsp";
			hsResultados = negoModificarAnuncio.getHsResultados();
		}catch(ErrorVendenet e){
			if(transaction!=null)transaction.rollback();
			logger.error(e);
			strPaginaDestino=strErrPagina;
		}		
	}
	
	public ProcesoWebPadre getInstance(HttpServletRequest req)
	{
		inicio = new ConfirmarModificacionAnuncio(req);
		return inicio;
	}

	public Hashtable getResultados() {
		return hsResultados;
	}
	
	public String getPagina() {
		return strPaginaDestino;
	}
}
