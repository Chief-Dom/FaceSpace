package test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;

import product.Database;
import product.Login;
import product.SignUp;
import org.junit.*;


/**
 * Test class for testing Login capabilities.
 * Requires JUnit 4
 * @author Andreas Ryeng
 * @author Marcus Henke
 * @author Dominik Huffield
 */
public class LoginTest {

	//Declare instance variables
	Database db = new Database();
	Login login = new Login(db);
	
	/**
	 * clear the database, then test if the database successfully creates the new users
	 */
	@Test
	public void testCreateUsers() {
		db.clearDatabase();
		assertTrue(db.createUser("busterbronco@gmail.com", "buster", "Gobroncos123",  "What is your mother's maiden name ?", "Smith"));
		assertTrue(db.createUser("butchcougar@gmail.com", "butchy", "Gocougs123",  "What is your mother's maiden name ?", "Smith"));
	}
	
	/**
	 * test if an invalid input returns the correct string length
	 */
	@Test
	public void testInvalidLogin() {
		assertEquals(db.login("buster", "Gocougs123").length, 1);
		assertEquals(db.login("butchy", "Gobroncos123").length, 1);
		assertEquals(db.login("marcus", "Gocougs123").length, 1);
		assertEquals(db.login("dom", "Gobroncos123").length, 1);
	}
	
	/**
	 * test if an invalid password input returns the correct error
	 */
	@Test
	public void testInvalidLoginPasswords() {
		assertEquals(db.login("buster", "Gocougs123")[0], "Password does not match");
		assertEquals(db.login("butchy", "Gobroncos123")[0], "Password does not match");
	}
	
	/**
	 * test if an invalid user-name input returns the correct error
	 */
	@Test
	public void testInvalidLoginUsernames() {
		assertEquals(db.login("marcus", "Gocougs123")[0], "Username does not exist");
		assertEquals(db.login("dom", "Gobroncos123")[0], "Username does not exist");
	}
	
	/**
	 * test if a valid login input returns the correct String[] length
	 */
	@Test
	public void testValidLogin() {
		assertEquals(db.login("buster", "Gobroncos123").length, 5);
		assertEquals(db.login("butchy", "Gocougs123").length, 5);
		
	}
	
	/**
	 * test if a valid login input returns the correct email address
	 */
	@Test
	public void testValidLoginEmails() {
		assertEquals(db.login("buster", "Gobroncos123")[4], "busterbronco@gmail.com");
		assertEquals(db.login("butchy", "Gocougs123")[4], "butchcougar@gmail.com");
	}
	
	/**
	 * test if a valid login input returns the correct user-name
	 */
	@Test
	public void testValidLoginUsernames() {
		assertEquals(db.login("buster", "Gobroncos123")[0], "buster");
		assertEquals(db.login("butchy", "Gocougs123")[0], "butchy");
	}
	
	/**
	 * test if a valid login input returns the correct password
	 */
	@Test
	public void testValidLoginPasswords() {
		assertEquals(db.login("buster", "Gobroncos123")[1], "Gobroncos123");
		assertEquals(db.login("butchy", "Gocougs123")[1], "Gocougs123");
	}
	
	/**
	 * test if a valid login input returns the correct security question
	 */
	@Test
	public void testValidLoginSecQ() {
		assertEquals(db.login("buster", "Gobroncos123")[2], "What is your mother's maiden name ?");
		assertEquals(db.login("butchy", "Gocougs123")[2], "What is your mother's maiden name ?");
	}
	
	/**
	 * test if a valid login input returns the correct security answer
	 */
	@Test
	public void testValidLoginSecA() {
		assertEquals(db.login("buster", "Gobroncos123")[3], "Smith");
		assertEquals(db.login("butchy", "Gocougs123")[3], "Smith");
	}
	
	
	

}

