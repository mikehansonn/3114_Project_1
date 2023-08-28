
/**
 * {Project Description Here}
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The class containing the main method.
 *
 * @author {Your Name Here}
 * @version {Put Something Here}
 */

// On my honor:
// - I have not used source code obtained from another current or
//   former student, or any other unauthorized source, either
//   modified or unmodified.
//
// - All source code and documentation used in my program is
//   either my original work, or was derived by me from the
//   source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
//   anyone other than my partner (in the case of a joint
//   submission), instructor, ACM/UPE tutors or the TAs assigned
//   to this course. I understand that I may discuss the concepts
//   of this program with other students, and that another student
//   may help me debug my program so long as neither of us writes
//   anything during the discussion or modifies any computer file
//   during the discussion. I have violated neither the spirit nor
//   letter of this restriction.


public class SemManager {
    /**
     * @param args
     *     Command line parameters
     *     args[2] holds the read file
     */
    public static void main(String[] args) {
        // This is the main file for the program.
        // commands = insert, delete, search, print 
        try {
            File file = new File(args[0]);
            Scanner reader = new Scanner(file);

            boolean inSearch = false;
            String inSearchString = "";

            while(reader.hasNextLine()) { 
                String string = reader.nextLine().trim(); 
                if(inSearch) {
                    if(string.startsWith("insert") || string.startsWith("delete")
                        || string.startsWith("search") || string.startsWith("print")) {
                        System.out.println(inSearchString);
                        inSearch = false;
                        inSearchString = "";    
                    }
                    else {
                        inSearchString += string;
                    }
                }
                
                if(string.length() == 0) {
                    //do nothing, this is a whitespace line
                }
                else if(string.startsWith("insert")) {
                    String[] splitStrings = string.split("\\s+"); //insert number will be in splitStrings[1] 
                    System.out.println(splitStrings[1]);
                    //call the delete(splitStrings[1])
                    inSearch = true;
                }
                else if(string.startsWith("delete")) {
                    String[] splitStrings = string.split("\\s+"); //delete number will be in splitStrings[1] 
                    System.out.println(splitStrings[1]);
                    //call the delete(splitStrings[1])
                }
                else if(string.startsWith("search")) {
                    String[] splitStrings = string.split("\\s+"); //search number will be in splitStrings[1] 
                    System.out.println(splitStrings[1]);
                    //call the search(splitStrings[1])
                }
                else if(string.startsWith("print")) {
                    String[] splitStrings = string.split("\\s+");
                    System.out.println(splitStrings[1]); //hash or block will be in splitStrings[1]
                    //call the print(splitStrings[1])
                }
                else {
                    //go to the next line
                }
            }

            reader.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
    }
}
