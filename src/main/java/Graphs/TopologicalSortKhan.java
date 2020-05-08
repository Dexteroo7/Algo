package Graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiConsumer;

class TopologicalSortKhan {

    static void topoSort(Map<Integer, List<Integer>> adjacency, int nodes) {

        int[] indegree = new int[nodes + 1];
        adjacency.forEach((id, neighbors) -> neighbors.forEach(x -> indegree[x]++));

        PriorityQueue<Integer> zeroDegree = new PriorityQueue<>();
        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0)
                zeroDegree.add(i);
        }

        while (!zeroDegree.isEmpty()) {

            int id = zeroDegree.poll();
            System.out.print(id + " ");
            for (int x : adjacency.getOrDefault(id, Collections.emptyList())) {
                indegree[x]--;
                if (indegree[x] == 0)
                    zeroDegree.add(x);
            }
        }
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
