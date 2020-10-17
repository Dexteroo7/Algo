package graph;

import java.util.*;

class PacificAtlanticFlow {

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return Collections.emptyList();

        boolean[][] toPacific = new boolean[matrix.length][];
        Arrays.setAll(toPacific, i -> new boolean[matrix[i].length]);
        boolean[][] toAtlantic = new boolean[matrix.length][];
        Arrays.setAll(toAtlantic, i -> new boolean[matrix[i].length]);

        for (int column = 0; column < matrix[0].length; column++) {
            bfs(matrix, toPacific, 0, column);
            bfs(matrix, toAtlantic, toAtlantic.length - 1, column);
        }

        for (int row = 0; row < matrix.length; row++) {
            bfs(matrix, toPacific, row, 0);
            bfs(matrix, toAtlantic, row, toAtlantic[row].length - 1);
        }

        List<List<Integer>> toReturn = new ArrayList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (toAtlantic[row][column] && toPacific[row][column])
                    toReturn.add(Arrays.asList(row, column));
            }
        }

        return toReturn;
    }

    public void bfs(int[][] matrix, boolean[][] canFlow, int row, int column) {

        //already visited
        //can already flow
        if (canFlow[row][column])
            return;
        canFlow[row][column] = true;

        //can flow to these neighbours
        List<int[]> neighbours = neighbours(matrix, row, column);
        for (int[] neighbour : neighbours) {
            bfs(matrix, canFlow, neighbour[0], neighbour[1]);
        }
    }


    public List<int[]> neighbours(int[][] matrix, int row, int column) {

        List<int[]> neighbours = new ArrayList<>();
        int currentLevel = matrix[row][column];

        neighbours.add(new int[]{row - 1, column});
        neighbours.add(new int[]{row + 1, column});
        neighbours.add(new int[]{row, column - 1});
        neighbours.add(new int[]{row, column + 1});
        neighbours.removeIf(x -> x[0] < 0 || x[0] >= matrix.length || x[1] < 0 || x[1] >= matrix[0].length);
        //we want to flow to higher/equal level
        neighbours.removeIf(x -> matrix[x[0]][x[1]] < currentLevel);

        return neighbours;
    }

    public static void main(String[] args) {

//        Solution solution = new Solution();
//        List<List<Integer>> result = solution.pacificAtlantic(new int[][]{
//                new int[]{2, 3, 5},
//                new int[]{3, 4, 4}
//        });
//        result.forEach(System.out::println);

        PacificAtlanticFlow solution = new PacificAtlanticFlow();
        List<List<Integer>> result = solution.pacificAtlantic(new int[][]{
                new int[]{3, 3, 3, 3, 3, 3},
                new int[]{3, 0, 3, 3, 0, 3},
                new int[]{3, 3, 3, 3, 3, 3},
        });
        result.forEach(System.out::println);
    }
}
