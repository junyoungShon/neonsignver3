package org.cobro.neonsign.utility;

import java.io.InputStream;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("neonsignMailSender")
public class NeonsignEmailSenderAPI{
	private JavaMailSender neonsignMailSender; // MailSender interface defines a strategy
										// for sending simple mails
	public JavaMailSender getNeonsignMailSender() {
		return neonsignMailSender;
	}
	public void setNeonsignMailSender(JavaMailSender neonsignMailSender) {
		this.neonsignMailSender = neonsignMailSender;
	}
	public void ifyReadyToSendEmail(String toAddress, String fromAddress, String subject, String msgBody) {
		MimeMessage message = neonsignMailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setTo(toAddress);
			messageHelper.setText(msgBody,true);
			messageHelper.setFrom(fromAddress);
			messageHelper.setSubject(subject);	// 메일제목은 생략이 가능하다
			neonsignMailSender.send(message);
		} catch(Exception e){
			System.out.println(e);
		}
	}
}
