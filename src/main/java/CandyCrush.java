import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/candy-crush/
class CandyCrush {
    public int[][] candyCrush(int[][] board) {

        List<int[]> toCrush = new ArrayList<>();

        //check each row
        for (int i = 0; i < board.length; i++) {
            for (int j = 2; j < board[i].length; j++) {

                if (board[i][j] == 0)
                    continue;

                if (board[i][j] == board[i][j - 1] && board[i][j - 1] == board[i][j - 2]) {

                    //prevent duplicates
                    if (j - 3 >= 0 && board[i][j - 2] == board[i][j - 3])
                        toCrush.add(new int[]{i, j});
                    else {
                        toCrush.add(new int[]{i, j});
                        toCrush.add(new int[]{i, j - 1});
                        toCrush.add(new int[]{i, j - 2});
                    }
                }
            }
        }

        //check each column
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 2; j < board.length; j++) {

                if (board[j][i] == 0)
                    continue;

                if (board[j][i] == board[j - 1][i] && board[j - 1][i] == board[j - 2][i]) {

                    //prevent duplicates
                    if (j - 3 >= 0 && board[j - 2][i] == board[j - 3][i])
                        toCrush.add(new int[]{j, i});
                    else {
                        toCrush.add(new int[]{j, i});
                        toCrush.add(new int[]{j - 1, i});
                        toCrush.add(new int[]{j - 2, i});
                    }
                }
            }
        }

        //crush
        for (int[] crush : toCrush) {
            board[crush[0]][crush[1]] = 0;
        }

        //stabilize
        boolean wasStable = true;
        //check each column
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 1; j < board.length; j++) {

                if (j < 1)
                    continue;

                if (board[j][i] == 0 && board[j - 1][i] != 0) {
                    wasStable = false;
                    board[j][i] = board[j - 1][i];
                    board[j - 1][i] = 0;
                    //decrement
                    j -= 2;
                }
            }
        }

        if (wasStable)
            return board;
        else
            return candyCrush(board);
    }
}
