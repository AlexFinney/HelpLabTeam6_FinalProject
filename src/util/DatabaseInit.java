package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import database.MySQLAccess;

public class DatabaseInit {
	
	public static void init(){
		Connection c = MySQLAccess.getConnection();
/*
		try {
			BufferedImage image = ImageIO.read(new File("img_testing/cake.png"));
			
			c.prepareStatement("INSERT INTO birthday_cards (pic, age, gender, card_type, id)"
								+ "VALUES() ").;
			c.close();
			
			
			
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		*/
	}

}
