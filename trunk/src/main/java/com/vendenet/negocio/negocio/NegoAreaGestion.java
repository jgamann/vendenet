/*
 * Creado el 08-oct-09
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package com.vendenet.negocio.negocio;

import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class NegoAreaGestion {
	private Logger logger = Logger.getLogger(NegoGestorArchivos.class);
	private Hashtable hsResultados = new Hashtable();
		

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

	
	public void recogerContenido(String idAnuncio, String accion,Session session) throws ErrorVendenet {
		try{
			Criteria crit = session.createCriteria(Anuncio.class);
			crit.add(Restrictions.eq("id",new Integer(idAnuncio)));
			crit.add(Restrictions.eq("publicado",true));
			List listaResultados=crit.list();
			Anuncio loadedAnuncio = null;
			if((listaResultados!=null)&&(listaResultados.size()>0)){
				loadedAnuncio = (Anuncio)crit.list().get(0);
				hsResultados.put(TextConstant.KEY_ANUNCIO,loadedAnuncio);
				hsResultados.put(TextConstant.KEY_ACCION,(accion!=null &&accion.equals(TextConstant.ACTION_BORRAR_ANUNCIO))?new Boolean(true):new Boolean(false));
			}
		}
		catch(Exception eError)
		{
			logger.error("Error en NegoAreaGestion - recogerContenido"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
}
