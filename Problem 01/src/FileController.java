import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class FileController {
    private static final String path = "src/database.txt";
    private static HashMap<String, Integer> classMap;
    static {
        classMap = new HashMap<>();
        classMap.put("class8", 0);
        classMap.put("class9", 1);
        classMap.put("class10", 2);
    }

    public void write(String line) {
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(line);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    private String[] getLines(String line){
        String[] lines = line.split("\n");
        if(lines.length == 1 && lines[0].length() == 0){
            write("class8\nclass9\nclass10\n");
            lines = new String[]{"class8", "class9", "class10"};
        }
        else if(lines.length != 3){
            String[] temp = {"class8", "class9", "class10"};
            for(int i=0; i< lines.length; i++) temp[i] = lines[i];
            return temp;
        }
        return lines;
    }

    public void save(String classInfo, String className){
        String line = read();
        String[] lines = getLines(line);
        lines[classMap.get(className)] = classInfo;
        line = String.join("\n", lines) + "\n";
        write(line);
    }

    public String read() {
        String data = "";
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = data.concat(myReader.nextLine() + "\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exit in the expected directory.");
        }
        return data;
    }

    public String[] load(String className){
        String line = read();
        String[] lines = getLines(line);
        return lines[classMap.get(className)].split("\t\t\t\t");
    }
}
