package graph;

import java.util.*;

//https://leetcode.com/problems/critical-connections-in-a-network/
class TrajanUndirectedGraph {

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        Map<Integer, List<Integer>> mappedConnections = new HashMap<>();
        int[] discoveryTime = new int[n];
        int[] lowestNeighbour = new int[n];

        for (List<Integer> connection : connections) {
            mappedConnections.computeIfAbsent(connection.get(0), na -> new ArrayList<>()).add(connection.get(1));
            mappedConnections.computeIfAbsent(connection.get(1), na -> new ArrayList<>()).add(connection.get(0));
        }

        return trajanDfs(0, new int[1], -1, mappedConnections, discoveryTime, lowestNeighbour, new ArrayList<>());
    }

    public List<List<Integer>> trajanDfs(int id, int[] timer, int parent,
                                         Map<Integer, List<Integer>> connections, int[] discoveryTime, int[] lowestNeighbour,
                                         List<List<Integer>> bridges) {

        lowestNeighbour[id] = discoveryTime[id] = timer[0] += 1;

        for (int neighbour : connections.getOrDefault(id, Collections.emptyList())) {

            if (neighbour == parent)
                continue;
            if (discoveryTime[neighbour] == 0)
                //traverse to neighbour
                trajanDfs(neighbour, timer, id, connections, discoveryTime, lowestNeighbour, bridges);

            lowestNeighbour[id] = Integer.min(lowestNeighbour[id], lowestNeighbour[neighbour]);
        }

        if (discoveryTime[id] == lowestNeighbour[id] && parent != -1)
            bridges.add(Arrays.asList(id, parent));

        return bridges;
    }

    public static void main(String[] args) {

        System.out.println(new TrajanUndirectedGraph().criticalConnections(4, Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(0, 2),
                Arrays.asList(0, 3),
                Arrays.asList(1, 2)
        )));
    }
}
