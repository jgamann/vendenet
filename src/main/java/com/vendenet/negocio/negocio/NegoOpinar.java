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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.CategoriaAnuncio;
import com.vendenet.negocio.entidad.Critica;
import com.vendenet.negocio.entidad.Provincia;
import com.vendenet.negocio.entidad.TipoAnuncio;
import com.vendenet.negocio.entidad.TipoVendedor;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class NegoOpinar {
	private Logger logger = Logger.getLogger(NegoOpinar.class);
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

	public void recogerContenido(String idAnuncio, Session session) throws ErrorVendenet {
		try{
			if(session!=null){
				Criteria crit = session.createCriteria(Critica.class);
				Criterion criteriosporanuncio = Restrictions.sqlRestriction("{alias}.EMAIL_CLIENTE = (select EMAIL from cliente where CLIENTE_ID = (select CLIENTE_ID from anuncio where ANUNCIO_ID = "+idAnuncio+" ))");
	    		crit.add(criteriosporanuncio);
	    		crit.addOrder(Order.asc("fechaAlta"));
	    		List<Critica> result = crit.list();
	    		hsResultados.put(TextConstant.KEY_LISTA_CRITICAS,result);
			}
			hsResultados.put(TextConstant.KEY_ANUNCIO,idAnuncio);
		}
		catch(Exception eError)
		{
			logger.error("Error en NegoOpinar - recogerContenido:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
}
