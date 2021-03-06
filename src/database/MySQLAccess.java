package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import gui.DatabaseParametersFrame;

public class MySQLAccess {
	
	static String ip = "10.173.1.6";
	static String port = "3306";
	static String dbname = "WebCentricDB";
	static String username = "Master";
	static String password = "webcentric";
	
	public static Connection getConnection(){
		 try {
			return DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port +"/"
					+ dbname + "?" + "user=" + username + "&password=" + password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	static final String fileName = "DatabaseParameters.txt";
	public static void init(){
	    
        try{
        	File file = new File(fileName);
        	if(!file.exists())
        		file.createNewFile();
        	
        	BufferedReader br = new BufferedReader(new FileReader(file));

        	String input = "";
        	String _pwd = "";
        	String _dbname = "";
        	String _ip = "";
        	String _port = "";
        	String _username = "";
        	
        	while((input = br.readLine()) != null){
        		switch(input.split(":")[0]){
        		case "password":
        			_pwd =input.split(":")[1]; 
        			break;
        		case "dbname":
        			_dbname =input.split(":")[1];
        			break;
        		case "username":
        			_username =input.split(":")[1];
        			break;
        		case "ip":
        			_ip =input.split(":")[1];
        			break;
        		case "port":
        			_port =input.split(":")[1];
        			break;
        		}
        	}
        	
        	br.close();
        	
        	if(!_pwd.equals(""))
        			password = _pwd;
        	if(!_username.equals(""))
    			username = _username;
        	if(!_dbname.equals(""))
    			dbname = _dbname;
        	if(!_ip.equals(""))
    			ip = _ip;
        	if(!_port.equals(""))
    			port = _port;
        	
        	Connection c = getConnection();
        	if(c == null){
        		DatabaseParametersFrame dpf = new DatabaseParametersFrame(ip, port, username, password, dbname);
        		JOptionPane.showMessageDialog(dpf, "Unable to connect to the database with loaded parameters. "
        				+ "\nPlease contact a system administrator or adjust parameters.");
        	}else
        		c.close();
        	
        	System.out.println("Loaded params from config file. Connectvity == " + (getConnection() != null));
        	
        }catch (Exception e){
        }
		
	}
	
	public static void setParameters(String _port, String _ip, String _username, 
			String _password, String _dbname){
		port = _port;
		ip = _ip;
		username = _username;
		password = _password;
		dbname = _dbname;
		
	}
	
	static boolean saveParams = false;
	public static void setSaveParams(boolean save){
		saveParams = save;
		System.out.println(saveParams);
	}
	
	public static void shutdown() throws IOException{
		if(saveParams){
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));
				bw.write("ip:" + ip  + "\n");
				bw.write("port:" + port  + "\n");
				bw.write("username:" + username  + "\n");
				bw.write("password:" + password  + "\n");
				bw.write("dbname:" + dbname  + "\n");
				bw.close();
				
			
		}
	
	}
	
}
