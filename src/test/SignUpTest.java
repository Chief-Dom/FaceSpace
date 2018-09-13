package test;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;

import product.Database;
import product.Login;
import product.SignUp;
import org.junit.*;


/**
 * @author Andreas Ryeng
 * @author Rohit Gangurde
 */
public class SignUpTest {

	//Declare instance variables
	Database db = new Database();
	SignUp reg = new SignUp(db);
	
	/**
	 * the following method should return false as the username guidelines are not followed
	 */	
	@Test
	public void testInvalidUsername() {
		
		assertFalse(reg.isValidated("ROHIT","Cshu2seven","Cshu2seven","rohit@rohit.com","me"));
		assertFalse(reg.isValidated("Rohit","Cshu2seven","Cshu2seven","rohit@rohit.com","me"));
	}
	
	
	/**
	 * the following method should return false as the password does not follow the guidelines
	 */
	@Test
	public void testInvalidPassword() {
		assertFalse(reg.isValidated("rohit", "cshu2seven","cshu2seven","rohit@rohit.com","me"));
		assertFalse(reg.isValidated("rohit", "Cshutwoseven","Cshutwoseven","rohit@rohit.com","me"));
	}
	
	@Test
	public void testMismatchPassword() {
		assertFalse(reg.isValidated("rohit", "Cshu2seven","CShu2seven","rohit@rohit.com","me"));
	}
	
	
	/**
	 * the following method should return false as there is no "@" in the email
	 * @return 
	 */
	@Test 
	public void testInvalidEmail() {
		assertFalse(reg.isValidated("rohit","Cshu2seven","Cshu2seven","rohitrohit.com","me"));
	}
	
	
	/**
	 * the following method should return false as the security ans field is empty
	 */
	@Test
	public void testEmptyAnsField() {
		assertFalse(reg.isValidated("rohit","Cshu2seven","Cshu2seven","rohit@rohit.com",""));
	}
	
	
	/**
	 * the following method should return true as all the fields are valid
	 */
	@Test
	public void testValidField() {
		assertTrue(reg.isValidated("rohit", "Cshu2seven","Cshu2seven","rohit@rohit.com","me"));

	}

}

	
	