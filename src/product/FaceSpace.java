package product;

/**
 * @author marcushenke
 * This is the driver class for the FaceSpace Application
 */
public class FaceSpace {
	/**
	 * Launches the program
	 * @param args
	 */
	public static void main(String[] args) {
		Database d = new Database();
		Login login = new Login(d);
	}
}
