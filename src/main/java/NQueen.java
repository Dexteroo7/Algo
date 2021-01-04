import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

class NQueen {

    public List<List<String>> solveNQueens(int n) {

        char[][] placements = new char[n][n];
        for (int i = 0; i < placements.length; i++) {
            placements[i] = new char[n];
            Arrays.fill(placements[i], '.');
        }
        List<List<String>> output = new ArrayList<>();

        solveNQueens(placements, n, output, 0, new BitSet(n), new BitSet(2 * n), new BitSet(2 * n));

        return output;
    }

    public void solveNQueens(char[][] placements, int left, List<List<String>> output, int row, BitSet column, BitSet diag, BitSet antiDiag) {

        if (left == 0) {
            List<String> toAdd = new ArrayList<>();
            for (char[] placement : placements) {
                toAdd.add(new String(placement));
            }
            output.add(toAdd);
            return;
        }

        int size = placements.length;

        //1 queen must be placed on each row
        for (int j = 0; j < size; j++) {

            int columnIndex = j;
            int diagIndex = j + row;
            int antiDiagIndex = j + size - 1 - row;
            if (column.get(columnIndex))
                continue;
            if (diag.get(diagIndex))
                continue;
            if (antiDiag.get(antiDiagIndex))
                continue;

            //place
            column.set(columnIndex);
            diag.set(diagIndex);
            antiDiag.set(antiDiagIndex);
            placements[row][j] = 'Q';
            solveNQueens(placements, left - 1, output, row + 1, column, diag, antiDiag);
            //remove
            column.clear(columnIndex);
            diag.clear(diagIndex);
            antiDiag.clear(antiDiagIndex);
            placements[row][j] = '.';
        }
    }
}
