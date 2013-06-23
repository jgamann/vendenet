package com.vendenet.utilidades;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.vendenet.negocio.entidad.Anuncio;
import com.vendenet.negocio.entidad.EnvioPendiente;
import com.vendenet.negocio.entidad.EnvioRealizado;
import com.vendenet.negocio.error.ErrorVendenet;
import com.vendenet.utilidades.constantes.ConstantesVendenet;
import com.vendenet.utilidades.constantes.TextConstant;

public final class UtilidadesMail {

	private static class SMTPAuthenticator extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("info.vendenet@gmail.com",
					"B0rtxus!");
		}
	}

	private static Logger logger = Logger.getLogger(UtilidadesMail.class);
	boolean semaforoEnvio = false;


	public static boolean envioEnvioPendienteDesdeGmail(EnvioPendiente envio) {
		
		String d_email = "info.vendenet@gmail.com", d_uname = "info.vendenet@gmail.com", d_password = "B0rtxus!", d_host = "smtp.gmail.com", d_port = "465", // 465,587
		m_to = "testepscript@gmail.com", m_subject = "Testing", m_text = "Hey, this is the testing email.";

		Properties props = new Properties();
		props.put("mail.smtp.user", d_email);
		props.put("mail.smtp.host", d_host);
		props.put("mail.smtp.port", d_port);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", d_port);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		try{
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			BodyPart messageBodyPart = new MimeBodyPart();
			MimeMultipart multipart = new MimeMultipart();
			messageBodyPart.setContent(envio.getEmailBody(),"text/html");
			multipart.addBodyPart(messageBodyPart);
			MimeMessage message = new MimeMessage(session);
			StringTokenizer st=new StringTokenizer(envio.getEmailTo(),";");
			while(st.hasMoreTokens()){
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(st.nextToken()));
			}
			Address[] fromAddress = 
			      InternetAddress.parse(envio.getEmailFrom()); 
			message.addFrom(fromAddress);
			message.setSubject(envio.getEmailSubject());
			message.setContent(multipart);
			if(envio.getEmailReply()!=null){
				Address[] replyAddress = 
				      InternetAddress.parse(envio.getEmailReply());
				message.setReplyTo(replyAddress);
			}
			
			Transport transport = session.getTransport("smtp");
			transport.connect(d_host, 465, d_uname, d_password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		} finally {
		}
	}

