import javax.swing.JFrame;

public class register {
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Welcome");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcomePage panel = new welcomePage();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}


