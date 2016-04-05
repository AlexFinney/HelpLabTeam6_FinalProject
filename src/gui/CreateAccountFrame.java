package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CreateAccountFrame extends JFrame{
	
	private static final long serialVersionUID = 1030944898808131067L;

	public CreateAccountFrame(){
		setResizable(false);
		setSize(626, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Create New Account");
		getContentPane().setLayout(null);
		
		
		createComponents();		
	
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2,
				(int) (dim.height / 2 - this.getSize().height / 2 - dim.getHeight() / 8));
		setVisible(true);
	}

	private JTextField usernameField;
	private JTextField passwordField1;
	private JTextField passwordField2;
	private JTextField emailField1;
	private JTextField emailField2;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JButton btnNext;
	private void createComponents() {
	
		
		createImageLabels();
		createLabels();
		
		
		usernameField = new JTextField();
		usernameField.setBounds(151, 138, 125, 20);
		usernameField.getDocument().addDocumentListener(new TextChangedDocumentListener());
		usernameField.setColumns(10);
		getContentPane().add(usernameField);

		
		passwordField1 = new JPasswordField();
		passwordField1.setBounds(151, 191, 125, 20);
		passwordField1.getDocument().addDocumentListener(new TextChangedDocumentListener());
		passwordField1.setColumns(10);
		getContentPane().add(passwordField1);
		
		passwordField2 = new JPasswordField();
		passwordField2.setColumns(10);
		passwordField2.setBounds(151, 222, 125, 20);
		passwordField2.getDocument().addDocumentListener(new TextChangedDocumentListener());
		getContentPane().add(passwordField2);
		
		passwordField1.getDocument().addDocumentListener(new ComparableTextFieldListener(passwordImgLabel, passwordField1, passwordField2));
		passwordField2.getDocument().addDocumentListener(new ComparableTextFieldListener(passwordImgLabel, passwordField1, passwordField2));

		
		
		emailField1 = new JTextField();
		emailField1.setBounds(105, 67, 169, 20);
		getContentPane().add(emailField1);
		emailField1.getDocument().addDocumentListener(new TextChangedDocumentListener());
		emailField1.setColumns(10);
		
		emailField2 = new JTextField();
		emailField2.setColumns(10);
		emailField2.setBounds(422, 67, 169, 20);
		emailField2.getDocument().addDocumentListener(new TextChangedDocumentListener());
		getContentPane().add(emailField2);
		
		emailField1.getDocument().addDocumentListener(new ComparableTextFieldListener(emailImgLabel, emailField1, emailField2));
		emailField2.getDocument().addDocumentListener(new ComparableTextFieldListener(emailImgLabel, emailField1, emailField2));
		
		
		
		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		firstNameField.setBounds(105, 40, 125, 20);
		firstNameField.getDocument().addDocumentListener(new TextChangedDocumentListener());
		getContentPane().add(firstNameField);
		
		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(422, 38, 125, 20);
		lastNameField.getDocument().addDocumentListener(new TextChangedDocumentListener());
		getContentPane().add(lastNameField);
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VerifyAccountFrame(firstNameField.getText() + " " + lastNameField.getText(),
										usernameField.getText(),
										passwordField1.getText(),
										emailField1.getText());
				setVisible(false);
				dispose();
			}
		});
		btnNext.setBounds(461, 237, 103, 23);
		getContentPane().add(btnNext);
	}
	
	JLabel emailImgLabel;
	JLabel usernameImgLabel;
	JLabel passwordImgLabel;
	String redXImg = "res/red_x.png";
	String greenCheck = "res/green_check.png";
	private void createImageLabels() {

		usernameImgLabel = new JLabel("");
		usernameImgLabel.setBounds(286, 138, 23, 22);
		usernameImgLabel.setIcon(new ImageIcon());
		getContentPane().add(usernameImgLabel);
		
		passwordImgLabel = new JLabel("");
		passwordImgLabel.setBounds(286, 191, 23, 22);
		passwordImgLabel.setIcon(new ImageIcon());
		getContentPane().add(passwordImgLabel);
		
		emailImgLabel = new JLabel("");
		emailImgLabel.setBounds(582, 38, 23, 22);
		getContentPane().add(emailImgLabel);		
	}
	
	private void createLabels() {
		JLabel fnameLbl = new JLabel("First Name:");
		fnameLbl.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		fnameLbl.setBounds(10, 36, 103, 20);
		getContentPane().add(fnameLbl);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblLastName.setBounds(298, 38, 103, 20);
		getContentPane().add(lblLastName);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblEmail.setBounds(10, 67, 103, 20);
		getContentPane().add(lblEmail);
		
		JLabel lblRepeatEmail = new JLabel("Repeat E-mail:");
		lblRepeatEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblRepeatEmail.setBounds(298, 67, 131, 20);
		getContentPane().add(lblRepeatEmail);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblUsername.setBounds(10, 138, 103, 20);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPassword.setBounds(10, 191, 103, 20);
		getContentPane().add(lblPassword);
		
		JLabel lblRepeatPassword = new JLabel("Repeat Password:");
		lblRepeatPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblRepeatPassword.setBounds(10, 225, 131, 20);
		getContentPane().add(lblRepeatPassword);		
	}
	
	private boolean checkForGoodInput(){
		return  !firstNameField.getText().equals("") && !lastNameField.getText().equals("") 
				
				&& !passwordField1.getText().equals("") && !passwordField2.getText().equals("") 
				&& passwordField1.getText().equals(passwordField2.getText())
				
				&& !emailField1.getText().equals("") && !emailField2.getText().equals("") 
				&& emailField1.getText().equals(emailField2.getText())
				
				&& !usernameField.getText().equals("");
	}
	
	class TextChangedDocumentListener implements DocumentListener{
		
		public void insertUpdate(DocumentEvent e) {
			btnNext.setEnabled(checkForGoodInput());
		}
		public void changedUpdate(DocumentEvent e) {
			btnNext.setEnabled(checkForGoodInput());
		}
		public void removeUpdate(DocumentEvent e) {
			btnNext.setEnabled(checkForGoodInput());
		}
		
	}
	
	class ComparableTextFieldListener implements DocumentListener{
		JLabel label;
		JTextField field1;
		JTextField field2;
		
		public ComparableTextFieldListener(JLabel label, JTextField field1, JTextField field2){
			this.label = label;
			this.field1 = field1;
			this.field2 = field2;
		}
		
		public void insertUpdate(DocumentEvent e) {
			update();
		}
		public void changedUpdate(DocumentEvent e) {
			update();
		}
		public void removeUpdate(DocumentEvent e) {
			update();
		}
		
		private void update(){
			if(field1 == null)
				System.out.println("1 null");
			if(field2 == null)
				System.out.println("2 null");
			if(field1.getText() != null && field2.getText() != null){
				if(!field1.getText().equals("") && !field2.getText().equals("")){	
					if(field1.getText().equals(field2.getText()))
						label.setIcon(new ImageIcon(greenCheck));
					else
						label.setIcon(new ImageIcon(redXImg));
				}
			}
		}
	}
	
}



