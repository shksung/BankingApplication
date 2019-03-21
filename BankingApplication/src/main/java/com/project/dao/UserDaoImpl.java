package com.project.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.users.User;

public class UserDaoImpl implements UserDao {
	private static String url="jdbc:oracle:thin:@sql0304db.clxhqtzlud4f.us-east-2.rds.amazonaws.com:1521:SQL0304";
	private static String username= "ksung12";
	private static String password= "A070694";


	@Override
	public int updateUser(User p) {
	
		return 0;
	}

	@Override
	public int deleteUser(User p) {
		try(Connection conn = DriverManager.getConnection(url, username, password)){

			PreparedStatement ps = conn.prepareStatement("DELETE FROM Users WHERE username= ?");
			ps.setString(1, p.getUsername());
			return ps.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public int deleteUser(String u) { //for customers and admins
		try(Connection conn = DriverManager.getConnection(url, username, password)){

			PreparedStatement ps = conn.prepareStatement("DELETE FROM Users WHERE username= ?");
			ps.setString(1, u);
			return ps.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int insertUser(User p) {
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("INSERT INTO users VALUES(?,?,?,?)");
			ps.setString(1, p.getUsername());
			ps.setString(2, p.getPassword());
			ps.setString(3, p.getName());
			ps.setString(4, p.getType());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public User selectUser(String userName, String passWord) {
		User user= null;
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Users WHERE username=? AND user_pass=? ");
			ps.setString(1,userName);
			ps.setString(2,passWord);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user= new User(rs.getString("username"), rs.getString("user_pass"), rs.getString("full_name"), rs.getString("user_type")); // from the result set, get the name and type and store it into the pet object
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public List<User> viewAllCustomers() {
		List<User> users = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT * FROM Users WHERE user_type= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "Customer");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				users.add(new User(rs.getString("username"),rs.getString("user_pass"),rs.getString("full_name"),rs.getString("user_type")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println(users);
		return users;
	}


}
