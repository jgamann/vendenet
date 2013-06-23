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
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.Critica;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 * 
 *         Para cambiar la plantilla para este comentario de tipo generado vaya
 *         a Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y
 *         comentarios
 */
public class NegoConfirmarOpinion {
	private Logger logger = Logger.getLogger(NegoConfirmarOpinion.class);
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

	public void confirmarOpinion(Session session, String codigo)
			throws ErrorVendenet {
		try {
			Criteria crit = session.createCriteria(Critica.class);
			crit.add(Restrictions.eq("codigo", codigo));
			List listaResultados = crit.list();
			if (listaResultados != null && listaResultados.size() > 0) {
				Critica critica = (Critica) listaResultados.get(0);
				if (!critica.isConfirmada()) {
					critica.setConfirmada(true);
					session.saveOrUpdate(critica);
					hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.MENSAJE_OPINION_CONFIRMADA);
					hsResultados.put(TextConstant.KEY_ICONO,TextConstant.IMAGEN_OK_P);
				} else{
					hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.MENSAJE_OPINION_YA_CONFIRMADA);
					hsResultados.put(TextConstant.KEY_ICONO,TextConstant.IMAGEN_WRN_P);
				}
			} else {// No se encuentra el codigo
				hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.MENSAJE_OPINION_NO_CONFIRMADA);
				hsResultados.put(TextConstant.KEY_ICONO,TextConstant.IMAGEN_KO_P);
			}
		} catch (Exception eError) {
			hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.MENSAJE_OPINION_NO_CONFIRMADA);
			hsResultados.put(TextConstant.KEY_ICONO,TextConstant.IMAGEN_KO_P);
			logger.error("Error en NegoConfirmarOpinion - confirmarOpinion"
					+ eError);
			ErrorVendenet err = ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}

}
