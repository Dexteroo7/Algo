import static java.lang.Integer.*;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/submissions/
class SellStockTwice {
    public int maxProfit(int[] prices) {

        int t1Cost = MAX_VALUE;
        int t2Cost = MAX_VALUE;
        int t1Profit = MIN_VALUE;
        int t2Profit = MIN_VALUE;

        for (int price : prices) {

            t1Cost = min(t1Cost, price);
            t1Profit = max(t1Profit, price - t1Cost);
            t2Cost = min(t2Cost, price - t1Profit);
            t2Profit = max(t2Profit, price - t2Cost);
        }

        return t2Profit;
    }
}
