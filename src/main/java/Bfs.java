import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.*;

public class Bfs {

    private static final class Node {

        int id;
        int distanceFromStart;

        public Node(int id, int distanceFromStart) {
            this.id = id;
            this.distanceFromStart = distanceFromStart;
        }
    }

    // Complete the bfs function below.
    static int[] bfs(int nodes, int[][] edges, int starting) {

        int[] distances = new int[nodes + 1];
        Arrays.fill(distances, -1);

        Map<Integer, List<Integer>> adjacency = new HashMap<>(edges.length);
        for (int[] edge : edges) {
            adjacency.computeIfAbsent(edge[0], na -> new ArrayList<>()).add(edge[1]);
            adjacency.computeIfAbsent(edge[1], na -> new ArrayList<>()).add(edge[0]);
        }

        Deque<Node> bfsQueue = new LinkedList<>();
        bfsQueue.add(new Node(starting, 0));
        while (!bfsQueue.isEmpty()) {

            Node current = bfsQueue.removeFirst();
            if (distances[current.id] != -1)
                continue;

            distances[current.id] = current.distanceFromStart;

            adjacency.getOrDefault(current.id, Collections.emptyList())
                    .stream()
                    .filter(id -> distances[id] == -1)
                    .map(id -> new Node(id, current.distanceFromStart + 1))
                    .forEach(bfsQueue::addLast);
        }

        //add weight
        int[] toReturn = new int[nodes - 1];
        int counter = 0;
        for (int i = 1; i < distances.length; i++) {
            if (i == starting)
                continue;
            if (distances[i] > 0)
                toReturn[counter++] = distances[i] * 6;
            else
                toReturn[counter++] = distances[i];
        }
        return toReturn;
    }


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(Files.newInputStream(Paths.get("input.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, edges, s);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

//6 6 6 6 12 6 12 6 12 12 6 6 6 6 6 12 12 6 6 6 6 12 6 12 6 12 6 12 12 12 12 6 12 12 6 12 12 6 12 6 12 6 12 12 6 6 12 6 6 6 6 12 12 12 12 6 6 6 12 6 6 12 12 12 12 12 12 6 6
//6 6 6 6 12 6 12 6 12 12 6 6 6 6 6 12 12 6 6 6 6 12 6 12 6 12 6 12 12 12 12 6 12 12 6 12 12 6 12 6 12 6 12 12 6 6 12 6 6 6 6 12 12 12 12 6 6 6 12 6 6 12 12 12 12 12 12 6 6
//6 6 6 6 12 12 12 12 12 12 12 12 6 6 12 12 12 12 6 6 6 12 12 12 12 12 6 12 12 12 12 6 12 12 6 12 12 6 12 12 12 6 12 12 6 12 12 6 12 12 12 12 12 12 12 6 6 12 12 12 12 12 12 12 12 12 12 6 6
//6 6 6 6 12 12 12 12 12 12 12 12 6 6 12 12 12 12 6 6 6 12 12 12 12 12 6 12 12 12 12 6 12 12 6 12 12 6 12 12 12 6 12 12 6 12 12 6 12 12 12 12 12 12 12 6 6 12 12 12 12 12 12 12 12 12 12 6 6
