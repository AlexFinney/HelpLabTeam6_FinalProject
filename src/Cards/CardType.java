package Cards;
import javax.swing.JPanel;

import gui.CardViewer;

public abstract class CardType {

	JPanel panel;
	String cardTypeName;
	String equivTableName = "BirthdayCard";
	CardViewer cardViewer;
	
	public JPanel getInfoFormPanel(){
		return panel;
	}
	
	public String getCardTypeName(){
		return cardTypeName;
	}
	
	protected abstract void setUpInfoFormPanel();
	
	public abstract void onGenerate();
	
	public String toString(){
		return cardTypeName;
	}
	
	public CardViewer getCardViewer(){
		return cardViewer;
	}
}
