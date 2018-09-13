package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;

import product.Database;
import product.SignUp;
import org.junit.*;
import org.junit.Assert;
import org.junit.runners.MethodSorters;


/**
 * @author Marcus Henke
 * @author Andreas Ryeng
 * @author Rohit Gangurde
 */
public class SignUpDatabaseTest {

	//Declare instance variables
	Database db = new Database();
	SignUp reg = new SignUp(db);
	
	
	/**
	 * Create a new user with valid parameters
	 */
	@Test
	public void testCreateUser() {
		db.clearDatabase();
		assertTrue(db.createUser("busterbronco@gmail.com", "buster", "Gobroncos123",  "What is your mother's maiden name ?", "Smith"));
	}
	
	
	/**
	 * Create a new user with used email/user name
	 */
	@Test
	public void testCreateUserWithUsedEmailOrUsername() {
		
		//Attempt to create an account with the exact same information
		assertFalse(db.createUser("busterbronco@gmail.com", "bronco", "Gobroncos123",  "What is your mother's maiden name ?", "Smith"));
		
		//Attempt to create an account with an existing email address
		assertFalse(db.createUser("broncos@gmail.com", "buster", "Gobroncos123",  "What is your mother's maiden name ?", "Smith"));
		
		//Attempt to create an account with an existing username
		assertFalse(db.createUser("busterbronco@gmail.com", "buster", "Gobroncos123",  "What is your mother's maiden name ?", "Smith"));
	}

	
	/**
	 * Check if the created user exists in the database
	 * getUserInfo methods return a String array of length 5 if the user exists
	 */
	@Test
	public void testFindingExistingUser() {
		assertEquals(db.getUserInfo("busterbronco@gmail.com").length, 5);
		assertEquals(db.getUserInfoWithUsername("buster").length, 5);
	}
	
	@Test 
	public void testFindingExistingUserUsername() {
		assertEquals(db.getUserInfo("busterbronco@gmail.com")[0], "buster");
		assertEquals(db.getUserInfoWithUsername("buster")[0], "buster");
	}
	
	@Test 
	public void testFindingExistingUserPassword() {
		assertEquals(db.getUserInfo("busterbronco@gmail.com")[1], "Gobroncos123");
		assertEquals(db.getUserInfoWithUsername("buster")[1], "Gobroncos123");
	}
	
	@Test 
	public void testFindingExistingUserSecQ() {
		assertEquals(db.getUserInfo("busterbronco@gmail.com")[2], "What is your mother's maiden name ?");
		assertEquals(db.getUserInfoWithUsername("buster")[2], "What is your mother's maiden name ?");
	}
	
	@Test 
	public void testFindingExistingUserSecA() {
		assertEquals(db.getUserInfo("busterbronco@gmail.com")[3], "Smith");
		assertEquals(db.getUserInfoWithUsername("buster")[3], "Smith");
	}
	
	@Test 
	public void testFindingExistingUserEmail() {
		assertEquals(db.getUserInfo("busterbronco@gmail.com")[4], "busterbronco@gmail.com");
		assertEquals(db.getUserInfoWithUsername("buster")[4], "busterbronco@gmail.com");
	}
	
	/**
	 * Ensure the database can't find a non-existent user
	 * getUserInfo methods return a String array of length 1 if the user does not exist  
	 */
	@Test
	public void testFindingNonExistentUser() {
		assertEquals(db.getUserInfo("broncos@gmail.com").length, 1);
		assertEquals(db.getUserInfoWithUsername("bronco").length, 1);
		assertEquals(db.getUserInfo("").length, 1);
		assertEquals(db.getUserInfoWithUsername("").length, 1);
		
	}
	

}
