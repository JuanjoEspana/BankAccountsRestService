package com.sunhill.codeTest.service;

import com.sunhill.codeTest.entity.Saving;

public interface SavingsService extends AccountService{
	
	float calculateInvestment(final Saving account);
	Saving payInterests(final Saving account);
}
