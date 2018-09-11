package product;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;


public class Login {
	private int appWidth = 400; 
	private int appHeight = 400;
	
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
	JFrame frame = new JFrame("FaceSpace.com");
	
	
	public Login(Database d) {
		data = d;
		//Creates Window Frame
		//JFrame frame = new JFrame("FaceSpace.com");
		frame.setLayout(new GridLayout(3,1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setResizable(false);
		frame.setLocation(xMid, yMid);
		
		
		//Creates Listener
		GUIListener listen = new GUIListener();
		loginButton.addActionListener(listen);
		registButton.addActionListener(listen);
		forgotPassButton.addActionListener(listen);
		
				
		//User text field Panel
		JPanel userPanel = new MakePanel();
		userPanel.add(userLabel);
		userPanel.add(userText);
		
		
		//Password text Panel
		JPanel passPanel = new MakePanel();
		passPanel.add(passLabel);
		passPanel.add(passText);
		
		//Button Panel
		JPanel buttonPanel = new MakePanel();
		buttonPanel.add(registButton);
		buttonPanel.add(loginButton);
		buttonPanel.add(forgotPassButton);
		
		
		
		//Add contents to frame
		frame.getContentPane().add(userPanel, BorderLayout.PAGE_START);
		frame.getContentPane().add(passPanel, BorderLayout.NORTH);
		frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
		
	
	}
	

	public class MakePanel extends JPanel {
		private JPanel makePanel() {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
			panel.setPreferredSize(new Dimension(400, 150));
			panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 50));
			
			return panel;
			
		}
		
	}
	private class GUIListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent act) {
			
			if (act.getSource() == forgotPassButton) {
				forgotPassword();
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
		
	} //End of GUI Listener class
	
	
	
}