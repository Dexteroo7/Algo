package Graphs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Prims {

    private static final class PrimsEdge implements Comparable<PrimsEdge> {

        final int from, to, weight;

        private PrimsEdge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(PrimsEdge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    // Complete the prims function below.
    static int prims(int nodeCount, int[][] edges, int start) {

        int toReturn = 0;

        BitSet included = new BitSet(nodeCount + 1);

        Map<Integer, List<PrimsEdge>> adjacencyList = new HashMap<>();
        for (int[] edge : edges) {
            adjacencyList.computeIfAbsent(edge[0], na -> new ArrayList<>())
                    .add(new PrimsEdge(edge[0], edge[1], edge[2]));
            adjacencyList.computeIfAbsent(edge[1], na -> new ArrayList<>())
                    .add(new PrimsEdge(edge[1], edge[0], edge[2]));
        }
        PriorityQueue<PrimsEdge> primsPriorityQueue = new PriorityQueue<>();

        primsPriorityQueue.addAll(adjacencyList.get(start));
        included.set(start);
        while (included.nextClearBit(1) <= nodeCount) {

            PrimsEdge primsEdge = primsPriorityQueue.poll();
            if (included.get(primsEdge.to))
                continue;
            included.set(primsEdge.to);
            //take this edge
            toReturn += primsEdge.weight;
            primsPriorityQueue.addAll(adjacencyList.get(primsEdge.to));
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Files.newInputStream(Paths.get("input.txt")));
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] edges = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] edgesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int edgesItem = Integer.parseInt(edgesRowItems[j]);
                edges[i][j] = edgesItem;
            }
        }

        int start = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = prims(n, edges, start);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
