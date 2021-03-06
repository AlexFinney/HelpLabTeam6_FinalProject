package Cards;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import database.MySQLAccess;
import gui.CardViewer;
import gui.MainFrame;
import main.Application;

public class SympathyCardType extends CardType {

	
	
	public SympathyCardType(){
		cardTypeName = "Sympathy Card";
		setUpInfoFormPanel();
	}
	
	
	JTextArea customMessage;
	JScrollPane customMessageWrapper;
	
	
	int itemWidth = 200;
	int itemVertIndent = 25;
	int itemHeight = 25;
	int itemIndent = 145;
	int itemVertSpacing = 5;
	
	int labelX = 50;
	
	@Override
	protected void setUpInfoFormPanel() {
		panel = new JPanel();
		panel.setLayout(null);
		
		JLabel panelLabel = new JLabel(cardTypeName + " Info:");
		panelLabel.setBounds(labelX - 40, 10, 200, 50);
		panelLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		panel.add(panelLabel);
		
		
		JLabel messageLabel = new JLabel("Message:");
		messageLabel.setBounds(labelX,  itemVertIndent + itemHeight * 4 + itemVertSpacing, itemWidth, itemHeight);
		messageLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		panel.add(messageLabel);
		
		customMessage = new JTextArea();
		customMessage.setLineWrap(true);
		customMessage.setToolTipText("Please a short message for the card");
		customMessageWrapper = new JScrollPane(customMessage);
		customMessageWrapper.setBounds(itemIndent, itemVertIndent + itemHeight * 4 + itemVertSpacing, itemWidth, itemHeight * 7);
		customMessageWrapper.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		panel.add(customMessageWrapper);
		
	
	}
	
	
	@Override
	public void onGenerate(Color c) {
		Connection conn = MySQLAccess.getConnection();
		
		String stmt = "SELECT * FROM sympathy_cards;";
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(stmt);
			
			ResultSet rs = pstmt.executeQuery();
			
			Random rand = new Random(System.currentTimeMillis());
			
			
			int rows = 0;
			if(rs.last()){
				rows = rs.getRow();
				rs.beforeFirst();
			}
			rs.next();
			
			int id = rand.nextInt(rows);
			
			for(int i = 0; i < id; i++)
				rs.next();
			
			Blob blob = rs.getBlob("pic");
			
			BufferedImage image = ImageIO.read(blob.getBinaryStream());
			
			
			conn.close();
			MainFrame mf = Application.getMainFrame();
			
			new CardViewer(image, customMessage.getText(), Application.getMainFrame().getSelectedColor());
			
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	
		playSound();
			
	}

}
