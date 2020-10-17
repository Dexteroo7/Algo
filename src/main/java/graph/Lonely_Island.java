package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Lonely_Island {

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

        int[] inDegree = new int[nodes + 1];
        int[] outDegree = new int[nodes + 1];
        double[] probability = new double[nodes + 1];

        //set inDegree/outDegree
        adj.forEach((id, neighbours) -> {
            outDegree[id] = neighbours.size();
            neighbours.forEach(x -> inDegree[x]++);
        });

        probability[starting] = 1;

        Deque<Integer> khansQueue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                khansQueue.add(i);
        }

        while (!khansQueue.isEmpty()) {

            int current = khansQueue.poll();
            List<Integer> neighbours = adj.getOrDefault(current, Collections.emptyList());
            //update probability
            for (int id : neighbours) {
                probability[id] += probability[current] * (1.0d / neighbours.size());
                inDegree[id]--;
                if (inDegree[id] == 0)
                    khansQueue.add(id);
            }
        }

        double maxProb = 0;
        for (int i = 0; i < outDegree.length; i++) {
            if (outDegree[i] == 0 && probability[i] > maxProb)
                maxProb = probability[i];
        }

        for (int i = 0; i < outDegree.length; i++) {
            if (outDegree[i] == 0 && probability[i] == maxProb)
                System.out.print(i + " ");
        }
    }
}
