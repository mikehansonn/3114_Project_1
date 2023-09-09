
/**
 * {Project Description Here}
 */

import java.io.FileNotFoundException;

/**
 * The class containing the main method.
 *
 * @author Mike Hanson, Matt Stewart
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

//sem manager -> command parser
//               -> implement DB -> Index Structure -> Hash Table
//                               -> Storage, Memory Manager

public class SemManager {
	private HashTable<Integer, Handle> hashTable;
	private MemManager memoryManager;

	public SemManager(int poolSize, int hashSize) {
		hashTable = new HashTable<>(hashSize);
		memoryManager = new MemManager(poolSize);
	}

	public void insertSeminar(int id, Seminar seminar) {
		Handle handle = null;
		
		hashTable.insert(id, handle);
	}
	
	public void deleteSeminar(int id) {
		Handle handle = hashTable.delete(id);
	}
	
	public void searchSeminar(int id) {
		Handle handle = hashTable.search(id);
		
		//call memmanager to get the byte[] and put back into form
		//the call toString
	}
	
	public void printSeminar(String label) {
		if(label.equals("hashtable")) {
			System.out.println(hashTable.toString());
		}
		else {
			//call memmanager
		}
	}

	/**
	 * @param args Command line parameters args[2] holds the read file
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		int initialMemorySize = Integer.parseInt(args[0]);
		int initialHashSize = Integer.parseInt(args[1]);

		// This is the main file for the program.
		// commands = insert, delete, search, print
		SemManager semManager = new SemManager(initialMemorySize, initialHashSize);
		CommandFileParser parser = new CommandFileParser(args[2], semManager);
		parser.readCommands();
	}
}