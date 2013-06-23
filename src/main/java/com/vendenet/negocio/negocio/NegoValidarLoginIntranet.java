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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.Usuario;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.UtilidadesFecha;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class NegoValidarLoginIntranet {
	private Logger logger = Logger.getLogger(NegoValidarLoginIntranet.class);
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

	public Usuario validarUsuario(Session session, String username, String pass) throws ErrorVendenet {
		Usuario user = null;
		try{
			Criteria crit = session.createCriteria(Usuario.class);
			crit.add(Restrictions.eq("name",username));
			crit.add(Restrictions.eq("pass",pass));
			List listaResultados=crit.list();
			if((listaResultados!=null)&&(listaResultados.size()>0)){
				user=(Usuario)listaResultados.get(0);
			}
		}catch(Exception eError){
			logger.error("Error guardando un anuncio en NegoValidarLoginIntranet:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
		return user;
	}

	public void recogerAnunciosParaValidar(Session session) throws ErrorVendenet {
		try{
			Criteria crit = session.createCriteria(Anuncio.class);
			crit.setMaxResults(ConstantesVendenet.ANUNCIOS_POR_PAGINA_INTRANET);
			crit.add(Restrictions.eq("revisado",false));
			crit.addOrder(Order.asc(TextConstant.CRITERIO_ORDEN_INTRANET));
			List listaResultados=crit.list();
			hsResultados.put(TextConstant.KEY_LISTA_ANUNCIOS_SIN_PUBLICAR, listaResultados);
		}catch(Exception eError){
			logger.error("Error guardando un anuncio en NegoValidarLoginIntranet:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
		
	}

	public void recogerAnuncios(Session session, boolean incluirRevisados,boolean incluirPublicados, String fechainicio,
			String fechafin) throws ErrorVendenet {
		try{
			Criteria crit = session.createCriteria(Anuncio.class);
			crit.setMaxResults(ConstantesVendenet.ANUNCIOS_POR_PAGINA_INTRANET);
			if(!incluirRevisados){
				crit.add(Restrictions.eq("revisado",false));
			}else hsResultados.put(TextConstant.KEY_INCLUIR_REVISADOS, true);
			if(!incluirPublicados){
				crit.add(Restrictions.eq("publicado",false));
			}else hsResultados.put(TextConstant.KEY_INCLUIR_PUBLICADOS, true);
			if((fechainicio!=null)&&(!fechainicio.equals(TextConstant.BLANK))){
				hsResultados.put(TextConstant.KEY_FECHA_DESDE, fechainicio);
				crit.add(Restrictions.ge("fechaAlta",UtilidadesFecha.crearFechaDesdeDDMMYYYY(fechainicio)));
			}
			if((fechafin!=null)&&(!fechafin.equals(TextConstant.BLANK))){
				hsResultados.put(TextConstant.KEY_FECHA_HASTA, fechafin);
				crit.add(Restrictions.le("fechaAlta",UtilidadesFecha.crearFechaDesdeDDMMYYYY(fechafin)));
			}
			crit.addOrder(Order.asc(TextConstant.CRITERIO_ORDEN_INTRANET));
			List listaResultados=crit.list();
			hsResultados.put(TextConstant.KEY_LISTA_ANUNCIOS_SIN_PUBLICAR, listaResultados);
		}catch(Exception eError){
			logger.error("Error guardando un anuncio en NegoValidarLoginIntranet:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}

	public void calcularAnuncioPorPublicar(Session session) throws ErrorVendenet {
		try{
			Criteria crit = session.createCriteria(Anuncio.class);
	        crit.add( Restrictions.eq("publicado",false));
	        crit.setProjection(Projections.rowCount()); 
			int numTotal=((Integer)crit.list().get(0)).intValue();
			hsResultados.put(TextConstant.KEY_NUM_TOTAL_ANUNCIOS_REVISION, numTotal);
		}catch(Exception eError){
			logger.error("Error guardando un anuncio en NegoValidarLoginIntranet:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}

	public void calcularAnuncioPorRevisar(Session session) throws ErrorVendenet {
		try{
			Criteria crit = session.createCriteria(Anuncio.class);
	        crit.add( Restrictions.eq("revisado",false));
	        crit.setProjection(Projections.rowCount()); 
			int numTotal=((Integer)crit.list().get(0)).intValue();
			hsResultados.put(TextConstant.KEY_NUM_TOTAL_ANUNCIOS_REVISION, numTotal);
		}catch(Exception eError){
			logger.error("Error guardando un anuncio en NegoValidarLoginIntranet:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
}
