package gui;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import database.MySQLAccess;

public class CardViewer extends JFrame{
	BufferedImage image = null;
	
	public CardViewer(BufferedImage image){
		
		
		
		this.image = (BufferedImage) resizeToBig(image, 500, 500);
		setImage();
		
		setSize(this.image.getWidth(), this.image.getHeight());
		setResizable(false);
		
		setVisible(true);
	}

	private void setImage() {
			setContentPane(new JLabel(new ImageIcon(image)));
			setSize(this.getWidth() - 1,this.getHeight() - 1);
			setSize(this.getWidth() + 1,this.getHeight() + 1);
	}


	//taken from http://stackoverflow.com/questions/3967731/how-to-improve-the-performance-of-g-drawimage-method-for-resizing-images/11371387#11371387
	private Image resizeToBig(Image originalImage, int biggerWidth, int biggerHeight) {
	    int type = BufferedImage.TYPE_INT_ARGB;
	    
	    BufferedImage resizedImage = new BufferedImage(biggerWidth, biggerHeight, type);
	    Graphics2D g = resizedImage.createGraphics();
	    g.setComposite(AlphaComposite.Src);
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	    g.drawImage(originalImage, 0, 0, biggerWidth, biggerHeight, this);
	    g.dispose();

	    return resizedImage;
	}
	
	
	

}
