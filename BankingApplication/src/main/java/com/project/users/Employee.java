package com.project.users;

import java.util.List;

import com.project.dao.AccountDaoImpl;
import com.project.dao.UserDaoImpl;

public class Employee extends User {

	public Employee(String username, String password, String name, String type) {
		super(username, password, name, type);
	}
	
	public void viewCustomers(UserDaoImpl userimp) {
			userimp.viewAllCustomers();
		}
	public float accountRetrieval(AccountDaoImpl accountimp,String userName) {
		float balance = accountimp.selectAccount(userName);
		return balance;
	}
	
	public void changeStatus(AccountDaoImpl imp,String u, String s) {
		imp.updateAccountStatus(u, s);
	}
}
