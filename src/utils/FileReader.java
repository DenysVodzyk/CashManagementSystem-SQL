package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public List<String> readFromFile(String path) {
        List<String> fileContent = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            reader.readLine();
            String input;
            while ((input = reader.readLine()) != null) {
                fileContent.add(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}
