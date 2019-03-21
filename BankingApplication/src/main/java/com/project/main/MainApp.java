package com.project.main;


import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


import com.project.account.Account;
import com.project.dao.AccountDaoImpl;
import com.project.dao.UserDaoImpl;
import com.project.users.Admin;
import com.project.users.Employee;
import com.project.users.Customer;
import com.project.users.User;

import org.apache.log4j.Logger;


public class MainApp {
	
//	final static Logger logger = Logger.getLogger(MainApp.class);
	
	public static void main(String[] args) {	
		
	    UserDaoImpl imp = new UserDaoImpl();
	    AccountDaoImpl accountdaoimpl = new AccountDaoImpl();
	    start(imp, accountdaoimpl);
	    
	}
	
	public static void start(UserDaoImpl imp, AccountDaoImpl accountdaoimpl) {
		Scanner start = new Scanner(System.in); 
		System.out.println("register,login or terminate?");
		String startAction = start.nextLine(); 
		
		switch(startAction) {
			case "register":
//				String typeforReg = "customer";
				Scanner regname = new Scanner(System.in); //user inputs for name, username, password
				System.out.println("Full Name?");
				String rgnme = regname.nextLine(); 
				
				Scanner register = new Scanner(System.in); 
				System.out.println("Desired Username?");
				String reg = register.nextLine(); 
				
				Scanner pass = new Scanner(System.in); 
				System.out.println("Desired Password?");
				String ps = pass.nextLine();
				
				register(reg, ps, rgnme, "Customer", imp, accountdaoimpl);
				System.out.println("Your Account has been registered. Please sign back in");
				start(imp, accountdaoimpl);	
				break;
			case "terminate":
				System.exit(0);
			
			case "login" :
				break;
			default:
				System.out.println("Invalid");
				start(imp, accountdaoimpl);				
		}
		
		 User user= null;
		 Account account= null;
		
		 Scanner myObj = new Scanner(System.in); 
		 System.out.println("Enter username");
		 String userInput = myObj.nextLine(); // username  
			    
		 Scanner myObj2 = new Scanner(System.in);  // Create a Scanner object
		 System.out.println("Enter password");
		 String passwordInput = myObj2.nextLine();
		 
		 user = imp.selectUser(userInput, passwordInput);
		 
		 if(user == null) {
			 System.out.println("Invalid Credentials");
			 start(imp, accountdaoimpl);
		 }
		 
		 switch(user.getType()) {
		 	case "Customer": 
		 		account= accountdaoimpl.selectAccount(user.getUsername(), user.getPassword(), user.getName(), user.getType());
		 		customerFunction(user,account,imp,accountdaoimpl); //calls the customer methods
		 		start(imp, accountdaoimpl);
		 		break;
		 	case "Employee":
		 		employeeFunction(user, imp, accountdaoimpl);
		 		start(imp, accountdaoimpl);
		 		break;
		 	case "Admin":
		 		adminFunction(user,imp,accountdaoimpl);
		 		start(imp, accountdaoimpl);
		 }
	}
	

	public static void register(String username, String password, String name, String type, UserDaoImpl imp, AccountDaoImpl imp2) {
		User user= new User(username, password, name, type);
		Account account = new Account(username, password, name, type, 0, "waiting"); //default values are 0 and waiting for account creation
		imp.insertUser(user); //inserts into the user database
		imp2.insertAccount(account);
		
	}
	
