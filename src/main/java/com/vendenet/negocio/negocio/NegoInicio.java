/*
 * Creado el 08-oct-09
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package com.vendenet.negocio.negocio;

import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.CategoriaAnuncio;
import com.vendenet.negocio.entidad.CriterioOrden;
import com.vendenet.negocio.entidad.Provincia;
import com.vendenet.negocio.entidad.TipoAnuncio;
import com.vendenet.negocio.entidad.TipoVendedor;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.HibernateUtil;
import com.vendenet.utilidades.UtilidadesCritica;
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
public class NegoInicio {
	private Logger logger = Logger.getLogger(NegoGestorArchivos.class);
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

	public void recogerContenido(String patron, String pagina, HttpSession sesion, Session session) throws ErrorVendenet {
		try{
			if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
			List<CriterioOrden> resultCriterios = (List<CriterioOrden>)session.createQuery("from CriterioOrden order by CRITERIOORDEN_NAME").list();
	        List<Provincia> resultProvincias = (List<Provincia>)session.createQuery("from Provincia order by PROVINCIA_NAME_LONG").list();
	        List<TipoAnuncio> resultTiposAnuncio = (List<TipoAnuncio>)session.createQuery("from TipoAnuncio order by TIPOANUNCIO_ID").list();
	        List<TipoVendedor> resultTiposVendedor = (List<TipoVendedor>)session.createQuery("from TipoVendedor order by TIPOVENDEDOR_ID").list();
	        List<CategoriaAnuncio> resultCategorias = (List<CategoriaAnuncio>)session.createQuery("from CategoriaAnuncio order by ORDEN").list();
	        buscar(patron,pagina,sesion,session);
	        int numResultados=obtenerNumResultados(patron,pagina,sesion,session);
	        int numVisitas=UtilidadesVendenet.obtenerNumVisitas(session);
	        hsResultados.put(TextConstant.KEY_TOTAL_VISITAS,numVisitas);
			hsResultados.put(TextConstant.KEY_PROVINCIAS,resultProvincias);
			hsResultados.put(TextConstant.KEY_CATEGORIAS,resultCategorias);
			hsResultados.put(TextConstant.KEY_TIPOS_ANUNCIO,resultTiposAnuncio);
			hsResultados.put(TextConstant.KEY_TIPOS_VENDEDOR,resultTiposVendedor);
			hsResultados.put(TextConstant.KEY_CRITERIOS_ORDEN,resultCriterios);
			hsResultados.put(TextConstant.KEY_NUM_TOTAL,numResultados);
		}
		catch(Exception eError)
		{
			System.err.println(eError);
			logger.error("Error en NegoInicio: recogerContenido"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
	

	private int obtenerNumResultados(String patron, String pagina,
			HttpSession sesion, Session session) throws ErrorVendenet {
		try{
			int numTotal = 0;
			CategoriaAnuncio categoria = null;
			Provincia provincia = null;
			CriterioOrden criterio = null;
			TipoAnuncio tipoAnuncio = null;
			TipoVendedor tipoVendedor = null;
			if(sesion.getAttribute(TextConstant.KEY_CATEGORIA)!=null)categoria=(CategoriaAnuncio)sesion.getAttribute(TextConstant.KEY_CATEGORIA);
			if(sesion.getAttribute(TextConstant.KEY_PROVINCIA)!=null)provincia=(Provincia)sesion.getAttribute(TextConstant.KEY_PROVINCIA);
			if(sesion.getAttribute(TextConstant.KEY_TIPO_ANUNCIO)!=null)tipoAnuncio=(TipoAnuncio)sesion.getAttribute(TextConstant.KEY_TIPO_ANUNCIO);
			if(sesion.getAttribute(TextConstant.KEY_TIPO_VENDEDOR)!=null)tipoVendedor=(TipoVendedor)sesion.getAttribute(TextConstant.KEY_TIPO_VENDEDOR);
			if(sesion.getAttribute(TextConstant.KEY_CRITERIO_ORDEN)!=null)criterio=(CriterioOrden)sesion.getAttribute(TextConstant.KEY_CRITERIO_ORDEN);
			int paginaActual = 1;
			if(pagina!=null){//Mirar si se esta paginando
				if(UtilidadesNumericos.isNumberInteger(pagina)){
					paginaActual = Integer.parseInt(pagina);
				}
			}
			Criteria crit = session.createCriteria(Anuncio.class);
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
			//crit.setFirstResult(ConstantesVendenet.ANUNCIOS_POR_PAGINA*(paginaActual-1)).setMaxResults(ConstantesVendenet.ANUNCIOS_POR_PAGINA);
	        if(!patron.equals("")){
	        	StringTokenizer st = new StringTokenizer(patron,TextConstant.SPACE);
            	crit.add(HibernateUtil.metodoBuclado(st));
	        }
	        crit.add( Restrictions.eq("publicado",true));
	        crit.setProjection(Projections.rowCount()); 
			numTotal=((Integer)crit.list().get(0)).intValue();
			return numTotal;
		}			
		catch(Exception eError)
		{
			logger.error("Error en NegoInicio: obtenerNumResultados"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	
	}
		

	public void buscar(String patron, String pagina, HttpSession sesion,Session session) throws ErrorVendenet {
		try{
			CategoriaAnuncio categoria = null;
			Provincia provincia = null;
			CriterioOrden criterio = null;
			TipoAnuncio tipoAnuncio = null;
			TipoVendedor tipoVendedor = null;
			if(sesion.getAttribute(TextConstant.KEY_CATEGORIA)!=null)categoria=(CategoriaAnuncio)sesion.getAttribute(TextConstant.KEY_CATEGORIA);
			if(sesion.getAttribute(TextConstant.KEY_PROVINCIA)!=null)provincia=(Provincia)sesion.getAttribute(TextConstant.KEY_PROVINCIA);
			if(sesion.getAttribute(TextConstant.KEY_CRITERIO_ORDEN)!=null)criterio=(CriterioOrden)sesion.getAttribute(TextConstant.KEY_CRITERIO_ORDEN);
			if(sesion.getAttribute(TextConstant.KEY_TIPO_ANUNCIO)!=null)tipoAnuncio=(TipoAnuncio)sesion.getAttribute(TextConstant.KEY_TIPO_ANUNCIO);
			if(sesion.getAttribute(TextConstant.KEY_TIPO_VENDEDOR)!=null)tipoVendedor=(TipoVendedor)sesion.getAttribute(TextConstant.KEY_TIPO_VENDEDOR);
			
			int paginaActual = 1;
			if(pagina!=null){//Mirar si se esta paginando
				if(UtilidadesNumericos.isNumberInteger(pagina)){
					paginaActual = Integer.parseInt(pagina);
				}
			}
			Criteria crit = session.createCriteria(Anuncio.class);
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
			crit.setFirstResult(ConstantesVendenet.ANUNCIOS_POR_PAGINA*(paginaActual-1)).setMaxResults(ConstantesVendenet.ANUNCIOS_POR_PAGINA);
	        if(!patron.equals("")){
	        	StringTokenizer st = new StringTokenizer(patron,TextConstant.SPACE);
            	crit.add(HibernateUtil.metodoBuclado(st));
	        }
	        crit.add( Restrictions.eq("publicado",true));
	        List lstAnuncios = crit.list();
			hsResultados.put(TextConstant.PATRON_NO_AJAX,patron);
			hsResultados.put(TextConstant.KEY_PAGINA_ACTUAL,paginaActual);
			hsResultados.put("lstAnuncios",UtilidadesCritica.asignarCriticas(session, lstAnuncios));
		}			
		catch(Exception eError)
		{
			logger.error("Error en NegoInicio: buscar"+eError);
			System.err.println(eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
	
}
