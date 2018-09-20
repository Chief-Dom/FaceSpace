package product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This is the class that stores user account information. User information from other 
 * classes are sent and retrieved through this class. Information is stored in two linked HashMaps,
 * where the Information is initially read in from the database.txt file.
 * @author Marcus Henke
 */
public class Database {
	
	/*------DECLARE INSTANCE VARIABLES-----
	The database file where user information is stored */
	private final File DATABASE = new File("database.txt");
	//HashMap containing a user-name Key (String) and an Object holding account information (String[])
	private HashMap<String, String[]> accountList;
	//HashMap containing an email Key (String) and a user-name Object (String)
	private HashMap<String, String> listOfUsers;
	
	
	/**
	 * Constructor for the Database class
	 */
	public Database() {
		accountList = new HashMap<String, String[]>();
		listOfUsers = new HashMap <String, String>();
		parseDatabase();
	}
	
	
	/**
	 * Returns true if the user was successfully added
	 * to the database and false if the user already exists
	 * @param email
	 * @param username
	 * @param password
	 * @param secQ
	 * @param secA
	 * @return boolean
	 */
	public boolean createUser(String email, String username, String password, String secQ, String secA) {
		if (doesUserExist(username, email)) {
			return false;
		}
		else {
			addUser(email, username, password, secQ, secA);
			return true;
		}
	}
	
	
	/**
	 * Attempts to log the user in. If the user-name and password match in the database, the method returns a String[] with the 
	 * user's account info. If the user doesn't exist or the passwords don't match, the method returns
	 * a String[] of length 1 containing the corresponding error message.
	 * @param username
	 * @param password
	 * @return String[]
	 */
	public String[] login(String username, String password) {
		String[] arr = new String[1];
		if (!doesUserExist(username, " ")) {
			arr[0] = "Username does not exist";
			return arr;
					
		}
		else if(password.equals(getUserInfoWithUsername(username)[1])) {
			return getUserInfoWithUsername(username);
		}
		else {
			arr[0] = "Password does not match";
			return arr;
		}
	}
	
	
	/**
	 * Deletes all data in the database.
	 */
	public void clearDatabase() {
		this.listOfUsers = new HashMap<String, String>();
		this.accountList = new HashMap<String, String[]>();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATABASE))) {

			String content = ""; 
			bw.write(content);
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Edits the specified account's user-name in the database.txt file, 
	 * then re-parses the text file to store new data in the HashMaps.
	 * Returns true if the edit was successful and false if otherwise.
	 * @param newUser
	 * @param oldUsername
	 * @return boolean
	 */
	public boolean editUsername(String[] newUser, String oldUsername) {
		try {
			ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("database.txt"), StandardCharsets.UTF_8));

			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).equals(oldUsername)) {
			        fileContent.set(i, newUser[0]);
			        Files.write(Paths.get("database.txt"), fileContent, StandardCharsets.UTF_8);
			        break;
			    }
			}
			parseDatabase();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Edits the specified account's password in the database.txt file, 
	 * then re-parses the text file to store new data in the HashMaps.
	 * Returns true if the edit was successful and false if otherwise.
	 * @param newUser
	 * @return boolean
	 */
	public boolean editPassword(String[] newUser) {
		try {
			ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("database.txt"), StandardCharsets.UTF_8));

			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).equals(newUser[0])) {
			        fileContent.set(i+1, newUser[1]);
			        Files.write(Paths.get("database.txt"), fileContent, StandardCharsets.UTF_8);
			        break;
			    }
			}
			parseDatabase();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Edits the specified account's email in the database.txt file, 
	 * then re-parses the text file to store new data in the HashMaps.
	 * Returns true if the edit was successful and false if otherwise.
	 * @param newUser
	 * @return boolean
	 */
	public boolean editEmail(String[] newUser) {
		try {
			ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("database.txt"), StandardCharsets.UTF_8));

			for (int i = 0; i < fileContent.size(); i++) {
			    if (fileContent.get(i).equals(newUser[0])) {
			        fileContent.set(i+4, newUser[4]);
			        Files.write(Paths.get("database.txt"), fileContent, StandardCharsets.UTF_8);
			        break;
			    }
			}
			parseDatabase();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	
	/**
	 * Attempts to retrieve the user's account info. if the email exists in the database,
	 * the method returns a String[] of length 5 with the corresponding account information.
	 * If the email doesn't exist, the method returns a String[] of length 1 containing null.
	 * @param email
	 * @return String[]
	 */
	public String[] getUserInfo(String email) {
		if (!doesUserExist(this.listOfUsers.get(email), email)) {
			return new String[1];
		}
		else {
			return this.accountList.get(this.listOfUsers.get(email));
		}
	}
	
	
	/**
	 * Attempts to retrieve the user's account info. if the user-name exists in the database,
	 * the method returns a String[] of length 5 with the corresponding account information.
	 * If the user-name doesn't exist, the method returns a String[] of length 1 containing null.
	 * @param username
	 * @return String[]
	 */
	public String[] getUserInfoWithUsername(String username) {
		if (!doesUserExist(username, "")) {
			return new String[1];
		}
		else {
			return this.accountList.get(username);
		}
	}
	
	/**
	 * Parses through the database.txt file and stores the user account information
	 * into the HashMaps. Different accounts in the database.txt file are separated by '$'.
	 */
	private void parseDatabase() {
		accountList.clear();
		listOfUsers.clear();
		try {
			Scanner scan = new Scanner(DATABASE);
			while (scan.hasNextLine()) {
				if (scan.nextLine().equals("$")) {
					String username = scan.nextLine();
					String password = scan.nextLine();
					String securityQ = scan.nextLine();
					String securityA = scan.nextLine();
					String email = scan.nextLine();
					
					String[] arr = new String[5];
					arr[0] = username;
					arr[1] = password;
					arr[2] = securityQ;
					arr[3] = securityA;
					arr[4] = email;
					
					accountList.put(username, arr);
					listOfUsers.put(email, username);
				}
				
			}
			scan.close();
		}
		//  An exception should never be caught if the program is
		//  ran with the database.txt file
		catch(Exception e) {
			
		}
		
	}
	
	
	/**
	 * Checks if the given username or email exist in the database. Returns
	 * true if the user exists and false if they do not exist.
	 * @param username
	 * @param email
	 * @return boolean
	 */
	private boolean doesUserExist(String username, String email) {
		if (accountList.containsKey(username) || listOfUsers.containsKey(email)) {
			return true;
		}
		else return false;
	}
	
	
	/**
	 * Writes the new user account information to the database and stores the information
	 * inside of the HashMaps.
	 * @param email
	 * @param username
	 * @param password
	 * @param secQ
	 * @param secA
	 */
	private void addUser(String email, String username, String password, String secQ, String secA) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATABASE, true))) {
			String[] arr = new String[5];
			arr[0] = username;
			arr[1] = password;
			arr[2] = secQ;
			arr[3] = secA;
			arr[4] = email;
			accountList.put(username, arr);
			listOfUsers.put(email, username);

			String content = "\n$\n" + username + "\n" + password + "\n" + secQ + "\n" + secA + "\n" + email; 

			bw.write(content);
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
