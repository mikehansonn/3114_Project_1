import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParser {
    private String fileName;

    public FileParser(String fileName) {
        this.fileName = fileName;
    }

    public void readCommands() {
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            int counter = 1;

            while(reader.hasNextLine()) { 
                String string = reader.nextLine().trim(); 
                
                if(string.length() == 0) {
                    //do nothing, this is a whitespace line
                }
                else if(string.startsWith("insert")) {
                    String[] splitStrings = string.split("\\s+"); //insert number will be in splitStrings[1] 
                    System.out.println(splitStrings[1]);
                    String[] data = { splitStrings[1], "", "", "", "" };

                    while(counter < 5) {
                        data[counter] = reader.nextLine().trim();
                        System.out.println(data[counter]);
                        counter++;
                    }
                    //call the insert(data)
                    counter = 1;
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
