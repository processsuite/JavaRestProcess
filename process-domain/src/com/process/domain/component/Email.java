package com.process.domain.component;

public class Email {
	private String smtp;// props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	private String remitente;// props.put("mail.smtp.user", remitente);
	private String clave;// props.put("mail.smtp.clave", clave);    //La clave de la cuenta
	private String authUsuClave;// props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	private String startTls;// props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	private String port;// props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
	private String asunto;
	private String cuerpo;
	private String destinatario;
	
	public String getSmtp() {
		return smtp;
	}
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getAuthUsuClave() {
		return authUsuClave;
	}
	public void setAuthUsuClave(String authUsuClave) {
		this.authUsuClave = authUsuClave;
	}
	public String getStartTls() {
		return startTls;
	}
	public void setStartTls(String startTls) {
		this.startTls = startTls;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	
	
}
