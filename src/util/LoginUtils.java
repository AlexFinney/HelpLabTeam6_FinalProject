package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.MySQLAccess;

public class LoginUtils {
	
	static boolean loggingIn = false;
	public static boolean canLogin(){
		return !loggingIn;
	}
	
	
	
	public static boolean login(String username, String password){
		loggingIn = true;
		
		Boolean[] outSuccess = {false};
		String[] outUsername = {""};
		Thread loginThread = new Thread(new LoginThread(username, password, outSuccess, outUsername));
		loginThread.start();
		try {
			loginThread.join();
			System.out.println("Welcome, " + outUsername[0]);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		loggingIn = false;
		return outSuccess[0];
	}

	
	
}

class LoginThread implements Runnable{
	

	String username = "";
	String password = "";
	Boolean[] outDidSucceed;
	String[] outUsername;
	public LoginThread(String username, String password, Boolean[] outDidSucceed, String[] outUsername){
		this.username = username;
		this.password = password;
		this.outDidSucceed = outDidSucceed;
		this.outUsername = outUsername;
	}
	
	@Override
	public void run() {
		try {
			String query = "SELECT * FROM users where username='" + username + "' AND password='" + password + "'";
			Connection c = MySQLAccess.getConnection();
			
			PreparedStatement stmt = c.prepareStatement(query);
			//stmt.setString(1, username);
			//stmt.setString(2, password);
			
			System.out.println(stmt.toString());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				outDidSucceed[0] = true;
				outUsername[0] = rs.getString("username");
			}
			else
				outDidSucceed[0] = false;
			
			
			c.close();					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