	public static void customerFunction(User user, Account account, UserDaoImpl imp, AccountDaoImpl imp2) {
		System.out.println("Hello " + account.getName() + "!");
    	Scanner myObj4 = new Scanner(System.in);  // Create a Scanner object
   	    System.out.println("What would you like to do? (deposit, withdraw, transfer, delete, terminate)");
   	    String action  = myObj4.nextLine(); 
   	    switch (action) {
   	    	case "deposit":
   	    		switch(account.getStatus()) {
   	    			case "approved":
   	    				break;
   	    			default: 
   	    				System.out.println("Your Account needs to be approved first");
   	    				customerFunction(user,account,imp,imp2);
   	    		}
   	    		Scanner myObj5 = new Scanner(System.in);
   	    		System.out.println("How much would you like to deposit?"); 
   	    		float amount = myObj5.nextFloat(); //amount to be deposited
   	    		account.deposit(amount);
   	    		System.out.println("Your new balance is: " + account.getBalance());
   	    		imp2.updateAccountAmount(account); 
//   	    		logger.info("User Deposited");
   	    		break;
   	    		
   	    	case "withdraw":
   	    		switch(account.getStatus()) {
	    			case "approved":
	    				break;
	    			default: 
	    				System.out.println("Your Account needs to be approved first");
	    				customerFunction(user,account,imp,imp2);
	    		}
   	    		Scanner myObj6 = new Scanner(System.in);
   	    		System.out.println("How much would you like to withdraw?");
   	    		int amount2 = myObj6.nextInt();
   	    		account.withdraw(amount2);
   	    		System.out.println("Your balance is:" + account.getBalance());
   	    		imp2.updateAccountAmount(account);
//   	    		logger.info("User Withdrew");
   	    		break;	
   	    	case "transfer":
   	    		switch(account.getStatus()) {
	    			case "approved":
	    				break;
	    			default: 
	    				System.out.println("Your Account needs to be approved first");
	    				customerFunction(user,account,imp,imp2);
	    		}
   	    		float balanceBeforeTransfer;
   	    		float balanceAfterTransfer;
   	    		float newUserBalance;
   	    		
   	    		Scanner transfer = new Scanner(System.in);
	    		System.out.println("What is the username of the person's account you would like to transfer to?");
	    		String transName  = transfer.nextLine();
	    		balanceBeforeTransfer= imp2.selectAccount2(transName);
	    		
	    		Scanner transfer2 = new Scanner(System.in);
	    		System.out.println("How much would you like to transfer?");
	    		float t = transfer2.nextFloat();
	    		balanceAfterTransfer = balanceBeforeTransfer + t;
	    		imp2.updateAccountAmount(transName, balanceAfterTransfer);
	    		newUserBalance = account.getBalance() - t;
	    		account.setBalance(newUserBalance);
	    		imp2.updateAccountAmount(account);
   	    		break;
   	    		
   	    	case "delete":
   	    		Scanner deleteObj = new Scanner (System.in);
   	    		System.out.println("Are you sure you want to delete your account? (yes or no)");
   	    		String del = deleteObj.nextLine();
   	    		
   	    		switch(del) {
   	    			case "yes":
   	    				imp.deleteUser(account);
   	    				imp2.deleteAccount(account);
   	    		}
   	    		System.exit(0);
   	    		break;
   	    	case "terminate":
   	    		start(imp, imp2);
   	    		break;
   	     default:
 	    	System.out.println("Invalid Command");
 	    } 
   	    
     Scanner question = new Scanner(System.in);  // Create a Scanner object
 	   System.out.println("Would you like to perform another action? (yes or no)");
 	   String actionemp1  = question.nextLine(); 
 	   
 	   switch(actionemp1) {
 	   		case "yes": 
 	   			customerFunction(user,account,imp,imp2);
 	   }
	}
	public static void employeeFunction(User p, UserDaoImpl d, AccountDaoImpl f) {
		Employee employee = new Employee(p.getUsername(), p.getPassword(), p.getName(), p.getType());
    	Scanner emp = new Scanner(System.in);  // Create a Scanner object
   	    System.out.println("What would you like to do? (customerlist, accountretrieval, changeaccountstatus,terminate)");
   	    String actionemp  = emp.nextLine(); 
   	    	switch(actionemp) {
   	    		case "customerlist" :
   	    			employee.viewCustomers(d);
   	    			break;
   	    		case "accountretrieval" :
   	    			Scanner retrieval = new Scanner(System.in);
   	    			System.out.println("What is the username of the person's account you would like to view?");
   	    			String retrieve  = retrieval.nextLine(); 
   	    			employee.accountRetrieval(f, retrieve);
   	    			break;
   	    		case "changeaccountstatus":
   	    			Scanner uname = new Scanner(System.in);
   	    			System.out.println("What is the username of the person's account you would like to update?");
   	    			String usname = uname.nextLine(); 
   	    			Scanner status = new Scanner(System.in);
   	    			System.out.println("What would you like to change the status too? (approved, denied, waiting)");
   	    			String statu = status.nextLine(); 
   	    			employee.changeStatus(f,usname,statu);
   	    			
   	    			switch(statu) {
   	    				case "denied":
   	    					f.deleteAccount(usname);
   	    					d.deleteUser(usname);
   	    				default:
   	    					break;
   	    			}   	    			
   	    			break;
   	    		case "terminate": 
   	    			start(d, f);
   	    		default: 
   	    			System.out.println("Invalid");
   	    			break;
   	    	}
   	   Scanner question = new Scanner(System.in);  // Create a Scanner object
   	   System.out.println("Would you like to perform another action? (yes or no)");
   	   String actionemp1  = question.nextLine(); 
   	   
   	   switch(actionemp1) {
   	   		case "yes": 
   	   			employeeFunction(p,d,f);
   	   }
	}
	
