/*
 * Creado el 08-oct-09
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package com.vendenet.negocio.negocio;

import java.util.Date;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.EnvioPendiente;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.UtilidadesMail;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

/**
 * @author TXUS
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class NegoEnviarConsulta {
	private Logger logger = Logger.getLogger(NegoEnviarConsulta.class);
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

	public boolean validarCampos() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void registrarEmail(Session session, String nombre, String email,
			String tfno, String pregunta,String idAnuncio) throws ErrorVendenet {
		try{
			Anuncio loadeAnuncio = (Anuncio)session.load(Anuncio.class, new Integer(idAnuncio));
			EnvioPendiente envio = new EnvioPendiente();
			envio.setAnuncio(loadeAnuncio);
			envio.setEmailBody(UtilidadesMail.cabeceraEmail(UtilidadesMail.cuerpoMailConsulta(nombre,email,tfno,pregunta,loadeAnuncio),loadeAnuncio));
			envio.setEmailFrom(ConstantesVendenet.EMAIL_FROM);
			envio.setEmailReply(email);
			envio.setEmailSubject(new StringBuffer(TextConstant.CONSULTA_RECIBIDA).append(loadeAnuncio.getName()).toString());
			envio.setEmailTo(loadeAnuncio.getCliente().getEmail());
			envio.setEnviado(false);
			envio.setFechaAlta(new Date());
			envio.setName(TextConstant.BLANK);
			session.saveOrUpdate(envio);
			hsResultados.put(TextConstant.KEY_RESPUESTA, TextConstant.OK);
		}catch(Exception eError){
			hsResultados.put(TextConstant.KEY_RESPUESTA, "No se ha enviado la consulta, algun campo del formulario estaba incorrecto");
			logger.error("Error en NegoEnviarConsulta:"+eError);
			ErrorVendenet err= ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}
	
}
