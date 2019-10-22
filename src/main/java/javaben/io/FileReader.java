package javaben.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    public static String getFileAsString(String filepath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filepath))) {
            String line;
            StringBuilder res = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                res.append(line).append("\n");
            }
            return res.toString();
        }
    }
}
