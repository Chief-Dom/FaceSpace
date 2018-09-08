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
	
	JTextField userText = new JTextField(20); 
	JTextField passText = new JTextField(20);
	JLabel userLabel = new JLabel("username: "); 
	JLabel passLabel  = new JLabel("password: ");
	JButton loginButton = new JButton("Login"); 
	JButton registButton = new JButton("Register");
	JFrame frame = new JFrame("FaceSpace.com");
	
	
	public Login() {
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
	public class GUIListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent act) {
			
			if ((act.getSource() == loginButton) && (userText.getText().length() >= 6) && (passText.getText().length() >= 6)) { //if  fields have entered data
				//Attempt to sign user in
				DataBase data = new Database();
				String[] string = new String;
				string = data.login(userText.getText(), passText.getText());
				
				if (string.length() == 4) {
					// Sign user in
					OpenHome home = new OpenHome();
					home.OpenHome(string);
					frame.dispose():
					
				} else if(string.length() == 1){
					//Tell user their credentials were wrong
					String issue = string[0];
					JOptionPane.showMessageDialog(frame, issue);
					passText.setText("");
				} 
			} else if ((act.getSource() == loginButton) && ((userText.getText().length() < 6) || (passText.getText().length() < 6))) { //if either field is empty 
				//Tell user they must fill out both fields
				JOptionPane.showMessageDialog(frame, "Please enter valid usename and password");
				passText.setText("");
			} else if (act.getSource() == registButton) {
				//Take user to register page
				welcomePage registPage = new welcomePage();
				registPage.createPage();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				frame.dispose();
			}
		
		} //End of action method
		
	} //End of GUI Listener class
}