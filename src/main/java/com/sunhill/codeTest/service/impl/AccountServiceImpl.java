package com.sunhill.codeTest.service.impl;

import com.sunhill.codeTest.entity.Account;
import com.sunhill.codeTest.service.AccountService;

public abstract class AccountServiceImpl implements AccountService {
	
	public synchronized Account deposit(Account account, float amount) {		
		account.setBalance(account.getBalance() + amount);
		
		return account;
	}


}
