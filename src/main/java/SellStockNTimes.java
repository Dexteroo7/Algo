import java.util.Arrays;

import static java.lang.Integer.*;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
class SellStockNTimes {

    public int maxProfit(int k, int[] prices) {

        if (k == 0 || prices.length < 2)
            return 0;

        if (k >= prices.length)
            return infiniteTransactions(prices);

        int[] purchaseCost = new int[k];
        Arrays.fill(purchaseCost, MAX_VALUE);
        int[] profit = new int[k];

        for (int price : prices) {

            purchaseCost[0] = min(purchaseCost[0], price);
            profit[0] = max(profit[0], price - purchaseCost[0]);

            for (int i = 1; i < purchaseCost.length; i++) {

                purchaseCost[i] = min(purchaseCost[i], price - profit[i - 1]);
                profit[i] = max(profit[i], price - purchaseCost[i]);
            }
        }

        return profit[k - 1];
    }

    public int infiniteTransactions(int[] prices) {

        int profit = 0;
        int boughtAt = prices[0];

        for (int i = 1; i < prices.length; i++) {

            if (prices[i] < boughtAt)
                boughtAt = prices[i];
            else {
                profit += prices[i] - boughtAt;
                boughtAt = prices[i];
            }
        }

        return profit;
    }

    public static void main(String[] args) {

    }
}
