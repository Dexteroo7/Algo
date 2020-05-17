import java.util.Arrays;

class Min_cost_ticket {
    public int mincostTickets(int[] days, int[] costs) {

        Arrays.sort(days);
        return mincostTickets(days, -1, 0, costs, new int[days.length]);
    }

    public int mincostTickets(int[] days, int clearTill, int index, int[] costs, int[] memo) {

        if (index >= days.length)
            return 0;

        if (clearTill >= days[index])
            return mincostTickets(days, clearTill, index + 1, costs, memo);

        if (memo[index] > 0)
            return memo[index];

        //get-1-day
        int costA = costs[0] + mincostTickets(days, days[index], index + 1, costs, memo);
        //get-7-day
        int costB = costs[1] + mincostTickets(days, days[index] + 6, index + 1, costs, memo);
        //get-30-day
        int costC = costs[2] + mincostTickets(days, days[index] + 29, index + 1, costs, memo);

        return memo[index] = Integer.min(costA, Integer.min(costB, costC));
    }
}
