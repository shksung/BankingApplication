package com.project.dao;

import java.util.List;

import com.project.account.Account;
import com.project.users.User;

public interface AccountDao {
	public int insertAccount(Account P);
	public Account selectAccount(String userName,String password, String name, String type);
	public float selectAccount(String userName);
	public float selectAccount2(String userName); //doesn't print out a message
	public int updateAccountAmount(Account p);
	public int updateAccountAmount(String u,float p);
	public int updateAccountStatus(String u, String s);
	public int deleteAccount(Account p);
	public int deleteAccount(String u); //for customer and admin
}
