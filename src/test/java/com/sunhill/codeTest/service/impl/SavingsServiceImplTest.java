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
import com.sunhill.codeTest.service.exception.CodeTestServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest(		  
		  classes = CodeTestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SavingsServiceImplTest {

	static Saving account;
	
	@Autowired
	SavingsService savingsService;
			
	@BeforeClass
	public static void setUp() {
		account = new Saving();
		
		account.setOwner("Test");
		account.setBalance(100.0f);
		account.setInterestRate(10.0f);
	}
	
	@Test
	public void testCalculateInvestment() {
		final float currentInvestment = savingsService.calculateInvestment(account);
		
		Assert.assertNotEquals(0.0f, currentInvestment, 0f);
		Assert.assertEquals(10.0f, currentInvestment, 0f);
	}

	@Test
	public void testPayInterests() {
		final Saving result = savingsService.payInterests(account);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(110.0f, result.getBalance(), 0f);
	}

	@Test
	public void testSaveDeposit() {
		final Account result = savingsService.deposit(account, 10);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(120.0f, result.getBalance(), 0.0f);
	}

	@Test
	public void testWithdrawal() {
		final Account result = savingsService.withdrawal(account, 20);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(100.0f, result.getBalance(), 0.0f);
	}
	
	@Test
	public void testWithdrawalOverBalance() {
		try {
			savingsService.withdrawal(account, 20000);
			Assert.fail("Can not reach this part, exception must be thrown.");
		} catch ( RuntimeException e ) {
			Assert.assertNotNull(e);
			Assert.assertTrue(e instanceof CodeTestServiceException);
			Assert.assertNotNull( ((CodeTestServiceException) e).getMessage());
			
		}		
	}
}
