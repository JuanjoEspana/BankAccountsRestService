package com.sunhill.codeTest.service.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sunhill.codeTest.CodeTestApplication;
import com.sunhill.codeTest.entity.Account;
import com.sunhill.codeTest.entity.Saving;
import com.sunhill.codeTest.service.SavingsService;

@RunWith(SpringRunner.class)
@SpringBootTest(		  
		  classes = CodeTestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceImplTest {

	static Saving account;
	
	@Autowired
	SavingsService savingsService;
	
	@BeforeClass
	public static void setUp() {
		account = new Saving();
		
		account.setOwner("Test");
		account.setBalance(100.0f);
	}
	
	@Test
	public void testDeposit() {
		final Account result = savingsService.deposit(account, 10);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(110.0f, result.getBalance(), 0.0f);
	}

	@Test
	public void testWithdrawal() {
		final Account result = savingsService.withdrawal(account, 20);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(90.0f, result.getBalance(), 0.0f);
	}

}
