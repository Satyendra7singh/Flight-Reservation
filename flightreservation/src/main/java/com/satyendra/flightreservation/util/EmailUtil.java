package com.satyendra.flightreservation.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.satyendra.flightreservation.services.ReservationService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
	
	@Value("${com.satyendra.flightreservation.itinerary.email.body}")
	private String EMAIL_BODY;
	@Value("${com.satyendra.flightreservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT;

	private static final Logger LOGGER=LoggerFactory.getLogger(EmailUtil.class);

	@Autowired
	private JavaMailSender sender;
	
	public void sendItinerary(String toAddress,String filePath) {
		LOGGER.info("Inside sendItenerary()");
		MimeMessage message=sender.createMimeMessage();
		
		try {
			MimeMessageHelper messageHelper= new MimeMessageHelper(message,true);;
			messageHelper.setTo(toAddress);
			messageHelper.setSubject(EMAIL_BODY);
			messageHelper.setText(EMAIL_SUBJECT);
			messageHelper.addAttachment("Itinerary",new File(filePath));
			sender.send(message);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Enception while sending message");
			e.printStackTrace();
		}
		
	}
}
