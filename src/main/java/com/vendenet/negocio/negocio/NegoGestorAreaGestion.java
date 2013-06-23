/*
 * Creado el 08-oct-09
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaciï¿½n de cï¿½digo&gt;Cï¿½digo y comentarios
 */
package com.vendenet.negocio.negocio;

import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.Adjunto;
import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.Cliente;
import com.vendenet.negocio.entidad.EnvioPendiente;
import com.vendenet.negocio.entidad.HistoricoBajas;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.UtilidadesAdjunto;
import com.vendenet.utilidades.UtilidadesMail;
import com.vendenet.utilidades.UtilidadesNumericos;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaciï¿½n de cï¿½digo&gt;Cï¿½digo y comentarios
 */
public class NegoGestorAreaGestion {
	private Logger logger = Logger.getLogger(NegoGestorAreaGestion.class);
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

	
	public void borrarAnuncio(String idAnuncio, String pass, Session session) throws ErrorVendenet {
		if((idAnuncio!=null)&&(UtilidadesNumericos.isNumberInteger(idAnuncio))){
			Anuncio loadedAnuncio = (Anuncio)session.get(Anuncio.class, new Integer(idAnuncio));
			if(loadedAnuncio!=null){
				if(loadedAnuncio.getCliente().getPass().equals(pass)){
					borrarAnuncio(idAnuncio,session);
					hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.OK);
				}else hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.CONTRASENA_INCORRECTA);
			}else hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.ANUNCIO_NO_ENCONTRADO);
		}else{
			hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.MENSAJE_ERROR);
		}
	}

	public void borrarAnuncio(String idAnuncio, Session session) throws ErrorVendenet {
		if((idAnuncio!=null)&&(UtilidadesNumericos.isNumberInteger(idAnuncio))){
			Anuncio loadedAnuncio = (Anuncio)session.get(Anuncio.class, new Integer(idAnuncio));
			
			HistoricoBajas historico = new HistoricoBajas();
			historico.setEmailCliente(loadedAnuncio.getCliente().getEmail());
			historico.setFechaBorrado(new Date());
			historico.setName(loadedAnuncio.getCliente().getName());
			historico.setCategoria(loadedAnuncio.getCategoria());
			historico.setTituloAnuncio(loadedAnuncio.getName());
			historico.setCuerpoAnuncio(loadedAnuncio.getCuerpo());
			historico.setPrecio(loadedAnuncio.getPrecio());
			historico.setProvincia(loadedAnuncio.getProvincia());
			historico.setTipoAnuncio(loadedAnuncio.getTipoAnuncio());
			List lstAdjuntos = loadedAnuncio.getAdjuntos();
			if((lstAdjuntos!=null)&&(lstAdjuntos.size()>0)){//Si tiene archivos adjuntos, se borraran fisicamente del HD
				Iterator itAdjuntos = lstAdjuntos.iterator();
				while(itAdjuntos.hasNext()){
					Adjunto adjuntoTemp = (Adjunto)itAdjuntos.next();
					UtilidadesAdjunto.borrarFicheros(adjuntoTemp.getPath());
					session.delete(adjuntoTemp);
				}
			}
//			
//			StringBuffer asunto = new StringBuffer(TextConstant.ANUNCIO_NOTIFICACION_BORRADO);
//			if((loadedAnuncio!=null) && (loadedAnuncio.getName()!=null)){
//				asunto.append(loadedAnuncio.getName());
//			}
//			EnvioPendiente envio = new EnvioPendiente();
//			envio.setFechaAlta(new Date());
//			envio.setEmailFrom(ConstantesVendenet.EMAIL_FROM);
//			envio.setEmailTo(ConstantesVendenet.EMAIL_WEBMASTER);
//			envio.setEmailSubject(asunto.toString());
//			envio.setEnviado(false);
//			envio.setName(TextConstant.BLANK);
//			envio.setAnuncio(null);
//			envio.setEmailBody("");
//			session.saveOrUpdate(envio);
			UtilidadesMail.emailAnuncioBorrado(session,loadedAnuncio);
			session.saveOrUpdate(historico);
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

	

	public void recordarClave(String idAnuncio, Session session) throws ErrorVendenet {
		if((idAnuncio!=null)&&(UtilidadesNumericos.isNumberInteger(idAnuncio))){
			Anuncio loadedAnuncio = (Anuncio)session.get(Anuncio.class, new Integer(idAnuncio));
			//Antes de registrar otro envio de email de recordatorio de contraseña para ese anuncio sin enviar
			Criteria crit = session.createCriteria(EnvioPendiente.class);
			crit.add(Restrictions.eq("anuncio",loadedAnuncio));
			crit.add(Restrictions.eq("enviado",false));
			crit.add(Restrictions.eq("emailSubject",TextConstant.TIPO_EMAIL_RECORDATORIO));
			crit.setProjection(Projections.rowCount());
			int numTotal=((Integer)crit.list().get(0)).intValue();
			if(numTotal==0){
				if(loadedAnuncio!=null && loadedAnuncio.getCliente() != null && loadedAnuncio.getCliente().getEmail() != null){
					EnvioPendiente envio = new EnvioPendiente();
					envio.setFechaAlta(new Date());
					envio.setEmailFrom(ConstantesVendenet.EMAIL_FROM);
					envio.setEmailTo(loadedAnuncio.getCliente().getEmail());
					envio.setName(TextConstant.BLANK);
					envio.setEmailSubject(TextConstant.TIPO_EMAIL_RECORDATORIO);
					envio.setEnviado(false);
					envio.setAnuncio(loadedAnuncio);
					envio.setEmailBody(UtilidadesMail.cabeceraEmail(UtilidadesMail.cuerpoMailRecordatorioClave(loadedAnuncio),loadedAnuncio));
					session.saveOrUpdate(envio);
					hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.OK);
				}
			}else hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.YA_HAY_UN_ENVIO_PENDIENTE);
		}
	}

	public Cliente validarClave(String idAnuncio, String pass, Session session) throws ErrorVendenet {
		Cliente cliente=null;
		if((idAnuncio!=null)&&(UtilidadesNumericos.isNumberInteger(idAnuncio))){
			Anuncio loadedAnuncio = (Anuncio)session.get(Anuncio.class, new Integer(idAnuncio));
			//Antes de registrar otro envio de email de recordatorio de contraseña para ese anuncio sin enviar
			if(loadedAnuncio!=null && loadedAnuncio.getCliente() != null && loadedAnuncio.getCliente().getPass() != null){
					if(loadedAnuncio.getCliente().getPass().equals(pass)){
						cliente=loadedAnuncio.getCliente();
						hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.OK);
					}else hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.CONTRASENA_INCORRECTA);
			}else hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.ANUNCIO_NO_ENCONTRADO);
		}else hsResultados.put(TextConstant.KEY_RESPUESTA,TextConstant.ANUNCIO_NO_ENCONTRADO);
		return cliente;
	}
}
