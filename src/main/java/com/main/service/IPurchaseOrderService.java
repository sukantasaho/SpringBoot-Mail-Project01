package com.main.service;

import jakarta.mail.MessagingException;

public interface IPurchaseOrderService 
{
	public String purchase(String[] items, double[] prices, String[] toEmails) throws MessagingException;
}
