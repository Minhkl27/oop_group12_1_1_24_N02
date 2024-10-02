import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("File/login.txt");
            writer.write("username: password");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }
}
