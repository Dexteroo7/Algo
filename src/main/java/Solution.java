import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

class Solution {
    public int maxDistance(int[][] grid) {

        int[][] distances = new int[grid.length][];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = new int[grid[i].length];
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] == 0)
                    continue;

                LinkedList<int[]> traversalQueue = new LinkedList<>();
                traversalQueue.addFirst(new int[]{i, j});
                while (!traversalQueue.isEmpty()) {

                    int[] poll = traversalQueue.removeFirst();
                    int distance = distance(i, j, poll[0], poll[1]);
                    if (distance >= distances[poll[0]][poll[1]])
                        continue;

                    distances[poll[0]][poll[1]] = distance;

                    if (poll[0] - 1 >= 0 && grid[poll[0] - 1][poll[1]] == 0) {
                        traversalQueue.add(new int[]{poll[0] - 1, poll[1]});
                    }
                    if (poll[0] + 1 < grid.length && grid[poll[0] + 1][poll[1]] == 0) {
                        traversalQueue.add(new int[]{poll[0] + 1, poll[1]});
                    }
                    if (poll[1] - 1 >= 0 && grid[poll[0]][poll[1] - 1] == 0) {
                        traversalQueue.add(new int[]{poll[0], poll[1] - 1});
                    }
                    if (poll[1] + 1 < grid[0].length && grid[poll[0]][poll[1 + 1]] == 0) {
                        traversalQueue.add(new int[]{poll[0], poll[1] + 1});
                    }
                }
            }
        }

        int maxDist = -1;
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[i].length; j++) {
                if (grid[i][j] == 1)
                    continue;
                maxDist = Integer.max(maxDist, distances[i][j]);
            }
        }

        return maxDist == Integer.MAX_VALUE ? -1 : maxDist;
    }

    public int distance(int a1, int b1, int a2, int b2) {

        int x = a1 - a2;
        x = x < 0 ? -x : x;
        int y = b1 - b2;
        y = y < 0 ? -y : y;
        return x + y;
    }
}
