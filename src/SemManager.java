
/**
 * {Project Description Here}
 */

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

	public void insertSeminar(int id, Seminar seminar) throws Exception { //done?
		if(hashTable.search(id) != null) {
			System.out.println("Insert FAILED - There "
					+ "is already a record with ID " + id);
			return;
		}
		//if this id is already inserted, stop.
		
		byte[] record = seminar.serialize();
		Handle handle = memoryManager.insert(record, record.length);
		
		hashTable.insert(id, handle);
		System.out.println(
				"Successfully inserted record with ID " + id);
		System.out.println(seminar.toString());
		System.out.println("Size: " + memoryManager.get(record, handle, record.length));
	}
	
	public void deleteSeminar(int id) { //done
		Handle handle = hashTable.delete(id);
		if(handle == null) {
			System.out.println(
					"Delete FAILED -- There is no record with ID " + id);
			return;
		}
		System.out.println(
				"Record with ID " + id + " successfully deleted from the database");
		memoryManager.remove(handle);
	}
	
	public void searchSeminar(int id) throws Exception {
		Handle handle = hashTable.search(id);
		if(handle != null) {
			System.out.println("Found record with ID " + id + ":");
			byte[] arr = new byte[handle.getLength()];
			memoryManager.get(arr, handle, handle.getLength());
			Seminar sem = Seminar.deserialize(arr);
			System.out.println(sem.toString());
		}
		else {
			System.out.println(
					"Search FAILED -- There is no record with ID " + id);
		}
	}
	
	public void printSeminar(String label) { //done
		if(label.equals("hashtable")) {
			System.out.println(hashTable.toString());
		}
		else {
			memoryManager.dump();
		}
	}

	/**
	 * @param args Command line parameters args[2] holds the read file
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		int initialMemorySize = Integer.parseInt(args[0]);
		int initialHashSize = Integer.parseInt(args[1]);

		// This is the main file for the program.
		// commands = insert, delete, search, print 
		SemManager semManager = new SemManager(initialMemorySize, initialHashSize);
		CommandFileParser parser = new CommandFileParser(args[2], semManager);
		parser.readCommands();
	}
}