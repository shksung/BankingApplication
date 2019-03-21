package com.project.users;

public class User {
	private String username;
	private String password;
	private String name;
	private String type;
	
	public User(String username, String password, String name, String type) {
		super();
		this.username = username;
		this.password = password;
		this.name= name;
		this.type= type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", type=" + type + "]";
	}
	
	
}
