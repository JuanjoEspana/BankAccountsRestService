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
import com.sunhill.codeTest.entity.Checkings;
import com.sunhill.codeTest.service.CheckingsService;
import com.sunhill.codeTest.service.exception.CodeTestServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest(		  
		  classes = CodeTestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CheckingsServiceImplTest {
	
	static Checkings source;
	static Checkings target;
	
	@Autowired
	CheckingsService checkingsService;
	
	@BeforeClass
	public static void setUp() {
		source = new Checkings();
		
		source.setOwner("Test source account");
		source.setBalance(100.0f);
		source.setLimit(30.0f);
		
		target = new Checkings();
		target.setOwner("Test source account");
		target.setBalance(0.0f);
		target.setLimit(50.0f);
	}

	@Test
	public void testTransfer() {
		final boolean result = checkingsService.transfer(source, target, 1);
		
		Assert.assertTrue(result);
		Assert.assertEquals(1f, target.getBalance(), 0f);
		Assert.assertEquals(99f, source.getBalance(), 0f);
	}
	
	@Test
	public void testTransferOverLimit() {		
		final boolean result = checkingsService.transfer(source, target, 4000f);
		Assert.assertFalse(result);
		
	}
	@Test
	public void testWithdrawal() {
		final Account result = checkingsService.withdrawal(source, 20f);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(79.0f, result.getBalance(), 0f);
	}

	@Test
	public void testWithdrawalOverLimit() {
		final Account result = checkingsService.withdrawal(target, 20f);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(-19.0f, result.getBalance(), 0f);
	}
	
	@Test
	public void testWithdrawalReachingLimit() {
		try {
			checkingsService.withdrawal(target, 40f);
			Assert.fail("Exception must be thrown");
		} catch ( RuntimeException e) {
			Assert.assertNotNull(e);
			Assert.assertTrue(e instanceof CodeTestServiceException);
			final String msg = ((CodeTestServiceException) e).getMessage();
			Assert.assertTrue(msg.contains("can not go under its overdraft limit"));
		}
	}
}
