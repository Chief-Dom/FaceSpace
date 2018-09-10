package product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Database {

	private final File DATABASE = new File("database.txt");
	private HashMap<String, String[]> AccountList = new HashMap<String, String[]>();
	private HashMap<String, String> listOfUsers = new HashMap <String, String>();
	
	public Database() {
		parseDatabase();
	}
	
	public boolean createUser(String email, String username, String password, String secQ, String secA) {
		if (doesUserExist(username)) {
			return false;
		}
		else {
			addUser(email, username, password, secQ, secA);
			return true;
		}
	}
	
	public String[] login(String username, String password) {
		String[] arr = new String[1];
		if (!doesUserExist(username)) {
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
	 * Returns an array of Strings containing the 
	 * given user's info
	 * [0]username
	 * [1]password
	 * [2]security question
	 * [3]security question answer 
	 * @param username
	 * @return String array
	 */
	public String[] getUserInfo(String email) {
		if (!doesUserExist(this.listOfUsers.get(email))) {
			return new String[1];
		}
		else {
			return this.AccountList.get(this.listOfUsers.get(email));
		}
	}
	
	public String[] getUserInfoWithUsername(String username) {
		if (!doesUserExist(username)) {
			return new String[1];
		}
		else {
			return this.AccountList.get(username);
		}
	}
	
	private void parseDatabase() {
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
					
					AccountList.put(username, arr);
					listOfUsers.put(email, username);
				}
				
			}
		}
		catch(Exception e) {
			System.out.println("error");
		}
		
	}
	
	public boolean doesUserExist(String username) {
		if (AccountList.containsKey(username)) {
			return true;
		}
		else return false;
	}
	
	private void addUser(String email, String username, String password, String secQ, String secA) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATABASE, true))) {
			String[] arr = new String[5];
			arr[0] = username;
			arr[1] = password;
			arr[2] = secQ;
			arr[3] = secA;
			arr[4] = email;
			AccountList.put(username, arr);
			listOfUsers.put(username, email);

			String content = "\n$\n" + username + "\n" + password + "\n" + secQ + "\n" + secA + "\n" + email; 

			bw.write(content);
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void thing() {
		System.out.println(AccountList.get("Gary")[2]);
	}
	
	
}
