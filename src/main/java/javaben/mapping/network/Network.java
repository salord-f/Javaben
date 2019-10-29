package javaben.mapping.network;

import javaben.io.FileReader;

import java.io.IOException;

public abstract class Network {
    protected int verticesCount;
    protected int edgesCount;

    public void readFile(String filepath) {
        try {
            parseNetwork(FileReader.getFileAsString(filepath));
        } catch (IOException e) {
            System.out.println("Error while reading file : " + e.getMessage());
        }
    }

    public void parseNetwork(String network) {
        int firstLineSeparatorIndex = network.indexOf("\n");
        String firstLine = network.substring(0, firstLineSeparatorIndex);
        this.verticesCount = Integer.parseInt(firstLine.split(" ")[0]);
        this.edgesCount = Integer.parseInt(firstLine.split(" ")[1]);
    }

    public int getVerticesCount() {
        return verticesCount;
    }

    public int getEdgesCount() {
        return edgesCount;
    }

    public int offset() {
        return (int) Math.floor(Math.sqrt(this.getVerticesCount()));
    }

    public void clean() {
        this.verticesCount = 0;
        this.edgesCount = 0;
    }
}
