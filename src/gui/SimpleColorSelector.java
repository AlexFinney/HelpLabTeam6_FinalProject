//Coded by Trent May
//Idea and basic skeleton used from
//https://docs.oracle.com/javase/tutorial/uiswing/components/colorchooser.html#chooserpanel

package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SimpleColorSelector extends JPanel {
	private static final long serialVersionUID = 1L;
	protected JPanel panel;
	protected Color colorChosen;

	public SimpleColorSelector() {
		setLayout(null);
		
		JButton btnPink = new JButton("Pink");
		btnPink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setBackground(Color.PINK);
				colorChosen = Color.PINK;
			}
		});
		btnPink.setBounds(10, 5, 51, 23);
		btnPink.setBackground(Color.PINK);
		btnPink.setForeground(Color.PINK);
		add(btnPink);
		
		JButton btnYellow = new JButton("Yellow");
		btnYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setBackground(Color.YELLOW);
				colorChosen = Color.YELLOW;
			}
		});
		btnYellow.setBounds(71, 5, 63, 23);
		btnYellow.setBackground(Color.YELLOW);
		btnYellow.setForeground(Color.YELLOW);
		add(btnYellow);
		
		JButton btnGreen = new JButton("Green");
		btnGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setBackground(Color.GREEN);
				colorChosen = Color.GREEN;
			}
		});
		btnGreen.setBounds(144, 5, 61, 23);
		btnGreen.setBackground(Color.GREEN);
		btnGreen.setForeground(Color.GREEN);
		add(btnGreen);
		
		JButton btnBlue = new JButton("Blue");
		btnBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setBackground(Color.BLUE);
				colorChosen = Color.BLUE;
			}
		});
		btnBlue.setBounds(215, 5, 53, 23);
		btnBlue.setBackground(Color.BLUE);
		btnBlue.setForeground(Color.BLUE);
		add(btnBlue);
		
		JButton btnRed = new JButton("RED");
		btnRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setBackground(Color.RED);
				colorChosen = Color.RED;
			}
		});
		btnRed.setBounds(278, 5, 53, 23);
		btnRed.setBackground(Color.RED);
		btnRed.setForeground(Color.RED);
		add(btnRed);
		
		panel = new JPanel();
		panel.setLocation(10, 39);
		panel.setBackground(Color.WHITE);
		panel.setSize(321,27);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setVisible(true);
		add(panel);
	}
	
	public Color getColorChosen(){
		return colorChosen;
	}
}
