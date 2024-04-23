package com.main.service;
 
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service("pService")
public class PurchaseOrderServiceImp implements IPurchaseOrderService 
{
	 @Autowired
     private JavaMailSender sender;
	 
	 @Value("${spring.mail.username}")
	 private String fromEmail;
	 
	public String purchase(String[] items, double[] prices, String[] toEmails) throws MessagingException
	{
		double totalAmt = 0.0;
		for(double d : prices)
		{
			totalAmt = totalAmt+d;
		}
		String msg = Arrays.toString(items)+" are purchased having prices"+Arrays.toString(prices)+" having the bill amount-"+totalAmt;
		
		 String statusMsg = sentMail(msg, toEmails);
		return msg+"........"+statusMsg;
	}
	private String sentMail(String msg, String[] toEmails) throws MessagingException
	{
		//create the email message
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(fromEmail);
		helper.setTo(toEmails);
		helper.setSubject("Open it and know it");
		helper.setSentDate(new Date());
		helper.setText(msg);
		helper.addAttachment("batch1.jpg", new ClassPathResource("batch1.jpg"));
		sender.send(message);
		
		return "mail sent with an attachment";
	}
}
