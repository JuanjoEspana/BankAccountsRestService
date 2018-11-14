package com.sunhill.codeTest.service;

import com.sunhill.codeTest.entity.Checkings;

public interface CheckingsService extends AccountService{

	void add(final Checkings acc);
	Checkings getById(final int id);
	boolean transfer(final Checkings source, final Checkings target, final float amount);
}
