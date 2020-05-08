package Graphs;/*package whatever //do not write package name here */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Djikstra {

    private static final Map<Integer, Integer> DESTINATION_MAP = new HashMap<>();

    static {
        DESTINATION_MAP.put(1, 0);

        PriorityQueue<Node> djikstraQueue = new PriorityQueue<>();
        djikstraQueue.add(new Node(1, 0));

        while (!djikstraQueue.isEmpty()) {

            Node current = djikstraQueue.remove();
            if (current.nodeId > 10000)
                continue;

            Node nextNodeA = new Node(current.nodeId * 3, 1 + current.destinationFromStart);
            Node nextNodeB = new Node(current.nodeId + 1, 1 + current.destinationFromStart);
            //update destination if it is minimum
            DESTINATION_MAP.compute(nextNodeA.nodeId, (key, value) -> {
                if (value == null) {
                    djikstraQueue.add(nextNodeA);
                    return nextNodeA.destinationFromStart;
                }
                return Integer.min(value, nextNodeA.destinationFromStart);
            });
            DESTINATION_MAP.compute(nextNodeB.nodeId, (key, value) -> {
                if (value == null) {
                    djikstraQueue.add(nextNodeB);
                    return nextNodeB.destinationFromStart;
                }
                return Integer.min(value, nextNodeB.destinationFromStart);
            });
        }
    }

    public static void main(String[] args) throws IOException {
        //code

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int testCases = Integer.parseInt(reader.readLine().trim());
            for (int cases = testCases; cases > 0; cases--) {

                int n = Integer.parseInt(reader.readLine());
                int shortestPath = DESTINATION_MAP.get(n);
                System.out.println(shortestPath);
            }
        }
    }


    private static final class Node implements Comparable<Node> {

        final int nodeId, destinationFromStart;

        private Node(int nodeId, int destinationFromStart) {
            this.nodeId = nodeId;
            this.destinationFromStart = destinationFromStart;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.destinationFromStart, o.destinationFromStart);
        }
    }
}
