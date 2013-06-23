/*
 * Creado el 08-oct-09
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package com.vendenet.negocio.negocio;

import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.vendenet.negocio.entidad.CategoriaAnuncio;
import com.vendenet.negocio.entidad.Provincia;
import com.vendenet.negocio.entidad.TipoAnuncio;
import com.vendenet.negocio.entidad.TipoVendedor;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class NegoPonerAnuncio {
	private Logger logger = Logger.getLogger(NegoPonerAnuncio.class);
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
//			EnvioPendiente envio = (EnvioPendiente)session.load(EnvioPendiente.class, new Integer(49));
//			UtilidadesMail.enviarEmailDesdeGmail(envio);
//			Anuncio anuncio = (Anuncio)session.load(Anuncio.class, new Integer(44));
//			UtilidadesAnuncio.tienePalabrotas(session,anuncio);
		}
		catch(Exception eError)
		{
			logger.error("Error en NegoPonerAnuncio:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
}
