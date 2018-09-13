This is Version 1 of FaceSpace.

----------------------------------------------------------
Authors:
Marcus Henke
Dominik Huffield
Rohit Gangurde
Andreas Ryeng
----------------------------------------------------------
Requirements:
The test package in this application requires 
JUnit 4 to operate correctly.
----------------------------------------------------------

Version 1 Features:

Sign Up
	Users can create a new account for FaceSpace with a 
	unique username and email. Passwords must contain at 
	least one upper case letter, one number, and one 
	lowercase letter. Accounts will be saved in a database 
	text file when the users exit the application.
	
Log In
	Users can log in to their existing accounts with their
	username and password. Once logged in, a welcome message 
	is displayed and the user can log out.
	
Forgot Password
	If a user has forgotten their password, the user can 
	ttempt to recover their password with their
	email address. If a valid email address is used, the 
	account's security question will be displayed. If the 
	user enters the correct answer, their password will be 
	given to them and they can attempt to log in.
	
-----------------------------------------------------------

Upcoming Features:

Forgot Username
	If a user has forgotten their username, the user can 
	choose to attempt to recover their username with their
	email address. If a valid email address is used, the 
	account's security question will be displayed. If the 
	user enters the correct answer, their username will be 
	given to them and they can attempt to log in.
	
Edit Account
	Logged in users will have the opportunity to edit their 
	account information. Only the user's username, password, 
	and email can be changed. The security question and answer
	will not be allowed to be changed for the user's protection
	
-----------------------------------------------------------