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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import database.MySQLAccess;
import gui.CardViewer;

public class BirthdayCardType extends CardType {

	private enum Gender{
		M,
		F,
		U
	}
	
	public BirthdayCardType(){
		cardTypeName = "Birthday Card";
		setUpInfoFormPanel();
	}
	
	JTextField ageField;
	JComboBox<Gender> genderSelectionBox;
	JScrollPane customMessageWrapper;
	JTextArea customMessage;
	
	
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
		
		JLabel ageLabel = new JLabel("Age:");
		ageLabel.setBounds(labelX, itemVertIndent + itemHeight, itemWidth, itemHeight);
		ageLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		panel.add(ageLabel);
		
		ageField = new JTextField();
		ageField.setBounds(itemIndent, itemVertIndent + itemHeight, 20, itemHeight);
		panel.add(ageField);
		
		
		JLabel genderLabel = new JLabel("Gender:");
		genderLabel.setBounds(labelX, itemVertIndent + itemHeight * 3 + itemVertSpacing * 2, itemWidth, itemHeight);
		genderLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		panel.add(genderLabel);
		
		genderSelectionBox = new JComboBox<Gender>();
		genderSelectionBox.setBounds(itemIndent, itemVertIndent + itemHeight * 3 + itemVertSpacing * 2, itemWidth, itemHeight);
		genderSelectionBox.addItem(Gender.M);
		genderSelectionBox.addItem(Gender.F);
		genderSelectionBox.addItem(Gender.U);
		panel.add(genderSelectionBox);
		
		JLabel messageLabel = new JLabel("Message:");
		messageLabel.setBounds(labelX, itemVertIndent + itemHeight * 5 + itemVertSpacing * 2, itemWidth, itemHeight);
		messageLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		panel.add(messageLabel);
		
		customMessage = new JTextArea();
		customMessageWrapper = new JScrollPane(customMessage);
		customMessage.setLineWrap(true);
		customMessageWrapper.setBounds(itemIndent, itemVertIndent + itemHeight * 5 + itemVertSpacing * 2, itemWidth, itemHeight * 6);
		customMessageWrapper.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		panel.add(customMessageWrapper);
	}

	
	public void onGenerate(Color c) {

		Connection conn = MySQLAccess.getConnection();
		
		String stmt = "SELECT * FROM birthday_cards WHERE gender='" + genderSelectionBox.getSelectedItem().toString() + "';";
		
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
			System.out.println(blob.length());
			
			BufferedImage image = ImageIO.read(blob.getBinaryStream());
			
			
			conn.close();
			new CardViewer(image, customMessage.getText(), Color.red);
			
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	
		playSound();
	}
	
}
