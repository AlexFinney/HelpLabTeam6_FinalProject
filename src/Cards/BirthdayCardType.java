package Cards;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import gui.CardViewer;

public class BirthdayCardType extends CardType {

	private enum CardSpecialty{
		HUMOR,
		CARING
	}
	
	private enum Gender{
		M,
		F
	}
	
	public BirthdayCardType(){
		cardTypeName = "Birthday Card";
		setUpInfoFormPanel();
	}
	
	JTextField ageField;
	JComboBox<CardSpecialty> cardSpecSelectionBox;
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
		
		
		JLabel cardTypeLabel = new JLabel("Card Type:");
		cardTypeLabel.setBounds(labelX, itemVertIndent + itemHeight * 2 + itemVertSpacing, itemWidth, itemHeight);
		cardTypeLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		panel.add(cardTypeLabel);
		
		cardSpecSelectionBox = new JComboBox<CardSpecialty>();
		cardSpecSelectionBox.setBounds(itemIndent, itemVertIndent + itemHeight * 2 + itemVertSpacing, itemWidth, itemHeight);
		cardSpecSelectionBox.addItem(CardSpecialty.HUMOR);
		cardSpecSelectionBox.addItem(CardSpecialty.CARING);
		panel.add(cardSpecSelectionBox);
		
		JLabel genderLabel = new JLabel("Gender:");
		genderLabel.setBounds(labelX, itemVertIndent + itemHeight * 3 + itemVertSpacing * 2, itemWidth, itemHeight);
		genderLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		panel.add(genderLabel);
		
		genderSelectionBox = new JComboBox<Gender>();
		genderSelectionBox.setBounds(itemIndent, itemVertIndent + itemHeight * 3 + itemVertSpacing * 2, itemWidth, itemHeight);
		genderSelectionBox.addItem(Gender.M);
		genderSelectionBox.addItem(Gender.F);
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
		BufferedImage image;
		try {
			image = ImageIO.read(new File("img_testing/cake.png"));
			CardViewer cv = new CardViewer(image, customMessage.getText(), c);
			//CardViewer cv = new CardViewer(image, "Happyyyyyyy  birthhhhhdayyyy!!!!", c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		playSound();
	}
	
}
