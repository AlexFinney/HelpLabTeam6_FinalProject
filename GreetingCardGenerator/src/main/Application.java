package main;

import javax.swing.UIManager;
import gui.MainFrame;

public class Application {
	
	public static void main(String[] args){
		
		try{
			Application.init();
			Application.execute();
		}catch(Exception e){
			System.out.println("A critical error has occured. "
					+ "Please contact a system administrator.");
			Application.shutdown();
		}
	}
	
	private static void init() {
		 try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	  catch(Exception e){
		  e.printStackTrace();
	  }
		
	}

	static void execute(){
		//create GUI
		new MainFrame();		
	}
	
	static void shutdown(){
		
	}
}
