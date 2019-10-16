package javaben;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {

	public static void writeToFile(String name, String initType, String method, long size, long time) {
		String filename = name + "_" + initType + "_" + method + ".si5";
		Path path = Paths.get("results/" + filename.toLowerCase());

		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
			PrintWriter printWriter = new PrintWriter(writer);
			printWriter.println(size + " " + time);
		} catch (Exception e) {
			System.out.println("Error while writing file : " + e.getMessage());
		}
	}
}
