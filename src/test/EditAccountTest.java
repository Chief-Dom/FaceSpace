package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import product.AcctDetails;
import product.Database;
import org.junit.*;


/**
 * Test class for testing EditAccount capabilities.
 * Requires JUnit 4
 * @author Andreas Ryeng
 */
public class EditAccountTest {


	//Declare instance variables
	Database db = new Database();
	private String[] array = new String[] {"busterbronco@gmail.com", "buster", "Gobroncos123",  "What is your mother's maiden name ?", "Smith"};
	AcctDetails acctDetails = new AcctDetails(array, db);
	

	/**
	 * test if username check returns true with various valid inputs
	 */
	@Test
	public void testIsUserNameValidValidInput() {
		assertTrue(acctDetails.isUserNameValid("buster", "buster"));
		assertTrue(acctDetails.isUserNameValid("rohit", "rohit"));
		assertTrue(acctDetails.isUserNameValid("buster", "buster"));
	}
	
	/**
	 * test if username check returns true with various valid inputs
	 */
	@Test
	public void testIsUserNameValidInvalidInput() {
		assertFalse(acctDetails.isUserNameValid("newBuster", "buster"));
		assertFalse(acctDetails.isUserNameValid("rohit", "andreas"));
		assertFalse(acctDetails.isUserNameValid("buster", "newbuster"));
	}
	
	/**
	 * test if password check returns true with various valid inputs
	 */
	@Test
	public void testIsPasswordValidValidInput() {
		assertTrue(acctDetails.isPasswordValid("Buster1", "Buster1"));
		assertTrue(acctDetails.isPasswordValid("roHit1", "roHit1"));
		assertTrue(acctDetails.isPasswordValid("Passw0rd", "Passw0rd"));
		assertTrue(acctDetails.isPasswordValid("pAssw0rd", "pAssw0rd"));
		assertTrue(acctDetails.isPasswordValid("paSsw0rd", "paSsw0rd"));
		assertTrue(acctDetails.isPasswordValid("obnoxiouslyLongPassword1337", "obnoxiouslyLongPassword1337"));
	}
	
	/**
	 * test if password check returns false with various invalid inputs
	 */
	@Test
	public void testIsPasswordValidInvalidInput() {
		assertFalse(acctDetails.isPasswordValid("Passw0rd", "password"));
		assertFalse(acctDetails.isPasswordValid("Passw0rd", "Password"));
		assertFalse(acctDetails.isPasswordValid("Passw0rd", "passw0rd"));		
		assertFalse(acctDetails.isPasswordValid("password", "Passw0rd"));		
		assertFalse(acctDetails.isPasswordValid("password", "Password"));
		assertFalse(acctDetails.isPasswordValid("password", "passw0rd"));
		assertFalse(acctDetails.isPasswordValid("passw0rd", "Passw0rd"));		
		assertFalse(acctDetails.isPasswordValid("passw0rd", "Password"));
		assertFalse(acctDetails.isPasswordValid("passw0rd", "password"));
		assertFalse(acctDetails.isPasswordValid("P", "Password"));
		assertFalse(acctDetails.isPasswordValid("Psw0d", "Psw0d"));
		assertFalse(acctDetails.isPasswordValid("Passw0d", "Psw0d"));
	}
	
	/**
	 * test if email check returns true with various valid inputs
	 */
	@Test
	public void testIsEmailValidValidInput() {
		assertTrue(acctDetails.isEmailValid("buster@buster.com", "buster@buster.com"));
		assertTrue(acctDetails.isEmailValid("rohit@rohit.com", "rohit@rohit.com"));
		assertTrue(acctDetails.isEmailValid("extremelylongandobnoxiousemailaddress@email.com", "extremelylongandobnoxiousemailaddress@email.com"));
	}
	
	/**
	 * test if email check returns false with various invalid inputs
	 */
	@Test
	public void testIsEmailValidInvalidInput() {
		assertFalse(acctDetails.isEmailValid("buster@buster.com", "buster@rohit.com"));
		assertFalse(acctDetails.isEmailValid("buster.com", "buster@rohit.com"));
		assertFalse(acctDetails.isEmailValid("b", "b"));
		assertFalse(acctDetails.isEmailValid("buster.com", "buster.com"));
		assertFalse(acctDetails.isEmailValid("@buster.com", "@buster.com"));
		assertFalse(acctDetails.isEmailValid("buster@buster@buster.com", "buster@buster@buster.com"));
		assertFalse(acctDetails.isEmailValid("rohit@rohit.com", "rohit.com"));
		assertFalse(acctDetails.isEmailValid("extremelylongandobnoxiousemailaddress", "extremelylongandobnoxiousemailaddress@email.com"));
	}
	
}
