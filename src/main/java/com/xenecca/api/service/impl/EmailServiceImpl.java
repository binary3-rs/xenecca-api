package com.xenecca.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.xenecca.api.service.EmailService;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender _emailSender;

	@Override
	public void sendEmail(String email, String message, String subject) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("xenecca@binary3.rs");
		mailMessage.setTo(email);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		getEmailSender().send(mailMessage);

	}

}
