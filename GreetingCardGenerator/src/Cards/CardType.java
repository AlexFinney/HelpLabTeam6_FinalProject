package Cards;
import javax.swing.JPanel;

public abstract class CardType {

	JPanel panel;
	String cardTypeName;
	String equivTableName = "BirthdayCard";
	
	
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
}
