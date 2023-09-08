import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is the file to parse the command file
 */
public class CommandFileParser {
	private String fileName;
	private SemManager semManager;

	/**
	 * constructor for the command file parser
	 * 
	 * @param fileName   the name of file to parse
	 * @param semManager the sem manager
	 */
	public CommandFileParser(String fileName, SemManager semManager) {
		this.fileName = fileName;
		this.semManager = semManager;
	}

	/**
	 * Reads the file's commands
	 * 
	 * @throws FileNotFoundException if the file is not found
	 */
	public String readCommands() throws FileNotFoundException {
		File file = new File(fileName);
		Scanner reader = new Scanner(file);
		String ret = "";

		while (reader.hasNextLine()) {
			String string = reader.nextLine().trim();

			if (string.length() == 0) {
				// do nothing, this is a whitespace line
			} 
			else if (string.startsWith("insert")) {
				String[] splitStrings = string.split("\\s+");
				// insert number will be in splitStrings[1]
				int idin = Integer.parseInt(splitStrings[1]);

				String tin = reader.nextLine().trim();

				String[] data = reader.nextLine().trim().split("\\s+");
				String datein = data[0];
				int lin = Integer.parseInt(data[1]);
				short xin = Short.parseShort(data[2]);
				short yin = Short.parseShort(data[3]);
				int cin = Integer.parseInt(data[4]);

				String[] kin = reader.nextLine().trim().split("\\s+");
				String descin = reader.nextLine().trim();

				Seminar seminar = new Seminar(
						idin, tin, datein, lin, xin, yin, cin, kin, descin);

				// Prints the Seminar Object
				System.out.println(seminar.toString());

				// Delegate the handling of the Seminar object to SemManager
				// semManager.insertSeminar(idin, seminar);

			} 
			else if (string.startsWith("delete")) {
				String[] splitStrings = string.split("\\s+");
				// delete number will be in splitStrings[1]
				System.out.println(splitStrings[1]);
				// call the delete(splitStrings[1])
			} 
			else if (string.startsWith("search")) {
				String[] splitStrings = string.split("\\s+");
				// search number will be in splitStrings[1]
				System.out.println(splitStrings[1]);
				// call the search(splitStrings[1])
			} 
			else if (string.startsWith("print")) {
				String[] splitStrings = string.split("\\s+");
				System.out.println(splitStrings[1]);
				// hash or block will be in splitStrings[1]
				// call the print(splitStrings[1])
			}
		}

		reader.close();
		return ret;
	}
}
