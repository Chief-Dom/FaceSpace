package product;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;



/**
 * Creates a GUI that displays account username and email. 
 * Also contains buttons to edit account information.
 * 
 * @author Dominik Huffield
 * @author Rohit Gangurde
 * @author Marcus Henke
 */
public class AcctDetails {
	
	static int appWidth = 400; 
	static int appHeight = 200;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenWidth = (int) screenSize.getWidth();
	private int screenHeight = (int) screenSize.getHeight();
	private int xMid = screenWidth/2 - appWidth/2;
	private int yMid = screenHeight/2 - appHeight/2; 
	
	JFrame frame = new JFrame("FaceSpace.com");
	JPanel basePanel = new JPanel();
	JPanel labelPane = new JPanel();
	JPanel editPane = new JPanel();
	JPanel buttonPane = new JPanel();
	JPanel userPane = new JPanel();
	JPanel emailPane = new JPanel();
	
	
	JLabel userLabel = new JLabel();
	JLabel emailLabel = new JLabel();
	
	JButton userButton = new JButton("Username");
	JButton passButton = new JButton("Password");
	JButton emailButton = new JButton("Email");
	
	JButton logoutButton = new JButton("Logout");
	JButton homeButton = new JButton("Home");
	
	private Database data;
	private String[] array;
	
	/**
	 * Constructor method for account details GUI
	 * 
	 * @param stringArray array of account info for logged in user
	 * @param db database class to alter account info
	 */
	public AcctDetails(String[] stringArray, Database db) {
		data = db;
		array = stringArray;
		
		frame.setLayout(new GridLayout(1,1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(appWidth, appHeight);
		frame.setResizable(false);
		frame.setLocation(xMid, yMid);
		frame.setVisible(true);
		
		userLabel.setText(stringArray[0]);

		emailLabel.setText(stringArray[4]);
		emailLabel.add(Box.createRigidArea(new Dimension(5,5)));
		
		TitledBorder border = BorderFactory.createTitledBorder("EDIT");
	    border.setTitleJustification(TitledBorder.CENTER);
		
		editPane.setBorder(border);
		
		basePanel.setSize(appWidth, appHeight);
		basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.PAGE_AXIS));
		
		labelPane.setLayout(new BoxLayout(labelPane, BoxLayout.X_AXIS));
		
		userPane.add(userLabel);
		emailPane.add(emailLabel);
		
		labelPane.add(userPane);
		labelPane.add(emailPane);
		
		editPane.add(userButton);
		editPane.add(passButton);
		editPane.add(emailButton);
		
		buttonPane.add(logoutButton);
		buttonPane.add(homeButton);
		
		//Sets Action Listener to buttons
		GUIListener listen = new GUIListener();
		userButton.addActionListener(listen);
		passButton.addActionListener(listen);
		emailButton.addActionListener(listen);
		logoutButton.addActionListener(listen);
		homeButton.addActionListener(listen);

		
		Container container = frame.getContentPane();
		basePanel.add(labelPane, BorderLayout.PAGE_START);
		basePanel.add(editPane, BorderLayout.NORTH);
		basePanel.add(buttonPane, BorderLayout.NORTH);
		
		container.add(basePanel);
		frame.pack();
	}

	
	
