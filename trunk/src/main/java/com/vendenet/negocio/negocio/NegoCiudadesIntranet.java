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
import org.hibernate.criterion.Order;

import com.vendenet.negocio.entidad.CiudadTemp;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class NegoCiudadesIntranet {
	private Logger logger = Logger.getLogger(NegoCiudadesIntranet.class);
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
	
	public void recogerCiudades(Session session) throws ErrorVendenet {
		try{
			Criteria crit = session.createCriteria(CiudadTemp.class);
			crit.addOrder(Order.asc("comunidad"));
			List<CiudadTemp> lstCiudadTemp = crit.list();
			hsResultados.put(TextConstant.KEY_CIUDADES_TEMP, lstCiudadTemp);
		}catch(Exception eError){
			logger.error("Error recogerCiudades en NegoCiudadesIntranet"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
}
