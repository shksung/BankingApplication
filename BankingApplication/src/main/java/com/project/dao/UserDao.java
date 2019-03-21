package com.project.dao;

import java.util.List;

import com.project.users.User;

public interface UserDao {
	public int insertUser(User P);
	public User selectUser(String username, String password);
	public int updateUser(User p);
	public int deleteUser(User p);
	public int deleteUser(String u);
	public List<User> viewAllCustomers();
	
}
