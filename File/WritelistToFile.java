import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class WritelistToFile {

    public static void main(String[] args) {
        // Example list of data
        List<String> data = new ArrayList<>();
        data.add("HieuVn" + " " + "hieu12345");
        data.add("MinhPC" + " " + "minh1337");
        data.add("LeVoc" + " " + "voc12345");
        // File path to write data
        String filePath = "File/loginAccount.txt";

        // Writing the list to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine(); // to move to the next line
            }
            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
