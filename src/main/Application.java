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
			//MailUtils.sendEmail("alexander.n.finney@gmail.com", "Welcome to Greeting Cards R Us", "Here is your validation code: HQRZ12R\n\nPlease enter this code within the next 30 minutes to validate your account!\n\n\nThanks again for using Greeting Cards 'R Us,\n\n\tThe Greeting Cards Team");
			
			
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

	static void execute(){
		//create GUI
		//DatabasePopulator.populate();
		//DatabasePopulator.retrieve();
		new LoginFrame();	
		MySQLAccess.init();
	}
	
	public static void shutdown() throws IOException{
		MySQLAccess.shutdown();
	}
	
	
}
