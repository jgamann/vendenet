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
public class NegoConfirmarModificacionAnuncio {
	private Logger logger = Logger.getLogger(NegoConfirmarModificacionAnuncio.class);
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
			logger.error("Error en NegoValidarAnuncio - obtenerUnAnuncioCualquiera"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
		
	}
	
}
