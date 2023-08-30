# 3114_Project_1

ALL THAT MATTERs TO READ IS THE FIRST NOTES AND CODE EXAMPLES AT THE BOTTOM

Project 1 Tasks: 
Create Record class 
Send Seminar into SemManager



Project 1 Notes: 


SemManager is the main class that initializes the Database and CommandFileParser.


CommandFileParser reads commands from a file and creates Seminar objects, which it then passes to SemManager for further handling.


Database is responsible for database-related operations like inserting a Seminar objects The Database class acts as a mediator between the Hash table and Memory Manager, ensuring they remain decoupled. 


- The record class (handle) will just have initialized a starting point and a length, which it will be given in the memory manager. 

- Get the data from the paraser and turn the parser data into a seminar object, then you can call the function to turn the seminar object into byte data. 


- When Fetching data the DB goes to the Hash table and looks for the handle and then takes that handle to fetch the byte data from the memory manager and then you convert it back at the end. 


- when looking to insert or get or remove you hit the hash table first to look for the handle. 


- The ID in the hash table is the key, and the data is the Handle

- The handle is a class that is used between the DB and the Memory manager; it is what is used to grab the memory. The handle is the Location and length. 


- Use a pseudo linked list for the memory manager (I believe it is actually just an array) 
