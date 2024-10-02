import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadfromFile {
    public static void main(String[] args) {
        File file = new File("File/login.txt");
        try {
                FileInputStream fis = new FileInputStream(file);
                int c;
                while ((c = fis.read()) != -1) {
                    System.out.print((char) c);
}
        }  
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}