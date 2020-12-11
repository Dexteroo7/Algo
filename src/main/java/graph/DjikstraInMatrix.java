package graph;

import java.util.*;

class DjikstraInMatrix {

    public int shortestPathBinaryMatrix(int[][] grid) {

        if (grid[0][0] == 1 || grid[grid.length - 1][grid[grid.length - 1].length - 1] == 1)
            return -1;

        BitSet visited = new BitSet();
        int[][] distance = new int[grid.length][];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = new int[grid[i].length];
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> traversalQueue = new PriorityQueue<>(Comparator.comparingInt(a -> distance[a[0]][a[1]]));
        distance[0][0] = 1;
        traversalQueue.add(new int[]{0, 0});

        while (!traversalQueue.isEmpty()) {

            int[] current = traversalQueue.poll();
            int bitIndex = (current[0] * grid.length) + current[1];
            if (visited.get(bitIndex))
                continue;
            visited.set(bitIndex);

            int currentDistance = distance[current[0]][current[1]];
            //stop at destination
            if (current[0] == grid.length - 1 && current[1] == grid[grid.length - 1].length - 1)
                continue;

            for (int[] neighbour : Arrays.asList(new int[]{current[0] - 1, current[1]},
                    new int[]{current[0] - 1, current[1] - 1},
                    new int[]{current[0] - 1, current[1] + 1},
                    new int[]{current[0], current[1] - 1},
                    new int[]{current[0], current[1] + 1},
                    new int[]{current[0] + 1, current[1]},
                    new int[]{current[0] + 1, current[1] - 1},
                    new int[]{current[0] + 1, current[1] + 1})) {//ignore out of bounds
                if (neighbour[0] < 0 || neighbour[0] >= grid.length || neighbour[1] < 0 || neighbour[1] >= grid[neighbour[0]].length)
                    continue;
                //ignore barrier
                if (grid[neighbour[0]][neighbour[1]] == 1)
                    continue;

                if (distance[neighbour[0]][neighbour[1]] > currentDistance + 1) {
                    traversalQueue.add(neighbour);
                    distance[neighbour[0]][neighbour[1]] = currentDistance + 1;
                }
            }
        }

        if (distance[grid.length - 1][grid[grid.length - 1].length - 1] == Integer.MAX_VALUE)
            return -1;
        return distance[grid.length - 1][grid[grid.length - 1].length - 1];
    }

    public static void main(String[] args) {

        new DjikstraInMatrix().shortestPathBinaryMatrix(new int[][]{
                new int[]{0, 1},
                new int[]{1, 0}
        });
    }
}
