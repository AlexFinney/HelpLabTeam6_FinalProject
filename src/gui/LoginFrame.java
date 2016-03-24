package gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import util.LoginUtils;

import javax.swing.JButton;

public class LoginFrame extends JFrame{
	private JTextField userNameField;
	private JTextField passwordField;

	public LoginFrame(){
		setTitle("Welcome to the Greeting Card Generator!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setSize(400, 300);
		
		createComponents();
		
		
		setVisible(true);
	}
	
	void createComponents(){
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
		userNameField.setBounds(155, 59, 123, 20);
		getContentPane().add(userNameField);
		userNameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(155, 95, 123, 20);
		getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblOr = new JLabel("Or");
		lblOr.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblOr.setBounds(195, 180, 46, 28);
		getContentPane().add(lblOr);
		
		JButton btnCreateNewAccount = new JButton("Create New Account");
		btnCreateNewAccount.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnCreateNewAccount.setBounds(101, 207, 217, 37);
		getContentPane().add(btnCreateNewAccount);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(LoginUtils.canLogin()){
					boolean success = LoginUtils.login(userNameField.getText(), passwordField.getText());
					if(success){
						new MainFrame();
						dispose();
					}else{
						JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");
					}
				}
			
				
				
			}
		});
		btnLogin.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btnLogin.setBounds(101, 145, 217, 37);
		getContentPane().add(btnLogin);
		btnCreateNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateAccountFrame();
				dispose();
			}
		});
	}
	
	
}