	public static void adminFunction(User p,  UserDaoImpl d, AccountDaoImpl f) {
		
		Admin admin = new Admin(p.getUsername(),p.getPassword(), p.getName(), p.getType());
    	Scanner emp = new Scanner(System.in);  // Create a Scanner object
   	    System.out.println("What would you like to do? (customerlist, accountretrieval, changeaccountstatus, withdraw, deposit)");
   	    String actionemp  = emp.nextLine(); 
   	    	switch(actionemp) {
   	    		case "customerlist" :
   	    			admin.viewCustomers(d);
   	    			break;
   	    		case "accountretrieval" :
   	    			Scanner retrieval = new Scanner(System.in);
   	    			System.out.println("What is the username of the person's account you would like to view?");
   	    			String retrieve  = retrieval.nextLine(); 
   	    			admin.accountRetrieval(f,retrieve);
//   	    			logger.info("Admin Deposited");
   	    			break;
   	    		case "changeaccountstatus":
	    			Scanner uname = new Scanner(System.in);
	    			System.out.println("What is the username of the person's account you would like to update?");
	    			String usname = uname.nextLine(); 
	    			Scanner status = new Scanner(System.in);
	    			System.out.println("What would you like to change the status too? (approved, denied, waiting)");
	    			String statu = status.nextLine(); 
	    			admin.changeStatus(f,usname,statu);
//	    			logger.info("Admin Changed Status");
	    			switch(statu) {
	    				case "denied":
	    					f.deleteAccount(usname);
	    					d.deleteUser(usname);
	    				default:
	    					break;
	    			}
	    			break;
   	    		case "deposit":
   	    			float balance = 0;
   	    			float amount;
   	    			float newBalance;
   	    			boolean bool = false;
   	    			Scanner nme = new Scanner(System.in);
   	    			System.out.println("What is the username of the person's account you would like to deposit into?");
   	    			String names  = nme.nextLine(); 
   	    			balance = admin.accountRetrieval(f,names);
   	    		 	Scanner deposit = new Scanner(System.in);
   	    			System.out.println("How Much?");
   	    			amount  = deposit.nextFloat();
   	    			newBalance = admin.deposit(amount,balance);
   	    			System.out.println("New Balance is:" + newBalance);
   	    			f.updateAccountAmount(names, newBalance);
//   	    			logger.info("Admin Updated Account");
   	    		    break;
   	    		case"withdraw" :
   	    			float balance2 = 0;
   	    			float amount2;
   	    			float newBalance2;
   	    			boolean bool2 = false;
   	    			Scanner nme2 = new Scanner(System.in);
   	    			System.out.println("What is the username of the person's account you would like to withdraw from?");
   	    			String names2  = nme2.nextLine(); 
   	    			balance2 = admin.accountRetrieval(f,names2);
   	    		 	Scanner deposit2 = new Scanner(System.in);
   	    			System.out.println("How Much?");
   	    			amount2  = deposit2.nextFloat();
   	    			newBalance2 = admin.withdraw(amount2,balance2);
   	    			System.out.println("New Balance is:" + newBalance2);
   	    			f.updateAccountAmount(names2, newBalance2);
//   	    			logger.info("Admin Updated Account");
   	    		    break;	
   	    		case "terminate":
   	    			start(d, f);
   	    			break;
   	    		default:
   	    			System.out.println("Invalid");
   	    			break;
   	    	}
   	   Scanner question = new Scanner(System.in);  // Create a Scanner object
   	   System.out.println("Would you like to perform another action? (yes or no)");
   	   String actionemp1  = question.nextLine(); 
   	   
   	   switch(actionemp1) {
   	   		case "yes": 
   	   			adminFunction(p,d,f);
   	   }
		
		
	}
}
