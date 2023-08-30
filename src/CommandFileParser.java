import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommandFileParser {
    private String fileName;

    public CommandFileParser(String fileName) {
        this.fileName = fileName;
    }

    public void readCommands() {
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            while(reader.hasNextLine()) { 
                String string = reader.nextLine().trim(); 
                 
                if(string.length() == 0) {
                    //do nothing, this is a whitespace line
                }
                else if(string.startsWith("insert")) {
					String[] splitStrings = string.split("\\s+"); //insert number will be in splitStrings[1]
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
					
					Seminar seminar = new Seminar(idin, tin, datein, lin, xin, yin, cin, kin, descin);
					
					//Prints the Seminar Object
					System.out.println(seminar.toString()); // Will call the seminars toString() method
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
                    //this is not a valid command
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

