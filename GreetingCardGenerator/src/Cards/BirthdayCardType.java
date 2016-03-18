package Cards;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	JTextField customMessage;
	
	
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
		
	}

	@Override
	public void onGenerate() {
		System.out.println("Genreate meee");
	}

}