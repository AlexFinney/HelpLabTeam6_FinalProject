package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import util.MailUtils;
import util.VerificationCodeUtils;

import java.awt.Font;
import javax.swing.JTextField;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerifyAccountFrame extends JFrame{

	private static final long serialVersionUID = -4877196862408419354L;
	private JTextField textField;

	public VerifyAccountFrame(String fullName, String username, String password, String email ){
		setTitle("Verify Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setResizable(false);
		getContentPane().setLayout(null);

		createComponents();
		setVisible(true);
		
		String message = "Hello, " + fullName + "!\n" +
						 "Thank you for registering with Greeting Cards 'R Us!\n\n"
						 + "Please copy this code to finish registering your account and to gain full access"
						 + " to everything we have to offer!\n\n\n	\tCode: " + VerificationCodeUtils.generateCode()
						 + "\n\n\nWe hope you enjoy the plethora of Cards you now have at your fingertips!\n"
						 + "Signed,\n\tYour Greeting Cards Team";
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					MailUtils.sendEmail(email, "Account Activation", message);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
	
		});
		thread.start();
	}

	private void createComponents() {
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setTabSize(0);
		textArea.setLineWrap(true);
		textArea.setText("Thank your for registering with us! We have sent you an email at the address provided with a verification code to prove you're human.  Please open the email and copy the code here.");
		textArea.setBounds(10, 11, 374, 99);
		textArea.setBackground(new Color(240, 240, 240));
		getContentPane().add(textArea);
		
		JButton btnVerify = new JButton("Verify!");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VerificationCodeUtils.verifyCode(textField.getText())){
					JOptionPane.showMessageDialog(null, "Your account has successfully been activated! "
							+ "\nPlease proceed to login.");
					new LoginFrame();
					setVisible(false);
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Incorrect code! :(");
				}
			}
		});
		btnVerify.setBounds(146, 203, 89, 23);
		btnVerify.setEnabled(false);
		getContentPane().add(btnVerify);
		
		textField = new JTextField();
		textField.setBounds(71, 160, 298, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				btnVerify.setEnabled(!textField.getText().equals(""));
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				btnVerify.setEnabled(!textField.getText().equals(""));
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				btnVerify.setEnabled(!textField.getText().equals(""));				
			}
		});
		
		JLabel lblCode = new JLabel("Code:");
		lblCode.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCode.setBounds(10, 163, 46, 14);
		getContentPane().add(lblCode);
		
	}
	
	
}
