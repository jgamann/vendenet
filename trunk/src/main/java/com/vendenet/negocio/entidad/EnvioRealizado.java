package com.vendenet.negocio.entidad;

import java.util.Date;

public class EnvioRealizado extends BasicBean {

	private String emailFrom;
	private String emailTo;
	private String emailSubject;
	private String emailBody;
	private Date fechaAlta;	
	private boolean envioOk;
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
	public boolean isEnvioOk() {
		return envioOk;
	}
	public void setEnvioOk(boolean envioOk) {
		this.envioOk = envioOk;
	}
	
	
	
}
