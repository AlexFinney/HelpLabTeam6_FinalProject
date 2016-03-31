package main;

import java.io.IOException;

import javax.swing.UIManager;

import database.DatabasePopulator;
import database.MySQLAccess;
import gui.LoginFrame;
import gui.MainFrame;
import util.MailUtils;

public class Application {
	
	public static void main(String[] args) throws IOException{
		
		
		try{
			Application.init();
			Application.execute();
			
		}catch(Exception e){
			System.out.println("A critical error has occured. "
					+ "Please contact a system administrator.");
			e.printStackTrace();
			Application.shutdown();
		}
	}
	

	private static void init() throws Exception {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}

	static boolean shouldLogin = true;
	static void execute(){
		//create GUI
		//DatabasePopulator.populate();
		//DatabasePopulator.retrieve();
		if(shouldLogin)
			new LoginFrame();
		else
			new MainFrame();
		MySQLAccess.init();
	}
	
	public static void shutdown() throws IOException{
		MySQLAccess.shutdown();
	}
	
	
}
