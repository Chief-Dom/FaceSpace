package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import product.Database;


/**
 * @author Marcus Henke
 * Tests the database portion of editing user accounts
 */
public class EditAccountDatabaseTest {

	//Declare instance variables
		Database db = new Database();
		
		/**
		 * clear the database, then test if the database successfully creates the new user
		 */
		@Test
		public void testCreateUser() {
			db.clearDatabase();
			assertTrue(db.createUser("busterbronco@gmail.com", "buster", "Gobroncos123",  "What is your mother's maiden name ?", "Smith"));
		}
		
		/**
		 * test if changing the user's email will edit the database
		 */
		@Test
		public void testValidEmailChange() {
			String[] arr = db.getUserInfo("busterbronco@gmail.com");
			arr[4] = "busterbronco@boisestate.edu";
			assertTrue(db.editEmail(arr));
			assertEquals(db.getUserInfo("busterbronco@boisestate.edu")[4], "busterbronco@boisestate.edu");
		}
		
		/**
		 * test if changing the user's password will edit the database
		 */
		@Test
		public void testValidPasswordChange() {
			String[] arr = db.getUserInfo("busterbronco@boisestate.edu");
			arr[1] = "Gobroncos456";
			assertTrue(db.editPassword(arr));
			assertEquals(db.getUserInfo("busterbronco@boisestate.edu")[1], "Gobroncos456");
		}
		
		/**
		 * test if changing the user's user-name will edit the database
		 */
		@Test
		public void testValidUsernameChange() {
			String[] arr = db.getUserInfo("busterbronco@boisestate.edu");
			arr[0] = "bronco";
			assertTrue(db.editUsername(arr, "buster"));
			assertEquals(db.getUserInfo("busterbronco@boisestate.edu")[0], "bronco");
		}
		
		
}
