package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.LoginUtils;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 366002291412726663L;
	
	private JTextField userNameField;
	private JTextField passwordField;
	private JCheckBox checkBoxRemember;

	public LoginFrame() {
		setTitle("Welcome to the Greeting Card Generator!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setSize(400, 300);

		createComponents();

		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2,
				(int) (dim.height / 2 - this.getSize().height / 2 - dim.getHeight() / 8));
		
		
		checkForExistingLoginInfo();
	}


	void createComponents() {
		JLabel lblPleaseEnter = new JLabel("Please enter your login information:");
		lblPleaseEnter.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPleaseEnter.setBounds(10, 11, 343, 37);
		getContentPane().add(lblPleaseEnter);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblUsername.setBounds(53, 59, 132, 14);
		getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPassword.setBounds(53, 96, 110, 14);
		getContentPane().add(lblPassword);

		userNameField = new JTextField();
		userNameField.setBounds(155, 59, 143, 20);
		getContentPane().add(userNameField);
		userNameField.setColumns(10);
		userNameField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attemptToLogin(userNameField.getText(), passwordField.getText());
			}
		});

		passwordField = new JPasswordField();
		passwordField.setBounds(155, 95, 143, 20);
		getContentPane().add(passwordField);
		passwordField.setColumns(10);

		passwordField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				attemptToLogin(userNameField.getText(), passwordField.getText());
			}
		});

		JLabel lblOr = new JLabel("Or");
		lblOr.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblOr.setBounds(195, 193, 46, 28);
		getContentPane().add(lblOr);

		JButton btnCreateNewAccount = new JButton("Create New Account");
		btnCreateNewAccount.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnCreateNewAccount.setBounds(101, 219, 197, 37);
		getContentPane().add(btnCreateNewAccount);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attemptToLogin(userNameField.getText(), passwordField.getText());
			}

		});
		btnLogin.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnLogin.setBounds(101, 159, 197, 37);
		getContentPane().add(btnLogin);

		checkBoxRemember = new JCheckBox("Remember for next time?");
		checkBoxRemember.setBounds(155, 115, 163, 23);
		getContentPane().add(checkBoxRemember);
		btnCreateNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateAccountFrame();
				dispose();
			}
		});
	}

	
	private void attemptToLogin(String username, String password) {
		
		
		if (LoginUtils.canLogin()) {
			boolean success = LoginUtils.login(username, password);
			if (success) {
				if(checkBoxRemember.isSelected())
					saveLoginInfoToFile(username, password);
				setVisible(false);
				dispose();
				new MainFrame();
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");
			}
		}
	}
	String filename = "gcrus.bin";
	private void saveLoginInfoToFile(String username, String password){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filename)));
			oos.writeObject(new LoginInfo(username, password));
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private void checkForExistingLoginInfo() {
		File file = new File(filename);
		if(file.exists()){
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				
				LoginInfo info = (LoginInfo)ois.readObject();
				ois.close();
				if(info == null)
					return;
				else{
					attemptToLogin(info.username, info.password);
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}else{
			setVisible(true);
		}
	}
}

class LoginInfo implements Serializable{
	private static final long serialVersionUID = -6090914938879123190L;

	String username;
	String password;
	
	public LoginInfo(String username, String password){
		this.username = username;
		this.password = password;
	}
}
