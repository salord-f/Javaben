package javaben.mapping;

import java.util.*;
import java.util.stream.Collectors;

public class ScoreComputer {
    private List<List<Integer>> coords;
    private List<Set<Integer>> edges;
    private int vertexCount;
    private int edgeCount;

    public ScoreComputer(String input) {
        vertexCount = Integer.parseInt(input.split("\n")[0].split(" ")[0]);
        edgeCount = Integer.parseInt(input.split("\n")[0].split(" ")[1]);
        input = input.substring(input.indexOf("\n"));

        edges = Arrays.stream(input.split("\n"))
                .filter(line -> !("".equals(line)))
                .map(line -> new HashSet<>(Arrays.asList(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1]))))
                .collect(Collectors.toList());

        if (edgeCount != edges.size()) {
            throw new IllegalStateException(String.format("%d edges specified but %d edges found in input.", edgeCount, edges.size()));
        }
    }

    public int computeScore(String output) {
        coords = Arrays.stream(output.split("\n"))
                .map(line -> Arrays.asList(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1])))
                .collect(Collectors.toList());

        if (vertexCount != coords.size()) {
            throw new IllegalStateException(String.format("%d vertices specified but %d vertices found in output.", vertexCount, coords.size()));
        }

        int score = 0;

        Map<List<Integer>, Long> counters = coords.stream()
                .collect(Collectors.groupingBy(edge -> edge, Collectors.counting()));

        for (Map.Entry<List<Integer>, Long> entry : counters.entrySet()) {
            score += (3 * (entry.getValue() - 1)); // Overlap
        }

        for (Set<Integer> edge : edges) {
            score += (2 * (distance(edge) - 1)); // Distances
        }

        int width = coords.stream().mapToInt(c -> c.get(0)).max().getAsInt() - coords.stream().mapToInt(c -> c.get(0)).min().getAsInt();
        int height = coords.stream().mapToInt(c -> c.get(1)).max().getAsInt() - coords.stream().mapToInt(c -> c.get(1)).min().getAsInt();

        score += Math.pow(Math.max(width, height), 2); // Size

        return score;
    }

    private int distance(Set<Integer> edge) {
        List<Integer> vertices = new ArrayList<>(edge);
        int v1 = vertices.get(0);
        int v2 = vertices.get(1);
        int x1 = coords.get(v1).get(0);
        int x2 = coords.get(v2).get(0);
        int y1 = coords.get(v1).get(1);
        int y2 = coords.get(v2).get(1);
        int distance = Math.abs(x2 - x1) + Math.abs(y2 - y1);
        if (distance == 0) {
            throw new IllegalStateException(String.format("Components %d and %d both on (%d, %d).", v1, v2, x1, y1));
        }
        return distance;
    }
}
