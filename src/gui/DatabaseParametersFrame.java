package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.MySQLAccess;

public class DatabaseParametersFrame extends JFrame {

	private static final long serialVersionUID = -8888737165941272741L;
	
	
	
	private int lblXIndent = 20;
	private int yIndent = 20;
	private int textFieldIndent = 100;
	private int vertIndent = 5;
	
	private JTextField ipField;
	private JTextField portField;
	private JTextField usernameField;
	private JTextField passwordField;
	private JTextField dbnameField;

	
	int itemHeight = 30;
	int itemWidth = 100;
	
	public DatabaseParametersFrame(String ip, String port, String username, String password, String dbname){
		setSize(263, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		createComponents();
		
		ipField.setText(ip);
		portField.setText(port);
		usernameField.setText(username);
		passwordField.setText(password);
		dbnameField.setText(dbname);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2,
				(int) (dim.height / 2 - this.getSize().height / 2 - dim.getHeight() / 8));
		setVisible(true);
	}
	
	
	private void createComponents(){

		
		ipField = new JTextField();
		ipField.setBounds(lblXIndent + textFieldIndent, yIndent, itemWidth, itemHeight);
		getContentPane().add(ipField);
		ipField.setColumns(10);
		
		portField = new JTextField();
		portField.setBounds(lblXIndent + textFieldIndent, yIndent + itemHeight * 1 + vertIndent * 1, itemWidth, itemHeight);
		getContentPane().add(portField);
		portField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setBounds(lblXIndent + textFieldIndent, yIndent + itemHeight * 2 + vertIndent * 2, itemWidth, itemHeight);
		getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(lblXIndent + textFieldIndent, yIndent + itemHeight * 3 + vertIndent * 3, itemWidth, itemHeight);
		getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		dbnameField = new JTextField();
		dbnameField.setBounds(lblXIndent + textFieldIndent, yIndent + itemHeight * 4 + vertIndent * 4, itemWidth, itemHeight);
		getContentPane().add(dbnameField);
		dbnameField.setColumns(10);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(lblXIndent, yIndent, itemWidth, itemHeight);
		getContentPane().add(lblIp);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(lblXIndent, yIndent + vertIndent * 1 + itemHeight * 1, itemWidth, itemHeight);
		getContentPane().add(lblPort);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(lblXIndent, yIndent + vertIndent * 2 + itemHeight * 2, itemWidth, itemHeight);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(lblXIndent, yIndent + vertIndent * 3 + itemHeight * 3, itemWidth, itemHeight);
		getContentPane().add(lblPassword);
		
		JLabel lblDatabaseName = new JLabel("Database Name:");
		lblDatabaseName.setBounds(lblXIndent, yIndent + vertIndent * 4 + itemHeight * 4, itemWidth, itemHeight);
		getContentPane().add(lblDatabaseName);
		
		JButton setParamsBtn = new JButton("Set Parameters");
		setParamsBtn.setBounds(57, 212, 123, 48);
		getContentPane().add(setParamsBtn);
		setParamsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				
				MySQLAccess.setParameters(portField.getText(), ipField.getText(),
						usernameField.getText(), passwordField.getText(), dbnameField.getText());
				
				
				if(MySQLAccess.getConnection() == null){
					JOptionPane.showMessageDialog(null, "Unable to connect to the database with loaded parameters. "
	        				+ "\nPlease contact a system administrator or adjust parameters.");
				}else{
					int resp = JOptionPane.showOptionDialog(null,  "Successfully connected to database.\nShould these parameters will be saved for next time?"
							, "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					if(resp == JOptionPane.YES_OPTION){
						MySQLAccess.setSaveParams(true);
					}else{
						MySQLAccess.setSaveParams(false);
					}
					setVisible(false);
					dispose();
				}
			}
		});
	}
	

}

