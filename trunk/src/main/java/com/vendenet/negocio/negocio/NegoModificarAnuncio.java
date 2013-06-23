/*
 * Creado el 08-oct-09
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaciï¿½n de cï¿½digo&gt;Cï¿½digo y comentarios
 */
package com.vendenet.negocio.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.Adjunto;
import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.CategoriaAnuncio;
import com.vendenet.negocio.entidad.Cliente;
import com.vendenet.negocio.entidad.Provincia;
import com.vendenet.negocio.entidad.TipoAnuncio;
import com.vendenet.negocio.entidad.TipoVendedor;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.UtilidadesAdjunto;
import com.vendenet.utilidades.UtilidadesFecha;
import com.vendenet.utilidades.UtilidadesNumericos;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaciï¿½n de cï¿½digo&gt;Cï¿½digo y comentarios
 */
public class NegoModificarAnuncio {
	private Logger logger = Logger.getLogger(NegoGestorArchivos.class);
	private Hashtable hsResultados = new Hashtable();
	
	public void iniciarAplicacion() throws ErrorVendenet {
				
	}

	/**
	 * @return
	 */
	public Hashtable getHsResultados() {
		return hsResultados;
	}

	/**
	 * @param hashtable
	 */
	public void setHsResultados(Hashtable hashtable) {
		hsResultados = hashtable;
	}	

	

	public Anuncio obtenerAnuncioPorId(Integer idAnuncio,Session session) throws ErrorVendenet {
		try{
			Criteria crit = session.createCriteria(Anuncio.class);
			crit.add(Restrictions.eq("id",idAnuncio));
			List listaResultados=crit.list();
			Anuncio loadedAnuncio = null;
			if((listaResultados!=null)&&(listaResultados.size()>0)){
				loadedAnuncio = (Anuncio)crit.list().get(0);
				hsResultados.put(TextConstant.KEY_ANUNCIO,loadedAnuncio);
			}
			return loadedAnuncio;
		}
		catch(Exception eError)
		{
			logger.error("Error en NegoModificarAnuncio:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
		
	}
	
	public void recogerContenido(Session session) throws ErrorVendenet {
		try{
			List<Provincia> resultProvincias = (List<Provincia>)session.createQuery("from Provincia order by PROVINCIA_NAME_LONG").list();
	        List<CategoriaAnuncio> resultCategorias = (List<CategoriaAnuncio>)session.createQuery("from CategoriaAnuncio order by ORDEN").list();
	        List<TipoAnuncio> resultTiposAnuncio = (List<TipoAnuncio>)session.createQuery("from TipoAnuncio order by TIPOANUNCIO_NAME").list();
	        List<TipoVendedor> resultTiposVendedor = (List<TipoVendedor>)session.createQuery("from TipoVendedor order by TIPOVENDEDOR_NAME").list();
			hsResultados.put(TextConstant.KEY_PROVINCIAS,resultProvincias);
			hsResultados.put(TextConstant.KEY_CATEGORIAS,resultCategorias);
			hsResultados.put(TextConstant.KEY_TIPOS_VENDEDOR,resultTiposVendedor);
			hsResultados.put(TextConstant.KEY_TIPOS_ANUNCIO,resultTiposAnuncio);
		}			
		catch(Exception eError)
		{
			logger.error("Error en NegoModificarAnuncio: recogerContenido"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}

		
	public Anuncio modificarAnuncio(Session session, Anuncio anuncio, String nombre, String email,
			String telefono, String titulo, String cuerpo,
			String precio, String provinciaSelected, String categoriaSelected,
			String tipoVendedorSelected, String tipoAnuncioSelected,
			List lstFotos) throws ErrorVendenet {
		try{
			CategoriaAnuncio loadedCategoria = (CategoriaAnuncio)session.load(CategoriaAnuncio.class, new Integer(categoriaSelected));
			anuncio.setCategoria(loadedCategoria);
			anuncio.setCuerpo(cuerpo);
			anuncio.setDestacado(false);
			anuncio.setFechaBorrado(null);
			anuncio.setFechaCaducidad(UtilidadesFecha.devolverFechaCaducidad(new Date()));
			anuncio.setName(titulo);
			anuncio.setPrecio(new Double(precio).doubleValue());
			Provincia loadedProvincia = (Provincia)session.load(Provincia.class, new Integer(provinciaSelected));
			anuncio.setProvincia(loadedProvincia);
			TipoAnuncio loadedTipoAnuncio = (TipoAnuncio)session.load(TipoAnuncio.class, new Integer(tipoAnuncioSelected));
			anuncio.setTipoAnuncio(loadedTipoAnuncio);
			TipoVendedor loadedTipoVendedor = (TipoVendedor)session.load(TipoVendedor.class, new Integer(tipoVendedorSelected));
			anuncio.setTipoVendedor(loadedTipoVendedor);
			anuncio.setVisitas(new ArrayList());
			Iterator itFotos = lstFotos.iterator();
			List lstAdjuntos = new ArrayList<Adjunto>();
			while(itFotos.hasNext()){
				String urlFotoPeque = (String)itFotos.next();
				Adjunto adjuntoTemp = (Adjunto)session.load(Adjunto.class, new Integer(UtilidadesAdjunto.obtenerIdFromPequePath(urlFotoPeque)));
				lstAdjuntos.add(adjuntoTemp);
			}
			anuncio.setAdjuntos(lstAdjuntos);
			Cliente cliente = anuncio.getCliente();
			cliente.setName(nombre);
			cliente.setEmail(email);
			cliente.setFechaAlta(new Date());
			cliente.setTelefono(telefono);
			anuncio.setCliente(cliente);
			anuncio.setPublicado(false);
			anuncio.setRevisado(false);
			session.saveOrUpdate(anuncio);
			hsResultados.put(TextConstant.KEY_ANUNCIO, anuncio);
			return anuncio;
		}catch(Exception eError){
			logger.error("Error en NegoModificarAnuncio: modificarAnuncio"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
		
	}
	
	public Cliente validarClave(String idAnuncio, String pass, Session session) throws ErrorVendenet {
		Cliente cliente=null;
		if((idAnuncio!=null)&&(UtilidadesNumericos.isNumberInteger(idAnuncio))){
			Anuncio loadedAnuncio = (Anuncio)session.get(Anuncio.class, new Integer(idAnuncio));
			//Antes de registrar otro envio de email de recordatorio de contraseña para ese anuncio sin enviar
			if(loadedAnuncio!=null && loadedAnuncio.getCliente() != null && loadedAnuncio.getCliente().getPass() != null){
					if(loadedAnuncio.getCliente().getPass().equals(pass)){
						cliente=loadedAnuncio.getCliente();
					}
			}
		}
		return cliente;
	}
	
}
