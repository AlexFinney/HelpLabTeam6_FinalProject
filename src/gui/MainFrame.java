package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Cards.CardType;
import main.Application;
import util.ClassUtils;

public class MainFrame extends JFrame {
	
	JPanel panel1;
	JPanel panel2;
	JPanel container;
	JRadioButton setOwnColor;
	SimpleColorSelector colorSelector;
	
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
		
		colorSelector = new SimpleColorSelector();
		colorSelector.setBounds(20,150,346,75);
		panel1.add(colorSelector);
		
		setOwnColor = new JRadioButton("");
		setOwnColor.setBounds(203, 118, 21, 31);
		setOwnColor.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.DESELECTED) {
					colorSelector.setVisible(true);
				} else {
					colorSelector.setVisible(false);
				}
			}
		});
		panel1.add(setOwnColor);
		
		
		
		JLabel lblDefaultColoring = new JLabel("Use Default Text Color:");
		lblDefaultColoring.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblDefaultColoring.setBounds(10, 118, 187, 31);
		panel1.add(lblDefaultColoring);
		
		container.add(panel1);
		container.add(panel2);
		
		
		getContentPane().add(container);
		
		setVisible(true);
		addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				try {
					Application.shutdown();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			public void windowDeiconified(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
		});
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
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
			btnGenerate.setBounds(10, 470, 356, 90);
			btnGenerate.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(setOwnColor.isSelected()){
						((CardType)getCardSelectionBox().getSelectedItem()).onGenerate();
					}else{
						Color c = colorSelector.getColorChosen();
						((CardType)getCardSelectionBox().getSelectedItem()).onGenerate(c);
					}
					
					
					
				}
			});
			panel1.add(btnGenerate);
	}

	 static JComboBox<CardType> getCardSelectionBox(){
		 return cardTypeComboBox;
	 }
	 
	 
	 public Color getSelectedColor(){
		 
		 return setOwnColor.isSelected() ? null : colorSelector.colorChosen;
	 }
	
	 
	public void paint(Graphics g) {
	        super.paint(g);  // fixes the immediate problem.
	        Graphics2D g2 = (Graphics2D) g;
	        /*
	        Line2D lin = new Line2D.Float(panel1.getWidth(), 0, 
	        				 panel1.getWidth(), this.getHeight() - this.getHeight() / 8);
	       
	        Line2D lin2 = new Line2D.Float(0,  this.getHeight() - this.getHeight() / 8, 
   				 this.getWidth() / 2, this.getHeight() - this.getHeight() / 8);
	       */ 
	        //g2.draw(lin);
	        g2.setStroke(new BasicStroke(2));
	        //g2.draw(lin2);
	    }
}
