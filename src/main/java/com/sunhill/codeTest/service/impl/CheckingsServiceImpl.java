package com.sunhill.codeTest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunhill.codeTest.dao.AccountDao;
import com.sunhill.codeTest.entity.Account;
import com.sunhill.codeTest.entity.Checkings;
import com.sunhill.codeTest.service.CheckingsService;
import com.sunhill.codeTest.service.exception.CodeTestServiceException;

@Service
public class CheckingsServiceImpl extends AccountServiceImpl implements CheckingsService {
	
	@Autowired
	AccountDao accountDao;

	public synchronized Account withdrawal(Account account, float amount) throws CodeTestServiceException {
		final float result = account.getBalance() - amount;
		
		if(result < 0 && ((Checkings)account).getLimit() <= Math.abs(result)) {
			throw new CodeTestServiceException("Checking account balance for owner " 
					+ account.getOwner()
					+ "can not go under its overdraft limit");
		} else {
			account.setBalance(result);
			return account;
		}		

	}

	public synchronized boolean transfer(final Checkings source, final Checkings target, 
			final float amount) {
		try {
			this.withdrawal(source, amount);
			super.deposit(target, amount);
		} catch (CodeTestServiceException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void add(Checkings acc) {
		accountDao.add(acc);
	}

	public Checkings getById(int id) {
		return (Checkings) accountDao.getById(id);
	}

}
