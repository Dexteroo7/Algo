package Graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.*;

public class TestClass {

    public static void main(String[] args) throws IOException {

//        BufferedReader reader = Files.newBufferedReader(Paths.get("input2.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] s = reader.readLine().split(" ");
        int nodes = Integer.parseInt(s[0]);
        int edges = Integer.parseInt(s[1]);
        int starting = Integer.parseInt(s[2]);

        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int i = 0; i < edges; i++) {

            s = reader.readLine().split(" ");
            int from = Integer.parseInt(s[0]);
            int to = Integer.parseInt(s[1]);
            adj.computeIfAbsent(from, na -> new ArrayList<>()).add(to);
        }

        Map<Integer, Double> probability = new HashMap<>();
        Deque<Integer> dfsQueue = new LinkedList<>();
        dfsQueue.addFirst(starting);
        probability.putIfAbsent(starting, 1d);

        Set<Integer> stuckIds = new HashSet<>();

        while (!dfsQueue.isEmpty()) {

            int current = dfsQueue.pollFirst();
            double currentProbability = probability.get(current);
            List<Integer> neighbours = adj.getOrDefault(current, Collections.emptyList());
            if (neighbours.isEmpty())
                stuckIds.add(current);
            neighbours.forEach(id -> {
                dfsQueue.addFirst(id);
                probability.merge(id, currentProbability * (1d / neighbours.size()), Double::sum);
            });
        }

        TreeMap<Double, List<Integer>> collect = probability.entrySet().stream()
                //filter on stuck ids
                .filter(entry -> stuckIds.contains(entry.getKey()))
                .collect(groupingBy(Map.Entry::getValue, TreeMap::new, mapping(Map.Entry::getKey, toList())));

        List<Integer> toPrint = collect.lastEntry().getValue();
        toPrint.sort(Comparator.naturalOrder());
        toPrint.forEach(x -> System.out.print(x + " "));
    }
}
