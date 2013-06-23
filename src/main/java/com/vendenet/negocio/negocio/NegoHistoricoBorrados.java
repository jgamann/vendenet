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

import com.vendenet.negocio.entidad.HistoricoBajas;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.UtilidadesFecha;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class NegoHistoricoBorrados {
	private Logger logger = Logger.getLogger(NegoHistoricoBorrados.class);
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
			Criteria crit = session.createCriteria(HistoricoBajas.class);
			crit.addOrder(Order.desc("fechaBorrado"));
			if((fechainicio!=null)&&(!fechainicio.equals(TextConstant.BLANK))){
				hsResultados.put(TextConstant.KEY_FECHA_DESDE, fechainicio);
				crit.add(Restrictions.ge("fechaBorrado",UtilidadesFecha.crearFechaDesdeDDMMYYYY(fechainicio)));
			}
			if((fechafin!=null)&&(!fechafin.equals(TextConstant.BLANK))){
				hsResultados.put(TextConstant.KEY_FECHA_HASTA, fechafin);
				crit.add(Restrictions.le("fechaBorrado",UtilidadesFecha.crearFechaDesdeDDMMYYYY(fechafin)));
			}
			List<HistoricoBajas> lstHistorico = crit.list();
			
			hsResultados.put(TextConstant.KEY_HISTORICO_BORRADOS, lstHistorico);
		}catch(Exception eError){
			logger.error("Error recogerHistorico en NegoHistoricoBorrados:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
}
