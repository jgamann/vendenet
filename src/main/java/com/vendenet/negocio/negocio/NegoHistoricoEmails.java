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
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.EnvioRealizado;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.UtilidadesFecha;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class NegoHistoricoEmails {
	private Logger logger = Logger.getLogger(NegoHistoricoEmails.class);
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
	public void recogerHistorico(Session session, String fechainicio, String fechafin) throws ErrorVendenet {
		try{
			Criteria crit = session.createCriteria(EnvioRealizado.class);
			crit.addOrder(Order.desc("fechaAlta"));
			if((fechainicio!=null)&&(!fechainicio.equals(TextConstant.BLANK))){
				hsResultados.put(TextConstant.KEY_FECHA_DESDE, fechainicio);
				crit.add(Restrictions.ge("fechaAlta",UtilidadesFecha.crearFechaDesdeDDMMYYYY(fechainicio)));
			}
			if((fechafin!=null)&&(!fechafin.equals(TextConstant.BLANK))){
				hsResultados.put(TextConstant.KEY_FECHA_HASTA, fechafin);
				crit.add(Restrictions.le("fechaAlta",UtilidadesFecha.crearFechaDesdeDDMMYYYY(fechafin)));
			}
			List<EnvioRealizado> lstEnviosRealizados = crit.list();
			
			hsResultados.put(TextConstant.KEY_ENVIOS_REALIZADOS, lstEnviosRealizados);
		}catch(Exception eError){
			logger.error("Error recogerHistorico en NegoHistoricoEmails:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
}
