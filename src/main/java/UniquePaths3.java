import java.util.*;

//https://leetcode.com/problems/unique-paths-iii
class UniquePaths3 {

    public static class Board {

        final int[][] grid;
        final int[][] indexedBoard;
        final List<int[]> reverseIndexed;

        public Board(int[][] grid) {
            this.grid = grid;
            //quick mapping from 1D to 2D
            this.indexedBoard = new int[grid.length][];
            this.reverseIndexed = new ArrayList<>();

            int counter = 0;
            for (int row = 0; row < grid.length; row++) {
                indexedBoard[row] = new int[grid[row].length];
                for (int column = 0; column < grid[row].length; column++) {
                    indexedBoard[row][column] = counter;
                    reverseIndexed.add(new int[]{row, column});
                    counter++;
                }
            }
        }

        public int[] to2D(int index) {
            return reverseIndexed.get(index);
        }

        public int from2D(int row, int column) {
            return indexedBoard[row][column];
        }

        public boolean safe(int row, int column) {
            return row >= 0 && row < grid.length && column >= 0 && column < grid[row].length && grid[row][column] != -1;
        }
    }

    public int uniquePathsIII(int[][] grid) {

        Board board = new Board(grid);
        BitSet visited = new BitSet();

        int startingRow = -1, startingColumn = -1;
        int requiredMoves = 0;
        for (int row = 0; row < board.grid.length; row++) {
            for (int column = 0; column < board.grid[row].length; column++) {
                if (grid[row][column] == 1) {
                    startingRow = row;
                    startingColumn = column;
                } else if (grid[row][column] == 0)
                    requiredMoves++;
            }
        }

        int[] validTraversalCounter = new int[]{0};
        uniquePathsIII(startingRow, startingColumn, board, visited, 0, requiredMoves, validTraversalCounter);
        return validTraversalCounter[0];
    }

    public void uniquePathsIII(int row, int column, Board board, BitSet visited, int moves, int requiredMoves, int[] validTraversalCounter) {

        if (!board.safe(row, column))
            return;
        int index = board.from2D(row, column);
        if (visited.get(index))
            return;
        if (board.grid[row][column] == 2) {
            if (moves - 1 == requiredMoves)
                validTraversalCounter[0]++;
            return;
        }

        visited.set(index);
        uniquePathsIII(row, column - 1, board, visited, moves + 1, requiredMoves, validTraversalCounter);
        uniquePathsIII(row, column + 1, board, visited, moves + 1, requiredMoves, validTraversalCounter);
        uniquePathsIII(row - 1, column, board, visited, moves + 1, requiredMoves, validTraversalCounter);
        uniquePathsIII(row + 1, column, board, visited, moves + 1, requiredMoves, validTraversalCounter);
        visited.clear(index);
    }

    public static void main(String[] args) {

        int[][] board = new int[][]{
                new int[]{1, 0, 0, 0},
                new int[]{0, 0, 0, 0},
                new int[]{0, 0, 2, -1}
        };

        System.out.println(new UniquePaths3().uniquePathsIII(board));
    }
}
