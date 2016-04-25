package Cards;
import java.awt.Color;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
	
	public void onGenerate(){
		onGenerate(null);
	}
	public abstract void onGenerate(Color c);
	
	public String toString(){
		return cardTypeName;
	}
	
	public CardViewer getCardViewer(){
		return cardViewer;
	}
	
	boolean shouldPlaySound = true;
	void playSound() {
		if(shouldPlaySound){
		    try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
		        					new File("audio/tada.wav").getAbsoluteFile());
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
		}
	}

	
}
