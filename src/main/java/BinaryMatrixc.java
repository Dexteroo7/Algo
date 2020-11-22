import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 * public int get(int row, int col) {}
 * public List<Integer> dimensions {}
 * };
 */

//https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
class BinaryMatrixc {

    interface BinaryMatrix {
        int get(int row, int col);

        List<Integer> dimensions();
    }

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {

        int rows = binaryMatrix.dimensions().get(0);
        int columns = binaryMatrix.dimensions().get(0);

        int low = 0;
        int high = columns;

        Set<Integer> rowsToConsider = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            rowsToConsider.add(i);
        }

        Set<Integer> goodRows = new HashSet<>();
        int lastGoodPivot = -1;
        while (low < high) {

            int pivot = (low + high) / 2;
            for (int row : rowsToConsider) {
                if (binaryMatrix.get(row, pivot) == 1) {
                    goodRows.add(row);
                    lastGoodPivot = pivot;
                }
            }

            if (goodRows.isEmpty())
                low = pivot + 1;
            else {
                rowsToConsider = goodRows;
                goodRows = new HashSet<>();
                high = pivot - 1;
            }
        }

        return lastGoodPivot;
    }
}
