package product;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * Class to create a GUI of the home page of FaceSpace
 * Welcomes the user and allows them to view their account 
 * details or to logout and return to the login page
 * 
 * @author Dominik Huffield
 *
 */
public class OpenHome {

	private int appWidth = 400; 
	private int appHeight = 400;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenWidth = (int) screenSize.getWidth();
	private int screenHeight = (int) screenSize.getHeight();
	private int xMid = screenWidth/2 - appWidth;
	private int yMid = screenHeight/2 - appHeight/2;
	
	JButton acctDet = new JButton("Account Details");
	JButton logout = new JButton("Logout");
	Database db;
	
	
	private JFrame frame = new JFrame("Your FaceSpace Home");
	
	
	/**
	 * The constructor for the home page 
	 * 
	 * @param array Contains string values at each index with information about the user  
	 * @param db The database class that saves and accesses user account information 
	 */
	public OpenHome(String[] array, Database db) {
		this.db = db;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 400));
		frame.setLocation(xMid, yMid);
		frame.setResizable(false);
		
		JLabel welcome = new JLabel("Welcome to FaceSpace, " + array[0] + "!");
		
		JPanel basePane = new JPanel();
		basePane.setLayout(new BoxLayout(basePane, BoxLayout.PAGE_AXIS));
		basePane.setPreferredSize(new Dimension(600, 200));
		
		GUIListener listen = new GUIListener();
		logout.addActionListener(listen);
		acctDet.addActionListener(listen);
		
		JPanel textPane = new JPanel();
		textPane.setLayout(new BoxLayout(textPane, BoxLayout.LINE_AXIS));
		textPane.add(welcome);
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(acctDet);
		buttonPane.add(Box.createRigidArea(new Dimension(5,0)));
		buttonPane.add(logout);
		
		
		Dimension windowSize = frame.getBounds().getSize();
		
		double heightSize = windowSize.getHeight();
		Dimension fillerSize = new Dimension((int)windowSize.getWidth(), (int)heightSize/3);
		Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
		Container container = frame.getContentPane();
		
		basePane.add(Box.createRigidArea(new Dimension(600,150)));
		basePane.add(textPane, BorderLayout.NORTH);
		basePane.add(buttonPane, BorderLayout.NORTH);
		
		container.add(basePane, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	/**
	 * The action listener class for the buttons
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
		
			if (act.getSource() == logout) {
				Login login = new Login(db);
				frame.dispose();
			} else if (act.getSource() == acctDet) {
				//Make account details constructor 
				//frame.dispose();
			}
		}
	}	
}