package com.project.users;

public class Admin extends Employee {
	public Admin(String username, String password, String type, String name) {
		super(username, password, type, name);
	}
	
	public String viewEmployee(String name) {
	 return name;
	}
	
	
	public float deposit(float i,float balance) {
		return balance += i;
	}

	public float withdraw(float i, float balance) {
		if (balance - i <0) {
			System.out.println("Insufficient Funds");
			return 0;
		} else {
			return balance -= i;
		}
		
	}

}
