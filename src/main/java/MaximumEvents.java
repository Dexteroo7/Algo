import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;

//https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/submissions/
class MaximumEvents {

    public int maxEvents(int[][] events) {

        Arrays.sort(events, Comparator.<int[]>comparingInt(x -> x[1]).thenComparingInt(x -> x[0]));

        BitSet scheduled = new BitSet();
        int toReturn = 0;
        for (int[] event : events) {

            int start = event[0];
            int end = event[1];
            int possibleSchedule = scheduled.nextClearBit(start);
            if (possibleSchedule <= end) {
                scheduled.set(possibleSchedule);
                toReturn++;
            }
        }

        return toReturn;
    }

    public static void main(String[] args) {

        System.out.println(new MaximumEvents().maxEvents(new int[][]{
                new int[]{1, 1},
                new int[]{2, 2},
                new int[]{3, 3}}));
        System.out.println(new MaximumEvents().maxEvents(new int[][]{
                new int[]{1, 2},
                new int[]{2, 2},
                new int[]{3, 3}}));
        System.out.println(new MaximumEvents().maxEvents(new int[][]{
                new int[]{1, 2}}));
        System.out.println(new MaximumEvents().maxEvents(new int[][]{
                new int[]{1, 10},
                new int[]{2, 2},
                new int[]{2, 3}}));
        System.out.println(new MaximumEvents().maxEvents(new int[][]{
                new int[]{1, 2},
                new int[]{2, 2},
                new int[]{3, 3},
                new int[]{3, 4},
                new int[]{3, 4}}));
    }
}
