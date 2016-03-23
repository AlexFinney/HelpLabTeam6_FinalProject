package database;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import gui.CardViewer;


public class DatabasePopulator {

	public static void populate(){
		try {
			BufferedImage image = ImageIO.read(new File("img_testing/cake.png"));
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			
			byte[] bytes = baos.toByteArray();
			
			String stmt = "INSERT INTO test VALUES(?, ?)";
			Connection c =  MySQLAccess.getConnection();
			PreparedStatement pstmt = c.prepareStatement(stmt);
			
			pstmt.setBytes(1, bytes);
			pstmt.setInt(2, 1);
			
			//pstmt.execute();
			
			c.close();
			
			
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void retrieve(){
		Connection c = MySQLAccess.getConnection();
		
		String stmt = "SELECT * FROM test WHERE id=?";
		
		PreparedStatement pstmt;
		try {
			pstmt = c.prepareStatement(stmt);
			pstmt.setInt(1, 1);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			Blob blob = rs.getBlob("pic");
			
			BufferedImage image = ImageIO.read(blob.getBinaryStream());
			
			
			c.close();
			new CardViewer(image);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
}
