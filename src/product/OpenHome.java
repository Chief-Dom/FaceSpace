import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class OpenHome {

	
	private JFrame frame = new JFrame("Your FaceSpace Home");
	
	
	public OpenHome(String[] array) {
		//frame.setLayout(new GridLayout(2, 1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 400));
		
		JLabel welcome = new JLabel("Welcome " + array[0] + "this is FaceSpace");
		
		JPanel basePane = new JPanel();
		basePane.setLayout(new BoxLayout(basePane, BoxLayout.PAGE_AXIS));
		basePane.setPreferredSize(new Dimension(600, 400));
		
		JPanel textPane = new JPanel();
		textPane.setLayout(new BoxLayout(textPane, BoxLayout.LINE_AXIS));
		textPane.add(welcome);
		
		JButton acctDet = new JButton("Account Details");
		JButton loggout = new JButton("Loggout");
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(acctDet);
		buttonPane.add(loggout);
		
		basePane.add(textPane, BorderLayout.NORTH);
		basePane.add(buttonPane, BorderLayout.NORTH);
		
		frame.getContentPane().add(basePane, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		
		
		
	}
}
