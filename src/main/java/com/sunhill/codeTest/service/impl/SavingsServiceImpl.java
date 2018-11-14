package com.sunhill.codeTest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunhill.codeTest.entity.Account;
import com.sunhill.codeTest.entity.Saving;
import com.sunhill.codeTest.service.AccountService;
import com.sunhill.codeTest.service.SavingsService;
import com.sunhill.codeTest.service.exception.CodeTestServiceException;

@Service
public class SavingsServiceImpl extends AccountServiceImpl implements SavingsService {

	@Autowired
	AccountService accountService;
	
	public synchronized float calculateInvestment(final Saving account) {
		return (account.getBalance() * account.getInterestRate()) / 100;
	}

	public Saving payInterests(Saving account) {
		final float amount = calculateInvestment(account);
		return (Saving) accountService.deposit(account, amount);
	}

	public Account deposit(Account account, float amount) {
		return super.deposit(account, amount);
	}

	public synchronized Account withdrawal(Account account, float amount) throws CodeTestServiceException {
		
		if(amount > account.getBalance()) {
			throw new CodeTestServiceException("Owner " + account.getOwner() + " can not " 
					+ "withdrawal " + amount + ", which is more than account balance: " 
					+ account.getBalance());
		}
		
		account.setBalance(account.getBalance() - amount);
		
		return account;
	}

}
