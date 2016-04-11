package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.OverlayLayout;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

public class CardViewer extends JFrame{
	BufferedImage image = null;
	String cardText;
	
	public CardViewer(BufferedImage image, String text){
		this.cardText = text;
		this.setLayout(new OverlayLayout(this));
		this.image = (BufferedImage) resizeToBig(image, 768, 1024 );
		setImage();
		setSize(this.image.getWidth(), this.image.getHeight());
		setResizable(false);
		
		createTextPane();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2,(int) (dim.height / 2 - this.getSize().height / 2 - dim.getHeight() / 8));
		
		setVisible(true);
	}
	
	private void createTextPane(){
		int areaWidth = (int) (this.image.getWidth() / 1.3);
		int areaHeight = this.image.getHeight() - image.getHeight() / 16;
		int xOffset = (this.image.getWidth() - areaWidth) / 2;
		int yOffset = this.image.getHeight() / 16;
		
		
		JTextPane area = new JTextPane();
		area.setEditable(false);
		area.setBackground(new Color(0,0,0,0));
		area.setBounds(xOffset, yOffset, areaWidth, areaHeight);
		area.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
		area.setOpaque(false);
		area.setOpaque(false);
		area.setBorder(null);
		backroundLabel.add(area);

		
		Color c = getMostCommonColor();
		Style style = area.addStyle("ColorStyle", null);
		StyleConstants.setForeground(style, c);
		try {
			area.getStyledDocument().insertString(0, cardText, style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	JLabel backroundLabel;
	private void setImage() {
		backroundLabel = new JLabel(new ImageIcon(image)); 
		setContentPane(backroundLabel);
		setSize(this.getWidth() - 1,this.getHeight() - 1);
		setSize(this.getWidth() + 1,this.getHeight() + 1);
	}
	
	
	
	private Color getMostCommonColor(){
		
		HashMap<Color, Integer> occurances = new HashMap<Color, Integer>();
		
		for(int y = 0; y < image.getHeight(); y++){
			for(int x = 0; x < image.getWidth(); x++){
				int data = image.getRGB(x, y);

				int r =  ((data  & 0xFF0000) >> 16);
				int g =  ((data  & 0xFF00) >>  8);
				int b =  (data & 0xFF);
				
				r /= 32;
				g /= 32;
				b /= 32;
				
				Color col = new Color(r,g,b);
				if(occurances.get(col) == null){
					occurances.put(col, 1);
				}else{
					occurances.put(col, occurances.get(col) + 1);
				}
			}
		}
		
		Integer[] vals = (Integer[])occurances.values().toArray(new Integer[occurances.keySet().size()]);
		Arrays.sort(vals);
		Color firstPlace = null;
		Color secondPlace = null;
		Color thirdPlace = null;
		for(Map.Entry<Color, Integer> entry : occurances.entrySet()){
			
			if(entry.getValue() == vals[vals.length - 1]){
				firstPlace = entry.getKey();
			}
			if(entry.getValue() == vals[vals.length - 2]){
				secondPlace = entry.getKey();
			}
			if(entry.getValue() == vals[vals.length - 3]){
				thirdPlace = entry.getKey();
			}
		}
		
		JFrame frame = new JFrame();
		frame.setLayout(null);
		frame.setSize(300,300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		
	
		
		
		
		Color finalColor = new Color(255 - firstPlace.getRed() * 32,
									 255 - firstPlace.getGreen() * 32,
									 255 - firstPlace.getBlue() * 32);
		
		JLabel first = new JLabel("First Place");
		first.setOpaque(true);
		first.setBounds(0,0, 300,100);
		first.setBackground( new Color( firstPlace.getRed() * 32,
				  firstPlace.getGreen() * 32,
				  firstPlace.getBlue() * 32));
		frame.add(first);
		
		
		
		JLabel second = new JLabel("Second Place");
		second.setOpaque(true);
		second.setBounds(0,100, 300,100);
		second.setBackground( new Color( secondPlace.getRed() * 32,
				 secondPlace.getGreen() * 32,
				  secondPlace.getBlue() * 32));
		frame.add(second);
		
		JLabel third = new JLabel("Third Place");
		third.setOpaque(true);
		third.setBounds(0,200, 300,100);
		third.setBackground( new Color( thirdPlace.getRed() * 32,
				  thirdPlace.getGreen() * 32,
				  thirdPlace.getBlue() * 32));
		frame.add(third);
		
		
		return finalColor;
	}
	
	
	
	
	private Color getAverageColor(){
		
		long rSum = 0;
		long gSum = 0;
		long bSum = 0;
		
		for(int y = 0; y < image.getHeight(); y++){
			for(int x = 0; x < image.getWidth(); x++){
				int data = image.getRGB(x, y);
				rSum += (data >> 16) & 0xFF;
				gSum += (data >>  8) & 0xFF;
				bSum += (data & 0xFF);
			}
		}
		long numPixels = image.getWidth() * image.getHeight();
		int rAvg = (int)(rSum / numPixels);
		int gAvg = (int)(gSum / numPixels);
		int bAvg = (int)(bSum / numPixels);
		
		int a = Integer.max(rAvg, gAvg);
		int b = Integer.max(a, bAvg);
		
		
		int greyAvg = (rAvg + bAvg + gAvg) / 3;
		int greyContrast = 255 - greyAvg;
		
		float lightRatio = greyContrast / 255;
		float lowerBoundRatio = .3f;
		float adjustment = 1;
		
		lightRatio = lightRatio - Math.abs(lightRatio - lowerBoundRatio) * adjustment;
		
		if(lightRatio < 0)
			lightRatio = 0;
		else if (lightRatio > 1)
			lightRatio = 1;
		System.out.println(lightRatio);
		
		
		return new Color(lightRatio, lightRatio, lightRatio);
		//return new Color(greyAvg, greyAvg, greyAvg);
		
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
