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
//		userLabel.add(Box.createRigidArea(new Dimension(5,5)));

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
				String userName = JOptionPane.showInputDialog("Enter Username: ");
			}
			
			if (act.getSource() == passButton) {
				
			}
			
			if (act.getSource() == emailButton) {
				
			}
		}
	}
	
}
