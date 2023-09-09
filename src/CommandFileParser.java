import java.io.File;
import java.util.Scanner;

/**
 * This is the file to parse the command file
 * 
 * @author mikehanson
 * @version 9/8/23
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
	 * @return returns the string of the parse
	 * @throws Exception 
	 */
	public String readCommands() throws Exception { 
		File file = new File(fileName);
		Scanner reader = new Scanner(file);
		String ret = "";

		while (reader.hasNextLine()) {
			String string = reader.nextLine().trim();

			if (string.startsWith("insert")) {
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
				ret += seminar.toString() + "\n";

				semManager.insertSeminar(idin, seminar);

			} 
			else if (string.startsWith("delete")) {
				String[] splitStrings = string.split("\\s+");
				ret += splitStrings[1] + "\n";
				
				int idin = Integer.parseInt(splitStrings[1]);
				semManager.deleteSeminar(idin);
			} 
			else if (string.startsWith("search")) {
				String[] splitStrings = string.split("\\s+");
				ret += splitStrings[1] + "\n";
				
				int idin = Integer.parseInt(splitStrings[1]);
				semManager.searchSeminar(idin);
			} 
			else if (string.startsWith("print")) {
				String[] splitStrings = string.split("\\s+");
				ret += splitStrings[1] + "\n";
				
				semManager.printSeminar(splitStrings[1]);
			}
		}

		reader.close();
		return ret;
	}
}
