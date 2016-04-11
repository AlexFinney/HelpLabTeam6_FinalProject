package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.UIManager;

import database.MySQLAccess;
import gui.CardViewer;
import gui.LoginFrame;
import gui.MainFrame;

public class Application {
	
	public static void main(String[] args) throws IOException{
		try{
			
		
			BufferedImage image = ImageIO.read(new File("img_testing/download.png"));
			
			CardViewer cv = new CardViewer(image, "'ello there, 'ol chap! Seems like a perfectly fine day for nice, cuppa' tea!\n\nOh, and happy birthday!");
			
			//Application.init();
			//Application.execute();
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

	static boolean shouldLogin = false;
	static void execute(){
		
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