//	 public static boolean envioEnvioPendienteDesdeVendenet(EnvioPendiente
//	 envio){
//	 try{
//	 Properties props = new Properties();
//	 props.setProperty("mail.smtp.host", "smtp.vendenet.net");
//	 props.setProperty("mail.smtp.auth", "true");
//	 // props.put("mail.debug", "true");
//	 props.setProperty("mail.smtp.starttls.enable", "false");
//	 props.setProperty("mail.smtp.port","25");
//	 props.setProperty("mail.smtp.user", "info@vendenet.net");
//	 props.put("mail.smtp.password", "Bortxus!");
//	 BodyPart messageBodyPart = new MimeBodyPart();
//	 MimeMultipart multipart = new MimeMultipart();
//	 messageBodyPart.setContent(envio.getEmailBody(),"text/html");
//	 multipart.addBodyPart(messageBodyPart);
//	 Session session = Session.getDefaultInstance(props);
//	 // session.setDebug(true);
//	 MimeMessage message = new MimeMessage(session);
//	 message.setFrom(new InternetAddress("info@vendenet.net"));
//	 StringTokenizer st=new StringTokenizer(envio.getEmailTo(),";");
//	 while(st.hasMoreTokens()){
//	 message.addRecipient(Message.RecipientType.TO, new
//	 InternetAddress(st.nextToken()));
//	 }
//	 Address[] fromAddress =
//	 InternetAddress.parse(envio.getEmailFrom());
//	 message.addFrom(fromAddress);
//	 message.setSubject(envio.getEmailSubject());
//	 message.setContent(multipart);
//	 if(envio.getEmailReply()!=null){
//	 Address[] replyAddress =
//	 InternetAddress.parse(envio.getEmailReply());
//	 message.setReplyTo(replyAddress);
//	 }
//	 Transport t = session.getTransport("smtp");
//	 t.connect("smtp.vendenet.net", "info@vendenet.net","B0rtxus!");
//	 t.sendMessage(message,message.getAllRecipients());
//	 t.close();
//	 return true;
//	 }catch(Exception e){
//	 System.out.println(e);
//	 return false;
//	 }
//	 }

	// public static boolean envioEnvioPendienteDesdeYahoo(EnvioPendiente
	// envio){
	// try{
	// Properties props = new Properties();
	// props.setProperty("mail.smtp.host", "smtp.mail.yahoo.com");
	// props.setProperty("mail.smtp.auth", "true");
	// props.put("mail.debug", "true");
	// props.setProperty("mail.smtp.starttls.enable", "true");
	// props.setProperty("mail.smtp.port","25");
	// props.setProperty("mail.smtp.user", "txeus@yahoo.es");
	// props.put("mail.smtp.password", "lu0la3ba");
	// BodyPart messageBodyPart = new MimeBodyPart();
	// MimeMultipart multipart = new MimeMultipart();
	// messageBodyPart.setContent(envio.getEmailBody(),"text/html");
	// multipart.addBodyPart(messageBodyPart);
	// Session session = Session.getDefaultInstance(props);
	// session.setDebug(true);
	// MimeMessage message = new MimeMessage(session);
	// message.setFrom(new InternetAddress("txeus@yahoo.es"));
	// message.addRecipient(Message.RecipientType.TO, new
	// InternetAddress(envio.getEmailTo()));
	// Address[] fromAddress =
	// InternetAddress.parse(envio.getEmailFrom());
	// message.addFrom(fromAddress);
	// message.setSubject(envio.getEmailSubject());
	// message.setContent(multipart);
	//
	// Transport t = session.getTransport("smtp");
	// t.connect("smtp.mail.yahoo.com", "txeus@yahoo.es","lu0la3ba");
	// t.sendMessage(message,message.getAllRecipients());
	// t.close();
	// return true;
	// }catch(Exception e){
	// System.out.println(e);
	// return false;
	// }
	// }

	public static boolean enviarEmailsPendientes() {
		org.hibernate.classic.Session session = HibernateUtil
				.getSessionFactory().openSession();
		try {
			org.hibernate.Session sesion = HibernateUtil.getSessionFactory()
					.openSession();
			Transaction transaccion = sesion.beginTransaction();
			Criteria crit = sesion.createCriteria(EnvioPendiente.class);
			// crit.add(Restrictions.isNotNull("anuncio"));
			crit.add(Restrictions.eq("enviado", false));
			List lstEnviosPendientes = crit.list();
			Iterator itEnviosPendientes = lstEnviosPendientes.iterator();
			while (itEnviosPendientes.hasNext()) {
				EnvioRealizado envioRealizado = new EnvioRealizado();
				EnvioPendiente envioTemp = (EnvioPendiente) itEnviosPendientes
						.next();
				envioRealizado.setEmailBody(envioTemp.getEmailBody());
				envioRealizado.setEmailFrom(envioTemp.getEmailFrom());
				envioRealizado.setEmailSubject(envioTemp.getEmailSubject());
				envioRealizado.setEmailTo(envioTemp.getEmailTo());
				envioRealizado.setFechaAlta(envioTemp.getFechaAlta());
				envioRealizado.setName(envioTemp.getName());
				if (envioEnvioPendienteDesdeGmail(envioTemp) == true) {
					envioRealizado.setEnvioOk(true);
					sesion.delete(envioTemp);
					// System.out.println("Envio OK");
				} else {
					envioRealizado.setEnvioOk(false);
				} // registrar que el envio no se ha realizado
				sesion.saveOrUpdate(envioRealizado);
			}
			transaccion.commit();
			sesion.close();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public static String cuerpoMailConsulta(String nombre, String email,
			String tfno, String pregunta, Anuncio anuncio) {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.EMAIL_DIV_CONSULTA_1);
		dev.append(TextConstant.EMAIL_DIV_CONSULTA_2);
		dev.append(nombre);
		dev.append(TextConstant.EMAIL_DIV_CONSULTA_3);
		dev.append(TextConstant.EMAIL_DIV_CONSULTA_4);
		dev.append(tfno);
		dev.append(TextConstant.EMAIL_DIV_CONSULTA_3);
		dev.append(TextConstant.EMAIL_DIV_CONSULTA_5);
		dev.append(convertirMailTo(email));
		dev.append(TextConstant.EMAIL_DIV_CONSULTA_3);
		dev.append(TextConstant.APERTURA_DIV).append(TextConstant.HTMLBLANK)
				.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.EMAIL_DIV_CONSULTA_6);
		dev.append(pregunta);
		dev.append(TextConstant.CIERRE_DIV);
		dev.append(TextConstant.EMAIL_DIV_CONSULTA_7);
		dev.append(anuncio.getId());
		dev.append(TextConstant.EMAIL_DIV_CONSULTA_8);
		dev.append(TextConstant._URL_VER_ANUNCIO).append(anuncio.getId());
		dev.append(TextConstant.CIERRE_A);
		dev.append(TextConstant.CIERRE_DIV);
		// TODO Auto-generated method stub
		return dev.toString();
	}

	private static String convertirMailTo(String email) {
		return new StringBuffer("<a href='mailto:").append(email).append("'>")
				.append(email).append("</a>").toString();
	}

	public static String cabeceraEmail(String cuerpoMailConsulta,
			Anuncio anuncio) {
		StringBuffer dev = new StringBuffer(TextConstant.PLANTILLA_EMAIL_PRE);
		dev.append(cuerpoMailConsulta);
		dev.append(meterDarseDeBaja(anuncio));
		dev.append(TextConstant.PLANTILLA_EMAIL_POST);
		return dev.toString();
	}

	public static String cabeceraEmailSinBaja(String cuerpoMailConsulta) {
		StringBuffer dev = new StringBuffer(TextConstant.PLANTILLA_EMAIL_PRE);
		dev.append(cuerpoMailConsulta);
		dev.append(TextConstant.PLANTILLA_EMAIL_POST);
		return dev.toString();
	}

	private static String meterDarseDeBaja(Anuncio anuncio) {
		StringBuffer dev = new StringBuffer();
		dev.append(
				"<div style='width: 100%; font-size: 0.8em; margin-top:2%;'>Para darte de baja pulsa <a href='")
				.append(TextConstant._URL_DARSE_DE_BAJA).append("&idCliente=")
				.append(anuncio.getCliente().getId()).append("&email=")
				.append(anuncio.getCliente().getEmail()).append("&pass=")
				.append(anuncio.getCliente().getPass());
		dev.append("'>aqu&iacute;</a></div>");
		return dev.toString();
	}

	public static String cuerpoMailAnuncioAceptado(Anuncio loadedAnuncio) {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.EMAIL_DIV_ANUNCIO_ACEPTADO_1);
		dev.append(TextConstant._URL_VER_ANUNCIO);
		dev.append(loadedAnuncio.getId());
		dev.append(TextConstant.EMAIL_DIV_ANUNCIO_ACEPTADO_2);
		dev.append(TextConstant._URL_VER_ANUNCIO).append(loadedAnuncio.getId());
		dev.append(TextConstant.CIERRE_A).append(TextConstant.CIERRE_DIV);
		// TODO Auto-generated method stub
		return dev.toString();
	}

	public static String cuerpoMailConfirmarOpinion(String codigo,
			String nombreCriticon) {
		StringBuffer dev = new StringBuffer();
		String[] literales = { nombreCriticon };
		dev.append(UtilidadesTexto.sustituirLiterales(
				TextConstant.EMAIL_DIV_CONFIRMAR_OPINION, literales));
		dev.append(TextConstant._URL_CONFIRMAR_OPINION);
		dev.append(codigo);
		dev.append(TextConstant.EMAIL_DIV_ANUNCIO_ACEPTADO_2);
		dev.append(TextConstant._URL_CONFIRMAR_OPINION).append(codigo);
		dev.append(TextConstant.CIERRE_A).append(TextConstant.CIERRE_DIV);
		// TODO Auto-generated method stub
		return dev.toString();
	}

	public static String cuerpoMailAnuncioDevuelto(String motivo,
			Anuncio loadedAnuncio) {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.EMAIL_DIV_ANUNCIO_DEVUELTO_1);
		dev.append(motivo);
		dev.append(TextConstant.EMAIL_DIV_ANUNCIO_DEVUELTO_2);
		dev.append("<a href='").append(TextConstant._URL_MODIFICAR_ANUNCIO)
				.append(loadedAnuncio.getId());
		dev.append("&email=").append(loadedAnuncio.getCliente().getEmail())
				.append("&pass=").append(loadedAnuncio.getCliente().getPass())
				.append("'>aqu&iacute;</a>");
		dev.append(TextConstant.CIERRE_DIV);
		// TODO Auto-generated method stub
		return dev.toString();
	}

	public static String cuerpoMailRecordatorioClave(Anuncio loadedAnuncio) {
		StringBuffer dev = new StringBuffer();
		dev.append(TextConstant.EMAIL_DIV_RECORDATORIO_1);
		dev.append(TextConstant.EMAIL_DIV_RECORDATORIO_2);
		dev.append(loadedAnuncio.getCliente().getPass());
		dev.append(TextConstant.EMAIL_DIV_RECORDATORIO_3);
		// TODO Auto-generated method stub
		return dev.toString();
	}

	public static void emailAnuncioNuevo(org.hibernate.Session session,
			Anuncio anuncio) throws ErrorVendenet {
		try {
			StringBuffer asunto = new StringBuffer(
					TextConstant.ANUNCIO_NOTIFICACION_NUEVO);
			if ((anuncio != null) && (anuncio.getName() != null)) {
				asunto.append(TextConstant.DOS_PUNTOS)
						.append(anuncio.getName());
			}
			EnvioPendiente envio = new EnvioPendiente();
			envio.setFechaAlta(new Date());
			envio.setEmailFrom(ConstantesVendenet.EMAIL_FROM);
			envio.setEmailTo(ConstantesVendenet.EMAIL_WEBMASTER);
			envio.setEmailSubject(asunto.toString());
			envio.setEnviado(false);
			envio.setName(TextConstant.BLANK);
			envio.setAnuncio(anuncio);
			envio.setEmailBody("");
			session.saveOrUpdate(envio);
		} catch (Exception eError) {
			logger.error("Error en UtilidadesMail emailAnuncioNuevo:" + eError);
			ErrorVendenet err = ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}

	public static void emailAnuncioBorrado(org.hibernate.Session session,
			Anuncio anuncio) throws ErrorVendenet {
		try {
			StringBuffer asunto = new StringBuffer(
					TextConstant.ANUNCIO_NOTIFICACION_BORRADO);
			if ((anuncio != null) && (anuncio.getName() != null)) {
				asunto.append(TextConstant.DOS_PUNTOS)
						.append(anuncio.getName());
			}
			EnvioPendiente envio = new EnvioPendiente();
			envio.setFechaAlta(new Date());
			envio.setEmailFrom(ConstantesVendenet.EMAIL_FROM);
			envio.setEmailTo(ConstantesVendenet.EMAIL_WEBMASTER);
			envio.setEmailSubject(asunto.toString());
			envio.setEnviado(false);
			envio.setName(TextConstant.BLANK);
			envio.setAnuncio(null);
			envio.setEmailBody("");
			session.saveOrUpdate(envio);
		} catch (Exception eError) {
			logger.error("Error en UtilidadesMail emailAnuncioBorrado:"
					+ eError);
			ErrorVendenet err = ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}

	public static void emailAnuncioModificado(org.hibernate.Session session,
			Anuncio anuncio) throws ErrorVendenet {
		try {
			StringBuffer asunto = new StringBuffer(
					TextConstant.ANUNCIO_NOTIFICACION_MODIFICADO);
			if ((anuncio != null) && (anuncio.getName() != null)) {
				asunto.append(TextConstant.DOS_PUNTOS)
						.append(anuncio.getName());
			}
			EnvioPendiente envio = new EnvioPendiente();
			envio.setFechaAlta(new Date());
			envio.setEmailFrom(ConstantesVendenet.EMAIL_FROM);
			envio.setEmailTo(ConstantesVendenet.EMAIL_WEBMASTER);
			envio.setEmailSubject(asunto.toString());
			envio.setEnviado(false);
			envio.setName(TextConstant.BLANK);
			envio.setAnuncio(anuncio);
			envio.setEmailBody("");
			session.saveOrUpdate(envio);
		} catch (Exception eError) {
			logger.error("Error en UtilidadesMail emailAnuncioModificado:"
					+ eError);
			ErrorVendenet err = ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}

	public static void devolverAnuncioPalabrota(org.hibernate.Session session,
			Anuncio anuncio) throws ErrorVendenet {
		try {
			// Antes de registrar otro envio de email comprobar que para este
			// anuncio no hay mas envios pendientes (caso que no deberia darse
			// pero por si acaso)
			if (anuncio != null && anuncio.getCliente() != null
					&& anuncio.getCliente().getEmail() != null) {
				EnvioPendiente envio = new EnvioPendiente();
				envio.setFechaAlta(new Date());
				envio.setEmailFrom(ConstantesVendenet.EMAIL_FROM);
				envio.setEmailTo(anuncio.getCliente().getEmail());
				envio.setEmailSubject(TextConstant.ANUNCIO_NO_ACEPTADO);
				envio.setEnviado(false);
				envio.setName(TextConstant.BLANK);
				envio.setAnuncio(anuncio);
				envio.setEmailBody(UtilidadesMail.cabeceraEmail(UtilidadesMail
						.cuerpoMailAnuncioDevuelto(
								TextConstant.PALABRAS_MAL_SONANTES, anuncio),
						anuncio));
				session.saveOrUpdate(envio);
				anuncio.setPublicado(false);
				anuncio.setRevisado(true);
				anuncio.setFechaCaducidad(UtilidadesFecha
						.devolverFechaCaducidadDevuelto(new Date()));// Actualizamos
																		// la
																		// fecha
																		// de
																		// caducidad
																		// a la
																		// fecha
																		// de
																		// plazo
																		// para
																		// corregir
																		// anuncio
				session.saveOrUpdate(anuncio);
			}
		} catch (Exception eError) {
			logger.error("Error en UtilidadesMail devolverAnuncioPalabrota:"
					+ eError);
			ErrorVendenet err = ErrorVendenet.tratarErrorEx(eError);
			throw err;
		}
	}

}