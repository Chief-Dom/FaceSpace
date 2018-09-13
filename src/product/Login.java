package product;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * @author Dominik Huffield
 * @author Marcus Henke
 *
 * Creates a GUI for the FaceSpace log in page 
 * the GUI contains text fields for username and password
 * in addition to some buttons to navigate the site  
 */
public class Login {
	static int appWidth = 400; 
	static int appHeight = 200;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenWidth = (int) screenSize.getWidth();
	private int screenHeight = (int) screenSize.getHeight();
	private int xMid = screenWidth/2 - appWidth/2;
	private int yMid = screenHeight/2 - appHeight/2; 
	private Database data;
	
	JTextField userText = new JTextField(20); 
	JPasswordField passText = new JPasswordField(20);
	JLabel userLabel = new JLabel("username: "); 
	JLabel passLabel  = new JLabel("password: ");
	JButton loginButton = new JButton("Login"); 
	JButton registButton = new JButton("Register");
	JButton forgotPassButton = new JButton("Forgot Password");
	JButton forgotUserButton = new JButton("Forgot Username");
	JFrame frame = new JFrame("FaceSpace.com");
	
	JPanel basePanel = new JPanel();
	JPanel userPanel = new JPanel();
	JPanel passPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel buttonPanel2 = new JPanel();
	
	/**
	 * The constructor method for the login class 
	 * 
	 * @param d the database class in which user accounts are saved 
	 */
	public Login(Database d) {
		data = d;
		//Creates Window Frame
		frame.setLayout(new GridLayout(1,1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(appWidth, appHeight);
		frame.setResizable(false);
		frame.setLocation(xMid, yMid);
		
		
		//Creates Listener
		GUIListener listen = new GUIListener();
		loginButton.addActionListener(listen);
		registButton.addActionListener(listen);
		forgotPassButton.addActionListener(listen);
		forgotUserButton.addActionListener(listen);
		
		
		//Makes Base Panel
		basePanel.setSize(appWidth, appHeight);
		basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.PAGE_AXIS));
		
		//User text field Panel
		userPanel.add(userLabel);
		userPanel.add(userText);
		
		
		//Password text Panel
		passPanel.add(passLabel);
		passPanel.add(passText);
		
		//Button Panel
		buttonPanel.add(registButton);
		buttonPanel.add(loginButton);
		
		//Second button panel
		buttonPanel2.add(forgotPassButton);
		buttonPanel2.add(forgotUserButton);
		
		
		
		//Add contents to frame
		Container container = frame.getContentPane();
		basePanel.add(userPanel, BorderLayout.PAGE_START);
		basePanel.add(passPanel, BorderLayout.NORTH);
		basePanel.add(buttonPanel, BorderLayout.NORTH);
		basePanel.add(buttonPanel2, BorderLayout.NORTH);
		
		container.add(basePanel);
		frame.pack();
		frame.setVisible(true);
		
	
	}
	

	/**
	 * Action listener class for buttons
	 * 
	 * @author Dominik Huffield
	 * @author Marcus Henke
	 *
	 */
	private class GUIListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent act) {
			
			if (act.getSource() == forgotPassButton) {
				forgotPassword();
			}
			if (act.getSource() == forgotUserButton) {
				forgotUsername();
			}
			
			if (act.getSource() == loginButton) { //if  fields have entered data
				//Attempt to sign user in
				String[] string = data.login(userText.getText(), passText.getText());
				
				if (string.length == 5) {
					//Sign user in
					OpenHome home = new OpenHome(string, data);
					frame.dispose();
					JOptionPane.showMessageDialog(frame, "welcome, " + string[0]);
					
				} else if(string.length == 1){
					//Tell user their credentials were wrong
					String issue = string[0];
					JOptionPane.showMessageDialog(frame, issue);
					passText.setText("");
				} 
			} 
			else if (act.getSource() == registButton) {
				//Take user to register page
				SignUp registPage = new SignUp(data);
				frame.dispose();
			}
		
		} //End of action method
		
		/**
		 * Method for forgot password button 
		 * Gives user dialog panes and fields 
		 * with security question to securely give 
		 * users their passwords
		 */
		private void forgotPassword() {
			String email = JOptionPane.showInputDialog("Enter email: ");
			String[] arr = data.getUserInfo(email);
			if (email == null) {}
			else if (arr.length == 1) {
				JOptionPane.showMessageDialog(frame, "User does not exist.");
			}
			else {
				String answer = JOptionPane.showInputDialog(arr[2]);
				String result;
				if (answer == null) {}
				else if (answer.equals(arr[3])) {
					result = "Your password is: \n" + arr[1];
					JOptionPane.showMessageDialog(frame, result);
				}
				else {
					result = "Your answer is incorrect.";
					JOptionPane.showMessageDialog(frame, result);
				}
			}
		}
		
		private void forgotUsername() {
			String email = JOptionPane.showInputDialog("Enter email: ");
			String[] arr = data.getUserInfo(email);
			if (email == null) {}
			else if (arr.length == 1) {
				JOptionPane.showMessageDialog(frame, "User does not exist.");
			}
			else {
				String answer = JOptionPane.showInputDialog(arr[2]);
				String result;
				if (answer == null) {}
				else if (answer.equals(arr[3])) {
					result = "Your username is: \n" + arr[0];
					JOptionPane.showMessageDialog(frame, result);
				}
				else {
					result = "Your answer is incorrect.";
					JOptionPane.showMessageDialog(frame, result);
				}
			}
		}
		
	} //End of GUI Listener class
	
	
	
}