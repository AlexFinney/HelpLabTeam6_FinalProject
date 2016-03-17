package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLAccess {
	
	static final String ip = "127.0.0.1";
	static final String port = "3307";
	static final String dbName = "WebCentricDB";
	static final String userName = "Master";
	static final String password = "webcentric";
	
	public static Connection getConnection(){
		 try {
			return DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port +"/"
					+ dbName + "?" + "user=" + userName + "&password=" + password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
