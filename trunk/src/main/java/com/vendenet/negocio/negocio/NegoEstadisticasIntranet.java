/*
 * Creado el 08-oct-09
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package com.vendenet.negocio.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.VisitaIntranet;
import com.vendenet.negocio.entidad.VisitaWeb;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.UtilidadesFecha;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class NegoEstadisticasIntranet {
	private Logger logger = Logger.getLogger(NegoEstadisticasIntranet.class);
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
	public void recogerEstadisticas(Session session, String fechainicio, String fechafin) throws ErrorVendenet {
		try{
			if(((fechainicio==null)&&(fechafin==null))||
					((fechainicio!=null)&&(fechafin!=null)&&(fechainicio.equals(TextConstant.BLANK))&&(fechafin.equals(TextConstant.BLANK)))){
				fechainicio=UtilidadesFecha.formatearFechaDDMMYYNoHoy(UtilidadesFecha.devolverFechaDiaMenos(new Date()));
				fechafin=UtilidadesFecha.formatearFechaDDMMYYNoHoy(UtilidadesFecha.devolverFechaDiaMas(new Date()));
			}
			Criteria critIntranet = session.createCriteria(VisitaIntranet.class);
			if((fechainicio!=null)&&(!fechainicio.equals(TextConstant.BLANK))){
				hsResultados.put(TextConstant.KEY_FECHA_DESDE, fechainicio);
				critIntranet.add(Restrictions.ge("fechaVisita",UtilidadesFecha.crearFechaDesdeDDMMYYYY(fechainicio)));
			}
			if((fechafin!=null)&&(!fechafin.equals(TextConstant.BLANK))){
				hsResultados.put(TextConstant.KEY_FECHA_HASTA, fechafin);
				critIntranet.add(Restrictions.le("fechaVisita",UtilidadesFecha.crearFechaDesdeDDMMYYYY(fechafin)));
			}
			List<VisitaIntranet> lstVisitasIntranet = critIntranet.list();
			hsResultados.put(TextConstant.KEY_VISITAS_INTRANET, lstVisitasIntranet);
			Criteria crit = session.createCriteria(VisitaWeb.class);
			if((fechainicio!=null)&&(!fechainicio.equals(TextConstant.BLANK))){
				hsResultados.put(TextConstant.KEY_FECHA_DESDE, fechainicio);
				crit.add(Restrictions.ge("fechaVisita",UtilidadesFecha.crearFechaDesdeDDMMYYYY(fechainicio)));
			}
			if((fechafin!=null)&&(!fechafin.equals(TextConstant.BLANK))){
				hsResultados.put(TextConstant.KEY_FECHA_HASTA, fechafin);
				crit.add(Restrictions.le("fechaVisita",UtilidadesFecha.crearFechaDesdeDDMMYYYY(fechafin)));
			}
			List<VisitaWeb> lstVisitasWeb = crit.list();
			List lstNavegador = new ArrayList();
			lstNavegador.add("Googlebot");
			lstNavegador.add("msnbot");
			lstNavegador.add("bingbot");
			lstNavegador.add("YandexBot");
			lstNavegador.add("Twitterbot");
			lstNavegador.add("Js-kit.com");
			lstNavegador.add("PaperLiBot");
			lstNavegador.add("metauri");
			lstNavegador.add("facebook");
			lstNavegador.add("pycURL");
			lstNavegador.add("wikiwix-bot");
			lstNavegador.add("OGS Critter");
			lstNavegador.add("voyager");
			lstNavegador.add("birubot");
			lstNavegador.add("MLBot");
			lstNavegador.add("MFE_expand");
			lstNavegador.add("yahoo");
			lstNavegador.add("Python-urllib");
			lstNavegador.add("Python-httplib");
			lstNavegador.add("LongURL API");
			lstNavegador.add("postrank.com");
			lstNavegador.add("TweetedTimes");
			lstNavegador.add("tweetmemebot");
			lstNavegador.add("butterfly");
			lstNavegador.add(TextConstant.KEY_NAVEGADORES_BOT);
			lstNavegador.add("MSIE");
			lstNavegador.add("Firefox");
			lstNavegador.add("Chrome");
			lstNavegador.add("Opera");
			lstNavegador.add("Macintosh");
			lstNavegador.add("Mozilla/5.0 (compatible");
			lstNavegador.add("iPhone");
			lstNavegador.add("ipad");
			lstNavegador.add("Android");
			hsResultados.put(TextConstant.KEY_VISITAS_WEB, lstVisitasWeb);
			hsResultados.put(TextConstant.KEY_TIPOS_NAVEGADOR, lstNavegador);
		}catch(Exception eError){
			logger.error("Error guardando un anuncio en NegoEstadisticasIntranet:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
}
