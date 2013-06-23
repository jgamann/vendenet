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
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.Adjunto;
import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.CategoriaAnuncio;
import com.vendenet.negocio.entidad.CriterioOrden;
import com.vendenet.negocio.entidad.EnvioPendiente;
import com.vendenet.negocio.entidad.Provincia;
import com.vendenet.negocio.entidad.TipoAnuncio;
import com.vendenet.negocio.entidad.TipoVendedor;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.UtilidadesAdjunto;
import com.vendenet.utilidades.UtilidadesFecha;
import com.vendenet.utilidades.UtilidadesMail;
import com.vendenet.utilidades.UtilidadesNumericos;
import com.vendenet.utilidades.UtilidadesVendenet;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class NegoGestorAjax {
	private Logger logger = Logger.getLogger(NegoGestorAjax.class);
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

	public void buscar(String patron, HttpSession sesion) throws ErrorVendenet {
		Transaction transaction = null; 
		List lstAnuncios = new ArrayList();
        Session session=HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        UtilidadesVendenet.guardarBusqueda(patron,session);
        CategoriaAnuncio categoria = null;
		Provincia provincia = null;
		TipoAnuncio tipoAnuncio = null;
		TipoVendedor tipoVendedor = null;
		CriterioOrden criterio = null;
		if(sesion.getAttribute(TextConstant.KEY_CATEGORIA)!=null)categoria=(CategoriaAnuncio)sesion.getAttribute(TextConstant.KEY_CATEGORIA);
		if(sesion.getAttribute(TextConstant.KEY_PROVINCIA)!=null)provincia=(Provincia)sesion.getAttribute(TextConstant.KEY_PROVINCIA);
		if(sesion.getAttribute(TextConstant.KEY_TIPO_ANUNCIO)!=null)tipoAnuncio=(TipoAnuncio)sesion.getAttribute(TextConstant.KEY_TIPO_ANUNCIO);
		if(sesion.getAttribute(TextConstant.KEY_TIPO_VENDEDOR)!=null)tipoVendedor=(TipoVendedor)sesion.getAttribute(TextConstant.KEY_TIPO_VENDEDOR);
		if(sesion.getAttribute(TextConstant.KEY_CRITERIO_ORDEN)!=null)criterio=(CriterioOrden)sesion.getAttribute(TextConstant.KEY_CRITERIO_ORDEN);
        StringTokenizer st = new StringTokenizer(patron,TextConstant.SPACE);
        Criteria crit = session.createCriteria(Anuncio.class);
        crit.setFirstResult(0).setMaxResults(ConstantesVendenet.ANUNCIOS_POR_PAGINA_AJAX);
    	if(categoria!=null){
    		Criterion crt_categoriaPadre = Restrictions.sqlRestriction("{alias}.CATEGORIA_ID in (select CATEGORIA_ID from categoriaanuncio where CATEGORIAPADRE_ID = "+categoria.getId()+")");
    		Criterion crt_categoria = Restrictions.eq("categoria",categoria);
    		Criterion orCriterion = Restrictions.or(crt_categoria, crt_categoriaPadre);
    		crit.add(orCriterion);
    	}
    	if(provincia!=null){
    		Criterion crt_provincia = Restrictions.eq("provincia",provincia);
    		crit.add(crt_provincia);
    	}
    	if(tipoAnuncio!=null){
    		Criterion crt_tipoAnuncio = Restrictions.eq("tipoAnuncio",tipoAnuncio);
    		crit.add(crt_tipoAnuncio);
    	}
    	if(tipoVendedor!=null){
    		Criterion crt_tipoVendedor = Restrictions.eq("tipoVendedor",tipoVendedor);
    		crit.add(crt_tipoVendedor);
    	}
    	if(criterio!=null){
    		if(criterio.isAsc())crit.addOrder(Order.asc(criterio.getClave()));
    		else crit.addOrder(Order.desc(criterio.getClave()));
    	}else{
    		crit.addOrder(Order.desc(TextConstant.DEFAULT_CRITERIO_ORDEN));
    	}
    	crit.add( Restrictions.eq("publicado",true));
    	List result = crit.add(HibernateUtil.metodoBuclado(st)).list();
        lstAnuncios=result;
        if(transaction!=null)transaction.commit();
        try{
        	hsResultados.put(TextConstant.PATRON,patron);
			hsResultados.put("lstAnuncios",lstAnuncios);
		}
		catch(Exception eError)
		{
			logger.error("Error en NegoGestorAjax:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
		finally{
			session.close();
		}
		}

	public Provincia obtenerProvincia(String idProvincia) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Provincia loadedProvincia = (Provincia)session.get(Provincia.class, new Integer(idProvincia));
		session.close();
		return loadedProvincia;
	}

	public CategoriaAnuncio obtenerCategoria(String idCategoria) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		CategoriaAnuncio loadedCategoria = (CategoriaAnuncio)session.get(CategoriaAnuncio.class, new Integer(idCategoria));
		session.close();
		return loadedCategoria;
	}
	
	public TipoAnuncio obtenerTipoAnuncio(String idTipoAnuncios) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		TipoAnuncio loadedTipoAnuncio = (TipoAnuncio)session.get(TipoAnuncio.class, new Integer(idTipoAnuncios));
		session.close();
		return loadedTipoAnuncio;
	}
	
	public TipoVendedor obtenerTipoVendedor(String idTipoVendedor) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		TipoVendedor loadedTipoVendedor = (TipoVendedor)session.get(TipoVendedor.class, new Integer(idTipoVendedor));
		session.close();
		return loadedTipoVendedor;
	}
	
	
	public CriterioOrden obtenerOrden(String idOrden,boolean asc) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		CriterioOrden loadedCriterio = (CriterioOrden)session.get(CriterioOrden.class, new Integer(idOrden));
		session.close();
        loadedCriterio.setAsc(asc);
		return loadedCriterio;
	}

	public void cambiarEstadoAnuncio(String idAnuncio, Session session,
			boolean estado) throws ErrorVendenet {
		if((idAnuncio!=null)&&(UtilidadesNumericos.isNumberInteger(idAnuncio))){
			Anuncio loadedAnuncio = (Anuncio)session.get(Anuncio.class, new Integer(idAnuncio));
			loadedAnuncio.setPublicado(estado);
			loadedAnuncio.setRevisado(true);
			loadedAnuncio.setFechaCaducidad(UtilidadesFecha.devolverFechaCaducidad(new Date()));//Actualizamos la fecha de caducidad
			session.saveOrUpdate(loadedAnuncio);
			hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.OK);
		}else hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.NUMERO_DE_ANUNCIO_INCORRECTO);
		
	}

	public void borrarAnuncio(String idAnuncio, Session session) throws ErrorVendenet {
		if((idAnuncio!=null)&&(UtilidadesNumericos.isNumberInteger(idAnuncio))){
			Anuncio loadedAnuncio = (Anuncio)session.get(Anuncio.class, new Integer(idAnuncio));
			List lstAdjuntos = loadedAnuncio.getAdjuntos();
			if((lstAdjuntos!=null)&&(lstAdjuntos.size()>0)){//Si tiene archivos adjuntos, se borraran fisicamente del HD
				Iterator itAdjuntos = lstAdjuntos.iterator();
				while(itAdjuntos.hasNext()){
					Adjunto adjuntoTemp = (Adjunto)itAdjuntos.next();
					UtilidadesAdjunto.borrarFicheros(adjuntoTemp.getPath());
					session.delete(adjuntoTemp);
				}
			}
			session.delete(loadedAnuncio);
			hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.OK);
		}else hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.NUMERO_DE_ANUNCIO_INCORRECTO);
	}

	public void guardarEnvioMailAceptado(String idAnuncio, Session session) {
		if((idAnuncio!=null)&&(UtilidadesNumericos.isNumberInteger(idAnuncio))){
			Anuncio loadedAnuncio = (Anuncio)session.get(Anuncio.class, new Integer(idAnuncio));
			//Antes de registrar otro envio de email comprobar que para este anuncio no hay mas envios pendientes (caso que no deberia darse pero por si acaso)
			Criteria crit = session.createCriteria(EnvioPendiente.class);
			crit.add(Restrictions.eq("anuncio",loadedAnuncio));
			crit.add(Restrictions.eq("enviado",false));
			crit.setProjection(Projections.rowCount());
			int numTotal=((Integer)crit.list().get(0)).intValue();
			if(numTotal==0){
				if(loadedAnuncio!=null && loadedAnuncio.getCliente() != null && loadedAnuncio.getCliente().getEmail() != null){
					EnvioPendiente envio = new EnvioPendiente();
					envio.setFechaAlta(new Date());
					envio.setEmailFrom(ConstantesVendenet.EMAIL_FROM);
					envio.setEmailTo(loadedAnuncio.getCliente().getEmail());
					envio.setEmailSubject(TextConstant.ANUNCIO_ACEPTADO);
					envio.setEnviado(false);
					envio.setName(TextConstant.BLANK);
					envio.setAnuncio(loadedAnuncio);
					envio.setEmailBody(UtilidadesMail.cabeceraEmail(UtilidadesMail.cuerpoMailAnuncioAceptado(loadedAnuncio),loadedAnuncio));
					session.saveOrUpdate(envio);
				}
			}
		}
	}

	public void guardarEnvioMailDevuelto(String idAnuncio, Session session,
			String motivo) {
		if((idAnuncio!=null)&&(UtilidadesNumericos.isNumberInteger(idAnuncio))){
			Anuncio loadedAnuncio = (Anuncio)session.get(Anuncio.class, new Integer(idAnuncio));
			//Antes de registrar otro envio de email comprobar que para este anuncio no hay mas envios pendientes (caso que no deberia darse pero por si acaso)
			Criteria crit = session.createCriteria(EnvioPendiente.class);
			crit.add(Restrictions.eq("anuncio",loadedAnuncio));
			crit.add(Restrictions.eq("enviado",false));
			crit.setProjection(Projections.rowCount());
			int numTotal=((Integer)crit.list().get(0)).intValue();
			if(numTotal==0){
				if(loadedAnuncio!=null && loadedAnuncio.getCliente() != null && loadedAnuncio.getCliente().getEmail() != null){
					EnvioPendiente envio = new EnvioPendiente();
					envio.setFechaAlta(new Date());
					envio.setEmailFrom(ConstantesVendenet.EMAIL_FROM);
					envio.setEmailTo(loadedAnuncio.getCliente().getEmail());
					envio.setEmailSubject(TextConstant.ANUNCIO_NO_ACEPTADO);
					envio.setEnviado(false);
					envio.setName(TextConstant.BLANK);
					envio.setAnuncio(loadedAnuncio);
					envio.setEmailBody(UtilidadesMail.cabeceraEmail(UtilidadesMail.cuerpoMailAnuncioDevuelto(motivo, loadedAnuncio),loadedAnuncio));
					session.saveOrUpdate(envio);
				}
			}
		}
	}

	public void devolverAnuncio(String idAnuncio, Session session, boolean b) throws ErrorVendenet {
		if((idAnuncio!=null)&&(UtilidadesNumericos.isNumberInteger(idAnuncio))){
			Anuncio loadedAnuncio = (Anuncio)session.get(Anuncio.class, new Integer(idAnuncio));
			loadedAnuncio.setPublicado(b);
			loadedAnuncio.setRevisado(true);
			loadedAnuncio.setFechaCaducidad(UtilidadesFecha.devolverFechaCaducidadDevuelto(new Date()));//Actualizamos la fecha de caducidad a la fecha de plazo para corregir anuncio
			session.saveOrUpdate(loadedAnuncio);
			hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.OK);
		}else hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.NUMERO_DE_ANUNCIO_INCORRECTO);
	}
	
}
