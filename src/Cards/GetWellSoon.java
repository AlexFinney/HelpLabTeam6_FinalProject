package Cards;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;


public class GetWellSoon extends CardType {
	
	private enum CardSpecialty {
		SYMPATHY,
		COMPASSION,
		CARE
	}
	
	public GetWellSoon(){
		cardTypeName = "Get Well Soon Card";
		setUpInfoFormPanel();
	}
	
	JComboBox<CardSpecialty> cardSpecSelectionBox;
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
		
		
		JLabel cardTypeLabel = new JLabel("Card Type:");
		cardTypeLabel.setBounds(labelX, itemVertIndent + itemHeight * 2 + itemVertSpacing, itemWidth, itemHeight);
		cardTypeLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		panel.add(cardTypeLabel);
		
		cardSpecSelectionBox = new JComboBox<CardSpecialty>();
		cardSpecSelectionBox.setBounds(itemIndent, itemVertIndent + itemHeight * 2 + itemVertSpacing, itemWidth, itemHeight);
		cardSpecSelectionBox.addItem(CardSpecialty.SYMPATHY);
		cardSpecSelectionBox.addItem(CardSpecialty.COMPASSION);
		cardSpecSelectionBox.addItem(CardSpecialty.CARE);
		panel.add(cardSpecSelectionBox);
		
		JLabel messageLabel = new JLabel("Message:");
		messageLabel.setBounds(labelX,  itemVertIndent + itemHeight * 4 + itemVertSpacing, itemWidth, itemHeight);
		messageLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		panel.add(messageLabel);
		
		customMessage = new JTextArea();
		customMessage.setBounds(itemIndent, itemVertIndent + itemHeight * 4 + itemVertSpacing, itemWidth, itemHeight * 7);
		customMessage.setLineWrap(true);
		customMessage.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		panel.add(customMessage);
	}

	@Override
	public void onGenerate() {
		System.out.println("On gen");
	}

}
