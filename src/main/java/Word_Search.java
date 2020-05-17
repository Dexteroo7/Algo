class Word_Search {
    public boolean exist(char[][] board, String word) {

        boolean[][] seen = new boolean[board.length][];
        for (int i = 0; i < board.length; i++) {
            seen[i] = new boolean[board[i].length];
        }

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                boolean found = exist(board, word, row, column, 0, seen);
                if (found)
                    return true;
            }
        }

        return false;
    }

    public boolean exist(char[][] board, String word, int row, int column, int wordIndex, boolean[][] seen) {

        //word was found
        if (wordIndex >= word.length())
            return true;
        //out of bounds
        if (row < 0 || column < 0 || row >= board.length || column >= board[row].length)
            return false;
        //can not repeat
        if (seen[row][column])
            return false;
        //looking for wordIndex at row/column
        if (board[row][column] != word.charAt(wordIndex))
            return false;

        seen[row][column] = true;
        System.out.println(board[row][column]);

        //recurse in neighbours
        boolean found = exist(board, word, row, column + 1, wordIndex + 1, seen);
        if (found)
            return true;
        found = exist(board, word, row, column - 1, wordIndex + 1, seen);
        if (found)
            return true;
        found = exist(board, word, row + 1, column, wordIndex + 1, seen);
        if (found)
            return true;
        found = exist(board, word, row - 1, column, wordIndex + 1, seen);
        if (found)
            return true;

        //unsee
        seen[row][column] = false;
        return false;
    }

    public static void main(String[] args) {

        Word_Search solution = new Word_Search();
        System.out.println(solution.exist(new char[][]{
                        new char[]{'A', 'B', 'C', 'E'},
                        new char[]{'S', 'F', 'C', 'S'},
                        new char[]{'A', 'D', 'E', 'E'}},
                "ABCCED"));
        System.out.println(solution.exist(new char[][]{
                        new char[]{'A', 'B', 'C', 'E'},
                        new char[]{'S', 'F', 'C', 'S'},
                        new char[]{'A', 'D', 'E', 'E'}},
                "SEE"));
        System.out.println(solution.exist(new char[][]{
                        new char[]{'A', 'B', 'C', 'E'},
                        new char[]{'S', 'F', 'C', 'S'},
                        new char[]{'A', 'D', 'E', 'E'}},
                "ABCB"));
    }
}
