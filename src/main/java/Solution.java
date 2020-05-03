import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {

    private static final class Edge {

        int from, to, weight;

        public Edge(int from, int to, int weight) {

            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    /*
     * Complete the 'kruskals' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts WEIGHTED_INTEGER_GRAPH g as parameter.
     */

    /*
     * For the weighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] and <name>To[i]. The weight of the edge is <name>Weight[i].
     *
     */

    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {

        BitSet visited = new BitSet(gNodes);

        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.<Edge>comparingInt(x -> x.weight)
                .thenComparingInt(x -> x.to + x.from + x.weight));
        Map<Integer, List<Integer>> adjacency = new HashMap<>();

        for (int i = 0; i < gFrom.size(); i++) {

            Edge edge = new Edge(gFrom.get(i), gTo.get(i), gWeight.get(i));
            edges.add(edge);

            adjacency.computeIfAbsent(gFrom.get(i), na -> new ArrayList<>()).add(gTo.get(i));
            adjacency.computeIfAbsent(gTo.get(i), na -> new ArrayList<>()).add(gFrom.get(i));
        }

        int weight = 0;
        while (!edges.isEmpty()) {

            Edge current = edges.poll();
            //skip is overlap
            if (visited.get(current.from) && visited.get(current.to))
                continue;

            visited.set(current.from);
            visited.set(current.to);
            weight += current.weight;
        }

        //all visited ?
        //return weight ?
        int i = visited.nextClearBit(1);
        if (i > gNodes)
            return weight;
        else
            return -1;
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
