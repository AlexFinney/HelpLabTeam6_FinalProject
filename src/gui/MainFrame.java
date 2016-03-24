package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Line2D;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Cards.CardType;
import main.Application;
import util.ClassUtils;

public class MainFrame extends JFrame {
	
	JPanel panel1;
	JPanel panel2;
	JPanel container;
	public MainFrame(){
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Greeting Card Generator");
		setResizable(false);
		
		
		container = new JPanel();
		container.setLayout(new GridLayout(1,2));
		
		
		setupLeftPanel();
		
		panel2 = ((CardType)cardTypeComboBox.getSelectedItem()).getInfoFormPanel();
		panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		container.add(panel1);	
		
		
		container.add(panel2);
		getContentPane().add(container);
		
		setVisible(true);
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Application.shutdown();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	static JComboBox<CardType> cardTypeComboBox;
	 private void setupLeftPanel() {
			panel1 = new JPanel();
			panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			panel1.setLayout(null);
			
			cardTypeComboBox = new JComboBox<CardType>();
			cardTypeComboBox.setBounds(10, 48, 237, 39);
			
			try {
				Class[] classes = ClassUtils.getClasses("Cards");
				for(Class cls : classes){
					if(CardType.class.isAssignableFrom(cls)){
						if(!cls.equals(CardType.class)){
							System.out.println("added " + (CardType)cls.newInstance());
							cardTypeComboBox.addItem((CardType)cls.newInstance());
						}
					}
				}
			} catch (ClassNotFoundException | IOException | InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
			cardTypeComboBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){
						panel2 = ((CardType)getCardSelectionBox().getSelectedItem()).getInfoFormPanel();
						panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
						container.removeAll();
						container.add(panel1);
						container.add(panel2);
						
						invalidate();
						validate();
						repaint();
					}
				}
			});
			
			panel1.add(cardTypeComboBox);
			
			JLabel lblCardType = new JLabel("Card Type:");
			lblCardType.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
			lblCardType.setBounds(10, 11, 82, 24);
			panel1.add(lblCardType);
			
			
			JButton btnGenerate = new JButton("Generate Card!");
			btnGenerate.setBounds(10, 400, 356, 90);
			btnGenerate.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					((CardType)getCardSelectionBox().getSelectedItem()).onGenerate();
				}
			});
			panel1.add(btnGenerate);
	}

	 static JComboBox<CardType> getCardSelectionBox(){
		 return cardTypeComboBox;
	 }
	
	 
	public void paint(Graphics g) {
	        super.paint(g);  // fixes the immediate problem.
	        Graphics2D g2 = (Graphics2D) g;
	        /*
	        Line2D lin = new Line2D.Float(panel1.getWidth(), 0, 
	        				 panel1.getWidth(), this.getHeight() - this.getHeight() / 8);
	       */
	        Line2D lin2 = new Line2D.Float(0,  this.getHeight() - this.getHeight() / 8, 
   				 this.getWidth() / 2, this.getHeight() - this.getHeight() / 8);
	        
	        //g2.draw(lin);
	        g2.setStroke(new BasicStroke(2));
	        g2.draw(lin2);
	    }
	  
}
