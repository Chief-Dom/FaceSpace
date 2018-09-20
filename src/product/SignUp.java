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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * this class creates the necessary componenets required for the sign up page
 * @author Rohit Gangurde
 *
 */
public class SignUp extends JPanel{

	private static JFrame frame;
	private static JButton registerButton;
	private static JButton cancelButton;
	
	private static JTextField usernameField;
	private static JPasswordField passwordField;
	private static JPasswordField confirmPasswordField;
	private static JTextField securityAns;
	private static JTextField emailField;
	private static JComboBox securityQ;
	
	private static JLabel userLabel;
	private static JLabel passwordLabel;
	private static JLabel confirmPasswordLabel;
	private static JLabel securityQuestion;
	private static JLabel securityAnsLabel;
	private static JLabel emailLabel; 
	
	private static JLabel userInfoLabel;
	private static JLabel passInfoLabel;
	private static int appWidth = 500; 
	private static int appHeight = 400;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenWidth = (int) screenSize.getWidth();
	private int screenHeight = (int) screenSize.getHeight();
	private int xMid = screenWidth/2 - appWidth/2;
	private int yMid = screenHeight/2 - appHeight/2; 

	
	JPanel basePane ;
	
	private Database db;
	
	/**
	 * constructor for the class
	 * @param db
	 */
	public SignUp (Database db)
	{
		frame = new JFrame("Welcome");
		registerButton = new JButton ("Register");
		cancelButton = new JButton ("Cancel");
		registerButton.setToolTipText("(1 uppercase letter, 1 lowercase letter, 1 number, minimum of 6 characters)");
		
		usernameField = new JTextField (8);
		passwordField = new JPasswordField (8);
		confirmPasswordField = new JPasswordField (8) ;
		securityAns = new JTextField (10) ;
		emailField = new JTextField(6);
		
		userLabel = new JLabel ("Username");
		passwordLabel = new JLabel ("Password");
		confirmPasswordLabel = new JLabel ("Confirm Password");
		securityQuestion = new JLabel ("Security Question");
		securityAnsLabel = new JLabel ("Security Answer");
		emailLabel = new JLabel ("Email"); 
		
		
		userLabel.setToolTipText("(Only lowercase letters)");
		usernameField.setToolTipText("(Only lowercase letters)");
			
		passwordLabel.setToolTipText("(1 uppercase letter, 1 lowercase letter, 1 number, minimum of 6 characters)");
		confirmPasswordLabel.setToolTipText("(1 uppercase letter, 1 lowercase letter, 1 number, minimum of 6 characters)");
		passwordField.setToolTipText("(1 uppercase letter, 1 lowercase letter, 1 number, minimum of 6 characters)");
		confirmPasswordField.setToolTipText("(1 uppercase letter, 1 lowercase letter, 1 number, minimum of 6 characters)");
		
		this.db = db;

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(appWidth, appHeight);
		frame.setLayout(new GridLayout(0,1));
			
		createPage() ;
		frame.getContentPane().add(basePane);
		frame.getContentPane().add(basePane);
		frame.setLocation(xMid, yMid);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	
	/**
	 * this method adds the labels, buttons, textfields for the sign up page
	 */
	private void createPage () {

		basePane = new JPanel ();
		basePane.setLayout (new BoxLayout(basePane, BoxLayout.Y_AXIS));
		String question[] = {"What is your mother's maiden name ?", "What is the name of your first dog ?", "What is the name of your first school ?"};
		securityQ = new JComboBox (question) ;
	
		
		basePane = new JPanel ();
		basePane.setLayout (new BoxLayout(basePane, BoxLayout.Y_AXIS));
		
		
		registerButton.addActionListener(new RegisterListener());
		cancelButton.addActionListener(new CancelListener());
	
		JPanel userPanel = new JPanel();
	
		userPanel.add(userLabel);
		
		
		userPanel.add(usernameField);
		userPanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		userPanel.add(emailLabel);
	
		
		userPanel.add(emailField);
		
		userPanel.add(Box.createRigidArea(new Dimension(110,0)));


		JPanel emailPanel = new JPanel ();

		
		JPanel passPanel = new JPanel ();
		passPanel.add(passwordLabel);
		
		
		passPanel.add(passwordField);

		passPanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		JPanel cPassPanel = new JPanel();
		passPanel.add(confirmPasswordLabel);
		
		
		passPanel.add(confirmPasswordField);

		
		JPanel securityQPanel = new JPanel ();
		securityQPanel.add(securityQuestion);
		
		
		securityQPanel.add(securityQ);

		
		JPanel securityAnsPanel = new JPanel();
		securityAnsPanel.add(securityAnsLabel);
	
		securityAnsPanel.add(securityAns);

		
		JPanel buttonPanel = new JPanel ();
		securityAnsPanel.add(registerButton);
	
		
		securityAnsPanel.add(cancelButton);

		basePane.add(userPanel, BorderLayout.WEST) ;
		
		basePane.add(userPanel, BorderLayout.EAST) ;
		
	
		basePane.add(emailPanel, BorderLayout.WEST);
		basePane.add(passPanel, BorderLayout.WEST);
		
		basePane.add(securityQPanel, BorderLayout.WEST);
		basePane.add(securityAnsPanel, BorderLayout.WEST);
		
	} //End of createPage method

	/**
	 * actionlistener for the register button
	 * @author Rohit Gangurde
	 *
	 */
	private class RegisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if (!isValidated(usernameField.getText(), passwordField.getText(), confirmPasswordField.getText(), emailField.getText(), securityAns.getText())) {
				
				JOptionPane errPane = new JOptionPane();
				errPane.showMessageDialog(basePane, "Either the username or password are Invalid OR the security answer field is empty. Enter Again!");
				
				usernameField.setText("");
				passwordField.setText("");
				confirmPasswordField.setText("");
				securityAns.setText("");
				emailField.setText("");
			}
			else {
				if (!db.createUser (emailField.getText(),usernameField.getText(),passwordField.getText(), securityQ.getSelectedItem().toString(),securityAns.getText())) {
					JOptionPane errPane = new JOptionPane();
					errPane.showMessageDialog(basePane, "User Exists! Log In to your existing account");
					Login login = new Login(db);
					frame.dispose();
				}
				else {
					JOptionPane errPane = new JOptionPane();
					errPane.showMessageDialog(basePane, "Sign Up Complete! Log In to your account!");
					Login login = new Login(db);
					frame.dispose();
				}
			}
		} //End of actionPerformed method
	}//End of RegisterListener class
	
	/**
	 * actionlistener for the cancel button
	 * @author Rohit Gangurde
	 *
	 */
	private class CancelListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			basePane.removeAll();
			frame.dispose();
			Login login = new Login(db);
		}
	}
	
	/**
	 * validtaes the input given by the user and returns true if the input is valid
	 * @param name
	 * @param password
	 * @param cPassword
	 * @param email
	 * @param sAns
	 * @return true or false
	 */
	public boolean isValidated (String name, String password, String cPassword, String email, String sAns ) {
	

		boolean flagName = true;
		boolean flagPassword = false ;
		boolean flagEmail = false ;
		boolean flagSAns = true ;
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
		
		if (password.equals(cPassword) && countUpper > 0 && countNumber > 0)
			flagPassword = true;
		}
		
		for(int i=0; i<email.length();i++) {
			char ch = email.charAt(i);
			if(ch == '@')
				flagEmail = true ;
				
		}
		
		if(sAns.equals(""))
			flagSAns = false ;
		
		if (flagName == false || flagPassword == false || flagEmail == false || flagSAns == false)
			return false ;
		else
			return true ;
		
	} //End of isValidated method
}