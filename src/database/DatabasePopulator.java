package database;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;


public class DatabasePopulator {

	public static byte[] getBytes(String imageName){
		
		BufferedImage image;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			image = ImageIO.read(new File(imageName));
			ImageIO.write(image, "png", baos);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return baos.toByteArray();
	}
	
	
	
	
	
	
	
}
