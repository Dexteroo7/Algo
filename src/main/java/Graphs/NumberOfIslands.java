package Graphs;

import java.util.*;

class NumberOfIslands {
    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0)
            return 0;

        boolean[][] visited = new boolean[grid.length][];
        Arrays.setAll(visited, i -> new boolean[grid[i].length]);

        int islands = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {

                if (visited[row][column])
                    continue;
                if (grid[row][column] == '0')
                    continue;

                islands++;
                Stack<int[]> bfsQueue = new Stack<>();
                bfsQueue.push(new int[]{row, column});
                while (!bfsQueue.isEmpty()) {

                    int[] current = bfsQueue.pop();
                    if (visited[current[0]][current[1]])
                        continue;
                    visited[current[0]][current[1]] = true;
                    List<int[]> neighbours = neighbours(grid, current);
                    neighbours.forEach(bfsQueue::push);
                }
            }
        }

        return islands;
    }

    public List<int[]> neighbours(char[][] grid, int[] current) {

        int row = current[0];
        int column = current[1];
        List<int[]> possible = new ArrayList<>();
        possible.add(new int[]{row - 1, column});
        possible.add(new int[]{row + 1, column});
        possible.add(new int[]{row, column - 1});
        possible.add(new int[]{row, column + 1});

        possible.removeIf(point -> point[0] >= grid.length);
        possible.removeIf(point -> point[0] < 0);
        possible.removeIf(point -> point[1] >= grid[0].length);
        possible.removeIf(point -> point[1] < 0);
        possible.removeIf(point -> grid[point[0]][point[1]] == '0');

        return possible;
    }

    public static void main(String[] args) {

        NumberOfIslands solution = new NumberOfIslands();
        solution.numIslands(new char[][]{
                new char[]{'1', '1', '1', 1, 0},
                new char[]{},
                new char[]{},
        });
    }
}
