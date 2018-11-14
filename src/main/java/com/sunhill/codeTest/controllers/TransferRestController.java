package com.sunhill.codeTest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sunhill.codeTest.entity.Checkings;
import com.sunhill.codeTest.service.CheckingsService;

@RestController
public class TransferRestController {

	@Autowired
	CheckingsService checkingsService;
	
	
	@RequestMapping(value="/transfer/{sourceId}/{targetId}/{amount}", method = RequestMethod.GET)
	public Checkings transfer( @PathVariable("sourceId") int sourceId,
			 @PathVariable("targetId") int targetId,
			 @PathVariable("amount") float amount) {
		
		try {
			setUp();
			final Checkings source = checkingsService.getById(sourceId);
			final Checkings target = checkingsService.getById(targetId);
			
			checkingsService.transfer(source, target, amount);
			
			return target;
		} catch (Exception e) {
			e.printStackTrace();
			return new Checkings();
		}
	}
	
	
	private void setUp() {
		Checkings source = new Checkings();
		
		source.setId(1);
		source.setOwner("Test source account");
		source.setBalance(100.0f);
		source.setLimit(30.0f);
		
		checkingsService.add(source);
		
		Checkings target = new Checkings();
		
		target.setId(2);
		target.setOwner("Test source account");
		target.setBalance(0.0f);
		target.setLimit(50.0f);
		
		checkingsService.add(target);
	}
}
