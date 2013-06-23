/*
 * Creado el 08-oct-09
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package com.vendenet.negocio.negocio;

import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.Critica;
import com.vendenet.negocio.entidad.EnvioPendiente;
import com.vendenet.negocio.entidad.IpVisitante;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.UtilidadesCritica;
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
public class NegoAnuncio {
	private Logger logger = Logger.getLogger(NegoAnuncio.class);
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
	public Anuncio obtenerAnuncioActivoPorId(Integer idAnuncio,Session session) throws ErrorVendenet {
		try{
			Anuncio loadedAnuncio = null;
			if(session!=null){
				Criteria crit = session.createCriteria(Anuncio.class);
				crit.add(Restrictions.eq("id",idAnuncio));
				crit.add(Restrictions.eq("publicado",true));
				List listaResultados=crit.list();
				if((listaResultados!=null)&&(listaResultados.size()>0)){
					loadedAnuncio = (Anuncio)crit.list().get(0);
					int numCriticas=0;
					double mediaCriticas=0;
					List lstCriticas=UtilidadesCritica.obtenerCriticas(session,loadedAnuncio.getCliente().getEmail());
					if(lstCriticas!=null && lstCriticas.size()>0){
						Iterator<Critica> itCriticas = lstCriticas.iterator();
						while(itCriticas.hasNext()){
							Critica criticaTemp=(Critica)itCriticas.next();
							numCriticas++;
							mediaCriticas+=criticaTemp.getValoracion();
						}
						mediaCriticas= UtilidadesNumericos.redondear(mediaCriticas/numCriticas,2);
					}
					loadedAnuncio.setNumCriticas(numCriticas);
					loadedAnuncio.setMediaCriticas(mediaCriticas);
					hsResultados.put(TextConstant.KEY_ANUNCIO,loadedAnuncio);
				}
				int numVisitas=UtilidadesVendenet.obtenerNumVisitas(session);
				int numAnuncios=UtilidadesVendenet.obtenerNumAnuncioVendedor(session,loadedAnuncio.getCliente().getEmail());
				hsResultados.put(TextConstant.KEY_TOTAL_VISITAS,numVisitas);
				hsResultados.put(TextConstant.KEY_TOTAL_ANUNCIOS,numAnuncios);
			}
			return loadedAnuncio;
		}			
		catch(Exception eError)
		{
			logger.error("Error en NegoAnuncio: obtenerAnuncioActivoPorId:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
		
	}
	
	public void grabarAnuncio(Session session, Anuncio anuncio, String password) throws ErrorVendenet {
		try{
			if((anuncio!=null)&&(password!=null)){
				anuncio.getCliente().setPass(password);
				session.saveOrUpdate(anuncio.getCliente());
				session.saveOrUpdate(anuncio);
			}
		}catch(Exception eError){
			logger.error("Error guardando un anuncio en NegoAnuncio - grabarAnuncio:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}

	public Anuncio obtenerAnuncioPorId(Integer idAnuncio, Session session) throws ErrorVendenet {
		try{
			Criteria crit = session.createCriteria(Anuncio.class);
			crit.add(Restrictions.eq("id",idAnuncio));
			//crit.add(Restrictions.eq("publicado",false));
			List listaResultados=crit.list();
			Anuncio loadedAnuncio = null;
			if((listaResultados!=null)&&(listaResultados.size()>0)){
				loadedAnuncio = (Anuncio)crit.list().get(0);
				hsResultados.put("anuncio",loadedAnuncio);
			}
			int numVisitas=UtilidadesVendenet.obtenerNumVisitas(session);
			int numAnuncios=UtilidadesVendenet.obtenerNumAnuncioVendedor(session,loadedAnuncio.getCliente().getEmail());
			hsResultados.put(TextConstant.KEY_TOTAL_VISITAS,numVisitas);
			hsResultados.put(TextConstant.KEY_TOTAL_ANUNCIOS,numAnuncios);
			return loadedAnuncio;
		}			
		catch(Exception eError)
		{
			logger.error("Error en NegoAnuncio - obtenerAnuncioPorId:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
		
	}
	
//	public void emailAnuncioNuevo(Session session, Anuncio anuncio) throws ErrorVendenet {
//		try{
//			EnvioPendiente envio = new EnvioPendiente();
//			envio.setFechaAlta(new Date());
//			envio.setEmailFrom(ConstantesVendenet.EMAIL_FROM);
//			envio.setEmailTo(ConstantesVendenet.EMAIL_WEBMASTER);
//			envio.setEmailSubject(TextConstant.ANUNCIO_NUEVO);
//			envio.setEnviado(false);
//			envio.setName(TextConstant.BLANK);
//			envio.setAnuncio(anuncio);
//			envio.setEmailBody("");
//			session.saveOrUpdate(envio);
//		}
//		catch(Exception eError)
//		{
//			logger.error("Error en NegoAnuncio devolverAnuncioPalabrota:");
//			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
//			throw err;
//		}
//	}
	
	public void devolverAnuncioPalabrota(Session session, Anuncio anuncio) throws ErrorVendenet {
		try{
			//Antes de registrar otro envio de email comprobar que para este anuncio no hay mas envios pendientes (caso que no deberia darse pero por si acaso)
			if(anuncio!=null && anuncio.getCliente() != null && anuncio.getCliente().getEmail() != null){
				EnvioPendiente envio = new EnvioPendiente();
				envio.setFechaAlta(new Date());
				envio.setEmailFrom(ConstantesVendenet.EMAIL_FROM);
				envio.setEmailTo(anuncio.getCliente().getEmail());
				envio.setEmailSubject(TextConstant.ANUNCIO_NO_ACEPTADO);
				envio.setEnviado(false);
				envio.setName(TextConstant.BLANK);
				envio.setAnuncio(anuncio);
				envio.setEmailBody(UtilidadesMail.cabeceraEmail(UtilidadesMail.cuerpoMailAnuncioDevuelto(TextConstant.PALABRAS_MAL_SONANTES, anuncio),anuncio));
				session.saveOrUpdate(envio);
				anuncio.setPublicado(false);
				anuncio.setRevisado(true);
				anuncio.setFechaCaducidad(UtilidadesFecha.devolverFechaCaducidadDevuelto(new Date()));//Actualizamos la fecha de caducidad a la fecha de plazo para corregir anuncio
				session.saveOrUpdate(anuncio);
			}
		}
		catch(Exception eError)
		{
			logger.error("Error en NegoAnuncio - devolverAnuncioPalabrota:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
	
	public void aumentarVisitasAnuncio(Anuncio anuncio, Session session, String ip) throws ErrorVendenet {
		try{
			if(anuncio!=null){
				Criteria crit = session.createCriteria(IpVisitante.class);
				Criterion crt_anuncio = Restrictions.eq("anuncio",anuncio);
				crit.add(crt_anuncio);
				Criterion crt_ip = Restrictions.eq("name",ip);
				crit.add(crt_ip);
				List<IpVisitante> resultVisitas = crit.list();
				if(resultVisitas.size()==0){
					IpVisitante nuevaVisita = new IpVisitante();
					nuevaVisita.setAnuncio(anuncio);
					nuevaVisita.setName(ip);
					session.saveOrUpdate(nuevaVisita);
				}
			}
		}catch(Exception eError){
			logger.error("Error en NegoAnuncio - aumentarVisitasAnuncio:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
	
}
