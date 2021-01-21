package br.com.cursomc.service;

import org.springframework.mail.SimpleMailMessage;

import br.com.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
