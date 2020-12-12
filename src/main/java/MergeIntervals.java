import java.util.*;

//https://leetcode.com/problems/merge-intervals/solution/
class MergeIntervals {
    public int[][] merge(int[][] input) {

        //sort by start and end index
        Arrays.sort(input, Comparator.<int[]>comparingInt(x -> x[0]).thenComparingInt(x -> x[1]));

        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {

            int[] current = input[i];
            i++;
            for (; i < input.length; i++) {

                int[] next = input[i];
                if (isOverlap(current, next)) {
                    current[0] = Integer.min(current[0], next[0]);
                    current[1] = Integer.max(current[1], next[1]);
                } else
                    break;
            }
            i--;
            intervals.add(current);
        }

        int[][] toReturn = new int[intervals.size()][];
        for (int i = 0; i < intervals.size(); i++) {
            toReturn[i] = intervals.get(i);
        }
        return toReturn;
    }

    public boolean isOverlap(int[] a, int[] b) {

        return a[0] <= b[0] && a[1] >= b[0] && a[0] <= b[1] || b[0] <= a[0] && b[1] >= a[0] && b[0] <= a[1];
    }
}