	/**
	 * Listener class for the buttons on the GUI
	 * 
	 * @author Dominik Huffield
	 *
	 */
	private class GUIListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent act) {
			
			if (act.getSource() == logoutButton) {
				Login login = new Login(data);
				frame.dispose();
			}
			
			if (act.getSource() == homeButton) {
				OpenHome home = new OpenHome(array, data);	
				frame.dispose();
			}
			
			if (act.getSource() == userButton) {
				int userOption = JOptionPane.YES_NO_OPTION;
				userOption = JOptionPane.showConfirmDialog(null ,"Your username is "+array[0]+". To change your username press YES. Press NO to go back to the Edit Account Page");
				if(userOption == JOptionPane.YES_OPTION) {
					for(int i = 0; i >=0; i++) {
						String userName = JOptionPane.showInputDialog("Enter new username: ");
						String cUserName = JOptionPane.showInputDialog("Confirm your username : ");
						if(isUserNameValid(userName, cUserName)) {
							String oldUser = array[0] ;
							array[0] = userName ;
							userLabel.setText(array[0]);
				     		data.editUsername(array,oldUser);
							JOptionPane.showMessageDialog(null, "Username Changed Successfully");
							// code to return to account details
							break ;
						} else {
							JOptionPane.showMessageDialog(null,"Username does not follow the rules for a valid username. For a username, all the letters should be in lowercase", "Try Again" ,JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				
			}
					
			if (act.getSource() == passButton) {
				int userOption = JOptionPane.YES_NO_OPTION;
				userOption = JOptionPane.showConfirmDialog(null ,"Your password is "+array[1]+". To change your password press YES. Press NO to go back to the Edit Account Page");
				if(userOption == JOptionPane.YES_OPTION) {
					for(int i=0; i>=0; i++) {
					String password = JOptionPane.showInputDialog("Enter new password: ");
					String cPassword = JOptionPane.showInputDialog("Confirm your new password: ");
					
					if(isPasswordValid(password, cPassword)) {
						array[1]= password ;
						data.editPassword(array) ;
						JOptionPane.showMessageDialog(null, "Password Chnaged Successfully");
						//code to return to account detials
						break;
					} else
						JOptionPane.showMessageDialog(null,"Your new password does not follow the rules for a valid passwrod. For a password, atleast one letter should be capital and there should be atleast one number.", "Try Again" ,JOptionPane.ERROR_MESSAGE);
				}
			}

			}
			
			if (act.getSource() == emailButton) {
				int userOption = JOptionPane.YES_NO_OPTION;
				userOption = JOptionPane.showConfirmDialog(null ,"Your email is "+array[2]+". To change your email press YES. Press NO to go back to the Edit Account Page");
				
				if(userOption == JOptionPane.YES_OPTION) {
					for(int i=0; i>=0; i++) {
				String email = JOptionPane.showInputDialog("Enter new email: ");
				String cEmail = JOptionPane.showInputDialog("Confirm your new email: ");

					if(isEmailValid(email, cEmail)) {
						array[4] = email ;
						emailLabel.setText(array[4]);
						data.editEmail(array) ;
						JOptionPane.showMessageDialog(null, "Email Chnaged Successfully");
						//code to return to account detials
						break;
					} else
						JOptionPane.showMessageDialog(null,"Your new email is invalid. For a password", "Try Again" ,JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
	/**
	 * method to validate the username
	 * @param name The username that is being checked for validity 
	 * @return flagName boolean of validity
	 */
	public boolean isUserNameValid(String name, String cName) {
		
		boolean flagName = true ;
		for (int i=0; i<name.length(); i++) {
			char ch = name.charAt(i);
			if (Character.isUpperCase(ch)) {
				flagName = false ;
			}
			
		}
		if(!name.equals(cName))
			flagName = false ;
		return flagName;
		
	}
	
	/**
	 * method to validate the password
	 * @param pass password being checked for validity
	 * @return flagPass boolean of validity
	 */
	public boolean isPasswordValid (String pass, String cPass) {
		int countUpper = 0 ;
		int countNumber = 0 ;
		boolean flagPass = true ;
		if(pass.length() >= 6) {
				
			for (int i=0; i<pass.length(); i++) {
				char ch = pass.charAt(i);
				if (Character.isUpperCase(ch))
					countUpper ++;
				if (Character.isDigit(ch))
					countNumber ++;
				
			}
			
			if(!pass.equals(cPass))
				flagPass = false ;
			if(countUpper < 1 || countNumber < 1)
				flagPass = false ;
		}
		return flagPass ; 

	}
	
	/**
	 * method to validate the email
	 * @param email email being checked for validity
	 * @return flagEmail boolean of validity 
	 */
	public boolean isEmailValid (String email, String cEmail) {
		boolean flagEmail = false ;
		
		for(int i=0; i<email.length();i++) {
			char ch = email.charAt(i);
			if(ch == '@')
				flagEmail = true ;
				
			if(!email.equals(cEmail))
				flagEmail = false;
		}
		return flagEmail ; 
		
	}
	
}