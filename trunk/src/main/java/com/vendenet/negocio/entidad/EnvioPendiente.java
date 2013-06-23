package com.vendenet.negocio.entidad;

import java.util.Date;

public class EnvioPendiente extends BasicBean {

	private String emailFrom;
	private String emailTo;
	private String emailSubject;
	private String emailBody;
	private String emailReply;
	private Date fechaAlta;
	private boolean enviado;
	private Anuncio anuncio;
	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	public String getEmailTo() {
		return emailTo;
	}
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getEmailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public boolean isEnviado() {
		return enviado;
	}
	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}
	public Anuncio getAnuncio() {
		return anuncio;
	}
	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}
	public String getEmailReply() {
		return emailReply;
	}
	public void setEmailReply(String emailReply) {
		this.emailReply = emailReply;
	}
	
	
	
}
