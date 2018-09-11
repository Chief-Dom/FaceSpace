package test;
import product.SignUp; 
import product.Database;
import junit.framework.TestCase;

/**
 * class to test the signup class
 * @author rohitgangurde
 *
 */
public class SignUpTest extends TestCase {
Database db ;
SignUp reg ;

/**
 * calls the methods required for testing
 */
	protected void setUp()throws Exception {
		super.setUp();
		 reg = new SignUp(db);
		 invalidUsernameCheck() ;
		 invalidPasswordCheck();
		 invalidEmailCheck();
		 emptyAnsField();
		 validField();
	}
	
	/**
	 * the following method should return false as the username guidelines are not followed
	 */
	public void invalidUsernameCheck() {
		
		assertFalse(reg.isValidated("ROHIT","Cshu2seven","Cshu2seven","rohit@rohit.com","me"));
		assertFalse(reg.isValidated("Rohit","Cshu2seven","Cshu2seven","rohit@rohit.com","me"));
	}
	
	/**
	 * the following method should return false as the password does not follow the guidelines
	 */
	public void invalidPasswordCheck() {
		assertFalse(reg.isValidated("rohit", "CShu2seven","CShu2seven","rohit@rohit.com","me"));
		assertFalse(reg.isValidated("rohit", "Cshu22seven","Cshu22seven","rohit@rohit.com", "me"));
		assertFalse(reg.isValidated("rohit", "Cshu2seven","CShu2seven","rohit@rohit.com","me"));
	}
	
	/**
	 * the following method should return false as there is no "@" in the email
	 */
	public void invalidEmailCheck() {
		assertFalse(reg.isValidated("rohit","Cshu2seven","Cshu2seven","rohitrohit.com","me"));
	}
	
	/**
	 * the following method should return false as the security ans field is empty
	 */
	public void emptyAnsField() {
		assertFalse(reg.isValidated("rohit","Cshu2seven","Cshu2seven","rohitrohit.com",""));
	}
	
	/**
	 * the following method should return true as all the fields are valid
	 */
	public void validField() {
		assertTrue(reg.isValidated("rohit", "Cshu2seven","Cshu2seven","rohit@rohit.com","me"));

	}
	
	
	
}
