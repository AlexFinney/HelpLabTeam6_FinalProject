package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

import database.MySQLAccess;

public class Application {
	
	public static void main(String[] args){
		Driver myDriver;
		
		try{
			Application.init();
			Application.execute();
		}catch(Exception e){
			System.out.println("A critical error has occured. "
					+ "Please contact a system administrator.");
			Application.shutdown();
		}
	}

	static void init(){
		Connection conn = MySQLAccess.getConnection();
		
		String name = null;
		
		try {
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM temp WHERE id<100");
			while(rs.next()){
				name = rs.getString("name");
				System.out.println(name);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	static void execute(){
		
	}
	
	static void shutdown(){
		
	}
}