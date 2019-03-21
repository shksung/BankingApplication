package com.project.account;

import com.project.users.Customer;

public class Account extends Customer {
	
	private float balance;
	private String status;
	
	public Account(String username, String password, String name, String type, float balance, String status) {
		super(username, password, name, type);
		this.balance = balance;
		this.status = status;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", status=" + status + "]";
	}
	
	public void deposit(float i) {
		this.balance += i;
	}

	public void withdraw(float i) {
		if (this.balance - i <0) {
			System.out.println("Insufficient Funds");
		} else {
			this.balance -= i;
		}
		
	}
	

	
}
