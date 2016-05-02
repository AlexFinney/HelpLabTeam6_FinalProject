package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.UIManager;

import database.DatabasePopulator;
import database.MySQLAccess;
import gui.CardViewer;
import gui.LoginFrame;
import gui.MainFrame;

public class Application {
	
	
	static MainFrame mainFrame;
	
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
	
	public static MainFrame getMainFrame(){
		return mainFrame;
	}
	
	public static void setMainFrame(MainFrame frame){
		mainFrame = frame;
	}

	private static void init() throws Exception {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}

	static boolean shouldLogin = true;
	static void execute() throws SQLException{
		
		if(shouldLogin)
			new LoginFrame();
		else
			mainFrame = new MainFrame();
		
		MySQLAccess.init();		

		
		//uploadImageToDB();
		
	}	
	
	
	public static void uploadImageToDB() throws SQLException{
			int id = 4;
			String imageName = "img_testing/anv" + id + ".png";
			
			
			System.out.println("Begin upload...");
			byte[] bytes = DatabasePopulator.getBytes(imageName);
			
			String stmt = "INSERT INTO anniversary_cards VALUES(?, ?)";
			
			Connection c =  MySQLAccess.getConnection();
			PreparedStatement pstmt = c.prepareStatement(stmt);
			
			
			pstmt.setBytes(1, bytes);
			pstmt.setInt(2, id);
			pstmt.execute();
			c.close();

			System.out.println("Upload complete.");
	}

	public static void shutdown() throws IOException{
		MySQLAccess.shutdown();
	}
	
	
}
