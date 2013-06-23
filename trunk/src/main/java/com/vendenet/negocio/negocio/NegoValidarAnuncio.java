/*
 * Creado el 08-oct-09
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package com.vendenet.negocio.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

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
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class NegoValidarAnuncio {
	private Logger logger = Logger.getLogger(NegoValidarAnuncio.class);
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

	public boolean validarCampos() {
		// TODO Auto-generated method stub
		return true;
	}

	public Anuncio construirAnuncio(Session session, String nombre, String email,
			String telefono, String titulo, String cuerpo,
			String precio, String provinciaSelected, String categoriaSelected,
			String tipoVendedorSelected, String tipoAnuncioSelected,
			List lstFotos) throws ErrorVendenet {
		try{
			Anuncio anuncio = new Anuncio();
			CategoriaAnuncio loadedCategoria = (CategoriaAnuncio)session.load(CategoriaAnuncio.class, new Integer(categoriaSelected));
			anuncio.setCategoria(loadedCategoria);
			anuncio.setCuerpo(cuerpo);
			anuncio.setDestacado(false);
			anuncio.setFechaAlta(new Date());
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
			Cliente cliente = new Cliente();
			cliente.setName(nombre);
			cliente.setEmail(email);
			cliente.setFechaAlta(new Date());
			cliente.setTelefono(telefono);
			anuncio.setCliente(cliente);
			anuncio.setPublicado(false);
			anuncio.setRevisado(false);
			hsResultados.put(TextConstant.KEY_ANUNCIO, anuncio);
			return anuncio;
		}catch(Exception eError){
			logger.error("Error en NegoValidarAnuncio:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
		
	}

	public Anuncio obtenerUnAnuncioCualquiera(Session session) throws ErrorVendenet {
		try{
			Anuncio anuncio = new Anuncio();
			Anuncio loadedAnuncio = (Anuncio)session.load(Anuncio.class, new Integer(1));
			Anuncio otro = new Anuncio();
			otro.setAdjuntos(loadedAnuncio.getAdjuntos());
			otro.setCategoria(loadedAnuncio.getCategoria());
			otro.setCliente(loadedAnuncio.getCliente());
			otro.setCuerpo(loadedAnuncio.getCuerpo());
			
			otro.setFechaAlta(loadedAnuncio.getFechaAlta());
			otro.setFechaBorrado(loadedAnuncio.getFechaBorrado());
			otro.setFechaCaducidad(loadedAnuncio.getFechaCaducidad());
			otro.setId(loadedAnuncio.getId());
			otro.setName(loadedAnuncio.getName());
			otro.setPrecio(loadedAnuncio.getPrecio());
			otro.setProvincia(loadedAnuncio.getProvincia());
			otro.setTipoAnuncio(loadedAnuncio.getTipoAnuncio());
			otro.setTipoVendedor(loadedAnuncio.getTipoVendedor());
			otro.setVisitas(loadedAnuncio.getVisitas());
			otro.setPublicado(loadedAnuncio.isPublicado());
			hsResultados.put(TextConstant.KEY_ANUNCIO, otro);
			return otro;
		}catch(Exception eError){
			logger.error("Error en NegoValidarAnuncio:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
		
	}
	
}
