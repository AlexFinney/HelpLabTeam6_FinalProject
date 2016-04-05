package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import database.MySQLAccess;

public class VerificationCodeUtils {
	
	public static String generateCode(){
		Random random = new Random(System.currentTimeMillis());
		
		String code = "";
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				char c = (char) (random.nextInt(26) + 65);
				code += c;
			}
			if(i != 4)
				code += "-";
		}
		
		Connection c = MySQLAccess.getConnection();
		
		try {
			String query = "INSERT INTO codes VALUES(\'" + code + "\')";
			PreparedStatement stmt = c.prepareStatement(query);
			stmt.execute();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return code;
	}
	
	public static boolean verifyCode(String code){
		Connection c = MySQLAccess.getConnection();
		
		try {
			String query = "SELECT * FROM codes WHERE code=?";
			PreparedStatement stmt = c.prepareStatement(query);
			stmt.setString(1, code);
			
			stmt.execute();
			
			ResultSet rs = stmt.getResultSet();
			
			boolean ret = rs.next();
			
			if(ret){
				query = "DELETE FROM codes WHERE code=?";
				stmt = c.prepareStatement(query);
				stmt.setString(1, code);
				stmt.execute();
			}
				
			
			c.close();
			
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	
	}
}
