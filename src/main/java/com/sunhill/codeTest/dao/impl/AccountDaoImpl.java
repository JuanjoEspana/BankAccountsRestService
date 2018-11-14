package com.sunhill.codeTest.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.sunhill.codeTest.dao.AccountDao;
import com.sunhill.codeTest.entity.Account;

@Repository
public class AccountDaoImpl implements AccountDao {

	private final HashMap<Integer, Account> cache = new HashMap<Integer, Account>();
	
	public Account getById(int id) {
		return cache.get(id);
	}

	public void add(Account account) {
		cache.put(account.getId(), account);
	}

}
