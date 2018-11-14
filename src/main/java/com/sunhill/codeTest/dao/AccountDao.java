package com.sunhill.codeTest.dao;

import com.sunhill.codeTest.entity.Account;

public interface AccountDao {

	Account getById(final int id);
	void add(final Account account);
}
