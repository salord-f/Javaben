package javaben.mapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EdgeListNetwork extends Network {
    private List<Edge> edges;

    public EdgeListNetwork() {
        edges = new ArrayList<>();
    }

    @Override
    public void readFile(String filepath) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filepath))) {
            String[] firstLine = reader.readLine().split(" ");
            setVerticesCount(Integer.parseInt(firstLine[0]));
            setEdgesCount(Integer.parseInt(firstLine[1]));
            for (int i = 0; i < getEdgesCount(); i++) {
                String[] line = reader.readLine().split(" ");
                edges.add(new Edge(new Vertex(Integer.parseInt(line[0])), new Vertex(Integer.parseInt(line[1]))));
            }
        } catch (IOException e) {
            System.out.println("Error while reading file : " + e.getMessage());
        }
    }

    public List<Edge> getEdges() {
        return edges;
    }
}