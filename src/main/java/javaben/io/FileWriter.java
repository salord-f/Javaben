package javaben.io;

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

	public static void writeMappingToFile(String name, boolean optimized, String input, long time, int score) {
		String filename = (name + "_" + optimized + ".si5").toLowerCase();
		Path path = Paths.get("results/" + filename);

		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
			PrintWriter printWriter = new PrintWriter(writer);
			printWriter.println(input.split("\\.")[0] + " " + time + " " + score);
		} catch (Exception e) {
			System.out.println("Error while writing file.");
			e.printStackTrace();
		}
	}

    public static void writeOutputToFile(String filename, String output, long score, long time) {
        Path path = Paths.get(filename);
        Path resultsPath = Paths.get(filename.split("/")[0] + "/results.si5");

		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
			PrintWriter printWriter = new PrintWriter(writer);
			printWriter.println(output);
		} catch (Exception e) {
            System.out.println("Error while writing output file.");
			e.printStackTrace();
		}

        try (BufferedWriter writer = Files.newBufferedWriter(resultsPath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.println(filename.substring(0, filename.length() - 4).split("/")[1] + " " + score + " " + time);
        } catch (Exception e) {
            System.out.println("Error while writing results file.");
            e.printStackTrace();
        }
	}
}
