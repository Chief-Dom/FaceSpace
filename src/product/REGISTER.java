package product;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class REGISTER extends JPanel{

	private static JFrame frame = new JFrame("Welcome");
	private static JButton registerButton = new JButton ("Register");
	private static JButton cancelButton = new JButton ("Cancel");
	
	private static JTextField usernameField = new JTextField (8);
	private static JTextField passwordField = new JTextField (8);
	private static JTextField confirmPasswordField = new JTextField (8) ;
	private static JTextField securityAns = new JTextField (10) ;
	private static JTextField emailField = new JTextField(6);
	private static JComboBox securityQ ;
	
	private static JLabel userLabel = new JLabel ("Username");
	private static JLabel passwordLabel = new JLabel ("Password");
	private static JLabel confirmPasswordLabel = new JLabel ("Confirm Password");
	private static JLabel securityQuestion = new JLabel ("Security Question");
	private static JLabel securityAnsLabel = new JLabel ("Security Answer");
	private static JLabel emailLabel = new JLabel ("Email"); 
	
	private static JLabel userInfoLabel = new JLabel ("(Only lowercase letters)");
	private static JLabel passInfoLabel = new JLabel ("(1 uppercase letter, 1 lowercase letter, 1 number, minimum of 6 characters)");
	private int appWidth = 400; 
	private int appHeight = 400;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenWidth = (int) screenSize.getWidth();
	private int screenHeight = (int) screenSize.getHeight();
	private int xMid = screenWidth/2 - appWidth/2;
	private int yMid = screenHeight/2 - appHeight/2; 

	
	JPanel panel ;
	private Database db;
	
	public REGISTER (Database db)
	{
		
			this.db = db;
		//	Database db = new Database () ;
		//	JFrame frame = new JFrame ("Welcome");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			createPage() ;
			frame.getContentPane().add(panel);
			frame.setLocation(xMid, yMid);
			frame.pack();
			frame.setVisible(true);
			frame.setResizable(false);
	}
	
	private void createPage () {
		
		Login login = new Login(db);
		
		
		panel = new JPanel ();
		panel.setLayout (new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(400, 400));
		String question[] = {"What is your mother's maiden name ?", "What is the name of your first dog ?", "What is the name of your first school ?"};
		securityQ = new JComboBox (question) ;
		
		
		
		registerButton.addActionListener(new RegisterListener());
		cancelButton.addActionListener(new CancelListener());
	
		JPanel userPanel = new JPanel();
		userPanel.add(userLabel);
		userPanel.add(usernameField);
		userPanel.setAlignmentX(RIGHT_ALIGNMENT);
		
		JPanel userInfoPanel = new JPanel() ;
		userInfoPanel.add(userInfoLabel);
		userInfoPanel.setAlignmentX(RIGHT_ALIGNMENT);

		JPanel passInfoPanel = new JPanel() ;
		passInfoPanel.add(passInfoLabel);
		passInfoPanel.setAlignmentX(RIGHT_ALIGNMENT);

		JPanel emailPanel = new JPanel ();
		emailPanel.add(emailLabel);
		emailPanel.add(emailField);
		emailPanel.setAlignmentX(RIGHT_ALIGNMENT);
		
		JPanel passPanel = new JPanel ();
		passPanel.add(passwordLabel);
		passPanel.add(passwordField);
		passPanel.setAlignmentX(RIGHT_ALIGNMENT);

		
		JPanel cPassPanel = new JPanel();
		cPassPanel.add(confirmPasswordLabel);
		cPassPanel.add(confirmPasswordField);
		cPassPanel.setAlignmentX(RIGHT_ALIGNMENT);
		
		JPanel securityQPanel = new JPanel ();
		securityQPanel.add(securityQuestion);
		securityQPanel.add(securityQ);
		securityQPanel.setAlignmentX(RIGHT_ALIGNMENT);
		
		JPanel securityAnsPanel = new JPanel();
		securityAnsPanel.add(securityAnsLabel);
		securityAnsPanel.add(securityAns);
		securityAnsPanel.setAlignmentX(RIGHT_ALIGNMENT);
		
		JPanel buttonPanel = new JPanel ();
		buttonPanel.add(registerButton);
		buttonPanel.add(cancelButton);
		buttonPanel.setAlignmentX(RIGHT_ALIGNMENT);
	
		
//		panel.add(Box.createHorizontalGlue());
//		panel.add(userLabel, BorderLayout.AFTER_LINE_ENDS);
//		panel.add(usernameField);
//		panel.add(Box.createHorizontalGlue());
//		panel.add(passwordLabel, BorderLayout.AFTER_LINE_ENDS);
//		panel.add(passwordField);
//		panel.add(Box.createHorizontalGlue());
//		panel.add(confirmPasswordLabel, BorderLayout.AFTER_LINE_ENDS);
//		panel.add(confirmPasswordField);
//		panel.add(Box.createHorizontalGlue());
//		panel.add(securityQ, BorderLayout.AFTER_LINE_ENDS);
//		panel.add(Box.createHorizontalGlue());
//		panel.add(securityAnsLabel, BorderLayout.AFTER_LINE_ENDS);
//		panel.add(securityAns, BorderLayout.AFTER_LINE_ENDS);
//		panel.add(Box.createHorizontalGlue());
//		panel.add(registerButton, BorderLayout.CENTER);
//		panel.add(cancelButton, BorderLayout.CENTER) ;

		panel.add(userPanel, BorderLayout.WEST) ;
		panel.add(userInfoPanel, BorderLayout.WEST);
		panel.add(emailPanel, BorderLayout.WEST);
		panel.add(passPanel, BorderLayout.WEST);
		panel.add(passInfoPanel, BorderLayout.WEST);
		panel.add(cPassPanel, BorderLayout.WEST);
		panel.add(securityQPanel, BorderLayout.WEST);
		panel.add(securityAnsPanel, BorderLayout.WEST);
		panel.add(buttonPanel, BorderLayout.WEST);
		
	//	frame.add(panel) ;
		
		
		
	}

	private class RegisterListener implements ActionListener
	{
	

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if (!isValidated(usernameField.getText(), passwordField.getText(), confirmPasswordField.getText(), emailField.getText())) {
				
				JOptionPane errPane = new JOptionPane();
				errPane.showMessageDialog(panel, "Username or Password Invalid. Enter Again!");
				
				usernameField.setText("");
				passwordField.setText("");
				confirmPasswordField.setText("");
				securityAns.setText("");
				emailField.setText("");
			}
			else {
				if (!db.createUser (emailField.getText(),usernameField.getText(),passwordField.getText(), securityQ.getSelectedItem().toString(),securityAns.getText())) {
					JOptionPane errPane = new JOptionPane();
					errPane.showMessageDialog(panel, "User Exists! Log In to your existing account");
					Login login = new Login(db);
					frame.dispose();
				}
				else {
					Login login = new Login(db);
					frame.dispose();
				}
			}
		}
	}
	
	private class CancelListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	}

	private boolean isValidated (String name, String password, String cPassword, String email ) {
	

		boolean flagName = true;
		boolean flagPassword = false ;
		boolean flagEmail = false ;
		for (int i=0; i<name.length(); i++) {
			char ch = name.charAt(i);
			if (Character.isUpperCase(ch)) {
				flagName = false ;
			}
		}
		
		int countUpper = 0 ;
		int countNumber = 0 ;
		
		if(password.length() >= 6) {
			
		for (int i=0; i<password.length(); i++) {
			char ch = password.charAt(i);
			if (Character.isUpperCase(ch))
				countUpper ++;
			if (Character.isDigit(ch))
				countNumber ++;
			
		}
		if (countUpper > 1 || countNumber > 1){
			flagPassword = false ;
		}
		
		if (password.equals(cPassword))
			flagPassword = true;
		}
		
		for(int i=0; i<email.length();i++) {
			char ch = email.charAt(i);
			if(ch == '@')
				flagEmail = true ;
				
		}
		
		if (flagName == false || flagPassword == false || flagEmail == false)
			return false ;
		else
			return true ;
		
	}
	
	
	
	
	
	
	}


