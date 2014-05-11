package com.knowledgehut.crm.events.mail;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("mailApi")
public class MailApi {

	private static final Logger log = LoggerFactory.getLogger(MailApi.class);

	private Session session;

	@Value("${crm.email.host}")
	private String smtpServer;

	String getJndiName(){
		return "Mail/ISM";
	}

	String getSmtpServer(){
		return smtpServer;
	}

	/**
	 * Get mail session
	 * 
	 * @return
	 */
	public Session getSession() {
		if (session != null)
			return session;

		try {
			InitialContext ctx = new InitialContext();
			session = (Session) ctx.lookup(getJndiName());
			return session;
		} catch (Exception e) {
			log.warn("Unable to lookup mail session from JNDI lookup[{}]", e.getMessage());
		}

		if (session == null) {
			Properties props = new Properties();
			log.warn("SMTP Server: {}", getSmtpServer());
			props.put("mail.smtp.host", getSmtpServer());
			//props.put("mail.debug", "true");
			session = Session.getInstance(props, null);
			log.info("Obatained Session from local configuration.");
		}
		return session;
	}

	/**
	 * To make a one connection for transport for bulk mail sending
	 * @return
	 * @throws com.hs18.oms.ctp.exceptions.CustomerCommunicationException
	 */
	public Transport openMailTransport(Session session) {
		try {
			Transport transport = session.getTransport("smtp");
			transport.connect();
			return transport;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MailApiException("No such provider smtp");
		}
	}

	/**
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param msg
	 * @throws com.hs18.oms.ctp.exceptions.CustomerCommunicationException
	 */
	private void setMessageRecepients(String to, String cc, String bcc,
			Message msg) {
		try {
			InternetAddress[] toAddrs = null, ccAddrs = null, bccAddrs = null;
			toAddrs = InternetAddress.parse(to, false);

			if (cc != null) {
				ccAddrs = InternetAddress.parse(cc, false);
			}
			if (bcc != null) {
				bccAddrs = InternetAddress.parse(bcc, false);
			}
			msg.setRecipients(Message.RecipientType.TO, toAddrs);
			msg.setRecipients(Message.RecipientType.CC, ccAddrs);
			msg.setRecipients(Message.RecipientType.BCC, bccAddrs);
		} catch (Exception e) {
			throw new MailApiException(
					"Error in setting recepients : Address parsing");
		}
	}

	/**
	 * @param from
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param body
	 * @param subject
	 * @throws com.hs18.oms.ctp.exceptions.CustomerCommunicationException
	 */
	public void sendMail(String from, String to, String cc, String bcc, Object body, String subject) {
		try{
			Session session = getSession();
			Transport transport = openMailTransport(session);
			Message msg = new MimeMessage(session);
			if (to != null) {
				setMessageRecepients(to, cc, bcc, msg);
			} else {
				throw new MailApiException("No \"To\" address specified");
			}
			if (subject != null) {
				msg.setSubject(subject);
			} else {
				subject = "Subject not specified";
			}

			msg.setFrom(new InternetAddress(from));
			
			if (body != null){
				if(body instanceof Multipart){
					msg.setContent((Multipart)body);
				}
				else  {
					msg.setContent(body, "text/html");
				}
			}

			transport.sendMessage(msg,msg.getAllRecipients());
		} 
		catch (Exception e) {
			log.error("Error in sending mail. ToEmail: " + to + "; cc:" + cc + "; bcc: " + bcc + ". Exception: {}", e);
			throw new MailApiException("Error in sending mail. ToEmail: " + to + "; cc:" + cc + "; bcc: " + bcc + "; Msg: " + e.getMessage());
		}	
	}
}