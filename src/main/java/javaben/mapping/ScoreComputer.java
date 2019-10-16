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

        assert edgeCount == edges.size();
    }

    public int computeScore(String output) {
        coords = Arrays.stream(output.split("\n"))
                .map(line -> Arrays.asList(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1])))
                .collect(Collectors.toList());

        assert vertexCount == coords.size();

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
        int x1 = coords.get(vertices.get(0)).get(0);
        int x2 = coords.get(vertices.get(1)).get(0);
        int y1 = coords.get(vertices.get(0)).get(1);
        int y2 = coords.get(vertices.get(1)).get(1);
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }
}
