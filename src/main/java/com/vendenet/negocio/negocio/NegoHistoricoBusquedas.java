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

import com.vendenet.negocio.entidad.Busqueda;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.UtilidadesFecha;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class NegoHistoricoBusquedas {
	private Logger logger = Logger.getLogger(NegoHistoricoBusquedas.class);
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
			Criteria crit = session.createCriteria(Busqueda.class);
			crit.addOrder(Order.desc("fechaBusqueda"));
			if((fechainicio!=null)&&(!fechainicio.equals(TextConstant.BLANK))){
				hsResultados.put(TextConstant.KEY_FECHA_DESDE, fechainicio);
				crit.add(Restrictions.ge("fechaBusqueda",UtilidadesFecha.crearFechaDesdeDDMMYYYY(fechainicio)));
			}
			if((fechafin!=null)&&(!fechafin.equals(TextConstant.BLANK))){
				hsResultados.put(TextConstant.KEY_FECHA_HASTA, fechafin);
				crit.add(Restrictions.le("fechaBusqueda",UtilidadesFecha.crearFechaDesdeDDMMYYYY(fechafin)));
			}
			List<Busqueda> lstHistorico = crit.list();
			
			hsResultados.put(TextConstant.KEY_HISTORICO_BUSQUEDAS, lstHistorico);
		}catch(Exception eError){
			logger.error("Error recogerHistorico en NegoHistoricoBusquedas:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
}
