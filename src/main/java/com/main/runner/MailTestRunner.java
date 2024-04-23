package com.main.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.main.service.IPurchaseOrderService;

@Component
public class MailTestRunner implements CommandLineRunner {

	@Autowired
	private IPurchaseOrderService service;
	
	@Override
	public void run(String... args) throws Exception {
		 
         String resMsg =  service.purchase(new String[] {"shirt","bag","fruit"},
          new double[] {200d,390d,145d},
          new String[] {"ssukanta520@gmail.com","ssukanta531@gmail.com","suksahoo1993@gmail.com"});
	   System.out.println(resMsg);
	}

}
