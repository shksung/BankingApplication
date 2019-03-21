package com.test;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

import com.project.account.Account;

public class Testing {

	Account tester1 = new Account("ksung12", "123456", "Kevin Sung", "Employee", 45, "Approved");
	
	
	@BeforeClass
	public static void methodCalledBeforeAllTests() {
		System.out.println("Welcome to Junit");
	}
	
//	@Test
//	public float deposit(float i) {
//		assertEquals("Should return right amount",65 , tester.deposit(20));
//		return i;
//	}
	
	
}
