package com.sunhill.codeTest.service;

import com.sunhill.codeTest.entity.Account;
import com.sunhill.codeTest.service.exception.CodeTestServiceException;

public interface AccountService {

	Account deposit(final Account account, final float amount);
	Account withdrawal(final Account account, final float amount) throws CodeTestServiceException;
	
}
