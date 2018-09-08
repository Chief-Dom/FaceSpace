import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class welcomePage extends JPanel{

	private static JFrame frame = new JFrame("Welcome");
	private static JButton registerButton = new JButton ("Register");
	private static JButton cancelButton = new JButton ("Cancel");
	
	private static JTextField usernameField = new JTextField (6);
	private static JTextField passwordField = new JTextField (6);
	private static JTextField confirmPasswordField = new JTextField (6) ;
	private static JTextField securityAns = new JTextField (6) ;
	
	private static JComboBox securityQ ;
	
	private static JLabel userLabel = new JLabel ("username");
	private static JLabel passwordLabel = new JLabel ("password");
	private static JLabel confirmPasswordLabel = new JLabel ("Confirm Password");
	private static JLabel securityQuestion = new JLabel ("Security Question");
	private static JLabel securityAnsLabel = new JLabel ("Security Answer");
	

	
	
	public welcomePage ()
	{
		this.setLayout (new BorderLayout());
		this.setPreferredSize(new Dimension(400, 400));
		JPanel panel = new JPanel ();
		String question[] = {"What is your mother's maiden name ?", "What is the name of your first dog ?", "What is the name of your first school ?"};
		securityQ = new JComboBox (question) ;
		
		
		
		registerButton.addActionListener(new RegisterListener());
		securityQ.addActionListener(new SecurityListener());
		
		panel.add(Box.createHorizontalGlue());
		panel.add(userLabel, BorderLayout.WEST);
		panel.add(usernameField);
		panel.add(Box.createHorizontalGlue());
		panel.add(passwordLabel, BorderLayout.WEST);
		panel.add(Box.createHorizontalGlue());
		panel.add(confirmPasswordLabel, BorderLayout.WEST);
		panel.add(Box.createHorizontalGlue());
		panel.add(usernameField, BorderLayout.WEST);
		panel.add(Box.createHorizontalGlue());
		panel.add(passwordField, BorderLayout.WEST);
		panel.add(Box.createHorizontalGlue());
		panel.add(confirmPasswordField, BorderLayout.WEST);
		panel.add(Box.createHorizontalGlue());
		panel.add(registerButton, BorderLayout.WEST) ;
		panel.add(Box.createHorizontalGlue());
		panel.add(securityQ, BorderLayout.WEST);
		panel.add(Box.createHorizontalGlue());
		panel.add(securityAnsLabel, BorderLayout.WEST);
		panel.add(Box.createHorizontalGlue());
		panel.add(registerButton, BorderLayout.CENTER);
		panel.add(cancelButton, BorderLayout.CENTER) ;

		this.add(panel, BorderLayout.CENTER);
	
		
	}

	private class RegisterListener implements ActionListener
	{
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	}
	
	private class SecurityListener implements ActionListener
	{
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	}
	
	
	
	
	
	

}

