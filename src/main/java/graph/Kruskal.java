package graph;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

public class Kruskal {

    final KruskalNode[] nodes;

    private Kruskal(int count) {
        this.nodes = new KruskalNode[count + 1];
        for (int i = 0; i < nodes.length; i++) {
            this.nodes[i] = new KruskalNode(i, 0, 0);
        }
    }

    public int findParent(int id) {
        int parent = nodes[id].parent;
        if (parent == 0)
            return id;
        //with path compression
        return nodes[id].parent = findParent(nodes[id].parent);
    }

    public void union(int id1, int id2) {

        KruskalNode node1 = nodes[id1];
        KruskalNode node2 = nodes[id2];

        if (node1.rank > node2.rank)
            node2.parent = node1.id;
        else if (node2.rank > node1.rank)
            node1.parent = node2.id;
        else {
            node2.parent = node1.id;
            node1.rank++;
        }
    }

    private static final class KruskalNode {

        public KruskalNode(int id, int parent, int rank) {
            this.id = id;
            this.parent = parent;
            this.rank = rank;
        }

        final int id;
        int parent;
        int rank;
    }

    private static final class Edge {

        int from, to, weight;

        public Edge(int from, int to, int weight) {

            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {

        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(x -> x.weight));
        for (int i = 0; i < gFrom.size(); i++) {
            edges.add(new Edge(gFrom.get(i), gTo.get(i), gWeight.get(i)));
        }

        int weight = 0;
        int expectedEdges = gNodes - 1;
        int edgeCount = 0;
        Kruskal kruskal = new Kruskal(gNodes);
        while (edgeCount < expectedEdges) {

            Edge toAdd = edges.poll();
            int a = toAdd.from;
            int b = toAdd.to;
            int parentA = kruskal.findParent(a);
            int parentB = kruskal.findParent(b);
            //do not add this edge as it forms a cycle
            if (parentA != parentB) {
                weight += toAdd.weight;
                kruskal.union(parentA, parentB);
                edgeCount++;
            }
        }

        return weight;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        PrintStream output = new PrintStream(Files.newOutputStream(Paths.get(System.getenv("OUTPUT_PATH"))));
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("input.txt"));
        PrintStream output = System.out;

        String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();
        List<Integer> gWeight = new ArrayList<>();

        IntStream.range(0, gEdges).forEach(i -> {
            try {
                String[] gFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                gFrom.add(Integer.parseInt(gFromToWeight[0]));
                gTo.add(Integer.parseInt(gFromToWeight[1]));
                gWeight.add(Integer.parseInt(gFromToWeight[2]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int res = kruskals(gNodes, gFrom, gTo, gWeight);
        output.println(res);
        // Write your code here.

        bufferedReader.close();
        output.close();
    }
}
