package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

//https://www.hackerrank.com/challenges/synchronous-shopping/problem
class DjikstraNode {

    int nodeId, fishesProcuredSoFar, distanceFromStart;

    public DjikstraNode(int nodeId, int distanceFromStart, int fishesProcuredSoFar) {
        this.nodeId = nodeId;
        this.distanceFromStart = distanceFromStart;
        this.fishesProcuredSoFar = fishesProcuredSoFar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DjikstraNode that = (DjikstraNode) o;
        return nodeId == that.nodeId &&
                fishesProcuredSoFar == that.fishesProcuredSoFar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, fishesProcuredSoFar);
    }
}

class Road {

    int start, end, weight;

    public Road(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

class Result {

    /*
     * Complete the 'shop' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. STRING_ARRAY centers
     *  4. 2D_INTEGER_ARRAY roads
     */
    public static int shop(int n, int totalFishes, List<String> centers, List<List<Integer>> roads) {
        // Write your code here
        Map<Integer, Integer> fishMappings = new HashMap<>();
        Map<Integer, List<Road>> allConnections = new HashMap<>();
        for (int id = 1; id <= centers.size(); id++) {

            int fishesSold = 0;
            String[] split = centers.get(id - 1).split(" ");
            for (int j = 1; j < split.length; j++) {
                int pos = Integer.parseInt(split[j]);
                fishesSold = fishesSold | (1 << (pos - 1));
            }
            fishMappings.put(id, fishesSold);
        }

        for (List<Integer> road : roads) {
            int start = road.get(0);
            int end = road.get(1);
            int weigth = road.get(2);
            allConnections.computeIfAbsent(start, na -> new ArrayList<>())
                    .add(new Road(start, end, weigth));
            allConnections.computeIfAbsent(end, na -> new ArrayList<>())
                    .add(new Road(end, start, weigth));
        }

        return shop(n, (1 << totalFishes) - 1, fishMappings, allConnections);
    }

    public static int shop(int end, int totalFishes, Map<Integer, Integer> fishMappings, Map<Integer, List<Road>> allConnections) {

        int[][] distances = new int[end + 1][totalFishes + 1];
        for (int[] ints : distances) {
            Arrays.fill(ints, -1);
        }
        Set<DjikstraNode> destinationPaths = new HashSet<>();
        PriorityQueue<DjikstraNode> djikstraQueue = new PriorityQueue<>(Comparator.comparingInt(x -> x.distanceFromStart));
        djikstraQueue.add(new DjikstraNode(1, 0, fishMappings.get(1)));

        long start = System.nanoTime();
        int loops = 0;
        while (!djikstraQueue.isEmpty()) {

            DjikstraNode current = djikstraQueue.remove();
            if (distances[current.nodeId][current.fishesProcuredSoFar] != -1)
                continue;
            distances[current.nodeId][current.fishesProcuredSoFar] = current.distanceFromStart;
            if (current.nodeId == end) {
                destinationPaths.add(current);
            }
            loops++;
            List<Road> connections = allConnections.getOrDefault(current.nodeId, Collections.emptyList());
            for (Road connection : connections) {

                int fishesProcured = current.fishesProcuredSoFar | fishMappings.get(connection.end);
                int newDistance = current.distanceFromStart + connection.weight;
                DjikstraNode newNode = new DjikstraNode(connection.end, newDistance, fishesProcured);
                if (distances[newNode.nodeId][newNode.fishesProcuredSoFar] == -1)
                    djikstraQueue.add(newNode);
            }
        }

        System.out.println(loops + " iterations");
        System.out.println(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));
        start = System.nanoTime();
        int minDistance = Integer.MAX_VALUE;
        for (DjikstraNode a : destinationPaths) {
            for (DjikstraNode b : destinationPaths) {
                int distance = Integer.max(a.distanceFromStart, b.distanceFromStart);
                if ((a.fishesProcuredSoFar | b.fishesProcuredSoFar) >= totalFishes)
                    minDistance = Integer.min(minDistance, distance);
            }
        }
        System.out.println(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));
        return minDistance;
    }
}

public class Synchronous_Shopping {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("input30.txt"))));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        int k = Integer.parseInt(firstMultipleInput[2]);

        List<String> centers = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        List<List<Integer>> roads = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                roads.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int res = Result.shop(n, k, centers, roads);
        System.out.println(res);

//        bufferedWriter.write(String.valueOf(res));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
