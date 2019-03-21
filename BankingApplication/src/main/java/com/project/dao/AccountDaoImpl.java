package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.project.account.Account;
import com.project.users.User;

public class AccountDaoImpl implements AccountDao {
	private static String url="jdbc:oracle:thin:@sql0304db.clxhqtzlud4f.us-east-2.rds.amazonaws.com:1521:SQL0304";
	private static String username= "ksung12";
	private static String password= "A070694";

	@Override
	public int insertAccount(Account p) {
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("INSERT INTO accounts VALUES(?,?,?)");
			ps.setString(1, p.getUsername());
			ps.setFloat(2, 0);
			ps.setString(3, "Waiting");
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Account selectAccount(String userName, String passWord, String name, String type) {
		Account account= null;
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE username=?");
			ps.setString(1,userName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				account= new Account(userName,  passWord, name, type, rs.getFloat("amount"), rs.getString("status")); // from the result set, get the name and type and store it into the pet object
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}

	@Override
	public int updateAccountAmount(Account p) {
		int res=0;
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			String sql = "UPDATE Accounts SET amount=? WHERE username=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, p.getBalance());
			ps.setString(2,  p.getUsername());
			res = ps.executeUpdate();
		
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	@Override
	public int updateAccountAmount(String u, float p) {
		int res=0;
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			String sql = "UPDATE Accounts SET amount=? WHERE username=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, p);
			ps.setString(2, u);
			res = ps.executeUpdate();
		
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
		
	}

	@Override
	public int deleteAccount(Account p) {
		try(Connection conn = DriverManager.getConnection(url, username, password)){

			PreparedStatement ps = conn.prepareStatement("DELETE FROM Accounts WHERE username= ?");
			ps.setString(1, p.getUsername());
			return ps.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	

	@Override
	public int deleteAccount(String u) {
		try(Connection conn = DriverManager.getConnection(url, username, password)){

			PreparedStatement ps = conn.prepareStatement("DELETE FROM Accounts WHERE username= ?");
			ps.setString(1, u);
			return ps.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}


	@Override
	public int updateAccountStatus(String u, String s) {
		int res=0;
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			String sql = "UPDATE Accounts SET status=? WHERE username=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, u);
			res = ps.executeUpdate();
		
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public float selectAccount(String userName) {
		String user = null;
		float amount = 0;
		String stat = null;
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE username=?");
			ps.setString(1,userName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user= rs.getString("username");
				amount = rs.getFloat("amount");
				stat = rs.getString("status");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("User: " + user);
		System.out.println("Amount : " + amount);
		System.out.println("Status : " + stat);
		
		return amount;
	}

	@Override
	public float selectAccount2(String userName) {
		String user = null;
		float amount = 0;
		String stat = null;
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE username=?");
			ps.setString(1,userName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user= rs.getString("username");
				amount = rs.getFloat("amount");
				stat = rs.getString("status");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return amount;
		
	}





}
