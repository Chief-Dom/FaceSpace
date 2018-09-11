package test;
import product.SignUp; 
import product.Database;
import junit.framework.TestCase;


public class SignUpTest extends TestCase {
Database db ;
SignUp reg ;
	protected void setUp()throws Exception {
		super.setUp();
		 reg = new SignUp(db);
		 invalidUsernameCheck() ;
		 invalidPasswordCheck();
		 invalidEmailCheck();
		 emptyAnsField();
		 validField();
	}
	public void invalidUsernameCheck() {
		//the following methods should return false as the username guidelines are not followed
		assertFalse(reg.isValidated("ROHIT","Cshu2seven","Cshu2seven","rohit@rohit.com","me"));
		assertFalse(reg.isValidated("Rohit","Cshu2seven","Cshu2seven","rohit@rohit.com","me"));
	}
	
	public void invalidPasswordCheck() {
		//the following methods should return false as the password does not follow the guidelines
		assertFalse(reg.isValidated("rohit", "CShu2seven","CShu2seven","rohit@rohit.com","me"));
		assertFalse(reg.isValidated("rohit", "Cshu22seven","Cshu22seven","rohit@rohit.com", "me"));
		assertFalse(reg.isValidated("rohit", "Cshu2seven","CShu2seven","rohit@rohit.com","me"));
	}
	
	public void invalidEmailCheck() {
		//the following should return false as there is no "@" in the email
		assertFalse(reg.isValidated("rohit","Cshu2seven","Cshu2seven","rohitrohit.com","me"));
	}
	
	public void emptyAnsField() {
		assertFalse(reg.isValidated("rohit","Cshu2seven","Cshu2seven","rohitrohit.com",""));
	}
	
	public void validField() {
		assertTrue(reg.isValidated("rohit", "Cshu2seven","Cshu2seven","rohit@rohit.com","me"));

	}
	
	
	
}
