This is Version 2 of FaceSpace

-------Authors-------
Marcus Henke 
Dominik Huffield 
Rohit Gangurde 
Andreas Ryeng

------Requirements------ 
The test package in this application requires JUnit 4 to operate correctly.

-----Compiling------
To compile, navigate to the directory where the .java files are located, then
type:

javac FaceSpace.java

then to run, type:

java FaceSpace

Please ensure that your database.txt file is in the same directory as your .java files

------Added Features------

Forgot Username: This is an additional feature to the Login GUI. Represented as a button when the user clicks 'Forgot Username' the user is prompted for their email. The email is then run through the database class and if there is a matching account it will present that accounts username to the user.


Edit Account: This feature is a new GUI in itself which displays the current accounts username and email. The GUI also contains buttons for editing the accounts username, email and password. The GUI also contains buttons to take the user back to the home screen or to log them out and return to the login window.

---------------------------

