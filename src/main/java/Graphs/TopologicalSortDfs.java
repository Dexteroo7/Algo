package Graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class TopologicalSortDfs {

    static void topoSort(Map<Integer, List<Integer>> adjacency, int nodes) {

        BitSet visited = new BitSet(nodes);

        Stack<Integer> topoStack = new Stack<>();
        while (visited.nextClearBit(1) <= nodes) {

            int start = visited.nextClearBit(1);
            topoSort(adjacency, topoStack, visited, start);
        }

        while (!topoStack.isEmpty())
            System.out.print(topoStack.pop() + " ");
    }

    static void topoSort(Map<Integer, List<Integer>> adjacency,
                         Stack<Integer> topoStack,
                         BitSet visited,
                         int current) {

        if (visited.get(current))
            return;
        visited.set(current);
        adjacency.getOrDefault(current, Collections.emptyList())
                .forEach(x -> topoSort(adjacency, topoStack, visited, x));
        topoStack.push(current);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("input.txt"))));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();
        String[] split = line.split(" ");
        int n = Integer.parseInt(split[0]);

        Map<Integer, List<Integer>> adjacency = new HashMap<>();
        while ((line = reader.readLine()) != null) {

            split = line.split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            adjacency.computeIfAbsent(from, na -> new ArrayList<>()).add(to);
        }

        topoSort(adjacency, n);
    }
}
